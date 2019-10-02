
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.fi.dao.FiFundbookTempDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiFundbookTempDaoHibernate extends BaseDaoHibernate implements FiFundbookTempDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookTempDao#getFiFundbookTemps(com.joymain.jecs.fi.model.FiFundbookTemp)
     */
    public List getFiFundbookTemps(final FiFundbookTemp fiFundbookTemp) {
        return getHibernateTemplate().find("from FiFundbookTemp");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiFundbookTemp == null) {
            return getHibernateTemplate().find("from FiFundbookTemp");
        } else {
            // filter on properties set in the fiFundbookTemp
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiFundbookTemp).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiFundbookTemp.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookTempDao#getFiFundbookTemp(Long tempId)
     */
    public FiFundbookTemp getFiFundbookTemp(final Long tempId) {
        FiFundbookTemp fiFundbookTemp = (FiFundbookTemp) getHibernateTemplate().get(FiFundbookTemp.class, tempId);
        if (fiFundbookTemp == null) {
            log.warn("uh oh, fiFundbookTemp with tempId '" + tempId + "' not found...");
            throw new ObjectRetrievalFailureException(FiFundbookTemp.class, tempId);
        }

        return fiFundbookTemp;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookTempDao#saveFiFundbookTemp(FiFundbookTemp fiFundbookTemp)
     */    
    public void saveFiFundbookTemp(final FiFundbookTemp fiFundbookTemp) {
        getHibernateTemplate().saveOrUpdate(fiFundbookTemp);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiFundbookTempDao#removeFiFundbookTemp(Long tempId)
     */
    public void removeFiFundbookTemp(final Long tempId) {
        getHibernateTemplate().delete(getFiFundbookTemp(tempId));
    }
    //added for getFiFundbookTempsByCrm
    public List getFiFundbookTempsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiFundbookTemp fiFundbookTemp where 1=1";
    	
    	hql += this.buildListSqlQuery(crm, null);
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by tempId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * 获取某用户的临时基金条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String bankbookType) {
		return this.getTotalByHql("select count(*) as totalCount from FiFundbookTemp where companyCode='" + companyCode + "' and bankbookType ='" + bankbookType + "' and userCode='" + userCode + "'");
	}
	
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm){
		String incHql = "select nvl(sum(money),0) as inMoney from FiFundbookTemp where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select nvl(sum(money),0) as inMoney from FiFundbookTemp where 1=1 " + this.buildListSqlQuery(crm, "D");
		BigDecimal expTotal = (BigDecimal) this.getObjectByHqlQuery(expHql);

		Map<String, BigDecimal> incExpStatMap = new HashMap<String, BigDecimal>();
		incExpStatMap.put("incTotal", incTotal);
		incExpStatMap.put("expTotal", expTotal);
		
		return incExpStatMap;
	}
	
	/**
	 * 根据外部参数生成查询语句
	 * @param crm
	 * @return
	 */
	private String buildListSqlQuery(CommonRecord crm, String dealType){
		String hql="";
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}
		
		if (!StringUtils.isEmpty(dealType)) {
			hql += " and dealType='" + dealType + "'";
		}

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode='" + userCode + "'";
		}

		String bankbookType = crm.getString("bankbookType", "");
		if (!StringUtils.isEmpty(bankbookType)) {
			hql += " and bankbookType='" + bankbookType + "'";
		}

		String moneyType = crm.getString("moneyType", "");
		if (!StringUtils.isEmpty(moneyType)) {
			hql += " and moneyType='" + moneyType + "'";
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status='" + status + "'";
		}

		String createrCode = crm.getString("createrCode", "");
		if (!StringUtils.isEmpty(createrCode)) {
			hql += " and createrCode='" + createrCode + "'";
		}
		
		String createrName = crm.getString("createrName", "");
		if (!StringUtils.isEmpty(createrName)) {
			hql += " and createrName='" + createrName + "'";
		}
		
		String checkerName = crm.getString("checkerName", "");
		if (!StringUtils.isEmpty(checkerName)) {
			hql += " and checkerName='" + checkerName + "'";
		}

		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and createTime>=to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and createTime<=to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
		return hql;
	}

	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveFiFundbookTemps(List fiFundbookTemps) {
		this.getHibernateTemplate().saveOrUpdateAll(fiFundbookTemps);
	}
}
