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