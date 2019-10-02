<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserRoleDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysUserRoleDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysUserRole">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysUserRole<jecs:localefmt:message key="operation.button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysUserRole.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
 <jecs:locale   alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="sysUserRole" method="post" action="editSysUserRole.html" onsubmit="return validateSysUserRole(this)" id="sysUserRoleForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>





<ec:table 
	tableId="sysUserListTable"
	items="sysUserList"
	var="sysUser"
	action="${pageContext.request.contextPath}/sysUserRoles.html"
	width="100%"
	form="sysUserRoleListTable"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
						<ec:column property="ruId" title="sysUser.userCode" sortable="false">
								<input type="checkbox" name="chkSel" value="${sysUser.userCode}" />
						</ec:column>
						
						<ec:column property="userCode" title="sys.user.usercode" />
						<ec:column property="userName" title="sys.user.userName" />
				</ec:row>

</ec:table>	




</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysUserRoleForm'));
</script>

<v:javascript formName="sysUserRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
