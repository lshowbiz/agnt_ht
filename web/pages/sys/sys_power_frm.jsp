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

<form action="sys_power_frm.html" method="post" name="myPowerForm">
<input type="hidden" name="strAction" value="sysMyPowerManager"/>
<input type="hidden" name="masterUserCode" value="${param.masterUserCode }"/>
<input type="hidden" name="selectedModule" value=""/>
<input type="hidden" name="selectedSalveUserCode" value=""/>

<table width="100%" height="100%" border="0">
	<tr>
		<td height="20">
		<jecs:locale key="please.select.company"/>:
			<select name="companyCode" onChange="changeCompany(this.form)">
				<c:forEach items="${alCompanys}" var="alCompanyVar">
					<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==companyCode}"> selected</c:if>>${alCompanyVar.companyCode } - ${alCompanyVar.companyName}</option>
				</c:forEach>
			</select>		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td height="20">1.
				<jecs:locale key="sys.power.myMan.title"/>:
				<input type="button" value="+" onclick="window.frmManTree.tree.expandAll();" class="form_button" style="width:20px;font-weight: bold; font-size: 16px; padding-top: 2px;"/>
				<input type="button" value="-" onclick="window.frmManTree.tree.expand(window.frmManTree.tree.node['0'].childNodes[0].id,false);" class="form_button" style="width:20px;font-weight: bold; font-size: 16px; padding-top: 2px;"/>
		</td>
		<td>2.
				<jecs:locale key="sys.power.myMod.title"/>:
				<input type="button" value="+" onclick="window.frmModTree.myModTree.openAllItems(0);" class="form_button" style="width:20px;font-weight: bold; font-size: 16px; padding-top: 2px;"/>
				<input type="button" value="-" onclick="window.frmModTree.myModTree.closeAllItems(0);" class="form_button" style="width:20px;font-weight: bold; font-size: 16px; padding-top: 2px;"/>
		</td>
		<td>3.
				<jecs:locale key="sys.power.myMansMan.title"/>:
				<input type="button" value="+" onclick="window.frmMansManTree.myMansMan.openAllItems(0);" class="form_button" style="width:20px;font-weight: bold; font-size: 16px; padding-top: 2px;"/>
				<input type="button" value="-" onclick="window.frmMansManTree.myMansMan.closeAllItems(0);" class="form_button" style="width:20px;font-weight: bold; font-size: 16px; padding-top: 2px;"/>
		</td>
	</tr>
	<tr>
		<td width="30%">
			<iframe src="./sys_my_man_tree.html?companyCode=${companyCode }&masterUserCode=${param.masterUserCode }" name="frmManTree" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" width="100%" height="100%" class="frmStyle"></iframe>		</td>
		<td width="40%">
			<iframe src="./sys_my_mod_tree.html?companyCode=${companyCode }&slaveUserCode=${param.masterUserCode }" name="frmModTree" frameborder="0" scrolling="no" marginwidth="0" width="100%" height="100%" class="frmStyle"></iframe>		</td>
		<td width="30%">
			<iframe src="./sys_my_mans_man_tree.html?strAction=sysMyManMTree&companyCode=${companyCode }&slaveUserCode=${param.masterUserCode }" name="frmMansManTree" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" width="100%" height="100%" class="frmStyle"></iframe>		</td>
	</tr>
	<tr>
		<td height="20" colspan="3">
			<table width="100%" class="detail">
				<tr>
				<td class="command" align="center">
					<jecs:power powerCode="sysMyPowerManager">
					<button type="button" class="btn btn_sele" onClick="saveMyPower(this.form)" ><jecs:locale key="operation.button.save"/></button>					</jecs:power>				
				</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>
</form>

<script type="text/javascript">
function saveMyPower(theForm){
	if(theForm.masterUserCode.value==""){
		alert("<jecs:locale key="sysPower.please.operation.userCode"/>");
		return;
	}
	
	var selectedModule=window.frmModTree.myModTree.getAllCheckedBranches();
	theForm.selectedModule.value=selectedModule;
	var selectedSalveUserCode=window.frmMansManTree.myMansMan.getAllCheckedBranches();
	theForm.selectedSalveUserCode.value=selectedSalveUserCode;
	theForm.submit();	
}

function changeCompany(theForm){
	window.frmManTree.location="sys_my_man_tree.html?companyCode="+theForm.companyCode.value;
	window.frmModTree.location="sys_my_mod_tree.html?companyCode="+theForm.companyCode.value;
	window.frmMansManTree.location="sys_my_mans_man_tree.html?strAction=sysMyManMTree&companyCode="+theForm.companyCode.value;
}
</script>
</body>
</html>