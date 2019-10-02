
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.dao.PdEnterWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdEnterWarehouseDaoHibernate extends BaseDaoHibernate implements PdEnterWarehouseDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDao#getPdEnterWarehouses(com.joymain.jecs.pd.model.PdEnterWarehouse)
     */
    public List getPdEnterWarehouses(final PdEnterWarehouse pdEnterWarehouse) {
        return getHibernateTemplate().find("from PdEnterWarehouse");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdEnterWarehouse == null) {
            return getHibernateTemplate().find("from PdEnterWarehouse");
        } else {
            // filter on properties set in the pdEnterWarehouse
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdEnterWarehouse).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdEnterWarehouse.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDao#getPdEnterWarehouse(String ewNo)
     */
    public PdEnterWarehouse getPdEnterWarehouse(final String ewNo) {
        PdEnterWarehouse pdEnterWarehouse = (PdEnterWarehouse) getHibernateTemplate().get(PdEnterWarehouse.class, ewNo);
        if (pdEnterWarehouse == null) {
            log.warn("uh oh, pdEnterWarehouse with ewNo '" + ewNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdEnterWarehouse.class, ewNo);
        }

        return pdEnterWarehouse;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDao#savePdEnterWarehouse(PdEnterWarehouse pdEnterWarehouse)
     */    
    public void savePdEnterWarehouse(final PdEnterWarehouse pdEnterWarehouse) {
        getHibernateTemplate().saveOrUpdate(pdEnterWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdEnterWarehouseDao#removePdEnterWarehouse(String ewNo)
     */
    public void removePdEnterWarehouse(final String ewNo) {
        getHibernateTemplate().delete(getPdEnterWarehouse(ewNo));
    }
    //added for getPdEnterWarehousesByCrm
    public List getPdEnterWarehousesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdEnterWarehouse pdEnterWarehouse where 1=1";
    	String checkUsrCode = crm.getString("checkUsrCode","");
//    	String checkStatus = crm.getString("checkStatus","");
    	String createUsrCode = crm.getString("createUsrCode","");
    	
    	String hasCheckUsrCodeBlank = crm.getString("hasCheckUsrCodeBlank","0");
    	
    	String defaultWarehouse = crm.getString("defaultWarehouse", "");
		if (StringUtils.isNotEmpty(defaultWarehouse)) {
			hql += " and pdEnterWarehouse.warehouseNo in(" + defaultWarehouse + ")";
		}
		
		String productNo = crm.getString("productNo", "");
		if (StringUtils.isNotEmpty(productNo)) {
			hql += " and pdEnterWarehouse.ewNo in( select  ewNo from PdEnterWarehouseDetail psd where psd.productNo='"+productNo+"' )";
		}

		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//		String userCodeT = crm.getString("userCodeT", ""); 
//		if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//			hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdEnterWarehouse.warehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//		} 
		
		
		String warehouseNo = crm.getString("warehouseNo","");
    	if(StringUtils.isNotEmpty(warehouseNo)){
    		hql += " and pdEnterWarehouse.warehouseNo='"+warehouseNo+"'";
    	}
    	String orderFlagDefault = crm.getString("orderFlagDefault", "");
		if (StringUtils.isNotEmpty(orderFlagDefault)) {
			hql += " and pdEnterWarehouse.orderFlag in(" + orderFlagDefault + ")";
		}
		
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and pdEnterWarehouse.companyCode='"+companyCode+"'";
    	}
    	
    	String ewNo = crm.getString("ewNo","");
    	if(StringUtils.isNotEmpty(ewNo)){
    		hql += " and pdEnterWarehouse.ewNo='"+ewNo+"'";
    	}
    	if(StringUtils.isNotEmpty(createUsrCode)){
    		hql += " and pdEnterWarehouse.createUsrCode='"+createUsrCode+"'";
    	}
    	/*if(StringUtils.isNotEmpty(checkStatus)){
    		hql += " and pdEnterWarehouse.checkStatus='"+checkStatus+"'";
    	}*/
    	if(StringUtils.isNotEmpty(checkUsrCode)){
    		if(hasCheckUsrCodeBlank.equals("1")){
    			
    			hql += " and (pdEnterWarehouse.checkUsrCode ='"+checkUsrCode+"' or pdEnterWarehouse.checkUsrCode is null)";
    		}else{
    			hql += " and pdEnterWarehouse.checkUsrCode='"+checkUsrCode+"'";
    		}
    	}
    	//确认者
    	String okUsrCode = crm.getString("okUsrCode","");
    	if(StringUtils.isNotEmpty(okUsrCode)){
    		if(hasCheckUsrCodeBlank.equals("1")){
    			
    			hql += " and (pdEnterWarehouse.okUsrCode ='"+okUsrCode+"' or pdEnterWarehouse.okUsrCode is null)";
    		}else{
    			hql += " and pdEnterWarehouse.okUsrCode='"+okUsrCode+"'";
    		}
    	}
    	
    	
    	String orderFlag = crm.getString("orderFlag","");
    	if(StringUtils.isNotEmpty(orderFlag)){
    		hql +=" and pdEnterWarehouse.orderFlag in("+ orderFlag +")";
    	}
    	
    	
    	//创建时间
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and pdEnterWarehouse.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and pdEnterWarehouse.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	//确认时间
    	String okTimeBegain = crm.getString("okTimeStart", "");
    	if(StringUtils.isNotEmpty(okTimeBegain)){
    		hql += " and pdEnterWarehouse.okTime >=to_date('"+okTimeBegain+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	String okTimeEnd = crm.getString("okTimeEnd", "");
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and pdEnterWarehouse.okTime <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	/*String okStatus = crm.getString("okStatus", "");
    	if(StringUtils.isNotEmpty(okStatus)){
    		hql += " and pdEnterWarehouse.okStatus='"+okStatus+"'";
    	}*/
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by ewNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getEnterDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		//预编译参数
		//Modify By WuCF 20140728
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer paramsBuf = new StringBuffer("");
		
		sqlBuffer.append(" select ew.EW_NO,ew.WAREHOUSE_NO,ew.ORDER_FLAG,ew.CREATE_TIME,ew.OK_TIME,ew.REMARK,ewd.product_no,ewd.qty,ps.product_name ");
		sqlBuffer.append(" from pd_enter_warehouse ew,pd_enter_warehouse_detail ewd, ");
		sqlBuffer.append(" jpm_product_sale_new ps where ew.ew_no=ewd.ew_no ");
		sqlBuffer.append(" and ew.company_code=ps.company_code and ewd.product_no=ps.product_no ");
		
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		if(StringUtils.isNotEmpty(startTime)){
			sqlBuffer.append(" and ew.create_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(endTime)){
			sqlBuffer.append(" and ew.create_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+endTime+" 23:59:59");
		}
		String okStartTime = crm.getString("okStartTime", "");
		String okEndTime = crm.getString("okEndTime", "");
		if(StringUtils.isNotEmpty(okStartTime)){
			sqlBuffer.append(" and ew.ok_Time >=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okStartTime+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(okEndTime)){
			sqlBuffer.append(" and ew.ok_Time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+okEndTime+" 23:59:59");
		}
		sqlBuffer.append(" order by ew.ew_no ");
		
		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		List list = this.getJdbcTemplateReport().queryForList(sqlBuffer.toString(),parameters);
		return list;
	}
    
    
}
