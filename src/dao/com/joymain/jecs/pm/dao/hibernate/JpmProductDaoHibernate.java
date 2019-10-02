package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.dao.JpmProductDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmProductDaoHibernate extends BaseDaoHibernate implements JpmProductDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductDao#getJpmProducts(com.joymain.jecs.pm.model.JpmProduct)
     */
    public List getJpmProducts(final JpmProduct jpmProduct) {
        return getHibernateTemplate().find("from JpmProduct");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProduct == null) {
            return getHibernateTemplate().find("from JpmProduct");
        } else {
            // filter on properties set in the jpmProduct
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProduct).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProduct.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductDao#getJpmProduct(String
     *      productNo)
     */
    public JpmProduct getJpmProduct(final String productNo) {
        JpmProduct jpmProduct = (JpmProduct) getHibernateTemplate().get(JpmProduct.class, productNo);
        //        if (jpmProduct == null) {
        //            log.warn("uh oh, jpmProduct with productNo '" + productNo + "' not found...");
        //            throw new ObjectRetrievalFailureException(JpmProduct.class, productNo);
        //        }

        return jpmProduct;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductDao#saveJpmProduct(JpmProduct
     *      jpmProduct)
     */
    public void saveJpmProduct(final JpmProduct jpmProduct) {
        getHibernateTemplate().saveOrUpdate(jpmProduct);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductDao#removeJpmProduct(String
     *      productNo)
     */
    public void removeJpmProduct(final String productNo) {
        getHibernateTemplate().delete(getJpmProduct(productNo));
    }

    //added for getJpmProductsByCrm
    public List getJpmProductsByCrm(CommonRecord crm, Pager pager) {
        String hql = "from JpmProduct pmProduct where 1=1";
        /**
         * ---example---
         * String userCode = crm.getString("userCode", "");
         * if(StringUtils.isNotEmpty(userCode)){
         * hql += "???????????";
         * }
         ***/

        String productName = crm.getString("productName", "");
        if (StringUtils.isNotEmpty(productName)) {
            hql += " and pmProduct.productName like '%" + productName.trim() + "%'";
        }

        //Modify By WuCF 20140827 分公司编码
        String companyCode = crm.getString("companyCode", "");
        if (StringUtils.isNotEmpty(companyCode)) {
            hql += " and exists(select 1 from JpmProductSaleNew jpsn where jpsn.jpmProduct.productNo=pmProduct.productNo and jpsn.companyCode='" + companyCode + "' )";
        }
        
        String smNo = crm.getString("smNo", "");
        if (StringUtils.isNotEmpty(smNo)) {
            hql += " and pmProduct.smNo='" + smNo + "'";
        }

        String combineFlag = crm.getString("combineFlag", "");

        if (StringUtils.isNotEmpty(combineFlag)) {
            if ("1".equals(combineFlag)) {
                hql += " and pmProduct.combineFlag='1'";
            } else {
                hql += " and (pmProduct.combineFlag='0' or pmProduct.combineFlag is null) ";
            }

        }

        /*	String saleNo = crm.getString("saleNo","");
        	if(StringUtils.isNotEmpty(saleNo)){
        		hql += " and pmProduct.saleNo='"+saleNo+"'";
        	}*/

        String productCategory = crm.getString("productCategory", "");
        if (StringUtils.isNotEmpty(productCategory)) {
            hql += " and pmProduct.productCategory='" + productCategory + "'";
        }

        String productProvider = crm.getString("productProvider", "");
        if (StringUtils.isNotEmpty(productProvider)) {
            hql += " and pmProduct.productProvider='" + productProvider + "'";
        }

        String productNo = crm.getString("productNo", "");
        if (StringUtils.isNotEmpty(productNo)) {
            hql += " and pmProduct.productNo like '%" + productNo.trim() + "%'";
        }
        
        String erpProductNo = crm.getString("erpProductNo", "");
        if (StringUtils.isNotEmpty(erpProductNo)) {
            hql += " and pmProduct.erpProductNo like '%" + erpProductNo.trim() + "%'";
        }

        if (pager == null) {
            hql += " order by pmProduct.productNo";
            return this.getHibernateTemplate().find(hql);
        }
        // 
        if (!pager.getLimit().getSort().isSorted()) {
            //sort
            hql += " order by productNo desc";
        } else {
            hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }

    //added for getJpmProductsByCrm
    public List getJpmProductsByCrm2(CommonRecord crm, Pager pager) {
        //    	String hql = "from JpmProduct jpmProductSaleNew where 1=1 ";
        //		return this.findObjectsByHqlQuery(hql, pager);
        String hql = "from JpmProductSaleNew pmProductSaleNew where pmProductSaleNew.status!='0' ";

        String companyCode = crm.getString("companyCode", "");
        if (StringUtils.isNotEmpty(companyCode)) {
            hql += " and pmProductSaleNew.companyCode='" + companyCode + "' ";
        }

        String productNo = crm.getString("productNo", "");
        if (StringUtils.isNotEmpty(productNo)) {
            hql += " and pmProductSaleNew.jpmProduct.productNo='" + productNo + "' ";
        }

        String productName = crm.getString("productName", "");
        if (StringUtils.isNotEmpty(productName)) {
            hql += " and pmProductSaleNew.productName like '%" + productName + "%' ";
        }

        //关于团队的管理
        String uniNo = crm.getString("uniNo", "");
        if (StringUtils.isNotEmpty(uniNo)) {
            hql += " and pmProductSaleNew.productName like '%" + productName + "%' ";
        }

        //道和酒业模板选择商品时增加的查询条件
        String productCategory = crm.getString("productCategory", "");
        if (StringUtils.isNotEmpty(productCategory)) {
            hql += " and pmProductSaleNew.jpmProduct.productCategory='" + productCategory + "'";
        }

        if (pager == null) {
            hql += " order by pmProductSaleNew.jpmProduct.productNo ";
            return this.getHibernateTemplate().find(hql);
        }
        // 
        if (!pager.getLimit().getSort().isSorted()) {
            //sort
            hql += " order by pmProductSaleNew.jpmProduct.productNo desc";
        } else {
            hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
        }
        return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * 判断商品是否是组合商品
	 * @author fx 2015-08-11
	 * @param productNo
	 * @return list
	 */
     public boolean getCombinationJudgmentResult(String productNo){
    	 if(!StringUtil.isEmpty(productNo)){
    		 String hql = " from JpmProduct pmProduct where 1=1 and pmProduct.combineFlag='1' and pmProduct.productNo='"+productNo+"' ";
    		 List list = this.findObjectsByHqlQuery(hql);
    		 if(null!=list){
    			 if(list.size()>0){
    				 return true;
    			 }
    		 }
    		 return false;
    	 }
    	 return false;
     }

}
