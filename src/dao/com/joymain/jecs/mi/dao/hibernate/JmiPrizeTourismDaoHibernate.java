
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiPrizeTourism;
import com.joymain.jecs.mi.dao.JmiPrizeTourismDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiPrizeTourismDaoHibernate extends BaseDaoHibernate implements JmiPrizeTourismDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiPrizeTourismDao#getJmiPrizeTourisms(com.joymain.jecs.mi.model.JmiPrizeTourism)
     */
    public List getJmiPrizeTourisms(final JmiPrizeTourism jmiPrizeTourism) {
        return getHibernateTemplate().find("from JmiPrizeTourism");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiPrizeTourism == null) {
            return getHibernateTemplate().find("from JmiPrizeTourism");
        } else {
            // filter on properties set in the jmiPrizeTourism
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiPrizeTourism).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiPrizeTourism.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiPrizeTourismDao#getJmiPrizeTourism(String userCode)
     */
    public JmiPrizeTourism getJmiPrizeTourism(final Long prizeTouismId) {
        JmiPrizeTourism jmiPrizeTourism = (JmiPrizeTourism) getHibernateTemplate().get(JmiPrizeTourism.class, prizeTouismId);
        if (jmiPrizeTourism == null) {
//            log.warn("uh oh, jmiPrizeTourism with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(JmiPrizeTourism.class, userCode);
        	return new JmiPrizeTourism();
        }

        return jmiPrizeTourism;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiPrizeTourismDao#saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism)
     */    
    public void saveJmiPrizeTourism(final JmiPrizeTourism jmiPrizeTourism) {
        getHibernateTemplate().saveOrUpdate(jmiPrizeTourism);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiPrizeTourismDao#removeJmiPrizeTourism(String userCode)
     */
    public void removeJmiPrizeTourism(final Long prizeTouismId) {
        getHibernateTemplate().delete(getJmiPrizeTourism(Long.valueOf(prizeTouismId)));
    }
    //added for getJmiPrizeTourismsByCrm
    public List getJmiPrizeTourismsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiPrizeTourism jmiPrizeTourism where 1=1";
    	
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += " and jmiPrizeTourism.userCode = '" + userCode + "' ";
	}
	String cardname = crm.getString("cardname", "");
	if (!StringUtils.isEmpty(cardname)) {
		hql += " and jmiPrizeTourism.cardname = '" + cardname + "' ";
	}
	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    public String getPassStarByUserCode(String userCode){
    	StringBuffer sql= new StringBuffer("select nvl(pass_star, 0) as pass_star from jbd_travel_point2014 where user_code='"+userCode+"' ") ;
		
		Map map= this.findOneObjectBySQL(sql.toString());
		if(map!=null&&!map.isEmpty()){
			return map.get("pass_star").toString();
		}else{
			return "0";
		}
	}

}
