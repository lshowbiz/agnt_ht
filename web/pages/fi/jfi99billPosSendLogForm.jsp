<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billPosSendLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billPosSendLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfi99billPosSendLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'Jfi99billPosSendLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfi99billPosSendLog.*">
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

<form:form commandName="jfi99billPosSendLog" method="post" action="editJfi99billPosSendLog.html" onsubmit="return validateJfi99billPosSendLog(this)" id="jfi99billPosSendLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Jfi99billPosSendLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.actionType"/>
    </th>
    <td align="left">
        <!--form:errors path="actionType" cssClass="fieldError"/-->
        <form:input path="actionType" id="actionType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.actionNo"/>
    </th>
    <td align="left">
        <!--form:errors path="actionNo" cssClass="fieldError"/-->
        <form:input path="actionNo" id="actionNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.createCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createCode" cssClass="fieldError"/-->
        <form:input path="createCode" id="createCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.returnTime"/>
    </th>
    <td align="left">
        <!--form:errors path="returnTime" cssClass="fieldError"/-->
        <form:input path="returnTime" id="returnTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billPosSendLog.hashMd5Code"/>
    </th>
    <td align="left">
        <!--form:errors path="hashMd5Code" cssClass="fieldError"/-->
        <form:input path="hashMd5Code" id="hashMd5Code" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Jfi99billPosSendLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfi99billPosSendLogForm'));
</script>

<v:javascript formName="jfi99billPosSendLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
