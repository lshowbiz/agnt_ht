
package com.joymain.jecs.pm.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmProductSaleLogManager extends Manager {
    /**
     * Retrieves all of the jpmProductSaleLogs
     */
    public List getJpmProductSaleLogs(JpmProductSaleLog jpmProductSaleLog);

    /**
     * Gets jpmProductSaleLog's information based on uniNo.
     * @param uniNo the jpmProductSaleLog's uniNo
     * @return jpmProductSaleLog populated jpmProductSaleLog object
     */
    public JpmProductSaleLog getJpmProductSaleLog(final String uniNo);

    /**
     * Saves a jpmProductSaleLog's information
     * @param jpmProductSaleLog the object to be saved
     */
    public void saveJpmProductSaleLog(JpmProductSaleLog jpmProductSaleLog);

    /**
     * Removes a jpmProductSaleLog from the database by uniNo
     * @param uniNo the jpmProductSaleLog's uniNo
     */
    public void removeJpmProductSaleLog(final String uniNo);
    
    //added for getJpmProductSaleLogsByCrm
    public List getJpmProductSaleLogsByCrm(CommonRecord crm, Pager pager);
    //WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public JpmProductSaleLog getJpmProductSaleToLog(JpmProductSale jpmProductSale) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;*/
    public JpmProductSaleLog getJpmProductSaleToLog(JpmProductSaleNew jpmProductSaleNew) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
}

