
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiAmountDetailDao;
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JfiAmountDetailDaoHibernate extends BaseDaoHibernate implements JfiAmountDetailDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiAmountDetailDao#getJfiAmountDetails(com.joymain.jecs.fi.model.JfiAmountDetail)
     */
    public List getJfiAmountDetails(final JfiAmountDetail jfiAmountDetail) {
        return getHibernateTemplate().find("from JfiAmountDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiAmountDetail == null) {
            return getHibernateTemplate().find("from JfiAmountDetail");
        } else {
            // filter on properties set in the jfiAmountDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiAmountDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiAmountDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiAmountDetailDao#getJfiAmountDetail(Long id)
     */
    public JfiAmountDetail getJfiAmountDetail(final Long id) {
        JfiAmountDetail jfiAmountDetail = (JfiAmountDetail) getHibernateTemplate().get(JfiAmountDetail.class, id);
        if (jfiAmountDetail == null) {
            log.warn("uh oh, jfiAmountDetail with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JfiAmountDetail.class, id);
        }

        return jfiAmountDetail;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiAmountDetailDao#saveJfiAmountDetail(JfiAmountDetail jfiAmountDetail)
     */    
    public void saveJfiAmountDetail(final JfiAmountDetail jfiAmountDetail) {
        getHibernateTemplate().saveOrUpdate(jfiAmountDetail);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiAmountDetailDao#removeJfiAmountDetail(Long id)
     */
    public void removeJfiAmountDetail(final Long id) {
        getHibernateTemplate().delete(getJfiAmountDetail(id));
    }
    //added for getJfiAmountDetailsByCrm
    public List getJfiAmountDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiAmountDetail jfiAmountDetail where 1=1";
    	//明细外键
    	String quotaId = crm.getString("quotaId", "");
    	if(StringUtils.isNotEmpty(quotaId)){
    		hql += " and jfiAmountDetail.quotaId='"+quotaId.trim()+"' ";
    	}
    	
    	//期别
    	String validityPeriod = crm.getString("validityPeriod", "");
    	if(StringUtils.isNotEmpty(validityPeriod)){
    		hql += " and jfiAmountDetail.jfiQuota.validityPeriod='"+validityPeriod.trim()+"' ";
    	}
    	
    	//商户号
    	String billAccountCode = crm.getString("billAccountCode", "");
    	if(StringUtils.isNotEmpty(billAccountCode)){
    		hql += " and jfiAmountDetail.jfiQuota.fiBillAccount.billAccountCode='"+billAccountCode.trim()+"' ";
    	}
    	
    	//用户编号
    	String userCode = crm.getString("userCode", "");
    	if(StringUtils.isNotEmpty(userCode)){
    		hql += " and jfiAmountDetail.userCode='"+userCode.trim()+"' ";
    	}
    	
    	//起始时间、截止时间
    	String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and jfiAmountDetail.createTime >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and jfiAmountDetail.createTime <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
