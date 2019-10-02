
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayTenpayDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiPayTenpayDetail.heading"/></content>
<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/tenpayUtil.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/Tenpay.js'/>" ></script>

<form id="tenpayForm" method="POST" action="" target="_blank">
</form>
<form id="jfiPayTenpay" method="GET" action="" onsubmit="return validateOthers(this);">
<table class='detail' width="100%">
			
    <tr><th>
        <jecs:label  key="jfiPay99bill.bankId"/>
    </th>
    <td align="left">
    	<jecs:list listCode="tenpay.bank.id" name="bankId" id="bankId" value="1037" defaultValue="1037"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiPay99bill.orderAmount"/>
    </th>
    <td align="left">
    	<c:if test="${empty jpoMemberOrder}">
    	<input id="orderAmount1" name="orderAmount1" type="text" value="" size="10" onchange="resetOrderAmount()"/>
    	</c:if>
    	<c:if test="${not empty jpoMemberOrder}">
    	<input id="orderAmount1" name="orderAmount1" type="text" value="${jpoMemberOrder.amount }" size="10" readonly="readonly"/>
    	<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1" /><jecs:locale key="bankbook.pay"/>
    	</c:if>
    </td></tr>
    
	<tr><th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
		<input type="button" class="button" name="cancel" onclick="actionPost();" value="<jecs:locale key="operation.button.save"/>" />
		<input type="button" class="button" name="cancel" onclick="history.back(-1);" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>

</table>
</form>
<c:if test="${not empty jpoMemberOrder }">
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
    	<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
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
</c:if>
<script>
function resetOrderAmount(){
	if($('orderAmount1').value < 0 || !isUnsignedNumeric($('orderAmount1').value) ){
		$('orderAmount1').value = 0;
	}
	if($('bankbook') == undefined){
		
	}else{
		if($('bankbook').checked==true){
			var orderAmount = ${bankbook - jpoMemberOrder.amount };
			if(orderAmount < 0 ){
				$('orderAmount1').value = -orderAmount;
			}else{
				$('orderAmount1').value = '0';
			}
		}else{
			$('orderAmount1').value = '${jpoMemberOrder.amount }';
		}
	}
}
function actionPost(){
	if($('orderAmount1').value < 0 || !isUnsignedNumeric($('orderAmount1').value) ){
		$('orderAmount1').value = 0;
		return ;
	}
	<c:if test='${empty param.orderId}'>
		if($('orderAmount1').value>0){
			if(isFormPosted()){
				var tenpay =  new Tenpay();
				tenpay.bank_type = $('bankId').value;
				tenpay.total_fee = $('orderAmount1').value;
				tenpay.attach = '${sessionLogin.userCode }';
			tenpay.spbill_create_ip = '<%=request.getRemoteAddr()%>';
				tenpayUtil.getTenpay(tenpay,${flag},callBack);
			}
		}
	</c:if>
	<c:if test='${not empty param.orderId}'>
	if(isFormPosted()){
		if($('orderAmount1').value==0){
			<c:if test='${not empty param.orderId}'>
				window.location.href = 'jfiPay99billReceive.html?orderId=${param.orderId}&isAllPay=1';
			</c:if>
		}else{
			var tenpay =  new Tenpay();
			tenpay.bank_type = $('bankId').value;
			tenpay.total_fee = $('orderAmount1').value;
			tenpay.attach = '${sessionLogin.userCode }';
			tenpay.sp_billno = '${param.orderId}';
			tenpay.spbill_create_ip = '<%=request.getRemoteAddr()%>';
			tenpayUtil.getTenpay(tenpay,${flag},callBack);
		}
	}
	</c:if>
}
function callBack(valid){
	if(valid==null){
		alert('ERROR!');
	}else{
		var url = valid.postUrl;
		tenpayForm.action = url;
		tenpayForm.submit();
	}
}
</script>