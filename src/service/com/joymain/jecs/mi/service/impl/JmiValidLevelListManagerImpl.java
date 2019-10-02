
package com.joymain.jecs.mi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.mi.dao.JmiValidLevelListDao;
import com.joymain.jecs.mi.service.JmiLevelNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiValidLevelListManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiValidLevelListManagerImpl extends BaseManager implements JmiValidLevelListManager {
    private JmiValidLevelListDao dao;
    private JmiLevelNoteManager jmiLevelNoteManager;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    public void setJmiLevelNoteManager(JmiLevelNoteManager jmiLevelNoteManager) {
		this.jmiLevelNoteManager = jmiLevelNoteManager;
	}
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    private SysRoleManager sysRoleManager;
	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}
	private SysUserRoleManager sysUserRoleManager;
	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiValidLevelListDao(JmiValidLevelListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiValidLevelListManager#getJmiValidLevelLists(com.joymain.jecs.mi.model.JmiValidLevelList)
     */
    public List getJmiValidLevelLists(final JmiValidLevelList jmiValidLevelList) {
        return dao.getJmiValidLevelLists(jmiValidLevelList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiValidLevelListManager#getJmiValidLevelList(String id)
     */
    public JmiValidLevelList getJmiValidLevelList(final String id) {
        return dao.getJmiValidLevelList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiValidLevelListManager#saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList)
     */
    public void saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList) {
        dao.saveJmiValidLevelList(jmiValidLevelList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiValidLevelListManager#removeJmiValidLevelList(String id)
     */
    public void removeJmiValidLevelList(final String id) {
        dao.removeJmiValidLevelList(new Long(id));
    }
    //added for getJmiValidLevelListsByCrm
    public List getJmiValidLevelListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiValidLevelListsByCrm(crm,pager);
    }

	
	@Override
	public void saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList,SysUser defSysUser) {
		
        JmiValidLevelList resJmiValidLevelList=this.getJmiValidLevelListsByUserCode(jmiValidLevelList.getUserCode(), new Integer(WeekFormatUtil.getFormatWeek("f",jmiValidLevelList.getWweek().toString())));
	    	
    	
    	if(resJmiValidLevelList!=null){
    		resJmiValidLevelList.setNewMemberLevel(jmiValidLevelList.getNewMemberLevel());
    		jmiValidLevelList=resJmiValidLevelList;
    	}
    	
    	
    	
    	/*Integer curOldMemberLevel=0;
    	
    	curOldMemberLevel=oldMemberLevel;*/
		//oldMemberLevel=jmiMember.getMemberLevel();
		jmiValidLevelList.setCreateNo(defSysUser.getUserCode());
		//jmiValidLevelList.setOldMemberLevel(oldMemberLevel);
		
		if (resJmiValidLevelList == null) {
			jmiValidLevelList.setWweek(new Integer(WeekFormatUtil.getFormatWeek("f", jmiValidLevelList.getWweek().toString())));
		}

		jmiValidLevelList.setIsLock("1");

		JmiMember jmiMember = jmiMemberManager.getJmiMember(jmiValidLevelList.getUserCode());
		
		if (resJmiValidLevelList != null) {
			jmiValidLevelList.setOldMemberLevel(jmiMember.getMemberLevel());
		}
		
		Integer wweek = bdPeriodManager.getBdPeriodByTimeFormated(new Date(), null);

		jmiMemberManager.changeMemberLevel(jmiMember, jmiValidLevelList.getWweek(), defSysUser, jmiValidLevelList.getIsLock(), jmiValidLevelList.getNewMemberLevel(), jmiValidLevelList.getOldMemberLevel());
    	
		
		
		this.saveJmiValidLevelList(jmiValidLevelList);;
		
	}
	@Override
	public JmiValidLevelList getJmiValidLevelListsByUserCode(String userCode,
			Integer wweek) {
		return dao.getJmiValidLevelListsByUserCode(userCode, wweek);
	}
	
	public void saveJmiValidLevelListImport(List<JmiValidLevelList> list,SysUser defSysUser){
		for (JmiValidLevelList jmiValidLevelList : list) {
			this.saveJmiValidLevelList(jmiValidLevelList, defSysUser);
		}
	}
	
}
