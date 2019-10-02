
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdLogisticsBaseDetailManager extends Manager {
    /**
     * Retrieves all of the pdLogisticsBaseDetails
     */
    public List getPdLogisticsBaseDetails(PdLogisticsBaseDetail pdLogisticsBaseDetail);

    /**
     * Gets pdLogisticsBaseDetail's information based on detailId.
     * @param detailId the pdLogisticsBaseDetail's detailId
     * @return pdLogisticsBaseDetail populated pdLogisticsBaseDetail object
     */
    public PdLogisticsBaseDetail getPdLogisticsBaseDetail(final String detailId);

    /**
     * Saves a pdLogisticsBaseDetail's information
     * @param pdLogisticsBaseDetail the object to be saved
     */
    public void savePdLogisticsBaseDetail(PdLogisticsBaseDetail pdLogisticsBaseDetail);

    /**
     * Removes a pdLogisticsBaseDetail from the database by detailId
     * @param detailId the pdLogisticsBaseDetail's detailId
     */
    public void removePdLogisticsBaseDetail(final String detailId);
    //added for getPdLogisticsBaseDetailsByCrm
    public List getPdLogisticsBaseDetailsByCrm(CommonRecord crm, Pager pager);
}

