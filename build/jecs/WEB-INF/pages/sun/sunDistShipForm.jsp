<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sunDistShipDetail.title"/></title>
<content tag="heading"><jecs:locale key="sunDistShipDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSunDistShip">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SunDistShip')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sunDistShip.*">
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

<form:form commandName="sunDistShip" method="post" action="editSunDistShip.html" onsubmit="return validateSunDistShip(this)" id="sunDistShipForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SunDistShip')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="sdsId"/>

    <tr><th>
        <jecs:label  key="sunDistShip.siNo"/>
    </th>
    <td align="left">
        <!--form:errors path="siNo" cssClass="fieldError"/-->
        <form:input path="siNo" id="siNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sunDistShip.distCode"/>
    </th>
    <td align="left">
        <!--form:errors path="distCode" cssClass="fieldError"/-->
        <form:input path="distCode" id="distCode" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SunDistShip')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sunDistShipForm'));
</script>

<v:javascript formName="sunDistShip" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
