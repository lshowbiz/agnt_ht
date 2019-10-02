
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdReturnPurchaseDetailDao extends Dao {

    /**
     * Retrieves all of the pdReturnPurchaseDetails
     */
    public List getPdReturnPurchaseDetails(PdReturnPurchaseDetail pdReturnPurchaseDetail);

    /**
     * Gets pdReturnPurchaseDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdReturnPurchaseDetail's uniNo
     * @return pdReturnPurchaseDetail populated pdReturnPurchaseDetail object
     */
    public PdReturnPurchaseDetail getPdReturnPurchaseDetail(final Long uniNo);

    /**
     * Saves a pdReturnPurchaseDetail's information
     * @param pdReturnPurchaseDetail the object to be saved
     */    
    public void savePdReturnPurchaseDetail(PdReturnPurchaseDetail pdReturnPurchaseDetail);

    /**
     * Removes a pdReturnPurchaseDetail from the database by uniNo
     * @param uniNo the pdReturnPurchaseDetail's uniNo
     */
    public void removePdReturnPurchaseDetail(final Long uniNo);
    //added for getPdReturnPurchaseDetailsByCrm
    public List getPdReturnPurchaseDetailsByCrm(CommonRecord crm, Pager pager);

	public List getPdReturnPurchaseDetail(String productNo, String rpNo);

	public List getTotalPdReturnPurchaseDetails(CommonRecord crm);
}

