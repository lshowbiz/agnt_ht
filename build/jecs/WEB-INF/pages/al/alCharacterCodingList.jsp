<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterCodingList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterCodingList.heading"/></content>
<meta name="menu" content="AlCharacterCodingMenu"/>

<form action="alCharacterCodings.html?typeId=${typeId }" method="get" name="alCharacterCodingForm" id="alCharacterCodingForm">

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addAlCharacterCoding">
			<a href="#" onclick="window.location.href='<c:url value="/editAlCharacterCoding.html"/>?strAction=addAlCharacterCoding'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table tableId="alCharacterCodingListTable"
	items="alCharacterCodingList" autoIncludeParameters="true"
	var="alVar"
	action="${pageContext.request.contextPath}/alCharacterCodings.html"
	width="100%"
	form="alCharacterCodingForm"
	showPagination="false"
	imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" 
	>
	
	<ec:row>
		<ec:column property="characterId" title="title.operation" sortable="false" style="width:5px;">
			<jecs:power powerCode="editAlCharacterCoding">
				<a href="editAlCharacterCoding.html?strAction=editAlCharacterCoding&characterId=${alVar.characterId}">
				<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
		</ec:column>
		<ec:column property="characterCode" title="alCharacterCoding.characterCode" />
		<ec:column property="characterName" title="alCharacterCoding.characterName"/>
		<ec:column property="allowedUser" title="login.userType.console"/>
	</ec:row>
</ec:table>

</form>