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
function checkSelected(theForm){
	if(!confirm("<jecs:locale key="bdBounsDeduct.confirm.checkSelected"/>"))
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
	theForm.strActionOperation.value="checkSelected";
	theForm.submit();
}
function unCheckSelected(theForm){
	if(!confirm("<jecs:locale key="bdBounsDeduct.confirm.unCheckSelected"/>"))
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
	theForm.strActionOperation.value="unCheckSelected";
	theForm.submit();
}

function bdNotCheckedSelected(theForm){
	if(!confirm("<jecs:locale key="bdBounsDeduct.confirm.bdNotCheckedSelected"/>"))
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
	theForm.strActionOperation.value="bdNotCheckedSelected";
	theForm.submit();
}
</script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="bdBounsDeductCreateList.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductCheckList.heading"/></content>
<meta name="menu" content="BdBounsDeductMenu"/>



<form action="bdBounsDeductsCheck.html" method="get" name="bdBounsDeductCheckForm1" id="bdBounsDeductCheckForm1">
<div class="searchBar">

<div class="new_searchBar">
	<jecs:title key="bdBounsDeduct.status"/>
		<jecs:list name="status" listCode="bdbounsdeduct.status" value="${param.status }" defaultValue="1"/>	
		</div>
		
<div class="new_searchBar">		
<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode }" size="12"/>
		</div>
			
<%--<jecs:title key="bdCalculatingSubDetail.name"/>
		<input name="name" type="text" value="${param.name }" size="12"/>--%>
<div class="new_searchBar">	
<jecs:title key="bdBounsDeduct.createName"/>
	<input name="createName" type="text" value="${param.createName }" size="12"/>
	</div>
	
<div class="new_searchBar">	
<jecs:locale key="bdBounsDeduct.createTimeScope"/>

<input name="startCreateTime" id="startCreateTime" type="text" 
			value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endCreateTime" id="endCreateTime" type="text"
			value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			
<div class="new_searchBar">
<jecs:title key="bdBounsDeduct.moneyScope"/>
		<input name="startMoney" type="text" value="${param.startMoney }"  style="cursor: pointer;width:75px;"/>-
			<input name="endMoney" type="text" value="${param.endMoney }"  style="cursor: pointer;width:75px;"/>
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
		<td>	
		</td>
		<td>
		</td>
		<td width="100%">&nbsp;</td>
	</tr>
</table>--%>


		<c:if test="${param.status==1 }">

<button name="search" class="btn btn_ins" type="button" onclick="checkSelected(bdBounsDeductCheckForm1)" >
	<jecs:locale key="operation.button.confirm"/>
</button>
<button name="search" class="btn btn_ins" type="button" onclick="unCheckSelected(bdBounsDeductCheckForm1)" >
	<jecs:locale key="button.bdUnCheck"/>
</button>

			</c:if>
			
			<c:if test="${param.status==2||param.status==3 }">
<button name="search" class="btn btn_ins" type="button" onclick="bdNotCheckedSelected(bdBounsDeductCheckForm1)" >
	<jecs:locale key="button.bdNotChecked"/>
</button>
			
			</c:if>
			<%--<span  style="cursor:pointer">
				<img alt="<jecs:locale key="operation.button.confirm"/>" src="images/newIcons/tick_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key=""/></font>
			</span>
			
			<span onclick="" style="cursor:pointer">
				<img alt="<jecs:locale key="button.bdUnCheck"/>" src="images/newIcons/block_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key=""/></font>
			</span>
			</c:if>
			
			<span onclick="" style="cursor:pointer">
				<img alt="<jecs:locale key="button.bdNotChecked"/>" src="images/icons/unverify.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key=""/></font>
			</span>--%>

</div>
</div>



<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="strActionOperation" value=""/>
<input type="hidden" name="strAdvicesCodes" value=""/>

<%--<table class='grid_table'>
	<tr class='grid_span'>
		<td colspan='10'>	
		</td>
</tr>
</table>--%>


</form>



<form action="bdBounsDeductsCheck.html" method="get" name="bdBounsDeductCheckForm" id="bdBounsDeductCheckForm">
<ec:table 
	tableId="bdBounsDeductCheckListTable"
	items="bdBounsDeductCheckList"
	var="bdBounsDeductCheck"
	form="bdBounsDeductCheckForm"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/bdBounsDeductsCheck.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row>
				
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" >
					<c:if test="${empty bdBounsDeductCheck.deductTime }">
					<input type="checkbox" name="selectedId" id="selectedId" value="${bdBounsDeductCheck.id}" class="checkbox"/>
					</c:if>
					
				</ec:column>
    			<ec:column property="createTime" title="prRefund.createTime" >
    			<fmt:formatDate value="${ bdBounsDeductCheck.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<%--<ec:column property="jmiMember.miUserName" title="bdCalculatingSubDetail.name" />--%>
    			
    			
    			<ec:column property="type" title="bdReconsumMoneyReport.typeCH" styleClass="centerColumn">
    				<jecs:code listCode="bdbounsdeduct.item" value="${bdBounsDeductCheck.type}"/>
    			</ec:column>

    			

    			<ec:column property="money" title="bdBounsDeduct.money" styleClass="numberColumn">
				    <fmt:formatNumber value="${bdBounsDeductCheck.money}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			
    			<ec:column property="status" title="bdBounsDeduct.status" styleClass="centerColumn">
    				<jecs:code listCode="bdbounsdeduct.status" value="${bdBounsDeductCheck.status}"/>
    			</ec:column>

    			<ec:column property="createName" title="bdBounsDeduct.createName" />
    			<ec:column property="summary" title="bdBounsDeduct.summary" />
    			<%--<ec:column property="status" title="title.operation" sortable="false">
					<a href="editBdBounsDeductCheck.html?id=${bdBounsDeductCheck.id }&strAction=view"><jecs:locale key="bdBounsDeduct.view"/></a>
				</ec:column>--%>
				<ec:column property="operation" title="title.operation" sortable="false" styleClass="centerColumn">
					<img style="cursor:pointer" src="images/newIcons/search_16.gif" onclick="window.location.href='editBdBounsDeductCheck.html?id=${bdBounsDeductCheck.id }&strAction=view';" alt="<jecs:locale key="function.menu.view"/>"/>
				</ec:column>
				</ec:row>

</ec:table>	
</form>
<form name="form1" action="<c:url value='editBdBounsDeduct.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='id' id='id'/>
</form>

<script type="text/javascript">

   function editBdBounsDeduct(id){
   		<jecs:power powerCode="editBdBounsDeduct">
					window.location="editBdBounsDeduct.html?strAction=editBdBounsDeduct&id="+id;
			</jecs:power>
		}

</script>
