
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiBcoinPayconfigManager extends Manager {
    /**
     * Retrieves all of the fiBcoinPayconfigs
     */
    public List getFiBcoinPayconfigs(FiBcoinPayconfig fiBcoinPayconfig);

    /**
     * Gets fiBcoinPayconfig's information based on configId.
     * @param configId the fiBcoinPayconfig's configId
     * @return fiBcoinPayconfig populated fiBcoinPayconfig object
     */
    public FiBcoinPayconfig getFiBcoinPayconfig(final String configId);

    /**
     * Saves a fiBcoinPayconfig's information
     * @param fiBcoinPayconfig the object to be saved
     */
    public void saveFiBcoinPayconfig(FiBcoinPayconfig fiBcoinPayconfig);

    /**
     * Removes a fiBcoinPayconfig from the database by configId
     * @param configId the fiBcoinPayconfig's configId
     */
    public void removeFiBcoinPayconfig(final String configId);
    //added for getFiBcoinPayconfigsByCrm
    public List getFiBcoinPayconfigsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 查询当前时间内积分换购活动
     * @return
     */
    public FiBcoinPayconfig getFiBcoinPayconfigByNowTime();
    
    /**
     * 查询当前订单积分换购商品权限
     * @param jpoMemberOrder
     * @return
     */
    public boolean getCanUseCoinPayByOrder(FiBcoinPayconfig fiBcoinPayconfig, JpoMemberOrder jpoMemberOrder);
}

