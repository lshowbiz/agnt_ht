<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmWineSettingInfDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmWineSettingInfDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmWineSettingInf">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmWineSettingInf')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmWineSettingInf.*">
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

<form:form commandName="jpmWineSettingInf" method="post" action="editJpmWineSettingInf.html" onsubmit="return validateJpmWineSettingInf(this)" id="jpmWineSettingInfForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmWineSettingInf')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="settingId"/>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.productId"/>
    </th>
    <td align="left">
        <!--form:errors path="productId" cssClass="fieldError"/-->
        <form:input path="productId" id="productId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.productName"/>
    </th>
    <td align="left">
        <!--form:errors path="productName" cssClass="fieldError"/-->
        <form:input path="productName" id="productName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.productQty"/>
    </th>
    <td align="left">
        <!--form:errors path="productQty" cssClass="fieldError"/-->
        <form:input path="productQty" id="productQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.unitNo"/>
    </th>
    <td align="left">
        <!--form:errors path="unitNo" cssClass="fieldError"/-->
        <form:input path="unitNo" id="unitNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.resultcode"/>
    </th>
    <td align="left">
        <!--form:errors path="resultcode" cssClass="fieldError"/-->
        <form:input path="resultcode" id="resultcode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingInf.resultdescription"/>
    </th>
    <td align="left">
        <!--form:errors path="resultdescription" cssClass="fieldError"/-->
        <form:input path="resultdescription" id="resultdescription" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmWineSettingInf')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmWineSettingInfForm'));
</script>

<v:javascript formName="jpmWineSettingInf" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
