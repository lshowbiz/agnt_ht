<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysCompanyPowDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysCompanyPowDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysCompanyPow">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysCompanyPow<jecs:localefmt:message key="operation.button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysCompanyPow.*">
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

<form:form commandName="sysCompanyPow" method="post" action="editSysCompanyPow.html" onsubmit="return validateSysCompanyPow(this)" id="sysCompanyPowForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return co<jecs:locale'SysCompanyPow')" value="<fmt:message key="operation.button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="operation.button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="sysCompanyPow.powerId"/>
    </th>
    <td align="left">
        <!--form:errors path="powerId" cssClass="fieldError"/-->
        <form:input path="powerId" id="powerId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysCompanyPow.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="<jecs:locales="button" name="save"  onclick="bCancel=false" value="<fmt:message key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" <jecs:localencel=true;return confirmDelete('SysCompanyPow')" value="<fmt:message key="operation.button.delete"/>" />
        <input type<jecs:localeass="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysCompanyPowForm'));
</script>

<v:javascript formName="sysCompanyPow" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
