<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysLoginLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysLoginLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysLoginLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysLoginLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysLoginLog.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="sysLoginLog" method="post" action="editSysLoginLog.html" onsubmit="return validateSysLoginLog(this)" id="sysLoginLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysLoginLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="llId"/>

    <tr><th>
        <jecs:label  key="sysLoginLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysLoginLog.operaterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterCode" cssClass="fieldError"/-->
        <form:input path="operaterCode" id="operaterCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysLoginLog.ipAddr"/>
    </th>
    <td align="left">
        <!--form:errors path="ipAddr" cssClass="fieldError"/-->
        <form:input path="ipAddr" id="ipAddr" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysLoginLog.operateTime"/>
    </th>
    <td align="left">
        <!--form:errors path="operateTime" cssClass="fieldError"/-->
        <form:input path="operateTime" id="operateTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysLoginLog.operationType"/>
    </th>
    <td align="left">
        <!--form:errors path="operationType" cssClass="fieldError"/-->
        <form:input path="operationType" id="operationType" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysLoginLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysLoginLogForm'));
</script>

<v:javascript formName="sysLoginLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
