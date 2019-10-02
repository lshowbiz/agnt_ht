
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdSubBonusList;
import com.joymain.jecs.bd.dao.JbdSubBonusListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSubBonusListManager extends Manager {
    /**
     * Retrieves all of the jbdSubBonusLists
     */
    public List getJbdSubBonusLists(JbdSubBonusList jbdSubBonusList);

    /**
     * Gets jbdSubBonusList's information based on id.
     * @param id the jbdSubBonusList's id
     * @return jbdSubBonusList populated jbdSubBonusList object
     */
    public JbdSubBonusList getJbdSubBonusList(final String id);

    /**
     * Saves a jbdSubBonusList's information
     * @param jbdSubBonusList the object to be saved
     */
    public void saveJbdSubBonusList(JbdSubBonusList jbdSubBonusList);

    /**
     * Removes a jbdSubBonusList from the database by id
     * @param id the jbdSubBonusList's id
     */
    public void removeJbdSubBonusList(final String id);
    //added for getJbdSubBonusListsByCrm
    public List getJbdSubBonusListsByCrm(CommonRecord crm, Pager pager);
    public List getJbdSubBonusListsBySql(CommonRecord crm, Pager pager);
	public void saveInSubBonusFiBook(SysUser defSysUser,String id);
}

