
package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysBackServiceInfo;
import com.joymain.jecs.sys.dao.SysBackServiceInfoDao;
import com.joymain.jecs.sys.service.SysBackServiceInfoManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class SysBackServiceInfoManagerImpl extends BaseManager implements SysBackServiceInfoManager {
    private SysBackServiceInfoDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysBackServiceInfoDao(SysBackServiceInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBackServiceInfoManager#getSysBackServiceInfos(com.joymain.jecs.sys.model.SysBackServiceInfo)
     */
    public List getSysBackServiceInfos(final SysBackServiceInfo sysBackServiceInfo) {
        return dao.getSysBackServiceInfos(sysBackServiceInfo);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBackServiceInfoManager#getSysBackServiceInfo(String bsiId)
     */
    public SysBackServiceInfo getSysBackServiceInfo(final String bsiId) {
        return dao.getSysBackServiceInfo(new Long(bsiId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBackServiceInfoManager#saveSysBackServiceInfo(SysBackServiceInfo sysBackServiceInfo)
     */
    public void saveSysBackServiceInfo(SysBackServiceInfo sysBackServiceInfo) {
        dao.saveSysBackServiceInfo(sysBackServiceInfo);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBackServiceInfoManager#removeSysBackServiceInfo(String bsiId)
     */
    public void removeSysBackServiceInfo(final String bsiId) {
        dao.removeSysBackServiceInfo(new Long(bsiId));
    }
    //added for getSysBackServiceInfosByCrm
    public List getSysBackServiceInfosByCrm(CommonRecord crm, Pager pager){
	return dao.getSysBackServiceInfosByCrm(crm,pager);
    }
}
