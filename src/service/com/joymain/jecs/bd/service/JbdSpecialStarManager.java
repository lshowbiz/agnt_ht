
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdSpecialStar;
import com.joymain.jecs.bd.dao.JbdSpecialStarDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSpecialStarManager extends Manager {
    /**
     * Retrieves all of the jbdSpecialStars
     */
    public List getJbdSpecialStars(JbdSpecialStar jbdSpecialStar);

    /**
     * Gets jbdSpecialStar's information based on userCode.
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

