<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberCompanyNoteList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberCompanyNoteList.heading"/></content>
<meta name="menu" content="JmiMemberCompanyNoteMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiMemberCompanyNote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiMemberCompanyNote.html"/>?strAction=addJmiMemberCompanyNote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<div id="titleBar" class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>
		</div>
		<div class="new_searchBar">
			<button type="submit" class="btn btn_sele" name="cancel">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>
<ec:table 
	tableId="jmiMemberCompanyNoteListTable"
	items="jmiMemberCompanyNoteList"
	var="jmiMemberCompanyNote"
	action="${pageContext.request.contextPath}/jmiMemberCompanyNotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="oldCompany" title="jbdUserCompanyCode.oldCompany" >
    			<jecs:company name="oldCompany" selected="${jmiMemberCompanyNote.oldCompany }"  prompt="" withAA="false" label="true" />	
    			</ec:column>
    			<ec:column property="newCompany" title="jbdUserCompanyCode.newCompany" >
    			<jecs:company name="newCompany" selected="${jmiMemberCompanyNote.newCompany }"  prompt="" withAA="false" label="true" />	
    			</ec:column>
    			
    			<ec:column property="createUser" title="sysOperationLog.operaterCode" />
    			<ec:column property="createTime" title="sysOperationLog.operateTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiMemberCompanyNote(id){
   		<jecs:power powerCode="editJmiMemberCompanyNote">
					window.location="editJmiMemberCompanyNote.html?strAction=editJmiMemberCompanyNote&id="+id;
			</jecs:power>
		}

</script>
