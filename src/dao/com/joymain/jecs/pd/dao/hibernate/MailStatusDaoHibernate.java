
package com.joymain.jecs.pd.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.dao.MailStatusDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class MailStatusDaoHibernate extends BaseDaoHibernate implements MailStatusDao {

    /**
     * @see com.joymain.jecs.pd.dao.MailStatusDao#getMailStatuss(com.joymain.jecs.pd.model.MailStatus)
     */
    public List getMailStatuss(final MailStatus mailStatus) {
        return getHibernateTemplate().find("from MailStatus");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (mailStatus == null) {
            return getHibernateTemplate().find("from MailStatus");
        } else {
            // filter on properties set in the mailStatus
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(mailStatus).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(MailStatus.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.MailStatusDao#getMailStatus(Long statusId)
     */
    public MailStatus getMailStatus(final Long statusId) {
        MailStatus mailStatus = (MailStatus) getHibernateTemplate().get(MailStatus.class, statusId);
        if (mailStatus == null) {
            log.warn("uh oh, mailStatus with statusId '" + statusId + "' not found...");
            throw new ObjectRetrievalFailureException(MailStatus.class, statusId);
        }

        return mailStatus;
    }

    /**
     * (定时器用到)
     * @see com.joymain.jecs.pd.dao.MailStatusDao#saveMailStatus(MailStatus mailStatus)
     */    
    public String saveMailStatus(MailStatus mailStatus) {
    	log.info("在类MailStatusDaoHibernate的方法中saveMailStatus运行");
    	try{
    		 //因为json转对象的时候，主键被弄成了0，因此这里重新赋值
    		 String hql = "select SEQ_PD.nextval from dual";
    		 Long statusId = mailStatus.getStatusId();
    		 if((null==statusId) || (0== statusId)){
    			 Long idOne = jdbcTemplate.queryForLong(hql);
    			 mailStatus.setStatusId(idOne);
    		 }
    		if((null!=mailStatus.getItems())&&(mailStatus.getItems().size()>0)){ 
	    		 for(int i=0;i<mailStatus.getItems().size();i++){
	    			Long itemsId =  mailStatus.getItems().get(i).getItemsId();
	    			if((null==itemsId) || (0== itemsId)){
		    			 Long idTwo = jdbcTemplate.queryForLong(hql);
		    			 mailStatus.getItems().get(i).setItemsId(idTwo);
	    			}
	    			//mailStatus.getItems().get(i).setStatusId(idOne.toString());
	    			 mailStatus.getItems().get(i).setMailStatus(mailStatus);
	    		 }
    		}else{
    			return Constants.INTERFACE_NULL;
    		}
    		 getHibernateTemplate().saveOrUpdate(mailStatus);
    		 return Constants.INTERFACE_NORMAL;
    	}catch(Exception e){
        	log.info("在类MailStatusDaoHibernate的方法中saveMailStatus运行异常"+e);
            e.printStackTrace();
    		//接口传递过来的数据保存是报异常
    		 return Constants.INTERFACE_SAVE_EXCEPTION;
    	}
    }

    /**
     * @see com.joymain.jecs.pd.dao.MailStatusDao#removeMailStatus(Long statusId)
     */
    public void removeMailStatus(final Long statusId) {
        getHibernateTemplate().delete(getMailStatus(statusId));
    }
    //added for getMailStatussByCrm
    public List getMailStatussByCrm(CommonRecord crm, Pager pager){
    	String hql = "from MailStatus mailStatus where 1=1";
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
			hql += " order by statusId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 根据物流单号查询物流跟踪的信息
     * @author gw 2015-01-10 modify by fu 2016-03-03
     * @param mailNo 物流单号
     * @return mailStatus
     */
	public MailStatus getMailStatusByMailNo(String mailNo) {
		log.info("在类MailStatusDaoHibernate的方法中getMailStatusByMailNo运行");
		String sqlPdLogisticsBaseNum = " select num_id,PDLOGISTICSBASENUM_NO mail_no,NAME logist_comp from pd_logistics_base_num where PDLOGISTICSBASENUM_NO = '"+mailNo+"'";
		List list = this.jdbcTemplate.queryForList(sqlPdLogisticsBaseNum);
		if((null!=list)&&(list.size()>0)){
			    MailStatus  mailStatusNew = new MailStatus();
			    Map map = (Map) list.get(0);
			    BigDecimal numId = (BigDecimal)map.get("num_id");
				//Long numId = Long.parseLong(numIdS);
			    //BigDecimal statusId = (BigDecimal)map.get("status_id");
			    String mail_no = (String)map.get("mail_no"); 
			    String logist_comp = (String)map.get("logist_comp");
			    mailStatusNew.setMail_no(mail_no);
			    mailStatusNew.setLogist_comp(logist_comp);
			    List<Items> items = new ArrayList();
				String sqlItems = "select items_id,accepttime,acceptaddress,event from items where num_id = '"+numId.toString()+"' order by accepttime desc";
				List listItems = this.jdbcTemplateReport.queryForList(sqlItems);
				
				if((null!=listItems)&&(listItems.size()>0)){
                       for(int i=0;i<listItems.size();i++){
                    	   Map mapItems = (Map) listItems.get(i);
                    	   Items itemsNew = new Items();
                    	   String acceptTime = (String)mapItems.get("accepttime");
                    	   String acceptAddress  = (String)mapItems.get("acceptaddress");
                    	   String remark = (String)mapItems.get("event");
                    	   itemsNew.setAcceptTime(acceptTime);
                    	   itemsNew.setAcceptAddress(acceptAddress);
                    	   itemsNew.setRemark(remark);
                    	   items.add(itemsNew);
                       }
				}
				mailStatusNew.setItems(items);
				return mailStatusNew;
		}else{
			return null;
		}
	}

	/**
	 * 获取主键
	 */
	public Long getId() {
		String hql = "select SEQ_PD.nextval from dual";
	    return jdbcTemplate.queryForLong(hql);
	}
	
	/**
     * 根据物流单号查询物流跟踪的信息----容灾库查询
     * @author gw 2016-01-27
     * @param mailNo 物流单号
     * @return Long
     */
	public Long getMailStatusByMailNoReport(String mailNo){
		log.info("在类MailStatusDaoHibernate的方法中getMailStatusByMailNoReport运行");
		String sql = " select status_id from mail_status where mail_no = '"+mailNo+"'";
		List list = this.getJdbcTemplateReport().queryForList(sql);
		if(null!=list && list.size()>0){
		    Map map = (Map) list.get(0);
		    BigDecimal statusId = (BigDecimal)map.get("status_id");
		    if((null!=statusId) && (!"0".equals(statusId.toString()))){
		    	return Long.parseLong(statusId.toString());
		    }
		}
		return null;
	}
	
	/**
     * 容灾库保存物流实时信息
     * @author gw 2016-01-27
     * @param mailStatus
     * @return String
     */
	public String sOrUMailStatusReport(MailStatus mailStatus,Long numId){
		log.info("在类MailStatusDaoHibernate的方法中sOrUMailStatusReport运行");
		try{
				 String sqlId = "select SEQ_PD.nextval from dual";
				 String mail_no = mailStatus.getMail_no();
				 String logist_comp = mailStatus.getLogist_comp();
				 //modify by fu 2016-03-02 ----------优化物流实时信息查询,将下面无用的方法注释掉---begin
				 //保存MailStatus，但是不保存Items
				 /*Long statusId = this.getMailStatusByMailNoReport(mail_no);
				 if((null==statusId) || (0== statusId)){
					 statusId = this.jdbcTemplate.queryForLong(sqlId);
					 String sqlMailStatus = "insert into mail_status(status_id,mail_no,logist_comp)  values('"+statusId+"','"+mail_no+"','"+logist_comp+"')";
					 //容灾库库保存等更新到生产机的时候再更打开
					 log.info("在类MailStatusDaoHibernate的方法中sOrUMailStatusReport运行:容灾库中保存mailStatus");
					 //在容灾库中保存
					 this.jdbcTemplateReport.execute(sqlMailStatus);
		 			//普通库保存等更新到生产机的时候再注释
					 //在普通中保存
					 //this.jdbcTemplate.execute(sqlMailStatus);
				 }
				
				 //查看数据库中已经保存的物流实时信息有几条
				 String sqlItemsIdlist = " select items_id from items where logistic_id = '"+statusId+"'";
				 List listItemsId = this.getJdbcTemplateReport().queryForList(sqlItemsIdlist);
				 int initItemsId = 0;
				 if(null!=listItemsId && listItemsId.size()>0){
					 initItemsId = listItemsId.size();
				 }
				 */
				//modify by fu 2016-03-02 ----------优化物流实时信息查询,将上面无用的方法注释掉---end
				 
				 //查看数据库中已经保存的物流实时信息有几条
				 String sqlItemsIdlist = " select items_id from items where num_id = '"+numId+"'";
				 List listItemsId = this.getJdbcTemplateReport().queryForList(sqlItemsIdlist);
				 int initItemsId = 0;
				 if(null!=listItemsId && listItemsId.size()>0){
					 initItemsId = listItemsId.size();
				 }
				 //查看数据库中已经保存的物流实时信息有几条
				 //更新未保存的物流实时信息
				 if((null!=mailStatus.getItems())&&(mailStatus.getItems().size()>0)){ 
						      for(int i=initItemsId;i<mailStatus.getItems().size();i++){
								    Items items = mailStatus.getItems().get(i);
					    			Long itemsId =  items.getItemsId();
					    			if((null==itemsId) || (0== itemsId)){
						    			 itemsId = this.jdbcTemplate.queryForLong(sqlId);
					    			}
					    			 String acceptTime = items.getAcceptTime();
					    			 String acceptAddress = items.getAcceptAddress();
					    			 String remark =  items.getRemark();
					    			 String sqlItems = "insert into items(items_id,num_id,accepttime,acceptaddress,event)  values('"+itemsId+"','"+numId.toString()+"','"+acceptTime+"','"+acceptAddress+"','"+remark+"')";
					    			 //容灾库库保存等更新到生产机的时候再更打开
					    			 //在容灾库中保存
									 log.info("在类MailStatusDaoHibernate的方法中sOrUMailStatusReport运行:容灾库中保存Items");
					    			 this.jdbcTemplateReport.execute(sqlItems);
					    			//普通库保存等更新到生产机的时候再注释
					    			 //在普通中保存
					    			 //this.jdbcTemplate.execute(sqlItems);
				    		 }
				 }
				return Constants.INTERFACE_NORMAL; 
		}catch(Exception e){
			 log.info("在类MailStatusDaoHibernate的方法中sOrUMailStatusReport运行异常:"+e.toString());
			 e.printStackTrace();
			 return null;
		}
	}
	
	/**
	 * 在容灾库保存pdMailReceipt
	 * @author modify by fu 2016-01-28
	 * @param pdMailReceipt
     * @return 
	 */
	public void ssavePdMailReceiptReport(PdMailReceipt pdMailReceipt){
		 String sqlItems = "insert into PD_MAIL_RECEIPT(MAIL_RECEIPT_ID,STATUS_ID,OTHER) values('"+pdMailReceipt.getMailReceiptId()+"','"+pdMailReceipt.getStatusId()+"','"+pdMailReceipt.getOther()+"')";
		 this.jdbcTemplateReport.execute(sqlItems);
	}
	
}
