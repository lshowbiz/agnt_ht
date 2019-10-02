
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.pm.dao.JpmWineSettingListInfDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmWineSettingListInfManager extends Manager {
    /**
     * Retrieves all of the jpmWineSettingListInfs
     */
    public List getJpmWineSettingListInfs(JpmWineSettingListInf jpmWineSettingListInf);

    /**
     * Gets jpmWineSettingListInf's information based on idf.
     * @param idf the jpmWineSettingListInf's idf
     * @return jpmWineSettingListInf populated jpmWineSettingListInf object
     */
    public JpmWineSettingListInf getJpmWineSettingListInf(final String idf);

    /**
     * Saves a jpmWineSettingListInf's information
     * @param jpmWineSettingListInf the object to be saved
     */
    public void saveJpmWineSettingListInf(JpmWineSettingListInf jpmWineSettingListInf);

    /**
     * Removes a jpmWineSettingListInf from the database by idf
     * @param idf the jpmWineSettingListInf's idf
     */
    public void removeJpmWineSettingListInf(final String idf);
    //added for getJpmWineSettingListInfsByCrm
    public List getJpmWineSettingListInfsByCrm(CommonRecord crm, Pager pager);
}

