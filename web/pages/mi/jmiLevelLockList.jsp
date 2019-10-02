<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiLevelLockList.title"/></title>
<content tag="heading"><jecs:locale key="jmiLevelLockList.heading"/></content>
<meta name="menu" content="JmiLevelLockMenu"/>

<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>
	</div>
	<div class="new_searchBar">
		<button type="submit" class="btn btn_sele" name="cancel"><jecs:locale key="operation.button.search"/></button>
		<%-- <input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />--%>
		<jecs:power powerCode="addJmiLevelLock">
			<button type="button" class="btn btn_ins" style="margin-right: 5px"
				onclick="location.href='<c:url value="/editJmiLevelLock.html"/>?strAction=addJmiLevelLock'">
				<jecs:locale key="member.new.num"/>
			</button>
			<%-- 
			<input type="button" class="button" style="margin-right: 5px"
				onclick="location.href='<c:url value="/editJmiLevelLock.html"/>?strAction=addJmiLevelLock'"
				        value="<jecs:locale key="member.new.num"/>"/>
			--%>
		</jecs:power>
	</div>
</div>

</form>


<ec:table 
	tableId="jmiLevelLockListTable"
	items="jmiLevelLockList"
	var="jmiLevelLock"
	action="${pageContext.request.contextPath}/jmiLevelLocks.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="memberLevel" title="miMember.cardType" >
    				<jecs:code listCode="member.level" value="${jmiLevelLock.memberLevel}"/>
    			</ec:column><%-- 
    			<ec:column property="isValid" title="sysClickLog.isValid" >
    				<jecs:code listCode="yesno" value="${jmiLevelLock.isValid}"/>
    			</ec:column> --%>
		<%-- 		<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiLevelLock('${jmiLevelLock.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column> --%>
					<%-- 
					<ec:column property="1" title="title.operation" sortable="false" width="100" >
					
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiLevelLock.html?id=${jmiLevelLock.id}&strAction=editJmiLevelLock';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
					
					</ec:column> --%>
				</ec:row>

</ec:table>	

