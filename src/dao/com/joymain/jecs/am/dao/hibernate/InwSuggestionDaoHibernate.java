
package com.joymain.jecs.am.dao.hibernate;

import java.util.Date;
import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.dao.InwSuggestionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;

public class InwSuggestionDaoHibernate extends BaseDaoHibernate implements InwSuggestionDao {

    /**
     * @see com.joymain.jecs.am.dao.InwSuggestionDao#getInwSuggestions(com.joymain.jecs.am.model.InwSuggestion)
     */
    public List getInwSuggestions(final InwSuggestion inwSuggestion) {
        return getHibernateTemplate().find("from InwSuggestion");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwSuggestion == null) {
            return getHibernateTemplate().find("from InwSuggestion");
        } else {
            // filter on properties set in the inwSuggestion
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwSuggestion).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwSuggestion.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * 创新共赢-建议的详细查询
     * @author gw 2013-08-21
     * @param id
     * @return inwSuggestion
     */
    public InwSuggestion getInwSuggestion(final Long id) {
        InwSuggestion inwSuggestion = (InwSuggestion) getHibernateTemplate().get(InwSuggestion.class, id);
        if (inwSuggestion == null) {
            log.warn("uh oh, inwSuggestion with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwSuggestion.class, id);
        }
        return inwSuggestion;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwSuggestionDao#saveInwSuggestion(InwSuggestion inwSuggestion)
     */    
    public void saveInwSuggestion(final InwSuggestion inwSuggestion) {
        getHibernateTemplate().saveOrUpdate(inwSuggestion);
    }

    /**
     * @author gw 2013-09-13 
     * 删除建议   
     * 
     */
    public void removeInwSuggestion(final Long id) {
    	
    	
    	
    	
    	
    	
        getHibernateTemplate().delete(getInwSuggestion(id));
    }
    
    
    //added for getInwSuggestionsByCrm
    public List getInwSuggestionsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwSuggestion inwSuggestion where 1=1";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 创新共赢的后台意见(活动)的查询方法   ------针对需求变更做了修改
     * @author gw 2013-08-20   update 2013-11-12  gw 
     * @param crm
     * @param pager
     * @param lookStatus
     * @return list
     */
	public List getSuggestionAndViewPeople(CommonRecord crm, Pager pager,String lookStatus) {
		String userCode = crm.getString("userCode", "");
		//----------------------------------------手机功能------------------开始---------------------------------
        //phone这个参数有值为phone,那么表示是手机提的建议
        String phone = crm.getString("phone","");
        //手机功能查看
        if(!StringUtil.isEmpty(phone)&& "phone".equals(phone)){
        	 String sqlQuery = "";
        	//0代表未阅，1代表已阅，2全部（已阅＋未阅）
	        	//全部-----需求名称干掉
	        	if("2".equals(lookStatus)){
	        		sqlQuery = " select subject,create_time,user_code,id,demand_id  from inw_suggestion b  where 1=1 ";
	        		
	        	}
	        	//1代表已阅
	        	else if("1".equals(lookStatus)){
	        		sqlQuery = " select b.subject,to_char(b.create_time,'yyyy-MM-dd HH24:MI:SS') create_time,b.user_code,b.id,b.demand_id  from inw_suggestion b,inw_viewPeople c " +
	        				" where b.id = c.suggestionid and c.user_code = '"+userCode+"'";
	        	}
	        	//0代表未阅
	        	else{
	        		sqlQuery = " select subject,create_time,user_code,id,demand_id  from (" +
	        				" ( select subject, create_time,user_code,id,demand_id  from inw_suggestion b )" +
	        				" minus ( select b.subject, create_time,b.user_code,b.id,b.demand_id  from inw_suggestion b,inw_viewPeople c " +
	        				" where  b.id = c.suggestionid and c.user_code = '"+userCode+"' ) ) where 1=1 ";
	        	}
			        	//建议的建议人
			 	        String suggestionUserCode = crm.getString("suggestionUserCode", "");
			 	        if(!StringUtil.isEmpty(suggestionUserCode)){
			 	        	if("1".equals(lookStatus)){
			 	        		sqlQuery += " and b.user_code like '%"+suggestionUserCode+"%' ";
			 	        	}else{
			 	        		sqlQuery += " and user_code like '%"+suggestionUserCode+"%' ";
			 	        	}
			 	        	 
			 	        }
			 	        //建议主题
			 	        String subject = crm.getString("subject", "");
			             if(!StringUtil.isEmpty(subject)){
			             	sqlQuery += " and subject like '%"+subject+"%' ";
			 	        }
			             //建议时间-开始
			 	        String createTimeBegin = crm.getString("createTimeBegin", "");
			             if(!StringUtil.isEmpty(createTimeBegin)){
			             	sqlQuery += " and create_time >= to_date('"+createTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
			 	        }
			 	        //建议时间-结束
			 	        String createTimeEnd = crm.getString("createTimeEnd", "");
			             if(!StringUtil.isEmpty(createTimeEnd)){
			             	sqlQuery += " and create_time <= to_date('"+createTimeEnd+" 23:59:59 ','yyyy-MM-dd hh24:mi:ss ') ";
			 	        }
			             sqlQuery += " and demand_id is null order by create_time desc";
	        return this.findObjectsBySQL(sqlQuery,pager);
        }
        //----------------------------------------手机功能-------------------结束
        //建议查看---------------------------------------新需求----update   2013-11-13
        else{
		    //other 1代表是需求,2代表的是活动,3代表的是系统功能的需求
		    String other = crm.getString("other", "");
	        //---------------------------------拼接ＳＱＬ语句
	        String sqlOne =	" select a.name name,b.subject subject,to_char(b.create_time,'yyyy-MM-dd hh24:mi:ss ') create_time,b.user_code user_code,b.id id,b.demand_id demand_id,a.demandsort_id demandsort_id," +
	        		" b.status status,b.proposed_adoption proposed_adoption,b.initial_audit initial_audit,b.feasibility_audit feasibility_audit,b.suggestion_sort suggestion_sort,b.update_flag update_flag,b.initial_audit_time  from inw_demand a,inw_suggestion b ";
	        String sqlQuery = "";
	        //查询状态---------------------------------------------开始
	        //查询全部的SQL语句的拼接,这里面去掉了之前所用到的已阅,未阅,全部
            	 sqlQuery = sqlOne+" where a.id = b.demand_id ";
            	 //针对other的不同,other是1代表需求,2代表活动,3代表系统功能的需求,在后台中,将需求和系统功能的需求放在一起查询
	        	 if("2".equals(other)){
	        		 sqlQuery +=" and a.other='"+other+"' ";
	        	 }else{
	        		 sqlQuery +=" and a.other in ('1','3')  ";
	        	 }
            //查询状态---------------------------------------------结束
            //获取查询的条件
	        	 
	       //update 2014-09-23 gw 查询条件，用来判别是否是“活动建议还原” 	 
	           String feasibilityAudit = crm.getString("feasibilityAudit", "");
	 	    if(!StringUtil.isEmpty(feasibilityAudit)){
	 	       sqlQuery += " and b.feasibility_audit='Y' ";
	 	    }else{
	 	       sqlQuery += " and ( b.feasibility_audit='N' or b.feasibility_audit is null ) ";	
	 	    }
	        //需求表的需求的名称
	        String name = crm.getString("name", "");
	        if(!StringUtil.isEmpty(name)){
	        	 sqlQuery += " and a.name like '%"+name+"%' ";
	        }
	        //建议的建议人
	        String suggestionUserCode = crm.getString("suggestionUserCode", "");
	        if(!StringUtil.isEmpty(suggestionUserCode)){
	        	 sqlQuery += " and b.user_code like '%"+suggestionUserCode+"%' ";
	        }
	        //建议主题
	        String subject = crm.getString("subject", "");
            if(!StringUtil.isEmpty(subject)){
            	sqlQuery += " and b.subject like '%"+subject+"%' ";
	        }
            //建议时间-开始
	        String createTimeBegin = crm.getString("createTimeBegin", "");
            if(!StringUtil.isEmpty(createTimeBegin)){
            	sqlQuery += " and b.create_time >= to_date('"+createTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
	        }
	        //建议时间-结束
	        String createTimeEnd = crm.getString("createTimeEnd", "");
            if(!StringUtil.isEmpty(createTimeEnd)){
            	sqlQuery += " and b.create_time <= to_date('"+createTimeEnd+" 23:59:59 ','yyyy-MM-dd hh24:mi:ss ') ";
	        }
            
            //建议状态   update 2013-12-23  gw   (添加状态的查询条件)
	        String status = crm.getString("status", "");
            if(!StringUtil.isEmpty(status)){
            	sqlQuery += " and b.status = '"+status+"' ";
	        }
            
            //initialAudit 建议分流到具体业务部门前的审核   yxzz 2014-06-24 
            String initialAudit = crm.getString("initialAudit", "");
            if(!StringUtil.isEmpty(initialAudit)){
            	sqlQuery += " and b.initial_audit = '"+initialAudit+"' ";
            }
            

            //-----额外添加----需求分类的查询条件及相关的查询结果-----新需求
            String sql = " select p.name demandsortname,q.name name ,subject,create_time,q.user_code,q.id,demand_id,demandsort_id, " +
            		" q.status status,q.proposed_adoption proposed_adoption,q.initial_audit initial_audit,q.feasibility_audit feasibility_audit,q.suggestion_sort suggestion_sort,q.update_flag update_flag,q.initial_audit_time  "+
            		" from inw_demandsort p,("+sqlQuery+") q where p.id = q.demandsort_id ";
            String demandsortId = crm.getString("demandsortId","");
            if(!StringUtil.isEmpty(demandsortId)){
            	sql += " and q.demandsort_id = '"+demandsortId+"'";
            }
            
            //活动查看的菜单----查看的建议的建议为:可以查询全部,但是仅仅只能修改字段status为0代表未受理,1代表已受理,待评审 2代表未采纳,3代表已采纳
            
            /*建议初次审核的菜单------查看的建议为项目型的建议:可以查询字段status建议状态(0代表未受理,1代表已受理,待评审 2代表未采纳,3代表已采纳,4代表项目初次审核未通过,5代表项目初次审核通过,6代表项目可行性审核不通过,7代表项目可行性审核通过) 大于3的
                                                                                              但是  只能修改status为3,4,5这三种情况*/
             
            /*建议可行性审核的菜单------查看的建议为项目型的建议:可以查询字段status建议状态(0代表未受理,1代表已受理,待评审 2代表未采纳,3代表已采纳,4代表项目初次审核未通过,5代表项目初次审核通过,6代表项目可行性审核不通过,7代表项目可行性审核通过) 大于5的
                                 但是  只能修改status为5,6,7这三种情况*/
            String strAction = crm.getString("strAction", "");
            //最低等级的建议查看权限------活动查看菜单(以后可能改名)--的权限
            if("activitySuggestion".equals(strAction)||"activitySuggestionRestore".equals(strAction)){
            	
            }
            //"初次审核"菜单的权限
            else if("initialAudit".equals(strAction)){
            	sql += " and q.suggestion_sort = '2' and q.status in (3,4,5,6,7)";
            }
            //"可行性审核"菜单的权限   auditLevel = feasibilityLevel
            else{
            	sql += " and q.suggestion_sort = '2' and q.status in (5,6,7)";
            }
                sql += " order by create_time desc ";
             //  sql += " "
            return this.findObjectsBySQL(sql,pager);
        }   
	}

	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return List
	 */
	public List getInwViewpeopleIsExist(String id, String userCode) {
		String sql = " select * from inw_viewpeople where suggestionid = '"+id+"' and user_code = '"+userCode+"'";
		return this.findObjectsBySQL(sql,new Pager());
	}

	/**
	 * 建议查看--建议回复之前对回复内容的不为空校验
	 * @author 2013-09-03
	 * @param inwSuggestion
	 * @param errors
	 * @param defCharacterCoding
	 * @return boolean
	 */
	/*public boolean getReplyContent(InwSuggestion inwSuggestion,BindException errors,String characterCoding) {
		String replyContent = inwSuggestion.getReplyTontent();
		if(StringUtil.isEmpty(replyContent)){
			return true;
		}
		return false;
	}*/

	/**
	 * 建议--通过demandId获取InwSuggestion
	 * @author 2013-09-03
	 * @param demandId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<InwSuggestion> getInwSuggestionByDemandid(final Long demandId) {
	    String hql = " from InwSuggestion where demandId = '"+demandId+"' ";
		return this.findObjectsByHqlQuery(hql);
	}

	/**
     * 审核(批量审核)
     * @author 2014-06-24
     * @param ids
     */
	public void checkInwSuggestionList(String ids) {
        String[] idsList = ids.split(",");
    	for(int i=0;i<idsList.length;i++){
    		InwSuggestion inwSuggestion = (InwSuggestion) getHibernateTemplate().get(InwSuggestion.class, Long.parseLong(idsList[i]) );
    		inwSuggestion.setInitialAudit("Y");
    		if(null==inwSuggestion.getInitialAuditTime()){
    			inwSuggestion.setInitialAuditTime(new Date());
    		}
    		this.getHibernateTemplate().saveOrUpdate(inwSuggestion);
    	}
	}

	/**
     * 创新共赢的建议的查询 方法--具体业务部门查询
     * @author gw 2014-07-01
     * @param crm
     * @param pager
     * @return list
     */
	public List getSuggestionForDepartment(CommonRecord crm, Pager pager) {

	    //other 1代表是需求,2代表的是活动,3代表的是系统功能的需求
	    String other = crm.getString("other", "");
        //---------------------------------拼接ＳＱＬ语句
        String sqlOne =	" select a.name name,b.subject subject,to_char(b.create_time,'yyyy-MM-dd hh24:mi:ss ') create_time,b.user_code user_code,b.id id,b.demand_id demand_id,a.demandsort_id demandsort_id," +
        		" b.status status,b.proposed_adoption proposed_adoption,b.initial_audit initial_audit,b.feasibility_audit feasibility_audit,b.suggestion_sort suggestion_sort  from inw_demand a,inw_suggestion b ";
        String sqlQuery = "";
        //查询状态---------------------------------------------开始
        //查询全部的SQL语句的拼接,这里面去掉了之前所用到的已阅,未阅,全部
        	 sqlQuery = sqlOne+" where a.id = b.demand_id and ( b.feasibility_audit='N' or b.feasibility_audit is null ) ";
        	 //针对other的不同,other是1代表需求,2代表活动,3代表系统功能的需求,在后台中,将需求和系统功能的需求放在一起查询
        	 if("2".equals(other)){
        		 sqlQuery +=" and a.other='"+other+"' ";
        	 }else{
        		 sqlQuery +=" and a.other in ('1','3')  ";
        	 }
        	 //initialAudit 建议分流到具体业务部门前的已审核   yxzz 2014-06-24 
        	 sqlQuery += "and b.initial_audit = 'Y' ";
        //查询状态---------------------------------------------结束
        //获取查询的条件
        //需求表的需求的名称
        String name = crm.getString("name", "");
        if(!StringUtil.isEmpty(name)){
        	 sqlQuery += " and a.name like '%"+name+"%' ";
        }
        //建议的建议人
        String suggestionUserCode = crm.getString("suggestionUserCode", "");
        if(!StringUtil.isEmpty(suggestionUserCode)){
        	 sqlQuery += " and b.user_code like '%"+suggestionUserCode+"%' ";
        }
        //建议主题
        String subject = crm.getString("subject", "");
        if(!StringUtil.isEmpty(subject)){
        	sqlQuery += " and b.subject like '%"+subject+"%' ";
        }
        //建议时间-开始
        String createTimeBegin = crm.getString("createTimeBegin", "");
        if(!StringUtil.isEmpty(createTimeBegin)){
        	sqlQuery += " and b.create_time >= to_date('"+createTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
        }
        //建议时间-结束
        String createTimeEnd = crm.getString("createTimeEnd", "");
        if(!StringUtil.isEmpty(createTimeEnd)){
        	sqlQuery += " and b.create_time <= to_date('"+createTimeEnd+" 23:59:59 ','yyyy-MM-dd hh24:mi:ss ') ";
        }
        
        //建议状态
        String status = crm.getString("status", "");
        if(!StringUtil.isEmpty(status)){
        	sqlQuery += " and b.status = '"+status+"' ";
        }
        
        //建议回复审核
        String firstReplyAudit = crm.getString("firstReplyAudit", "");
        if(!StringUtil.isEmpty(firstReplyAudit)){
        	sqlQuery += " and b.first_reply_audit = '"+firstReplyAudit+"' ";
        }else{
        	sqlQuery += " and b.first_reply_audit is null ";
        }
        
        
        
        //部门的建议权限(范围)
        String  demandIdListTwo = crm.getString("demandIdListTwo", "");
        if(!StringUtil.isEmpty(demandIdListTwo)){
        	sqlQuery += " and a.id in("+demandIdListTwo+") ";
        }
        
        
        //-----额外添加----需求分类的查询条件及相关的查询结果-----新需求
        String sql = " select p.name demandsortname,q.name name ,subject,create_time,q.user_code,q.id,demand_id,demandsort_id, " +
        		" q.status status,q.proposed_adoption proposed_adoption,q.initial_audit initial_audit,q.feasibility_audit feasibility_audit,q.suggestion_sort suggestion_sort "+
        		" from inw_demandsort p,("+sqlQuery+") q where p.id = q.demandsort_id ";
        String demandsortId = crm.getString("demandsortId","");
        if(!StringUtil.isEmpty(demandsortId)){
        	sql += " and q.demandsort_id = '"+demandsortId+"'";
        }
        
        //活动查看的菜单----查看的建议的建议为:可以查询全部,但是仅仅只能修改字段status为0代表未受理,1代表已受理,待评审 2代表未采纳,3代表已采纳
        
        /*建议初次审核的菜单------查看的建议为项目型的建议:可以查询字段status建议状态(0代表未受理,1代表已受理,待评审 2代表未采纳,3代表已采纳,4代表项目初次审核未通过,5代表项目初次审核通过,6代表项目可行性审核不通过,7代表项目可行性审核通过) 大于3的
                                                                                          但是  只能修改status为3,4,5这三种情况*/
         
        /*建议可行性审核的菜单------查看的建议为项目型的建议:可以查询字段status建议状态(0代表未受理,1代表已受理,待评审 2代表未采纳,3代表已采纳,4代表项目初次审核未通过,5代表项目初次审核通过,6代表项目可行性审核不通过,7代表项目可行性审核通过) 大于5的
                             但是  只能修改status为5,6,7这三种情况*/
        String strAction = crm.getString("strAction", "");
        //最低等级的建议查看权限------活动查看菜单(以后可能改名)--的权限
        if("activitySuggestionDepart".equals(strAction)){
        	
        }
        //"初次审核"菜单的权限
        else if("initialAuditDepart".equals(strAction)){
        	sql += " and q.suggestion_sort = '2' and q.status in (3,4,5,6,7)";
        }
        //"可行性审核"菜单的权限   auditLevel = feasibilityAuditDepart
        else{
        	sql += " and q.suggestion_sort = '2' and q.status in (5,6,7)";
        }
            sql += " order by create_time desc ";
        return this.findObjectsBySQL(sql,pager);

	}
	
}
