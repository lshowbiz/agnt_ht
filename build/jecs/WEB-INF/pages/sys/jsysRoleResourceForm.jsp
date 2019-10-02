<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysRoleDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysRoleDetail.heading"/></content>

<form:form commandName="sysRole" method="post" action="editSysRoleResource.html" onsubmit="return validateOthers(this)" id="sysRoleForm">

<spring:bind path="sysRole.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<table class='detail' width="70%" >
	<tbody class="window" >
		

<form:hidden path="roleId"/>
<input type="hidden" name="resIds" value="${resIds }"/>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span></th>
		<td>
			<span class="textbox">
			 <jecs:locale key="${sysRole.companyCode}"/>
			 </span>
		</td>
	</tr>
   
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="sysRole.userType"/></span></th>
		<td>
			<span class="textbox">
			<jecs:code listCode="user_type" value="${sysRole.userType}"/>  
			</span>
		</td>
	</tr>
    
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"> <jecs:label  key="sysRole.roleCode"/></span></th>
		<td>
			<span class="textbox">
			<jecs:locale key="${sysRole.roleCode}"/>
			</span>
		</td>
	</tr>
    
    <tr>
		<th><span class="text-font-style-tc"><jecs:label  key="sysRole.roleName"/></span></th>
		<td>
			<span class="textbox">
			<jecs:locale key="${sysRole.roleName}"/>
			</span>
		</td>
	</tr>

    <tr>
	
	<th><span class="text-font-style-tc" style="height: 246px;line-height: 220px;"><jecs:label  key="sysRole.resources"/></span></th>
    <td align="left">
        <iframe name="frmRollPower" width="45%" height="250" src="sysRoleResources.html?roleId=${sysRole.roleId }" frameborder="0"></iframe>
    </td></tr>
    
    <tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="button.submit"/></button>
			</jecs:power>
			
	
			<button type="button" class="btn btn_sele" name="cancel" onclick="toback()"><jecs:locale key="operation.button.return"/></button>
			<input type="hidden" name="strAction" value="${param.strAction }"/>
		</td>
	</tr>
</tbody>
</table>

</form:form>

<script type="text/javascript">
Form.focusFirstElement($('sysRoleForm'));

function reloadRolePowers(theForm){
	window.frmRollPower.location="sysRoleResources.html?roleId=${sysRole.roleId}";
}

function validateOthers(theForm){
	window.frmRollPower.removeDisableNodes();
	theForm.resIds.value=window.frmRollPower.myModTree.getAllCheckedBranches();
}

function toback(){
    this.location='<c:url value="/sysRoles.html"/>?needReload=1';
}
</script>

<v:javascript formName="sysRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>