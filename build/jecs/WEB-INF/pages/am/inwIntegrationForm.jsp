<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwIntegrationDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwIntegrationDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteInwIntegration">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'InwIntegration')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="inwIntegration.*">
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

<form:form commandName="inwIntegration" method="post" action="editInwIntegration.html" onsubmit="return validateInwIntegration(this)" id="inwIntegrationForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('InwIntegration')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="inwIntegration.suggestionUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="suggestionUserCode" cssClass="fieldError"/-->
        <form:input path="suggestionUserCode" id="suggestionUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.integrationAdd"/>
    </th>
    <td align="left">
        <!--form:errors path="integrationAdd" cssClass="fieldError"/-->
        <form:input path="integrationAdd" id="integrationAdd" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.integrationAddTime"/>
    </th>
    <td align="left">
        <!--form:errors path="integrationAddTime" cssClass="fieldError"/-->
        <form:input path="integrationAddTime" id="integrationAddTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.suggestionid"/>
    </th>
    <td align="left">
        <!--form:errors path="suggestionid" cssClass="fieldError"/-->
        <form:input path="suggestionid" id="suggestionid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.integrationMinus"/>
    </th>
    <td align="left">
        <!--form:errors path="integrationMinus" cssClass="fieldError"/-->
        <form:input path="integrationMinus" id="integrationMinus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.integrationActivity"/>
    </th>
    <td align="left">
        <!--form:errors path="integrationActivity" cssClass="fieldError"/-->
        <form:input path="integrationActivity" id="integrationActivity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.integrationMinusTime"/>
    </th>
    <td align="left">
        <!--form:errors path="integrationMinusTime" cssClass="fieldError"/-->
        <form:input path="integrationMinusTime" id="integrationMinusTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.operatorUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="operatorUserCode" cssClass="fieldError"/-->
        <form:input path="operatorUserCode" id="operatorUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwIntegration.other"/>
    </th>
    <td align="left">
        <!--form:errors path="other" cssClass="fieldError"/-->
        <form:input path="other" id="other" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('InwIntegration')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('inwIntegrationForm'));
</script>

<v:javascript formName="inwIntegration" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
