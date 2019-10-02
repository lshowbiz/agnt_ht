
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdVentureLeaderSubHist;
import com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdVentureLeaderSubHistDaoHibernate extends BaseDaoHibernate implements JbdVentureLeaderSubHistDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao#getJbdVentureLeaderSubHists(com.joymain.jecs.bd.model.JbdVentureLeaderSubHist)
     */
    public List getJbdVentureLeaderSubHists(final JbdVentureLeaderSubHist jbdVentureLeaderSubHist) {
        return getHibernateTemplate().find("from JbdVentureLeaderSubHist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdVentureLeaderSubHist == null) {
            return getHibernateTemplate().find("from JbdVentureLeaderSubHist");
        } else {
            // filter on properties set in the jbdVentureLeaderSubHist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdVentureLeaderSubHist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdVentureLeaderSubHist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao#getJbdVentureLeaderSubHist(Long id)
     */
    public JbdVentureLeaderSubHist getJbdVentureLeaderSubHist(final Long id) {
        JbdVentureLeaderSubHist jbdVentureLeaderSubHist = (JbdVentureLeaderSubHist) getHibernateTemplate().get(JbdVentureLeaderSubHist.class, id);
        if (jbdVentureLeaderSubHist == null) {
            log.warn("uh oh, jbdVentureLeaderSubHist with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdVentureLeaderSubHist.class, id);
        }

        return jbdVentureLeaderSubHist;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao#saveJbdVentureLeaderSubHist(JbdVentureLeaderSubHist jbdVentureLeaderSubHist)
     */    
    public void saveJbdVentureLeaderSubHist(final JbdVentureLeaderSubHist jbdVentureLeaderSubHist) {
        getHibernateTemplate().saveOrUpdate(jbdVentureLeaderSubHist);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao#removeJbdVentureLeaderSubHist(Long id)
     */
    public void removeJbdVentureLeaderSubHist(final Long id) {
        getHibernateTemplate().delete(getJbdVentureLeaderSubHist(id));
    }
    //added for getJbdVentureLeaderSubHistsByCrm
    public List getJbdVentureLeaderSubHistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdVentureLeaderSubHist jbdVentureLeaderSubHist where 1=1";
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

	public List getJbdVentureLeaderSubHistByUserCode(String userCode, String wweek, String bounsType) {
		List list=null;
		   //旬结旬发
			if(StringUtil.formatInt(wweek)>=StringUtil.formatInt("201905")){
				StringBuffer sb = new StringBuffer("   Select rownum as num,");
				sb.append("   b.User_Code,");
				sb.append("   a.w_Week,");
				sb.append("   b.WEEK_GROUP_FIRST_PV_LNK,");
				sb.append("   decode(SIGN(b.Serial_Number - 1), 1, a.Bouns_Point, 0) As Bouns_Point,");
				sb.append("   (DECODE(SIGN(B.SERIAL_NUMBER - 1),1,LEAST((CASE WHEN B.USER_CODE = A.LAST_KEEP_USER_CODE THEN");
				sb.append("	      (B.WEEK_GROUP_FIRST_PV_LNK + NVL(A.LAST_KEEP_PV, 0)) * A.BOUNS_POINT");
				sb.append("                       ELSE B.WEEK_GROUP_FIRST_PV_LNK * A.BOUNS_POINT END), A.FD_PV),0)) AS Bouns,");
			   
				
				sb.append("   decode(b.Serial_Number, 1, a.Keep_Pv, 0) As Keep_Pv,");
				sb.append("   decode(a.Last_Keep_User_Code, b.User_Code, a.Last_Keep_Pv, 0) As Last_Keep_Pv");
				sb.append("   From V_JBD_CALC_SELL_LIST a, V_JBD_CALC_SELL_LIST b");
				sb.append("   Where 1 = 1");
				sb.append("   And a.User_Code = '"+userCode+"'");
				sb.append("   And a.User_Code = b.Link_No");
				sb.append("   And a.w_Week = b.w_Week");
				sb.append("   And a.w_Week = "+wweek);
				list=  this.findObjectsBySQL(sb.toString());
			}
			//日结月发
			else if(StringUtil.formatInt(wweek)<StringUtil.formatInt("201905")&&StringUtil.formatInt(wweek)>=StringUtil.formatInt("201805")){
				StringBuffer sb = new StringBuffer("   Select rownum as num,");
				sb.append("   b.User_Code,");
				sb.append("   a.w_Week,");
				sb.append("   b.WEEK_GROUP_FIRST_PV_LNK,");
				sb.append("   decode(SIGN(b.Serial_Number - 1), 1, a.Bouns_Point, 0) As Bouns_Point,");
				
				//sb.append("   decode(SIGN(b.Serial_Number - 1),1,least(b.WEEK_GROUP_FIRST_PV_LNK, a.FD_PV) * a.Bouns_Point,0) as Bouns,");
				//Bouns
				sb.append("   (DECODE(SIGN(B.SERIAL_NUMBER - 1),1,LEAST((CASE WHEN B.USER_CODE = A.LAST_KEEP_USER_CODE THEN");
				sb.append("	      (B.WEEK_GROUP_FIRST_PV_LNK + NVL(A.LAST_KEEP_PV, 0)) * A.BOUNS_POINT");
				sb.append("                       ELSE B.WEEK_GROUP_FIRST_PV_LNK * A.BOUNS_POINT END), A.FD_PV),0)) AS Bouns,");
			   
				
				sb.append("   decode(b.Serial_Number, 1, a.Keep_Pv, 0) As Keep_Pv,");
				sb.append("   decode(a.Last_Keep_User_Code, b.User_Code, a.Last_Keep_Pv, 0) As Last_Keep_Pv");
				sb.append("   From V_JBD_CALC_SELL_LIST a, V_JBD_CALC_SELL_LIST b, JBD_PERIOD JP");
				sb.append("   Where 1 = 1");
				sb.append("   AND a.W_WEEK >= TO_CHAR(JP.START_TIME, 'YYYYMMDD')");
				sb.append("   AND a.W_WEEK < TO_CHAR(JP.END_TIME, 'YYYYMMDD')");
				sb.append("   AND JP.W_YEAR || LPAD(JP.W_WEEK, 2, 0) = "+wweek);
				sb.append("   AND b.W_WEEK >= TO_CHAR(JP.START_TIME, 'YYYYMMDD')");
				sb.append("   AND b.W_WEEK < TO_CHAR(JP.END_TIME, 'YYYYMMDD')");
				sb.append("   AND JP.W_YEAR || LPAD(JP.W_WEEK, 2, 0) = "+wweek);
				sb.append("   And a.User_Code = '"+userCode+"'");
				sb.append("   And a.User_Code = b.Link_No");
				sb.append("   And a.w_Week = b.w_Week");
	
		 
			list=  this.findObjectsBySQL(sb.toString());
		}else{
			list= this.getHibernateTemplate().find("from VJbdVentureLeaderSub where userCode='"+userCode+"' and wweek="+wweek+" order by nlevel");
		}
		return list;
	}
	public List getJbdVentureLeaderSubHistDetailByUserCode(String userCode, String wweek) {
		StringBuffer sb = new StringBuffer("   SELECT EL.REUSER_CODE , EL.MEMBER_ORDER_NO, EL.ORDER_TYPE, EL.ORDER_PV ");
		sb.append("   FROM V_JBD_CALC_SELL_ORDER_LIST EL");
		sb.append("   WHERE 1=1  ");
		sb.append("   AND EL.W_WEEK = " +wweek);
		sb.append("   AND EL.TEAM_CODE = '"+userCode+"'");

		return this.findObjectsBySQL(sb.toString());
	}
}
