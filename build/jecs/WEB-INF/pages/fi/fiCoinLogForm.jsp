<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCoinLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiCoinLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteFiCoinLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiCoinLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="fiCoinLog.*">
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

<form:form commandName="fiCoinLog" method="post" action="editFiCoinLog.html" onsubmit="return validateFiCoinLog(this)" id="fiCoinLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiCoinLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="fclId"/>

    <tr><th>
        <jecs:label  key="fiCoinLog.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.coinType"/>
    </th>
    <td align="left">
        <!--form:errors path="coinType" cssClass="fieldError"/-->
        <form:input path="coinType" id="coinType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.dealType"/>
    </th>
    <td align="left">
        <!--form:errors path="dealType" cssClass="fieldError"/-->
        <form:input path="dealType" id="dealType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.createUser"/>
    </th>
    <td align="left">
        <!--form:errors path="createUser" cssClass="fieldError"/-->
        <form:input path="createUser" id="createUser" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.receiveCoin"/>
    </th>
    <td align="left">
        <!--form:errors path="receiveCoin" cssClass="fieldError"/-->
        <form:input path="receiveCoin" id="receiveCoin" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.coin"/>
    </th>
    <td align="left">
        <!--form:errors path="coin" cssClass="fieldError"/-->
        <form:input path="coin" id="coin" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.uniqueCode"/>
    </th>
    <td align="left">
        <!--form:errors path="uniqueCode" cssClass="fieldError"/-->
        <form:input path="uniqueCode" id="uniqueCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiCoinLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiCoinLogForm'));
</script>

<v:javascript formName="fiCoinLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
