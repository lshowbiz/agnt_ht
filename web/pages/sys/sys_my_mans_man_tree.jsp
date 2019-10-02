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
var myMansMan=new dhtmlXTreeObject("treebox","100%","100%",0);
myMansMan.setImagePath("./images/dhtmlxTree/csh_vista/");
myMansMan.enableCheckBoxes(true);
myMansMan.enableThreeStateCheckboxes(true);

var userImg="user.gif";

<c:forEach items="${alCompanys}" var="alCompanyVar">
	myMansMan.insertNewChild("0","C#${alCompanyVar.companyCode}","${alCompanyVar.companyName}",0,0,0,0,""); 
	//myMansMan.showItemCheckbox("C#${alCompanyVar.companyCode}",false) ;
</c:forEach>


<c:forEach items="${sysDepartments}" var="sysDepartmentVar">
	<c:if test="${sysDepartmentVar.parentId==0}">
	myMansMan.insertNewChild("C#${sysDepartmentVar.companyCode}","D#${sysDepartmentVar.departmentId}","${sysDepartmentVar.departmentName}",0,0,0,0,"");
	</c:if> 
	<c:if test="${sysDepartmentVar.parentId!=0}">
	myMansMan.insertNewChild("D#${sysDepartmentVar.parentId}","D#${sysDepartmentVar.departmentId}","${sysDepartmentVar.departmentName}",0,0,0,0,"");
	</c:if> 
	//myMansMan.showItemCheckbox("D#${sysDepartmentVar.departmentId}",false) ;
</c:forEach>

<c:forEach items="${sysManagers}" var="managerRow" varStatus="managerRowStatus">
	myMansMan.insertNewChild("D#${managerRow.department_id}","M#${managerRow.user_code}","${managerRow.user_name}(${managerRow.user_code})",0,userImg,userImg,userImg,"<c:if test="${not empty managerRow.slave_roll_id}">CHECKED</c:if>"); 
</c:forEach>

myMansMan.closeAllItems(0);
</script>