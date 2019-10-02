<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiUsCreditCardLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiUsCreditCardLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiUsCreditCardLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiUsCreditCardLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiUsCreditCardLog.*">
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

<form:form commandName="jfiUsCreditCardLog" method="post" action="editJfiUsCreditCardLog.html" onsubmit="return validateJfiUsCreditCardLog(this)" id="jfiUsCreditCardLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiUsCreditCardLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="jucclId"/>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.jiType"/>
    </th>
    <td align="left">
        <!--form:errors path="jiType" cssClass="fieldError"/-->
        <form:input path="jiType" id="jiType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.password"/>
    </th>
    <td align="left">
        <!--form:errors path="password" cssClass="fieldError"/-->
        <form:input path="password" id="password" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.ccNumber"/>
    </th>
    <td align="left">
        <!--form:errors path="ccNumber" cssClass="fieldError"/-->
        <form:input path="ccNumber" id="ccNumber" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.ccExp"/>
    </th>
    <td align="left">
        <!--form:errors path="ccExp" cssClass="fieldError"/-->
        <form:input path="ccExp" id="ccExp" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.amount"/>
    </th>
    <td align="left">
        <!--form:errors path="amount" cssClass="fieldError"/-->
        <form:input path="amount" id="amount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.cvv"/>
    </th>
    <td align="left">
        <!--form:errors path="cvv" cssClass="fieldError"/-->
        <form:input path="cvv" id="cvv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.payment"/>
    </th>
    <td align="left">
        <!--form:errors path="payment" cssClass="fieldError"/-->
        <form:input path="payment" id="payment" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.firstName"/>
    </th>
    <td align="left">
        <!--form:errors path="firstName" cssClass="fieldError"/-->
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.lastName"/>
    </th>
    <td align="left">
        <!--form:errors path="lastName" cssClass="fieldError"/-->
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.address"/>
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.city"/>
    </th>
    <td align="left">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <form:input path="city" id="city" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.state"/>
    </th>
    <td align="left">
        <!--form:errors path="state" cssClass="fieldError"/-->
        <form:input path="state" id="state" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.zip"/>
    </th>
    <td align="left">
        <!--form:errors path="zip" cssClass="fieldError"/-->
        <form:input path="zip" id="zip" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.country"/>
    </th>
    <td align="left">
        <!--form:errors path="country" cssClass="fieldError"/-->
        <form:input path="country" id="country" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.phone"/>
    </th>
    <td align="left">
        <!--form:errors path="phone" cssClass="fieldError"/-->
        <form:input path="phone" id="phone" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.email"/>
    </th>
    <td align="left">
        <!--form:errors path="email" cssClass="fieldError"/-->
        <form:input path="email" id="email" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.response"/>
    </th>
    <td align="left">
        <!--form:errors path="response" cssClass="fieldError"/-->
        <form:input path="response" id="response" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.responseText"/>
    </th>
    <td align="left">
        <!--form:errors path="responseText" cssClass="fieldError"/-->
        <form:input path="responseText" id="responseText" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.authCode"/>
    </th>
    <td align="left">
        <!--form:errors path="authCode" cssClass="fieldError"/-->
        <form:input path="authCode" id="authCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.transactionId"/>
    </th>
    <td align="left">
        <!--form:errors path="transactionId" cssClass="fieldError"/-->
        <form:input path="transactionId" id="transactionId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.avsResponse"/>
    </th>
    <td align="left">
        <!--form:errors path="avsResponse" cssClass="fieldError"/-->
        <form:input path="avsResponse" id="avsResponse" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.cvvResponse"/>
    </th>
    <td align="left">
        <!--form:errors path="cvvResponse" cssClass="fieldError"/-->
        <form:input path="cvvResponse" id="cvvResponse" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.orderId"/>
    </th>
    <td align="left">
        <!--form:errors path="orderId" cssClass="fieldError"/-->
        <form:input path="orderId" id="orderId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.typeResponse"/>
    </th>
    <td align="left">
        <!--form:errors path="typeResponse" cssClass="fieldError"/-->
        <form:input path="typeResponse" id="typeResponse" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.responseCode"/>
    </th>
    <td align="left">
        <!--form:errors path="responseCode" cssClass="fieldError"/-->
        <form:input path="responseCode" id="responseCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUsCreditCardLog.responseStr"/>
    </th>
    <td align="left">
        <!--form:errors path="responseStr" cssClass="fieldError"/-->
        <form:input path="responseStr" id="responseStr" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiUsCreditCardLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiUsCreditCardLogForm'));
</script>

<v:javascript formName="jfiUsCreditCardLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
