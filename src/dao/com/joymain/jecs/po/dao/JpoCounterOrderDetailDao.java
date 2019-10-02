
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoCounterOrderDetailDao extends Dao {

    /**
     * Retrieves all of the jpoCounterOrderDetails
     */
    public List getJpoCounterOrderDetails(JpoCounterOrderDetail jpoCounterOrderDetail);

    /**
     * Gets jpoCounterOrderDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param codNo the jpoCounterOrderDetail's codNo
     * @return jpoCounterOrderDetail populated jpoCounterOrderDetail object
     */
    public JpoCounterOrderDetail getJpoCounterOrderDetail(final Long codNo);

    /**
     * Saves a jpoCounterOrderDetail's information
     * @param jpoCounterOrderDetail the object to be saved
     */    
    public void saveJpoCounterOrderDetail(JpoCounterOrderDetail jpoCounterOrderDetail);

    /**
     * Removes a jpoCounterOrderDetail from the database by codNo
     * @param codNo the jpoCounterOrderDetail's codNo
     */
    public void removeJpoCounterOrderDetail(final Long codNo);
    //added for getJpoCounterOrderDetailsByCrm
    public List getJpoCounterOrderDetailsByCrm(CommonRecord crm, Pager pager);
    public List getJpoCounterOrderDetails(String coNo, Long productId) ;

	public List getPoCounterSumByCrm(CommonRecord crm);
	public List getTotolPoCounterOrderDetails(CommonRecord crm);
}

