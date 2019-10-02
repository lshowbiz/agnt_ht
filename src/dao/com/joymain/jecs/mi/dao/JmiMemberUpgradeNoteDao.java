
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiMemberUpgradeNoteDao extends Dao {

    /**
     * Retrieves all of the jmiMemberUpgradeNotes
     */
    public List getJmiMemberUpgradeNotes(JmiMemberUpgradeNote jmiMemberUpgradeNote);

    /**
     * Gets jmiMemberUpgradeNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param munId the jmiMemberUpgradeNote's munId
     * @return jmiMemberUpgradeNote populated jmiMemberUpgradeNote object
     */
    public JmiMemberUpgradeNote getJmiMemberUpgradeNote(final Long munId);

    /**
     * Saves a jmiMemberUpgradeNote's information
     * @param jmiMemberUpgradeNote the object to be saved
     */    
    public void saveJmiMemberUpgradeNote(JmiMemberUpgradeNote jmiMemberUpgradeNote);

    /**
     * Removes a jmiMemberUpgradeNote from the database by munId
     * @param munId the jmiMemberUpgradeNote's munId
     */
    public void removeJmiMemberUpgradeNote(final Long munId);
    //added for getJmiMemberUpgradeNotesByCrm
    public List getJmiMemberUpgradeNotesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据订单号返回升级记录
     * @param orderId
     * @return
     */
    public List getJmiMemberUpgradeNoteByOrderId(String orderId);
}

