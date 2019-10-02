<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><jecs:locale key="webapp.name"/></title>
<link href="styles/default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
html {
	height:100%;
	width:100%;
}
body{
	overflow:hidden;
}

.frmStyle{
	 border :1px solid Silver;
}
</style>
</head>
<body>

<form action="sys_power_m_frm.html" method="post" name="myPowerForm">
<input type="hidden" name="strAction" value="sysPowerMFrm"/>
<input type="hidden" name="selectedUserCode" value=""/>
<input type="hidden" name="moduleId" value=""/>

<table width="100%" height="100%" border="0">
	<tr>
		<td height="20" colspan="2">
			<jecs:locale key="please.select.company"/>:
			<select name="companyCode" onChange="changeCompany(this.form)">
				<c:forEach items="${alCompanys}" var="alCompanyVar">
					<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==companyCode}"> selected</c:if>>${alCompanyVar.companyCode } - ${alCompanyVar.companyName}</option>
				</c:forEach>
			</select>		</td>
	</tr>
	<tr>
		<td height="20">1.<jecs:locale key="sys.power.m.myMod.title"/>:</td>
		<td>2.<jecs:locale key="sys.power.m.myMan.title"/>:</td>
	</tr>
	<tr>
		<td width="50%">
			<iframe src="./sys_my_mod_m_tree.html?companyCode=${companyCode }&moduleId=${param.moduleId }" name="frmModTree" frameborder="0" scrolling="auto" marginwidth="0" width="100%" height="100%" class="frmStyle"></iframe>		</td>
		<td width="50%">
			<iframe src="./sys_my_man_m_tree.html?companyCode=${companyCode }&moduleId=${param.moduleId }" name="frmManMTree" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" width="100%" height="100%" class="frmStyle"></iframe>		</td>
	</tr>
	<tr>
		<td height="20" colspan="2">
			<table width="100%" class="detail">
				<tr>
				<td class="command" align="center">
					<jecs:power powerCode="sysPowerMFrm">
					<button type="button" class="btn btn_sele" onClick="saveMyPower(this.form)" ><jecs:locale key="operation.button.save"/></button>
					</jecs:power>				</td>
				</tr>
			</table>		</td>
	</tr>
</table>
</form>

<script type="text/javascript">
function saveMyPower(theForm){
	var selectedModule=window.frmModTree.tree.currentNode;
	if(!selectedModule || selectedModule.id=="" || selectedModule.id==0){
		alert("<jecs:locale key="sysPower.please.operation.userCode"/>");
		return;
	}
	var sourceId = selectedModule.sourceIndex.substr(selectedModule.sourceIndex.indexOf(window.frmModTree.tree.divider) + window.frmModTree.tree.divider.length);
	theForm.moduleId.value=sourceId;
	
	var selectedUserCode=window.frmManMTree.myManM.getAllCheckedBranches();
	theForm.selectedUserCode.value=selectedUserCode;
	theForm.submit();	
}

function changeCompany(theForm){
	window.frmModTree.location="sys_my_mod_m_tree.html?companyCode="+theForm.companyCode.value;
	window.frmManMTree.location="sys_my_man_m_tree.html?companyCode="+theForm.companyCode.value;
}
</script>
</body>
</html>