/**
 * 项目名：jecs-base
 * 包名：com.joymain.jecs.util.wine
 * 文件名：WineInterfaceUtil.java
 * 版本信息：@version 1.0
 * 日期：2013-12-2-下午5:59:53
 * Copyright 2013 sbChina Tech. Co. Ltd. All Rights Reserved.  
 */
package com.joymain.jecs.util.wine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmWineOrderInterface;
import com.joymain.jecs.pm.model.JpmWineOrderListInterface;
import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.pm.service.JpmWineOrderInterfaceManager;
import com.joymain.jecs.pm.service.JpmWineSettingInfManager;
import com.joymain.jecs.util.WebContextFactoryUtil;
import com.joymain.jecs.util.httpclient.HttpClientUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.xmlbeantransfer.XmlBeanTransferUtil;

/**
 * 酒业相关的方法
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-2 下午5:59:53
 * 
 */
public class WineInterfaceUtil {

    private volatile transient int orderFaultCount = 0;

    private volatile transient int settingFaultCount = 0;

    private String settingUrl = Constants.sysConfigMap.get("AA").get("erp.wine.setting.interface.url");

    private String billUrl = Constants.sysConfigMap.get("AA").get("erp.wine.bill.interface.url");

    private String tryTime = Constants.sysConfigMap.get("AA").get("erp.wine.request.fault.trytime");

    private String tryTimeSplit = Constants.sysConfigMap.get("AA").get("erp.wine.request.fault.trytimespite");

    /**
     * @param jpmWineOrderInterface
     *            保存（采用双向关联级联保存）并发送发货单到ERP
     * 
     * @return 0:成功，非0：失效
     */
    public int saveAndSendOrder(JpmWineOrderInterface jpmWineOrderInterface, int status) {
        initJpmWineOrderInterface(jpmWineOrderInterface);
        JpmWineOrderInterfaceManager manager = (JpmWineOrderInterfaceManager) WebContextFactoryUtil.getBean("jpmWineOrderInterfaceManager");
        manager.saveJpmWineOrderInterface(jpmWineOrderInterface);
        int ret = sendOrder2ERP(jpmWineOrderInterface, status);
        return ret;
    }

    /**
     * 
     * 发送发货单到ERP，同时记录ERP返回的响应
     * 
     * @param jpmWineOrderInterface ：发货单信息
     * @param status 表示失败需要多次尝试，非0：只发送一次请求，失败也不再发
     * @return
     */
    public int sendOrder2ERP(final JpmWineOrderInterface jpmWineOrderInterface, int status) {
        int ret = 1;

        //String url = "http://127.0.0.1:8090/test/xml.xml";
        //String url = "http://192.168.20.50/service/XChangeServlet?account=design&receiver=103";
        //final int tryTime = 5;
        //final int tryTimeSplit = 30;
        //System.out.println("orderFaultCount:" + orderFaultCount);
        JpmWineOrderInterfaceManager manager = (JpmWineOrderInterfaceManager) WebContextFactoryUtil.getBean("jpmWineOrderInterfaceManager");
        WineOrderInterfaceReq req = initOrderReq(jpmWineOrderInterface);
        String xml = new XmlBeanTransferUtil<WineOrderInterfaceReq>(WineOrderInterfaceReq.class).bean2Xml(req);
        String resStr = HttpClientUtil.getInstance().postXML(billUrl, xml, null);
        WineRes wineRes = new XmlBeanTransferUtil<WineRes>(WineRes.class).Xml2Bean(resStr);
        if (null != wineRes) {
            String resultCode = wineRes.getSendresult().getResultcode();
            String resultDescription = wineRes.getSendresult().getResultdescription();
            jpmWineOrderInterface.setResultCode(Integer.parseInt(resultCode));
            jpmWineOrderInterface.setResultDescription(StringUtils.substring(resultDescription, 0, 150));

            if ("1".equals(wineRes.getSendresult().getResultcode())) {
                jpmWineOrderInterface.setStatus(0);//0为成功
                ret = 0;
            } else {
                jpmWineOrderInterface.setStatus(1);//1未成功
                /*if (status == 0 && orderFaultCount++ < StringUtil.formatInt(tryTime, 5)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(StringUtil.formatInt(tryTimeSplit, 30) * 1000);
                                sendOrder2ERP(jpmWineOrderInterface, 0);
                            } catch (Exception e) {

                            }

                        }
                    }).start();
                }*/
            }
            manager.saveJpmWineOrderInterface(jpmWineOrderInterface);//更新状态
        }
        return ret;
    }

    /**
     * 
     * 配置单保存（采用双向关联级联保存）并把配置单发送到ERP，同时保存返回结果
     * 
     * @param jpmWineSettingInf
     * @return
     */
    public int saveAndSendSettingBill(JpmWineSettingInf jpmWineSettingInf, int status) {
        initJpmWineSettingInf(jpmWineSettingInf);
        JpmWineSettingInfManager manager = (JpmWineSettingInfManager) WebContextFactoryUtil.getBean("jpmWineSettingInfManager");
        manager.saveJpmWineSettingInf(jpmWineSettingInf);
        int ret = sendSettingToERP(jpmWineSettingInf, status);
        return ret;
    }

