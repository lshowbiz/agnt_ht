
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineSettingListInfDao extends Dao {

    /**
     * Retrieves all of the jpmWineSettingListInfs
     */
    public List getJpmWineSettingListInfs(JpmWineSettingListInf jpmWineSettingListInf);

    /**
     * Gets jpmWineSettingListInf's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param idf the jpmWineSettingListInf's idf
     * @return jpmWineSettingListInf populated jpmWineSettingListInf object
     */
    public JpmWineSettingListInf getJpmWineSettingListInf(final Long idf);

    /**
     * Saves a jpmWineSettingListInf's information
     * @param jpmWineSettingListInf the object to be saved
     */    
    public void saveJpmWineSettingListInf(JpmWineSettingListInf jpmWineSettingListInf);

    /**
     * Removes a jpmWineSettingListInf from the database by idf
     * @param idf the jpmWineSettingListInf's idf
     */
    public void removeJpmWineSettingListInf(final Long idf);
    //added for getJpmWineSettingListInfsByCrm
    public List getJpmWineSettingListInfsByCrm(CommonRecord crm, Pager pager);
}

