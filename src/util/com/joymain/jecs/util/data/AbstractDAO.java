package com.joymain.jecs.util.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joymain.jecs.util.exception.AppException;

public class AbstractDAO extends HibernateDaoSupport {

	protected JdbcTemplate jdbcTemplate;
	protected JdbcTemplate jdbcTemplateReport;
	
	

	public JdbcTemplate getJdbcTemplateReport() {
		return jdbcTemplateReport;
	}

	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}

	public void setJdbcTemplate(JdbcTemplate dao) {
		this.jdbcTemplate = dao;
	}

	/**
	 * 根据关键字从数据库加载指定类型的业务对象
	 * 
	 * @param clazz 业务对象的Class
	 * @param keyName 指定关键字对应的字段名称
	 * @param keyValue 指定关键字的值
	 * @return 当关键字唯一并存在该记录时,返回该记录对应的业务对象;当关键字不唯一,返回查询结果的第一条记录所对应的业务对象;当不存在该记录时,返回null
	 * @throws AppException
	 */
	public Object loadByKey(Class clazz, String keyName, Object keyValue) throws AppException {
		try {
			List result = getHibernateTemplate().find("from " + clazz.getName() + " where " + keyName + " = ?", keyValue);
			if (result != null && result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (DataAccessException e) {
			logger.error("加载 " + keyName + " 为 " + keyValue + " 的 " + clazz.getName() + " 实例失败", e);
			throw new AppException("加载 " + keyName + " 为 " + keyValue + " 的 " + clazz.getName() + " 实例失败", e);
		}
	}

	/**
	 * 根据指定的类型取出对应业务对象的记录集
	 * 
	 * @param clazz
	 * @return 返回查询结果包含的业务对象集合
	 */
	public List findAll(final Class clazz) {
		return getHibernateTemplate().find("from " + clazz.getName());
	}
	/**
	 * 
	 * @param sql
	 */
	 public void excuteSql(String sql){
	    	this.getJdbcTemplate().execute(sql);
	    }
	/**
	 * 通过一个对象实例和分页条件查找记录，返回list列表
	 * 
	 * @param object
	 * @param pager
	 * @return
	 */
	public List findObjects(final Object object, final Pager pager) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Example ex = Example.create(object).ignoreCase().enableLike(MatchMode.ANYWHERE);// 通过一个给定实例
				// 构建一个条件查询
				Criteria ca = session.createCriteria(object.getClass()).add(ex);

				pager.setTotalObjects(((Integer) (ca.setProjection(Projections.rowCount()).uniqueResult())).intValue()); // 获取条件查询总数Count
				ca.setProjection(null);

				pager.calc();

				if (pager.getLimit() == null) {
					ca.setFirstResult(pager.getFirstResult());
					ca.setMaxResults(pager.getPageSize());
				} else {
					ca.setFirstResult(pager.getLimit().getRowStart());
					ca.setMaxResults(pager.getLimit().getRowEnd() - pager.getLimit().getRowStart());
				}

				return ca.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);

	}

	/**
	 * 根据查询语句及页码,分页大小查询数据库并返回查询结果所包含的业务对象集合
	 * 
	 * @param hqlQuery 指定查询语句
	 * @param pager 指定页码
	 * @return 返回查询结果包含的业务对象集合
	 * @throws AppException
	 */
	public List findObjectsByHqlQuery(final String hqlQuery, final Pager pager) throws AppException {
		logger.debug(hqlQuery);
		try {
			return (List) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					long startTime = System.currentTimeMillis();
					Query query = session.createQuery(hqlQuery);
					// 获取总记录数 -- 目前查询出所有记录,需要改进
					int totalCount = getTotalCountByHQL(hqlQuery);
					if (pager != null && pager.getPageSize() > 0) {
						pager.setTotalObjects(totalCount);
						pager.calc();
						if (pager.getLimit() == null) {
							query.setFirstResult(pager.getFirstResult());
							query.setMaxResults(pager.getPageSize());
						} else {
							query.setFirstResult(pager.getLimit().getRowStart());
							query.setMaxResults(pager.getLimit().getRowEnd() - pager.getLimit().getRowStart());
						}
					}

					List items = query.list();
					logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
					return items;
				}
			}, true);
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + hqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + hqlQuery, e);
		}
	}

	/**
	 * 根据查询语句返回默认pager的查询结果所包含的业务对象集合
	 * 
	 * @param hqlQuery 指定查询语句
	 * @throws AppException
	 */
	public List findObjectsByHqlQuery(final String hqlQuery) throws AppException {
		Pager pager = new Pager();
		pager.setPageSize(0);
		pager.setFirstResult(0);
		return findObjectsByHqlQuery(hqlQuery, pager);
	}

	/**
	 * 根据HQL语句查询并返回第一条对象
	 * 
	 * @param hqlQuery String
	 * @return Object
	 */
	public Object getObjectByHqlQuery(final String hqlQuery) {
		logger.debug(hqlQuery);
		try {
			return (Object) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					long startTime = System.currentTimeMillis();
					Query query = session.createQuery(hqlQuery);
					// 获取总记录数 -- 目前查询出所有记录,需要改进
					query.setFirstResult(0);
					query.setMaxResults(1);

					List items = query.list();
					logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
					if (items != null && items.size() > 0) {
						return items.get(0);
					} else {
						return null;
					}
				}
			}, true);
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + hqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + hqlQuery, e);
		}
	}

	/**
	 * 根据类名分页查询此类对应的数据
	 * 
	 * @param clazz Class
	 * @param pager Pager
	 * @return List
	 */
	public List findObjectsByClass(final Class clazz, final Pager pager) {
		String hqlQuery = "from " + clazz.getName();
		return findObjectsByHqlQuery(hqlQuery, pager);
	}

	/**
	 * 将查询语句转换为包含count查询数据库并返回结果的记录总数
	 * 
	 * @param hqlQuery 指定查询语句
	 * @return 返回记录总数
	 * @throws AppException
	 */
	public int getTotalCountByHQL(final String hqlQuery) throws AppException {
		// logger.debug(hqlQuery);
		try {
			return ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					int totalCount = 0;
					Query query = null;

					query = session.createQuery(getHqlCountString(hqlQuery)); // 查询符合条件数据
					List list = query.list();
					if (!list.isEmpty()) {
						totalCount = Integer.parseInt(list.get(0).toString());
					}
					return new Integer(totalCount);
				}
			}, true)).intValue();
		} catch (DataAccessException e) {
			logger.error("执行汇总查询失败, 查询语句为: " + hqlQuery, e);
			throw new AppException("执行汇总查询失败, 查询语句为: " + hqlQuery, e);
		}
	}

	/**
	 * 执行更新或删除的HQL语句
	 * 
	 * @param hqlQuery String
	 * @return int 所影响的记录总数
	 */
	public int executeUpdate(final String hqlQuery) {
		logger.debug(hqlQuery);
		try {
			return ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					int recordsCount = session.createQuery(hqlQuery).executeUpdate();
					return new Integer(recordsCount);
				}
			}, true)).intValue();
		} catch (DataAccessException e) {
			logger.error("执行更新语句失败, 更新语句为: " + hqlQuery, e);
			throw new AppException("执行更新语句失败, 更新语句为: " + hqlQuery, e);
		}
	}

	/**
	 * author cfy 根据hql语句，查询总条数
	 * 
	 * @param hql String
	 * @return long
	 */
	public long getTotalByHql(final String hql) {
		logger.debug(hql);
		try {
			return ((Long) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					long totalCount = 0;
					totalCount = ((Long) session.createQuery(hql).iterate().next()).longValue();
					return new Long(totalCount);
				}
			}, true)).longValue();
		} catch (DataAccessException e) {
			logger.error("执行汇总查询失败, 查询语句为: " + hql, e);
			throw new AppException("执行汇总查询失败, 查询语句为: " + hql, e);
		}
	}

	// ====================SQL 相关方法 =======================//

	/**
	 * 根据查询语句及页码,分页大小查询数据库并返回查询结果所包含的业务对象集合
	 * 
	 * @param sqlQuery 指定查询的SQL语句
	 * @param pager 分页对象
	 * @return 返回查询结果包含的业务对象集合
	 * @throws AppException
	 */
	public List findObjectsBySQL(final String sqlQuery, final Pager pager) throws AppException {
		logger.debug(sqlQuery);
		try {
			int totalCount = this.getTotalCountBySQL(sqlQuery);
			pager.setTotalObjects(totalCount);

			String limitString = sqlQuery;
			if (pager != null && pager.getPageSize() > 0) {

				pager.calc();

				if (pager.getLimit() == null) {
					limitString = getLimitString(sqlQuery, (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
				} else {
					limitString = getLimitString(sqlQuery, pager.getLimit().getRowStart(), pager.getLimit().getRowEnd() - pager.getLimit().getRowStart());
				}
			}
			

			long startTime = System.currentTimeMillis();
			List recordSet = (List) this.jdbcTemplate.query(limitString, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					return buildSqlResultList(rs);
				}
			});
			logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
			return recordSet;
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}

	/**
	 * 根据查询语句查询数据库并返回查询结果所包含的业务对象集合
	 * 
	 * @param sqlQuery 指定查询的SQL语句
	 * @return 返回查询结果包含的业务对象集合
	 * @throws AppException
	 */
	public List findObjectsBySQL(final String sqlQuery) throws AppException {
		logger.debug(sqlQuery);
		try {
			long startTime = System.currentTimeMillis();
			List result=(List) this.jdbcTemplate.query(sqlQuery, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					return buildSqlResultList(rs);
				}
			});
			logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
			return result;
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}
	
	/**
	 * 获取SQL对应的前几行的记录.(单纯提取前几行时,使用此方法提高性能)
	 * @param sqlQuery
	 * @param count
	 * @return
	 */
	public List findTopObjectsBySQL(final String sqlQuery, final int count){
		String tmpQuery="select * from ("+sqlQuery+") where rownum <="+count;
		logger.debug(tmpQuery);
		try {
	        long startTime = System.currentTimeMillis();
	        List result = (List) this.jdbcTemplate.query(tmpQuery, new ResultSetExtractor() {
		        public Object extractData(ResultSet rs) throws SQLException {
			        return buildSqlResultList(rs);
		        }
	        });
	        logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
	        return result;
        } catch (DataAccessException e) {
        	logger.error("执行查询失败, 查询语句为: " + tmpQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + tmpQuery, e);
        }
	}
	public List findTopObjectsBySQLReport(final String sqlQuery, final int count){
		String tmpQuery="select * from ("+sqlQuery+") where rownum <="+count;
		logger.debug(tmpQuery);
		try {
	        long startTime = System.currentTimeMillis();
	        List result = (List) this.jdbcTemplateReport.query(tmpQuery, new ResultSetExtractor() {
		        public Object extractData(ResultSet rs) throws SQLException {
			        return buildSqlResultList(rs);
		        }
	        });
	        logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
	        return result;
        } catch (DataAccessException e) {
        	logger.error("执行查询失败, 查询语句为: " + tmpQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + tmpQuery, e);
        }
	}

	/**
	 * 根据SQL查询语句和RowMapperr查询
	 * 
	 * @param sqlQuery
	 * @param rowMapper
	 * @return
	 * @throws AppException
	 */
	public List findObjectsBySQLAndRowMapper(final String sqlQuery, final RowMapper rowMapper) throws AppException {
		logger.debug(sqlQuery);
		try {
			return (List) this.jdbcTemplate.query(sqlQuery, rowMapper);
		} catch (Exception e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}

	/**
	 * 根据SQL查询语句和RowMapperr进行分页查询
	 * @param sqlQuery
	 * @param rowMapper
	 * @param pager
	 * @return
	 * @throws AppException
	 */
	public List findObjectsBySQL(final String sqlQuery, final RowMapper rowMapper, final Pager pager) throws AppException {
		logger.debug(sqlQuery);
		try {
			int totalCount = this.getTotalCountBySQL(sqlQuery);
			pager.setTotalObjects(totalCount);

			String limitString = sqlQuery;
			if (pager != null && pager.getPageSize() > 0) {
				pager.calc();
				if (pager.getLimit() == null) {
					limitString = getLimitString(sqlQuery, (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
				} else {
					limitString = getLimitString(sqlQuery, pager.getLimit().getRowStart(), pager.getLimit().getRowEnd() - pager.getLimit().getRowStart());
				}
			}

			List recordSet = (List) this.jdbcTemplate.query(limitString, rowMapper);
			return recordSet;
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}

	/**
	 * 查询数据库并返回第一条记录
	 * 
	 * @param sqlQuery String 指定查询的SQL语句
	 * @return CommonRecord 返回查询结果
	 * @throws AppException
	 */
	public HashMap findOneObjectBySQL(final String sqlQuery) throws AppException {
		logger.debug(sqlQuery);
		HashMap crm = null;
		try {
			long startTime = System.currentTimeMillis();
			List recordSet = this.findTopObjectsBySQL(sqlQuery, 1);
			if (recordSet != null && recordSet.size() > 0) {
				crm = (HashMap) recordSet.get(0);
			}
			logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
			return crm;
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}

	public HashMap findOneObjectBySQLReport(final String sqlQuery) throws AppException {
		logger.debug(sqlQuery);
		HashMap crm = null;
		try {
			long startTime = System.currentTimeMillis();
			List recordSet = this.findTopObjectsBySQLReport(sqlQuery, 1);
			if (recordSet != null && recordSet.size() > 0) {
				crm = (HashMap) recordSet.get(0);
			}
			logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
			return crm;
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}
	/**
	 * 根据SQL语句查询并得到记录总数
	 * 
	 * @param sqlQuery String
	 * @return int
	 */
	public int getTotalCountBySQL(final String sqlQuery) {
		logger.debug(sqlQuery);
		return this.jdbcTemplate.queryForInt(getSqlCountString(sqlQuery));
	}

	// ====================相关工具 =======================//
	/**
	 * 将SQL语句转化为分页的SQL语句
	 * 
	 * @param queryString 指定的SQL语句
	 * @param offset 需要读取的第一条数据的偏移量
	 * @param limit 需要读取的数据数
	 * @return String 生成的SQL语句
	 */
	public String getLimitString(String queryString, int offset, int limit) {
		queryString = queryString.trim();
		boolean isForUpdate = false;
		if (queryString.toLowerCase().endsWith(" for update")) {
			queryString = queryString.substring(0, queryString.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(queryString.length() + 100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(queryString);
		pagingSelect.append(" ) row_ where rownum <= " + (offset + limit) + ") where rownum_ > " + offset);

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

	/**
	 * 生成查询总记录数的HQL语句
	 * 
	 * @param queryString 指定的查询语句
	 * @return 查询总记录数的SQL语句
	 */
	public String getHqlCountString(String queryString) {
		// 判断queryString最后是否以）结束
		/*
		 * String flag = ""; String tmpStr = queryString.trim(); if(")".equals(tmpStr.substring(tmpStr.length()-1)) && (tmpStr.toLowerCase().indexOf("order")>0 || tmpStr.toLowerCase().indexOf("group")>0)){ flag = ")"; }
		 */

		// 去除order by语句
		// 生成Pattern对象并且编译正则表达式" order by "
		Pattern p = Pattern.compile("\\s+order+\\s+by+\\s+.+", Pattern.CASE_INSENSITIVE);
		// 用Pattern类的matcher()方法生成一个Matcher对象
		Matcher m = p.matcher(queryString);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		// 使用find()方法查找第一个匹配的对象
		boolean result = m.find();
		// 使用循环将句子里所有符合条件的找出并替换再将内容加到sb里
		while (result) {
			i++;
			m.appendReplacement(sb, "");
			// 继续查找下一个匹配对象
			result = m.find();
		}
		// 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
		m.appendTail(sb);
		queryString = sb.toString();
		// 去除结束
		int start = queryString.toLowerCase().indexOf("from ");
		return "select count(*) from " + queryString.substring(start + 4); /* flag */
	}

	/**
	 * 生成查询总记录数的SQL语句
	 * 
	 * @param queryString 指定的查询语句
	 * @return 查询总记录数的SQL语句
	 */
	public String getSqlCountString(String queryString) {
		return "select count(*) from (" + queryString + ")";
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 获取表中某个字段所对应的备注描述
	 * 
	 * @param tableName String
	 * @param fieldName String
	 * @return String
	 */
	public String getFieldComment(final String tableName, final String fieldName) {
		HashMap crm = this.findOneObjectBySQL("select comments from user_COL_COMMENTS where table_name='" + tableName.toUpperCase() + "' and column_name='" + fieldName.toUpperCase() + "'");
		if(crm==null || crm.get("comments")==null){
			return "";
		}else{
			return crm.get("comments").toString();
		}
	}

	/**
	 * 根据SQL查询所返回的RS生成对应的List
	 * 
	 * @param rs
	 * @return
	 */
	public List<HashMap<String, String>> buildSqlResultList(ResultSet rs) {
		List<HashMap<String, String>> presidents = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData mData = rs.getMetaData();
			int fieldCount = mData.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> president = new HashMap<String, String>();
				for (int i = 1; i <= fieldCount; i++) {
					String fieldName = mData.getColumnName(i);
					int fieldType = mData.getColumnType(i);
					String fieldValue = null;
					switch (fieldType) {
						case Types.DATE:
						case Types.TIME:
						case Types.TIMESTAMP:
							fieldValue = rs.getString(i);
							if (fieldValue == null) {
								fieldValue = "";
							} else if (fieldValue.indexOf(".") >= 0) {// oracle日期处理
								fieldValue = fieldValue.substring(0, fieldValue.indexOf("."));
							}
							break;
						default:
							fieldValue = rs.getString(i);
							if (fieldValue == null)
								fieldValue = "";
							break;
					}

					president.put(fieldName.toLowerCase(), fieldValue);
				}
				presidents.add(president);
			} // while
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {  
			JdbcUtils.closeResultSet(rs);
		}
		return presidents;
	}
	public List findObjectsBySQLTemplateReport(final String sqlQuery) throws AppException {
		logger.debug(sqlQuery);
		try {
			long startTime = System.currentTimeMillis();
			List result=(List) this.jdbcTemplateReport.query(sqlQuery, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					return buildSqlResultList(rs);
				}
			});
			logger.debug("数据库查询耗时:" + (System.currentTimeMillis() - startTime));
			return result;
		} catch (DataAccessException e) {
			logger.error("执行查询失败, 查询语句为: " + sqlQuery, e);
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}
}
