
package com.joymain.jecs.pm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmProductSaleNewManager extends Manager {
	
	public Map getCompanyProductMap(String companyCode);
	
    /**
     * Retrieves all of the jpmProductSaleNews
     */
    public List getJpmProductSaleNews(JpmProductSaleNew jpmProductSaleNew);
    
    /**
     * Retrieves all of the jpmProductSaleNews
     * Add By WuCF 20140306 新增商品团队类型，选择指定团队&订单类型未新增的商品数据
     */
    public List getNotExistsJpmProductSaleNews(CommonRecord crm);

    /**
     * Gets jpmProductSaleNew's information based on uniNo.
     * @param uniNo the jpmProductSaleNew's uniNo
     * @return jpmProductSaleNew populated jpmProductSaleNew object
     */
    public JpmProductSaleNew getJpmProductSaleNew(final String uniNo);

    /**
     * Saves a jpmProductSaleNew's information
     * @param jpmProductSaleNew the object to be saved
     */
    public void saveJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew);
    
    /**
     * Add By WuCF 20140311 发送短信记录保存
     */
    public void saveJpmSmssendInfo(JpmSmssendInfo jpmSmssendInfo);

    /**
     * Removes a jpmProductSaleNew from the database by uniNo
     * @param uniNo the jpmProductSaleNew's uniNo
     */
    public void removeJpmProductSaleNew(final String uniNo);
    //added for getJpmProductSaleNewsByCrm
    public List getJpmProductSaleNewsByCrm(CommonRecord crm, Pager pager);

    //Is exist PO ?
    public boolean existProductNo(String companyCode, String productNo);
    
    /**
     * 批量审核商品
     * @param uniNoStr:商品编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditProductNews(String uniNoStr,String status);
    
    
	/**
	 * 获得相关商品数据集合
	 * @param uniNo：商品销售新表主键
	 * @status：状态类型
	 * @return
	 */
	public List<JpmProductSaleRelated> getJpmProductSaleRelatedList(String uniNo,String status);
	
	/**
	 * 获得相关商品对应的JpmProductSaleTeamType对象的集合
	 * @param uniNo：商品销售新表主键
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @return:uniNo对应的商品JpmProductSaleTeamType对象的List集合
	 */
	public List<JpmProductSaleTeamType> getRelatedTeamTypeList(String uniNo,String teamCode,String orderType);
	
	/**
	 * 获得商品的图片集合
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	 * 注释：如果imageType为空，则返回指定商品uniNo所有的图片的集合
	 */
	public List<JpmProductSaleImage> getJpmProductSaleImageList(String uniNo,String imageType);
	
	/**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	 * 注释：参数查询条件teamCode,orderType,companyCode如果为空，则传递null
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String uniNo,String teamCode,String orderType,String companyCode);
	
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String productNo,String productName,String productCategory,String teamCode,String orderType,String companyCode);
	
	/**
	 * 获得商品的团队类型Map
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productCategoryStr：商品系列
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr);
						   
	
	/**
	 * 获得指定的商品团队类型对象
	 * @param companyCode:分区
	 * @param productNo:商品编码
	 * @param orderType:订单类型
	 * @param teamCode:团队类型
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode);
	
	
	/**
	 * 获得指定的商品团队类型对象
	 * @param companyCode:分区
	 * @param productNo:商品编码
	 * @param orderType:订单类型
	 * @param teamCode:团队类型
	 * @param stauts:商品状态
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode,String status);

		
	/**
	 * 通过主键获得JpmProductSaleTeamType对象
	 * @param pttId
	 * @return
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId);
	 
	
	/**
	 * 通过商品编号productNo商品团队类型对应的主键ptt_id
	 * @param：productNo 商品编码
	 * @param：teamCode  团队编码
	 * @param：orderType 订单类型
	 * @return：商品团队类型对应的主键ptt_id
	 */
	public String getJpmProductSaleTeamTypePttid(String productNo,String teamCode,String orderType);
	
	
	/*************************************2.物流信息**************************************/
	/**
	 * 通过主键获得物流发货信息，目前只查询4个字段：发货仓库、物流公司、运单号、发货时间
	 * @param pttId
	 * @return
	 */
	public HashMap<String,Object> getPdSendinfo(String siNo);
	
	/**
	 * 通过物流公司编号获得物流信息：目前返回的只有网站信息
	 * @param shno
	 * @return
	 */
	public HashMap<String,String> getShnoInfo();
	
	/**
	 * 通过物流公司编号获得物流网站查询网址
	 * @param shno：物流公司编号
	 * @return：物流公司查询网址
	 */
	public String getShnoUrl(String shno);
	/**
	 * 商品名称
	 * @param companyCode
	 * @param productNo
	 * @return
	 */
	 public JpmProductSaleNew getPmProductSaleNew(String companyCode, String productNo);
	 
	/**
     * Add By WuCF 20141110
     * 得到指定主键集合的商品编码集合
     * @param uniNoStr:
     * @return：商品编码集合
     */
    public String getProductNos(String uniNoStr);
}

