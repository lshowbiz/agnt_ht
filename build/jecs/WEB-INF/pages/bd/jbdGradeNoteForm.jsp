<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdGradeNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdGradeNoteDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdGradeNote">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdGradeNote')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdGradeNote.*">
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

<form:form commandName="jbdGradeNote" method="post" action="editJbdGradeNote.html" onsubmit="return validateJbdGradeNote(this)" id="jbdGradeNoteForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdGradeNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdGradeNote.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.oldHonorStar"/>
    </th>
    <td align="left">
        <!--form:errors path="oldHonorStar" cssClass="fieldError"/-->
        <form:input path="oldHonorStar" id="oldHonorStar" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.newHonorStar"/>
    </th>
    <td align="left">
        <!--form:errors path="newHonorStar" cssClass="fieldError"/-->
        <form:input path="newHonorStar" id="newHonorStar" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.oldPassStar"/>
    </th>
    <td align="left">
        <!--form:errors path="oldPassStar" cssClass="fieldError"/-->
        <form:input path="oldPassStar" id="oldPassStar" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.newPassStar"/>
    </th>
    <td align="left">
        <!--form:errors path="newPassStar" cssClass="fieldError"/-->
        <form:input path="newPassStar" id="newPassStar" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        <form:input path="createNo" id="createNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdGradeNote.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdGradeNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdGradeNoteForm'));
</script>

<v:javascript formName="jbdGradeNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
