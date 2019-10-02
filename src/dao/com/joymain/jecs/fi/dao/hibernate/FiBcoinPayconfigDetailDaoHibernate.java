
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiBcoinPayconfigDetailDaoHibernate extends BaseDaoHibernate implements FiBcoinPayconfigDetailDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao#getFiBcoinPayconfigDetails(com.joymain.jecs.fi.model.FiBcoinPayconfigDetail)
     */
    public List getFiBcoinPayconfigDetails(final FiBcoinPayconfigDetail fiBcoinPayconfigDetail) {
        return getHibernateTemplate().find("from FiBcoinPayconfigDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBcoinPayconfigDetail == null) {
            return getHibernateTemplate().find("from FiBcoinPayconfigDetail");
        } else {
            // filter on properties set in the fiBcoinPayconfigDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBcoinPayconfigDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBcoinPayconfigDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao#getFiBcoinPayconfigDetail(Long detailId)
     */
    public FiBcoinPayconfigDetail getFiBcoinPayconfigDetail(final Long detailId) {
        FiBcoinPayconfigDetail fiBcoinPayconfigDetail = (FiBcoinPayconfigDetail) getHibernateTemplate().get(FiBcoinPayconfigDetail.class, detailId);
        if (fiBcoinPayconfigDetail == null) {
            log.warn("uh oh, fiBcoinPayconfigDetail with detailId '" + detailId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBcoinPayconfigDetail.class, detailId);
        }

        return fiBcoinPayconfigDetail;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao#saveFiBcoinPayconfigDetail(FiBcoinPayconfigDetail fiBcoinPayconfigDetail)
     */    
    public void saveFiBcoinPayconfigDetail(final FiBcoinPayconfigDetail fiBcoinPayconfigDetail) {
        getHibernateTemplate().saveOrUpdate(fiBcoinPayconfigDetail);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao#removeFiBcoinPayconfigDetail(Long detailId)
     */
    public void removeFiBcoinPayconfigDetail(final Long detailId) {
        getHibernateTemplate().delete(getFiBcoinPayconfigDetail(detailId));
    }
    //added for getFiBcoinPayconfigDetailsByCrm
    public List getFiBcoinPayconfigDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBcoinPayconfigDetail fiBcoinPayconfigDetail where 1=1";
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

	@Override
	public List getFiBcoinPayconfigDetailsByConfigId(String configId) {
		
		String hqlQuery = "from FiBcoinPayconfigDetail fiBcoinPayconfigDetail where fiBcoinPayconfigDetail.configId="+configId;
		return this.findObjectsByHqlQuery(hqlQuery);
	}

	/**
     * 查询换购明细
     * @param configId
     * @param productNo
     * @return
     */
	@Override
	public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailsByProductNo(String configId, String productNo) {
		
		if(StringUtils.isNotEmpty(configId) && StringUtils.isNotEmpty(productNo)){
			
			String hql ="from FiBcoinPayconfigDetail fiBcoinPayconfigDetail where configId="+configId+" and productNo='"+productNo+"'";
			
			try{
				
				return (FiBcoinPayconfigDetail) this.getObjectByHqlQuery(hql);
			}catch(ClassCastException ex){
				return null;
			}
		}else{
			
			return null;
		}		
	}

	@Override
	public void saveFiBcoinPayconfigDetails(
			List<FiBcoinPayconfigDetail> detailTemps) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdateAll(detailTemps);
	}
}
