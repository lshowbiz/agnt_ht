<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
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
	N["0_A"]="T:<jecs:locale key="webapp.company.title"/>;url:<c:url value="/sys_my_man_m_tree.html"/>?companyCode=${companyCode}&moduleId=;data:id=;";
	<c:if test="${not empty sysModules}">
		<c:forEach items="${sysModules}" var="row">
			<c:set var="parentId" value="${row.parent_id}"/>
			<c:if test="${row.parent_id==0}">
				<c:set var="parentId" value="A"/>
			</c:if>
			<c:set var="menuIcon" value="leafMenu"/>
			<c:set var="folderIcon" value="folderMenu"/>
			<c:if test="${row.module_type==0}">
				<c:set var="menuIcon" value="leafModule"/>
				<c:set var="folderIcon" value="folderModule"/>
			</c:if>
			N["${parentId}_${row.module_id}"]="T:<jecs:locale key="${row.module_name}"/>;url:<c:url value="/sys_my_man_m_tree.html"/>?companyCode=${companyCode}&moduleId=${row.module_id};icon:${menuIcon};folderIcon:${folderIcon};data:id=${row.module_id};";
		</c:forEach>
	</c:if>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
tree.setTarget("frmManMTree");
document.write(tree.toString());

<c:if test="${not empty param.moduleId}">
	tree.focus("${param.moduleId}");
</c:if>
</script>