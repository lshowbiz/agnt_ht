
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.fi.dao.Jfi99billPosLogDao;
import com.joymain.jecs.fi.model.Jfi99billPosLog;
import com.joymain.jecs.fi.service.Jfi99billPosLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class Jfi99billPosLogManagerImpl extends BaseManager implements Jfi99billPosLogManager {
    private Jfi99billPosLogDao dao;
    private JfiBankbookJournalManager jfiBankbookJournalManager;

    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfi99billPosLogDao(Jfi99billPosLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosLogManager#getJfi99billPosLogs(com.joymain.jecs.fi.model.Jfi99billPosLog)
     */
    public List getJfi99billPosLogs(final Jfi99billPosLog jfi99billPosLog) {
        return dao.getJfi99billPosLogs(jfi99billPosLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosLogManager#getJfi99billPosLog(String logId)
     */
    public Jfi99billPosLog getJfi99billPosLog(final String logId) {
        return dao.getJfi99billPosLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosLogManager#saveJfi99billPosLog(Jfi99billPosLog jfi99billPosLog)
     */
    public void saveJfi99billPosLog(Jfi99billPosLog jfi99billPosLog) {
        dao.saveJfi99billPosLog(jfi99billPosLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosLogManager#removeJfi99billPosLog(String logId)
     */
    public void removeJfi99billPosLog(final String logId) {
        dao.removeJfi99billPosLog(new Long(logId));
    }
    //added for getJfi99billPosLogsByCrm
    public List getJfi99billPosLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfi99billPosLogsByCrm(crm,pager);
    }
    
    /**
     * POS支付进电子存折
     * @param jfi99billPosLog
     * @param sysUser
     */
    public void saveJfi99billPosLogAndBankAccount(Jfi99billPosLog jfi99billPosLog, SysUser sysUser){
    	jfi99billPosLog.setInc("1");
    	this.saveJfi99billPosLog(jfi99billPosLog);
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 28;
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = new BigDecimal(jfi99billPosLog.getAmt());
		String[] notes = new String[1];
		notes[0] = "快钱POS支付";
    	jfiBankbookJournalManager.saveJfiPayDataVerify("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), jfi99billPosLog.getActionNo(), notes,"1");
    }
}
