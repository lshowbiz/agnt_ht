<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="com.joymain.jecs.util.chanjet.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="com.buybal.util.signers.*" %> 
<html>
  <head>
    <title>获取交易数据对账文件</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../common/css/public_css_ussys.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../common/syntaxHighlighter/css/SyntaxHighlighter.css"></link>
	<script language="javascript" src="../common/syntaxHighlighter/js/shCore.js"></script>
	<script language="javascript" src="../common/syntaxHighlighter/js/shBrushJava.js"></script>

  </head>
<%
    StringBuffer signSb = new StringBuffer();
	
	String businessId = request.getParameter("businessId");
	signSb.append("businessId="+businessId); 
	String merchantId = request.getParameter("merchantId");
	signSb.append("&merchantId="+merchantId); 
	String transDate = request.getParameter("transDate");
	signSb.append("&transDate="+transDate); 
	String signType = request.getParameter("signType");
	signSb.append("&signType="+signType); 
	String version = request.getParameter("version");
	signSb.append("&version="+version); 

	
    String key="G08C17J21R90P3QF";//MD5商户密钥
    signSb.append("&key="+key);
    	
    //MD5签名	
	String plain = signSb.toString();//这个是为了在本DEMO中显示签名原串
	byte[]  temp  =   plain.getBytes("UTF-8");
	String pubString=new String(temp);
	String sign=Md5.encodeMD5(plain);
	
	
	//公私钥签名 商户号100000131
    /*
	String plain = signSb.toString();
    String sign = SignVer.sign(plain, "D:/Program Files/apache-tomcat-7.0.37/webapps/demo/WEB-INF/cert/100000131.key.p8");
    */
	 
	System.out.println("签名前的串:"+plain);
    System.out.println("签名后的串:"+sign);
    

%>

  <body>

    <div id="MAINA">
      <div class="mindexa">

         <div class="mright">
          <h3>提交信息及签名查看</h3>
          <div class="mrmain">
            <form name="sendOrder" action="http://www.chanpay.com:2917/gateway/gatewayManage.do?action=transBillManage" method="post" > 
            <table>
              <tbody>

			   
                <tr>
                  <th nowrap>商户号【merchantId】：</th>
                  <td nowrap><strong><%=request.getParameter("merchantId")%></strong><input type="hidden" name="merchantId" value="<%=request.getParameter("merchantId")%>"/></td>
				</tr>
				 
				<tr>
                  <th nowrap>交易日期【transDate】：</th>
                  <td nowrap><strong><%=request.getParameter("transDate")%></strong><input type="hidden" name="transDate" value="<%=request.getParameter("transDate")%>"/></td>
                </tr>
				<tr>
                  <th nowrap>业务号【businessId】：</th>
                  <td nowrap><strong><%=request.getParameter("businessId")%></strong><input type="hidden" name="businessId" value="<%=request.getParameter("businessId")%>"/></td>
				</tr>
				<tr>
                  <th nowrap>签名类型【version】：</th>
                  <td nowrap><strong><%=request.getParameter("signType")%></strong><input type="hidden" name="signType" value="<%=request.getParameter("signType")%>"/></td>
				</tr>
				<tr>
                  <th nowrap>版本号【version】：</th>
                  <td nowrap><strong><%=request.getParameter("version")%></strong><input type="hidden" name="version" value="<%=request.getParameter("version")%>"/></td>
				</tr>
				<tr>
                  <th valign="top" nowrap>签名串【sign】：</th>
                  <td><textarea name="signMsg" cols="40" rows="5" readonly="readonly"><%=sign%></textarea></td>
                </tr>
				<tr>
                  <th valign="top" nowrap>签名明文：</th>
                  <td><textarea cols="40" rows="5" readonly="readonly"><%=plain%></textarea></td>
                </tr>
                <tr>
                  <td colspan="2"><div class="pbutton2 pline" align="center">
				  <input type="submit" value="提交" class="button" />
				  <input type="button" class="button" name="cancel" onclick="window.location='chanjetDownloadTransBill.html'" value="返回" />
				  </div></td>
                </tr>
              </tbody>
            </table>
			</form>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
