
package com.joymain.jecs.pm.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmProductSaleNewDao;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductSaleNewDaoHibernate extends BaseDaoHibernate implements JpmProductSaleNewDao {

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
    		hql += " and pmProductSale.status in( "+statusNo.replace(",", "','")+")";
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
    	/*String controlFirst= crm.getString("controlFirst","");
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
    	}*/
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
    }
	
	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleNewDao#getJpmProductSaleNews(com.joymain.jecs.pm.model.JpmProductSaleNew)
	 */
	public List getJpmProductSaleNews(final JpmProductSaleNew jpmProductSaleNew) {
		return getHibernateTemplate().find("from JpmProductSaleNew jpsn where 1=2 and jpsn.status='1' and jpsn.companyCode='CN' ");

		/* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductSaleNew == null) {
            return getHibernateTemplate().find("from JpmProductSaleNew");
        } else {
            // filter on properties set in the jpmProductSaleNew
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductSaleNew).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductSaleNew.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
	}

	/**
     * Retrieves all of the jpmProductSaleNews
     * Add By WuCF 20140306 新增商品团队类型，选择指定团队&订单类型未新增的商品数据
     */
	public List getNotExistsJpmProductSaleNews(CommonRecord crm) {
		//查询条件
		String companyCode = crm.getString("companyCode","");
		String teamCode = crm.getString("teamCode","");
		String orderType = crm.getString("orderType","");
		StringBuffer hqlBuf = new StringBuffer("select jpsn.uni_no UNI_NO,jpsn.product_no PRODUCT_NO,jpsn.product_name PRODUCT_NAME,jpstt.price PRICE,jpstt.pv PV from Jpm_Product_Sale_New jpsn,Jpm_product jp,Jpm_Product_Sale_Team_Type jpstt where jpsn.uni_No=jpstt.uni_No and jp.product_no=jpsn.product_no and jpsn.product_No not like 'PLC%' ");
		
		//商品编码
		String productNo = crm.getString("productNo",""); 
    	if(StringUtils.isNotEmpty(productNo)){
    		hqlBuf.append(" and jpsn.product_No like '%");
    		hqlBuf.append(productNo);
    		hqlBuf.append("%' ");
    	}
    	
    	//商品名称
		String productName = crm.getString("productName",""); 
    	if(StringUtils.isNotEmpty(productName)){
    		hqlBuf.append(" and jpsn.product_Name like '%");
    		hqlBuf.append(productName);
    		hqlBuf.append("%' ");
    	}
    	
    	//商品销售状态
		String status = crm.getString("status",""); 
    	if(StringUtils.isNotEmpty(status)){
    		hqlBuf.append(" and jpsn.status ='");
    		hqlBuf.append(status);
    		hqlBuf.append("' ");
    	}
    	
    	//商品订单类型状态
		String state = crm.getString("state",""); 
    	if(StringUtils.isNotEmpty(state)){
    		hqlBuf.append(" and jpstt.state ='");
    		hqlBuf.append(state);
    		hqlBuf.append("' ");
    	}
    	
    	//商品分类
		String productCategory = crm.getString("productCategory",""); 
    	if(StringUtils.isNotEmpty(productCategory)){
    		hqlBuf.append(" and jp.product_Category ='");
    		hqlBuf.append(productCategory);
    		hqlBuf.append("' ");
    	}
    	
    	//商品销售方式
		String smNo = crm.getString("smNo",""); 
    	if(StringUtils.isNotEmpty(smNo)){
    		hqlBuf.append(" and jp.sm_No ='");
    		hqlBuf.append(smNo);
    		hqlBuf.append("' ");
    	}
		//Modify By WuCF 20140506 修改成取全部商品，防止添加时遗漏！and jpsn.status in('1','2')
		hqlBuf.append(" and jpsn.company_Code='");
		hqlBuf.append(companyCode);
		hqlBuf.append("' and not exists(select 1 from Jpm_Product_Sale_Team_Type jpstt where jpsn.uni_No=jpstt.uni_No and jpstt.company_Code='");
		hqlBuf.append(companyCode);
		hqlBuf.append("' and jpstt.team_Code='");
		hqlBuf.append(teamCode);
		hqlBuf.append("' and jpstt.order_Type='");
		hqlBuf.append(orderType);
		hqlBuf.append("') and jpstt.team_Code='8888888888' and jpstt.order_Type='");
		hqlBuf.append(orderType);
		hqlBuf.append("' order by jpsn.product_no");
		log.info("getNotExistsJpmProductSaleNews:"+hqlBuf.toString());
		return this.getJdbcTemplate().queryForList(hqlBuf.toString());
	}
	
	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleNewDao#getJpmProductSaleNew(Long uniNo)
	 */
	public JpmProductSaleNew getJpmProductSaleNew(final Long uniNo) {
		JpmProductSaleNew jpmProductSaleNew = (JpmProductSaleNew) getHibernateTemplate().get(JpmProductSaleNew.class, uniNo);
		if (jpmProductSaleNew == null) {
			log.warn("uh oh, jpmProductSaleNew with uniNo '" + uniNo + "' not found...");
			throw new ObjectRetrievalFailureException(JpmProductSaleNew.class, uniNo);
		}

		return jpmProductSaleNew;
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleNewDao#saveJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew)
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
     * Add By WuCF 20140311 发送短信记录保存
     */
    public void saveJpmSmssendInfo(JpmSmssendInfo jpmSmssendInfo){
    	if(jpmSmssendInfo!=null && jpmSmssendInfo.getSmsMessage()!=null){//发送消息不能为空，且过滤引号
    		String smsMessage = jpmSmssendInfo.getSmsMessage();
    		smsMessage.replace("\'", "‘");
    		smsMessage.replace("\"", "“");
    		jpmSmssendInfo.setSmsMessage(smsMessage);
    	}else{
    		return;
    	}
    	
    	StringBuffer sqlBuf = new StringBuffer("insert into jpm_smssend_info");
    	sqlBuf.append("(sms_id,sms_type,sms_recipient,sms_message,sms_time,sms_operator,sms_status,remark,phone_num)values(");
    	sqlBuf.append("SEQ_SMS.nextval,'");
    	sqlBuf.append(jpmSmssendInfo.getSmsType());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getSmsRecipient());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getSmsMessage());
    	sqlBuf.append("',sysdate,'");
    	sqlBuf.append(jpmSmssendInfo.getSmsOperator());
    	sqlBuf.append("','"); 
    	sqlBuf.append(jpmSmssendInfo.getSmsStatus());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getRemark());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getPhoneNum());
    	sqlBuf.append("')");
    	this.getJdbcTemplate().execute(sqlBuf.toString());
    }
	
	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleNewDao#removeJpmProductSaleNew(Long uniNo)
	 */
	public void removeJpmProductSaleNew(final Long uniNo) {
		getHibernateTemplate().delete(getJpmProductSaleNew(uniNo));
	}
	//added for getJpmProductSaleNewsByCrm
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
	    		hql += " and jpmProductSaleNew.status in('"+status.replace(",", "','")+"') ";
	    	}
	    	
	    	String confirm = crm.getString("confirm","");
	    	if(StringUtils.isNotEmpty(confirm)){
	    		hql += " and jpmProductSaleNew.confirm='"+confirm.trim()+"' ";
	    	}
	    	
	    	String productCategory = crm.getString("productCategory","");
	    	if(StringUtils.isNotEmpty(productCategory)){
	    		hql += " and jpmProductSaleNew.jpmProduct.productCategory='"+productCategory.trim()+"' ";
	    	}
		}
		
		//  
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public JpmProductSaleNew getPmProductSaleNew(String companyCode,
			String productNo) {
		// TODO Auto-generated method stub
		String hql = "from JpmProductSaleNew pmProductSaleNew where pmProductSaleNew.companyCode='"+companyCode+"' and pmProductSaleNew.jpmProduct.productNo='"+productNo+"'";
		if(getObjectByHqlQuery(hql) !=null){
			return (JpmProductSaleNew) this.getObjectByHqlQuery(hql);
		}else{
			return null;
		}
	}
	
	 /**
     * 批量审核商品
     * @param uniNoStr:商品编码字符串：用逗号隔开
     * @return
     */
	public int batchAuditProductNews(String uniNoStr,String status) {
		StringBuffer sqlBuf = new StringBuffer();
		if(uniNoStr!=null){
			uniNoStr = uniNoStr.substring(0, uniNoStr.length()-1);
			sqlBuf.append(" update JpmProductSaleNew jpsn set jpsn.status='");
			sqlBuf.append(status);
			sqlBuf.append("' where jpsn.uniNo in('");
			sqlBuf.append(uniNoStr.replace(",", "','"));
			sqlBuf.append("') ");
		} 
		return this.executeUpdate(sqlBuf.toString());
	}
	
	/*************************************1.商品信息管理**************************************/
    /**
	 * 获得相关商品数据集合
	 * @param uniNo：商品销售新表主键
	 * @status：状态类型
	 * @return
	 */
	public List<JpmProductSaleRelated> getJpmProductSaleRelatedList(String uniNo,String status) {
		StringBuffer sqlBuf = new StringBuffer("select jpsr from JpmProductSaleRelated jpsr where 1=1 ");
		//Modify By WuCF 20140530 暂时注释
		//and (jpsr.relationJpmProductSaleNew.changeabledFlag!='1' or jpsr.relationJpmProductSaleNew.changeabledFlag is null) 
		//查询条件：分公司编码
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpsr.jpmProductSaleNew.uniNo='");
			sqlBuf.append(uniNo);
			sqlBuf.append("' ");
		}
		if(StringUtils.isNotEmpty(status)){
			sqlBuf.append(" and jpsr.status='");
			sqlBuf.append(status);
			sqlBuf.append("' ");
		}
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
    /**
	 * 获得相关商品对应的JpmProductSaleTeamType对象的集合
	 * @param uniNo：商品销售新表主键
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @return:uniNo对应的商品JpmProductSaleTeamType对象的List集合
	 */
	public List<JpmProductSaleTeamType> getRelatedTeamTypeList(String uniNo,String teamCode,String orderType) {
    	List<JpmProductSaleTeamType> returnList = new ArrayList<JpmProductSaleTeamType>();
    	
    	//查询商品的相关商品，状态参数：0 表示:可用
    	ArrayList list = (ArrayList<JpmProductSaleRelated>)this.getJpmProductSaleRelatedList(uniNo,"0");
    	
    	//获得相关商品的编号信息字符串
    	StringBuffer relatedUninoBuf = new StringBuffer("");
		for(int i=0;i<list.size();i++){
			relatedUninoBuf.append("'");
			relatedUninoBuf.append(((JpmProductSaleRelated)list.get(i)).getRelationJpmProductSaleNew().getUniNo());
			relatedUninoBuf.append("'");
			if(i<list.size()-1){
				relatedUninoBuf.append(",");
			}
		}
		
		//获得满足指定条件的teamCode和orderType的JpmProductSaleTeamType对象的集合
		StringBuffer sqlBuf = new StringBuffer();
		if(!"".equals(relatedUninoBuf.toString())){
			sqlBuf.append(" select jpstt from JpmProductSaleTeamType jpstt where jpstt.state='0'  ");
			//Modify By WuCF 20140530 暂时注释
			//and (jpstt.jpmProductSaleNew.changeabledFlag!='1' or jpstt.jpmProductSaleNew.changeabledFlag is null)
			sqlBuf.append(" and jpstt.jpmProductSaleNew.uniNo in(");
			sqlBuf.append(relatedUninoBuf.toString());
			sqlBuf.append(")");
			//查询条件：分公司编码
			if(StringUtils.isNotEmpty(teamCode)){
				sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
				sqlBuf.append(teamCode);
				sqlBuf.append("' ");
			}
			if(StringUtils.isNotEmpty(orderType)){
				sqlBuf.append(" and jpstt.orderType='");
				sqlBuf.append(orderType);
				sqlBuf.append("' ");
			}
			returnList = this.getSession().createQuery(sqlBuf.toString()).list(); 
		}

		return returnList; 

	}
	
    
    /**
	 * 获得商品的图片集合
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	 * 注释：如果imageType为空，则返回指定商品uniNo所有的图片的集合
	 */
	public List<JpmProductSaleImage> getJpmProductSaleImageList(String uniNo,String imageType) {
		StringBuffer sqlBuf = new StringBuffer("select jpsi from JpmProductSaleImage jpsi where 1=1 ");
		//如果查询的图片类型不为空，则作为查询条件
		if(StringUtils.isNotEmpty(imageType)){
			sqlBuf.append(" and jpsi.jpmProductSaleNew.jpmProduct.uniNo='");
			sqlBuf.append(uniNo);
			sqlBuf.append("' ");
		}
		
		//如果查询的图片类型不为空，则作为查询条件
		if(StringUtils.isNotEmpty(imageType)){
			sqlBuf.append(" and jpsi.imageType='");
			sqlBuf.append(imageType);
			sqlBuf.append("' ");
		}
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
    /**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	 * 注释：如果imageType为空，则返回指定商品uniNo所有的图片的集合
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String uniNo,String teamCode,String orderType,String companyCode) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt ");
		sqlBuf.append(" where 1=1  ");
		//Modify By WuCF 20140530 不用下面这段代码，这个方法其他地方暂时也没用到
		//and (jpstt.jpmProductSaleNew.changeabledFlag!='1' or jpstt.jpmProductSaleNew.changeabledFlag is null)
		//商品UNI_NO
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.uniNo in('");
			sqlBuf.append(uniNo.replace(",", "','"));
			sqlBuf.append("') ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType in('");
			sqlBuf.append(orderType.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
    /**
	 * 获得商品团队类型数据集合
	 * @param productNo：商品编码
	 * @param productName：商品名称
	 * @param productCategory：商品类别
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @param companyCode：国别
	 * @return：获得商品的团队类型集合
	 * 注释：商品编码、名称、类别、国别都是按等于或like；团队编码、订单类型可能有多个
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(
			String productNo, String productName, String productCategory,
			String teamCode, String orderType, String companyCode) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt ");
		sqlBuf.append(" where 1=1  ");//and jpstt.jpmProductSaleNew.changeabledFlag!='1'  Modify By WuCF 20140530暂时注释
		//商品编码
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo like '%");
			sqlBuf.append(productNo.trim());
			sqlBuf.append("%' ");
		}		
		//商品名称
		if(StringUtils.isNotEmpty(productName)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productName like '%");
			sqlBuf.append(productName.trim());
			sqlBuf.append("%' ");
		}	
		//商品类别
		if(StringUtils.isNotEmpty(productName)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productCategory ='");
			sqlBuf.append(productCategory.trim());
			sqlBuf.append("' ");
		}	
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType in('");
			sqlBuf.append(orderType.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
    /**
	 * 获得商品的团队类型Map
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public HashMap<String,ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr) {
    	HashMap<String,ArrayList<JpmProductSaleTeamType>> map = new HashMap<String,ArrayList<JpmProductSaleTeamType>>();
		
    	//调用方法获得JpmProductSaleTeamType对象的List集合
    	ArrayList<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = (ArrayList<JpmProductSaleTeamType>)this.getJpmProductSaleTeamTypeList(uniNo, teamCode, orderType, companyCode);
		
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
		
		//获得系列的list集合
		List productCateList = new ArrayList<String>();
		if(productCategoryStr!=null){
			String[] strs = productCategoryStr.split(",");
			for(String str : strs){
				productCateList.add(str);
			}
		}
		
		
		//第二步：通过商品系列分类
		ArrayList<JpmProductSaleTeamType> tempList = null; 
		for(String productCategory : productCategorys){
			if(productCateList.contains(productCategory) || productCategoryStr==null || "".equals(productCategoryStr) ){
				tempList = new ArrayList<JpmProductSaleTeamType>();
				for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
					if(productCategory!=null && productCategory.equals(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory())){
						tempList.add(jpstt);
					}
				}
				map.put(productCategory, tempList);
			}
		}
		
		
		return map;
	}
	
    
	/**
	 * 获得指定的商品团队类型对象
	 * @param companyCode:分区
	 * @param productNo:商品编码
	 * @param orderType:订单类型
	 * @param teamCode:团队类型
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode) {
		JpmProductSaleTeamType jpst = null;
		StringBuffer sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleNew jpsn,JpmProductSaleTeamType jpstt ");
		sqlBuf.append(" where jpsn.uniNo=jpstt.jpmProductSaleNew.uniNo ");
		//商品编码
		sqlBuf.append(" and jpsn.jpmProduct.productNo = :productNo ");
		//团队类型
		sqlBuf.append(" and jpstt.jmiMemberTeam.code = :teamCode ");
		//订单类型
		sqlBuf.append(" and jpstt.orderType = :orderType ");
		//分区
		sqlBuf.append(" and jpsn.companyCode = :companyCode ");
		
//		List<JpmProductSaleTeamType> list = this.getSession().createQuery(sqlBuf.toString()).list();
		//预编译处理
		Query q = getSession().createQuery(sqlBuf.toString()); 
		q.setParameter("productNo", productNo);
		q.setParameter("teamCode", teamCode);
		q.setParameter("orderType", orderType);
		q.setParameter("companyCode", companyCode);
		List<JpmProductSaleTeamType> list = q.list();
		if(list!=null && list.size()>=1){
			jpst = (JpmProductSaleTeamType)list.get(0);
		}
		return jpst;
	}
	
	/**
	 * 获得指定的商品团队类型对象
	 * @param companyCode:分区
	 * @param productNo:商品编码
	 * @param orderType:订单类型
	 * @param teamCode:团队类型
	 * @param status:商品状态
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode,String status) {
		JpmProductSaleTeamType jpst = null;
		StringBuffer sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleNew jpsn,JpmProductSaleTeamType jpstt ");
		sqlBuf.append(" where jpsn.uniNo=jpstt.jpmProductSaleNew.uniNo ");
		//商品编码
		sqlBuf.append(" and jpsn.jpmProduct.productNo = :productNo ");
		//团队类型
		sqlBuf.append(" and jpstt.jmiMemberTeam.code = :teamCode ");
		//订单类型
		sqlBuf.append(" and jpstt.orderType = :orderType ");
		//分区
		sqlBuf.append(" and jpsn.companyCode = :companyCode ");
		//
		sqlBuf.append(" and jpsn.status in('"+status.replace(",", "','")+"') ");
		
//		List<JpmProductSaleTeamType> list = this.getSession().createQuery(sqlBuf.toString()).list();
		//预编译处理
		Query q = getSession().createQuery(sqlBuf.toString()); 
		q.setParameter("productNo", productNo);
		q.setParameter("teamCode", teamCode);
		q.setParameter("orderType", orderType);
		q.setParameter("companyCode", companyCode);
		List<JpmProductSaleTeamType> list = q.list();
		if(list!=null && list.size()>=1){
			jpst = (JpmProductSaleTeamType)list.get(0);
		}
		return jpst;
	}

	

	/**
	 * 通过主键获得JpmProductSaleTeamType对象
	 * @param pttId
	 * @return
	 */ 
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId) {
    	return (JpmProductSaleTeamType) this.getObjectByHqlQuery("from JpmProductSaleTeamType jpstt where jpstt.pttId='"+pttId+"' ");
	}	
    
    /**
	 * 通过主键获得JpmProductSaleNew对象
	 * @param pttId
	 * @return
	 */ 
	public JpmProductSaleNew getJpmProductSaleNew(String uniNo) {
    	return (JpmProductSaleNew) this.getObjectByHqlQuery("from JpmProductSaleNew jpsn where jpsn.uniNo='"+uniNo+"' ");
	}	
    
    /**
	 * 通过商品编号productNo商品团队类型对应的主键ptt_id
	 * @param：productNo 商品编码
	 * @return：商品团队类型对应的主键ptt_id
	 */
	public String getJpmProductSaleTeamTypePttid(String productNo,String teamCode,String orderType) {
    	String pttId = "";
    	StringBuffer sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleNew jpsn , ");
    	sqlBuf.append(" JpmProductSaleTeamType jpstt where jpsn.uniNo=jpstt.jpmProductSaleNew.uniNo ");
    	//商品UNI_NO
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productNo='");
			sqlBuf.append(productNo);
			sqlBuf.append("' ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
			sqlBuf.append(teamCode);
			sqlBuf.append("' ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType='");
			sqlBuf.append(orderType);
			sqlBuf.append("' ");
		}
		
		List list =this.getSession().createQuery(sqlBuf.toString()).list();
		if(list!=null && list.size()>=1){
			pttId = String.valueOf(((JpmProductSaleTeamType)list.get(0)).getPttId());
		}
    	return pttId;
	}	
    
    /*************************************2.物流信息**************************************/
    /**
	 * 通过主键获得物流发货信息，目前只查询4个字段：发货仓库、物流公司、运单号、发货时间
	 * @param pttId
	 * @return
	 */
	public HashMap<String, Object> getPdSendinfo(String siNo) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		String sql = "select t.sh_no,t.warehouse_no,t.order_no,t.create_time from pd_send_info t where t.si_no=? ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, new Object[]{siNo});
		if(list!=null && list.size()>=1){
			map = (HashMap<String,Object>)list.get(0);
		}
		return map;
	}

	/**
	 * 通过物流公司编号获得物流信息：目前返回的只有网站信息
	 * @param shno
	 * @return
	 */
	public HashMap<String, String> getShnoInfo() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("ZTWL", JpmProductSaleNewDao.ZTWL_URL);//中铁物流
		map.put("GT", JpmProductSaleNewDao.GT_URL);//国通
		map.put("BZ", JpmProductSaleNewDao.BZ_URL);//倍智
		map.put("TXJ", JpmProductSaleNewDao.TXJ_URL);//统新捷
		map.put("YD", JpmProductSaleNewDao.YD_URL);//韵达
		map.put("ZJS", JpmProductSaleNewDao.ZJS_URL);//宅急送
		map.put("KJ", JpmProductSaleNewDao.KJ_URL);//科捷
		return map;
	}

	/**
	 * 通过物流公司编号获得物流网站查询网址
	 * @param shno：物流公司编号
	 * @return：物流公司查询网址
	 */
	public String getShnoUrl(String shno) {
		return (String)this.getShnoInfo().get(shno);
	}
	
	/**
     * Add By WuCF 20141110
     * 得到指定主键集合的商品编码集合
     * @param uniNoStr:
     * @return：商品编码集合
     */
	public String getProductNos(String uniNoStr) {
		StringBuffer sqlBuf = new StringBuffer();
		if(uniNoStr!=null){
			sqlBuf.append(" select jpsn.product_no PRODUCT_NO from jpm_product_sale_new jpsn ");
			sqlBuf.append(" where jpsn.uni_No in('");
			sqlBuf.append(uniNoStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		List<Map<String,String>> list = this.getJdbcTemplate().queryForList(sqlBuf.toString());
		StringBuffer productStr = new StringBuffer("");
		for(Map<String,String> map : list){
			productStr.append(","+map.get("PRODUCT_NO"));
		}
		if(productStr.toString()!=null && productStr.toString().length()>=1){
			productStr = new StringBuffer(productStr.toString().substring(1));
		}
		return productStr.toString();
	}
}
