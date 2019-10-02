<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCommonAddrDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiCommonAddrDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteFiCommonAddr">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiCommonAddr')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="fiCommonAddr.*">
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

<form:form commandName="fiCommonAddr" method="post" action="editFiCommonAddr.html" onsubmit="return validateFiCommonAddr(this)" id="fiCommonAddrForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiCommonAddr')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="userCode"/>

    <tr><th>
        <jecs:label  key="fiCommonAddr.province"/>
    </th>
    <td align="left">
        <!--form:errors path="province" cssClass="fieldError"/-->
        <form:input path="province" id="province" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCommonAddr.city"/>
    </th>
    <td align="left">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <form:input path="city" id="city" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCommonAddr.district"/>
    </th>
    <td align="left">
        <!--form:errors path="district" cssClass="fieldError"/-->
        <form:input path="district" id="district" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCommonAddr.address"/>
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiCommonAddr')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiCommonAddrForm'));
</script>

<v:javascript formName="fiCommonAddr" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
