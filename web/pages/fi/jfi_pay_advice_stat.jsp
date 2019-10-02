<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayAdviceList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayAdviceList.heading" />
</content>
<meta name="menu" content="FiPayAdviceMenu" />

<script language="javascript" src="scripts/validate.js"></script>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<form action="jfiPayAdviceStat.html" method="get"
	name="jfiPayAdviceSearchForm" id="jfiPayAdviceSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.checkeTime" />
			<input name="startCheckDate" id="startCheckDate" type="text"
					value="${param.startCheckDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endCheckDate" id="endCheckDate" type="text"
					value="${param.endCheckDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="pd.createTime" />
			<input name="startCreateDate" id="startCreateDate" type="text"
					value="${param.startCreateDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateDate" id="endCreateDate" type="text"
					value="${param.endCreateDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="fiPayAdvice.accountCode" />
			<select name="accountCode">
				<option value=""></option>
				<c:forEach items="${jfiPayBanks}" var="payBankVar">
					<option value="${payBankVar.accountCode }"
						<c:if test="${payBankVar.accountCode==param.accountCode }"> selected</c:if>>
						${payBankVar.bankName }
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
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
		<input type="hidden" name="strAction" value="fiPayAdviceStat" />
	</div>
</form>

<c:set var="footTotalVar">
	<tr>
		<td align="right" colspan="3" class="footer">
			<jecs:locale key="report.allTotal" />
			:
		</td>
		<td class="footer">
			<b> <c:if test="${not empty statMap.totalTradeMoney}">
					<fmt:formatNumber pattern="###,##0.00">${statMap.totalTradeMoney}</fmt:formatNumber>
				</c:if> <c:if test="${empty statMap.totalTradeMoney}">
			0.00
		</c:if> </b>
		</td>
		<td class="footer">
			<b> <c:if test="${not empty statMap.totalArrivedMoney}">
					<fmt:formatNumber pattern="###,##0.00">${statMap.totalArrivedMoney}</fmt:formatNumber>
				</c:if> <c:if test="${empty statMap.totalArrivedMoney}">
			0.00
		</c:if> </b>
		</td>
		<td colspan="3" class="footer">
			&nbsp;
		</td>
	</tr>
</c:set>

<form action="jfiPayAdviceStat.html" method="get"
	name="jfiPayAdviceForm" id="jfiPayAdviceForm">
	<%
		request.setAttribute("vEnter", "\n");
	%>
	<ec:table tableId="jfiPayAdviceListTable" items="jfiPayAdviceList"
		var="jfiPayAdvice" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="jfiPayAdviceForm"
		action="${pageContext.request.contextPath}/jfiPayAdviceStat.html"
		width="100%" footer="${footTotalVar}" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:column property="createTime" title="pd.createTime" />
			<ec:column property="accountCode" title="fiPayAdvice.accountCode" />
			<ec:column property="payDate" title="comm.busi.deal.transaction.date"
				style="white-space: nowrap;" />
			<ec:column property="payMoney" title="fiBankbookJournal.money">
				<fmt:formatNumber pattern="#,##0.00"
					value="${jfiPayAdvice.payMoney }" />
			</ec:column>
			<ec:column property="arrivedMoney" title="fiPayAdvice.arrivedMoney">
				<fmt:formatNumber pattern="#,##0.00"
					value="${jfiPayAdvice.arrivedMoney }" />
			</ec:column>
			<ec:column property="sysUser.userCode"
				title="label.member.or.agent.code">
			${jfiPayAdvice.sysUser.userCode }
		</ec:column>
			<ec:column property="checkerName" title="fiPayAdvice.checkerName"
				style="white-space: nowrap;" />
			<ec:column property="checkTime" title="fiBankbookTemp.checkeTime" />
		</ec:row>
		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}">
				<tr id="tr${ROWCOUNT}" class="odddetail">
			</c:if>
			<c:if test="${ROWCOUNT%2==0}">
				<tr id="tr${ROWCOUNT}" class="evendetail">
			</c:if>
			<td align="right" valign="top">
				<jecs:locale key="busi.common.remark" />
			</td>
			<td colspan="7">
				${fn:replace(jfiPayAdvice.remark, vEnter, '<br>')}
			</td>
			</tr>
		</c:if>

	</ec:table>

</form>