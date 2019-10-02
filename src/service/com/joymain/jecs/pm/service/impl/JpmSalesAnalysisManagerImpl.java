
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.pm.dao.JpmSalesAnalysisDao;
import com.joymain.jecs.pm.service.JpmSalesAnalysisManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;

public class JpmSalesAnalysisManagerImpl extends BaseManager implements JpmSalesAnalysisManager {
    private JpmSalesAnalysisDao jpmSalesAnalysisDao;
    
	public JpmSalesAnalysisDao getJpmSalesAnalysisDao() {
		return jpmSalesAnalysisDao;
	}

	public void setJpmSalesAnalysisDao(JpmSalesAnalysisDao jpmSalesAnalysisDao) {
		this.jpmSalesAnalysisDao = jpmSalesAnalysisDao;
	}

	/**
	 * 1.获得指定区域的会员信息
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberInfo(CommonRecord crm) {
		return jpmSalesAnalysisDao.getJmiMemberInfo(crm);
	}

	/**
	 * 2.成员活跃度分析
	 * @day：日期
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberActive(CommonRecord crm) {
		return jpmSalesAnalysisDao.getJmiMemberActive(crm);
	}

	/**
	 * 3.团队新增会员
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param year：年份
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberNewTeams(CommonRecord crm) {
		return jpmSalesAnalysisDao.getJmiMemberNewTeams(crm);
	}

	/**
	 * 4.环比分析
	 * @year:用户编码
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberPoMemberOrder(CommonRecord crm) {
		return jpmSalesAnalysisDao.getJmiMemberPoMemberOrder(crm);
	}

	/**
	 * 5.商品分析
	 * @param companyCode：分公司
	 * @param userCode：用户编号
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberProduct(CommonRecord crm) {
		return jpmSalesAnalysisDao.getJmiMemberProduct(crm);
	}
	
	/**
	 * 6.销售冠军
	 * @year:用户编码
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberChampion(CommonRecord crm) {
		return jpmSalesAnalysisDao.getJmiMemberChampion(crm);
	}
}
