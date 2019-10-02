<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysOperationLogList.title" /></title>
<content tag="heading">
<jecs:locale key="sysOperationLogList.heading" />
</content>
<meta name="menu" content="SysOperationLogMenu" />

<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 



<form action="/sysOperationLogs.html" method="get" name="sysOperationLogSearchForm" id="sysOperationLogSearchForm" onsubmit="return validateSearch(this);">
<div class="searchBar">
	<input name="search" id="search" type="hidden" value="search" size="14"/>
	<div class="new_searchBar">
		<label><jecs:locale key="sysUser.userCode"/></label>
		<input name="userCode" type="text" value="${param.userCode }" size="14"/>
	</div>
	<div class="new_searchBar">	
		<label><jecs:locale key="sysOperationLog.operaterCode"/></label>
			<input name="operaterCode" type="text" value="${param.operaterCode }" size="14"/>
	</div>
	<div class="new_searchBar">	
		<label><jecs:locale key="comm.it.ip"/></label>
		<input name="ipAddr" type="text" value="${param.ipAddr }" size="14"/>	
	</div>
	<div class="new_searchBar">			
		<label><jecs:locale key="sysModule.moduleName"/></label>
			<input name="moduleName" type="text" value="${param.moduleName }" size="14"/>
	</div>
	<div class="new_searchBar">			
		<label><jecs:locale key="sysOperationLog.operateTime"/></label>
	
		 <input name="startOperateTime" id="startOperateTime" type="text" 
			value="${param.startOperateTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	     -
		<input name="endOperateTime" id="endOperateTime" type="text" 
			value="${param.endOperateTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>		 
	    
	</div>
	<div class="new_searchBar" style="margin-left:80px;">
		<button name="search" class="btn btn_sele" type="submit">
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>
</div>


<ec:table 
	tableId="sysOperationLogListTable" 
	items="sysOperationLogList" 
	var="sysOperationLog" 
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	form="sysOperationLogSearchForm"
	action="${pageContext.request.contextPath}/sysOperationLogs.html" 
	width="100%" rowsDisplayed="20" 
	sortable="true" 
	imagePath="./images/extremetable/*.gif">
	<ec:row highlightRow="false">
		<ec:column property="log_id" title="title.view" sortable="false" style="white-space: nowrap;width:5px;">
  			<jecs:power powerCode="viewSysOperationLog">
				<a href="viewSysOperationLog.html?strAction=viewSysOperationLog&logId=${sysOperationLog.log_id}&viewData=postData">
				<img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
		</ec:column>
		<ec:column property="operate_time" title="sysOperationLog.operateTime" />
		<ec:column property="operater_code" title="title.operator" >
			${sysOperationLog.operater_code }&nbsp;${sysOperationLog.operater_name }
		</ec:column>
		<ec:column property="ip_addr" title="comm.it.ip" />
		<ec:column property="module_name" title="sysOperationLog.moduleName" >
			[<jecs:code listCode="sysoperationlog.dotype" value="${sysOperationLog.do_type}"/>]
			<jecs:locale key="${sysOperationLog.module_name }"/>
		</ec:column>
		<ec:column property="company_code" title="sysOperationLog.companyCode" />
	</ec:row>
</ec:table>
</form>

<script type="text/javascript">
<!--
function validateSearch(theForm){
	if(theForm.operaterCode.value=="" && theForm.ipAddr.value=="" && theForm.moduleName.value=="" && theForm.startOperateTime.value=="" && theForm.endOperateTime.value==""){
		alert("<jecs:locale key="please.input.search.condition"/>");
		return false;
	}
	return true;
}
//-->
</script>