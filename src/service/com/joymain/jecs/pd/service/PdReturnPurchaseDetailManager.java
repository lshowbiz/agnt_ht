
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdReturnPurchaseDetailManager extends Manager {
	
	
	public boolean existPdReturnPurchaseDetail(String productNo,String rpNo);
    /**
     * Retrieves all of the pdReturnPurchaseDetails
     */
    public List getPdReturnPurchaseDetails(PdReturnPurchaseDetail pdReturnPurchaseDetail);

    /**
     * Gets pdReturnPurchaseDetail's information based on uniNo.
     * @param uniNo the pdReturnPurchaseDetail's uniNo
     * @return pdReturnPurchaseDetail populated pdReturnPurchaseDetail object
     */
    public PdReturnPurchaseDetail getPdReturnPurchaseDetail(final String uniNo);

    /**
     * Saves a pdReturnPurchaseDetail's information
     * @param pdReturnPurchaseDetail the object to be saved
     */
    public void savePdReturnPurchaseDetail(PdReturnPurchaseDetail pdReturnPurchaseDetail);

    /**
     * Removes a pdReturnPurchaseDetail from the database by uniNo
     * @param uniNo the pdReturnPurchaseDetail's uniNo
     */
    public void removePdReturnPurchaseDetail(final String uniNo);
    //added for getPdReturnPurchaseDetailsByCrm
    public List getPdReturnPurchaseDetailsByCrm(CommonRecord crm, Pager pager);
	public List getTotalPdReturnPurchaseDetails(CommonRecord crm);
}

