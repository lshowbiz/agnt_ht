
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.dao.PdCombinationOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdCombinationOrderManager extends Manager {
	
    /**
     * Retrieves all of the pdCombinationOrders
     */
    public List getPdCombinationOrders(PdCombinationOrder pdCombinationOrder);

    /**
     * Gets pdCombinationOrder's information based on pcNo.
     * @param pcNo the pdCombinationOrder's pcNo
     * @return pdCombinationOrder populated pdCombinationOrder object
     */
    public PdCombinationOrder getPdCombinationOrder(final String pcNo);

    /**
     * Saves a pdCombinationOrder's information
     * @param pdCombinationOrder the object to be saved
     */
    public void savePdCombinationOrder(PdCombinationOrder pdCombinationOrder);

    /**
     * Removes a pdCombinationOrder from the database by pcNo
     * @param pcNo the pdCombinationOrder's pcNo
     */
    public void removePdCombinationOrder(final String pcNo);
    //added for getPdCombinationOrdersByCrm
    public List getPdCombinationOrdersByCrm(CommonRecord crm, Pager pager);
    public void addPdCombinationOrder(PdCombinationOrder pdCombinationOrder);
	public void submitPdCombinationOrder(PdCombinationOrder pdCombinationOrder,SysUser sysUser);

	public void confirmPdCombinationOrder(
			PdCombinationOrder pdCombinationOrder, SysUser sessionLogin);
}

