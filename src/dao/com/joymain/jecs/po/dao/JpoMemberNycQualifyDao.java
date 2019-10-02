
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberNycQualify;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberNycQualifyDao extends Dao {

    /**
     * Retrieves all of the jpoMemberNycQualifys
     */
    public List getJpoMemberNycQualifys(JpoMemberNycQualify jpoMemberNycQualify);

    /**
     * Gets jpoMemberNycQualify's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpoMemberNycQualify's id
     * @return jpoMemberNycQualify populated jpoMemberNycQualify object
     */
    public JpoMemberNycQualify getJpoMemberNycQualify(final Long id);

    /**
     * Saves a jpoMemberNycQualify's information
     * @param jpoMemberNycQualify the object to be saved
     */    
    public void saveJpoMemberNycQualify(JpoMemberNycQualify jpoMemberNycQualify);

    /**
     * Removes a jpoMemberNycQualify from the database by id
     * @param id the jpoMemberNycQualify's id
     */
    public void removeJpoMemberNycQualify(final Long id);
    //added for getJpoMemberNycQualifysByCrm
    public List getJpoMemberNycQualifysByCrm(CommonRecord crm, Pager pager);
}

