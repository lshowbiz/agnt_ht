<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendRecordHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSendRecordHistDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSendRecordHist">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSendRecordHist')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSendRecordHist.*">
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

<form:form commandName="jbdSendRecordHist" method="post" action="editJbdSendRecordHist.html" onsubmit="return validateJbdSendRecordHist(this)" id="jbdSendRecordHistForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSendRecordHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.recommendNo"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNo" cssClass="fieldError"/-->
        <form:input path="recommendNo" id="recommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.linkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNo" cssClass="fieldError"/-->
        <form:input path="linkNo" id="linkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.name"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        <form:input path="name" id="name" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.patName"/>
    </th>
    <td align="left">
        <!--form:errors path="patName" cssClass="fieldError"/-->
        <form:input path="patName" id="patName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.bank"/>
    </th>
    <td align="left">
        <!--form:errors path="bank" cssClass="fieldError"/-->
        <form:input path="bank" id="bank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.bankaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="bankaddress" cssClass="fieldError"/-->
        <form:input path="bankaddress" id="bankaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.bankbook"/>
    </th>
    <td align="left">
        <!--form:errors path="bankbook" cssClass="fieldError"/-->
        <form:input path="bankbook" id="bankbook" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.bankcard"/>
    </th>
    <td align="left">
        <!--form:errors path="bankcard" cssClass="fieldError"/-->
        <form:input path="bankcard" id="bankcard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.exitDate"/>
    </th>
    <td align="left">
        <!--form:errors path="exitDate" cssClass="fieldError"/-->
        <form:input path="exitDate" id="exitDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.sendLateCause"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
        <form:input path="sendLateCause" id="sendLateCause" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.sendLateRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateRemark" cssClass="fieldError"/-->
        <form:input path="sendLateRemark" id="sendLateRemark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.remittanceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceMoney" cssClass="fieldError"/-->
        <form:input path="remittanceMoney" id="remittanceMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.remittanceBNum"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceBNum" cssClass="fieldError"/-->
        <form:input path="remittanceBNum" id="remittanceBNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.operaterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterCode" cssClass="fieldError"/-->
        <form:input path="operaterCode" id="operaterCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.operaterTime"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterTime" cssClass="fieldError"/-->
        <form:input path="operaterTime" id="operaterTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.registerStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="registerStatus" cssClass="fieldError"/-->
        <form:input path="registerStatus" id="registerStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.reissueStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="reissueStatus" cssClass="fieldError"/-->
        <form:input path="reissueStatus" id="reissueStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.supplyTime"/>
    </th>
    <td align="left">
        <!--form:errors path="supplyTime" cssClass="fieldError"/-->
        <form:input path="supplyTime" id="supplyTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.sendTrace"/>
    </th>
    <td align="left">
        <!--form:errors path="sendTrace" cssClass="fieldError"/-->
        <form:input path="sendTrace" id="sendTrace" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.sendRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendRemark" cssClass="fieldError"/-->
        <form:input path="sendRemark" id="sendRemark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.sendMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="sendMoney" cssClass="fieldError"/-->
        <form:input path="sendMoney" id="sendMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordHist.totalMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="totalMoney" cssClass="fieldError"/-->
        <form:input path="totalMoney" id="totalMoney" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSendRecordHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSendRecordHistForm'));
</script>

<v:javascript formName="jbdSendRecordHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
