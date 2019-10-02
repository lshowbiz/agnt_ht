
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.dao.FiCommonAddrDao;
import com.joymain.jecs.fi.service.FiCommonAddrManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiCommonAddrManagerImpl extends BaseManager implements FiCommonAddrManager {
    private FiCommonAddrDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiCommonAddrDao(FiCommonAddrDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCommonAddrManager#getFiCommonAddrs(com.joymain.jecs.fi.model.FiCommonAddr)
     */
    public List getFiCommonAddrs(final FiCommonAddr fiCommonAddr) {
        return dao.getFiCommonAddrs(fiCommonAddr);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCommonAddrManager#getFiCommonAddr(String userCode)
     */
    public FiCommonAddr getFiCommonAddr(final String userCode) {
        return dao.getFiCommonAddr(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCommonAddrManager#saveFiCommonAddr(FiCommonAddr fiCommonAddr)
     */
    public void saveFiCommonAddr(FiCommonAddr fiCommonAddr) {
        dao.saveFiCommonAddr(fiCommonAddr);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCommonAddrManager#removeFiCommonAddr(String userCode)
     */
    public void removeFiCommonAddr(final String userCode) {
        dao.removeFiCommonAddr(new String(userCode));
    }
    //added for getFiCommonAddrsByCrm
    public List getFiCommonAddrsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiCommonAddrsByCrm(crm,pager);
    }
}
