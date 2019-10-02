<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="sysUserDetail.heading" />
</content>

<form:form commandName="sysUser" method="post" action="sys_modify_pwd.html" onsubmit="return validatePassword(this)" id="sysUserEditForm">
<input type="hidden" name="strAction" value="sysModifyPwd" />
<input type="hidden" name="modifyType" value="${param.modifyType }" />
<form:hidden path="userCode"/>
	<table class='detail' width="70%" >
		<tbody class="window" >
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="password.type" /></span>
				</th>
				<td >
					<span class="textbox">
					<input type="radio" value="userPassword" name="passwordType" id="userPassword" class="checkbox" checked="checked"/>
					<label for="userPassword"><jecs:locale key="sysUser.userPassword"/></label>
					<input type="radio" value="password2" name="passwordType" id="password2" class="checkbox"/>
					<label for="password2"><jecs:locale key="sysUser.password2"/></label>
					</span>
				</td>
				
				<c:if test="${empty param.modifyType}">
					<th>
						<span class="text-font-style-tc"><jecs:label key="sysUser.oldPassword" /></span>
					</th>
					<td><span class="textbox"><input type="password" id="oldPassword" name="oldPassword" class="textbox-text"/></span></td>
				</c:if>
			</tr>
			
			
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="sysUser.newPassword" /></span>
				</th>
				<td><span class="textbox"><input type="password" id="newPassword" name="newPassword" class="textbox-text"/></span></td>
				<th>
					<span class="text-font-style-tc"><jecs:label key="sysUser.repeatPassword" /></span>
				</th>
				<td><span class="textbox"><input type="password" id="newPassword2" name="newPassword2" class="textbox-text"/></span></td>
			</tr>
			
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="sysModifyPwd">
						<input type="submit" class="btn_sele btn" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
					</jecs:power>
					<c:if test="${empty param.modifyType}">
					<input type="button" class="btn_sele btn" name="cancel" onclick="window.location='sys_modify_pwd.html?strAction=sysModifyPwd'" value="<jecs:locale key="operation.button.cancel"/>" />
					</c:if>
					
					<c:if test="${param.modifyType=='other'}">
					<input type="button" class="btn_sele btn" name="cancel" onclick="window.location='sysManagers.html?companyCode=${sysUser.companyCode }&departmentId=${sysManager.sysDepartment.departmentId }'" value="<jecs:locale key="operation.button.cancel"/>" />
					</c:if>
					
					<c:if test="${param.modifyType=='account'}">
					<input type="button" class="btn_sele btn" name="cancel" onclick="window.location='sysAccounts.html?needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" />
					</c:if>
				</td>
			</tr>
			
			
		</tbody>
		</table>
</form:form>

<script type="text/javascript">
function validatePassword(theForm){
	<c:if test="${empty param.modifyType}">
	if(theForm.oldPassword.value==""){
		alert("<jecs:locale key="please.input.oldPassword"/>");
		theForm.oldPassword.focus();
		return false;
	}
	</c:if>
	if(theForm.newPassword.value==""){
		alert("<jecs:locale key="please.input.newPassword"/>");
		theForm.newPassword.focus();
		return false;
	}
	if(theForm.newPassword2.value==""){
		alert("<jecs:locale key="please.reInput.newPassword"/>");
		theForm.newPassword2.focus();
		return false;
	}
	if(theForm.newPassword.value!=theForm.newPassword2.value){
		alert("<jecs:locale key="error.password.not.accord"/>");
		theForm.newPassword.focus();
		return false;
	}
	return true;
}
</script>