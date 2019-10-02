<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.text.*" %>   
<%@ page import="java.util.*" %> 
<html>
  <head>
    <title>查询交易记录</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="../common/css/public_css_ussys.css" type="text/css">
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
	<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
	<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
	<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
	    
  </head>
<%
//初始化测试数据
String orderId=""+(Math.round(Math.random()*800000)+100000)+"";
SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//String date = format.format(new Date());
%>


<div class="searchBar">
<form name="subForm" action="chanjetCheckOrderStateTwo.html" method="post" onsubmit="return validateOthers(this)" >
<input type="hidden" class="wtxt" name="signType" value="0"/>
<input type="hidden" value="v1.0" class="wtxt" name="version"/>
<input type="hidden" value="00WGCX110022" class="wtxt" name="businessId"/>
<input type="hidden" class="wtxt" name="platIdtfy" value="t3"/>
<input type="hidden" value="" class="wtxt" name="deviceId"/>

商户号:
<select name="merchantId">
	<option value="CP008021">CP008021</option>
</select><span>*</span>
订单号:<input type="text" value="" class="wtxt" name="orderId"/><span>*</span>
下单日期:<input type="text" value="${param.orderDate }" class="wtxt" name="orderDate" id="orderDate" readonly="readonly"/> 
<img src="./images/calendar/calendar7.gif" id="img_orderDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "orderDate", 
			ifFormat       :    "%Y%m%d",  
			button         :    "img_orderDate", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>
<span>*</span>
<input type="submit" value="提交" class="button" />
</form>
</div>
<script>
document.getElementById("checkOrderState").className="msel";
</script>
<script type="text/javascript">

    Form.focusFirstElement($('subForm'));
    
    function validateOthers(theForm){
  
		if(theForm.elements['orderId'].value==""){
			alert("订单号不能为空!");
			theForm.elements['orderId'].focus();
			return false;
		}
		if(theForm.elements['orderDate'].value==""){
			alert("下单日期不能为空!");
			theForm.elements['orderDate'].focus();
			return false;
		}
	}
</script>