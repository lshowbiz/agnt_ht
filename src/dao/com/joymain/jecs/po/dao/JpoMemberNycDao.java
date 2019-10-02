
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberNycDao extends Dao {

    /**
     * Retrieves all of the jpoMemberNycs
     */
    public List getJpoMemberNycs(JpoMemberNyc jpoMemberNyc);

    /**
     * Gets jpoMemberNyc's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpoMemberNyc's id
     * @return jpoMemberNyc populated jpoMemberNyc object
     */
    public JpoMemberNyc getJpoMemberNyc(final Long id);

    /**
     * Saves a jpoMemberNyc's information
     * @param jpoMemberNyc the object to be saved
     */    
    public void saveJpoMemberNyc(JpoMemberNyc jpoMemberNyc);

    /**
     * Removes a jpoMemberNyc from the database by id
     * @param id the jpoMemberNyc's id
     */
    public void removeJpoMemberNyc(final Long id);
    //added for getJpoMemberNycsByCrm
    public List getJpoMemberNycsByCrm(CommonRecord crm, Pager pager);
    List getJpoMemberNycQualifiedsByCrm(CommonRecord crm, Pager pager);
    public void bacthUpdateNycStatus();
}

