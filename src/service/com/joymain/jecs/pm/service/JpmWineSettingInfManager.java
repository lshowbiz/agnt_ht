package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.pm.dao.JpmWineSettingInfDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineSettingInfManager extends Manager {
    /**
     * Retrieves all of the jpmWineSettingInfs
     */
    public List getJpmWineSettingInfs(JpmWineSettingInf jpmWineSettingInf);

    /**
     * Gets jpmWineSettingInf's information based on settingId.
     * 
     * @param settingId the jpmWineSettingInf's settingId
     * @return jpmWineSettingInf populated jpmWineSettingInf object
     */
    public JpmWineSettingInf getJpmWineSettingInf(final String settingId);

    /**
     * Saves a jpmWineSettingInf's information
     * 
     * @param jpmWineSettingInf the object to be saved
     */
    public void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf);

    /**
     * Removes a jpmWineSettingInf from the database by settingId
     * 
     * @param settingId the jpmWineSettingInf's settingId
     */
    public void removeJpmWineSettingInf(final String settingId);

    //added for getJpmWineSettingInfsByCrm
    public List getJpmWineSettingInfsByCrm(CommonRecord crm, Pager pager);

    public int rePushSettingToERP(String settingId);
}
