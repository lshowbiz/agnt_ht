
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoCounterOrderDetailManager extends Manager {
    /**
     * Retrieves all of the jpoCounterOrderDetails
     */
    public List getJpoCounterOrderDetails(JpoCounterOrderDetail jpoCounterOrderDetail);

    /**
     * Gets jpoCounterOrderDetail's information based on codNo.
     * @param codNo the jpoCounterOrderDetail's codNo
     * @return jpoCounterOrderDetail populated jpoCounterOrderDetail object
     */
    public JpoCounterOrderDetail getJpoCounterOrderDetail(final String codNo);

    /**
     * Saves a jpoCounterOrderDetail's information
     * @param jpoCounterOrderDetail the object to be saved
     */
    public void saveJpoCounterOrderDetail(JpoCounterOrderDetail jpoCounterOrderDetail);

    /**
     * Removes a jpoCounterOrderDetail from the database by codNo
     * @param codNo the jpoCounterOrderDetail's codNo
     */
    public void removeJpoCounterOrderDetail(final String codNo);
    //added for getJpoCounterOrderDetailsByCrm
    public List getJpoCounterOrderDetailsByCrm(CommonRecord crm, Pager pager);
	public void savePreDetails(String companyCode,JpoCounterOrder jpoCounterOrder) throws Exception;
	public boolean getExistPoCounterOrderDetail(String coNo, Long productId);

	public List getPoCounterSumByCrm(CommonRecord crm);
	public List getTotolPoCounterOrderDetails(CommonRecord crm);
}

