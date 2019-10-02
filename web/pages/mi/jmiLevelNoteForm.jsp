<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiLevelNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiLevelNoteDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJmiLevelNote">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiLevelNote')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jmiLevelNote.*">
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

<form:form commandName="jmiLevelNote" method="post" action="editJmiLevelNote.html" onsubmit="return validateJmiLevelNote(this)" id="jmiLevelNoteForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiLevelNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jmiLevelNote.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiLevelNote.oldMemberLevel"/>
    </th>
    <td align="left">
        <!--form:errors path="oldMemberLevel" cssClass="fieldError"/-->
        <form:input path="oldMemberLevel" id="oldMemberLevel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiLevelNote.newMemberLevel"/>
    </th>
    <td align="left">
        <!--form:errors path="newMemberLevel" cssClass="fieldError"/-->
        <form:input path="newMemberLevel" id="newMemberLevel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiLevelNote.updateType"/>
    </th>
    <td align="left">
        <!--form:errors path="updateType" cssClass="fieldError"/-->
        <form:input path="updateType" id="updateType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiLevelNote.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiLevelNote.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiLevelNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiLevelNoteForm'));
</script>

<v:javascript formName="jmiLevelNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
