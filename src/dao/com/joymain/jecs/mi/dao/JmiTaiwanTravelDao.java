
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiTaiwanTravelDao extends Dao {

    /**
     * Retrieves all of the jmiTaiwanTravels
     */
    public List getJmiTaiwanTravels(JmiTaiwanTravel jmiTaiwanTravel);

    /**
     * Gets jmiTaiwanTravel's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the jmiTaiwanTravel's userCode
     * @return jmiTaiwanTravel populated jmiTaiwanTravel object
     */
    public JmiTaiwanTravel getJmiTaiwanTravel(final String userCode);

    /**
     * Saves a jmiTaiwanTravel's information
     * @param jmiTaiwanTravel the object to be saved
     */    
    public void saveJmiTaiwanTravel(JmiTaiwanTravel jmiTaiwanTravel);

    /**
     * Removes a jmiTaiwanTravel from the database by userCode
     * @param userCode the jmiTaiwanTravel's userCode
     */
    public void removeJmiTaiwanTravel(final String userCode);
    //added for getJmiTaiwanTravelsByCrm
    public List getJmiTaiwanTravelsByCrm(CommonRecord crm, Pager pager);
    
    public boolean getCheckJmiTaiwanTravelExist(JmiTaiwanTravel jmiTaiwanTravel);
}

