<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdStatusExcStockDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdStatusExcStockDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdStatusExcStockDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdStatusExcStockDetail')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdStatusExcStockDetail.*">
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

<form:form commandName="pdStatusExcStockDetail" method="post" action="editPdStatusExcStockDetail.html" onsubmit="return validatePdStatusExcStockDetail(this)" id="pdStatusExcStockDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdStatusExcStockDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="pdStatusExcStockDetail.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStockDetail.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStockDetail.damageQty"/>
    </th>
    <td align="left">
        <!--form:errors path="damageQty" cssClass="fieldError"/-->
        <form:input path="damageQty" id="damageQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStockDetail.normalQty"/>
    </th>
    <td align="left">
        <!--form:errors path="normalQty" cssClass="fieldError"/-->
        <form:input path="normalQty" id="normalQty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdStatusExcStockDetail.unknownQty"/>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdStatusExcStockDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdStatusExcStockDetailForm'));
</script>

<v:javascript formName="pdStatusExcStockDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
