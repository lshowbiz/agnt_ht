
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.dao.InwIntegrationDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwIntegrationManager extends Manager {
    /**
     * Retrieves all of the inwIntegrations
     */
    public List getInwIntegrations(InwIntegration inwIntegration);

    /**
     * Gets inwIntegration's information based on id.
     * @param id the inwIntegration's id
     * @return inwIntegration populated inwIntegration object
     */
    public InwIntegration getInwIntegration(final String id);

    /**
     * 向创新共赢的创新积分表中添加数据
     * @author gw 2013-09-05
     * @param inwIntegration
     */
    public void saveInwIntegration(InwIntegration inwIntegration);

    /**
     * Removes a inwIntegration from the database by id
     * @param id the inwIntegration's id
     */
    public void removeInwIntegration(final String id);
    //added for getInwIntegrationsByCrm
    public List getInwIntegrationsByCrm(CommonRecord crm, Pager pager);

    /**
     * 在增加创新积分前,先去数据库中查询.如果该条建议已经为会员增加了创新积分,那么不再为该会员添加创新积分
     * @author 2013-09-13 
     * @param suggestionUserCode
     * @param suggestionid
     * @return InwIntegration
     */
	public InwIntegration getInwIntegrationByParam(String suggestionUserCode, String suggestionid);
}

