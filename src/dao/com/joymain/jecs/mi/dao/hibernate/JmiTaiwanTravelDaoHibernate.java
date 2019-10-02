
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.mi.dao.JmiTaiwanTravelDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiTaiwanTravelDaoHibernate extends BaseDaoHibernate implements JmiTaiwanTravelDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiTaiwanTravelDao#getJmiTaiwanTravels(com.joymain.jecs.mi.model.JmiTaiwanTravel)
     */
    public List getJmiTaiwanTravels(final JmiTaiwanTravel jmiTaiwanTravel) {
        return getHibernateTemplate().find("from JmiTaiwanTravel");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiTaiwanTravel == null) {
            return getHibernateTemplate().find("from JmiTaiwanTravel");
        } else {
            // filter on properties set in the jmiTaiwanTravel
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiTaiwanTravel).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiTaiwanTravel.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiTaiwanTravelDao#getJmiTaiwanTravel(String userCode)
     */
    public JmiTaiwanTravel getJmiTaiwanTravel(final String userCode) {
        JmiTaiwanTravel jmiTaiwanTravel = (JmiTaiwanTravel) getHibernateTemplate().get(JmiTaiwanTravel.class, userCode);
//        if (jmiTaiwanTravel == null) {
//            log.warn("uh oh, jmiTaiwanTravel with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(JmiTaiwanTravel.class, userCode);
//        }

        return jmiTaiwanTravel;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiTaiwanTravelDao#saveJmiTaiwanTravel(JmiTaiwanTravel jmiTaiwanTravel)
     */    
    public void saveJmiTaiwanTravel(final JmiTaiwanTravel jmiTaiwanTravel) {
        getHibernateTemplate().saveOrUpdate(jmiTaiwanTravel);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiTaiwanTravelDao#removeJmiTaiwanTravel(String userCode)
     */
    public void removeJmiTaiwanTravel(final String userCode) {
        getHibernateTemplate().delete(getJmiTaiwanTravel(userCode));
    }
    //added for getJmiTaiwanTravelsByCrm
    public List getJmiTaiwanTravelsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiTaiwanTravel jmiTaiwanTravel where 1=1";
    	// 
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
		}
		String createUser = crm.getString("createUser", "");
		if (!StringUtils.isEmpty(createUser)) {
			hql += " and createUser = '" + createUser + "'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	/**
	 * 检查会员是否存在
	 * 
	 * @param miMember
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckJmiTaiwanTravelExist(JmiTaiwanTravel jmiTaiwanTravel) {
		String sqlQuery = "select count(user_code) as idsum  from jmi_taiwan_travel  where user_code='"+ jmiTaiwanTravel.getUserCode()+"'";
//		if (!StringUtil.isEmpty(jmiTaiwanTravel.getUserCode())) {
//			sqlQuery += " and user_code!='" + jmiTaiwanTravel.getUserCode() + "'";
//		}
		List membeList = this.findObjectsBySQL(sqlQuery);
		Map idsum = (Map) membeList.get(0);
		if ("0".equals(idsum.get("idsum").toString())) {
			return false;
		}
		return true;
	}
}
