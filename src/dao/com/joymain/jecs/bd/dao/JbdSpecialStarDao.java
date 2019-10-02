
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSpecialStar;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSpecialStarDao extends Dao {

    /**
     * Retrieves all of the jbdSpecialStars
     */
    public List getJbdSpecialStars(JbdSpecialStar jbdSpecialStar);

    /**
     * Gets jbdSpecialStar's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the jbdSpecialStar's userCode
     * @return jbdSpecialStar populated jbdSpecialStar object
     */
    public JbdSpecialStar getJbdSpecialStar(final String userCode);

    /**
     * Saves a jbdSpecialStar's information
     * @param jbdSpecialStar the object to be saved
     */    
    public void saveJbdSpecialStar(JbdSpecialStar jbdSpecialStar);

    /**
     * Removes a jbdSpecialStar from the database by userCode
     * @param userCode the jbdSpecialStar's userCode
     */
    public void removeJbdSpecialStar(final String userCode);
    //added for getJbdSpecialStarsByCrm
    public List getJbdSpecialStarsByCrm(CommonRecord crm, Pager pager);
}

