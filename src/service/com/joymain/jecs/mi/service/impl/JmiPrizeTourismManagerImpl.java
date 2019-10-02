
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiPrizeTourism;
import com.joymain.jecs.mi.dao.JmiPrizeTourismDao;
import com.joymain.jecs.mi.service.JmiPrizeTourismManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiPrizeTourismManagerImpl extends BaseManager implements JmiPrizeTourismManager {
    private JmiPrizeTourismDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiPrizeTourismDao(JmiPrizeTourismDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiPrizeTourismManager#getJmiPrizeTourisms(com.joymain.jecs.mi.model.JmiPrizeTourism)
     */
    public List getJmiPrizeTourisms(final JmiPrizeTourism jmiPrizeTourism) {
        return dao.getJmiPrizeTourisms(jmiPrizeTourism);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiPrizeTourismManager#getJmiPrizeTourism(String userCode)
     */
    public JmiPrizeTourism getJmiPrizeTourism(final Long prizeTouismId) {
        return dao.getJmiPrizeTourism(prizeTouismId);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiPrizeTourismManager#saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism)
     */
    public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism) {
        dao.saveJmiPrizeTourism(jmiPrizeTourism);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiPrizeTourismManager#removeJmiPrizeTourism(String userCode)
     */
    public void removeJmiPrizeTourism(final Long prizeTouismId) {
        dao.removeJmiPrizeTourism(prizeTouismId);
    }
    //added for getJmiPrizeTourismsByCrm
    public List getJmiPrizeTourismsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiPrizeTourismsByCrm(crm,pager);
    }
    
    public String getPassStarByUserCode(String userCode){
    	return dao.getPassStarByUserCode(userCode);
    }
}
