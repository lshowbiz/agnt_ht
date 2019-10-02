<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinJournalDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinJournalDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteFiBcoinJournal">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiBcoinJournal')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="fiBcoinJournal.*">
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

<form:form commandName="fiBcoinJournal" method="post" action="editFiBcoinJournal.html" onsubmit="return validateFiBcoinJournal(this)" id="fiBcoinJournalForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiBcoinJournal')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="journalId"/>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.serial"/>
    </th>
    <td align="left">
        <!--form:errors path="serial" cssClass="fieldError"/-->
        <form:input path="serial" id="serial" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.dealType"/>
    </th>
    <td align="left">
        <!--form:errors path="dealType" cssClass="fieldError"/-->
        <form:input path="dealType" id="dealType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.dealDate"/>
    </th>
    <td align="left">
        <!--form:errors path="dealDate" cssClass="fieldError"/-->
        <form:input path="dealDate" id="dealDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.coin"/>
    </th>
    <td align="left">
        <!--form:errors path="coin" cssClass="fieldError"/-->
        <form:input path="coin" id="coin" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.notes"/>
    </th>
    <td align="left">
        <!--form:errors path="notes" cssClass="fieldError"/-->
        <form:input path="notes" id="notes" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.balance"/>
    </th>
    <td align="left">
        <!--form:errors path="balance" cssClass="fieldError"/-->
        <form:input path="balance" id="balance" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.createrCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createrCode" cssClass="fieldError"/-->
        <form:input path="createrCode" id="createrCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.createrName"/>
    </th>
    <td align="left">
        <!--form:errors path="createrName" cssClass="fieldError"/-->
        <form:input path="createrName" id="createrName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.moneyType"/>
    </th>
    <td align="left">
        <!--form:errors path="moneyType" cssClass="fieldError"/-->
        <form:input path="moneyType" id="moneyType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.uniqueCode"/>
    </th>
    <td align="left">
        <!--form:errors path="uniqueCode" cssClass="fieldError"/-->
        <form:input path="uniqueCode" id="uniqueCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinJournal.appId"/>
    </th>
    <td align="left">
        <!--form:errors path="appId" cssClass="fieldError"/-->
        <form:input path="appId" id="appId" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiBcoinJournal')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiBcoinJournalForm'));
</script>

<v:javascript formName="fiBcoinJournal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
