
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao;
import com.joymain.jecs.pm.service.JpmProductSaleRelatedManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmProductSaleRelatedManagerImpl extends BaseManager implements JpmProductSaleRelatedManager {
    private JpmProductSaleRelatedDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductSaleRelatedDao(JpmProductSaleRelatedDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleRelatedManager#getJpmProductSaleRelateds(com.joymain.jecs.pm.model.JpmProductSaleRelated)
     */
    public List getJpmProductSaleRelateds(final JpmProductSaleRelated jpmProductSaleRelated) {
        return dao.getJpmProductSaleRelateds(jpmProductSaleRelated);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleRelatedManager#getJpmProductSaleRelated(String id)
     */
    public JpmProductSaleRelated getJpmProductSaleRelated(final String id) {
        return dao.getJpmProductSaleRelated(new Long(id));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleRelatedManager#saveJpmProductSaleRelated(JpmProductSaleRelated jpmProductSaleRelated)
     */
    public void saveJpmProductSaleRelated(JpmProductSaleRelated jpmProductSaleRelated) {
        dao.saveJpmProductSaleRelated(jpmProductSaleRelated);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleRelatedManager#removeJpmProductSaleRelated(String id)
     */
    public void removeJpmProductSaleRelated(final String id) {
        dao.removeJpmProductSaleRelated(new Long(id));
    }
    //added for getJpmProductSaleRelatedsByCrm
    public List getJpmProductSaleRelatedsByCrm(CommonRecord crm, Pager pager){
	//return dao.getJpmProductSaleRelatedsByCrm(crm,pager);
    	return dao.getJpmProductSaleRelatedsByCrmSql(crm,pager);
    }
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
	public boolean isExist(CommonRecord crm, String type) {
		return dao.isExist(crm,type);
	}
}
