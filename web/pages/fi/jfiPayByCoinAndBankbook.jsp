
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayByCoinDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiPayByCoinDetail.heading"/></content>
<script language="javascript" src="scripts/validate.js"></script>
<script language="javascript">
function saveSub(){
	<c:if test="${not empty msg }">
		if(!confirm('${msg}')){
			return false;
		}
	</c:if>
		if(!isFormPosted()){
			return false;
		}
	return true;
}
</script>
<form id="jfiPayByCoin" method="POST" action="jfiPayByCoinAndBankbook.html?strAction=jfiPayByCoinAndBankbook&orderId=${param.orderId }" onsubmit="if(saveSub()){return validateOthers(this);}else{return false;};">
<table class='detail' width="100%">
    <tr><th>
        <jecs:label  key="jfiPay99bill.orderAmount"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.amount }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="jfiPayByCoin.bankbook"/>
    </th>
    <td align="left">
    	${bankbook }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="jfiPayByCoin.coin"/>
    </th>
    <td align="left">
    	${coin }
    </td></tr>
    
	<tr><th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.save"/>" />
		<input type="button" class="button" name="cancel" onclick="history.back(-1);" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>

</table>
</form>

<table class='detail' width="100%">

    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.memberOrderNo }
    </td><th>
        <jecs:label  key="shipping.firstName"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.firstName}
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    </td><th>
        <jecs:label  key="shipping.lastName"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.lastName}
    </td></tr>

    <tr><th>
        <jecs:label  key="bonus.order.amount"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.amount }
    </td><th>
        <jecs:label  key="shipping.province"/>
    </th>
    <td align="left">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoMemberOrder.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.pvAmt }
    </td><th>
        <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.city}
    </td></tr>

    <tr><th>
    	&nbsp;
        <%--<jecs:label  key="busi.order.payMode"/>--%>
    </th>
    <td align="left">
    	&nbsp;
    	<%--<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>--%>
    </td><th>
        <jecs:label  key="shipping.district"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.district}
    </td></tr>

    <tr><th>
        <jecs:label  key="pomember.fee"/>
    </th>
    <td align="left">
    	<c:forEach items="${jpoMemberOrder.jpoMemberOrderFee }" var="pof">
    		<c:if test="${pof.feeType=='1' }">
    			<jecs:code listCode="feetype" value="${pof.detailType}"/>:<fmt:formatNumber value="${pof.fee}" type="number" pattern="###,###,##0.00"/><br/>
    		</c:if>
    		<c:if test="${pof.feeType=='2' }">
    			<jecs:code listCode="handle.fee" value="${pof.detailType}"/>:<fmt:formatNumber value="${pof.fee}" type="number" pattern="###,###,##0.00"/><br/>
    		</c:if>
    	</c:forEach>
    </td><th>
        <jecs:label  key="shipping.address"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.address}
    </td></tr>

    <tr><th>
        &nbsp;
    </th>
    <td align="left">
    	&nbsp;
    </td><th>
        <jecs:label  key="shipping.postalcode"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.postalcode}
    </td></tr>

    <tr><th>
        &nbsp;
    </th>
    <td align="left">
    	&nbsp;
    </td><th>
        <jecs:label  key="miMember.phone"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.phone}
    </td></tr>

    <tr><th>
        &nbsp;
    </th>
    <td align="left">
    	&nbsp;
    </td><th>
        <jecs:label  key="miMember.mobiletele"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.mobiletele}
    </td></tr>

    <tr><th>
        &nbsp;
    </th>
    <td align="left">
    	&nbsp;
    </td><th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.email}
    </td></tr>

</table>

<ec:table 
	tableId="piProductListTable"
	items="jpoMemberOrder.jpoMemberOrderList"
	var="pi"
	action="${pageContext.request.contextPath}/editPoMemberFOrder.html"
	width="100%" form = "poMemberOrderForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
    			<ec:column property="jpmProductSale.jpmProduct.productNo" title="busi.product.productno"/>
    			<ec:column property="jpmProductSale.productName" title="pmProduct.productName"/>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn">
    				<fmt:formatNumber value="${pi.price}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="pv" title="busi.direct.pv" styleClass="numberColumn"/>
    			<ec:column property="qty" title="poOrder.count" styleClass="centerColumn">
    			</ec:column>
				</ec:row>
</ec:table>	