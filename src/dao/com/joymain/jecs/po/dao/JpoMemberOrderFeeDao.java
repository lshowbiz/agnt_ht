
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderFeeDao extends Dao {

    /**
     * Retrieves all of the jpoMemberOrderFees
     */
    public List getJpoMemberOrderFees(JpoMemberOrderFee jpoMemberOrderFee);

    /**
     * Gets jpoMemberOrderFee's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param mofId the jpoMemberOrderFee's mofId
     * @return jpoMemberOrderFee populated jpoMemberOrderFee object
     */
    public JpoMemberOrderFee getJpoMemberOrderFee(final Long mofId);

    /**
     * Saves a jpoMemberOrderFee's information
     * @param jpoMemberOrderFee the object to be saved
     */    
    public void saveJpoMemberOrderFee(JpoMemberOrderFee jpoMemberOrderFee);

    /**
     * Removes a jpoMemberOrderFee from the database by mofId
     * @param mofId the jpoMemberOrderFee's mofId
     */
    public void removeJpoMemberOrderFee(final Long mofId);
    //added for getJpoMemberOrderFeesByCrm
    public List getJpoMemberOrderFeesByCrm(CommonRecord crm, Pager pager);
}

