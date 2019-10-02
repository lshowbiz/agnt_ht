
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiQuotaDao;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings({"unchecked","rawtypes"})
public class JfiQuotaDaoHibernate extends BaseDaoHibernate implements JfiQuotaDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiQuotaDao#getJfiQuotas(com.joymain.jecs.fi.model.JfiQuota)
     */
    public List getJfiQuotas(final JfiQuota jfiQuota) {
        return getHibernateTemplate().find("from JfiQuota");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiQuota == null) {
            return getHibernateTemplate().find("from JfiQuota");
        } else {
            // filter on properties set in the jfiQuota
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiQuota).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiQuota.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }
    
    public JfiQuota getJfiQuota(JfiQuota jfiQuota,String merchantId) {
		//String hql = "from JfiQuota a , FiBillAccount b where  a.accountId=b.accountId  and a.status=0 ";
		String hql = "from JfiQuota a where a.status=0 ";
		if (StringUtils.isNotEmpty(jfiQuota.getValidityPeriod())) {
			hql += " and a.validityPeriod ='" + jfiQuota.getValidityPeriod() + "'";
		}
		if (jfiQuota.getAccountId() != null) {
			hql += " and a.accountId =" + jfiQuota.getAccountId();
		}
		if (StringUtils.isNotEmpty(merchantId)) {
			hql += " and a.fiBillAccount.billAccountCode ='" + merchantId + "'";
		}
		List<JfiQuota> list = getHibernateTemplate().find(hql);
		return list.size() > 0 ? list.get(0) : null;
	}

    /**
     * @see com.joymain.jecs.fi.dao.JfiQuotaDao#getJfiQuota(Long quotaId)
     */
    public JfiQuota getJfiQuota(final Long quotaId) {
        JfiQuota jfiQuota = (JfiQuota) getHibernateTemplate().get(JfiQuota.class, quotaId);
        if (jfiQuota == null) {
            log.warn("uh oh, jfiQuota with quotaId '" + quotaId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiQuota.class, quotaId);
        }

        return jfiQuota;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiQuotaDao#saveJfiQuota(JfiQuota jfiQuota)
     */    
    public void saveJfiQuota(final JfiQuota jfiQuota) {
        getHibernateTemplate().saveOrUpdate(jfiQuota);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiQuotaDao#removeJfiQuota(Long quotaId)
     */
    public void removeJfiQuota(final Long quotaId) {
        getHibernateTemplate().delete(getJfiQuota(quotaId));
    }
    //added for getJfiQuotasByCrm
	public List getJfiQuotasByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiQuota jfiQuota where 1=1";
    	//期别
    	String validityPeriod = crm.getString("validityPeriod", "");
    	if(StringUtils.isNotEmpty(validityPeriod)){
    		hql += " and jfiQuota.validityPeriod='"+validityPeriod.trim()+"' ";
    	}
    	
    	//商户号
    	String billAccountCode = crm.getString("billAccountCode", "");
    	if(StringUtils.isNotEmpty(billAccountCode)){
    		hql += " and jfiQuota.fiBillAccount.billAccountCode='"+billAccountCode.trim()+"' ";
    	}
    	
    	//状态
    	String status = crm.getString("status", "");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and jfiQuota.status='"+status.trim()+"' ";
    	}
    	
    	//操作时间、截止时间
    	String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and jfiQuota.operateName >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and jfiQuota.operateName <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by quotaId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 金流限额定时调用功能实现
     */
    public void quartJfiQuota(){
    	//UNDO：金流限额每月最后一天结束之前生成下一财月的限额数据
    	
    }
    
    /**
     * 修改指定期别、商户号的原数据状态
     * @param crm
     */
    public void updateJfiQuotaStatus(CommonRecord crm){
    	String operatorName = crm.getString("operatorName", "");
    	String validityPeriod = crm.getString("validityPeriod", "");//期别
    	String accountId = crm.getString("accountId", "");//商户号
    	if(StringUtils.isNotEmpty(validityPeriod) && StringUtils.isNotEmpty(accountId)){
    		String hql = "update JFI_QUOTA JQ set JQ.status='1',JQ.OPERATE_NAME='"+operatorName+"',JQ.OPERATE_TIME=SYSDATE WHERE 1=1 ";
    		hql += " and JQ.VALIDITY_PERIOD='"+validityPeriod.trim()+"' ";
    		hql += " and JQ.ACCOUNT_ID='"+accountId.trim()+"' ";
    		this.jdbcTemplate.update(hql);
    	}
    }
}
