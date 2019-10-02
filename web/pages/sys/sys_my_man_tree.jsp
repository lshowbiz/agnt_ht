<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script src="./scripts/MzTreeView/jquery.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/jquery.thickbox.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/MzTreeView12.js" type="text/javascript" ></script>

<style>
html,body{
     margin:0px;
     height:100%;
}
</style>

<div id="treebox" style="width:100%; height:100%;overflow:auto;">
<script type="text/javascript">
tree = new MzTreeView("tree");

tree.icons["user"]  = "user.gif";

with(tree){
	N["0_R"]="T:<jecs:locale key="webapp.company.title"/>";
	<c:forEach items="${alCompanys}" var="alCompanyVar">
		N["R_C#${alCompanyVar.companyCode}"]="T:${alCompanyVar.companyName}";
	</c:forEach>
	
	<c:forEach items="${sysDepartments}" var="sysDepartmentVar">
		<c:if test="${sysDepartmentVar.parentId==0}">
		N["C#${sysDepartmentVar.companyCode}_D#${sysDepartmentVar.departmentId}"]="T:${sysDepartmentVar.departmentName}";
		</c:if> 
		<c:if test="${sysDepartmentVar.parentId!=0}">
		N["D#${sysDepartmentVar.parentId}_D#${sysDepartmentVar.departmentId}"]="T:${sysDepartmentVar.departmentName}";
		</c:if> 
	</c:forEach>
	
	<c:forEach items="${sysManagers}" var="managerRow" varStatus="managerRowStatus">
		N["D#${managerRow.department_id}_M#${managerRow.user_code}"]="T:${managerRow.user_name}(${managerRow.user_code});C:onNodeSelect('${managerRow.user_code}');icon:user";
	</c:forEach>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
tree.setTarget("frmSysModuleList");
document.write(tree.toString());

<c:if test="${not empty param.masterUserCode}">
tree.focus('M#${param.masterUserCode}');
</c:if>

function onNodeSelect(userCode){
	window.parent.document.myPowerForm.masterUserCode.value=userCode;
	window.parent.frmModTree.location="sys_my_mod_tree.html?companyCode=${param.companyCode}&slaveUserCode="+userCode;
	window.parent.frmMansManTree.location="sys_my_mans_man_tree.html?strAction=sysMyManMTree&companyCode=${param.companyCode}&slaveUserCode="+userCode;
}
</script>
</div>