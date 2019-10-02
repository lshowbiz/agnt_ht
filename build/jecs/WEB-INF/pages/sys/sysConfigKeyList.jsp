<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysConfigKeyList.title"/></title>
<content tag="heading"><jecs:locale key="sysConfigKeyList.heading"/></content>

<form action="" method="get" name="sysConfigKeyForm" id="sysConfigKeyForm">

<div class="searchBar">

	
<table width="100%" border="0">
	<tr>
		<th><jecs:locale key="sysConfigKey.configCode"/>:</th>
		<th><jecs:locale key="sysConfigKey.keyDesc"/></th>
		<th><jecs:locale key="operation.button.search"/></th>
		<th width="100%">&nbsp;</th>
	</tr>
	<tr>
		<td><input name="configCode" type="text" value="${param.configCode }"/></td>
		<td><input name="keyDesc" type="text" value="${param.keyDesc }"/></td>
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
			<jecs:power powerCode="addSysConfigKey">
			<a href="#" onclick="location.href='<c:url value="/editSysConfigKey.html"/>?strAction=addSysConfigKey'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table items="sysConfigKeyList"
	tableId="sysConfigKeyListTable"
	var="sysConfigKey"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/sysConfigKeys.html"
	width="100%"
	rowsDisplayed="20"
	form="sysConfigKeyForm" 
	imagePath="./images/extremetable/*.gif">
	<ec:row onclick="javascript:showSysConfigValues(${sysConfigKey.keyId})">
		<jecs:power powerCode="editSysConfigKey">
		<ec:column property="keyId" title="title.operation" styleClass="centerColumn">
			<a href="editSysConfigKey.html?strAction=editSysConfigKey&keyId=${sysConfigKey.keyId}"><img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="button.edit"/>"/></a>&nbsp;
		</ec:column>
		</jecs:power>
		<ec:column property="configCode" title="sysConfigKey.configCode" />
		<ec:column property="keyDesc" title="sysConfigKey.keyDesc" />
		<ec:column property="defaultValue" title="sysConfigKey.defaultValue" />
	</ec:row>

</ec:table>	

</form>

<script type="text/javascript">

function showSysConfigValues(keyId){
	<jecs:power powerCode="editSysConfigValue">
	window.parent.frmConfigValue.location="editSysConfigValue.html?strAction=editSysConfigValue&keyId="+keyId;
	</jecs:power>
}

</script>