<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.text.*" %>   
<%@ page import="java.util.*" %> 
<%@ page import="md5.*" %> 
<%@ page import="com.buybal.util.signers.*" %> 
<html>
  <head>
    <title>商户退费服务</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../common/css/public_css_ussys.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../common/syntaxHighlighter/css/SyntaxHighlighter.css"></link>
	<script language="javascript" src="../common/syntaxHighlighter/js/shCore.js"></script>
	<script language="javascript" src="../common/syntaxHighlighter/js/shBrushJava.js"></script>

  </head>
<%
  request.setCharacterEncoding("utf-8"); 
  response.setCharacterEncoding("utf-8");
	StringBuffer signSb = new StringBuffer(); 
	
	signSb.append("businessId="); 
	String businessId = ""; 
	if(request.getParameter("businessId")!=null) 
	  businessId = request.getParameter("businessId");
	signSb.append(businessId);  
	 
	
	signSb.append("&platIdtfy="); 
	String platIdtfy = ""; 
	if(request.getParameter("platIdtfy")!=null) 
	  platIdtfy = request.getParameter("platIdtfy");
	signSb.append(platIdtfy);  
	 
	
	signSb.append("&merchantId="); 
	String merchantId = ""; 
	if(request.getParameter("merchantId")!=null) 
	  merchantId = request.getParameter("merchantId"); 
	signSb.append(merchantId);  
	  
		
	signSb.append("&orderId="); 
	String orderId = ""; 
	if(request.getParameter("orderId")!=null) 
	  orderId = request.getParameter("orderId"); 
	signSb.append(orderId);  
	 
	
	
	signSb.append("&orderDate="); 
	String orderDate = ""; 
	if(request.getParameter("orderDate")!=null) 
	  orderDate = request.getParameter("orderDate"); 
	signSb.append(orderDate);  
	 
	
	signSb.append("&refundId="); 
	String refundId = ""; 
	if(request.getParameter("refundId")!=null) 
	  refundId = request.getParameter("refundId"); 
	signSb.append(refundId);  
	 
	
	
	signSb.append("&refundAmt="); 
	String refundAmt = ""; 
	if(request.getParameter("refundAmt")!=null) 
	  refundAmt = request.getParameter("refundAmt");
	signSb.append(refundAmt);  
	 
	
	
	signSb.append("&orderTime="); 
	String orderTime = ""; 
	if(request.getParameter("orderTime")!=null) 
	  orderTime = request.getParameter("orderTime");
	signSb.append(orderTime);  
	 
	
	
	signSb.append("&notifyUrl="); 
	String notifyUrl = ""; 
	if(request.getParameter("notifyUrl")!=null) 
	  notifyUrl = request.getParameter("notifyUrl");
	signSb.append(notifyUrl);  
	 
	
	signSb.append("&payeeBankAccount="); 
	String payeeBankAccount = ""; 
	if(request.getParameter("payeeBankAccount")!=null) 
	  payeeBankAccount = request.getParameter("payeeBankAccount");
	signSb.append(payeeBankAccount);  
	 
	
	signSb.append("&payeeBankType="); 
	String payeeBankType = ""; 
	if(request.getParameter("payeeBankType")!=null) 
	  payeeBankType = request.getParameter("payeeBankType");
	signSb.append(payeeBankType);  
	 
	
	signSb.append("&payeeBankName="); 
	String payeeBankName = ""; 
	if(request.getParameter("payeeBankName")!=null) 
	  payeeBankName = request.getParameter("payeeBankName");
	signSb.append(payeeBankName);  
	 
	
	signSb.append("&payeeName="); 
	String payeeName = ""; 
	if(request.getParameter("payeeName")!=null) 
	  payeeName = request.getParameter("payeeName");
	signSb.append(payeeName);  
	 
	
	signSb.append("&deviceId="); 
	String deviceId = ""; 
	if(request.getParameter("deviceId")!=null) 
	  deviceId = request.getParameter("deviceId");
	signSb.append(deviceId);   


    /*
	*
	*MD5加密签名*/
	
	String key="&key=12356780Poi)(*"; //MD5商户密钥
	signSb.append(key);
    String plain = signSb.toString();
	String sign=Md5.encodeMD5(plain);
	


	 
	 /*
	 *
	 *公私钥签名*/
	/*
	String plain = signSb.toString();
	String sign = SignVer.sign(plain, "D:/Program Files/apache-tomcat-7.0.37/webapps/demo/WEB-INF/cert/100000131.key.p8");
	*/

    //这个是为了在本DEMO中显示签名结果。
    System.out.println("签名明文串:"+plain); 
    System.out.println("签名密文串:"+sign); 
	


	
