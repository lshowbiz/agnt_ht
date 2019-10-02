
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.dao.PdReturnPurchaseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdReturnPurchaseManager extends Manager {
	
	public List getReturnDetails(CommonRecord crm);
	public long getProductCountByRpNo(String rpNo);
	public void updateAmount(final String rpNo);
	public void confirmPdReturnPurchase(PdReturnPurchase pdReturnPurchase,String deport);
    /**
     * Retrieves all of the pdReturnPurchases
     */
    public List getPdReturnPurchases(PdReturnPurchase pdReturnPurchase);

    /**
     * Gets pdReturnPurchase's information based on rpNo.
     * @param rpNo the pdReturnPurchase's rpNo
     * @return pdReturnPurchase populated pdReturnPurchase object
     */
    public PdReturnPurchase getPdReturnPurchase(final String rpNo);

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
    
    /**
     * 发货退回入库接口
     * @author fu 20150820
     * @param jsonString
     * @return rspEntity
     */
    public RspEntity reSetPdReturnPurchaseStatus(String jsonString);
    
    /**
	 * 根据发货退回单号查询发货退回单状态信息
	 * @author fu 2015-09-11 
	 * @param rpNo
	 * @return string
	 */
	public String getPdReturnOrderFlag(String rpNo);
    
}

