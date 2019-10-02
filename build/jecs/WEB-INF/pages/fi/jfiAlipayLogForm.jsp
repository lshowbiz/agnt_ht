<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiAlipayLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiAlipayLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiAlipayLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiAlipayLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiAlipayLog.*">
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

<form:form commandName="jfiAlipayLog" method="post" action="editJfiAlipayLog.html" onsubmit="return validateJfiAlipayLog(this)" id="jfiAlipayLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiAlipayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.isSuccess"/>
    </th>
    <td align="left">
        <!--form:errors path="isSuccess" cssClass="fieldError"/-->
        <form:input path="isSuccess" id="isSuccess" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.sign"/>
    </th>
    <td align="left">
        <!--form:errors path="sign" cssClass="fieldError"/-->
        <form:input path="sign" id="sign" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.signType"/>
    </th>
    <td align="left">
        <!--form:errors path="signType" cssClass="fieldError"/-->
        <form:input path="signType" id="signType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.exterface"/>
    </th>
    <td align="left">
        <!--form:errors path="exterface" cssClass="fieldError"/-->
        <form:input path="exterface" id="exterface" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.notifyTime"/>
    </th>
    <td align="left">
        <!--form:errors path="notifyTime" cssClass="fieldError"/-->
        <form:input path="notifyTime" id="notifyTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.notifyId"/>
    </th>
    <td align="left">
        <!--form:errors path="notifyId" cssClass="fieldError"/-->
        <form:input path="notifyId" id="notifyId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.notifyType"/>
    </th>
    <td align="left">
        <!--form:errors path="notifyType" cssClass="fieldError"/-->
        <form:input path="notifyType" id="notifyType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.tradeNo"/>
    </th>
    <td align="left">
        <!--form:errors path="tradeNo" cssClass="fieldError"/-->
        <form:input path="tradeNo" id="tradeNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.paymentType"/>
    </th>
    <td align="left">
        <!--form:errors path="paymentType" cssClass="fieldError"/-->
        <form:input path="paymentType" id="paymentType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.outTradeNo"/>
    </th>
    <td align="left">
        <!--form:errors path="outTradeNo" cssClass="fieldError"/-->
        <form:input path="outTradeNo" id="outTradeNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.body"/>
    </th>
    <td align="left">
        <!--form:errors path="body" cssClass="fieldError"/-->
        <form:input path="body" id="body" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.subject"/>
    </th>
    <td align="left">
        <!--form:errors path="subject" cssClass="fieldError"/-->
        <form:input path="subject" id="subject" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.totalFee"/>
    </th>
    <td align="left">
        <!--form:errors path="totalFee" cssClass="fieldError"/-->
        <form:input path="totalFee" id="totalFee" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.buyerEmail"/>
    </th>
    <td align="left">
        <!--form:errors path="buyerEmail" cssClass="fieldError"/-->
        <form:input path="buyerEmail" id="buyerEmail" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.buyerId"/>
    </th>
    <td align="left">
        <!--form:errors path="buyerId" cssClass="fieldError"/-->
        <form:input path="buyerId" id="buyerId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.sellerEmail"/>
    </th>
    <td align="left">
        <!--form:errors path="sellerEmail" cssClass="fieldError"/-->
        <form:input path="sellerEmail" id="sellerEmail" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.sellerId"/>
    </th>
    <td align="left">
        <!--form:errors path="sellerId" cssClass="fieldError"/-->
        <form:input path="sellerId" id="sellerId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.tradeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="tradeStatus" cssClass="fieldError"/-->
        <form:input path="tradeStatus" id="tradeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.extraCommonParam"/>
    </th>
    <td align="left">
        <!--form:errors path="extraCommonParam" cssClass="fieldError"/-->
        <form:input path="extraCommonParam" id="extraCommonParam" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.bankSeqNo"/>
    </th>
    <td align="left">
        <!--form:errors path="bankSeqNo" cssClass="fieldError"/-->
        <form:input path="bankSeqNo" id="bankSeqNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.referer"/>
    </th>
    <td align="left">
        <!--form:errors path="referer" cssClass="fieldError"/-->
        <form:input path="referer" id="referer" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.returnMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="returnMsg" cssClass="fieldError"/-->
        <form:input path="returnMsg" id="returnMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiAlipayLog.createTime"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiAlipayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiAlipayLogForm'));
</script>

<v:javascript formName="jfiAlipayLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
