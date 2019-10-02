
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class PdLogisticsBaseDetailDaoHibernate extends BaseDaoHibernate implements PdLogisticsBaseDetailDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao#getPdLogisticsBaseDetails(com.joymain.jecs.pd.model.PdLogisticsBaseDetail)
     */
    public List getPdLogisticsBaseDetails(final PdLogisticsBaseDetail pdLogisticsBaseDetail) {
        return getHibernateTemplate().find("from PdLogisticsBaseDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdLogisticsBaseDetail == null) {
            return getHibernateTemplate().find("from PdLogisticsBaseDetail");
        } else {
            // filter on properties set in the pdLogisticsBaseDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdLogisticsBaseDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdLogisticsBaseDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao#getPdLogisticsBaseDetail(BigDecimal detailId)
     */
    public PdLogisticsBaseDetail getPdLogisticsBaseDetail(final BigDecimal detailId) {
        PdLogisticsBaseDetail pdLogisticsBaseDetail = (PdLogisticsBaseDetail) getHibernateTemplate().get(PdLogisticsBaseDetail.class, detailId);
        if (pdLogisticsBaseDetail == null) {
            log.warn("uh oh, pdLogisticsBaseDetail with detailId '" + detailId + "' not found...");
            throw new ObjectRetrievalFailureException(PdLogisticsBaseDetail.class, detailId);
        }

        return pdLogisticsBaseDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao#savePdLogisticsBaseDetail(PdLogisticsBaseDetail pdLogisticsBaseDetail)
     */    
    public void savePdLogisticsBaseDetail(final PdLogisticsBaseDetail pdLogisticsBaseDetail) {
        getHibernateTemplate().saveOrUpdate(pdLogisticsBaseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao#removePdLogisticsBaseDetail(BigDecimal detailId)
     */
    public void removePdLogisticsBaseDetail(final BigDecimal detailId) {
        getHibernateTemplate().delete(getPdLogisticsBaseDetail(detailId));
    }
    //added for getPdLogisticsBaseDetailsByCrm
    public List getPdLogisticsBaseDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdLogisticsBaseDetail pdLogisticsBaseDetail where 1=1";
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
			hql += " order by detailId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 根据EC商品编码和物流状态接口物流信息表主键获取对象PdLogisticsBaseDetail
     * @author 2014-12-02
     * @param  numId 物流状态接口物流信息表主键
     * @param productNo
     * @return pdLogisticsBaseDetail
     */
	public PdLogisticsBaseDetail getPdLogisticsBaseDetailByInter(String numId,String productNo,BigDecimal price,String qty,String combination_product_no) {
		if((!StringUtil.isEmpty(numId))&&(!StringUtil.isEmpty(productNo))){
			/*String hql = " from PdLogisticsBaseDetail pdLogisticsBaseDetail where pdLogisticsBaseDetail.numId='"+numId+"' " +
			             " and pdLogisticsBaseDetail.PRODUCT_NO = '"+productNo+"'";
			*/
			String hql = " from PdLogisticsBaseDetail pdLogisticsBaseDetail where pdLogisticsBaseDetail.pdLogisticsBaseNum.numId='"+numId+"' " +
            " and pdLogisticsBaseDetail.product_no = '"+productNo+"' and pdLogisticsBaseDetail.price = '"+price+"' and pdLogisticsBaseDetail.qty = '"+qty+"' ";
			if(StringUtil.isEmpty(combination_product_no)){
				hql += " and pdLogisticsBaseDetail.combination_product_no is null ";
			}else{
				hql += " and pdLogisticsBaseDetail.combination_product_no = '"+combination_product_no+"'";
			}
			
	        List<PdLogisticsBaseDetail> list = this.findObjectsByHqlQuery(hql);
	        if(null!=list){
		        if(list.size()>0){
		        	return list.get(0);
		        }
		    }
		    return null;
		}else{
			return null;
		}
	}
	
	/**
     * 
     * @author gw 2015-01-26
     * @param pdLogisticsBaseNum
     * @return
     */
	public List<PdLogisticsBaseDetail> getPdLogisticsBaseDetailByInterList(PdLogisticsBaseNum pdLogisticsBaseNum) {
		if(null!=pdLogisticsBaseNum){
			String hql = " from PdLogisticsBaseDetail pdLogisticsBaseDetail where pdLogisticsBaseDetail.pdLogisticsBaseNum.numId='"+pdLogisticsBaseNum.getNumId().toString()+"'";
            return this.findObjectsByHqlQuery(hql);
		}
		return null;
	}
	
	
}
