<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysVisitLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysVisitLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysVisitLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysVisitLog<jecs:localefmt:message key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysVisitLog.*">
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

<form:form commandName="sysVisitLog" method="post" action="editSysVisitLog.html" onsubmit="return validateSysVisitLog(this)" id="sysVisitLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return <jecs:localee('SysVisitLog')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="sysVisitLog.moduleCode"/>
    </th>
    <td align="left">
        <!--form:errors path="moduleCode" cssClass="fieldError"/-->
        <form:input path="moduleCode" id="moduleCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysVisitLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysVisitLog.visitUrl"/>
    </th>
    <td align="left">
        <!--form:errors path="visitUrl" cssClass="fieldError"/-->
        <form:input path="visitUrl" id="visitUrl" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysVisitLog.visitTime"/>
    </th>
    <td align="left">
        <!--form:errors path="visitTime" cssClass="fieldError"/-->
        <form:input path="visitTime" id="visitTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
     <jecs:localepe="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="butt<jecs:localelete" onclick="bCancel=true;return confirmDelete('SysVisitLog')" value="<fmt:message key="button.delete"/>" />
   <jecs:localetype="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysVisitLogForm'));
</script>

<v:javascript formName="sysVisitLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
