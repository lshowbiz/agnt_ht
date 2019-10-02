
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JmiZcwMember;
import com.joymain.jecs.fi.dao.JmiZcwMemberDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiZcwMemberManager extends Manager {
    /**
     * Retrieves all of the jmiZcwMembers
     */
    public List getJmiZcwMembers(JmiZcwMember jmiZcwMember);

    /**
     * Gets jmiZcwMember's information based on zcwId.
     * @param zcwId the jmiZcwMember's zcwId
     * @return jmiZcwMember populated jmiZcwMember object
     */
    public JmiZcwMember getJmiZcwMember(final String zcwId);

    /**
     * Saves a jmiZcwMember's information
     * @param jmiZcwMember the object to be saved
     */
    public void saveJmiZcwMember(JmiZcwMember jmiZcwMember);

    /**
     * Removes a jmiZcwMember from the database by zcwId
     * @param zcwId the jmiZcwMember's zcwId
     */
    public void removeJmiZcwMember(final String zcwId);
    //added for getJmiZcwMembersByCrm
    public List getJmiZcwMembersByCrm(CommonRecord crm, Pager pager);
}

