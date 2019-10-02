<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseStockDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseStockDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<input type="button" class="button" name="back"
					onclick="toBack('${param.strAction}')"
					value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="pdWarehouseStock.*">
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

<form:form commandName="pdWarehouseStock" method="post" action="editPdWarehouseStock.html" onsubmit="return validatePdWarehouseStock(this)" id="pdWarehouseStockForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseStock')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="jpmSalePromoter.companyno"/>
    </th>
    <td align="left">
        ${pdWarehouseStock.companyCode }
    </td></tr>

    <tr><th>
        <jecs:label  key="pdWarehouseStock.productNo"/>
    </th>
    <td align="left">
        ${pdWarehouseStock.productNo }
    </td></tr>

	<tr><th>
        <jecs:label  key="busi.warehouse.warehouseno"/>
    </th>
    <td align="left">
    	${pdWarehouseStock.pdWarehouse.warehouseNo }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="pmProduct.storageCordon"/>
    </th>
    <td align="left">
        <!--form:errors path="version" cssClass="fieldError"/-->
        <form:input path="warnQty" id="warnQty" cssClass="text medium" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseStock')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdWarehouseStockForm'));
</script>

<v:javascript formName="pdWarehouseStock" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
