
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.am.dao.AmRegularMsgDao;
import com.joymain.jecs.am.model.AmRegularMsg;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AmRegularMsgDaoHibernate extends BaseDaoHibernate implements AmRegularMsgDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.am.dao.AmRegularMsgDao#getAmRegularMsgs(com.joymain.jecs.am.model.AmRegularMsg)
     */
    public List getAmRegularMsgs(final AmRegularMsg amRegularMsg) {
        return getHibernateTemplate().find("from AmRegularMsg");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amRegularMsg == null) {
            return getHibernateTemplate().find("from AmRegularMsg");
        } else {
            // filter on properties set in the amRegularMsg
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amRegularMsg).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmRegularMsg.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmRegularMsgDao#getAmRegularMsg(String uniNo)
     */
    public AmRegularMsg getAmRegularMsg(final String uniNo) {
        AmRegularMsg amRegularMsg = (AmRegularMsg) getHibernateTemplate().get(AmRegularMsg.class, uniNo);
        if (amRegularMsg == null) {
            log.warn("uh oh, amRegularMsg with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(AmRegularMsg.class, uniNo);
        }

        return amRegularMsg;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmRegularMsgDao#saveAmRegularMsg(AmRegularMsg amRegularMsg)
     */    
    public void saveAmRegularMsg(final AmRegularMsg amRegularMsg) {
        getHibernateTemplate().saveOrUpdate(amRegularMsg);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmRegularMsgDao#removeAmRegularMsg(String uniNo)
     */
    public void removeAmRegularMsg(final String uniNo) {
        getHibernateTemplate().delete(getAmRegularMsg(uniNo));
    }
    //added for getAmRegularMsgsByCrm
    public List getAmRegularMsgsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AmRegularMsg amRegularMsg where 1=1";
    	/*** ����Լ��Ĳ�ѯ���������***/
    	
    	String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotEmpty(companyCode)){
			hql +=" and amRegularMsg.companyCode ='" +companyCode+ "'";
		}
    	
    	String content = crm.getString("content", "");
		if(StringUtils.isNotEmpty(content)){
			hql +=" and amRegularMsg.content like'%" +content+ "%'";
		}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
