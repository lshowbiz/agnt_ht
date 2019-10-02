<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVoteList.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteList.heading"/></content>
<meta name="menu" content="VtVoteMenu"/>

<form action="" method="get" name="voteForm" id="voteForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="strActionOperation" value=""/>
<input type="hidden" name="strAdvicesCodes" value=""/>


<div id="titleBar" class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="customerRecord.subject"/>
		<input name="subject" type="text" value="${param.subject}" size="10"/>	
	</div>
	<div class="new_searchBar">
		<button type="submit" class="btn btn_sele" style="width:auto" name="cancel"><jecs:locale key="operation.button.search"/></button>
	</div>

</div>
<!--  
<div id="titleBar" class="searchBar">
			<jecs:title key="customerRecord.subject"/>
			<input name="subject" type="text" value="${param.subject}" size="10"/>	

			<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />

</div>

-->

</form>


<ec:table 
	tableId="vtVoteListTable"
	items="vtVoteList"
	var="vtVote"
	action="${pageContext.request.contextPath}/vtVotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<c:if test="${param.strAction=='auditVote' }">
					<ec:column property="2" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" >
						<input type="checkbox" name="selectedId" id="selectedId" value="${vtVote.vtId}" class="checkbox"/>
					</ec:column>
				</c:if>
    			<ec:column property="subject" title="customerRecord.subject" />
    			<ec:column property="optionNum" title="vtVote.optionNum" />
    			<%--<ec:column property="startTime" title="common.startTime" />--%>
    			<ec:column property="endTime" title="customerFollow.endTime" />
 
    			<%--<ec:column property="createTime" title="pd.createTime" />
    			<ec:column property="createUser" title="pd.createUserNo" />--%>
    			
					<ec:column property="1" title="title.operation" sortable="false" width="100" >
						
					<jecs:power powerCode="editVtVotePoll">
						<img src="images/newIcons/vote.gif" onclick="window.location.href='editVtVotePoll.html?strAction=editVtVotePoll&vtId=${vtVote.vtId }';" alt="<jecs:locale key="menu.vote.poll"/>" style="cursor:pointer"/>
					</jecs:power>
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editVtVote(vtId){
   		<jecs:power powerCode="editVtVote">
					window.location="editVtVote.html?strAction=editVtVote&vtId="+vtId;
			</jecs:power>
		}

</script>
