<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositTempDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiDepositTempDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiDepositTemp">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiDepositTemp')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiDepositTemp.*">
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

<form:form commandName="jfiDepositTemp" method="post" action="editJfiDepositTemp.html" onsubmit="return validateJfiDepositTemp(this)" id="jfiDepositTempForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiDepositTemp')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="tempId"/>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.dealType"/>
    </th>
    <td align="left">
        <!--form:errors path="dealType" cssClass="fieldError"/-->
        <form:input path="dealType" id="dealType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.moneyType"/>
    </th>
    <td align="left">
        <!--form:errors path="moneyType" cssClass="fieldError"/-->
        <form:input path="moneyType" id="moneyType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.money"/>
    </th>
    <td align="left">
        <!--form:errors path="money" cssClass="fieldError"/-->
        <form:input path="money" id="money" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.notes"/>
    </th>
    <td align="left">
        <!--form:errors path="notes" cssClass="fieldError"/-->
        <form:input path="notes" id="notes" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.createrNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createrNo" cssClass="fieldError"/-->
        <form:input path="createrNo" id="createrNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiDepositTemp.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiDepositTemp')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiDepositTempForm'));
</script>

<v:javascript formName="jfiDepositTemp" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
