<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipStrategyDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShipStrategyDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdShipStrategy">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdShipStrategy')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdShipStrategy.*">
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

<form:form commandName="pdShipStrategy" method="post" action="editPdShipStrategy.html" onsubmit="return validatePdShipStrategy(this)" id="pdShipStrategyForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipStrategy')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="ssId"/>

    <tr><th>
        <jecs:label  key="pdShipStrategy.compayCode"/>
    </th>
    <td align="left">
        <!--form:errors path="compayCode" cssClass="fieldError"/-->
        <form:input path="compayCode" id="compayCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipStrategy.ssName"/>
    </th>
    <td align="left">
        <!--form:errors path="ssName" cssClass="fieldError"/-->
        <form:input path="ssName" id="ssName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipStrategy.ssDesc"/>
    </th>
    <td align="left">
        <!--form:errors path="ssDesc" cssClass="fieldError"/-->
        <form:input path="ssDesc" id="ssDesc" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipStrategy')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdShipStrategyForm'));
</script>

<v:javascript formName="pdShipStrategy" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
