
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiSunMemberOrderFee;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiSunMemberOrderFeeDao extends Dao {

    /**
     * Retrieves all of the jfiSunMemberOrderFees
     */
    public List getJfiSunMemberOrderFees(JfiSunMemberOrderFee jfiSunMemberOrderFee);

    /**
     * Gets jfiSunMemberOrderFee's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param mofId the jfiSunMemberOrderFee's mofId
     * @return jfiSunMemberOrderFee populated jfiSunMemberOrderFee object
     */
    public JfiSunMemberOrderFee getJfiSunMemberOrderFee(final Long mofId);

    /**
     * Saves a jfiSunMemberOrderFee's information
     * @param jfiSunMemberOrderFee the object to be saved
     */    
    public void saveJfiSunMemberOrderFee(JfiSunMemberOrderFee jfiSunMemberOrderFee);

    /**
     * Removes a jfiSunMemberOrderFee from the database by mofId
     * @param mofId the jfiSunMemberOrderFee's mofId
     */
    public void removeJfiSunMemberOrderFee(final Long mofId);
    //added for getJfiSunMemberOrderFeesByCrm
    public List getJfiSunMemberOrderFeesByCrm(CommonRecord crm, Pager pager);
}

