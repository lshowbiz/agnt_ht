
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.mi.dao.JmiTicketDao;
import com.joymain.jecs.mi.service.JmiTicketManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiTicketManagerImpl extends BaseManager implements JmiTicketManager {
    private JmiTicketDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiTicketDao(JmiTicketDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTicketManager#getJmiTickets(com.joymain.jecs.mi.model.JmiTicket)
     */
    public List getJmiTickets(final JmiTicket jmiTicket) {
        return dao.getJmiTickets(jmiTicket);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTicketManager#getJmiTicket(String id)
     */
    public JmiTicket getJmiTicket(final String id) {
        return dao.getJmiTicket(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTicketManager#saveJmiTicket(JmiTicket jmiTicket)
     */
    public void saveJmiTicket(JmiTicket jmiTicket) {
        dao.saveJmiTicket(jmiTicket);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTicketManager#removeJmiTicket(String id)
     */
    public void removeJmiTicket(final String id) {
        dao.removeJmiTicket(new Long(id));
    }
    //added for getJmiTicketsByCrm
    public List getJmiTicketsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiTicketsByCrm(crm,pager);
    }

	@Override
	public void saveJmiTickets(List<JmiTicket> jmiTickets) {
		dao.saveJmiTickets(jmiTickets);
	}
}
