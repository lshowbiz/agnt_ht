<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunMemberOrderDetail.heading"/></content>

<c:set var="buttons">

<input type="button" class="button" name="back"  onclick="history.back(-1);" value="<jecs:locale key="operation.button.return"/>" />

</c:set>

<table class='detail' width="100%">

    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.memberOrderNo }
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.all.order_type" value="${jfiSunMemberOrder.orderType}"/>
    </td></tr><!--

    <tr><th>
        <jecs:label  key="bonus.order.amount"/>
    </th>
    <td align="left">
        ${jfiSunMemberOrder.amount }
    </td></tr>

    --><tr><th>
        <jecs:label  key="shipping.province"/>
    </th>
    <td align="left">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jfiSunMemberOrder.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.city}
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.district"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.district}
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.address"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.address}
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.postalcode"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.postalcode}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.phone"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.phone}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.mobiletele"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.mobiletele}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
    	${jfiSunMemberOrder.email}
    </td></tr>
    
    <tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>

</table>

<ec:table 
	tableId="piProductListTable"
	items="jfiSunMemberOrder.jfiSunMemberOrderList"
	var="pi"
	action="${pageContext.request.contextPath}/editPoMemberFOrder.html"
	width="100%" form = "poMemberOrderForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
    			<ec:column property="jpmProductSale.productName" title="pmProduct.productName"/>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn">
    				<fmt:formatNumber value="${pi.price}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="qty" title="poOrder.count" styleClass="centerColumn">
    			</ec:column>
				</ec:row>
</ec:table>	
