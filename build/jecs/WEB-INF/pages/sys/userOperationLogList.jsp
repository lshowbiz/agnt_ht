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



<form action="${pageContext.request.contextPath}/userOperationLogs.html" method="get" name="sysOperationLogSearchForm" id="sysOperationLogSearchForm">
<div  class="searchBar">
<input type="hidden" name="strAction" id="strAction" value="${param.strAction}" />
<table border="0" id="searchTable" >
	<tr>
		<th align="right" nowrap="nowrap">
			<jecs:locale key="sysUser.userCode"/>
		</th>
		<th align="right" nowrap="nowrap">
			<jecs:locale key="sysOperationLog.operaterCode"/>
		</th>	    
	    <th align="right" nowrap="nowrap">
			<jecs:locale key="comm.it.ip"/>
		</th>
	    <th align="right" nowrap="nowrap">
			<jecs:locale key="sysOperationLog.visitUrl"/>
		</th>
	    <th align="right" nowrap="nowrap">
	    	<jecs:locale key="sysOperationLog.operateTime"/>
	    </th>	    
        <th><jecs:locale key="operation.button.search"/></th>
		<th width="100%">&nbsp;</th>
      </tr>
      <tr>      	
	    <td nowrap="nowrap">
	    	<input name="userCode" type="text" value="${param.userCode }" size="14"/>
	    </td>
	    <td nowrap="nowrap">
	    	<input name="operaterCode" type="text" value="${param.operaterCode }" size="14"/>
	    </td>	    
	    <td nowrap="nowrap">
	    <input name="ipAddr" type="text" value="${param.ipAddr }" size="14"/>
	    </td>
	    
	    <td nowrap="nowrap">
	    <input name="visitUrl" type="text" value="${param.visitUrl }" size="14"/>
	    </td>
	    
	    <td nowrap="nowrap">
	    <input name="startOperateTime" id="startOperateTime" type="text" value="${param.startOperateTime }" size="10" class="readonly" readonly/>
	    <img src="./images/calendar/calendar7.gif" id="img_startOperateTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "startOperateTime", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_startOperateTime", 
			singleClick    :    true
			}); 
		</script>
	     - 
	     <input name="endOperateTime" id="endOperateTime" type="text" value="${param.endOperateTime }" size="10" class="readonly" readonly/>
	     <img src="./images/calendar/calendar7.gif" id="img_endOperateTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "endOperateTime", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_endOperateTime", 
			singleClick    :    true
			}); 
		</script>
        </td>
        <td nowrap="nowrap"><input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/></td>
        <td>&nbsp;</td>
      </tr>
</table>


</div>


<ec:table 
	tableId="sysOperationLogListTable" 
	items="sysOperationLogList" 
	var="sysOperationLog" 
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	form="sysOperationLogSearchForm"
	action="${pageContext.request.contextPath}/userOperationLogs.html" 
	width="100%" rowsDisplayed="20" 
	sortable="true" 
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="logId" title="title.view" sortable="false" style="white-space: nowrap;width:5px;">
  			<jecs:power powerCode="viewSysOperationLog">
				<a href="viewSysOperationLog.html?strAction=viewSysOperationLog&logId=${sysOperationLog.logId}">
				<img src="images/newIcons/search_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
		</ec:column>
		<ec:column property="companyCode" title="sysOperationLog.companyCode" />		 
		<ec:column property="operaterCode" title="sysUser.userCode" >
			${sysOperationLog.userCode }&nbsp;${sysOperationLog.userName}
		</ec:column>
		<ec:column property="operaterCode" title="sysOperationLog.operaterCode" >
			${sysOperationLog.operaterCode}&nbsp;${sysOperationLog.operaterName}
		</ec:column>
		<ec:column property="moduleName" title="sysOperationLog.moduleName" >
			[<jecs:code listCode="sysoperationlog.dotype" value="${sysOperationLog.doType}"/>]
			<jecs:locale key="${sysOperationLog.moduleName }"/>
		</ec:column>
		<ec:column property="visitUrl" title="sysOperationLog.visitUrl" />
		
		<ec:column property="operateTime" title="sysOperationLog.operateTime" />
		<ec:column property="ipAddr" title="comm.it.ip" />
	</ec:row>
</ec:table>

</form>

<script type="text/javascript">

function editSysOperationLog(logId){
<jecs:power powerCode="viewSysOperationLog">
	window.location="viewSysOperationLog.html?strAction=viewSysOperationLog&logId="+logId;
</jecs:power>
}

</script>