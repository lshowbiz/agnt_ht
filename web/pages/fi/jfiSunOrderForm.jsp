<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunOrderDetail.heading"/></content>

<c:set var="buttons">
		<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
</c:set>

<spring:bind path="jfiSunOrder.*">
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

<form:form commandName="jfiSunOrder" method="post" action="editJfiSunOrder.html" onsubmit="return validateJfiSunOrder(this)" id="jfiSunOrderForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="moId"/>

    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
        ${jfiSunOrder.memberOrderNo }
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<jecs:code listCode="fi.sun.order" value="${jfiSunOrder.sorderType}"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.firstName"/>
    </th>
    <td align="left">
    	${jfiSunOrder.firstName}
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.lastName"/>
    </th>
    <td align="left">
    	${jfiSunOrder.lastName}
    </td></tr>

</table>
<ec:table
	tableId="sellStock"
	items="jfiSunOrder.jfiSunOrderList"
	var="pi"
	width="100%" form = "prRefundForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
    			<ec:column property="jpmProductSale.productName" title="pmProduct.productName"/>
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn"/>
    			<ec:column property="pr_qty" title="pr.Stock.returnqty" styleClass="centerColumn">
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${pi.jpmProductSale.uniNo }" />]" value="${pi.jpmProductSale.uniNo }" />
				    <input type="hidden" name="mol_id" id="mol_id" value="${pi.molId }" />
				    <input type='text' class='text medium' name='qty' value='${pi.rqty }' size='3' id=qty<c:out value="${pi.jpmProductSale.uniNo }" />>
    			</ec:column>
    			<ec:column property="product_price" title="pd.price" styleClass="centerColumn">
				    <input type="text" class='text medium' name='price' value='<c:out value="${pi.srprice }" />' size='3' id=price<c:out value="${pi.jpmProductSale.uniNo }" />>
    			</ec:column>
				</ec:row>
</ec:table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiSunOrderForm'));
</script>

<v:javascript formName="jfiSunOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
