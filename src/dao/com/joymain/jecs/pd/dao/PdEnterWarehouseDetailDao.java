
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdEnterWarehouseDetailDao extends Dao {

	public List  getEnterWarehouseStaticsByCrm(CommonRecord crm);
	/**
	 * ͳ��
	 * @param crm
	 * @return
	 */
	public List getTotolPdEnterWarehouseDetails(CommonRecord crm);
	public List getPdEnterWarehouseDetails(String productNo,String ewNo);
    /**
     * Retrieves all of the pdEnterWarehouseDetails
     */
    public List getPdEnterWarehouseDetails(PdEnterWarehouseDetail pdEnterWarehouseDetail);

    /**
     * Gets pdEnterWarehouseDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdEnterWarehouseDetail's uniNo
     * @return pdEnterWarehouseDetail populated pdEnterWarehouseDetail object
     */
    public PdEnterWarehouseDetail getPdEnterWarehouseDetail(final Long uniNo);

    /**
     * Saves a pdEnterWarehouseDetail's information
     * @param pdEnterWarehouseDetail the object to be saved
     */    
    public void savePdEnterWarehouseDetail(PdEnterWarehouseDetail pdEnterWarehouseDetail);

    /**
     * Removes a pdEnterWarehouseDetail from the database by uniNo
     * @param uniNo the pdEnterWarehouseDetail's uniNo
     */
    public void removePdEnterWarehouseDetail(final Long uniNo);
    //added for getPdEnterWarehouseDetailsByCrm
    public List getPdEnterWarehouseDetailsByCrm(CommonRecord crm, Pager pager);
}

