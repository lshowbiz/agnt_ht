package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.dao.JpmWineOrderInterfaceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineOrderInterfaceManager extends Manager {
    /**
     * Retrieves all of the jpmWineOrderInterfaces
     */
    public List getJpmWineOrderInterfaces(JpmWineOrderInterface jpmWineOrderInterface);

    /**
     * Gets jpmWineOrderInterface's information based on moId.
     * 
     * @param moId the jpmWineOrderInterface's moId
     * @return jpmWineOrderInterface populated jpmWineOrderInterface object
     */
    public JpmWineOrderInterface getJpmWineOrderInterface(final String moId);

    /**
     * Saves a jpmWineOrderInterface's information
     * 
     * @param jpmWineOrderInterface the object to be saved
     */
    public void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface);

    /**
     * Removes a jpmWineOrderInterface from the database by moId
     * 
     * @param moId the jpmWineOrderInterface's moId
     */
    public void removeJpmWineOrderInterface(final String moId);

    //added for getJpmWineOrderInterfacesByCrm
    public List getJpmWineOrderInterfacesByCrm(CommonRecord crm, Pager pager);

    public int rePushOrderToERP(final String moId);
}
