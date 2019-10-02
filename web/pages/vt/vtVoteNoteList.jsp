<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVoteNoteList.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteNoteList.heading"/></content>
<meta name="menu" content="VtVoteNoteMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addVtVoteNote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editVtVoteNote.html"/>?strAction=addVtVoteNote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="vtVoteNoteListTable"
	items="vtVoteNoteList"
	var="vtVoteNote"
	action="${pageContext.request.contextPath}/vtVoteNotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editVtVoteNote('${vtVoteNote.vnId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="vdId" title="vtVoteNote.vdId" />
    			<ec:column property="userCode" title="vtVoteNote.userCode" />
    			<ec:column property="createTime" title="vtVoteNote.createTime" />
    			<ec:column property="companyCode" title="vtVoteNote.companyCode" />
    			<ec:column property="userType" title="vtVoteNote.userType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editVtVoteNote(vnId){
   		<jecs:power powerCode="editVtVoteNote">
					window.location="editVtVoteNote.html?strAction=editVtVoteNote&vnId="+vnId;
			</jecs:power>
		}

</script>
