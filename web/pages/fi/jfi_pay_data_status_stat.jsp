<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayDataList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayDataList.heading" />
</content>
<meta name="menu" content="FiPayDataMenu" />

<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiPayDataStatusStat.html" method="get"
	name="jfiPayDataSearchForm" id="jfiPayDataSearchForm"
	onsubmit="return validateSearch(this);">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.status" />
			<jecs:list listCode="fiagentpayadvice.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true"
				/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiPayData.accountCode" />
			<select name="accountCode">
				<option value=""></option>
				<c:forEach items="${jfiPayBanks}" var="payBankVar">
					<option value="${payBankVar.accountCode }"
						<c:if test="${payBankVar.accountCode==param.accountCode }"> selected</c:if>>
						[${payBankVar.accountCode }]${payBankVar.bankName }
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiPayAdvice.checkerName" />
			<input name="checkerName" type="text" value="${param.checkerName }"
				size="14" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="comm.busi.deal.transaction.date" />
			<input name="startDealDate" id="startDealDate" type="text" 
					value="${param.startDealDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endDealDate" id="endDealDate" type="text" 
					value="${param.endDealDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiPayData.createNo" />
			<input name="createNo" type="text" value="${param.createNo }"
				size="14" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" colspan="8" class="footer">
			<jecs:locale key="fiPayData.income" />
			<jecs:locale key="busi.order.amount" />
			:
			<b> <c:if test="${not empty totalMap.total_income_money}">
					<fmt:formatNumber pattern="###,##0.00">${totalMap.total_income_money}</fmt:formatNumber>
				</c:if> <c:if test="${empty totalMap.total_income_money}">
				0.00
			</c:if> </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<jecs:locale key="po.paymode.3" />
			<jecs:locale key="busi.order.amount" />
			:
			<b> <c:if test="${not empty totalMap.total_pay_money}">
					<fmt:formatNumber pattern="###,##0.00">${totalMap.total_pay_money}</fmt:formatNumber>
				</c:if> <c:if test="${empty totalMap.total_pay_money}">
				0.00
			</c:if> </b>
		</td>
	</tr>
</c:set>

<form action="jfiPayDataStatusStat.html" method="get"
	name="jfiPayDataForm" id="jfiPayDataForm">
	<ec:table tableId="jfiPayDataListTable" items="jfiPayDataList"
		var="jfiPayData" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="jfiPayDataForm"
		action="${pageContext.request.contextPath}/jfiPayDataStatusStat.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">

		<ec:exportCsv fileName="pay_data_status.csv" />
		<ec:exportXls fileName="pay_data_status.xls" />

		<ec:row>
			<ec:column property="account_code" title="fiPayData.accountCode" />
			<ec:column property="bank_name"
				title="bdSendRemittanceReport.openBankCH" />
			<ec:column property="deal_date"
				title="comm.busi.deal.transaction.date" style="white-space: nowrap;" />
			<ec:column property="income_money" title="fiPayData.incomeMoney"
				styleClass="numberColumn">
				<fmt:formatNumber value="${jfiPayData.income_money}"
					pattern="###,##0.00" />
			</ec:column>
			<ec:column property="pay_user_code"
				title="label.member.or.agent.code">
			${jfiPayData.user_code } ${jfiPayData.pay_user_name }
		</ec:column>
			<ec:column property="pay_date" title="fiPayAdvice.send.date"
				style="white-space: nowrap;" sortable="false" />
			<ec:column property="pay_money" title="bdSendRecord.remittanceMoney"
				styleClass="numberColumn" sortable="false">
				<fmt:formatNumber value="${jfiPayData.pay_money}"
					pattern="###,##0.00" />
			</ec:column>
			<ec:column property="check_time" title="fiBankbookTemp.checkeTime"
				sortable="false">
			${jfiPayData.checker_name }<br>${jfiPayData.check_time }
		</ec:column>
		</ec:row>
	</ec:table>

	<script>
function validateSearch(theForm){
	if(theForm.status.value=="" && theForm.accountCode.value=="" && theForm.startDealDate.value=="" && theForm.endDealDate.value=="" && theForm.checkerName.value==""){
		alert("<jecs:locale key="please.input.search.condition"/>");
		return false;
	}
	return true;
}
</script>