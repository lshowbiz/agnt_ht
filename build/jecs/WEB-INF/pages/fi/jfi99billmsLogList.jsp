<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billmsLogList.title" /></title>
<content tag="heading">
<jecs:locale key="jfi99billmsLogList.heading" />
</content>
<meta name="menu" content="Jfi99billmsLogMenu" />


<form action="jfi99billLogs.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" size="10"
				value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfi99billmsLog.pId" />
			<input name="pId" type="text" size="10" value="${param.pId }" />
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
				value="${param.startCreateTime }"
				style="cursor: pointer; width: 70px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
			-
			<input name="endCreateTime" id="endCreateTime" type="text"
				value="${param.endCreateTime }"
				style="cursor: pointer; width: 70px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search" />
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jfi99billmsLogListTable" items="jfi99billmsLogList"
	var="jfi99billmsLog"
	action="${pageContext.request.contextPath}/jfi99billmsLogs.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:exportXls fileName="jfi99billLogs.xls" />
		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="pId" title="jfi99billLog.pId" />
		<ec:column property="orderId" title="poMemberOrder.memberOrderNo" />
		<ec:column property="dealId" title="jfi99billLog.dealId" />
		<ec:column property="bankDealId" title="jfi99billLog.bankDealId" />
		<ec:column property="orderAmount"
			title="poAssistMemberOrder.productAmount" styleClass="numberColumn">
			<fmt:formatNumber value="${jfi99billmsLog.orderAmount * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="payAmount" title="fiBankbookJournal.money"
			styleClass="numberColumn">
			<fmt:formatNumber value="${jfi99billmsLog.payAmount * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="inc" title="jfi99billLog.inc"
			styleClass="centerColumn">
			<jecs:code listCode="yesno" value="${jfi99billmsLog.inc}" />
		</ec:column>
		<ec:column property="createTime"
			title="comm.busi.deal.transaction.date">
			<fmt:formatDate value="${jfi99billmsLog.createTime }"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</ec:column>
		<ec:column property="returnMsg" title="jfi99billLog.returnMsg"
			styleClass="centerColumn">
			<jecs:code listCode="returnmsg" value="${jfi99billmsLog.returnMsg}" />
		</ec:column>
	</ec:row>

</ec:table>
