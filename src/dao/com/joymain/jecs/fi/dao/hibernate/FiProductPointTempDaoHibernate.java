
package com.joymain.jecs.fi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.dao.FiProductPointTempDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiProductPointTempDaoHibernate extends BaseDaoHibernate implements FiProductPointTempDao {

    /**
     * @see com.sp.spms.fi.dao.FiProductPointTempDao#getFiProductPointTemps(com.sp.spms.fi.model.FiProductPointTemp)
     */
    public List getFiProductPointTemps(final FiProductPointTemp fiProductPointTemp) {
        return getHibernateTemplate().find("from FiProductPointTemp");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiProductPointTemp == null) {
            return getHibernateTemplate().find("from FiProductPointTemp");
        } else {
            // filter on properties set in the fiProductPointTemp
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiProductPointTemp).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiProductPointTemp.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointTempDao#getFiProductPointTemp(Long tempId)
     */
    public FiProductPointTemp getFiProductPointTemp(final Long tempId) {
        FiProductPointTemp fiProductPointTemp = (FiProductPointTemp) getHibernateTemplate().get(FiProductPointTemp.class, tempId);
        if (fiProductPointTemp == null) {
            log.warn("uh oh, fiProductPointTemp with tempId '" + tempId + "' not found...");
            throw new ObjectRetrievalFailureException(FiProductPointTemp.class, tempId);
        }

        return fiProductPointTemp;
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointTempDao#saveFiProductPointTemp(FiProductPointTemp fiProductPointTemp)
     */    
    public void saveFiProductPointTemp(final FiProductPointTemp fiProductPointTemp) {
        getHibernateTemplate().saveOrUpdate(fiProductPointTemp);
    }

    /**
     * @see com.sp.spms.fi.dao.FiProductPointTempDao#removeFiProductPointTemp(Long tempId)
     */
    public void removeFiProductPointTemp(final Long tempId) {
        getHibernateTemplate().delete(getFiProductPointTemp(tempId));
    }
    //added for getFiProductPointTempsByCrm
    public List getFiProductPointTempsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiProductPointTemp fiProductPointTemp where 1=1";
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
		String incHql = "select nvl(sum(money),0) as inMoney from FiProductPointTemp where 1=1 " + this.buildListSqlQuery(crm, "A");
		BigDecimal incTotal = (BigDecimal) this.getObjectByHqlQuery(incHql);

		String expHql = "select nvl(sum(money),0) as inMoney from FiProductPointTemp where 1=1 " + this.buildListSqlQuery(crm, "D");
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
	public long getCountByDate(final String companyCode, final String userCode, final String productPointType) {
		return this.getTotalByHql("select count(*) as totalCount from FiProductPointTemp where companyCode='" + companyCode + "' and productPointType ='" + productPointType + "' and sysUser.userCode='" + userCode + "'");
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

		String productPointType = crm.getString("productPointType", "");
		if (!StringUtils.isEmpty(productPointType)) {
			hql += " and productPointType='" + productPointType + "'";
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
	 * @param fiProductPointTemps
	 */
	public void saveFiProductPointTemps(List fiProductPointTemps){
		this.getHibernateTemplate().saveOrUpdateAll(fiProductPointTemps);
	}
}
