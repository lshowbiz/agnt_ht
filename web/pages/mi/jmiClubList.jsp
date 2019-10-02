<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><fmt:message key="jmiAddrBookList.title"/></title>
<content tag="heading"><fmt:message key="jmiAddrBookList.heading"/></content>
<meta name="menu" content="JmiAddrBookMenu"/>


<form action="" method="get" name="miMemberForm" id="miMemberForm">

	<div class="searchBar">
		
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
			<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
	
			
		
	<c:if test="${not empty param.userCode && sessionScope.sessionLogin.userType!='C'}">
		
			
	<c:if test="${not empty jmiClubList}">
		会员：${param.userCode } 已达成
	</c:if>
	<c:if test="${  empty jmiClubList}">
		会员：${param.userCode } 未达成
	</c:if>
			
			
	</c:if>
	</div>
</form>


		<c:if test="${sessionScope.sessionLogin.userType=='C'}">

<ec:table 
	tableId="jmiClubsListTable"
	items="jmiClubList"
	var="jmiClub"
	action="${pageContext.request.contextPath}/jmiClubs.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="images/extremetable/*.gif">
				<ec:row>
	    			<ec:column property="user_code" title="miMember.memberNo" />
	    			<ec:column property="user_name" title="bdCalculatingSubDetail.name" />
    				<ec:column property="create_time" title="miMember.createTime" />
	    			
				</ec:row>

</ec:table>	
	</c:if>

