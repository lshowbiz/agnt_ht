<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.text.*" %>   
<%@ page import="java.util.*" %> 
<html>
  <head>
    <title>获取交易数据对账文件</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
   
   
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
	<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
	<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
	<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
  </head>
<%
//初始化测试数据
String orderId=""+(Math.round(Math.random()*800000)+100000)+"";
SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
String date = format.format(new Date());
%>

<div class="searchBar">
<form name="subForm" action="chanjetDownloadTransBillTwo.html" method="post" onsubmit="return validateOthers(this)" >
<input type="hidden" value="0" class="wtxt" name="signType"/>
<input type="hidden" value="v1.0" class="wtxt" name="version"/>
<input type="hidden" name="businessId" value="00DBTB210077"/>


商户号:
<select name="merchantId">
	<option value="CP008021">CP008021</option>
</select><span>*</span>

交易日期:
<input type="text" value="${param.transDate }" class="wtxt" name="transDate" id="transDate" readonly="readonly"/> 
<img src="./images/calendar/calendar7.gif" id="img_transDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "transDate", 
			ifFormat       :    "%Y%m%d",
			button         :    "img_transDate", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>

<span>*</span>
<input type="submit" value="提交" class="button" />
</form>
</div>

<script>
document.getElementById("downloadTransBill").className="msel";
</script>
<script type="text/javascript">

    Form.focusFirstElement($('subForm'));
    
    function validateOthers(theForm){
  
		if(theForm.elements['transDate'].value==""){
			alert("交易日期不能为空!");
			theForm.elements['transDate'].focus();
			return false;
		}
	}
</script>