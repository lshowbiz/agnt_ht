
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.mi.dao.JmiTicketDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiTicketManager extends Manager {
    /**
     * Retrieves all of the jmiTickets
     */
    public List getJmiTickets(JmiTicket jmiTicket);

    /**
     * Gets jmiTicket's information based on id.
     * @param id the jmiTicket's id
     * @return jmiTicket populated jmiTicket object
     */
    public JmiTicket getJmiTicket(final String id);

    /**
     * Saves a jmiTicket's information
     * @param jmiTicket the object to be saved
     */
    public void saveJmiTicket(JmiTicket jmiTicket);

    /**
     * Removes a jmiTicket from the database by id
     * @param id the jmiTicket's id
     */
    public void removeJmiTicket(final String id);
    //added for getJmiTicketsByCrm
    public List getJmiTicketsByCrm(CommonRecord crm, Pager pager);
    public void saveJmiTickets(List<JmiTicket> jmiTickets);
}

