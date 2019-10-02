<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>

<c:set var="buttons">

<button type="button" class="btn btn_sele" name="back"  onclick="history.back(-1);" >
	<jecs:locale key="operation.button.return"/>
</button>
<!--
<jecs:power powerCode="printPhInvoice">
<c:if test="${jpoMemberOrder.status==2 }">
<input type="button" class="button" name="print"  onclick="window.open('jpoMemberOrderPHInvoice.html?moId=${jpoMemberOrder.moId }');" value="<jecs:locale key="button.print"/>" />
</c:if>
</jecs:power>
-->
</c:set>

<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.memberOrderNo }
    </span></td>

<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    <tr><th><span class="text-font-style-tc">
        Transaction Number
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.transactionNumber }
    </span></td></tr>
</c:if>

    <th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.orderType"/>
    </span></th>
    <td><span class="textbox">
    	<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bonus.order.amount"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.amount }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.discountAmount"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.discountAmount }
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="fiPayByCoin.bankbook.1"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.jjAmount }
    </span></td>
    
    <th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.pvAmt }
    </span></td></tr>

<%--
    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="busi.order.payMode"/>
    </span></th>
    <td><span class="textbox">
    	<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    </span></td></tr>
--%>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="pomember.fee"/>
    </span></th>
    <td><span class="textbox">
    	<c:forEach items="${jpoMemberOrder.jpoMemberOrderFee }" var="pof">
    		<c:if test="${pof.feeType=='1' }">
    			<jecs:code listCode="feetype" value="${pof.detailType}"/>:<fmt:formatNumber value="${pof.fee}" type="number" /><br/>
    		</c:if>
    		<c:if test="${pof.feeType=='2' }">
    			<jecs:code listCode="handle.fee" value="${pof.detailType}"/>:<fmt:formatNumber value="${pof.fee}" type="number" /><br/>
    		</c:if>
    		<c:if test="${pof.feeType=='3' }">
    			<jecs:label  key="poOrder.saletaxUSFee"/><fmt:formatNumber value="${pof.fee}" type="number"/><br/>
    		</c:if>
    	</c:forEach>
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="po.shipment.type"/>
    </span></th>
    <td><span class="textbox">
    	<jecs:code listCode="po.shipment.type" value="${jpoMemberOrder.pickup}"/>
    </span></td></tr>

<c:if test="${jpoMemberOrder.pickup!='1' }">
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.firstName"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.firstName}
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="shipping.lastName"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.lastName}
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.island" required="true"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.province" required="true"/>
	    </c:if>
    </span></th>
    <td><span class="textbox">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoMemberOrder.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
    </span></td>

	<th><span class="text-font-style-tc">
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.region"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.city" required="true"/>
	    </c:if>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.city}
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.province"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.district"/>
	    </c:if>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.district}
    </span></td>

    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">

    <tr><th><span class="text-font-style-tc">
	       <jecs:label  key="shipping.city"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.town}
    </span></td></tr>
    </c:if>

    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.address"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.address}
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.building"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.building}
    </span></td>
    
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.postalcode"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.postalcode}
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="miMember.phone"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.phone}
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="miMember.mobiletele"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.mobiletele}
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="miMember.email"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.email}
    </span></td>
    
    <!-- 2015-10-20 modify fu  After the release of this part of the code WMS -->
    <!--   
    <th><span class="text-font-style-tc">
        <jecs:label  key="po.deliveryStatus"/>
    </span></th>
    <td><span class="textbox">
    	<c:if test="${jpoMemberOrder.interOkDelivery=='0' || empty jpoMemberOrder.interOkDelivery}">
	        <jecs:locale  key="pd.sendInfoNoSend"/>
	    </c:if>
	    <c:if test="${jpoMemberOrder.interOkDelivery=='1' }">
	        <jecs:locale  key="pd.partialShipment"/>
	    </c:if>
	    <c:if test="${jpoMemberOrder.interOkDelivery=='2'}">
	        <jecs:locale  key="pdSendInfo.orderFlag.list2"/>
	    </c:if>
	    <c:if test="${jpoMemberOrder.interOkDelivery=='3'}">
	        <jecs:locale  key="pdSendInfo.orderFlag.list3"/>
	    </c:if>
    </span></td>
     -->
    </tr>
    
    
</c:if>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="fiPayData.createrName"/>
    </span></th>
    <td><span class="textbox">
      <font size="1">
        <c:if test="${sessionLogin.userType=='C' }">${jpoMemberOrder.orderUserCode } - </c:if><fmt:formatDate value="${jpoMemberOrder.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
      </font>
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="logType.BC"/>
    </span></th>
    <td><span class="textbox">
    <font size="1">
        <c:if test="${sessionLogin.userType=='C' }">${jpoMemberOrder.checkUserCode } - </c:if><fmt:formatDate value="${jpoMemberOrder.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    </font>
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="logType.C"/>
    </span></th>
    <td><span class="textbox">
    <font size="1">
        <c:if test="${sessionLogin.userType=='C' }">${jpoMemberOrder.checkDateUserCode } - </c:if><fmt:formatDate value="${jpoMemberOrder.checkDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
    </font>
    </span></td></tr>
    
    <tr>
		<td class="command" align="center" colspan="4">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>

</tbody>
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
	width="70%" form = "poMemberOrderForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif" footer="${footTotalVar}" styleClass="detail">
				<ec:row>
    			<ec:column property="jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo" title="busi.product.productno"/>
    			<ec:column property="jpmProductSaleTeamType.jpmProductSaleNew.productName" title="pmProduct.productName"/>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn">
    				<fmt:formatNumber value="${pi.price}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="pv" title="busi.direct.pv" styleClass="numberColumn"/>
    			<ec:column property="qty" title="poOrder.count" styleClass="centerColumn"></ec:column>
    			
				</ec:row>
</ec:table>	
<script>
     function ggetUrl(trackingSingle,memberOrderNo){
	   		window.open("<c:url value='/mailStatuss.html'/>?strAction=logisticsDo&jpoMemberList=jpoMemberList&logisticsDo="+trackingSingle+"&memberOrderNo="+memberOrderNo,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     }
</script>