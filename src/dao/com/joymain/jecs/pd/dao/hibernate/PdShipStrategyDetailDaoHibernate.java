
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdShipStrategyDetailDao;
import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdShipStrategyDetailDaoHibernate extends BaseDaoHibernate implements PdShipStrategyDetailDao {
	
	public List findArea(CommonRecord crm) {
		String companyCode = crm.getString("companyCode", "CN");
		String ssId = crm.getString("ssId", "1");
		String sql = " select jsp.state_province_id, jsp.state_province_name "
			+ " from jal_state_province jsp,jal_country jc where jsp.country_id=jc.country_id"
			+ " and jc.company_code= '" + companyCode + "' and jsp.state_province_id not in("
			+ " select ps.ship_area from pd_ship_strategy_detail ps where ps.ss_id = " + ssId + ")";
		return this.findObjectsBySQL(sql);
	}

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDetailDao#getPdShipStrategyDetails(com.joymain.jecs.pd.model.PdShipStrategyDetail)
     */
    public List getPdShipStrategyDetails(final PdShipStrategyDetail pdShipStrategyDetail) {
        return getHibernateTemplate().find("from PdShipStrategyDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdShipStrategyDetail == null) {
            return getHibernateTemplate().find("from PdShipStrategyDetail");
        } else {
            // filter on properties set in the pdShipStrategyDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdShipStrategyDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdShipStrategyDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDetailDao#getPdShipStrategyDetail(Long ssdId)
     */
    public PdShipStrategyDetail getPdShipStrategyDetail(final Long ssdId) {
        PdShipStrategyDetail pdShipStrategyDetail = (PdShipStrategyDetail) getHibernateTemplate().get(PdShipStrategyDetail.class, ssdId);
        if (pdShipStrategyDetail == null) {
            log.warn("uh oh, pdShipStrategyDetail with ssdId '" + ssdId + "' not found...");
            throw new ObjectRetrievalFailureException(PdShipStrategyDetail.class, ssdId);
        }

        return pdShipStrategyDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDetailDao#savePdShipStrategyDetail(PdShipStrategyDetail pdShipStrategyDetail)
     */    
    public void savePdShipStrategyDetail(final PdShipStrategyDetail pdShipStrategyDetail) {
        getHibernateTemplate().saveOrUpdate(pdShipStrategyDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDetailDao#removePdShipStrategyDetail(Long ssdId)
     */
    public void removePdShipStrategyDetail(final Long ssdId) {
        getHibernateTemplate().delete(getPdShipStrategyDetail(ssdId));
    }
    //added for getPdShipStrategyDetailsByCrm
    public List getPdShipStrategyDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdShipStrategyDetail pdShipStrategyDetail where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	String ssId = crm.getString("ssId", "");
    	if(StringUtils.isNotBlank(ssId)){
    		hql +=" and pdShipStrategyDetail.ssId ="+ssId;
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by ssdId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	/* (non-Javadoc)
	 * @see com.joymain.jecs.pd.dao.PdShipStrategyDetailDao#getPdShipStrategyDetail(java.lang.String, java.lang.String)
	 */
	public PdShipStrategyDetail getPdShipStrategyDetail(String ssId,
			String shipArea) {
		// TODO Auto-generated method stub
		String hql = "from PdShipStrategyDetail pdShipStrategyDetail where pdShipStrategyDetail.ssId = '"+ssId+"' and pdShipStrategyDetail.shipArea = "+shipArea;
		List list = this.findObjectsByHqlQuery(hql);
		if(list.size()>0){
			return (PdShipStrategyDetail) list.get(0);
		}else {
			return null;
		}
		
	}
	
	/**
     * 批量审核发货策略
     * @param uniNoStr:编码字符串：用逗号隔开
     * @return
     */
	public int batchAuditPdShipNews(String uniNoStr,String status) {
		StringBuffer sqlBuf = new StringBuffer();
		if(uniNoStr!=null){
			uniNoStr = uniNoStr.substring(0, uniNoStr.length()-1);
			sqlBuf.append(" update PdShipStrategyDetail pssd set pssd.status='");
			sqlBuf.append(status);
			sqlBuf.append("' where pssd.ssdId in('");
			sqlBuf.append(uniNoStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		return this.executeUpdate(sqlBuf.toString());
	}
     
}
