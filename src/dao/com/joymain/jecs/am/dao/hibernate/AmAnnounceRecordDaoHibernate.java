
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.am.dao.AmAnnounceRecordDao;
import com.joymain.jecs.am.model.AmAnnounceRecord;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AmAnnounceRecordDaoHibernate extends BaseDaoHibernate implements AmAnnounceRecordDao {

public boolean existAmAnnounceRecord(String aaNo, String userNo) {
		/*// TODO Auto-generated method stub
		boolean ret = false;
		String hql = "from AmAnnounceRecord amAnnounceRecord where amAnnounceRecord.browser.userCode='"+userNo+"' " +
				"and amAnnounceRecord.amAnnounce.aaNo='"+aaNo+"'";
//		this.getHibernateTemplate().find(hql);
		int i=this.getTotalCountByHQL(hql);
		if(i>0){
			ret= true;
		}
		return ret;*/
		//Modify By WuCF 20140703 绑定变量
		Query q=getSession().createQuery("select count(*) from AmAnnounceRecord amAnnounceRecord where amAnnounceRecord.browser.userCode= :userCode and amAnnounceRecord.amAnnounce.aaNo= :aaNo ");
		q.setParameter("userCode",userNo);
		q.setParameter("aaNo",aaNo);
		List list = q.list();
		boolean ret = false;
		if(list!=null && list.size()>=1){	    	   
			Integer i = Integer.parseInt(list.get(0).toString());
			if(i>0){
				ret = true;
			}
		}
		return ret;
	}

	/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceRecordDao#getAmAnnounceRecords(com.joymain.jecs.am.model.AmAnnounceRecord)
     */
    public List getAmAnnounceRecords(final AmAnnounceRecord amAnnounceRecord) {
        return getHibernateTemplate().find("from AmAnnounceRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amAnnounceRecord == null) {
            return getHibernateTemplate().find("from AmAnnounceRecord");
        } else {
            // filter on properties set in the amAnnounceRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amAnnounceRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmAnnounceRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceRecordDao#getAmAnnounceRecord(String uniNo)
     */
    public AmAnnounceRecord getAmAnnounceRecord(final String uniNo) {
        AmAnnounceRecord amAnnounceRecord = (AmAnnounceRecord) getHibernateTemplate().get(AmAnnounceRecord.class, uniNo);
        if (amAnnounceRecord == null) {
            log.warn("uh oh, amAnnounceRecord with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(AmAnnounceRecord.class, uniNo);
        }

        return amAnnounceRecord;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceRecordDao#saveAmAnnounceRecord(AmAnnounceRecord amAnnounceRecord)
     */    
    public void saveAmAnnounceRecord(final AmAnnounceRecord amAnnounceRecord) {
        getHibernateTemplate().saveOrUpdate(amAnnounceRecord);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmAnnounceRecordDao#removeAmAnnounceRecord(String uniNo)
     */
    public void removeAmAnnounceRecord(final String uniNo) {
        getHibernateTemplate().delete(getAmAnnounceRecord(uniNo));
    }
    //added for getAmAnnounceRecordsByCrm
    public List getAmAnnounceRecordsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AmAnnounceRecord amAnnounceRecord where 1=1";
    	/*** ����Լ��Ĳ�ѯ���������***/
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
