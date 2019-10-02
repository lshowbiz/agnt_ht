<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiZcwMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiZcwMemberList.heading"/></content>
<meta name="menu" content="JmiZcwMemberMenu"/>

<form action="jmiZcwMembers.html" method="get" name="jmiZcwMemberSearchForm" id="jmiZcwMemberSearchForm">

<jecs:power powerCode="addJmiZcwMember">
<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<jecs:title key="label.member.or.agent.code"/>
			<input name="userCode" type="text" value="${param['userCode'] }" size="14"/>
		</c:if>
<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/>


			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiZcwMember.html"/>?strAction=addJmiZcwMember'"
			        value="<jecs:locale key="button.add"/>"/>
		
</div>
</jecs:power>
</form>

<ec:table 
	tableId="jmiZcwMemberListTable"
	items="jmiZcwMemberList"
	var="jmiZcwMember"
	action="${pageContext.request.contextPath}/jmiZcwMembers.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiZcwMember('${jmiZcwMember.zcwId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="zcwDept" title="jmiZcwMember.zcwDept" />
    			<ec:column property="zcwDeptname" title="jmiZcwMember.zcwDeptname" />
    			<ec:column property="userCode" title="jmiZcwMember.userCode" />
    			<ec:column property="userName" title="jmiZcwMember.userName" />
    			<ec:column property="tel" title="jmiZcwMember.tel" />
    			<ec:column property="email" title="jmiZcwMember.email" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiZcwMember(zcwId){
   		<jecs:power powerCode="editJmiZcwMember">
					window.location="editJmiZcwMember.html?strAction=editJmiZcwMember&zcwId="+zcwId;
			</jecs:power>
		}

</script>
