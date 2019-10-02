<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysClickLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysClickLogDetail.heading"/></content>

<form:form commandName="sysClickLog" method="post" action="editSysClickLog.html" onsubmit="return validateSysClickLog(this)" id="sysClickLogForm">

<table width="100%" class="detail">

<form:hidden path="logId"/>
    <tr><th  align="right">
        <jecs:label  key="sysClickLog.remoteUser"/>
    </th>
    <td align="left">${sysClickLog.remoteUser }</td></tr>

    <tr><th  align="right">
        <jecs:label  key="sysClickLog.runDate"/>
    </th>
    <td align="left">${sysClickLog.runDate }</td></tr>

    <tr><th  align="right">
        <jecs:label  key="comm.it.ip"/>
    </th>
    <td align="left">${sysClickLog.ipAddress }</td></tr>

    <tr><th  align="right">
        <jecs:label  key="sysClickLog.hostName"/>
    </th>
    <td align="left">${sysClickLog.hostName }</td></tr>

    <tr><th  align="right">
        <jecs:label  key="sysClickLog.clickUri"/>
    </th>
    <td align="left">${sysClickLog.clickUri }</td></tr>

    <tr><th  align="right">
        <jecs:label  key="sysClickLog.content"/>
    </th>
    <td align="left">
    <%
	request.setAttribute("vEnter", "\n");
	%>
	${fn:replace(sysClickLog.content, vEnter, '<br>')}
    </td></tr>
    
    <tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<input type="button" class="button" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" />
		</td>
	</tr>
</table>
</form:form>