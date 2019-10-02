
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.dao.PdCombinationDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdCombinationDetailManager extends Manager {
    /**
     * Retrieves all of the pdCombinationDetails
     */
    public List getPdCombinationDetails(PdCombinationDetail pdCombinationDetail);

    /**
     * Gets pdCombinationDetail's information based on uniNo.
     * @param uniNo the pdCombinationDetail's uniNo
     * @return pdCombinationDetail populated pdCombinationDetail object
     */
    public PdCombinationDetail getPdCombinationDetail(final String uniNo);

    /**
     * Saves a pdCombinationDetail's information
     * @param pdCombinationDetail the object to be saved
     */
    public void savePdCombinationDetail(PdCombinationDetail pdCombinationDetail);

    /**
     * Removes a pdCombinationDetail from the database by uniNo
     * @param uniNo the pdCombinationDetail's uniNo
     */
    public void removePdCombinationDetail(final String uniNo);
    //added for getPdCombinationDetailsByCrm
    public List getPdCombinationDetailsByCrm(CommonRecord crm, Pager pager);

	public List getPdCombinationDetailTotals(CommonRecord crm);
}

