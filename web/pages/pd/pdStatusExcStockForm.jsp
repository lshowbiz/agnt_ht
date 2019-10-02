<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdStatusExcStockDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdStatusExcStockDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdStatusExcStock">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdStatusExcStock')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdStatusExcStock.*">
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

<form:form commandName="pdStatusExcStock" method="post" action="editPdStatusExcStock.html" onsubmit="return validatePdStatusExcStock(this)" id="pdStatusExcStockForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdStatusExcStock')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="seNo"/>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.amount"/>
    </th>
    <td align="left">
        <!--form:errors path="amount" cssClass="fieldError"/-->
        <form:input path="amount" id="amount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.createUrsCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createUrsCode" cssClass="fieldError"/-->
        <form:input path="createUrsCode" id="createUrsCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.checkTime"/>
    </th>
    <td align="left">
        <!--form:errors path="checkTime" cssClass="fieldError"/-->
        <form:input path="checkTime" id="checkTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.checkUrsCode"/>
    </th>
    <td align="left">
        <!--form:errors path="checkUrsCode" cssClass="fieldError"/-->
        <form:input path="checkUrsCode" id="checkUrsCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.checkRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="checkRemark" cssClass="fieldError"/-->
        <form:input path="checkRemark" id="checkRemark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.okTime"/>
    </th>
    <td align="left">
        <!--form:errors path="okTime" cssClass="fieldError"/-->
        <form:input path="okTime" id="okTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.okUrsCode"/>
    </th>
    <td align="left">
        <!--form:errors path="okUrsCode" cssClass="fieldError"/-->
        <form:input path="okUrsCode" id="okUrsCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.okRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="okRemark" cssClass="fieldError"/-->
        <form:input path="okRemark" id="okRemark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.editUrsCode"/>
    </th>
    <td align="left">
        <!--form:errors path="editUrsCode" cssClass="fieldError"/-->
        <form:input path="editUrsCode" id="editUrsCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.editTime"/>
    </th>
    <td align="left">
        <!--form:errors path="editTime" cssClass="fieldError"/-->
        <form:input path="editTime" id="editTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.stockFlag"/>
    </th>
    <td align="left">
        <!--form:errors path="stockFlag" cssClass="fieldError"/-->
        <form:input path="stockFlag" id="stockFlag" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStock.orderFlag"/>
    </th>
    <td align="left">
        <!--form:errors path="orderFlag" cssClass="fieldError"/-->
        <form:input path="orderFlag" id="orderFlag" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdStatusExcStock')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdStatusExcStockForm'));
</script>

<v:javascript formName="pdStatusExcStock" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
