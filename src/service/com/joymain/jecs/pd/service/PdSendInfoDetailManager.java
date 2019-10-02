
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.dao.PdSendInfoDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdSendInfoDetailManager extends Manager {
	public List getShipDetailsByTown(CommonRecord crm);
	public List getShipDetails(CommonRecord crm);
	public List getShipDetailsByState(CommonRecord crm);
	
	public List getTotalPdSendInfoDetails(CommonRecord crm);
    /**
     * Retrieves all of the pdSendInfoDetails
     */
    public List getPdSendInfoDetails(PdSendInfoDetail pdSendInfoDetail);

    /**
     * Gets pdSendInfoDetail's information based on uniNo.
     * @param uniNo the pdSendInfoDetail's uniNo
     * @return pdSendInfoDetail populated pdSendInfoDetail object
     */
    public PdSendInfoDetail getPdSendInfoDetail(final String uniNo);

    /**
     * Saves a pdSendInfoDetail's information
     * @param pdSendInfoDetail the object to be saved
     */
    public void savePdSendInfoDetail(PdSendInfoDetail pdSendInfoDetail);

    /**
     * Removes a pdSendInfoDetail from the database by uniNo
     * @param uniNo the pdSendInfoDetail's uniNo
     */
    public void removePdSendInfoDetail(final String uniNo);
    //added for getPdSendInfoDetailsByCrm
    public List getPdSendInfoDetailsByCrm(CommonRecord crm, Pager pager);
}

