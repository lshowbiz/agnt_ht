<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx }/scripts/jquery/jquery-142min.js"> </script>

<form action="" method="get" name="jbdMemberCongrations" id="jbdMemberCongrations">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>	
		<input name="userCode" type="text" id="query_userCode" value="${param.userCode }" size="10"/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="bdPeriod.fmonth"/>	
		<input name="yearMonth" type="text" id="query_yearMonth" value="${param.yearMonth }" size="8"/>
	</div>
	<div class="new_searchBar">	
		<jecs:label key="pass.star.type"/>
		<jecs:list name="starLevel" id="query_starLevel" listCode="qualify.star.zero" 
		defaultValue="" value="${param.starLevel }" showBlankLine="true"></jecs:list>
	</div>
	<div class="new_searchBar" style="margin-left: 45px">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%--<input name="search" type="submit" class="button" value="<jecs:locale key="operation.button.search"/>"/>--%>
	</div>	
</div>

</form>


<ec:table 
	tableId="JbdMemberCongrationsTable"
	items="jbdMemberCongrations"
	var="jbdMemberCongration"
	action="${pageContext.request.contextPath}/jbdMemberCongrations.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:exportXls fileName="jbdMemberCongrations.xls"/>
    			<ec:column property="userCode" title="miMember.memberNo" style="text-align:center"/>
    			<ec:column property="yearMonth" title="bdPeriod.fmonth"  style="text-align:center"/>
    			<ec:column property="starLevel" title="pass.star.type"  style="text-align:center" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			<jecs:code listCode="qualify.star.zero" value="${jbdMemberCongration.starLevel}"/>
    			</ec:column>
    			<ec:column property="chineseName" title="chinese.name"  style="text-align:center"/>
    			<ec:column property="englishName" title="english.name"  style="text-align:center"/>
    			<ec:column property="remark" title="schedule.remark"  style="text-align:center">
    				<span title="${jbdMemberCongration.remark}">${jbdMemberCongration.remark}</span>
    			</ec:column>
    			
    			<ec:column property="2" title="title.operation" sortable="false"  style="text-align:center">
    			
    			<jecs:power powerCode="editJbdMemberCongrations">
    			<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdMemberCongrations.html?id=${jbdMemberCongration.id}&strAction=editJbdMemberCongrations';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
    			</jecs:power>
    			<jecs:power powerCode="deleteJbdMemberCongrations">
					<a onclick="deleteConfirm(${jbdMemberCongration.id});"><img border="0" src="images/icons/w.gif" alt="<jecs:locale key="operation.button.delete"/>" /></a>
				</jecs:power>
    			</ec:column>
    			
				</ec:row>

</ec:table>	

<script type="text/javascript">
var tips = "<jecs:locale key='amMessage.confirmdelete'/>";
function deleteConfirm(id) {
	if(window.confirm(tips)) {
		var userCode=$("#query_userCode").val();
		if(!userCode)userCode="";
		var yearMonth=$("#query_yearMonth").val();
		if(!yearMonth)yearMonth="";
		var starLevel=$("#query_starLevel").val();
		if(!starLevel)starLevel="";
		window.location.href="jbdMemberCongrations.html?id="+id+"&userCode="+userCode+"&yearMonth="+yearMonth+"&starLevel="+starLevel+"&strAction=deleteJbdMemberCongrations";
	}
}
</script>