    /**
     * 
     * 把配置单发送到ERP
     * 
     * @param jpmWineSettingInf ：配置单信息
     * @param status 0:表示失败需要多次尝试，非0：只发送一次请求，失败也不再发
     * @return
     */
    public int sendSettingToERP(final JpmWineSettingInf jpmWineSettingInf, int status) {
        int ret = 1;
        //String url = "http://127.0.0.1:8090/test/xml.xml";
        //final int tryTime = 5;
        //final int tryTimeSplit = 30;

        JpmWineSettingInfManager manager = (JpmWineSettingInfManager) WebContextFactoryUtil.getBean("jpmWineSettingInfManager");
        WineSettingInterfaceReq req = initSettingBillReq(jpmWineSettingInf);
        String xml = new XmlBeanTransferUtil<WineSettingInterfaceReq>(WineSettingInterfaceReq.class).bean2Xml(req);
        String resStr = HttpClientUtil.getInstance().postXML(settingUrl, xml, null);
        WineRes wineRes = new XmlBeanTransferUtil<WineRes>(WineRes.class).Xml2Bean(resStr);
        if (null != wineRes) {
            String resultCode = wineRes.getSendresult().getResultcode();
            String resultDescription = wineRes.getSendresult().getResultdescription();
            jpmWineSettingInf.setResultcode(Integer.parseInt(resultCode));
            jpmWineSettingInf.setResultdescription(StringUtils.substring(resultDescription, 0, 150));

            if ("1".equals(wineRes.getSendresult().getResultcode())) {
                jpmWineSettingInf.setStatus(0);
                ret = 0;
            } else {
                jpmWineSettingInf.setStatus(1);//未成功
                /*if (status == 0 && settingFaultCount++ < StringUtil.formatInt(tryTime, 5)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(StringUtil.formatInt(tryTimeSplit, 30) * 1000);
                                sendSettingToERP(jpmWineSettingInf, 0);
                            } catch (Exception e) {

                            }

                        }
                    }).start();
                }*/
            }
            manager.saveJpmWineSettingInf(jpmWineSettingInf);//更新状态
        }
        return ret;
    }

