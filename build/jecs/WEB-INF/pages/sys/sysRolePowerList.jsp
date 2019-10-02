<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="styles/dhtmlXTree.css" />

<script src="./scripts/dhtmlxTree/dhtmlXCommon.js" type="text/javascript"></script>
<script src="./scripts/dhtmlxTree/dhtmlXTree.js" type="text/javascript"></script>

<div id="treebox" style="width:100%; height:100%;overflow:auto;"></div>

<script type="text/javascript">
var myModTree=new dhtmlXTreeObject("treebox","100%","100%",0);
myModTree.setImagePath("./images/dhtmlxTree/csh_vista/");
myModTree.enableCheckBoxes(true);
myModTree.enableThreeStateCheckboxes(true);

<c:forEach items="${sysModules}" var="sysModuleVar">
	myModTree.insertNewChild("${sysModuleVar.parent_id}","${sysModuleVar.module_id}","<jecs:locale key="${sysModuleVar.module_name}"/>",0,0,0,0,"<c:if test="${not empty sysModuleVar.rp_id}">CHECKED</c:if>"); 
</c:forEach>

</script>