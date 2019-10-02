
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductSaleLogDao extends Dao {

    /**
     * Retrieves all of the jpmProductSaleLogs
     */
    public List getJpmProductSaleLogs(JpmProductSaleLog jpmProductSaleLog);

    /**
     * Gets jpmProductSaleLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the jpmProductSaleLog's uniNo
     * @return jpmProductSaleLog populated jpmProductSaleLog object
     */
    public JpmProductSaleLog getJpmProductSaleLog(final Long uniNo);

    /**
     * Saves a jpmProductSaleLog's information
     * @param jpmProductSaleLog the object to be saved
     */    
    public void saveJpmProductSaleLog(JpmProductSaleLog jpmProductSaleLog);

    /**
     * Removes a jpmProductSaleLog from the database by uniNo
     * @param uniNo the jpmProductSaleLog's uniNo
     */
    public void removeJpmProductSaleLog(final Long uniNo);
    //added for getJpmProductSaleLogsByCrm
    public List getJpmProductSaleLogsByCrm(CommonRecord crm, Pager pager);
}

