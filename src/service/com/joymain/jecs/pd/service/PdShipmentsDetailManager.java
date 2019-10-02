
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdShipmentsDetail;
import com.joymain.jecs.pd.dao.PdShipmentsDetailDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface PdShipmentsDetailManager extends Manager {
	
	
	
	public PdShipmentsDetail getPdShipmentsDetail(String orderNo,String productNo);
	public void addPdShipmentsDetailsByOrder(PdReturnPurchase order);
	public void addPdShipmentsDetailsByOrder(JpoMemberOrder order);
	public void addPdShipmentsDetailsByOrder(Object order,String className);
    /**
     * Retrieves all of the pdShipmentsDetails
     */
    public List getPdShipmentsDetails(PdShipmentsDetail pdShipmentsDetail);

    /**
     * Gets pdShipmentsDetail's information based on sdId.
     * @param sdId the pdShipmentsDetail's sdId
     * @return pdShipmentsDetail populated pdShipmentsDetail object
     */
    public PdShipmentsDetail getPdShipmentsDetail(final String sdId);

    /**
     * Saves a pdShipmentsDetail's information
     * @param pdShipmentsDetail the object to be saved
     */
    public void savePdShipmentsDetail(PdShipmentsDetail pdShipmentsDetail);

    /**
     * Removes a pdShipmentsDetail from the database by sdId
     * @param sdId the pdShipmentsDetail's sdId
     */
    public void removePdShipmentsDetail(final String sdId);
    //added for getPdShipmentsDetailsByCrm
    public List getPdShipmentsDetailsByCrm(CommonRecord crm, Pager pager);
	public String saveMatchSendInfo(PdSendInfo pdSendInfo);
	public void addPdShipmentsDetailsByOrder(PdExchangeOrder pdExchangeOrder);
}

