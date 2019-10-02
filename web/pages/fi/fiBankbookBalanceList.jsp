<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookBalanceList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookBalanceList.heading" />
</content>
<meta name="menu" content="FiBankbookBalanceMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<form action="fiBankbookBalances.html" method="get"
	name="fiBankbookBalanceSearchForm" id="fiBankbookBalanceSearchForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
			<div class="new_searchBar">
				<jecs:title key="bdReconsumMoneyReport.companyCH" />
				<jecs:company selected="${param.companyCode }" name="companyCode"
					prompt="" withAA='false' />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:title key="pd.agentormember" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="14" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.bankbookType" />
			<jecs:list listCode="fibankbooktemp.bankbooktype" name="bankbookType"
				id="bankbookType" value="${param.bankbookType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBankbookBalance.endDate" />
			<input name="endDate" id="endDate" type="text" 
					value="${param.endDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer">
			<b><jecs:locale key="report.allTotal" />:</b>
		</td>
		<td width="100" align="right" class="footer">
			<b><c:if test="${not empty statMap.sum_balance}">
					<fmt:formatNumber pattern="###,##0.00"
						value="${statMap.sum_balance}" />
				</c:if> <c:if test="${empty statMap.sum_balance}">0.00</c:if>
			</b>
		</td>
		<td width="100" align="right" class="footer">
			<b><c:if test="${not empty statMap.sum_money}">
					<fmt:formatNumber pattern="###,##0.00" value="${statMap.sum_money}" />
				</c:if> <c:if test="${empty statMap.sum_money}">0.00</c:if>
			</b>
		</td>
		<td width="100" align="right" class="footer">
			<b><c:if test="${not empty statMap.sum_count}">
					<fmt:formatNumber pattern="###,##0" value="${statMap.sum_count}" />
				</c:if> <c:if test="${empty statMap.sum_count}">0</c:if>
			</b>
		</td>
	</tr>
</c:set>

<form action="fiBankbookBalance.html" method="get"
	name="fiBankbookBalanceForm" id="fiBankbookBalanceForm">
	<ec:table tableId="fiBankbookBalanceListTable"
		items="fiBankbookBalanceList" var="fiBankbookBalance"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="fiBankbookBalanceForm"
		action="${pageContext.request.contextPath}/fiBankbookBalances.html"
		width="100%" showPagination="true" sortable="true"
		imagePath="./images/extremetable/*.gif">
		<ec:exportXls fileName="fi_balance.xls" />
		<ec:row highlightRow="false">
			<ec:column property="user_code" title="pd.agentormember">
		${fiBankbookBalance.user_code } / ${fiBankbookBalance.user_name }
		</ec:column>
			<ec:column property="balance" title="bdBounsDeduct.remainMoney"
				styleClass="numberColumn" width="100" calc="total"
				calcTitle="report.allTotal" format="###,##0.00">
				<c:if test="${not empty fiBankbookBalance.balance}">
					<fmt:formatNumber pattern="###,##0.00"
						value="${fiBankbookBalance.balance}" />
				</c:if>
				<c:if test="${empty fiBankbookBalance.balance}">0.00</c:if>
			</ec:column>
			<%--		<ec:column property="total_money" title="fiBankbookBalance.totalMoney" styleClass="numberColumn" width="100" calc="total" format="###,##0.00">
			<c:if test="${not empty fiBankbookBalance.total_money}"><fmt:formatNumber pattern="###,##0.00" value="${fiBankbookBalance.total_money}"/></c:if>
			<c:if test="${empty fiBankbookBalance.total_money}">0.00</c:if>
		</ec:column>
		<ec:column property="total_count" title="fiBankbookBalance.totalCount" styleClass="numberColumn" width="100" calc="total">
			<c:if test="${not empty fiBankbookBalance.total_count}"><fmt:formatNumber pattern="###,##0" value="${fiBankbookBalance.total_count}"/></c:if>
			<c:if test="${empty fiBankbookBalance.total_count}">0</c:if>
		</ec:column>--%>
		</ec:row>
	</ec:table>
</form>