
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiSunMemberOrderList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiSunMemberOrderListDao extends Dao {

    /**
     * Retrieves all of the jfiSunMemberOrderLists
     */
    public List getJfiSunMemberOrderLists(JfiSunMemberOrderList jfiSunMemberOrderList);

    /**
     * Gets jfiSunMemberOrderList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param molId the jfiSunMemberOrderList's molId
     * @return jfiSunMemberOrderList populated jfiSunMemberOrderList object
     */
    public JfiSunMemberOrderList getJfiSunMemberOrderList(final Long molId);

    /**
     * Saves a jfiSunMemberOrderList's information
     * @param jfiSunMemberOrderList the object to be saved
     */    
    public void saveJfiSunMemberOrderList(JfiSunMemberOrderList jfiSunMemberOrderList);

    /**
     * Removes a jfiSunMemberOrderList from the database by molId
     * @param molId the jfiSunMemberOrderList's molId
     */
    public void removeJfiSunMemberOrderList(final Long molId);
    //added for getJfiSunMemberOrderListsByCrm
    public List getJfiSunMemberOrderListsByCrm(CommonRecord crm, Pager pager);
}

