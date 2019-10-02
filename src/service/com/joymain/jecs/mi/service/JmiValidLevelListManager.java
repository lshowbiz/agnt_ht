
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.mi.dao.JmiValidLevelListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiValidLevelListManager extends Manager {
    /**
     * Retrieves all of the jmiValidLevelLists
     */
    public List getJmiValidLevelLists(JmiValidLevelList jmiValidLevelList);

    /**
     * Gets jmiValidLevelList's information based on id.
     * @param id the jmiValidLevelList's id
     * @return jmiValidLevelList populated jmiValidLevelList object
     */
    public JmiValidLevelList getJmiValidLevelList(final String id);

    /**
     * Saves a jmiValidLevelList's information
     * @param jmiValidLevelList the object to be saved
     */
    public void saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList);

    /**
     * Removes a jmiValidLevelList from the database by id
     * @param id the jmiValidLevelList's id
     */
    public void removeJmiValidLevelList(final String id);
    //added for getJmiValidLevelListsByCrm
    public List getJmiValidLevelListsByCrm(CommonRecord crm, Pager pager);
	public JmiValidLevelList getJmiValidLevelListsByUserCode(String userCode, Integer wweek);
	public void saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList,SysUser defSysUser);
	public void saveJmiValidLevelListImport(List<JmiValidLevelList> list,SysUser defSysUser);
}

