<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiTenpayLogList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiTenpayLogList.heading" />
</content>
<meta name="menu" content="JfiTenpayLogMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<form action="jfiTenpayLogs.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" size="10"
				value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.merchantAcctId" />
			<input name="bargainorId" type="text" size="10"
				value="${param.bargainorId }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billLog.dealId" />
			<input name="transactionId" type="text" size="10"
				value="${param.transactionId }" />
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
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jfiTenpayLogListTable" items="jfiTenpayLogList"
	var="jfiTenpayLog"
	action="${pageContext.request.contextPath}/jfiTenpayLogs.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:exportXls fileName="jfi99billLogs.xls" />
		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="bargainorId" title="jfi99billLog.merchantAcctId" />
		<ec:column property="orderId" title="poMemberOrder.memberOrderNo" />
		<ec:column property="transactionId" title="jfi99billLog.dealId" />
		<ec:column property="totalFee"
			title="poAssistMemberOrder.productAmount" styleClass="numberColumn">
			<fmt:formatNumber value="${jfiTenpayLog.totalFee * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="inc" title="jfi99billLog.inc"
			styleClass="centerColumn">
			<jecs:code listCode="yesno" value="${jfiTenpayLog.inc}" />
		</ec:column>
		<ec:column property="createTime"
			title="comm.busi.deal.transaction.date">
			<fmt:formatDate value="${jfiTenpayLog.createTime }"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</ec:column>
		<ec:column property="returnMsg" title="jfi99billLog.returnMsg"
			styleClass="centerColumn">
			<jecs:code listCode="returnmsg" value="${jfiTenpayLog.returnMsg}" />
		</ec:column>
	</ec:row>

</ec:table>
