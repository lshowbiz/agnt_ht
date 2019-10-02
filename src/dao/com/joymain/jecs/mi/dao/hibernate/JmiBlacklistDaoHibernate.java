
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.mi.dao.JmiBlacklistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiBlacklistDaoHibernate extends BaseDaoHibernate implements JmiBlacklistDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiBlacklistDao#getJmiBlacklists(com.joymain.jecs.mi.model.JmiBlacklist)
     */
    public List getJmiBlacklists(final JmiBlacklist jmiBlacklist) {
        return getHibernateTemplate().find("from JmiBlacklist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiBlacklist == null) {
            return getHibernateTemplate().find("from JmiBlacklist");
        } else {
            // filter on properties set in the jmiBlacklist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiBlacklist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiBlacklist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiBlacklistDao#getJmiBlacklist(Long blId)
     */
    public JmiBlacklist getJmiBlacklist(final Long blId) {
        JmiBlacklist jmiBlacklist = (JmiBlacklist) getHibernateTemplate().get(JmiBlacklist.class, blId);
        if (jmiBlacklist == null) {
            log.warn("uh oh, jmiBlacklist with blId '" + blId + "' not found...");
            throw new ObjectRetrievalFailureException(JmiBlacklist.class, blId);
        }

        return jmiBlacklist;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiBlacklistDao#saveJmiBlacklist(JmiBlacklist jmiBlacklist)
     */    
    public void saveJmiBlacklist(final JmiBlacklist jmiBlacklist) {
        getHibernateTemplate().saveOrUpdate(jmiBlacklist);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiBlacklistDao#removeJmiBlacklist(Long blId)
     */
    public void removeJmiBlacklist(final Long blId) {
        getHibernateTemplate().delete(getJmiBlacklist(blId));
    }
    //added for getJmiBlacklistsByCrm
    public List getJmiBlacklistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiBlacklist jmiBlacklist where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
		}
		

		String userName = crm.getString("userName", "");
		if (!StringUtils.isEmpty(userName)) {
			hql += " and userName = '" + userName + "'";
		}
		String papernumber = crm.getString("papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
			hql += " and papernumber = '" + papernumber + "'";
		}

		String phone = crm.getString("phone", "");
		if (!StringUtils.isEmpty(phone)) {
			hql += " and phone = '" + phone + "'";
		}
		String blackType = crm.getString("blackType", "");
		if (!StringUtils.isEmpty(blackType)) {
			hql += " and blackType = '" + blackType + "'";
		}

		String remark = crm.getString("remark", "");
		if (!StringUtils.isEmpty(remark)) {
			hql += " and remark like  '%" + remark + "%'";
		}

		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			hql += " and createTime >= to_date('" + createBTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createETime)) {
			hql += " and createTime <= to_date('" + createETime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode)) {
			hql += " and companyCode = '" + companyCode + "'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by blId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public boolean getCheckJmiBlacklist(String papertype, String papernumber) {
		String hql = "from JmiBlacklist jmiBlacklist where papertype='"+papertype+"' and upper(papernumber)=upper('"+papernumber+"') and status='0' ";
		List list=this.findObjectsByHqlQuery(hql);
		if(list.size()>0){
			return false;
		}else {
			return true;
		}
	}
}
