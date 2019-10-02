<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sys.roleedit"/></title>
<content tag="heading"><jecs:locale key="sysRoleList.heading"/></content>
<meta name="menu" content="SysRoleMenu"/>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/sysUserRoleManager.js'/>" ></script>


<form action="sysUsers.html" method="get" name="sysUserListTable" id="sysUserListTable">
<input type="hidden" name="strAction" id="strAction" value="manageRole"/>
<input type="hidden" name="uCode" id="uCode" value="${uCode}"/>
<div class="searchBar">
<table width="100%" border="0" id="searchTable" class="">
	<tr>		
        <td width="100%">
        <jecs:locale key="label.member.or.agent.code"/>:${uCode} &nbsp;&nbsp;
        </td>
	</tr>
</table>
</div>


<ec:table 
	tableId="sysUserListTable"
	items="sysUserList"
	var="sysRole"
	action="${pageContext.request.contextPath}/sysUsers.html"
	width="100%"
	form="sysUserListTable"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
	<ec:row>
		<ec:column property="aa" sortable="false" title=" " style="width:3%;">  
		      <input type="checkbox" id="chkSel" name="chkSel" value="${sysRole.roleid}" <c:if test="${not empty sysRole.ru_id}"> checked </c:if >
		      	/>
		</ec:column>
		
		
		<ec:column property="role_name" title="sysRole.roleName" />
	</ec:row>

</ec:table>

<table class='grid_table'   cellpadding='0' cellspacing='1' border='0'>
	<tr class='grid_command'>
		<td colspan='10' align="left">
		&nbsp;
        <input name="search" class="form_button" type="button" value="<jecs:locale key="button.submit"/>" onclick="manageRole()"/>
        <input name="search" class="form_button" type="button" value="<jecs:locale key="operation.button.close"/>"
        	onclick="window.close()"/>
		</td>
	</tr>
</table>
		
</form>
<script type="text/javascript">


function getSelectedInfoRows(formId,checkName)
{
 var temp = "";
 for ( var i=0; i<Form.getInputs(formId,'checkbox',checkName).length; i++ )
 {
  var e = Form.getInputs(formId,'checkbox',checkName)[i];
  if((!e.disabled)&&(e.checked)){
  	temp += temp==""? e.value: "," + e.value ;
  	}

 }

 return temp;
}


	function manageRole(){
		
		var temptemp = getSelectedInfoRows('sysUserListTable','chkSel');
		if(temptemp==""){
			alert("<jecs:locale key="please.select.bank"/>");
			return;
		}else{
			if(temptemp.indexOf(",")!="-1"){
				alert("<jecs:locale key="please.select.onlyone"/>");
				return;
			}else{
				
				var roles = getSelectedInfoRows('sysUserListTable','chkSel');
				//alert("${uCode}");
				if(roles.length==0){
					sysUserRoleManager.removeSysUserRoleByUcode("${uCode}",function(){window.location.reload();});
				}else{
					sysUserRoleManager.saveSysUserRoleByUcode(roles,"${uCode}",function(){window.location.reload();});
					alert("<jecs:locale key="sysRole.updated"/>");
				}
			}
		}
		 
	}
	
	function editMe(roleId){
			<jecs:power powerCode="editSysRole">
				window.location="editSysRole.html?strAction=editSysRole&roleId="+roleId;
			</jecs:power>
		}
		
		function editUsers(roleId){
				<jecs:power powerCode="roleUserManage">
					window.location="sysUserRoles.html?strAction=roleUserManage&roleId="+roleId;
				</jecs:power>
			}
function editSysRole(roleId){
	<jecs:power powerCode="editSysRole">
	window.location="editSysRole.html?strAction=editSysRole&roleId="+roleId;
	</jecs:power>
}
</script>