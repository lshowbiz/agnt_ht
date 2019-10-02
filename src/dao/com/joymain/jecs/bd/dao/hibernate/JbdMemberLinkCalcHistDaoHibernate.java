
package com.joymain.jecs.bd.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.model.VJbdMemberLinkCalc;
import com.joymain.jecs.bd.dao.JbdMemberLinkCalcHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdMemberLinkCalcHistDaoHibernate extends BaseDaoHibernate implements JbdMemberLinkCalcHistDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdMemberLinkCalcHistDao#getJbdMemberLinkCalcHists(com.joymain.jecs.bd.model.JbdMemberLinkCalcHist)
     */
    public List getJbdMemberLinkCalcHists(final JbdMemberLinkCalcHist jbdMemberLinkCalcHist) {
        return getHibernateTemplate().find("from JbdMemberLinkCalcHist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdMemberLinkCalcHist == null) {
            return getHibernateTemplate().find("from JbdMemberLinkCalcHist");
        } else {
            // filter on properties set in the jbdMemberLinkCalcHist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdMemberLinkCalcHist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdMemberLinkCalcHist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdMemberLinkCalcHistDao#getJbdMemberLinkCalcHist(Long id)
     */
    public JbdMemberLinkCalcHist getJbdMemberLinkCalcHist(final Long id) {
        JbdMemberLinkCalcHist jbdMemberLinkCalcHist = (JbdMemberLinkCalcHist) getHibernateTemplate().get(JbdMemberLinkCalcHist.class, id);
        if (jbdMemberLinkCalcHist == null) {
            log.warn("uh oh, jbdMemberLinkCalcHist with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdMemberLinkCalcHist.class, id);
        }

        return jbdMemberLinkCalcHist;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdMemberLinkCalcHistDao#saveJbdMemberLinkCalcHist(JbdMemberLinkCalcHist jbdMemberLinkCalcHist)
     */    
    public void saveJbdMemberLinkCalcHist(final JbdMemberLinkCalcHist jbdMemberLinkCalcHist) {
        getHibernateTemplate().saveOrUpdate(jbdMemberLinkCalcHist);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdMemberLinkCalcHistDao#removeJbdMemberLinkCalcHist(Long id)
     */
    public void removeJbdMemberLinkCalcHist(final Long id) {
        getHibernateTemplate().delete(getJbdMemberLinkCalcHist(id));
    }
    //added for getJbdMemberLinkCalcHistsByCrm
    public List getJbdMemberLinkCalcHistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VJbdMemberLinkCalc jbdMemberLinkCalcHist where 1=1";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		String userName = crm.getString("userName", "");
		if(!StringUtil.isEmpty(userName)){
			hql += " and name='"+userName+"'";
		}
		String wweek = crm.getString("wweek", "");
		if(!StringUtil.isEmpty(wweek)){
			hql += " and wweek='"+wweek+"'";
		}
		String companyCode = crm.getString("companyCode", "");
		if(!StringUtil.isEmpty(companyCode)){
			hql += " and companyCode='"+companyCode+"'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by wweek desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public VJbdMemberLinkCalc getJbdMemberLinkCalcHistsByUserCodeWeek(String userCode, String wweek) {
		String hql="from VJbdMemberLinkCalc where userCode='"+userCode+"' and wweek= "+wweek;
		return (VJbdMemberLinkCalc) this.getObjectByHqlQuery(hql);
	}

	public Map findByWweekAndMemberBySql(int wweek, String userCode) {
		String sql="select t.user_code,t.name,t.card_type,t.link_no,s.keep_pv from jbd_member_link_calc_hist t " +
		"left join jbd_member_link_calc_hist s on t.user_code=s.keep_user_code and t.w_week=s.w_week"+ 
		" where t.user_code='"+userCode+"' and t.w_week="+wweek;
		List bdSendRecordList=this.findObjectsBySQL(sql);
		if(bdSendRecordList.size()!=1){
			return null;
		}else{
			return (Map) bdSendRecordList.get(0);
		}
	}
	/**
	 * 获取整组PV
	 * @param memberNo
	 * @param wWeek
	 * @return
	 */
	public Map getBonusRecordbyUserCode(String userCode, String wWeek){
		String sqlQuery="select round(week_group_pv,2) as pv from jbd_member_link_calc_hist where user_code='"+userCode+"' and w_week="+wWeek;

		List listTmp = this.findObjectsBySQL(sqlQuery);
		if(listTmp.size()>0){
			return (Map)this.findObjectsBySQL(sqlQuery).get(0);
		}else{
			Map mapTmp = new HashMap();
			mapTmp.put("pv", 0);
			return mapTmp;
		}
		
	}

	/**
	 * 获取某会员在某一期的奖金数据
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public Map getBdSendRecordMap(final String userCode, final String wweek){
		String sqlQuery="select * from jbd_member_link_calc_hist t where t.user_code='"+userCode+"' and t.w_week="+wweek;
		return this.findOneObjectBySQL(sqlQuery);
	}
	/**
	 * 成功销售奖金明细
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public List getSuccessSaleBonus(final String recommendNo, final String wweek){
		return this.findObjectsBySQL("select * from v_jbd_success_sales where user_code='"+recommendNo+"' and w_week="+wweek);
	}

	public List getFranchises(final String recommendNo, final String wweek,String type) {
		return this.findObjectsBySQL("select * from v_jbd_franchise_fees where user_code='"+recommendNo+"' and w_week="+wweek+" and fees_type='"+type+"'");
	}

	public JbdMemberLinkCalcHist getJJbdMemberLinkCalcHistByUserCodeWeek(String userCode, String wweek) {
		String hql="from JbdMemberLinkCalcHist where userCode='"+userCode+"' and wweek= "+wweek;
		return (JbdMemberLinkCalcHist) this.getObjectByHqlQuery(hql);
	}

	public List getStoreExpandPv(String userCode, String wweek) {
		return this.findObjectsBySQL("select * from v_jbd_store_expand_sub where user_code='"+userCode+"' and w_week="+wweek);
	}

	public List getStoreServePv(String userCode, String wweek) {
		return this.findObjectsBySQL("select * from v_jbd_store_serve_sub where user_code='"+userCode+"' and w_week="+wweek);
	}

	public List getStoreRecommendPv(String userCode, String wweek) {
		return this.findObjectsBySQL("select * from v_Jbd_Store_Recommend_Sub where user_code='"+userCode+"' and w_week="+wweek);
	}
	public List getVentureFundPv(String userCode, String wweek) {
		return this.findObjectsBySQL("select * from JBD_VENTURE_FUND_LIST where user_code='"+userCode+"' and w_week="+wweek);
	}
	public List getJbdSuccessLeaderPv(String userCode, String wweek) {
		String sql="select algebra,re_user_code,success_rate,sum(success_leader_pv) success_leader_pv,w_week,repass_star　from v_jbd_success_leader t "
				+ "where user_code = '"+userCode+ "' and w_week = "+wweek	+ " group by algebra,re_user_code,success_rate,w_week,repass_star";
		if(StringUtil.formatInt(wweek)>=201711&&StringUtil.formatInt(wweek)<201805){
			
		}else{
			sql="select * from v_jbd_success_leader where user_code='"+userCode+"' and w_week="+wweek;
		}
		return this.findObjectsBySQL(sql);
	}
	public List getPopularizeBonusPv(String userCode, String wweek) {
		String sql="select *　from V_JBD_RECOMMEND_LIST_X t where user_code = '"+userCode+ "' and w_week = "+wweek;
		return this.findObjectsBySQL(sql);
	}
	
	
	/**
	 * 代数奖明细
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getSuccessLeaderPvDetail(String userCode,String wweek,String passStar,String startDate,String endDate,String algebra,String passStar1){
		List list=null;
		if(StringUtil.formatInt(wweek)>=201711&&StringUtil.formatInt(wweek)<201805){
			String sql=" select b.user_code as re_user_code, b.member_order_no, b.pv_amt "+
					"  from  jpo_member_order b "+
					"  where b.user_code = '"+userCode+"' "+
					"  and b.check_date >= to_date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') "+
					" and b.check_date <  to_date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') "+
					" and b.status = '2' "+
					" and b.order_type not in ('4', '14', '9')";
			
			list=this.findObjectsBySQL(sql);
		}
		else{
			String sql="select a.re_user_code,b.member_order_no,b.pv_amt from "+passStar+" a, jpo_member_order b "
					+ "where a.user_code = '"+userCode+"' and a.w_week = "+wweek+"  and a.RE_USER_CODE = b.user_code and b.check_date >= "
					+ " to_date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') and b.check_date <  to_date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') and b.status='2'";
			if("100".equals(algebra)){
				sql+="  and a.RE_USER_CODE='"+userCode+"' ";
			}

			if(StringUtil.formatInt(wweek)>=201701){
				sql+=" and b.order_type not in ('4','14','9')";
			}
			  list=this.findObjectsBySQL(sql);
			
			if("0".equals(passStar1) && "10".equals(algebra)){
				sql=sql.replaceAll("a.user_code", "a.user_code1");
				List list1=this.findObjectsBySQL(sql);
				list.addAll(list1);
			}
		}
		
		
		
		

		return list;
		
	}
	public Map getJBdLevel(String userCode ,String wweek){
		String sql="select * from v_jbd_level where user_code='"+userCode+"' and w_week="+wweek;
		List list=this.findObjectsBySQL(sql);
		if(list.isEmpty()){
			return new HashMap();
		}else{
			return (Map) list.get(0);
		}
		
	}
	
	public List getVentureLeaderPvDetail(String userCode,String startDate,String endDate ){
		String sql="select b.user_code,b.company_code,b.order_type,b.member_order_no,b.pv_amt from jpo_member_order b where b.user_code = '"+userCode+"' and b.check_date >= to_date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') "
				+ "and b.check_date < to_date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') and b.status = '2' ";
		
		return this.findObjectsBySQL(sql);
	}
	public List getServicePv(String userCode, String wweek) {
		return this.findObjectsBySQL("select * from v_JBD_VENTURE_SALES where user_code='"+userCode+"' and w_week="+wweek);
	}
	public List getServicePv201607(String userCode, String wweek){
		return this.findObjectsBySQL("select * from V_JBD_SERVICE_AWARDS where user_code='"+userCode+"' and w_week="+wweek);
	}
	
	public List getbdjPv201607(String userCode, String wweek){
		StringBuffer sb = new StringBuffer(" select recommended_no as reno,isstore as isstore,order_no as order_no,pv_amt as pv_amt,proportion as bfb,pv as pv from v_jbd_store_expand_sub ");
		sb.append(" where user_code='"+userCode+"'  and w_week=" +wweek);
		sb.append(" union all ");
		sb.append(" select recommend_no as reno,'' as isstore,member_order_no as order_no,fees as pv_amt,percentage as bfb,null as pv from v_jbd_franchise_fees ");
		sb.append(" where user_code='"+userCode+"'  and w_week=" +wweek);
		
		return this.findObjectsBySQL(sb.toString());
	}
}
