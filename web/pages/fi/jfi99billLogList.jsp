<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billLogList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfi99billLogList.heading" />
</content>
<meta name="menu" content="Jfi99billLogMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<form action="jfi99billLogs.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" size="10"
				value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.merchantAcctId" />
			<input name="merchantAcctId" type="text" size="10"
				value="${param.merchantAcctId }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.dealId" />
			<input name="dealId" type="text" size="10" value="${param.dealId }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.bankDealId" />
			<input name="bankDealId" type="text" size="10"
				value="${param.bankDealId }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.inc" />
			<jecs:list name="inc" listCode="yesno" value="${param.inc}"
				defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="comm.busi.deal.transaction.date" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="fibankbook.dataType" />
			<jecs:list name="dataType" listCode="fibankbook.datatype"
				value="${param.dataType}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jfi99billLogListTable" items="jfi99billLogList"
	var="jfi99billLog" autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jfi99billLogs.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:exportXls fileName="jfi99billLogs.xls" />
	<ec:row>

		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="merchantAcctId"
			title="jfi99billLog.merchantAcctId" escapeAutoFormat="true" />
		<ec:column property="orderId" title="poMemberOrder.memberOrderNo"
			styleClass="numberColumn" escapeAutoFormat="true" />
		<ec:column property="dealId" title="jfi99billLog.dealId"
			styleClass="numberColumn" escapeAutoFormat="true" />
		<ec:column property="bankDealId" title="jfi99billLog.bankDealId"
			styleClass="numberColumn" escapeAutoFormat="true" />
		<ec:column property="orderAmount"
			title="poAssistMemberOrder.productAmount" styleClass="numberColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatNumber value="${jfi99billLog.orderAmount * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="payAmount" title="fiBankbookJournal.money"
			styleClass="numberColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatNumber value="${jfi99billLog.payAmount * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="inc" title="jfi99billLog.inc"
			styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="yesno" value="${jfi99billLog.inc}" />
		</ec:column>
		<ec:column property="createTime"
			title="comm.busi.deal.transaction.date"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatDate value="${jfi99billLog.createTime }"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</ec:column>
		<ec:column property="returnMsg" title="jfi99billLog.returnMsg"
			styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="returnmsg" value="${jfi99billLog.returnMsg}" />
		</ec:column>
		<ec:column property="dataType" title="fibankbook.dataType"
			styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="fibankbook.datatype"
				value="${jfi99billLog.dataType}" />
		</ec:column>
	</ec:row>

</ec:table>
