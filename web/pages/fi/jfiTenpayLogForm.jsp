<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiTenpayLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiTenpayLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiTenpayLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiTenpayLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiTenpayLog.*">
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

<form:form commandName="jfiTenpayLog" method="post" action="editJfiTenpayLog.html" onsubmit="return validateJfiTenpayLog(this)" id="jfiTenpayLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiTenpayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.cmdno"/>
    </th>
    <td align="left">
        <!--form:errors path="cmdno" cssClass="fieldError"/-->
        <form:input path="cmdno" id="cmdno" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.payResult"/>
    </th>
    <td align="left">
        <!--form:errors path="payResult" cssClass="fieldError"/-->
        <form:input path="payResult" id="payResult" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.payInfo"/>
    </th>
    <td align="left">
        <!--form:errors path="payInfo" cssClass="fieldError"/-->
        <form:input path="payInfo" id="payInfo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.tpDate"/>
    </th>
    <td align="left">
        <!--form:errors path="tpDate" cssClass="fieldError"/-->
        <form:input path="tpDate" id="tpDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.purchaserId"/>
    </th>
    <td align="left">
        <!--form:errors path="purchaserId" cssClass="fieldError"/-->
        <form:input path="purchaserId" id="purchaserId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.bargainorId"/>
    </th>
    <td align="left">
        <!--form:errors path="bargainorId" cssClass="fieldError"/-->
        <form:input path="bargainorId" id="bargainorId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.transactionId"/>
    </th>
    <td align="left">
        <!--form:errors path="transactionId" cssClass="fieldError"/-->
        <form:input path="transactionId" id="transactionId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.spBillno"/>
    </th>
    <td align="left">
        <!--form:errors path="spBillno" cssClass="fieldError"/-->
        <form:input path="spBillno" id="spBillno" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.totalFee"/>
    </th>
    <td align="left">
        <!--form:errors path="totalFee" cssClass="fieldError"/-->
        <form:input path="totalFee" id="totalFee" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.feeType"/>
    </th>
    <td align="left">
        <!--form:errors path="feeType" cssClass="fieldError"/-->
        <form:input path="feeType" id="feeType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.attach"/>
    </th>
    <td align="left">
        <!--form:errors path="attach" cssClass="fieldError"/-->
        <form:input path="attach" id="attach" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.sign"/>
    </th>
    <td align="left">
        <!--form:errors path="sign" cssClass="fieldError"/-->
        <form:input path="sign" id="sign" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.busType"/>
    </th>
    <td align="left">
        <!--form:errors path="busType" cssClass="fieldError"/-->
        <form:input path="busType" id="busType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.busArgs"/>
    </th>
    <td align="left">
        <!--form:errors path="busArgs" cssClass="fieldError"/-->
        <form:input path="busArgs" id="busArgs" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.version"/>
    </th>
    <td align="left">
        <!--form:errors path="version" cssClass="fieldError"/-->
        <form:input path="version" id="version" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.referer"/>
    </th>
    <td align="left">
        <!--form:errors path="referer" cssClass="fieldError"/-->
        <form:input path="referer" id="referer" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.returnMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="returnMsg" cssClass="fieldError"/-->
        <form:input path="returnMsg" id="returnMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiTenpayLog.createTime"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiTenpayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiTenpayLogForm'));
</script>

<v:javascript formName="jfiTenpayLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
