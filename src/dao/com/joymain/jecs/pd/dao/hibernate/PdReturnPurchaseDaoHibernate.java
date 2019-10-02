
package com.joymain.jecs.pd.dao.hibernate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.dao.PdReturnPurchaseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdReturnPurchaseDaoHibernate extends BaseDaoHibernate implements PdReturnPurchaseDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDao#getPdReturnPurchases(com.joymain.jecs.pd.model.PdReturnPurchase)
     */
    public List getPdReturnPurchases(final PdReturnPurchase pdReturnPurchase) {
        return getHibernateTemplate().find("from PdReturnPurchase");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdReturnPurchase == null) {
            return getHibernateTemplate().find("from PdReturnPurchase");
        } else {
            // filter on properties set in the pdReturnPurchase
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdReturnPurchase).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdReturnPurchase.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDao#getPdReturnPurchase(String rpNo)
     */
    public PdReturnPurchase getPdReturnPurchase(final String rpNo) {
        PdReturnPurchase pdReturnPurchase = (PdReturnPurchase) getHibernateTemplate().get(PdReturnPurchase.class, rpNo);
        /*if (pdReturnPurchase == null) {
            log.warn("uh oh, pdReturnPurchase with rpNo '" + rpNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdReturnPurchase.class, rpNo);
        }*/

        return pdReturnPurchase;
    }
    
    /**
     * Modify Bu WuCF 20151226 添加对数据锁定的方法
     * @param rpNo
     * @return
     */
    public PdReturnPurchase getPdReturnPurchaseForUpdate(final String rpNo) {
        PdReturnPurchase pdReturnPurchase = (PdReturnPurchase) getHibernateTemplate().get(PdReturnPurchase.class, rpNo,LockMode.UPGRADE);
        /*if (pdReturnPurchase == null) {
            log.warn("uh oh, pdReturnPurchase with rpNo '" + rpNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdReturnPurchase.class, rpNo);
        }*/

        return pdReturnPurchase;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDao#savePdReturnPurchase(PdReturnPurchase pdReturnPurchase)
     */    
    public void savePdReturnPurchase(final PdReturnPurchase pdReturnPurchase) {
    	pdReturnPurchase.setFinanceRemark("noNew");
        getHibernateTemplate().saveOrUpdate(pdReturnPurchase);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdReturnPurchaseDao#removePdReturnPurchase(String rpNo)
     */
    public void removePdReturnPurchase(final String rpNo) {
        getHibernateTemplate().delete(getPdReturnPurchase(rpNo));
    }
    //added for getPdReturnPurchasesByCrm
    public List getPdReturnPurchasesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdReturnPurchase pdReturnPurchase where 1=1";
    	String rpNo = crm.getString("rpNo", "");
    	if(StringUtils.isNotEmpty(rpNo)){
    		hql +=" and pdReturnPurchase.rpNo='"+rpNo+"'";
    	}
    	
    	//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", "");
		if (StringUtils.isNotEmpty(createUsrNo)) {
			hql += " and pdReturnPurchase.createUsrCode ='" + createUsrNo.trim() + "' ";
		}
    	
    	String defaultWarehouse = crm.getString("defaultWarehouse", "");
    	if(StringUtils.isNotEmpty(defaultWarehouse)){
    		hql += " and pdReturnPurchase.returnWarehouseNo in("+defaultWarehouse+")";
    	}
    	String returnWarehouseNo = crm.getString("returnWarehouseNo", "");
    	if(StringUtils.isNotEmpty(returnWarehouseNo)){
    		hql += " and pdReturnPurchase.returnWarehouseNo ='"+returnWarehouseNo+"'";
    	}
    	String hasCheckUsrCodeBlank = crm.getString("hasCheckUsrCodeBlank","0");
    	
    	String createUsrCode = crm.getString("createUsrCode", "");
    	if(StringUtils.isNotEmpty(createUsrCode)){
    		hql +=" and pdReturnPurchase.createUsrCode='"+createUsrCode+"'";
    	}
    	String checkUsrCode = crm.getString("checkUsrCode", "");
    	if(StringUtils.isNotEmpty(checkUsrCode)){
    		if(hasCheckUsrCodeBlank.equals("1")){
    			hql +=" and pdReturnPurchase.checkUsrCode='"+checkUsrCode+"' or pdReturnPurchase.checkUsrCode is null";
    		}else{
    			hql +=" and pdReturnPurchase.checkUsrCode='"+checkUsrCode+"' ";
    		}
    		
    	}
    	
    	//modify by fu 2016-04-19 发货退回类型 -----begin
    	String returnType = crm.getString("returnType", "");
    	if(!StringUtil.isEmpty(returnType)){
    		hql += " and pdReturnPurchase.returnType = '"+returnType+"'";
    	}
    	//modify by fu 2016-04-19 发货退回类型 -----begin

    	
    	String okUsrCode = crm.getString("okUsrCode", "");
    	if(StringUtils.isNotEmpty(okUsrCode)){
    		if(hasCheckUsrCodeBlank.equals("1")){
    			hql +=" and pdReturnPurchase.okUsrCode='"+okUsrCode+"' or pdReturnPurchase.okUsrCode is null ";
    		}else{
    			hql +=" and pdReturnPurchase.okUsrCode='"+okUsrCode+"'";
    		}
    		
    	}
    	String financeUsrCode = crm.getString("financeUsrCode", "");
    	if(StringUtils.isNotEmpty(financeUsrCode)){
    		if(hasCheckUsrCodeBlank.equals("1")){
    			hql +=" and pdReturnPurchase.financeUsrCode='"+financeUsrCode+"' or pdReturnPurchase.financeUsrCode is null ";
    		}else{
    			hql +=" and pdReturnPurchase.financeUsrCode='"+financeUsrCode+"'";
    		}
    		
    	}

    	String companyCode = crm.getString("companyCode", "");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql +=" and pdReturnPurchase.companyCode='"+companyCode+"'";
    	}
    	String customCode = crm.getString("customCode", "");
    	if(StringUtils.isNotEmpty(customCode)){
    		hql +=" and pdReturnPurchase.customer.userCode='"+customCode+"'";
    	}

    	//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//		String userCodeT = crm.getString("userCodeT", ""); 
//		if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//			hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdReturnPurchase.returnWarehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//		}
		
    	String orderFlagDefault = crm.getString("orderFlagDefault", "");
    	if(StringUtils.isNotEmpty(orderFlagDefault)){
    		hql +=" and pdReturnPurchase.orderFlag in("+orderFlagDefault+")";
    	}
    	String orderFlag = crm.getString("orderFlag", "");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and pdReturnPurchase.orderFlag in("+orderFlag+")";
    	}
    	//创建时间
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and pdReturnPurchase.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and pdReturnPurchase.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//确认时间
    	String okTimeBegain = crm.getString("okTimeStart", "");
    	if(StringUtils.isNotEmpty(okTimeBegain)){
    		hql += " and pdReturnPurchase.okTime >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String okTimeEnd = crm.getString("okTimeEnd", "");
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and pdReturnPurchase.okTime <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by rpNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getReturnDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		//预编译参数
		//Modify By WuCF 20140728
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer paramsBuf = new StringBuffer("");
		
		sqlBuffer.append(" select rp.RP_NO,rp.CUSTOMER_CODE,rp.RETURN_WAREHOUSE_NO,rp.ORDER_FLAG,rp.CREATE_TIME,rp.OK_TIME,rp.REMARK,rpd.product_no,rpd.qty,ps.product_name ");
		sqlBuffer.append(" from pd_return_purchase rp,pd_return_purchase_detail rpd,jpm_product_sale_new ps ");
		sqlBuffer.append(" where rp.rp_no=rpd.rp_no and rp.company_code=ps.company_code and rpd.product_no=ps.product_no ");
		
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		if(StringUtils.isNotEmpty(startTime)){
			sqlBuffer.append(" and rp.create_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(endTime)){
			sqlBuffer.append(" and rp.create_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+endTime+" 23:59:59");
		}
		String okStartTime = crm.getString("okStartTime", "");
		String okEndTime = crm.getString("okEndTime", "");
		if(StringUtils.isNotEmpty(okStartTime)){
			sqlBuffer.append(" and rp.ok_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okStartTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(okEndTime)){
			sqlBuffer.append(" and rp.ok_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okEndTime+" 23:59:59");
		}
		sqlBuffer.append(" order by rp.rp_no ");
		
		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		List list = this.getJdbcTemplateReport().queryForList(sqlBuffer.toString(),parameters);
		return list;
	}
	
	/**
	 * 根据发货退回单号查询发货退回单状态信息
	 * @author fu 2015-09-11 
	 * @param rpNo
	 * @return string
	 */
	public String getPdReturnOrderFlag(String rpNo){
		if(!StringUtil.isEmpty(rpNo)){
			String sql = " select order_flag from pd_return_purchase where rp_no = '"+rpNo+"'";
			List list =  this.getJdbcTemplate().queryForList(sql);
            if(null!=list && list.size()>0){
            	Map map = (Map) list.get(0);
            	BigDecimal a =  (BigDecimal) map.get("order_flag");
            	return a.toString();
            }
		}
		return null;
	}
	
	/**
     * 获取数据库中的pdReturnPurchase对象
     * @author fu 2015-09-14 
     * @param pdReturnPurchase
     * @return pdReturnPurchase
     */
	public PdReturnPurchase getPdReturnPurchaseDBA(PdReturnPurchase pdReturnPurchase){
		String rpNo = pdReturnPurchase.getRpNo();
		if(!StringUtil.isEmpty(rpNo)){
			String sql = " select * from  pd_return_purchase where rp_no = '"+rpNo+"'";
			List list = this.getJdbcTemplate().queryForList(sql);
			 if(null!=list && list.size()>0){
	            	Map map = (Map) list.get(0);
	            	//pdReturnPurchase.setCustomer(customer);
	            	pdReturnPurchase.setFinanceUsrCode((String)map.get("finance_usr_code"));//临时字段:有用，存储发货退回单的会员编号
	            	pdReturnPurchase.setReturnWarehouseNo((String)map.get("return_warehouse_no"));
	            	pdReturnPurchase.setRemark((String)map.get("remark"));
	            	
	            	pdReturnPurchase.setOrderFlag(Integer.parseInt((String)map.get("order_flag")));
	            	pdReturnPurchase.setStockFlag((String)map.get("stock_flag"));
	            	pdReturnPurchase.setFinanceRemark((String)map.get("finance_remark"));
	            	
	            	pdReturnPurchase.setFirstName((String)map.get("first_name"));
	            	pdReturnPurchase.setLastName((String)map.get("last_name"));
	            	pdReturnPurchase.setProvince((String)map.get("province"));
	            	pdReturnPurchase.setCity((String)map.get("city"));
	            	pdReturnPurchase.setDistrict((String)map.get("district"));
	            	pdReturnPurchase.setAddress((String)map.get("address"));
	            	pdReturnPurchase.setPostalcode((String)map.get("postalcode"));
	            	pdReturnPurchase.setPhone((String)map.get("phone"));
	            	pdReturnPurchase.setMobiletele((String)map.get("mobiletele"));
	            	pdReturnPurchase.setEmail((String)map.get("email"));
	            	
	                Set<PdReturnPurchaseDetail> pdReturnPurchaseDetails = new HashSet();
	            	String sqlDetail = " select * from  pd_return_purchase_detail where rp_no = '"+rpNo+"'";
	    			List listDetail = this.getJdbcTemplate().queryForList(sqlDetail);
	    			if((null!=listDetail) && listDetail.size()>0){
	    				Set<PdReturnPurchaseDetail> pdReturnPurchaseDetailsDBA = pdReturnPurchase.getPdReturnPurchaseDetails();
	    				for(int i=0;i<listDetail.size();i++){
	    					Map mapDetail = (Map) listDetail.get(i);
	    					String uniNo = (String)mapDetail.get("uni_no");
	    					String qty = (String)mapDetail.get("qty");
	    					Iterator itDetail = pdReturnPurchaseDetailsDBA.iterator();
	    					int a = 0;
	    					while(itDetail.hasNext()){
	    						PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) itDetail.next();
	    						Long uniNoDBAl = pdReturnPurchaseDetail.getUniNo();
	    						if(uniNoDBAl.toString().equals(uniNo)){
	    							a= 1;
	    							pdReturnPurchaseDetail.setQty(Long.parseLong(qty));
	    							pdReturnPurchaseDetails.add(pdReturnPurchaseDetail);
	    							break;
	    						}
	    					}
	    					if(0==a){
	    						PdReturnPurchaseDetail pdReturnPurchaseDetail = new PdReturnPurchaseDetail();
	    						pdReturnPurchaseDetail.setUniNo(Long.parseLong(uniNo));
	    						pdReturnPurchaseDetail.setProductNo((String)mapDetail.get("product_no"));
	    						pdReturnPurchaseDetail.setPrice((BigDecimal)mapDetail.get("price"));
	    						pdReturnPurchaseDetail.setQty(Long.parseLong(qty));
	    						pdReturnPurchaseDetail.setRpNo(rpNo);
	    						pdReturnPurchaseDetail.setErpProductNo((String)mapDetail.get("erp_product_no"));
	    						pdReturnPurchaseDetails.add(pdReturnPurchaseDetail);
	    					}
	    				}
	    			}
	    			pdReturnPurchase.getPdReturnPurchaseDetails().clear();
	    			pdReturnPurchase.setPdReturnPurchaseDetails(pdReturnPurchaseDetails);
	            	return pdReturnPurchase;
	        }
		}
		
		return null;
	}
    
}
