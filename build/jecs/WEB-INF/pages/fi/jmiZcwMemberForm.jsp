<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiZcwMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiZcwMemberDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJmiZcwMember">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiZcwMember')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jmiZcwMember.*">
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

<form:form commandName="jmiZcwMember" method="post" action="editJmiZcwMember.html" onsubmit="return validateJmiZcwMember(this)" id="jmiZcwMemberForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiZcwMember')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="zcwId"/>

    <tr><th>
        <jecs:label  key="jmiZcwMember.zcwDept"/>
    </th>
    <td align="left">
        <!--form:errors path="zcwDept" cssClass="fieldError"/-->
        <form:input path="zcwDept" id="zcwDept" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiZcwMember.zcwDeptname"/>
    </th>
    <td align="left">
        <!--form:errors path="zcwDeptname" cssClass="fieldError"/-->
        <form:input path="zcwDeptname" id="zcwDeptname" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiZcwMember.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiZcwMember.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiZcwMember.tel"/>
    </th>
    <td align="left">
        <!--form:errors path="tel" cssClass="fieldError"/-->
        <form:input path="tel" id="tel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiZcwMember.email"/>
    </th>
    <td align="left">
        <!--form:errors path="email" cssClass="fieldError"/-->
        <form:input path="email" id="email" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiZcwMember')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiZcwMemberForm'));
</script>

<v:javascript formName="jmiZcwMember" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
