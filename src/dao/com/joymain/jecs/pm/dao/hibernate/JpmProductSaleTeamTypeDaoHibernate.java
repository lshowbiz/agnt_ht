
package com.joymain.jecs.pm.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.dao.JpmProductSaleTeamTypeDao;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpmProductSaleTeamTypeDaoHibernate extends BaseDaoHibernate implements JpmProductSaleTeamTypeDao {

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleTeamTypeDao#getJpmProductSaleTeamTypes(com.joymain.jecs.pm.model.JpmProductSaleTeamType)
	 */
	public List getJpmProductSaleTeamTypes(final JpmProductSaleTeamType jpmProductSaleTeamType) {
		return getHibernateTemplate().find("from JpmProductSaleTeamType");

		/* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductSaleTeamType == null) {
            return getHibernateTemplate().find("from JpmProductSaleTeamType");
        } else {
            // filter on properties set in the jpmProductSaleTeamType
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductSaleTeamType).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductSaleTeamType.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleTeamTypeDao#getJpmProductSaleTeamType(Long pttId)
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(final Long pttId) {
		JpmProductSaleTeamType jpmProductSaleTeamType = (JpmProductSaleTeamType) getHibernateTemplate().get(JpmProductSaleTeamType.class, pttId);
		if (jpmProductSaleTeamType == null) {
			log.warn("uh oh, jpmProductSaleTeamType with pttId '" + pttId + "' not found...");
			throw new ObjectRetrievalFailureException(JpmProductSaleTeamType.class, pttId);
		}

		return jpmProductSaleTeamType;
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleTeamTypeDao#saveJpmProductSaleTeamType(JpmProductSaleTeamType jpmProductSaleTeamType)
	 */    
	public void saveJpmProductSaleTeamType(final JpmProductSaleTeamType jpmProductSaleTeamType) {
		getHibernateTemplate().saveOrUpdate(jpmProductSaleTeamType);
	}

	/**
	 * @see com.joymain.jecs.pm.dao.JpmProductSaleTeamTypeDao#removeJpmProductSaleTeamType(Long pttId)
	 */
	public void removeJpmProductSaleTeamType(final Long pttId) {
		getHibernateTemplate().delete(getJpmProductSaleTeamType(pttId));
	}
	//added for getJpmProductSaleTeamTypesByCrm
	public List getJpmProductSaleTeamTypesByCrm(CommonRecord crm, Pager pager){
		String hql = "from JpmProductSaleTeamType jpmProductSaleTeamType where 1=1";
		String cxFlag = crm.getString("cxFlag",""); 
		if("n".equals(cxFlag)){
			hql += " and 1=2 ";
		}else{			 
			String companyCode = crm.getString("companyCode","");
	    	if(StringUtils.isNotEmpty(companyCode)){
	    		hql += " and jpmProductSaleTeamType.companyCode='"+companyCode.trim()+"' ";
	    	}
			
			String productNo = crm.getString("productNo","");
	    	if(StringUtils.isNotEmpty(productNo)){
	    		hql += " and jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo='"+productNo.trim()+"' ";
	    	}
	    	
	    	String productName = crm.getString("productName","");
	    	if(StringUtils.isNotEmpty(productName)){
	    		hql += " and jpmProductSaleTeamType.jpmProductSaleNew.productName like '%"+productName.trim()+"%' ";
	    	}
	    	
	    	String state = crm.getString("state","");
	    	if(StringUtils.isNotEmpty(state)){
	    		hql += " and jpmProductSaleTeamType.state='"+state.trim()+"' ";
	    	}
	    	
	    	String teamCode = crm.getString("teamCode","");
	    	if(StringUtils.isNotEmpty(teamCode)){
	    		hql += " and jpmProductSaleTeamType.teamCode='"+teamCode.trim()+"' ";
	    	}
	    	
	    	String orderType = crm.getString("orderType","");
	    	if(StringUtils.isNotEmpty(orderType)){
	    		hql += " and jpmProductSaleTeamType.orderType='"+orderType.trim()+"' ";
	    	} 
	    	
	    	String isHidden = crm.getString("isHidden","");
	    	if(StringUtils.isNotEmpty(isHidden)){
	    		hql += " and jpmProductSaleTeamType.isHidden='"+isHidden.trim()+"' ";
	    	} 
	    	
	    	hql += " and exists(select 1 from JmiMemberTeam jmiMemberTeam where jpmProductSaleTeamType.teamCode=jmiMemberTeam.code and jmiMemberTeam.status='0')";
		} 
    	/*if(pager == null){
    		hql += " order by pmProductSaleNew.jpmProduct.productNo "; 
    		return this.getHibernateTemplate().find(hql);
    	}*/
		
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by pttId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	 /**
     * @param crm：查询条件
     * @param type：查询类型    0：新增   1：修改
     * @return：返回是否已经有重复的数据
     */
	public boolean isExist(CommonRecord crm, String type) {
		boolean isExist = false;
		String hql = "from JpmProductSaleTeamType jpmProductSaleTeamType where 1=1 ";
		 
		//如果是禁用的,也算是存在
//    	String state = crm.getString("state","");
//    	if(StringUtils.isNotEmpty(state)){
//    		hql += " and jpmProductSaleTeamType.state='"+state.trim()+"' ";
//    	}
		String companyCode = crm.getString("companyCode","");
		String teamCode = crm.getString("teamCode","");
		String orderType = crm.getString("orderType","");
		String uniNo = crm.getString("uniNo","");
    	if(StringUtils.isNotEmpty(teamCode) || StringUtils.isNotEmpty(orderType) ||StringUtils.isNotEmpty(uniNo)){
    		if(StringUtils.isNotEmpty(companyCode)){
        		hql += " and jpmProductSaleTeamType.companyCode='"+companyCode.trim()+"' ";
        	}
        	
        	if(StringUtils.isNotEmpty(teamCode)){
        		hql += " and jpmProductSaleTeamType.teamCode='"+teamCode.trim()+"' ";
        	}
        	
        	if(StringUtils.isNotEmpty(orderType)){
        		hql += " and jpmProductSaleTeamType.orderType='"+orderType.trim()+"' ";
        	} 
        	
        	if(StringUtils.isNotEmpty(uniNo)){
        		hql += " and jpmProductSaleTeamType.uniNo='"+uniNo.trim()+"' ";
        	} 
        	
        	//如果是编辑功能
        	String pttId = crm.getString("pttId","");
        	if("1".equals(type) && StringUtils.isNotEmpty(uniNo)){ 
        		hql += " and jpmProductSaleTeamType.pttId!='"+pttId+"' ";
        	}
        	
        	List list = this.findObjectsByHqlQuery(hql);
        	if(list!=null && list.size()>0){
        		isExist = true;
        	} 
    	} 
		return isExist;
	}

	 /**
     * 得到指定类型编码的全部数据
     * @param listCode
     * @return
     */
	public List getJsysListValues(String listCode,String companyCode) {
//		select t2.* from jsys_list_key t1,jsys_list_value t2
//		where t1.key_id=t2.key_id and t1.list_code='po.all.order_type' order by t2.order_no;
		List list = new ArrayList();
		StringBuffer sqlBuf = new StringBuffer("select t2.value_code,t2.value_title from JSYS_LIST_KEY t1,JSYS_LIST_VALUE t2 where t1.key_id=t2.key_id ");
		if (StringUtils.isNotEmpty(listCode)) {
			sqlBuf.append(" and t1.list_code='");
			sqlBuf.append(listCode);
			sqlBuf.append("' ");
		} 
		sqlBuf.append(" and (ex_company_code is null or ex_company_code not like '%"+companyCode+"%')");
		sqlBuf.append(" order by t2.order_No ");
		list = this.getJdbcTemplate().queryForList(sqlBuf.toString());
		return list;
	}

	
	/**
     * 通过商品编号和
     * @param companyCode：分区
     * @param productNo：商品编号
     * @param teamCode：团队编号
     * @return：返回商品的订单类型字符串集合
     */
	public String getOrderTypeStr(String companyCode, String uniNo,String teamCode) {
		StringBuffer orderTypeStr = new StringBuffer("");
		StringBuffer hqlBuf = new StringBuffer(" select t from JpmProductSaleTeamType t where 1=1 ");
		if(StringUtils.isNotEmpty(companyCode)) {
			hqlBuf.append(" and t.companyCode='");
			hqlBuf.append(companyCode);
			hqlBuf.append("' ");
		}  
		if(StringUtils.isNotEmpty(uniNo)) {
			hqlBuf.append(" and t.uniNo='");
			hqlBuf.append(uniNo);
			hqlBuf.append("' ");
		}  
		if(StringUtils.isNotEmpty(teamCode)) {
			hqlBuf.append(" and t.teamCode='");
			hqlBuf.append(teamCode);
			hqlBuf.append("' ");
		}  
		List<JpmProductSaleTeamType> list = this.findObjectsByHqlQuery(hqlBuf.toString());
		for(JpmProductSaleTeamType jpstt : list){
			orderTypeStr.append(jpstt.getPrice()+"-"+jpstt.getPv()+"-"+jpstt.getOrderType()+"#");
		}
		return orderTypeStr.toString();
	}

	 /**
     * 获得指定商品团队类型对象
     * @param companyCode
     * @param productNo
     * @return
     */
	public JpmProductSaleNew getJpmProductSaleNew(String companyCode,
			String productNo) {
		JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
		StringBuffer orderTypeStr = new StringBuffer("");
		StringBuffer hqlBuf = new StringBuffer(" from JpmProductSaleNew t where 1=1 ");
		if(StringUtils.isNotEmpty(companyCode)) {
			hqlBuf.append(" and t.companyCode='");
			hqlBuf.append(companyCode);
			hqlBuf.append("' ");
		}  
		if(StringUtils.isNotEmpty(productNo)) {
			hqlBuf.append(" and t.jpmProduct.productNo='");
			hqlBuf.append(productNo);
			hqlBuf.append("' ");
		}  
		List<JpmProductSaleNew> list = this.findObjectsByHqlQuery(hqlBuf.toString());
		if(list!=null && list.size()>=1){
			jpmProductSaleNew = list.get(0);
		}
		return jpmProductSaleNew;
	}
	
	/**
     * 获得指定商品对象
     * @param companyCode
     * @param productNo
     * @return
     */
	public List getJpmProductSaleTeamType(String companyCode,
			String productNo) {
		JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
		StringBuffer orderTypeStr = new StringBuffer("");
		StringBuffer hqlBuf = new StringBuffer(" from JpmProductSaleTeamType t where 1=1 ");
		if(StringUtils.isNotEmpty(companyCode)) {
			hqlBuf.append(" and t.jpmProductSaleNew.companyCode='");
			hqlBuf.append(companyCode);
			hqlBuf.append("' ");
		}  
		if(StringUtils.isNotEmpty(productNo)) {
			hqlBuf.append(" and t.jpmProductSaleNew.jpmProduct.productNo='");
			hqlBuf.append(productNo);
			hqlBuf.append("' ");
		}  
		return this.findObjectsByHqlQuery(hqlBuf.toString());
	}
	
	/**
     * 批量审核团队订单类型
     * @param uniNoStr:商品图片字符串：用逗号隔开
     * @return
     */
	public int batchAuditProductTeamTypes(String uniNoStr,String status) {
		StringBuffer sqlBuf = new StringBuffer();
		if(uniNoStr!=null){
			uniNoStr = uniNoStr.substring(0, uniNoStr.length()-1);
			sqlBuf.append(" update JpmProductSaleTeamType jpstt set jpstt.state='");
			sqlBuf.append(status);
			sqlBuf.append("' where jpstt.pttId in('");
			sqlBuf.append(uniNoStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		return this.executeUpdate(sqlBuf.toString());
	}
	
	/**
     * 根据分区，订单类型，团队标志，商品编码获取商品价格
     * @author fx 2015-08-11
     * @param companyCode 公司编号
     * @param orderType 订单类型
     * @param teamCode 团队标志
     * @param productNo 商品编码
     * @return BigDecimal 商品价格
     */
	public BigDecimal getSubProductNoPrice(String companyCode, String orderType,String teamCode, String productNo) {
	 try{	
		StringBuffer orderTypeStr = new StringBuffer("");
		StringBuffer hqlBuf = new StringBuffer(" from JpmProductSaleTeamType t where 1=1 ");
		if(StringUtils.isNotEmpty(companyCode)) {
			hqlBuf.append(" and t.companyCode='");
			hqlBuf.append(companyCode);
			hqlBuf.append("' ");
		}  
		if(StringUtils.isNotEmpty(orderType)) {
			hqlBuf.append(" and t.orderType='");
			hqlBuf.append(orderType);
			hqlBuf.append("' ");
		}  
		if(StringUtils.isNotEmpty(teamCode)) {
			hqlBuf.append(" and t.teamCode='");
			hqlBuf.append(teamCode);
			hqlBuf.append("' ");
		} 
		if(StringUtils.isNotEmpty(productNo)) {
			hqlBuf.append(" and t.jpmProductSaleNew.jpmProduct.productNo='");
			hqlBuf.append(productNo);
			hqlBuf.append("' ");
		} 
		List<JpmProductSaleTeamType> list = this.findObjectsByHqlQuery(hqlBuf.toString());
		if(null!=list){
			if(list.size()>0){
				return list.get(0).getPrice();
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	    return null;
	}
	
}
