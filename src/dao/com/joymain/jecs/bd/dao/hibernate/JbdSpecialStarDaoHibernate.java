
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSpecialStar;
import com.joymain.jecs.bd.dao.JbdSpecialStarDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSpecialStarDaoHibernate extends BaseDaoHibernate implements JbdSpecialStarDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSpecialStarDao#getJbdSpecialStars(com.joymain.jecs.bd.model.JbdSpecialStar)
     */
    public List getJbdSpecialStars(final JbdSpecialStar jbdSpecialStar) {
        return getHibernateTemplate().find("from JbdSpecialStar");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSpecialStar == null) {
            return getHibernateTemplate().find("from JbdSpecialStar");
        } else {
            // filter on properties set in the jbdSpecialStar
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSpecialStar).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSpecialStar.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSpecialStarDao#getJbdSpecialStar(String userCode)
     */
    public JbdSpecialStar getJbdSpecialStar(final String userCode) {
        JbdSpecialStar jbdSpecialStar = (JbdSpecialStar) getHibernateTemplate().get(JbdSpecialStar.class, userCode);
//        if (jbdSpecialStar == null) {
//            log.warn("uh oh, jbdSpecialStar with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(JbdSpecialStar.class, userCode);
//        }

        return jbdSpecialStar;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSpecialStarDao#saveJbdSpecialStar(JbdSpecialStar jbdSpecialStar)
     */    
    public void saveJbdSpecialStar(final JbdSpecialStar jbdSpecialStar) {
        getHibernateTemplate().saveOrUpdate(jbdSpecialStar);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSpecialStarDao#removeJbdSpecialStar(String userCode)
     */
    public void removeJbdSpecialStar(final String userCode) {
        getHibernateTemplate().delete(getJbdSpecialStar(userCode));
    }
    //added for getJbdSpecialStarsByCrm
    public List getJbdSpecialStarsByCrm(CommonRecord crm, Pager pager){
    	String hql = "select a.*,b.mi_user_name from jbd_special_star_hist a , jmi_member b  where a.user_code=b.user_code";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and a.user_Code='"+userCode+"'";
		}
		String passStar = crm.getString("passStar", "");
		if(!StringUtil.isEmpty(passStar)){
			hql += " and a.pass_Star >= 41";
		}
		String crownEnvoyNum = crm.getString("crownEnvoyNum", "");
		if(StringUtil.isInteger(crownEnvoyNum)){
			hql += " and a.crown_envoy_num="+crownEnvoyNum;
		}
		String departmentNum = crm.getString("departmentNum", "");
		if(StringUtil.isInteger(departmentNum)){
			hql += " and a.department_num="+departmentNum;
		}
		String minDepartmentNum = crm.getString("minDepartmentNum", "");
		if(StringUtil.isInteger(minDepartmentNum)){
			hql += " and a.min_department_num="+minDepartmentNum;
		}
		String companyCode = crm.getString("companyCode", "");
		if(!StringUtil.isEmpty(companyCode)){ 
			hql += " and b.company_code='"+companyCode+"'";
		}

		String fyear = crm.getString("fyear", "");
		if(StringUtil.isInteger(fyear)){
			hql += " and a.f_year="+fyear;
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by a.user_Code desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(hql, pager);
    }
}
