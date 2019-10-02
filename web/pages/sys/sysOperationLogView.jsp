<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.joymain.jecs.sys.service.SysDataLogManager" %>
<%@page import="com.joymain.jecs.sys.model.SysDataLog" %>
<%@page import="com.joymain.jecs.sys.model.SysOperationLog" %>
<%@page import="java.util.List" %>
<%@page import="com.joymain.jecs.webapp.util.LocaleUtil" %>
<%@page import="com.joymain.jecs.webapp.util.ListUtil" %>
<%@page import="com.joymain.jecs.webapp.util.SessionLogin"%>

<%
SysDataLogManager sysDataLogManager=(SysDataLogManager)request.getAttribute("sysDataLogManager");
%>

<title><jecs:locale key="sysOperationLogDetail.title" /></title>
<content tag="heading">
<jecs:locale key="sysOperationLogDetail.heading" />
</content>

<form:form commandName="sysOperationLog" method="post" action="editSysOperationLog.html" onsubmit="return validateSysOperationLog(this)" id="sysOperationLogForm">

	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="100%">
		<form:hidden path="logId" />

		<tr>
			<th><jecs:label key="sysOperationLog.operateTime" /></th>
			<td>${sysOperationLog.operateTime }</td>
		</tr>
		
		<tr>
			<th><jecs:label key="sysOperationLog.companyCode" /></th>
			<td width="100%">${sysOperationLog.companyCode }</td>
		</tr>

		<tr>
			<th><jecs:label key="sysUser.userCode" /></th>
			<td>${sysOperationLog.userCode } / ${sysOperationLog.userName }</td>
		</tr>		
		<tr>
			<th><jecs:label key="sysOperationLog.moduleName" /></th>
			<td>
				<jecs:code listCode="sysoperationlog.dotype" value="${sysOperationLog.doType}" /> 
				<jecs:locale key="${sysOperationLog.moduleName }" />
			</td>
		</tr>

		<tr>
			<th><jecs:label key="sysOperationLog.visitUrl" /></th>
			<td>${sysOperationLog.visitUrl }</td>
		</tr>
		<tr>
			<th><jecs:label key="sysOperationLog.operaterCode" /></th>
			<td>${sysOperationLog.operaterCode }/${sysOperationLog.operaterName }</td>
		</tr>
		
		<c:if test="${param.viewData=='postData'}">
		<tr>
			<th><jecs:label key="sysOperationLog.operateData" /></th>
			<td>

			<%
			request.setAttribute("x_n", "\n");
			%>
			${fn:replace(sysOperationLog.operateData, x_n, '<br/>')}

			</td>
		</tr>
		</c:if>

		<tr>
			<th><jecs:label key="comm.it.ip" /></th>
			<td>${sysOperationLog.ipAddr }</td>
		</tr>
		
		<c:if test="${param.viewData=='changedData'}">
		<tr>
			<th><jecs:label key="msgclassno.5" /></th>
			<td>
			<%
			SysOperationLog sysOperationLog=(SysOperationLog)request.getAttribute("sysOperationLog");
			List sysDataLogs=sysDataLogManager.getSysDataLogsByOperation(sysOperationLog.getLogId());
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
			</td>
		</tr>
		</c:if>
		
		<tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<input type="button" class="button" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" />
		</td>
	</tr>

	</table>

</form:form>