
package com.joymain.jecs.bd.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdUserCompanyCode;
import com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdUserCompanyCodeManager extends Manager {
    /**
     * Retrieves all of the jbdUserCompanyCodes
     */
    public List getJbdUserCompanyCodes(JbdUserCompanyCode jbdUserCompanyCode);

    /**
     * Gets jbdUserCompanyCode's information based on id.
     * @param id the jbdUserCompanyCode's id
     * @return jbdUserCompanyCode populated jbdUserCompanyCode object
     */
    public JbdUserCompanyCode getJbdUserCompanyCode(final String id);

    /**
     * Saves a jbdUserCompanyCode's information
     * @param jbdUserCompanyCode the object to be saved
     */
    public void saveJbdUserCompanyCode(JbdUserCompanyCode jbdUserCompanyCode);

    /**
     * Removes a jbdUserCompanyCode from the database by id
     * @param id the jbdUserCompanyCode's id
     */
    public void removeJbdUserCompanyCode(final String id);
    //added for getJbdUserCompanyCodesByCrm
    public List getJbdUserCompanyCodesByCrm(CommonRecord crm, Pager pager);
    
    public void changeCompanyCode(String userCode, Integer wweek, String newCompanyCode,String updateType,HttpServletRequest request,SysUser defSysUser);
}

