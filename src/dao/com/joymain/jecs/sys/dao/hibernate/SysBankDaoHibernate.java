
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.sys.dao.SysBankDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SysBankDaoHibernate extends BaseDaoHibernate implements SysBankDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.sys.dao.SysBankDao#getSysBanks(com.joymain.jecs.sys.model.SysBank)
     */
    public List getSysBanks(final SysBank sysBank) {
        return getHibernateTemplate().findByExample(sysBank);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sysBank == null) {
            return getHibernateTemplate().find("from SysBank");
        } else {
            // filter on properties set in the sysBank
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sysBank).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SysBank.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysBankDao#getSysBank(Long bankId)
     */
    public SysBank getSysBank(final Long bankId) {
        SysBank sysBank = (SysBank) getHibernateTemplate().get(SysBank.class, bankId);
        if (sysBank == null) {
            log.warn("uh oh, sysBank with bankId '" + bankId + "' not found...");
            throw new ObjectRetrievalFailureException(SysBank.class, bankId);
        }

        return sysBank;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysBankDao#saveSysBank(SysBank sysBank)
     */    
    public void saveSysBank(final SysBank sysBank) {
        getHibernateTemplate().saveOrUpdate(sysBank);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysBankDao#removeSysBank(Long bankId)
     */
    public void removeSysBank(final Long bankId) {
        getHibernateTemplate().delete(getSysBank(bankId));
    }
    //added for getSysBanksByCrm
    public List getSysBanksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysBank sysBank where 1=1";
    	// 
    	
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode ='" + companyCode + "'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by orderNo asc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getSysBankByCompanyCode(String companyCode) {
		String hql="from SysBank sysBank where 1=1";
		if (!StringUtils.isEmpty(companyCode)&&!"AA".equals(companyCode)) {
			hql += " and companyCode ='" + companyCode + "'";
		}
		hql+=" order by orderNo";
		return this.findObjectsByHqlQuery(hql);
	}
	public SysBank getJsysBankByBankNo(String bankNo) {
		SysBank sysBank=(SysBank) this.getObjectByHqlQuery("from SysBank where bankNo='"+bankNo+"'");
		return sysBank;
	}
}
