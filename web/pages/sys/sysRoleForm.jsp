<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="sysRoleDetail.title"/></title>
<content tag="heading"><fmt:message key="sysRoleDetail.heading"/></content>

<form:form commandName="sysRole" method="post" action="editSysRole.html" onsubmit="return validateSysRole(this) && validateOthers(this)" id="sysRoleForm">

<spring:bind path="sysRole.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<table class='detail' width="70%" >
	<tbody class="window" >

<form:hidden path="roleId"/>
<input type="hidden" name="moduleIds" value="${moduleIds }"/>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span></th>
		<td>
			<span class="textbox">
			<form:select path="companyCode" id="companyCode" onchange="reloadRolePowers(this.form)" cssClass="textbox-text">
				<option value=""></option>
				<c:forEach items="${alCompanys}" var="alCompanyVar">
					<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
						<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==sysRole.companyCode}"> selected</c:if>>${alCompanyVar.companyCode } - ${alCompanyVar.companyName }</option>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.companyCode!='AA' && alCompanyVar.companyCode==sessionScope.sessionLogin.companyCode}">
						<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==sysRole.companyCode}"> selected</c:if>>${alCompanyVar.companyCode } - ${alCompanyVar.companyName }</option>
					</c:if>
				</c:forEach>
			</form:select>
			</span>
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="sysRole.userType"/></span></th>
		<td>
			<span class="textbox">
			<form:select path="userType" id="userType" onchange="reloadRolePowers(this.form)" cssClass="textbox-text">
				<option value=""></option>
				<c:forEach items="${userTypes}" var="userTypeVar">
					<c:if test="${sessionScope.sessionLogin.companyCode=='AA' || userTypeVar.key!='C'}">
					<option value="${userTypeVar.key }" <c:if test="${userTypeVar.key==sysRole.userType }"> selected</c:if>>
					  <jecs:locale key="${userTypeVar.value }"/></option>
					</c:if>
				</c:forEach>
			</form:select>
			</span>
		</td>
	</tr>
  
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="sysRole.roleCode"/></span></th>
		<td>
			<span class="textbox">
			<form:input path="roleCode" id="roleCode" cssClass="textbox-text"/>
			</span>
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="sysRole.roleName"/></span></th>
		<td>
			<span class="textbox">
			<form:input path="roleName" id="roleName" cssClass="textbox-text"/>
			</span>
		</td>
	</tr>
   
   <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="sysRole.roleDes"/></span></th>
		<td>
			<span class="textbox">
			<form:input path="roleDes" id="roleDes" size="60" cssClass="textbox-text"/>
			</span>
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="sysClickLog.isValid"/></span></th>
		<td>
			<span class="textbox">
			<spring:bind path="sysRole.available">
				<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1" styleClass="textbox-text"/>
			</spring:bind>
			</span>
		</td>
	</tr>

    <tr>
	<th><span class="text-font-style-tc"><jecs:label  key="sysRole.modules"/></span></th>
    <td><span class="textbox">
        <iframe name="frmRollPower" width="100%" height="250" src="sysRolePowers.html?roleId=${sysRole.roleId }&userType=${sysRole.userType }&companyCode=${sysRole.companyCode }" frameborder="0"></iframe></span>
    </td></tr>
    
    <tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><fmt:message key="button.submit"/></button>
			</jecs:power>
			<c:if test="${not empty sysRole.roleId}">
				<jecs:power powerCode="deleteSysRole">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysRole')"><fmt:message key="operation.button.delete"/></button>
				</jecs:power>
			</c:if>
	
			<button type="button" class="btn btn_sele" name="cancel" onclick="toback()"><fmt:message key="operation.button.return"/></button>
			<input type="hidden" name="strAction" value="${param.strAction }"/>
		</td>
	</tr>

</table>

</form:form>

<script type="text/javascript">
Form.focusFirstElement($('sysRoleForm'));

function reloadRolePowers(theForm){
	window.frmRollPower.location="sysRolePowers.html?roleId=${sysRole.roleId}&userType="+theForm.userType.value+"&companyCode="+theForm.companyCode.value;
}

function validateOthers(theForm){
	theForm.moduleIds.value=window.frmRollPower.myModTree.getAllCheckedBranches();
}

function toback(){
    this.location='<c:url value="/sysRoles.html"/>?needReload=1';
}
</script>

<v:javascript formName="sysRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>