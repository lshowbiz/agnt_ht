
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoShippingFeeDao extends Dao {

    /**
     * Retrieves all of the jpoShippingFees
     */
    public List getJpoShippingFees(JpoShippingFee jpoShippingFee);

    /**
     * Gets jpoShippingFee's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jpsId the jpoShippingFee's jpsId
     * @return jpoShippingFee populated jpoShippingFee object
     */
    public JpoShippingFee getJpoShippingFee(final Long jpsId);

    /**
     * Saves a jpoShippingFee's information
     * @param jpoShippingFee the object to be saved
     */    
    public void saveJpoShippingFee(JpoShippingFee jpoShippingFee);

    /**
     * Removes a jpoShippingFee from the database by jpsId
     * @param jpsId the jpoShippingFee's jpsId
     */
    public void removeJpoShippingFee(final Long jpsId);
    //added for getJpoShippingFeesByCrm
    public List getJpoShippingFeesByCrm(CommonRecord crm, Pager pager);
}

