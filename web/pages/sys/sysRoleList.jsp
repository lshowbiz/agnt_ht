<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysRoleList.title"/></title>
<content tag="heading"><jecs:locale key="sysRoleList.heading"/></content>
<meta name="menu" content="SysRoleMenu"/>

<form action="sysRoles.html" method="get" name="sysRoleSearchForm" id="sysRoleSearchForm">
<div class="searchBar">

	
		<c:if test="${not empty alCompanys}">
			<div class="new_searchBar">	
				<jecs:title key="bdReconsumMoneyReport.companyCH"/>
				<select name="companyCode">
					<option value=""></option>
					<c:forEach items="${alCompanys}" var="alCompanyVar">
						<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==param.companyCode}"> selected</c:if>>${alCompanyVar.companyCode } - ${alCompanyVar.companyName }</option>
					</c:forEach>
				</select>
			</div>
		</c:if>
		<div class="new_searchBar">	
			<jecs:title key="sysRole.userType"/>
			<select name="userType">
			  <option value=""></option>
			  <c:forEach items="${userTypes}" var="userTypeVar">
				<option value="${userTypeVar.key }" <c:if test="${userTypeVar.key==param.userType }"> selected</c:if>>
				  <jecs:locale key="${userTypeVar.value }"/></option>
			  </c:forEach>
			</select>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="sysRole.roleCode"/>
			<input name="roleCode" type="text" value="${param.roleCode }" size="14"/>
		</div>	
		<div class="new_searchBar" style="margin-left:65px;">
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
</div>
</form>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysRole">
			<a href="#" onclick="location.href='<c:url value="/editSysRole.html"/>?strAction=addSysRole'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	
</table>

<ec:table 
	tableId="sysRoleListTable"
	items="sysRoleList"
	var="sysRole"
	action="${pageContext.request.contextPath}/sysRoles.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="roleId" title="title.operation" sortable="false" style="white-space: nowrap;width:70px;">
			&nbsp; 
			<jecs:power powerCode="roleUserManage">
				<img src="<c:url value='/images/bonus_tree/tree_group_pv.gif'/>" onclick="editUsers('${sysRole.roleId}','${sysRole.roleName }')"  style="cursor: pointer;" title="<jecs:locale key="menu.systemPowerManage.roleManage"/>"/>
			</jecs:power> 
			&nbsp;
			<jecs:power powerCode="sysRoleCompanyUser">
				<a href="sys_role_company_user.html?roleId=${sysRole.roleId}"><img src="<c:url value='/images/bonus_tree/tree_group_pv.gif'/>" title="<jecs:locale key="menu.systemPowerManage.roleManage"/>" border="0"/></a>
			</jecs:power> 
			&nbsp;
			<jecs:power powerCode="editSysRoleResource">
				<img src="<c:url value='/images/icons/unLock.gif'/>" onclick="editSysRoleResource('${sysRole.roleId}')"  style="cursor: pointer;" title="<jecs:locale key="sys.editroleresource"/>"/>
			</jecs:power>
		</ec:column>
		<ec:column property="companyCode" title="bdReconsumMoneyReport.companyCH" />
		<ec:column property="userType" title="sysRole.userType" >
			<jecs:code listCode="user_type" value="${sysRole.userType}"/>
		</ec:column>
		<ec:column property="roleCode" title="sysRole.roleCode" />
		<ec:column property="roleName" title="sysRole.roleName" />
		<ec:column property="roleDes" title="sysRole.roleDes" />
		<ec:column property="available" title="sysClickLog.isValid">
			<jecs:code listCode="yesno" value="${sysRole.available}"/>
		</ec:column>
	</ec:row>

</ec:table>	

<script type="text/javascript">
	
function editUsers(roleId,roleName){
	<jecs:power powerCode="roleUserManage">
		window.location="sysUserRoles.html?strAction=roleUserManage&roleId="+roleId;
	</jecs:power>
}
function editSysRole(roleId){
	<jecs:power powerCode="editSysRole">
	window.location="editSysRole.html?strAction=editSysRole&roleId="+roleId;
	</jecs:power>
}
function editSysRoleResource(roleId){
	<jecs:power powerCode="editSysRoleResource">
	window.location="editSysRoleResource.html?strAction=editSysRoleResource&roleId="+roleId;
	</jecs:power>
}
</script>