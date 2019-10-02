
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.fi.dao.JfiDepositSendDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiDepositSendManager extends Manager {
    /**
     * Retrieves all of the jfiDepositSends
     */
    public List getJfiDepositSends(JfiDepositSend jfiDepositSend);

    /**
     * Gets jfiDepositSend's information based on id.
     * @param id the jfiDepositSend's id
     * @return jfiDepositSend populated jfiDepositSend object
     */
    public JfiDepositSend getJfiDepositSend(final String id);

    /**
     * Saves a jfiDepositSend's information
     * @param jfiDepositSend the object to be saved
     */
    public void saveJfiDepositSend(JfiDepositSend jfiDepositSend);

    /**
     * Removes a jfiDepositSend from the database by id
     * @param id the jfiDepositSend's id
     */
    public void removeJfiDepositSend(final String id);
    //added for getJfiDepositSendsByCrm
    public List getJfiDepositSendsByCrm(CommonRecord crm, Pager pager);
    public void saveJfiDepositSend(List<JfiDepositSend> jfiDepositSends);
	public BigDecimal getSumDepositMoney(CommonRecord crm);
	public void sendToBankbook(SysUser defSysUser,String id);
}

