<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function selectAll(theForm,status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function deleteSelected(theForm){
	if(!confirm("<jecs:locale key="bdOutWardBank.confirm.delete"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.bank"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strAdviceCodes.value+=","+elements[i].value;
		}
	}
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bank"/>");
		return false;
	}
	theForm.strAction.value="deleteSelected";
	theForm.submit();
}

</script>
<title><jecs:locale key="bdOutwardBankList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdOutwardBankList.heading" />
</content>
<meta name="menu" content="BdOutwardBankMenu" />



<form action="bdOutwardBanks.html" method="post"
	name="bdOutwardBankFrom" id="bdOutwardBankFrom">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:locale key="bdOutwardBank.bankCode" />
			<input name="bankCode" type="text" value="${param.bankCode }" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" onclick="javascript:document.bdOutwardBankFrom.strAction.value='bdOutwardBanks';document.bdOutwardBankFrom.submit();">
				<jecs:locale key="operation.button.search"/>
			</button>
			
			<button name="search" class="btn btn_ins"  type="button" onclick="window.location='editBdOutwardBank.html?bankId=${bdOutwardBank.bankId }'">
				<jecs:locale key="member.new.num"/>
			</button>			

			<button name="search" class="btn btn_ins" onclick="deleteSelected(bdOutwardBankFrom)">
				<jecs:locale key="operation.button.delete"/>
			</button>
		</div>
 
		<%--<table width="100%" border="0">
	<tr>
		<th>
		</th>
		
		<th>
<jecs:locale key="operation.button.search"/>
		</th>
		
	
		<th>&nbsp;</th>
	</tr>
	<tr>

		<td>
		</td>
		<td>
			
		</td>

		<td width="100%">&nbsp;</td>
	</tr>
</table>--%>
	</div>

	<input type="hidden" name="strAction" value="" />
	<input type="hidden" name="strAdviceCodes" value="" />

	<%--<table class='grid_table'>
	<tr class='grid_span'>
		<td colspan='10'>	
			<span  style="cursor:pointer">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key=''/></font>
			</span>
			<span  style="cursor:pointer">
				<img alt="<jecs:locale  key='operation.button.delete'/>" src="images/newIcons/delete_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key=''/></font>
			</span>
		</td>
</tr>
</table>--%>



</form>

<ec:table tableId="bdOutwardBankListTable" items="bdOutwardBankList"
	var="bdOutwardBank"
	action="${pageContext.request.contextPath}/bdOutwardBanks.html"
	width="100%" retrieveRowsCallback="limit" autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">

	<ec:row>
		<ec:column property="1" title="alCharacterKey.select1"
			sortable="false" styleClass="centerColumn">
			<input type="checkbox" name="selectedId" id="selectedId"
				value="${bdOutwardBank.bankId}" class="checkbox" />

		</ec:column>
		<ec:column property="bankCode" title="bdOutwardBank.bankCode" />
		<ec:column property="bankName" title="bdOutwardBank.bankName" />
		<ec:column property="cityName"
			title="bdSendRemittanceReport.openCityCH" />
		<ec:column property="accountName" title="bdOutwardBank.accountName" />
		<ec:column property="accountCode" title="bdOutwardBank.accountCode" />
		<ec:column property="operation" title="title.operation"
			sortable="false" width="100">
			<img src="images/newIcons/search_16.gif"
				onclick="window.location.href='bdOutwardBanks.html?bankId=${bdOutwardBank.bankId }&view=true&strAction=bdOutwardBankView';"
				alt="<jecs:locale key="function.menu.view"/>"
				style="cursor: pointer" />
			<img src="images/newIcons/pencil_16.gif"
				onclick="window.location.href='editBdOutwardBank.html?bankId=${bdOutwardBank.bankId }';"
				alt="<jecs:locale key="button.edit"/>" style="cursor: pointer" />
		</ec:column>
	</ec:row>

</ec:table>

<form name="form1" action="<c:url value='editBdOutwardBank.html'/>">
	<input type="hidden" name="strAction" id="strAction" />
	<input type="hidden" name='bankId' id='bankId' />
</form>

<script type="text/javascript">

   function editBdOutwardBank(bankId){
   		<jecs:power powerCode="editBdOutwardBank">
					window.location="editBdOutwardBank.html?strAction=editBdOutwardBank&bankId="+bankId;
			</jecs:power>
		}

</script>
