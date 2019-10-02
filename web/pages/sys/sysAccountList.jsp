<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysManagerList.title"/></title>
<content tag="heading"><jecs:locale key="sysManagerList.heading"/></content>
<meta name="menu" content="SysManagerMenu"/>


<form action="sysAccounts.html" method="get" name="sysAccountSearchForm" id="sysAccounts.html">
<div class="searchBar">
<table width="100%" border="0">
	<tr>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<th><jecs:locale key="bdReconsumMoneyReport.companyCH"/></th>
		</c:if>
		<th align="right" nowrap="nowrap"><jecs:locale key="sysUser.loginCode"/></th>
		<th><jecs:locale key="sysUser.userName"/></th>
		<th nowrap="nowrap"><jecs:locale key="sysUser.state"/></th>
		<th><jecs:locale key="operation.button.search"/></th>
		<th width="100%">&nbsp;</th>
	</tr>
	<tr>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
	    <td>
    		<select name="companyCode">
				<option value=""></option>
				<c:forEach items="${alCompanys}" var="alCompanyVar">
					<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==param.companyCode}"> selected</c:if>>${alCompanyVar.companyName }</option>
				</c:forEach>
			</select>
		</td>
		</c:if>
		<td nowrap="nowrap">
	    	<input name="userCode" type="text" value="${param.userCode }"/>
	    </td>
		<td nowrap="nowrap">
			<input name="userName" type="text" value="${param.userName }"/>
		</td>
		<td nowrap="nowrap">
    		<jecs:list name="state" listCode="yesno" value="${param.state}" defaultValue="" showBlankLine="true"/>
    	</td>
		<td>
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</div>
</form>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysManager">
			<a href="#" onclick="location.href='<c:url value="/editSysManager.html"/>?strAction=addSysManager&companyCode=${param.companyCode }&departmentId=${param.departmentId }&modifyType=account'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key="member.new.num"/><jecs:locale key="login.userType.console"/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>
	
<ec:table 
	tableId="sysManagerListTable"
	items="sysManagerList"
	var="sysManager"
	action="${pageContext.request.contextPath}/sysAccounts.html"
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
			&nbsp;
			<jecs:power powerCode="sysPowerSimple">
			<a href="sys_power_simple.html?userCode=${sysManager.userCode}"><img src="images/icons/unLock.gif" border="0" width="16" height="16" alt="<jecs:locale key="member.status.limit1"/>"/></a>
			</jecs:power>
			</nobr>
		</ec:column>
		<ec:column property="companyCode" title="bdReconsumMoneyReport.companyCH" />
		<ec:column property="userCode" title="sysUser.userCode" />
		<ec:column property="sysUser.userName" title="sysUser.userName" />
		<ec:column property="email" title="sysUser.email" />
		<ec:column property="tel" title="alCompany.phone" />
		<ec:column property="mobile" title="sysUser.mobile" />
		<ec:column property="depOrder" title="sysModule.orderNo" />
		<ec:column property="sysUser.state" title="sysUser.state" styleClass="centerColumn">
			<jecs:code listCode="yesno" value="${sysManager.sysUser.state}"/>
		</ec:column>
	</ec:row>

</ec:table>	

<script type="text/javascript">

function editSysManager(userCode){
	<jecs:power powerCode="editSysManager">
		window.location="editSysManager.html?strAction=editSysManager&modifyType=account&userCode="+userCode;
	</jecs:power>
}

function changePassword(userCode){
	<jecs:power powerCode="sysModifyPwd">
	window.location="sys_modify_pwd.html?strAction=sysModifyPwd&modifyType=account&userCode="+userCode;
	</jecs:power>
}
</script>
