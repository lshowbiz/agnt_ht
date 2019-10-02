
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.JalPostalcode;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JalPostalcodeDao extends Dao {

    /**
     * Retrieves all of the jalPostalcodes
     */
    public List getJalPostalcodes(JalPostalcode jalPostalcode);

    /**
     * Gets jalPostalcode's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param postalcodeId the jalPostalcode's postalcodeId
     * @return jalPostalcode populated jalPostalcode object
     */
    public JalPostalcode getJalPostalcode(final Long postalcodeId);

    /**
     * Saves a jalPostalcode's information
     * @param jalPostalcode the object to be saved
     */    
    public void saveJalPostalcode(JalPostalcode jalPostalcode);

    /**
     * Removes a jalPostalcode from the database by postalcodeId
     * @param postalcodeId the jalPostalcode's postalcodeId
     */
    public void removeJalPostalcode(final Long postalcodeId);
    //added for getJalPostalcodesByCrm
    public List getJalPostalcodesByCrm(CommonRecord crm, Pager pager);
	public JalPostalcode getJalPostalcodeByCode(String zipCode);
}

