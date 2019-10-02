<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterKeyList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterKeyList.heading"/></content>

<div class="searchBar">

<table width="100%" border="0" cellpadding="1" cellspacing="0">
	<form action="alCharacterKeys.html" method="get" name="searchForm" id="searchForm">
	<tr>
		<th><jecs:locale key="alCharacterKey.characterKey"/></th>
		<th><jecs:locale key="alCharacterKey.keyDesc"/></th>
		<th><jecs:locale key="operation.button.search"/></th>
		<th width="100%">&nbsp;</th>
	</tr>
	<tr>
		<td><input name="characterKey" type="text" value="${param.characterKey }" size="35"/></td>
		<td><input name="keyDesc" type="text" value="${param.keyDesc }" size="35"/></td>
		<td><button name="search" class="btn btn_sele" type="submit"><jecs:locale key="operation.button.search"/></button></td>
		<td>
		<input name="typeId" type="hidden" value="${param.typeId }"/>
		<input name="strAction" type="hidden" value="listAlCharacterKeys"/>
		<a href="http://e4.jmtop.com/jecs/nosec/reload" target="_self">@</a>
		</td>
	</tr>
	</form>
</table>

</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addAlCharacterKey">
			<a href="#" onclick="window.location.href='<c:url value="/editAlCharacterKey.html"/>?strAction=addAlCharacterKey&typeId=${param.typeId }'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color="black"><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<form action="alCharacterKeys.html?typeId=${typeId }" method="get" name="alCharacterKeyForm" id="alCharacterKeyForm">
<ec:table tableId="alCharacterKeyListTable"
	items="alCharacterKeyList" autoIncludeParameters="true"
	var="alVar"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/alCharacterKeys.html"
	width="100%"
	rowsDisplayed="20" form="alCharacterKeyForm" bufferView="true"
	imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" 
	>
	
	<ec:exportCsv fileName="character_key.csv"/>
	<ec:exportXls fileName="character_key.xls"/>
	
	<ec:row>
		<ec:column alias="selectedId" headerCell="selectAll" style="width:5px;">  
  			<input type="checkbox" name="selectedId" value="${alVar.keyId}"/>
  		</ec:column>
  		<ec:column property="operation" title="title.operation" sortable="false" style="width:5px;">
  			<nobr>
			<jecs:power powerCode="editAlCharacterKey">
			<a href="editAlCharacterKey.html?strAction=editAlCharacterKey&keyId=${alVar.keyId}">
			<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="alCharacterKey.link.editAlCharacterKey"/>"/>
			</a>
			</jecs:power>
			&nbsp;
			<jecs:power powerCode="editAlCharacterValue">
			<a href="javascript:editCharacterValue(${alVar.keyId})">
			<img src="images/newIcons/globe_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="alCharacterKey.link.editAlCharacterValue"/>"/>
			</a>
			</jecs:power>
			</nobr>
		</ec:column>
		<ec:column property="characterKey" title="alCharacterKey.characterKey" />
		<ec:column property="keyDesc" title="alCharacterKey.keyDesc" escapeAutoFormat="true"/>
		<ec:column property="alCharacterType.typeId" title="alCharacterType.parentId">
			<c:if test="${not empty alVar.alCharacterType.typeId}">
		    	<jecs:locale key="${alVar.alCharacterType.typeName }"/>
	        </c:if>
	        <c:if test="${empty alVar.alCharacterType.typeId}">
		    	<jecs:locale key="alCharacterType.allType"/>
	        </c:if>
		</ec:column>
	</ec:row>
</ec:table>

</form>
<br>
<jecs:power powerCode="changeAlCharacterKeyType">
<div class="searchBar">
<table width="100%" border="0" cellpadding="1" cellspacing="0">
	<form action="alCharacterKeys.html" method="post" name="typeForm" id="typeForm" onsubmit="return validateForm(this)">
	<tr>
		<th><jecs:locale key="alCharacterType.newTypeName"/></th>
		<th><jecs:locale key="sysOperationLog.moduleName"/></th>
		<th width="100%">&nbsp;</th>
	</tr>
	<tr>
		<td>
			<input name="newTypeId" type="hidden"/>
			<input name="newTypeName" type="text" class="readonly" readonly/>
			<button type="button" class="btn btn_sele" name="btnSelectNewType" onclick="selectAlCharacterType(this.form)">
				<jecs:locale key="button.selectNewType"/>
			</button>
		</td>
		<td>
		<input type="submit" class="button" name="change" value="<jecs:locale key="button.changeType"/>"/>
		
		<input name="keyIds" type="hidden"/>
		<input type="hidden" name="encodedQueryString" value="${encodedQueryString}"/>
		<input type="hidden" name="strAction" value="changeAlCharacterKeyType"/>		</td>
		<td>&nbsp;</td>
	</tr>
	</form>
</table>
</div>
</jecs:power>

<script>
function editCharacterValue(keyId){
	<jecs:power powerCode="editAlCharacterValue">
	parent.window.frmCharacterValue.location="editAlCharacterValue.html?strAction=editAlCharacterValue&keyId="+keyId;
	</jecs:power>
}


function validateForm(theForm){
	if(theForm.newTypeId.value==""){
		alert("<jecs:locale key="alCharacterType.please.select.newType"/>");
		return false;
	}
	
	var selectedIds=document.getElementsByName("selectedId");
	var selected=false;
	var willChangedIds="";
	var j=0;
	for(var i=0;i<selectedIds.length;i++){
		if(selectedIds[i].checked){
			selected=true;
			if(j>0){
				willChangedIds+=",";
			}
			willChangedIds+=selectedIds[i].value;
			j++;
		}
	}
	
	if(!selected){
		alert("<jecs:locale key="alCharacterType.please.select.alCharacterKey"/>");
		return false;
	}
	theForm.keyIds.value=willChangedIds;
	return true;
}

function selectAlCharacterType(theForm){
	var pars=new Array();
	pars[0]="<jecs:locale key="alCharacterType.addType"/>";
	pars[1]="al_select_character_type.html?typeId=${param.typeId}";
	pars[2]=window;
	var ret=showDialogWin("<%=request.getContextPath()%>",pars,250,300);
	if(ret!=undefined && ret!=""){
		theForm.newTypeId.value=ret.typeId;
		theForm.newTypeName.value=ret.typeName;
	}
}

function selectAll(theForm,status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
</script>