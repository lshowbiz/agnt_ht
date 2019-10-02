<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysModuleList.title"/></title>
<content tag="heading"><jecs:locale key="sysModuleList.heading"/></content>
<meta name="menu" content="SysModuleMenu"/>

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
	N["0_A"]="T:<jecs:locale key="webapp.company.title"/>;C:doSelectSysModule('0','<jecs:locale key="webapp.company.title"/>');";
	<c:if test="${not empty sysModules}">
		<c:forEach items="${sysModules}" var="row">
			<c:set var="parentId" value="${row.parentId}"/>
			<c:if test="${row.parentId==0}">
				<c:set var="parentId" value="A"/>
			</c:if>
			<c:set var="menuIcon" value="leafMenu"/>
			<c:set var="folderIcon" value="folderMenu"/>
			<c:if test="${row.moduleType==0}">
				<c:set var="menuIcon" value="leafModule"/>
				<c:set var="folderIcon" value="folderModule"/>
			</c:if>
			N["${parentId}_${row.moduleId}"]="T:<jecs:locale key="${row.moduleName}"/>;icon:${menuIcon};folderIcon:${folderIcon};C:doSelectSysModule('${row.moduleId}','<jecs:locale key="${row.moduleName}"/>');";
		</c:forEach>
	</c:if>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
document.write(tree.toString());

tree.focus('${param.moduleId}');

function doSelectSysModule(moduleId, moduleName){
	var obj=new Object();
	obj.moduleId=moduleId;
	obj.moduleName=moduleName;
	window.top.returnValue=obj;
	window.top.close();
}
</script>