<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserList.title" /></title>
<content tag="heading">
<jecs:locale key="sysUserList.heading" />
</content>
<meta name="menu" content="sysUserListMenu" />





<form action="${pageContext.request.contextPath}/sysUsers.html" method="get" name="sysUserListForm" id="sysUserListForm">
<div id="titleBar">
<input type="hidden" name="strAction" id="strAction" value="${param.strAction}" />
<table border="0" id="searchTable" >
	<tr>
		<td align="right" nowrap="nowrap">
		
			<jecs:locale key="sys.user.usercode"/>:
	    <input name="userCode" type="text" value="${param.userCode}" size="14"/>
	   
			
			<jecs:locale key="sys.user.username"/>:
	    
	    <input name="userName" type="text" value="${param.userName }" size="14"/>
	    
    	<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/>
    </td>
   </tr>
</table>


</div>


<ec:table 
	tableId="sysUserListTable" 
	items="sysUserList" 
	var="sysUser" 
	retrieveRowsCallback="limit"
	form="sysUserListForm"
	action="${pageContext.request.contextPath}/sysUsers.html" 
	width="100%" rowsDisplayed="20" 
	method="post"
	sortable="true" 
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="userType" title="operation.manage" sortable="false">
			<img src="<c:url value='/images/icons/edit.gif'/>" onclick="editMe('${sysUser.userCode}')"  style="cursor: pointer;" title="<jecs:locale key="operation.tip.manage"/>"/> 
			<img src="<c:url value='/images/door_next.gif'/>" onclick="loginThis('${sysUser.userCode}')"  style="cursor: pointer;" title="<jecs:locale key="operation.tip.login"/>"/>
		</ec:column>
		<ec:column property="userCode" title="sys.user.usercode" />
		<ec:column property="userName" title="sys.user.username" />
		<ec:column property="state" title="sys.user.state" />
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
</script>