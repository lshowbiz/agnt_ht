<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalPostalcodeDetail.title"/></title>
<content tag="heading"><jecs:locale key="jalPostalcodeDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJalPostalcode">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JalPostalcode')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jalPostalcode.*">
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

<form:form commandName="jalPostalcode" method="post" action="editJalPostalcode.html" onsubmit="return validateJalPostalcode(this)" id="jalPostalcodeForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalPostalcode')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="postalcodeId"/>

    <tr><th>
        <jecs:label  key="jalPostalcode.cityId"/>
    </th>
    <td align="left">
        <!--form:errors path="cityId" cssClass="fieldError"/-->
        <form:input path="cityId" id="cityId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jalPostalcode.postalcode"/>
    </th>
    <td align="left">
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
        <form:input path="postalcode" id="postalcode" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalPostalcode')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jalPostalcodeForm'));
</script>

<v:javascript formName="jalPostalcode" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
