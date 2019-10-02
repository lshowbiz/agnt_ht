<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseStockTraceDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseStockTraceDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdWarehouseStockTrace">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdWarehouseStockTrace')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdWarehouseStockTrace.*">
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

<form:form commandName="pdWarehouseStockTrace" method="post" action="editPdWarehouseStockTrace.html" onsubmit="return validatePdWarehouseStockTrace(this)" id="pdWarehouseStockTraceForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseStockTrace')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.beforeQty"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeQty" cssClass="fieldError"/-->
        <form:input path="beforeQty" id="beforeQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.changeQty"/>
    </th>
    <td align="left">
        <!--form:errors path="changeQty" cssClass="fieldError"/-->
        <form:input path="changeQty" id="changeQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.behindQty"/>
    </th>
    <td align="left">
        <!--form:errors path="behindQty" cssClass="fieldError"/-->
        <form:input path="behindQty" id="behindQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.orderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <form:input path="orderNo" id="orderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.actionType"/>
    </th>
    <td align="left">
        <!--form:errors path="actionType" cssClass="fieldError"/-->
        <form:input path="actionType" id="actionType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.usrCode"/>
    </th>
    <td align="left">
        <!--form:errors path="usrCode" cssClass="fieldError"/-->
        <form:input path="usrCode" id="usrCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStockTrace.ip"/>
    </th>
    <td align="left">
        <!--form:errors path="ip" cssClass="fieldError"/-->
        <form:input path="ip" id="ip" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseStockTrace')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdWarehouseStockTraceForm'));
</script>

<v:javascript formName="pdWarehouseStockTrace" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
