
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JocsInterfaceRetransmissionDao extends Dao {

    /**
     * Retrieves all of the jocsInterfaceRetransmissions
     */
    public List getJocsInterfaceRetransmissions(JocsInterfaceRetransmission jocsInterfaceRetransmission);

    /**
     * Gets jocsInterfaceRetransmission's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jocsInterfaceRetransmission's logId
     * @return jocsInterfaceRetransmission populated jocsInterfaceRetransmission object
     */
    public JocsInterfaceRetransmission getJocsInterfaceRetransmission(final BigDecimal logId);

    /**
     * Saves a jocsInterfaceRetransmission's information
     * @param jocsInterfaceRetransmission the object to be saved
     */    
    public void saveJocsInterfaceRetransmission(JocsInterfaceRetransmission jocsInterfaceRetransmission);

    /**
     * Removes a jocsInterfaceRetransmission from the database by logId
     * @param logId the jocsInterfaceRetransmission's logId
     */
    public void removeJocsInterfaceRetransmission(final BigDecimal logId);
    //added for getJocsInterfaceRetransmissionsByCrm
    public List getJocsInterfaceRetransmissionsByCrm(CommonRecord crm, Pager pager);
}

