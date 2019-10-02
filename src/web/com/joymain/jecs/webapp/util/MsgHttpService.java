package com.joymain.jecs.webapp.util;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.service.MailStatusManager;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.json.SysParameter;
import com.joymain.jecs.webapp.util.msgUtil.BeanToMapUtil;


/**
 * <p>
 * Title: (Initialize)
 * </p>
 * <p>
 * Description: (Initialize)
 * </p>
 * 
 * @author jfoy
 * @version [RBT_CNCMSXV5.0D605SP33, 2014-11-25]
 * @since SP33
 */
public class MsgHttpService extends HttpServlet
{
    
    protected final Log log = LogFactory.getLog(getClass());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
        IOException
    {
        this.doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        log.info("MsgHttpService come in -------------------------------------");
        // 接收发送来的所有参数并进行解析和加密数据核对
        
        // 签名，详见签名算法
        // 1、将所有请求参数（key，value 为一组），对数据结构按照key的升序，重新排序
        // a)需要对null值进行过滤
        // b)需要将boolean型进行转换为1和0
        // 2、将排序后参数组合为新的字符串 （keyvaluekeyvalue 中间无分割符）
        // 3、将生成好的字符串进行MD5加密
        // 4、将新的字符串，统一为大写字符串
        // 5、将生成好的字符串加上“私钥”，形成新的字符串 （私钥为OMS给出）
        // 6、再进行一次MD5加密，得到加密后的字符串
        // 7、再统一为大写字符串，得到最终的签名字符串
        // 注意：从“系统级参数”开始到“业务级参数”全部作为请求参数.sign 可以不算入，如果放进去，开始sign = null ，也会被过滤掉的
        String sign = null;
        
        RspEntity rsp = new RspEntity();
        try
        {
            // Modify By WuCF 20141229 ，新的获取数据的方式
            // 获得数据流
            ServletInputStream in = req.getInputStream();
            // 调用私有方法获取数据内容
            String res = readLine(in);
            
            // 得到返回数据的Model形式的对象
            SysParameter sp =
                InterfaceJsonUtil.returnnoJsonToSysParameter(res, SysParameter.class, null);
            // 解析json字符串，获取content的单独json串
            org.json.JSONObject jo = new org.json.JSONObject(res);
            log.info("MsgHttpService data is :" + jo.toString());
            //String content = jo.getJSONObject("content").toString();
            String content = null;
            try{
                content = jo.getJSONObject("content").toString();
            }
            catch(Exception e)
            {
                content = jo.getString("content");
                if(null == content || "".equals(content))
                {
                    throw new NullPointerException();
                }
            }
            log.info("MsgHttpService content is :" + content);
            sign = sp.getSign();
            Map<String, Object> msgMap = BeanToMapUtil.convertBean(sp);
            
            String signData = encryptMap(msgMap);// SignGenerater.genSign(msgMap);
            
            if (null != sign && !"".equals(sign))
            {
                // 加密数据对比
                if (sign.equals(signData))
                {
                    log.info("MsgHttpService signData all right");
                    ServletContext application;
                    WebApplicationContext wac;
                    application = getServletContext();
                    wac = WebApplicationContextUtils.getWebApplicationContext(application);// 获取spring的context
                    
                    // Modify By WuCF 20141229 执行业务，并返回执行的结果
                    StringBuffer returnBuffer = new StringBuffer();
                    // 返回的结果:比如地址不能为空(执行完毕之后！的返回结果 多个else if )
                  if ("savePdLogisticsBaseByInter".equals(sp.getMethod()))
                  {
                    PdLogisticsBaseManager pdLogisticsBaseManager =
                         (PdLogisticsBaseManager) wac.getBean("pdLogisticsBaseManager");
                     rsp = pdLogisticsBaseManager.savePdLogisticsBaseByInter(content);
                     //DO保存成功，发送短信
                     if( Constants.SUCCESS_STRING.equals(rsp.getRsp())){
                    	 pdLogisticsBaseManager.sendMessage(content);
                     }
                  }
                  else if ("saveMailStatus".equals(sp.getMethod()))
                   {
                        MailStatusManager mailStatusManager =
                           (MailStatusManager) wac.getBean("mailStatusManager");
                        rsp = mailStatusManager.saveMailStatuss(content);
                        
                   }
                   else if ("savePdLogisticsBaseList".equals(sp.getMethod()))
                    {
                      PdLogisticsBaseManager pdLogisticsBaseManager =
                            (PdLogisticsBaseManager) wac.getBean("pdLogisticsBaseManager");
                        rsp = pdLogisticsBaseManager.savePdLogisticsBaseList(sp.getContent());
                        
                    }
                   												
                   else if ("getLoginStatus".equals(sp.getMethod()))
                   {
                      SysUserManager sysUserManager =
                           (SysUserManager) wac.getBean("sysUserManager");
                        rsp = sysUserManager.getLoginStatus(content);
                       
                   }/*
                    else if ("saveAmMessageForCrm".equals(sp.getMethod()))
                   {
                        AmMessageManager amMessageManager =
                            (AmMessageManager) wac.getBean("amMessageManager");
                       rsp = amMessageManager.saveAmMessageForCrm(content);
                       
                    }
//                    // Modify By gw 20150505暂停发货
                    else if ("suspendedShipments".equals(sp.getMethod()))
                   {
                        PdSendInfoManager pdSendInfoManager =
                            (PdSendInfoManager) wac.getBean("pdSendInfoManager");
                      rsp = pdSendInfoManager.suspendedShipments(content);
                       
                   }
                   //恢复发货
                    else if ("replyshipments".equals(sp.getMethod()))
                    {
                        PdSendInfoManager pdSendInfoManager =
                            (PdSendInfoManager) wac.getBean("pdSendInfoManager");
                        rsp = pdSendInfoManager.replyshipments(content);
                    }
                    
                   //订单信息
                    else if ("getOrderInfos".equals(sp.getMethod()))
                    {
                    	JpoMemberOrderManager jpoMemberOrderManager =
                            (JpoMemberOrderManager) wac.getBean("jpoMemberOrderManager");
                        rsp = jpoMemberOrderManager.getOrderInfos(content);
                    	
                    }*/
                    
                  
                   //modify fx 2015-08-17 退货入库接口
                   else if ("reSetJprRefundStatus".equals(sp.getMethod()))
                   {
                	   JprRefundManager jprRefundManager =
                           (JprRefundManager) wac.getBean("jprRefundManager");
                       rsp = jprRefundManager.reSetJprRefundStatus(content);
                   	
                   }
                  
                   //modify fx 2015-08-17 换货单和发货退回单直接关联的发货单状态接口
                   else if ("reSetPdSendInfoStatus".equals(sp.getMethod()))
                   {
                	   PdSendInfoManager pdSendInfoManager =
                           (PdSendInfoManager) wac.getBean("pdSendInfoManager");
                       rsp = pdSendInfoManager.reSetPdSendInfoStatus(content);
                   	
                   }
                  
                  //MODIFY FU 2015-09-06 发货退回入库接口
                   else if ("reSetPdReturnPurchaseStatus".equals(sp.getMethod()))
                   {
                	   PdReturnPurchaseManager pdReturnPurchaseManager =
                           (PdReturnPurchaseManager) wac.getBean("pdReturnPurchaseManager");
                       rsp = pdReturnPurchaseManager.reSetPdReturnPurchaseStatus(content);
                   	
                   }
                  
                   else if ("getJmiMemberForGHB".equals(sp.getMethod()))
                   {
                       System.out.println(content);
                       JmiMemberManager jmiMemberManager =
                           (JmiMemberManager) wac.getBean("jmiMemberManager");
                       rsp = jmiMemberManager.getJmiMemberForGHB(content);
                    
                   }  
                   else if ("getUpRecommendNo".equals(sp.getMethod()))
                   {
                       System.out.println(content);
                       JmiMemberManager jmiMemberManager =
                           (JmiMemberManager) wac.getBean("jmiMemberManager");
                       rsp = jmiMemberManager.getUpRecommendNo(content);
                    
                   }
                    
                    // Modify By WuCF 20141229 设置编码格式
                    resp.setContentType("application/json; charset=UTF-8");
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonObject = JSONObject.fromObject(rsp);
                    // 返回执行的结果：
                    out.write(jsonObject.toString());
                    
                    // =============================接口日志写入 Modify By WuCF
                    // 20150112
                    JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
                    jocsInterfaceLog.setLogType("1");// 0：本地发送数据 1：本地接收数据
                    jocsInterfaceLog.setMethod(sp.getMethod());// 方法名称
                    jocsInterfaceLog.setContent(content);// 发送内容content
                    jocsInterfaceLog.setReturnDesc(jsonObject.toString());// 返回内容
                    
                    ReportLogUtil.addJocsInterface(jocsInterfaceLog);// 写入日志操作
                    
                }
                else
                {
                    log.info("MsgHttpService signData error");
                    rsp.setCode(Constants.REPONSE_SIGN_ERR);
                    rsp.setSub_msg("sign error");
                    respSend(resp, rsp);
                }
            }
            else
            {
                log.info("MsgHttpService signData error");
                rsp.setCode(Constants.REPONSE_SIGN_ERR);
                rsp.setSub_msg("sign error");
                respSend(resp, rsp);
            }
            
        }
        catch (IntrospectionException e)
        {
            log.info("MsgHttpService error  :" + e);
            e.printStackTrace();
            rsp.setCode(Constants.REPONSE_APP_PARAM_ERR);
            rsp.setSub_msg("sign error");
            respSend(resp, rsp);
        }
        catch (IllegalAccessException e)
        {
            log.info("MsgHttpService error  :" + e);
            e.printStackTrace();
            rsp.setCode(Constants.REPONSE_APP_PARAM_ERR);
            rsp.setSub_msg("sign error");
            respSend(resp, rsp);
        }
        catch (InvocationTargetException e)
        {
            log.info("MsgHttpService error  :" + e);
            e.printStackTrace();
            rsp.setCode(Constants.REPONSE_APP_PARAM_ERR);
            rsp.setSub_msg("sign error");
            respSend(resp, rsp);
        }
        catch(NullPointerException e)
        {
            log.info("MsgHttpService error  :" + e);
            e.printStackTrace();
            rsp.setCode(Constants.REPONSE_APP_PARAM_ERR);
            rsp.setSub_msg("content is null");
            respSend(resp, rsp);
        }
        // modify fu 2015-09-21 针对物流库存不足的异常--begin
        catch(AppException e )
        {		
            log.info("MsgHttpService error  : EC商品库存不足");
            e.printStackTrace();
            rsp.setCode(Constants.REPONSE_APP_PARAM_ERR);
            rsp.setSub_msg("TimeoutException");//告诉OMS系统EC商品库存不足
            respSend(resp, rsp);
        }
        // modify fu 2015-09-21 针对物流库存不足的异常--begin
        catch (Exception e)
        {
            log.info("MsgHttpService error  :" + e);
            e.printStackTrace();
            rsp.setCode(Constants.REPONSE_APP_PARAM_ERR);
            rsp.setSub_msg("sign error");
            respSend(resp, rsp);
        }
        
    }
    
