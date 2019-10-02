
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.fi.dao.FiSecurityDepositDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiSecurityDepositDaoHibernate extends BaseDaoHibernate implements FiSecurityDepositDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositDao#getFiSecurityDeposits(com.joymain.jecs.fi.model.FiSecurityDeposit)
     */
    public List getFiSecurityDeposits(final FiSecurityDeposit fiSecurityDeposit) {
        return getHibernateTemplate().find("from FiSecurityDeposit");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiSecurityDeposit == null) {
            return getHibernateTemplate().find("from FiSecurityDeposit");
        } else {
            // filter on properties set in the fiSecurityDeposit
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiSecurityDeposit).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiSecurityDeposit.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositDao#getFiSecurityDeposit(Long fsdId)
     */
    public FiSecurityDeposit getFiSecurityDeposit(final Long fsdId) {
        FiSecurityDeposit fiSecurityDeposit = (FiSecurityDeposit) getHibernateTemplate().get(FiSecurityDeposit.class, fsdId);
        if (fiSecurityDeposit == null) {
            log.warn("uh oh, fiSecurityDeposit with fsdId '" + fsdId + "' not found...");
            throw new ObjectRetrievalFailureException(FiSecurityDeposit.class, fsdId);
        }

        return fiSecurityDeposit;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositDao#saveFiSecurityDeposit(FiSecurityDeposit fiSecurityDeposit)
     */    
    public void saveFiSecurityDeposit(final FiSecurityDeposit fiSecurityDeposit) {
        getHibernateTemplate().saveOrUpdate(fiSecurityDeposit);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiSecurityDepositDao#removeFiSecurityDeposit(Long fsdId)
     */
    public void removeFiSecurityDeposit(final Long fsdId) {
        getHibernateTemplate().delete(getFiSecurityDeposit(fsdId));
    }
    //added for getFiSecurityDepositsByCrm
    public List getFiSecurityDepositsByCrm(CommonRecord crm, Pager pager){
    	//String hql = "select fsdId,userCode,userName,dept,tel,email,balance,(select balance from JfiBankbookBalance j where j.userCode=f.userCode) as jfiBalance from FiSecurityDeposit f where 1=1";
    	String sql = "select f.fsd_id,f.dept,f.user_code,f.user_name,f.tel,f.balance,j.balance as j_balance from FI_SECURITY_DEPOSIT f left join JFI_BANKBOOK_BALANCE j on j.user_code=f.user_code where 1=1";
    	String userCode = crm.getString("userCode", "");
    	if(StringUtils.isNotEmpty(userCode)){
    		sql += " and f.user_code='"+userCode+"'";
    	}
    	String userName = crm.getString("userName", "");
    	if(StringUtils.isNotEmpty(userName)){
    		sql += " and f.user_name='"+userName+"'";
    	}
    	String dept = crm.getString("dept", "");
    	if(StringUtils.isNotEmpty(dept)){
    		sql += " and f.dept='"+dept+"'";
    	}

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sql += " order by f.fsd_id desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
    }

    /**
     * 查询保证金余额不足的所有帐户
     */
	@Override
	public List getFiSecurityDepositsByLimit(String limit) {
		// TODO Auto-generated method stub
		 return getHibernateTemplate().find("from FiSecurityDeposit where balance<"+limit);
	}

	@Override
	public Integer getCountOfSecurityDepositByUserCode(String userCode) {
		
		String sql="select count(*) from FI_SECURITY_DEPOSIT where user_code='"+userCode+"'";
		return this.getJdbcTemplate().queryForInt(sql);
	}
}
