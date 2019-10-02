<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysMenuDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="sysMenuDetail.heading" />
</content>

<form:form commandName="sysMenu" method="post" action="editMenu.html"	onsubmit="return validateForm(this)" id="sysMenuForm">
		
	<form:hidden path="menuId" />
	<form:hidden path="parentId" />
	<form:hidden path="leaf"/>
	<form:hidden path="type"/>
	<table class="detail" width="100%">
		<tr>
			<th><jecs:label key="sysMenu.menuName" /></th>
			<td><form:input path="menuName" id="menuName" cssClass="text medium" /></td>
		</tr>

		<tr>
			<th><jecs:label key="sysMenu.menuDes" /></th>
			<td><form:input path="menuDes" id="menuDes" cssClass="text medium" /></td>
		</tr>

		<tr>
			<th><jecs:label key="sysMenu.address" /></th>
			<td><form:input path="address" id="address"	cssClass="text medium" /></td>
		</tr>

		<tr>
			<th><jecs:label key="sysMenu.menuOrder" /></th>
			<td><form:input path="menuOrder" id="mOrder" cssClass="text medium" /></td>
		</tr>

		<tr>
			<th><jecs:label key="sys.common.status" />:</th>
			<td>
				<form:radiobutton path="status" value="0"/><jecs:locale key="sys.common.status.0" />
				<form:radiobutton path="status" value="1" /><jecs:locale key="sys.common.status.1" />
			</td>
		</tr>

		<tr>
			<th><jecs:label key="sys.common.belongType" /></th>
			<td>
				<input type="checkbox" name="isA" value="1" <c:out value="${sysMenu.isA==1?'checked':''}"/>/><jecs:locale key="sysMenu.belongType.managerCenter"/>
				<input type="checkbox" name="isC" value="1" <c:out value="${sysMenu.isC==1?'checked':''}"/>/><jecs:locale key="sysMenu.belongType.company"/>
				<input type="checkbox" name="isQ" value="1" <c:out value="${sysMenu.isQ==1?'checked':''}"/>/><jecs:locale key="sysMenu.belongType.agent"/>
				<input type="checkbox" name="isM" value="1" <c:out value="${sysMenu.isM==1?'checked':''}"/>/><jecs:locale key="sysMenu.belongType.member"/>
			</td>
		</tr>
		
		<tr>
			<th><jecs:label key="sys.common.remark" /></th>
			<td><form:textarea path="remark" id="remark" cssClass="text medium" /></td>
		</tr>

		<tr>
			<th></th>
			<td>
				<input type="submit" class="button" name="save"	onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
				<input type="button" class="button" name="cancel"	onclick="window.history.back()" value="<jecs:locale key="operation.button.cancel"/>" />
			</td>
		</tr>
	</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysMenuForm'));
		function validateForm(form)
		{
			if(validateSysMenu(form)){
				if(form.leaf1.checked==false&&form.leaf2.checked==false&&form.leaf3.checked==false){
					alert("<jecs:locale key="sysMenu.leaf" />");
					form.leaf1.focus();
					return false;
				}
				if(form.type1.checked==false&&form.type2.checked==false&&form.type3.checked==false){
					alert("<jecs:locale key="sysMenu.type" />");
					form.type1.focus();
					return false;
				}
				if(form.status1.checked==false&&form.status2.checked==false){
					alert("<jecs:locale key="sys.common.status"/>");
					form.status1.focus();
					return false;
				}
				return true;
			}else{
				return false;
			}
		}
</script>

<v:javascript formName="sysMenu" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
