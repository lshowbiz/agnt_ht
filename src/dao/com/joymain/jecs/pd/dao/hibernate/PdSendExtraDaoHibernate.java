
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdSendExtra;
import com.joymain.jecs.pd.dao.PdSendExtraDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdSendExtraDaoHibernate extends BaseDaoHibernate implements PdSendExtraDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdSendExtraDao#getPdSendExtras(com.joymain.jecs.pd.model.PdSendExtra)
     */
    public List getPdSendExtras(final PdSendExtra pdSendExtra) {
        return getHibernateTemplate().find("from PdSendExtra");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdSendExtra == null) {
            return getHibernateTemplate().find("from PdSendExtra");
        } else {
            // filter on properties set in the pdSendExtra
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdSendExtra).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdSendExtra.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdSendExtraDao#getPdSendExtra(Long uniId)
     */
    public PdSendExtra getPdSendExtra(final Long uniId) {
        PdSendExtra pdSendExtra = (PdSendExtra) getHibernateTemplate().get(PdSendExtra.class, uniId);
        if (pdSendExtra == null) {
            log.warn("uh oh, pdSendExtra with uniId '" + uniId + "' not found...");
            throw new ObjectRetrievalFailureException(PdSendExtra.class, uniId);
        }

        return pdSendExtra;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdSendExtraDao#savePdSendExtra(PdSendExtra pdSendExtra)
     */    
    public void savePdSendExtra(final PdSendExtra pdSendExtra) {
        getHibernateTemplate().saveOrUpdate(pdSendExtra);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdSendExtraDao#removePdSendExtra(Long uniId)
     */
    public void removePdSendExtra(final Long uniId) {
        getHibernateTemplate().delete(getPdSendExtra(uniId));
    }
    //added for getPdSendExtrasByCrm
    public List getPdSendExtrasByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdSendExtra pdSendExtra where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	String siNo = crm.getString("siNo", "");
    	if(StringUtils.isNotBlank(siNo)){
    		hql +=" and pdSendExtra.siNo='"+siNo+"'";
    	}
    	if(pager == null){
    		return this.getHibernateTemplate().find(hql);
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by uniId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
