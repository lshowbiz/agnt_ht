<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta name="decorator" content="mobile"/>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>

<style>
	#tableId th {
		text-align: right;
	}
</style>

<c:set var="buttons">

<input data-inline="true" type="button" class="button" name="back"  onclick="history.back(-1);" value="<jecs:locale key="operation.button.return"/>" />
<jecs:power powerCode="printPhInvoice">
<c:if test="${jpoMemberOrder.status==2 }">
<input data-inline="true" type="button" class="button" name="print"  onclick="window.open('jpoMemberOrderPHInvoice.html?moId=${jpoMemberOrder.moId }');" value="<jecs:locale key="button.print"/>" />
</c:if>
</jecs:power>
</c:set>
<table id="tableId" class='detail' width="320px">

    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.memberOrderNo }
    </td></tr>

<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    <tr><th>
        Transaction Number
    </th>
    <td align="left">
    	${jpoMemberOrder.transactionNumber }
    </td></tr>
</c:if>

    <tr><th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="bonus.order.amount"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.amount }
    </td></tr>

    <tr><th>
        <%-- <jecs:label  key="poMemberOrder.discountAmount"/> --%>
        佣金:
    </th>
    <td align="left">
        ${jpoMemberOrder.discountAmount }
    </td></tr>

    <tr><th>
        <%-- <jecs:label  key="fiPayByCoin.bankbook.1"/> --%>
        银行存折:
    </th>
    <td align="left">
        ${jpoMemberOrder.jjAmount }
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.pvAmt }
    </td></tr>

<%--
    <tr><th>
        <jecs:label  key="busi.order.payMode"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    </td></tr>
--%>
    
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
    		<c:if test="${pof.feeType=='3' }">
    			<jecs:label  key="poOrder.saletaxUSFee"/><fmt:formatNumber value="${pof.fee}" type="number" pattern="###,###,##0.00"/><br/>
    		</c:if>
    	</c:forEach>
    </td></tr>

    <tr><th>
        <jecs:label  key="po.shipment.type"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.shipment.type" value="${jpoMemberOrder.pickup}"/>
    </td></tr>

<c:if test="${jpoMemberOrder.pickup!='1' }">
    <tr><th>
        <jecs:label  key="shipping.firstName"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.firstName}
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.lastName"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.lastName}
    </td></tr>

    <tr><th>
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.island" required="true"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.province" required="true"/>
	    </c:if>
    </th>
    <td align="left">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoMemberOrder.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
    </td></tr>

    <tr><th>
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.region"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.city" required="true"/>
	    </c:if>
    </th>
    <td align="left">
    	${jpoMemberOrder.city}
    </td></tr>

    <tr><th>
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.province"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.district"/>
	    </c:if>
    </th>
    <td align="left">
    	${jpoMemberOrder.district}
    </td></tr>

    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">

    <tr><th>
	       <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.town}
    </td></tr>
    </c:if>

    <tr><th>
        <jecs:label  key="shipping.address"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.address}
    </td></tr>

    <tr><th>
        <%-- <jecs:label  key="shipping.building"/> --%>
        building:
    </th>
    <td align="left">
    	${jpoMemberOrder.building}
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.postalcode"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.postalcode}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.phone"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.phone}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.mobiletele"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.mobiletele}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.email}
    </td></tr>
</c:if>

    <tr><th>
        <jecs:label  key="fiPayData.createrName"/>
    </th>
    <td align="left">
        <c:if test="${sessionLogin.userType=='C' }">${jpoMemberOrder.orderUserCode } - </c:if><fmt:formatDate value="${jpoMemberOrder.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="logType.BC"/>
    </th>
    <td align="left">
        <c:if test="${sessionLogin.userType=='C' }">${jpoMemberOrder.checkUserCode } - </c:if><fmt:formatDate value="${jpoMemberOrder.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="logType.C"/>
    </th>
    <td align="left">
        <c:if test="${sessionLogin.userType=='C' }">${jpoMemberOrder.checkDateUserCode } - </c:if><fmt:formatDate value="${jpoMemberOrder.checkDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
    </td></tr>
    
   <%--  <tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr> --%>

</table>

<c:set var="pv_amt" value="0"/>
<c:set var="amount_amt" value="0"/>
<c:set var="footTotalVar">

<c:forEach items="${jpoMemberOrder.jpoMemberOrderList }" var="jl">
	<c:set var="pv_amt" value="${pv_amt + jl.pv * jl.qty }"/>
	<c:set var="amount_amt" value="${amount_amt + jl.price * jl.qty }"/>
</c:forEach>
<tr>
	<td align="right" class="footer">&nbsp;</td>
	<td align="right" class="footer"><jecs:locale key="report.allTotal"/></td>
	<td class="footer">
		${amount_amt }
	</td>
	<td class="footer">
		${pv_amt }
	</td>
	<td align="right" class="footer">&nbsp;</td>
</tr>
</c:set>

<ec:table 
	tableId="piProductListTable"
	items="jpoMemberOrder.jpoMemberOrderList"
	var="pi"
	action="${pageContext.request.contextPath}/editPoMemberFOrder.html"
	width="100%" form = "poMemberOrderForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
				<ec:row>
    			<ec:column property="productA" title="商品">
    				${pi.jpmProductSale.jpmProduct.productNo}<br />
    				${pi.jpmProductSale.productName}
    			</ec:column>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn">
    				<fmt:formatNumber value="${pi.price}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="pv" title="busi.direct.pv" styleClass="numberColumn"/>
    			<ec:column property="qty" title="poOrder.count" styleClass="centerColumn">
    			</ec:column>
				</ec:row>
</ec:table>	
<c:out value="${buttons}" escapeXml="false"/>

<%-- <ec:column property="jpmProductSale.jpmProduct.productNo" title="busi.product.productno"/>
<ec:column property="jpmProductSale.productName" title="pmProduct.productName"/> --%>