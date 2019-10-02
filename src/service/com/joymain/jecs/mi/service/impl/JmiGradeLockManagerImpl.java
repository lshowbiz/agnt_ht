
package com.joymain.jecs.mi.service.impl;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.mi.dao.JmiGradeLockDao;
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiGradeLockManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiGradeLockManagerImpl extends BaseManager implements JmiGradeLockManager {
    private JmiGradeLockDao dao;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JbdSummaryListManager jbdSummaryListManager;
    public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
		this.jbdSummaryListManager = jbdSummaryListManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiGradeLockDao(JmiGradeLockDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiGradeLockManager#getJmiGradeLocks(com.joymain.jecs.mi.model.JmiGradeLock)
     */
    public List getJmiGradeLocks(final JmiGradeLock jmiGradeLock) {
        return dao.getJmiGradeLocks(jmiGradeLock);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiGradeLockManager#getJmiGradeLock(String id)
     */
    public JmiGradeLock getJmiGradeLock(final String id) {
        return dao.getJmiGradeLock(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiGradeLockManager#saveJmiGradeLock(JmiGradeLock jmiGradeLock)
     */
    public void saveJmiGradeLock(JmiGradeLock jmiGradeLock,SysUser defSysUser) {

		Date curDate=new Date();
		
		dao.saveJmiGradeLock(jmiGradeLock);
	
		
		JbdSummaryList jbdSummaryList=new JbdSummaryList();
		
		jbdSummaryList.setCreateTime(curDate);
		jbdSummaryList.setInType(12);;
		jbdSummaryList.setUserCode(jmiGradeLock.getUserCode());
		jbdSummaryList.setWweek(jmiGradeLock.getValidWeek());
		
		
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);

		JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiGradeLock.getUserCode());
		if(jmiGradeLock.getGradeType()!=0 && jmiMember.getGradeType()==0){
			jmiMember.setNotFirst(1);
			jmiMemberManager.saveJmiMember(jmiMember);
			
			BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByFormatedWeek(jmiGradeLock.getValidWeek().toString());	
			Integer startMonth = bdPeriod.getWyear()*100 + bdPeriod.getWmonth();
			String year = startMonth.toString().substring(0, 4);
			String month = startMonth.toString().substring(4, 6);
			String period = bdPeriodManager.getFutureBdYearMonth(year, month, 12);
			jmiMember.setStartWeek(startMonth);
			jmiMember.setValidWeek(Integer.parseInt(period));
			
			
			jmiMember.setCheckNo(defSysUser.getUserCode());
			jmiMember.setCheckDate(curDate);
			
			
		}
		
		
		
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiGradeLockManager#removeJmiGradeLock(String id)
     */
    public void removeJmiGradeLock(final String id) {
        dao.removeJmiGradeLock(new Long(id));
    }
    //added for getJmiGradeLocksByCrm
    public List getJmiGradeLocksByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiGradeLocksByCrm(crm,pager);
    }

	@Override
	public JmiGradeLock getJmiGradeLockByUserCode(String userCode,
			Integer validWeek) {
		return dao.getJmiGradeLockByUserCode(userCode, validWeek);
	}
	
	
	public void saveJmiGradeLockList( List<JmiGradeLock> jmiGradeLockList,SysUser defSysUser) {
		if(!MeteorUtil.isBlankByList(jmiGradeLockList)){
			for(JmiGradeLock jmiGradeLock : jmiGradeLockList){
				this.saveJmiGradeLock(jmiGradeLock, defSysUser);
			}
		}
		
	}
	
}
