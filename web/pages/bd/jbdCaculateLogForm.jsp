<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCaculateLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdCaculateLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdCaculateLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdCaculateLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdCaculateLog.*">
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

<form:form commandName="jbdCaculateLog" method="post" action="editJbdCaculateLog.html" onsubmit="return validateJbdCaculateLog(this)" id="jbdCaculateLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdCaculateLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.currentStep"/>
    </th>
    <td align="left">
        <!--form:errors path="currentStep" cssClass="fieldError"/-->
        <form:input path="currentStep" id="currentStep" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.errno"/>
    </th>
    <td align="left">
        <!--form:errors path="errno" cssClass="fieldError"/-->
        <form:input path="errno" id="errno" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.errmsg"/>
    </th>
    <td align="left">
        <!--form:errors path="errmsg" cssClass="fieldError"/-->
        <form:input path="errmsg" id="errmsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdCaculateLog.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdCaculateLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdCaculateLogForm'));
</script>

<v:javascript formName="jbdCaculateLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
