
package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.model.JbdUserCompanyCode;
import com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao;
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.bd.service.JbdUserCompanyCodeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class JbdUserCompanyCodeManagerImpl extends BaseManager implements JbdUserCompanyCodeManager {
    private JbdUserCompanyCodeDao dao;
    private JmiMemberManager jmiMemberManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    private AlCompanyManager alCompanyManager;
    private SysRoleManager sysRoleManager;
    private SysUserRoleManager sysUserRoleManager;
    private JmiMemberCompanyNoteManager jmiMemberCompanyNoteManager;
    private JbdBonusBalanceManager jbdBonusBalanceManager;
    private JbdSummaryListManager jbdSummaryListManager;
	public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
		this.jbdSummaryListManager = jbdSummaryListManager;
	}

    public void setJmiMemberCompanyNoteManager(
			JmiMemberCompanyNoteManager jmiMemberCompanyNoteManager) {
		this.jmiMemberCompanyNoteManager = jmiMemberCompanyNoteManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdUserCompanyCodeDao(JbdUserCompanyCodeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCompanyCodeManager#getJbdUserCompanyCodes(com.joymain.jecs.bd.model.JbdUserCompanyCode)
     */
    public List getJbdUserCompanyCodes(final JbdUserCompanyCode jbdUserCompanyCode) {
        return dao.getJbdUserCompanyCodes(jbdUserCompanyCode);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCompanyCodeManager#getJbdUserCompanyCode(String id)
     */
    public JbdUserCompanyCode getJbdUserCompanyCode(final String id) {
        return dao.getJbdUserCompanyCode(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCompanyCodeManager#saveJbdUserCompanyCode(JbdUserCompanyCode jbdUserCompanyCode)
     */
    public void saveJbdUserCompanyCode(JbdUserCompanyCode jbdUserCompanyCode) {
        dao.saveJbdUserCompanyCode(jbdUserCompanyCode);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserCompanyCodeManager#removeJbdUserCompanyCode(String id)
     */
    public void removeJbdUserCompanyCode(final String id) {
        dao.removeJbdUserCompanyCode(new Long(id));
    }
    //added for getJbdUserCompanyCodesByCrm
    public List getJbdUserCompanyCodesByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdUserCompanyCodesByCrm(crm,pager);
    }

	public void changeCompanyCode(String userCode, Integer wweek, String newCompanyCode, String updateType,HttpServletRequest request,SysUser defSysUser) {
		//获取当前期
		JbdUserCompanyCode curJbdUserCompanyCode=dao.getJbdUserCompanyCodeByUserCodeAndWeek(userCode, wweek);
		//获取前一期
		JbdUserCompanyCode previousJbdUserCompanyCode=dao.getPreviousJbdUserCompanyCode(userCode, wweek);
		//获取当前会员
		JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
		String oldCompanyCode="";
		if(previousJbdUserCompanyCode==null){
			oldCompanyCode=jmiMember.getCompanyCode();
		}else{
			oldCompanyCode=previousJbdUserCompanyCode.getNewCompany();
		}
		
		//更新记录
		JbdUserCompanyCode jbdUserCompanyCode=null;
		if(curJbdUserCompanyCode!=null){
			jbdUserCompanyCode=curJbdUserCompanyCode;
		}else{
			jbdUserCompanyCode=new JbdUserCompanyCode();
			jbdUserCompanyCode.setUpdateType("1");
			jbdUserCompanyCode.setUserCode(userCode);
			jbdUserCompanyCode.setWweek(wweek);
		}
		jbdUserCompanyCode.setOldCompany(oldCompanyCode);
		jbdUserCompanyCode.setNewCompany(newCompanyCode);
		
		this.saveJbdUserCompanyCode(jbdUserCompanyCode);
		
		
		
		//删除当前期别后的记录
		List<JbdUserCompanyCode> nextJbdUserCompanyCodeList=dao.getNextJbdUserCompanyCode(userCode, wweek);
		for (JbdUserCompanyCode code : nextJbdUserCompanyCodeList) {
			dao.removeJbdUserCompanyCode(code.getId());
		}
		
		//更新国别时，同时更新角色
		
		//判断会员是否存待审订单
		
		CommonRecord crm=new CommonRecord();

		Pager pager = new Pager("jfiBankbookJournalListTable", request, 0);
		crm.addField("sysUser.userCode", jmiMember.getUserCode());
		crm.addField("status", "1");
		List list=jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm, pager);
		if(!list.isEmpty()){
			throw new AppException("not.audit.exsit");
		}
		//
		jmiMember.getSysUser().setCompanyCode(newCompanyCode);
		//更新contrycode
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(newCompanyCode);
		jmiMember.setCountryCode(alCompany.getCountryCode());
		
		String memberRoleId =(String) Constants.sysConfigMap.get(newCompanyCode).get("member_role_id." + jmiMember.getCardType());

		
		SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
		SysUserRole sysUserRole=sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
		if(sysUserRole==null){
			sysUserRole=new SysUserRole();
		}
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		sysUserRole.setUserCode(jmiMember.getUserCode());
		sysUserRoleManager.saveSysUserRole(sysUserRole);
		
		jmiMember.setCompanyCode(newCompanyCode);
		jmiMemberManager.saveJmiMember(jmiMember);
		Date curDate=new Date();
		//记录日志
		JmiMemberCompanyNote jmiMemberCompanyNote=new JmiMemberCompanyNote();
		jmiMemberCompanyNote.setCreateTime(curDate);
		jmiMemberCompanyNote.setCreateUser(defSysUser.getUserCode());
		jmiMemberCompanyNote.setNewCompany(newCompanyCode);
		jmiMemberCompanyNote.setOldCompany(oldCompanyCode);
		jmiMemberCompanyNote.setUpdateType("1");
		jmiMemberCompanyNote.setUserCode(userCode);
		jmiMemberCompanyNoteManager.saveJmiMemberCompanyNote(jmiMemberCompanyNote);
		
		

		//插入每日计算表
		JbdSummaryList jbdSummaryList=new JbdSummaryList();
		jbdSummaryList.setUserCode(jmiMember.getUserCode());
		jbdSummaryList.setInType(9);
		jbdSummaryList.setCreateTime(curDate);
		jbdSummaryList.setNewCompanyCode(newCompanyCode);
		jbdSummaryList.setWweek(wweek);
		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
		//
		
		
		//转到台湾时，待审
		if("TW".equals(newCompanyCode)){
			JbdBonusBalance jbdBonusBalance=jbdBonusBalanceManager.getJbdBonusBalance(userCode);
			jbdBonusBalance.setStatus("1");
			jbdBonusBalanceManager.saveJbdBonusBalance(jbdBonusBalance);
		}
		//
		
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	public void setJbdBonusBalanceManager(
			JbdBonusBalanceManager jbdBonusBalanceManager) {
		this.jbdBonusBalanceManager = jbdBonusBalanceManager;
	}
}
