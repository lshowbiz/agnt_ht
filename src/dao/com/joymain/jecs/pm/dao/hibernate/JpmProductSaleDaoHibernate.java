
package com.joymain.jecs.pm.dao.hibernate;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmProductSaleDao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductSaleDaoHibernate extends BaseDaoHibernate implements JpmProductSaleDao {

	//Modify By WuCF 注释之前的商品取法
    /*public JpmProductSale getJpmProductSale(String companyCode, String productNo) {
		// TODO Auto-generated method stub
    	String hql = "from JpmProductSale pm where pm.companyCode='"+companyCode+"' and pm.jpmProduct.productNo='"+productNo+"'";
    	return (JpmProductSale) this.getObjectByHqlQuery(hql);
		
	}*/

	public List getAlCompanyNotSaled(String productNo) {
		// TODO Auto-generated method stub
    	String sql = "select al.company_code,al.company_name from jal_company al  " +
		"where al.company_code <>'AA' and al.company_code  not in(select pm.company_code from jpm_product_sale_new pm where  pm.product_no='"+productNo+"') order by al.company_code";
    	return this.getJdbcTemplate().queryForList(sql);
	}

	/**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleDao#getJpmProductSales(com.joymain.jecs.pm.model.JpmProductSale)
     */
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public List getJpmProductSales(final JpmProductSale jpmProductSale) {
        return getHibernateTemplate().find("from JpmProductSale");

         Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductSale == null) {
            return getHibernateTemplate().find("from JpmProductSale");
        } else {
            // filter on properties set in the jpmProductSale
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductSale).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductSale.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
    }*/

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleDao#getJpmProductSale(Long uniNo)
     */
    //Modify By WuCF 注释之前的商品取法
    /*public JpmProductSale getJpmProductSale(final Long uniNo) {
        JpmProductSale jpmProductSale = (JpmProductSale) getHibernateTemplate().get(JpmProductSale.class, uniNo);
        if (jpmProductSale == null) {
            log.warn("uh oh, jpmProductSale with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductSale.class, uniNo);
        }

        return jpmProductSale;
    }*/

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleDao#saveJpmProductSale(JpmProductSale jpmProductSale)
     */    
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
   /* public void saveJpmProductSale(final JpmProductSale jpmProductSale) {
        getHibernateTemplate().saveOrUpdate(jpmProductSale);
    }*/

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleDao#removeJpmProductSale(Long uniNo)
     */
    
    public void removeJpmProductSale(final Long uniNo) {
//        getHibernateTemplate().delete(getJpmProductSale(uniNo));//Modify By WuCF 注释之前的商品取法
    }
    
    /**
	 * 得到分公司被动重销商品
	 * @param companyCode
	 * @param productNo
	 * @return
	 */
  //WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public JpmProductSale getAutoSell(String companyCode, String productNo){
		String hql = "from JpmProductSale pmProductSale where pmProductSale.companyCode='"+companyCode+"' " +
				"and pmProductSale.jpmProduct.productNo='"+productNo+"' and pmProductSale.controlAutosale='1'";
		if(getObjectByHqlQuery(hql) !=null){
			return (JpmProductSale) this.getObjectByHqlQuery(hql);
		}else{
			return null;
		}
	}*/
  //WuCF JpmProductSaleNew Modify By WuCF 20130917
   /* public JpmProductSale getPmProductSale(String companyCode, String productNo) {
		// TODO Auto-generated method stub
		String hql = "from JpmProductSale pmProductSale where pmProductSale.companyCode='"+companyCode+"' and pmProductSale.jpmProduct.productNo='"+productNo+"'";
		if(getObjectByHqlQuery(hql) !=null){
			return (JpmProductSale) this.getObjectByHqlQuery(hql);
		}else{
			return null;
		}
		
	}*/
    
    //added for getJpmProductSalesByCrm
    //WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public List getJpmProductSalesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmProductSale pmProductSale where 1=1";
    	
    	//Modify By WuCF 20130522新增查询条件
    	String isHidden = crm.getString("isHidden","");
    	if(StringUtils.isNotEmpty(isHidden)){
    		hql += " and pmProductSale.isHidden='"+isHidden+"'";
    	}
    	
    	//Modify By WuCF 20130731 添加关键字功能查询
		String keyword = crm.getString("keyword", "");
		if (StringUtils.isNotEmpty(keyword)) { 
			keyword = keyword.trim(); 
			hql += " and (pmProductSale.jpmProduct.productNo like '%"+keyword+"%' " + 
				   " or pmProductSale.productName like '%"+keyword+"%') ";
		} 
    	
    	// 
    	String productNo = crm.getString("productNo","");
    	if(StringUtils.isNotEmpty(productNo)){
    		hql += " and pmProductSale.jpmProduct.productNo like '%"+productNo+"%'";
    	}
    	String productName = crm.getString("productName","");
    	if(StringUtils.isNotEmpty(productName)){
    		hql += " and pmProductSale.productName like '%"+productName+"%'";
    	}
    	String statusNo = crm.getString("status","");
    	if(StringUtils.isNotEmpty(statusNo)){
    		hql += " and pmProductSale.status in( "+statusNo+")";
    	}
    	

    	String saleNo = crm.getString("saleNo","");
    	if(StringUtils.isNotEmpty(saleNo)){
    		hql += " and pmProductSale.saleNo='"+saleNo+"'";
    	}
    	
    	
    	String confirmNo = crm.getString("confirm","");
    	if(StringUtils.isNotEmpty(confirmNo)){
    		hql += " and pmProductSale.confirm = '"+confirmNo+"'";
    	}
    	String companyCode= crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and pmProductSale.companyCode = '"+companyCode+"'";
    	}
    	String controlFirst= crm.getString("controlFirst","");
    	if(StringUtils.isNotEmpty(controlFirst)){
    		hql += " and pmProductSale.controlFirst = '"+controlFirst+"'";
    	}
    	String controlUpdate= crm.getString("controlUpdate","");
    	if(StringUtils.isNotEmpty(controlUpdate)){
    		hql += " and pmProductSale.controlUpdate = '"+controlUpdate+"'";
    	}
    	String controlRepurchase= crm.getString("controlRepurchase","");
    	if(StringUtils.isNotEmpty(controlRepurchase)){
    		hql += " and pmProductSale.controlRepurchase = '"+controlRepurchase+"'";
    	}
    	
    	String controlPointExchange= crm.getString("controlPointExchange","");
    	if(StringUtils.isNotEmpty(controlPointExchange)){
    		hql += " and pmProductSale.controlPointExchange = '"+controlPointExchange+"'";
    	}
    	
    	String storeControlFirst= crm.getString("storeControlFirst","");
    	if(StringUtils.isNotEmpty(storeControlFirst)){
    		hql += " and pmProductSale.storeControlFirst = '"+storeControlFirst+"'";
    	}
    	String storeControlUpdate= crm.getString("storeControlUpdate","");
    	if(StringUtils.isNotEmpty(storeControlUpdate)){
    		hql += " and pmProductSale.storeControlUpdate = '"+storeControlUpdate+"'";
    	}
    	String storeControlRepurchase= crm.getString("storeControlRepurchase","");
    	if(StringUtils.isNotEmpty(storeControlRepurchase)){
    		hql += " and pmProductSale.storeControlRepurchase = '"+storeControlRepurchase+"'";
    	}
    	
    	
    	sub-store
    	String subStoreFirst= crm.getString("subStoreFirst","");
    	if(StringUtils.isNotEmpty(subStoreFirst)){
    		hql += " and pmProductSale.subStoreFirst = '"+subStoreFirst+"'";
    	}
    	String subStoreUpdate= crm.getString("subStoreUpdate","");
    	if(StringUtils.isNotEmpty(subStoreUpdate)){
    		hql += " and pmProductSale.subStoreUpdate = '"+subStoreUpdate+"'";
    	}
    	String subStoreRepurchase= crm.getString("subStoreRepurchase","");
    	if(StringUtils.isNotEmpty(subStoreRepurchase)){
    		hql += " and pmProductSale.subStoreRepurchase = '"+subStoreRepurchase+"'";
    	}
    	
    	
    	
    	String controlAutosale= crm.getString("controlAutosale","");
    	if(StringUtils.isNotEmpty(controlAutosale)){
    		hql += " and pmProductSale.controlAutosale = '"+controlAutosale+"'";
    	}
    	String smNo= crm.getString("smNo","");
    	if(StringUtils.isNotEmpty(smNo)){
    		hql +=" and pmProductSale.jpmProduct.smNo ='"+smNo+"'";
//    		hql += " and pmProductSale.pmProduct.productNo in (select pmProduct.productNo from PmProduct pmProduct where pmProduct.smNo in ('"+smNo.toUpperCase()+"'))";
    	}
    	String productCategory= crm.getString("productCategory","");
    	if(StringUtils.isNotEmpty(productCategory)){
    		hql +=" and pmProductSale.jpmProduct.productCategory = '"+productCategory+"'";

    	}
    	String productProvider = crm.getString("productProvider", "");
		if(StringUtils.isNotBlank(productProvider)){
			hql += " and pmProductSale.jpmProduct.productProvider like '%["+productProvider+"]%'";
		}
		
		String onSaleDateControl = crm.getString("onSaleDateControl", "");
		if("1".equals(onSaleDateControl)){
			hql += " and (pmProductSale.startOnSale <= sysdate or pmProductSale.startOnSale is null) ";
			hql += " and (pmProductSale.endOnSale > sysdate-1 or pmProductSale.endOnSale is null) ";
		}
    	log.info("productSale>>,sql="+hql);
    	if(pager == null){
    		hql += " order by pmProductSale.sortFlag ,pmProductSale.jpmProduct.productNo desc";
    		return this.getHibernateTemplate().find(hql);
    	}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by pmProductSale.sortFlag ,pmProductSale.jpmProduct.productNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }*/

    
	public List getCategorysByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		String sql = "select distinct p.product_category from jpm_product_sale_new pmProductSale ,jpm_product p where pmProductSale.product_no=p.product_no ";
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotBlank(companyCode)){
			sql += " and pmProductSale.company_code='"+companyCode+"'";
		}
		
		String productProvider = crm.getString("productProvider", "");
		if(StringUtils.isNotBlank(productProvider)){
			sql += " and p.product_provider like '%["+productProvider+"]%'";
		}
		
		
		
		String statusNo = crm.getString("status","");
    	if(StringUtils.isNotEmpty(statusNo)){
    		sql += " and pmProductSale.status in( "+statusNo+")";
    	}
    	

    	String saleNo = crm.getString("saleNo","");
    	if(StringUtils.isNotEmpty(saleNo)){
    		sql += " and pmProductSale.sale_No='"+saleNo+"'";
    	}
    	
    	String controlPointExchange= crm.getString("controlPointExchange","");
    	if(StringUtils.isNotEmpty(controlPointExchange)){
    		sql += " and pmProductSale.control_Point_Exchange = '"+controlPointExchange+"'";
    	}
    	String confirmNo = crm.getString("confirm","");
    	if(StringUtils.isNotEmpty(confirmNo)){
    		sql += " and pmProductSale.confirm = '"+confirmNo+"'";
    	}
    	
    	String controlFirst= crm.getString("controlFirst","");
    	if(StringUtils.isNotEmpty(controlFirst)){
    		sql += " and pmProductSale.control_First = '"+controlFirst+"'";
    	}
    	String controlUpdate= crm.getString("controlUpdate","");
    	if(StringUtils.isNotEmpty(controlUpdate)){
    		sql += " and pmProductSale.control_Update = '"+controlUpdate+"'";
    	}
    	String controlRepurchase= crm.getString("controlRepurchase","");
    	if(StringUtils.isNotEmpty(controlRepurchase)){
    		sql += " and pmProductSale.control_Repurchase = '"+controlRepurchase+"'";
    	}
    	
    	
    	String storeControlFirst= crm.getString("storeControlFirst","");
    	if(StringUtils.isNotEmpty(storeControlFirst)){
    		sql += " and pmProductSale.store_Control_First = '"+storeControlFirst+"'";
    	}
    	String storeControlUpdate= crm.getString("storeControlUpdate","");
    	if(StringUtils.isNotEmpty(storeControlUpdate)){
    		sql += " and pmProductSale.store_Control_Update = '"+storeControlUpdate+"'";
    	}
    	String storeControlRepurchase= crm.getString("storeControlRepurchase","");
    	if(StringUtils.isNotEmpty(storeControlRepurchase)){
    		sql += " and pmProductSale.store_Control_Repurchase = '"+storeControlRepurchase+"'";
    	}
    	
    	
    	/*sub-store*/
    	String subStoreFirst= crm.getString("subStoreFirst","");
    	if(StringUtils.isNotEmpty(subStoreFirst)){
    		sql += " and pmProductSale.sub_Store_First = '"+subStoreFirst+"'";
    	}
    	String subStoreUpdate= crm.getString("subStoreUpdate","");
    	if(StringUtils.isNotEmpty(subStoreUpdate)){
    		sql += " and pmProductSale.sub_Store_Update = '"+subStoreUpdate+"'";
    	}
    	String subStoreRepurchase= crm.getString("subStoreRepurchase","");
    	if(StringUtils.isNotEmpty(subStoreRepurchase)){
    		sql += " and pmProductSale.sub_Store_Repurchase = '"+subStoreRepurchase+"'";
    	}
    	
    	
    	String controlAutosale= crm.getString("controlAutosale","");
    	if(StringUtils.isNotEmpty(controlAutosale)){
    		sql += " and pmProductSale.control_Autosale = '"+controlAutosale+"'";
    	}
    	String smNo= crm.getString("smNo","");
    	if(StringUtils.isNotEmpty(smNo)){
    		sql +=" and p.sm_No ='"+smNo+"'";
//    		hql += " and pmProductSale.pmProduct.productNo in (select pmProduct.productNo from PmProduct pmProduct where pmProduct.smNo in ('"+smNo.toUpperCase()+"'))";
    	}
    	
		sql +=" order by TO_NUMBER(p.product_category) ";
		log.info("product_category:"+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getPropertysByCatagory(CommonRecord crm,String catagory, String property) {
		// TODO Auto-generated method stub
		String sql = "select distinct p."+property+" from  jpm_product p,jpm_product_sale_new pmProductSale where pmProductSale.product_no=p.product_no and p.product_category ='"+catagory+"' ";
		String companyCode = crm.getString("companyCode", "");
		if(StringUtils.isNotBlank(companyCode)){
			sql += " and pmProductSale.company_code='"+companyCode+"'";
		}
		
		String productProvider = crm.getString("productProvider", "");
		if(StringUtils.isNotBlank(productProvider)){
			sql += " and p.product_provider='"+productProvider+"'";
		}
		
		
		
		String statusNo = crm.getString("status","");
    	if(StringUtils.isNotEmpty(statusNo)){
    		sql += " and pmProductSale.status in( "+statusNo+")";
    	}
    	
    	String controlPointExchange= crm.getString("controlPointExchange","");
    	if(StringUtils.isNotEmpty(controlPointExchange)){
    		sql += " and pmProductSale.control_Point_Exchange = '"+controlPointExchange+"'";
    	}
    	String saleNo = crm.getString("saleNo","");
    	if(StringUtils.isNotEmpty(saleNo)){
    		sql += " and pmProductSale.sale_No='"+saleNo+"'";
    	}
    	
    	
    	String confirmNo = crm.getString("confirm","");
    	if(StringUtils.isNotEmpty(confirmNo)){
    		sql += " and pmProductSale.confirm = '"+confirmNo+"'";
    	}
    	
    	String controlFirst= crm.getString("controlFirst","");
    	if(StringUtils.isNotEmpty(controlFirst)){
    		sql += " and pmProductSale.control_First = '"+controlFirst+"'";
    	}
    	String controlUpdate= crm.getString("controlUpdate","");
    	if(StringUtils.isNotEmpty(controlUpdate)){
    		sql += " and pmProductSale.control_Update = '"+controlUpdate+"'";
    	}
    	String controlRepurchase= crm.getString("controlRepurchase","");
    	if(StringUtils.isNotEmpty(controlRepurchase)){
    		sql += " and pmProductSale.control_Repurchase = '"+controlRepurchase+"'";
    	}
    	
    	
    	String storeControlFirst= crm.getString("storeControlFirst","");
    	if(StringUtils.isNotEmpty(storeControlFirst)){
    		sql += " and pmProductSale.store_Control_First = '"+storeControlFirst+"'";
    	}
    	String storeControlUpdate= crm.getString("storeControlUpdate","");
    	if(StringUtils.isNotEmpty(storeControlUpdate)){
    		sql += " and pmProductSale.store_Control_Update = '"+storeControlUpdate+"'";
    	}
    	String storeControlRepurchase= crm.getString("storeControlRepurchase","");
    	if(StringUtils.isNotEmpty(storeControlRepurchase)){
    		sql += " and pmProductSale.store_Control_Repurchase = '"+storeControlRepurchase+"'";
    	}
    	
    	
    	/*sub-store*/
    	String subStoreFirst= crm.getString("subStoreFirst","");
    	if(StringUtils.isNotEmpty(subStoreFirst)){
    		sql += " and pmProductSale.sub_Store_First = '"+subStoreFirst+"'";
    	}
    	String subStoreUpdate= crm.getString("subStoreUpdate","");
    	if(StringUtils.isNotEmpty(subStoreUpdate)){
    		sql += " and pmProductSale.sub_Store_Update = '"+subStoreUpdate+"'";
    	}
    	String subStoreRepurchase= crm.getString("subStoreRepurchase","");
    	if(StringUtils.isNotEmpty(subStoreRepurchase)){
    		sql += " and pmProductSale.sub_Store_Repurchase = '"+subStoreRepurchase+"'";
    	}
    	
    	
    	String controlAutosale= crm.getString("controlAutosale","");
    	if(StringUtils.isNotEmpty(controlAutosale)){
    		sql += " and pmProductSale.control_Autosale = '"+controlAutosale+"'";
    	}
    	String smNo= crm.getString("smNo","");
    	if(StringUtils.isNotEmpty(smNo)){
    		sql +=" and p.sm_No ='"+smNo+"'";
//    		hql += " and pmProductSale.pmProduct.productNo in (select pmProduct.productNo from PmProduct pmProduct where pmProduct.smNo in ('"+smNo.toUpperCase()+"'))";
    	}
    	
		sql +=" order by p."+property;
		return this.getJdbcTemplate().queryForList(sql);
	}

	/* (non-Javadoc)
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleDao#getPreUpdateSales(com.joymain.jecs.util.data.CommonRecord)
	 */
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public List<JpmProductSale> getPreUpdateSales(CommonRecord crm) {
		// TODO Auto-generated method stub
		String hql = "from JpmProductSale pmProductSale where 1=1";
		String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hql += " and pmProductSale.status in( "+status+")";
    	}
    	String companyCode= crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and pmProductSale.companyCode = '"+companyCode+"'";
    	}
    	String saleControl= crm.getString("saleControl","");
    	if("1".equals(saleControl)){
    		hql += " and pmProductSale.endOnSale < sysdate-1 ";
    	}else{
    		hql += " and pmProductSale.startOnSale < sysdate ";
    	}
		return this.getHibernateTemplate().find(hql);
	}*/

	
	public List<HashMap<String, String>> getJmiMemberTeams(ResultSet rs) {
//		AbstractDAO ad = new AbstractDAO(); 
        return buildSqlResultList(rs); 
	}

	public JmiMemberTeam getJmiMemberTeam(String code) {
		// TODO Auto-generated method stub
		return (JmiMemberTeam)this.getHibernateTemplate().get(JmiMemberTeam.class, code);
	}    
	
	/**
	 * 根据SQL查询所返回的RS生成对应的List
	 *  
	 * @param rs
	 * @return
	 */
	public List<HashMap<String, String>> buildSqlResultList(ResultSet rs) {
		List<HashMap<String, String>> presidents = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData mData = rs.getMetaData();
			int fieldCount = mData.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> president = new HashMap<String, String>();
				for (int i = 1; i <= fieldCount; i++) {
					String fieldName = mData.getColumnName(i);
					int fieldType = mData.getColumnType(i);
					String fieldValue = null;
					switch (fieldType) {
						case Types.DATE:
						case Types.TIME:
						case Types.TIMESTAMP:
							fieldValue = rs.getString(i);
							if (fieldValue == null) {
								fieldValue = "";
							} else if (fieldValue.indexOf(".") >= 0) {// oracle日期处理
								fieldValue = fieldValue.substring(0, fieldValue.indexOf("."));
							}
							break;
						default:
							fieldValue = rs.getString(i);
							if (fieldValue == null)
								fieldValue = "";
							break;
					}

					president.put(fieldName.toLowerCase(), fieldValue);
				}
				presidents.add(president);
			} // while
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {  
			JdbcUtils.closeResultSet(rs);
		}
		return presidents;
	}
	
	
	/*************************************商品新表数据获取***********************************/
	 /**
     * Add By WuCF 20130916 商品新表相关，通过UNI_NO查询指定商品对象 
     * @param uniNo：商品uni_no主键
     * @return
     */
    public JpmProductSaleNew getJpmProductSaleNew(final Long uniNo) {
    	JpmProductSaleNew jpmProductSaleNew = (JpmProductSaleNew) getHibernateTemplate().get(JpmProductSaleNew.class, uniNo);
        if (jpmProductSaleNew == null) {
            log.warn("uh oh, JpmProductSaleNew with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductSaleNew.class, uniNo);
        }

        return jpmProductSaleNew;
    }
	
    /**
     * Add By WuCF 20130916 商品新表相关，通过companyCode和product_no查询指定商品对象 
     * @param companyCode：分公司编码
     * @param productNo：商品编码
     * @return
     */
	public JpmProductSaleNew getJpmProductSaleNew(String companyCode, String productNo) {
		// TODO Auto-generated method stub
    	String hql = "from JpmProductSaleNew pm where pm.companyCode='"+companyCode+"' and pm.jpmProduct.productNo='"+productNo+"'";
    	return (JpmProductSaleNew) this.getObjectByHqlQuery(hql);
		
	}

	/**
     * Add By WuCF 20130916 商品团队类型对象
     * @param pttId：主键pttId
     * @return
     */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId) {
		JpmProductSaleTeamType jpmProductSaleTeamType = (JpmProductSaleTeamType) getHibernateTemplate().get(JpmProductSaleTeamType.class, Long.parseLong(pttId));

        if (jpmProductSaleTeamType == null) {
            log.warn("uh oh, JpmProductSaleNew with uniNo '" + pttId + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductSaleTeamType.class, pttId);
        }

        return jpmProductSaleTeamType;
	}
	
	 /**
     * Add By WuCF 20130916 商品团队类型对象
     * @param productNo：商品编码
     * @param companyCode：分公司编码
     * @param teamCode：团队类型
     * @param orderType：订单类型
     * @return
     */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode) {
		JpmProductSaleTeamType jpmProductSaleTeamType = new JpmProductSaleTeamType();
		
		//如果团对为空，则默认中脉
		if(StringUtils.isEmpty(teamCode)){
			teamCode = JpmProductSaleDao.DEFAULT_TEAM_CODE;
		}
		
		StringBuffer sqlBuf = new StringBuffer("from JpmProductSaleTeamType jpstt where 1=1 ");
		sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productNo = :productNo ");
		sqlBuf.append(" and jpstt.jpmProductSaleNew.companyCode = :companyCode ");
		sqlBuf.append(" and jpstt.teamCode = :teamCode ");
		sqlBuf.append(" and jpstt.orderType = :orderType ");
		
		Query q = getSession().createQuery(sqlBuf.toString());
		q.setParameter("productNo", productNo);
		q.setParameter("companyCode", companyCode);
		q.setParameter("teamCode", teamCode);
		q.setParameter("orderType", orderType);
		
		List<JpmProductSaleTeamType> scOrderS=q.list();
		if(scOrderS!=null && scOrderS.size()!=0)
		{	    	   
			jpmProductSaleTeamType = (JpmProductSaleTeamType)scOrderS.get(0);
		} 
		return jpmProductSaleTeamType;
	}

	/**
     * Add By WuCF 20130916 获得商品新表对象数据
     * @param companyCode：分公司编码
     * @param type：订单类型
     * @param status：类型
     * @teamCode：团队编码
     * @return
     * @throws Exception
     */
	public Map<String, List<JpmProductSaleTeamType>> getPmProductSalesNew(
			String companyCode, String orderType, String status, String teamCode)
			throws Exception {
		Map<String, List<JpmProductSaleTeamType>> map = new LinkedHashMap<String, List<JpmProductSaleTeamType>>();
		//先按条件查询数据集合
		StringBuffer sqlBuf = new StringBuffer("from JpmProductSaleTeamType jpstt where 1=1 "); 
		sqlBuf.append(" and jpstt.jpmProductSaleNew.companyCode = :companyCode ");
		sqlBuf.append(" and jpstt.orderType = :orderType ");
		sqlBuf.append(" and jpstt.teamCode = :teamCode ");
		if("2".equals(status)){
			status = "1,2";
		}
		sqlBuf.append(" and jpstt.jpmProductSaleNew.status in(");
		sqlBuf.append(status);
		sqlBuf.append(")");
		//添加日期判断
		sqlBuf.append(" and (jpstt.jpmProductSaleNew.startOnSale <= sysdate or jpstt.jpmProductSaleNew.startOnSale is null) ");
		sqlBuf.append(" and (jpstt.jpmProductSaleNew.endOnSale > sysdate-1 or jpstt.jpmProductSaleNew.endOnSale is null) ");
		
		//预编译处理
		Query q = getSession().createQuery(sqlBuf.toString()); 
		q.setParameter("companyCode", companyCode);
		q.setParameter("orderType", orderType);
		q.setParameter("teamCode", teamCode);
		List<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = q.list();
		
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
		
		//第二步：通过商品系列分类
		ArrayList<JpmProductSaleTeamType> tempList = null; 
		for(String productCategory : productCategorys){
			tempList = new ArrayList<JpmProductSaleTeamType>();
			for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
				if(productCategory!=null && productCategory.equals(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory())){
					tempList.add(jpstt);
				}
			}
			if(tempList!=null && tempList.size()>=1){
				map.put(productCategory, tempList);//分类下有商品才添加进去
			}
		}
		
		return map;
	}	
	
	//added for getJpmProductSalesByCrm
    public List getJpmProductSalesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmProductSaleNew pmProductSale where 1=1";
    	 
    	//Modify By WuCF 20130522新增查询条件
    	String isHidden = crm.getString("isHidden","");
    	if(StringUtils.isNotEmpty(isHidden)){
    		hql += " and pmProductSale.isHidden='"+isHidden+"'";
    	}
    	
    	//Modify By WuCF 20130731 添加关键字功能查询
		String keyword = crm.getString("keyword", "");
		if (StringUtils.isNotEmpty(keyword)) { 
			keyword = keyword.trim(); 
			hql += " and (pmProductSale.jpmProduct.productNo like '%"+keyword+"%' " + 
				   " or pmProductSale.productName like '%"+keyword+"%') ";
		} 
    	
    	// 
    	String productNo = crm.getString("productNo","");
    	if(StringUtils.isNotEmpty(productNo)){
    		hql += " and pmProductSale.jpmProduct.productNo like '%"+productNo+"%'";
    	}
    	String productName = crm.getString("productName","");
    	if(StringUtils.isNotEmpty(productName)){
    		hql += " and pmProductSale.productName like '%"+productName+"%'";
    	}
    	String statusNo = crm.getString("status","");
    	if(StringUtils.isNotEmpty(statusNo)){
    		hql += " and pmProductSale.status in( "+statusNo+")";
    	}
    	

    	String saleNo = crm.getString("saleNo","");
    	if(StringUtils.isNotEmpty(saleNo)){
    		hql += " and pmProductSale.saleNo='"+saleNo+"'";
    	}
    	
    	
    	String confirmNo = crm.getString("confirm","");
    	if(StringUtils.isNotEmpty(confirmNo)){
    		hql += " and pmProductSale.confirm = '"+confirmNo+"'";
    	}
    	String companyCode= crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hql += " and pmProductSale.companyCode = '"+companyCode+"'";
    	}
    	
    	log.info("productSale>>,sql="+hql);
    	if(pager == null){
    		hql += " order by pmProductSale.sortFlag ,pmProductSale.jpmProduct.productNo desc";
    		return this.getHibernateTemplate().find(hql);
    	}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by pmProductSale.sortFlag ,pmProductSale.jpmProduct.productNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
	
	
	/**
     * Add By WuCF 20130916 获得商品团队类型数据
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
	public List getJpmProductSalesByCrmNew(CommonRecord crm, Pager pager){
    	StringBuffer hqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn ");
    	hqlBuf.append(" where jpstt.uniNo=jpsn.uniNo ");
    	 
    	//分公司编码
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hqlBuf.append(" and jpsn.companyCode ='"+companyCode+"' ");
    	}
    	
    	//订单类型
    	String orderType = crm.getString("orderType","");
    	if(StringUtils.isNotEmpty(orderType)){
    		hqlBuf.append(" and jpstt.orderType in ('"+orderType.replace(",", "','")+"') ");
    	}
    	
    	//团队编码
    	String teamCode = crm.getString("teamCode","");
    	if(StringUtils.isNotEmpty(teamCode)){
    		hqlBuf.append(" and jpstt.teamCode ='"+teamCode+"' ");
    	}
    	
    	//商品主键
    	String uniNo = crm.getString("uniNo","");
    	if(StringUtils.isNotEmpty(uniNo)){
    		hqlBuf.append(" and jpsn.uniNo ='"+uniNo+"' ");
    	} 
    	
    	//商品编码
    	String productNo = crm.getString("productNo","");
    	if(StringUtils.isNotEmpty(uniNo)){
    		hqlBuf.append(" and jpsn.jpmProduct.productNo ='"+productNo+"' ");
    	} 
    	
    	//商品状态
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(uniNo)){
    		hqlBuf.append(" and jpsn.status ='"+status+"' ");
    	} 
    	 
    	hqlBuf.append(" order by jpsn.sortFlag ,jpsn.jpmProduct.productNo desc");
		/*if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hqlBuf.append(" order by jpsn.sortFlag ,jpsn.productNo desc");
		} else {
			hqlBuf.append(" ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder());
		}*/ 
		return this.findObjectsByHqlQuery(hqlBuf.toString());
    }

	/**
     * Add By WuCF 20130916 获得商品团队类型数据
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
	public List getExJpmProductSalesByCrmNew(CommonRecord crm, Pager pager){
    	StringBuffer hqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn ");
    	hqlBuf.append(" where jpstt.uniNo=jpsn.uniNo ");/*and jpstt.state='0' */
    	
    	//换货单数据排除美体的数据
    	hqlBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");
    	
    	//分公司编码
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hqlBuf.append(" and jpsn.companyCode ='"+companyCode+"' ");
    	}
    	
    	//换货单对应的订单类型，需要加上辅消单商品，因为辅消品一般只有辅消单才有数据
    	String orderType = crm.getString("orderType","");
    	if(StringUtils.isNotEmpty(orderType)){
    		hqlBuf.append(" and jpstt.orderType in('"+orderType.replace(",", "','")+"') ");
    	}
    	
    	//团队编码
    	String teamCode = crm.getString("teamCode","");
    	if(StringUtils.isNotEmpty(teamCode)){
    		hqlBuf.append(" and jpstt.teamCode ='"+teamCode+"' ");
    	}
    	
    	//商品主键
    	String uniNo = crm.getString("uniNo","");
    	if(StringUtils.isNotEmpty(uniNo)){
    		hqlBuf.append(" and jpsn.uniNo ='"+uniNo+"' ");
    	} 
    	
    	//商品编码
    	String productNo = crm.getString("productNo","");
    	if(StringUtils.isNotEmpty(productNo)){
    		hqlBuf.append(" and jpsn.jpmProduct.productNo ='"+productNo+"' ");
    	} 
    	
    	//商品状态：销售状态和前台销售都可以查询到 
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hqlBuf.append(" and jpsn.status  in('"+status.replace(",", "','")+"') ");
    	} 
    	 
    	hqlBuf.append(" order by jpsn.sortFlag ,jpsn.jpmProduct.productNo desc");
