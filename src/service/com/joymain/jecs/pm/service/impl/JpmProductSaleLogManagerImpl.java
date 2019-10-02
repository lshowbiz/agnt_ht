
package com.joymain.jecs.pm.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.pm.dao.JpmProductSaleLogDao;
import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleLogManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmProductSaleLogManagerImpl extends BaseManager implements JpmProductSaleLogManager {
    private JpmProductSaleLogDao dao;

    
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductSaleLogDao(JpmProductSaleLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleLogManager#getJpmProductSaleLogs(com.joymain.jecs.pm.model.JpmProductSaleLog)
     */
    public List getJpmProductSaleLogs(final JpmProductSaleLog jpmProductSaleLog) {
        return dao.getJpmProductSaleLogs(jpmProductSaleLog);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleLogManager#getJpmProductSaleLog(String uniNo)
     */
    public JpmProductSaleLog getJpmProductSaleLog(final String uniNo) {
        return dao.getJpmProductSaleLog(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleLogManager#saveJpmProductSaleLog(JpmProductSaleLog jpmProductSaleLog)
     */
    public void saveJpmProductSaleLog(JpmProductSaleLog jpmProductSaleLog) {
        dao.saveJpmProductSaleLog(jpmProductSaleLog);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleLogManager#removeJpmProductSaleLog(String uniNo)
     */
    public void removeJpmProductSaleLog(final String uniNo) {
        dao.removeJpmProductSaleLog(new Long(uniNo));
    }
    //added for getJpmProductSaleLogsByCrm
    public List getJpmProductSaleLogsByCrm(CommonRecord crm, Pager pager){
    	return dao.getJpmProductSaleLogsByCrm(crm,pager);
    }
    
    /*public JpmProductSaleLog getJpmProductSaleToLog(JpmProductSale jpmProductSale) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
    	JpmProductSaleLog jpmProductSaleLog = new JpmProductSaleLog();
    	Field[] fields =jpmProductSale.getClass().getDeclaredFields();
    	String fieldName;
    	Method methodGet;
    	Method methodSet;
    	for(int i=0;i<fields.length;i++){
    		Field field = fields[i];
    		fieldName = StringUtils.capitalize(field.getName());
    		if((!"uniNo".equalsIgnoreCase(fieldName)) &&(!"remark".equalsIgnoreCase(fieldName)) ){
    			methodGet = jpmProductSale.getClass().getMethod("get"+fieldName, null);
    			methodSet = jpmProductSaleLog.getClass().getMethod("set"+fieldName, field.getType());
    			Object o =methodGet.invoke(jpmProductSale, null);
    			methodSet.invoke(jpmProductSaleLog, new Object[]{o});
    		}
    	}
    	log.info("jpmProductSaleLog>>>"+jpmProductSaleLog);
    	return jpmProductSaleLog;
    }*/
    //WuCF JpmProductSaleNew Modify By WuCF 20130917
    public JpmProductSaleLog getJpmProductSaleToLog(JpmProductSaleNew jpmProductSaleNew) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
    	JpmProductSaleLog jpmProductSaleLog = new JpmProductSaleLog();
    	Field[] fields =jpmProductSaleNew.getClass().getDeclaredFields();
    	String fieldName;
    	Method methodGet;
    	Method methodSet;
    	for(int i=0;i<fields.length;i++){
    		Field field = fields[i];
    		fieldName = StringUtils.capitalize(field.getName());
    		if((!"uniNo".equalsIgnoreCase(fieldName)) &&(!"remark".equalsIgnoreCase(fieldName)) ){
    			methodGet = jpmProductSaleNew.getClass().getMethod("get"+fieldName, null);
    			methodSet = jpmProductSaleLog.getClass().getMethod("set"+fieldName, field.getType());
    			Object o =methodGet.invoke(jpmProductSaleNew, null);
    			methodSet.invoke(jpmProductSaleLog, new Object[]{o});
    		}
    	}
    	log.info("jpmProductSaleLog>>>"+jpmProductSaleLog);
    	return jpmProductSaleLog;
    }
	 
    
}
