
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.am.model.AmMessage;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AmMessageManager extends Manager {
	
	public void saveAmMessageReceiver(final String uniNo,String userNo, String userName, String ctrluser);
	public void saveAmMessageStatus(final String uniNo ,Integer status);
	public void checkAmMessage(final String uniNo );//,final String checkUserNo
	public void readAmMessage(final String uniNo );
    /**
     * Retrieves all of the amMessages
     */
    public List getAmMessages(AmMessage amMessage);

    /**
     * Gets amMessage's information based on uniNo.
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
		
		public void agentSendMessage(String uniNo);
		
		/**
		 * 
		 * @param tag	"reply"代表查询回复数,"noreply"代表查询未回复数
		 * @return
		 */
		public String getAmMessagesReplyNum(CommonRecord crm, String tag);
		
		public String getAgentReplyNum(CommonRecord crm, String tag);
		
		public String getCompanyReplyNum(CommonRecord crm, String tag);
		
		public int checkAmMessageBatch(String aanos);
		/**
		 * 获取该会员最近几次的记录
		 * @param userCode
		 * @return
		 */
		public List getRecentlyAmMessage(String userCode,String companyCode);
		public List getAmMessageByUserCode(String userCode,String msgClassNo);
}

