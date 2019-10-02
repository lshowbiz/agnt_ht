package com.joymain.jecs.al.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.al.dao.AlCompanyDao;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCompanyManagerImpl extends BaseManager implements AlCompanyManager {
	public String getCompanyName(String companyNo) {
		return dao.getCompanyName(companyNo);
	}

	private AlCompanyDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setAlCompanyDao(AlCompanyDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCompanyManager#getAlCompanys(com.joymain.jecs.al.model.AlCompany)
	 */
	public List getAlCompanys(final AlCompany alCompany) {
		return dao.getAlCompanys(alCompany);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCompanyManager#getAlCompany(String companyId)
	 */
	public AlCompany getAlCompany(final String companyId) {
		return dao.getAlCompany(new Long(companyId));
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCompanyManager#saveAlCompany(AlCompany alCompany)
	 */
	public void saveAlCompany(AlCompany alCompany) {
		dao.saveAlCompany(alCompany);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCompanyManager#removeAlCompany(String companyId)
	 */
	public void removeAlCompany(final String companyId) {
		dao.removeAlCompany(new Long(companyId));
	}

	/**
	 * 根据外部参数分页获取对应的公司列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCompanysByPage(CommonRecord crm, Pager pager) {
		return dao.getAlCompanysByPage(crm, pager);
	}

	/**
	 * 根据公司编码获取对应的公司资料
	 * @param companyCode
	 * @return
	 */
	public AlCompany getAlCompanyByCode(final String companyCode) {
		return dao.getAlCompanyByCode(companyCode);
	}

	
	public List getAlCompanysExceptAA() {
		return dao.getAlCompanysExceptAA();
	}
	
	/**
	 * 获取所管理人员所有对应的公司
	 * @param sysUser
	 * @param companyCode
	 * @param limitCompany 是否限定指定的公司
	 * @return
	 */
	public List getMyAlCompanys(final SysUser sysUser, final String companyCode, boolean limitCompany) {
		return dao.getMyAlCompanys(sysUser, companyCode, limitCompany);
	}
	
	/**
	 * 获取所管理人员所有对应的公司,并以MAP类型传回
	 * @param userCode
	 * @return
	 */
	public Map getMyAlCompanysToMap(final String userCode){
		SysUser sysUser=new SysUser();
		sysUser.setUserCode(userCode);
		
		Map<String, String> map=new LinkedHashMap<String, String>();
		List<AlCompany> alCompanys=dao.getMyAlCompanys(sysUser, null, false);
		if(alCompanys!=null){
			for(AlCompany alCompany:alCompanys){
	    		map.put(alCompany.getCompanyCode(), "["+alCompany.getCompanyCode()+"] "+alCompany.getCompanyName());
	    	}
		}
		return map;
	}
}
