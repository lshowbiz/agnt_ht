package com.joymain.jecs.al.dao;

import java.util.List;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCompanyDao extends Dao {

	public String getCompanyName(String companyNo);

	/**
	 * Retrieves all of the alCompanys
	 */
	public List getAlCompanys(AlCompany alCompany);

	/**
	 * Gets alCompany's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param companyId the alCompany's companyId
	 * @return alCompany populated alCompany object
	 */
	public AlCompany getAlCompany(final Long companyId);

	/**
	 * Saves a alCompany's information
	 * @param alCompany the object to be saved
	 */
	public void saveAlCompany(AlCompany alCompany);

	/**
	 * Removes a alCompany from the database by companyId
	 * @param companyId the alCompany's companyId
	 */
	public void removeAlCompany(final Long companyId);

	/**
	 * 根据外部参数分页获取对应的公司列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCompanysByPage(CommonRecord crm, Pager pager);

	/**
	 * 根据公司编码获取对应的公司资料
	 * @param companyCode
	 * @return
	 */
	public AlCompany getAlCompanyByCode(final String companyCode);

	public List getAlCompanysExceptAA();
	
	/**
	 * 获取所管理人员所有对应的公司
	 * @param sysUser
	 * @param companyCode
	 * @param limitCompany 是否限定指定的公司
	 * @return
	 */
	public List getMyAlCompanys(final SysUser sysUser, final String companyCode, boolean limitCompany);
}
