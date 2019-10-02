<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiLevelNoteList.title"/></title>
<content tag="heading"><jecs:locale key="jmiLevelNoteList.heading"/></content>
<meta name="menu" content="JmiLevelNoteMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiLevelNote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiLevelNote.html"/>?strAction=addJmiLevelNote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiLevelNoteListTable"
	items="jmiLevelNoteList"
	var="jmiLevelNote"
	action="${pageContext.request.contextPath}/jmiLevelNotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiLevelNote('${jmiLevelNote.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jmiLevelNote.userCode" />
    			<ec:column property="oldMemberLevel" title="jmiLevelNote.oldMemberLevel" />
    			<ec:column property="newMemberLevel" title="jmiLevelNote.newMemberLevel" />
    			<ec:column property="updateType" title="jmiLevelNote.updateType" />
    			<ec:column property="createTime" title="jmiLevelNote.createTime" />
    			<ec:column property="createNo" title="jmiLevelNote.createNo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiLevelNote(id){
   		<jecs:power powerCode="editJmiLevelNote">
					window.location="editJmiLevelNote.html?strAction=editJmiLevelNote&id="+id;
			</jecs:power>
		}

</script>
