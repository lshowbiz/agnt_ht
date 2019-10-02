<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiFundbookBalanceList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiFundbookBalanceList.heading" />
</content>
<meta name="menu" content="FiFundbookBalanceMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<form action="fiFundbookBalances.html" method="get"
	name="fiFundbookBalanceSearchForm" id="fiFundbookBalanceSearchForm">
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
			<jecs:list listCode="fifundbook.bankbooktype" name="bankbookType"
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

<form action="fiFundbookBalance.html" method="get"
	name="fiFundbookBalanceForm" id="fiFundbookBalanceForm">
	<ec:table tableId="fiFundbookBalanceListTable"
		items="fiFundbookBalanceList" var="fiFundbookBalance"
		autoIncludeParameters="true" retrieveRowsCallback="limit"
		form="fiFundbookBalanceForm"
		action="${pageContext.request.contextPath}/fiFundbookBalances.html"
		width="100%" showPagination="true" sortable="true"
		imagePath="./images/extremetable/*.gif">
		<ec:exportXls fileName="fi_balance.xls" />
		<ec:row highlightRow="false">
			<ec:column property="user_code" title="pd.agentormember">
		${fiFundbookBalance.user_code } / ${fiFundbookBalance.user_name }
		</ec:column>
			<ec:column property="balance" title="bdBounsDeduct.remainMoney"
				styleClass="numberColumn" width="100" calc="total"
				calcTitle="report.allTotal" format="###,##0.00">
				<c:if test="${not empty fiFundbookBalance.balance}">
					<fmt:formatNumber pattern="###,##0.00"
						value="${fiFundbookBalance.balance}" />
				</c:if>
				<c:if test="${empty fiFundbookBalance.balance}">0.00</c:if>
			</ec:column>
			<%--		<ec:column property="total_money" title="fiFundbookBalance.totalMoney" styleClass="numberColumn" width="100" calc="total" format="###,##0.00">
			<c:if test="${not empty fiFundbookBalance.total_money}"><fmt:formatNumber pattern="###,##0.00" value="${fiFundbookBalance.total_money}"/></c:if>
			<c:if test="${empty fiFundbookBalance.total_money}">0.00</c:if>
		</ec:column>
		<ec:column property="total_count" title="fiFundbookBalance.totalCount" styleClass="numberColumn" width="100" calc="total">
			<c:if test="${not empty fiFundbookBalance.total_count}"><fmt:formatNumber pattern="###,##0" value="${fiFundbookBalance.total_count}"/></c:if>
			<c:if test="${empty fiFundbookBalance.total_count}">0</c:if>
		</ec:column>--%>
		</ec:row>
	</ec:table>
</form>



<%--
<c:set var="buttons">
		<jecs:power powerCode="addFiFundbookBalance">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiFundbookBalance.html"/>?strAction=addFiFundbookBalance'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiFundbookBalanceListTable"
	items="fiFundbookBalanceList"
	var="fiFundbookBalance"
	action="${pageContext.request.contextPath}/fiFundbookBalances.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiFundbookBalance('${fiFundbookBalance.fbbId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="fiFundbookBalance.userCode" />
    			<ec:column property="balance" title="fiFundbookBalance.balance" />
    			<ec:column property="bankbookType" title="fiFundbookBalance.bankbookType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiFundbookBalance(fbbId){
   		<jecs:power powerCode="editFiFundbookBalance">
					window.location="editFiFundbookBalance.html?strAction=editFiFundbookBalance&fbbId="+fbbId;
			</jecs:power>
		}

</script>--%>
