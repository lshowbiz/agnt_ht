/*
 * 文件名：  PosXmlParse.java 2014-8-12
 * 版权：    Copyright 2000-2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2014-8-12
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.jecs.webapp.util.xmlParse;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.webapp.action.Jfi99billLogController;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.util.Base64;
import com.joymain.jecs.webapp.util.MD5;

/**
 * <p>
 * Title: (Initialize)
 * </p>
 * <p>
 * Description: (Initialize)
 * </p>
 * 
 * @author jfoy
 * @version [RBT_CNCMSXV5.0D605SP33, 2014-8-12]
 * @since SP33
 */
public class PosXmlParse
{
    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    
    public JfiChinapayPosLog readStringXml(String xml) throws AppException
    {
        log.info("PosXmlParse Method readStringXml");
        Document doc = null;
        String signData = null;
        JfiChinapayPosLog jfiChinapayPosLog = new JfiChinapayPosLog();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            
            Element recordEle = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + recordEle.getName()); // 拿到根节点的名称
            
            // 拿到stream节点下的子节点
            String action = recordEle.elementTextTrim("Action"); // 中脉科技
            jfiChinapayPosLog.setMsgCode(recordEle.elementTextTrim("MsgCode"));// 交易类型：1.扫码支付2.会员号支付3.手机号支付
            jfiChinapayPosLog.setPayAmount(BigDecimal.valueOf(Long.parseLong(recordEle.elementTextTrim("TransAmount"))));// 交易金额
            jfiChinapayPosLog.setPayAmount(jfiChinapayPosLog.getPayAmount().divide(new BigDecimal(100)));
            jfiChinapayPosLog.setTransCard(recordEle.elementTextTrim("TransCard"));// 交易卡号
            jfiChinapayPosLog.setMobileNumber(recordEle.elementTextTrim("Number"));// 会员号、手机号或扫码序列号
            jfiChinapayPosLog.setMerchantNo(recordEle.elementTextTrim("MerchantNo"));// 商户号
            jfiChinapayPosLog.setTerminalNo(recordEle.elementTextTrim("TerminalNo"));// 终端号
            jfiChinapayPosLog.setPayDealCode(recordEle.elementTextTrim("TransRefNo"));// 交易参考号
            jfiChinapayPosLog.setTransRetrievalNo(recordEle.elementTextTrim("TransRetrievalNo"));// 检索参考号
            jfiChinapayPosLog.setTransSerialNo(recordEle.elementTextTrim("TransSerialNo"));// POS终端交易流水号
            jfiChinapayPosLog.setCreateTime(Date.valueOf(formatter.format(format.parse(recordEle.elementTextTrim("TransDate")))));// 交易日期
            jfiChinapayPosLog.setCompany(recordEle.elementTextTrim("Company"));//0:畅捷入口
            jfiChinapayPosLog.setIsBack(recordEle.elementTextTrim("IsBack"));
            format = new SimpleDateFormat("yyyyMMddHHmmss");
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jfiChinapayPosLog.setTransTime(formatter.parse(formatter.format(format.parse(recordEle.elementTextTrim("TransDate")+recordEle.elementTextTrim("TransTime")))));// 交易时间
            signData = recordEle.elementTextTrim("signData");// MD5加密的签名说明
            if ("2".equals(recordEle.elementTextTrim("MsgCode")))
            {
                log.info("PosXmlParse Method readStringXml jfiChinapayPosLog msgCode is:" + recordEle.elementTextTrim("Number"));
                jfiChinapayPosLog.setUserCode(recordEle.elementTextTrim("Number"));
            }
            // 还原xml原始数据
            String initXml = delEle(doc.asXML());
            // 数据进行加密
            String enXml = encryptXml(initXml);
            log.info("PosXmlParse Method md5 xml is:" + enXml);
            log.info("PosXmlParse Method signData xml is:" + signData);
            // 原始数据加密后和signdata加密数据进行对比
            if (enXml.equals(signData))
            {
                log.info("PosXmlParse Method readStringXml signdata success");
                return jfiChinapayPosLog;
            }
            else
            {
                log.debug("PosXmlParse Method signData err !"+signData);
                throw new AppException("PosXmlParse Method signData err");
            }
        }
        catch (DocumentException e)
        {
            log.error("PosXmlParse Method readStringXml err", e);
            throw new AppException("PosXmlParse Method readStringXml err" + e);
        }
        catch (Exception e)
        {
            log.error("PosXmlParse Method readStringXml err", e);
            throw new AppException("PosXmlParse Method readStringXml err" + e);
        }
        finally
        {
            return jfiChinapayPosLog;
        }
    }
    
    /**
     * 加密
     * 
     * @param xmlStr
     * @return
     */
    public String encryptXml(String xmlStr)
    {
        log.info("PosXmlParse Method encryptXml");
        try
        {
            // 第一次加密，在没有signdata的时候进行md5
            log.info("PosXmlParse Method xml first is:" + xmlStr);
            String noSignStr = MD5.md5(xmlStr);
            log.info("PosXmlParse Method md5 xml first is:" + noSignStr);
            Document noSignDoc = DocumentHelper.parseText(xmlStr);
            noSignDoc.getRootElement().addElement("signData").setText(noSignStr);
            System.out.println(noSignDoc.asXML());
            xmlStr = noSignDoc.asXML();
            
            // 第二次加密，增加signdata的xml和中脉国际编码
            String notifyStr = xmlStr + "zmkjjsums";
            log.info("PosXmlParse Method xml sec is:" + notifyStr);
            String notifyStrBase64 = Base64.encode(notifyStr.getBytes());
            log.info("PosXmlParse Method md5 xml sec notifyStrBase64 is:" + notifyStrBase64);
            
            // 第三次加密，整段加密并存入xml
            String signStr = MD5.md5(notifyStrBase64);
            log.info("PosXmlParse Method md5 xml three is:" + signStr);
            return signStr;
        }
        catch (DocumentException e)
        {
            log.error("PosXmlParse Method encryptXml err", e);
        }
        return null;
    }
    
    /**
     * 节点删除
     * 
     * @param xml
     * @return
     */
    public String delEle(String xml)
    {
        log.info("PosXmlParse Method delEle");
        try
        {
            Document noSignDoc = DocumentHelper.parseText(xml);
            boolean bl =
                noSignDoc.getRootElement().remove(noSignDoc.getRootElement().element("signData"));
            if (true == bl)
            {
                System.out.println(noSignDoc.asXML());
                return noSignDoc.asXML();
            }
        }
        catch (DocumentException e)
        {
            log.error("PosXmlParse Method delEle err", e);
        }
        return null;
    }
    
    /**
     * 构造响应xml数据
     * 
     * @param reCode
     * @return
     */
    public Document createReXml(String reCode)
    {
        log.info("PosXmlParse Method createReXml");
        Document returnXml = DocumentHelper.createDocument();
        Element streamElement = returnXml.addElement("stream");
        streamElement.addElement("rspcode").setText(reCode);
        return returnXml;
    }
    
    public static void main(String[] args)
    {
        System.out.println(MD5.md5("12345"));
    }
}
