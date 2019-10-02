<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipmentsDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShipmentsDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdShipmentsDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdShipmentsDetail')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdShipmentsDetail.*">
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

<form:form commandName="pdShipmentsDetail" method="post" action="editPdShipmentsDetail.html" onsubmit="return validatePdShipmentsDetail(this)" id="pdShipmentsDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipmentsDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="sdId"/>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.orderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <form:input path="orderNo" id="orderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.orderType"/>
    </th>
    <td align="left">
        <!--form:errors path="orderType" cssClass="fieldError"/-->
        <form:input path="orderType" id="orderType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.quantity"/>
    </th>
    <td align="left">
        <!--form:errors path="quantity" cssClass="fieldError"/-->
        <form:input path="quantity" id="quantity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.remainQuantity"/>
    </th>
    <td align="left">
        <!--form:errors path="remainQuantity" cssClass="fieldError"/-->
        <form:input path="remainQuantity" id="remainQuantity" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdShipmentsDetail.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipmentsDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdShipmentsDetailForm'));
</script>

<v:javascript formName="pdShipmentsDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
