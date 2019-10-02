<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiEcmallNoteList.title"/></title>
<content tag="heading"><jecs:locale key="jmiEcmallNoteList.heading"/></content>
<meta name="menu" content="JmiEcmallNoteMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiEcmallNote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiEcmallNote.html"/>?strAction=addJmiEcmallNote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiEcmallNoteListTable"
	items="jmiEcmallNoteList"
	var="jmiEcmallNote"
	action="${pageContext.request.contextPath}/jmiEcmallNotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiEcmallNote('${jmiEcmallNote.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jmiEcmallNote.userCode" />
    			<ec:column property="url" title="jmiEcmallNote.url" />
    			<ec:column property="info" title="jmiEcmallNote.info" />
    			<ec:column property="createTime" title="jmiEcmallNote.createTime" />
    			<ec:column property="createNo" title="jmiEcmallNote.createNo" />
    			<ec:column property="code" title="jmiEcmallNote.code" />
    			<ec:column property="noteTyoe" title="jmiEcmallNote.noteTyoe" />
    			<ec:column property="remark" title="jmiEcmallNote.remark" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiEcmallNote(id){
   		<jecs:power powerCode="editJmiEcmallNote">
					window.location="editJmiEcmallNote.html?strAction=editJmiEcmallNote&id="+id;
			</jecs:power>
		}

</script>
