
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.pm.dao.JpmWineOrderListInterfaceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmWineOrderListInterfaceManager extends Manager {
    /**
     * Retrieves all of the jpmWineOrderListInterfaces
     */
    public List getJpmWineOrderListInterfaces(JpmWineOrderListInterface jpmWineOrderListInterface);

    /**
     * Gets jpmWineOrderListInterface's information based on idf.
     * @param idf the jpmWineOrderListInterface's idf
     * @return jpmWineOrderListInterface populated jpmWineOrderListInterface object
     */
    public JpmWineOrderListInterface getJpmWineOrderListInterface(final String idf);

    /**
     * Saves a jpmWineOrderListInterface's information
     * @param jpmWineOrderListInterface the object to be saved
     */
    public void saveJpmWineOrderListInterface(JpmWineOrderListInterface jpmWineOrderListInterface);

    /**
     * Removes a jpmWineOrderListInterface from the database by idf
     * @param idf the jpmWineOrderListInterface's idf
     */
    public void removeJpmWineOrderListInterface(final String idf);
    //added for getJpmWineOrderListInterfacesByCrm
    public List getJpmWineOrderListInterfacesByCrm(CommonRecord crm, Pager pager);
}

