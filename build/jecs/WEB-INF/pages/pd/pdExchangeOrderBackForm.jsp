<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdExchangeOrderBackDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdExchangeOrderBackDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdExchangeOrderBack">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdExchangeOrderBack')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdExchangeOrderBack.*">
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

<form:form commandName="pdExchangeOrderBack" method="post" action="editPdExchangeOrderBack.html" onsubmit="return validatePdExchangeOrderBack(this)" id="pdExchangeOrderBackForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdExchangeOrderBack')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="pdExchangeOrderBack.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdExchangeOrderBack.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdExchangeOrderBack.originalQty"/>
    </th>
    <td align="left">
        <!--form:errors path="originalQty" cssClass="fieldError"/-->
        <form:input path="originalQty" id="originalQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdExchangeOrderBack.qty"/>
    </th>
    <td align="left">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <form:input path="qty" id="qty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdExchangeOrderBack.eoNo"/>
    </th>
    <td align="left">
        <!--form:errors path="eoNo" cssClass="fieldError"/-->
        <form:input path="eoNo" id="eoNo" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdExchangeOrderBack')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdExchangeOrderBackForm'));
</script>

<v:javascript formName="pdExchangeOrderBack" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
