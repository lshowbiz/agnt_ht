<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositJournalList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiDepositJournalList.heading" />
</content>
<meta name="menu" content="jfiDepositJournalMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>


<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="3">
			<jecs:locale key="report.allTotal" />
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumMap.addMoney}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.addMoney}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumMap.addMoney}">
				0.00
			</c:if> </b>
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumMap.decMoney}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.decMoney}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumMap.decMoney}">
				0.00
			</c:if> </b>
		</td>
		<td align="right" class="footer">
			<b> <c:if test="${not empty sumMap.balance}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.balance}</fmt:formatNumber>
				</c:if> <c:if test="${empty sumMap.balance}">
				0.00
			</c:if> </b>
		</td>

	</tr>
</c:set>
<form action="" method="get" name="jfiDepositJournalSearchForm"
	id="jfiDepositJournalSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.member.or.agent.code" />
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="14" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="customerRecord.type" />
			<jecs:list listCode="deposit.type" name="depositType" id="depositType"
				value="${param.depositType}" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />	
			<input name="createBTime" id="createBTime" type="text" 
					value="${param.createBTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="createETime" id="createETime" type="text" 
					value="${param.createETime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jfiDepositJournalListTable"
	items="jfiDepositJournalList" var="jfiDepositJournal"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jfiDepositJournals.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif"
	footer="${footTotalVar}">

	<ec:row>

		<ec:column property="userCode" title="label.member.or.agent.code">

		</ec:column>
		<ec:column property="dealDate" title="comm.busi.deal.transaction.date"
			style="white-space:nowrap;">
			<fmt:formatDate value="${jfiDepositJournal.createTime }"
				pattern="yyyy-MM-dd" />
		</ec:column>
		<ec:column property="notes" title="bdBounsDeduct.summary"
			escapeAutoFormat="true" sortable="false">
			${fn:replace(jfiDepositJournal.notes, vEnter, '<br>')}
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.A"
			styleClass="numberColumn">
			<c:if test="${not empty jfiDepositJournal.addMoney}">
				<fmt:formatNumber value="${jfiDepositJournal.addMoney}"
					type="number" pattern="###,###,##0.00" />
			</c:if>
			<c:if test="${empty jfiDepositJournal.addMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="money" title="fibankbooktemp.dealtype.D"
			styleClass="numberColumn">
			<c:if test="${not empty jfiDepositJournal.decMoney}">
				<fmt:formatNumber value="${jfiDepositJournal.decMoney}"
					type="number" pattern="###,###,##0.00" />
			</c:if>
			<c:if test="${empty jfiDepositJournal.decMoney}">&nbsp;</c:if>
		</ec:column>
		<ec:column property="balance" title="fiBankbookJournal.balance"
			styleClass="numberColumn">
			<c:if test="${empty jfiDepositJournal.balance}">0.00</c:if>
			<c:if test="${not empty jfiDepositJournal.balance}">
				<fmt:formatNumber value="${jfiDepositJournal.balance}" type="number"
					pattern="###,###,##0.00" />
			</c:if>
		</ec:column>
	</ec:row>
</ec:table>


