
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.JalLibraryFile;
import com.joymain.jecs.al.dao.JalLibraryFileDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.apache.commons.lang.StringUtils;

public class JalLibraryFileDaoHibernate extends BaseDaoHibernate implements JalLibraryFileDao {

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryFileDao#getJalLibraryFiles(com.joymain.jecs.al.model.JalLibraryFile)
     */
    public List getJalLibraryFiles(final JalLibraryFile jalLibraryFile) {
        return getHibernateTemplate().find("from JalLibraryFile");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jalLibraryFile == null) {
            return getHibernateTemplate().find("from JalLibraryFile");
        } else {
            // filter on properties set in the jalLibraryFile
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jalLibraryFile).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JalLibraryFile.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryFileDao#getJalLibraryFile(Long fileId)
     */
    public JalLibraryFile getJalLibraryFile(final Long fileId) {
        JalLibraryFile jalLibraryFile = (JalLibraryFile) getHibernateTemplate().get(JalLibraryFile.class, fileId);
        if (jalLibraryFile == null) {
            log.warn("uh oh, jalLibraryFile with fileId '" + fileId + "' not found...");
            throw new ObjectRetrievalFailureException(JalLibraryFile.class, fileId);
        }

        return jalLibraryFile;
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryFileDao#saveJalLibraryFile(JalLibraryFile jalLibraryFile)
     */    
    public void saveJalLibraryFile(final JalLibraryFile jalLibraryFile) {
        getHibernateTemplate().saveOrUpdate(jalLibraryFile);
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryFileDao#removeJalLibraryFile(Long fileId)
     */
    public void removeJalLibraryFile(final Long fileId) {
        getHibernateTemplate().delete(getJalLibraryFile(fileId));
    }
    //added for getJalLibraryFilesByCrm
    public List getJalLibraryFilesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JalLibraryFile jalLibraryFile where 1=1";
 
		String columnId = crm.getString("columnId", "");
		if(StringUtils.isNotEmpty(columnId)){
			hql += " and columnId="+columnId;
		}else{
			
			String plateId = crm.getString("plateId", "");
			if(StringUtils.isNotEmpty(columnId)){
				hql += " and columnId=(select columnId from JalLibraryColumn c where c.plateId="+plateId+")";
			}
		}
	
    
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by fileId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJalLibraryFileByCulumnId(String columnId) {
		
		String hql = "from JalLibraryFile jalLibraryFile where columnId="+columnId;
		return this.findObjectsByHqlQuery(hql);
	}
}
