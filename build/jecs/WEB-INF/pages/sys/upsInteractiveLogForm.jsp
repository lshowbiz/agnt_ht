<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="upsInteractiveLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="upsInteractiveLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteUpsInteractiveLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'UpsInteractiveLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="upsInteractiveLog.*">
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

<form:form commandName="upsInteractiveLog" method="post" action="editUpsInteractiveLog.html" onsubmit="return validateUpsInteractiveLog(this)" id="upsInteractiveLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('UpsInteractiveLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniId"/>

    <tr><th>
        <jecs:label  key="upsInteractiveLog.type"/>
    </th>
    <td align="left">
        <!--form:errors path="type" cssClass="fieldError"/-->
        <form:input path="type" id="type" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="upsInteractiveLog.orderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <form:input path="orderNo" id="orderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="upsInteractiveLog.send"/>
    </th>
    <td align="left">
        <!--form:errors path="send" cssClass="fieldError"/-->
        <form:input path="send" id="send" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="upsInteractiveLog.receive"/>
    </th>
    <td align="left">
        <!--form:errors path="receive" cssClass="fieldError"/-->
        <form:input path="receive" id="receive" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="upsInteractiveLog.createTime"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('UpsInteractiveLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('upsInteractiveLogForm'));
</script>

<v:javascript formName="upsInteractiveLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
