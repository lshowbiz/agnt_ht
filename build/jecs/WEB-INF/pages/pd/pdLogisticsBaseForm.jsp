<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdLogisticsBaseDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdLogisticsBaseDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdLogisticsBase">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdLogisticsBase')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdLogisticsBase.*">
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

<form:form commandName="pdLogisticsBase" method="post" action="editPdLogisticsBase.html" onsubmit="return validatePdLogisticsBase(this)" id="pdLogisticsBaseForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdLogisticsBase')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="baseId"/>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.memberOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="memberOrderNo" cssClass="fieldError"/-->
        <form:input path="memberOrderNo" id="memberOrderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.siNo"/>
    </th>
    <td align="left">
        <!--form:errors path="siNo" cssClass="fieldError"/-->
        <form:input path="siNo" id="siNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.wmsDo"/>
    </th>
    <td align="left">
        <!--form:errors path="wmsDo" cssClass="fieldError"/-->
        <form:input path="wmsDo" id="wmsDo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.statusTime"/>
    </th>
    <td align="left">
        <!--form:errors path="statusTime" cssClass="fieldError"/-->
        <form:input path="statusTime" id="statusTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.statusCode"/>
    </th>
    <td align="left">
        <!--form:errors path="statusCode" cssClass="fieldError"/-->
        <form:input path="statusCode" id="statusCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.statusName"/>
    </th>
    <td align="left">
        <!--form:errors path="statusName" cssClass="fieldError"/-->
        <form:input path="statusName" id="statusName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.operator"/>
    </th>
    <td align="left">
        <!--form:errors path="operator" cssClass="fieldError"/-->
        <form:input path="operator" id="operator" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.otherOne"/>
    </th>
    <td align="left">
        <!--form:errors path="otherOne" cssClass="fieldError"/-->
        <form:input path="otherOne" id="otherOne" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.otherTwo"/>
    </th>
    <td align="left">
        <!--form:errors path="otherTwo" cssClass="fieldError"/-->
        <form:input path="otherTwo" id="otherTwo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.otherThree"/>
    </th>
    <td align="left">
        <!--form:errors path="otherThree" cssClass="fieldError"/-->
        <form:input path="otherThree" id="otherThree" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.otherFour"/>
    </th>
    <td align="left">
        <!--form:errors path="otherFour" cssClass="fieldError"/-->
        <form:input path="otherFour" id="otherFour" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBase.otherFive"/>
    </th>
    <td align="left">
        <!--form:errors path="otherFive" cssClass="fieldError"/-->
        <form:input path="otherFive" id="otherFive" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdLogisticsBase')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdLogisticsBaseForm'));
</script>

<v:javascript formName="pdLogisticsBase" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
