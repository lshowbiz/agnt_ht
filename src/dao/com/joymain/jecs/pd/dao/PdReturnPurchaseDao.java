
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdReturnPurchaseDao extends Dao {

    /**
     * Retrieves all of the pdReturnPurchases
     */
    public List getPdReturnPurchases(PdReturnPurchase pdReturnPurchase);

    /**
     * Gets pdReturnPurchase's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param rpNo the pdReturnPurchase's rpNo
     * @return pdReturnPurchase populated pdReturnPurchase object
     */
    public PdReturnPurchase getPdReturnPurchase(final String rpNo);

    /**
     * Modify Bu WuCF 20151226 添加对数据锁定的方法
     * @param rpNo
     * @return
     */
    public PdReturnPurchase getPdReturnPurchaseForUpdate(final String rpNo);
    
    /**
     * Saves a pdReturnPurchase's information
     * @param pdReturnPurchase the object to be saved
     */    
    public void savePdReturnPurchase(PdReturnPurchase pdReturnPurchase);

    /**
     * Removes a pdReturnPurchase from the database by rpNo
     * @param rpNo the pdReturnPurchase's rpNo
     */
    public void removePdReturnPurchase(final String rpNo);
    //added for getPdReturnPurchasesByCrm
    public List getPdReturnPurchasesByCrm(CommonRecord crm, Pager pager);

	public List getReturnDetails(CommonRecord crm);

	/**
	 * 根据发货退回单号查询发货退回单状态信息
	 * @author fu 2015-09-11 
	 * @param rpNo
	 * @return string
	 */
	public String getPdReturnOrderFlag(String rpNo);

	/**
     * 获取数据库中的pdReturnPurchase对象
     * @author fu 2015-09-14 
     * @param pdReturnPurchase
     * @return pdReturnPurchase
     */
	public PdReturnPurchase getPdReturnPurchaseDBA(PdReturnPurchase pdReturnPurchase);
	
}

