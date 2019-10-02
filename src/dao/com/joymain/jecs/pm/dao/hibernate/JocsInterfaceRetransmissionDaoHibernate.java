
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JocsInterfaceRetransmissionDaoHibernate extends BaseDaoHibernate implements JocsInterfaceRetransmissionDao {

    /**
     * @see com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao#getJocsInterfaceRetransmissions(com.joymain.jecs.pm.model.JocsInterfaceRetransmission)
     */
    public List getJocsInterfaceRetransmissions(final JocsInterfaceRetransmission jocsInterfaceRetransmission) {
//        return null;
    	StringBuffer hql = new StringBuffer("from JocsInterfaceRetransmission jocsInterfaceRetransmission where 1=1 ");
    	hql.append(" and jocsInterfaceRetransmission.retranStatus='");
		hql.append(jocsInterfaceRetransmission.getRetranStatus());
		hql.append("' ");
		
		
		hql.append(" and jocsInterfaceRetransmission.retranType='");
		hql.append(jocsInterfaceRetransmission.getRetranType());
		hql.append("' ");
		
		return this.findObjectsByHqlQuery(hql.toString());
        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jocsInterfaceRetransmission == null) {
            return getHibernateTemplate().find("from JocsInterfaceRetransmission");
        } else {
            // filter on properties set in the jocsInterfaceRetransmission
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jocsInterfaceRetransmission).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JocsInterfaceRetransmission.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao#getJocsInterfaceRetransmission(BigDecimal logId)
     */
    public JocsInterfaceRetransmission getJocsInterfaceRetransmission(final BigDecimal logId) {
        JocsInterfaceRetransmission jocsInterfaceRetransmission = (JocsInterfaceRetransmission) getHibernateTemplate().get(JocsInterfaceRetransmission.class, logId);
        if (jocsInterfaceRetransmission == null) {
            log.warn("uh oh, jocsInterfaceRetransmission with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JocsInterfaceRetransmission.class, logId);
        }

        return jocsInterfaceRetransmission;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao#saveJocsInterfaceRetransmission(JocsInterfaceRetransmission jocsInterfaceRetransmission)
     */    
    public void saveJocsInterfaceRetransmission(final JocsInterfaceRetransmission jocsInterfaceRetransmission) {
        getHibernateTemplate().saveOrUpdate(jocsInterfaceRetransmission);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JocsInterfaceRetransmissionDao#removeJocsInterfaceRetransmission(BigDecimal logId)
     */
    public void removeJocsInterfaceRetransmission(final BigDecimal logId) {
        getHibernateTemplate().delete(getJocsInterfaceRetransmission(logId));
    }
    //added for getJocsInterfaceRetransmissionsByCrm
    public List getJocsInterfaceRetransmissionsByCrm(CommonRecord crm, Pager pager){
    	StringBuffer hql = new StringBuffer("from JocsInterfaceRetransmission jocsInterfaceRetransmission where 1=1 ");
    	
    	//内容模糊匹配
    	String userCode = crm.getString("userCode", "");
    	log.info("userCode====userCode:"+userCode);
    	System.out.println("userCode====userCode:"+userCode);
    	if("root".equals(userCode)){
	    	if(StringUtils.isNotEmpty(userCode)){
	    		if(!"root".equals(userCode)){
	    			hql.append(" and jocsInterfaceRetransmission.method !='ship.loEdit' ");
	    		}
	    	}
	    	
	    	//日志收发类型
	    	String logType = crm.getString("logType", "");
	    	if(StringUtils.isNotEmpty(logType)){
	    		hql.append(" and jocsInterfaceRetransmission.logType='");
	    		hql.append(logType.trim());
	    		hql.append("' ");
	    	}
	    	
	    	//内容模糊匹配
	    	String content = crm.getString("content", "");
	    	if(StringUtils.isNotEmpty(content)){
	    		hql.append(" and jocsInterfaceRetransmission.content like '%"+content.trim()+"%' ");
	    	}
	    	
	    	hql.append(" and jocsInterfaceRetransmission.method!='ship.search' " );//实时物流查询过滤
	    	
	    	//日志数据来源
	    	String flag = crm.getString("flag", "");
	    	if(StringUtils.isNotEmpty(flag)){
	    		hql.append(" and jocsInterfaceRetransmission.flag='");
	    		hql.append(flag.trim());
	    		hql.append("' ");
	    	}
	    	
	    	//方法名称
	    	String method = crm.getString("method", "");
	    	if(StringUtils.isNotEmpty(method)){
	    		hql.append(" and jocsInterfaceRetransmission.method like '%");
	    		hql.append(method.trim());
	    		hql.append("%' ");
	    	}
	    	
	    	//短信发送时间
	    	String logStartTime = crm.getString("logStartTime", "");
	    	if (StringUtils.isNotEmpty(logStartTime)) {
	    		hql.append(" and jocsInterfaceRetransmission.reciverTime>= to_date ('");
	    		hql.append(logStartTime);
	    		hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			}
	    	String logEndTime = crm.getString("logEndTime", "");
			if (StringUtils.isNotEmpty(logEndTime)) {
				hql.append(" and jocsInterfaceRetransmission.reciverTime<= to_date ('");
				hql.append(logEndTime);
	    		hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
			
			//重发类型
			String retranType = crm.getString("retranType", "");
	    	if(StringUtils.isNotEmpty(retranType)){
	    		hql.append(" and jocsInterfaceRetransmission.retranType='");
	    		hql.append(retranType.trim());
	    		hql.append("' ");
	    	}
	    	
	    	//重发状态
	    	String retranStatus = crm.getString("retranStatus", "");
	    	if(StringUtils.isNotEmpty(retranStatus)){
	    		hql.append(" and jocsInterfaceRetransmission.retranStatus='");
	    		hql.append(retranStatus.trim());
	    		hql.append("' ");
	    	}
    	}else{
    		hql.append(" and jocsInterfaceRetransmission.retranStatus='0' and jocsInterfaceRetransmission.method!='ship.search'  ");
    	}

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql.append(" order by jocsInterfaceRetransmission.logId ");
		} else {
			hql.append(" order by jocsInterfaceRetransmission.logId ");
		}
		log.info("getJocsInterfaceRetransmissionsByCrm====hql:"+hql);
		System.out.println("getJocsInterfaceRetransmissionsByCrm====hql:"+hql);
		return this.findObjectsByHqlQuery(hql.toString(), pager);
    }
}
