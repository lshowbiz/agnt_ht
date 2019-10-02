<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunMemberOrderList.heading"/></content>
<meta name="menu" content="JfiSunMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<jecs:label key="jfiSunDistribution.agentCode"/>
<input name="agentNo" type="text" value="${param['agentNo'] }" size="12"/>
<jecs:label key="logType.BC"/><jecs:label key="label.dateselect.ex"/>
			<input id="startLogTime" name="startLogTime" type="text" size="8" maxlength="10" value="${param.startLogTime }"/>
			<img src="./images/calendar/calendar7.gif" id="img_startOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "startLogTime", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_startOperatorTime", 
				singleClick    :    true
				}); 
			</script> - 
			<input id="endLogTime" name="endLogTime" type="text" size="8" maxlength="10" value="${param.endLogTime }"/>
			<img src="./images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "endLogTime", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_endOperatorTime", 
				singleClick    :    true
				}); 
			</script>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
</div>
</form>
<c:if test="${not empty jfiSunMemberOrderList}">
<c:if test="${param.strAction=='jfiSunMemberOrderReportEdit'}">
<form action="" method="post" name="saveForm" id="saveForm">
<div class="searchBar">
<input name="agentNoPost" type="hidden" value="${param['agentNo'] }" />
<input name="startLogTimePost" type="hidden" value="${param.startLogTime }"/>
<input name="endLogTimePost" type="hidden" value="${param.endLogTime }"/>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input name="save" class="button" type="button" onclick="check();" value="<jecs:locale key="operation.button.save"/>" />
</div>
</c:if>
<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr height="20">
    <td colspan="8" align="center"><jecs:locale key="menu.pd.statisticPdOutWarehouse"/></td>
  </tr>
  <tr height="20">
    <td align="center"><jecs:locale key="title.index"/></td>
    <td align="center"><jecs:locale key="busi.product.productno"/></td>
    <td align="center"><jecs:locale key="pmProduct.productName"/></td>
    <td align="center"><jecs:locale key="piProduct.unitNo"/></td>
    <td align="center"><jecs:locale key="jfiSunMemberOrder.qty"/></td>
    <td align="center"><jecs:locale key="jfiSunMemberOrder.price"/></td>
    <td align="center"><jecs:locale key="jfiSunMemberOrder.sumPrice"/></td>
  </tr>
<c:forEach items="${jfiSunMemberOrderList}" var="jfiSunMemberOrderMap" varStatus="bdOrderStatus">
  <tr height="20">
    <td align="center">${bdOrderStatus.count }</td>
    <td align="center">${jfiSunMemberOrderMap.product_no }</td>
    <td align="center">${jfiSunMemberOrderMap.product_name }</td>
    <td align="center"><jecs:code listCode="pmproduct.unitno" value="${jfiSunMemberOrderMap.unit_no }"/></td>
    <td align="center">${jfiSunMemberOrderMap.sumqty }</td>
    <c:if test="${param.strAction=='jfiSunMemberOrderReportEdit'}">
    <td align="center"><input id="price${jfiSunMemberOrderMap.uni_no }" name="price${jfiSunMemberOrderMap.uni_no }" type="text" size="8" maxlength="10" value="${jfiSunMemberOrderMap.price }"/>
    	<input type="hidden" name="productId" value="${jfiSunMemberOrderMap.uni_no }"/>
    	<input type="hidden" name="productPrice${jfiSunMemberOrderMap.uni_no }" value="${jfiSunMemberOrderMap.price }"/></td>
    </c:if>
    <c:if test="${param.strAction!='jfiSunMemberOrderReportEdit'}">
    <td align="center">${jfiSunMemberOrderMap.price }</td>
    </c:if>
    <td align="center">${jfiSunMemberOrderMap.sumprice }</td>
  </tr>
</c:forEach>
</table>
<c:if test="${param.strAction=='jfiSunMemberOrderReportEdit'}">
</form>
<script>
function check(){
	if($('agentNo').value!=$('agentNoPost').value){
		return;
	}
	if($('startLogTime').value!=$('startLogTimePost').value){
		return;
	}
	if($('endLogTime').value!=$('endLogTimePost').value){
		return;
	}
	if(isFormPosted()){
		$('saveForm').submit();
	}
}
</script>
</c:if>
</c:if>
