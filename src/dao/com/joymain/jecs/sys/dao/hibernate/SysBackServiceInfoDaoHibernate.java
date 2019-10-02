
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.SysBackServiceInfo;
import com.joymain.jecs.sys.dao.SysBackServiceInfoDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SysBackServiceInfoDaoHibernate extends BaseDaoHibernate implements SysBackServiceInfoDao {

    /**
     * @see com.joymain.jecs.sys.dao.SysBackServiceInfoDao#getSysBackServiceInfos(com.joymain.jecs.sys.model.SysBackServiceInfo)
     */
    public List getSysBackServiceInfos(final SysBackServiceInfo sysBackServiceInfo) {
        return getHibernateTemplate().find("from SysBackServiceInfo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sysBackServiceInfo == null) {
            return getHibernateTemplate().find("from SysBackServiceInfo");
        } else {
            // filter on properties set in the sysBackServiceInfo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sysBackServiceInfo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SysBackServiceInfo.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysBackServiceInfoDao#getSysBackServiceInfo(Long bsiId)
     */
    public SysBackServiceInfo getSysBackServiceInfo(final Long bsiId) {
        SysBackServiceInfo sysBackServiceInfo = (SysBackServiceInfo) getHibernateTemplate().get(SysBackServiceInfo.class, bsiId);
        if (sysBackServiceInfo == null) {
            log.warn("uh oh, sysBackServiceInfo with bsiId '" + bsiId + "' not found...");
            throw new ObjectRetrievalFailureException(SysBackServiceInfo.class, bsiId);
        }

        return sysBackServiceInfo;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysBackServiceInfoDao#saveSysBackServiceInfo(SysBackServiceInfo sysBackServiceInfo)
     */    
    public void saveSysBackServiceInfo(final SysBackServiceInfo sysBackServiceInfo) {
        getHibernateTemplate().saveOrUpdate(sysBackServiceInfo);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysBackServiceInfoDao#removeSysBackServiceInfo(Long bsiId)
     */
    public void removeSysBackServiceInfo(final Long bsiId) {
        getHibernateTemplate().delete(getSysBackServiceInfo(bsiId));
    }
    //added for getSysBackServiceInfosByCrm
    public List getSysBackServiceInfosByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysBackServiceInfo sysBackServiceInfo where 1=1";
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
			hql += " order by bsiId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
