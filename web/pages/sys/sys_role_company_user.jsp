<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="styles/dhtmlXTree.css" />

<script src="./scripts/dhtmlxTree/dhtmlXCommon.js" type="text/javascript"></script>
<script src="./scripts/dhtmlxTree/dhtmlXTree.js" type="text/javascript"></script>

<form:form commandName="sysRole" method="post" action="sys_role_company_user.html" onsubmit="return validateOthers(this)" id="sysRoleForm">

<table width="100%" class="detail">

<form:hidden path="roleId"/>
<form:hidden path="companyCode"/>
	<tr><th width="100">
        <jecs:label  key="bdReconsumMoneyReport.companyCH"/>
    </th>
    <td align="left">${sysRole.companyCode }</td>
    
	<th width="100">
        <jecs:label  key="sysRole.roleCode"/>
    </th>
    <td align="left">${sysRole.roleCode }</td>
    
    <th width="100">
        <jecs:label  key="sysRole.roleName"/>
    </th>
    <td align="left">${sysRole.roleName } </td>

    <th width="100">
        <jecs:label  key="sysRole.roleDes"/>
    </th>
    <td align="left">${sysRole.roleDes }</td>

    <tr><th width="100">
        <jecs:label key="sysRole.to.user"/>
    </th>
    <td align="left" colspan="7">&nbsp;</td>
    </tr>
</table>

<div id="treebox" style="width:100%; height:300px;overflow:auto;"></div>

<table width="100%" class="detail">
	<tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="sysRoleCompanyUser">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="button.submit"/></button>
			</jecs:power>
	
			<button type="button" class="btn btn_sele" name="cancel" onclick="toback()" ><jecs:locale key="operation.button.return"/></button>
			<input type="hidden" name="strAction" value="sysRoleCompanyUser"/>
			<input type="hidden" name="selectedUser" value=""/>
		</td>
	</tr>
</table>

</form:form>

<script type="text/javascript">
var myManM=new dhtmlXTreeObject("treebox","100%","100%",0);
myManM.setImagePath("./images/dhtmlxTree/csh_vista/");
myManM.enableCheckBoxes(true);
myManM.enableThreeStateCheckboxes(true);

var userImg="user.gif";

<c:forEach items="${alCompanys}" var="alCompanyVar">
	myManM.insertNewChild("0","C#${alCompanyVar.companyCode}","${alCompanyVar.companyName}",0,0,0,0,""); 
	myManM.showItemCheckbox("C#${alCompanyVar.companyCode}",false) ;
</c:forEach>


<c:forEach items="${sysDepartments}" var="sysDepartmentVar">
	<c:if test="${sysDepartmentVar.parentId==0}">
	myManM.insertNewChild("C#${sysDepartmentVar.companyCode}","D#${sysDepartmentVar.departmentId}","${sysDepartmentVar.departmentName}",0,0,0,0,"");
	</c:if> 
	<c:if test="${sysDepartmentVar.parentId!=0}">
	myManM.insertNewChild("D#${sysDepartmentVar.parentId}","D#${sysDepartmentVar.departmentId}","${sysDepartmentVar.departmentName}",0,0,0,0,"");
	</c:if> 
	myManM.showItemCheckbox("D#${sysDepartmentVar.departmentId}",false) ;
</c:forEach>

<c:forEach items="${sysManagers}" var="managerRow" varStatus="managerRowStatus">
	myManM.insertNewChild("D#${managerRow.department_id}","M#${managerRow.user_code}","${managerRow.user_name}(${managerRow.user_code})",0,userImg,userImg,userImg,"<c:if test="${not empty managerRow.power_id}">CHECKED</c:if>"); 
</c:forEach>
</script>

<script type="text/javascript">
function validateOthers(theForm){
	if(!confirm("<jecs:locale key="sysRole.notice.override.confirm"/>")){
		return false;
	}
	theForm.selectedUser.value=myManM.getAllCheckedBranches();
	return true;
}

function toback(){
    this.location='<c:url value="/sysRoles.html"/>?needReload=1';
}
</script>
