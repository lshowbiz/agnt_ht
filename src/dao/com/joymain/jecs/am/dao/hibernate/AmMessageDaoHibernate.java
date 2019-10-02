
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.dao.AmMessageDao;
import com.joymain.jecs.am.model.AmMessage;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AmMessageDaoHibernate extends BaseDaoHibernate implements AmMessageDao {

	
	

	/*
	 * 公司人员能看到的消息
	 * (non-Javadoc)
	 * @see com.joymain.jecs.am.dao.AmMessageDao#getCompanyAmMessages(com.joymain.jecs.util.data.CommonRecord, com.joymain.jecs.util.data.Pager)
	 */
	public List getCompanyAmMessages(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		String loginUserNo = crm.getString("loginUserNo", "");
		String hql = "from AmMessage  amMessage  where 1=1 " ;
				//" and amMessage.status >= 0 " +	//公司人员不能看到代理商还没发送的消息

		String searchFlag = crm.getString("searchFlag", "");
		if("company".equals(searchFlag)){
			hql+=" and (amMessage.senderNo='"+loginUserNo+"' or  amMessage.receiverNo='"+loginUserNo+"'  )";
		}else if("type".equals(searchFlag)){
			hql+=" and msgClassNo in ("+crm.getString("amMessageType", "")+")";
		}
		
		//or amMessage.receiverNo is null
		
		String reply_status = crm.getString("reply_status","");
    	if("3".equals(reply_status)){
    		hql +=" and amMessage.status >= 0 and amMessage.status <2 and amMessage.replyContent is null ";
    	}else if("4".equals(reply_status)){
    		hql +=" and amMessage.replyContent is not null ";
    	}else{
    		hql +=" and amMessage.status >= 0 and amMessage.status <2 and amMessage.replyContent is null ";
    	}
    	
		/*
		String userName = crm.getString("userName","");
    	if(StringUtils.isNotEmpty(userName)){
    		hql += " and sysUser.userName='"+userName+"'";
    	}
		*/
		String userName = crm.getString("userName","");
    	if(StringUtils.isNotEmpty(userName)){
    		hql += " and amMessage.senderName='"+userName+"'";
    	}
    	
		String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agentNo='"+agentNo+"'";
    	}
    	
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.companyCode='"+companyCode+"'";
    	}
    	
		String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.sendRoute ='"+sendRoute+"'";
    	}
    	
    	String msgClassNo = crm.getString("msgClassNo","");
    	if(StringUtils.isNotEmpty(msgClassNo)){
    		hql += " and amMessage.msgClassNo='"+msgClassNo+"'";
    	}
    	
    	String selectTime = crm.getString("selectTime","");
    	if(StringUtils.equals(selectTime, "issueTime")){
    		selectTime = "amMessage.issueTime";
    	}else if(StringUtils.equals(selectTime, "replyTime")){
    		selectTime = "amMessage.replyTime";
    	}
    	
    	String startSearchTime = crm.getString("startSearchTime", "");
		if (!StringUtils.isEmpty(startSearchTime)) {
			hql += " and " + selectTime + " >=to_date('" + (startSearchTime+" 00:00:00") + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endSearchTime = crm.getString("endSearchTime", "");
		if (!StringUtils.isEmpty(endSearchTime)) {
			hql += " and " + selectTime + " <=to_date('" + (endSearchTime+" 23:59:59") + "','yyyy-mm-dd hh24:mi:ss')";
		}
    	
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by amMessage.replyTime desc,amMessage.issueTime desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	
	/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.am.dao.AmMessageDao#getAmMessages(com.joymain.jecs.am.model.AmMessage)
     */
    public List getAmMessages(final AmMessage amMessage) {
        return getHibernateTemplate().find("from AmMessage");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amMessage == null) {
            return getHibernateTemplate().find("from AmMessage");
        } else {
            // filter on properties set in the amMessage
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amMessage).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmMessage.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmMessageDao#getAmMessage(String uniNo)
     */
    public AmMessage getAmMessage(final String uniNo) {
        AmMessage amMessage = (AmMessage) getHibernateTemplate().get(AmMessage.class, uniNo);
        if (amMessage == null) {
            log.warn("uh oh, amMessage with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(AmMessage.class, uniNo);
        }

        return amMessage;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmMessageDao#saveAmMessage(AmMessage amMessage)
     */    
    public void saveAmMessage(final AmMessage amMessage) {
        getHibernateTemplate().saveOrUpdate(amMessage);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmMessageDao#removeAmMessage(String uniNo)
     */
    public void removeAmMessage(final String uniNo) {
        getHibernateTemplate().delete(getAmMessage(uniNo));
    }
    
    
    
    public List getAgentAmMessages(CommonRecord crm, Pager pager){
    	String loginUserNo = crm.getString("loginUserNo", "");
    	String hql = "from AmMessage amMessage where 1=1  and  amMessage.agentNo='"+loginUserNo+"' " +
    			"and ((amMessage.sendRoute='0' and amMessage.status>2) or (amMessage.sendRoute='1') )";
    	
    	String reply_status = crm.getString("reply_status","");
    	if("2".equals(reply_status)){    		
    		hql +="and amMessage.status =3 ";
    	}else if("3".equals(reply_status)){
    		hql +="and  amMessage.status <3 and amMessage.status >=0 ";
    	}else if("4".equals(reply_status)){
    		hql +="and  amMessage.status >3 ";
    	}
    	
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.companyCode='"+companyCode+"'";
    	}
    	
    	String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.sendRoute ='"+sendRoute+"'";
    	}
    	
    	String msgClassNo = crm.getString("msgClassNo","");
    	if(StringUtils.isNotEmpty(msgClassNo)){
    		hql += " and amMessage.msgClassNo='"+msgClassNo+"'";
    	}
    	
    	String status = crm.getString("status","");
    	if("-1".equals(status)){
    		hql += " and amMessage.status = -1 ";
    	}
    	if("1".equals(status)){
    		//hql += " and amMessage.status = 3 and amMessage.sendRoute='1' ";
    		hql += " and amMessage.status < 3  ";
    	}
    	if("2".equals(status)){
    		hql += " and amMessage.status >= 3   ";
    	}
    	if("3".equals(status)){
    		hql += " and amMessage.status > 3 and amMessage.sendRoute='0' ";
    	}
    	
    	
    	
    	
    	if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by issueTime desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    
    //added for getAmMessagesByCrm
    public List getAmMessagesByCrm(CommonRecord crm, Pager pager){
    	String hql = "select * from Am_Message amMessage where 1=1  ";
    	//管控人员是否连还没发送的消息都需要显示
    	
    	String recipientCaNo = crm.getString("recipientCaNo","");
    	if(StringUtils.isNotEmpty(recipientCaNo)){
    		hql = "select amMessage.* from Am_Message amMessage ,jsys_user sysUser where sysUser.User_Code = amMessage.Sender_No and sysUser.user_area='"+recipientCaNo+"'";
    	}
    	String msgStatus = crm.getString("msgStatus","");
    	String reply_status = crm.getString("reply_status","");
    	if("3".equals(reply_status)){
    		hql +=" and amMessage.status <2 and amMessage.status >=0  ";
    	}else if("4".equals(reply_status)){
    		hql +=" and amMessage.status >=2  ";
    	}else if("5".equals(reply_status)){
    		hql +=" and amMessage.status = 2 and amMessage.receiver_No is not null ";
    	}else{
    		if(!StringUtils.isNotEmpty(msgStatus)){
    			hql += " and amMessage.status <2 and amMessage.status >=0 ";
    		}
    	}    	
    	
    	if(StringUtils.isNotEmpty(msgStatus)){
    		switch(Integer.valueOf(msgStatus)){
    			case 1:
    				hql += " and amMessage.status =0 and amMessage.receiver_No is null ";break; //未管控
    			case 2:
    				hql += " and amMessage.status =0 and amMessage.receiver_No is not null ";break; //已管控
    			case 3:
    				//hql += " and (amMessage.status =0  or amMessage.status =2) and amMessage.receiverNo is not null ";break; //未发送(公司回复为2 ,公司主动发送的消息未0)
    				hql += " and amMessage.status =2 and amMessage.receiver_No is not null ";break; //未发送(公司回复为2 ,公司主动发送的消息未0)
    			case 4:
    				hql += " and amMessage.status >=3  ";break; //已发送
    		}
    	}
    	
    	
		
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
    	
    	String msgClassNo = crm.getString("msgClassNo","");
    	if(StringUtils.isNotEmpty(msgClassNo)){
    		hql += " and amMessage.msg_Class_No='"+msgClassNo+"'";
    	}
    	
    	String userName = crm.getString("userName","");
    	if(StringUtils.isNotEmpty(userName)){
    		hql += " and amMessage.sender_Name='"+userName+"'";
    	}
    	
    	String senderNo = crm.getString("senderNo","");
    	if(StringUtils.isNotEmpty(senderNo)){
    		hql += " and amMessage.sender_No='"+senderNo+"'";
    	}
    	
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and amMessage.status in ("+status+")";
    	}
    	String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agent_No='"+agentNo+"'";
    	}
    	
    	String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.send_Route ='"+sendRoute+"'";
    	}
    	
    	String receiverNo = crm.getString("receiverNo","");
    	if(StringUtils.isNotEmpty(receiverNo)){
    		hql += " and amMessage.receiver_No ='"+receiverNo+"'";
    	}
    	
    	String selectTime = crm.getString("selectTime","");
    	if(StringUtils.equals(selectTime, "issueTime")){
    		selectTime = "issue_Time";
    	}else if(StringUtils.equals(selectTime, "replyTime")){
    		selectTime = "reply_Time";
    	}
    	
    	String startSearchTime = crm.getString("startSearchTime", "");
		if (!StringUtils.isEmpty(startSearchTime)) {
			hql += " and " + selectTime + " >=to_date('" + (startSearchTime+" 00:00:00") + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endSearchTime = crm.getString("endSearchTime", "");
		if (!StringUtils.isEmpty(endSearchTime)) {
			hql += " and " + selectTime + " <=to_date('" + (endSearchTime+" 23:59:59") + "','yyyy-mm-dd hh24:mi:ss')";
		}
    	
    	if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by amMessage.reply_Time desc,amMessage.issue_Time desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
    	return this.findObjectsBySQL(hql, pager);
		//return this.findObjectsByHqlQuery(hql, pager);
    	
    }
    
    
    //add by lihao,信息管理菜单增加部门筛选项查询数据
    @Override
    public List getAmMessagesByCrm2(CommonRecord crm, Pager pager){
    	/*
    	 * SELECT amMessage.*,(SELECT sysDepartment.department_name 
    	 	FROM jsys_manager sysManager,Am_Message amMessage,jsys_department sysDepartment
			WHERE sysManager.user_code =amMessage.receiver_no AND 
			sysDepartment.Department_Id = sysManager.department_id  ) 
			AS department_name FROM Am_Message amMessage WHERE 1=1	
    	 */
    	String hql = "SELECT amMessage.*,(SELECT sysDepartment.department_name FROM jsys_manager sysManager,jsys_department sysDepartment "
			+" WHERE sysManager.user_code =amMessage.receiver_no AND sysDepartment.Department_Id = sysManager.department_id  ) "
			+" AS department_name FROM Am_Message amMessage WHERE 1=1 ";
    	//管控人员是否连还没发送的消息都需要显示
    	
    	String recipientCaNo = crm.getString("recipientCaNo","");
    	if(StringUtils.isNotEmpty(recipientCaNo)){
    		hql = "select amMessage.*,(SELECT sysDepartment.department_name FROM jsys_manager sysManager,jsys_department sysDepartment "
			+" WHERE sysManager.user_code =amMessage.receiver_no AND sysDepartment.Department_Id = sysManager.department_id  ) "
			+" AS department_name from Am_Message amMessage ,jsys_user sysUser where sysUser.User_Code = amMessage.Sender_No and sysUser.user_area='"+recipientCaNo+"'";
    	}
    	
    	String msgStatus = crm.getString("msgStatus","");
    	String reply_status = crm.getString("reply_status","");
    	if("3".equals(reply_status)){
    		hql +=" and amMessage.status <2 and amMessage.status >=0  ";
    	}else if("4".equals(reply_status)){
    		hql +=" and amMessage.status >=2  ";
    	}else if("5".equals(reply_status)){
    		hql +=" and amMessage.status = 2 and amMessage.receiver_No is not null ";
    	}else{
    		if(!StringUtils.isNotEmpty(msgStatus)){
    			hql += " and amMessage.status <2 and amMessage.status >=0 ";
    		}
    	}    	
    	
    	if(StringUtils.isNotEmpty(msgStatus)){
    		switch(Integer.valueOf(msgStatus)){
    			case 1:
    				hql += " and amMessage.status =0 and amMessage.receiver_No is null ";break; //未管控
    			case 2:
    				hql += " and amMessage.status =0 and amMessage.receiver_No is not null ";break; //已管控
    			case 3:
    				//hql += " and (amMessage.status =0  or amMessage.status =2) and amMessage.receiverNo is not null ";break; //未发送(公司回复为2 ,公司主动发送的消息未0)
    				hql += " and amMessage.status =2 and amMessage.receiver_No is not null ";break; //未发送(公司回复为2 ,公司主动发送的消息未0)
    			case 4:
    				hql += " and amMessage.status >=3  ";break; //已发送
    		}
    	}
    	
    	
		
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
    	
    	String msgClassNo = crm.getString("msgClassNo","");
    	if(StringUtils.isNotEmpty(msgClassNo)){
    		hql += " and amMessage.msg_Class_No='"+msgClassNo+"'";
    	}
    	
    	String userName = crm.getString("userName","");
    	if(StringUtils.isNotEmpty(userName)){
    		hql += " and amMessage.sender_Name='"+userName+"'";
    	}
    	
    	String senderNo = crm.getString("senderNo","");
    	if(StringUtils.isNotEmpty(senderNo)){
    		hql += " and amMessage.sender_No='"+senderNo+"'";
    	}
    	
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and amMessage.status in ("+status+")";
    	}
    	String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agent_No='"+agentNo+"'";
    	}
    	
    	String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.send_Route ='"+sendRoute+"'";
    	}
    	
    	String receiverNo = crm.getString("receiverNo","");
    	if(StringUtils.isNotEmpty(receiverNo)){
    		hql += " and amMessage.receiver_No ='"+receiverNo+"'";
    	}
    	
    	//add by lihao ,会员信息→信息管理查询时加上部门名称字段
    	String departmentName = crm.getString("departmentName","");
    	if(!StringUtils.isEmpty(departmentName)){
    		/*
    		 receiver_no in (SELECT amMessage.receiver_no
      		FROM am_message amMessage,jsys_manager sysManager,jsys_department sysDepartment
      		WHERE amMessage.Receiver_No=sysManager.User_Code AND sysDepartment.Department_Id = sysManager.Department_Id   
      		AND sysDepartment.department_name = '40' )
    		 
    		 */
    		String realDepartmentName = "";
    		switch (Integer.valueOf(departmentName)) {
			case 1:
				realDepartmentName = "财务部";
				break;
			case 2:
				realDepartmentName = "售后部";
				break;
			case 3:
				realDepartmentName = "客服部";
				break;
			case 4:
				realDepartmentName = "物流部";
				break;
			case 5:
				realDepartmentName = "业务支持部";
				break;
			case 6:
				realDepartmentName = "结算部";
				break;
			default:
				break;
			}
    		
    		hql += " and amMessage.receiver_No = (SELECT amMessage.receiver_no "
    	      		 + " FROM jsys_manager sysManager,jsys_department sysDepartment "
    	      		 + " WHERE amMessage.Receiver_No=sysManager.User_Code AND sysDepartment.Department_Id = sysManager.Department_Id "
    	      		 + " and sysDepartment.department_name='" + realDepartmentName + "') ";
    	}    	
    	
    	String selectTime = crm.getString("selectTime","");
    	if(StringUtils.equals(selectTime, "issueTime")){
    		selectTime = "issue_Time";
    	}else if(StringUtils.equals(selectTime, "replyTime")){
    		selectTime = "reply_Time";
    	}
    	
    	String startSearchTime = crm.getString("startSearchTime", "");
		if (!StringUtils.isEmpty(startSearchTime)) {
			hql += " and " + selectTime + " >=to_date('" + (startSearchTime+" 00:00:00") + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endSearchTime = crm.getString("endSearchTime", "");
		if (!StringUtils.isEmpty(endSearchTime)) {
			hql += " and " + selectTime + " <=to_date('" + (endSearchTime+" 23:59:59") + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
    	if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by amMessage.reply_Time desc,amMessage.issue_Time desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
    	return this.findObjectsBySQL(hql, pager);
		//return this.findObjectsByHqlQuery(hql, pager);
    	
    }


  //add by lihao,信息管理菜单增加部门筛选项查询数据
    @Override
    public List getAmMessagesByCrm3(CommonRecord crm, Pager pager){
    	
    	String hql = "SELECT amMessage.*,(SELECT sysDepartment.department_name FROM jsys_manager sysManager,jsys_department sysDepartment "
			+" WHERE sysManager.user_code =amMessage.receiver_no AND sysDepartment.Department_Id = sysManager.department_id  ) "
			+" AS department_name FROM Am_Message amMessage WHERE 1=1 ";
    	//管控人员是否连还没发送的消息都需要显示
    	
    	String recipientCaNo = crm.getString("recipientCaNo","");
    	if(StringUtils.isNotEmpty(recipientCaNo)){
    		hql = "select amMessage.*,(SELECT sysDepartment.department_name FROM jsys_manager sysManager,jsys_department sysDepartment "
			+" WHERE sysManager.user_code =amMessage.receiver_no AND sysDepartment.Department_Id = sysManager.department_id  ) "
			+" AS department_name from Am_Message amMessage ,jsys_user sysUser where sysUser.User_Code = amMessage.Sender_No and sysUser.user_area='"+recipientCaNo+"'";
    	}
    	
    	String msgStatus = crm.getString("msgStatus","");
    	String reply_status = crm.getString("reply_status","");
    	if("3".equals(reply_status)){
    		hql +=" and amMessage.status <2 and amMessage.status >=0  ";
    	}else if("4".equals(reply_status)){
    		hql +=" and amMessage.status >=2  ";
    	}else if("5".equals(reply_status)){
    		hql +=" and amMessage.status = 2 and amMessage.receiver_No is not null ";
    	}else{
    		if(!StringUtils.isNotEmpty(msgStatus)){
    			hql += " and amMessage.status <2 and amMessage.status >=0 ";
    		}
    	}    	
    	
    	if(StringUtils.isNotEmpty(msgStatus)){
    		switch(Integer.valueOf(msgStatus)){
    			case 1:
    				hql += " and amMessage.status =0 and amMessage.receiver_No is null ";break; //未管控
    			case 2:
    				hql += " and amMessage.status =0 and amMessage.receiver_No is not null ";break; //已管控
    			case 3:
    				//hql += " and (amMessage.status =0  or amMessage.status =2) and amMessage.receiverNo is not null ";break; //未发送(公司回复为2 ,公司主动发送的消息未0)
    				hql += " and amMessage.status =2 and amMessage.receiver_No is not null ";break; //未发送(公司回复为2 ,公司主动发送的消息未0)
    			case 4:
    				hql += " and amMessage.status >=3  ";break; //已发送
    		}
    	}
    	
    	
		
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
    	
    	String msgClassNo = crm.getString("msgClassNo","");
    	if(StringUtils.isNotEmpty(msgClassNo)){
    		hql += " and amMessage.msg_Class_No='"+msgClassNo+"'";
    	}
    	
    	String userName = crm.getString("userName","");
    	if(StringUtils.isNotEmpty(userName)){
    		hql += " and amMessage.sender_Name='"+userName+"'";
    	}
    	
    	String senderNo = crm.getString("senderNo","");
    	if(StringUtils.isNotEmpty(senderNo)){
    		hql += " and amMessage.sender_No='"+senderNo+"'";
    	}
    	
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and amMessage.status in ("+status+")";
    	}
    	String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agent_No='"+agentNo+"'";
    	}
    	
    	String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.send_Route ='"+sendRoute+"'";
    	}
    	
    	String receiverNo = crm.getString("receiverNo","");
    	if(StringUtils.isNotEmpty(receiverNo)){
    		hql += " and amMessage.receiver_No ='"+receiverNo+"'";
    	}
    	
    	//add by lihao ,会员信息→信息管理查询时加上部门名称字段
    	String departmentId = crm.getString("departmentId","");
    	if(!StringUtils.isEmpty(departmentId)){
    		hql += " and amMessage.receiver_No = (SELECT amMessage.receiver_no "
    	      		 + " FROM jsys_manager sysManager,jsys_department sysDepartment "
    	      		 + " WHERE amMessage.Receiver_No=sysManager.User_Code AND sysDepartment.Department_Id = sysManager.Department_Id "
    	      		 + " and sysDepartment.department_id='" + departmentId + "') ";
    	}    	
    	
    	String selectTime = crm.getString("selectTime","");
    	if(StringUtils.equals(selectTime, "issueTime")){
    		selectTime = "issue_Time";
    	}else if(StringUtils.equals(selectTime, "replyTime")){
    		selectTime = "reply_Time";
    	}
    	
    	String startSearchTime = crm.getString("startSearchTime", "");
		if (!StringUtils.isEmpty(startSearchTime)) {
			hql += " and " + selectTime + " >=to_date('" + (startSearchTime+" 00:00:00") + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endSearchTime = crm.getString("endSearchTime", "");
		if (!StringUtils.isEmpty(endSearchTime)) {
			hql += " and " + selectTime + " <=to_date('" + (endSearchTime+" 23:59:59") + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
    	if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by amMessage.reply_Time desc,amMessage.issue_Time desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
    	return this.findObjectsBySQL(hql, pager);
		//return this.findObjectsByHqlQuery(hql, pager);
    	
    }

    
	public String getAmMessagesReplyNum(CommonRecord crm) {
		String num = "0";
		
		String hql = "select uni_no from Am_Message amMessage where  amMessage.status >=3 ";
		
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
		
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}


	public String getAmMessagesNoReplyNum(CommonRecord crm) {
		String num = "0";
		
		String hql = "select uni_no from Am_Message amMessage where  amMessage.status <3 and amMessage.status >=0 ";
		
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
		
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}
	
	public String getAmMessagesNoCkeckNum(CommonRecord crm){
		String num = "0";
		//String hql = "select * from Am_Message amMessage where 1=1 and (amMessage.status =0  or amMessage.status =2) and amMessage.receiver_No is not null ";
		String hql = "select uni_no from Am_Message amMessage where 1=1 and  amMessage.status =2 and amMessage.receiver_No is not null ";
		
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
		
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}

	/**
	 * 代理商未回复信息统计
	 */
	public String getAgentNoReplyNum(CommonRecord crm) {
		String num = "0";
		
		String hql = "select uni_no from Am_Message amMessage where  amMessage.status <3 and amMessage.status >=0 " +
			" and ((amMessage.send_Route='0' and amMessage.status>2) or (amMessage.send_Route='1') )";
		
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
    	
    	String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agent_No='"+agentNo+"'";
    	}
		
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}

	/**
	 * 代理商已回复信息统计
	 */
	public String getAgentReplyNum(CommonRecord crm) {
		String num = "0";
		
		String hql = "select uni_no from Am_Message amMessage where  amMessage.status >3 " +
			" and ((amMessage.send_Route='0' and amMessage.status>2) or (amMessage.send_Route='1') )";
		
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
		
    	String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agent_No='"+agentNo+"'";
    	}
    	
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}


	public String getAgentNoReadNum(CommonRecord crm) {
		String num = "0";
		
		String hql = "select uni_no from Am_Message amMessage where  amMessage.status =3  " +
			" and ((amMessage.send_Route='0' and amMessage.status>2) or (amMessage.send_Route='1') )";
		
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
		
    	String agentNo = crm.getString("agentNo","");
    	if(StringUtils.isNotEmpty(agentNo)){
    		hql += " and amMessage.agent_No='"+agentNo+"'";
    	}
    	
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}


	public String getCompanyNoReplyNum(CommonRecord crm) {
		String num = "0";
		
		String loginUserNo = crm.getString("loginUserNo", "");
		String hql = "select uni_no from Am_Message  amMessage  where 1=1 " +
				" and amMessage.status >= 0 and amMessage.status <2  " ;	// and amMessage.reply_Content is null 公司人员不能看到代理商还没发送的消息
//				" and (amMessage.sender_No='"+loginUserNo+"' or  amMessage.receiver_No='"+loginUserNo+"'  )";
		
		String searchFlag = crm.getString("searchFlag", "");
		if("type".equals(searchFlag)){
			hql+=" and msg_class_no in ("+crm.getString("amMessageType", "")+")";
		}else{
			hql+=" and (amMessage.sender_No='"+loginUserNo+"' or  amMessage.receiver_No='"+loginUserNo+"'  )";
			
		}
    	
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
    	
		String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.send_Route ='"+sendRoute+"'";
    	}
    	
		//List list = this.findObjectsByHqlQuery(hql);
		
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}


	public String getCompanyReplyNum(CommonRecord crm) {
		String num = "0";
		
		String loginUserNo = crm.getString("loginUserNo", "");
		String hql = "select uni_no from Am_Message  amMessage  where 1=1 " +
				" and amMessage.status >= 3 ";
				//" and amMessage.reply_Content is not null " +	//公司人员不能看到代理商还没发送的消息
		String searchFlag = crm.getString("searchFlag", "");
		if("type".equals(searchFlag)){
			hql+=" and msg_class_no in ("+crm.getString("amMessageType", "")+")";
		}else{
			hql+=" and (amMessage.sender_No='"+loginUserNo+"' or  amMessage.receiver_No='"+loginUserNo+"'  )";
			
		}
    	
		String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    	}
    	
		String sendRoute = crm.getString("sendRoute","");
    	if(StringUtils.isNotEmpty(sendRoute)){
    		hql += " and amMessage.send_Route ='"+sendRoute+"'";
    	}
    	
		//List list = this.findObjectsByHqlQuery(hql);
    	//this.getTotalCountBySQL(hql);
		//if(!list.isEmpty())			
			num = String.valueOf(this.getTotalCountBySQL(hql));
		
		return num;
	}
	
	public int checkAmMessageBatch(String aanos){
		
		String  hql = "update  am_message t set t.status=3 where t.uni_no in("+aanos+")";
    	
    	this.getJdbcTemplate().execute(hql);
		
		return 0;
	}


	public List getRecentlyAmMessage(String userCode,String companyCode) {
		String num=(String) Constants.sysConfigMap.get(companyCode).get("ammessage.recent.num");
		String hql="from AmMessage where senderNo='"+userCode+"'and rownum<="+num+" order by issueTime desc";
		return this.getHibernateTemplate().find(hql);
	}


	public List getAmMessageByUserCode(String userCode, String msgClassNo) {
		return this.findObjectsByHqlQuery("From AmMessage where senderNo='"+userCode+"' and msgClassNo='"+msgClassNo+"'");
	}
	
}
