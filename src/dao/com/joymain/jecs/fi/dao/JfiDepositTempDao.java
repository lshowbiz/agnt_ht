
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiDepositTemp;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiDepositTempDao extends Dao {

    /**
     * Retrieves all of the jfiDepositTemps
     */
    public List getJfiDepositTemps(JfiDepositTemp jfiDepositTemp);

    /**
     * Gets jfiDepositTemp's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param tempId the jfiDepositTemp's tempId
     * @return jfiDepositTemp populated jfiDepositTemp object
     */
    public JfiDepositTemp getJfiDepositTemp(final Long tempId);

    /**
     * Saves a jfiDepositTemp's information
     * @param jfiDepositTemp the object to be saved
     */    
    public void saveJfiDepositTemp(JfiDepositTemp jfiDepositTemp);

    /**
     * Removes a jfiDepositTemp from the database by tempId
     * @param tempId the jfiDepositTemp's tempId
     */
    public void removeJfiDepositTemp(final Long tempId);
    //added for getJfiDepositTempsByCrm
    public List getJfiDepositTempsByCrm(CommonRecord crm, Pager pager);
}

