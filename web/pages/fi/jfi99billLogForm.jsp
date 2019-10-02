<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billLogDetail.heading"/></content>

<spring:bind path="jfi99billLog.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jfi99billLog" method="post" action="editJfi99billLog.html" onsubmit="return validateJfi99billLog(this)" id="jfi99billLogForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <wecs:label  key="jfi99billLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.merchantAcctId"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantAcctId" cssClass="fieldError"/-->
        <form:input path="merchantAcctId" id="merchantAcctId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.version"/>
    </th>
    <td align="left">
        <!--form:errors path="version" cssClass="fieldError"/-->
        <form:input path="version" id="version" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.billLanguage"/>
    </th>
    <td align="left">
        <!--form:errors path="billLanguage" cssClass="fieldError"/-->
        <form:input path="billLanguage" id="billLanguage" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.signType"/>
    </th>
    <td align="left">
        <!--form:errors path="signType" cssClass="fieldError"/-->
        <form:input path="signType" id="signType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.payType"/>
    </th>
    <td align="left">
        <!--form:errors path="payType" cssClass="fieldError"/-->
        <form:input path="payType" id="payType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.bankId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankId" cssClass="fieldError"/-->
        <form:input path="bankId" id="bankId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.orderId"/>
    </th>
    <td align="left">
        <!--form:errors path="orderId" cssClass="fieldError"/-->
        <form:input path="orderId" id="orderId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.orderTime"/>
    </th>
    <td align="left">
        <!--form:errors path="orderTime" cssClass="fieldError"/-->
        <form:input path="orderTime" id="orderTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.orderAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="orderAmount" cssClass="fieldError"/-->
        <form:input path="orderAmount" id="orderAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.dealId"/>
    </th>
    <td align="left">
        <!--form:errors path="dealId" cssClass="fieldError"/-->
        <form:input path="dealId" id="dealId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.bankDealId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankDealId" cssClass="fieldError"/-->
        <form:input path="bankDealId" id="bankDealId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.dealTime"/>
    </th>
    <td align="left">
        <!--form:errors path="dealTime" cssClass="fieldError"/-->
        <form:input path="dealTime" id="dealTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.payAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="payAmount" cssClass="fieldError"/-->
        <form:input path="payAmount" id="payAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.fee"/>
    </th>
    <td align="left">
        <!--form:errors path="fee" cssClass="fieldError"/-->
        <form:input path="fee" id="fee" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.ext1"/>
    </th>
    <td align="left">
        <!--form:errors path="ext1" cssClass="fieldError"/-->
        <form:input path="ext1" id="ext1" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.ext2"/>
    </th>
    <td align="left">
        <!--form:errors path="ext2" cssClass="fieldError"/-->
        <form:input path="ext2" id="ext2" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.payResult"/>
    </th>
    <td align="left">
        <!--form:errors path="payResult" cssClass="fieldError"/-->
        <form:input path="payResult" id="payResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.errCode"/>
    </th>
    <td align="left">
        <!--form:errors path="errCode" cssClass="fieldError"/-->
        <form:input path="errCode" id="errCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.signMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="signMsg" cssClass="fieldError"/-->
        <form:input path="signMsg" id="signMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.verifySignResult"/>
    </th>
    <td align="left">
        <!--form:errors path="verifySignResult" cssClass="fieldError"/-->
        <form:input path="verifySignResult" id="verifySignResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jfi99billLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>
	
	<tr><th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
		<wecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</wecs:power>
		<c:if test="${not empty jfi99billLog.logId}">
		<wecs:power powerCode="deleteFi99billLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'Jfi99billLog')" value="<jecs:locale key="button.delete"/>" />
		</wecs:power>
		</c:if>
		<input type="button" class="button" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>

</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfi99billLogForm'));
</script>

<v:javascript formName="jfi99billLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
