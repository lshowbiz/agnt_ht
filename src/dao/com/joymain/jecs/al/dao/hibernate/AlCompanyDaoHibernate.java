package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.dao.AlCompanyDao;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCompanyDaoHibernate extends BaseDaoHibernate implements AlCompanyDao {
	public List getAlCompanysExceptAA() {
		String queryString = "from AlCompany alCompany where alCompany.companyCode !='AA' order by alCompany.companyCode";
		return this.getHibernateTemplate().find(queryString);
	}

	public String getCompanyName(String companyNo) {
		String sql = "select COMPANY_NAME from JAL_COMPANY where COMPANY_CODE='" + companyNo + "' order by alCompany.companyCode";
		return (String) this.getJdbcTemplate().queryForObject(sql, String.class);
		//return null;
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCompanyDao#getAlCompanys(com.joymain.jecs.al.model.AlCompany)
	 */
	public List getAlCompanys(final AlCompany alCompany) {
		return getHibernateTemplate().find("from AlCompany al order by al.companyCode");
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCompanyDao#getAlCompany(Long companyId)
	 */
	public AlCompany getAlCompany(final Long companyId) {
		AlCompany alCompany = (AlCompany) getHibernateTemplate().get(AlCompany.class, companyId);
		if (alCompany == null) {
			log.warn("uh oh, alCompany with companyId '" + companyId + "' not found...");
			throw new ObjectRetrievalFailureException(AlCompany.class, companyId);
		}

		return alCompany;
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCompanyDao#saveAlCompany(AlCompany alCompany)
	 */
	public void saveAlCompany(final AlCompany alCompany) {
		getHibernateTemplate().saveOrUpdate(alCompany);
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCompanyDao#removeAlCompany(Long companyId)
	 */
	public void removeAlCompany(final Long companyId) {
		AlCompany alCompany=getAlCompany(companyId);
		this.executeUpdate("delete from SysCompanyPow where companyCode='"+alCompany.getCompanyCode()+"'");
		getHibernateTemplate().delete(alCompany);
	}

	/**
	 * 根据外部参数分页获取对应的公司列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCompanysByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from AlCompany where 1=1";
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hqlQuery += " and companyCode='" + companyCode + "'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by companyId desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List alCompanys = this.findObjectsByHqlQuery(hqlQuery, pager);

		return alCompanys;
	}

	/**
	 * 根据公司编码获取对应的公司资料
	 * @param companyCode
	 * @return
	 */
	public AlCompany getAlCompanyByCode(final String companyCode) {
		return (AlCompany) this.getObjectByHqlQuery("from AlCompany where companyCode='" + companyCode + "'");
	}

	/**
	 * 获取所管理人员所有对应的公司
	 * @param sysUser
	 * @param companyCode
	 * @param limitCompany 是否限定指定的公司
	 * @return
	 */
	public List getMyAlCompanys(final SysUser sysUser, final String companyCode, boolean limitCompany) {
		if (sysUser == null || StringUtils.isEmpty(sysUser.getUserCode())) {
			return null;
		}
		String hqlQuery = "from AlCompany a ";
		if (!Constants.ROOT_ACCOUNT.equals(sysUser.getUserCode()) && !Constants.GLOBAL_ACCOUNT.equals(sysUser.getUserCode())) {
			if (!StringUtils.isEmpty(companyCode) && (limitCompany || !"AA".equals(sysUser.getCompanyCode()))) {
				hqlQuery += " where a.companyCode='" + companyCode + "'";
			} else {
				hqlQuery += " where a.companyCode in (" 
					+ "select distinct(b.companyCode) from SysManagerModlPow b where b.userCode='" + sysUser.getUserCode() + "')";
			}
		}
		hqlQuery += " order by a.companyCode";
		return this.findObjectsByHqlQuery(hqlQuery);
	}
}