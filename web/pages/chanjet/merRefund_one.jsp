<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.text.*" %>   
<%@ page import="java.util.*" %> 
<html>
  <head>
    <title>商户退费服务</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../common/css/public_css_ussys.css" type="text/css">
  </head>
<%
//初始化测试数据
String refundId=""+(Math.round(Math.random()*800000)+100000)+"";
SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
String date = format.format(new Date());
String orderId=""+(Math.round(Math.random()*800000)+100000)+""; 

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
          <h3>商户退费服务<span>(带*项为必填)</span></h3>
          <div class="mrmain">
		  <form name="subForm" action="./merRefund_two.jsp" method="post" style="margin:0px">
            <table>
              <tbody>
                <tr>
                  <th>商户号【merchantId】：</th>
                  <td><input type="text" class="wtxt" name="merchantId" value="100000130"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>平台标识【platIdtfy】：</th>
                  <td><input type="text" class="wtxt" name="platIdtfy" value="t3"/> <span>*</span></td>
                </tr>
				        <tr>
                  <th>退款号【refundId】：</th>
                  <td><input type="text" value="<%=refundId%>" class="wtxt" name="refundId"/> <span>*</span></td>
                </tr>
                 <tr>
                  <th>业务编号【businessId】：</th>
                  <td><input type="text" value="00WGTK310024" class="wtxt" name="businessId"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>原订单号【orderId】：</th>
                  <td><input type="text" value="zlw201308011320" class="wtxt" name="orderId"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>原订单日期【orderDate】：</th>
                  <td><input type="text" value="<%=date%>" class="wtxt" name="orderDate"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>下单详细时间【orderTime】：</th>
                  <td><input type="text" value="<%=time %>" class="wtxt" name="orderTime"/> <span>*</span></td>
                </tr>
				<tr>
                  <th>退款金额【refundAmt】：</th>
                  <td><input type="text" value="1000" class="wtxt" name="refundAmt"/> <span>*</span></td>
                </tr>
				<tr>
                  <th>交易金额【orderAmount】：</th>
                  <td><input type="text" value="1000" class="wtxt" name="orderAmount"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>版本号【version】：</th>
                  <td><input type="text" value="v1.0" class="wtxt" name="version"/> <span>*</span></td>
                </tr>
                <tr>
                  <th>退款结果通讯地址【notifyUrl】：</th>
                  <td><input type="text" value="http://www.baidu.com" class="wtxt" name="notifyUrl"/></td>
                </tr>
                <tr>
                  <th>收款方账号【payeeBankAccount】：</th>
                  <td><input type="text" value="" class="wtxt" name="payeeBankAccount"/></td>
                </tr>
                <tr>
                  <th>收款方银行类型【payeeBankType】：</th>
                  <td><input type="text" value="" class="wtxt" name="payeeBankType"/></td>
                </tr>
                <tr>
                  <th>收款方银行名称【payeeBankName】：</th>
                  <td><input type="text" value="" class="wtxt" name="payeeBankName"/></td>
                </tr>
                <tr>
                  <th>收款方姓名【payeeName】：</th>
                  <td><input type="text" value="" class="wtxt" name="payeeName"/></td>
                </tr>
                
                <tr>
                  <th>设备Id【deviceId】：</th>
                  <td><input type="text" value="" class="wtxt" name="deviceId"/></td>
                </tr>
                <tr>
                  <th>签名类型【signType】：</th>
                  <td><input type="text" class="wtxt" name="signType" value="0"/> <span>*</span></td>
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
<script>
document.getElementById("merRefund").className="msel";
</script>