<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseFrozenDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseFrozenDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdWarehouseFrozenDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdWarehouseFrozenDetail')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdWarehouseFrozenDetail.*">
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

<form:form commandName="pdWarehouseFrozenDetail" method="post" action="editPdWarehouseFrozenDetail.html" onsubmit="return validatePdWarehouseFrozenDetail(this)" id="pdWarehouseFrozenDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseFrozenDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.batchId"/>
    </th>
    <td align="left">
        <!--form:errors path="batchId" cssClass="fieldError"/-->
        <form:input path="batchId" id="batchId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.warehouseNo"/>
    </th>
    <td align="left">
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <form:input path="warehouseNo" id="warehouseNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.normalQty"/>
    </th>
    <td align="left">
        <!--form:errors path="normalQty" cssClass="fieldError"/-->
        <form:input path="normalQty" id="normalQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.damageQty"/>
    </th>
    <td align="left">
        <!--form:errors path="damageQty" cssClass="fieldError"/-->
        <form:input path="damageQty" id="damageQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseFrozenDetail.unknownQty"/>
    </th>
    <td align="left">
        <!--form:errors path="unknownQty" cssClass="fieldError"/-->
        <form:input path="unknownQty" id="unknownQty" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseFrozenDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdWarehouseFrozenDetailForm'));
</script>

<v:javascript formName="pdWarehouseFrozenDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
