<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdPeriodDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteBdPeriod">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'BdPeriod')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="bdPeriod.*">
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

<form:form commandName="bdPeriod" method="post" action="editBdPeriod.html" onsubmit="return validateBdPeriod(this)" id="bdPeriodForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('BdPeriod')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="bdPeriod.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.startTime"/>
    </th>
    <td align="left">
        <!--form:errors path="startTime" cssClass="fieldError"/-->
        <form:input path="startTime" id="startTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.endTime"/>
    </th>
    <td align="left">
        <!--form:errors path="endTime" cssClass="fieldError"/-->
        <form:input path="endTime" id="endTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.lastMark"/>
    </th>
    <td align="left">
        <!--form:errors path="lastMark" cssClass="fieldError"/-->
        <form:input path="lastMark" id="lastMark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.bonusSend"/>
    </th>
    <td align="left">
        <!--form:errors path="bonusSend" cssClass="fieldError"/-->
        <form:input path="bonusSend" id="bonusSend" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.archivingStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="archivingStatus" cssClass="fieldError"/-->
        <form:input path="archivingStatus" id="archivingStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.monthStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="monthStatus" cssClass="fieldError"/-->
        <form:input path="monthStatus" id="monthStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdPeriod.oldWeek"/>
    </th>
    <td align="left">
        <!--form:errors path="oldWeek" cssClass="fieldError"/-->
        <form:input path="oldWeek" id="oldWeek" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('BdPeriod')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('bdPeriodForm'));
</script>

<v:javascript formName="bdPeriod" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
