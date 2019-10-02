
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysBackServiceInfo;
import com.joymain.jecs.sys.dao.SysBackServiceInfoDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SysBackServiceInfoManager extends Manager {
    /**
     * Retrieves all of the sysBackServiceInfos
     */
    public List getSysBackServiceInfos(SysBackServiceInfo sysBackServiceInfo);

    /**
     * Gets sysBackServiceInfo's information based on bsiId.
     * @param bsiId the sysBackServiceInfo's bsiId
     * @return sysBackServiceInfo populated sysBackServiceInfo object
     */
    public SysBackServiceInfo getSysBackServiceInfo(final String bsiId);

    /**
     * Saves a sysBackServiceInfo's information
     * @param sysBackServiceInfo the object to be saved
     */
    public void saveSysBackServiceInfo(SysBackServiceInfo sysBackServiceInfo);

    /**
     * Removes a sysBackServiceInfo from the database by bsiId
     * @param bsiId the sysBackServiceInfo's bsiId
     */
    public void removeSysBackServiceInfo(final String bsiId);
    //added for getSysBackServiceInfosByCrm
    public List getSysBackServiceInfosByCrm(CommonRecord crm, Pager pager);
}

