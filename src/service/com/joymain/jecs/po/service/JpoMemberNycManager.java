
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.dao.JpoMemberNycDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoMemberNycManager extends Manager {
    /**
     * Retrieves all of the jpoMemberNycs
     */
    public List getJpoMemberNycs(JpoMemberNyc jpoMemberNyc);

    /**
     * Gets jpoMemberNyc's information based on id.
     * @param id the jpoMemberNyc's id
     * @return jpoMemberNyc populated jpoMemberNyc object
     */
    public JpoMemberNyc getJpoMemberNyc(final String id);

    /**
     * Saves a jpoMemberNyc's information
     * @param jpoMemberNyc the object to be saved
     */
    public void saveJpoMemberNyc(JpoMemberNyc jpoMemberNyc,String userCode);

    /**
     * Removes a jpoMemberNyc from the database by id
     * @param id the jpoMemberNyc's id
     */
    public void removeJpoMemberNyc(final String id,String userCode);
    //added for getJpoMemberNycsByCrm
    public List getJpoMemberNycsByCrm(CommonRecord crm, Pager pager);

    public List getJpoMemberNycQualifiedsByCrm(CommonRecord crm, Pager pager);

    public void work();
}

