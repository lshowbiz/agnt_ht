
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.mi.dao.JmiBlacklistDao;
import com.joymain.jecs.mi.service.JmiBlacklistManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiBlacklistManagerImpl extends BaseManager implements JmiBlacklistManager {
    private JmiBlacklistDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiBlacklistDao(JmiBlacklistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiBlacklistManager#getJmiBlacklists(com.joymain.jecs.mi.model.JmiBlacklist)
     */
    public List getJmiBlacklists(final JmiBlacklist jmiBlacklist) {
        return dao.getJmiBlacklists(jmiBlacklist);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiBlacklistManager#getJmiBlacklist(String blId)
     */
    public JmiBlacklist getJmiBlacklist(final String blId) {
        return dao.getJmiBlacklist(new Long(blId));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiBlacklistManager#saveJmiBlacklist(JmiBlacklist jmiBlacklist)
     */
    public void saveJmiBlacklist(JmiBlacklist jmiBlacklist) {
        dao.saveJmiBlacklist(jmiBlacklist);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiBlacklistManager#removeJmiBlacklist(String blId)
     */
    public void removeJmiBlacklist(final String blId) {
        dao.removeJmiBlacklist(new Long(blId));
    }
    //added for getJmiBlacklistsByCrm
    public List getJmiBlacklistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiBlacklistsByCrm(crm,pager);
    }

	public boolean getCheckJmiBlacklist(String papertype, String papernumber) {
		return dao.getCheckJmiBlacklist(papertype, papernumber);
	}
}
