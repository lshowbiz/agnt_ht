
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdJdSendRecord;
import com.joymain.jecs.bd.dao.JbdJdSendRecordDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdJdSendRecordDaoHibernate extends BaseDaoHibernate implements JbdJdSendRecordDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdJdSendRecordDao#getJbdJdSendRecords(com.joymain.jecs.bd.model.JbdJdSendRecord)
     */
    public List getJbdJdSendRecords(final JbdJdSendRecord jbdJdSendRecord) {
        return getHibernateTemplate().find("from JbdJdSendRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdJdSendRecord == null) {
            return getHibernateTemplate().find("from JbdJdSendRecord");
        } else {
            // filter on properties set in the jbdJdSendRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdJdSendRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdJdSendRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdJdSendRecordDao#getJbdJdSendRecord(BigDecimal id)
     */
    public JbdJdSendRecord getJbdJdSendRecord(final Long id) {
        JbdJdSendRecord jbdJdSendRecord = (JbdJdSendRecord) getHibernateTemplate().get(JbdJdSendRecord.class, id);
/*        if (jbdJdSendRecord == null) {
            log.warn("uh oh, jbdJdSendRecord with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdJdSendRecord.class, id);
        }*/

        return jbdJdSendRecord;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdJdSendRecordDao#saveJbdJdSendRecord(JbdJdSendRecord jbdJdSendRecord)
     */    
    public void saveJbdJdSendRecord(final JbdJdSendRecord jbdJdSendRecord) {
        getHibernateTemplate().saveOrUpdate(jbdJdSendRecord);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdJdSendRecordDao#removeJbdJdSendRecord(BigDecimal id)
     */
    public void removeJbdJdSendRecord(final Long id) {
        getHibernateTemplate().delete(getJbdJdSendRecord(id));
    }
    //added for getJbdJdSendRecordsByCrm
    public List getJbdJdSendRecordsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdJdSendRecord jbdJdSendRecord where 1=1";

    	
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and jmiMember.userCode='"+userCode+"'";
    	}

    	String freezeStatus=crm.getString("freezeStatus", "");
    	if(!StringUtil.isEmpty(freezeStatus)){
    		hql+=" and freezeStatus="+freezeStatus;
    	}

    	String startWeek=crm.getString("startWeek", "");
    	if(!StringUtil.isEmpty(startWeek)){
    		hql+=" and wweek>="+startWeek;
    	}
    	String endWeek=crm.getString("endWeek", "");
    	if(!StringUtil.isEmpty(endWeek)){
    		hql+=" and wweek<="+endWeek;
    	}
    	String wweek=crm.getString("wweek", "");
    	if(!StringUtil.isEmpty(wweek)){
    		hql+=" and wweek="+wweek;
    	}
    	

    	String status=crm.getString("status", "");
    	if(!StringUtil.isEmpty(status)){
    		hql+=" and status="+status;
    	}
    	// 
    	
    	//modify by fu 2017-4-5 云客推荐奖查询----begin
    	
    	String sendTimeBegin = crm.getString("sendTimeBegin", "");
    	if(!StringUtil.isEmpty(sendTimeBegin)){//'"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS'
    		hql += " and jbdJdSendRecord.sendDate>=to_date('"+sendTimeBegin+" 00:00:00','yyyy/mm/dd HH24:MI:SS') ";
    	}
    	String sendTimeEnd = crm.getString("sendTimeEnd", "");
    	if(!StringUtil.isEmpty(sendTimeEnd)){
    		hql += " and jbdJdSendRecord.sendDate<=to_date('"+sendTimeEnd+" 00:00:00','yyyy/mm/dd HH24:MI:SS') ";
    	}
    	//modify by fu 2017-4-5 云客推荐奖查询----end
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
