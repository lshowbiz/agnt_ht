
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.am.model.AmRegularMsg;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmRegularMsgManager extends Manager {
    /**
     * Retrieves all of the amRegularMsgs
     */
    public List getAmRegularMsgs(AmRegularMsg amRegularMsg);

    /**
     * Gets amRegularMsg's information based on uniNo.
     * @param uniNo the amRegularMsg's uniNo
     * @return amRegularMsg populated amRegularMsg object
     */
    public AmRegularMsg getAmRegularMsg(final String uniNo);

    /**
     * Saves a amRegularMsg's information
     * @param amRegularMsg the object to be saved
     */
    public void saveAmRegularMsg(AmRegularMsg amRegularMsg);

    /**
     * Removes a amRegularMsg from the database by uniNo
     * @param uniNo the amRegularMsg's uniNo
     */
    public void removeAmRegularMsg(final String uniNo);
    //added for getAmRegularMsgsByCrm
		public List getAmRegularMsgsByCrm(CommonRecord crm, Pager pager);
}

