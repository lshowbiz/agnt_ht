<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCoinConfigDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiCoinConfigDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteFiCoinConfig">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiCoinConfig')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="fiCoinConfig.*">
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

<form:form commandName="fiCoinConfig" method="post" action="editFiCoinConfig.html" onsubmit="return validateFiCoinConfig(this)" id="fiCoinConfigForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiCoinConfig')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="configId"/>

    <tr><th>
        <jecs:label  key="fiCoinConfig.configName"/>
    </th>
    <td align="left">
        <!--form:errors path="configName" cssClass="fieldError"/-->
        <form:input path="configName" id="configName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.startTime"/>
    </th>
    <td align="left">
        <!--form:errors path="startTime" cssClass="fieldError"/-->
        <form:input path="startTime" id="startTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.endTime"/>
    </th>
    <td align="left">
        <!--form:errors path="endTime" cssClass="fieldError"/-->
        <form:input path="endTime" id="endTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.memberTopCode"/>
    </th>
    <td align="left">
        <!--form:errors path="memberTopCode" cssClass="fieldError"/-->
        <form:input path="memberTopCode" id="memberTopCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.description"/>
    </th>
    <td align="left">
        <!--form:errors path="description" cssClass="fieldError"/-->
        <form:input path="description" id="description" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.field1"/>
    </th>
    <td align="left">
        <!--form:errors path="field1" cssClass="fieldError"/-->
        <form:input path="field1" id="field1" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.createCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createCode" cssClass="fieldError"/-->
        <form:input path="createCode" id="createCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.createName"/>
    </th>
    <td align="left">
        <!--form:errors path="createName" cssClass="fieldError"/-->
        <form:input path="createName" id="createName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinConfig.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiCoinConfig')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiCoinConfigForm'));
</script>

<v:javascript formName="fiCoinConfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
