<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiInvoiceDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiInvoiceDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiInvoice">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiInvoice')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiInvoice.*">
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

<form:form commandName="jfiInvoice" method="post" action="editJfiInvoice.html" onsubmit="return validateJfiInvoice(this)" id="jfiInvoiceForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiInvoice')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jfiInvoice.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.invoiceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="invoiceMoney" cssClass="fieldError"/-->
        <form:input path="invoiceMoney" id="invoiceMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.usedInvoice"/>
    </th>
    <td align="left">
        <!--form:errors path="usedInvoice" cssClass="fieldError"/-->
        <form:input path="usedInvoice" id="usedInvoice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.unusedInvoice"/>
    </th>
    <td align="left">
        <!--form:errors path="unusedInvoice" cssClass="fieldError"/-->
        <form:input path="unusedInvoice" id="unusedInvoice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.company"/>
    </th>
    <td align="left">
        <!--form:errors path="company" cssClass="fieldError"/-->
        <form:input path="company" id="company" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.useStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="useStatus" cssClass="fieldError"/-->
        <form:input path="useStatus" id="useStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.checkTime"/>
    </th>
    <td align="left">
        <!--form:errors path="checkTime" cssClass="fieldError"/-->
        <form:input path="checkTime" id="checkTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.checkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="checkNo" cssClass="fieldError"/-->
        <form:input path="checkNo" id="checkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.invoiceNo"/>
    </th>
    <td align="left">
        <!--form:errors path="invoiceNo" cssClass="fieldError"/-->
        <form:input path="invoiceNo" id="invoiceNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiInvoice.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiInvoice')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiInvoiceForm'));
</script>

<v:javascript formName="jfiInvoice" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
