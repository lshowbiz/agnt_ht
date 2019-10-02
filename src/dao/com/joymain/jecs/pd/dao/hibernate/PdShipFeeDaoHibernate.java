
package com.joymain.jecs.pd.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdShipFeeDao;
import com.joymain.jecs.pd.model.PdShipFee;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class PdShipFeeDaoHibernate extends BaseDaoHibernate implements PdShipFeeDao {
	
	public HashMap<String, BigDecimal> getFeeMap() {
		HashMap<String, BigDecimal> feeMap = new HashMap<String, BigDecimal>();
		List<PdShipFee> list = getHibernateTemplate().find("from PdShipFee");
		for (PdShipFee f : list) {
			feeMap.put(f.getProvinceName(), f.getFee());
		}
		
		return feeMap;
	}

    /**
     * @see com.joymain.jecs.pd.dao.PdShipFeeDao#getPdShipFees(com.joymain.jecs.pd.model.PdShipFee)
     */
    public List getPdShipFees(final PdShipFee pdShipFee) {
        return getHibernateTemplate().find("from PdShipFee");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdShipFee == null) {
            return getHibernateTemplate().find("from PdShipFee");
        } else {
            // filter on properties set in the pdShipFee
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdShipFee).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdShipFee.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipFeeDao#getPdShipFee(String psfId)
     */
    public PdShipFee getPdShipFee(final String psfId) {
        PdShipFee pdShipFee = (PdShipFee) getHibernateTemplate().get(PdShipFee.class, psfId);
        if (pdShipFee == null) {
            log.warn("uh oh, pdShipFee with psfId '" + psfId + "' not found...");
            throw new ObjectRetrievalFailureException(PdShipFee.class, psfId);
        }

        return pdShipFee;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipFeeDao#savePdShipFee(PdShipFee pdShipFee)
     */    
    public void savePdShipFee(final PdShipFee pdShipFee) {
        getHibernateTemplate().saveOrUpdate(pdShipFee);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipFeeDao#removePdShipFee(String psfId)
     */
    public void removePdShipFee(final String psfId) {
        getHibernateTemplate().delete(getPdShipFee(psfId));
    }
    //added for getPdShipFeesByCrm
    public List getPdShipFeesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdShipFee pdShipFee where 1=1";
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
			hql += " order by provinceName asc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * Add By WuCF 20140813 发货单信息同步
     */
    public List getPdExportLogByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdExportLog pdExportLog where 1=1 ";
    	
    	//报表类型
    	String logType = crm.getString("logType", "");
    	if(StringUtils.isNotEmpty(logType)){
    		hql += " and pdExportLog.logType='"+logType.trim()+"' ";
    	}
    	
    	//报表状态
    	String logStatus = crm.getString("logStatus", "");
    	if(StringUtils.isNotEmpty(logStatus)){
    		hql += " and pdExportLog.logStatus='"+logStatus.trim()+"' ";
    	}
    	
    	//操作人
    	String userCode = crm.getString("userCode", "");
    	if(StringUtils.isNotEmpty(userCode)){
    		hql += " and pdExportLog.logUserCode='"+userCode.trim()+"' ";
    	}
    	
    	//导出开始时间
    	String logStartTime = crm.getString("logStartTime", "");
    	if (StringUtils.isNotEmpty(logStartTime)) {
    		hql += " and pdExportLog.logStartTime>= to_date ('" + logStartTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
    	String logEndTime = crm.getString("logEndTime", "");
		if (StringUtils.isNotEmpty(logEndTime)) {
			hql += " and pdExportLog.logStartTime<= to_date ('" + logEndTime
			+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
    	
		hql += " order by pdExportLog.logId desc ";
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
	/**
	 * Add By WuCF 20140909 短信查询功能
	 */
    public List getJpmSmssendInfoByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmSmssendInfo jpmSmssendInfo where 1=1 ";
    	
    	//短信类型
    	String smsType = crm.getString("smsType", "");
    	if(StringUtils.isNotEmpty(smsType)){
    		hql += " and jpmSmssendInfo.smsType='"+smsType.trim()+"' ";
    	}
    	
    	//短信接收人会员编号
    	String smsRecipient = crm.getString("smsRecipient", "");
    	if(StringUtils.isNotEmpty(smsRecipient)){
    		hql += " and jpmSmssendInfo.smsRecipient='"+smsRecipient.trim()+"' ";
    	}
    	
    	//操作人
    	String smsOperator = crm.getString("smsOperator", "");
    	if(StringUtils.isNotEmpty(smsOperator)){
    		hql += " and jpmSmssendInfo.smsOperator='"+smsOperator.trim()+"' ";
    	}
    	
    	//手机号码
    	String phoneNum = crm.getString("phoneNum", "");
    	if(StringUtils.isNotEmpty(phoneNum)){
    		hql += " and jpmSmssendInfo.phoneNum='"+phoneNum.trim()+"' ";
    	}
    	
    	//短信发送时间
    	String logStartTime = crm.getString("logStartTime", "");
    	if (StringUtils.isNotEmpty(logStartTime)) {
    		hql += " and jpmSmssendInfo.smsTime>= to_date ('" + logStartTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
    	String logEndTime = crm.getString("logEndTime", "");
		if (StringUtils.isNotEmpty(logEndTime)) {
			hql += " and jpmSmssendInfo.smsTime<= to_date ('" + logEndTime
			+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
    	
		hql += " order by jpmSmssendInfo.smsId desc ";
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 保存之前先做同一个地区的唯一性校验
     * @author gw 2015-01-20
     * @param pdShipFee
     */
	public boolean getUniqueResult(PdShipFee pdShipFee) {
		String hql = " from PdShipFee pdShipFee where pdShipFee.provinceName = '"+pdShipFee.getProvinceName()+"' ";
		if(!StringUtil.isEmpty(pdShipFee.getPsfId())){
			hql += " and pdShipFee.psfId <> '"+pdShipFee.getPsfId()+"'";
		}
		List<PdShipFee> list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Add By WuCF 20141118 接口日志功能
	 */
    public List getJocsInterfaceLogByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JocsInterfaceLog jocsInterfaceLog where 1=1 ";
    	
    	//日志收发类型
    	String logType = crm.getString("logType", "");
    	if(StringUtils.isNotEmpty(logType)){
    		hql += " and jocsInterfaceLog.logType='"+logType.trim()+"' ";
    	}
    	
    	//日志收发类型
    	String contentText = crm.getString("contentText", "");
    	if(StringUtils.isNotEmpty(contentText)){
    		hql += " and jocsInterfaceLog.content like '%"+contentText.trim()+"%' ";
    	}
    	
    	//日志数据来源
    	String flag = crm.getString("flag", "");
    	if(StringUtils.isNotEmpty(flag)){
    		hql += " and jocsInterfaceLog.flag='"+flag.trim()+"' ";
    	}
    	
    	//方法名称
    	String method = crm.getString("method", "");
    	if(StringUtils.isNotEmpty(method)){
    		hql += " and jocsInterfaceLog.method like '%"+method.trim()+"%' ";
    	}
    	
    	//短信发送时间
    	String logStartTime = crm.getString("logStartTime", "");
    	if (StringUtils.isNotEmpty(logStartTime)) {
    		hql += " and jocsInterfaceLog.reciverTime>= to_date ('" + logStartTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
    	String logEndTime = crm.getString("logEndTime", "");
		if (StringUtils.isNotEmpty(logEndTime)) {
			hql += " and jocsInterfaceLog.reciverTime<= to_date ('" + logEndTime
			+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
    	
		hql += " order by jocsInterfaceLog.logId desc ";
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * Add By WuCF 20141118 接口日志功能
	 */
    public List getJocsInterfaceRetransmisionByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JocsInterfaceRetransmission jocsInterfaceRetransmission where 1=1 ";
    	
    	//日志收发类型
    	String logType = crm.getString("logType", "");
    	if(StringUtils.isNotEmpty(logType)){
    		hql += " and jocsInterfaceRetransmission.logType='"+logType.trim()+"' ";
    	}
    	
    	//日志数据来源
    	String flag = crm.getString("flag", "");
    	if(StringUtils.isNotEmpty(flag)){
    		hql += " and jocsInterfaceRetransmission.flag='"+flag.trim()+"' ";
    	}
    	
    	//方法名称
    	String method = crm.getString("method", "");
    	if(StringUtils.isNotEmpty(method)){
    		hql += " and jocsInterfaceRetransmission.method like '%"+method.trim()+"%' ";
    	}
    	
    	//短信发送时间
    	String logStartTime = crm.getString("logStartTime", "");
    	if (StringUtils.isNotEmpty(logStartTime)) {
    		hql += " and jocsInterfaceRetransmission.reciverTime>= to_date ('" + logStartTime
			+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
    	String logEndTime = crm.getString("logEndTime", "");
		if (StringUtils.isNotEmpty(logEndTime)) {
			hql += " and jocsInterfaceRetransmission.reciverTime<= to_date ('" + logEndTime
			+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
    	
		hql += " order by jocsInterfaceRetransmission.logId desc ";
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
