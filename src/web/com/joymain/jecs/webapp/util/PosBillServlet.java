/*
 * 文件名：  PosBillServlet.java 2014-8-12
 * 版权：    Copyright 2000-2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2014-8-12
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.jecs.webapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.service.JfiChinapayPosLogManager;
import com.joymain.jecs.fi.service.impl.JfiChinapayPosLogManagerImpl;
import com.joymain.jecs.fi.webapp.action.Jfi99billLogController;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.util.xmlParse.PosXmlParse;

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
public class PosBillServlet extends HttpServlet
{
    private final Log log = LogFactory.getLog(PosBillServlet.class);
    
    //00
    private final static String SUCCESS = "00";
    
    //02
    private final static String FAIL = "02";
    
    //01
    private final static String RESEND = "01";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
        IOException
    {
        // TODO Auto-generated method stub
//        super.doGet(req, resp);
        this.doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        log.info("PosBillServlet doPost");
//        super.doPost(req, resp);
//        XMLWriter writer = null;
        PrintWriter out = resp.getWriter();
        // 获取传入的参数
//        String xml = new String(req.getParameter("notifyData").getBytes("GBK"));
        InputStream in = req.getInputStream();
        InputStreamReader strInStream = new InputStreamReader(in, "GBK");
        SAXReader saxReader = new SAXReader();
        Document document;
        String xml = null;
        try
        {
            document = saxReader.read(strInStream);
            xml = document.asXML();
        }
        catch (DocumentException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        log.info("PosBillServlet doPost reqXml is :"+ xml);
        // 传入解析xml
        PosXmlParse parse = new PosXmlParse();
        JfiChinapayPosLog jfiChinapayPosLog = null;
        try
        {
            jfiChinapayPosLog = parse.readStringXml(xml);
        }
        catch (AppException e1)
        {
            // 返回失败响应 
            Document reXml = parse.createReXml(FAIL);
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setEncoding("GBK");
        }
        
        if (null != jfiChinapayPosLog)
        {
            // 调用入库操作，并执行扣款
            ServletContext application;
            WebApplicationContext wac;
            application = getServletContext();
            wac = WebApplicationContextUtils.getWebApplicationContext(application);// 获取spring的context
            JfiChinapayPosLogManager jfiChinapayPosLogManager =
                (JfiChinapayPosLogManager) wac.getBean("jfiChinapayPosLogManager");
            
            try
            {
                jfiChinapayPosLogManager.saveJfiPayData(jfiChinapayPosLog);
                log.info("PosBillServlet doPost sec");
            }
            catch (AppException e)
            {
                // 返回失败响应 
                Document reXml = parse.createReXml(FAIL);
                OutputFormat format = OutputFormat.createCompactFormat();
                format.setEncoding("GBK");
                out.write(reXml.asXML());    
                return;
            }
            catch (Exception e)
            {
                // 返回失败响应 
                Document reXml = parse.createReXml(RESEND);
                OutputFormat format = OutputFormat.createCompactFormat();
                format.setEncoding("GBK");
                out.write(reXml.asXML());    
                return;
            }
            
            // 返回成功响应
            Document reXml = parse.createReXml(SUCCESS);
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setEncoding("GBK");
            out.write(reXml.asXML());      
            return;
        }
        else
        {
            // 返回失败响应
            Document reXml = parse.createReXml(FAIL);
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setEncoding("GBK");
            out.write(reXml.asXML());    
            return;
//            writer = new XMLWriter(resp.getOutputStream(), format);
//            writer.write(reXml);
//            writer.close();
//            if (writer != null)
//            {
//                writer.close();
//            }
        }
    }
    
}
