
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.dao.FiAvailableInvoiceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiAvailableInvoiceDaoHibernate extends BaseDaoHibernate implements FiAvailableInvoiceDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiAvailableInvoiceDao#getFiAvailableInvoices(com.joymain.jecs.fi.model.FiAvailableInvoice)
     */
    public List getFiAvailableInvoices(final FiAvailableInvoice fiAvailableInvoice) {
        return getHibernateTemplate().find("from FiAvailableInvoice");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiAvailableInvoice == null) {
            return getHibernateTemplate().find("from FiAvailableInvoice");
        } else {
            // filter on properties set in the fiAvailableInvoice
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiAvailableInvoice).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiAvailableInvoice.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiAvailableInvoiceDao#getFiAvailableInvoice(String id)
     */
    public FiAvailableInvoice getFiAvailableInvoice(final String id) {
        FiAvailableInvoice fiAvailableInvoice = (FiAvailableInvoice) getHibernateTemplate().get(FiAvailableInvoice.class, id);
        if (fiAvailableInvoice == null) {
            log.warn("uh oh, fiAvailableInvoice with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(FiAvailableInvoice.class, id);
        }

        return fiAvailableInvoice;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiAvailableInvoiceDao#saveFiAvailableInvoice(FiAvailableInvoice fiAvailableInvoice)
     */    
    public void saveFiAvailableInvoice(final FiAvailableInvoice fiAvailableInvoice) {
    	String id = fiAvailableInvoice.getId();
    	if(StringUtil.isEmpty(id)){
    		id = this.definitionIdGenerate().toString();
    	}
        getHibernateTemplate().saveOrUpdate(fiAvailableInvoice);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiAvailableInvoiceDao#removeFiAvailableInvoice(String id)
     */
    public void removeFiAvailableInvoice(final String id) {
        getHibernateTemplate().delete(getFiAvailableInvoice(id));
    }
    
    /**
     * @author fu 2015-11-30 合规可用发票查询
     * @param crm,pager
     * @return List
     */
    public List getFiAvailableInvoicesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiAvailableInvoice fiAvailableInvoice where 1=1 and fiAvailableInvoice.logicalDelete='N' ";
    	
	    String userCode = crm.getString("userCode", "");
	    if(!StringUtil.isEmpty(userCode)){
	    	   hql += " and fiAvailableInvoice.userCode = '"+userCode+"'";
	    }
	    String userName = crm.getString("userName", "");
	    if(!StringUtil.isEmpty(userName)){
	    	   hql += " and fiAvailableInvoice.userName like '%"+userName+"%'";
	    }
	    String jyear = crm.getString("jyear", "");
	    if(!StringUtil.isEmpty(jyear)){
	    	   hql += " and fiAvailableInvoice.jyear = '"+jyear+"'";
	    }
	    String jmonth = crm.getString("jmonth", "");
	    if(!StringUtil.isEmpty(jmonth)){
	    	   hql += " and fiAvailableInvoice.jmonth = '"+jmonth+"'";
	    }
	    String ownedCompany = crm.getString("ownedCompany", "");
	    if(!StringUtil.isEmpty(ownedCompany)){
	    	   hql += " and fiAvailableInvoice.ownedCompany like '%"+ownedCompany+"%'";
	    }
	    
	    String createTimeStart = crm.getString("createTimeStart", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and fiAvailableInvoice.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and fiAvailableInvoice.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		
		String status = crm.getString("status", "");
	    if(!StringUtil.isEmpty(status)){
	    	   hql += " and fiAvailableInvoice.status = '"+status+"'";
	    }
    	
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * 定义ID
	 * @author gw 2015-11-29
	 * @return Long
	 */
	public Long definitionIdGenerate() {
		String hql = "select SEQ_PD.nextval from dual";
		return jdbcTemplate.queryForLong(hql);
	}
}
