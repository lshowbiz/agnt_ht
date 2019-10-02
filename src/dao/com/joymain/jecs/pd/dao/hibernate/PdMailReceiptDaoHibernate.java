
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.dao.PdMailReceiptDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdMailReceiptDaoHibernate extends BaseDaoHibernate implements PdMailReceiptDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdMailReceiptDao#getPdMailReceipts(com.joymain.jecs.pd.model.PdMailReceipt)
     */
    public List getPdMailReceipts(final PdMailReceipt pdMailReceipt) {
        return getHibernateTemplate().find("from PdMailReceipt");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdMailReceipt == null) {
            return getHibernateTemplate().find("from PdMailReceipt");
        } else {
            // filter on properties set in the pdMailReceipt
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdMailReceipt).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdMailReceipt.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdMailReceiptDao#getPdMailReceipt(BigDecimal mailReceiptId)
     */
    public PdMailReceipt getPdMailReceipt(final BigDecimal mailReceiptId) {
        PdMailReceipt pdMailReceipt = (PdMailReceipt) getHibernateTemplate().get(PdMailReceipt.class, mailReceiptId);
        if (pdMailReceipt == null) {
            log.warn("uh oh, pdMailReceipt with mailReceiptId '" + mailReceiptId + "' not found...");
            throw new ObjectRetrievalFailureException(PdMailReceipt.class, mailReceiptId);
        }

        return pdMailReceipt;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdMailReceiptDao#savePdMailReceipt(PdMailReceipt pdMailReceipt)
     */    
    public void savePdMailReceipt(final PdMailReceipt pdMailReceipt) {
        getHibernateTemplate().saveOrUpdate(pdMailReceipt);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdMailReceiptDao#removePdMailReceipt(BigDecimal mailReceiptId)
     */
    public void removePdMailReceipt(final BigDecimal mailReceiptId) {
        getHibernateTemplate().delete(getPdMailReceipt(mailReceiptId));
    }
    //added for getPdMailReceiptsByCrm
    public List getPdMailReceiptsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdMailReceipt pdMailReceipt where 1=1";
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
			hql += " order by mailReceiptId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
