
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.dao.JamMsnTypeDao;
import com.joymain.jecs.am.service.JamMsnTypeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JamMsnTypeManagerImpl extends BaseManager implements JamMsnTypeManager {
    private JamMsnTypeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJamMsnTypeDao(JamMsnTypeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnTypeManager#getJamMsnTypes(com.joymain.jecs.am.model.JamMsnType)
     */
    public List getJamMsnTypes(final JamMsnType jamMsnType) {
        return dao.getJamMsnTypes(jamMsnType);
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnTypeManager#getJamMsnType(String mtId)
     */
    public JamMsnType getJamMsnType(final String mtId) {
        return dao.getJamMsnType(new Long(mtId));
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnTypeManager#saveJamMsnType(JamMsnType jamMsnType)
     */
    public void saveJamMsnType(JamMsnType jamMsnType) {
        dao.saveJamMsnType(jamMsnType);
    }

    /**
     * @see com.joymain.jecs.am.service.JamMsnTypeManager#removeJamMsnType(String mtId)
     */
    public void removeJamMsnType(final String mtId) {
        dao.removeJamMsnType(new Long(mtId));
    }
    //added for getJamMsnTypesByCrm
    public List getJamMsnTypesByCrm(CommonRecord crm, Pager pager){
	return dao.getJamMsnTypesByCrm(crm,pager);
    }
}
