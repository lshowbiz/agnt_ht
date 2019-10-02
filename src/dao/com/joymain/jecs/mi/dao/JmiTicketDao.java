
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiTicketDao extends Dao {

    /**
     * Retrieves all of the jmiTickets
     */
    public List getJmiTickets(JmiTicket jmiTicket);

    /**
     * Gets jmiTicket's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiTicket's id
     * @return jmiTicket populated jmiTicket object
     */
    public JmiTicket getJmiTicket(final Long id);

    /**
     * Saves a jmiTicket's information
     * @param jmiTicket the object to be saved
     */    
    public void saveJmiTicket(JmiTicket jmiTicket);

    /**
     * Removes a jmiTicket from the database by id
     * @param id the jmiTicket's id
     */
    public void removeJmiTicket(final Long id);
    //added for getJmiTicketsByCrm
    public List getJmiTicketsByCrm(CommonRecord crm, Pager pager);
    public void saveJmiTickets(List<JmiTicket> jmiTickets);
}

