<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>   
<%@ page import="java.util.*" %> 
<html>
  <head>
    <title>商户向平台下订单(网银直连)</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../common/css/public_css_ussys.css" type="text/css">
  </head>
<%
//初始化测试数据
String orderId=""+(Math.round(Math.random()*800000)+100000)+"";
SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
String date = format.format(new Date());

SimpleDateFormat formatT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String time = formatT.format(new Date());
%>


  <body>
    <div id="HEADA"><jsp:include page="./head.jsp"/></div>
    <div id="MAINA">
      <div class="mindexa">
        <div class="mleft">
          <jsp:include page="./left.jsp"/>
        </div>
        <div class="mright">
          <h3>商户向平台下订单(网银直连)<span>(带*项为必填)</span></h3>
          <div class="mrmain">
		  <form name="subForm" action="./webDirectWyPay_two.jsp" method="post" style="margin:0px">
            <table>
              <tbody>
                              
                <tr>
                  <th>商户号【merchantId】：</th>
                  <td><input type="text" class="wtxt" name="merchantId" value="100000130"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>商品号【goodsId】：</th>
                  <td><input type="text" class="wtxt" name="goodsId" value="100"/></td>
                </tr>
                <tr>
                  <th>产品名称【productName】：</th>
                  <td><input type="text" value="1111" class="wtxt" name="productName"/></td>
                </tr>
				        <tr>
                  <th>商品信息【productDesc】：</th>
                  <td><input type="text" class="wtxt" name="productDesc" value="测试商品"/></td>
                </tr>
                  <tr>
                  <th>订单号【orderId】：</th>
                  <td><input type="text" value="<%=orderId %>" class="wtxt" name="orderId"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>下单日期【orderDate】：</th>
                  <td><input type="text" value="<%=date %>" class="wtxt" name="orderDate"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>下单详细时间【orderTime】：</th>
                  <td><input type="text" value="<%=time %>" class="wtxt" name="orderTime"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>金额【orderAmount】：</th>
                  <td><input type="text" value="1" class="wtxt" name="orderAmount"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>金额类型【amtType】：</th>
                  <td><select name="amtType">
						<option value="01" selected>人民币</option>
					  </select> <span>*</span></td>
                </tr>
                <tr>
                  <th>平台标识【platIdtfy】：</th>
                  <td><input type="text" class="wtxt" name="platIdtfy" value="t3"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>支付方式【bankType】：</th>
                  <td><input type="text" value="" class="wtxt" name="bankType"/></td>
                </tr>
                
				        <tr>
                  <th>业务编号【businessId】：</th>
                  <td><input type="text" value="00000000001" class="wtxt" name="businessId"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>页面返回地址【bgUrl】：</th>
                  <td><input type="text" value="http://jesus-pc:8080/merdemo/jsp/receive.jsp" class="wtxt" name="bgUrl"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>结果通讯地址【notifyUrl】：</th>
                  <td><input type="text" value="http://jesus-pc:8080/merdemo/jsp/receive.jsp" class="wtxt" name="notifyUrl"/></td>
                </tr>
				<tr>
                  <th>商户私有信息【merPriv】：</th>
                  <td><input type="text" value="" class="wtxt" name="merPriv"/> </td>
                </tr>
                <tr>
                  <th>支付人手机号【payerContactMbl】：</th>
                  <td><input type="text" value="13683279559" class="wtxt" name="payerContactMbl"/> </td>
                </tr>
                <tr>
                  <th>支付人邮箱【payerContactMal】：</th>
                  <td><input type="text" value="123@la.com" class="wtxt" name="payerContactMal"/> </td>
                </tr>
                <tr>
                  <th nowrap>支付卡类型【payerCardType】：</th>
                  <td nowrap><strong><input type="text" readonly="readonly" name="payerCardType" value=""></strong></td>
                </tr>
                <tr>
                  <th>版本号【version】：</th>
                  <td><input type="text" value="v1.0" class="wtxt" name="version"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>过期时长【expireTime】：</th>
                  <td><input type="text" value="2016-12-30 10:10:10" class="wtxt" name="expireTime"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>付款人姓名【payerName】：</th>
                  <td><input type="text" value="某某" class="wtxt" name="payerName"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>机构证书签名【inputCharset】：</th>
                  <td><input type="text" class="wtxt" name="inputCharset" value=""/> <span>*</span></td>
                </tr>
                <tr>
                  <th>签名类型【signType】：</th>
                  <td><input type="text" class="wtxt" name="signType" value="0"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>是否重复提交标识【redoFlag】：</th>
                  <td><input type="text" class="wtxt" name="redoFlag" value="0"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>扩展字段【expand】：</th>
                  <td><input type="text" class="wtxt" name="expand" value="7482"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>扩展字段【expand2】：</th>
                  <td><input type="text" class="wtxt" name="expand2" value="7482"/> <span>*</span></td>
                </tr>
                <tr>
                  <th></th>
                  <td><div class="pbutton2 pline"><input type="submit" value="提交" class="button" /></div></td>
                </tr>
              </tbody>
            </table>
			</form>
          </div>
        </div>
      </div>
    </div>
    <div id="FOOTA"><jsp:include page="./bottom.jsp"/></div>
  </body>
</html>
