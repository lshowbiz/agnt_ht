
package com.joymain.jecs.fi.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderDao;
import com.joymain.jecs.fi.model.JfiSunMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JfiSunMemberOrderDaoHibernate extends BaseDaoHibernate implements JfiSunMemberOrderDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderDao#getJfiSunMemberOrders(com.joymain.jecs.fi.model.JfiSunMemberOrder)
     */
    public List getJfiSunMemberOrders(final JfiSunMemberOrder jfiSunMemberOrder) {
        return getHibernateTemplate().find("from JfiSunMemberOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunMemberOrder == null) {
            return getHibernateTemplate().find("from JfiSunMemberOrder");
        } else {
            // filter on properties set in the jfiSunMemberOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunMemberOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunMemberOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderDao#getJfiSunMemberOrder(Long moId)
     */
    public JfiSunMemberOrder getJfiSunMemberOrder(final Long moId) {
        JfiSunMemberOrder jfiSunMemberOrder = (JfiSunMemberOrder) getHibernateTemplate().get(JfiSunMemberOrder.class, moId);
        if (jfiSunMemberOrder == null) {
            log.warn("uh oh, jfiSunMemberOrder with moId '" + moId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunMemberOrder.class, moId);
        }

        return jfiSunMemberOrder;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderDao#saveJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder)
     */    
    public void saveJfiSunMemberOrder(final JfiSunMemberOrder jfiSunMemberOrder) {
        getHibernateTemplate().saveOrUpdate(jfiSunMemberOrder);
    }

    public List getJfiSunMemberOrderReportByCrm(CommonRecord crm){
    	String sql = "Select jps.uni_no,jps.product_no,jps.product_name,jp.unit_no,Sum(jsmol.qty) sumQty,jsmol.price,Sum(jsmol.qty*jsmol.price) sumPrice ";
    	sql += "From Jfi_Sun_Member_Order Jsmo, Jfi_Sun_Member_Order_List Jsmol, jpm_product_sale_new jps, Jpm_Product jp ";
    	sql += "Where Jsmo.Mo_Id = Jsmol.Mo_Id ";
    	sql += "And jsmol.product_id = jps.uni_no ";
    	sql += "And Jps.Product_No = jp.Product_No ";
    	String agentNo = crm.getString("agentNo", "");
		if (!StringUtils.isEmpty(agentNo)) {
			sql += "And Jsmo.Agent_No = '" + agentNo + "' ";
		}
		String startLogTime = crm.getString("startLogTime", "");
		if (!StringUtils.isEmpty(startLogTime)) {
			sql += "And Jsmo.Check_Time >= To_Date('" + startLogTime + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ";
		}
    	
    	String endLogTime = crm.getString("endLogTime", "");
		if (!StringUtils.isEmpty(endLogTime)) {
			sql += "And Jsmo.Check_Time <= To_Date('" + endLogTime + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";
		}
    	
    	sql += "Group By jps.product_no,jps.product_name,jsmol.price,jp.unit_no,jps.uni_no ";
    	sql += "Order By jps.product_no asc";
    	return this.findObjectsBySQL(sql);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderDao#removeJfiSunMemberOrder(Long moId)
     */
    public void removeJfiSunMemberOrder(final Long moId) {
        getHibernateTemplate().delete(getJfiSunMemberOrder(moId));
    }
    //added for getJfiSunMemberOrdersByCrm
    public List getJfiSunMemberOrdersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunMemberOrder jfiSunMemberOrder where 1=1";
    	hql += this.buildListHqlQuery(crm);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by moId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	/**
	 * 根据外部参数生成查询语句
	 * @param crm
	 * @return
	 */
	private String buildListHqlQuery(CommonRecord crm) {
		String hql = "";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode='" + userCode + "'";
		}

		String agentNo = crm.getString("agentNo", "");
		if (!StringUtils.isEmpty(agentNo)) {
			hql += " and agentNo='" + agentNo + "'";
		}

		String memberOrderNo = crm.getString("memberOrderNo", "");
		if (!StringUtils.isEmpty(memberOrderNo)) {
			hql += " and memberOrderNo='" + memberOrderNo + "'";
		}
		
		String logType = "BC";
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if("A".equals(logType)){
					hql += " and orderTime>=to_date('" + startLogTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if("C".equals(logType)){
					hql += " and checkDate>=to_date('" + startLogTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
				if("BC".equals(logType)){
					hql += " and checkTime>=to_date('" + startLogTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if("A".equals(logType)){
					hql += " and orderTime<=to_date('" + endLogTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if("C".equals(logType)){
					hql += " and checkDate<=to_date('" + endLogTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
				if("BC".equals(logType)){
					hql += " and checkTime<=to_date('" + endLogTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				}
			}
		}
		
		return hql;
	}
}
