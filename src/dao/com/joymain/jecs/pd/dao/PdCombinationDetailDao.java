
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdCombinationDetailDao extends Dao {

    /**
     * Retrieves all of the pdCombinationDetails
     */
    public List getPdCombinationDetails(PdCombinationDetail pdCombinationDetail);

    /**
     * Gets pdCombinationDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdCombinationDetail's uniNo
     * @return pdCombinationDetail populated pdCombinationDetail object
     */
    public PdCombinationDetail getPdCombinationDetail(final Long uniNo);

    /**
     * Saves a pdCombinationDetail's information
     * @param pdCombinationDetail the object to be saved
     */    
    public void savePdCombinationDetail(PdCombinationDetail pdCombinationDetail);

    /**
     * Removes a pdCombinationDetail from the database by uniNo
     * @param uniNo the pdCombinationDetail's uniNo
     */
    public void removePdCombinationDetail(final Long uniNo);
    //added for getPdCombinationDetailsByCrm
    public List getPdCombinationDetailsByCrm(CommonRecord crm, Pager pager);

	public List getPdCombinationDetailTotals(CommonRecord crm);
    
   
}

