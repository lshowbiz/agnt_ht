package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysModuleDao;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class SysModuleDaoHibernate extends BaseDaoHibernate implements SysModuleDao {
	/**
	 * @see com.joymain.jecs.sys.dao.SysModuleDao#getSysModules(com.joymain.jecs.sys.model.SysModule)
	 */
	public List getSysModules(final SysModule sysModule) {
		return getHibernateTemplate().find("from SysModule order by treeIndex");
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysModuleDao#getSysModule(Long moduleId)
	 */
	public SysModule getSysModule(final Long moduleId) {
		SysModule sysModule = (SysModule) getHibernateTemplate().get(SysModule.class, moduleId);

		return sysModule;
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysModuleDao#saveSysModule(SysModule sysModule)
	 */
	public void saveSysModule(final SysModule sysModule) {
		getHibernateTemplate().saveOrUpdate(sysModule);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysModuleDao#removeSysModule(Long moduleId)
	 */
	public void removeSysModule(final Long moduleId) {
		this.executeUpdate("delete from SysRolePower where moduleId=" + moduleId);
		this.executeUpdate("delete from SysUsrTypePow where sysModule.moduleId=" + moduleId);
		this.executeUpdate("delete from SysCompanyPow where sysModule.moduleId=" + moduleId);
		this.executeUpdate("delete from SysManagerModlPow where moduleId=" + moduleId);
		getHibernateTemplate().delete(getSysModule(moduleId));
	}

	//added for getSysModulesByCrm
	public List getSysModulesByCrm(CommonRecord crm, Pager pager) {
		String sqlQuery = "select * from (select a.*, b.module_name as parent_module_name from jsys_module a "
		        + " left join jsys_module b on a.parent_id=b.module_id where 1=1";

		String parentId = crm.getString("parentId", "");
		if (!StringUtils.isEmpty(parentId)) {
			sqlQuery += " and a.parent_Id='" + parentId + "'";
		}
		sqlQuery += ")";
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			sqlQuery += " order by order_No, module_Id desc";
		} else {
			sqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sqlQuery, pager);
	}

	/**
	 * 获取直接下级的模块列表
	 * @param moduleId
	 * @param orderField
	 * @return
	 */
	public List getDirectChildModules(final Long moduleId, final String orderField) {
		return this.getHibernateTemplate().find("from SysModule where parentId=? order by " + orderField, moduleId);
	}

	/**
	 * 根据模块编码获取对应的唯一的模块
	 * @param moduleCode
	 * @return
	 */
	public SysModule getSysModuleByCode(final String moduleCode) {
		return (SysModule) this.getObjectByHqlQuery("from SysModule where moduleCode='" + moduleCode + "'");
	}

	/**
	 * 获取所有有权限的模块
	 * @param sysUser
	 * @param moduleType
	 * @return
	 */
	public List getSysModules(SysUser sysUser, final Integer moduleType,String parentId,String startWithParentId) {
		if (sysUser == null) {
			return null;
		}
		String sqlQuery ="";
		if(!StringUtil.isEmpty(startWithParentId) && StringUtil.isInteger(startWithParentId)){
			sqlQuery += " select * from ( ";
		}
		sqlQuery += "select distinct * from (select a.* from JSYS_MODULE a ";
		if (!Constants.ROOT_ACCOUNT.equalsIgnoreCase(sysUser.getUserCode())) {
			sqlQuery += ", JSYS_USR_TYPE_POW b, JSYS_COMPANY_POW c";
			if ("A".equals(sysUser.getUserType()) || "C".equals(sysUser.getUserType())) {
				//如果是管理中心或者公司用户
				sqlQuery += ", JSYS_MANAGER_MODL_POW d ";
			} else {
				sqlQuery += ", JSYS_ROLE_POWER d, JSYS_ROLE e, JSYS_USER_ROLE f";
			}
		}
		sqlQuery += " where a.IS_ACTIVE=1 and a.is_validate=1 ";
		if (moduleType != null) {
			sqlQuery += " and a.MODULE_TYPE=" + moduleType + " ";
		}
		if (!Constants.ROOT_ACCOUNT.equalsIgnoreCase(sysUser.getUserCode())) {
			sqlQuery += " and ((a.module_id=b.module_id and b.user_type='" + sysUser.getUserType()
			        + "') and (a.module_id=c.module_id and c.company_code='" + sysUser.getCompanyCode() + "') ";
			if ("A".equals(sysUser.getUserType()) || "C".equals(sysUser.getUserType())) {
				//如果是管理中心或者公司用户
				sqlQuery += " and (a.module_id=d.module_id and d.user_code='" + sysUser.getUserCode() + "' and d.company_code='"
				        + sysUser.getCompanyCode() + "')";
			} else {
				sqlQuery += " and (a.module_id=d.module_id and d.role_id=e.role_id and e.role_id=f.role_id and e.available=1 and f.user_code='"
				        + sysUser.getUserCode() + "')";
			}
			sqlQuery += ")";
		}
		sqlQuery += " union (select x.* from jsys_module x where x.is_active=1 and x.is_validate=0";
		if (moduleType != null) {
			sqlQuery += " and x.module_type=" + moduleType + " )";
		}
		
		sqlQuery += " union (SELECT m.* FROM JSYS_MODULE m,jsys_star_role r,jsys_star_power p" +
				" where m.is_active=1 and m.is_validate=1 and r.star_role_id=p.star_role_id and m.module_id=p.module_id " +
				" and m.MODULE_TYPE=" + moduleType +
				" and r.role_code='"+sysUser.getCompanyCode()+"_"+sysUser.getHonorStar()+"')";
		
		sqlQuery += ") ";
		
		if(!StringUtil.isEmpty(parentId) && StringUtil.isInteger(parentId)){
			sqlQuery += " where parent_id= "+parentId;
		}
		sqlQuery += " order by TREE_INDEX, ORDER_NO";
		
		
		if(!StringUtil.isEmpty(startWithParentId) && StringUtil.isInteger(startWithParentId)){
			sqlQuery += "  )  Connect By Nocycle Prior module_id = parent_id Start With parent_id = "+startWithParentId;
		}
		return this.findObjectsBySQL(sqlQuery);
	}

	/**
	 * 根据用户获取对应的菜单,如果用户为空则返回所有有效的菜单
	 * @param sysUser
	 * @return
	 */
	public List getSysMenus(final SysUser sysUser,String parentId,String startWithParentId) {
		return this.getSysModules(sysUser, new Integer(1),parentId,startWithParentId);
	}

	/**
	 * 获取masterUserCode所管理的模块清单,包含slaveUserCode所管理的模块清单,其所返回的slave_power_id为两者的交集记录ID
	 * @param masterUserCode
	 * @param slaveUserCode
	 * @param companyCode
	 * @return
	 */
	public List getMyManSysModules(final String masterUserCode, final String slaveUserCode, final String companyCode) {
		if (StringUtils.isEmpty(masterUserCode)) {
			return null;
		}
		String sqlQuery = "select distinct a.*";
		if (!StringUtils.isEmpty(slaveUserCode)) {
			sqlQuery += " ,e.power_id as slave_power_id ";
		}
		sqlQuery += " from jsys_usr_type_pow b, jsys_company_pow c";
		if (!Constants.ROOT_ACCOUNT.equals(masterUserCode)) {
			sqlQuery += " ,JSYS_MANAGER_MODL_POW d";
		}
		sqlQuery += ", (select * from jsys_module where is_active=1) a ";
		if (!StringUtils.isEmpty(slaveUserCode)) {
			sqlQuery += " left join JSYS_MANAGER_MODL_POW e on e.module_id=a.module_id and e.user_code='" + slaveUserCode + "' "
			        + " and e.company_code='" + companyCode + "' ";
		}
		sqlQuery += " where a.module_id=b.module_id and b.user_type='C' " + " and a.module_id=c.module_id and c.company_code='"
		        + companyCode + "' ";
		if (!Constants.ROOT_ACCOUNT.equals(masterUserCode)) {
			sqlQuery += " and a.module_id=d.module_id and d.user_code='" + masterUserCode + "' and d.company_code='" + companyCode + "'";
		}
		sqlQuery += " order by a.tree_index, a.tree_level";
		log.info("getMyManSysModules="+sqlQuery);
		return this.findObjectsBySQL(sqlQuery);
	}

	/**
	 * 获取某个角色最多可拥有的模块,其中如果已经有相应模块的权限,则rp_id不为空
	 * @param sysRole
	 * @return
	 */
	public List getSysModulesJoinRole(final SysRole sysRole) {
		if (sysRole == null || sysRole.getCompanyCode() == null || sysRole.getUserType() == null) {
			return null;
		}
		String sqlQuery = "select a.*";
		if (sysRole != null && sysRole.getRoleId() != null) {
			sqlQuery += ", b.rp_id ";
		}
		sqlQuery += " from jsys_usr_type_pow c, jsys_company_pow d, jsys_module a ";
		if (sysRole != null && sysRole.getRoleId() != null) {
			sqlQuery += " left join jsys_role_power b on a.module_id=b.module_id and b.role_id='" + sysRole.getRoleId() + "' ";
		}
		sqlQuery += " where c.module_id=a.module_id and c.user_type='" + sysRole.getUserType() + "' and d.module_id=a.module_id "
		        + " and d.company_code='" + sysRole.getCompanyCode() + "' order by a.tree_index, a.module_id";
		return this.findObjectsBySQL(sqlQuery);
	}

	/**
	 * 重新更新模块树结构,递归
	 * @param parentLvel
	 * @param parentTreeIndex
	 * @param parentId
	 */
	private void saveSysModulesRebuild(Long parentLvel, String parentTreeIndex, Long parentId) {
		List sysModules = this.getHibernateTemplate().find("from SysModule where parentId=? order by orderNo, moduleId", parentId);
		if (sysModules != null && sysModules.size() > 0) {
			for (int i = 0; i < sysModules.size(); i++) {
				SysModule sysModule = (SysModule) sysModules.get(i);
				sysModule.setTreeLevel(parentLvel + 1);
				sysModule.setTreeIndex(parentTreeIndex + StringUtils.leftPad(Long.toString(i + 1), 2, '0'));
				this.saveSysModule(sysModule);

				this.saveSysModulesRebuild(sysModule.getTreeLevel(), sysModule.getTreeIndex(), sysModule.getModuleId());
			}
		}
	}

	/**
	 * 重新生成模块树结构
	 */
	public void saveSysModulesRebuild() {
		this.saveSysModulesRebuild(new Long(0), "", new Long(0));
	}

	/**
	 * 根据链接URL获取对应的模块列表
	 * @param linkUrl
	 * @return
	 */
	public List getSysModulesByUrl(final String linkUrl) {
		return this.getHibernateTemplate().find(
		        "from SysModule where linkUrl='" + linkUrl + "' or otherUrl1='" + linkUrl + "' or otherUrl2='" + linkUrl
		                + "' or otherUrl3='" + linkUrl + "' or otherUrl4='" + linkUrl + "' or otherUrl5='" + linkUrl + "'");
	}
	
	/**
	 * 根据模块编码和链接地址获取对应的模块
	 * @param moduleCode
	 * @param linkUrl
	 * @return
	 */
	public SysModule getSysModuleByCode(final String moduleCode,final String linkUrl) {
		return (SysModule) this.getObjectByHqlQuery("from SysModule where moduleCode='" + moduleCode + "' and (linkUrl='" + linkUrl + "' or otherUrl1='" + linkUrl + "' or otherUrl2='" + linkUrl
		                + "' or otherUrl3='" + linkUrl + "' or otherUrl4='" + linkUrl + "' or otherUrl5='" + linkUrl + "')");
	}

	/**
	 * 判断某用户对某模块是否有权限
	 * @param sysUser
	 * @param sysModule
	 * @return
	 */
	public boolean getSysUserPower(SysUser sysUser, SysModule sysModule) {
		boolean ret = false;
		if (Constants.ROOT_ACCOUNT.equals(sysUser.getUserCode())) {
			return true;
		}
		if (this.jdbcTemplate.queryForInt("select count(id) from jsys_usr_type_pow where module_id='" + sysModule.getModuleId().toString()
		        + "' and user_type='" + sysUser.getUserType() + "'") == 0) {
			return false;
		}
		if (this.jdbcTemplate.queryForInt("select count(id) from jsys_company_pow where module_id='" + sysModule.getModuleId().toString()
		        + "' and company_code='" + sysUser.getCompanyCode() + "'") == 0) {
			return false;
		}
		if (sysModule.getIsValidate() == 0) {
			//不验证
			return true;
		}
		if ("C".equals(sysUser.getUserType())) {
			StringBuffer sqlBuffer = new StringBuffer("select count(power_id) from JSYS_MANAGER_MODL_POW where user_code=? and module_id=? and company_code=?  ");
			StringBuffer paramsBuf = new StringBuffer("");
			paramsBuf.append(","+sysUser.getUserCode());
			paramsBuf.append(","+sysModule.getModuleId().toString());
			paramsBuf.append(","+sysUser.getCompanyCode());
			//返回时调用分页的查询的方法
			Object[] parameters = null;
			if(paramsBuf.toString().length()>=1){
		    	parameters = paramsBuf.toString().substring(1).split(",");
			} 
		    Integer powerId = this.jdbcTemplate.queryForInt(sqlBuffer.toString(), parameters); 
		    //Modify By WuCF 20140702 绑定变量查询 
			if (powerId == 0) {
				return false;
			}
			/*//如果是公司用户:原始代码
			if (this.jdbcTemplate.queryForInt("select count(power_id) from JSYS_MANAGER_MODL_POW where user_code='" + sysUser.getUserCode()
			        + "' and module_id='" + sysModule.getModuleId().toString() + "' and company_code='" + sysUser.getCompanyCode() + "'") == 0) {
				return false;
			}*/
		} else {
			//如果不是公司用户
			SysUserRole sysUserRole = (SysUserRole) this.getObjectByHqlQuery("from SysUserRole where userCode='" + sysUser.getUserCode()
			        + "'");
			if (sysUserRole == null) {
				ret = ret || false;
			} else {
				if (this.jdbcTemplate.queryForInt("select count(rp_id) from JSYS_ROLE_POWER where role_id='"
				        + sysUserRole.getRoleId().toString() + "' and module_id='" + sysModule.getModuleId().toString() + "'") != 0) {
					ret = ret || true;
				}
			}
			//add star
			String sql = "select count(p.sp_id) from jsys_star_power p,jsys_star_role r " +
					"where p.star_role_id=r.star_role_id  " +
					"and p.module_id="+sysModule.getModuleId()+" and r.role_code='"+sysUser.getCompanyCode()+"_"+sysUser.getHonorStar()+"'";
			if(this.jdbcTemplate.queryForInt(sql) != 0){
				ret = ret || true;
			}
			return ret;
		}
		return true;
	}

	/**
	 * 判断某用户对某模块是否有权限
	 * @param sysUser
	 * @param moduleCode
	 * @return
	 */
	public boolean getSysUserPowerByCode(SysUser sysUser, final String moduleCode) {
		SysModule sysModule = this.getSysModuleByCode(moduleCode);
		if (sysModule == null) {
			return false;
		}
		return this.getSysUserPower(sysUser, sysModule);
	}
}