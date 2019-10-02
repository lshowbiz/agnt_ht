package com.joymain.jecs.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysManagerModlPowDao;
import com.joymain.jecs.sys.dao.SysManagerUserDao;
import com.joymain.jecs.sys.dao.SysModuleDao;
import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysManagerModlPowManagerImpl extends BaseManager implements SysManagerModlPowManager {
	private SysManagerModlPowDao dao;
	private SysManagerUserDao sysManagerUserDao;
	private SysModuleDao sysModuleDao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysManagerModlPowDao(SysManagerModlPowDao dao) {
		this.dao = dao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * @param sysManagerUserDao
	 */
	public void setSysManagerUserDao(SysManagerUserDao sysManagerUserDao) {
		this.sysManagerUserDao = sysManagerUserDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * @param sysModuleDao
	 */
	public void setSysModuleDao(SysModuleDao sysModuleDao) {
		this.sysModuleDao = sysModuleDao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerModlPowManager#getSysManagerModlPows(com.joymain.jecs.sys.model.SysManagerModlPow)
	 */
	public List getSysManagerModlPows(final SysManagerModlPow sysManagerModlPow) {
		return dao.getSysManagerModlPows(sysManagerModlPow);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerModlPowManager#getSysManagerModlPow(String powerId)
	 */
	public SysManagerModlPow getSysManagerModlPow(final String powerId) {
		return dao.getSysManagerModlPow(new Long(powerId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerModlPowManager#saveSysManagerModlPow(SysManagerModlPow sysManagerModlPow)
	 */
	public void saveSysManagerModlPow(SysManagerModlPow sysManagerModlPow) {
		dao.saveSysManagerModlPow(sysManagerModlPow);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerModlPowManager#removeSysManagerModlPow(String powerId)
	 */
	public void removeSysManagerModlPow(final String powerId) {
		dao.removeSysManagerModlPow(new Long(powerId));
	}

	//added for getSysManagerModlPowsByCrm
	public List getSysManagerModlPowsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysManagerModlPowsByCrm(crm, pager);
	}

	/**
	 * 删除用户指定的模块以外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
	 * @param operatorCode
	 * @param userCode
	 * @param companyCode
	 * @param moduleIds
	 */
	public void removeSysManagerModlPowsByIds(final String operatorCode, final String userCode, final String companyCode,
	        final String[] moduleIds) {
		dao.removeSysManagerModlPowsByIds(operatorCode, userCode, companyCode, moduleIds);
	}

	/**
	 * 获取用户指定模块所对应的权限
	 * @param userCode
	 * @param companyCode
	 * @param moduleId
	 * @return
	 */
	public SysManagerModlPow getSysManagerModlPow(final String userCode, final String companyCode, final String moduleId) {
		return dao.getSysManagerModlPow(userCode, companyCode, new Long(moduleId));
	}
	
	/**
	 * Add By WuCF 20140507通过会员编号和仓库编码得到数据
	 * 获取用户指定模块所对应的权限
	 * @param userCode
	 * @param warehouseNo
	 * @return
	 */
	public PdWarehouseUser getPdWarehouseUser(String userCode,String warehouseNo) {
		return dao.getPdWarehouseUser(userCode,warehouseNo);
	}

	/**
	 * 批量保存多条记录
	 * @param sysManagerModlPows
	 */
	public void saveSysManagerModlPows(List sysManagerModlPows) {
		dao.saveSysManagerModlPows(sysManagerModlPows);
	}
	
	/**
	 * Add By WuCF 20140507
	 * 批量保存多个仓库权限数据
	 * @param sysManagerModlPows
	 */
	public void savePdWarehouseUserPows(List pdWarehouseUsers) {
		dao.savePdWarehouseUserPows(pdWarehouseUsers);
	}
	
	/**
	 * 删除一定用户范围内, 对应模块所指定的用外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
	 * @param manageredUserCodes
	 * @param userCodes
	 * @param companyCode
	 * @param moduleId
	 */
	public void removeSysManagerModlPowsByUserCodes(final String[] manageredUserCodes, final String[] userCodes, final String companyCode,
	        final String moduleId) {
		dao.removeSysManagerModlPowsByUserCodes(manageredUserCodes, userCodes, companyCode, moduleId);
	}
	
	/**
	 * Add By WuCF 20140507
	 * 删除一定用户范围内，对应仓库的指定的用外的所有权限。
	 * @param manageredUserCodes
	 * @param userCodes
	 * @param companyCode
	 * @param warehouseNo
	 */
	public void removePdWarehousePowsByUserCodes(final String[] manageredUserCodes, final String[] userCodes, final String companyCode,
	        final String warehouseNo) {
		dao.removePdWarehousePowsByUserCodes(manageredUserCodes, userCodes, companyCode, warehouseNo);
	}

	/**
	 * 删除用户在角色以外模块的所有权限
	 * @param userCode
	 * @param companyCode
	 * @param sysRole
	 */
	public void removeSysManagerModlPowsByRole(final String userCode, final String companyCode, final SysRole sysRole) {
		dao.removeSysManagerModlPowsByRole(userCode, companyCode, sysRole);
	}

	/**
	 * 授权主代码
	 * @param operatorCode
	 * @param masterUserCode
	 * @param selectedModule
	 * @param selectedSalveUserCode
	 * @param companyCode
	 */
	public void saveSysManagerModlPowsAssigned(final String operatorCode, final String masterUserCode, final String selectedModule,
	        final String selectedSalveUserCode, String companyCode) {
		if (!StringUtils.isEmpty(masterUserCode)) {
			/*---- 保存用户权限 ---- */
			this.saveSysManagerPowers(operatorCode, masterUserCode, selectedModule, companyCode);
			/*---- 保存所管理的用户关系 ---- */
			this.saveSysManagerUsers(masterUserCode, selectedModule, selectedSalveUserCode, companyCode);
		}
	}

	/**
	 * 保存用户权限
	 * @param operatorCode
	 * @param masterUserCode
	 * @param selectedModule
	 * @param companyCode
	 */
	public void saveSysManagerPowers(final String operatorCode, final String masterUserCode, final String selectedModule, String companyCode) {
		if (!StringUtils.isEmpty(masterUserCode)) {
			//清除不在所选权限范围内的权限
			List<String> moduleIdList = new ArrayList<String>();
			if (!StringUtils.isEmpty(selectedModule)) {
				String[] selectedModules = selectedModule.split(",");
				for (int i = 0; i < selectedModules.length; i++) {
					if (!StringUtils.isEmpty(selectedModules[i]) && !"A".equals(selectedModules[i])) {
						moduleIdList.add(selectedModules[i]);
					}
				}
			}
			String[] moduleIds = (String[]) moduleIdList.toArray(new String[moduleIdList.size()]);
			this.removeSysManagerModlPowsByIds(operatorCode, masterUserCode, companyCode, moduleIds);
			//重新设置
			if (moduleIds != null && moduleIds.length > 0) {
				List<SysManagerModlPow> sysManagerModlPows = new ArrayList<SysManagerModlPow>();
				for (int i = 0; i < moduleIds.length; i++) {
					SysManagerModlPow sysManagerModlPow = this.getSysManagerModlPow(masterUserCode, companyCode, moduleIds[i]);
					if (sysManagerModlPow == null) {
						sysManagerModlPow = new SysManagerModlPow();
						sysManagerModlPow.setModuleId(new Long(moduleIds[i]));
						sysManagerModlPow.setUserCode(masterUserCode);
						sysManagerModlPow.setCompanyCode(companyCode);
						sysManagerModlPows.add(sysManagerModlPow);
					}
				}
				this.saveSysManagerModlPows(sysManagerModlPows);
			}
		}
	}

	/**
	 * 保存所管理的用户关系
	 * @param masterUserCode
	 * @param selectedModule
	 * @param selectedSalveUserCode
	 * @param companyCode
	 */
	public void saveSysManagerUsers(final String masterUserCode, final String selectedModule, final String selectedSalveUserCode,
	        final String companyCode) {
		if (!StringUtils.isEmpty(masterUserCode)) {
			//清除不在所选范围内的对应关系
			String[] selectedSalveUserCodes = selectedSalveUserCode.split(",");
			List<String> slaveUserCodeList = new ArrayList<String>();
			if (selectedSalveUserCodes != null && selectedSalveUserCodes.length > 0) {
				for (int i = 0; i < selectedSalveUserCodes.length; i++) {
					if (!StringUtils.isEmpty(selectedSalveUserCodes[i])) {
						String[] tmpStr = selectedSalveUserCodes[i].split("#");
						if (!StringUtils.isEmpty(tmpStr[1]) && "M".equals(tmpStr[0])) {
							slaveUserCodeList.add(tmpStr[1]);
						}
					}
				}
			}
			String[] slaveUserCodes = (String[]) slaveUserCodeList.toArray(new String[slaveUserCodeList.size()]);
			this.sysManagerUserDao.removeSysManagerUsersBySlaveCodes(masterUserCode, companyCode, slaveUserCodes);
			if (slaveUserCodes != null && slaveUserCodes.length > 0) {
				List<SysManagerUser> sysManagerUsers = new ArrayList<SysManagerUser>();
				for (int i = 0; i < slaveUserCodes.length; i++) {
					SysManagerUser sysManagerUser = this.sysManagerUserDao.getSysManagerUser(masterUserCode, slaveUserCodes[i]);
					if (sysManagerUser == null) {
						sysManagerUser = new SysManagerUser();
						sysManagerUser.setMasterUserCode(masterUserCode);
						sysManagerUser.setSlaveUserCode(slaveUserCodes[i]);
						sysManagerUsers.add(sysManagerUser);
					}
				}
				this.sysManagerUserDao.saveSysManagerUsers(sysManagerUsers);
			}
		}
	}

	/**
	 * 按模块授权
	 * @param currentSysUser
	 * @param selectedUserCode
	 * @param moduleId
	 * @param companyCode
	 */
	public void saveSysManagerModlPowsByModule(final SysUser currentSysUser, final String selectedUserCode, final String moduleId,
	        final String companyCode) {
		//清除不在所选权限范围内的权限
		List<String> userCodeList = new ArrayList<String>();
		if (!StringUtils.isEmpty(selectedUserCode)) {
			String[] selectedUserCodes = selectedUserCode.split(",");
			for (int i = 0; i < selectedUserCodes.length; i++) {
				if (!StringUtils.isEmpty(selectedUserCodes[i])) {
					String[] tmpStr = selectedUserCodes[i].split("#");
					if (!StringUtils.isEmpty(tmpStr[1])) {
						userCodeList.add(tmpStr[1]);
					}
				}
			}
		}
		String[] userCodes = (String[]) userCodeList.toArray(new String[userCodeList.size()]);

		//读取所管理的人员信息
		List sysManagers = this.sysManagerUserDao.getSysManagersWithPowerJoin(currentSysUser, moduleId, companyCode, true);
		if (sysManagers != null) {
			String[] manageredUserCodes = new String[sysManagers.size()];
			for (int i = 0; i < sysManagers.size(); i++) {
				HashMap tempMap = (HashMap) sysManagers.get(i);
				manageredUserCodes[i] = tempMap.get("user_code").toString();
			}
			this.removeSysManagerModlPowsByUserCodes(manageredUserCodes, userCodes, companyCode, moduleId);
			//重新设置
			if (userCodes != null && userCodes.length > 0) {
				List<SysManagerModlPow> sysManagerModlPows = new ArrayList<SysManagerModlPow>();
				for (int i = 0; i < userCodes.length; i++) {
					SysManagerModlPow sysManagerModlPow = this.getSysManagerModlPow(userCodes[i], companyCode, moduleId);
					if (sysManagerModlPow == null) {
						sysManagerModlPow = new SysManagerModlPow();
						sysManagerModlPow.setModuleId(new Long(moduleId));
						sysManagerModlPow.setUserCode(userCodes[i]);
						sysManagerModlPow.setCompanyCode(companyCode);
						sysManagerModlPows.add(sysManagerModlPow);
					}
				}
				this.saveSysManagerModlPows(sysManagerModlPows);
			}
		}
	}
	
	/**
	 * Add By WuCF 20140507
	 * 仓库批量授权
	 * @param currentSysUser：当前登录用户
	 * @param selectedUserCode：选择的用户数据
	 * @param warehouseNo：仓库编码
	 * @param companyCode：分公司编码
	 */
	public void savePdWarehousePowsByModule(final SysUser currentSysUser, final String selectedUserCode, final String warehouseNo,
	        final String companyCode) {
		//清除不在所选权限范围内的权限
		List<String> userCodeList = new ArrayList<String>();
		if (!StringUtils.isEmpty(selectedUserCode)) {
			String[] selectedUserCodes = selectedUserCode.split(",");
			for (int i = 0; i < selectedUserCodes.length; i++) {
				if (!StringUtils.isEmpty(selectedUserCodes[i])) {
					String[] tmpStr = selectedUserCodes[i].split("#");
					if (!StringUtils.isEmpty(tmpStr[1])) {
						userCodeList.add(tmpStr[1]);
					}
				}
			}
		}
		String[] userCodes = (String[]) userCodeList.toArray(new String[userCodeList.size()]);

		//读取所管理的人员信息
		List sysManagers = this.sysManagerUserDao.getPdWarehouseWithPowerJoin(currentSysUser, warehouseNo, companyCode, true);
		if (sysManagers != null) {
			String[] manageredUserCodes = new String[sysManagers.size()];
			for (int i = 0; i < sysManagers.size(); i++) {
				HashMap tempMap = (HashMap) sysManagers.get(i);
				manageredUserCodes[i] = tempMap.get("user_code").toString();
			}
			this.removePdWarehousePowsByUserCodes(manageredUserCodes, userCodes, companyCode, warehouseNo);
			//重新设置
			if (userCodes != null && userCodes.length > 0) {
				List<PdWarehouseUser> pdWarehouseUsers = new ArrayList<PdWarehouseUser>();
				for (int i = 0; i < userCodes.length; i++) {
					PdWarehouseUser pdWarehouseUser = this.getPdWarehouseUser(userCodes[i],warehouseNo);
					if (pdWarehouseUser == null) {
						pdWarehouseUser = new PdWarehouseUser();
						pdWarehouseUser.setWarehouseNo(warehouseNo);
						pdWarehouseUser.setUserCode(userCodes[i]);
						pdWarehouseUsers.add(pdWarehouseUser);
					}
				}
				this.savePdWarehouseUserPows(pdWarehouseUsers);
			}
		}
	}
	
	/**
	 * 保存用户受权仓库
	 * @param userCode
	 * @param selectedWarehouseCode
	 */
	public void savePdWarehousePowsByUser(String userCode,String selectedWarehouseCode){
		dao.removePdWarehousePowsByUserCode(userCode);
		if(!StringUtils.isEmpty(selectedWarehouseCode)){
			String[] warehouseCodeArray = selectedWarehouseCode.split(",");
			List<PdWarehouseUser> pdWarehouseUsers = new ArrayList<PdWarehouseUser>();
			for (int i = 0; i < warehouseCodeArray.length; i++) {
				PdWarehouseUser pdWarehouseUser = new PdWarehouseUser();
				pdWarehouseUser.setWarehouseNo(warehouseCodeArray[i]);
				pdWarehouseUser.setUserCode(userCode);
				pdWarehouseUsers.add(pdWarehouseUser);
			}
			this.savePdWarehouseUserPows(pdWarehouseUsers);
		}
	}

	/**
	 * 复制某用户在某些公司的权限至其它用户
	 * @param fromUser
	 * @param fromUserCompanys
	 * @param toUsers
	 */
	public void saveSysPowerCloneByUser(final String fromUser, final String[] fromUserCompanys, final String[] toUsers) {
		if (!StringUtils.isEmpty(fromUser) && fromUserCompanys != null && toUsers != null) {
			for (int i = 0; i < fromUserCompanys.length; i++) {
				List sysModules = this.sysModuleDao.getMyManSysModules(fromUser, null, fromUserCompanys[i]);
				if (sysModules != null && sysModules.size() > 0) {
					for (int j = 0; j < sysModules.size(); j++) {
						Map sysModuleMap = (Map) sysModules.get(j);
						for (int k = 0; k < toUsers.length; k++) {
							SysManagerModlPow sysManagerModlPow = this.getSysManagerModlPow(toUsers[k], fromUserCompanys[i], sysModuleMap
							        .get("module_id").toString());
							if (sysManagerModlPow == null) {
								sysManagerModlPow = new SysManagerModlPow();
								sysManagerModlPow.setModuleId(new Long(sysModuleMap.get("module_id").toString()));
								sysManagerModlPow.setUserCode(toUsers[k]);
								sysManagerModlPow.setCompanyCode(fromUserCompanys[i]);

								this.saveSysManagerModlPow(sysManagerModlPow);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 复制某用户在某公司的权限至其它公司
	 * @param fromUser
	 * @param fromUserCompany
	 * @param toUserCompanys
	 */
	public void saveSysPowerCloneByCompany(final String fromUser, final String fromUserCompany, final String[] toUserCompanys) {
		if (!StringUtils.isEmpty(fromUser) && fromUserCompany != null && toUserCompanys != null) {
			//查出在所选公司内所拥有的权限
			List sysModules = this.sysModuleDao.getMyManSysModules(fromUser, null, fromUserCompany);
			if (sysModules != null && sysModules.size() > 0) {
				for (int j = 0; j < sysModules.size(); j++) {
					Map sysModuleMap = (Map) sysModules.get(j);
					for (int k = 0; k < toUserCompanys.length; k++) {
						//获取在目的公司是否有权限
						SysManagerModlPow sysManagerModlPow = this.getSysManagerModlPow(fromUser, toUserCompanys[k], sysModuleMap.get(
						        "module_id").toString());
						if (sysManagerModlPow == null) {
							sysManagerModlPow = new SysManagerModlPow();
							sysManagerModlPow.setModuleId(new Long(sysModuleMap.get("module_id").toString()));
							sysManagerModlPow.setUserCode(fromUser);
							sysManagerModlPow.setCompanyCode(toUserCompanys[k]);

							this.saveSysManagerModlPow(sysManagerModlPow);
						}
					}
				}
			}
		}
	}
}