    /**
     * 加密
     * 
     * @param xmlStr
     * @return
     */
    public String encryptMap(Map<String, Object> map)
    {
        map.remove("sign");
        String mapStr = map.toString().replace("{", "");
        mapStr = mapStr.replace("}", "");
        log.info("pwd map is-------------------------------------- :" + mapStr);
        // 第一次加密，在没有signdata的时候进行md5
        String noSignStr = MD5.md5(mapStr);
        log.info("pwd one :" + noSignStr);
        map.put("sign", noSignStr);
        mapStr = map.toString().replace("{", "");
        mapStr = mapStr.replace("}", "");
        
        // 第二次加密，增加signdata的xml和中脉国际编码
        String notifyStr = mapStr + "zmkjjsums";
        System.out.println(notifyStr);
        String notifyStrBase64 = Base64.encode(notifyStr.getBytes());
        log.info("pwd two :" + notifyStrBase64);
        // 第三次加密，整段加密并存入xml
        String signStr = MD5.md5(notifyStrBase64);
        log.info("pwd three :" + signStr);
        return signStr;
    }
    
    private String readLine(ServletInputStream in) throws IOException
    {
        byte[] buf = new byte[8 * 1024];
        StringBuffer sbuf = new StringBuffer();
        int result;
        // String line;
        
        do
        {
            result = in.readLine(buf, 0, buf.length); // does +=
            if (result != -1)
            {
                sbuf.append(new String(buf, 0, result, "UTF-8"));
            }
            // } while (result == buf.length); // loop only if the buffer was
            // filled
        } while (result != -1); // loop only if the buffer was filled
        
        if (sbuf.length() == 0)
        {
            return null; // nothing read, must be at the end of stream
        }
        
        // Cut off the trailing \n or \r\n
        // It should always be \r\n but IE5 sometimes does just \n
        int len = sbuf.length();
        if (sbuf.charAt(len - 2) == '\r')
        {
            sbuf.setLength(len - 2); // cut \r\n
        }
        else if (sbuf.charAt(len - 1) == '\n')
        {
            sbuf.setLength(len - 1); // cut \n
        }
        else
        {
            sbuf.setLength(len);
        }
        
        return sbuf.toString();
    }
    
