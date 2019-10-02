<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>   
<%@ page import="java.util.*" %> 
<%@ page import="md5.*" %> 
<%@ page import="com.buybal.util.signers.*" %> 
<html>
  <head>
    <title>商户向平台下订单(网银直连)</title>
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
    /*
	businessId=val&platIdtfy=val&merchantId=val&orderId=val&orderDate=val&orderAmount=val&orderTime=val&expireTime=val&payeeBankAccount=val&payeeBankType=val&payeeBankName=val&payeeName=val&deviceId=val&detailId=val&detailTime=val&bankId=val&bankDealId=val&amount=val&amtType=val
	&payResult=val&errCode=val&errMsg=val&payeeBankAccount=val&payeeBankType=val&payeeBankName=val&payeeName=val&deviceId=val
	*/

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
	 
	
	
	signSb.append("&orderAmount="); 
	String orderAmount = ""; 
	if(request.getParameter("orderAmount")!=null) 
	  orderAmount = request.getParameter("orderAmount");
	signSb.append(orderAmount);  
	
	 
	signSb.append("&orderTime="); 
	String orderTime = ""; 
	if(request.getParameter("orderTime")!=null) 
	  orderTime = request.getParameter("orderTime");
	signSb.append(orderTime);  
	
	
	signSb.append("&expireTime="); 
	String expireTime = ""; 
	if(request.getParameter("expireTime")!=null) 
	  expireTime = request.getParameter("expireTime");
	signSb.append(expireTime);  
	
	
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
	
	signSb.append("&detailId="); 
	String detailId = ""; 
	if(request.getParameter("detailId")!=null) 
	  detailId = request.getParameter("detailId");
	signSb.append(detailId);  
	
	signSb.append("&detailTime="); 
	String detailTime = ""; 
	if(request.getParameter("detailTime")!=null) 
	  detailTime = request.getParameter("detailTime");
	signSb.append(detailTime);  
	
	signSb.append("&bankId="); 
	String bankId = ""; 
	if(request.getParameter("bankId")!=null) 
	  bankId = request.getParameter("bankId");
	signSb.append(bankId);  
	
	
	signSb.append("&bankDealId="); 
	String bankDealId = ""; 
	if(request.getParameter("bankDealId")!=null) 
	  bankDealId = request.getParameter("bankDealId");
	signSb.append(bankDealId);  
	
	
	
	signSb.append("&amount="); 
	String amount = ""; 
	if(request.getParameter("amount")!=null) 
	  amount = request.getParameter("amount");
	signSb.append(amount);  
	
	 
	
	signSb.append("&amtType="); 
	String amtType = ""; 
	if(request.getParameter("amtType")!=null) 
	  amtType = request.getParameter("amtType");
	signSb.append(amtType);  
	
	signSb.append("&payResult="); 
	String payResult = ""; 
	if(request.getParameter("payResult")!=null) 
	  payResult = request.getParameter("payResult");
	signSb.append(payResult);  

	
	
	signSb.append("&errCode="); 
	String errCode = ""; 
	if(request.getParameter("errCode")!=null) 
	  errCode = request.getParameter("errCode");
	signSb.append(errCode);  
	

	
	signSb.append("&errMsg="); 
	String errMsg = ""; 
	if(request.getParameter("errMsg")!=null) 
	  errMsg = request.getParameter("errMsg");
	signSb.append(errMsg);  
	
	
	signSb.append("&payeeBankAccount="); 
	signSb.append(payeeBankAccount);  
	 
	
	signSb.append("&payeeBankType="); 
	signSb.append(payeeBankType);  
	
	signSb.append("&payeeBankName="); 
	signSb.append(payeeBankName);  
	
	signSb.append("&payeeName="); 
	signSb.append(payeeName);  
    
    String signMsg="";
	if(request.getParameter("signMsg")!=null){
		signMsg=request.getParameter("signMsg");
	}
	
	boolean check=false;//签名状态
	/*
	 *
	 *MD5加密验签*/
	
	String key="&key=12356780Poi)(*";//MD5商户密钥
	signSb.append(key);
	String plain=signSb.toString();
	String sign=Md5.encodeMD5(plain);
	//String re_sign=Md5.encodeMD5(plain);
	check=signMsg.equals(sign);
	System.out.println("接收的MD5密文串:"+signMsg);
	System.out.println("MD5签名串:"+sign);

	

	/*
	 *
	 *公私钥验签 商户号100000131 */

	 /*
	String plain=signSb.toString();
	check = SignVer.verify(plain,signMsg,"D:/Program Files/apache-tomcat-7.0.37/webapps/demo/WEB-INF/cert/935acff9f80961b2b241d82bede6dfa4.cert.der");
    */

	System.out.println("接受的明文串:"+plain);
	System.out.println("验签结果:"+check);
