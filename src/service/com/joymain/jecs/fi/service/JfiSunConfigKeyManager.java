
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunConfigKey;
import com.joymain.jecs.fi.dao.JfiSunConfigKeyDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunConfigKeyManager extends Manager {
    /**
     * Retrieves all of the jfiSunConfigKeys
     */
    public List getJfiSunConfigKeys(JfiSunConfigKey jfiSunConfigKey);

    /**
     * Gets jfiSunConfigKey's information based on configCode.
     * @param configCode the jfiSunConfigKey's configCode
     * @return jfiSunConfigKey populated jfiSunConfigKey object
     */
    public JfiSunConfigKey getJfiSunConfigKey(final String configCode);

    /**
     * Saves a jfiSunConfigKey's information
     * @param jfiSunConfigKey the object to be saved
     */
    public void saveJfiSunConfigKey(JfiSunConfigKey jfiSunConfigKey);

    /**
     * Removes a jfiSunConfigKey from the database by configCode
     * @param configCode the jfiSunConfigKey's configCode
     */
    public void removeJfiSunConfigKey(final String configCode);
    //added for getJfiSunConfigKeysByCrm
    public List getJfiSunConfigKeysByCrm(CommonRecord crm, Pager pager);
}

