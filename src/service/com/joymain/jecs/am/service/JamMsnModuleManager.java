
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.dao.JamMsnModuleDao;
import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JamMsnModuleManager extends Manager {
    /**
     * Retrieves all of the jamMsnModules
     */
    public List getJamMsnModules(JamMsnModule jamMsnModule);

    /**
     * Gets jamMsnModule's information based on jmmNo.
     * @param jmmNo the jamMsnModule's jmmNo
     * @return jamMsnModule populated jamMsnModule object
     */
    public JamMsnModule getJamMsnModule(final String jmmNo);

    /**
     * Saves a jamMsnModule's information
     * @param jamMsnModule the object to be saved
     */
    public void saveJamMsnModule(JamMsnModule jamMsnModule);

    /**
     * Removes a jamMsnModule from the database by jmmNo
     * @param jmmNo the jamMsnModule's jmmNo
     */
    public void removeJamMsnModule(final String jmmNo);
    //added for getJamMsnModulesByCrm
    public List getJamMsnModulesByCrm(CommonRecord crm, Pager pager);
	public List getJamMsnModuleByMtId(Long mtId) ;
	/**
	 * 获取消息模板 可以发送消息返回消息模板，否则返回NULL
	 * @param userCode
	 * @param msnKey
	 * @param cardType
	 * @return
	 */
	public String getJamMsnModeulBySql(String userCode, String msnKey,Object[] objects);
}

