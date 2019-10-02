
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class PdLogisticsBaseNumDaoHibernate extends BaseDaoHibernate implements PdLogisticsBaseNumDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao#getPdLogisticsBaseNums(com.joymain.jecs.pd.model.PdLogisticsBaseNum)
     */
    public List getPdLogisticsBaseNums(final PdLogisticsBaseNum pdLogisticsBaseNum) {
        return getHibernateTemplate().find("from PdLogisticsBaseNum");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdLogisticsBaseNum == null) {
            return getHibernateTemplate().find("from PdLogisticsBaseNum");
        } else {
            // filter on properties set in the pdLogisticsBaseNum
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdLogisticsBaseNum).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdLogisticsBaseNum.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao#getPdLogisticsBaseNum(BigDecimal numId)
     */
    public PdLogisticsBaseNum getPdLogisticsBaseNum(final BigDecimal numId) {
        PdLogisticsBaseNum pdLogisticsBaseNum = (PdLogisticsBaseNum) getHibernateTemplate().get(PdLogisticsBaseNum.class, numId);
        if (pdLogisticsBaseNum == null) {
            log.warn("uh oh, pdLogisticsBaseNum with numId '" + numId + "' not found...");
            throw new ObjectRetrievalFailureException(PdLogisticsBaseNum.class, numId);
        }

        return pdLogisticsBaseNum;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao#savePdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum)
     */    
    public void savePdLogisticsBaseNum(final PdLogisticsBaseNum pdLogisticsBaseNum) {
        getHibernateTemplate().saveOrUpdate(pdLogisticsBaseNum);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao#removePdLogisticsBaseNum(BigDecimal numId)
     */
    public void removePdLogisticsBaseNum(final BigDecimal numId) {
        getHibernateTemplate().delete(getPdLogisticsBaseNum(numId));
    }
    //added for getPdLogisticsBaseNumsByCrm
    public List getPdLogisticsBaseNumsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdLogisticsBaseNum pdLogisticsBaseNum where 1=1";
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
			hql += " order by numId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * @author gw 2014-12-01 根据baseId,PdLogisticsBaseNum_no查询
     * @param baseId 关联到物流状态接口物流信息表主键（本表外键）
     * @param PdLogisticsBaseNum_no  物流单号
     * @param otherOne do单号+wmsDo+物流单号  modify fu 2015-11-26
     * @return pdLogisticsBaseNum
     */
	public PdLogisticsBaseNum getPdLogisticsBaseNumByBaseIdAndNo(String baseId,String PdLogisticsBaseNum_no,String otherOne) {
		//String hql = "from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.baseId='"+baseId+"' and pdLogisticsBaseNum.pdLogisticsBaseNum_no = '"+PdLogisticsBaseNum_no+"' ";
		
		String hql = "from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.pdLogisticsBase.baseId='"+baseId+"' ";
		
        
		//modify by  fu 2015-09-22 物流单号存在为空的情况的。---------begin
		//具体的业务场景是：真实的DO单，一个DO单，有且仅有一个一个物流单号,并且商品不允许夸DO单
		/*if(!StringUtil.isEmpty(PdLogisticsBaseNum_no)){
        	hql += "  and pdLogisticsBaseNum.pdLogisticsBaseNum_no = '"+PdLogisticsBaseNum_no+"' ";
        }*/
		//modify by fu 2015-09-22 ----------------------------------end
		//modify by fu 2015-11-26 DO单下物流单号重复，为解决问题将上述代码注释掉
		
		// modify fu 2015-11-26 otherOne do单号+wmsDo+物流单号---begin 
		if(!StringUtil.isEmpty(otherOne)){
        	hql += "  and pdLogisticsBaseNum.otherOne = '"+otherOne+"' ";
        }
		// modify fu 2015-11-26 otherOne do单号+wmsDo+物流单号---end

		List list  = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return (PdLogisticsBaseNum) list.get(0);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * @author gw 2014-12-22
	 * 
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumByInter(Long baseId) {
		if(null==baseId){
			return null;
		}else{
			//String hql = " from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.baseId ='"+baseId.toString()+"'";
			
			String hql = " from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.pdLogisticsBase.baseId ='"+baseId.toString()+"'";

			List<PdLogisticsBaseNum> list = this.findObjectsByHqlQuery(hql);
			if(null!=list){
				if(list.size()>0){
					return list;
				}
			}
			return null;
		}
	}

	/**
	 * 根据物流单号查询物流信息
	 * @author gw 2015-02-01
	 * @param pdLogisticsBaseNumNo 物流单号
	 * @return pdLogisticsBaseNum
	 */
	public PdLogisticsBaseNum getPdLogisticsBaseNumByPdLogisticsBaseNumno(String pdLogisticsBaseNumNo) {
		if(null==pdLogisticsBaseNumNo){
			return null;
		}else{
			String hql = " from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.pdLogisticsBaseNum_no ='"+pdLogisticsBaseNumNo+"'";
			List<PdLogisticsBaseNum> list = this.findObjectsByHqlQuery(hql);
			if(null!=list){
				if(list.size()>0){
					return list.get(0);
				}
			}
			return null;
		}
	}

	/**
	 * 获取未完整获取物流单号信息的PdLogisticsBaseNum-定时器用到
	 * @author gw 2015-06-15
	 * @return
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumForPdMailReceipt() {
		log.info("在类PdLogisticsBaseNumDaoHibernate的方法中getPdLogisticsBaseNumForPdMailReceipt运行");
		//String sql = " select * from pd_logistics_base_num where not exists (select status_id from pd_mail_receipt ) ";
		//List<PdLogisticsBaseNum> list = this.findObjectsBySQL(sql);
		String hql = " from PdLogisticsBaseNum pdLogisticsBaseNum where not exists ( select pdMailReceipt.statusId from PdMailReceipt pdMailReceipt where pdLogisticsBaseNum.numId = pdMailReceipt.statusId ) ";
		List<PdLogisticsBaseNum> list = this.findObjectsByHqlQuery(hql);
		if(null!=list){
			if(list.size()>0){
				return list;
			}
		}
		return null;
	}

	/**
	 * 根据物流单号和订单号号或发货单号获取物流信息
	 * @author gw 2015-11-27
	 * @param pdLogisticsBaseNumNo 物流单号
	 * @return List
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumByParams(String pdLogisticsBaseNumNo,String siNo, String memberOrderNo) {
		if(null==pdLogisticsBaseNumNo){
			return null;
		}else{
			String baseList = "";
			String hqlOne = " from PdLogisticsBase pdLogisticsBase where 1=1 ";
            if(!StringUtil.isEmpty(siNo)){
            	hqlOne +=" and pdLogisticsBase.si_no = '"+siNo+"'";
			}else if(!StringUtil.isEmpty(memberOrderNo)){
				hqlOne +=" and pdLogisticsBase.member_order_no = '"+memberOrderNo+"' ";
			}else{
				return null;
			}
            List<PdLogisticsBase> listOne = this.findObjectsByHqlQuery(hqlOne);
            if(null!=listOne){
				if(listOne.size()>0){
					for(int i=0;i<listOne.size();i++){
						PdLogisticsBase pdLogisticsBase = listOne.get(i);
						Long baseId =  pdLogisticsBase.getBaseId();
						if(StringUtil.isEmpty(baseList)){
							baseList = "'"+baseId.toString()+"'";
						}else{
							baseList += ",'"+baseId.toString()+"'";
						}
						
					}
				}
			}
			String hql = " from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.pdLogisticsBaseNum_no ='"+pdLogisticsBaseNumNo+"'";
			if(!StringUtil.isEmpty(baseList)){
				   hql += " and  pdLogisticsBaseNum.pdLogisticsBase.baseId in ("+baseList+")";
			}
			List<PdLogisticsBaseNum> list = this.findObjectsByHqlQuery(hql);
			if(null!=list){
				if(list.size()>0){
					return list;
				}
			}
			return null;
		}
	}
	
	/**
	 * 物流查询定时器用到---获取DO相关的信息
	 * @author fu 2016-02-16
	 * @return list
	 */
	public List getDoForSql(){
		String sql = "select b.pdlogisticsbasenum_no mailNo,b.status status,a.si_no,a.member_order_no,b.num_id,a.base_id "+
			" from pd_logistics_base a,pd_logistics_base_num b,pd_send_info c  where a.base_id = b.base_id "+
			" and a.si_no = c.si_no  and c.order_flag <3 and a.other_one is null order by b.mail_time asc ";
        List list = this.findObjectsBySQL(sql);
        if(null!=list){
        	if(list.size()>0){
        		return list;
        	}
        }
		return null;
	}
}
