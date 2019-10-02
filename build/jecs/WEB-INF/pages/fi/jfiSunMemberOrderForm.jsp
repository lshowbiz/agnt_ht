<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunMemberOrderDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiSunMemberOrder">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiSunMemberOrder')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiSunMemberOrder.*">
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

<form:form commandName="jfiSunMemberOrder" method="post" action="editJfiSunMemberOrder.html" onsubmit="return validateJfiSunMemberOrder(this)" id="jfiSunMemberOrderForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunMemberOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="moId"/>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.countryCode"/>
    </th>
    <td align="left">
        <!--form:errors path="countryCode" cssClass="fieldError"/-->
        <form:input path="countryCode" id="countryCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberOrderNo" cssClass="fieldError"/-->
        <form:input path="memberOrderNo" id="memberOrderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.orderType"/>
    </th>
    <td align="left">
        <!--form:errors path="orderType" cssClass="fieldError"/-->
        <form:input path="orderType" id="orderType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.agentNo"/>
    </th>
    <td align="left">
        <!--form:errors path="agentNo" cssClass="fieldError"/-->
        <form:input path="agentNo" id="agentNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.agentName"/>
    </th>
    <td align="left">
        <!--form:errors path="agentName" cssClass="fieldError"/-->
        <form:input path="agentName" id="agentName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.userSpCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userSpCode" cssClass="fieldError"/-->
        <form:input path="userSpCode" id="userSpCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.amount"/>
    </th>
    <td align="left">
        <!--form:errors path="amount" cssClass="fieldError"/-->
        <form:input path="amount" id="amount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.pvAmt"/>
    </th>
    <td align="left">
        <!--form:errors path="pvAmt" cssClass="fieldError"/-->
        <form:input path="pvAmt" id="pvAmt" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.consumerAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="consumerAmount" cssClass="fieldError"/-->
        <form:input path="consumerAmount" id="consumerAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.payMode"/>
    </th>
    <td align="left">
        <!--form:errors path="payMode" cssClass="fieldError"/-->
        <form:input path="payMode" id="payMode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.orderUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="orderUserCode" cssClass="fieldError"/-->
        <form:input path="orderUserCode" id="orderUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.orderTime"/>
    </th>
    <td align="left">
        <!--form:errors path="orderTime" cssClass="fieldError"/-->
        <form:input path="orderTime" id="orderTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.checkTime"/>
    </th>
    <td align="left">
        <!--form:errors path="checkTime" cssClass="fieldError"/-->
        <form:input path="checkTime" id="checkTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.checkUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="checkUserCode" cssClass="fieldError"/-->
        <form:input path="checkUserCode" id="checkUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.checkDate"/>
    </th>
    <td align="left">
        <!--form:errors path="checkDate" cssClass="fieldError"/-->
        <form:input path="checkDate" id="checkDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.pickup"/>
    </th>
    <td align="left">
        <!--form:errors path="pickup" cssClass="fieldError"/-->
        <form:input path="pickup" id="pickup" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.submitTime"/>
    </th>
    <td align="left">
        <!--form:errors path="submitTime" cssClass="fieldError"/-->
        <form:input path="submitTime" id="submitTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.submitUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="submitUserCode" cssClass="fieldError"/-->
        <form:input path="submitUserCode" id="submitUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.submitStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="submitStatus" cssClass="fieldError"/-->
        <form:input path="submitStatus" id="submitStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.firstName"/>
    </th>
    <td align="left">
        <!--form:errors path="firstName" cssClass="fieldError"/-->
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.lastName"/>
    </th>
    <td align="left">
        <!--form:errors path="lastName" cssClass="fieldError"/-->
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.province"/>
    </th>
    <td align="left">
        <!--form:errors path="province" cssClass="fieldError"/-->
        <form:input path="province" id="province" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.city"/>
    </th>
    <td align="left">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <form:input path="city" id="city" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.address"/>
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.postalcode"/>
    </th>
    <td align="left">
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
        <form:input path="postalcode" id="postalcode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.phone"/>
    </th>
    <td align="left">
        <!--form:errors path="phone" cssClass="fieldError"/-->
        <form:input path="phone" id="phone" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.email"/>
    </th>
    <td align="left">
        <!--form:errors path="email" cssClass="fieldError"/-->
        <form:input path="email" id="email" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.mobiletele"/>
    </th>
    <td align="left">
        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
        <form:input path="mobiletele" id="mobiletele" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrder.district"/>
    </th>
    <td align="left">
        <!--form:errors path="district" cssClass="fieldError"/-->
        <form:input path="district" id="district" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunMemberOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiSunMemberOrderForm'));
</script>

<v:javascript formName="jfiSunMemberOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
