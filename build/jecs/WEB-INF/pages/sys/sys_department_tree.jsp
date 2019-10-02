<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<script src="./scripts/MzTreeView/jquery.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/jquery.thickbox.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/MzTreeView12.js" type="text/javascript" ></script>

<script type="text/javascript">
tree = new MzTreeView("tree");
tree.icons["leafMenu"] = "leafMenu.gif";
tree.icons["leafModule"] = "leafModule.gif";
tree.icons["folderMenu"] = "folderClosedMenu.gif";
tree.icons["folderModule"] = "folderClosedModule.gif";

tree.iconsExpand["folderMenu"]="folderOpenMenu.gif";
tree.iconsExpand["folderModule"]="folderOpenModule.gif";

with(tree){
	N["0_R"]="T:<jecs:locale key="webapp.company.title"/>;C:selectRoot()";
	<c:forEach items="${treeMap}" var="treeRow">
		N["R_C${treeRow.key.companyId}"]="T:${treeRow.key.companyName};C:selectCompany('${treeRow.key.companyCode}')";

		<c:forEach items="${treeRow.value}" var="deptRow">
			<c:if test="${deptRow.parentId==0}">
			N["C${treeRow.key.companyId}_D${deptRow.departmentId}"]="T:${deptRow.departmentName};C:selectDepartment('${treeRow.key.companyCode}','${deptRow.departmentId}')";
			</c:if>
			<c:if test="${deptRow.parentId!=0}">
			N["D${deptRow.parentId}_D${deptRow.departmentId}"]="T:${deptRow.departmentName};C:selectDepartment('${treeRow.key.companyCode}','${deptRow.departmentId}')";
			</c:if>
		</c:forEach>
	</c:forEach>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
tree.setTarget("frmSysModuleList");
document.write(tree.toString());
</script>

<script type="text/javascript">
function selectRoot(){
	window.parent.frmDepartmentList.location="<c:url value="/alCompanys.html"/>";
	window.parent.frmManagerList.location="<c:url value="/sysManagers.html"/>?departmentId=0";
}

function selectCompany(companyCode){
	window.parent.frmDepartmentList.location="<c:url value="/sysDepartments.html"/>?companyCode="+companyCode+"&parentId=0";
	window.parent.frmManagerList.location="<c:url value="/sysManagers.html"/>?departmentId=0";
}

function selectDepartment(companyCode, departmentId){
	window.parent.frmDepartmentList.location="<c:url value="/sysDepartments.html"/>?companyCode="+companyCode+"&parentId="+departmentId;
	window.parent.frmManagerList.location="<c:url value="/sysManagers.html"/>?companyCode="+companyCode+"&departmentId="+departmentId;
}
</script>