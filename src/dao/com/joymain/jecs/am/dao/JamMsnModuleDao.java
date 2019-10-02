
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JamMsnModuleDao extends Dao {

    /**
     * Retrieves all of the jamMsnModules
     */
    public List getJamMsnModules(JamMsnModule jamMsnModule);

    /**
     * Gets jamMsnModule's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jmmNo the jamMsnModule's jmmNo
     * @return jamMsnModule populated jamMsnModule object
     */
    public JamMsnModule getJamMsnModule(final Long jmmNo);

    /**
     * Saves a jamMsnModule's information
     * @param jamMsnModule the object to be saved
     */    
    public void saveJamMsnModule(JamMsnModule jamMsnModule);

    /**
     * Removes a jamMsnModule from the database by jmmNo
     * @param jmmNo the jamMsnModule's jmmNo
     */
    public void removeJamMsnModule(final Long jmmNo);
    //added for getJamMsnModulesByCrm
    public List getJamMsnModulesByCrm(CommonRecord crm, Pager pager);

	public List getJamMsnModuleByMtId(Long mtId) ;
	
	public List getJamMsnModeulBySql(String userCode, String msnKey,String cardType);
}

