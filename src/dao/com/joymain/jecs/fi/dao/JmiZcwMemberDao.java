
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JmiZcwMember;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiZcwMemberDao extends Dao {

    /**
     * Retrieves all of the jmiZcwMembers
     */
    public List getJmiZcwMembers(JmiZcwMember jmiZcwMember);

    /**
     * Gets jmiZcwMember's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param zcwId the jmiZcwMember's zcwId
     * @return jmiZcwMember populated jmiZcwMember object
     */
    public JmiZcwMember getJmiZcwMember(final BigDecimal zcwId);

    /**
     * Saves a jmiZcwMember's information
     * @param jmiZcwMember the object to be saved
     */    
    public void saveJmiZcwMember(JmiZcwMember jmiZcwMember);

    /**
     * Removes a jmiZcwMember from the database by zcwId
     * @param zcwId the jmiZcwMember's zcwId
     */
    public void removeJmiZcwMember(final BigDecimal zcwId);
    //added for getJmiZcwMembersByCrm
    public List getJmiZcwMembersByCrm(CommonRecord crm, Pager pager);
}

