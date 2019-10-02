
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.dao.FiCommonAddrDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiCommonAddrManager extends Manager {
    /**
     * Retrieves all of the fiCommonAddrs
     */
    public List getFiCommonAddrs(FiCommonAddr fiCommonAddr);

    /**
     * Gets fiCommonAddr's information based on userCode.
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

