
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiPayAdviceDao extends Dao {

    /**
     * Retrieves all of the jfiPayAdvices
     */
    public List getJfiPayAdvices(JfiPayAdvice jfiPayAdvice);

    /**
     * Gets jfiPayAdvice's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param adviceCode the jfiPayAdvice's adviceCode
     * @return jfiPayAdvice populated jfiPayAdvice object
     */
    public JfiPayAdvice getJfiPayAdvice(final String adviceCode);

    /**
     * Saves a jfiPayAdvice's information
     * @param jfiPayAdvice the object to be saved
     */    
    public void saveJfiPayAdvice(JfiPayAdvice jfiPayAdvice);

    /**
     * Removes a jfiPayAdvice from the database by adviceCode
     * @param adviceCode the jfiPayAdvice's adviceCode
     */
    public void removeJfiPayAdvice(final String adviceCode);
    //added for getJfiPayAdvicesByCrm
    public List getJfiPayAdvicesByCrm(CommonRecord crm, Pager pager);


	/**
	 * 批量保存或更新多个付款通知
	 * @param fiPayAdvices
	 */
	public void saveJfiPayAdvices(List jfiPayAdvices);
	
	/**
     * 获取存折查询统计
     * @param crm
     * @return
     */
    public Map getJfiPayAdviceStatMap(CommonRecord crm);
	
	/**
	 * 代理商分组查询付款通知
	 * @param crm
	 * @return
	 */
	public List getJfiPayAdvicesStatGroup(CommonRecord crm);
	
	/**
	 * 银行提款报表
	 * @param crm
	 * @return
	 */
	public List getJfiPayAdvicesStat(CommonRecord crm);
}