%>

  <body>
    <div id="HEADA"><jsp:include page="./head.jsp"/></div>
    <div id="MAINA">
      <div class="mindexa">
        <div class="mleft">
          <jsp:include page="./left.jsp"/>
        </div>
         <div class="mright">
          <h3>支付返回通知数据：</h3>
          <div class="mrmain">
         <%-- <form name="sendOrder" action=http://localhost:8080/cjtgate/gateway/gatewayManage.do?action=paymentManage method="post"> --%>
		  <form name="sendOrder" action=http://118.186.66.166:2917/gateway/gatewayManage.do?action=paymentManage method="post">
            <table>
              <tbody>
			  <tr>
                  <th nowrap>验签结果【check】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="check" value="<%=check==true?"验签成功":"验签失败" %>"> </strong></td>
				 </tr>
                <tr>
                  <th nowrap>商户号【merchantId】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="merchantId" value="<%=request.getParameter("merchantId")%>"> </strong></td>
				 </tr>
				 <tr>
                  <th nowrap>手机号【payerContactMbl】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="payerContactMbl" value="<%=request.getParameter("payerContactMbl")%>"></strong></td>
                </tr>
         <tr>
                  <th nowrap>邮箱【payerContactMal】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="payerContactMal" value="<%=request.getParameter("payerContactMal")%>"></strong></td>
                </tr>
				<tr>
                  <th nowrap>商品号【goodsId】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="goodsId" value="<%=request.getParameter("goodsId")%>"></strong></td>
				</tr>
				<tr>
                  <th>产品名称【productName】：</th>
                  <td><input type="text" value="<%=request.getParameter("productName")%>" class="wtxt" name="productName"/></td>
                </tr>
				<tr>
                  <th nowrap>商品信息【productDesc】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="productDesc" value="<%=request.getParameter("productDesc")%>"></strong></td>
                </tr>
				<tr>
                  <th nowrap>订单号【orderId】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="orderId" value="<%=request.getParameter("orderId")%>"></strong></td>
				</tr>
				<tr>
                  <th nowrap>下单日期【orderDate】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="orderDate" value="<%=request.getParameter("orderDate")%>"></strong></td>
                </tr>
        <tr>
                  <th>下单详细时间【orderTime】：</th>
                  <td><input type="text" value="<%=request.getParameter("orderTime")%>" class="wtxt" name="orderTime"/> <span>*</span></td>
                </tr>
				<tr>
                  <th nowrap>金额【orderAmount】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="orderAmount" value="<%=request.getParameter("orderAmount")%>"></strong></td>
				</tr>
				<tr>
                  <th nowrap>金额类型【amtType】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="amtType" value="<%=request.getParameter("amtType")%>"></strong></td>
                </tr>
                 <tr>
                  <th>平台身份表示【platIdtfy】：</th>
                  <td><input type="text" class="wtxt" name="platIdtfy" value="<%=request.getParameter("platIdtfy")%>"/> <span>*</span></td>
                </tr>
				<tr>
                  <th nowrap>支付方式【bankType】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="bankType" value="<%=request.getParameter("bankType")%>"></strong></td>
				</tr>
				<tr>
                  <th>业务编号【businessId】：</th>
                  <td><input type="text" value="<%=request.getParameter("businessId")%>" class="wtxt" name="businessId"/> <span>*</span></td>
                </tr>
				<tr>
                  <th nowrap>网银网关【gateId】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="gateId" value=""></strong></td>
                </tr>
				<tr>
                  <th nowrap>页面返回地址【bgUrl】：</th>
                  <td><strong><input type="text" readonly="readonly" name="bgUrl" value="<%=request.getParameter("bgUrl")%>"></strong></td>
                </tr>
				<tr>
                  <th nowrap>结果通讯地址【notifyUrl】：</th>
                  <td><strong><input type="readoly" name="notifyUrl" value="<%=request.getParameter("notifyUrl")%>"></strong></td>
                </tr>
				<tr>
                  <th nowrap>商户私有信息【merPriv】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="merPriv" value="<%=request.getParameter("merPriv")%>"></strong></td>
				</tr>
				<tr>
                  <th nowrap>商户扩展信息【expand】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="expand" value="<%=request.getParameter("expand")%>"></strong></td>
                </tr>
         <tr>
                  <th nowrap>商户扩展信息2【expand2】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="expand2" value="<%=request.getParameter("expand2")%>"></strong></td>
                </tr>
                 <tr>
                  <th nowrap>支付人名称【payerName】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="payerName" value="<%=request.getParameter("payerName")%>"></strong></td>
                </tr> 
                <tr>
                  <th nowrap>支付卡类型【payerCardType】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="payerCardType" value="<%=request.getParameter("payerCardType")%>"></strong></td>
                </tr>
                <tr>
                  <th nowrap>设备id【deviceId】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="deviceId" value=""></strong></td>
                </tr>               
				<tr>
                  <th nowrap>版本号【version】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="version" value="<%=request.getParameter("version")%>"></strong></td>
				</tr>
				<tr>
                  <th>是否重复提交标识【redoFlag】：</th>
                  <td><input type="text" class="wtxt" name="redoFlag" value="0"/> <span>*</span></td>
               </tr>
				<tr>
                  <th>签名类型【signType】：</th>
                  <td><input type="text" class="wtxt" name="signType" value="<%=request.getParameter("signType")%>"/> <span>*</span></td>
                </tr>
        <tr>
                  <th>过期时长【expireTime】：</th>
                  <td><input type="text" value="<%=request.getParameter("expireTime")%>" class="wtxt" name="expireTime"/> <span>*</span></td>
                </tr>
				<tr>
                  <th valign="top" nowrap>签名串【sign】：</th>
                  <td><input type="text" readonly="readonly" name="signMsg" value="<%=signMsg%>"></textarea></td>
                </tr>
                <tr>
                  <th valign="top" nowrap>签名串【plain】：</th>
                  <td><textarea name="plain" cols="40" rows="5" readonly="readonly"><%=plain%></textarea></td>
                </tr>
                <tr>
				<tr>
                  <th valign="top" nowrap>errMsg【plain】：</th>
                  <td><input type="text" readonly="readonly" name="errMsg" value="<%=errMsg%>"></textarea></td>
                </tr>
                  <td colspan="2"><div class="pbutton2 pline" align="center">
                 <input type="submit" value="提交" class="button" onClick="sub()"/>
                  </div></td>
                </tr>

              </tbody>
            </table>
	</form>		
			<div id="codeDiv" style="display:none">
			
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