
package com.joymain.jecs.mi.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.dao.JmiSubStoreDao;
import com.joymain.jecs.mi.service.JmiSubStoreManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class JmiSubStoreManagerImpl extends BaseManager implements JmiSubStoreManager {
    private JmiSubStoreDao dao;
    private SysUserRoleManager sysUserRoleManager;
    private SysRoleManager sysRoleManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiSubStoreDao(JmiSubStoreDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSubStoreManager#getJmiSubStores(com.joymain.jecs.mi.model.JmiSubStore)
     */
    public List getJmiSubStores(final JmiSubStore jmiSubStore) {
        return dao.getJmiSubStores(jmiSubStore);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSubStoreManager#getJmiSubStore(String id)
     */
    public JmiSubStore getJmiSubStore(final String id) {
        return dao.getJmiSubStore(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSubStoreManager#saveJmiSubStore(JmiSubStore jmiSubStore)
     */
    public void saveJmiSubStore(JmiSubStore jmiSubStore) {
        dao.saveJmiSubStore(jmiSubStore);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiSubStoreManager#removeJmiSubStore(String id)
     */
    public void removeJmiSubStore(final String id) {
        dao.removeJmiSubStore(new Long(id));
    }
    //added for getJmiSubStoresByCrm
    public List getJmiSubStoresByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiSubStoresByCrm(crm,pager);
    }

    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param checkStatus 1审核成功，3不成功
     */
	public void checkJmiSubStore(String[] strCodes,SysUser sysUser,String checkStatus,String checkRemark) {
    	for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JmiSubStore jmiSubStore=dao.getJmiSubStore(new Long(strCodes[i]));
				
				if(!"0".equals(jmiSubStore.getJmiMember().getSubStoreStatus())){
					throw new AppException("bdSendRegister.operaterFail");
				}
				if("1".equals(checkStatus)){
					jmiSubStore.setCheckTime(new Date());
					jmiSubStore.setCheckUser(sysUser.getUserCode());
				}
				jmiSubStore.getJmiMember().setSubStoreStatus(checkStatus);
				jmiSubStore.setCheckRemark(checkRemark);
				dao.saveJmiSubStore(jmiSubStore);
			}
		}
	}
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param confirmStatus 1 未确认转确认 2 未确认转不确认
     */
	public void confirmJmiSubStore(String[] strCodes, SysUser sysUser,String confirmStatus,String confirmRemark) {
		for (int i = 0; i < strCodes.length; i++) {
			if (!StringUtils.isEmpty(strCodes[i])) {
				JmiSubStore jmiSubStore=dao.getJmiSubStore(new Long(strCodes[i]));
				if(!"1".equals(jmiSubStore.getJmiMember().getSubStoreStatus())||"1".equals(jmiSubStore.getConfirmStatus())){
					throw new AppException("bdSendRegister.operaterFail");
				}
				if("1".equals(confirmStatus)){
					jmiSubStore.setConfirmUser(sysUser.getUserCode());
					jmiSubStore.setConfirmTime(new Date());
					
					
				}else if("2".equals(confirmStatus)){
					jmiSubStore.getJmiMember().setSubStoreStatus("0");
				}
				jmiSubStore.setConfirmRemark(confirmRemark);
				jmiSubStore.setConfirmStatus(confirmStatus);
				dao.saveJmiSubStore(jmiSubStore);
			}
		}
	}

	public void saveJmiSubStoreCheck(JmiSubStore jmiSubStore,JmiMember jmiMember) {

		Date curDate=new Date();
		jmiSubStore.setBusinessLicese("0");
		jmiSubStore.setStorePic("0");
		jmiSubStore.setContract("0");
		jmiSubStore.setAddrConfirm("0");
		jmiSubStore.setAddrCheck("0");
		jmiSubStore.getJmiMember().setSubStoreStatus("1");
		jmiSubStore.setConfirmStatus("2");
		jmiSubStore.setCreateTime(curDate);
		jmiSubStore.setEditTime(curDate);
		jmiSubStore.setCheckTime(curDate);
		jmiSubStore.setCheckUser(jmiMember.getUserCode());
		

		jmiSubStore.setConfirmUser(jmiMember.getUserCode());
		jmiSubStore.setConfirmTime(curDate);
		

		SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiSubStore.getJmiMember().getUserCode());

		String memberRoleId =(String) Constants.sysConfigMap.get(jmiSubStore.getJmiMember().getCompanyCode()).get("member_role_id.store1");
//		if(getCheckIsBuySeat(jmiMember.getSysUser(), jmiMember)){
//			memberRoleId=(String) Constants.sysConfigMap.get(jmiSubStore.getJmiMember().getCompanyCode()).get("member_role_id.store2");;
//			jmiMember.setIsstore("2");
//			jmiSubStore.getJmiMember().setSubStoreStatus("2");
//			
//		}
		
		

		SysRole memberSysRole=sysRoleManager.getSysRoleByCode(memberRoleId);
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		sysUserRoleManager.saveSysUserRole(sysUserRole);
		jmiSubStore.setConfirmStatus("1");
		
		this.saveJmiSubStore(jmiSubStore);
		
	}

	public JmiSubStore getJmiSubStoresByUserCode(String userCode) {
		return dao.getJmiSubStoresByUserCode(userCode);
	}
	
	
	/**
     * 
     * @param strCodes
     * @param sysUser
     * @param checkStatus 1审核成功，3不成功
     */
	public void addrCheckJmiSubStore(String id,SysUser sysUser,String checkStatus) {

				JmiSubStore jmiSubStore=dao.getJmiSubStore(new Long(id));
				
				if(!"0".equals(jmiSubStore.getAddrCheck())){
					throw new AppException("bdSendRegister.operaterFail");
				}
				if("1".equals(checkStatus)){
					jmiSubStore.setAddrCheckUser(sysUser.getUserCode());
					jmiSubStore.setAddrCheckTime(new Date());
				}
				jmiSubStore.setAddrCheck(checkStatus);
				dao.saveJmiSubStore(jmiSubStore);
	}
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param confirmStatus 1 未确认转确认 2 未确认转不确认
     */
	public void addrConfirmJmiSubStore( String id, SysUser sysUser,String confirmStatus) {
				JmiSubStore jmiSubStore=dao.getJmiSubStore(new Long(id));
				if(!"1".equals(jmiSubStore.getAddrCheck())||"1".equals(jmiSubStore.getAddrConfirm())){
					throw new AppException("bdSendRegister.operaterFail");
				}
				if("1".equals(confirmStatus)){
					
					jmiSubStore.setAddrConfirmUser(sysUser.getUserCode());
					jmiSubStore.setAddrConfirmTime(new Date());
					
				}else if("2".equals(confirmStatus)){
					//jmiSubStore.getJmiMember().setSubStoreStatus("0");
				}
				jmiSubStore.setAddrConfirm(confirmStatus);
				dao.saveJmiSubStore(jmiSubStore);
	}

/*	public boolean getCheckIsBuySeat(SysUser defSysUser,JmiMember jmiMember) {
		if("M".equals(defSysUser.getUserType())){
    		
    		JpoMemberOrder jpoMemberOrder=new JpoMemberOrder();
    		jpoMemberOrder.setSysUser(jmiMember.getSysUser());
    		jpoMemberOrder.setStatus("2");
    		jpoMemberOrder.setOrderType("1");
    		
    		List list=jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
    		
    		if(list.size()!=1){
    			 return false;
    		}else{
    			JpoMemberOrder curJpoMemberOrder=(JpoMemberOrder) list.get(0);
    			 Iterator ite = curJpoMemberOrder.getJpoMemberOrderList().iterator();
     			while(ite.hasNext()){
     				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) ite.next();
     	    		if("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
     	    			return true;
     	    		}
     			}
     		
    		}
    		
    	}
    	return true;
	}*/
}
