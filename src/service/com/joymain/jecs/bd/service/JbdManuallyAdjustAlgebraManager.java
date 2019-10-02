
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra;
import com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdManuallyAdjustAlgebraManager extends Manager {
    /**
     * Retrieves all of the jbdManuallyAdjustAlgebras
     */
    public List getJbdManuallyAdjustAlgebras(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra);

    /**
     * Gets jbdManuallyAdjustAlgebra's information based on id.
     * @param id the jbdManuallyAdjustAlgebra's id
     * @return jbdManuallyAdjustAlgebra populated jbdManuallyAdjustAlgebra object
     */
    public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebra(final String id);

    /**
     * Saves a jbdManuallyAdjustAlgebra's information
     * @param jbdManuallyAdjustAlgebra the object to be saved
     */
    public void saveJbdManuallyAdjustAlgebra(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra);

    /**
     * Removes a jbdManuallyAdjustAlgebra from the database by id
     * @param id the jbdManuallyAdjustAlgebra's id
     */
    public void removeJbdManuallyAdjustAlgebra(final String id);
    //added for getJbdManuallyAdjustAlgebrasByCrm
    public List getJbdManuallyAdjustAlgebrasByCrm(CommonRecord crm, Pager pager);
	public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebraByUserCodeAndWeek(String userCode,String wweek);
}

