<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiUmbpayLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiUmbpayLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiUmbpayLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiUmbpayLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiUmbpayLog.*">
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

<form:form commandName="jfiUmbpayLog" method="post" action="editJfiUmbpayLog.html" onsubmit="return validateJfiUmbpayLog(this)" id="jfiUmbpayLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiUmbpayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.signType"/>
    </th>
    <td align="left">
        <!--form:errors path="signType" cssClass="fieldError"/-->
        <form:input path="signType" id="signType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.payType"/>
    </th>
    <td align="left">
        <!--form:errors path="payType" cssClass="fieldError"/-->
        <form:input path="payType" id="payType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.payorderid"/>
    </th>
    <td align="left">
        <!--form:errors path="payorderid" cssClass="fieldError"/-->
        <form:input path="payorderid" id="payorderid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.merchantid"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantid" cssClass="fieldError"/-->
        <form:input path="merchantid" id="merchantid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.merorderid"/>
    </th>
    <td align="left">
        <!--form:errors path="merorderid" cssClass="fieldError"/-->
        <form:input path="merorderid" id="merorderid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.amountsum"/>
    </th>
    <td align="left">
        <!--form:errors path="amountsum" cssClass="fieldError"/-->
        <form:input path="amountsum" id="amountsum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.currencytype"/>
    </th>
    <td align="left">
        <!--form:errors path="currencytype" cssClass="fieldError"/-->
        <form:input path="currencytype" id="currencytype" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.subject"/>
    </th>
    <td align="left">
        <!--form:errors path="subject" cssClass="fieldError"/-->
        <form:input path="subject" id="subject" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.returnState"/>
    </th>
    <td align="left">
        <!--form:errors path="returnState" cssClass="fieldError"/-->
        <form:input path="returnState" id="returnState" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.paybank"/>
    </th>
    <td align="left">
        <!--form:errors path="paybank" cssClass="fieldError"/-->
        <form:input path="paybank" id="paybank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.banksendtime"/>
    </th>
    <td align="left">
        <!--form:errors path="banksendtime" cssClass="fieldError"/-->
        <form:input path="banksendtime" id="banksendtime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.merrecvtime"/>
    </th>
    <td align="left">
        <!--form:errors path="merrecvtime" cssClass="fieldError"/-->
        <form:input path="merrecvtime" id="merrecvtime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.version"/>
    </th>
    <td align="left">
        <!--form:errors path="version" cssClass="fieldError"/-->
        <form:input path="version" id="version" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.merkey"/>
    </th>
    <td align="left">
        <!--form:errors path="merkey" cssClass="fieldError"/-->
        <form:input path="merkey" id="merkey" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.mac"/>
    </th>
    <td align="left">
        <!--form:errors path="mac" cssClass="fieldError"/-->
        <form:input path="mac" id="mac" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.ip"/>
    </th>
    <td align="left">
        <!--form:errors path="ip" cssClass="fieldError"/-->
        <form:input path="ip" id="ip" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiUmbpayLog.dataType"/>
    </th>
    <td align="left">
        <!--form:errors path="dataType" cssClass="fieldError"/-->
        <form:input path="dataType" id="dataType" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiUmbpayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiUmbpayLogForm'));
</script>

<v:javascript formName="jfiUmbpayLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
