
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmWineOrderInterfaceDao extends Dao {

    /**
     * Retrieves all of the jpmWineOrderInterfaces
     */
    public List getJpmWineOrderInterfaces(JpmWineOrderInterface jpmWineOrderInterface);

    /**
     * Gets jpmWineOrderInterface's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param moId the jpmWineOrderInterface's moId
     * @return jpmWineOrderInterface populated jpmWineOrderInterface object
     */
    public JpmWineOrderInterface getJpmWineOrderInterface(final Long moId);

    /**
     * Saves a jpmWineOrderInterface's information
     * @param jpmWineOrderInterface the object to be saved
     */    
    public void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface);

    /**
     * Removes a jpmWineOrderInterface from the database by moId
     * @param moId the jpmWineOrderInterface's moId
     */
    public void removeJpmWineOrderInterface(final Long moId);
    //added for getJpmWineOrderInterfacesByCrm
    public List getJpmWineOrderInterfacesByCrm(CommonRecord crm, Pager pager);
}

