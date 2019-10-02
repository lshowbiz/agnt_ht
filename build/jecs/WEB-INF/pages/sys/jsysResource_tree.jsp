<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jsysResourceList.title"/></title>
<content tag="heading"><jecs:locale key="jsysResourceList.heading"/></content>
<meta name="menu" content="JsysResourceMenu"/>

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
	N["0_A"]="T:<jecs:locale key="webapp.company.title"/>;url:<c:url value="/jsysResources.html"/>?parentId=0";
	<c:if test="${not empty jsysResources}">
		<c:forEach items="${jsysResources}" var="row">
			<c:set var="parentId" value="${row.parentId}"/>
			<c:if test="${row.parentId==0}">
				<c:set var="parentId" value="A"/>
			</c:if>
			<c:set var="menuIcon" value="leafMenu"/>
			<c:set var="folderIcon" value="folderMenu"/>
			<c:if test="${row.resType==0}">
				<c:set var="menuIcon" value="leafModule"/>
				<c:set var="folderIcon" value="folderModule"/>
			</c:if>
			N["${parentId}_${row.resId}"]="T:<jecs:locale key="${row.resName}"/>;url:<c:url value="/jsysResources.html"/>?parentId=${row.resId};icon:${menuIcon};folderIcon:${folderIcon}";
		</c:forEach>
	</c:if>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
tree.setTarget("frmJsysResourcesList");
document.write(tree.toString());

//tree.focus(85551);
</script>