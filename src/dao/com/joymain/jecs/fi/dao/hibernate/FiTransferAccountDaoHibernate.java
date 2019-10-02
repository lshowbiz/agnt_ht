
package com.joymain.jecs.fi.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.fi.dao.FiTransferAccountDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiTransferAccountDaoHibernate extends BaseDaoHibernate implements FiTransferAccountDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferAccountDao#getFiTransferAccounts(com.joymain.jecs.fi.model.FiTransferAccount)
     */
    public List getFiTransferAccounts(final FiTransferAccount fiTransferAccount) {
        return getHibernateTemplate().find("from FiTransferAccount");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiTransferAccount == null) {
            return getHibernateTemplate().find("from FiTransferAccount");
        } else {
            // filter on properties set in the fiTransferAccount
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiTransferAccount).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiTransferAccount.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferAccountDao#getFiTransferAccount(Long taId)
     */
    public FiTransferAccount getFiTransferAccount(final Long taId) {
        FiTransferAccount fiTransferAccount = (FiTransferAccount) getHibernateTemplate().get(FiTransferAccount.class, taId);
        if (fiTransferAccount == null) {
            log.warn("uh oh, fiTransferAccount with taId '" + taId + "' not found...");
            throw new ObjectRetrievalFailureException(FiTransferAccount.class, taId);
        }

        return fiTransferAccount;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferAccountDao#saveFiTransferAccount(FiTransferAccount fiTransferAccount)
     */    
    public void saveFiTransferAccount(final FiTransferAccount fiTransferAccount) {
        getHibernateTemplate().saveOrUpdate(fiTransferAccount);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferAccountDao#removeFiTransferAccount(Long taId)
     */
    public void removeFiTransferAccount(final Long taId) {
        getHibernateTemplate().delete(getFiTransferAccount(taId));
    }
    
    //获取单日转账总额
    public BigDecimal getSumTransferValueByTransferCode(final String transferCode){
    	
    	String hql = "select sum(fiTransferAccount.money) from FiTransferAccount fiTransferAccount where fiTransferAccount.transferUserCode='"+transferCode+"'";
    	
    	//获取当日起始日期
    	Calendar currentDate = new GregorianCalendar();    
    	  
    	currentDate.set(Calendar.HOUR_OF_DAY, 0);   
    	currentDate.set(Calendar.MINUTE, 0);   
    	currentDate.set(Calendar.SECOND, 0);   
    	Date todayStartTime = (Date)currentDate.getTime().clone();  
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	hql += " and fiTransferAccount.createTime>=to_date('" + df.format(todayStartTime) + "','yyyy-mm-dd hh24:mi:ss')";
    	
    	if(this.getObjectByHqlQuery(hql) == null)
    		return new BigDecimal(0);
    	
    	return (BigDecimal) this.getObjectByHqlQuery(hql);
    }
    
    //added for getFiTransferAccountsByCrm
    public List getFiTransferAccountsByCrm(CommonRecord crm, Pager pager){
    	
    	String hql = "from FiTransferAccount fiTransferAccount where 1=1";
    	
    	//转账会员
    	String transferUserCode = crm.getString("transferUserCode", "");
		if (!StringUtils.isEmpty(transferUserCode)) {
			hql += " and transferUserCode='" + transferUserCode + "'";
		}
		
		//收款会员
		String destinationUserCode = crm.getString("destinationUserCode", "");
		if (!StringUtils.isEmpty(destinationUserCode)) {
			hql += " and destinationUserCode='" + destinationUserCode + "'";
		}
		
		//状态
    	String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status='" + status + "'";
		}
		
		//创建者
    	String createrCode = crm.getString("createrCode", "");
    	if (!StringUtils.isEmpty(createrCode)) {
			hql += " and createrCode='" + createrCode + "'";
		}
    	
    	String createrName = crm.getString("createrName", "");
		if (!StringUtils.isEmpty(createrName)) {
			hql += " and createrName like'%" + createrName + "%'";
		}
		
    	String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and createTime>=to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and createTime<=to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
		//审核人
		String checkerName = crm.getString("checkerName", "");
		if (!StringUtils.isEmpty(checkerName)) {
			hql += " and checkerName like'%" + checkerName + "%'";
		}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by taId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		log.info("=========="+hql);
		return this.findObjectsByHqlQuery(hql, pager);
    }

    //根据ID查状态
	public Integer getTransferAccountStatus(Long taId) {
		
		String hql = "select status from FiTransferAccount fiTransferAccount where fiTransferAccount.taId="+taId;
		
		return (Integer) this.getObjectByHqlQuery(hql);
	}

	/**
     * 查询待审的转账单
     * @return
     */
	@Override
	public List<FiTransferAccount> getToCheckTransferAccountList() {
		
		String hqlQuery = "from FiTransferAccount fiTransferAccount where fiTransferAccount.status=1";
		
		return this.findObjectsByHqlQuery(hqlQuery);
	}
}
