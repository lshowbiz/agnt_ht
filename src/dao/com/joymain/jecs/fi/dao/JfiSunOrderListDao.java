
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiSunOrderListDao extends Dao {

    /**
     * Retrieves all of the jfiSunOrderLists
     */
    public List getJfiSunOrderLists(JfiSunOrderList jfiSunOrderList);

    /**
     * Gets jfiSunOrderList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param molId the jfiSunOrderList's molId
     * @return jfiSunOrderList populated jfiSunOrderList object
     */
    public JfiSunOrderList getJfiSunOrderList(final Long molId);

    /**
     * Saves a jfiSunOrderList's information
     * @param jfiSunOrderList the object to be saved
     */    
    public void saveJfiSunOrderList(JfiSunOrderList jfiSunOrderList);

    /**
     * Removes a jfiSunOrderList from the database by molId
     * @param molId the jfiSunOrderList's molId
     */
    public void removeJfiSunOrderList(final Long molId);
    //added for getJfiSunOrderListsByCrm
    public List getJfiSunOrderListsByCrm(CommonRecord crm, Pager pager);
}

