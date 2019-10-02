
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdSendInfoDetailDao extends Dao {

	public List getShipDetailsByTown(CommonRecord crm);
	public List getShipDetailsByState(CommonRecord crm);
	public List getShipDetails(CommonRecord crm);
    /**
     * Retrieves all of the pdSendInfoDetails
     */
    public List getPdSendInfoDetails(PdSendInfoDetail pdSendInfoDetail);

    /**
     * Gets pdSendInfoDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdSendInfoDetail's uniNo
     * @return pdSendInfoDetail populated pdSendInfoDetail object
     */
    public PdSendInfoDetail getPdSendInfoDetail(final BigDecimal uniNo);

    /**
     * Saves a pdSendInfoDetail's information
     * @param pdSendInfoDetail the object to be saved
     */    
    public void savePdSendInfoDetail(PdSendInfoDetail pdSendInfoDetail);

    /**
     * Removes a pdSendInfoDetail from the database by uniNo
     * @param uniNo the pdSendInfoDetail's uniNo
     */
    public void removePdSendInfoDetail(final BigDecimal uniNo);
    //added for getPdSendInfoDetailsByCrm
    public List getPdSendInfoDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdSendInfoDetails(CommonRecord crm);
	
	/**
	 * Modify By WUCF 删除具体数据
	 * @param uniNo
	 */
	public void deletePdSendInfoDetail(final Long uniNo);
	
	/**
	 * 根据发货单号获取发货明细
	 * @author gw 2015-04-22
	 * @param siNo
	 * @return
	 */
	public List<PdSendInfoDetail> getPdSendInfoInterFaceList(String siNo);
		
}

