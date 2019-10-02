
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmMessagePermitDao extends Dao {

    /**
     * Retrieves all of the amMessagePermits
     */
    public List getAmMessagePermits(AmMessagePermit amMessagePermit);

    /**
     * Gets amMessagePermit's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param ampNo the amMessagePermit's ampNo
     * @return amMessagePermit populated amMessagePermit object
     */
    public AmMessagePermit getAmMessagePermit(final Long ampNo);

    /**
     * Saves a amMessagePermit's information
     * @param amMessagePermit the object to be saved
     */    
    public void saveAmMessagePermit(AmMessagePermit amMessagePermit);

    /**
     * Removes a amMessagePermit from the database by ampNo
     * @param ampNo the amMessagePermit's ampNo
     */
    public void removeAmMessagePermit(final Long ampNo);
    //added for getAmMessagePermitsByCrm
    public List getAmMessagePermitsByCrm(CommonRecord crm, Pager pager);
    
    public List getAmMessagePermitsByUserCode(String userCode);
}

