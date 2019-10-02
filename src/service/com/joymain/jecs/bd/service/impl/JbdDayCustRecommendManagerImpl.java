package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.dao.JbdDayCustRecommendDao;
import com.joymain.jecs.bd.model.JbdDayCustRecommend;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.service.JbdDayCustRecommendManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JbdDayCustRecommendManagerImpl extends BaseManager implements JbdDayCustRecommendManager {
	private JbdDayCustRecommendDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setJbdDayCustRecommendDao(JbdDayCustRecommendDao dao) {
		this.dao = dao;
	}   
	
	private JfiBankbookJournalManager jfiBankbookJournalManager;
    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
	 * @see com.joymain.jecs.bd.service.JbdDayCustRecommendManager#getJbdDayCustRecommends(com.joymain.jecs.bd.model.JbdDayCustRecommend)
	 */
	public List getJbdDayCustRecommends(final JbdDayCustRecommend jbdDayCustRecommend) {
		return dao.getJbdDayCustRecommends(jbdDayCustRecommend);
	}

	/**
	 * @see com.joymain.jecs.bd.service.JbdDayCustRecommendManager#getJbdDayCustRecommend(String id)
	 */
	public JbdDayCustRecommend getJbdDayCustRecommend(final String id) {
		return dao.getJbdDayCustRecommend(new Long(id));
	}

	/**
	 * @see com.joymain.jecs.bd.service.JbdDayCustRecommendManager#saveJbdDayCustRecommend(JbdDayCustRecommend jbdDayCustRecommend)
	 */
	public void saveJbdDayCustRecommend(JbdDayCustRecommend jbdDayCustRecommend) {
		dao.saveJbdDayCustRecommend(jbdDayCustRecommend);
	}

	//added for getJbdDayCustRecommendsByCrm
	public List getJbdDayCustRecommendsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJbdDayCustRecommendsByCrm(crm, pager);
	}

	public void saveJbdDayCustRecommends(List jbdDayCustRecommends) {
		dao.saveJbdDayCustRecommends(jbdDayCustRecommends);

	}

	public List getJbdDayCustRecommendsByCrmBySql(CommonRecord crm, Pager pager) {
		return dao.getJbdDayCustRecommendsByCrmBySql(crm, pager);
	}
	
    public void saveInJdFiBook(SysUser defSysUser,String id){

	    Date curDate=new Date();
	    
	    JbdDayCustRecommend jbdDayCustRecommend=this.getJbdDayCustRecommend(id);

	    
	    if(jbdDayCustRecommend.getStatus().intValue()==0){
		    Integer[] moneyType = new Integer[1];
			moneyType[0]=280;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdDayCustRecommend.getSendMoney();
			
			String[] notes = new String[1];

		    notes[0]="顾问推荐奖励进电子存折";
		    jbdDayCustRecommend.setStatus(1);
		    jbdDayCustRecommend.setSendDate(curDate);
			this.saveJbdDayCustRecommend(jbdDayCustRecommend);
		    
		    
		    jfiBankbookJournalManager.saveJfiPayDataVerify(jbdDayCustRecommend.getJmiMember().getCompanyCode(), jbdDayCustRecommend.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bddcr"+jbdDayCustRecommend.getId().toString(), notes, "1");

	    }
  
	}
}
