
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdSendInfoDao extends Dao {
 
    /**
     * Retrieves all of the pdSendInfos
     */ 
    public List getPdSendInfos(PdSendInfo pdSendInfo);

    /**
     * Gets pdSendInfo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param siNo the pdSendInfo's siNo
     * @return pdSendInfo populated pdSendInfo object
     */
    public PdSendInfo getPdSendInfo(final String siNo);

    /**
     * Saves a pdSendInfo's information
     * @param pdSendInfo the object to be saved
     */    
    public void savePdSendInfo(PdSendInfo pdSendInfo);

    /**
     * Removes a pdSendInfo from the database by siNo
     * @param siNo the pdSendInfo's siNo
     */
    public void removePdSendInfo(final String siNo);
    //added for getPdSendInfosByCrm
    public List getPdSendInfosByCrm(CommonRecord crm, Pager pager);

	public List getShippingReportList(CommonRecord crm);
	
	public List getSunShipmentsByCrm(CommonRecord crm, Pager pager);
	public Long getUnsendByProductNo(String companyCode,String productNo,String warehouseNo);

	/**
	 * 判断物流公司是否存在
	 * @param listCode:list编码
	 * @param valueCode：list值
	 * @return
	 */
	public List jsysListValues(String listCode,String valueCode);
	
	public List getAlCitysByCountry(String companyCode);
	
	/**
	 * 根据国家获取对应的省/州列表
	 * @param countryCode
	 * @return
	 */
	public List getAlStateProvincesByCountry(final String countryCode);
	
	public List getCompanyCode();
	
	/**
	 * 修改指定区域、商品编号的库存警戒量
	 * @param companyCode：区域
	 * @param productNo：商品编码
	 * @param storageCordon：警戒库存量
	 * @return
	 */
	public int updateStorageCordon(String companyCode,String productNo,String storageCordon);
	
	/**
     * Gets alCity's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param cityId the alCity's cityId
     * @return alCity populated alCity object
     */
    public AlCity getAlCity(final Long cityId);
    
    /**
	 * 查询发货单信息
	 * @return
	 */
	public List getPdSendInfos();
	
	/**
	 * 查询发货单详细信息
	 * @return
	 */
	public List getPdSendInfoDetails();
	
	/**
	 * Add By WuCF
	 * 定时任务，自动处理暂不发货发送短信
	 * @return
	 */
	public List checkSmssendTransferTask();
	
	/**
	 * 1.中策加入人数统计
	 * @param crm
	 * @return
	 */
	public List getZhongcenumByCrm(CommonRecord crm);
	
	/**
	 * 2.日业绩统计 
	 * @param crm
	 * @return
	 */
	public List getDayPerformanceByCrm(CommonRecord crm);
	
	/**
	 * 3.决策委省份统计
	 * @param crm
	 * @return
	 */
	public List getProvinceByCrm(CommonRecord crm);
	
	/**
	 * 查询临时表中的数据
	 * @param crm
	 * @return
	 */
	public List getZcwUsers(CommonRecord crm);
	
	/**
	 * 2.1.领导人网体业绩
	 * @param crm
	 * @return
	 */
	public List getNetLeadByCrm(CommonRecord crm);
	
	/**
	 * 2.2.领导人区域业绩
	 * @param crm
	 * @return
	 */
	public List getAreaLeadByCrm(CommonRecord crm);
	
	/**
	 * 2.3.领导人推荐网体加入人数
	 * @param crm
	 * @return
	 */
	public List getRecommendLeadByCrm(CommonRecord crm);
	
	/**
	 * 2.4.  30万大单数据
	 * @param crm
	 * @return
	 */
	public List getbigJpomemberorderByCrm(CommonRecord crm);
	
	/**
	 * 2.5.颐萃产品统计
	 * @param crm
	 * @return
	 */
	public List getHuicuiProductByCrm(CommonRecord crm);
	
	/**
	 * 2.6.云南团队保养贴
	 * @param crm
	 * @return
	 */
	public List getYunnanchongxiaoByCrm(CommonRecord crm);
	
	/**
	 * 2.7.道和坛酒数订单
	 * @param crm
	 * @return
	 */
	public List getDaoheWineJpomemberorderByCrm(CommonRecord crm);
	
	/**
	 * 2.8.道和坛酒数退单数据
	 * @param crm
	 * @return
	 */
	public List getDaoheWineJprrefundByCrm(CommonRecord crm);
	
	/**
	 * 2.9.网络费物流费
	 * @param crm
	 * @return
	 */
	public List getJponetfeeByCrm(CommonRecord crm);
	
	/**
	 * 获取业体领导人安置网体首单5500PV及以上业绩
	 * @param crm
	 * @return
	 */
	public List getShiyeTiLingDaoShouDanByCrm(CommonRecord crm);
	
	
	/**
	 * 根据订单号查询该订单下所有的发货单
	 * @author gw 2015-01-27
	 * @param memberOrderNo
	 * @return list
	 */
	public List<PdSendInfo> getPdSendInfoList(String memberOrderNo);
	
	/**
	 * 根据订单号和商品编号查询发货单号
	 * @author gw 2015-02-01
	 * @param memberOrderNo
	 * @param productNo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoForOrderNoAndProductNo(String memberOrderNo,String productNo);

	/**
	 * 根据发货单查询发货单号
	 * @author gw 2015-04-15
	 * @param siNo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoForSiNo(String siNo);

	/**
	 * 获取数据库中的pdSendInfo发货单对象
	 * @author fu  2015-09-16
	 * @param pdSendInfo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoDBA(PdSendInfo pdSendInfo);

	 /**
	  * 防止LO单接口和发货单发货确认菜单扣两次库存,所以先去数据库中查询发货单的状态
	  * @author fu 2015-09-20
	  * @param pdSendInfo
	  * @return boolean
	  */
	public boolean getPdSendInfoOrderFlagDBA(PdSendInfo pdSendInfo);
	
	/**
	 * 确认正常发货
	 * @author fu 2015-10-22
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:成功  false:失败
	 */
	public boolean updateShipType(String siNo);

	/**
	 * 加急发货
	 * @author fu 2015-10-19
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:成功  false:失败
	 */
	public boolean updateHurryFlag(String siNo);

	/**
	 * 发货退回订单入库生成发货单之前首先判断该单有没有发货单生成
	 * @author modify fu 2015-12-26 
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:发货退回单之前没有生成发货单  false:发货退回单已经生成了发货单
	 */
	public boolean getPdSendInfoOrderForOrderNo(String rpNo);

	/**
	 * 发货退回订单入库是否只生成一张发货单
	 * @author modify fu 2015-12-26 
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:发货退回单之前有且仅有一张发货单  false:发货退回单已经生成多张发货单
	 *//*
	public boolean getPdSendInfoForOrderNo(String rpNo);*/
	
	/**
	 * @author fu 2016-06-24 将WMS那边已撤单的发货单设为发货作废
	 * @param siNo
	 * @return 
	 */
	public void reSetPdSendInfoShipTypeFour(String siNo);
	
	/**
	 * @author WuCF 2016-09-05 获得需要重发的日志的logId集合
	 * @param siNo
	 * @return 
	 */
	public List<Map<String,Object>> geetJocsInterfaceRetranLogids();
	
	/**
	 * @author fu 2016-09-06 获取发货单的发货状态
	 * @param siNo
	 * @return orderFlag
	 */
	public String getPdSendInfoOrderFlag(String siNo);
	
	/**
	 * @author fu 2016-09-06 更新发货单的条形码的信息
	 * @param siNo 发货单号
	 * @param barCode 条形码
	 * @return 
	 */
	public void reSetPdSendInfoBarCode(String siNo,String barCode);

	//add by lihao ,发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。
	public List getPdSendInfosReport(CommonRecord crm, Pager pager);
	
}

