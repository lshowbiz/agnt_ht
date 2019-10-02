
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiPrizeTourism;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiPrizeTourismDao extends Dao {

    /**
     * Retrieves all of the jmiPrizeTourisms
     */
    public List getJmiPrizeTourisms(JmiPrizeTourism jmiPrizeTourism);

    /**
     * Gets jmiPrizeTourism's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the jmiPrizeTourism's userCode
     * @return jmiPrizeTourism populated jmiPrizeTourism object
     */
    public JmiPrizeTourism getJmiPrizeTourism(final Long prizeTouismId);

    /**
     * Saves a jmiPrizeTourism's information
     * @param jmiPrizeTourism the object to be saved
     */    
    public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism);

    /**
     * Removes a jmiPrizeTourism from the database by userCode
     * @param userCode the jmiPrizeTourism's userCode
     */
    public void removeJmiPrizeTourism(final Long prizeTouismId);
    //added for getJmiPrizeTourismsByCrm
    public List getJmiPrizeTourismsByCrm(CommonRecord crm, Pager pager);
    
    public String getPassStarByUserCode(String userCode);
}

