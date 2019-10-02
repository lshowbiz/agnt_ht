
package com.joymain.jecs.bd.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdDayBounsCalc;
import com.joymain.jecs.bd.dao.JbdDayBounsCalcDao;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdDayBounsCalcDaoHibernate extends BaseDaoHibernate implements JbdDayBounsCalcDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcDao#getJbdDayBounsCalcs(com.joymain.jecs.bd.model.JbdDayBounsCalc)
     */
    public List getJbdDayBounsCalcs(final JbdDayBounsCalc jbdDayBounsCalc) {
        return getHibernateTemplate().find("from JbdDayBounsCalc");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdDayBounsCalc == null) {
            return getHibernateTemplate().find("from JbdDayBounsCalc");
        } else {
            // filter on properties set in the jbdDayBounsCalc
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdDayBounsCalc).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdDayBounsCalc.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcDao#getJbdDayBounsCalc(Long id)
     */
    public JbdDayBounsCalc getJbdDayBounsCalc(final Long id) {
        JbdDayBounsCalc jbdDayBounsCalc = (JbdDayBounsCalc) getHibernateTemplate().get(JbdDayBounsCalc.class, id);
        if (jbdDayBounsCalc == null) {
            log.warn("uh oh, jbdDayBounsCalc with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdDayBounsCalc.class, id);
        }

        return jbdDayBounsCalc;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcDao#saveJbdDayBounsCalc(JbdDayBounsCalc jbdDayBounsCalc)
     */    
    public void saveJbdDayBounsCalc(final JbdDayBounsCalc jbdDayBounsCalc) {
        getHibernateTemplate().saveOrUpdate(jbdDayBounsCalc);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcDao#removeJbdDayBounsCalc(Long id)
     */
    public void removeJbdDayBounsCalc(final Long id) {
        getHibernateTemplate().delete(getJbdDayBounsCalc(id));
    }
    //added for getJbdDayBounsCalcsByCrm
    public List getJbdDayBounsCalcsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdDayBounsCalc jbdDayBounsCalc where 1=1";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	/**
	 * 获取会员在某一期的日结算记录,如果没有则各值为空
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public HashMap getBdDayBounsCalcBySql(final String memberNo, final Integer wweek){
		String sqlQuery="select a.user_code,a.company_code,a.card_type,a.mi_user_name as user_name," +
				"d.tree_index, d.layer_id, a.pet_name,b.month_consumer_pv,b.week_group_pv,b.recommend_num,b.link_num,b.pending_link_num " +
				" from  jmi_link_ref d, jmi_member a , v_jbd_day_bouns_calc b " +
				" where a.user_code=d.user_code and a.user_code = '"+memberNo+"' and a.user_code = b.user_code and b.w_week="+wweek+" ";
		return this.findOneObjectBySQL(sqlQuery);
	}
	/**
	 * 获取会员的下层接点在某一期的日结算记录
	 * @param miMember
	 * @param wweek
	 * @return
	 */
	public List getChildBdDayBounsCalcBySql(final JmiMember jmiMember, final Integer wweek){
		/*String sqlQuery="select a.user_code,a.company_code,a.card_type,a.mi_user_name as user_name, a.pet_name,b.month_consumer_pv,b.week_group_pv,b.recommend_num,b.link_num,b.pending_link_num" +
				" from jmi_member a ,v_jbd_day_bouns_calc b where a.user_code = b.user_code and b.w_week="+wweek +
				" and b.link_no='"+jmiMember.getUserCode()+"' ";
		
	 
			sqlQuery+=" order by a.CREATE_TIME";
				
		return this.findObjectsBySQL(sqlQuery);*/
		//Modify By WuCF 20140709 绑定变量
		StringBuffer sqlQuery= new StringBuffer("select a.user_code,a.company_code,a.card_type,a.mi_user_name as user_name, a.pet_name,b.month_consumer_pv,b.week_group_pv,b.recommend_num,b.link_num,b.pending_link_num ");
		sqlQuery.append(" from jmi_member a ,v_jbd_day_bouns_calc b where a.user_code = b.user_code and b.w_week=? ");
		sqlQuery.append(" and b.link_no=? ");
		
		StringBuffer paramsBuf = new StringBuffer(","+wweek);
		paramsBuf.append(","+jmiMember.getUserCode());
		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		List list = this.getJdbcTemplate().queryForList(sqlQuery.toString(), parameters); 
		return list;
	}

	public Map getBdDayBounsCalcByUserCode(String userCode, String wweek) {
		String sqlQuery="select * from v_jbd_day_bouns_calc t where t.user_code='"+userCode+"' and t.w_week="+wweek;
		return this.findOneObjectBySQLReport(sqlQuery);
	}

	public List getBdDayBounsCalcByTop(String netType, String userCode, String wweek) {
		String sqlQuery="select * from v_jbd_day_bouns_calc where "+netType+" = '"+userCode+"' and w_week="+wweek;
		return this.findObjectsBySQL(sqlQuery);
	}
	
	
}
