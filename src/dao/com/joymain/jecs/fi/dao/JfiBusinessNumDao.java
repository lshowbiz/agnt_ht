
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiBusinessNum;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiBusinessNumDao extends Dao {

    /**
     * Retrieves all of the jfiBusinessNums
     */
    public List getJfiBusinessNums(JfiBusinessNum jfiBusinessNum);

    /**
     * Gets jfiBusinessNum's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param businessId the jfiBusinessNum's businessId
     * @return jfiBusinessNum populated jfiBusinessNum object
     */
    public JfiBusinessNum getJfiBusinessNum(final Long businessId);

    /**
     * Saves a jfiBusinessNum's information
     * @param jfiBusinessNum the object to be saved
     */    
    public void saveJfiBusinessNum(JfiBusinessNum jfiBusinessNum);

    /**
     * Removes a jfiBusinessNum from the database by businessId
     * @param businessId the jfiBusinessNum's businessId
     */
    public void removeJfiBusinessNum(final Long businessId);
    //added for getJfiBusinessNumsByCrm
    public List getJfiBusinessNumsByCrm(CommonRecord crm, Pager pager);
}

