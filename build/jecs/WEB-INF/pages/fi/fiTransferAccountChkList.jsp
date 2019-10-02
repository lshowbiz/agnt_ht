<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiTransferAccountList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiTransferAccountList.heading" />
</content>
<meta name="menu" content="FiTransferAccountMenu" />
<script language="javascript" src="scripts/validate.js"></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="fiTransferAccountsChk.html" method="get"
	name="fiTransferAccountChkSearchForm"
	id="fiTransferAccountChkSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="label.fiTransferAccount.transferUserCode" />
			<input name="transferUserCode" type="text"
				value="${param['transferUserCode'] }" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="label.fiTransferAccount.destinationUserCode" />
			<input name="destinationUserCode" type="text"
				value="${param['destinationUserCode'] }" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiTransferAccount.status" />
			<jecs:list listCode="fitransferaccount.status" name="status"
				value="${param.status}" defaultValue="0" showBlankLine="true" />
		</div>
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
		<div class="new_searchBar">
			<jecs:title key="fiTransferAccount.createrName" />
			<input name="createrName" type="text" value="${param.createrName }"
				size="8" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiTransferAccount.checkerName" />
			<input name="checkerName" type="text" value="${param['checkerName'] }"
				size="10" />
		</div>
		<div class="new_searchBar">
			<input type="hidden" name="strAction" value="fiTransferAccountChkList" />
			<input type="hidden" name="strTempIds" value="" />
			&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="checkFiTransferAccount">
				<button name="verify" class="btn btn_ins" type="button" onclick="toverify()">
					<jecs:locale key="button.approve"/>
				</button>
			</jecs:power>
			<jecs:power powerCode="checkFiTransferAccount">
				<button name="revoke" class="btn btn_ins" type="button" onclick="torevoke()">
					<jecs:locale key="button.not.approve"/>
				</button>
			</jecs:power>
		</div>
	</div>
</form>

<form action="fiTransferAccountsChk.html" method="get"
	name="fiTransferAccountChkForm" id="fiTransferAccountChkForm">
	<ec:table tableId="fiTransferAccountChkListTable"
		items="fiTransferAccountChkList" var="fiTransferAccountChk"
		autoIncludeParameters="true" form="fiTransferAccountChkForm"
		action="${pageContext.request.contextPath}/fiTransferAccountsChk.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false">

			<ec:column alias="selectedId" headerCell="selectAll"
				style="width:5px;">
				<c:if test="${fiTransferAccountChk.status == 1}">
					<input type="checkbox" name="selectedId"
						value="${fiTransferAccountChk.taId}" />
				</c:if>
			</ec:column>
			<ec:column property="transferUserCode"
				title="fiTransferAccount.transferUserCode" />
			<ec:column property="destinationUserCode"
				title="fiTransferAccount.destinationUserCode" />
			<ec:column property="money" title="fiTransferAccount.money" />
			<ec:column property="notes" title="fiTransferAccount.notes"
				sortable="false" />


			<ec:column property="status" title="fiTransferAccount.status"
				style="white-space:nowrap;">
				<jecs:code listCode="fitransferaccount.status"
					value="${fiTransferAccountChk.status}" />
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
</form>
<script type="text/javascript">

function selectAll(theForm,status){

	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function toverify(){

	var theForm = document.getElementById("fiTransferAccountChkSearchForm")
	
	if(!confirm("<jecs:locale key="fiTransferAccount.confirm.verify"/>")){
		return false;
	}
	
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.fiTransferAccount"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strTempIds.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.fiTransferAccount"/>");
		return false;
	}
	theForm.strAction.value="checkFiTransferAccount";
	if(isFormPosted()){
		theForm.submit();
	}
}
function torevoke(){

	var theForm = document.getElementById("fiTransferAccountChkSearchForm")
	
	if(!confirm("<jecs:locale key="fiTransferAccount.confirm.revoke"/>")){
		return false;
	}
	
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.fiTransferAccount"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strTempIds.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.fiTransferAccount"/>");
		return false;
	}
	theForm.strAction.value="revokeFiTransferAccount";
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>
