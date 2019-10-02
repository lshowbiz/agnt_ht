
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.dao.JbdUserCardListDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdUserCardList;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdUserCardListManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.util.string.StringUtil;
public class JbdUserCardListManagerImpl extends BaseManager implements JbdUserCardListManager {
    private JbdUserCardListDao dao;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdUserCardListDao(JbdUserCardListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCardListManager#getJbdUserCardLists(com.joymain.jecs.bd.model.JbdUserCardList)
     */
    public List getJbdUserCardLists(final JbdUserCardList jbdUserCardList) {
        return dao.getJbdUserCardLists(jbdUserCardList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCardListManager#getJbdUserCardList(String id)
     */
    public JbdUserCardList getJbdUserCardList(final String id) {
        return dao.getJbdUserCardList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCardListManager#saveJbdUserCardList(JbdUserCardList jbdUserCardList)
     */
    public void saveJbdUserCardList(JbdUserCardList jbdUserCardList) {
        dao.saveJbdUserCardList(jbdUserCardList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCardListManager#removeJbdUserCardList(String id)
     */
    public void removeJbdUserCardList(final String id) {
        dao.removeJbdUserCardList(new Long(id));
    }
    //added for getJbdUserCardListsByCrm
    public List getJbdUserCardListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdUserCardListsByCrm(crm,pager);
    }
    /**
     * 
     */
	public void saveJbdUserCardList(String userCode,Date operatDate,String newCard,String updateType,String operaterType) {
		
		Date logCurDate=new Date();
		BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByTime(operatDate, null);		
		
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
		//Modify By WuCF 20140219 注释，会可能导致erp、erp1自动停掉
//		FileUtil.saveLogger("bd_user_card.txt", logCurDate, new Date(), "查出期别"+userCode);

		this.saveJbdUserCardList(userCode, StringUtil.formatInt(bdWeek), newCard, updateType,operaterType);
//		JbdUserCardList jbdUserCardList=dao.getJbdUserCardListByUserCodeAndWeek(userCode, StringUtil.formatInt(bdWeek));
//
//		if(jbdUserCardList!=null){
//			jbdUserCardList.setNewCard(newCard);
//		}else{
//			JbdUserCardList previousJbdUserCardList=dao.getPreviousJbdUserCardList(userCode,  StringUtil.formatInt(bdWeek));
//			jbdUserCardList=new JbdUserCardList();
//			if(previousJbdUserCardList==null){
//				jbdUserCardList.setOldCard(newCard);
//			}else{
//				jbdUserCardList.setOldCard(previousJbdUserCardList.getNewCard());
//			}
//			jbdUserCardList.setNewCard(newCard);
//			jbdUserCardList.setUserCode(userCode);
//			jbdUserCardList.setWweek(StringUtil.formatInt(bdWeek));
//		}
//		
//		dao.saveJbdUserCardList(jbdUserCardList);
	}

	public boolean getPreviousJbdUserCardList(Date tDate, String userCode) {
		BdPeriod sbdPeriod = bdPeriodManager.getBdPeriodByTime(tDate, null);
		JbdUserCardList previousJbdUserCardList=dao.getPreviousJbdUserCardList(userCode, sbdPeriod.getWweek());
		if(previousJbdUserCardList==null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * operaterType 1 审核订单 2.更改审核日期
	 */
	public void saveJbdUserCardList(String userCode, Integer wweek, String newCard,String updateType,String operaterType) {

		Date logCurDate=new Date();
		JbdUserCardList jbdUserCardList=dao.getJbdUserCardListByUserCodeAndWeek(userCode, wweek);

		JbdUserCardList previousJbdUserCardList=dao.getPreviousJbdUserCardList(userCode,  wweek);
		//Modify By WuCF 20140219 注释，会可能导致erp、erp1自动停掉
//		FileUtil.saveLogger("bd_user_card.txt", logCurDate, new Date(), "查出当前记录与前期记录"+userCode);
		logCurDate=new Date();
		//当前期别前没有记录
		if(previousJbdUserCardList==null){
			if(jbdUserCardList==null){//当前期没数据
				jbdUserCardList=new JbdUserCardList();
				jbdUserCardList.setUserCode(userCode);
				jbdUserCardList.setOldCard("0");
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setWweek(wweek);
				jbdUserCardList.setUpdateType(updateType);
			}else{//当前期有数据
				jbdUserCardList.setOldCard("0");
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setUpdateType(updateType);
			}
		}else{
			if(jbdUserCardList==null){
				jbdUserCardList=new JbdUserCardList();
				jbdUserCardList.setUserCode(userCode);
				
				jbdUserCardList.setOldCard(previousJbdUserCardList.getNewCard());
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setWweek(wweek);
				jbdUserCardList.setUpdateType(updateType);
			}else{
				jbdUserCardList.setOldCard(previousJbdUserCardList.getNewCard());
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setUpdateType(updateType);
			}
			if("1".equals(updateType)){
				previousJbdUserCardList.setNewCard("0");
				jbdUserCardList.setOldCard("0");
				dao.saveJbdUserCardList(previousJbdUserCardList);
			}else if("2".equals(updateType)){
				if("2".equals(previousJbdUserCardList.getUpdateType())&&"2".equals(operaterType)){
					previousJbdUserCardList.setNewCard(previousJbdUserCardList.getOldCard());
					jbdUserCardList.setOldCard(previousJbdUserCardList.getOldCard());
					dao.saveJbdUserCardList(previousJbdUserCardList);
				}
			}
		}
		//手动变级时，更新修改期别后面的期别
		
		//自然升级时，如果更新的新卡别大于升级卡别时，不动那一期的新卡别
		
		dao.saveJbdUserCardList(jbdUserCardList);
		//Modify By WuCF 20140219 注释，会可能导致erp、erp1自动停掉
//		FileUtil.saveLogger("bd_user_card.txt", logCurDate, new Date(), "更新记录及保存当前期记录"+userCode);
		
		String oldlCardType="";//每一期的
		//String newCardType="";
		logCurDate=new Date();
		List<JbdUserCardList> nextJbdUserCardList=dao.getNextJbdUserCardList(userCode,  wweek);
		//Modify By WuCF 20140219 注释，会可能导致erp、erp1自动停掉
//		FileUtil.saveLogger("bd_user_card.txt", logCurDate, new Date(), "查出后期记录"+userCode);
		logCurDate=new Date();
		for (int i = 0; i < nextJbdUserCardList.size(); i++) {
			JbdUserCardList curJbdUserCardList=nextJbdUserCardList.get(i);
			
			
			if("3".equals(updateType)){
				curJbdUserCardList.setOldCard(newCard);
				curJbdUserCardList.setNewCard(newCard);
			}else{
				if(i==0){
					oldlCardType=newCard;
				}
				curJbdUserCardList.setOldCard(oldlCardType);
				
				
				int curNewCard=Integer.valueOf(curJbdUserCardList.getNewCard());
				int inNewCard=Integer.valueOf(newCard);
				//如果卡别为5，则大于0时，修改，否则不修改
				
				if(curNewCard==0&&inNewCard==5){
					curJbdUserCardList.setNewCard(newCard);
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard!=0&&inNewCard==5){
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard==0&&inNewCard!=5){
					curJbdUserCardList.setNewCard(newCard);
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard==5&&inNewCard==0){
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard==5&&inNewCard!=0){
					curJbdUserCardList.setNewCard(newCard);
					oldlCardType=curJbdUserCardList.getNewCard();
				}else{
					if(Integer.valueOf(curJbdUserCardList.getNewCard())<Integer.valueOf(newCard)){
						curJbdUserCardList.setNewCard(newCard);
					}
					oldlCardType=curJbdUserCardList.getNewCard();
				}
				
				
				
				
			}
			
			dao.saveJbdUserCardList(curJbdUserCardList);
			//Modify By WuCF 20140219 注释，会可能导致erp、erp1自动停掉
//			FileUtil.saveLogger("bd_user_card.txt", logCurDate, new Date(), "循环并记录"+userCode);
		}
		
	}

	public List getJbdUserCardListByUserCode(String userCode) {
		return dao.getJbdUserCardListByUserCode(userCode);
	}

	public List getJbdUserCardListByUserCodeFweek(String userCode) {
		List<JbdUserCardList> list=dao.getJbdUserCardListByUserCode(userCode);
		for (JbdUserCardList jbdUserCardList : list) {
			jbdUserCardList.setWweek(new Integer(WeekFormatUtil.getFormatWeek("w", jbdUserCardList.getWweek().toString())));
		}
		return list;
	}

}
