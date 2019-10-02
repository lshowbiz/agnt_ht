
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.MiCoinLog;
import com.joymain.jecs.mi.dao.MiCoinLogDao;
import com.joymain.jecs.mi.service.MiCoinLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class MiCoinLogManagerImpl extends BaseManager implements MiCoinLogManager {
    private MiCoinLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setMiCoinLogDao(MiCoinLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.MiCoinLogManager#getMiCoinLogs(com.joymain.jecs.mi.model.MiCoinLog)
     */
    public List getMiCoinLogs(final MiCoinLog miCoinLog) {
        return dao.getMiCoinLogs(miCoinLog);
    }

    /**
     * @see com.joymain.jecs.mi.service.MiCoinLogManager#getMiCoinLog(String id)
     */
    public MiCoinLog getMiCoinLog(final String id) {
        return dao.getMiCoinLog(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.MiCoinLogManager#saveMiCoinLog(MiCoinLog miCoinLog)
     */
    public void saveMiCoinLog(MiCoinLog miCoinLog) {
        dao.saveMiCoinLog(miCoinLog);
    }

    /**
     * @see com.joymain.jecs.mi.service.MiCoinLogManager#removeMiCoinLog(String id)
     */
    public void removeMiCoinLog(final String id) {
        dao.removeMiCoinLog(new Long(id));
    }
    //added for getMiCoinLogsByCrm
    public List getMiCoinLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getMiCoinLogsByCrm(crm,pager);
    }

	@Override
	public BigDecimal getSumCoin(CommonRecord crm) {
		return dao.getSumCoin(crm);
	}
    
    
}
