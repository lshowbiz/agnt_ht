<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiTicketDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiTicketDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJmiTicket">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiTicket')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jmiTicket.*">
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

<form:form commandName="jmiTicket" method="post" action="editJmiTicket.html" onsubmit="return validateJmiTicket(this)" id="jmiTicketForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiTicket')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jmiTicket.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.ticketType"/>
    </th>
    <td align="left">
        <!--form:errors path="ticketType" cssClass="fieldError"/-->
        <form:input path="ticketType" id="ticketType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.applyUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="applyUserCode" cssClass="fieldError"/-->
        <form:input path="applyUserCode" id="applyUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.papernumber"/>
    </th>
    <td align="left">
        <!--form:errors path="papernumber" cssClass="fieldError"/-->
        <form:input path="papernumber" id="papernumber" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.censusProvince"/>
    </th>
    <td align="left">
        <!--form:errors path="censusProvince" cssClass="fieldError"/-->
        <form:input path="censusProvince" id="censusProvince" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.censusCity"/>
    </th>
    <td align="left">
        <!--form:errors path="censusCity" cssClass="fieldError"/-->
        <form:input path="censusCity" id="censusCity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.censusDistrict"/>
    </th>
    <td align="left">
        <!--form:errors path="censusDistrict" cssClass="fieldError"/-->
        <form:input path="censusDistrict" id="censusDistrict" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.censusAddress"/>
    </th>
    <td align="left">
        <!--form:errors path="censusAddress" cssClass="fieldError"/-->
        <form:input path="censusAddress" id="censusAddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.province"/>
    </th>
    <td align="left">
        <!--form:errors path="province" cssClass="fieldError"/-->
        <form:input path="province" id="province" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.city"/>
    </th>
    <td align="left">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <form:input path="city" id="city" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.district"/>
    </th>
    <td align="left">
        <!--form:errors path="district" cssClass="fieldError"/-->
        <form:input path="district" id="district" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.address"/>
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.mobiletele"/>
    </th>
    <td align="left">
        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
        <form:input path="mobiletele" id="mobiletele" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jmiTicket.remark"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiTicket')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiTicketForm'));
</script>

<v:javascript formName="jmiTicket" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
