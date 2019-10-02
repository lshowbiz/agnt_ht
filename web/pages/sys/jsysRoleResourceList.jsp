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
var notViewArr=[];
<c:forEach items="${jsysResources}" var="sysRoleRes">
    <c:if test="${unViewResources[sysRoleRes.res_code] !=null }">
       notViewArr.push("${sysRoleRes.res_id}");
       
    </c:if>
	//${unViewResources[sysRoleRes.res_code]}
	myModTree.insertNewChild("${sysRoleRes.parent_id}","${sysRoleRes.res_id}","<jecs:locale key="${sysRoleRes.res_name}"/>",0,0,0,0,"<c:if test="${not empty sysRoleRes.rp_id}">CHECKED</c:if>"); 
</c:forEach>

setDisableNodes();


function setDisableNodes(){
	for (var i = 0; i < notViewArr.length; i++) {
		var nodeId=notViewArr[i];
	  if(myModTree.isItemChecked(nodeId)=="0"){
			var subNodeIds=myModTree.getAllSubItems(nodeId);
			if(subNodeIds!=""){
				var subNodeIdsArr  = subNodeIds.split(",");

               for (var j = 0 ; j < subNodeIdsArr.length; j++) {
				    myModTree.disableCheckbox(subNodeIdsArr[j],true);
			   }
				
			}
		 }else{
			var parentId=myModTree.getParentId(nodeId);
			while(parentId!=""){
				myModTree.disableCheckbox(parentId,true);
				var parentId=myModTree.getParentId(parentId);
			}
		 }
		myModTree.disableCheckbox(nodeId,true);
	}
	 
}
function removeDisableNodes(){
	for (var i = 0; i < notViewArr.length; i++) {
		var nodeId=notViewArr[i];
		myModTree.disableCheckbox(nodeId,false);
		var subNodeIds=myModTree.getAllSubItems(nodeId);
		if(subNodeIds!=""){
			var subNodeIdsArr  = subNodeIds.split(",");
			for (var j = 0 ; j < subNodeIdsArr.length; j++) {
				    myModTree.disableCheckbox(subNodeIdsArr[j],false);
			   }
			
		}
		var parentId=myModTree.getParentId(nodeId);
		while(parentId!=""){
			myModTree.disableCheckbox(parentId,false);
			var parentId=myModTree.getParentId(parentId);
		}
	}

}
</script>