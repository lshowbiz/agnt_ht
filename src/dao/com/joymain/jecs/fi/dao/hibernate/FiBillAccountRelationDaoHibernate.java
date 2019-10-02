
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiBillAccountRelation;
import com.joymain.jecs.fi.dao.FiBillAccountRelationDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiBillAccountRelationDaoHibernate extends BaseDaoHibernate implements FiBillAccountRelationDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountRelationDao#getFiBillAccountRelations(com.joymain.jecs.fi.model.FiBillAccountRelation)
     */
    public List getFiBillAccountRelations(final FiBillAccountRelation fiBillAccountRelation) {
        return getHibernateTemplate().find("from FiBillAccountRelation");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBillAccountRelation == null) {
            return getHibernateTemplate().find("from FiBillAccountRelation");
        } else {
            // filter on properties set in the fiBillAccountRelation
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBillAccountRelation).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBillAccountRelation.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountRelationDao#getFiBillAccountRelation(Long relationId)
     */
    public FiBillAccountRelation getFiBillAccountRelation(final Long relationId) {
        FiBillAccountRelation fiBillAccountRelation = (FiBillAccountRelation) getHibernateTemplate().get(FiBillAccountRelation.class, relationId);
        if (fiBillAccountRelation == null) {
            log.warn("uh oh, fiBillAccountRelation with relationId '" + relationId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBillAccountRelation.class, relationId);
        }

        return fiBillAccountRelation;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountRelationDao#saveFiBillAccountRelation(FiBillAccountRelation fiBillAccountRelation)
     */    
    public void saveFiBillAccountRelation(final FiBillAccountRelation fiBillAccountRelation) {
        getHibernateTemplate().saveOrUpdate(fiBillAccountRelation);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBillAccountRelationDao#removeFiBillAccountRelation(Long relationId)
     */
    public void removeFiBillAccountRelation(final Long relationId) {
        getHibernateTemplate().delete(getFiBillAccountRelation(relationId));
    }
    //added for getFiBillAccountRelationsByCrm
    public List getFiBillAccountRelationsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBillAccountRelation fiBillAccountRelation where 1=1";
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
			hql += " order by relationId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * 根据省市区三个条件查询商户list,用于验证是否重叠
	 * @param mfiBillAccountRelation
	 * @return 列表
	 */
	@Override
	public List getFiBillAccountRelationsByConditions(FiBillAccountRelation fiBillAccountRelation) {
		
		String hql="from FiBillAccountRelation t where 1=1";
		
		if(StringUtils.isNotEmpty(fiBillAccountRelation.getDistrict())){
			
			hql+=" and t.district='"+fiBillAccountRelation.getDistrict()+"'";
		}
		
		if(StringUtils.isNotEmpty(fiBillAccountRelation.getCity())){
			
			hql+=" and t.city='"+fiBillAccountRelation.getCity()+"'";
		}else{
			
			hql+=" and t.city=null";
		}
		
		if(StringUtils.isNotEmpty(fiBillAccountRelation.getProvince())){
			
			hql+=" and t.province='"+fiBillAccountRelation.getProvince()+"'";
		}else{
			
			hql+=" and t.province=null";
		}
		
		return this.findObjectsByHqlQuery(hql);
	}
	
	/**
	 * 根据条件匹配一个对应的商户,用于快钱支付时候选择商户号
	 * @param mfiBillAccountRelation
	 * @return 商户对象
	 */
	@Override
	public FiBillAccountRelation getFiBillAccountRelationByConditions(FiBillAccountRelation mfiBillAccountRelation) {
		
		//第一种情况，匹配区
		List list = this.getFiBillAccountRelationsByConditions(mfiBillAccountRelation);
		if(list.size()==1){
			
			return (FiBillAccountRelation) list.get(0);
		}else{
			
			//第2种情况,匹配市
			mfiBillAccountRelation.setDistrict(null);
			List list2 = this.getFiBillAccountRelationsByConditions(mfiBillAccountRelation);
			
			if(list2.size()==1){
				
				return (FiBillAccountRelation) list2.get(0);
			}else{
				
				//第3种情况，匹配省
				mfiBillAccountRelation.setCity(null);
				List list3 = this.getFiBillAccountRelationsByConditions(mfiBillAccountRelation);
				
				if(list3.size()==1){
					
					return (FiBillAccountRelation) list3.get(0);
				}
			}
		}
		
		return null;
	}

	/**
     * 根据商户号查询对应关系
     * @param billAccountCode
     * @return
     */
	@Override
	public List getFiBillAccountRelationsByBillAccountCode(
			String billAccountCode) {
		
		String hql="from FiBillAccountRelation where fiBillAccount.billAccountCode='"+billAccountCode+"' order by province,city";
		
		return this.findObjectsByHqlQuery(hql);
	}
}
