
package com.joymain.jecs.fi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.fi.dao.JfiDepositSendDao;
import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiDepositJournalManager;
import com.joymain.jecs.fi.service.JfiDepositSendManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiDepositSendManagerImpl extends BaseManager implements JfiDepositSendManager {
    private JfiDepositSendDao dao;
    private JfiDepositJournalManager jfiDepositJournalManager;
    public void setJfiDepositJournalManager(
			JfiDepositJournalManager jfiDepositJournalManager) {
		this.jfiDepositJournalManager = jfiDepositJournalManager;
	}
    private JfiBankbookJournalManager jfiBankbookJournalManager;
	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}
	private SysUserManager sysUserManager;
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiDepositSendDao(JfiDepositSendDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositSendManager#getJfiDepositSends(com.joymain.jecs.fi.model.JfiDepositSend)
     */
    public List getJfiDepositSends(final JfiDepositSend jfiDepositSend) {
        return dao.getJfiDepositSends(jfiDepositSend);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositSendManager#getJfiDepositSend(String id)
     */
    public JfiDepositSend getJfiDepositSend(final String id) {
        return dao.getJfiDepositSend(new Long(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositSendManager#saveJfiDepositSend(JfiDepositSend jfiDepositSend)
     */
    public void saveJfiDepositSend(JfiDepositSend jfiDepositSend) {
        dao.saveJfiDepositSend(jfiDepositSend);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositSendManager#removeJfiDepositSend(String id)
     */
    public void removeJfiDepositSend(final String id) {
        dao.removeJfiDepositSend(new Long(id));
    }
    //added for getJfiDepositSendsByCrm
    public List getJfiDepositSendsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiDepositSendsByCrm(crm,pager);
    }

	@Override
	public void saveJfiDepositSend(List<JfiDepositSend> jfiDepositSends) {
		dao.saveJfiDepositSend(jfiDepositSends);
	}

	@Override
	public BigDecimal getSumDepositMoney(CommonRecord crm) {
		return dao.getSumDepositMoney(crm);
	}
	
	public void sendToBankbook(SysUser defSysUser,String id){

	    Date curDate=new Date();
	    JfiDepositSend jfiDepositSend=this.getJfiDepositSend(id);
	    if("1".equals(jfiDepositSend.getStatus())){
	    	jfiDepositSend.setStatus("2");
	    	jfiDepositSend.setCheckNo(defSysUser.getUserCode());
	    	jfiDepositSend.setCheckTime(curDate);
	    	
	    	//转账金额
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0] = jfiDepositSend.getDepositMoney();
			
			//资金类型
			Integer[] moneyType = new Integer[1];
			moneyType[0] =1;

	 		String[] notes = new String[1];
	 		notes[0]="保证金转电子存折";
	    	
	    	
	    	jfiDepositJournalManager.saveFiBankbookDeduct(jfiDepositSend.getCompanyCode(), jfiDepositSend.getUserCode(), moneyType, moneyArray, defSysUser.getUserCode(), "ds_"+jfiDepositSend.getId(), notes, "2");
	    	
	    	
	    	moneyType[0] =200;
	    	
	    	jfiBankbookJournalManager.saveJfiPayDataVerify(jfiDepositSend.getCompanyCode(), sysUserManager.getSysUser(jfiDepositSend.getUserCode()), moneyType,moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "ds_"+jfiDepositSend.getId(), notes,"1");

	    }
	    
	}
}
