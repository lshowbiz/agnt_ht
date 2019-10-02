<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserList.title" /></title>
<content tag="heading">
<jecs:locale key="sysUserList.heading" />
</content>
<meta name="menu" content="sysUserListMenu" />
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/sysUserManager.js'/>" ></script>




<form action="${pageContext.request.contextPath}/sysUsers.html" method="get" name="sysUserListForm" id="sysUserListForm">
<div class="searchBar">
<input type="hidden" name="strAction" id="strAction" value="${param.strAction}" />
<input type="hidden" name="searchFlag" id="searchFlag" value="show" />
	<div class="new_searchBar">
		<jecs:title key="label.member.or.agent.code"/>
		<input id="userCode" name="userCode" type="text" value="${param.userCode}" size="14"/>
	    <input id="uCode" name="uCode" type="hidden" value="" size="14"/>
	</div>
	
	<div class="new_searchBar">
		<jecs:title key="sys.user.username"/>
		<input name="userName" type="text" value="${param.userName }" size="14"/>
	</div>
	<div class="new_searchBar" style="margin-left:50px;">
		<button name="search" class="btn btn_sele" type="submit" >
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>
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
		<ec:column property="userType" title="title.operation" sortable="false">
			<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" onclick="editMe('${sysUser.userCode}')"  style="cursor: pointer;" title="<jecs:locale key="sys.chooserole"/>"/> 
			<img src="<c:url value='/images/bonus_tree/next_5.gif'/>" onclick="loginThis('${sysUser.userCode}')"  style="cursor: pointer;" title="<jecs:locale key="sys.logintool"/>"/>
		</ec:column>
		<ec:column property="userCode" title="label.member.or.agent.code" />
		<ec:column property="userName" title="sys.user.username" />
		<ec:column property="state" title="sys.user.state" >
			<jecs:code listCode="yesno" value="${sysUser.state}"/>
		</ec:column>
	</ec:row>
</ec:table>
</form>

<script type="text/javascript">
	var sysUrl="http://e6.jmtop.com/jecs";
	function editMe(userCode){
		window.open("<c:url value='/sysUsers.html?strAction=manageRole'/>&uCode="+userCode,'','height=350, width=550, top=250, left=300, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	
		}
	function loginThis(userCode){
			document.getElementById("uCode").value=userCode;
			var sysUser = sysUserManager.getSysUser(userCode,checkLogin);		
			
			//parent.window.location='<c:url value="/sysLoginTool.html"/>?strAction=loginMember&userCode='+userCode;
			//open('<c:url value="/sysLoginTool.html"/>?userCode='+userCode);
			//this.window.close();
		}
	
	function checkLogin(data){

		sysUserManager.getLoginKey(data['userCode'],'${sessionScope.sessionLogin.userCode}',loginNew);
		
		
		
	}
	
	function loginNew(d){
			sysUrl='<jecs:code listCode="logintool.url" value="1" />/loginTool/api/'+d+'/${sessionScope.sessionLogin.userCode}';
			open(sysUrl);
		}
</script>