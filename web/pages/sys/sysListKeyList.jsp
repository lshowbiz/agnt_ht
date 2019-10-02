<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysListKeyList.title"/></title>
<content tag="heading"><jecs:locale key="sysListKeyList.heading"/></content>

<form action="" method="get" name="sysListKeyForm" id="sysListKeyForm">

<div class="searchBar">
<table width="100%" border="0">
	<tr>
		<th><jecs:locale key="sysListKey.listCode"/></th>
		<th><jecs:locale key="sysListKey.listName"/></th>
		<th><jecs:locale key="operation.button.search"/></th>
		<th width="100%">&nbsp;</th>
	</tr>
	<tr>
		<td><input name="listCode" type="text" value="${param.listCode }"/></td>
		<td><input name="listName" type="text" value="${param.listName }"/></td>
		<td>
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
		</td>
		<td>&nbsp;<a href="http://e4.jmtop.com/jecs/nosec/reload" target="_self">@</a></td>
	</tr>
</table>
</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysListKey">
			<a href="#" onclick="window.location.href='<c:url value="/editSysListKey.html"/>?strAction=addSysListKey'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table items="sysListKeyList"
	tableId="sysListKeyListTable"
	var="sysListKey"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/sysListKeys.html"
	width="100%"
	rowsDisplayed="20"
	form="sysListKeyForm"
	imagePath="./images/extremetable/*.gif">
	
	<ec:row onclick="javascript:showSysListValues(${sysListKey.keyId})">
		<ec:column property="keyId" title="title.operation" styleClass="centerColumn" style="width:5px;">
			<nobr>
 			<c:if test="${sysListKey.isReadOnly!=1}">
 			<jecs:power powerCode="editSysListKey">
			<a href="editSysListKey.html?strAction=editSysListKey&keyId=${sysListKey.keyId}"><img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="button.edit"/>"/></a>
			</jecs:power>
			</c:if>
			&nbsp;
			</nobr>
		</ec:column>
 		<ec:column property="listCode" title="sysListKey.listCode" />
 		<ec:column property="listName" title="sysListKey.listName" >
 			<jecs:locale key="${sysListKey.listName}"/>
 		</ec:column>
	</ec:row>
</ec:table>	

</form>

<script type="text/javascript">
function showSysListValues(keyId){
	<jecs:power powerCode="listSysListValues">
	window.parent.frmListValue.location="sysListValues.html?strAction=listSysListValues&keyId="+keyId;
	</jecs:power>
}
</script>