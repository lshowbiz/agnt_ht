
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.dao.JpoCounterOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoCounterOrderDaoHibernate extends BaseDaoHibernate implements JpoCounterOrderDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDao#getJpoCounterOrders(com.joymain.jecs.po.model.JpoCounterOrder)
     */
    public List getJpoCounterOrders(final JpoCounterOrder jpoCounterOrder) {
        return getHibernateTemplate().find("from JpoCounterOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoCounterOrder == null) {
            return getHibernateTemplate().find("from JpoCounterOrder");
        } else {
            // filter on properties set in the jpoCounterOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoCounterOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoCounterOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDao#getJpoCounterOrder(Long coNo)
     */
    public JpoCounterOrder getJpoCounterOrder(final String coNo) {
        JpoCounterOrder jpoCounterOrder = (JpoCounterOrder) getHibernateTemplate().get(JpoCounterOrder.class, coNo);
//        if (jpoCounterOrder == null) {
//            log.warn("uh oh, jpoCounterOrder with coNo '" + coNo + "' not found...");
//            throw new ObjectRetrievalFailureException(JpoCounterOrder.class, coNo);
//        }

        return jpoCounterOrder;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDao#saveJpoCounterOrder(JpoCounterOrder jpoCounterOrder)
     */    
    public void saveJpoCounterOrder(final JpoCounterOrder jpoCounterOrder) {
        getHibernateTemplate().saveOrUpdate(jpoCounterOrder);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCounterOrderDao#removeJpoCounterOrder(Long coNo)
     */
    public void removeJpoCounterOrder(final String coNo) {
        getHibernateTemplate().delete(getJpoCounterOrder(coNo));
    }
    //added for getJpoCounterOrdersByCrm
    public List getJpoCounterOrdersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoCounterOrder jpoCounterOrder where 1=1";

    	hql+=returnHql(crm);
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by coNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    private String returnHql(CommonRecord crm){
    	String hql="";
    	String coNo = crm.getString("coNo", "");
		if (StringUtils.isNotEmpty(coNo)) {
			hql += " and coNo='" + coNo + "'";
		}
		
    	String warehouseNo = crm.getString("warehouseNo", "");
		if (StringUtils.isNotEmpty(warehouseNo)) {
			hql += " and warehouseNo='" + warehouseNo + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isNotEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}

		String createUserNo = crm.getString("createUserNo", "");
		if (StringUtils.isNotEmpty(createUserNo)) {
			hql += " and createUserNo='" + createUserNo + "'";
		}
		
		String csStatus = crm.getString("csStatus", "");
		if (StringUtils.isNotEmpty(csStatus)) {
			hql += " and csStatus in (" + csStatus + ")";
		}

		
		String userCode = crm.getString("userCode", "");
		if (StringUtils.isNotEmpty(userCode)) {
			hql += " and sysUser.userCode = '" + userCode + "'";
		}

		
		String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	String okTimeStart = crm.getString("okTimeStart","");
    	String okTimeEnd = crm.getString("okTimeEnd","");
    	if(StringUtils.isNotEmpty(okTimeStart)){
    		hql += " and confirmTime >=to_date('"+okTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and confirmTime <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String shipTimeStart = crm.getString("shipTimeStart","");
    	String shipTimeEnd = crm.getString("shipTimeEnd","");
    	if(StringUtils.isNotEmpty(shipTimeStart)){
    		hql += " and shipTime >=to_date('"+shipTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(shipTimeEnd)){
    		hql += " and shipTime <=to_date('"+shipTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	return hql;
    }

	public List getJpoCounterOrdersByCrmSum(CommonRecord crm) {
		String hql="select nvl(sum(amount),0) from JpoCounterOrder jpoCounterOrder where 1=1";

    	hql+=returnHql(crm);
		return this.getHibernateTemplate().find(hql);
	}
}
