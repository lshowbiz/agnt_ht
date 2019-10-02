
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.po.model.JpoBankBookPayList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.dao.JbdCalcDayFbDao;
import com.joymain.jecs.bd.service.JbdCalcDayFbManager;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.FiProductPointBalanceManager;
import com.joymain.jecs.fi.service.FiProductPointJournalManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdCalcDayFbManagerImpl extends BaseManager implements JbdCalcDayFbManager {
    private JbdCalcDayFbDao dao;
    private FiProductPointJournalManager fiProductPointJournalManager;
	public void setFiProductPointJournalManager(
			FiProductPointJournalManager fiProductPointJournalManager) {
		this.fiProductPointJournalManager = fiProductPointJournalManager;
	}
	private JfiBankbookJournalManager jfiBankbookJournalManager;


	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdCalcDayFbDao(JbdCalcDayFbDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayFbManager#getJbdCalcDayFbs(com.joymain.jecs.bd.model.JbdCalcDayFb)
     */
    public List getJbdCalcDayFbs(final JbdCalcDayFb jbdCalcDayFb) {
        return dao.getJbdCalcDayFbs(jbdCalcDayFb);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayFbManager#getJbdCalcDayFb(String id)
     */
    public JbdCalcDayFb getJbdCalcDayFb(final String id) {
        return dao.getJbdCalcDayFb(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayFbManager#saveJbdCalcDayFb(JbdCalcDayFb jbdCalcDayFb)
     */
    public void saveJbdCalcDayFb(JbdCalcDayFb jbdCalcDayFb) {
        dao.saveJbdCalcDayFb(jbdCalcDayFb);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayFbManager#removeJbdCalcDayFb(String id)
     */
    public void removeJbdCalcDayFb(final String id) {
        dao.removeJbdCalcDayFb(new Long(id));
    }
    //added for getJbdCalcDayFbsByCrm
    public List getJbdCalcDayFbsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdCalcDayFbsByCrm(crm,pager);
    }

	@Override
	public List getJbdCalcDayFbList(String userCode, String wweek) {
		return dao.getJbdCalcDayFbList(userCode, wweek);
	}


	public void saveInProductPointFiBook(SysUser defSysUser,String id){

	    Date curDate=new Date();
	    
	    
	    JbdCalcDayFb jbdCalcDayFb=this.getJbdCalcDayFb(id);
	    
	    if(jbdCalcDayFb.getStatus().intValue()==0){
		    Integer[] moneyType = new Integer[1];
			moneyType[0]=250;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdCalcDayFb.getDeductVolume();
			
			String[] notes = new String[1];
			notes[0]="奖金转入抵用券";
		    
			jbdCalcDayFb.setStatus(1);
			jbdCalcDayFb.setSendDate(curDate);
			this.saveJbdCalcDayFb(jbdCalcDayFb);
		    if(jbdCalcDayFb.getDeductVolume()!=null){
		    	
		    	fiProductPointJournalManager.saveFiPayDataVerify(jbdCalcDayFb.getJmiMember().getCompanyCode(), jbdCalcDayFb.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd"+jbdCalcDayFb.getId(), notes, "1", "1");
		    }

			moneyArray[0]=jbdCalcDayFb.getSendMoney();
		    
			notes[0]="创业保障奖转电子存折";
		    

			moneyType[0]=260;
			  if(jbdCalcDayFb.getSendMoney()!=null){
				  
				  jfiBankbookJournalManager.saveJfiPayDataVerify(jbdCalcDayFb.getJmiMember().getCompanyCode(), jbdCalcDayFb.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bdcd"+jbdCalcDayFb.getId().toString(), notes, "1");
			  }

	    }
  
	}
}
