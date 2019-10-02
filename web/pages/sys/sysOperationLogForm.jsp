<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysOperationLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysOperationLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysOperationLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysOperationLog')" value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysOperationLog.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<form:form commandName="sysOperationLog" method="post" action="editSysOperationLog.html" onsubmit="return validateSysOperationLog(this)" id="sysOperationLogForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysOperationLog')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="sysOperationLog.operaterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterCode" cssClass="fieldError"/-->
        <form:input path="operaterCode" id="operaterCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.operaterName"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterName" cssClass="fieldError"/-->
        <form:input path="operaterName" id="operaterName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="comm.it.ip"/>
    </th>
    <td align="left">
        <!--form:errors path="ipAddr" cssClass="fieldError"/-->
        <form:input path="ipAddr" id="ipAddr" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.startTime"/>
    </th>
    <td align="left">
        <!--form:errors path="startTime" cssClass="fieldError"/-->
        <form:input path="startTime" id="startTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.endTime"/>
    </th>
    <td align="left">
        <!--form:errors path="endTime" cssClass="fieldError"/-->
        <form:input path="endTime" id="endTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.summary"/>
    </th>
    <td align="left">
        <!--form:errors path="summary" cssClass="fieldError"/-->
        <form:input path="summary" id="summary" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.currentStep"/>
    </th>
    <td align="left">
        <!--form:errors path="currentStep" cssClass="fieldError"/-->
        <form:input path="currentStep" id="currentStep" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.executeTime"/>
    </th>
    <td align="left">
        <!--form:errors path="executeTime" cssClass="fieldError"/-->
        <form:input path="executeTime" id="executeTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.result"/>
    </th>
    <td align="left">
        <!--form:errors path="result" cssClass="fieldError"/-->
        <form:input path="result" id="result" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.length"/>
    </th>
    <td align="left">
        <!--form:errors path="length" cssClass="fieldError"/-->
        <form:input path="length" id="length" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysOperationLog.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysOperationLog')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysOperationLogForm'));
</script>

<v:javascript formName="sysOperationLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
