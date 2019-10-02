<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysManagerList.title"/></title>
<content tag="heading"><jecs:locale key="sysManagerList.heading"/></content>
<meta name="menu" content="SysManagerMenu"/>

<div class="topNavBar">
	<jecs:locale key="title.manager.list"/>, <jecs:locale key="sysDepartment.current.selected"/>: <b>${alCompany.companyName } - <jecs:locale key="${sysDepartment.departmentName }"/></b>
</div>

<c:if test="${not empty param.departmentId && param.departmentId!=0}">
<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysManager">
			<c:if test="${hasPermit==true}">
			<a href="#" onclick="location.href='<c:url value="/editSysManager.html"/>?strAction=addSysManager&companyCode=${param.companyCode }&departmentId=${param.departmentId }'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key="member.new.num"/><jecs:locale key="login.userType.console"/></font>
			</a>
			</c:if>
			</jecs:power>
		</td>
	</tr>
</table>
	
<ec:table 
	tableId="sysManagerListTable"
	items="sysManagerList"
	var="sysManager"
	action="${pageContext.request.contextPath}/sysManagers.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="1" title="title.operation" sortable="false" styleClass="centerColumn" style="white-space: nowrap;width:5px;">
			<nobr>
			<jecs:power powerCode="editSysManager">
			<a href="javascript:editSysManager('${sysManager.userCode}')"><img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="button.edit"/>"/></a>
			</jecs:power>
			&nbsp;
			<jecs:power powerCode="sysModifyPwd">
			<a href="javascript:changePassword('${sysManager.userCode}')"><img src="images/newIcons/key_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="title.change.password"/>"/></a>
			</jecs:power>
			</nobr>
		</ec:column>
		<ec:column property="userCode" title="sysUser.userCode" />
		<ec:column property="sysUser.userName" title="sysUser.userName" />
		<ec:column property="email" title="sysUser.email" />
		<ec:column property="tel" title="alCompany.phone" />
		<ec:column property="mobile" title="sysUser.mobile" />
		<ec:column property="sysUser.state" title="sysUser.state" styleClass="centerColumn">
			<jecs:code listCode="yesno" value="${sysManager.sysUser.state}"/>
		</ec:column>
	</ec:row>

</ec:table>	

<script type="text/javascript">

function editSysManager(userCode){
	<jecs:power powerCode="editSysManager">
		window.location="editSysManager.html?strAction=editSysManager&userCode="+userCode;
	</jecs:power>
}

function changePassword(userCode){
	<jecs:power powerCode="sysModifyPwd">
	window.location="sys_modify_pwd.html?strAction=sysModifyPwd&modifyType=other&userCode="+userCode;
	</jecs:power>
}
</script>
</c:if>