    /**
     * 
     * 修正以下三个字段
     * 
     * @param jpmWineOrderInterface
     */
    private void initJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface) {
        jpmWineOrderInterface.setStatus(1);//表示未发送或者发送失败
        jpmWineOrderInterface.setResultCode(null);
        jpmWineOrderInterface.setResultDescription("");
    }

    private void initJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf) {
        jpmWineSettingInf.setStatus(1);//表示未发送或者发送失败
        jpmWineSettingInf.setResultcode(null);
        jpmWineSettingInf.setResultdescription("");
    }

    /**
     * 
     * 根据发货单构造请求体的对象
     * 
     * @param order 发货单
     * @return
     */
    private WineOrderInterfaceReq initOrderReq(JpmWineOrderInterface order) {

        So_order_head so_order_head = initOrderHead(order);
        CustDocBodyVO custDocBodyVO = initOrderCustDocBodyVO(order);
        List<SaleOrderBodyVOEntry> saleOrderBodyVOList = initOrderSaleOrderBodyList(order);

        So_order so_order = new So_order();

        so_order.setOrder_id(order.getMemberOrderNo());

        so_order.setSo_order_head(so_order_head);
        so_order.setSaleOrderBodyVOList(saleOrderBodyVOList);
        so_order.setCustDocBodyVO(custDocBodyVO);

        WineOrderInterfaceReq req = new WineOrderInterfaceReq();
        req.setSo_order(so_order);
        return req;
    }

    /**
     * 
     * 构造头信息
     * 
     * @param order
     * @return
     */
    private So_order_head initOrderHead(JpmWineOrderInterface order) {

        So_order_head so_order_head = new So_order_head();

        so_order_head.setDbilldate(order.getDbillDate());
        so_order_head.setCustomerCode(order.getUserCode());
        so_order_head.setCcustomerid(order.getUserCode());
        //so_order_head.setCoperatorid(order.getCoperatorid());
        so_order_head.setCsalecorpid(order.getUserCode());

        so_order_head.setDmakedate(order.getDbillDate());
        so_order_head.setMembercode(order.getUserCode());
        so_order_head.setMembername(order.getMembername());
        so_order_head.setMemberphone(order.getMemberphone());
        so_order_head.setMemberid(order.getMemberid());

        so_order_head.setCusprovince(order.getCusprovince());
        so_order_head.setCuscity(order.getCuscity());
        so_order_head.setAreaclcode(order.getAreaclCode());
        so_order_head.setMemberaddress(order.getMemberaddress());
        so_order_head.setPostalcode(order.getPostalcode());
        so_order_head.setMembersex(order.getMembersex());

        so_order_head.setOrdercode(order.getMemberOrderNo());
        so_order_head.setBillcode(order.getBillCode());

        return so_order_head;
    }

    /**
     * 
     * 收货人信息
     * 
     * @param order
     * @return
     */
    private CustDocBodyVO initOrderCustDocBodyVO(JpmWineOrderInterface order) {

        CustDocBodyVO custDocBodyVO = new CustDocBodyVO();
        CustDocBodyVOEntry custDocBodyVOEntry = new CustDocBodyVOEntry();

        custDocBodyVOEntry.setAreaclcode(order.getReceiverAreaclcode());//区
        custDocBodyVOEntry.setCuscity(order.getReceiverCusCity());//市
        custDocBodyVOEntry.setCusprovince(order.getReceiverCusProvince());//省
        custDocBodyVOEntry.setPostalcode(order.getReceiverPostalCode());//邮编
        custDocBodyVOEntry.setReceiveraddress(order.getReceiverAddress());//收货地址

        custDocBodyVOEntry.setReceivercode(order.getReceiverCode());//收货人编号
        custDocBodyVOEntry.setCuscity(order.getReceiverCusCity());// 市
        custDocBodyVOEntry.setCusprovince(order.getReceiverCusProvince());// 省
        custDocBodyVOEntry.setPostalcode(order.getReceiverPostalCode());// 邮编
        custDocBodyVOEntry.setReceiveraddress(order.getReceiverAddress());// 收货地址
        
        custDocBodyVOEntry.setReceiverid(order.getReceiverId());//
        custDocBodyVOEntry.setReceivername(order.getReceiverName());//收货人名
        custDocBodyVOEntry.setReceiverphone(order.getReceiverPhone());//收货电话
        custDocBodyVOEntry.setReceiversex(order.getReceiverSex());//性别.

        custDocBodyVO.setEntry(custDocBodyVOEntry);
        return custDocBodyVO;
    }

    /**
     * 
     * 商品
     * 
     * @param order
     * @return
     */
    private List<SaleOrderBodyVOEntry> initOrderSaleOrderBodyList(JpmWineOrderInterface order) {
        List<SaleOrderBodyVOEntry> saleOrderBodyVOList = new ArrayList<SaleOrderBodyVOEntry>();
        Set<JpmWineOrderListInterface> set = order.getJpmWineOrderListInterfaceSet();
        Iterator<JpmWineOrderListInterface> it = set.iterator();
        int index = 0;
        while (it.hasNext()) {
            JpmWineOrderListInterface o = it.next();
            SaleOrderBodyVOEntry entry = new SaleOrderBodyVOEntry();

            entry.setId(new Integer(++index).toString());
            entry.setCinventoryid(o.getProductId().toString());//商品编码
            entry.setNnumber(o.getQty().toString());//数量
            entry.setCreceiptcorpid(order.getBillCode());//发货单号
            entry.setVreceiveaddress(o.getVreceiveaddress());//收货地址

            entry.setDconsigndate(order.getDbillDate());//发货日期
            entry.setNoriginalcurtaxprice(o.getPrice().toString());//单价
            entry.setInvpv(o.getInvpv().toString());//商品PV

            saleOrderBodyVOList.add(entry);
        }
        return saleOrderBodyVOList;
    }

    /**
     * 
     * 根据配置单构造请求体的对象
     * 
     * @param setting 配置单
     * @return
     */
    private WineSettingInterfaceReq initSettingBillReq(JpmWineSettingInf setting) {

        WineSettingInterfaceReq req = new WineSettingInterfaceReq();
        Bill bill = new Bill();
        bill.setId(setting.getSettingId().toString());

        Billhead billhead = new Billhead();
        billhead.setCfgnum(setting.getProductQty().toString());
        billhead.setInvcode(setting.getProductId().toString());
        billhead.setInvname(setting.getProductName());
        //billhead.setPk_measdoc(setting.getUnitNo());
        bill.setBillhead(billhead);

        List<Billbody> list = new ArrayList<Billbody>();
        Set<JpmWineSettingListInf> set = setting.getJpmWineSettingListInfSet();
        Iterator<JpmWineSettingListInf> it = set.iterator();
        int index = 0;
        while (it.hasNext()) {
            JpmWineSettingListInf o = it.next();
            Billbody billbody = new Billbody();

            billbody.setNrowid(new Integer(++index).toString());
            billbody.setPk_invbasid(o.getMaterialNo());
            billbody.setSl(o.getQty().toString());
            billbody.setSdate(o.getSdate());
            billbody.setEdate(o.getEdate());
            billbody.setMemo(o.getMemo());

            billbody.setLossRatio(new Float(o.getLossRatio()).toString());
            billbody.setIsMainMaterial(o.getIsMainMaterial());
            billbody.setIsSendMaterial(o.getIsSendMaterial());
            billbody.setIsDelegateOut(o.getIsDelegateOut());
            billbody.setIsFeatureItem(o.getIsFeatureItem());
            billbody.setPicName(o.getPicName());

            list.add(billbody);
        }
        bill.setBillbody(list);

        req.setBill(bill);
        return req;
    }
}
