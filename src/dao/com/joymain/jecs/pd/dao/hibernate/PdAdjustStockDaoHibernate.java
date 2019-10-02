
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.dao.PdAdjustStockDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdAdjustStockDaoHibernate extends BaseDaoHibernate implements PdAdjustStockDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdAdjustStockDao#getPdAdjustStocks(com.joymain.jecs.pd.model.PdAdjustStock)
     */
    public List getPdAdjustStocks(final PdAdjustStock pdAdjustStock) {
        return getHibernateTemplate().find("from PdAdjustStock");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdAdjustStock == null) {
            return getHibernateTemplate().find("from PdAdjustStock");
        } else {
            // filter on properties set in the pdAdjustStock
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdAdjustStock).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdAdjustStock.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdAdjustStockDao#getPdAdjustStock(String asNo)
     */
    public PdAdjustStock getPdAdjustStock(final String asNo) {
        PdAdjustStock pdAdjustStock = (PdAdjustStock) getHibernateTemplate().get(PdAdjustStock.class, asNo);
        if (pdAdjustStock == null) {
            log.warn("uh oh, pdAdjustStock with asNo '" + asNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdAdjustStock.class, asNo);
        }

        return pdAdjustStock;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdAdjustStockDao#savePdAdjustStock(PdAdjustStock pdAdjustStock)
     */    
    public void savePdAdjustStock(final PdAdjustStock pdAdjustStock) {
        getHibernateTemplate().saveOrUpdate(pdAdjustStock);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdAdjustStockDao#removePdAdjustStock(String asNo)
     */
    public void removePdAdjustStock(final String asNo) {
        getHibernateTemplate().delete(getPdAdjustStock(asNo));
    }
    //added for getPdAdjustStocksByCrm
    public List getPdAdjustStocksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdAdjustStock pdAdjustStock where 1=1";
    	String defaultWarehouse = crm.getString("defaultWarehouse", "");
    	if(StringUtils.isNotEmpty(defaultWarehouse)){
    		hql += " and pdAdjustStock.warehouseNo in("+defaultWarehouse+")";
    	}
    	
    	//Modify By WuCF 20150617 加上创建者、审核者、确认者查询条件判断查询
    	String createUsrNo = crm.getString("createusrcode", "");
		if (StringUtils.isNotEmpty(createUsrNo)) {
			hql += " and pdAdjustStock.createUsrCode ='" + createUsrNo.trim() + "' ";
		}
		
		String checkUsrNo = crm.getString("checkusrcode", "");
		if (StringUtils.isNotEmpty(checkUsrNo)) {
			hql += " and pdAdjustStock.checkUsrCode ='" + checkUsrNo.trim() + "' ";
		}
		
		String okUsrNo = crm.getString("okusrcode", "");
		if (StringUtils.isNotEmpty(okUsrNo)) {
			hql += " and pdAdjustStock.okUsrCode ='" + okUsrNo.trim() + "' ";
		}
    	
    	String warehouseNo = crm.getString("warehouseNo", "");
    	if(StringUtils.isNotEmpty(warehouseNo)){
    		hql += " and pdAdjustStock.warehouseNo ='"+warehouseNo+"'";
    	}

    	//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤 
//		String userCodeT = crm.getString("userCodeT", ""); 
//		if (StringUtils.isNotEmpty(userCodeT) && !"root".equals(userCodeT)) {
//			hql += " and exists (select 1 from PdWarehouseUser pdWarehouseUser where pdAdjustStock.warehouseNo=pdWarehouseUser.warehouseNo and pdWarehouseUser.userCode='"+userCodeT+"') ";
//		}
    	
    	String hasCheckUsrCodeBlank = crm.getString("hasCheckUsrCodeBlank","0");
    	
    	String orderFlagDefault = crm.getString("orderFlagDefault", "");
		if (StringUtils.isNotEmpty(orderFlagDefault)) {
			hql += " and pdAdjustStock.orderFlag in(" + orderFlagDefault + ")";
		}
		
		String orderFlag = crm.getString("orderFlag", "");
		if (StringUtils.isNotEmpty(orderFlag)) {
			hql += " and pdAdjustStock.orderFlag in(" + orderFlag + ")";
		}
		
		
		/*String checkUsrCode = crm.getString("checkUsrCode", "");
		if (StringUtils.isNotEmpty(checkUsrCode)) {
			if (hasCheckUsrCodeBlank.equals("1")) {

				hql += " and (pdAdjustStock.checkUsrCode ='" + checkUsrCode
						+ "' or pdAdjustStock.checkUsrCode is null)";
			} else {
				hql += " and pdAdjustStock.checkUsrCode='" + checkUsrCode + "'";
			}

		}
		*/
    	String asNo = crm.getString("asNo", "");
    	if(StringUtils.isNotEmpty(asNo)){
    		hql += " and pdAdjustStock.asNo='"+asNo+"'";
    	}
    	String astNo = crm.getString("astNo", "");
    	if(StringUtils.isNotEmpty(astNo)){
    		hql += " and pdAdjustStock.astNo='"+astNo+"'";
    	}
    	
    	/*String createUsrCode = crm.getString("createUsrCode", "");
    	if(StringUtils.isNotEmpty(createUsrCode)){
    		hql += " and pdAdjustStock.createUsrCode='"+createUsrCode+"'";
    	}*/
    	
    	
    	
    	
    	String checkUsrCode = crm.getString("checkUsrCode", "");
    	if(StringUtils.isNotEmpty(checkUsrCode)){
    		
    		if(hasCheckUsrCodeBlank.equals("1")){
    			
    			hql += " and (pdAdjustStock.checkUsrCode ='"+checkUsrCode+"' or pdAdjustStock.checkUsrCode is null)";
    		}else{
    			hql += " and pdAdjustStock.checkUsrCode='"+checkUsrCode+"'";
    		}
    		
    	}
    	
    	String okUsrCode = crm.getString("okUsrCode", "");
    	if(StringUtils.isNotEmpty(okUsrCode)){
    		
    		if(hasCheckUsrCodeBlank.equals("1")){
    			
    			hql += " and (pdAdjustStock.okUsrCode ='"+okUsrCode+"' or pdAdjustStock.okUsrCode is null)";
    		}else{
    			hql += " and pdAdjustStock.okUsrCode='"+okUsrCode+"'";
    		}
    		
    	}
    	
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and pdAdjustStock.companyCode='"+companyCode+"'";
    	}
    	
    	/*String checkStatus = crm.getString("checkStatus", "");
    	if(StringUtils.isNotEmpty(checkStatus)){
    		hql += " and pdAdjustStock.checkStatus='"+checkStatus+"'";
    	}
    	
    	String okStatus = crm.getString("okStatus", "");
    	if(StringUtils.isNotEmpty(okStatus)){
    		hql += " and pdAdjustStock.okStatus='"+okStatus+"'";
    	}*/
    	
    	
    	
    	String createTimeStart = crm.getString("createTimeStart","");
    	String createTimeEnd = crm.getString("createTimeEnd","");
    	if(StringUtils.isNotEmpty(createTimeStart)){
    		hql += " and pdAdjustStock.createTime >=to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(createTimeEnd)){
    		hql += " and pdAdjustStock.createTime <=to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	
    	String okTimeStart = crm.getString("okTimeStart","");
    	String okTimeEnd = crm.getString("okTimeEnd","");
    	if(StringUtils.isNotEmpty(okTimeStart)){
    		hql += " and pdAdjustStock.okTime >=to_date('"+okTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS')";
    	}
    	if(StringUtils.isNotEmpty(okTimeEnd)){
    		hql += " and pdAdjustStock.okTime <=to_date('"+okTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS')";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by asNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getSumGroupByAst(CommonRecord crm) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String flag = crm.getString("flag", "");
		String companyCode = crm.getString("companyCode", "");
		String warehouseNo = crm.getString("warehouseNo", "");
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String t1="select pd.product_no,pm.PRODUCT_NAME, owt_no,                                    "+
		"decode(AST_NO,'1',qty,0) count1,                                   "+
		"decode(AST_NO,'2',qty,0) count2,                                   "+
		"decode(AST_NO,'3',qty,0) count3,                                   "+
		"decode(AST_NO,'4',qty,0) count4,                                   "+
		"decode(AST_NO,'5',qty,0) count5,                                   "+
		"decode(AST_NO,'6',qty,0) count6,                                    "+
		"decode(AST_NO,'7',qty,0) count7,                                   "+
		"decode(AST_NO,'8',qty,0) count8,                                   "+
		"decode(AST_NO,'9',qty,0) count9,                                   "+
		"decode(AST_NO,'10',qty,0) count10                                   "+
		
		"from pd_adjust_stock p,pd_adjust_stock_detail pd,jpm_product_sale_new pm               "+
		"where p.ow_no=pd.ow_no and p.company_code=pm.company_code and pd.product_no=pm.product_no ";
		
		if("positive".equals(flag)){
			t1+=" and pd.qty >0 ";
		}else if("negative".equals(flag)){
			t1+=" and pd.qty <0 ";
		}
		if(StringUtils.isNotEmpty(companyCode)){
			t1+=" and p.company_code='"+companyCode+"' ";
		}
		if(StringUtils.isNotEmpty(warehouseNo)){
			t1+=" and p.warehouse_no='"+warehouseNo+"' ";
		}
		if(StringUtils.isNotEmpty(startTime)){
			t1 += " and p.ok_Time >=to_date('" + startTime
			+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if(StringUtils.isNotEmpty(endTime)){
			t1 += " and p.ok_Time <=to_date('" + endTime
			+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		String sql ="select t.product_no,t.PRODUCT_NAME ,sum(count1) s1,sum(count2) s2,sum(count3) s3,"+ 
		"sum(count4) s4,sum(count5) s5,sum(count6) s6,sum(count7) s7,sum(count8) s8,sum(count9) s9,sum(count10) s10                     "+
		"from( "+t1+" ) t   "+
		"group by t.product_no,t.PRODUCT_NAME  order by t.product_no       ";
		
		return this.getJdbcTemplate().queryForList(sql);
	
	}
    
}
