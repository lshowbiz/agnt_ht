package com.joymain.jecs.util.httpclient;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.string.StringUtil;

/**
 * 
 * HttpClient处理
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-2 下午4:36:04
 * 
 */
public class HttpClientUtil {
    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(HttpClientUtil.class);

    private static HttpClientUtil httpClientManager;

    private HttpClient httpClient;

    private int connectionTimeout = 30;

    private int socketTimeout = 30;

    private String content_type = "text/xml; charset=UTF-8";

    private HttpConnectionManager connectionManager = new SimpleHttpConnectionManager();

    private HttpClientUtil() {
        init();
    }

    private void init1() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();

        params.setConnectionTimeout(30000);
        params.setDefaultMaxConnectionsPerHost(3000);
        params.setSoTimeout(300000);
        connectionManager.setParams(params);
        httpClient = new HttpClient(connectionManager);
    }

    private void init() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        connectionTimeout = StringUtil.formatInt(Constants.sysConfigMap.get("AA").get("httpClient.connectionTimeout"), connectionTimeout) * 1000;
        socketTimeout = StringUtil.formatInt(Constants.sysConfigMap.get("AA").get("httpClient.socketTimeout"), socketTimeout) * 1000;
        int defaultMaxConnectionsPerHost = StringUtil.formatInt(Constants.sysConfigMap.get("AA").get("httpClient.defaultMaxConnectionsPerHost"), 100);
        content_type = Constants.sysConfigMap.get("AA").get("httpClient.content_type") == null ? content_type : Constants.sysConfigMap.get("AA").get("httpClient.content_type");

        params.setConnectionTimeout(connectionTimeout);
        params.setDefaultMaxConnectionsPerHost(defaultMaxConnectionsPerHost);
        params.setSoTimeout(socketTimeout);
        connectionManager.setParams(params);
        httpClient = new HttpClient(connectionManager);
    }

    /**
     * 获取单例
     * 
     * @return MultiThreadHttpManager
     */
    public static HttpClientUtil getInstance() {
        if (httpClientManager == null) {
            httpClientManager = new HttpClientUtil();
        }
        return httpClientManager;
    }

    public String postXML(String url, String xml, Map<String, String> headers) {
        return postXML(url, xml, connectionTimeout, socketTimeout, headers);
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ufinterface subbilltype=\"\" sender=\"5001\" roottag=\"\" replace=\"Y\" receiver=\"103\" proc=\"add\" isexchange=\"Y\" filename=\"filename\" billtype=\"invbom\" account=\"0001\"><bill id=\"1460555555298\"><billhead><cfgnum>1</cfgnum><invcode>2165554935</invcode><invname>测试1</invname><pk_corp>102</pk_corp><pk_invcl>1030</pk_invcl><pk_measdoc>瓶</pk_measdoc></billhead><billbody><entry><nrowid>1</nrowid><pk_corp>102</pk_corp> <pk_invbasid>270001</pk_invbasid> <sl>1</sl> <sdate>2013-1-1</sdate>  <edate>9999-12-31</edate>   <gcbm>102</gcbm>   <shxs>0.0</shxs>   <sfzl>0</sfzl>  <sffl>0</sffl>   <sfwwfl>0</sfwwfl>   <isspecialty>0</isspecialty> </entry>  <entry>    <nrowid>2</nrowid>    <pk_corp>102</pk_corp>    <pk_invbasid>30000002</pk_invbasid>     <sl>500</sl>     <sdate>2013-1-1</sdate>   <edate>9999-12-31</edate> <gcbm>102</gcbm>   <shxs>0.0</shxs> <sfzl>0</sfzl>  <sffl>0</sffl>   <sfwwfl>0</sfwwfl>  <isspecialty>0</isspecialty></entry></billbody>  </bill></ufinterface>";
        String res = HttpClientUtil.getInstance().postXML("http://192.168.20.142:80/service/XChangeServlet?account=design&receiver=102", xml, null);

        System.out.println(res);
    }

    /*
        *//**
     * 
     * 向服务器Post XML报文
     * 
     * @param url
     *            请求地址
     * @param xml
     *            XML
     * @param connectionTimeout
     *            连接超时时间(毫秒)
     * @param socketTimeout
     *            socket超时时间(毫秒)
     * @param headers
     *            请求头
     * @return String 异常
     */
    public String postXML(String url, String xml, int connectionTimeout, int socketTimeout, Map<String, String> headers) {
        try {
            PostMethod httpPost = new PostMethod(url);
            // 设置请求头
            connectionManager.getParams().setConnectionTimeout(connectionTimeout);
            connectionManager.getParams().setSoTimeout(socketTimeout);
            if (headers != null) {
                Set<String> headerNames = headers.keySet();
                Iterator<String> its = headerNames.iterator();
                while (its.hasNext()) {
                    String headerName = its.next();
                    httpPost.addRequestHeader(headerName, headers.get(headerName));
                }
            }
            httpPost.setRequestHeader("Content-type", content_type);
            // 设置请求体
            RequestEntity requestEntity = new ByteArrayRequestEntity(xml.getBytes("UTF-8"), content_type);
            httpPost.setRequestEntity(requestEntity);
            // 记录请求报文日志
            int statusCode = httpClient.executeMethod(httpPost);
            httpPost.getResponseBodyAsString();

            if (HttpStatus.SC_OK == statusCode) {
                String responseXML = httpPost.getResponseBodyAsString();
                return responseXML;
            }
        } catch (Exception e) {
            log.error("MultiThreadHttpManager.postXML Error : " + e);
        }
        return null;
    }
    /*
        public static void main(String args[]) throws Exception {
            String url = "http://127.0.0.1:8090/test/xml.xml";
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("x-UserAgent", "android|SAMSUMG9100|android 2.3|1.0");
            headerMap.put("Accept", "application/xml");
            headerMap.put("Authorization", "ip bWFzOjEzNjMxNDYwNzY3");
            //
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><getMissionInfo><account>+86+123456789</account></getMissionInfo>";
            try {
                String responseXML = HttpClientUtil.getInstance().postXML(url, xml, headerMap);
                System.out.print(responseXML);
                //
            } catch (Exception e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
            //
        }*/

}
