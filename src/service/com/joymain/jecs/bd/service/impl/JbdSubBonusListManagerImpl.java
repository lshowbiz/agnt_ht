
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdSubBonusList;
import com.joymain.jecs.bd.dao.JbdSubBonusListDao;
import com.joymain.jecs.bd.service.JbdSubBonusListManager;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSubBonusListManagerImpl extends BaseManager implements JbdSubBonusListManager {
    private JbdSubBonusListDao dao;
    private JfiBankbookJournalManager jfiBankbookJournalManager;

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSubBonusListDao(JbdSubBonusListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSubBonusListManager#getJbdSubBonusLists(com.joymain.jecs.bd.model.JbdSubBonusList)
     */
    public List getJbdSubBonusLists(final JbdSubBonusList jbdSubBonusList) {
        return dao.getJbdSubBonusLists(jbdSubBonusList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSubBonusListManager#getJbdSubBonusList(String id)
     */
    public JbdSubBonusList getJbdSubBonusList(final String id) {
        return dao.getJbdSubBonusList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSubBonusListManager#saveJbdSubBonusList(JbdSubBonusList jbdSubBonusList)
     */
    public void saveJbdSubBonusList(JbdSubBonusList jbdSubBonusList) {
        dao.saveJbdSubBonusList(jbdSubBonusList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSubBonusListManager#removeJbdSubBonusList(String id)
     */
    public void removeJbdSubBonusList(final String id) {
        dao.removeJbdSubBonusList(new Long(id));
    }
    //added for getJbdSubBonusListsByCrm
    public List getJbdSubBonusListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSubBonusListsByCrm(crm,pager);
    }

	@Override
	public List getJbdSubBonusListsBySql(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return dao.getJbdSubBonusListsBySql(crm, pager);
	}
	

	public void saveInSubBonusFiBook(SysUser defSysUser,String id){
		JbdSubBonusList jbdSubBonusList=dao.getJbdSubBonusList(new Long(id));
		 Date curDate=new Date();
		 
		 if("1".equals(jbdSubBonusList.getStatus())){
			 jbdSubBonusList.setSendNo(defSysUser.getUserCode());
			 jbdSubBonusList.setSendTime(curDate);
			 jbdSubBonusList.setStatus("2");
			 
		  
			Integer[] moneyType = new Integer[1];
			moneyType[0]=180;
			
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0]=jbdSubBonusList.getAmount();

			String[] notes = new String[1];
			notes[0]=jbdSubBonusList.getRemark();
			
			//fiBankbookJournalManager.saveFiPayDataVerify("CN", jbdSubBonusList.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "sub_bonus_"+jbdSubBonusList.getId().toString(), notes, "1", "1");
			jfiBankbookJournalManager.saveJfiPayDataVerify(jbdSubBonusList.getJmiMember().getCompanyCode(), jbdSubBonusList.getJmiMember().getSysUser(), moneyType,moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "sub_bonus_"+jbdSubBonusList.getId().toString(), notes,"1");

		    
		 }
	}
}
