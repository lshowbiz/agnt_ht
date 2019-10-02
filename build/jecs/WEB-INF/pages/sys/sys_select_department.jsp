<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<meta http-equiv="Cache-Control" content="no-cache">
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
	N["0_R"]="T:<jecs:locale key="webapp.company.title"/>;";
	<c:forEach items="${treeMap}" var="treeRow">
		N["R_C${treeRow.key.companyId}"]="T:${treeRow.key.companyName};";

		<c:forEach items="${treeRow.value}" var="deptRow">
			<c:if test="${deptRow.parentId==0}">
			N["C${treeRow.key.companyId}_D${deptRow.departmentId}"]="T:${deptRow.departmentName};C:selectDepartment('${treeRow.key.companyCode}','${deptRow.departmentId}','${deptRow.departmentName}')";
			</c:if>
			<c:if test="${deptRow.parentId!=0}">
			N["D${deptRow.parentId}_D${deptRow.departmentId}"]="T:${deptRow.departmentName};C:selectDepartment('${treeRow.key.companyCode}','${deptRow.departmentId}','${deptRow.departmentName}')";
			</c:if>
		</c:forEach>
	</c:forEach>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
document.write(tree.toString());

<c:if test="${not empty param.departmentId}">
tree.focus('D${param.departmentId}');
</c:if>
</script>

<script type="text/javascript">
function selectDepartment(companyCode, departmentId, departmentName){
	var obj=new Object();
	obj.companyCode=companyCode;
	obj.departmentId=departmentId;
	obj.departmentName=departmentName;
	window.top.returnValue=obj;
	window.top.close();
}
</script>