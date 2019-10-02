<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysManagerUserList.title"/></title>
<content tag="heading"><jecs:locale key="sysManagerUserList.heading"/></content>
<meta name="menu" content="SysManagerUserMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysManagerUser">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysManagerUser.html"/>?strAction=addSysManagerUser'"
			     value="<jecs:locale key="member.new.num"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysManagerUserListTable"
	items="sysManagerUserList"
	var="sysManagerUser"
	action="${pageContext.request.contextPath}/sysManagerUsers.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysManagerUser('${sysManagerUser.rollId}')">
    			<ec:column property="masterUserCode" title="sysManagerUser.masterUserCode" />
    			<ec:column property="slaveUserCode" title="sysManagerUser.slaveUserCode" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysManagerUser.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='rollId' id='rollId'/>
</form>

<script type="text/javascript">

   function editSysManagerUser(rollId){
   		<jecs:power powerCode="editSysManagerUser">
					window.location="editSysManagerUser.html?strAction=editSysManagerUser&rollId="+rollId;
			</jecs:power>
		}

</script>
