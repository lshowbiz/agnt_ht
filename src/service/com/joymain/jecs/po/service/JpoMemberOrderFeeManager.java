
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.dao.JpoMemberOrderFeeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoMemberOrderFeeManager extends Manager {
    /**
     * Retrieves all of the jpoMemberOrderFees
     */
    public List getJpoMemberOrderFees(JpoMemberOrderFee jpoMemberOrderFee);

    /**
     * Gets jpoMemberOrderFee's information based on mofId.
     * @param mofId the jpoMemberOrderFee's mofId
     * @return jpoMemberOrderFee populated jpoMemberOrderFee object
     */
    public JpoMemberOrderFee getJpoMemberOrderFee(final String mofId);

    /**
     * Saves a jpoMemberOrderFee's information
     * @param jpoMemberOrderFee the object to be saved
     */
    public void saveJpoMemberOrderFee(JpoMemberOrderFee jpoMemberOrderFee);

    /**
     * Removes a jpoMemberOrderFee from the database by mofId
     * @param mofId the jpoMemberOrderFee's mofId
     */
    public void removeJpoMemberOrderFee(final String mofId);
    //added for getJpoMemberOrderFeesByCrm
    public List getJpoMemberOrderFeesByCrm(CommonRecord crm, Pager pager);
}

