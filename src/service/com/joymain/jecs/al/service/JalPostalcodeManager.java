
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.JalPostalcode;
import com.joymain.jecs.al.dao.JalPostalcodeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JalPostalcodeManager extends Manager {
    /**
     * Retrieves all of the jalPostalcodes
     */
    public List getJalPostalcodes(JalPostalcode jalPostalcode);

    /**
     * Gets jalPostalcode's information based on postalcodeId.
     * @param postalcodeId the jalPostalcode's postalcodeId
     * @return jalPostalcode populated jalPostalcode object
     */
    public JalPostalcode getJalPostalcode(final String postalcodeId);

    /**
     * Saves a jalPostalcode's information
     * @param jalPostalcode the object to be saved
     */
    public void saveJalPostalcode(JalPostalcode jalPostalcode);

    /**
     * Removes a jalPostalcode from the database by postalcodeId
     * @param postalcodeId the jalPostalcode's postalcodeId
     */
    public void removeJalPostalcode(final String postalcodeId);
    //added for getJalPostalcodesByCrm
    public List getJalPostalcodesByCrm(CommonRecord crm, Pager pager);
	public JalPostalcode getJalPostalcodeByCode(String zipCode);
}

