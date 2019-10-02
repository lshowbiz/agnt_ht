
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.dao.JmiStoreDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiStoreManager extends Manager {
    /**
     * Retrieves all of the jmiStores
     */
    public List getJmiStores(JmiStore jmiStore);

    /**
     * Gets jmiStore's information based on id.
     * @param id the jmiStore's id
     * @return jmiStore populated jmiStore object
     */
    public JmiStore getJmiStore(final String id);

    /**
     * Saves a jmiStore's information
     * @param jmiStore the object to be saved
     */
    public void saveJmiStore(JmiStore jmiStore);

    /**
     * Removes a jmiStore from the database by id
     * @param id the jmiStore's id
     */
    public void removeJmiStore(final String id);
    //added for getJmiStoresByCrm
    public List getJmiStoresByCrm(CommonRecord crm, Pager pager);
    public JmiStore getJmiStoreByUserCode(String userCode);
	 /**
     * 
     * @param strCodes
     * @param sysUser
     * @param checkStatus 1审核成功，3不成功
     */
	public void checkJmiStore(JmiStore jmiStore,SysUser sysUser,String checkStatus) ;
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param confirmStatus 1 未确认转确认 2 未确认转不确认
     */
	public void confirmJmiStore(JmiStore jmiStore, SysUser sysUser,String confirmStatus);
}

