
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineSettingInfDao extends Dao {

    /**
     * Retrieves all of the jpmWineSettingInfs
     */
    public List getJpmWineSettingInfs(JpmWineSettingInf jpmWineSettingInf);

    /**
     * Gets jpmWineSettingInf's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param settingId the jpmWineSettingInf's settingId
     * @return jpmWineSettingInf populated jpmWineSettingInf object
     */
    public JpmWineSettingInf getJpmWineSettingInf(final Long settingId);

    /**
     * Saves a jpmWineSettingInf's information
     * @param jpmWineSettingInf the object to be saved
     */    
    public void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf);

    /**
     * Removes a jpmWineSettingInf from the database by settingId
     * @param settingId the jpmWineSettingInf's settingId
     */
    public void removeJpmWineSettingInf(final Long settingId);
    //added for getJpmWineSettingInfsByCrm
    public List getJpmWineSettingInfsByCrm(CommonRecord crm, Pager pager);
}

