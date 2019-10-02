<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayDataDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiPayDataDetail.heading"/></content>

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form method="post" action="verifyJfiPayData.html" onsubmit="return validatePayAdvice(this)" id="jfiPayDataForm">
<input type="hidden" name="strAction" value="verifyFiPayData"/>
<input type="hidden" name="strAdviceCode"/>
<input type="hidden" name="dataId" value="${jfiPayData.dataId }"/>
<div id="titleBar">
<jecs:power powerCode="verifyFiPayData">
	<input type="submit" class="button" name="save"  onclick="saveClicked=true;notDealClicked=false;bCancel=false" value="<jecs:locale key="button.verify"/>" />
	
	<input type="submit" class="button" name="notDeal"  onclick="saveClicked=false;notDealClicked=true;bCancel=false" value="<jecs:locale key="button.not.deal"/>" />
</jecs:power>
<input type="button" class="button" name="cancel" onclick="window.location='jfiPayDatas.html?strAction=listFiPayDatas&needReload=1';" value="<jecs:locale key="operation.button.cancel"/>" />
</div>
</form>

<form method="get" action="verifyJfiPayData.html" onsubmit="return validateSearch(this)" id="jfiPayDataSearchForm">
<table class='list' width="100%">
<input type="hidden" name="dataId" value="${jfiPayData.dataId }"/>
    <tr>
    	<th nowrap="nowrap"><jecs:locale key="fiPayData.accountCode"/></th>
    	<th nowrap="nowrap"><jecs:locale key="comm.busi.deal.transaction.date"/></th>
    	<th nowrap="nowrap"><jecs:locale key="fiPayData.incomeMoney"/></th>
    	<th nowrap="nowrap"><jecs:locale key="label.member.or.agent.code"/></th>
    	<th nowrap="nowrap"><jecs:locale key="busi.common.remark"/></th>
    	<th nowrap="nowrap"><jecs:locale key="operation.button.search"/></th>
    	<th width="100%">&nbsp;</th>
    </tr>
    <tr><td nowrap="nowrap">
    	<select name="accountCode">
          <option value=""></option>
          <c:forEach items="${jfiPayBanks}" var="payBankVar">
            <option value="${payBankVar.accountCode }" <c:if test="${payBankVar.accountCode==selectedAccountCode }"> selected</c:if>>
              [${payBankVar.accountCode }] ${payBankVar.bankName }</option>
          </c:forEach>
        </select>
    	</td>
    <td nowrap="nowrap">
	    <input name="payDate" id="payDate" type="text" value="${selectedDealDate }" size="10"/>
	    <img src="./images/calendar/calendar7.gif" id="img_payDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "payDate", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_payDate", 
			singleClick    :    true
			}); 
		</script>
    </td>
    <td nowrap="nowrap">
    <input name="payMoney" id="payMoney" type="text" value="${selectedIncomeMoney }" size="8"/>
    </td>
   	<td nowrap="nowrap">
   	<input name="userCode" id="userCode" type="text" value="${selectedUserCode }" size="12"/>
   	 </td>
   	 <td nowrap="nowrap">
   	<input name="remark" id="remark" type="text" value="${param.remark }" size="12"/>
   	 </td>
    <td nowrap="nowrap">
    <input type="submit" name="searchBtn" id="searchBtn" value="<jecs:locale key="operation.button.search"/>" class="button"/>
    </td>
    <td>&nbsp;</td>
    </tr>
</table>
</form>

<form action="jfiPayAdvices.html" method="get" name="jfiPayAdviceForm" id="jfiPayAdviceForm">
<%
request.setAttribute("vEnter", "\n");
%>
<ec:table 
	tableId="jfiPayAdviceListTable"
	items="jfiPayAdviceList"
	var="jfiPayAdvice"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	form="jfiPayAdviceForm"
	action="${pageContext.request.contextPath}/jfiPayAdvices.html"
	width="100%"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="companyCode" title="button.select" sortable="false" >
			<input type="radio" name="selectedId" value="${jfiPayAdvice.adviceCode}" class="checkbox"/>
		</ec:column>
		<ec:column property="adviceCode" title="fiPayAdvice.adviceCode" />
		<ec:column property="sysUser.userCode" title="label.member.or.agent.code" />
		<ec:column property="accountCode" title="fiPayAdvice.accountCode" />
		<ec:column property="payDate" title="comm.busi.deal.transaction.date" />
		<ec:column property="payMoney" title="fiBankbookJournal.money" >
			<fmt:formatNumber pattern="#,##0.00" value="${jfiPayAdvice.payMoney }"/>
		</ec:column>
		<ec:column property="arrivedMoney" title="fiPayAdvice.arrivedMoney" >
			<fmt:formatNumber pattern="#,##0.00" value="${jfiPayAdvice.arrivedMoney }"/>
		</ec:column>
		<ec:column property="notice" title="fiPayAdvice.notice" />
		<ec:column property="dealType" title="fiPayAdvice.dealType" />
		<ec:column property="telephone" title="fiPayAdvice.telephone" />
		<ec:column property="remark" title="busi.common.remark" >
			${fn:replace(jfiPayAdvice.remark, vEnter, '<br>')}
		</ec:column>
	</ec:row>

</ec:table>
</form>

<script type="text/javascript">
var saveClicked=false;
var notDealClicked=false;
function validateSearch(theForm){
	if(theForm.payDate.value!="" && !isValidDateFormat(theForm.payDate.value,"-")){
		alert("<jecs:locale key="errors.date"><fmt:param><jecs:locale key="comm.busi.deal.transaction.date"/></fmt:param></fmt:message>");
		return false;
	}
	return true;
}

function validatePayAdvice(theForm){
	if(saveClicked){
		if(!confirm("<jecs:locale key="fiPayData.confirm.verify"/>")){
			return false;
		}
	}else if(notDealClicked){
		if(!confirm("<jecs:locale key="fiPayAdvice.confirm.doNotDeal"/>")){
			return false;
		}
	}
	
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.payAdvice"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strAdviceCode.value=elements[i].value;
			break;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.payAdvice"/>");
		return false;
	}
	
	return isFormPosted();
}
</script>