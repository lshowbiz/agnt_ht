
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.model.JbdYdRebateHist;
import com.joymain.jecs.bd.model.JbdYdRebateList;
import com.joymain.jecs.bd.dao.JbdYdRebateHistDao;
import com.joymain.jecs.bd.dao.JbdYdRebateListDao;
import com.joymain.jecs.bd.service.JbdYdRebateListManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.bean.BeanUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdYdRebateListManagerImpl extends BaseManager implements JbdYdRebateListManager {
    private JbdYdRebateListDao jbdYdRebateListDao;
    private JbdYdRebateHistDao jbdYdRebateHistDao;
    private JfiBankbookJournalManager  jfiBankbookJournalManager;
    
    
	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setJbdYdRebateListDao(JbdYdRebateListDao jbdYdRebateListDao) {
		this.jbdYdRebateListDao = jbdYdRebateListDao;
	}

	public void setJbdYdRebateHistDao(JbdYdRebateHistDao jbdYdRebateHistDao) {
		this.jbdYdRebateHistDao = jbdYdRebateHistDao;
	}

	@Override
	public List getJbdYdRebateLists(JbdYdRebateList jbdYdRebateList) {
		// TODO Auto-generated method stub
		return jbdYdRebateListDao.getJbdYdRebateLists(jbdYdRebateList);
	}

	@Override
	public JbdYdRebateList getJbdYdRebateList(String id) {
		// TODO Auto-generated method stub
		return jbdYdRebateListDao.getJbdYdRebateList(new Long(id));
	}

	@Override
	public void saveJbdYdRebateList(JbdYdRebateList jbdYdRebateList) {
		// TODO Auto-generated method stub
		jbdYdRebateListDao.saveJbdYdRebateList(jbdYdRebateList);
	}

	@Override
	public void removeJbdYdRebateList(String id) {
		// TODO Auto-generated method stub
		jbdYdRebateListDao.removeJbdYdRebateList(new Long(id));
	}

	@Override
	public List getJbdYdRebateListsByCrm(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return jbdYdRebateListDao.getJbdYdRebateListsByCrm(crm,pager);
	}

	@Override
	public List getVJbdYdRebateListsByCrm(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return jbdYdRebateListDao.getVJbdYdRebateListsByCrm(crm,pager);
	}

	@Override
	public void saveInJdFiBook(SysUser defSysUser, String id) {
		// TODO Auto-generated method stub
		  //jbdYdRebateListDao.saveInJdFiBook(defSysUser,id);
		  
		Date curDate=new Date();
	    
		JbdYdRebateList jbdYdRebateList=this.getJbdYdRebateList(id);
		JbdYdRebateHist jbdYdRebateHist = new JbdYdRebateHist();
		BeanUtils.copyProperties(jbdYdRebateList, jbdYdRebateHist);
	    if(jbdYdRebateList.getSendStatus().intValue()==0 ){
		    Integer[] moneyType = new Integer[1];
			moneyType[0]=300;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdYdRebateList.getSendAmount();
			
			String[] notes = new String[1];

		     notes[0]="云店返利奖励转电子存折";
			jbdYdRebateHist.setSendStatus(1);
			jbdYdRebateHist.setSendDate(curDate);
			jbdYdRebateHist.setSendOperator(defSysUser.getUserCode());
			
			jbdYdRebateHistDao.save(jbdYdRebateHist);
		    jfiBankbookJournalManager.saveJfiPayDataVerify(jbdYdRebateList.getJmiMember().getCompanyCode(), jbdYdRebateList.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bdydrebate"+jbdYdRebateList.getId().toString(), notes, "1");
		    this.removeJbdYdRebateList(id);

	    }
		  
	}

   






}
