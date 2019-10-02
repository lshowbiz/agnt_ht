
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmProductSaleImageDao;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductSaleImageDaoHibernate extends BaseDaoHibernate implements JpmProductSaleImageDao {

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleImageDao#getJpmProductSaleImages(com.joymain.jecs.pm.model.JpmProductSaleImage)
	 */
	public List getJpmProductSaleImages(final JpmProductSaleImage jpmProductSaleImage) {
		return getHibernateTemplate().find("from JpmProductSaleImage");

		/* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductSaleImage == null) {
            return getHibernateTemplate().find("from JpmProductSaleImage");
        } else {
            // filter on properties set in the jpmProductSaleImage
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductSaleImage).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductSaleImage.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleImageDao#getJpmProductSaleImage(Long imageId)
	 */
	public JpmProductSaleImage getJpmProductSaleImage(final Long imageId) {
		JpmProductSaleImage jpmProductSaleImage = (JpmProductSaleImage) getHibernateTemplate().get(JpmProductSaleImage.class, imageId);
		if (jpmProductSaleImage == null) {
			log.warn("uh oh, jpmProductSaleImage with imageId '" + imageId + "' not found...");
			throw new ObjectRetrievalFailureException(JpmProductSaleImage.class, imageId);
		}

		return jpmProductSaleImage;
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleImageDao#saveJpmProductSaleImage(JpmProductSaleImage jpmProductSaleImage)
	 */    
	public void saveJpmProductSaleImage(final JpmProductSaleImage jpmProductSaleImage) {
		getHibernateTemplate().saveOrUpdate(jpmProductSaleImage);
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleImageDao#removeJpmProductSaleImage(Long imageId)
	 */
	public void removeJpmProductSaleImage(final Long imageId) {
		getHibernateTemplate().delete(getJpmProductSaleImage(imageId));
	}
	//added for getJpmProductSaleImagesByCrm
	public List getJpmProductSaleImagesByCrm(CommonRecord crm, Pager pager){
		String hql = "from JpmProductSaleImage jpmProductSaleImage where 1=1";
		 //select new JpmProductSaleImage(imageId,uniNo,imageLink,imageType,imageOrder,status,jpmProductSaleNew) 
		String cxFlag = crm.getString("cxFlag",""); 
		if("n".equals(cxFlag)){
			hql += " and 1=2 ";
		}else{	
			//查询条件
			String companyCode = crm.getString("companyCode","");
	    	if(StringUtils.isNotEmpty(companyCode) && !"AA".equals(companyCode)){
	    		hql += " and jpmProductSaleImage.jpmProductSaleNew.companyCode='"+companyCode+"' ";
	    	}
			
	    	String uniNo = crm.getString("uniNo","");
	    	if(StringUtils.isNotEmpty(uniNo)){
	    		hql += " and jpmProductSaleImage.uniNo='"+uniNo.trim()+"' ";
	    	}
	    	
			String productNo = crm.getString("productNo","");
	    	if(StringUtils.isNotEmpty(productNo)){
	    		hql += " and jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo='"+productNo.trim()+"' ";
	    	}
	    	
	    	String productName = crm.getString("productName","");
	    	if(StringUtils.isNotEmpty(productName)){
	    		hql += " and jpmProductSaleImage.jpmProductSaleNew.productName like '%"+productName.trim()+"%' ";
	    	}
	    	
	    	String imagetype = crm.getString("imagetype","");
	    	if(StringUtils.isNotEmpty(imagetype)){
	    		hql += " and jpmProductSaleImage.imageType='"+imagetype.trim()+"' ";
	    	} 
	    	
	    	String status = crm.getString("status","");
	    	if(StringUtils.isNotEmpty(status)){
	    		hql += " and jpmProductSaleImage.status='"+status.trim()+"' ";
	    	}
		}
		/*Query query = this.getSession().createQuery(hql);
		List list = query.list();
		System.out.println("list:"+list);*/
		//   
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jpmProductSaleImage.imageId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
	
	/**
     * 批量审核图片
     * @param uniNoStr:商品图片字符串：用逗号隔开
     * @return
     */
	public int batchAuditProductNews(String uniNoStr,String status) {
		StringBuffer sqlBuf = new StringBuffer();
		if(uniNoStr!=null){
			uniNoStr = uniNoStr.substring(0, uniNoStr.length()-1);
			sqlBuf.append(" update JpmProductSaleImage jpsi set jpsi.status='");
			sqlBuf.append(status);
			sqlBuf.append("' where jpsi.imageId in('");
			sqlBuf.append(uniNoStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		return this.executeUpdate(sqlBuf.toString());
	}
}
