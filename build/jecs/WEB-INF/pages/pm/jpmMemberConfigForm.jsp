<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmMemberConfigDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmMemberConfigDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmMemberConfig">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmMemberConfig')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmMemberConfig.*">
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

<form:form commandName="jpmMemberConfig" method="post" action="editJpmMemberConfig.html" onsubmit="return validateJpmMemberConfig(this)" id="jpmMemberConfigForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmMemberConfig')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="configNo"/>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.molId"/>
    </th>
    <td align="left">
        <!--form:errors path="molId" cssClass="fieldError"/-->
        <form:input path="molId" id="molId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.moId"/>
    </th>
    <td align="left">
        <!--form:errors path="moId" cssClass="fieldError"/-->
        <form:input path="moId" id="moId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.productId"/>
    </th>
    <td align="left">
        <!--form:errors path="productId" cssClass="fieldError"/-->
        <form:input path="productId" id="productId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.productName"/>
    </th>
    <td align="left">
        <!--form:errors path="productName" cssClass="fieldError"/-->
        <form:input path="productName" id="productName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.amount"/>
    </th>
    <td align="left">
        <!--form:errors path="amount" cssClass="fieldError"/-->
        <form:input path="amount" id="amount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.completeamount"/>
    </th>
    <td align="left">
        <!--form:errors path="completeamount" cssClass="fieldError"/-->
        <form:input path="completeamount" id="completeamount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.createtime"/>
    </th>
    <td align="left">
        <!--form:errors path="createtime" cssClass="fieldError"/-->
        <form:input path="createtime" id="createtime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.sysNo"/>
    </th>
    <td align="left">
        <!--form:errors path="sysNo" cssClass="fieldError"/-->
        <form:input path="sysNo" id="sysNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.weight"/>
    </th>
    <td align="left">
        <!--form:errors path="weight" cssClass="fieldError"/-->
        <form:input path="weight" id="weight" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmMemberConfig.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmMemberConfig')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmMemberConfigForm'));
</script>

<v:javascript formName="jpmMemberConfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
