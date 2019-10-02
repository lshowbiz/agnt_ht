
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdUserCompanyCode;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdUserCompanyCodeDao extends Dao {

    /**
     * Retrieves all of the jbdUserCompanyCodes
     */
    public List getJbdUserCompanyCodes(JbdUserCompanyCode jbdUserCompanyCode);

    /**
     * Gets jbdUserCompanyCode's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdUserCompanyCode's id
     * @return jbdUserCompanyCode populated jbdUserCompanyCode object
     */
    public JbdUserCompanyCode getJbdUserCompanyCode(final Long id);

    /**
     * Saves a jbdUserCompanyCode's information
     * @param jbdUserCompanyCode the object to be saved
     */    
    public void saveJbdUserCompanyCode(JbdUserCompanyCode jbdUserCompanyCode);

    /**
     * Removes a jbdUserCompanyCode from the database by id
     * @param id the jbdUserCompanyCode's id
     */
    public void removeJbdUserCompanyCode(final Long id);
    //added for getJbdUserCompanyCodesByCrm
    public List getJbdUserCompanyCodesByCrm(CommonRecord crm, Pager pager);
    public JbdUserCompanyCode getPreviousJbdUserCompanyCode(String userCode,Integer wweek);
    public JbdUserCompanyCode getJbdUserCompanyCodeByUserCodeAndWeek(String userCode, Integer wweek);
    public List<JbdUserCompanyCode> getNextJbdUserCompanyCode(String userCode,Integer wweek);
}