//    	log.info("--crm:"+crm);
//    	log.info("=====getExJpmProductSalesByCrmNew:"+hqlBuf.toString());
		/*if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hqlBuf.append(" order by jpsn.sortFlag ,jpsn.productNo desc");
		} else {
			hqlBuf.append(" ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder());
		}*/ 
		return this.findObjectsByHqlQuery(hqlBuf.toString());
    }
	
	
	
	
	//销售管理新增的方法 :所有的辅销品数据
	@Override
	public List getJpoCounterProductSales(String companyCode) throws Exception {
		StringBuffer hqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt ");
    	hqlBuf.append(" where jpstt.orderType='5' ");//辅销单
    	if(StringUtils.isNotEmpty(companyCode) && !"AA".equals(companyCode)){
    		hqlBuf.append(" and jpstt.jpmProductSaleNew.companyCode='");
    		hqlBuf.append(companyCode.trim());
    		hqlBuf.append("' ");
    	}
    	hqlBuf.append(" and jpstt.jpmProductSaleNew.status in('1','2') and jpstt.state='0' ");//只有销售和前台停售的才能看到
    	hqlBuf.append(" and jpstt.teamCode='8888888888' ");//中脉团队
    	hqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.smNo='A' ");//辅销品
    	 
    	hqlBuf.append(" order by jpstt.jpmProductSaleNew.jpmProduct.productNo");
		return this.findObjectsByHqlQuery(hqlBuf.toString());
	}

	/**
     * Add By WuCF 20130916 获得商品
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
	public List getJpmProductSaleNewsByCrm(CommonRecord crm, Pager pager){
		String hql = "from JpmProductSaleNew jpmProductSaleNew where 1=1";
		
		String cxFlag = crm.getString("cxFlag",""); 
		if("n".equals(cxFlag)){
			hql += " and 1=2 ";
		}else{		
			String companyCode = crm.getString("companyCode",""); 
	    	if(StringUtils.isNotEmpty(companyCode) && !"AA".equals(companyCode)){
	    		hql += " and jpmProductSaleNew.companyCode='"+companyCode.trim()+"' ";
	    	}
			
			String productNo = crm.getString("productNo","");
	    	if(StringUtils.isNotEmpty(productNo)){
	    		hql += " and jpmProductSaleNew.jpmProduct.productNo='"+productNo.trim()+"' ";
	    	}
	    	
	    	String productName = crm.getString("productName","");
	    	if(StringUtils.isNotEmpty(productName)){
	    		hql += " and jpmProductSaleNew.productName like '%"+productName.trim()+"%' ";
	    	}
	    	
	    	String status = crm.getString("status","");
	    	if(StringUtils.isNotEmpty(status)){
	    		hql += " and jpmProductSaleNew.status='"+status.trim()+"' ";
	    	}
	    	
	    	String confirm = crm.getString("confirm","");
	    	if(StringUtils.isNotEmpty(confirm)){
	    		hql += " and jpmProductSaleNew.confirm='"+confirm.trim()+"' ";
	    	}
		}
		
//		//  
//		if (!pager.getLimit().getSort().isSorted()) {
//			//sort
//			hql += " order by uniNo desc";
//		} else {
//			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
//		}
		hql += " order by jpmProductSaleNew.sortFlag ,jpmProductSaleNew.jpmProduct.productNo desc";
		if(pager==null){
			return this.findObjectsByHqlQuery(hql);
		}
		return this.findObjectsByHqlQuery(hql, pager);
		
	}
	
	/**
     * Add By WuCF 20130916 保存新商品表
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
	public void saveJpmProductSaleNew(final JpmProductSaleNew jpmProductSaleNew) {
		if(jpmProductSaleNew.getWhoPrice()==null || "".equals(jpmProductSaleNew.getWhoPrice())){
			jpmProductSaleNew.setWhoPrice(new BigDecimal(0));
		}
		if(jpmProductSaleNew.getDiscountPrice()==null || "".equals(jpmProductSaleNew.getDiscountPrice())){
			jpmProductSaleNew.setDiscountPrice(new BigDecimal(0));
		}
		if(jpmProductSaleNew.getWeight()==null || "".equals(jpmProductSaleNew.getWeight())){
			jpmProductSaleNew.setWeight(new BigDecimal(0));
		}
		if(jpmProductSaleNew.getVolume()==null || "".equals(jpmProductSaleNew.getVolume())){
			jpmProductSaleNew.setVolume(new BigDecimal(0));
		}
		if(jpmProductSaleNew.getLength()==null || "".equals(jpmProductSaleNew.getLength())){
			jpmProductSaleNew.setLength(new BigDecimal(0));
		}
		if(jpmProductSaleNew.getWidth()==null || "".equals(jpmProductSaleNew.getWidth())){
			jpmProductSaleNew.setWidth(new BigDecimal(0));
		}
		if(jpmProductSaleNew.getHeight()==null || "".equals(jpmProductSaleNew.getHeight())){
			jpmProductSaleNew.setHeight(new BigDecimal(0));
		} 
		getHibernateTemplate().saveOrUpdate(jpmProductSaleNew);
	}
	
	/**
     * 换货单查询换货商品
     * @author fx 2015-08-10
     * @param crm
     * @param pager
     * @return
	 * @throws Exception 
     */
	public List getExJpmProductSalesByCrmNewTwo(CommonRecord crm, Pager pager) {
		StringBuffer hqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn ");
    	hqlBuf.append(" where jpstt.uniNo=jpsn.uniNo ");/*and jpstt.state='0' */
    	
    	//换货单数据排除美体的数据
    	hqlBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");
    	
    	//分公司编码
    	String companyCode = crm.getString("companyCode","");
    	if(StringUtils.isNotEmpty(companyCode)){
    		hqlBuf.append(" and jpsn.companyCode ='"+companyCode+"' ");
    	}
    	
    	//换货单对应的订单类型，需要加上辅消单商品，因为辅消品一般只有辅消单才有数据
    	String orderType = crm.getString("orderType","");
    	if(StringUtils.isNotEmpty(orderType)){
    		hqlBuf.append(" and jpstt.orderType in('"+orderType.replace(",", "','")+"') ");
    	}
    	
    	//团队编码
    	String teamCode = crm.getString("teamCode","");
    	if(StringUtils.isNotEmpty(teamCode)){
    		hqlBuf.append(" and jpstt.teamCode ='"+teamCode+"' ");
    	}
    	
    	//商品主键
    	String uniNo = crm.getString("uniNo","");
    	if(StringUtils.isNotEmpty(uniNo)){
    		hqlBuf.append(" and jpsn.uniNo ='"+uniNo+"' ");
    	} 
    	
    	//商品编码
    	String productNo = crm.getString("productNo","");
    	if(StringUtils.isNotEmpty(productNo)){
    		hqlBuf.append(" and jpsn.jpmProduct.productNo ='"+productNo+"' ");
    	} 
    	
    	//商品名称
    	String productName = crm.getString("productName","");
    	if(StringUtils.isNotEmpty(productName)){
    		hqlBuf.append(" and jpsn.jpmProduct.productName ='"+productName+"' ");
    	} 
    	
    	//商品状态：销售状态和前台销售都可以查询到 
    	String status = crm.getString("status","");
    	if(StringUtils.isNotEmpty(status)){
    		hqlBuf.append(" and jpsn.status  in('"+status.replace(",", "','")+"') ");
    	} 
    	 
    	hqlBuf.append(" order by jpsn.sortFlag ,jpsn.jpmProduct.productNo desc");

		return this.findObjectsByHqlQuery(hqlBuf.toString(),pager);
		
	}
	
}
