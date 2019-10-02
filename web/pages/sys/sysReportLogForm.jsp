<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysReportLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysReportLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysReportLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysReportLog<jecs:localefmt:message key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysReportLog.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
 <jecs:locale   alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="sysReportLog" method="post" action="editSysReportLog.html" onsubmit="return validateSysReportLog(this)" id="sysReportLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return c<jecs:locale('SysReportLog')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="reportId"/>

    <tr><th>
        <jecs:label  key="sysReportLog.reportName"/>
    </th>
    <td align="left">
        <!--form:errors path="reportName" cssClass="fieldError"/-->
        <form:input path="reportName" id="reportName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.fileName"/>
    </th>
    <td align="left">
        <!--form:errors path="fileName" cssClass="fieldError"/-->
        <form:input path="fileName" id="fileName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.usedTime"/>
    </th>
    <td align="left">
        <!--form:errors path="usedTime" cssClass="fieldError"/-->
        <form:input path="usedTime" id="usedTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.recordsCount"/>
    </th>
    <td align="left">
        <!--form:errors path="recordsCount" cssClass="fieldError"/-->
        <form:input path="recordsCount" id="recordsCount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysReportLog.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="1<jecs:localetr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <<jecs:localesubmit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysReportLog')" value="<fmt:messag<jecs:localen.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysReportLogForm'));
</script>

<v:javascript formName="sysReportLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
