<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwViewpeopleDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwViewpeopleDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteInwViewpeople">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'InwViewpeople')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="inwViewpeople.*">
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

<form:form commandName="inwViewpeople" method="post" action="editInwViewpeople.html" onsubmit="return validateInwViewpeople(this)" id="inwViewpeopleForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('InwViewpeople')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="inwViewpeople.suggestionid"/>
    </th>
    <td align="left">
        <!--form:errors path="suggestionid" cssClass="fieldError"/-->
        <form:input path="suggestionid" id="suggestionid" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwViewpeople.viewStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="viewStatus" cssClass="fieldError"/-->
        <form:input path="viewStatus" id="viewStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwViewpeople.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="inwViewpeople.viewTime"/>
    </th>
    <td align="left">
        <!--form:errors path="viewTime" cssClass="fieldError"/-->
        <form:input path="viewTime" id="viewTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('InwViewpeople')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('inwViewpeopleForm'));
</script>

<v:javascript formName="inwViewpeople" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
