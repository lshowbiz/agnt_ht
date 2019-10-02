<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billmsLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billmsLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfi99billmsLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'Jfi99billmsLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfi99billmsLog.*">
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

<form:form commandName="jfi99billmsLog" method="post" action="editJfi99billmsLog.html" onsubmit="return validateJfi99billmsLog(this)" id="jfi99billmsLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Jfi99billmsLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.version"/>
    </th>
    <td align="left">
        <!--form:errors path="version" cssClass="fieldError"/-->
        <form:input path="version" id="version" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.billLanguage"/>
    </th>
    <td align="left">
        <!--form:errors path="billLanguage" cssClass="fieldError"/-->
        <form:input path="billLanguage" id="billLanguage" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.signType"/>
    </th>
    <td align="left">
        <!--form:errors path="signType" cssClass="fieldError"/-->
        <form:input path="signType" id="signType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.payType"/>
    </th>
    <td align="left">
        <!--form:errors path="payType" cssClass="fieldError"/-->
        <form:input path="payType" id="payType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.bankId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankId" cssClass="fieldError"/-->
        <form:input path="bankId" id="bankId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.pid"/>
    </th>
    <td align="left">
        <!--form:errors path="pid" cssClass="fieldError"/-->
        <form:input path="pid" id="pid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.orderId"/>
    </th>
    <td align="left">
        <!--form:errors path="orderId" cssClass="fieldError"/-->
        <form:input path="orderId" id="orderId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.orderTime"/>
    </th>
    <td align="left">
        <!--form:errors path="orderTime" cssClass="fieldError"/-->
        <form:input path="orderTime" id="orderTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.orderAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="orderAmount" cssClass="fieldError"/-->
        <form:input path="orderAmount" id="orderAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.dealId"/>
    </th>
    <td align="left">
        <!--form:errors path="dealId" cssClass="fieldError"/-->
        <form:input path="dealId" id="dealId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.bankDealId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankDealId" cssClass="fieldError"/-->
        <form:input path="bankDealId" id="bankDealId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.dealTime"/>
    </th>
    <td align="left">
        <!--form:errors path="dealTime" cssClass="fieldError"/-->
        <form:input path="dealTime" id="dealTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.payAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="payAmount" cssClass="fieldError"/-->
        <form:input path="payAmount" id="payAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.fee"/>
    </th>
    <td align="left">
        <!--form:errors path="fee" cssClass="fieldError"/-->
        <form:input path="fee" id="fee" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.payeeContactType"/>
    </th>
    <td align="left">
        <!--form:errors path="payeeContactType" cssClass="fieldError"/-->
        <form:input path="payeeContactType" id="payeeContactType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.payeeContact"/>
    </th>
    <td align="left">
        <!--form:errors path="payeeContact" cssClass="fieldError"/-->
        <form:input path="payeeContact" id="payeeContact" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.payeeAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="payeeAmount" cssClass="fieldError"/-->
        <form:input path="payeeAmount" id="payeeAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.ext1"/>
    </th>
    <td align="left">
        <!--form:errors path="ext1" cssClass="fieldError"/-->
        <form:input path="ext1" id="ext1" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.ext2"/>
    </th>
    <td align="left">
        <!--form:errors path="ext2" cssClass="fieldError"/-->
        <form:input path="ext2" id="ext2" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.payResult"/>
    </th>
    <td align="left">
        <!--form:errors path="payResult" cssClass="fieldError"/-->
        <form:input path="payResult" id="payResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.sharingResult"/>
    </th>
    <td align="left">
        <!--form:errors path="sharingResult" cssClass="fieldError"/-->
        <form:input path="sharingResult" id="sharingResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.errCode"/>
    </th>
    <td align="left">
        <!--form:errors path="errCode" cssClass="fieldError"/-->
        <form:input path="errCode" id="errCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.signMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="signMsg" cssClass="fieldError"/-->
        <form:input path="signMsg" id="signMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.verifySignResult"/>
    </th>
    <td align="left">
        <!--form:errors path="verifySignResult" cssClass="fieldError"/-->
        <form:input path="verifySignResult" id="verifySignResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.referer"/>
    </th>
    <td align="left">
        <!--form:errors path="referer" cssClass="fieldError"/-->
        <form:input path="referer" id="referer" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.returnMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="returnMsg" cssClass="fieldError"/-->
        <form:input path="returnMsg" id="returnMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billmsLog.ip"/>
    </th>
    <td align="left">
        <!--form:errors path="ip" cssClass="fieldError"/-->
        <form:input path="ip" id="ip" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Jfi99billmsLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfi99billmsLogForm'));
</script>

<v:javascript formName="jfi99billmsLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
