<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiHiTrustLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiHiTrustLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiHiTrustLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiHiTrustLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiHiTrustLog.*">
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

<form:form commandName="jfiHiTrustLog" method="post" action="editJfiHiTrustLog.html" onsubmit="return validateJfiHiTrustLog(this)" id="jfiHiTrustLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiHiTrustLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.orderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <form:input path="orderNo" id="orderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.retCode"/>
    </th>
    <td align="left">
        <!--form:errors path="retCode" cssClass="fieldError"/-->
        <form:input path="retCode" id="retCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.currency"/>
    </th>
    <td align="left">
        <!--form:errors path="currency" cssClass="fieldError"/-->
        <form:input path="currency" id="currency" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.orderDate"/>
    </th>
    <td align="left">
        <!--form:errors path="orderDate" cssClass="fieldError"/-->
        <form:input path="orderDate" id="orderDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.orderStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="orderStatus" cssClass="fieldError"/-->
        <form:input path="orderStatus" id="orderStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.approveAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="approveAmount" cssClass="fieldError"/-->
        <form:input path="approveAmount" id="approveAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.authCode"/>
    </th>
    <td align="left">
        <!--form:errors path="authCode" cssClass="fieldError"/-->
        <form:input path="authCode" id="authCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.authRrn"/>
    </th>
    <td align="left">
        <!--form:errors path="authRrn" cssClass="fieldError"/-->
        <form:input path="authRrn" id="authRrn" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.captureAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="captureAmount" cssClass="fieldError"/-->
        <form:input path="captureAmount" id="captureAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.payBatchNum"/>
    </th>
    <td align="left">
        <!--form:errors path="payBatchNum" cssClass="fieldError"/-->
        <form:input path="payBatchNum" id="payBatchNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.captureDate"/>
    </th>
    <td align="left">
        <!--form:errors path="captureDate" cssClass="fieldError"/-->
        <form:input path="captureDate" id="captureDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.refundAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="refundAmount" cssClass="fieldError"/-->
        <form:input path="refundAmount" id="refundAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.refundBatch"/>
    </th>
    <td align="left">
        <!--form:errors path="refundBatch" cssClass="fieldError"/-->
        <form:input path="refundBatch" id="refundBatch" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.refundRrn"/>
    </th>
    <td align="left">
        <!--form:errors path="refundRrn" cssClass="fieldError"/-->
        <form:input path="refundRrn" id="refundRrn" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.refundCode"/>
    </th>
    <td align="left">
        <!--form:errors path="refundCode" cssClass="fieldError"/-->
        <form:input path="refundCode" id="refundCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.refundDate"/>
    </th>
    <td align="left">
        <!--form:errors path="refundDate" cssClass="fieldError"/-->
        <form:input path="refundDate" id="refundDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.acquirer"/>
    </th>
    <td align="left">
        <!--form:errors path="acquirer" cssClass="fieldError"/-->
        <form:input path="acquirer" id="acquirer" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.ttype"/>
    </th>
    <td align="left">
        <!--form:errors path="ttype" cssClass="fieldError"/-->
        <form:input path="ttype" id="ttype" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.eci"/>
    </th>
    <td align="left">
        <!--form:errors path="eci" cssClass="fieldError"/-->
        <form:input path="eci" id="eci" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.referer"/>
    </th>
    <td align="left">
        <!--form:errors path="referer" cssClass="fieldError"/-->
        <form:input path="referer" id="referer" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.returnMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="returnMsg" cssClass="fieldError"/-->
        <form:input path="returnMsg" id="returnMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiHiTrustLog.pageType"/>
    </th>
    <td align="left">
        <!--form:errors path="pageType" cssClass="fieldError"/-->
        <form:input path="pageType" id="pageType" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiHiTrustLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiHiTrustLogForm'));
</script>

<v:javascript formName="jfiHiTrustLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
