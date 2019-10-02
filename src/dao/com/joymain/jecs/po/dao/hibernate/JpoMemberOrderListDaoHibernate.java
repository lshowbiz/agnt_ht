package com.joymain.jecs.po.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoMemberOrderListDaoHibernate extends BaseDaoHibernate implements
    JpoMemberOrderListDao
{
    
    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListDao#getJpoMemberOrderLists(com.joymain.jecs.po.model.JpoMemberOrderList)
     */
    public List getJpoMemberOrderLists(final JpoMemberOrderList jpoMemberOrderList)
    {
        return getHibernateTemplate().find("from JpoMemberOrderList");
        
        /*
         * Remove the line above and uncomment this code block if you want to
         * use Hibernate's Query by Example API. if (jpoMemberOrderList == null)
         * { return getHibernateTemplate().find("from JpoMemberOrderList"); }
         * else { // filter on properties set in the jpoMemberOrderList
         * HibernateCallback callback = new HibernateCallback() { public Object
         * doInHibernate(Session session) throws HibernateException { Example ex
         * =
         * Example.create(jpoMemberOrderList).ignoreCase().enableLike(MatchMode
         * .ANYWHERE); return
         * session.createCriteria(JpoMemberOrderList.class).add(ex).list(); } };
         * return (List) getHibernateTemplate().execute(callback); }
         */
    }
    
    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListDao#getJpoMemberOrderList(Long
     *      molId)
     */
    public JpoMemberOrderList getJpoMemberOrderList(final Long molId)
    {
        JpoMemberOrderList jpoMemberOrderList =
            (JpoMemberOrderList) getHibernateTemplate().get(JpoMemberOrderList.class, molId);
        if (jpoMemberOrderList == null)
        {
            log.warn("uh oh, jpoMemberOrderList with molId '" + molId + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberOrderList.class, molId);
        }
        
        return jpoMemberOrderList;
    }
    
    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListDao#saveJpoMemberOrderList(JpoMemberOrderList
     *      jpoMemberOrderList)
     */
    public void saveJpoMemberOrderList(final JpoMemberOrderList jpoMemberOrderList)
    {
        getHibernateTemplate().saveOrUpdate(jpoMemberOrderList);
    }
    
    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderListDao#removeJpoMemberOrderList(Long
     *      molId)
     */
    public void removeJpoMemberOrderList(final Long molId)
    {
        getHibernateTemplate().delete(getJpoMemberOrderList(molId));
    }
    
    // added for getJpoMemberOrderListsByCrm
    public List getJpoMemberOrderListsByCrm(CommonRecord crm, Pager pager)
    {
        String hql = "from JpoMemberOrderList jpoMemberOrderList where 1=1";
        
        if (!pager.getLimit().getSort().isSorted())
        {
            // sort
            hql += " order by molId desc";
        }
        else
        {
            hql +=
                " ORDER BY " + pager.getLimit().getSort().getProperty() + " "
                    + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 根据外部参数生成查询语句
     * 
     * @param crm
     * @return
     */
    private String buildListHqlQuery(CommonRecord crm)
    {
        String hql = "";
        
        String companyCode = crm.getString("companyCode", "");
        if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode))
        {
            hql += " and companyCode='" + companyCode + "'";
        }
        
        String userCode = crm.getString("sysUser.userCode", "");
        if (!StringUtils.isEmpty(userCode))
        {
            hql += " and sysUser.userCode='" + userCode + "'";
        }
        
        return hql;
    }
    
    @Override
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String, Long> map)
    {
        StringBuffer hql =
            new StringBuffer("from JpoMemberOrderList j where j.molId = " + map.get("molId"));
        return (JpoMemberOrderList) this.getObjectByHqlQuery(hql.toString());
    }

	@Override
	public Long getProSumByProNo(String proNo) {
		String hql ="";
		
		if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO)){
			hql = "select sum(l.qty) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo=?";
		} else if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO1)){
			hql = "select sum(l.qty*3) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo=?";
		} else if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO2)){
			hql = "select sum(l.qty*5) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo=?";
		}
		
		Query query = getSession().createQuery(hql);
		query.setParameter(0, proNo);

		List list = query.list();
		if(list !=null && list.size()>0){
			return (Long)list.get(0);
		} else {
			return null;
		}
	}
	
	 /**
     * 根据商品编码和订单号查询订单下的商品信息
     * @author gw 2015-02-02
     * @param orderNo
     * @param getproductNo
     * @return jpoMemberOrderList
     */
	public JpoMemberOrderList getJpoMemberOrderListForOrderNoAndProductNo(String orderNo, String productNo) {
		String hql = " from JpoMemberOrderList jpoMemberOrderList where jpoMemberOrderList.jpoMemberOrder.memberOrderNo='"+orderNo+"'" +
				" and jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo='"+productNo+"' ";
		List<JpoMemberOrderList> list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	public Integer getJpoMemberOrderListForReturn(Long molId) {
		Integer qtyS = 0;
		String sql = "select qty from jpo_member_order_list where mol_id = '"+molId+"'";
		List<Map<String,BigDecimal>>  list = this.jdbcTemplate.queryForList(sql);
		if(null!=list && list.size()>0){
			for(Map<String,BigDecimal> map : list){
				BigDecimal a = map.get("qty");
				qtyS = Integer.parseInt(a.toString());
			}
		}
		return qtyS;
	}
	
	 /**
     * 库存清货数量限制
     * @param proNo
     * @param statetime
     * @param endtime
     * @return
     */
    public Integer getProSumByProNo(String proNo,String statetime,String endtime){
    	
    	Long num = 0l;
		
		String hql = "select sum(l.qty) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.isPay=1 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.productNo= '" + proNo +"' " ;
		if (!StringUtils.isEmpty(statetime)) {
			hql += "and l.jpoMemberOrder.submitTime>=to_date('"+statetime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (!StringUtils.isEmpty(endtime)) {
			hql += "and l.jpoMemberOrder.submitTime<=to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
		Query query = getSession().createQuery(hql);
		
		List list = query.list();
		if(list !=null && list.size()>0){
			num = (Long)list.get(0);
		} 
		
    	return num!=null?num.intValue():0;
    	
    }
}
