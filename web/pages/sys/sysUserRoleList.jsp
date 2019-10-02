<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserRoleList.title"/></title>
<content tag="heading"><jecs:locale key="sysUserRoleList.heading"/></content>
<meta name="menu" content="SysUserRoleMenu"/>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/sysUserRoleManager.js'/>" ></script>

<form name="sysUserRoleListTable" action="<c:url value='/sysUserRoles.html'/>" id="sysUserRoleListTable">
	<input type="hidden"  id="roleId" name="roleId"  value="${param.roleId}"/>
			<input type="hidden" id="userCode" name="userCode" value="${param.userCode}"/>
			<input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>	
	
<div class="searchBar">
	
			${roleName} 
				<jecs:locale key="sys.user.hasrole"/>
						<jecs:list id="hasrole" name="hasrole" listCode="yesno" value="${param.hasrole}" defaultValue="1"/>
					
			<jecs:locale key="label.member.or.agent.code"/>
					<input name="searchusercode" type="text" value="${param.searchusercode }" size="14"/>
				
			<jecs:locale key="sys.user.state"/>
			<jecs:list id="state" name="state" listCode="prohibit" value="${param.state}" defaultValue="1"/>
			
			
				
			<button name="search" class="btn btn_sele" type="submit" ><jecs:locale key="operation.button.search"/></button>
			
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysRoles.html';" ><jecs:locale key="operation.button.return"/></button>
					
		
</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<span id="addrolebtn">
			<jecs:power powerCode="addSysRole">
			
			<a href="#" onclick="addUsersToRole();">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/icons/arrangeReply.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='operation.button.addrole'/></font>
			</a>
			
			</jecs:power>
			</span>
			<span id="delrolebtn">
			<jecs:power powerCode="deleteSysRole">
				<a href="#" onclick="deleteSysUserRole()">
					<img src="images/newIcons/delete_16.gif" border="0" align="absmiddle">
					<jecs:locale  key='operation.button.delrole'/>
				</a>
			</jecs:power>
			</span>
		</td>
	</tr>
</table>
	
<ec:table 
	tableId="sysUserRoleListTable"
	items="sysUserRoleList"
	var="sysUserRole"
	action="${pageContext.request.contextPath}/sysUserRoles.html"
	width="100%"
	form="sysUserRoleListTable"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
						<c:if test="${param.hasrole == 1 ||empty param.hasrole }">					
							
							<ec:column alias="chkSel" headerCell="selectAll" style="width:5px;">  
							      <input type="checkbox" name="chkSel" value="${sysUserRole.ru_id}"/>
							</ec:column>
							
							
							<ec:column property="user_code" title="label.member.or.agent.code" />
							<ec:column property="role_name" title="sysRole.roleName" />						
							<ec:column property="state" title="sys.user.state">
							<c:if test="${sysUserRole.state==0}">
									<jecs:locale key="button.lock"/>
								</c:if>
								<c:if test="${sysUserRole.state==1}">
									<jecs:locale key="button.unlock"/>
								</c:if>
							</ec:column>
						</c:if>
						<c:if test="${param.hasrole == 0}">
							<ec:column alias="chkSel" headerCell="selectAll" style="width:5px;">  
							      <input type="checkbox" name="chkSel" value="${sysUserRole.user_code}"/>
							</ec:column>
							<ec:column property="user_code" title="label.member.or.agent.code" />
							<ec:column property="user_name" title="sys.user.username" />
							<ec:column property="state" title="sys.user.state" >
								<c:if test="${sysUserRole.state==0}">
									<jecs:locale key="button.lock"/>
								</c:if>
								<c:if test="${sysUserRole.state==1}">
									<jecs:locale key="button.unlock"/>
								</c:if>
							</ec:column>
						</c:if>
				</ec:row>

</ec:table>	

</form>

<script type="text/javascript">
if("${param.hasrole}"=="1" || "${param.hasrole}"==""){
	$("addrolebtn").style.display="none";
	$("delrolebtn").style.display="";
}else if("${param.hasrole}"=="0"){
	$("addrolebtn").style.display="";
	$("delrolebtn").style.display="none";
}


   function editSysUserRole(ruId){
   		<jecs:power powerCode="editSysUserRole">
					window.location="editSysUserRole.html?strAction=editSysUserRole&ruId="+ruId;
			</jecs:power>
		}

	function deleteSysUserRole(){
		
			var str = getSelectedInfoRows('sysUserRoleListTable','chkSel');
			if(str.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				if(confirm("<jecs:locale key="bdOutWardBank.confirm.delete"/>"))
					sysUserRoleManager.removeSysUserRoleBatch(str,function(){window.location.reload();});
			}
			
			
	}



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


function addUsersToRole(){
			/*
			var roleId = $('roleId').value;
			location.href='<c:url value="/sysUsers.html"/>?strAction=listUsersNotInRole&roleId='+roleId;
			*/
			
			var users = getSelectedInfoRows('sysUserRoleListTable','chkSel');
			var roleId = $('roleId').value;
			if(users.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}
			sysUserRoleManager.saveSysUserRoleBatch(users,roleId,function(){window.location.reload();});
			
	}


</script>
