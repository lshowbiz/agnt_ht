
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdJdSendRecordDao extends Dao {

    /**
     * Retrieves all of the jbdJdSendRecords
     */
    public List getJbdJdSendRecords(JbdJdSendRecord jbdJdSendRecord);

    /**
     * Gets jbdJdSendRecord's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdJdSendRecord's id
     * @return jbdJdSendRecord populated jbdJdSendRecord object
     */
    public JbdJdSendRecord getJbdJdSendRecord(final Long id);

    /**
     * Saves a jbdJdSendRecord's information
     * @param jbdJdSendRecord the object to be saved
     */    
    public void saveJbdJdSendRecord(JbdJdSendRecord jbdJdSendRecord);

    /**
     * Removes a jbdJdSendRecord from the database by id
     * @param id the jbdJdSendRecord's id
     */
    public void removeJbdJdSendRecord(final Long id);
    //added for getJbdJdSendRecordsByCrm
    public List getJbdJdSendRecordsByCrm(CommonRecord crm, Pager pager);
}

