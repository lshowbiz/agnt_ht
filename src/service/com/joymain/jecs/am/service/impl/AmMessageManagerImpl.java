
package com.joymain.jecs.am.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.joymain.jecs.am.dao.AmMessageDao;
import com.joymain.jecs.am.model.AmMessage;
import com.joymain.jecs.am.service.AmMessageManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class AmMessageManagerImpl extends BaseManager implements AmMessageManager {
    public void saveAmMessageStatus(String uniNo, Integer status) {
		// TODO Auto-generated method stub
    	AmMessage amMessage = dao.getAmMessage(uniNo);
		amMessage.setStatus(status);
		dao.saveAmMessage(amMessage);
	}

	private AmMessageDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmMessageDao(AmMessageDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessageManager#getAmMessages(com.joymain.jecs.am.model.AmMessage)
     */
    public List getAmMessages(final AmMessage amMessage) {
        return dao.getAmMessages(amMessage);
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessageManager#getAmMessage(String uniNo)
     */
    public AmMessage getAmMessage(final String uniNo) {
        return dao.getAmMessage(new String(uniNo));
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessageManager#saveAmMessage(AmMessage amMessage)
     */
    public void saveAmMessage(AmMessage amMessage) {
        dao.saveAmMessage(amMessage);
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessageManager#removeAmMessage(String uniNo)
     */
    public void removeAmMessage(final String uniNo) {
        dao.removeAmMessage(new String(uniNo));
    }
    //added for getAmMessagesByCrm
		public List getAmMessagesByCrm(CommonRecord crm, Pager pager){
			
			List list = new ArrayList();
			String searchFlag = crm.getString("searchFlag", "");
			
			if("company".equals(searchFlag)){
				list = dao.getCompanyAmMessages(crm,pager);
			}else if("control".equals(searchFlag)){
				//list = dao.getAmMessagesByCrm(crm,pager);
				//add comment by lihao，查询条件里加上部门名称
				//list = dao.getAmMessagesByCrm2(crm,pager);
				//add comment by lihao，查询条件里加上部门名称（需求变更）
				list = dao.getAmMessagesByCrm3(crm,pager);
			}else if("cs".equals(searchFlag)){
				list = dao.getAmMessagesByCrm(crm,pager);
			}else if("agent".equals(searchFlag)){
				list = dao.getAgentAmMessages(crm,pager);
			}else if("type".equals(searchFlag)){
				String amMessageType=crm.getString("amMessageType", "");
				if(!StringUtil.isEmpty(amMessageType)){
					list = dao.getCompanyAmMessages(crm,pager);
				}
			}else{
				list = dao.getAmMessagesByCrm(crm,pager);
			}
			
			/*
			String isAgent = crm.getString("isAgent","");
			String agentNo = crm.getString("agentNo","");
			if("1".equals(isAgent)){
				list = dao.getAgentAmMessages(crm,pager);
			}else{
				list = dao.getAmMessagesByCrm(crm, pager);
			}
			*/
			
			return list;
//			List list = dao.getAmMessagesByCrm(crm,pager);
////			List ret = new ArrayList();
//			
//			String isAgent = crm.getString("isAgent","");
//			if("1".equals(isAgent)){
//				return reBuildList(list);
//			}else{
//				return list;
//			}
			
			
		}
		
		private List reBuildList(List list){
			List ret = new ArrayList();
			Iterator iterator=list.iterator();
			if(iterator.hasNext()){
				AmMessage amMessage = (AmMessage) iterator.next();
				if(amMessage.getSendRoute().equals("0")){
					if(amMessage.getStatus() >= 3){
						ret.add(amMessage);
					}
					
				}else{
					ret.add(amMessage);
				}
			}
			return ret;
		}

		public void readAmMessage(String uniNo) {
			// TODO Auto-generated method stub
			AmMessage amMessage = dao.getAmMessage(uniNo);
			amMessage.setStatus(9);
			saveAmMessage(amMessage);
		}

		public void checkAmMessage(String uniNo) {//,String checkUserNo
			// TODO Auto-generated method stub
			AmMessage amMessage = dao.getAmMessage(uniNo);
			//amMessage.setCheckUserNo(checkUserNo);
			//amMessage.setCheckMsgTime(new Date());
			amMessage.setStatus(3);
			saveAmMessage(amMessage);
		}
		
		public int checkAmMessageBatch(String aanos){
			return dao.checkAmMessageBatch(aanos);
			
		}
		
		public void agentSendMessage(String uniNo) {
			AmMessage amMessage = dao.getAmMessage(uniNo);
			amMessage.setStatus(0);
			saveAmMessage(amMessage);
			
		}

		public void saveAmMessageReceiver(String uniNo, String userNo, String userName, String ctrluser) {
			// TODO Auto-generated method stub
			AmMessage amMessage = dao.getAmMessage(uniNo);
			amMessage.setReceiverNo(userNo);
			amMessage.setReceiverName(userName);
			
			amMessage.setCheckUserNo(ctrluser);
			amMessage.setCheckMsgTime(new Date());
			
			dao.saveAmMessage(amMessage);
		}

		public String getAmMessagesReplyNum(CommonRecord crm, String tag) {
			String num = "0";
			if("reply".equals(tag)){
				num = dao.getAmMessagesReplyNum(crm);
			}else if ("noreply".equals(tag)){
				num = dao.getAmMessagesNoReplyNum(crm);
			}else if ("nocheck".equals(tag)){
				num = dao.getAmMessagesNoCkeckNum(crm);
			}
			
			return num;
		}

		public String getAgentReplyNum(CommonRecord crm, String tag) {
			String num = "0";
			if("reply".equals(tag)){
				num = dao.getAgentReplyNum(crm);
			}else if ("noreply".equals(tag)){
				num = dao.getAgentNoReplyNum(crm);
			}else if ("nocheck".equals(tag)){
				num = dao.getAmMessagesNoCkeckNum(crm);
			}else if ("noread".equals(tag)){
				num = dao.getAgentNoReadNum(crm);
			}
			
			return num;
		}

		public String getCompanyReplyNum(CommonRecord crm, String tag) {
			String num = "0";
			if("reply".equals(tag)){
				num = dao.getCompanyReplyNum(crm);
			}else if ("noreply".equals(tag)){
				num = dao.getCompanyNoReplyNum(crm);
			}else if ("nocheck".equals(tag)){
				//num = dao.getAmMessagesNoCkeckNum(crm);
			}else if ("noread".equals(tag)){
				//num = dao.getAgentNoReadNum(crm);
			}
			
			return num;
		}

		public List getRecentlyAmMessage(String userCode,String companyCode) {
			return dao.getRecentlyAmMessage(userCode,companyCode);
		}

		public List getAmMessageByUserCode(String userCode, String msgClassNo) {
			return dao.getAmMessageByUserCode(userCode, msgClassNo);
		}
}
