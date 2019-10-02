
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwIntegrationTotal;
import com.joymain.jecs.am.dao.InwIntegrationTotalDao;
import com.joymain.jecs.am.service.InwIntegrationTotalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class InwIntegrationTotalManagerImpl extends BaseManager implements InwIntegrationTotalManager {
    private InwIntegrationTotalDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwIntegrationTotalDao(InwIntegrationTotalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationTotalManager#getInwIntegrationTotals(com.joymain.jecs.am.model.InwIntegrationTotal)
     */
    public List getInwIntegrationTotals(final InwIntegrationTotal inwIntegrationTotal) {
        return dao.getInwIntegrationTotals(inwIntegrationTotal);
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationTotalManager#getInwIntegrationTotal(String id)
     */
    public InwIntegrationTotal getInwIntegrationTotal(final String id) {
        return dao.getInwIntegrationTotal(new Long(id));
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationTotalManager#saveInwIntegrationTotal(InwIntegrationTotal inwIntegrationTotal)
     */
    public void saveInwIntegrationTotal(InwIntegrationTotal inwIntegrationTotal) {
        dao.saveInwIntegrationTotal(inwIntegrationTotal);
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationTotalManager#removeInwIntegrationTotal(String id)
     */
    public void removeInwIntegrationTotal(final String id) {
        dao.removeInwIntegrationTotal(new Long(id));
    }
    //added for getInwIntegrationTotalsByCrm
    public List getInwIntegrationTotalsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwIntegrationTotalsByCrm(crm,pager);
    }

    /**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-05
     * @param userCode
     * @param integratotal
     * @param uniqueCode
     * @return boolean
     */
	public boolean minusIntegrationTotal(String userCode, String integratotal,String uniqueCode) {
		return dao.minusIntegrationTotal(userCode,integratotal,uniqueCode);
	}

	/**
     * 建议回复时给于积分对总积分的同步操作
     * @author yxzz  2014-06-05
     * @param userCode
     * @param parseInt
     * @return 
     */
	public void updateOrSaveInwIntegrationTotal(String userCode, int parseInt) {
		dao.updateOrSaveInwIntegrationTotal(userCode,parseInt);
	}
	
}
