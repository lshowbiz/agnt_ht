package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysManagerModlPowDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the sysManagerModlPows
	 */
	public List getSysManagerModlPows(SysManagerModlPow sysManagerModlPow);

	/**
	 * Gets sysManagerModlPow's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param powerId the sysManagerModlPow's powerId
	 * @return sysManagerModlPow populated sysManagerModlPow object
	 */
	public SysManagerModlPow getSysManagerModlPow(final Long powerId);

	/**
	 * Saves a sysManagerModlPow's information
	 * @param sysManagerModlPow the object to be saved
	 */
	public void saveSysManagerModlPow(SysManagerModlPow sysManagerModlPow);

	/**
	 * Removes a sysManagerModlPow from the database by powerId
	 * @param powerId the sysManagerModlPow's powerId
	 */
	public void removeSysManagerModlPow(final Long powerId);

	//added for getSysManagerModlPowsByCrm
	public List getSysManagerModlPowsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 删除用户指定的模块以外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
	 * @param operatorCode
	 * @param userCode
	 * @param companyCode
	 * @param moduleIds
	 */
	public void removeSysManagerModlPowsByIds(final String operatorCode, final String userCode, final String companyCode, final String[] moduleIds);
	
    /**
     * 获取用户指定模块所对应的权限
     * @param userCode
     * @param companyCode
     * @param moduleId
     * @return
     */
    public SysManagerModlPow getSysManagerModlPow(final String userCode, final String companyCode, final Long moduleId);
    
    /**
	 * Add By WuCF 20140507通过会员编号和仓库编码得到数据
	 * 获取用户指定模块所对应的权限
	 * @param userCode
	 * @param warehouseNo
	 * @return
	 */
    public PdWarehouseUser getPdWarehouseUser(String userCode,String warehouseNo);
    
    /**
     * 批量保存多条记录
     * @param sysManagerModlPows
     */
    public void saveSysManagerModlPows(List sysManagerModlPows);
    
    /**
	 * Add By WuCF 20140507
	 * 批量保存多个仓库权限数据
	 * @param sysManagerModlPows
	 */
    public void savePdWarehouseUserPows(List pdWarehouseUsers);
    
    /**
     * 删除一定用户范围内, 对应模块所指定的用外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
     * @param manageredUserCodes
     * @param userCodes
     * @param companyCode
     * @param moduleId
     */
    public void removeSysManagerModlPowsByUserCodes(final String[] manageredUserCodes,final String[] userCodes, final String companyCode, final String moduleId);
    
    /**
     * 删除一定用户范围内, 对应模块所指定的用外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
     * @param manageredUserCodes
     * @param userCodes
     * @param companyCode
     * @param moduleId
     */
    public void removePdWarehousePowsByUserCodes(final String[] manageredUserCodes,final String[] userCodes, final String companyCode, final String warehouseNo);
    
    /**
     * 删除用户仓库所有权限
     * @param userCode
     */
    public void removePdWarehousePowsByUserCode(String userCode);
    
    
    /**
     * 删除用户在角色以外模块的所有权限
     * @param userCode
     * @param companyCode
     * @param sysRole
     */
    public void removeSysManagerModlPowsByRole(final String userCode, final String companyCode, final SysRole sysRole);
}
