
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;



public class PdReturnPurchaseDetailDaoHibernate extends BaseDaoHibernate implements PdReturnPurchaseDetailDao {

	
    public List getTotalPdReturnPurchaseDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
    	String sql="select pm.product_no,pm.product_name,sum(pdd.qty) qty from pd_return_purchase pd,pd_return_purchase_detail pdd,jpm_product_sale_new pm" 
   		 +" where pd.rp_no=pdd.rp_no and pdd.product_no=pm.product_no and pd.company_code=pm.company_code ";
   		
    	//Modify By WuCF 20150602 添加仓库权限统计
		String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if(StringUtils.isNotEmpty(defaultWarehouse)){
			sql += " and pd.return_Warehouse_No in("+defaultWarehouse+")";
    	}
		
		//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", ""); 
		if (StringUtils.isNotEmpty(createUsrNo)) {
			sql += " and pd.CREATE_USR_CODE ='" + createUsrNo.trim() + "' ";
		}
    	
   		String rpNo = crm.getString("rpNo", "");
       	if(StringUtils.isNotEmpty(rpNo)){
       		sql +=" and pd.rp_No='"+rpNo+"'";
       	}
       	
   		String companyCode = crm.getString("companyCode","");
   		if(StringUtils.isNotEmpty(companyCode)){
   		sql += " and pd.company_code='"+companyCode+"'";
   		}
   		String returnWarehouseNo = crm.getString("returnWarehouseNo","");
   		if(StringUtils.isNotEmpty(returnWarehouseNo)){
   		sql += " and pd.return_Warehouse_No='"+returnWarehouseNo+"'";
   		}
   		String createTimeStart = crm.getString("createTimeStart","");
   		String createTimeEnd = crm.getString("createTimeEnd","");
   		if(StringUtils.isNotEmpty(createTimeStart)){
   		sql += " and pd.create_Time >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
   		}
   		if(StringUtils.isNotEmpty(createTimeEnd)){
   		sql += " and pd.create_Time <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
   		}
   		
   		//确认时间
   		String okTimeBegain = crm.getString("okTimeStart", "");
   		if(StringUtils.isNotEmpty(okTimeBegain)){
   		sql += " and pd.ok_Time >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
   		}
   		String okTimeEnd = crm.getString("okTimeEnd", "");
   		if(StringUtils.isNotEmpty(okTimeEnd)){
   		sql += " and pd.ok_Time <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
   		}
   		
   		String orderFlag = crm.getString("orderFlag", "");
       	if(StringUtils.isNotEmpty(orderFlag)){
       		sql +=" and pd.order_Flag ="+orderFlag+" ";
       	}
       	String customCode = crm.getString("customCode", "");
       	if(StringUtils.isNotEmpty(customCode)){
       		sql +=" and pd.customer_Code='"+customCode+"'";
       	}
       	
       	//modify by fu 2016-04-19 发货退回单添加类型和审核人的查询条件------begin
       	
       	//发货退回类型
       	String returnType = crm.getString("returnType", "");
       	if(!StringUtil.isEmpty(returnType)){
       		sql += " and pd.return_type = '"+returnType+"'";
       	}
       	
       	//审核人
       	String checkUsrCode = crm.getString("checkUsrCode","");
       	if(!StringUtil.isEmpty(checkUsrCode)){
       		sql += " and pd.check_usr_code = '"+checkUsrCode+"'";
       	}
       	//modify by fu 2016-04-19 发货退回单添加类型和审核人的查询条件------end

   		sql+=" group by pm.product_no,pm.product_name order by pm.product_no";
   		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPdReturnPurchaseDetail(String productNo, String rpNo) {
		// TODO Auto-generated method stub
    	String hql= "from PdReturnPurchaseDetail pdReturnPurchaseDetail where  pdReturnPurchaseDetail.rpNo='"+rpNo+"' and pdReturnPurchaseDetail.productNo='"+productNo+"'";
		return this.getHibernateTemplate().find(hql);
	}

	/**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao#getPdReturnPurchaseDetails(com.joymain.jecs.pd.model.PdReturnPurchaseDetail)
     */
    public List getPdReturnPurchaseDetails(final PdReturnPurchaseDetail pdReturnPurchaseDetail) {
        return getHibernateTemplate().find("from PdReturnPurchaseDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdReturnPurchaseDetail == null) {
            return getHibernateTemplate().find("from PdReturnPurchaseDetail");
        } else {
            // filter on properties set in the pdReturnPurchaseDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdReturnPurchaseDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdReturnPurchaseDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao#getPdReturnPurchaseDetail(Long uniNo)
     */
    public PdReturnPurchaseDetail getPdReturnPurchaseDetail(final Long uniNo) {
        PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) getHibernateTemplate().get(PdReturnPurchaseDetail.class, uniNo);
        if (pdReturnPurchaseDetail == null) {
            log.warn("uh oh, pdReturnPurchaseDetail with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdReturnPurchaseDetail.class, uniNo);
        }

        return pdReturnPurchaseDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao#savePdReturnPurchaseDetail(PdReturnPurchaseDetail pdReturnPurchaseDetail)
     */    
    public void savePdReturnPurchaseDetail(final PdReturnPurchaseDetail pdReturnPurchaseDetail) {
        getHibernateTemplate().saveOrUpdate(pdReturnPurchaseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDetailDao#removePdReturnPurchaseDetail(Long uniNo)
     */
    public void removePdReturnPurchaseDetail(final Long uniNo) {
        getHibernateTemplate().delete(getPdReturnPurchaseDetail(uniNo));
    }
    //added for getPdReturnPurchaseDetailsByCrm
    public List getPdReturnPurchaseDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdReturnPurchaseDetail pdReturnPurchaseDetail where 1=1";
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
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
