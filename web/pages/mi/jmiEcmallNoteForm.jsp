<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiEcmallNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiEcmallNoteDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJmiEcmallNote">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiEcmallNote')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jmiEcmallNote.*">
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

<form:form commandName="jmiEcmallNote" method="post" action="editJmiEcmallNote.html" onsubmit="return validateJmiEcmallNote(this)" id="jmiEcmallNoteForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiEcmallNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.url"/>
    </th>
    <td align="left">
        <!--form:errors path="url" cssClass="fieldError"/-->
        <form:input path="url" id="url" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.info"/>
    </th>
    <td align="left">
        <!--form:errors path="info" cssClass="fieldError"/-->
        <form:input path="info" id="info" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.code"/>
    </th>
    <td align="left">
        <!--form:errors path="code" cssClass="fieldError"/-->
        <form:input path="code" id="code" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.noteTyoe"/>
    </th>
    <td align="left">
        <!--form:errors path="noteTyoe" cssClass="fieldError"/-->
        <form:input path="noteTyoe" id="noteTyoe" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiEcmallNote.remark"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiEcmallNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiEcmallNoteForm'));
</script>

<v:javascript formName="jmiEcmallNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
