
package com.joymain.jecs.fi.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FoundationOrderDao;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("rawtypes")
public class FoundationOrderDaoHibernate extends BaseDaoHibernate implements FoundationOrderDao {

    /**
     * @see com.joymain.jecs.fi.dao.FoundationOrderDao#getFoundationOrders(com.joymain.jecs.fi.model.FoundationOrder)
     */
	public List getFoundationOrders(final FoundationOrder foundationOrder) {
        return getHibernateTemplate().find("from FoundationOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (foundationOrder == null) {
            return getHibernateTemplate().find("from FoundationOrder");
        } else {
            // filter on properties set in the foundationOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(foundationOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FoundationOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FoundationOrderDao#getFoundationOrder(Long orderId)
     */
    public FoundationOrder getFoundationOrder(final Long orderId) {
        FoundationOrder foundationOrder = (FoundationOrder) getHibernateTemplate().get(FoundationOrder.class, orderId);
        if (foundationOrder == null) {
            log.warn("uh oh, foundationOrder with orderId '" + orderId + "' not found...");
            throw new ObjectRetrievalFailureException(FoundationOrder.class, orderId);
        }

        return foundationOrder;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FoundationOrderDao#saveFoundationOrder(FoundationOrder foundationOrder)
     */    
    public void saveFoundationOrder(final FoundationOrder foundationOrder) {
        getHibernateTemplate().saveOrUpdate(foundationOrder);
    }
    
    /**
     * @see com.joymain.jecs.fi.dao.FoundationOrderDao#saveFoundationOrder(FoundationOrder foundationOrder)
     */    
    public Long saveFoundationOrder2(final FoundationOrder foundationOrder) {
        return (Long)getHibernateTemplate().save(foundationOrder);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FoundationOrderDao#removeFoundationOrder(Long orderId)
     */
    public void removeFoundationOrder(final Long orderId) {
        getHibernateTemplate().delete(getFoundationOrder(orderId));
    }
    //added for getFoundationOrdersByCrm
    public List getFoundationOrdersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FoundationOrder foundationOrder where 1=1";
    	
    	//会员编号
    	String userCode = crm.getString("userCode",""); 
    	if(StringUtils.isNotEmpty(userCode)){
    		hql += " and foundationOrder.jmiMember.userCode='"+userCode.trim()+"' ";
    	}
    	
    	//支付类型
    	String payType = crm.getString("payType",""); 
    	if(StringUtils.isNotEmpty(payType)){
    		hql += " and foundationOrder.payType='"+payType.trim()+"' ";
    	}
    	
    	//状态
    	String status = crm.getString("status",""); 
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and foundationOrder.status='"+status.trim()+"' ";
    	}
    	
    	//状态
    	String fiId = crm.getString("fiId",""); 
    	if(StringUtils.isNotEmpty(fiId)){
    		hql += " and foundationOrder.foundationItem.fiId='"+fiId.trim()+"' ";
    	}
//    	startTime  支付时间
    	//起始时间
    	String createTimeStart = crm.getString("createTimeStart",""); 
    	if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and foundationOrder.startTime >= to_date ('" + createTimeStart
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
    	
    	//截止时间
    	String createTimeEnd = crm.getString("createTimeEnd",""); 
    	if (StringUtils.isNotEmpty(createTimeEnd)) {
    		hql += " and foundationOrder.startTime <= to_date ('" + createTimeEnd
			+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
    	
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by orderId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		} 
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
  //added for getFoundationOrdersByCrm
    public List foundationItemsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FoundationItem foundationItem where 1=1 ";
    	
    	//状态
    	String status = crm.getString("status",""); 
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and foundationItem.status='"+status.trim()+"' ";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by foundationItem.fiId ";
		} else {
//			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		} 
		return this.findObjectsByHqlQuery(hql);
    }
    
    //查询会员在过去的1年里面参加的爱心365活动的订单
    public List getOrdersByItemTypeAndTime(String userCode){
    		
    	String sql = "select * from FOUNDATION_ORDER where STATUS='1' and USER_CODE='"+userCode+"'";
    	
    	sql += " and FI_ID=(select FI_ID from FOUNDATION_ITEM where TYPE='1') ";//项目类型为爱心365
    	
    	//往前推一年的时间
    	Calendar cal=Calendar.getInstance();//使用日历类
    	int year=cal.get(Calendar.YEAR);//得到年
    	int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
    	int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
    	year--;
    	
    	String lastYearToday=year+"-"+month+"-"+day;
    	
    	sql += " and CREATE_TIME >= to_date ('" + lastYearToday + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
    	
    	return this.jdbcTemplate.queryForList(sql);
    }
}
