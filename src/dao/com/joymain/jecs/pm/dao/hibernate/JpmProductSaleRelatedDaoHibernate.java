
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmProductSaleRelatedDaoHibernate extends BaseDaoHibernate implements JpmProductSaleRelatedDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao#getJpmProductSaleRelateds(com.joymain.jecs.pm.model.JpmProductSaleRelated)
     */
    public List getJpmProductSaleRelateds(final JpmProductSaleRelated jpmProductSaleRelated) {
        return getHibernateTemplate().find("from JpmProductSaleRelated");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductSaleRelated == null) {
            return getHibernateTemplate().find("from JpmProductSaleRelated");
        } else {
            // filter on properties set in the jpmProductSaleRelated
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductSaleRelated).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductSaleRelated.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao#getJpmProductSaleRelated(Long id)
     */
    public JpmProductSaleRelated getJpmProductSaleRelated(final Long id) {
        JpmProductSaleRelated jpmProductSaleRelated = (JpmProductSaleRelated) getHibernateTemplate().get(JpmProductSaleRelated.class, id);
        if (jpmProductSaleRelated == null) {
            log.warn("uh oh, jpmProductSaleRelated with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductSaleRelated.class, id);
        }

        return jpmProductSaleRelated;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao#saveJpmProductSaleRelated(JpmProductSaleRelated jpmProductSaleRelated)
     */    
    public void saveJpmProductSaleRelated(final JpmProductSaleRelated jpmProductSaleRelated) {
        getHibernateTemplate().saveOrUpdate(jpmProductSaleRelated);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleRelatedDao#removeJpmProductSaleRelated(Long id)
     */
    public void removeJpmProductSaleRelated(final Long id) {
        getHibernateTemplate().delete(getJpmProductSaleRelated(id));
    }
    //added for getJpmProductSaleRelatedsByCrm
    public List getJpmProductSaleRelatedsByCrm(CommonRecord crm, Pager pager){
//    	String sql="select s.grade,s.id,s.card_no,m.user_code,m.mi_user_name,m.papernumber,o.member_order_no," +
//		"o.amount,o.discount_amount,p.state_province_name,c.city_name,d.district_name,o.address,o.mobiletele " +
//		"from jpm_card_seq s, jmi_member m, jpo_member_order o left join jal_state_province p on p.state_province_id = o.province " +
//		"left join jal_city c on c.city_id = o.city left join jal_district d on d.district_id = o.district where s.create_time >= " +
//		"to_date('2013-1-19 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
//		"and s.create_time < to_date('2013-2-9 00:00:00', 'yyyy-mm-dd hh24:mi:ss') and" +
//		" s.user_code = m.user_code and o.member_order_no = s.member_order_no";
    	
//    	String hql = "select jpr.uniNo,jpr.relationUniNo,jpsn.productNo,jpsn.productName " +
//    			     " from JpmProductSaleRelated jpr left join JpmProductSaleNew jpsn on jpr.uniNo=jpsn.uniNo where 1=1 ";

    	
    	String hql = " select jpsr " + 
//    			" ,jpsn.productName as productName, (select t.productName from JpmProductSaleNew t where t.uniNo=jpsr.relationUniNo) as relationProductName " +
	     " from JpmProductSaleRelated jpsr,JpmProductSaleNew jpsn where jpsr.uniNo=jpsn.uniNo  ";

		String uniNo = crm.getString("uniNo", "");
		if(StringUtils.isNotEmpty(uniNo)){
			hql += " and jpsr.uniNo='"+uniNo+"' ";
		}
	
		String relatedproductNo = crm.getString("relatedproductNo","");
    	if(StringUtils.isNotEmpty(relatedproductNo)){
    		hql += " and jpsr.relationJpmProductSaleNew.jpmProduct.productNo='"+relatedproductNo.trim()+"' ";
    	}
    	
    	String productName = crm.getString("productName","");
    	if(StringUtils.isNotEmpty(productName)){
    		hql += " and jpsr.relationJpmProductSaleNew.productName like '%"+productName.trim()+"%' ";
    	} 
    	
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and jpsr.status='"+status.trim()+"' ";
    	}
		
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jpsr.relationUniNo desc "; 
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
	public boolean isExist(CommonRecord crm, String type) {
		boolean isExist = false;
		String hql = "from JpmProductSaleRelated jpmProductSaleRelated where 1=1 ";
		
		//如果是禁用的,也算是存在
//    	String state = crm.getString("state","");
//    	if(StringUtils.isNotEmpty(state)){
//    		hql += " and jpmProductSaleTeamType.state='"+state.trim()+"' ";
//    	} 
		String uniNo = crm.getString("uniNo","");
		String relationUniNo = crm.getString("relationUniNo",""); 
		String id = crm.getString("id",""); 
		if(StringUtils.isNotEmpty(uniNo)){
    		hql += " and jpmProductSaleRelated.jpmProductSaleNew.uniNo='"+uniNo.trim()+"' ";
    	}
    	
    	if(StringUtils.isNotEmpty(relationUniNo)){
    		hql += " and jpmProductSaleRelated.relationJpmProductSaleNew.uniNo='"+relationUniNo.trim()+"' ";
    	} 
    	 
    	if("1".equals(type)){
    		hql += " and jpmProductSaleRelated.id!='"+id.trim()+"' ";
    	}
    	
    	List list = this.findObjectsByHqlQuery(hql);
    	if(list!=null && list.size()>0){
    		isExist = true;
    	}    
		return isExist;
	}
	
	
	//add by lihao 
	public List getJpmProductSaleRelatedsByCrmSql(CommonRecord crm, Pager pager){
		/*
		String sql = " select jpsr.id as id,jpsn.company_Code as companycode,jpsr.uni_No as unino,"
    			+" jpsr.relation_Uni_No as relationunino,jpsn.product_name as productname,jpsr.status as status "
    			+" FROM jpm_product_sale_related jpsr, jpm_product_sale_new jpsn1,jpm_product_sale_new jpsn2 "
    			+" WHERE JPSR.UNI_NO = JPSN1.UNI_NO and  JPSR.UNI_NO = JPSN2.UNI_NO";*/
		
		String sql = " select jpsr.id as id,jpsn1.company_Code as companycode,jpsn1.product_no as productno,"
				+" jpsn2.product_no as relationproductno ,jpsn2.product_name as relationproductname ,jpsr.status as status "
    			+" FROM jpm_product_sale_related jpsr, jpm_product_sale_new jpsn1,jpm_product_sale_new jpsn2 "
    			+" WHERE JPSR.UNI_NO = JPSN1.UNI_NO and  JPSR.RELATION_UNI_NO = JPSN2.UNI_NO";
		
		
		String uniNo = crm.getString("uniNo", "");
		if(StringUtils.isNotEmpty(uniNo)){
			sql += " and jpsr.uni_No='"+uniNo+"' ";
		}
	
		String relatedproductNo = crm.getString("relatedproductNo","");
    	if(StringUtils.isNotEmpty(relatedproductNo)){
    		sql += " and jpsn2.product_no='"+relatedproductNo.trim()+"' ";
    	}
    	
    	String productName = crm.getString("productName","");
    	if(StringUtils.isNotEmpty(productName)){
    		sql += " and jpsn2.product_name like '%"+productName.trim()+"%' ";
    	} 
    	
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		sql += " and jpsr.status='"+status.trim()+"' ";
    	}
		
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sql += " order by jpsr.relation_Uni_No desc "; 
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}

}
