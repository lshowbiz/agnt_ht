
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.dao.JmiSubStoreDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiSubStoreManager extends Manager {
    /**
     * Retrieves all of the jmiSubStores
     */
    public List getJmiSubStores(JmiSubStore jmiSubStore);

    /**
     * Gets jmiSubStore's information based on id.
     * @param id the jmiSubStore's id
     * @return jmiSubStore populated jmiSubStore object
     */
    public JmiSubStore getJmiSubStore(final String id);

    /**
     * Saves a jmiSubStore's information
     * @param jmiSubStore the object to be saved
     */
    public void saveJmiSubStore(JmiSubStore jmiSubStore);

    /**
     * Removes a jmiSubStore from the database by id
     * @param id the jmiSubStore's id
     */
    public void removeJmiSubStore(final String id);
    //added for getJmiSubStoresByCrm
    public List getJmiSubStoresByCrm(CommonRecord crm, Pager pager);
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param checkStatus 1审核成功，3不成功
     */
    public void checkJmiSubStore(String[] strCodes,SysUser sysUser,String checkStatus,String checkRemark);
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param confirmStatus 2 未确认转确认 3 未确认转不确认
     */
    public void confirmJmiSubStore(String[] strCodes,SysUser sysUser,String confirmStatus,String confirmRemark);
    /**
     *  新建并审核确认
     * @param jmiSubStore
     */

    public void saveJmiSubStoreCheck(JmiSubStore jmiSubStore,JmiMember jmiMember);

    public JmiSubStore getJmiSubStoresByUserCode(String userCode);
	/**
     * 
     * @param strCodes
     * @param sysUser
     * @param checkStatus 1审核成功，3不成功
     */
	public void addrCheckJmiSubStore(String id,SysUser sysUser,String checkStatus);
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param confirmStatus 1 未确认转确认 2 未确认转不确认
     */
	public void addrConfirmJmiSubStore(String id, SysUser sysUser,String confirmStatus) ;
	
}

