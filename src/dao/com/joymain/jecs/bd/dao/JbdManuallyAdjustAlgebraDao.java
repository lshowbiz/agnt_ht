
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdManuallyAdjustAlgebraDao extends Dao {

    /**
     * Retrieves all of the jbdManuallyAdjustAlgebras
     */
    public List getJbdManuallyAdjustAlgebras(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra);

    /**
     * Gets jbdManuallyAdjustAlgebra's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdManuallyAdjustAlgebra's id
     * @return jbdManuallyAdjustAlgebra populated jbdManuallyAdjustAlgebra object
     */
    public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebra(final Long id);

    /**
     * Saves a jbdManuallyAdjustAlgebra's information
     * @param jbdManuallyAdjustAlgebra the object to be saved
     */    
    public void saveJbdManuallyAdjustAlgebra(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra);

    /**
     * Removes a jbdManuallyAdjustAlgebra from the database by id
     * @param id the jbdManuallyAdjustAlgebra's id
     */
    public void removeJbdManuallyAdjustAlgebra(final Long id);
    //added for getJbdManuallyAdjustAlgebrasByCrm
    public List getJbdManuallyAdjustAlgebrasByCrm(CommonRecord crm, Pager pager);
	public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebraByUserCodeAndWeek(String userCode,String wweek);
}

