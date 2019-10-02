<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysDataLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysDataLogDetail.heading"/></content>

<form:form commandName="sysDataLog" method="post" action="editSysDataLog.html" onsubmit="return validateSysDataLog(this)" id="sysDataLogForm">

<%
request.setAttribute("vEnter", "\n");
%>
<table width="100%" class="detail">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="sysDataLog.changeType"/>
    </th>
    <td width="100%"><jecs:code listCode="change_type" value="${sysDataLog.changeType}"/></td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.tableName"/>
    </th>
    <td>${sysDataLog.tableName }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.columnName"/>
    </th>
    <td>${sysDataLog.columnName }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.pidName"/>
    </th>
    <td>${sysDataLog.pidName }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.pidValue"/>
    </th>
    <td>${sysDataLog.pidValue }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.beforeChange"/>
    </th>
    <td>${fn:replace(sysDataLog.beforeChange, vEnter, '<br>')}</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.afterChange"/>
    </th>
    <td>${fn:replace(sysDataLog.afterChange, vEnter, '<br>')}</td></tr>

    <tr><th>
        <jecs:label  key="sysUser.userCode"/>
    </th>
    <td>${sysDataLog.operatorPerson }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.operatorTime"/>
    </th>
    <td>${sysDataLog.operatorTime }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.moduleName"/>
    </th>
    <td>${sysDataLog.moduleName }</td></tr>

    <tr><th>
        <jecs:label  key="sysClickLog.hostName"/>
    </th>
    <td>${sysDataLog.hostName }</td></tr>

    <tr><th>
        <jecs:label  key="sysDataLog.ipAddress"/>
    </th>
    <td>${sysDataLog.ipAddress }</td></tr>
    
    <tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<input type="button" class="button" name="cancel" onclick="window.location='sysDataLogs.html?needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" />
		</td>
	</tr>
</table>

</form:form>