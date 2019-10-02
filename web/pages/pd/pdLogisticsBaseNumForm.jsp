<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdLogisticsBaseNumDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdLogisticsBaseNumDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdLogisticsBaseNum">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdLogisticsBaseNum')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdLogisticsBaseNum.*">
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

<form:form commandName="pdLogisticsBaseNum" method="post" action="editPdLogisticsBaseNum.html" onsubmit="return validatePdLogisticsBaseNum(this)" id="pdLogisticsBaseNumForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdLogisticsBaseNum')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="numId"/>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.baseId"/>
    </th>
    <td align="left">
        <!--form:errors path="baseId" cssClass="fieldError"/-->
        <form:input path="baseId" id="baseId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.pdlogisticsbasenumNo"/>
    </th>
    <td align="left">
        <!--form:errors path="pdlogisticsbasenumNo" cssClass="fieldError"/-->
        <form:input path="pdlogisticsbasenumNo" id="pdlogisticsbasenumNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.name"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        <form:input path="name" id="name" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.mailTime"/>
    </th>
    <td align="left">
        <!--form:errors path="mailTime" cssClass="fieldError"/-->
        <form:input path="mailTime" id="mailTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.otherOne"/>
    </th>
    <td align="left">
        <!--form:errors path="otherOne" cssClass="fieldError"/-->
        <form:input path="otherOne" id="otherOne" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseNum.otherTwo"/>
    </th>
    <td align="left">
        <!--form:errors path="otherTwo" cssClass="fieldError"/-->
        <form:input path="otherTwo" id="otherTwo" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdLogisticsBaseNum')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdLogisticsBaseNumForm'));
</script>

<v:javascript formName="pdLogisticsBaseNum" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
