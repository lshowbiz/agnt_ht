<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysIdList.title"/></title>
<content tag="heading"><jecs:locale key="sysIdList.heading"/></content>

<form action="" method="get" name="sysIdForm" id="sysIdForm">

<div class="searchBar">
<table width="100%" border="0" cellpadding="1" cellspacing="0">
	<tr>
		<th><jecs:locale key="sysId.idName"/></th>
		<th><jecs:locale key="sysId.idCode"/></th>
		<th><jecs:locale key="operation.button.search"/></th>
		<th width="100%">&nbsp;</th>
	</tr>
	<tr>
		<td><input name="idName" type="text" value="${param.idName }"/></td>
		<td><input name="idCode" type="text" value="${param.idCode }"/></td>
		<td>
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysId">
			<a href="#" onclick="window.location.href='<c:url value="/editSysId.html"/>?strAction=addSysId'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table 
	items="sysIdList"
	tableId="sysIdListTable"
	var="sysId"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/sysIds.html"
	width="100%"
	form="sysIdForm"
	rowsDisplayed="20" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="id" title="title.operation" sortable="false" style="white-space: nowrap;width:5px;">
			<nobr>
			<jecs:power powerCode="editSysId">
				<c:if test="${sysId.fixed!=1}">
					<a href="editSysId.html?strAction=editSysId&id=${sysId.id}">
					<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
				</c:if>
			</jecs:power>
			&nbsp;
			</nobr>
		</ec:column>
		<ec:column property="idName" title="sysId.idName" />
		<ec:column property="idValue" title="sysId.idValue" />
		<ec:column property="idCode" title="sysId.idCode" />
		<ec:column property="dateFormat" title="alCompany.dateFormat" />
		<ec:column property="dateValue" title="sysId.dateValue" />
		<ec:column property="prefix" title="sysId.prefix" />
		<ec:column property="postfix" title="sysId.postfix" />
		<ec:column property="idLength" title="sysId.idLength" />
		<ec:column property="fixed" title="sysId.fixed" />
		<ec:column property="seqTable" title="sysId.seqTable" />
		<ec:column property="version" title="sysId.version" />
		<ec:column property="infix" title="sysId.infix" />
	</ec:row>

</ec:table>	

</form>