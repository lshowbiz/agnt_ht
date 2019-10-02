
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.model.VJbdMemberLinkCalc;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdMemberLinkCalcHistDao extends Dao {

    /**
     * Retrieves all of the jbdMemberLinkCalcHists
     */
    public List getJbdMemberLinkCalcHists(JbdMemberLinkCalcHist jbdMemberLinkCalcHist);

    /**
     * Gets jbdMemberLinkCalcHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdMemberLinkCalcHist's id
     * @return jbdMemberLinkCalcHist populated jbdMemberLinkCalcHist object
     */
    public JbdMemberLinkCalcHist getJbdMemberLinkCalcHist(final Long id);
    
    /**
     * Saves a jbdMemberLinkCalcHist's information
     * @param jbdMemberLinkCalcHist the object to be saved
     */    
    public void saveJbdMemberLinkCalcHist(JbdMemberLinkCalcHist jbdMemberLinkCalcHist);

    /**
     * Removes a jbdMemberLinkCalcHist from the database by id
     * @param id the jbdMemberLinkCalcHist's id
     */
    public void removeJbdMemberLinkCalcHist(final Long id);
    //added for getJbdMemberLinkCalcHistsByCrm
    public List getJbdMemberLinkCalcHistsByCrm(CommonRecord crm, Pager pager);
    public VJbdMemberLinkCalc getJbdMemberLinkCalcHistsByUserCodeWeek(String userCode,String wweek);

	public Map findByWweekAndMemberBySql(int wweek,String userCode);
	/**
	 * 获取整组PV
	 * @param memberNo
	 * @param wWeek
	 * @return
	 */
	public Map getBonusRecordbyUserCode(String userCode, String wWeek);
	/**
	 * 获取某会员在某一期的奖金数据
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public Map getBdSendRecordMap(final String userCode, final String wweek);
	/**
	 * 成功销售奖金明细
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public List getSuccessSaleBonus(final String recommendNo, final String wweek);
	
	public List getFranchises(final String recommendNo, final String wweek,String type);
	/**
	 * 获取奖金计算历史记录表 userCode week
	 * 
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public JbdMemberLinkCalcHist getJJbdMemberLinkCalcHistByUserCodeWeek(String userCode, String wweek) ;
	
	/**
	 * 店铺拓展奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getStoreExpandPv(String userCode, String wweek);
	/**
	 * 店铺服务奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getStoreServePv(String userCode, String wweek);
	/**
	 * 店铺推荐奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getStoreRecommendPv(String userCode, String wweek );
	/**
	 * 创业扶持奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getVentureFundPv(String userCode, String wweek) ;
	public List getJbdSuccessLeaderPv(String userCode, String wweek);
	public List getSuccessLeaderPvDetail(String userCode,String wweek,String passStar,String startDate,String endDate,String algebra,String passStar1);
	public Map getJBdLevel(String userCode ,String wweek);
	public List getVentureLeaderPvDetail(String userCode,String startDate,String endDate );
	public List getServicePv(String userCode, String wweek) ;
	public List getServicePv201607(String userCode, String wweek);
	
	/**
	 * 推广奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getPopularizeBonusPv(String userCode, String wweek);
	/**
	 * 报单奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getbdjPv201607(String userCode, String wweek);
}

