
package com.joymain.jecs.pm.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmCouponInfoDao;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import yspay.util.StringUtil;

public class JpmCouponInfoDaoHibernate extends BaseDaoHibernate implements JpmCouponInfoDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponInfoDao#getJpmCouponInfos(com.joymain.jecs.pm.model.JpmCouponInfo)
     */
    public List getJpmCouponInfos(final JpmCouponInfo jpmCouponInfo) {
        return getHibernateTemplate().find("from JpmCouponInfo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmCouponInfo == null) {
            return getHibernateTemplate().find("from JpmCouponInfo");
        } else {
            // filter on properties set in the jpmCouponInfo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmCouponInfo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmCouponInfo.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponInfoDao#getJpmCouponInfo(BigDecimal cpId)
     */
    public JpmCouponInfo getJpmCouponInfo(final long cpId) {
        JpmCouponInfo jpmCouponInfo = (JpmCouponInfo) getHibernateTemplate().get(JpmCouponInfo.class, cpId);
        if (jpmCouponInfo == null) {
            log.warn("uh oh, jpmCouponInfo with cpId '" + cpId + "' not found...");
            throw new ObjectRetrievalFailureException(JpmCouponInfo.class, cpId);
        }

        return jpmCouponInfo;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponInfoDao#saveJpmCouponInfo(JpmCouponInfo jpmCouponInfo)
     */    
    public void saveJpmCouponInfo(final JpmCouponInfo jpmCouponInfo) {
        getHibernateTemplate().saveOrUpdate(jpmCouponInfo);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponInfoDao#removeJpmCouponInfo(BigDecimal cpId)
     */
    public void removeJpmCouponInfo(final long cpId) {
        getHibernateTemplate().delete(getJpmCouponInfo(cpId));
    }

    public List getJpmCouponInfosByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmCouponInfo jpmCouponInfo where 1=1 ";

//    	String cpname=crm.getString("cpname", "");
//    	if(StringUtils.isNotEmpty(cpname)){
//    		hql+=" and jpmCouponInfo.cpName like'%"+cpname+"%'";
//    	}
    	//代金券面额，暂时屏蔽
//    	String cpvalue=crm.getString("cpvalue", "");
//    	if(StringUtils.isNotEmpty(cpvalue)){
//    		hql+=" and jpmCouponInfo.cpValue='"+cpvalue+"'";
//    	}
    	
    	String status=crm.getString("status", "");
    	if(StringUtils.isNotEmpty(status)){
    		hql+=" and jpmCouponInfo.status='"+status+"'";
    	}

    	String startCreateTime=crm.getString("startCreateTime","");
    	if(StringUtils.isNotEmpty(startCreateTime)){
    		hql+=" and jpmCouponInfo.createTime>= to_date('"+startCreateTime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
    	}
    	
    	String endCreateTime=crm.getString("endCreateTime","");  	
    	if(StringUtils.isNotEmpty(endCreateTime)){
    		hql+=" and jpmCouponInfo.createTime<= to_date('"+endCreateTime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
    	}
    	String startUpdateTime=crm.getString("startUpdateTime","");
    	if(StringUtils.isNotEmpty(startUpdateTime)){
    		hql+=" and jpmCouponInfo.updateTime>= to_date('"+startUpdateTime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
    	}
    	
    	String endUpdateTime=crm.getString("endUpdateTime","");  	
    	if(StringUtils.isNotEmpty(endUpdateTime)){
    		hql+=" and jpmCouponInfo.updateTime<= to_date('"+endUpdateTime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
    	}
    	//add by lihao
    	//代金券名名称
    	String cpName = crm.getString("cpName","");
    	if(StringUtils.isNotEmpty(cpName)){
    		hql += " and jpmCouponInfo.cpName like '%" +cpName.trim() +"%' ";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by cpId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    public int batchAuditCouponInfo(String cpId,String status,String userCode){
		StringBuffer sqlBuf = new StringBuffer();
		if(cpId!=null){
			cpId = cpId.substring(0, cpId.length()-1);
			sqlBuf.append(" update JpmCouponInfo jpmCouponInfo set jpmCouponInfo.status='");
			sqlBuf.append(status);
			sqlBuf.append("', "); 
			sqlBuf.append(" jpmCouponInfo.updateTime=sysdate, ");	
			sqlBuf.append(" jpmCouponInfo.updateUserCode='");
			sqlBuf.append(userCode);
			sqlBuf.append("' where jpmCouponInfo.cpId in('");
			sqlBuf.append(cpId.replace(",", "','"));
			sqlBuf.append("') ");
		} 
		return this.executeUpdate(sqlBuf.toString());
    }
    
    /**
     * 统计reportDate当天代金券的使用面额总数
     * 2017-7-17 
     * @param reportDate 时间（天）
     * @return Map
     */
	public Map getJpmCouponInfoCpValueS(String reportDate){
		
		if(!StringUtil.isEmpty(reportDate)){
			    log.info("在类JpmCouponInfoDaoHibernate的方法getJpmCouponInfoCpValueS中执行：代金券面额总数");
				String sql = " select sum(CP_VALUE) as cpvalueSum from JPO_COUPON_RELATIONSHIP a,JPO_USER_COUPON b,JPM_COUPON_INFO c where a.cp_id = b.id and b.cp_id=c.cp_id  " +
						" and a.create_time >=to_date('"+reportDate+" 00:00:00','yyyy/mm/dd hh24:mi:ss') " +
						" and a.create_time <=to_date('"+reportDate+" 23:59:59','yyyy/mm/dd hh24:mi:ss') ";
				/*List list = this.findObjectsBySQL(sql);
				if(null!=list){
					if(list.size()>0){
						return list;
					}
				}*/
				Map map = this.jdbcTemplate.queryForMap(sql);
				if(null!=map){
					if(map.size()>0){
						return map;
					}
				}
		 }
		
		return null;
	}
	
	/**
     * 统计reportDate当天订单实际使用的代金券总额
     * 2017-7-17
     * @param reportDate 时间（天）
     * @return Map
     */
	public Map getJpoMemberOrderCpValueS(String reportDate){
		if(!StringUtil.isEmpty(reportDate)){
		    log.info("在类JpmCouponInfoDaoHibernate的方法getJpoMemberOrderCpValueS中执行");
			String sql = " select sum(cp_value) as cpvalueSum  from jpo_member_order where STATUS=2 and IS_PAY=1 " +
					" and CHECK_TIME>=to_date('"+reportDate+" 00:00:00','yyyy/mm/dd hh24:mi:ss') " +
					" and CHECK_TIME<=to_date('"+reportDate+" 23:59:59','yyyy/mm/dd hh24:mi:ss') ";
			Map map = this.jdbcTemplate.queryForMap(sql);
			if(null!=map){
				if(map.size()>0){
					return map;
				}
			}
		}
		return null;
	}
	
	/**
     * 统计截止到reportDate那天会员使用或未用代金券的总额
     * @param reportDate jsp传到后台的时间（天）
     * @param status 状态 0：未用  1：已用
     * @return Map
     */
	public Map getJpmCoumponInfoCpValueS(String reportDate, String status){
		if((!StringUtil.isEmpty(reportDate))&&(!StringUtil.isEmpty(status))){
	    	    log.info("在类JpmCouponInfoDaoHibernate的方法getJpmCoumponInfoCpValueS中开始运行");
				String sql = " select sum(b.cp_value) as cpvalueSum from JPO_USER_COUPON a,JPM_COUPON_INFO b where a.cp_id=b.cp_id " +
						" and a.status='"+status+"' and a.CREATE_TIME<=to_date('"+reportDate+" 23:59:59','yyyy/mm/dd hh24:mi:ss') " ;
				
				/*String sql = " select sum(b.cp_value) as cpvalueSum from JPO_USER_COUPON a,JPM_COUPON_INFO b where a.cp_id=b.cp_id and b.status=0 and ( a.able_status='Y' or a.able_status is null ) " +
						" and a.status='"+status+"' and a.CREATE_TIME<=to_date('"+reportDate+" 23:59:59','yyyy/mm/dd hh24:mi:ss') " ;*/
				Map map = this.jdbcTemplate.queryForMap(sql);
				if(null!=map){
					if(map.size()>0){
						return map;
					}
				}
		 }
		
		return null;
	}

	@Override
	public JpmCouponInfo getByCouponName(String name) {
		String hql="from JpmCouponInfo jpmCouponInfo where 1=1 and cpName=?";
		List find = this.getHibernateTemplate().find(hql,name);
		if (find.size()!=0) {
			return (JpmCouponInfo) find.get(0);
		}else{
			return null;
		}
		
	}
	

	@Override
	public boolean serachJpmCouponInfosByParams(CommonRecord crm) {
    	String hql = "from JpmCouponInfo jpmCouponInfo where 1=1 ";
    	String cpName=crm.getString("cpName","");
    	String cpValue=crm.getString("cpValue","");
    	if(StringUtils.isNotEmpty(cpName)&&StringUtils.isNotEmpty(cpValue)){
    		hql+=" and (jpmCouponInfo.cpName ='"+cpName+"' or jpmCouponInfo.cpValue='"+cpValue+"')";
    	}else{
    	if(StringUtils.isNotEmpty(cpName)){
    		hql+=" and jpmCouponInfo.cpName ='"+cpName+"'";
    	}

    	if(StringUtils.isNotEmpty(cpValue)){
    		hql+=" and jpmCouponInfo.cpValue='"+cpValue+"'";
    	}
    	}
    	List jpmCouponInfoList=this.findObjectsByHqlQuery(hql);
    	if(null!=jpmCouponInfoList&&jpmCouponInfoList.size()>0){
    		return true;
    	}
		return false;
	}
	
	/**
     * 获取reportDate当天升级单赠送的代金券的总面额(当天赠送的代金券是否使用，都统计出来）
     * modify by fu 2017-7-19 
     * @param reportDate jsp传到后台的时间（天）
     * @return Map
     */
	public Map getUpgradeSheetCpValueS(String reportDate){
		
		if(!StringUtil.isEmpty(reportDate)){
	    	    log.info("在类JpmCouponInfoDaoHibernate的方法getUpgradeSheetCpValueS中开始运行");
				String sql = " select sum(b.cp_value) as cpvalueSum from JPO_USER_COUPON a,JPM_COUPON_INFO b where a.cp_id=b.cp_id and b.status=0 and ( a.able_status='Y' or a.able_status is null ) " +
						" and a.CREATE_TIME>=to_date('"+reportDate+" 00:00:00','yyyy/mm/dd hh24:mi:ss')  " +
						" and a.CREATE_TIME<=to_date('"+reportDate+" 23:59:59','yyyy/mm/dd hh24:mi:ss') " ;
				Map map = this.jdbcTemplate.queryForMap(sql);
				if(null!=map){
					if(map.size()>0){
						return map;
					}
				}
	   }
	   return null;
	}
	
	/**
	 * 统计截止到reportDate之前的时间赠送的代金券，但是这部分代金券在reportDate时间之后使用的
	 * modify by fu 2017-7-27
	 * @param reportDate 时间（天）
	 * @param status 状态
	 * @return Map
	 */
	public Map getJpmCoumponInfoReportDateYsy(String reportDate, String status){
		if(!StringUtil.isEmpty(reportDate)){
			log.info("在类JpmCouponInfoDaoHibernate的方法getJpmCoumponInfoReportDateYsy中开始运行");
			/*select sum(CP_VALUE) as cpvalueSum
			   from jecs.JPO_COUPON_RELATIONSHIP a, jecs.JPO_USER_COUPON b, jecs.JPM_COUPON_INFO c
			  where a.cp_id = b.id
			    and b.cp_id = c.cp_id
			    and b.create_time <=
			        to_date('2017-7-24 23:59:59', 'yyyy/mm/dd hh24:mi:ss') 
			    and a.create_time>to_date('2017-7-24 23:59:59', 'yyyy/mm/dd hh24:mi:ss');  */  
		    String sql = " select sum(CP_VALUE) as cpvalueSum from JPO_COUPON_RELATIONSHIP a, JPO_USER_COUPON b, JPM_COUPON_INFO c " +
		    		" where a.cp_id = b.id and b.cp_id = c.cp_id " +
		    		" and b.create_time <=to_date('"+reportDate+" 23:59:59', 'yyyy/mm/dd hh24:mi:ss') " +
		    		" and a.create_time>to_date('"+reportDate+" 23:59:59', 'yyyy/mm/dd hh24:mi:ss')";	
		    Map map = this.jdbcTemplate.queryForMap(sql);
			if(null!=map){
				if(map.size()>0){
					return map;
				}
			}
		}
		
		return null;
	
	}
	
	
}
