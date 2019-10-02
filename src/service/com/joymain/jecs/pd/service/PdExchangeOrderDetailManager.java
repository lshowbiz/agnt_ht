
package com.joymain.jecs.pd.service;

import java.util.List;
import java.util.Set;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdExchangeOrderDetailManager extends Manager {
    /**
     * Retrieves all of the pdExchangeOrderDetails
     */
    public List getPdExchangeOrderDetails(PdExchangeOrderDetail pdExchangeOrderDetail);

    /**
     * Gets pdExchangeOrderDetail's information based on uniNo.
     * @param uniNo the pdExchangeOrderDetail's uniNo
     * @return pdExchangeOrderDetail populated pdExchangeOrderDetail object
     */
    public PdExchangeOrderDetail getPdExchangeOrderDetail(final String uniNo);

    /**
     * Saves a pdExchangeOrderDetail's information
     * @param pdExchangeOrderDetail the object to be saved
     */
    public void savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail);

    /**
     * Removes a pdExchangeOrderDetail from the database by uniNo
     * @param uniNo the pdExchangeOrderDetail's uniNo
     */
    public void removePdExchangeOrderDetail(final String uniNo);
    //added for getPdExchangeOrderDetailsByCrm
    public List getPdExchangeOrderDetailsByCrm(CommonRecord crm, Pager pager);

    /**
     * @author gw 2015-03-03
     * @param pdExchangeOrderDetail
     * @return pdExchangeOrderDetail
     */
	public PdExchangeOrderDetail getPdExchangeOrderDetailForEP(PdExchangeOrderDetail pdExchangeOrderDetail);

	/**
	 * 换货单获取换货商品信息
	 * @author gw 2015-05-28
	 * @param OrderNo 
	 * @return pdExchangeOrderDetails
	 */
	public Set<PdExchangeOrderDetail> getPdExchangeOrderDetailForOrderNo(String orderNo);

	/**
	 * 换货明细不为空的校验
	 * @author gw 2015-07-06
	 * @param pdExchangeOrderDetail
	 * @param errors
	 * @param defCharacterCoding
	 * @return String
	 */
	public String getEmptyCheck(PdExchangeOrderDetail pdExchangeOrderDetail,BindException errors, String defCharacterCoding);

}

