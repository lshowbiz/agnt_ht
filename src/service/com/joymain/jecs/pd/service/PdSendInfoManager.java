
package com.joymain.jecs.pd.service;
 
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdSendInfoManager extends Manager {
	
	
	public List getShippingReportListByCompany(CommonRecord crm);
	public List getShippingReportList(CommonRecord crm);
	public void doWhileVoidOrder(JpoMemberOrder order);
	public void doWhileOrderConfirmed(JpoMemberOrder order);
	public void doWhileOrderConfirmed(PdReturnPurchase order);
	
	public String autoSaveShipInfo(JpoMemberOrder order);
	public String autoCreatePdSendInfo(JpoMemberOrder order);
    /**
     * Retrieves all of the pdSendInfos
     */
    public List getPdSendInfos(PdSendInfo pdSendInfo);

    /**
     * Gets pdSendInfo's information based on siNo.
     * @param siNo the pdSendInfo's siNo
     * @return pdSendInfo populated pdSendInfo object
     */
    public PdSendInfo getPdSendInfo(final String siNo);

    /**
     * Saves a pdSendInfo's information
     * @param pdSendInfo the object to be saved
     */
    public void savePdSendInfo(PdSendInfo pdSendInfo);
    public void checkPdSendInfo(PdSendInfo pdSendInfo)throws Exception;
    /**
     * Removes a pdSendInfo from the database by siNo
     * @param siNo the pdSendInfo's siNo
     */
    public void removePdSendInfo(final String siNo);
    //added for getPdSendInfosByCrm
    public List getPdSendInfosByCrm(CommonRecord crm, Pager pager);
	public void confirmSendInfo(PdSendInfo pdSendInfo, SysUser sessionLogin)throws Exception;
	public void confirmSendInfo(PdSendInfo pdSendInfo, SysUser sessionLogin,HttpServletRequest request)throws Exception;
	public void doWhileOrderConfirmed(PdExchangeOrder pdExchangeOrder)throws Exception;
	public List getSunShipmentsByCrm(CommonRecord crm, Pager pager);
	
	public BigDecimal getUPSRateByOrder(JpoMemberOrder order)throws Exception;
	public void upsConfirmRequest(PdSendInfo pdSendInfo)throws Exception;
	public void upsVoidRequest(PdSendInfo pdSendInfo)throws Exception;
	public void upsAcceptRequest(PdSendInfo pdSendInfo)throws Exception;
	public PdSendInfo getPdSendInfoByOrderNo(String orderNo);
	/**@author 罗婷
	 * 物流soap接口
	 * @param pdsendInfo
	 */
	public void getPdsendInfoInterface(PdSendInfo pdsendInfo);
	/**@author 罗婷
	 * 订单审核后发送邮件
	 */
	public void getSendEmail(JpoMemberOrder order);

	public void excuteShipTask();

	
	/**
	 * 判断物流公司是否存在
	 * @param listCode:list编码
	 * @param valueCode：list值
	 * @return
	 */
	public List jsysListValues(String listCode,String valueCode);
	
	public List getCompanyCode();
	
	public Map<String,String> getAlCityMap(String companyCode);
	
	/**
	 * 根据国家获取对应的省/州Map<code,name>
	 * @param countryCode
	 */
	public Map getAlStateProvincesMapByCountry(final String countryCode);
	
	/**
	 * 修改指定区域、商品编号的库存警戒量
	 * @param companyCode：区域
	 * @param productNo：商品编码
	 * @param storageCordon：警戒库存量
	 * @return
	 */
	public int updateStorageCordon(String companyCode,String productNo,String storageCordon);
	
	/**
     * Gets alCity's information based on cityId.
     * @param cityId the alCity's cityId
     * @return alCity populated alCity object
     */
	public AlCity getAlCity(final Long cityId);
	
	/**
	 * 查询发货单信息
	 * @return
	 */
	public String getPdSendInfoJsons();
	
	/**
	 * 查询发货单详细信息
	 * @return
	 */
	public String getPdSendInfoDetailJsons();
	
	/**
	 * Add By WuCF
	 * 定时任务，自动处理暂不发货发送短信
	 * @return
	 */
	public void checkSmssendTransferTask();

//	public void updateHarryFlag(String flag);
	
	/**
	 * 查找发货单
	 */
	public List<PdSendInfo> getPdSendInfoList(String memberOrderNo);

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
	 * 换货单和发货退回单直接关联的发货单状态接口
	 * @author fx  2015-08-14 
	 * @param jsonString
	 * @return rspEntity
	 */
	public RspEntity reSetPdSendInfoStatus(String jsonString);
	
	/**
	 * 发货单暂不发货/暂停发货接口
	 * @author fx 2015-08-14
	 * @param jsonString
	 * @param shipType:发货方式:0正常发货;2暂不发货;3暂停发货
	 * @return string
	 */
	public String reSetPdSendInfoStopOrNo(String siNo,String shipType) throws Exception;
	
	/**
     * 获取发货单编辑接口的对象
     * @author fu 2015-10-22 
     * @param pdSendInfo
     * @return string 
     */
	public String getPdSendInfoEditInterfaceResult(PdSendInfo pdSendInfo);
	
	/**
     * 确认正常发货
     * @author fu 20151022 
     * @param pdSendInfo
     * @return string 
     */
	public String updateShipType(String siNo);
	
	/**
     * 加急发货
     * @author fu 20151119 
     * @param pdSendInfo
	 * return: 
     */
	public String updateHurryFlag(String siNo);
	
	
	/**
	 * 发货退回订单入库是否只生成一张发货单
	 * @author modify fu 2015-12-26 
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:发货退回单之前有且仅有一张发货单  false:发货退回单已经生成多张发货单
	 *//*
	public boolean getPdSendInfoForOrderNo(String rpNo);*/
	
	/**
	 * 根据发货单查询发货单号
	 * @author gw 2016-02-16
	 * @param siNo
	 * @return pdSendInfo
	 */
	public PdSendInfo getPdSendInfoForSiNo(String siNo);
	
	/**
	 * @author fu 2016-06-24 将WMS那边已撤单的发货单设为发货作废
	 * @param siNo
	 * @return 
	 */
	public void reSetPdSendInfoShipTypeFour(String siNo);
	
	/**
	 * @author WuCF 20160905 订单发送重发日志接口数据
	 * @return 
	 */
	public void reJocsInterfaceTransmission();
}

