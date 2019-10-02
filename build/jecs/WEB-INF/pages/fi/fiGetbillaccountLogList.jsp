<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiGetbillaccountLogList.title"/></title>
<content tag="heading"><jecs:locale key="fiGetbillaccountLogList.heading"/></content>
<meta name="menu" content="FiGetbillaccountLogMenu"/>


<form action="fiGetbillaccountLogs.html" method="get" name="fiGetbillaccountLogSearchForm" id="fiGetbillaccountLogSearchForm">
<div class="searchBar">
	<jecs:title key="fiBillAccount.billAccountCode"/>
	<input name="billAccountCode" type="text" value="${param['billAccountCode'] }" size="14"/>
	
	<jecs:title key="miMember.memberNo"/>
	<input name="userCode" type="text" value="${param['userCode'] }" size="14"/>

<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/>

</div>
</form>
<ec:table 
	tableId="fiGetbillaccountLogListTable"
	items="fiGetbillaccountLogList"
	var="fiGetbillaccountLog"
	action="${pageContext.request.contextPath}/fiGetbillaccountLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
					
    				<ec:column property="createTime" title="fiGetbillaccountLog.createTime" />
    				<ec:column property="accountCode" title="fiBillAccount.billAccountCode" />
    				<ec:column property="userCode" title="miMember.memberNo" />
    				<ec:column property="status" title="fiGetbillaccountLog.status" >
    					<c:if test="${fiGetbillaccountLog.status eq '1'}"><jecs:locale key="fiGetbillaccountLog.status1"/></c:if>
    					<c:if test="${fiGetbillaccountLog.status eq '2'}"><jecs:locale key="fiGetbillaccountLog.status2"/></c:if>
    				</ec:column>
    				<ec:column property="logDes" title="fiGetbillaccountLog.logDes" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiGetbillaccountLog(logId){
   		<jecs:power powerCode="editFiGetbillaccountLog">
					window.location="editFiGetbillaccountLog.html?strAction=editFiGetbillaccountLog&logId="+logId;
			</jecs:power>
		}

</script>
