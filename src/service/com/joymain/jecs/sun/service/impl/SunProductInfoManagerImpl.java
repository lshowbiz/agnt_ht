
package com.joymain.jecs.sun.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sun.model.SunProductInfo;
import com.joymain.jecs.sun.dao.SunProductInfoDao;
import com.joymain.jecs.sun.service.SunProductInfoManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class SunProductInfoManagerImpl extends BaseManager implements SunProductInfoManager {
    private SunProductInfoDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSunProductInfoDao(SunProductInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sun.service.SunProductInfoManager#getSunProductInfos(com.joymain.jecs.sun.model.SunProductInfo)
     */
    public List getSunProductInfos(final SunProductInfo sunProductInfo) {
        return dao.getSunProductInfos(sunProductInfo);
    }

    /**
     * @see com.joymain.jecs.sun.service.SunProductInfoManager#getSunProductInfo(String jpiId)
     */
    public SunProductInfo getSunProductInfo(final String jpiId) {
        return dao.getSunProductInfo(new Long(jpiId));
    }

    /**
     * @see com.joymain.jecs.sun.service.SunProductInfoManager#saveSunProductInfo(SunProductInfo sunProductInfo)
     */
    public void saveSunProductInfo(SunProductInfo sunProductInfo) {
        dao.saveSunProductInfo(sunProductInfo);
        
    }

    /**
     * @see com.joymain.jecs.sun.service.SunProductInfoManager#removeSunProductInfo(String jpiId)
     */
    public void removeSunProductInfo(final String jpiId) {
        dao.removeSunProductInfo(new Long(jpiId));
    }
    //added for getSunProductInfosByCrm
    public List getSunProductInfosByCrm(CommonRecord crm, Pager pager){
	return dao.getSunProductInfosByCrm(crm,pager);
    }
    public void updateSunOrder(SunProductInfo sunProductInfo){
    	dao.updateSunOrder(sunProductInfo);
    }
}
