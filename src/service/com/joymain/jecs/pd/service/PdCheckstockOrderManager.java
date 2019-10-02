
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdCheckstockOrder;
import com.joymain.jecs.pd.dao.PdCheckstockOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdCheckstockOrderManager extends Manager {
    /**
     * Retrieves all of the pdCheckstockOrders
     */
    public List getPdCheckstockOrders(PdCheckstockOrder pdCheckstockOrder);

    /**
     * Gets pdCheckstockOrder's information based on pcoNo.
     * @param pcoNo the pdCheckstockOrder's pcoNo
     * @return pdCheckstockOrder populated pdCheckstockOrder object
     */
    public PdCheckstockOrder getPdCheckstockOrder(final String pcoNo);

    /**
     * Saves a pdCheckstockOrder's information
     * @param pdCheckstockOrder the object to be saved
     */
    public void savePdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder);

    /**
     * Removes a pdCheckstockOrder from the database by pcoNo
     * @param pcoNo the pdCheckstockOrder's pcoNo
     */
    public void removePdCheckstockOrder(final String pcoNo);
    //added for getPdCheckstockOrdersByCrm
    public List getPdCheckstockOrdersByCrm(CommonRecord crm, Pager pager);

	public void addPdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder);

	public void confirmPdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder);
}

