<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookJournalDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiBankbookJournalDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteFiBankbookJournal">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiBankbookJournal')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="fiBankbookJournal.*">
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

<form:form commandName="fiBankbookJournal" method="post" action="editFiBankbookJournal.html" onsubmit="return validateFiBankbookJournal(this)" id="fiBankbookJournalForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiBankbookJournal')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="journalId"/>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.tempId"/>
    </th>
    <td align="left">
        <!--form:errors path="tempId" cssClass="fieldError"/-->
        <form:input path="tempId" id="tempId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.serial"/>
    </th>
    <td align="left">
        <!--form:errors path="serial" cssClass="fieldError"/-->
        <form:input path="serial" id="serial" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.dealType"/>
    </th>
    <td align="left">
        <!--form:errors path="dealType" cssClass="fieldError"/-->
        <form:input path="dealType" id="dealType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.dealDate"/>
    </th>
    <td align="left">
        <!--form:errors path="dealDate" cssClass="fieldError"/-->
        <form:input path="dealDate" id="dealDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.money"/>
    </th>
    <td align="left">
        <!--form:errors path="money" cssClass="fieldError"/-->
        <form:input path="money" id="money" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.notes"/>
    </th>
    <td align="left">
        <!--form:errors path="notes" cssClass="fieldError"/-->
        <form:input path="notes" id="notes" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.balance"/>
    </th>
    <td align="left">
        <!--form:errors path="balance" cssClass="fieldError"/-->
        <form:input path="balance" id="balance" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.createrCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createrCode" cssClass="fieldError"/-->
        <form:input path="createrCode" id="createrCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.createrName"/>
    </th>
    <td align="left">
        <!--form:errors path="createrName" cssClass="fieldError"/-->
        <form:input path="createrName" id="createrName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.moneyType"/>
    </th>
    <td align="left">
        <!--form:errors path="moneyType" cssClass="fieldError"/-->
        <form:input path="moneyType" id="moneyType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.uniqueCode"/>
    </th>
    <td align="left">
        <!--form:errors path="uniqueCode" cssClass="fieldError"/-->
        <form:input path="uniqueCode" id="uniqueCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.bankbookType"/>
    </th>
    <td align="left">
        <!--form:errors path="bankbookType" cssClass="fieldError"/-->
        <form:input path="productPointType" id="bankbookType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.lastJournalId"/>
    </th>
    <td align="left">
        <!--form:errors path="lastJournalId" cssClass="fieldError"/-->
        <form:input path="lastJournalId" id="lastJournalId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.lastMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="lastMoney" cssClass="fieldError"/-->
        <form:input path="lastMoney" id="lastMoney" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiBankbookJournal')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiBankbookJournalForm'));
</script>

<v:javascript formName="fiBankbookJournal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
