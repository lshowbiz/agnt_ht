
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.dao.InwIntegrationDao;
import com.joymain.jecs.am.service.InwIntegrationManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class InwIntegrationManagerImpl extends BaseManager implements InwIntegrationManager {
    private InwIntegrationDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setInwIntegrationDao(InwIntegrationDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationManager#getInwIntegrations(com.joymain.jecs.am.model.InwIntegration)
     */
    public List getInwIntegrations(final InwIntegration inwIntegration) {
        return dao.getInwIntegrations(inwIntegration);
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationManager#getInwIntegration(String id)
     */
    public InwIntegration getInwIntegration(final String id) {
        return dao.getInwIntegration(new BigDecimal(id));
    }

    /**
     * 向创新共赢的创新积分表中添加数据
     * @author gw 2013-09-05
     * @param inwIntegration
     */
    public void saveInwIntegration(InwIntegration inwIntegration) {
        dao.saveInwIntegration(inwIntegration);
    }

    /**
     * @see com.joymain.jecs.am.service.InwIntegrationManager#removeInwIntegration(String id)
     */
    public void removeInwIntegration(final String id) {
        dao.removeInwIntegration(new BigDecimal(id));
    }
    //added for getInwIntegrationsByCrm
    public List getInwIntegrationsByCrm(CommonRecord crm, Pager pager){
	return dao.getInwIntegrationsByCrm(crm,pager);
    }

    /**
     * 在增加创新积分前,先去数据库中查询.如果该条建议已经为会员增加了创新积分,那么不再为该会员添加创新积分
     * @author 2013-09-13 
     * @param suggestionUserCode
     * @param suggestionid
     * @return InwIntegration
     */
	public InwIntegration getInwIntegrationByParam(String suggestionUserCode,
			String suggestionid) {
		return dao.getInwIntegrationByParam(suggestionUserCode,suggestionid);
	}
}
