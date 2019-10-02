<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJmiMemberLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiMemberLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jmiMemberLog.*">
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

<form:form commandName="jmiMemberLog" method="post" action="editJmiMemberLog.html" onsubmit="return validateJmiMemberLog(this)" id="jmiMemberLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiMemberLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jmiMemberLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.beforeBank"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeBank" cssClass="fieldError"/-->
        <form:input path="beforeBank" id="beforeBank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.beforeBankaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeBankaddress" cssClass="fieldError"/-->
        <form:input path="beforeBankaddress" id="beforeBankaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.beforeBankbook"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeBankbook" cssClass="fieldError"/-->
        <form:input path="beforeBankbook" id="beforeBankbook" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.beforeBankcard"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeBankcard" cssClass="fieldError"/-->
        <form:input path="beforeBankcard" id="beforeBankcard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.beforeBankprovince"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeBankprovince" cssClass="fieldError"/-->
        <form:input path="beforeBankprovince" id="beforeBankprovince" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.beforeBankcity"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeBankcity" cssClass="fieldError"/-->
        <form:input path="beforeBankcity" id="beforeBankcity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.logTime"/>
    </th>
    <td align="left">
        <!--form:errors path="logTime" cssClass="fieldError"/-->
        <form:input path="logTime" id="logTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.logType"/>
    </th>
    <td align="left">
        <!--form:errors path="logType" cssClass="fieldError"/-->
        <form:input path="logType" id="logType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.logUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="logUserCode" cssClass="fieldError"/-->
        <form:input path="logUserCode" id="logUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.afterBank"/>
    </th>
    <td align="left">
        <!--form:errors path="afterBank" cssClass="fieldError"/-->
        <form:input path="afterBank" id="afterBank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.afterBankaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="afterBankaddress" cssClass="fieldError"/-->
        <form:input path="afterBankaddress" id="afterBankaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.afterBankbook"/>
    </th>
    <td align="left">
        <!--form:errors path="afterBankbook" cssClass="fieldError"/-->
        <form:input path="afterBankbook" id="afterBankbook" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.afterBankcard"/>
    </th>
    <td align="left">
        <!--form:errors path="afterBankcard" cssClass="fieldError"/-->
        <form:input path="afterBankcard" id="afterBankcard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.afterBankprovince"/>
    </th>
    <td align="left">
        <!--form:errors path="afterBankprovince" cssClass="fieldError"/-->
        <form:input path="afterBankprovince" id="afterBankprovince" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiMemberLog.afterBankcity"/>
    </th>
    <td align="left">
        <!--form:errors path="afterBankcity" cssClass="fieldError"/-->
        <form:input path="afterBankcity" id="afterBankcity" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiMemberLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiMemberLogForm'));
</script>

<v:javascript formName="jmiMemberLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
