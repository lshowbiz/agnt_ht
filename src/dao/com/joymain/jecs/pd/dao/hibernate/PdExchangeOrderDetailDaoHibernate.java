
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class PdExchangeOrderDetailDaoHibernate extends BaseDaoHibernate implements PdExchangeOrderDetailDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao#getPdExchangeOrderDetails(com.joymain.jecs.pd.model.PdExchangeOrderDetail)
     */
    public List getPdExchangeOrderDetails(final PdExchangeOrderDetail pdExchangeOrderDetail) {
        return getHibernateTemplate().find("from PdExchangeOrderDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdExchangeOrderDetail == null) {
            return getHibernateTemplate().find("from PdExchangeOrderDetail");
        } else {
            // filter on properties set in the pdExchangeOrderDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdExchangeOrderDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdExchangeOrderDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao#getPdExchangeOrderDetail(Long uniNo)
     */
    public PdExchangeOrderDetail getPdExchangeOrderDetail(final Long uniNo) {
        PdExchangeOrderDetail pdExchangeOrderDetail = (PdExchangeOrderDetail) getHibernateTemplate().get(PdExchangeOrderDetail.class, uniNo);
        if (pdExchangeOrderDetail == null) {
            log.warn("uh oh, pdExchangeOrderDetail with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdExchangeOrderDetail.class, uniNo);
        }

        return pdExchangeOrderDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao#savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail)
     */    
    public void savePdExchangeOrderDetail(final PdExchangeOrderDetail pdExchangeOrderDetail) {
        getHibernateTemplate().saveOrUpdate(pdExchangeOrderDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderDetailDao#removePdExchangeOrderDetail(Long uniNo)
     */
    public void removePdExchangeOrderDetail(final Long uniNo) {
        getHibernateTemplate().delete(getPdExchangeOrderDetail(uniNo));
    }
    //added for getPdExchangeOrderDetailsByCrm
    public List getPdExchangeOrderDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdExchangeOrderDetail pdExchangeOrderDetail where 1=1";
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
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * @author gw 2015-03-03
     * @param pdExchangeOrderDetail
     * @return pdExchangeOrderDetail
     */
	public PdExchangeOrderDetail getPdExchangeOrderDetailForEP(PdExchangeOrderDetail pdExchangeOrderDetail) {
		String eoNo = pdExchangeOrderDetail.getEoNo();
		String productNo = pdExchangeOrderDetail.getProductNo();
		if((!StringUtil.isEmpty(eoNo))&&(!StringUtil.isEmpty(productNo))){
			String hql = " from PdExchangeOrderDetail pdExchangeOrderDetail where pdExchangeOrderDetail.eoNo='"+eoNo+"' and pdExchangeOrderDetail.productNo='"+productNo+"' ";
			
			BigDecimal price = pdExchangeOrderDetail.getPrice();
			BigDecimal a = new BigDecimal(0);
			if(null!=price && price.compareTo(a)>=0){
				 hql  += " and pdExchangeOrderDetail.price = '"+price.toString()+"' ";
			}
			BigDecimal PV = pdExchangeOrderDetail.getPv();
			BigDecimal b = new BigDecimal(0);
			if(null!=price && PV.compareTo(b)>=0){
				 hql  += " and pdExchangeOrderDetail.pv = '"+PV.toString()+"' ";
			}
			List<PdExchangeOrderDetail> list = this.findObjectsByHqlQuery(hql);
			if(null!=list){
				if(list.size()>0){
					return list.get(0);
				}
			}
		}
		return null;
	}
}
