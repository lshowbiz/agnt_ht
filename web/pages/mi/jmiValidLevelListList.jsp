<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiValidLevelListList.title"/></title>
<content tag="heading"><jecs:locale key="jmiValidLevelListList.heading"/></content>
<meta name="menu" content="JmiValidLevelListMenu"/>



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
		<jecs:power powerCode="addJmiValidLevelList">
			<button type="button" class="btn btn_ins" style="margin-right: 5px"
				onclick="location.href='<c:url value="/editJmiValidLevelList.html"/>?strAction=addJmiValidLevelList'">
				<jecs:locale key="member.new.num"/>
			</button>
			<%-- 
				<input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiValidLevelList.html"/>?strAction=addJmiValidLevelList'"
			        value="<jecs:locale key="member.new.num"/>"/>
			--%>
		</jecs:power>
	 </div>
</div>

</form>


<ec:table 
	tableId="jmiValidLevelListListTable"
	items="jmiValidLevelListList"
	var="jmiValidLevelList"
	action="${pageContext.request.contextPath}/jmiValidLevelLists.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jmiValidLevelList.wweek}" weekType="w"></jecs:weekFormat>
    			</ec:column>
    			<ec:column property="oldMemberLevel" title="member.oldCard" >
    				<jecs:code listCode="member.level" value="${jmiValidLevelList.oldMemberLevel}"/>
    			</ec:column>
    			<ec:column property="newMemberLevel" title="member.newCard" >
    			<jecs:code listCode="member.level" value="${jmiValidLevelList.newMemberLevel}"/>
    			</ec:column><%-- 
    			<ec:column property="isLock" title="sysClickLog.isValid" >
    				<jecs:code listCode="yesno" value="${jmiValidLevelList.isLock}"/>
    			</ec:column> --%>
					<%-- <ec:column property="1" title="title.operation" sortable="false" width="100" >
					
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiValidLevelList.html?id=${jmiValidLevelList.id}&strAction=editJmiValidLevelList';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
					
					</ec:column> --%>
				</ec:row>

</ec:table>	