%>

  <body>
    <div id="HEADA"><jsp:include page="./head.jsp"/></div>
    <div id="MAINA">
      <div class="mindexa">
        <div class="mleft">
          <jsp:include page="./left.jsp"/>
        </div>
         <div class="mright">
          <h3>提交信息及签名查看</h3>
          <div class="mrmain">
         <%-- <form name="sendOrder" action=http://localhost:9999/cjtgate/gateway/gatewayManage.do?action=refundManage method="post"> --%>
		  <form name="sendOrder" action=http://59.151.72.42:2917/gateway/gatewayManage.do?action=refundManage method="post">
            <table>
              <tbody>
                <tr>
                  <th>商户号【merchantId】：</th>
                  <td><input type="text" class="wtxt" name="merchantId" value="<%=request.getParameter("merchantId")%>"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>平台标识【platIdtfy】：</th>
                  <td><input type="text" class="wtxt" name="platIdtfy" value="<%=request.getParameter("platIdtfy")%>"/> <span>*</span></td>
                </tr>
				        <tr>
                  <th>退款号【refundId】：</th>
                  <td><input type="text" value="<%=request.getParameter("refundId")%>" class="wtxt" name="refundId"/> <span>*</span></td>
                </tr>
                 <tr>
                  <th>业务编号【businessId】：</th>
                  <td><input type="text" value="<%=request.getParameter("businessId")%>" class="wtxt" name="businessId"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>原订单号【orderId】：</th>
                  <td><input type="text" value="<%=request.getParameter("orderId")%>" class="wtxt" name="orderId"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>原订单日期【orderDate】：</th>
                  <td><input type="text" value="<%=request.getParameter("orderDate")%>" class="wtxt" name="orderDate"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>下单详细时间【orderTime】：</th>
                  <td><input type="text" value="<%=request.getParameter("orderTime")%>" class="wtxt" name="orderTime"/> <span>*</span></td>
                </tr>
				<tr>
                  <th>退款金额【refundAmt】：</th>
                  <td><input type="text" value="<%=request.getParameter("refundAmt")%>" class="wtxt" name="refundAmt"/> <span>*</span></td>
                </tr>
				<tr>
                  <th>交易金额【orderAmount】：</th>
                  <td><input type="text" value="<%=request.getParameter("orderAmount")%>" class="wtxt" name="orderAmount"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>版本号【version】：</th>
                  <td><input type="text" value="<%=request.getParameter("version")%>" class="wtxt" name="version"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>退款结果通讯地址【notifyUrl】：</th>
                  <td><input type="text" value="<%=request.getParameter("notifyUrl")%>" class="wtxt" name="notifyUrl"/></td>
                </tr>
                <tr>
                  <th>收款方账号【payeeBankAccount】：</th>
                  <td><input type="text" value="<%=request.getParameter("payeeBankAccount")%>" class="wtxt" name="payeeBankAccount"/></td>
                </tr>
                <tr>
                  <th>收款方银行类型【payeeBankType】：</th>
                  <td><input type="text" value="<%=request.getParameter("payeeBankType")%>" class="wtxt" name="payeeBankType"/></td>
                </tr>
                <tr>
                  <th>收款方银行名称【payeeBankName】：</th>
                  <td><input type="text" value="<%=request.getParameter("payeeBankName")%>" class="wtxt" name="payeeBankName"/></td>
                </tr>
                <tr>
                  <th>收款方姓名【payeeName】：</th>
                  <td><input type="text" value="<%=request.getParameter("payeeName")%>" class="wtxt" name="payeeName"/></td>
                </tr>
                
                <tr>
                  <th>设备Id【deviceId】：</th>
                  <td><input type="text" value="<%=request.getParameter("deviceId")%>" class="wtxt" name="deviceId"/></td>
                </tr>
                <tr>
                  <th>签名类型【signType】：</th>
                  <td><input type="text" class="wtxt" name="signType" value="<%=request.getParameter("signType")%>"/> <span>*</span></td>
                </tr>
				        <tr>
                  <th valign="top" nowrap>签名串【signMsg】：</th>
                  <td><input type="text" name="signMsg"  readonly="readonly" value="<%=sign%>"></td>
                </tr>
                
				        <tr>
                  <th valign="top" nowrap>签名明文：</th>
                  <td><textarea cols="40" rows="5" readonly="readonly"><%=plain%></textarea></td>
                </tr>
                 
                <tr>
                  <td colspan="2"><div class="pbutton2 pline" align="center"><input type="submit" value="提交" class="button" onClick="sub()"></div></td>
                </tr>
              </tbody>
            </table>
      </form>
			<div id="codeDiv" style="display:none">
			<table width="200" border="0">
			  <tr>
				<td>

				</td>
			  </tr>
			</table>
			</div>

          </div>
        </div>
      </div>
    </div>
    
    <div id="FOOTA"><jsp:include page="./bottom.jsp"/></div>

  </body>
</html>
<script>

function sub(){
	document.sendOrder.submit();
}
</script>