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

<form action="sysDataLogs.html" method="get" name="sysDataLogForm" id="sysDataLogForm" onsubmit="return validateForm(this)">
<input type="hidden" name="search" id="search" value="search"/>
<div class="searchBar">
	<div class="new_searchBar">
		<label><jecs:locale key="label.month"/></label>
			<select name="month">
			<c:forEach items="${months}" var="monthVar">
				<option value="${monthVar }"<c:if test="${monthVar==param.month}"> selected</c:if>>${monthVar}</option>
			</c:forEach>
		</select>
	</div>
	<div class="new_searchBar">	
		<label><jecs:title key="sysDataLog.changeType"/></label>
		<jecs:list name="changeType" listCode="change_type" value="${param.changeType }" defaultValue="" showBlankLine="true"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:title key="sysDataLog.beforeChange"/></label>
		<input name="beforeChange" type="text" value="${param.beforeChange }" size="14"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:title key="sysDataLog.tableName"/></label>
		<input name="tableName" type="text" value="${param.tableName }" size="14"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:title key="sysDataLog.columnName"/></label>
		<input name="columnName" type="text" value="${param.columnName }" size="14"/>
	</div>
	<div class="new_searchBar">		
		
		<label><jecs:title key="sysDataLog.afterChange"/></label>
		<input name="afterChange" type="text" value="${param.afterChange }" size="14"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:title key="sysDataLog.pidValue"/></label>
		<input name="pidValue" type="text" value="${param.pidValue }" size="10"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:title key="sysOperationLog.operaterCode"/></label>
		<input name="operatorPerson" type="text" value="${param.operatorPerson }" size="10"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:title key="sysDataLog.operatorTime"/></label>
		<input name="startOperatorTime" id="startOperatorTime" type="text" 
			value="${param.startOperatorTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	     - 
	    <input name="endOperatorTime" id="endOperatorTime" type="text" 
			value="${param.endOperatorTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
	<div class="new_searchBar" style="margin-left:60px;">
		<button name="search" class="btn btn_sele" type="submit" />
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>

</div>


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
  			<ec:column property="p_id_value" title="sysDataLog.pidValue"/>	
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
