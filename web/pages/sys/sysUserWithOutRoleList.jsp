<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserList.title" /></title>
<content tag="heading">
<jecs:locale key="sysUserList.heading" />
	
</content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/sysUserRoleManager.js'/>" ></script>
<meta name="menu" content="sysUserListMenu" />

<c:set var="buttons">
		
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="addUsersToRole();"
			        value="<jecs:locale key="operation.button.add"/>"/>
					<input type="button" class="button" style="margin-right: 5px"
			        onclick="toBack()"
			        value="<jecs:locale key="operation.button.return"/>"/>
</c:set>



<form action="${pageContext.request.contextPath}/sysUsers.html"   id="sysUserListTable">
<div id="titleBar">
<input type="hidden" name="strAction" id="strAction" value="${param.strAction}" />
<input type="hidden" name="roleId" id="roleId" value="${param.roleId}" />

	
	
	<input type="button" class="button" style="margin-right: 5px"
        onclick="selectAll()"
        value="<jecs:locale  key='common.selectAll'/>"/>
   <input type="button" class="button" style="margin-right: 5px"
        onclick="reverseSelect()"
        value="<jecs:locale  key='common.reSelectAll'/>"/>
		<input type="button" class="button" style="margin-right: 5px"
        onclick="cancelCheckAll()"
        value="<jecs:locale  key='common.cancelCheckAll'/>"/>
    <c:out value="${buttons}" escapeXml="false"/>
    	
   
</div>






<ec:table 
	tableId="sysUserListTable" 
	items="sysUserList" 
	var="sysUser" 
	retrieveRowsCallback="limit"
	form="sysUserListTable"
	action="${pageContext.request.contextPath}/sysUsers.html" 
	width="100%" rowsDisplayed="20" 
	method="post"
	sortable="true" 
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="userType" title="title.operation" sortable="false">
			<input type="checkbox" name="chkSel" value="${sysUser.user_code}" />
		</ec:column>
		<ec:column property="user_code" title="sys.user.usercode" />
		<ec:column property="user_name" title="sys.user.username" />
		<ec:column property="state" title="sys.user.state" >
			<jecs:code listCode="yesno" value="${sysUser.state}"/>
		</ec:column>
	</ec:row>
</ec:table>
</form>

<script type="text/javascript">
	function editMe(userCode){
		}
	function loginThis(userCode){
			parent.window.location='<c:url value="/sysLoginTool.html"/>?userCode='+userCode;
			//open('<c:url value="/sysLoginTool.html"/>?userCode='+userCode);
			//this.window.close();
		}
		
		
		// �õ����е�checkbox��ѡ��ֵ
function getSelectedInfoRows(checkName)
{
 var temp = "";
 for ( var i=0; i<Form.getInputs('sysUserListTable','checkbox',checkName).length; i++ )
 {
  var e = Form.getInputs('sysUserListTable','checkbox',checkName)[i];
  if((!e.disabled)&&(e.checked)){
  	temp += temp==""? e.value: "," + e.value ;
  	}

 }

 return temp;
}


function addUsersToRole(){
			var users = getSelectedInfoRows('chkSel');
			var roleId = $('roleId').value;
			
			sysUserRoleManager.saveSysUserRoleBatch(users,roleId,function(){window.location.reload();});
	}
	
	function toBack(){
			var roleId = $('roleId').value;
			this.location="<c:url value='sysUserRoles.html'/>?strAction=roleUserManage&roleId="+roleId;
		}
</script>