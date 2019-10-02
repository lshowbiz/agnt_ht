
package com.joymain.jecs.am.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.am.dao.InwDemandDao;
import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;


public class InwDemandDaoHibernate extends BaseDaoHibernate implements InwDemandDao {

    /**
     * @see com.joymain.jecs.am.dao.InwDemandDao#getInwDemands(com.joymain.jecs.am.model.InwDemand)
     */
    public List getInwDemands(final InwDemand inwDemand) {
        return getHibernateTemplate().find("from InwDemand");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwDemand == null) {
            return getHibernateTemplate().find("from InwDemand");
        } else {
            // filter on properties set in the inwDemand
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwDemand).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwDemand.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDemandDao#getInwDemand(String id)
     */
    public InwDemand getInwDemand(final Long id) {
        InwDemand inwDemand = (InwDemand) getHibernateTemplate().get(InwDemand.class, id);
        if (inwDemand == null) {
            log.warn("uh oh, inwDemand with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwDemand.class, id);
        }

        return inwDemand;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDemandDao#saveInwDemand(InwDemand inwDemand)
     */    
    public void saveInwDemand(final InwDemand inwDemand) {
        getHibernateTemplate().saveOrUpdate(inwDemand);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwDemandDao#removeInwDemand(String id)
     */
    public void removeInwDemand(final Long id) {
        getHibernateTemplate().delete(getInwDemand(id));
    }
    
    /**
     * 删除操作--删除需求(活动)对应的建议,建议查看
     * @author gw 3013-09-13
     * @param  ids
     * 
     */
    public void removeInwDemands(String ids){
    	
	String  hql = "delete from inw_demand t where t.id in("+ids+")";
	
    	this.getJdbcTemplate().execute(hql);
    }
    
    /**
     * @author 2013-09-10
     * 创新共赢的需求查询或者是活动查询
     * @param crm
     * @param pager
     * @return
     */
    public List getInwDemandsByCrm(CommonRecord crm, Pager pager){
    	
    	
    	/*String sql = "select id,name,summary,show_or_hide,verify,other,post_user_code,to_char(post_time,'yyyy-mm-dd HH24:MI:SS') post_time," +
    			"reviewed_user_code,to_char(reviewed_time,'yyyy-mm-dd HH24:MI:SS') reviewed_time,demand_image,detail_explanation " +
    			"from inw_demand ide where 1=1";*/
    	
    	String sql = "select ide.id id,ide.name name,ide.summary summary,ide.show_or_hide show_or_hide,ide.verify verify,ide.other other,ide.post_user_code post_user_code,to_char(ide.post_time,'yyyy-mm-dd HH24:MI:SS') post_time," +
		"ide.reviewed_user_code reviewed_user_code,to_char(ide.reviewed_time,'yyyy-mm-dd HH24:MI:SS') reviewed_time,ide.demand_image demand_image,ide.detail_explanation detail_explanation, ids.name  nameSort  " +
		"from inw_demand ide,inw_demandsort ids where 1=1 and ide.demandsort_id = ids.id ";
    	
    	
    String other = crm.getString("other", "");
    if(StringUtils.isNotEmpty(other)){
    	//2 代表活动
    	if("2".equals(other)){
    	     sql += " and ide.other = '"+ other +"'";
    	}
    	//1 代表需求,3代表 系统功能的需求,其他的留着其他扩展功能用
    	else{
    		 sql += " and ide.other in ('1','3') ";
    	}
    }
   
    String verify = crm.getString("verify", "");
    if(StringUtils.isNotEmpty(verify)){
    	sql += " and ide.verify = '"+ verify +"'";
    }
    	
	String name = crm.getString("name", "");
	if(StringUtils.isNotEmpty(name)){
		sql +=" and ide.name like '%"+name+"%'";
	}
	
	String summary = crm.getString("summary", "");
	if(StringUtils.isNotEmpty(summary)){
		sql +=" and ide.summary like '%"+summary+"%'";
	}
	
	String demandsortId = crm.getString("demandsortId", "");
	if(StringUtils.isNotEmpty(demandsortId)){
		sql +=" and ide.demandsort_id = '"+demandsortId+"' ";
	}
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String createTimeStart = crm.getString("postTime","");
	String createTimeEnd = crm.getString("postTimeEnd","");
	if(StringUtils.isNotEmpty(createTimeStart)){
		try {
			sql += " and ide.post_time >=to_date('"+ dateFormat.format(dateFormat.parse(createTimeStart) ) +" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	if(StringUtils.isNotEmpty(createTimeEnd)){
		try {
			sql += " and ide.post_time <=to_date('"+ dateFormat.format(dateFormat.parse(createTimeEnd)) +" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
		/*if (!pager.getLimit().getSort().isSorted()) {
			sql += " order by ide.id desc";
		} else {
			sql += " order by " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}*/
		return this.findObjectsBySQL(sql,pager);
    }
    
	public void checkInwDemand(String ids ,String userName) {
		String[] idsList = ids.split(",");
		
		System.out.println("idsList.length:  "+idsList.length);
    	for(int i=0;i<idsList.length;i++){

    		InwDemand inwDemand = (InwDemand) getHibernateTemplate().get(InwDemand.class, Long.parseLong(idsList[i]) );
    		System.out.println( inwDemand);
    		inwDemand.setReviewedTime(new Date());
    		inwDemand.setReviewedUserCode(userName);
    		inwDemand.setVerify("Y");
    		
    		this.getHibernateTemplate().saveOrUpdate(inwDemand);
    	}
	}

	/**
	 * 创新共赢-------新需求------删除需求分类这个大类时,同步删除创新共赢需求表的各子类
	 * @author gw  2013-11-12
	 * @param demandsortId
	 */
	public void deleteInwDemandByDemandsortId(String demandsortId) {
		String hql = " from InwDemand where demandsortId='"+demandsortId+"' ";
		List<InwDemand> list = this.findObjectsByHqlQuery(hql);
		for(int i=0;i<list.size();i++){
			Long id = list.get(i).getId();
			//执行删除操作
			this.removeInwDemand(id);
		}
		
	}

	/**
	 * 创新共赢--------------新需求-------------通过ID获取需求的名称
	 * @author gw 2013-11-15
	 * @param id
	 * @return
	 */
	public String getInwDemandById(String id) {
		 Long idl = Long.parseLong(id);
		 InwDemand inwDemand = (InwDemand) getHibernateTemplate().get(InwDemand.class, idl);
		return inwDemand.getName();
	}

	/**
	 * 根据需求分类大类别的ID查询出该大类下所有小类需求
	 * @author gw 2014-06-25
	 * @param demandsortID
	 * @return list 
	 */
	public List getInwDemandByDemandsortId(String demandsortID) {
		String hql = " from InwDemand where demandsortId='"+demandsortID+"' ";
		return this.findObjectsByHqlQuery(hql);
	}
	
}
