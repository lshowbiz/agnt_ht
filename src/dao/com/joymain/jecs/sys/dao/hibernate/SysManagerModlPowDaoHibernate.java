package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.sys.dao.SysManagerModlPowDao;
import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysManagerModlPowDaoHibernate extends BaseDaoHibernate implements SysManagerModlPowDao {
	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerModlPowDao#getSysManagerModlPows(com.joymain.jecs.sys.model.SysManagerModlPow)
	 */
	public List getSysManagerModlPows(final SysManagerModlPow sysManagerModlPow) {
		return getHibernateTemplate().find("from SysManagerModlPow");
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerModlPowDao#getSysManagerModlPow(Long powerId)
	 */
	public SysManagerModlPow getSysManagerModlPow(final Long powerId) {
		SysManagerModlPow sysManagerModlPow = (SysManagerModlPow) getHibernateTemplate().get(SysManagerModlPow.class, powerId);
		if (sysManagerModlPow == null) {
			log.warn("uh oh, sysManagerModlPow with powerId '" + powerId + "' not found...");
			throw new ObjectRetrievalFailureException(SysManagerModlPow.class, powerId);
		}

		return sysManagerModlPow;
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerModlPowDao#saveSysManagerModlPow(SysManagerModlPow sysManagerModlPow)
	 */
	public void saveSysManagerModlPow(final SysManagerModlPow sysManagerModlPow) {
		getHibernateTemplate().saveOrUpdate(sysManagerModlPow);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysManagerModlPowDao#removeSysManagerModlPow(Long powerId)
	 */
	public void removeSysManagerModlPow(final Long powerId) {
		getHibernateTemplate().delete(getSysManagerModlPow(powerId));
	}

	//added for getSysManagerModlPowsByCrm
	public List getSysManagerModlPowsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from SysManagerModlPow sysManagerModlPow where 1=1";
		/** * ����Լ��Ĳ�ѯ���������** */
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by powerId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
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
		String hqlQuery = "delete from SysManagerModlPow where userCode='" + userCode + "' and companyCode='" + companyCode +"' ";
		
		if (!Constants.ROOT_ACCOUNT.equalsIgnoreCase(operatorCode)) {
			hqlQuery += " and moduleId in (select moduleId from SysManagerModlPow where userCode='" + operatorCode + "' and companyCode='"
			        + companyCode + "')";
		}

		if (moduleIds != null && moduleIds.length > 0) {
			hqlQuery += " and moduleId not in (";
			for (int i = 0; i < moduleIds.length; i++) {
				if (i > 0) {
					hqlQuery += ",";
				}
				hqlQuery += moduleIds[i];
			}
			hqlQuery += ")";
		}
		this.executeUpdate(hqlQuery);
	}

	/**
	 * 获取用户指定模块所对应的权限
	 * @param userCode
	 * @param companyCode
	 * @param moduleId
	 * @return
	 */
	public SysManagerModlPow getSysManagerModlPow(final String userCode, final String companyCode, final Long moduleId) {
		return (SysManagerModlPow) this.getObjectByHqlQuery("from SysManagerModlPow where userCode='" + userCode + "' and companyCode='"
		        + companyCode + "' and moduleId=" + moduleId);
	}
	
	/**
	 * Add By WuCF 20140507通过会员编号和仓库编码得到数据
	 * 获取用户指定模块所对应的权限
	 * @param userCode
	 * @param warehouseNo
	 * @return
	 */
	public PdWarehouseUser getPdWarehouseUser(String userCode,String warehouseNo) {
		return (PdWarehouseUser) this.getObjectByHqlQuery("from PdWarehouseUser where userCode='" + userCode
		        + "' and warehouseNo='" + warehouseNo+"' ");
	}

	/**
	 * 批量保存多条记录
	 * @param sysManagerModlPows
	 */
	public void saveSysManagerModlPows(List sysManagerModlPows) {
		this.getHibernateTemplate().saveOrUpdateAll(sysManagerModlPows);
	}

	/**
	 * Add By WuCF 20140507
	 * 批量保存多个仓库权限数据
	 * @param sysManagerModlPows
	 */
    public void savePdWarehouseUserPows(List pdWarehouseUsers){
    	this.getHibernateTemplate().saveOrUpdateAll(pdWarehouseUsers);
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
		if (manageredUserCodes != null && manageredUserCodes.length > 0) {
			String hqlQuery = "delete from SysManagerModlPow where moduleId='" + moduleId + "' and companyCode='" + companyCode
			        + "' and userCode in (";
			for (int j = 0; j < manageredUserCodes.length; j++) {
				if (j > 0) {
					hqlQuery += ",";
				}
				hqlQuery += "'" + manageredUserCodes[j] + "'";
			}
			hqlQuery += ") ";

			if (userCodes != null && userCodes.length > 0) {
				hqlQuery += " and userCode not in (";
				for (int i = 0; i < userCodes.length; i++) {
					if (i > 0) {
						hqlQuery += ",";
					}
					hqlQuery += "'" + userCodes[i] + "'";
				}
				hqlQuery += ")";
			}
			this.executeUpdate(hqlQuery);
		}
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
		if (manageredUserCodes != null && manageredUserCodes.length > 0) {
			String hqlQuery = "delete from PdWarehouseUser where warehouseNo='" + warehouseNo
			        + "' and userCode in (";
			for (int j = 0; j < manageredUserCodes.length; j++) {
				if (j > 0) {
					hqlQuery += ",";
				}
				hqlQuery += "'" + manageredUserCodes[j] + "'";
			}
			hqlQuery += ") ";

			if (userCodes != null && userCodes.length > 0) {
				hqlQuery += " and userCode not in (";
				for (int i = 0; i < userCodes.length; i++) {
					if (i > 0) {
						hqlQuery += ",";
					}
					hqlQuery += "'" + userCodes[i] + "'";
				}
				hqlQuery += ")";
			}
			this.executeUpdate(hqlQuery);
		}
	}
	
	/**
     * 删除用户仓库所有权限
     * @param userCode
     */
	public void removePdWarehousePowsByUserCode(String userCode){
		String hqlQuery = "delete from PdWarehouseUser where userCode='"+userCode+"'";
		this.executeUpdate(hqlQuery);
	}
	
	/**
	 * 删除用户在角色以外模块的所有权限
	 * @param userCode
	 * @param companyCode
	 * @param sysRole
	 */
	public void removeSysManagerModlPowsByRole(final String userCode, final String companyCode, final SysRole sysRole) {
		String hqlQuery = "delete from SysManagerModlPow where userCode='" + userCode + "' and companyCode='" + companyCode
		        + "' and moduleId not in (" + "select moduleId from SysRolePower where roleId='" + sysRole.getRoleId() + "'" + ")";

		this.executeUpdate(hqlQuery);
	}
}