
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiMovieOrder;
import com.joymain.jecs.fi.dao.FiMovieOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiMovieOrderManager extends Manager {
    /**
     * Retrieves all of the fiMovieOrders
     */
    public List getFiMovieOrders(FiMovieOrder fiMovieOrder);

    /**
     * Gets fiMovieOrder's information based on orderId.
     * @param orderId the fiMovieOrder's orderId
     * @return fiMovieOrder populated fiMovieOrder object
     */
    public FiMovieOrder getFiMovieOrder(final String orderId);

    /**
     * Saves a fiMovieOrder's information
     * @param fiMovieOrder the object to be saved
     */
    public void saveFiMovieOrder(FiMovieOrder fiMovieOrder);

    /**
     * Removes a fiMovieOrder from the database by orderId
     * @param orderId the fiMovieOrder's orderId
     */
    public void removeFiMovieOrder(final String orderId);
    //added for getFiMovieOrdersByCrm
    public List getFiMovieOrdersByCrm(CommonRecord crm, Pager pager);
}

