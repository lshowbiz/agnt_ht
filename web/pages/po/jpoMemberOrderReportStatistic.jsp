<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />



<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<c:if test="${empty jpoMemberOrderReportStatistic}">
<form name="jpoMemberOrderReportStatisticForm" method="post" action="jpoMemberOrderReportStatistic.html" id="jpoMemberOrderReportStatisticForm">

	<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:locale key="bdCaculateLog.cretaeTime" />:</span></th>
			<td>
				<span class="textbox" style="width:82px;">
			<input name="startDate" id="startDate" type="text" value="${param.startDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" Class="textbox-text"/>
				</span>
				 - 
				<span class="textbox" style="width:82px;">
			<input name="endDate" id="endDate" type="text" value="${param.endDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" Class="textbox-text"/>	
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wweek" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="formatedWeek" id="formatedWeek" size="8" class="textbox-text"/></span>
				<jecs:locale key="label.example"/>200806
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="bdPeriod.wyear" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="formatedYear" id="formatedYear" size="8" value="${param.formatedYear }" class="textbox-text"/></span>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="bdPeriod.wmonth" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="formatedMonth" id="formatedMonth" size="8" value="${param.formatedMonth }" class="textbox-text"/></span>
			</td>
		</tr>
		<tr>		
			<td class="command" colspan="4" align="center">
			<input type="submit" name="button1" value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
				<input type="hidden" name="strAction" value="jpoMemberOrderReportStatistic"/>
		</td>
		</tr>
		</tbody>
	</table>
	
</form>

</c:if>

<c:if test="${not empty jpoMemberOrderReportStatistic}">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
    <td colspan="2"><jecs:locale key="jpoMemberOrder.huizong"/></td>
    <td colspan="2"><jecs:locale key="ordertype.6"/></td>
    <td colspan="2"><jecs:locale key="ordertype.9"/></td>
    <td colspan="2"><jecs:locale key="ordertype.11"/></td>
    <td colspan="2"><jecs:locale key="ordertype.12"/></td>
    <td colspan="2"><jecs:locale key="ordertype.14"/></td>
    <td colspan="2"><jecs:locale key="ordertype.1"/></td>
    <td colspan="2"><jecs:locale key="ordertype.2"/></td>
    <td colspan="2"><jecs:locale key="ordertype.3"/></td>
    <td colspan="2"><jecs:locale key="ordertype.4"/></td>
  </tr>
  <tr>
    <td><jecs:locale key="pmProduct.productName"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
    <td><jecs:locale key="pd.qty"/></td>
    <td><jecs:locale key="pd.price"/></td>
  </tr>
  <c:forEach items="${jpoMemberOrderReportStatistic }" var="po">
  <tr>
    <td>${po.PRODUCT_NAME }</td>
    <td>${po.QTY }</td>
    <td>${po.PRICE }</td>
    <td>${po.QTY6 }</td>
    <td>${po.PRICE6 }</td>
    <td>${po.QTY9 }</td>
    <td>${po.PRICE9 }</td>
    <td>${po.QTY11 }</td>
    <td>${po.PRICE11 }</td>
    <td>${po.QTY12 }</td>
    <td>${po.PRICE12 }</td>
    <td>${po.QTY14 }</td>
    <td>${po.PRICE14 }</td>
    <td>${po.QTY1 }</td>
    <td>${po.PRICE1 }</td>
    <td>${po.QTY2 }</td>
    <td>${po.PRICE2 }</td>
    <td>${po.QTY3 }</td>
    <td>${po.PRICE3 }</td>
    <td>${po.QTY4 }</td>
    <td>${po.PRICE4 }</td>
  </tr>
</c:forEach>
</table>
</c:if>