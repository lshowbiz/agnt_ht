<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiStateLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiStateLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJmiStateLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiStateLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jmiStateLog.*">
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

<form:form commandName="jmiStateLog" method="post" action="editJmiStateLog.html" onsubmit="return validateJmiStateLog(this)" id="jmiStateLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiStateLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jmiStateLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiStateLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiStateLog.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiStateLog.logType"/>
    </th>
    <td align="left">
        <!--form:errors path="logType" cssClass="fieldError"/-->
        <form:input path="logType" id="logType" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiStateLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiStateLogForm'));
</script>

<v:javascript formName="jmiStateLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>