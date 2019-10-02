<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCubMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jbdCubMemberList.heading"/></content>
<meta name="menu" content="JbdCubMemberMenu"/>

<style>
<!--
table.detail th {
	text-align: left !important;
}
-->
</style>

<%-- <ec:table 
	tableId="jbdCubMemberListTable"
	items="jbdCubMemberList"
	var="jbdCubMember"
	action="${pageContext.request.contextPath}/jbdCubMembers.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
			
    			<ec:column property="dapartment" title="jbdCubMember.status" />
    			<ec:column property="userCode" title="jbdCubMember.status" />
    			<ec:column property="userName" title="jbdCubMember.status" />
    			<ec:column property="wMonth" title="jbdCubMember.status" />
    			<ec:column property="passStar" title="jbdCubMember.status" />
    			<ec:column property="teamCode" title="jbdCubMember.status" />
    			<ec:column property="hgCount" title="jbdCubMember.status" />
    			
				</ec:row>

</ec:table>	 --%>
<div class="searchBar">
	<button name="return" class="btn btn_sele" type="button" onclick="javascritp:history.back();" >
		<jecs:locale key="operation.button.return"/>
	</button>
</div>
<table class="detail table-ct" width="100%">
	<tr>
		<th>部门</th>
		<th>会员编号</th>
		<th>会员姓名</th>
		<th>财月</th>
		<th>奖衔</th>
		<th>达到皇冠的次数</th>
		<th>星级特使编号</th>
	</tr>
	<c:forEach items="${jbdCubMemberlist }" var="list" >
	<tr>
		<td><jecs:code listCode="cub.department" value="${list.dapartment }"/></td>
		<td>${list.userCode }</td>
		<td>${list.userName }</td>
		<td>${list.wMonth }</td>
		<td><jecs:code listCode="pass.star.zero" value="${list.passStar }"/></td>
		<td>${list.hgCount }</td>
		<td>${list.teamCode }</td>
	</tr>
	</c:forEach>
</table>

