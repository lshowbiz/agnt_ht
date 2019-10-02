<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiCreditCardLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiCreditCardLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiCreditCardLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiCreditCardLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiCreditCardLog.*">
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

<form:form commandName="jfiCreditCardLog" method="post" action="editJfiCreditCardLog.html" onsubmit="return validateJfiCreditCardLog(this)" id="jfiCreditCardLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiCreditCardLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.firstName"/>
    </th>
    <td align="left">
        <!--form:errors path="firstName" cssClass="fieldError"/-->
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.lastName"/>
    </th>
    <td align="left">
        <!--form:errors path="lastName" cssClass="fieldError"/-->
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.cardNumber"/>
    </th>
    <td align="left">
        <!--form:errors path="cardNumber" cssClass="fieldError"/-->
        <form:input path="cardNumber" id="cardNumber" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.expireDate"/>
    </th>
    <td align="left">
        <!--form:errors path="expireDate" cssClass="fieldError"/-->
        <form:input path="expireDate" id="expireDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.payAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="payAmount" cssClass="fieldError"/-->
        <form:input path="payAmount" id="payAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.payTime"/>
    </th>
    <td align="left">
        <!--form:errors path="payTime" cssClass="fieldError"/-->
        <form:input path="payTime" id="payTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.payResult"/>
    </th>
    <td align="left">
        <!--form:errors path="payResult" cssClass="fieldError"/-->
        <form:input path="payResult" id="payResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.memberOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberOrderNo" cssClass="fieldError"/-->
        <form:input path="memberOrderNo" id="memberOrderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.payCause"/>
    </th>
    <td align="left">
        <!--form:errors path="payCause" cssClass="fieldError"/-->
        <form:input path="payCause" id="payCause" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiCreditCardLog.returnData"/>
    </th>
    <td align="left">
        <!--form:errors path="returnData" cssClass="fieldError"/-->
        <form:input path="returnData" id="returnData" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiCreditCardLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiCreditCardLogForm'));
</script>

<v:javascript formName="jfiCreditCardLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
