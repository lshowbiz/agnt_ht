<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiInvoiceChangeList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiInvoiceChangeList.heading" />
</content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"> </script>
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<meta name="menu" content="FiInvoiceChangeMenu" />

<form action="fiInvoiceChanges.html" method="get"
	name="fiInvoiceChangeQuery" id="fiInvoiceChangeQuery">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="pd.agentormember"></jecs:title>
			<input name="userCode" id="userCode" value="${param.userCode }" />
			<!-- <jecs:title key="fiAvailableInvoice.jyear"></jecs:title>
	        <input name="jyear" id="jyear" value="${param.jyear }" onkeyup="this.value=this.value.replace(/\D/g,'')"/> -->
			<!-- 
	         <jecs:title key="bdBounsDeduct.wweek"></jecs:title>
	         <input name="jmonth" id="jmonth" value="${param.jmonth }" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
	         -->
	     </div>
		 <div class="new_searchBar">
			<jecs:title key="pd.createTime" />
			<input name="createTimeStart" id="createTimeStart" type="text" 
					value="${param.createTimeStart }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="createTimeEnd" id="createTimeEnd" type="text" 
					value="${param.createTimeEnd }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" onclick="fiInvoiceChangeQ(document.fiInvoiceChangeQuery)">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>

	</div>
	<ec:table tableId="fiInvoiceChangeListTable"
		items="fiInvoiceChangeList" var="fiInvoiceChange"
		action="${pageContext.request.contextPath}/fiInvoiceChanges.html"
		width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="false"
		imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="userCode" title="pd.agentormember" />
			<ec:column property="createTime" title="pd.createTime">
				<fmt:formatDate value="${fiInvoiceChange.createTime }"
					pattern="yyyy-MM-dd" />
			</ec:column>
			<ec:column property="remark" title="bdBounsDeduct.summary" />
			<ec:column property="_1" title="busi.order.deposit">
				<c:if test="${fiInvoiceChange.changeType == '+'}">
    			          ${fiInvoiceChange.money}
    			     </c:if>
			</ec:column>
			<ec:column property="_2" title="busi.order.expenditure">
				<c:if test="${fiInvoiceChange.changeType == '-'}">
    			           ${fiInvoiceChange.money}
    			     </c:if>
			</ec:column>
			<ec:column property="balance" title="bdBounsDeduct.remainMoney" />

		</ec:row>
	</ec:table>
</form>
<script type="text/javascript">
function fiInvoiceChangeQ(theForm){
    theForm.submit();
}
</script>
