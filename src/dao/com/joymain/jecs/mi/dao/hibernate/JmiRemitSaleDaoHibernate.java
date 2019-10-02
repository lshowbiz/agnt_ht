package com.joymain.jecs.mi.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;

import com.ibm.icu.text.SimpleDateFormat;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.dao.JmiRemitSaleDao;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JmiRemitSaleDaoHibernate extends BaseDaoHibernate implements JmiRemitSaleDao {
	public JmiRemitSaleDaoHibernate() {
	}

	@Override
	// 保存 免重消用戶
	public int saveJmiRemitSale(JmiRemitSale jmiRemitSale) throws SQLException {
		int i = 0;
		String sql = "INSERT into WS_TP_MEMBER(USER_CODE,START_WEEK,END_WEEK"
				+ ",CREATE_TIME,REMARK,CREATE_USER,ID) values(?,?,?,TO_DATE(?,'yyyy-mm-dd hh24:mi'),?,?,?)";

		i = jdbcTemplate.update(sql,
				new Object[] { jmiRemitSale.getUserCode(), jmiRemitSale.getStartWeek(), jmiRemitSale.getEndWeek(),
						this.getDate(), jmiRemitSale.getRemark(), jmiRemitSale.getCreateUser(),
						this.getNextSequence() });
		return i;
	}

	@Override
	// 根據用戶號碼刪除一個用戶
	public void deleJmiRemitSaleByuserCode(String userCode) throws SQLException {

		String sql = "DELETE from WS_TP_MEMBER where USER_CODE =?";

		jdbcTemplate.update(sql, new Object[] { userCode });
	}

	@Override
	// 根據用戶id刪除一個用戶
	public int deleJmiRemitSaleById(String id) throws SQLException {
		String sql = "DELETE from WS_TP_MEMBER where ID = ?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	// 根据用户ID更新用戶信息 不包括用户ID和USER_CODE
	public int updateJmiRemitSaleById(BigDecimal oldjmiRemitSaleId, JmiRemitSale newjmiRemitSale) throws SQLException {
		int i = 0;
		/*
		 * private String userCode; private BigDecimal startWeek; private
		 * BigDecimal endWeek; private String createTime; private String remark;
		 * private String createUser;
		 */
		String sql = "UPDATE WS_TP_MEMBER set START_WEEK = ?,END_WEEK =?"
				+ ",CREATE_TIME =TO_DATE(?,'yyyy-mm-dd hh24:mi') ,REMARK=?,CREATE_USER=? where ID = '"
				+ oldjmiRemitSaleId.toString() + "'";

		i = jdbcTemplate.update(sql, new Object[] { newjmiRemitSale.getStartWeek(), newjmiRemitSale.getEndWeek(),
				this.getDate(), newjmiRemitSale.getRemark(), newjmiRemitSale.getCreateUser() });
		return i;
	}

	@Override
	// 根据用户USER_CODE更新用戶信息 不包括用户ID和USER_CODE
	public void updateJmiRemitSaleByUserCode(String oldUserCode, JmiRemitSale newjmiRemitSale) throws SQLException {
		String sql = "UPDATE WS_TP_MEMBER set START_WEEK = ?,END_WEEK =?" + ",REMARK=?,CREATE_USER=? where USER_CODE= '"
				+ oldUserCode + "'";
		jdbcTemplate.update(sql, new Object[] { newjmiRemitSale.getStartWeek(), newjmiRemitSale.getEndWeek(),
				newjmiRemitSale.getRemark(), newjmiRemitSale.getCreateUser() });
	}

	@Override
	// 根据用户ID返回用户记录，唯一
	public JmiRemitSale findJmiRemitSaleById(BigDecimal id) throws SQLException {
		JmiRemitSale jmiRemitSale = new JmiRemitSale();
		String sql = "SELECT * from WS_TP_MEMBER  where ID = '" + id.toString() + "'";
		List rows = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map jmiMap = (Map) rows.get(i);
			jmiRemitSale.setUserCode((String) jmiMap.get("USER_CODE"));
			jmiRemitSale.setStartWeek((BigDecimal) jmiMap.get("START_WEEK"));
			jmiRemitSale.setEndWeek((BigDecimal) jmiMap.get("END_WEEK"));
			jmiRemitSale.setCreateTime(jmiMap.get("CREATE_TIME").toString());
			jmiRemitSale.setRemark((String) jmiMap.get("REMARK"));
			jmiRemitSale.setCreateUser((String) jmiMap.get("CREATE_USER"));
			jmiRemitSale.setId((BigDecimal) jmiMap.get("ID"));
			return jmiRemitSale; // 唯一，退出循环
		}
		return null;
	}

	@Override
	// 根据用户USER_CODE返回用户记录，唯一
	public JmiRemitSale findJmiRemitSaleByUserCode(String userCode) throws SQLException {
		JmiRemitSale jmiRemitSale = new JmiRemitSale();
		String sql = "SELECT * from WS_TP_MEMBER  where USER_CODE = '" + userCode + "'";
		// jdbcTemplate.queryForList(sql) 返回数据带有元数据
		List rows = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map jmiMap = (Map) rows.get(i);
			jmiRemitSale.setUserCode((String) jmiMap.get("USER_CODE"));
			jmiRemitSale.setStartWeek((BigDecimal) jmiMap.get("START_WEEK"));
			jmiRemitSale.setEndWeek((BigDecimal) jmiMap.get("END_WEEK"));
			jmiRemitSale.setCreateTime(jmiMap.get("CREATE_TIME").toString());
			jmiRemitSale.setRemark((String) jmiMap.get("REMARK"));
			jmiRemitSale.setCreateUser((String) jmiMap.get("CREATE_USER"));
			jmiRemitSale.setId((BigDecimal) jmiMap.get("ID"));
			return jmiRemitSale; // 唯一，退出循环
		}
		return null;
	}

	@Override
	// 根据用户USER_CODE返回用户记录，唯一
	public boolean findJmiRemitSaleByUserCode(String userCode, String bool) throws SQLException {

		String sql = "SELECT * from WS_TP_MEMBER  where USER_CODE = '" + userCode + "'";
		// jdbcTemplate.queryForList(sql) 返回数据带有元数据
		List rows = jdbcTemplate.queryForList(sql);
		if (rows.size() > 0) {
			return true; // 唯一，退出循环
		}
		return false;
	}

	public boolean findJmiRemitSaleById(BigDecimal id, String bool) throws SQLException {
		String sql = "SELECT * from WS_TP_MEMBER  where id = '" + id.toString() + "'";
		List rows = jdbcTemplate.queryForList(sql);

		if (rows.size() > 0) {
			return true; // 唯一，退出循环
		}
		return false;
	}

	@Override
	// 查询 返回ListAll<Map<String,Object>>
	public List getJmiMemberTeamsByCrm(CommonRecord crm, Pager pager) throws SQLException {
		// HQL语句
		String hql = "from JmiRemitSale as jmiRemitSale where 1=1  ";

		// 会员编号
		String userCode = crm.getString("userCode", "");
		if (StringUtils.isNotEmpty(userCode)) {
			hql += " and jmiRemitSale.userCode='" + userCode.trim() + "' ";
		}
	
		// 会员编号
		String createTime = crm.getString("createTime", "");
		if (StringUtils.isNotEmpty(createTime)) {

			hql += " and jmiRemitSale.createTime like TO_DATE('" + createTime.trim() + "','yyyy-MM-dd')";

		}
		// 会员编号
		String remark = crm.getString("remark", "");
		if (StringUtils.isNotEmpty(remark)) {
			hql += " and jmiRemitSale.remark='" + remark.trim() + "' ";
		}
		// 会员编号
		String createUser = crm.getString("createUser", "");
		if (StringUtils.isNotEmpty(createUser)) {
			hql += " and jmiRemitSale.createUser='" + createUser.trim() + "' ";
		}

		// 会员编号
		String id = crm.getString("id", "");
		if (StringUtils.isNotEmpty(id)) {
			hql += " and jmiRemitSale.id='" + id.trim() + "' ";
		}

			// 会员编号
		String startWeek = crm.getString("startWeek", "");
		if (StringUtils.isNotEmpty(startWeek)) {
			hql += " and jmiRemitSale.startWeek >= '" + startWeek.trim() + "'";
				
		}
		// 会员编号
		String endWeek = crm.getString("endWeek", "");
		if (StringUtils.isNotEmpty(endWeek)) {
			hql += " and jmiRemitSale.endWeek >= '" + endWeek.trim() + "'";
			
		}
		
	/*	// 会员编号
		String startWeek = crm.getString("startWeek", "");
		if (StringUtils.isNotEmpty(startWeek)) {
			hql += " and jmiRemitSale.startWeek <= '" + startWeek.trim() + "'and jmiRemitSale.endWeek >= '"
					+ startWeek.trim()+"'";
		}
		// 会员编号
		String endWeek = crm.getString("endWeek", "");
		if (StringUtils.isNotEmpty(endWeek)) {
			hql += " and jmiRemitSale.endWeek >= '" + endWeek.trim() + "' and jmiRemitSale.startWeek <= '"
					+ endWeek.trim()+"'";
		}*/
				
		hql += " order by createTime desc ";

		// 分页查询核心
		return this.findObjectsByHqlQuery(hql, pager);

	}

	// 获取主键ID最大值+1
	private int getNextSequence() throws SQLException {
		String sql = " SELECT NVL(MAX(ID),0)+1  FROM WS_TP_MEMBER";
		return jdbcTemplate.queryForInt(sql);
	}

	// 获取当前创建时间
	private String getDate() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return sdf.format(new Date());
	}

	@Override
	public List getJmiMemberTeamsByCrm(CommonRecord crm) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> ajaxStarEndtWeek(String userCode) {

		String sql = " SELECT START_WEEK,END_WEEK FROM WS_TP_MEMBER WHERE USER_CODE = ? ";
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		List rows = jdbcTemplate.queryForList(sql, new Object[] { userCode });
		for (int i = 0; i < rows.size(); i++) {
			Map jmiMap = (Map) rows.get(i);
			map = new HashMap<String, Object>();
			map.put("startWeek", jmiMap.get("START_WEEK"));
			map.put("endWeek", jmiMap.get("END_WEEK"));
			resultList.add(map);
		}
		return resultList;
	}

	public List<Map<String, Object>> ajaxStarEndtWeek(String userCode, String notIncludeID) {

		String sql = " SELECT START_WEEK,END_WEEK FROM WS_TP_MEMBER WHERE USER_CODE = ? and ID != ?";

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		List rows = jdbcTemplate.queryForList(sql, new Object[] { userCode, notIncludeID });

		for (int i = 0; i < rows.size(); i++) {
			Map jmiMap = (Map) rows.get(i);
			map = new HashMap<String, Object>();
			map.put("startWeek", jmiMap.get("START_WEEK"));
			map.put("endWeek", jmiMap.get("END_WEEK"));
			resultList.add(map);
		}
		return resultList;
	}

	public List<Map<String, Object>> ajaxBdPeriodBonus(String bonus, int year) {
		String sql = " SELECT  W_YEAR,W_WEEK FROM Jbd_Period WHERE BONUS_SEND = ? and W_YEAR =? ";
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		List rows = jdbcTemplate.queryForList(sql, new Object[] { bonus, year });
		for (int i = 0; i < rows.size(); i++) {
			Map jmiMap = (Map) rows.get(i);
			map = new HashMap<String, Object>();
			map.put("wYear", jmiMap.get("W_YEAR"));
			map.put("wWeek", jmiMap.get("W_WEEK"));
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> ajaxVerifyJMIMEMBER(String userCode) {
		// TODO Auto-generated method stub

		String sql = " SELECT USER_CODE FROM JMI_MEMBER WHERE USER_CODE = ?";
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		List rows = jdbcTemplate.queryForList(sql, new Object[] { userCode });
		for (int i = 0; i < rows.size(); i++) {
			Map jmiMap = (Map) rows.get(i);
			map = new HashMap<String, Object>();
			map.put("userCode", jmiMap.get("USER_CODE"));

			resultList.add(map);
		}
		return resultList;
	}

	public int updateEndWeek(int bdPeriod, String id) {
		String sql = "UPDATE WS_TP_MEMBER set END_WEEK=? where ID = ?";

		return jdbcTemplate.update(sql, new Object[] { bdPeriod, id });
	}

	public int updateRemarkById(BigDecimal id, String remark) {
		String sql = "UPDATE WS_TP_MEMBER set REMARK=? where ID = ?";
		int flag = 0;
		flag = jdbcTemplate.update(sql, new Object[] { remark, id.toString() });

		return flag;
	}

	@Override
	public List query() {
		String sql = "SELECT * FROM WS_TP_MEMBER";
		List queryForList = jdbcTemplate.queryForList(sql);
		return queryForList;
	}

}
