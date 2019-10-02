
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdCheckstockOrderDetailManager extends Manager {
    /**
     * Retrieves all of the pdCheckstockOrderDetails
     */
    public List getPdCheckstockOrderDetails(PdCheckstockOrderDetail pdCheckstockOrderDetail);

    /**
     * Gets pdCheckstockOrderDetail's information based on uniNo.
     * @param uniNo the pdCheckstockOrderDetail's uniNo
     * @return pdCheckstockOrderDetail populated pdCheckstockOrderDetail object
     */
    public PdCheckstockOrderDetail getPdCheckstockOrderDetail(final String uniNo);

    /**
     * Saves a pdCheckstockOrderDetail's information
     * @param pdCheckstockOrderDetail the object to be saved
     */
    public void savePdCheckstockOrderDetail(PdCheckstockOrderDetail pdCheckstockOrderDetail);

    /**
     * Removes a pdCheckstockOrderDetail from the database by uniNo
     * @param uniNo the pdCheckstockOrderDetail's uniNo
     */
    public void removePdCheckstockOrderDetail(final String uniNo);
    //added for getPdCheckstockOrderDetailsByCrm
    public List getPdCheckstockOrderDetailsByCrm(CommonRecord crm, Pager pager);
}

