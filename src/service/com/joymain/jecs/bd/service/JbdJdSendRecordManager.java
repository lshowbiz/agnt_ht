
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.dao.JbdJdSendRecordDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdJdSendRecordManager extends Manager {
    /**
     * Retrieves all of the jbdJdSendRecords
     */
    public List getJbdJdSendRecords(JbdJdSendRecord jbdJdSendRecord);

    /**
     * Gets jbdJdSendRecord's information based on id.
     * @param id the jbdJdSendRecord's id
     * @return jbdJdSendRecord populated jbdJdSendRecord object
     */
    public JbdJdSendRecord getJbdJdSendRecord(final String id);

    /**
     * Saves a jbdJdSendRecord's information
     * @param jbdJdSendRecord the object to be saved
     */
    public void saveJbdJdSendRecord(JbdJdSendRecord jbdJdSendRecord);

    /**
     * Removes a jbdJdSendRecord from the database by id
     * @param id the jbdJdSendRecord's id
     */
    public void removeJbdJdSendRecord(final String id);
    //added for getJbdJdSendRecordsByCrm
    public List getJbdJdSendRecordsByCrm(CommonRecord crm, Pager pager);
    public void saveInJdFiBook(SysUser defSysUser,String id);
}

