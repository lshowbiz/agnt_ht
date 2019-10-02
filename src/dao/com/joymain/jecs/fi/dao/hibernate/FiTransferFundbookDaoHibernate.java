
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.dao.FiTransferFundbookDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiTransferFundbookDaoHibernate extends BaseDaoHibernate implements FiTransferFundbookDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferFundbookDao#getFiTransferFundbooks(com.joymain.jecs.fi.model.FiTransferFundbook)
     */
    public List getFiTransferFundbooks(final FiTransferFundbook fiTransferFundbook) {
        return getHibernateTemplate().find("from FiTransferFundbook");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiTransferFundbook == null) {
            return getHibernateTemplate().find("from FiTransferFundbook");
        } else {
            // filter on properties set in the fiTransferFundbook
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiTransferFundbook).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiTransferFundbook.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferFundbookDao#getFiTransferFundbook(Long taId)
     */
    public FiTransferFundbook getFiTransferFundbook(final Long taId) {
        FiTransferFundbook fiTransferFundbook = (FiTransferFundbook) getHibernateTemplate().get(FiTransferFundbook.class, taId);
        if (fiTransferFundbook == null) {
            log.warn("uh oh, fiTransferFundbook with taId '" + taId + "' not found...");
            throw new ObjectRetrievalFailureException(FiTransferFundbook.class, taId);
        }

        return fiTransferFundbook;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferFundbookDao#saveFiTransferFundbook(FiTransferFundbook fiTransferFundbook)
     */    
    public void saveFiTransferFundbook(final FiTransferFundbook fiTransferFundbook) {
        getHibernateTemplate().saveOrUpdate(fiTransferFundbook);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiTransferFundbookDao#removeFiTransferFundbook(Long taId)
     */
    public void removeFiTransferFundbook(final Long taId) {
        getHibernateTemplate().delete(getFiTransferFundbook(taId));
    }
    //added for getFiTransferFundbooksByCrm
    public List getFiTransferFundbooksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiTransferFundbook fiTransferFundbook where 1=1";
    	
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
		return this.findObjectsByHqlQuery(hql, pager);
    }

	@Override
	public Integer getTransferFundbookStatus(Long taId) {
		
		String hql = "select status from FiTransferFundbook fiTransferFundbook where fiTransferFundbook.taId="+taId;
		
		return (Integer) this.getObjectByHqlQuery(hql);
	}

	/**
	 * 查询待审核产业化基金转账单(fiTransferFundbook.status状态:1.未核准，2.已核准，3.已撤销)
	 * @return
	 */
	@Override
	public List<FiTransferFundbook> getToCheckFiTransferFundbookList() {
		
		String hqlQuery = "from FiTransferFundbook fiTransferFundbook where fiTransferFundbook.status=1";
		
		return this.findObjectsByHqlQuery(hqlQuery);
	}
}