    public void respSend(HttpServletResponse resp, RspEntity rspEntity)
    {
        resp.setContentType("text/html; charset=GBK");
        PrintWriter out;
        try
        {
            out = resp.getWriter();
            JSONObject jsonObject = JSONObject.fromObject(rspEntity);
            out.print(jsonObject.toString());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
        throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        super.service(arg0, arg1);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String res =
            //"{\"method\":\"savePdLogisticsBaseByInter\",\"flag\":\"OMS\",\"type\":\"josn\",\"charset\":\"utf-8\",\"ver\":\"1\",\"timestamp\":1420710487,\"content\":{\"MEMBER_ORDER_NO\":\"M020141202000013\",\"SI_NO\":\"LO012014120200005\",\"wms_do\":\"DO20949879022\",\"status\":\"1\",\"operator\":\"仓库管理员\",\"status_time\":\"2014-11-25 16:40:43\",\"status_code\":\"0006\",\"status_name\":\"已发货\",\"mail_list\":[{\"mail_no\":\"1201420920980\",\"name\":\"韵达\",\"status\":\"0000\",\"pdLogisticsBaseDetail_items\":[{\"erp_goods_bn\":\"P09080100101CN0\",\"PRODUCT_NO\":\"P09080100101CN0\",\"qty\":1}]}]},\"sign\":\"1F2876388016EC74FC9CAEA9002D8FA7\"}";
            "{\"method\":\"savePdLogisticsBaseByInter\",\"flag\":\"OMS\",\"type\":\"josn\",\"charset\":\"utf-8\",\"ver\":\"1\",\"timestamp\":1420710487,\"content\":\"M020150605000009\",\"sign\":\"1F2876388016EC74FC9CAEA9002D8FA7\"}";
        // TODO Auto-generated method stub
        // org.json.JSONObject jo = new org.json.JSONObject(res);
        // Object content = jo.getJSONObject("content");
        // System.out.println(content.toString());
        
        // JSONArray ja = new JSONArray(res);
        // String content = ja.getString(3);
        
        // MsgHttpService service = new MsgHttpService();
//        try
//        {
////            InterfaceJsonUtil.returnnoJsonToSysParameter(res, SysParameter.class, null);
//            SysParameter sp =
//                InterfaceJsonUtil.returnnoJsonToSysParameter(res, SysParameter.class, null);
//            // 解析json字符串，获取content的单独json串
//            org.json.JSONObject jo = new org.json.JSONObject(res);
//            System.out.println("MsgHttpService data is :" + jo.toString());
//            String content = jo.getJSONObject("content").toString();
//            System.out.println("MsgHttpService content is :" + content);
//        }
//        catch (Exception e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
    
}
