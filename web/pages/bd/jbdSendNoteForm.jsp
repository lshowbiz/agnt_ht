<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSendNoteDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSendNote">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSendNote')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSendNote.*">
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

<form:form commandName="jbdSendNote" method="post" action="editJbdSendNote.html" onsubmit="return validateJbdSendNote(this)" id="jbdSendNoteForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSendNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdSendNote.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.remittanceBNum"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceBNum" cssClass="fieldError"/-->
        <form:input path="remittanceBNum" id="remittanceBNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.operaterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterCode" cssClass="fieldError"/-->
        <form:input path="operaterCode" id="operaterCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.operaterTime"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterTime" cssClass="fieldError"/-->
        <form:input path="operaterTime" id="operaterTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.sendDate"/>
    </th>
    <td align="left">
        <!--form:errors path="sendDate" cssClass="fieldError"/-->
        <form:input path="sendDate" id="sendDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.registerStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="registerStatus" cssClass="fieldError"/-->
        <form:input path="registerStatus" id="registerStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.reissueStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="reissueStatus" cssClass="fieldError"/-->
        <form:input path="reissueStatus" id="reissueStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.supplyTime"/>
    </th>
    <td align="left">
        <!--form:errors path="supplyTime" cssClass="fieldError"/-->
        <form:input path="supplyTime" id="supplyTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.remittanceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceMoney" cssClass="fieldError"/-->
        <form:input path="remittanceMoney" id="remittanceMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.noteNo"/>
    </th>
    <td align="left">
        <!--form:errors path="noteNo" cssClass="fieldError"/-->
        <form:input path="noteNo" id="noteNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendNote.fee"/>
    </th>
    <td align="left">
        <!--form:errors path="fee" cssClass="fieldError"/-->
        <form:input path="fee" id="fee" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSendNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSendNoteForm'));
</script>

<v:javascript formName="jbdSendNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
