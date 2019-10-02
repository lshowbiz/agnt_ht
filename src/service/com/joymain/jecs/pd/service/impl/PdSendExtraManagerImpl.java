
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdSendExtra;
import com.joymain.jecs.pd.dao.PdSendExtraDao;
import com.joymain.jecs.pd.service.PdSendExtraManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdSendExtraManagerImpl extends BaseManager implements PdSendExtraManager {
    private PdSendExtraDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdSendExtraDao(PdSendExtraDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendExtraManager#getPdSendExtras(com.joymain.jecs.pd.model.PdSendExtra)
     */
    public List getPdSendExtras(final PdSendExtra pdSendExtra) {
        return dao.getPdSendExtras(pdSendExtra);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendExtraManager#getPdSendExtra(String uniId)
     */
    public PdSendExtra getPdSendExtra(final String uniId) {
        return dao.getPdSendExtra(new Long(uniId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendExtraManager#savePdSendExtra(PdSendExtra pdSendExtra)
     */
    public void savePdSendExtra(PdSendExtra pdSendExtra) {
        dao.savePdSendExtra(pdSendExtra);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdSendExtraManager#removePdSendExtra(String uniId)
     */
    public void removePdSendExtra(final String uniId) {
        dao.removePdSendExtra(new Long(uniId));
    }
    //added for getPdSendExtrasByCrm
    public List getPdSendExtrasByCrm(CommonRecord crm, Pager pager){
	return dao.getPdSendExtrasByCrm(crm,pager);
    }
}
