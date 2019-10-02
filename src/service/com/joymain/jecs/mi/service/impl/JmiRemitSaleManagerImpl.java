package com.joymain.jecs.mi.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.mi.dao.JmiRemitSaleDao;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.mi.service.JmiRemitSaleManager;
import com.joymain.jecs.mi.service.JmiStoreManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JmiRemitSaleManagerImpl extends BaseManager implements JmiRemitSaleManager {
	// 操作jmiRemitSaleDao
	private JmiRemitSaleDao jmiRemitSaleDao;

	public JmiRemitSaleManagerImpl() {
	}

	public void setJmiRemitSaleDao(JmiRemitSaleDao jmiRemitSaleDao) {
		this.jmiRemitSaleDao = jmiRemitSaleDao;
	}

	@Override
	public int saveJmiRemitSale(JmiRemitSale jmiRemitSale) throws SQLException {

		return jmiRemitSaleDao.saveJmiRemitSale(jmiRemitSale);
	}

	@Override
	public void deleJmiRemitSaleByuserCode(String userCode) throws SQLException {

	}

	@Override
	public int deleJmiRemitSaleById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.deleJmiRemitSaleById(id);
	}

	@Override
	public JmiRemitSale findJmiRemitSaleById(BigDecimal id) throws SQLException {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.findJmiRemitSaleById(id);
	}

	@Override
	public JmiRemitSale findJmiRemitSaleByUserCode(String userCode) throws SQLException {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.findJmiRemitSaleByUserCode(userCode);
	}

	@Override
	public int updateJmiRemitSaleById(BigDecimal oldjmiRemitSaleId, JmiRemitSale newjmiRemitSale) throws SQLException {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.updateJmiRemitSaleById(oldjmiRemitSaleId, newjmiRemitSale);
	}

	@Override
	public void updateJmiRemitSaleByUserCode(String oldUserCode, JmiRemitSale newjmiRemitSale) throws SQLException {
		jmiRemitSaleDao.updateJmiRemitSaleByUserCode(oldUserCode, newjmiRemitSale);

	}

	@Override
	public List getJmiMemberTeamsByCrm(CommonRecord crm, Pager pager) throws SQLException {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.getJmiMemberTeamsByCrm(crm, pager);
	}

	@Override
	public boolean findJmiRemitSaleByUserCode(String userCode, String bool) throws SQLException {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.findJmiRemitSaleByUserCode(userCode, bool);
	}

	public boolean findJmiRemitSaleById(BigDecimal id, String bool) throws SQLException {

		return jmiRemitSaleDao.findJmiRemitSaleById(id, bool);
	}

	@Override
	public List<Map<String, Object>> ajaxStarEndtWeek(String userCode, String notIncludeID) {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.ajaxStarEndtWeek(userCode,notIncludeID);
	}

	 public int  updateEndWeek(int bdPeriod,String id){
			return jmiRemitSaleDao.updateEndWeek(bdPeriod,id);
	 }
	@Override
	public List<Map<String, Object>> ajaxStarEndtWeek(String userCode) {

		return jmiRemitSaleDao.ajaxStarEndtWeek(userCode);
	}

	public List<Map<String, Object>> ajaxBdPeriodBonus(String bonus, int year) {
		return jmiRemitSaleDao.ajaxBdPeriodBonus(bonus, year);
	}

	@Override
	public List<Map<String, Object>> ajaxVerifyJMIMEMBER(String userCode) {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.ajaxVerifyJMIMEMBER(userCode);
	}

	public int updateRemarkById(BigDecimal id, String remark) {
		return jmiRemitSaleDao.updateRemarkById(id, remark);
	}

	@Override
	public void saveImportJmiRemitSale(List<JmiRemitSale> jmiRemitSaleList) {
		for(JmiRemitSale jmiRemitSale : jmiRemitSaleList){
			jmiRemitSaleDao.saveObject(jmiRemitSale);
		}
		
	}

	@Override
	public List queryRemitSale() {
		// TODO Auto-generated method stub
		return jmiRemitSaleDao.query();
	}

}
