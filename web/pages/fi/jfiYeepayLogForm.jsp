<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiYeepayLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiYeepayLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiYeepayLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiYeepayLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiYeepayLog.*">
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

<form:form commandName="jfiYeepayLog" method="post" action="editJfiYeepayLog.html" onsubmit="return validateJfiYeepayLog(this)" id="jfiYeepayLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiYeepayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.merchantId"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantId" cssClass="fieldError"/-->
        <form:input path="merchantId" id="merchantId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.businessType"/>
    </th>
    <td align="left">
        <!--form:errors path="businessType" cssClass="fieldError"/-->
        <form:input path="businessType" id="businessType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.orderId"/>
    </th>
    <td align="left">
        <!--form:errors path="orderId" cssClass="fieldError"/-->
        <form:input path="orderId" id="orderId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.orderDate"/>
    </th>
    <td align="left">
        <!--form:errors path="orderDate" cssClass="fieldError"/-->
        <form:input path="orderDate" id="orderDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.orderAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="orderAmount" cssClass="fieldError"/-->
        <form:input path="orderAmount" id="orderAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.orderTime"/>
    </th>
    <td align="left">
        <!--form:errors path="orderTime" cssClass="fieldError"/-->
        <form:input path="orderTime" id="orderTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.detailId"/>
    </th>
    <td align="left">
        <!--form:errors path="detailId" cssClass="fieldError"/-->
        <form:input path="detailId" id="detailId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.detailTime"/>
    </th>
    <td align="left">
        <!--form:errors path="detailTime" cssClass="fieldError"/-->
        <form:input path="detailTime" id="detailTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.bankId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankId" cssClass="fieldError"/-->
        <form:input path="bankId" id="bankId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.bankDealId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankDealId" cssClass="fieldError"/-->
        <form:input path="bankDealId" id="bankDealId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.amount"/>
    </th>
    <td align="left">
        <!--form:errors path="amount" cssClass="fieldError"/-->
        <form:input path="amount" id="amount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.amtType"/>
    </th>
    <td align="left">
        <!--form:errors path="amtType" cssClass="fieldError"/-->
        <form:input path="amtType" id="amtType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.payResult"/>
    </th>
    <td align="left">
        <!--form:errors path="payResult" cssClass="fieldError"/-->
        <form:input path="payResult" id="payResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.errCode"/>
    </th>
    <td align="left">
        <!--form:errors path="errCode" cssClass="fieldError"/-->
        <form:input path="errCode" id="errCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.errMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="errMsg" cssClass="fieldError"/-->
        <form:input path="errMsg" id="errMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.signMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="signMsg" cssClass="fieldError"/-->
        <form:input path="signMsg" id="signMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.dataType"/>
    </th>
    <td align="left">
        <!--form:errors path="dataType" cssClass="fieldError"/-->
        <form:input path="dataType" id="dataType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.returnMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="returnMsg" cssClass="fieldError"/-->
        <form:input path="returnMsg" id="returnMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiYeepayLog.createTime"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiYeepayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiYeepayLogForm'));
</script>

<v:javascript formName="jfiYeepayLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
