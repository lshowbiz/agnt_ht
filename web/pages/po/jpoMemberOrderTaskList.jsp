<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderTaskList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderTaskList.heading"/></content>
<meta name="menu" content="JpoMemberOrderTaskMenu"/>



<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div class="searchBar">

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	
	&nbsp;&nbsp;
	<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		
		
		
		</c:if>
		<jecs:power powerCode="editJpoMemberOrderTask">
		<c:if test="${empty jpoMemberOrderTask }">
		<input name="search" class="button" type="button" onclick="location.href='<c:url value="editJpoMemberOrderTask.html"/>'" value="<jecs:locale key="member.new.num"/>" />
		</c:if>
		<c:if test="${not empty jpoMemberOrderTask }">
		<input name="search" class="button" type="button" onclick="location.href='<c:url value="editJpoMemberOrderTask.html"/>'" value="<jecs:locale key="function.menu.view"/>" />
		</c:if>
		
		</jecs:power>
</div>
</form>

<ec:table 
	tableId="jpoMemberOrderTaskListTable"
	items="jpoMemberOrderTaskList"
	var="jpoMemberOrderTask"
	action="${pageContext.request.contextPath}/jpoMemberOrderTasks.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="status" title="customerFollow.state" >
	    					<jecs:code listCode="jpoMemberOrderTask.status" value="${jpoMemberOrderTask.status }"/>
    			</ec:column>
    			<ec:column property="remark" title="busi.common.remark" />
    			<ec:column property="actionDate" title="jpoMemberOrderTask.actionDate" />
    			<ec:column property="actionWeek" title="jpoMemberOrderTask.actionWeek" />
    			<ec:column property="actionTime" title="pd.createTime" />
				</ec:row>

</ec:table>	


