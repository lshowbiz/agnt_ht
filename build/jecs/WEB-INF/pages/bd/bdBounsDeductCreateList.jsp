<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function selectAll(status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function deleteDeduct(theForm){
	if(!confirm("<jecs:locale key="bdBounsDeduct.confirm.deleteDeduct"/>"))
	{
		return false;
	}
	var elements=document.getElementsByName("selectedId");
	if(elements==undefined){
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strAdvicesCodes.value+=","+elements[i].value;
		}
	}

	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
		return false;
	}
	theForm.strActionOperation.value="deleteDeduct";
	theForm.submit();
}
</script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="bdBounsDeductCreateList.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductCreateList.heading"/></content>
<meta name="menu" content="BdBounsDeductMenu"/>



<form action="bdBounsDeductsCreate.html" method="get" name="bdBounsDeductCreateForm1" id="bdBounsDeductCreateForm1">
<input type="hidden" id="strAction" name="strAction" value="${param.strAction }">
<input type="hidden" id="strActionOperation" name="strActionOperation" value="">
		<input type="hidden" name="strAdvicesCodes" id="strAdvicesCodes" value=""/>
		
		
<div class="searchBar">

<div class="new_searchBar">
	<jecs:label key="bdBounsDeduct.createTimeScope"/>
	
	<input name="startCreateTime" id="startCreateTime" type="text" 
			value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endCreateTime" id="endCreateTime" type="text"
			value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			
	
<div class="new_searchBar">	
<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="10"/>
			</div>
<%--<jecs:title key="bdCalculatingSubDetail.name"/>
	<input name="name" type="text" value="${param.name }" size="12"/>--%>	
<div class="new_searchBar">
<jecs:title key="bdBounsDeduct.moneyScope"/>
		<input name="startMoney" type="text" value="${param.startMoney }" style="cursor: pointer;width:75px;"/>-
	<input name="endMoney" type="text" value="${param.endMoney }" style="cursor: pointer;width:75px;"/>	
	</div>
	
<div class="new_searchBar">	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button name="search" type="submit" class="btn btn_sele" >
	<jecs:locale key="operation.button.search"/>
</button>
	
	
<%--<table width="100%" border="0">
	<tr>
		<th>
		</th>
		
		<th>
		</th>
		
		<th>
		</th>

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
		<td>		
		</td>
		<td>
		</td>
		<td>
		</td>
		<td width="100%">&nbsp;</td>
	</tr>
</table>--%>

<button name="search" class="btn btn_ins" type="button" onclick="javascript:location.href='editBdBounsDeductCreate.html?strAction=addDeduct'" >
	<jecs:locale key="member.new.num"/>
</button>

<button name="search" class="btn btn_ins" type="button" onclick="deleteDeduct(bdBounsDeductCreateForm1)" >
	<jecs:locale key="operation.button.delete"/>
</button>

</div>
</div>
<%--<table class='grid_table'>
	<tr class='grid_span'>
		<td colspan='10'>	
			<span  style="cursor:pointer">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key=''/></font>
			</span>
			<span onclick="" style="cursor:pointer">
				<img alt="<jecs:locale  key='operation.button.delete'/>" src="images/newIcons/delete_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key=''/></font>
			</span>
		</td>
</tr>
</table>--%>
</form>



<form action="bdBounsDeductsCreate.html" method="get" name="bdBounsDeductCreateForm" id="bdBounsDeductCreateForm">
<ec:table 
	tableId="bdBounsDeductCreateListTable"
	items="bdBounsDeductCreateList"
	var="bdBounsDeductCreate"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/bdBounsDeductsCreate.html"
	width="100%"
	form="bdBounsDeductCreateForm"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row>
				
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<c:if test="${bdBounsDeductCreate.status=='1'&&empty bdBounsDeductCreate.deductTime}">
					<input type="checkbox" name="selectedId" id="selectedId" value="${bdBounsDeductCreate.id}" class="checkbox"/>
					</c:if>

				</ec:column>
    			<ec:column property="createTime" title="prRefund.createTime" >
    			<fmt:formatDate value="${ bdBounsDeductCreate.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<%--<ec:column property="jmiMember.miUserName" title="bdCalculatingSubDetail.name" />--%>
    			
    			
    			<ec:column property="type" title="bdReconsumMoneyReport.typeCH" styleClass="centerColumn">
    				<jecs:code listCode="bdbounsdeduct.item" value="${bdBounsDeductCreate.type}"/>
    			</ec:column>

    			

    			<ec:column property="money" title="bdBounsDeduct.money" styleClass="numberColumn">
				    <fmt:formatNumber value="${bdBounsDeductCreate.money}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			
    			<ec:column property="status" title="bdBounsDeduct.status" styleClass="centerColumn">
    				<jecs:code listCode="bdbounsdeduct.status" value="${bdBounsDeductCreate.status}"/>
    			</ec:column>

    			<ec:column property="createCode" title="bdBounsDeduct.createName" />
    			<ec:column property="summary" title="bdBounsDeduct.summary" />
    			<%--<ec:column property="status" title="title.operation" sortable="false">
					<a href="editBdBounsDeductCreate.html?id=${bdBounsDeductCreate.id }&strAction=view"><jecs:locale key="bdBounsDeduct.view"/></a>
					<c:if test="${bdBounsDeductCreate.status==1&&empty bdBounsDeductCreate.deductTime}">
					<a href="editBdBounsDeductCreate.html?id=${bdBounsDeductCreate.id }&strAction=edit"><jecs:locale key="bdBounsDeduct.edit"/></a>
					</c:if>
				</ec:column>--%>
				<ec:column property="operation" title="title.operation" sortable="false">
					<img style="cursor:pointer" src="images/newIcons/search_16.gif" 
					onclick="window.location.href='editBdBounsDeductCreate.html?id=${bdBounsDeductCreate.id }&strAction=view';" 
					alt="<jecs:locale key="function.menu.view"/>"/>
					<c:if test="${bdBounsDeductCreate.status=='1'&&empty bdBounsDeductCreate.deductTime}">
					<img style="cursor:pointer" src="images/newIcons/pencil_16.gif" 
					onclick="window.location.href='editBdBounsDeductCreate.html?id=${bdBounsDeductCreate.id }&strAction=editDeduct';" 
					alt="<jecs:locale key="button.edit"/>"/>
					</c:if>
				</ec:column>
				</ec:row>

</ec:table>	
</form>
