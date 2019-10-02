
package com.joymain.jecs.sys.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysBackServiceInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysBackServiceInfoDao extends Dao {

    /**
     * Retrieves all of the sysBackServiceInfos
     */
    public List getSysBackServiceInfos(SysBackServiceInfo sysBackServiceInfo);

    /**
     * Gets sysBackServiceInfo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param bsiId the sysBackServiceInfo's bsiId
     * @return sysBackServiceInfo populated sysBackServiceInfo object
     */
    public SysBackServiceInfo getSysBackServiceInfo(final Long bsiId);

    /**
     * Saves a sysBackServiceInfo's information
     * @param sysBackServiceInfo the object to be saved
     */    
    public void saveSysBackServiceInfo(SysBackServiceInfo sysBackServiceInfo);

    /**
     * Removes a sysBackServiceInfo from the database by bsiId
     * @param bsiId the sysBackServiceInfo's bsiId
     */
    public void removeSysBackServiceInfo(final Long bsiId);
    //added for getSysBackServiceInfosByCrm
    public List getSysBackServiceInfosByCrm(CommonRecord crm, Pager pager);
}

