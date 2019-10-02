<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberUpgradeNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberUpgradeNoteDetail.heading"/></content>

<c:set var="buttons">

		<wecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.save"/>" />
		</wecs:power>
		<wecs:power powerCode="deleteJmiMemberUpgradeNote">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiMemberUpgradeNote<jecs:localefmt:message key="button.delete"/>" />
		</wecs:power>
</c:set>

<spring:bind path="jmiMemberUpgradeNote.*">
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

<form:form commandName="jmiMemberUpgradeNote" method="post" action="editJmiMemberUpgradeNote.html" onsubmit="return validateJmiMemberUpgradeNote(this)" id="jmiMemberUpgradeNoteForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDe<jecs:localeberUpgradeNote')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="munId"/>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.newCard"/>
    </th>
    <td align="left">
        <!--form:errors path="newCard" cssClass="fieldError"/-->
        <form:input path="newCard" id="newCard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.oldCard"/>
    </th>
    <td align="left">
        <!--form:errors path="oldCard" cssClass="fieldError"/-->
        <form:input path="oldCard" id="oldCard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.memberOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberOrderNo" cssClass="fieldError"/-->
        <form:input path="memberOrderNo" id="memberOrderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.updateDate"/>
    </th>
    <td align="left">
        <!--form:errors path="updateDate" cssClass="fieldError"/-->
        <form:input path="updateDate" id="updateDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.updateType"/>
    </th>
    <td align="left">
        <!--form:errors path="updateType" cssClass="fieldError"/-->
        <form:input path="updateType" id="updateType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiMemberUpgradeNote.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="1<jecs:localetr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input ty<jecs:localeclass="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiMemberUpgradeNote')" value="<fmt:messag<jecs:localen.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiMemberUpgradeNoteForm'));
</script>

<v:javascript formName="jmiMemberUpgradeNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
