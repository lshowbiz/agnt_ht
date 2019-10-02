
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.dao.JfiPosImportDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiPosImportDaoHibernate extends BaseDaoHibernate implements JfiPosImportDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiPosImportDao#getJfiPosImports(com.joymain.jecs.fi.model.JfiPosImport)
     */
    public List getJfiPosImports(final JfiPosImport jfiPosImport) {
        return getHibernateTemplate().findByExample(jfiPosImport);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiPosImport == null) {
            return getHibernateTemplate().find("from JfiPosImport");
        } else {
            // filter on properties set in the jfiPosImport
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiPosImport).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiPosImport.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPosImportDao#getJfiPosImport(Long jpiId)
     */
    public JfiPosImport getJfiPosImport(final Long jpiId) {
        JfiPosImport jfiPosImport = (JfiPosImport) getHibernateTemplate().get(JfiPosImport.class, jpiId);
        if (jfiPosImport == null) {
            log.warn("uh oh, jfiPosImport with jpiId '" + jpiId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiPosImport.class, jpiId);
        }

        return jfiPosImport;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiPosImportDao#saveJfiPosImport(JfiPosImport jfiPosImport)
     */    
    public void saveJfiPosImport(final JfiPosImport jfiPosImport) {
        getHibernateTemplate().saveOrUpdate(jfiPosImport);
    }

	/**
	 * 批量保存多条记录
	 * @param fiPayDatas
	 */
	public void saveJfiPosImports(List jfiPosImport){
		this.getHibernateTemplate().saveOrUpdateAll(jfiPosImport);
	}

    /**
     * @see com.joymain.jecs.fi.dao.JfiPosImportDao#removeJfiPosImport(Long jpiId)
     */
    public void removeJfiPosImport(final Long jpiId) {
        getHibernateTemplate().delete(getJfiPosImport(jpiId));
    }
    //added for getJfiPosImportsByCrm
    public List getJfiPosImportsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiPosImport jfiPosImport where 1=1 ";
		
		hql+=this.buildListHqlQuery(crm);
		
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jpiId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 获取总金额
     * @param crm
     * @return
     */
    public String getSumJfiPosImportsByCrm(CommonRecord crm){
    	String hql = "select nvl(sum(amount),0) as inMoney from JfiPosImport where 1=1 ";
		
		hql+=this.buildListHqlQuery(crm);
		
		return this.getObjectByHqlQuery(hql).toString();
    }
	
	/**
	 * 根据外部参数生成查询语句
	 * @param crm
	 * @return
	 */
	private String buildListHqlQuery(CommonRecord crm){
		String hql="";

		String posNo = crm.getString("posNo", "");
		if(StringUtils.isNotEmpty(posNo)){
			hql += " and posNo='" + posNo + "'";
		}
		
		String moneyType = crm.getString("moneyType", "");
		if(StringUtils.isNotEmpty(moneyType)){
			hql += " and MONEY_TYPE='" + moneyType + "'";
		}

		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode='" + userCode + "'";
		}

		String status = crm.getString("status", "");
		if(StringUtils.isNotEmpty(status)){
			hql += " and status='" + status + "'";
		}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc='" + inc + "'";
		}

		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and createTime>=to_date('" + startCreateTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and createTime<=to_date('" + endCreateTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startCheckTime = crm.getString("startCheckTime", "");
		if (!StringUtils.isEmpty(startCheckTime)) {
			hql += " and checkTime>=to_date('" + startCheckTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCheckTime = crm.getString("endCheckTime", "");
		if (!StringUtils.isEmpty(endCheckTime)) {
			hql += " and checkTime<=to_date('" + endCheckTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}

		String startIncTime = crm.getString("startIncTime", "");
		if (!StringUtils.isEmpty(startIncTime)) {
			hql += " and incTime>=to_date('" + startIncTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}

		String endIncTime = crm.getString("endIncTime", "");
		if (!StringUtils.isEmpty(endIncTime)) {
			hql += " and incTime<=to_date('" + endIncTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		return hql;
	}
}
