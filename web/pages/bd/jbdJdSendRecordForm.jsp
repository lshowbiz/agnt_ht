<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdJdSendRecordDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdJdSendRecordDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdJdSendRecord">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdJdSendRecord')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdJdSendRecord.*">
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

<form:form commandName="jbdJdSendRecord" method="post" action="editJbdJdSendRecord.html" onsubmit="return validateJbdJdSendRecord(this)" id="jbdJdSendRecordForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdJdSendRecord')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.petName"/>
    </th>
    <td align="left">
        <!--form:errors path="petName" cssClass="fieldError"/-->
        <form:input path="petName" id="petName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.userType"/>
    </th>
    <td align="left">
        <!--form:errors path="userType" cssClass="fieldError"/-->
        <form:input path="userType" id="userType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.memberLevel"/>
    </th>
    <td align="left">
        <!--form:errors path="memberLevel" cssClass="fieldError"/-->
        <form:input path="memberLevel" id="memberLevel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.ykRefMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="ykRefMoney" cssClass="fieldError"/-->
        <form:input path="ykRefMoney" id="ykRefMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.sendMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="sendMoney" cssClass="fieldError"/-->
        <form:input path="sendMoney" id="sendMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.freezeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="freezeStatus" cssClass="fieldError"/-->
        <form:input path="freezeStatus" id="freezeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdJdSendRecord.sendDate"/>
    </th>
    <td align="left">
        <!--form:errors path="sendDate" cssClass="fieldError"/-->
        <form:input path="sendDate" id="sendDate" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdJdSendRecord')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdJdSendRecordForm'));
</script>

<v:javascript formName="jbdJdSendRecord" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
