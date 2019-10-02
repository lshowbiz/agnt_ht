
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSendRecord;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSendRecordDao extends Dao {

    /**
     * Retrieves all of the jbdSendRecords
     */
    public List getJbdSendRecords(JbdSendRecord jbdSendRecord);

    /**
     * Gets jbdSendRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSendRecord's id
     * @return jbdSendRecord populated jbdSendRecord object
     */
    public JbdSendRecord getJbdSendRecord(final Long id);

    /**
     * Saves a jbdSendRecord's information
     * @param jbdSendRecord the object to be saved
     */    
    public void saveJbdSendRecord(JbdSendRecord jbdSendRecord);

    /**
     * Removes a jbdSendRecord from the database by id
     * @param id the jbdSendRecord's id
     */
    public void removeJbdSendRecord(final Long id);
    //added for getJbdSendRecordsByCrm
    public List getJbdSendRecordsByCrm(CommonRecord crm, Pager pager);
    public List getBdSendRecordReport(CommonRecord crm);
}

