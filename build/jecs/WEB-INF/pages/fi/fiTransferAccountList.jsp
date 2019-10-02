<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiTransferAccountList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiTransferAccountList.heading" />
</content>
<meta name="menu" content="FiTransferAccountMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="" method="get" name="fiBankbookTempSearchForm"
	id="fiBankbookTempSearchForm">
	<div class="searchBar">
		<!-- 
<jecs:title key="label.fiTransferAccount.transferUserCode"/>
<input name="transferUserCode" type="text" value="${param['transferUserCode'] }" size="10"/>

<jecs:title key="label.fiTransferAccount.destinationUserCode"/>
<input name="destinationUserCode" type="text" value="${param['destinationUserCode'] }" size="10"/>

<jecs:title key="fiTransferAccount.status"/>
<jecs:list listCode="fiagentpayadvice.status" name="status" value="${param.status}" defaultValue="0" showBlankLine="true"/>
 -->
 		<div class="new_searchBar">
			<jecs:title key="miMember.createTimeRange" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
			<!-- 
	<jecs:title key="fiTransferAccount.createrName"/>
	<input name="createrName" type="text" value="${param.createrName }" size="8"/>
	
	<jecs:title key="fiTransferAccount.checkerName"/>
	<input name="checkerName" type="text" value="${param['checkerName'] }" size="10"/>
	 -->
			<input type="hidden" name="strAction" value="fiTransferAccountList" />
	
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addFiTransferAccount">
				<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editFiTransferAccount.html"/>?strAction=addFiTransferAccount'">
					<jecs:locale key="button.taapply"/>
				</button>
			</jecs:power>
		</div>

	</div>
</form>
<ec:table tableId="fiTransferAccountListTable"
	items="fiTransferAccountList" var="fiTransferAccount"
	action="${pageContext.request.contextPath}/fiTransferAccounts.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row highlightRow="false">


		<ec:column property="transferUserCode"
			title="fiTransferAccount.transferUserCode" />
		<ec:column property="destinationUserCode"
			title="fiTransferAccount.destinationUserCode" />
		<ec:column property="money" title="fiTransferAccount.money" />
		<ec:column property="notes" title="fiTransferAccount.notes" />


		<ec:column property="status" title="fiTransferAccount.status"
			style="white-space:nowrap;">
			<jecs:code listCode="fitransferaccount.status"
				value="${fiTransferAccount.status}" />
		</ec:column>

		<ec:column property="createrName"
			title="fiTransferAccount.createrName" />
		<ec:column property="createTime" title="fiTransferAccount.createTime" />

		<ec:column property="checkerName"
			title="fiTransferAccount.checkerName" />
		<ec:column property="checkeTime" title="fiTransferAccount.checkeTime" />
		<ec:column property="dealDate" title="fiTransferAccount.dealDate" />
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editFiTransferAccount(taId){
   		<jecs:power powerCode="editFiTransferAccount">
					window.location="editFiTransferAccount.html?strAction=editFiTransferAccount&taId="+taId;
			</jecs:power>
		}

</script>
