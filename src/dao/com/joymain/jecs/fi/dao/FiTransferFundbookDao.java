
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiTransferFundbookDao extends Dao {

    /**
     * Retrieves all of the fiTransferFundbooks
     */
    public List getFiTransferFundbooks(FiTransferFundbook fiTransferFundbook);

    /**
     * Gets fiTransferFundbook's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param taId the fiTransferFundbook's taId
     * @return fiTransferFundbook populated fiTransferFundbook object
     */
    public FiTransferFundbook getFiTransferFundbook(final Long taId);

    /**
     * Saves a fiTransferFundbook's information
     * @param fiTransferFundbook the object to be saved
     */    
    public void saveFiTransferFundbook(FiTransferFundbook fiTransferFundbook);

    /**
     * Removes a fiTransferFundbook from the database by taId
     * @param taId the fiTransferFundbook's taId
     */
    public void removeFiTransferFundbook(final Long taId);
    //added for getFiTransferFundbooksByCrm
    public List getFiTransferFundbooksByCrm(CommonRecord crm, Pager pager);
    
    //根据ID查状态
	public Integer getTransferFundbookStatus(Long taId);
	
	/**
	 * 查询待审核产业化基金转账单
	 * @return
	 */
	public List<FiTransferFundbook> getToCheckFiTransferFundbookList();
}

