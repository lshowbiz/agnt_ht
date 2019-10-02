
package com.joymain.jecs.pm.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductSaleManager extends Manager {
	
	
	
    /**
     * Retrieves all of the jpmProductSales
     */
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
   /* public List getJpmProductSales(JpmProductSale jpmProductSale);*/
    
    

    /**
     * Gets jpmProductSale's information based on uniNo.
     * @param uniNo the jpmProductSale's uniNo
     * @return jpmProductSale populated jpmProductSale object
     */
//    public JpmProductSale getJpmProductSale(final String uniNo);//Modify By WuCF 注释之前的商品取法
    
    
    /**
     * Add By WuCF 20130525
     * Gets JmiMemberTeam's information based on code.
     * @param code the jpmProductSale's code
     * @return JmiMemberTeam populated JmiMemberTeam object
     */
    public JmiMemberTeam getJmiMemberTeam(final String code);

    /**
     * Saves a jpmProductSale's information
     * @param jpmProductSale the object to be saved
     */
  //WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public void saveJpmProductSale(JpmProductSale jpmProductSale);*/

    
    
    /**
     * Removes a jpmProductSale from the database by uniNo
     * @param uniNo the jpmProductSale's uniNo
     */
    public void removeJpmProductSale(final String uniNo);
    //added for getJpmProductSalesByCrm
  //WuCF JpmProductSaleNew Modify By WuCF 20130917
    public List getJpmProductSalesByCrm(CommonRecord crm, Pager pager);
    public List getJpmProductSalesByCrmNew(CommonRecord crm, Pager pager);
    
    //WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public boolean existProductNo(String companyCode, String productNo);*/

//    public JpmProductSale getJpmProductSale(String companyCode, String productNo);//Modify By WuCF 注释之前的商品取法
	public List getAlCompanyNotSaled(String productNo);
	
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public List getJpmProductSales(String companyCode,String type)throws Exception ;*/

	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public List getBackJpmProductSales(String companyCode,String type)throws Exception;*/
	
	public List getProductSales(String companyCode,String type,String status)throws Exception ;
	
	/**
	 * Add By WuCF 20140617 
	 * 得到换货单换货的商品信息
	 * @param companyCode
	 * @param type
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List getExProductSales(String companyCode,String type,String status)throws Exception ;
	
	//销售管理新增的方法 :所有的辅销品数据
	public List getJpoCounterProductSales(String companyCode)throws Exception ;
	
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public Map getLCProductSales(String companyCode,String type,String status)throws Exception ;*/
	//涓��浜у�
	//Modify By WuCF 注释之前的商品取法
//    public Map<String,List<JpmProductSale>> getPmProductSales(String companyCode,String type,String status)throws Exception ;

	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public Map getCompanyProductMap(String companyCode);*/
	
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public Map<String,JpmProductSale> getProductSaleMap(String companyCode);
	public Map<String,JpmProductSale> getProductSaleMap(CommonRecord crm);*/
	
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public void batchUpdateStatus();*/
	public void batchUpdateStatusNew();
	
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public Map<String, List<JpmProductSale>> getJpmProductSales(String groupType);*/
	
	//Modify By WuCF 注释之前的商品取法
//    public Map<String,List<JpmProductSale>> getPmProductSales(String companyCode,String type,String status,String productProvider)throws Exception ;

    /**
     * Add By WuCF 20130523 
     * Gets jpmProductSale's JmiMemberTeams information based on uniNo.
     * @param uniNo the jpmProductSale's uniNo
     * @return jpmProductSale populated jpmProductSale object
     */
    public List<HashMap<String,String>> getJmiMemberTeams(ResultSet rs);
    
    /*************************************商品新表数据获取***********************************/
    /**
     * Add By WuCF 20130916 商品新表相关，通过UNI_NO查询指定商品对象 
     * @param uniNo：商品uni_no主键
     * @return
     */
    public JpmProductSaleNew getJpmProductSaleNew(final String uniNo);
    
    
    /**
     * Add By WuCF 20130916 商品新表相关，通过companyCode和product_no查询指定商品对象 
     * @param companyCode：分公司编码
     * @param productNo：商品编码
     * @return
     */
    public JpmProductSaleNew getJpmProductSaleNew(String companyCode, String productNo);
    
    /**
     * Add By WuCF 20130916 商品团队类型对象
     * @param pttId：主键pttId
     * @return
     */
    public JpmProductSaleTeamType getJpmProductSaleTeamType(final String pttId);
    
    /**
     * Add By WuCF 20130916 商品团队类型对象     
     * @param companyCode：分公司编码
     * @param productNo：商品编码     
     * @param orderType：订单类型
     * @param teamCode：团队类型
     * @return
     */
    public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode);
    
    /**
     * Add By WuCF 20130916 获得商品新表对象数据
     * @param companyCode：分公司编码
     * @param type：订单类型
     * @param status：类型
     * @return
     * @throws Exception
     */
    public Map<String,List<JpmProductSaleTeamType>> getPmProductSalesNew(String companyCode,String orderType,String status)throws Exception ;
    
    /**
     * Add By WuCF 20130916 获得商品新表对象数据
     * @param companyCode：分公司编码
     * @param type：订单类型
     * @param status：类型
     * @teamCode：团队编码
     * @param user
     * @return
     * @throws Exception
     */
    public Map<String,List<JpmProductSaleTeamType>> getPmProductSalesNew(String companyCode,
    		String orderType,String status,String teamCode,SysUser user)throws Exception ;
    
    /**
     * Add By WuCF 20130916 获得指定分公司的商品团队类型信息
     * @param companyCode
     * @return
     */
    public Map getCompanyProductMapNew(String companyCode);
    
    /**
     * 得到JpmProductSaleTeamType
     * @param companyCode
     * @return
     */
    public Map<String,JpmProductSaleTeamType> getProductSaleMapNew(String companyCode);
    
    /**
     * 得到JpmProductSaleNew
     * @param companyCode
     * @return
     */
    public Map<String,JpmProductSaleNew> getProductSaleMapNew2(CommonRecord crm,String companyCode);

    /**
     * 换货单查询换货商品
     * @author fx 2015-08-10
     * @param crm
     * @param pager
     * @return
	 * @throws Exception 
     */
	public List getExProductSalesTwo(CommonRecord crm, Pager pager) throws Exception;
}

