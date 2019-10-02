
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiBcoinPayconfigDetailManager extends Manager {
    /**
     * Retrieves all of the fiBcoinPayconfigDetails
     */
    public List getFiBcoinPayconfigDetails(FiBcoinPayconfigDetail fiBcoinPayconfigDetail);

    /**
     * Gets fiBcoinPayconfigDetail's information based on detailId.
     * @param detailId the fiBcoinPayconfigDetail's detailId
     * @return fiBcoinPayconfigDetail populated fiBcoinPayconfigDetail object
     */
    public FiBcoinPayconfigDetail getFiBcoinPayconfigDetail(final String detailId);

    /**
     * Saves a fiBcoinPayconfigDetail's information
     * @param fiBcoinPayconfigDetail the object to be saved
     */
    public void saveFiBcoinPayconfigDetail(FiBcoinPayconfigDetail fiBcoinPayconfigDetail);

    /**
     * Removes a fiBcoinPayconfigDetail from the database by detailId
     * @param detailId the fiBcoinPayconfigDetail's detailId
     */
    public void removeFiBcoinPayconfigDetail(final String detailId);
    //added for getFiBcoinPayconfigDetailsByCrm
    public List getFiBcoinPayconfigDetailsByCrm(CommonRecord crm, Pager pager);
    
    public List getFiBcoinPayconfigDetailsByConfigId(String configId);
    
    /**
     * 查询换购明细
     * @param configId
     * @param productNo
     * @return
     */
    public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailsByProductNo(String configId, String productNo);
    
    public void saveFiBcoinPayconfigDetails(List<FiBcoinPayconfigDetail> detailTemps);
}

