
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmSalesAnalysisDao extends Dao {
    
    /**
	 * 1.获得指定区域的会员信息
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	public List<Map<String, Object>> getJmiMemberInfo(CommonRecord crm);
	
	
	/**
	 * 2.成员活跃度分析
	 * @day：日期
	 */
	public List<Map<String, Object>> getJmiMemberActive(CommonRecord crm);
	
	/**
	 * 3.团队新增会员
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param year：年份
	 * @return
	 */
	public List<Map<String, Object>> getJmiMemberNewTeams(CommonRecord crm);
	
	/**
	 * 4.环比分析
	 * @year:用户编码
	 * @return 返回List集合
	 */
	public List<Map<String, Object>> getJmiMemberPoMemberOrder(CommonRecord crm);
	
	/**
	 * 5.商品分析
	 * @param companyCode：分公司
	 * @param userCode：用户编号
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	public List<Map<String, Object>> getJmiMemberProduct(CommonRecord crm);
	
	/**
	 * 6.销售冠军
	 * @year:用户编码
	 * @return 返回List集合
	 */
	public List<Map<String, Object>> getJmiMemberChampion(CommonRecord crm);
}

