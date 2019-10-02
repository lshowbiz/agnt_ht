
package com.joymain.jecs.bd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.dao.JbdJdSendRecordDao;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.service.JbdJdSendRecordManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdJdSendRecordManagerImpl extends BaseManager implements JbdJdSendRecordManager {
    private JbdJdSendRecordDao dao;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdJdSendRecordDao(JbdJdSendRecordDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdJdSendRecordManager#getJbdJdSendRecords(com.joymain.jecs.bd.model.JbdJdSendRecord)
     */
    public List getJbdJdSendRecords(final JbdJdSendRecord jbdJdSendRecord) {
        return dao.getJbdJdSendRecords(jbdJdSendRecord);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdJdSendRecordManager#getJbdJdSendRecord(String id)
     */
    public JbdJdSendRecord getJbdJdSendRecord(final String id) {
        return dao.getJbdJdSendRecord(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdJdSendRecordManager#saveJbdJdSendRecord(JbdJdSendRecord jbdJdSendRecord)
     */
    public void saveJbdJdSendRecord(JbdJdSendRecord jbdJdSendRecord) {
        dao.saveJbdJdSendRecord(jbdJdSendRecord);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdJdSendRecordManager#removeJbdJdSendRecord(String id)
     */
    public void removeJbdJdSendRecord(final String id) {
        dao.removeJbdJdSendRecord(new Long(id));
    }
    //added for getJbdJdSendRecordsByCrm
    public List getJbdJdSendRecordsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdJdSendRecordsByCrm(crm,pager);
    }
    
    
    public void saveInJdFiBook(SysUser defSysUser,String id){

	    Date curDate=new Date();
	    
	    JbdJdSendRecord jbdJdSendRecord=this.getJbdJdSendRecord(id);

	    
	    if(jbdJdSendRecord.getStatus().intValue()==0){
		    Integer[] moneyType = new Integer[1];
			moneyType[0]=270;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdJdSendRecord.getSendMoney();
			
			String[] notes = new String[1];

		    notes[0]="云客推荐奖转电子存折";
			jbdJdSendRecord.setStatus(1);
			jbdJdSendRecord.setSendDate(curDate);
			this.saveJbdJdSendRecord(jbdJdSendRecord);
		    
		    
		    jfiBankbookJournalManager.saveJfiPayDataVerify(jbdJdSendRecord.getJmiMember().getCompanyCode(), jbdJdSendRecord.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bdjd"+jbdJdSendRecord.getId().toString(), notes, "1");

	    }
  
	}
}
