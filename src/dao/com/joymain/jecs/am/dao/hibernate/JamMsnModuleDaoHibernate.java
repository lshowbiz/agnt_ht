
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.am.dao.JamMsnModuleDao;
import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JamMsnModuleDaoHibernate extends BaseDaoHibernate implements JamMsnModuleDao {

    /**
     * @see com.joymain.jecs.am.dao.JamMsnModuleDao#getJamMsnModules(com.joymain.jecs.am.model.JamMsnModule)
     */
    public List getJamMsnModules(final JamMsnModule jamMsnModule) {
        return getHibernateTemplate().findByExample(jamMsnModule);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jamMsnModule == null) {
            return getHibernateTemplate().find("from JamMsnModule");
        } else {
            // filter on properties set in the jamMsnModule
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jamMsnModule).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JamMsnModule.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnModuleDao#getJamMsnModule(Long jmmNo)
     */
    public JamMsnModule getJamMsnModule(final Long jmmNo) {
        JamMsnModule jamMsnModule = (JamMsnModule) getHibernateTemplate().get(JamMsnModule.class, jmmNo);
        if (jamMsnModule == null) {
            log.warn("uh oh, jamMsnModule with jmmNo '" + jmmNo + "' not found...");
            throw new ObjectRetrievalFailureException(JamMsnModule.class, jmmNo);
        }

        return jamMsnModule;
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnModuleDao#saveJamMsnModule(JamMsnModule jamMsnModule)
     */    
    public void saveJamMsnModule(final JamMsnModule jamMsnModule) {
        getHibernateTemplate().saveOrUpdate(jamMsnModule);
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnModuleDao#removeJamMsnModule(Long jmmNo)
     */
    public void removeJamMsnModule(final Long jmmNo) {
        getHibernateTemplate().delete(getJamMsnModule(jmmNo));
    }
    //added for getJamMsnModulesByCrm
    public List getJamMsnModulesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JamMsnModule jamMsnModule where 1=1";
    	
    	String mtId = crm.getString("mtId","");
    	if(StringUtils.isNotEmpty(mtId)){
    		hql += " and jamMsnType.mtId='"+mtId+"'";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jmmNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJamMsnModuleByMtId(Long mtId) {
		return this.findObjectsByHqlQuery("from JamMsnModule where jamMsnType.mtId="+mtId);
	}

	public List getJamMsnModeulBySql(String userCode, String msnKey, String cardType) {
		String sql="select t.msn_name, nvl(d.status, 0) as status, m.content from jam_msn_type t " +
				"left join jam_msn_detail d on t.mt_id = d.mt_id and d.user_code = '"+userCode+"' " +
				"left join jam_msn_module m on m.mt_id = t.mt_id and m.mm_type = '"+cardType+"' " +
				"where t.msn_status = '1' and t.msn_key = '"+msnKey+"'";
		return this.findObjectsBySQL(sql);
	}
}
