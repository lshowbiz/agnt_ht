
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.dao.FiBankbookTempDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiBankbookTempDaoHibernate extends BaseDaoHibernate implements FiBankbookTempDao {

    /**
     * @see com.sp.spms.fi.dao.FiBankbookTempDao#getFiBankbookTemps(com.sp.spms.fi.model.FiBankbookTemp)
     */
    public List getFiBankbookTemps(final FiBankbookTemp fiBankbookTemp) {
        return getHibernateTemplate().find("from FiBankbookTemp");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBankbookTemp == null) {
            return getHibernateTemplate().find("from FiBankbookTemp");
        } else {
            // filter on properties set in the fiBankbookTemp
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBankbookTemp).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBankbookTemp.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.sp.spms.fi.dao.FiBankbookTempDao#getFiBankbookTemp(Long tempId)
     */
    public FiBankbookTemp getFiBankbookTemp(final Long tempId) {
        FiBankbookTemp fiBankbookTemp = (FiBankbookTemp) getHibernateTemplate().get(FiBankbookTemp.class, tempId);
        if (fiBankbookTemp == null) {
            log.warn("uh oh, fiBankbookTemp with tempId '" + tempId + "' not found...");
            throw new ObjectRetrievalFailureException(FiBankbookTemp.class, tempId);
        }

        return fiBankbookTemp;
    }

    /**
     * @see com.sp.spms.fi.dao.FiBankbookTempDao#saveFiBankbookTemp(FiBankbookTemp fiBankbookTemp)
     */    
    public void saveFiBankbookTemp(final FiBankbookTemp fiBankbookTemp) {
        getHibernateTemplate().saveOrUpdate(fiBankbookTemp);
    }

    /**
     * @see com.sp.spms.fi.dao.FiBankbookTempDao#removeFiBankbookTemp(Long tempId)
     */
    public void removeFiBankbookTemp(final Long tempId) {
        getHibernateTemplate().delete(getFiBankbookTemp(tempId));
    }
    //added for getFiBankbookTempsByCrm
    public List getFiBankbookTempsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBankbookTemp fiBankbookTemp where 1=1";
    	hql+=this.buildListSqlQuery(crm, null);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by tempId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm){
		String incHql = "select nvl(sum(money),0) as inMoney from FiBankbookTemp where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select nvl(sum(money),0) as inMoney from FiBankbookTemp where 1=1 " + this.buildListSqlQuery(crm, "D");
		BigDecimal expTotal = (BigDecimal) this.getObjectByHqlQuery(expHql);

		Map<String, BigDecimal> incExpStatMap = new HashMap<String, BigDecimal>();
		incExpStatMap.put("incTotal", incTotal);
		incExpStatMap.put("expTotal", expTotal);
		
		return incExpStatMap;
	}

	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String bankbookType) {
		return this.getTotalByHql("select count(*) as totalCount from FiBankbookTemp where companyCode='" + companyCode + "' and bankbookType ='" + bankbookType + "' and sysUser.userCode='" + userCode + "'");
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

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String userName = crm.getString("sysUser.userName", "");
		if (!StringUtils.isEmpty(userName)) {
			hql += " and sysUser.userName like'%" + userName + "%'";
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
	public void saveFiBankbookTemps(List fiBankbookTemps){
		this.getHibernateTemplate().saveOrUpdateAll(fiBankbookTemps);
	}
    public List getFiProductPointTempsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiProductPointTemp fiBankbookTemp where 1=1";
    	hql+=this.buildListPpSqlQuery(crm, null);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by tempId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	public Map getPpIncExpStatMap(CommonRecord crm){
		String incHql = "select nvl(sum(money),0) as inMoney from FiProductPointTemp where 1=1 " + this.buildListPpSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select nvl(sum(money),0) as inMoney from FiProductPointTemp where 1=1 " + this.buildListPpSqlQuery(crm, "D");
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
	private String buildListPpSqlQuery(CommonRecord crm, String dealType){
		String hql="";
		
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and companyCode='" + companyCode + "'";
		}
		
		if (!StringUtils.isEmpty(dealType)) {
			hql += " and dealType='" + dealType + "'";
		}

		String userCode = crm.getString("sysUser.userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and sysUser.userCode='" + userCode + "'";
		}

		String userName = crm.getString("sysUser.userName", "");
		if (!StringUtils.isEmpty(userName)) {
			hql += " and sysUser.userName like'%" + userName + "%'";
		}

		String bankbookType = crm.getString("bankbookType", "");
		if (!StringUtils.isEmpty(bankbookType)) {
			hql += " and productPointType='" + bankbookType + "'";
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

}
