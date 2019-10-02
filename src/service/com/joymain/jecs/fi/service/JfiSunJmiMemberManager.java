
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunJmiMember;
import com.joymain.jecs.fi.dao.JfiSunJmiMemberDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunJmiMemberManager extends Manager {
    /**
     * Retrieves all of the jfiSunJmiMembers
     */
    public List getJfiSunJmiMembers(JfiSunJmiMember jfiSunJmiMember);

    /**
     * Gets jfiSunJmiMember's information based on userCode.
     * @param userCode the jfiSunJmiMember's userCode
     * @return jfiSunJmiMember populated jfiSunJmiMember object
     */
    public JfiSunJmiMember getJfiSunJmiMember(final String userCode);

    /**
     * Saves a jfiSunJmiMember's information
     * @param jfiSunJmiMember the object to be saved
     */
    public void saveJfiSunJmiMember(JfiSunJmiMember jfiSunJmiMember);

    /**
     * Removes a jfiSunJmiMember from the database by userCode
     * @param userCode the jfiSunJmiMember's userCode
     */
    public void removeJfiSunJmiMember(final String userCode);
    //added for getJfiSunJmiMembersByCrm
    public List getJfiSunJmiMembersByCrm(CommonRecord crm, Pager pager);
}

