
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.AmNew;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmNewDao extends Dao {

    /**
     * Retrieves all of the amNews
     */
    public List getAmNews(AmNew amNew);

    /**
     * Gets amNew's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param newId the amNew's newId
     * @return amNew populated amNew object
     */
    public AmNew getAmNew(final String newId);

    /**
     * Saves a amNew's information
     * @param amNew the object to be saved
     */    
    public void saveAmNew(AmNew amNew);

    /**
     * Removes a amNew from the database by newId
     * @param newId the amNew's newId
     */
    public void removeAmNew(final String newId);
    //added for getAmNewsByCrm
    public List getAmNewsByCrm(CommonRecord crm, Pager pager);
}

