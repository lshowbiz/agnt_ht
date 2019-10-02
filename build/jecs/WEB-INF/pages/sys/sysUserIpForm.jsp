<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserIpDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysUserIpDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysUserIp">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysUserIp<jecs:localefmt:message key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysUserIp.*">
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

<form:form commandName="sysUserIp" method="post" action="editSysUserIp.html" onsubmit="return validateSysUserIp(this)" id="sysUserIpForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;retur<jecs:localeete('SysUserIp')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="ipId"/>

    <tr><th>
        <jecs:label  key="sysUserIp.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysUserIp.ipAddress"/>
    </th>
    <td align="left">
        <!--form:errors path="ipAddress" cssClass="fieldError"/-->
        <form:input path="ipAddress" id="ipAddress" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="<jecs:locales="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="dele<jecs:locale"bCancel=true;return confirmDelete('SysUserIp')" value="<fmt:message key="button.delete"/>" />
        <input type<jecs:localeass="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysUserIpForm'));
</script>

<v:javascript formName="sysUserIp" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
