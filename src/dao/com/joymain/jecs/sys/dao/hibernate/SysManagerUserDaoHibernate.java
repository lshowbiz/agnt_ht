package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysManagerUserDao;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysManagerUserDaoHibernate extends BaseDaoHibernate implements SysManagerUserDao {
	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerUserDao#getSysManagerUsers(com.joymain.jecs.sys.model.SysManagerUser)
	 */
	public List getSysManagerUsers(final SysManagerUser sysManagerUser) {
		return getHibernateTemplate().find("from SysManagerUser");
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerUserDao#getSysManagerUser(Long rollId)
	 */
	public SysManagerUser getSysManagerUser(final Long rollId) {
		SysManagerUser sysManagerUser = (SysManagerUser) getHibernateTemplate().get(SysManagerUser.class, rollId);
		if (sysManagerUser == null) {
			log.warn("uh oh, sysManagerUser with rollId '" + rollId + "' not found...");
			throw new ObjectRetrievalFailureException(SysManagerUser.class, rollId);
		}

		return sysManagerUser;
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerUserDao#saveSysManagerUser(SysManagerUser sysManagerUser)
	 */
	public void saveSysManagerUser(final SysManagerUser sysManagerUser) {
		getHibernateTemplate().saveOrUpdate(sysManagerUser);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerUserDao#removeSysManagerUser(Long rollId)
	 */
	public void removeSysManagerUser(final Long rollId) {
		getHibernateTemplate().delete(getSysManagerUser(rollId));
	}

	//added for getSysManagerUsersByCrm
	public List getSysManagerUsersByCrm(CommonRecord crm, Pager pager) {
		String hql = "from SysManagerUser sysManagerUser where 1=1";
		/** * ����Լ��Ĳ�ѯ���������** */
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by rollId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	/**
	 * 获取masterUserCode所管理的用户清单,包含slaveUserCode所管理的用户清单,其所返回的slave_roll_id为两者的交集记录ID
	 * @param masterSysUser
	 * @param slaveUserCode
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getSysManagersWithJoin(final SysUser masterSysUser, final String slaveUserCode, final String companyCode, boolean limitCompany) {
		if (masterSysUser==null || StringUtils.isEmpty(masterSysUser.getUserCode())) {
			return null;
		}
		String sqlQuery = "select a.user_name, b.department_id, b.user_code";
		if (!StringUtils.isEmpty(slaveUserCode)) {
			sqlQuery += ", c.roll_id as slave_roll_id ";
		}
		sqlQuery += " from jsys_user a, jsys_manager b ";
		if (!StringUtils.isEmpty(slaveUserCode)) {
			sqlQuery += " left join jsys_manager_user c on c.master_user_code='" + slaveUserCode + "' and c.slave_user_code=b.user_code ";
		}
		sqlQuery += " where a.user_code=b.user_code and a.user_code!='" + Constants.ROOT_ACCOUNT + "' and a.state=1 ";
		if (!StringUtils.isEmpty(slaveUserCode)) {
			sqlQuery+=" and a.user_code!='"+slaveUserCode+"'";
		}
		
		if (!Constants.ROOT_ACCOUNT.equals(masterSysUser.getUserCode())) {
			if (!StringUtils.isEmpty(companyCode) && (limitCompany || !"AA".equals(masterSysUser.getCompanyCode()))){
				sqlQuery+=" and a.company_code='"+companyCode+"'";
			}
			sqlQuery += " and a.user_code in (select d.slave_user_code from jsys_manager_user d where d.master_user_code='" + masterSysUser.getUserCode() + "')";
		}
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
     * 删除用户指定的被管理用户以外的所有记录
     * @param masterUserCode
     * @param slaveUserCodes
     */
    public void removeSysManagerUsersBySlaveCodes(final String masterUserCode, final String companyCode, final String[] slaveUserCodes){
    	String hqlQuery="delete from SysManagerUser where masterUserCode='"+masterUserCode+"'";
    	if(!StringUtils.isEmpty(companyCode)){
    		hqlQuery+=" and slaveUserCode in (select c.userCode from SysManager c where c.companyCode='"+companyCode+"')";
    	}
    	if(slaveUserCodes!=null && slaveUserCodes.length>0){
    		hqlQuery+=" and slaveUserCode not in (";
    		for(int i=0;i<slaveUserCodes.length;i++){
    			if(i>0){
    				hqlQuery+=",";
    			}
    			hqlQuery+="'"+slaveUserCodes[i]+"'";
    		}
    		hqlQuery+=")";
    	}
    	this.executeUpdate(hqlQuery);
    }
    
    /**
     * 获取用户与被管理用户所对应的记录
     * @param masterUserCode
     * @param slaveUserCode
     * @return
     */
    public SysManagerUser getSysManagerUser(final String masterUserCode, final String slaveUserCode) {
    	return  (SysManagerUser)this.getObjectByHqlQuery("from SysManagerUser where masterUserCode='"+masterUserCode+"' and slaveUserCode='"+slaveUserCode+"'");
    }
    
    /**
     * 批量保存多条记录
     * @param sysManagerModlPows
     */
    public void saveSysManagerUsers(List sysManagerUsers){
    	this.getHibernateTemplate().saveOrUpdateAll(sysManagerUsers);
    }
    
    /**
	 * 获取masterUserCode所管理的用户清单,包含被管理用户对指定模块是否拥有权限的信息,其所返回的power_id不为空则代表用户有权限
	 * @param masterSysUser
	 * @param moduleId
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getSysManagersWithPowerJoin(final SysUser masterSysUser, final String moduleId, final String companyCode, boolean limitCompany) {
		if (masterSysUser==null || StringUtils.isEmpty(masterSysUser.getUserCode())) {
			return null;
		}
		String sqlQuery="select a.user_name, b.department_id,  b.user_code";
		if (!StringUtils.isEmpty(moduleId)) {
			sqlQuery += ", c.power_id ";
		}
		sqlQuery += " from jsys_user a, jsys_manager b ";
		if (!StringUtils.isEmpty(moduleId)) {
			sqlQuery += " left join jsys_manager_modl_pow c on c.module_id='" + moduleId + "' and c.user_code=b.user_code and c.company_code='"+companyCode+"'";
		}
		sqlQuery += " where a.user_code=b.user_code and a.user_code!='" + Constants.ROOT_ACCOUNT + "' and a.state=1 ";
		
		if (!Constants.ROOT_ACCOUNT.equals(masterSysUser.getUserCode())) {
			if (!StringUtils.isEmpty(companyCode) && (limitCompany || !"AA".equals(masterSysUser.getCompanyCode()))){
				sqlQuery+=" and a.company_code='"+companyCode+"'";
			}
			sqlQuery += " and a.user_code in (select d.slave_user_code from jsys_manager_user d where d.master_user_code='" + masterSysUser.getUserCode() + "')";
		}
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * Add By WuCF 20140506
	 * 获取指定仓库包含被管理用户对指定模块是否拥有权限的信息,其所返回的power_id不为空则代表用户有权限
	 * @param masterSysUser
	 * @param moduleId
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getPdWarehouseWithPowerJoin(final SysUser masterSysUser, final String warehouseNo, final String companyCode, boolean limitCompany) {
		if (masterSysUser==null || StringUtils.isEmpty(masterSysUser.getUserCode())) {
			return null;
		}
		//Modify By WuCF 20140605，得到管理对象
    	SysManager sysManager = (SysManager) getHibernateTemplate().get(SysManager.class, masterSysUser.getUserCode());
		
		String sqlQuery="select a.user_name, b.department_id, b.user_code ";
		if (!StringUtils.isEmpty(warehouseNo)) {
			sqlQuery += ", c.wu_id ";
		}
		sqlQuery += " from jsys_user a, jsys_manager b ";
		if (!StringUtils.isEmpty(warehouseNo)) {
			sqlQuery += " left join pd_Warehouse_User c on c.warehouse_no='" + warehouseNo + "' and c.user_code = b.user_code ";
		}
		sqlQuery += " where a.user_code = b.user_code and a.user_code!='" + Constants.ROOT_ACCOUNT + "' and a.state=1 ";
		
		if("AA".equals(sysManager.getCompanyCode())){//如果是全球管理对象
			if (!Constants.ROOT_ACCOUNT.equals(masterSysUser.getUserCode()) && !Constants.GLOBAL_ACCOUNT.equals(masterSysUser.getUserCode())) {
				if (!StringUtils.isEmpty(companyCode) && (limitCompany || !"AA".equals(masterSysUser.getCompanyCode()))){
					sqlQuery+=" and a.company_code='"+companyCode+"'";
				}
				sqlQuery += " and a.user_code in (select d.slave_user_code from jsys_manager_user d where d.master_user_code='" + masterSysUser.getUserCode() + "')";
			}
		}else{
			sqlQuery += " and b.department_id='"+sysManager.getDepartmentId()+"' ";
		}
		return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * 获取用户所管理的用户列表
	 * @param masterUserCode
	 * @return
	 */
	public List getSysManagerUsersByMaster(final String masterUserCode){
		return this.getHibernateTemplate().find("from SysManagerUser where masterUserCode=?",masterUserCode);
	}
	
	/**
	 * 获取可管理某用户的用户列表
	 * @param slaveUserCode
	 * @return
	 */
	public List getSysManagerUsersBySlave(final String slaveUserCode){
		return this.getHibernateTemplate().find("from SysManagerUser where slaveUserCode=?",slaveUserCode);
	}
}