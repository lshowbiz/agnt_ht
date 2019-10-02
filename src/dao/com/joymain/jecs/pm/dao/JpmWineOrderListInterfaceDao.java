package com.joymain.jecs.pm.dao;

import java.util.Collection;
import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineOrderListInterfaceDao extends Dao {

    /**
     * Retrieves all of the jpmWineOrderListInterfaces
     */
    public List getJpmWineOrderListInterfaces(JpmWineOrderListInterface jpmWineOrderListInterface);

    /**
     * Gets jpmWineOrderListInterface's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     * 
     * @param idf the jpmWineOrderListInterface's idf
     * @return jpmWineOrderListInterface populated jpmWineOrderListInterface
     *         object
     */
    public JpmWineOrderListInterface getJpmWineOrderListInterface(final Long idf);

    /**
     * Saves a jpmWineOrderListInterface's information
     * 
     * @param jpmWineOrderListInterface the object to be saved
     */
    public void saveJpmWineOrderListInterface(JpmWineOrderListInterface jpmWineOrderListInterface);

    /**
     * Removes a jpmWineOrderListInterface from the database by idf
     * 
     * @param idf the jpmWineOrderListInterface's idf
     */
    public void removeJpmWineOrderListInterface(final Long idf);

    //added for getJpmWineOrderListInterfacesByCrm
    public List getJpmWineOrderListInterfacesByCrm(CommonRecord crm, Pager pager);

    public void saveJpmWineOrderListInterfaceAll(Collection<JpmWineOrderListInterface> jpmWineOrderListInterfaceSet);
}
