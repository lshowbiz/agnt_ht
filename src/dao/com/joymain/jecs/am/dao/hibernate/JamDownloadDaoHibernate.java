
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.am.dao.JamDownloadDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JamDownloadDaoHibernate extends BaseDaoHibernate implements JamDownloadDao {

    /**
     * @see com.joymain.jecs.am.dao.JamDownloadDao#getJamDownloads(com.joymain.jecs.am.model.JamDownload)
     */
    public List getJamDownloads(final JamDownload jamDownload) {
        return getHibernateTemplate().find("from JamDownload");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jamDownload == null) {
            return getHibernateTemplate().find("from JamDownload");
        } else {
            // filter on properties set in the jamDownload
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jamDownload).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JamDownload.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.JamDownloadDao#getJamDownload(Long dlId)
     */
    public JamDownload getJamDownload(final Long dlId) {
        JamDownload jamDownload = (JamDownload) getHibernateTemplate().get(JamDownload.class, dlId);
        if (jamDownload == null) {
            log.warn("uh oh, jamDownload with dlId '" + dlId + "' not found...");
            throw new ObjectRetrievalFailureException(JamDownload.class, dlId);
        }

        return jamDownload;
    }

    /**
     * @see com.joymain.jecs.am.dao.JamDownloadDao#saveJamDownload(JamDownload jamDownload)
     */    
    public void saveJamDownload(final JamDownload jamDownload) {
        getHibernateTemplate().saveOrUpdate(jamDownload);
    }

    /**
     * @see com.joymain.jecs.am.dao.JamDownloadDao#removeJamDownload(Long dlId)
     */
    public void removeJamDownload(final Long dlId) {
        getHibernateTemplate().delete(getJamDownload(dlId));
    }
    //added for getJamDownloadsByCrm
    public List getJamDownloadsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JamDownload jamDownload where 1=1";

		String fileType = crm.getString("fileType", "");
		if (!StringUtils.isEmpty(fileType)) {
			hql += " and fileType = '" + fileType + "'";
		}
		String fileName = crm.getString("fileName", "");
		if (!StringUtils.isEmpty(fileName)) {
			hql += " and fileName = '" + fileName + "'";
		}
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status = '" + status + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode)) {
			hql += " and companyCode = '" + companyCode + "'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by dlId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
