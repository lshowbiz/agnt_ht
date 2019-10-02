<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="scheduleManageDetail.title"/></title>
<content tag="heading"><jecs:locale key="scheduleManageDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteScheduleManage">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'ScheduleManage')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="scheduleManage.*">
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

<form:form commandName="scheduleManage" method="post" action="editScheduleManage.html" onsubmit="return validateScheduleManage(this)" id="scheduleManageForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('ScheduleManage')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="scheduleManage.scheduleName"/>
    </th>
    <td align="left">
        <!--form:errors path="scheduleName" cssClass="fieldError"/-->
        <form:input path="scheduleName" id="scheduleName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.startTime"/>
    </th>
    <td align="left">
        <!--form:errors path="startTime" cssClass="fieldError"/-->
        <form:input path="startTime" id="startTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.endTime"/>
    </th>
    <td align="left">
        <!--form:errors path="endTime" cssClass="fieldError"/-->
        <form:input path="endTime" id="endTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.priority"/>
    </th>
    <td align="left">
        <!--form:errors path="priority" cssClass="fieldError"/-->
        <form:input path="priority" id="priority" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.linkmanId"/>
    </th>
    <td align="left">
        <!--form:errors path="linkmanId" cssClass="fieldError"/-->
        <form:input path="linkmanId" id="linkmanId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.remind"/>
    </th>
    <td align="left">
        <!--form:errors path="remind" cssClass="fieldError"/-->
        <form:input path="remind" id="remind" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.repeat"/>
    </th>
    <td align="left">
        <!--form:errors path="repeat" cssClass="fieldError"/-->
        <form:input path="repeat" id="repeat" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.loginUserNo"/>
    </th>
    <td align="left">
        <!--form:errors path="loginUserNo" cssClass="fieldError"/-->
        <form:input path="loginUserNo" id="loginUserNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.eventType"/>
    </th>
    <td align="left">
        <!--form:errors path="eventType" cssClass="fieldError"/-->
        <form:input path="eventType" id="eventType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="scheduleManage.place"/>
    </th>
    <td align="left">
        <!--form:errors path="place" cssClass="fieldError"/-->
        <form:input path="place" id="place" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('ScheduleManage')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('scheduleManageForm'));
</script>

<v:javascript formName="scheduleManage" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
