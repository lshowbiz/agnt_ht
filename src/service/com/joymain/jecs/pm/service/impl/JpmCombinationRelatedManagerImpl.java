
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.pm.dao.JpmCombinationRelatedDao;
import com.joymain.jecs.pm.service.JpmCombinationRelatedManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmCombinationRelatedManagerImpl extends BaseManager implements JpmCombinationRelatedManager {
    private JpmCombinationRelatedDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmCombinationRelatedDao(JpmCombinationRelatedDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCombinationRelatedManager#getJpmCombinationRelateds(com.joymain.jecs.pm.model.JpmCombinationRelated)
     */
    public List getJpmCombinationRelateds(final JpmCombinationRelated jpmCombinationRelated) {
        return dao.getJpmCombinationRelateds(jpmCombinationRelated);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCombinationRelatedManager#getJpmCombinationRelated(String uniNo)
     */
    public JpmCombinationRelated getJpmCombinationRelated(final String uniNo) {
        return dao.getJpmCombinationRelated(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCombinationRelatedManager#saveJpmCombinationRelated(JpmCombinationRelated jpmCombinationRelated)
     */
    public void saveJpmCombinationRelated(JpmCombinationRelated jpmCombinationRelated) {
        dao.saveJpmCombinationRelated(jpmCombinationRelated);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCombinationRelatedManager#removeJpmCombinationRelated(String uniNo)
     */
    public void removeJpmCombinationRelated(final String uniNo) {
        dao.removeJpmCombinationRelated(new Long(uniNo));
    }
    //added for getJpmCombinationRelatedsByCrm
    public List getJpmCombinationRelatedsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmCombinationRelatedsByCrm(crm,pager);
    }
    /**
     * @Description 根据套餐代码，期别，退货商品代码  查询套餐子商品明细
     * @author houxyu
     * @date 2016-3-30
     * @param packageCode
     * @param productDate
     * @param subProductNo
     * @return
     */
    public List getJpmCombinationRelateds(String packageCode,String productDate,String subProductNo){
    	return dao.getJpmCombinationRelateds(packageCode,productDate,subProductNo);
    }
}
