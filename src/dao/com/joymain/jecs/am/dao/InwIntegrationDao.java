
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface InwIntegrationDao extends Dao {

    /**
     * Retrieves all of the inwIntegrations
     */
    public List getInwIntegrations(InwIntegration inwIntegration);

    /**
     * Gets inwIntegration's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the inwIntegration's id
     * @return inwIntegration populated inwIntegration object
     */
    public InwIntegration getInwIntegration(final BigDecimal id);

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
    public void removeInwIntegration(final BigDecimal id);
    //added for getInwIntegrationsByCrm
    public List getInwIntegrationsByCrm(CommonRecord crm, Pager pager);

    /**
     * 在增加创新积分前,先去数据库中查询.如果该条建议已经为会员增加了创新积分,那么不再为该会员添加创新积分
     * @author 2013-09-13 
     * @param suggestionUserCode
     * @param suggestionid
     * @return InwIntegration
     */
	public InwIntegration getInwIntegrationByParam(String suggestionUserCode,String suggestionid);

	/**
	 * 在扣除积分之前,首先进行放重复提交的校验
	 * @author 2014-06-10
	 * @param uniqueCode
	 * @return boolean
	 */
	public boolean getCheckExist(String uniqueCode);
			
}

