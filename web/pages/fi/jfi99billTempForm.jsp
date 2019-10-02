<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billTempDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billTempDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfi99billTemp">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'Jfi99billTemp')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfi99billTemp.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jfi99billTemp" method="post" action="editJfi99billTemp.html" onsubmit="return validateJfi99billTemp(this)" id="jfi99billTempForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Jfi99billTemp')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="orderId"/>

    <tr><th>
        <jecs:label  key="jfi99billTemp.payerName"/>
    </th>
    <td align="left">
        <!--form:errors path="payerName" cssClass="fieldError"/-->
        <form:input path="payerName" id="payerName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billTemp.payerContact"/>
    </th>
    <td align="left">
        <!--form:errors path="payerContact" cssClass="fieldError"/-->
        <form:input path="payerContact" id="payerContact" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billTemp.orderAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="orderAmount" cssClass="fieldError"/-->
        <form:input path="orderAmount" id="orderAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billTemp.bankId"/>
    </th>
    <td align="left">
        <!--form:errors path="bankId" cssClass="fieldError"/-->
        <form:input path="bankId" id="bankId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfi99billTemp.status"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Jfi99billTemp')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfi99billTempForm'));
</script>

<v:javascript formName="jfi99billTemp" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
