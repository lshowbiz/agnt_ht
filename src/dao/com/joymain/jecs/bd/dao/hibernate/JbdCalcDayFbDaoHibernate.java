
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.dao.JbdCalcDayFbDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdCalcDayFbDaoHibernate extends BaseDaoHibernate implements JbdCalcDayFbDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayFbDao#getJbdCalcDayFbs(com.joymain.jecs.bd.model.JbdCalcDayFb)
     */
    public List getJbdCalcDayFbs(final JbdCalcDayFb jbdCalcDayFb) {
        return getHibernateTemplate().find("from JbdCalcDayFb");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdCalcDayFb == null) {
            return getHibernateTemplate().find("from JbdCalcDayFb");
        } else {
            // filter on properties set in the jbdCalcDayFb
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdCalcDayFb).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdCalcDayFb.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayFbDao#getJbdCalcDayFb(BigDecimal id)
     */
    public JbdCalcDayFb getJbdCalcDayFb(final Long id) {
        JbdCalcDayFb jbdCalcDayFb = (JbdCalcDayFb) getHibernateTemplate().get(JbdCalcDayFb.class, id);
/*        if (jbdCalcDayFb == null) {
            log.warn("uh oh, jbdCalcDayFb with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdCalcDayFb.class, id);
        }*/

        return jbdCalcDayFb;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayFbDao#saveJbdCalcDayFb(JbdCalcDayFb jbdCalcDayFb)
     */    
    public void saveJbdCalcDayFb(final JbdCalcDayFb jbdCalcDayFb) {
        getHibernateTemplate().saveOrUpdate(jbdCalcDayFb);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayFbDao#removeJbdCalcDayFb(BigDecimal id)
     */
    public void removeJbdCalcDayFb(final Long id) {
        getHibernateTemplate().delete(getJbdCalcDayFb(id));
    }
    //added for getJbdCalcDayFbsByCrm
    public List getJbdCalcDayFbsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdCalcDayFb jbdCalcDayFb where 1=1";
 
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and jmiMember.userCode='"+userCode+"'";
    	}

    	String freezeStatus=crm.getString("freezeStatus", "");
    	if(!StringUtil.isEmpty(freezeStatus)){
    		hql+=" and freezeStatus="+freezeStatus;
    	}

    	String startWeek=crm.getString("startWeek", "");
    	if(!StringUtil.isEmpty(startWeek)){
    		hql+=" and wweek>="+startWeek;
    	}
    	String endWeek=crm.getString("endWeek", "");
    	if(!StringUtil.isEmpty(endWeek)){
    		hql+=" and wweek<="+endWeek;
    	}
    	String wweek=crm.getString("wweek", "");
    	if(!StringUtil.isEmpty(wweek)){
    		hql+=" and wweek="+wweek;
    	}
    	
		String team = crm.getString("team", "");
		if(StringUtils.isNotBlank(team)){
			hql +=" AND jmiMember.userCode IN (SELECT userCode FROM JmiMember MI WHERE MI.memberType='" + team + "')";
		}

    	String status=crm.getString("status", "");
    	if(!StringUtil.isEmpty(status)){
    		hql+=" and status="+status;
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
    
    public List getJbdCalcDayFbList(String userCode,String wweek){

		return this.findObjectsBySQL("select * from CALC_DAY_FB_LIST where user_code='"+userCode+"' and w_week= "+wweek);
    }
    
    
    
    
}
