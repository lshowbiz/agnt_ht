<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="styles/dhtmlXTree.css" />

<script src="./scripts/dhtmlxTree/dhtmlXCommon.js" type="text/javascript"></script>
<script src="./scripts/dhtmlxTree/dhtmlXTree.js" type="text/javascript"></script>

<style>
html,body{
     margin:0px;
     height:100%;
}
</style>

<div id="treebox" style="width:100%; height:100%;overflow:auto;"></div>
<script type="text/javascript">
var myModTree=new dhtmlXTreeObject("treebox","100%","100%",0);
myModTree.setImagePath("./images/dhtmlxTree/csh_vista/");
myModTree.enableCheckBoxes(true);
myModTree.enableThreeStateCheckboxes(true);

<c:if test="${not empty sysModules}">
	myModTree.insertNewChild("0","A","<jecs:locale key="sysmodule.all"/>",0,"leafModule.gif","folderOpenModule.gif","folderClosedModule.gif","");
</c:if>
<c:forEach items="${sysModules}" var="sysModuleVar">
	<c:set var="parentId" value="${sysModuleVar.parent_id}"/>
	<c:if test="${sysModuleVar.parent_id==0}">
		<c:set var="parentId" value="A"/>
	</c:if>
	<c:if test="${sysModuleVar.module_type==1}">
	myModTree.insertNewChild("${parentId}","${sysModuleVar.module_id}","<jecs:locale key="${sysModuleVar.module_name}"/>",0,"leafMenu.gif","folderOpenMenu.gif","folderClosedMenu.gif","<c:if test="${not empty sysModuleVar.slave_power_id}">CHECKED</c:if>");
	</c:if>
	<c:if test="${sysModuleVar.module_type==0}">
	myModTree.insertNewChild("${parentId}","${sysModuleVar.module_id}","<jecs:locale key="${sysModuleVar.module_name}"/>",0,"leafModule.gif","folderOpenModule.gif","folderClosedModule.gif","<c:if test="${not empty sysModuleVar.slave_power_id}">CHECKED</c:if>");
	</c:if>
</c:forEach>
myModTree.closeAllItems(0);
myModTree.openItem(0);
</script>