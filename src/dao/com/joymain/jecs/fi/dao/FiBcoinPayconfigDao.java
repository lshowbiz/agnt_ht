
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBcoinPayconfigDao extends Dao {

    /**
     * Retrieves all of the fiBcoinPayconfigs
     */
    public List getFiBcoinPayconfigs(FiBcoinPayconfig fiBcoinPayconfig);

    /**
     * Gets fiBcoinPayconfig's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param configId the fiBcoinPayconfig's configId
     * @return fiBcoinPayconfig populated fiBcoinPayconfig object
     */
    public FiBcoinPayconfig getFiBcoinPayconfig(final Long configId);

    /**
     * Saves a fiBcoinPayconfig's information
     * @param fiBcoinPayconfig the object to be saved
     */    
    public void saveFiBcoinPayconfig(FiBcoinPayconfig fiBcoinPayconfig);

    /**
     * Removes a fiBcoinPayconfig from the database by configId
     * @param configId the fiBcoinPayconfig's configId
     */
    public void removeFiBcoinPayconfig(final Long configId);
    //added for getFiBcoinPayconfigsByCrm
    public List getFiBcoinPayconfigsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 查询当前时间内积分换购活动
     * @return
     */
    public FiBcoinPayconfig getFiBcoinPayconfigByNowTime();
}

