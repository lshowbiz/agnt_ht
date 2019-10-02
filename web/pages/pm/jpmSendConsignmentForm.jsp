<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSendConsignmentDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmSendConsignmentDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmSendConsignment">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmSendConsignment')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmSendConsignment.*">
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

<form:form commandName="jpmSendConsignment" method="post" action="editJpmSendConsignment.html" onsubmit="return validateJpmSendConsignment(this)" id="jpmSendConsignmentForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmSendConsignment')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="consignmentNo"/>

    <tr><th>
        <jecs:label  key="jpmSendConsignment.fabId"/>
    </th>
    <td align="left">
        <!--form:errors path="fabId" cssClass="fieldError"/-->
        <form:input path="fabId" id="fabId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmSendConsignment.sendDate"/>
    </th>
    <td align="left">
        <!--form:errors path="sendDate" cssClass="fieldError"/-->
        <form:input path="sendDate" id="sendDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmSendConsignment.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmSendConsignment.specNo"/>
    </th>
    <td align="left">
        <!--form:errors path="specNo" cssClass="fieldError"/-->
        <form:input path="specNo" id="specNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmSendConsignment.consignmenNum"/>
    </th>
    <td align="left">
        <!--form:errors path="consignmenNum" cssClass="fieldError"/-->
        <form:input path="consignmenNum" id="consignmenNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmSendConsignment.sendUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="sendUserCode" cssClass="fieldError"/-->
        <form:input path="sendUserCode" id="sendUserCode" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmSendConsignment')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmSendConsignmentForm'));
</script>

<v:javascript formName="jpmSendConsignment" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
