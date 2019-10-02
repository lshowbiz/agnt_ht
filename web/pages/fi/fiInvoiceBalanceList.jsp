<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiInvoiceBalanceList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiInvoiceBalanceList.heading" />
</content>
<meta name="menu" content="FiInvoiceBalanceMenu" />

<form action="fiInvoiceBalances.html" method="get"
	name="fiInvoiceBalanceQuery" id="fiInvoiceBalanceQuery">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="pd.agentormember"></jecs:title>
			<input name="userCode" id="userCode" value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" onclick="fiInvoiceBalanceQ(document.fiInvoiceBalanceQuery)">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
	<ec:table tableId="fiInvoiceBalanceListTable"
		items="fiInvoiceBalanceList" var="fiInvoiceBalance"
		action="${pageContext.request.contextPath}/fiInvoiceBalances.html"
		width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="false"
		imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="userCode" title="pd.agentormember" />
			<ec:column property="balance" title="bdBounsDeduct.remainMoney" />
			<ec:column property="remark" title="busi.common.remark" />
		</ec:row>
	</ec:table>
</form>
<script type="text/javascript">
	function fiInvoiceBalanceQ(theForm){
	    theForm.submit();
	}
</script>
