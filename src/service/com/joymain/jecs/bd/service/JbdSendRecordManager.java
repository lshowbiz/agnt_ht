
package com.joymain.jecs.bd.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdSendRecord;
import com.joymain.jecs.bd.dao.JbdSendRecordDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSendRecordManager extends Manager {
    /**
     * Retrieves all of the jbdSendRecords
     */
    public List getJbdSendRecords(JbdSendRecord jbdSendRecord);

    /**
     * Gets jbdSendRecord's information based on id.
     * @param id the jbdSendRecord's id
     * @return jbdSendRecord populated jbdSendRecord object
     */
    public JbdSendRecord getJbdSendRecord(final String id);

    /**
     * Saves a jbdSendRecord's information
     * @param jbdSendRecord the object to be saved
     */
    public void saveJbdSendRecord(JbdSendRecord jbdSendRecord);

    /**
     * Removes a jbdSendRecord from the database by id
     * @param id the jbdSendRecord's id
     */
    public void removeJbdSendRecord(final String id);
    //added for getJbdSendRecordsByCrm
    public List getJbdSendRecordsByCrm(CommonRecord crm, Pager pager);
    public List getBdSendRecordReport(CommonRecord crm);
    
}

