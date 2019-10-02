
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.dao.JbdDayCustRecommendKbDao;
import com.joymain.jecs.bd.model.JbdDayCustRecommendKb;
import com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdDayCustRecommendKbManagerImpl extends BaseManager implements JbdDayCustRecommendKbManager {
    private JbdDayCustRecommendKbDao dao;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdDayCustRecommendKbDao(JbdDayCustRecommendKbDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager#getJbdDayCustRecommendKbs(com.joymain.jecs.bd.model.JbdDayCustRecommendKb)
     */
    public List getJbdDayCustRecommendKbs(final JbdDayCustRecommendKb jbdDayCustRecommendKb) {
        return dao.getJbdDayCustRecommendKbs(jbdDayCustRecommendKb);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager#getJbdDayCustRecommendKb(String id)
     */
    public JbdDayCustRecommendKb getJbdDayCustRecommendKb(final String id) {
        return dao.getJbdDayCustRecommendKb(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager#saveJbdDayCustRecommendKb(JbdDayCustRecommendKb jbdDayCustRecommendKb)
     */
    public void saveJbdDayCustRecommendKb(JbdDayCustRecommendKb jbdDayCustRecommendKb) {
        dao.saveJbdDayCustRecommendKb(jbdDayCustRecommendKb);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager#removeJbdDayCustRecommendKb(String id)
     */
    public void removeJbdDayCustRecommendKb(final String id) {
        dao.removeJbdDayCustRecommendKb(new Long(id));
    }
    //added for getJbdDayCustRecommendKbsByCrm
    public List getJbdDayCustRecommendKbsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdDayCustRecommendKbsByCrm(crm,pager);
    }
    
    
    public void saveInJdFiBook(SysUser defSysUser,String id){

/*	    Date curDate=new Date();
	    
	    JbdDayCustRecommendKb jbdDayCustRecommendKb=this.getJbdDayCustRecommendKb(id);

	    
	    if(jbdDayCustRecommendKb.getStatus().intValue()==0){
		    Integer[] moneyType = new Integer[1];
			moneyType[0]=270;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdDayCustRecommendKb.getSendMoney();
			
			String[] notes = new String[1];

		    notes[0]="云客推荐奖转电子存折";
			jbdDayCustRecommendKb.setStatus(1);
			jbdDayCustRecommendKb.setSendDate(curDate);
			this.saveJbdDayCustRecommendKb(jbdDayCustRecommendKb);
		    
		    
		    jfiBankbookJournalManager.saveJfiPayDataVerify(jbdDayCustRecommendKb.getJmiMember().getCompanyCode(), jbdDayCustRecommendKb.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bdjd"+jbdDayCustRecommendKb.getId().toString(), notes, "1");

	    }*/
  
	}
}
