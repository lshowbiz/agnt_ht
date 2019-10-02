<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.joymain.jecs.sys.service.SysDataLogManager" %>
<%@page import="com.joymain.jecs.sys.model.SysDataLog" %>
<%@page import="java.util.List" %>
<%@page import="com.joymain.jecs.webapp.util.LocaleUtil" %>
<%@page import="com.joymain.jecs.webapp.util.ListUtil" %>
<%@page import="com.joymain.jecs.webapp.util.SessionLogin"%>

<title><jecs:locale key="sysOperationLogList.title" /></title>
<content tag="heading">
<jecs:locale key="sysOperationLogList.heading" />
</content>
<meta name="menu" content="SysOperationLogMenu" />

<%
SysDataLogManager sysDataLogManager=(SysDataLogManager)request.getAttribute("sysDataLogManager");
%>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 



<form action="sysOperationLogs.html" method="get" name="sysOperationLogSearchForm" id="sysOperationLogSearchForm"  onsubmit="return validateSearch(this);">
<div class="searchBar">
	<div class="new_searchBar">
		<label><jecs:locale key="label.month"/></label>
		<select name="month" id="month">
			<c:forEach items="${months}" var="monthVar">
				<option value="${monthVar }"<c:if test="${monthVar==param.month}"> selected</c:if>>${monthVar}</option>
			</c:forEach>
		</select>
	</div>
	<div class="new_searchBar">
		
		<label><jecs:locale key="sysOperationLog.operaterCode"/></label>
		<input name="operaterCode" type="text" value="${param.operaterCode }" size="12"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:locale key="comm.it.ip"/></label>
		<input name="ipAddr" type="text" value="${param.ipAddr }" size="12"/>
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
	<div class="new_searchBar">		
			
		<label><jecs:locale key="sysDataLog.beforeChange"/></label>
				<input name="beforeChange" type="text" value="${param.beforeChange }" size="14"/>
	</div>
	<div class="new_searchBar">		
		<label><jecs:locale key="sysDataLog.afterChange"/></label>
			<input name="afterChange" type="text" value="${param.afterChange }" size="14"/>
	</div>
	<div class="new_searchBar" style="margin-left:50px;">
		<button name="search" class="btn btn_sele" type="submit" >
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
  				
				<a href="javascript:showSingleOperation('${sysOperationLog.log_id}')">
				<img src="images/newIcons/search_16.gif" border="0" width="16" height="16" /></a>
			</jecs:power>
		</ec:column>
		<ec:column property="operate_time" title="sysOperationLog.operateTime" />
		<ec:column property="operater_code" title="title.operator" >
			${sysOperationLog.operater_code }&nbsp;${sysOperationLog.operater_name }
		</ec:column>
		<ec:column property="ip_addr" title="comm.it.ip" />
			<ec:column property="visit_url" title="URL" />
		<ec:column property="module_name" title="sysOperationLog.moduleName" >
			[<jecs:code listCode="sysoperationlog.dotype" value="${sysOperationLog.do_type}"/>]
			<jecs:locale key="${sysOperationLog.module_name }"/>
				
		</ec:column>
		<ec:column property="company_code" title="sysOperationLog.companyCode" />
	</ec:row>
	
	<c:if test="${ROWCOUNT>0}">
		<c:if test="${ROWCOUNT%2!=0}"><tr class="odd"></c:if>
		<c:if test="${ROWCOUNT%2==0}"><tr class="even"></c:if>
			<td colspan="4">&nbsp;</td>
			<td colspan="2">
			<c:set var="operationLogId" value="${sysOperationLog.log_id}" scope="request"/>
			<font color="#888888">
			<%
			Long logId=new Long(request.getAttribute("operationLogId").toString());
			List sysDataLogs=sysDataLogManager.getSysDataLogsByOperation(logId);
			if(sysDataLogs!=null && sysDataLogs.size()>0){
				String oldTableName="";
				String oldPIdName="";
				String oldPIdValue="";
				boolean showTitle=true;
				for(int i=0;i<sysDataLogs.size();i++){
					SysDataLog sysDataLog=(SysDataLog)sysDataLogs.get(i);
					if(i==0){
						oldTableName=sysDataLog.getTableName();
						oldPIdName=sysDataLog.getPidName();
						oldPIdValue=sysDataLog.getPidValue();
					}
					if(showTitle){
						out.print("<font color=red>"+ListUtil.getListValue(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"change_type",sysDataLog.getChangeType())+"</font><br>");
						out.print(sysDataLogManager.getFieldComment(sysDataLog.getTableName(), sysDataLog.getPidName()).toLowerCase()+" = [ "+sysDataLog.getPidValue()+"]<br>");
					}
					
					showTitle=false;
					out.println(sysDataLogManager.getFieldComment(sysDataLog.getTableName(), sysDataLog.getColumnName()).toLowerCase()+ " = [ "+sysDataLog.getBeforeChange()+" ] --> [ "+sysDataLog.getAfterChange()+" ]<br>");
					if(!oldTableName.equalsIgnoreCase(sysDataLog.getTableName()) || !oldPIdName.equalsIgnoreCase(sysDataLog.getPidName()) || !oldPIdValue.equalsIgnoreCase(sysDataLog.getPidValue())){
						showTitle=true;
						out.println("<hr/>");
					}
					
					oldTableName=sysDataLog.getTableName();
					oldPIdName=sysDataLog.getPidName();
					oldPIdValue=sysDataLog.getPidValue();
				}
			}
			%>
			</font>
			</td>
		</tr>
	</c:if>
	
</ec:table>
</form>
<script type="text/javascript">
<!--
function showSingleOperation(logId){

	var str = "<c:url value='/sysDataLogs.html'/>?search=search&operationLogId="+logId+"&viewType=showSingleOperation&month="+$('month').value;
		open(str);
	}
function validateSearch(theForm){
	if(theForm.operaterCode.value=="" && theForm.ipAddr.value=="" && theForm.moduleName.value=="" && theForm.beforeChange.value=="" && theForm.afterChange.value=="" && theForm.startOperateTime.value=="" && theForm.endOperateTime.value==""){
		alert("<jecs:locale key="please.input.search.condition"/>");
		return false;
	}
	return true;
}
//-->
</script>