
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunConfigKey;
import com.joymain.jecs.fi.dao.JfiSunConfigKeyDao;
import com.joymain.jecs.fi.service.JfiSunConfigKeyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunConfigKeyManagerImpl extends BaseManager implements JfiSunConfigKeyManager {
    private JfiSunConfigKeyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunConfigKeyDao(JfiSunConfigKeyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunConfigKeyManager#getJfiSunConfigKeys(com.joymain.jecs.fi.model.JfiSunConfigKey)
     */
    public List getJfiSunConfigKeys(final JfiSunConfigKey jfiSunConfigKey) {
        return dao.getJfiSunConfigKeys(jfiSunConfigKey);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunConfigKeyManager#getJfiSunConfigKey(String configCode)
     */
    public JfiSunConfigKey getJfiSunConfigKey(final String configCode) {
        return dao.getJfiSunConfigKey(new String(configCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunConfigKeyManager#saveJfiSunConfigKey(JfiSunConfigKey jfiSunConfigKey)
     */
    public void saveJfiSunConfigKey(JfiSunConfigKey jfiSunConfigKey) {
        dao.saveJfiSunConfigKey(jfiSunConfigKey);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunConfigKeyManager#removeJfiSunConfigKey(String configCode)
     */
    public void removeJfiSunConfigKey(final String configCode) {
        dao.removeJfiSunConfigKey(new String(configCode));
    }
    //added for getJfiSunConfigKeysByCrm
    public List getJfiSunConfigKeysByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunConfigKeysByCrm(crm,pager);
    }
}
