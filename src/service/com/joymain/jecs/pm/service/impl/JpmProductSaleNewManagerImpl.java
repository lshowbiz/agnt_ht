
package com.joymain.jecs.pm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.pm.dao.JpmProductSaleNewDao;
import com.joymain.jecs.pm.model.JpmProductSale;
import com.joymain.jecs.pm.model.JpmProductSaleImage;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmProductSaleNewManagerImpl extends BaseManager implements JpmProductSaleNewManager {
    private JpmProductSaleNewDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmProductSaleNewDao(JpmProductSaleNewDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleNewManager#getJpmProductSaleNews(com.joymain.jecs.pm.model.JpmProductSaleNew)
     */
    public List getJpmProductSaleNews(final JpmProductSaleNew jpmProductSaleNew) {
        return dao.getJpmProductSaleNews(jpmProductSaleNew);
    }
    
    /**
     * Retrieves all of the jpmProductSaleNews
     * Add By WuCF 20140306 新增商品团队类型，选择指定团队&订单类型未新增的商品数据
     */
	public List getNotExistsJpmProductSaleNews(CommonRecord crm) {
		return dao.getNotExistsJpmProductSaleNews(crm);
	}
    
    public Map getCompanyProductMap(String companyCode) {
		// TODO Auto-generated method stub
		Map retMap = new HashMap();
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("companyCode", companyCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.fillInStackTrace());
		}
		List list = dao.getJpmProductSalesByCrm(crm, null);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JpmProductSaleNew pmProductSale = (JpmProductSaleNew) list.get(i);
//				retMap.put(pmProductSale.getJpmProduct().getProductNo(),
//						pmProductSale.getProductName());
				retMap.put(pmProductSale.getJpmProduct().getProductNo(),
						pmProductSale);
			}
		}
		return retMap;
	}

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleNewManager#getJpmProductSaleNew(String uniNo)
     */
    public JpmProductSaleNew getJpmProductSaleNew(final String uniNo) {
        return dao.getJpmProductSaleNew(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleNewManager#saveJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew)
     */
    public void saveJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
        dao.saveJpmProductSaleNew(jpmProductSaleNew);
    }

    /**
     * Add By WuCF 20140311 发送短信记录保存
     */
    public void saveJpmSmssendInfo(JpmSmssendInfo jpmSmssendInfo){
    	 dao.saveJpmSmssendInfo(jpmSmssendInfo);
    }
    
    /**
     * @see com.joymain.jecs.pm.service.JpmProductSaleNewManager#removeJpmProductSaleNew(String uniNo)
     */
    public void removeJpmProductSaleNew(final String uniNo) {
        dao.removeJpmProductSaleNew(new Long(uniNo));
    }
    //added for getJpmProductSaleNewsByCrm
    public List getJpmProductSaleNewsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmProductSaleNewsByCrm(crm,pager);
    }
    
    
    public boolean existProductNo(String companyCode, String productNo) {
		// TODO Auto-generated method stub
		JpmProductSaleNew pmProductSaleNew = dao.getPmProductSaleNew(companyCode,
				productNo);
		if (pmProductSaleNew == null) {
			return false;
		} else {
			return true;
		}

	}
    
    /**
     * 批量审核商品
     * @param uniNoStr:商品编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditProductNews(String uniNoStr,String status){
	return dao.batchAuditProductNews(uniNoStr,status);
    }

	 
    
	/**
	 * 获得相关商品数据集合
	 * @param uniNo：商品销售新表主键
	 * @status：状态类型
	 * @return
	 */
	public List<JpmProductSaleRelated> getJpmProductSaleRelatedList(String uniNo,String status){
		// TODO Auto-generated method stub
		return dao.getJpmProductSaleRelatedList(uniNo,status);
	}
	
	/**
	 * 获得相关商品对应的JpmProductSaleTeamType对象的集合
	 * @param uniNo：商品销售新表主键
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @return:uniNo对应的商品JpmProductSaleTeamType对象的List集合
	 */
	public List<JpmProductSaleTeamType> getRelatedTeamTypeList(String uniNo,String teamCode,String orderType) {
		// TODO Auto-generated method stub
		return dao.getRelatedTeamTypeList(uniNo,teamCode,orderType);
	}

	/**
	 * 获得商品的图片集合
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	 * 注释：如果imageType为空，则返回指定商品uniNo所有的图片的集合
	 */
	public List<JpmProductSaleImage> getJpmProductSaleImageList(String uniNo,String imageType) {
		return dao.getJpmProductSaleImageList(uniNo,imageType);
	}
	
	/**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	 * 注释：参数查询条件teamCode,orderType,companyCode如果为空，则传递null
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String uniNo,String teamCode,String orderType,String companyCode) {
		return dao.getJpmProductSaleTeamTypeList(uniNo,teamCode,orderType,companyCode);
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String productNo,String productName,String productCategory,String teamCode,String orderType,String companyCode){
		return dao.getJpmProductSaleTeamTypeList(productNo,productName,productCategory,teamCode,orderType,companyCode);
	}
	
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
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr) {
		return dao.getJpmProductSaleTeamTypeMap(uniNo,teamCode,orderType,companyCode,productCategoryStr);
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
		return dao.getJpmProductSaleTeamType(companyCode,productNo,orderType,teamCode);
	}
	
	/**
	 * 获得指定的商品团队类型对象
	 * @param companyCode:分区
	 * @param productNo:商品编码
	 * @param orderType:订单类型
	 * @param teamCode:团队类型
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String companyCode,String productNo,String orderType,String teamCode,String status) {
		return dao.getJpmProductSaleTeamType(companyCode,productNo,orderType,teamCode,status);
	}

	/**
	 * 通过主键获得JpmProductSaleTeamType对象
	 * @param pttId
	 * @return
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId) {
		return dao.getJpmProductSaleTeamType(pttId);
	}
 

	/**
	 * 通过商品编号productNo商品团队类型对应的主键ptt_id
	 * @param：productNo 商品编码
	 * @param：teamCode  团队编码
	 * @param：orderType 订单类型
	 * @return：商品团队类型对应的主键ptt_id
	 */
	public String getJpmProductSaleTeamTypePttid(String productNo,String teamCode,String orderType) {
		return dao.getJpmProductSaleTeamTypePttid(productNo,teamCode,orderType);
	}
	
	/*************************************2.物流信息**************************************/
	/**
	 * 通过主键获得物流发货信息，目前只查询4个字段：发货仓库、物流公司、运单号、发货时间
	 * @param pttId
	 * @return
	 */
	public HashMap<String, Object> getPdSendinfo(String siNo) {
		return dao.getPdSendinfo(siNo);
	}

	

	/**
	 * 通过物流公司编号获得物流信息：目前返回的只有网站信息
	 * @param shno
	 * @return
	 */
	public HashMap<String, String> getShnoInfo() {
		return dao.getShnoInfo();
	}

	/**
	 * 通过物流公司编号获得物流网站查询网址
	 * @param shno：物流公司编号
	 * @return：物流公司查询网址
	 */
	public String getShnoUrl(String shno) {
		return dao.getShnoUrl(shno);
	}
	
	public JpmProductSaleNew getPmProductSaleNew(String companyCode, String productNo){
		return dao.getPmProductSaleNew(companyCode, productNo);
	}
	
	/**
     * Add By WuCF 20141110
     * 得到指定主键集合的商品编码集合
     * @param uniNoStr:
     * @return：商品编码集合
     */
    public String getProductNos(String uniNoStr){
    	return dao.getProductNos(uniNoStr);
    }
}
