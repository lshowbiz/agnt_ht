<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiGradeLockList.title"/></title>
<content tag="heading"><jecs:locale key="jmiGradeLockList.heading"/></content>
<meta name="menu" content="JmiGradeLockMenu"/>






<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div class="searchBar">

	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
	</div>
	<div class="new_searchBar">
		<button type="submit" class="btn btn_sele" name="cancel">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%-- 
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		--%>
		<jecs:power powerCode="addJmiGradeLock">
			<button type="button" class="btn btn_ins"
				onclick="location.href='<c:url value="/editJmiGradeLock.html"/>?strAction=addJmiGradeLock'">
				<jecs:locale key="member.new.num"/>
			</button>
			<%-- 
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiGradeLock.html"/>?strAction=addJmiGradeLock'"
			        value="<jecs:locale key="member.new.num"/>"/>
			 --%>
		</jecs:power>
		<jecs:power powerCode="batchUpdateJmiGradeLock">
			<button type="button" class="btn btn_ins" onclick="location.href='<c:url value="/batchUpdateJmiGradeLock.html"/>'">
				批量修改身份
			</button>
			<%-- 
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/batchUpdateJmiGradeLock.html"/>'"
			        value="批量修改身份"/>
			--%>
		</jecs:power>
	</div>
</div>

</form>







<ec:table 
	tableId="jmiGradeLockListTable"
	items="jmiGradeLockList"
	var="jmiGradeLock"
	action="${pageContext.request.contextPath}/jmiGradeLocks.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="gradeType" title="miMember.gradeType" >
    				<jecs:code listCode="grade.type" value="${jmiGradeLock.gradeType}"/>
    			</ec:column>
    			<ec:column property="validWeek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jmiGradeLock.validWeek}" weekType="w"></jecs:weekFormat>
    			</ec:column>
				</ec:row>

</ec:table>	

