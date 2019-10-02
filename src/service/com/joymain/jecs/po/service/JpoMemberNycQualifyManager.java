
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberNycQualify;
import com.joymain.jecs.po.dao.JpoMemberNycQualifyDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoMemberNycQualifyManager extends Manager {
    /**
     * Retrieves all of the jpoMemberNycQualifys
     */
    public List getJpoMemberNycQualifys(JpoMemberNycQualify jpoMemberNycQualify);

    /**
     * Gets jpoMemberNycQualify's information based on id.
     * @param id the jpoMemberNycQualify's id
     * @return jpoMemberNycQualify populated jpoMemberNycQualify object
     */
    public JpoMemberNycQualify getJpoMemberNycQualify(final String id);

    /**
     * Saves a jpoMemberNycQualify's information
     * @param jpoMemberNycQualify the object to be saved
     */
    public void saveJpoMemberNycQualify(JpoMemberNycQualify jpoMemberNycQualify);

    /**
     * Removes a jpoMemberNycQualify from the database by id
     * @param id the jpoMemberNycQualify's id
     */
    public void removeJpoMemberNycQualify(final String id);
    //added for getJpoMemberNycQualifysByCrm
    public List getJpoMemberNycQualifysByCrm(CommonRecord crm, Pager pager);
}

