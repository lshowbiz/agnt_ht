
package com.joymain.jecs.pr.dao.hibernate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.dao.JprRefundListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JprRefundListDaoHibernate extends BaseDaoHibernate implements JprRefundListDao {

    /**
     * @see com.joymain.jecs.pr.dao.JprRefundListDao#getJprRefundLists(com.joymain.jecs.pr.model.JprRefundList)
     */
    public List getJprRefundLists(final JprRefundList jprRefundList) {
        return getHibernateTemplate().find("from JprRefundList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jprRefundList == null) {
            return getHibernateTemplate().find("from JprRefundList");
        } else {
            // filter on properties set in the jprRefundList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jprRefundList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JprRefundList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pr.dao.JprRefundListDao#getJprRefundList(Long prlId)
     */
    public JprRefundList getJprRefundList(final Long prlId) {
        JprRefundList jprRefundList = (JprRefundList) getHibernateTemplate().get(JprRefundList.class, prlId);
        if (jprRefundList == null) {
            log.warn("uh oh, jprRefundList with prlId '" + prlId + "' not found...");
            throw new ObjectRetrievalFailureException(JprRefundList.class, prlId);
        }

        return jprRefundList;
    }

    /**
     * @see com.joymain.jecs.pr.dao.JprRefundListDao#saveJprRefundList(JprRefundList jprRefundList)
     */    
    public void saveJprRefundList(final JprRefundList jprRefundList) {
        getHibernateTemplate().saveOrUpdate(jprRefundList);
    }

    /**
     * @see com.joymain.jecs.pr.dao.JprRefundListDao#removeJprRefundList(Long prlId)
     */
    public void removeJprRefundList(final Long prlId) {
        getHibernateTemplate().delete(getJprRefundList(prlId));
    }
    //added for getJprRefundListsByCrm
    public List getJprRefundListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JprRefundList jprRefundList where 1=1";
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
			hql += " order by prlId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	@Override
	public List<JprRefundList> getRefundListByMolId(Long molId,String roNo) {
		String hql = "from JprRefundList t where t.molId ="+molId;
		if(!StringUtil.isEmpty(roNo)){
			   hql += " and t.jprRefund.roNo <> '"+roNo+"'";
		}
		return getHibernateTemplate().find(hql);
	}
	
	/**
     * 查询退货明细
     * @author fu 2015-09-11
     * @param jprRefund
     * @return set 
     */
    public Set getRefundListForRono(JprRefund jprRefund,Set jprRefundSet){
    	Set jprRefundSetDba = new HashSet(0);
    	String roNo = jprRefund.getRoNo();
    	String sql = "select * from jpr_refund_List where ro_no = '"+roNo+"'";
    	List listJpr = this.findObjectsBySQL(sql);
    	for(int i=0;i<listJpr.size();i++){
    		Map map = (Map) listJpr.get(i);
    		String productId = (String) map.get("product_id");
    		String qty = (String) map.get("qty");
			Iterator it = jprRefundSet.iterator();
			while(it.hasNext()){
				JprRefundList jprRefundList = (JprRefundList) it.next();
				String pttId = jprRefundList.getJpmProductSaleTeamType().getPttId().toString();
				if(productId.equals(pttId)){
					jprRefundList.setQty(Integer.parseInt(qty));
					jprRefundSetDba.add(jprRefundList);
					break;
				}
			}
    	}
    	return jprRefundSetDba;
    }

	public void removeJprRefundListBySql(String roNo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(200);
		sb.append(" delete from jpr_refund_list s ");
		sb.append(" where s.RO_NO='"+roNo+"' ");
		
		this.jdbcTemplate.execute(sb.toString());
	}
    
}
