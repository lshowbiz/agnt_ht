<%@ page pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<form name="jocsInterfaceLogForm" method="post"  action="${pageContext.request.contextPath}/pdShipFees.html" >
<div class="searchBar">
<input type="hidden" name="strAction" value="jocsInterfaceLog" />
	<div class="new_searchBar">
		<label>方法名</label>
		<input name="method" id="method" value="<c:out value='${param.method}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">
		<label>内容</label>
		<input name="contentText" id="contentText" value="<c:out value='${param.contentText}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">
		<label>日志收发类型</label>
		<jecs:list listCode="jocsinterfacelog.logtype" name="logType" showBlankLine="true" id="logType" value="${param.logType}" defaultValue=""/>
	</div>
	<div class="new_searchBar">
		<label>来源</label>
		<jecs:list listCode="jocsinterfacelog.flag" name="flag" showBlankLine="true" id="flag" value="${param.flag}" defaultValue=""/>
	</div>
	<div class="new_searchBar">
	<label>接收时间</label>
		
		<input name="logStartTime" id="logStartTime" type="text" 
			value="${logStartTime}" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		- 
		<input name="logEndTime" id="logEndTime" type="text" 
			value="${logEndTime}" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
	<div class="new_searchBar" style="margin-left:50px;">
		<button name="search" class="btn btn_sele" type="submit" >
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>
</div>
<ec:table 
	tableId="jocsInterfaceLogListTable"
	items="jocsInterfaceLogList"
	var="jocsInterfaceLog"
	action="${pageContext.request.contextPath}/pdShipFees.html?strAction=jocsInterfaceLog"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" form="jocsInterfaceLogForm">
		<ec:row style="text-align: center;">
			<ec:column property="logType" title="日志收发类型" >
    				<jecs:code listCode="jocsinterfacelog.logtype" value="${jocsInterfaceLog.logType}"/>
    		</ec:column>
   			<ec:column property="flag" title="来源" >
    				<jecs:code listCode="jocsinterfacelog.flag" value="${jocsInterfaceLog.flag}"/>
    		</ec:column>
   			<ec:column property="method" title="方法名" />
   			<ec:column property="reciverTime" title="接收时间" >
   				<fmt:formatDate value="${jocsInterfaceLog.reciverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
   			</ec:column>
   			<ec:column property="returnResult" title="结果标识" />
   			<ec:column property="returnDesc" title="结果详细" />
   			<ec:column property="sender" title="sender" >
    			<c:choose>
    				<c:when test="${jocsInterfaceLog.sender=='1'}">SCH</c:when>
    				<c:when test="${jocsInterfaceLog.sender=='2'}">OMS</c:when>
    				<c:when test="${jocsInterfaceLog.sender=='3'}">CRM</c:when>
    				<c:when test="${jocsInterfaceLog.sender=='4'}">GHB</c:when>
    				<c:when test="${jocsInterfaceLog.sender=='5'}">XW</c:when>
    			</c:choose>
    		</ec:column>
		</ec:row>
</ec:table>	

</form>