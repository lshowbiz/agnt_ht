
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.dao.FiTransferFundbookDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiTransferFundbookManager extends Manager {
    /**
     * Retrieves all of the fiTransferFundbooks
     */
    public List getFiTransferFundbooks(FiTransferFundbook fiTransferFundbook);

    /**
     * Gets fiTransferFundbook's information based on taId.
     * @param taId the fiTransferFundbook's taId
     * @return fiTransferFundbook populated fiTransferFundbook object
     */
    public FiTransferFundbook getFiTransferFundbook(final String taId);

    /**
     * Saves a fiTransferFundbook's information
     * @param fiTransferFundbook the object to be saved
     */
    public void saveFiTransferFundbook(FiTransferFundbook fiTransferFundbook);

    /**
     * Removes a fiTransferFundbook from the database by taId
     * @param taId the fiTransferFundbook's taId
     */
    public void removeFiTransferFundbook(final String taId);
    //added for getFiTransferFundbooksByCrm
    public List getFiTransferFundbooksByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 审核通过转账单
	 * 审核转账 (其中包括1.转账成功，目标账户存入资金；2.修改转账单状态。在同一个事务里面)
	 */
	public void checkFiTransferFundbooks(List<FiTransferFundbook> fiTransferFundbookList);
	
	/**
	 * 撤销转账单
	 * 业务规则：撤销转账单 (其中包括1.撤销成功，资金退回转账用户；2.修改转账单状态。在同一个事务里面)
	 */
	public void revokeFiTransferFundbooks(List<FiTransferFundbook> fiTransferFundbookList);
	
	/**
	 * 自动审核产业化基金转账单
	 */
	public void autoCheckFiTransferFundbooks();
}

