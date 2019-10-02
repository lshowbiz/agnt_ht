<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdGradeNoteList.title"/></title>
<content tag="heading"><jecs:locale key="jbdGradeNoteList.heading"/></content>
<meta name="menu" content="JbdGradeNoteMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdGradeNote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdGradeNote.html"/>?strAction=addJbdGradeNote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdGradeNoteListTable"
	items="jbdGradeNoteList"
	var="jbdGradeNote"
	action="${pageContext.request.contextPath}/jbdGradeNotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdGradeNote('${jbdGradeNote.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jbdGradeNote.userCode" />
    			<ec:column property="oldHonorStar" title="jbdGradeNote.oldHonorStar" />
    			<ec:column property="newHonorStar" title="jbdGradeNote.newHonorStar" />
    			<ec:column property="oldPassStar" title="jbdGradeNote.oldPassStar" />
    			<ec:column property="newPassStar" title="jbdGradeNote.newPassStar" />
    			<ec:column property="createTime" title="jbdGradeNote.createTime" />
    			<ec:column property="createNo" title="jbdGradeNote.createNo" />
    			<ec:column property="wweek" title="jbdGradeNote.wweek" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdGradeNote(id){
   		<jecs:power powerCode="editJbdGradeNote">
					window.location="editJbdGradeNote.html?strAction=editJbdGradeNote&id="+id;
			</jecs:power>
		}

</script>
