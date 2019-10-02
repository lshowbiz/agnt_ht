
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiAmountDetailDao extends Dao {

    /**
     * Retrieves all of the jfiAmountDetails
     */
    public List getJfiAmountDetails(JfiAmountDetail jfiAmountDetail);

    /**
     * Gets jfiAmountDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jfiAmountDetail's id
     * @return jfiAmountDetail populated jfiAmountDetail object
     */
    public JfiAmountDetail getJfiAmountDetail(final Long id);

    /**
     * Saves a jfiAmountDetail's information
     * @param jfiAmountDetail the object to be saved
     */    
    public void saveJfiAmountDetail(JfiAmountDetail jfiAmountDetail);

    /**
     * Removes a jfiAmountDetail from the database by id
     * @param id the jfiAmountDetail's id
     */
    public void removeJfiAmountDetail(final Long id);
    //added for getJfiAmountDetailsByCrm
    public List getJfiAmountDetailsByCrm(CommonRecord crm, Pager pager);
}

