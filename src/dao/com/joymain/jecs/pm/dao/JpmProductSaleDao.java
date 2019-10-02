
package com.joymain.jecs.pm.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmProductSaleDao extends Dao {
	final String DEFAULT_TEAM_CODE = "8888888888";

    /**
     * Retrieves all of the jpmProductSales
     */
	//WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public List getJpmProductSales(JpmProductSale jpmProductSale);*/

    /**
     * Gets jpmProductSale's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the jpmProductSale's uniNo
     * @return jpmProductSale populated jpmProductSale object
     */
//    public JpmProductSale getJpmProductSale(final Long uniNo);//Modify By WuCF 注释之前的商品取法

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
    public void removeJpmProductSale(final Long uniNo);
    //WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public JpmProductSale getPmProductSale(String companyCode, String productNo);*/
    //added for getJpmProductSalesByCrm
    
    //WuCF JpmProductSaleNew Modify By WuCF 20130917
    /*public List getJpmProductSalesByCrm(CommonRecord crm, Pager pager);*/

	public List getAlCompanyNotSaled(String productNo);

//	public JpmProductSale getJpmProductSale(String companyCode, String productNo);//Modify By WuCF 注释之前的商品取法
	public JmiMemberTeam getJmiMemberTeam(String code);
	public List getCategorysByCrm(CommonRecord crm);
	public List getPropertysByCatagory(CommonRecord crm,String catagory,String property);

	//WuCF JpmProductSaleNew Modify By WuCF 20130917
	/*public List<JpmProductSale> getPreUpdateSales(CommonRecord crm);*/

	/**
     * Gets jpmProductSale's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the jpmProductSale's uniNo
     * @return jpmProductSale populated jpmProductSale object
     */
    public List<HashMap<String, String>> getJmiMemberTeams(ResultSet rs);
    
    
    /*************************************商品新表数据获取***********************************/
    /**
     * Add By WuCF 20130916 商品新表相关，通过UNI_NO查询指定商品对象 
     * @param uniNo：商品uni_no主键
     * @return
     */
    public JpmProductSaleNew getJpmProductSaleNew(final Long uniNo);

    
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
     * @param productNo：商品编码
     * @param companyCode：分公司编码
     * @param teamCode：团队类型
     * @param orderType：订单类型
     * @return
     */
    public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode);

    
    /**
     * Add By WuCF 20130916 获得商品新表对象数据
     * @param companyCode：分公司编码
     * @param type：订单类型
     * @param status：类型
     * @teamCode：团队编码
     * @return
     * @throws Exception
     */
    public Map<String,List<JpmProductSaleTeamType>> getPmProductSalesNew(String companyCode,String orderType,String status,String teamCode)throws Exception ;

  //added for getJpmProductSalesByCrm
    public List getJpmProductSalesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20130916 获得换货单商品团队类型数据
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
    public List getJpmProductSalesByCrmNew(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20130916 获得商品团队类型数据
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
    public List getExJpmProductSalesByCrmNew(CommonRecord crm, Pager pager);
    
     
  //销售管理新增的方法 :所有的辅销品数据
	public List getJpoCounterProductSales(String companyCode)throws Exception ;
    
    /**
     * Add By WuCF 20130916 获得商品新表
     * @param crm：查询条件
     * @return：分页标示
     * @throws Exception
     */
    public List getJpmProductSaleNewsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 保存新商品表
     * @param jpmProductSaleNew
     */
    public void saveJpmProductSaleNew(final JpmProductSaleNew jpmProductSaleNew);

    /**
     * 换货单查询换货商品
     * @author fx 2015-08-10
     * @param crm
     * @param pager
     * @return
	 * @throws Exception 
     */
	public List getExJpmProductSalesByCrmNewTwo(CommonRecord crm, Pager pager);
}

