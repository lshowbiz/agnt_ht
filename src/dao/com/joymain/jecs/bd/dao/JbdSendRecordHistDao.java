
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSendRecordHistDao extends Dao {

    /**
     * Retrieves all of the jbdSendRecordHists
     */
    public List getJbdSendRecordHists(JbdSendRecordHist jbdSendRecordHist);

    /**
     * Gets jbdSendRecordHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSendRecordHist's id
     * @return jbdSendRecordHist populated jbdSendRecordHist object
     */
    public JbdSendRecordHist getJbdSendRecordHist(final Long id);

    /**
     * Saves a jbdSendRecordHist's information
     * @param jbdSendRecordHist the object to be saved
     */    
    public void saveJbdSendRecordHist(JbdSendRecordHist jbdSendRecordHist);

    /**
     * Removes a jbdSendRecordHist from the database by id
     * @param id the jbdSendRecordHist's id
     */
    public void removeJbdSendRecordHist(final Long id);
    //added for getJbdSendRecordHistsByCrm
    public List getJbdSendRecordHistsByCrm(CommonRecord crm, Pager pager);
	public BigDecimal getSumRemittanceMoney(CommonRecord crm);
    /**
     * @param crm
     * @param pag
     * @return
     */
    public List getJbdSendRecordsBySqlByCrm(CommonRecord crm, Pager pager);
    public BigDecimal getJbdSendRecordsBySqlByCrmSum(CommonRecord crm);
	public List getBdSendRecordsByCrmBySqlForView(CommonRecord crm);
	public BigDecimal getJbdSendRecordsBySqlByCrmSumDev(CommonRecord crm);

	public List getJbdSendRecordsBySqlByCrmNew(CommonRecord crm, Pager pager) ;
    public BigDecimal getJbdSendRecordsBySqlByCrmSumNew(CommonRecord crm);
    
    public List getJbdBonusBalanceUserCodes(String startAllotMoney,String endAllotMoney,String flag,String status);
    public List getJbdSendRecordsByUserCode(String userCode,String companyCode,String fi);
    public JbdSendRecordHist getJbdSendRecordHistByUserCodeAndWeek(String userCode,String wweek);

    public JbdSendRecordHist getJbdSendRecordHistForUpdate(final Long id) ;
    public  Object[]  getSumBonus(CommonRecord crm);
}

