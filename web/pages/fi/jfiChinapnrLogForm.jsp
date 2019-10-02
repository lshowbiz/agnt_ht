<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiChinapnrLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiChinapnrLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiChinapnrLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiChinapnrLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiChinapnrLog.*">
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

<form:form commandName="jfiChinapnrLog" method="post" action="editJfiChinapnrLog.html" onsubmit="return validateJfiChinapnrLog(this)" id="jfiChinapnrLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiChinapnrLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.signType"/>
    </th>
    <td align="left">
        <!--form:errors path="signType" cssClass="fieldError"/-->
        <form:input path="signType" id="signType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.payType"/>
    </th>
    <td align="left">
        <!--form:errors path="payType" cssClass="fieldError"/-->
        <form:input path="payType" id="payType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.payorderid"/>
    </th>
    <td align="left">
        <!--form:errors path="payorderid" cssClass="fieldError"/-->
        <form:input path="payorderid" id="payorderid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.merchantid"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantid" cssClass="fieldError"/-->
        <form:input path="merchantid" id="merchantid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.merorderid"/>
    </th>
    <td align="left">
        <!--form:errors path="merorderid" cssClass="fieldError"/-->
        <form:input path="merorderid" id="merorderid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.amountsum"/>
    </th>
    <td align="left">
        <!--form:errors path="amountsum" cssClass="fieldError"/-->
        <form:input path="amountsum" id="amountsum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.currencytype"/>
    </th>
    <td align="left">
        <!--form:errors path="currencytype" cssClass="fieldError"/-->
        <form:input path="currencytype" id="currencytype" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.subject"/>
    </th>
    <td align="left">
        <!--form:errors path="subject" cssClass="fieldError"/-->
        <form:input path="subject" id="subject" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.returnState"/>
    </th>
    <td align="left">
        <!--form:errors path="returnState" cssClass="fieldError"/-->
        <form:input path="returnState" id="returnState" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.paybank"/>
    </th>
    <td align="left">
        <!--form:errors path="paybank" cssClass="fieldError"/-->
        <form:input path="paybank" id="paybank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.banksendtime"/>
    </th>
    <td align="left">
        <!--form:errors path="banksendtime" cssClass="fieldError"/-->
        <form:input path="banksendtime" id="banksendtime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.merrecvtime"/>
    </th>
    <td align="left">
        <!--form:errors path="merrecvtime" cssClass="fieldError"/-->
        <form:input path="merrecvtime" id="merrecvtime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.version"/>
    </th>
    <td align="left">
        <!--form:errors path="version" cssClass="fieldError"/-->
        <form:input path="version" id="version" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.merkey"/>
    </th>
    <td align="left">
        <!--form:errors path="merkey" cssClass="fieldError"/-->
        <form:input path="merkey" id="merkey" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.mac"/>
    </th>
    <td align="left">
        <!--form:errors path="mac" cssClass="fieldError"/-->
        <form:input path="mac" id="mac" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.returnMsg"/>
    </th>
    <td align="left">
        <!--form:errors path="returnMsg" cssClass="fieldError"/-->
        <form:input path="returnMsg" id="returnMsg" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.inc"/>
    </th>
    <td align="left">
        <!--form:errors path="inc" cssClass="fieldError"/-->
        <form:input path="inc" id="inc" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.ip"/>
    </th>
    <td align="left">
        <!--form:errors path="ip" cssClass="fieldError"/-->
        <form:input path="ip" id="ip" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiChinapnrLog.dataType"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiChinapnrLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiChinapnrLogForm'));
</script>

<v:javascript formName="jfiChinapnrLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
