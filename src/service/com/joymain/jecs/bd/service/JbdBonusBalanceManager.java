
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.dao.JbdBonusBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdBonusBalanceManager extends Manager {
    /**
     * Retrieves all of the jbdBonusBalances
     */
    public List getJbdBonusBalances(JbdBonusBalance jbdBonusBalance);

    /**
     * Gets jbdBonusBalance's information based on userCode.
     * @param userCode the jbdBonusBalance's userCode
     * @return jbdBonusBalance populated jbdBonusBalance object
     */
    public JbdBonusBalance getJbdBonusBalance(final String userCode);

    /**
     * Saves a jbdBonusBalance's information
     * @param jbdBonusBalance the object to be saved
     */
    public void saveJbdBonusBalance(JbdBonusBalance jbdBonusBalance);

    /**
     * Removes a jbdBonusBalance from the database by userCode
     * @param userCode the jbdBonusBalance's userCode
     */
    public void removeJbdBonusBalance(final String userCode);
    //added for getJbdBonusBalancesByCrm
    public List getJbdBonusBalancesByCrm(CommonRecord crm, Pager pager);
    
    public void saveApplication(String userCode);
    
    
    public void checkJmiMember(String strReissueCodes[],SysUser defSysUser);
}

