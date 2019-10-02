
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdExchangeOrderDetailDao extends Dao {

    /**
     * Retrieves all of the pdExchangeOrderDetails
     */
    public List getPdExchangeOrderDetails(PdExchangeOrderDetail pdExchangeOrderDetail);

    /**
     * Gets pdExchangeOrderDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdExchangeOrderDetail's uniNo
     * @return pdExchangeOrderDetail populated pdExchangeOrderDetail object
     */
    public PdExchangeOrderDetail getPdExchangeOrderDetail(final Long uniNo);

    /**
     * Saves a pdExchangeOrderDetail's information
     * @param pdExchangeOrderDetail the object to be saved
     */    
    public void savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail);

    /**
     * Removes a pdExchangeOrderDetail from the database by uniNo
     * @param uniNo the pdExchangeOrderDetail's uniNo
     */
    public void removePdExchangeOrderDetail(final Long uniNo);
    //added for getPdExchangeOrderDetailsByCrm
    public List getPdExchangeOrderDetailsByCrm(CommonRecord crm, Pager pager);

    /**
     * @author gw 2015-03-03
     * @param pdExchangeOrderDetail
     * @return pdExchangeOrderDetail
     */
	public PdExchangeOrderDetail getPdExchangeOrderDetailForEP(PdExchangeOrderDetail pdExchangeOrderDetail);
	
}

