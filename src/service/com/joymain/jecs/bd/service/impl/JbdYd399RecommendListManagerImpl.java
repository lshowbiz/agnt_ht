package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.joymain.jecs.bd.dao.JbdYd399RecommendHistDao;
import com.joymain.jecs.bd.dao.JbdYd399RecommendListDao;
import com.joymain.jecs.bd.model.JbdYd399RecommendHist;
import com.joymain.jecs.bd.model.JbdYd399RecommendList;
import com.joymain.jecs.bd.service.JbdYd399RecommendListManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JbdYd399RecommendListManagerImpl extends BaseManager implements JbdYd399RecommendListManager {
	
	private JbdYd399RecommendListDao dao;
	public void setJbdYd399RecommendListDao(JbdYd399RecommendListDao dao) {
		this.dao = dao;
	}   
	
	private JbdYd399RecommendHistDao jbdYd399RecommendHistDao;
	public void setJbdYd399RecommendHistDao(JbdYd399RecommendHistDao jbdYd399RecommendHistDao) {
		this.jbdYd399RecommendHistDao = jbdYd399RecommendHistDao;
	}

	private JfiBankbookJournalManager jfiBankbookJournalManager;
	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	
	public List getJbdYd399RecommendLists(final JbdYd399RecommendList jbdYd399RecommendList) {
		return dao.getJbdYd399RecommendLists(jbdYd399RecommendList);
	}


	public JbdYd399RecommendList getJbdYd399RecommendList(final String id) {
		return dao.getJbdYd399RecommendList(new Long(id));
	}


	public List getJbdYd399RecommendListsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJbdYd399RecommendListsByCrm(crm, pager);
	}
	
	public List getJbdYd399RecommendListsByCrm2(CommonRecord crm, Pager pager) {
		return dao.getJbdYd399RecommendListsByCrm2(crm, pager);
	}

	public List getJbdYd399RecommendListsByCrmBySql(CommonRecord crm, Pager pager) {
		return dao.getJbdYd399RecommendListsByCrmBySql(crm, pager);
	}
	
    public void saveInJdFiBook(SysUser defSysUser,String id){

	    Date curDate=new Date();
	    
	    JbdYd399RecommendList jbdYd399RecommendList=this.getJbdYd399RecommendList(id);
	    JbdYd399RecommendHist jbdYd399RecommendHist = new JbdYd399RecommendHist();
	    
	    if(jbdYd399RecommendList.getSendStatus().intValue()==0){
		    Integer[] moneyType = new Integer[1];
			moneyType[0]=290;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdYd399RecommendList.getSendAmount();
			String[] notes = new String[1];
		    notes[0]="399店主推荐奖励转电子存折";
		    jbdYd399RecommendList.setSendStatus(1);
		    jbdYd399RecommendList.setSendDate(curDate);
		    jbdYd399RecommendList.setSendOperator(defSysUser.getUserCode());
		    //添加到历史表
		    BeanUtils.copyProperties(jbdYd399RecommendList,jbdYd399RecommendHist);
		    jbdYd399RecommendHistDao.save(jbdYd399RecommendHist);
		    jfiBankbookJournalManager.saveJfiPayDataVerify(jbdYd399RecommendList.getJmiMember().getCompanyCode(), jbdYd399RecommendList.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd399yd"+jbdYd399RecommendList.getId().toString(), notes, "1");
		    //删除list表数据
		    this.removeJbdYd399RecommendList(id);
	    }
	}

	@Override
	public void removeJbdYd399RecommendList(String id) {
		dao.removeJbdYd399RecommendList(new Long(id));
	}
}
