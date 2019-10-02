<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBankbookJournalDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiBankbookJournalDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiBankbookJournal">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiBankbookJournal<jecs:localefmt:message key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiBankbookJournal.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
 <jecs:locale   alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jfiBankbookJournal" method="post" action="editJfiBankbookJournal.html" onsubmit="return validateJfiBankbookJournal(this)" id="jfiBankbookJournalForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirm<jecs:localeankbookJournal')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="journalId"/>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.serial"/>
    </th>
    <td align="left">
        <!--form:errors path="serial" cssClass="fieldError"/-->
        <form:input path="serial" id="serial" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.dealType"/>
    </th>
    <td align="left">
        <!--form:errors path="dealType" cssClass="fieldError"/-->
        <form:input path="dealType" id="dealType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.dealDate"/>
    </th>
    <td align="left">
        <!--form:errors path="dealDate" cssClass="fieldError"/-->
        <form:input path="dealDate" id="dealDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.money"/>
    </th>
    <td align="left">
        <!--form:errors path="money" cssClass="fieldError"/-->
        <form:input path="money" id="money" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.notes"/>
    </th>
    <td align="left">
        <!--form:errors path="notes" cssClass="fieldError"/-->
        <form:input path="notes" id="notes" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.balance"/>
    </th>
    <td align="left">
        <!--form:errors path="balance" cssClass="fieldError"/-->
        <form:input path="balance" id="balance" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.createrCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createrCode" cssClass="fieldError"/-->
        <form:input path="createrCode" id="createrCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.createrName"/>
    </th>
    <td align="left">
        <!--form:errors path="createrName" cssClass="fieldError"/-->
        <form:input path="createrName" id="createrName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.moneyType"/>
    </th>
    <td align="left">
        <!--form:errors path="moneyType" cssClass="fieldError"/-->
        <form:input path="moneyType" id="moneyType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBankbookJournal.uniqueCode"/>
    </th>
    <td align="left">
        <!--form:errors path="uniqueCode" cssClass="fieldError"/-->
        <form:input path="uniqueCode" id="uniqueCode" cssClass="text medium"/>
    </td></tr>

</table>
<!--/<jecs:locale

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:<jecs:locale"button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirm<jecs:localeankbookJournal')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiBankbookJournalForm'));
</script>

<v:javascript formName="jfiBankbookJournal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
