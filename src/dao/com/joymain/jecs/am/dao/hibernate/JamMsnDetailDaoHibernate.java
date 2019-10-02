
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.am.dao.JamMsnDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JamMsnDetailDaoHibernate extends BaseDaoHibernate implements JamMsnDetailDao {

    /**
     * @see com.joymain.jecs.am.dao.JamMsnDetailDao#getJamMsnDetails(com.joymain.jecs.am.model.JamMsnDetail)
     */
    public List getJamMsnDetails(final JamMsnDetail jamMsnDetail) {
        return getHibernateTemplate().find("from JamMsnDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jamMsnDetail == null) {
            return getHibernateTemplate().find("from JamMsnDetail");
        } else {
            // filter on properties set in the jamMsnDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jamMsnDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JamMsnDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnDetailDao#getJamMsnDetail(Long mdId)
     */
    public JamMsnDetail getJamMsnDetail(final Long mdId) {
        JamMsnDetail jamMsnDetail = (JamMsnDetail) getHibernateTemplate().get(JamMsnDetail.class, mdId);
        if (jamMsnDetail == null) {
            log.warn("uh oh, jamMsnDetail with mdId '" + mdId + "' not found...");
            throw new ObjectRetrievalFailureException(JamMsnDetail.class, mdId);
        }

        return jamMsnDetail;
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnDetailDao#saveJamMsnDetail(JamMsnDetail jamMsnDetail)
     */    
    public void saveJamMsnDetail(final JamMsnDetail jamMsnDetail) {
        getHibernateTemplate().saveOrUpdate(jamMsnDetail);
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnDetailDao#removeJamMsnDetail(Long mdId)
     */
    public void removeJamMsnDetail(final Long mdId) {
        getHibernateTemplate().delete(getJamMsnDetail(mdId));
    }
    //added for getJamMsnDetailsByCrm
    public List getJamMsnDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JamMsnDetail jamMsnDetail where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by mdId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJamMsnDetailsBySql(String userCode) {
		String sql="select t.msn_name,nvl(d.status,0) as status,d.md_id,t.mt_id from jam_msn_type t " +
				" left join jam_msn_detail d on t.mt_id = d.mt_id and d.user_code='"+userCode+"' where t.msn_status = '1'";
		return this.findObjectsBySQL(sql);
	}

	public List getJamMsnDetailsBySql(String userCode, String msnKey) {
		String sql="select t.msn_name, nvl(d.status, 0) as status from jam_msn_type t " +
				"left join jam_msn_detail d on t.mt_id = d.mt_id and d.user_code = '"+userCode+"' " +
				"where t.msn_status = '1' and t.msn_key='"+msnKey+"'";
		return this.findObjectsBySQL(sql);
	}
}
