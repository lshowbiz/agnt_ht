
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.JpoAutoShipDao;
import com.joymain.jecs.po.model.JpoAutoShip;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JpoAutoShipDaoHibernate extends BaseDaoHibernate implements JpoAutoShipDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoAutoShipDao#getJpoAutoShips(com.joymain.jecs.po.model.JpoAutoShip)
     */
    public List getJpoAutoShips(final JpoAutoShip jpoAutoShip) {
        return getHibernateTemplate().find("from JpoAutoShip");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoAutoShip == null) {
            return getHibernateTemplate().find("from JpoAutoShip");
        } else {
            // filter on properties set in the jpoAutoShip
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoAutoShip).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoAutoShip.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoAutoShipDao#getJpoAutoShip(Long jasId)
     */
    public JpoAutoShip getJpoAutoShip(final Long jasId) {
        JpoAutoShip jpoAutoShip = (JpoAutoShip) getHibernateTemplate().get(JpoAutoShip.class, jasId);
        if (jpoAutoShip == null) {
            log.warn("uh oh, jpoAutoShip with jasId '" + jasId + "' not found...");
            throw new ObjectRetrievalFailureException(JpoAutoShip.class, jasId);
        }

        return jpoAutoShip;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoAutoShipDao#saveJpoAutoShip(JpoAutoShip jpoAutoShip)
     */    
    public void saveJpoAutoShip(final JpoAutoShip jpoAutoShip) {
        getHibernateTemplate().saveOrUpdate(jpoAutoShip);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoAutoShipDao#removeJpoAutoShip(Long jasId)
     */
    public void removeJpoAutoShip(final Long jasId) {
        getHibernateTemplate().delete(getJpoAutoShip(jasId));
    }
    //added for getJpoAutoShipsByCrm
    public List getJpoAutoShipsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoAutoShip jpoAutoShip where 1=1";


    	String companyCode = crm.getString("companyCode", "");
    	if(!StringUtils.isEmpty(companyCode)){
    		hql += " and companyCode = '" + companyCode + "'";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jasId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 获取AutoShip的定时器是否在执行
     * @param configKey
     * @return
     */
    public String getAutoShipConfigValue(final String configKey){
    	String sqlQuery = "select nvl(max(default_value),0) as default_value from jsys_config_key where config_code='" + configKey + "'";
    	Map map = (Map)this.findObjectsBySQL(sqlQuery).get(0);
    	return map.get("default_value").toString();
    }
}
