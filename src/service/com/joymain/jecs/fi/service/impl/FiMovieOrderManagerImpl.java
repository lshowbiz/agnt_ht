
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiMovieOrder;
import com.joymain.jecs.fi.dao.FiMovieOrderDao;
import com.joymain.jecs.fi.service.FiMovieOrderManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiMovieOrderManagerImpl extends BaseManager implements FiMovieOrderManager {
    private FiMovieOrderDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiMovieOrderDao(FiMovieOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiMovieOrderManager#getFiMovieOrders(com.joymain.jecs.fi.model.FiMovieOrder)
     */
    public List getFiMovieOrders(final FiMovieOrder fiMovieOrder) {
        return dao.getFiMovieOrders(fiMovieOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiMovieOrderManager#getFiMovieOrder(String orderId)
     */
    public FiMovieOrder getFiMovieOrder(final String orderId) {
        return dao.getFiMovieOrder(new String(orderId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiMovieOrderManager#saveFiMovieOrder(FiMovieOrder fiMovieOrder)
     */
    public void saveFiMovieOrder(FiMovieOrder fiMovieOrder) {
        dao.saveFiMovieOrder(fiMovieOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiMovieOrderManager#removeFiMovieOrder(String orderId)
     */
    public void removeFiMovieOrder(final String orderId) {
        dao.removeFiMovieOrder(new String(orderId));
    }
    //added for getFiMovieOrdersByCrm
    public List getFiMovieOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getFiMovieOrdersByCrm(crm,pager);
    }
}
