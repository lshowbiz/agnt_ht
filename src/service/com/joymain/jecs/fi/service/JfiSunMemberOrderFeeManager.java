
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunMemberOrderFee;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunMemberOrderFeeManager extends Manager {
    /**
     * Retrieves all of the jfiSunMemberOrderFees
     */
    public List getJfiSunMemberOrderFees(JfiSunMemberOrderFee jfiSunMemberOrderFee);

    /**
     * Gets jfiSunMemberOrderFee's information based on mofId.
     * @param mofId the jfiSunMemberOrderFee's mofId
     * @return jfiSunMemberOrderFee populated jfiSunMemberOrderFee object
     */
    public JfiSunMemberOrderFee getJfiSunMemberOrderFee(final String mofId);

    /**
     * Saves a jfiSunMemberOrderFee's information
     * @param jfiSunMemberOrderFee the object to be saved
     */
    public void saveJfiSunMemberOrderFee(JfiSunMemberOrderFee jfiSunMemberOrderFee);

    /**
     * Removes a jfiSunMemberOrderFee from the database by mofId
     * @param mofId the jfiSunMemberOrderFee's mofId
     */
    public void removeJfiSunMemberOrderFee(final String mofId);
    //added for getJfiSunMemberOrderFeesByCrm
    public List getJfiSunMemberOrderFeesByCrm(CommonRecord crm, Pager pager);
}

