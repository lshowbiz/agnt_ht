
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.pd.dao.PdNotChangeProductDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;



public class PdNotChangeProductDaoHibernate extends BaseDaoHibernate implements PdNotChangeProductDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdNotChangeProductDao#getPdNotChangeProducts(com.joymain.jecs.pd.model.PdNotChangeProduct)
     */
    public List getPdNotChangeProducts(final PdNotChangeProduct pdNotChangeProduct) {
        return getHibernateTemplate().find("from PdNotChangeProduct");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdNotChangeProduct == null) {
            return getHibernateTemplate().find("from PdNotChangeProduct");
        } else {
            // filter on properties set in the pdNotChangeProduct
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdNotChangeProduct).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdNotChangeProduct.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdNotChangeProductDao#getPdNotChangeProduct(Long id)
     */
    public PdNotChangeProduct getPdNotChangeProduct(final Long id) {
        PdNotChangeProduct pdNotChangeProduct = (PdNotChangeProduct) getHibernateTemplate().get(PdNotChangeProduct.class, id);
        if (pdNotChangeProduct == null) {
            log.warn("uh oh, pdNotChangeProduct with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(PdNotChangeProduct.class, id);
        }

        return pdNotChangeProduct;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdNotChangeProductDao#savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct)
     */    
    public void savePdNotChangeProduct(final PdNotChangeProduct pdNotChangeProduct) {
        getHibernateTemplate().saveOrUpdate(pdNotChangeProduct);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdNotChangeProductDao#removePdNotChangeProduct(Long id)
     */
    public void removePdNotChangeProduct(final Long id) {
        getHibernateTemplate().delete(getPdNotChangeProduct(id));
    }
    
    //added for getPdNotChangeProductsByCrm
    public List getPdNotChangeProductsByCrm(CommonRecord crm, Pager pager){
    	/*String hql = "from PdNotChangeProduct pdNotChangeProduct where 1=1";
    	String companyCode = crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		hql += " and companyCode = '"+companyCode+"'";
    	}
    	
    	String productName = crm.getString("productName", "");
    	if(!StringUtil.isEmpty(productName)){
    		hql += " and pdNotChangeProduct.JpmProduct.productName like '%"+companyCode+"%'";
    	}
    	
    	String isAvailable = crm.getString("isAvailable", "");
    	if(!StringUtil.isEmpty(isAvailable)){
    		hql += " and isAvailable = '"+companyCode+"'";
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		
		return this.findObjectsByHqlQuery(hql, pager);*/
    	String sql = "select a.id,a.product_no productNo,a.team_code teamCode,a.order_type orderType,a.company_code companyCode,a.create_user_code createUserCode,a.create_time createTime,a.is_available isAvailable,b.product_name productName from pd_not_change_product a,jpm_product b  where a.product_no = b.product_no ";
    	String companyCode = crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		sql += " and a.company_code = '"+companyCode+"'";
    	}
    	
    	String productNo = crm.getString("productNo", "");
    	if(!StringUtil.isEmpty(productNo)){
    		sql += " and a.product_no = '"+productNo+"'";
    	}
    	
    	String productName = crm.getString("productName", "");
    	if(!StringUtil.isEmpty(productName)){
    		sql += " and b.product_name like '%"+productName+"%'";
    	}
    	
    	sql += " order by a.id desc";
    	return this.findObjectsBySQL(sql, pager);
    }
    
    /**
     * 根据商品编号获取不可换商品的对象
     * @author fu 2016-09-27
     * @param productNo
     * @return 
     */
	public PdNotChangeProduct getpdNotChangeProductByProductNo(String productNo){
		String hql = "from PdNotChangeProduct pdNotChangeProduct where pdNotChangeProduct.productNo='"+productNo+"'";
		List<PdNotChangeProduct> list = this.findObjectsByHqlQuery(hql);
		if((null!=list)&&list.size()>0){
			PdNotChangeProduct pdNotChangeProduct = list.get(0);
			return pdNotChangeProduct;
		}
		
		return null;
	}
	
}
