<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="billAccountList.title"/></title>
<content tag="heading"><jecs:locale key="billAccountList.heading"/></content>
<meta name="menu" content="BillAccountMenu"/>


<form  name="searchForm" id="searchForm">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="bdOutwardBank.bankCode"/>
		<input name="baCode" type="text" value="${param.baCode }" size="10"/>	
	</div>
	<div class="new_searchBar">
		<button name="search" class="btn btn_sele" type="submit">
			<jecs:locale key="operation.button.search"/>
		</button>
		<jecs:power powerCode="addBillAccount">
			<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editBillAccount.html"/>?strAction=addBillAccount'">
				<jecs:locale key="button.add"/>
			</button>
		</jecs:power>
	</div>
	
</div>
</form>
<ec:table 
	tableId="billAccountListTable"
	items="billAccountList"
	var="billAccount"
	action="${pageContext.request.contextPath}/billAccounts.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="baCode" title="bdOutwardBank.bankCode" />
    			<ec:column property="persent" title="billAccount.persent" />
    			
				<ec:column property="operation" title="title.operation" sortable="false" width="100">
<img src="images/newIcons/search_16.gif" onclick="window.location.href='<c:url value="/editBillAccount.html"/>?strAction=viewBillAccount&baId=${billAccount.baId }';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='<c:url value="/editBillAccount.html"/>?strAction=editBillAccount&baId=${billAccount.baId }';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editBillAccount(baId){
   		<jecs:power powerCode="editBillAccount">
					window.location="editBillAccount.html?strAction=editBillAccount&baId="+baId;
			</jecs:power>
		}

</script>
