
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiPayBank;
import com.joymain.jecs.fi.dao.JfiPayBankDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiPayBankDaoHibernate extends BaseDaoHibernate implements JfiPayBankDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayBankDao#getJfiPayBanks(com.joymain.jecs.fi.model.JfiPayBank)
     */
    public List getJfiPayBanks(final JfiPayBank jfiPayBank) {
        return getHibernateTemplate().find("from JfiPayBank");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiPayBank == null) {
            return getHibernateTemplate().find("from JfiPayBank");
        } else {
            // filter on properties set in the jfiPayBank
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiPayBank).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiPayBank.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayBankDao#getJfiPayBank(String accountCode)
     */
    public JfiPayBank getJfiPayBank(final String accountCode) {
        JfiPayBank jfiPayBank = (JfiPayBank) getHibernateTemplate().get(JfiPayBank.class, accountCode);
        return jfiPayBank;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayBankDao#saveJfiPayBank(JfiPayBank jfiPayBank)
     */    
    public void saveJfiPayBank(final JfiPayBank jfiPayBank) {
        getHibernateTemplate().saveOrUpdate(jfiPayBank);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPayBankDao#removeJfiPayBank(String accountCode)
     */
    public void removeJfiPayBank(final String accountCode) {
        getHibernateTemplate().delete(getJfiPayBank(accountCode));
    }
    //added for getJfiPayBanksByCrm
    public List getJfiPayBanksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiPayBank jfiPayBank where 1=1";
		
		String companyCode = crm.getString("companyCode", "");
		if(!StringUtils.isEmpty(companyCode)){
			hql += " and companyCode = '" + companyCode + "'";
		}
		
		String accountCode = crm.getString("accountCode", "");
		if(!StringUtils.isEmpty(accountCode)){
			hql += " and accountCode = '" + accountCode + "'";
		}
		
		String accountName = crm.getString("accountName", "");
		if(!StringUtils.isEmpty(accountName)){
			hql += " and accountName like '%" + accountName + "%'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by accountCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	
	/**
	 * 获取帐号代码在一个数组范围内的帐号资料
	 * @param accountCodes
	 * @return
	 */
	public List getJfiPayBanksWithStr(final String[] accountCodes){
		if(accountCodes==null){
			return this.getJfiPayBanks(null);
		}else{
			String hqlStr="from JfiPayBank";
			if(accountCodes.length>0){
				hqlStr+=" where accountCode in (";
				for(int i=0;i<accountCodes.length;i++){
					if(i>0){
						hqlStr+=",";
					}
					hqlStr+="'"+accountCodes[i]+"'";
				}
				hqlStr+=")";
			}
			return this.getHibernateTemplate().find(hqlStr);
		}
	}
	
	/**
	 * 获取公司所对应的银行
	 * @param companyCode
	 * @return
	 */
	public List getJfiPayBanksByCompany(final String companyCode){
		return this.getHibernateTemplate().find("from JfiPayBank where companyCode=? order by accountCode",companyCode);
	}
}
