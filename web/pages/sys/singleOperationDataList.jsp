<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.joymain.jecs.sys.service.SysDataLogManager" %>
<%@page import="com.joymain.jecs.sys.model.SysDataLog" %>
<%@page import="java.util.List" %>
<%@page import="com.joymain.jecs.webapp.util.LocaleUtil" %>
<%@page import="com.joymain.jecs.webapp.util.ListUtil" %>
<%@page import="com.joymain.jecs.webapp.util.SessionLogin"%>



<title><jecs:locale key="sysDataLogList.title"/></title>
<content tag="heading"><jecs:locale key="sysDataLogList.heading"/></content>
<meta name="menu" content="SysDataLogMenu"/>

<script language="javascript" src="scripts/validate.js"></script>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="sysDataLogs.html" method="get" name="sysDataLogForm" id="sysDataLogForm">
<input type="hidden" name="search" id="search" value="search"/>
	


<ec:table 
	items="sysDataLogList"
	tableId="sysDataLogListTable"
	var="sysDataLog"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/sysDataLogs.html"
	width="100%"
	form="sysDataLogForm"
	rowsDisplayed="20"
	sortable="true" 
	autoIncludeParameters="true"
	imagePath="./images/extremetable/*.gif">
		<ec:row>
		
  			<ec:column property="change_type" title="sysDataLog.changeType" >
  				<jecs:code listCode="change_type" value="${sysDataLog.change_type}"/>
  			</ec:column>
  			<ec:column property="table_name" title="sysDataLog.tableName"/>
  			<ec:column property="column_name" title="sysDataLog.columnName"/>	
  			<ec:column property="before_change" title="sysDataLog.beforeChange" />
  			<ec:column property="after_change" title="sysDataLog.afterChange" />
  			<ec:column property="operator_person" title="sysOperationLog.operaterCode" />
  			<ec:column property="operator_time" title="sysDataLog.operatorTime" />
  			<ec:column property="host_name" title="sysClickLog.hostName" />
  			<ec:column property="ip_address" title="sysDataLog.ipAddress" />
		</ec:row>
</ec:table>	
</form>
<script type="text/javascript">

function validateForm(theForm){
	/**
   	if(theForm.startOperatorTime.value!="" && !isValidDateFormat(theForm.startOperatorTime.value,"-")){
		alert("<jecs:locale key="sysDataLog.startOperatorTime.error"/>");
		theForm.startOperatorTime.focus();
		return false;
	}
	
	if(theForm.endOperatorTime.value!="" && !isValidDateFormat(theForm.endOperatorTime.value,"-")){
		alert("<jecs:locale key="sysDataLog.endOperatorTime.error"/>");
		theForm.endOperatorTime.focus();
		return false;
	}
**/
   	return true;
}

</script>
