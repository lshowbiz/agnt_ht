
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiCommonAddrDao extends Dao {

    /**
     * Retrieves all of the fiCommonAddrs
     */
    public List getFiCommonAddrs(FiCommonAddr fiCommonAddr);

    /**
     * Gets fiCommonAddr's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the fiCommonAddr's userCode
     * @return fiCommonAddr populated fiCommonAddr object
     */
    public FiCommonAddr getFiCommonAddr(final String userCode);

    /**
     * Saves a fiCommonAddr's information
     * @param fiCommonAddr the object to be saved
     */    
    public void saveFiCommonAddr(FiCommonAddr fiCommonAddr);

    /**
     * Removes a fiCommonAddr from the database by userCode
     * @param userCode the fiCommonAddr's userCode
     */
    public void removeFiCommonAddr(final String userCode);
    //added for getFiCommonAddrsByCrm
    public List getFiCommonAddrsByCrm(CommonRecord crm, Pager pager);
}

