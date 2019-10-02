<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSpecialStarDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSpecialStarDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSpecialStar">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSpecialStar')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSpecialStar.*">
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

<form:form commandName="jbdSpecialStar" method="post" action="editJbdSpecialStar.html" onsubmit="return validateJbdSpecialStar(this)" id="jbdSpecialStarForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSpecialStar')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="userCode"/>

    <tr><th>
        <jecs:label  key="jbdSpecialStar.crownEnvoyNum"/>
    </th>
    <td align="left">
        <!--form:errors path="crownEnvoyNum" cssClass="fieldError"/-->
        <form:input path="crownEnvoyNum" id="crownEnvoyNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSpecialStar.departmentNum"/>
    </th>
    <td align="left">
        <!--form:errors path="departmentNum" cssClass="fieldError"/-->
        <form:input path="departmentNum" id="departmentNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSpecialStar.minDepartmentNum"/>
    </th>
    <td align="left">
        <!--form:errors path="minDepartmentNum" cssClass="fieldError"/-->
        <form:input path="minDepartmentNum" id="minDepartmentNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSpecialStar.passStar"/>
    </th>
    <td align="left">
        <!--form:errors path="passStar" cssClass="fieldError"/-->
        <form:input path="passStar" id="passStar" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSpecialStar')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSpecialStarForm'));
</script>

<v:javascript formName="jbdSpecialStar" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
