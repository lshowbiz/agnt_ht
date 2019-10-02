
package com.joymain.jecs.am.dao;

import java.util.List;

import com.joymain.jecs.am.model.AmMessage;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmMessageDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the amMessages
     */
    public List getAmMessages(AmMessage amMessage);

    /**
     * Gets amMessage's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the amMessage's uniNo
     * @return amMessage populated amMessage object
     */
    public AmMessage getAmMessage(final String uniNo);

    /**
     * Saves a amMessage's information
     * @param amMessage the object to be saved
     */    
    public void saveAmMessage(AmMessage amMessage);

    /**
     * Removes a amMessage from the database by uniNo
     * @param uniNo the amMessage's uniNo
     */
    public void removeAmMessage(final String uniNo);
		//added for getAmMessagesByCrm
		public List getAmMessagesByCrm(CommonRecord crm, Pager pager);
		public List getAgentAmMessages(CommonRecord crm, Pager pager);

		public List getCompanyAmMessages(CommonRecord crm, Pager pager);
		
		public String getAmMessagesReplyNum(CommonRecord crm);
		public String getAmMessagesNoReplyNum(CommonRecord crm);
		public String getAmMessagesNoCkeckNum(CommonRecord crm);
		
		public String getAgentReplyNum(CommonRecord crm);
		public String getAgentNoReplyNum(CommonRecord crm);
		public String getAgentNoReadNum(CommonRecord crm);
		
		public String getCompanyReplyNum(CommonRecord crm);
		public String getCompanyNoReplyNum(CommonRecord crm);
		public int checkAmMessageBatch(String aanos);
		/**
		 * 获取该会员最近几次的记录
		 * @param userCode
		 * @return
		 */
		public List getRecentlyAmMessage(String userCode,String companyCode);
		
		public List getAmMessageByUserCode(String userCode,String msgClassNo);
		
		//add by lihao,查询条件里加上部门名称
		public List getAmMessagesByCrm2(CommonRecord crm, Pager pager);

		//add by lihao,查询条件里加上部门信息（需求变更）
		List getAmMessagesByCrm3(CommonRecord crm, Pager pager);
		
}

