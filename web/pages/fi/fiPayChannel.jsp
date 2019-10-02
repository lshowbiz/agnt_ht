
<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="jfiPay99billDetail.title"/></title>
<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/channelUtil.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/ChannelBean.js'/>" ></script>

<form id="fiPayChannel" method="post" action="" target="_blank" onsubmit="return validateOthers(this);">
			<input type="hidden" id="Name" name="Name" value=""/>
			<input type="hidden" id="Version" name="Version" value=""/>
			<input type="hidden" id="Charset" name="Charset" value=""/>
			<input type="hidden" id="MsgSender" name="MsgSender" value=""/>
			<input type="hidden" id="SendTime" name="SendTime" value=""/>
			<input type="hidden" id="OrderNo" name="OrderNo" value=""/>
			<input type="hidden" id="OrderAmount" name="OrderAmount" value=""/>
			<input type="hidden" id="OrderTime" name="OrderTime" value=""/>
			<input type="hidden" id="PayType" name="PayType" value=""/>
			<input type="hidden" id="PayChannel" name="PayChannel" value=""/>
			<input type="hidden" id="InstCode" name="InstCode" value=""/>
			<input type="hidden" id="PageUrl" name="PageUrl" value=""/>
			<input type="hidden" id="BackUrl " name="BackUrl " value=""/>
			<input type="hidden" id="NotifyUrl" name="NotifyUrl" value=""/>
			<input type="hidden" id="ProductName" name="ProductName" value=""/>
			<input type="hidden" id="BuyerContact" name="BuyerContact" value=""/>
			<input type="hidden" id="BuyerIp" name="BuyerIp" value=""/>
			<input type="hidden" id="Ext1" name="Ext1" value=""/>
			<input type="hidden" id="SignType" name="SignType" value=""/>
			<input type="hidden" id="SignMsg" name="SignMsg" value=""/>
	
<table class='detail' width="100%">
    <tr><th>
        <jecs:label  key="jfiPay99bill.payerName"/>
    </th>
    <td align="left">
    	<input id="payerName" name="payerName" type="text" value="${sessionScope.sessionLogin.userName }" size="30"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="jfiPay99bill.bankId"/>
    </th>
    <td align="left">
    	<jecs:list listCode="bill99.bank.id" name="bankId" id="bankId" value="ICBC" defaultValue="ICBC"/>
    	
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
    	<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1" /><jecs:locale key="bankbook.pay"/>(<jecs:locale key="bankbook.balance"/>:${bankbook })
		
		<jecs:power powerCode="fiPayByJJ">
			<input name="change" class="button" type="button" onclick="location.href='fiPayByJJ.html?strAction=fiPayByJJ&orderId=${param.orderId }'" value="<jecs:locale key="jfiPayByJJ.Button"/>" />
		</jecs:power>
		
			<c:if test="${coinPay=='true'}">

				<input name="change" class="button" type="button" onclick="location.href='jfiPayByCoinAndBankbook.html?&orderId=${param.orderId }'" value="<jecs:locale key="jfiPayByCoin.Button"/>" />

			</c:if>
    	</c:if>
    </td></tr>
    
    <input type="hidden" id="payerContact" name="payerContact" value=""/>
    
	<tr><th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
		<input type="button" class="button" name="cancel" onclick="actionPost();" value="<fmt:message key="operation.button.save"/>" />
		<input type="button" class="button" name="cancel" onclick="history.back(-1);" value="<fmt:message key="operation.button.cancel"/>" />
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
        &nbsp;
    </th>
    <td align="left">
        &nbsp;
    </td><th>
        <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.city}
    </td></tr>

    <tr><th>
    	&nbsp;
    </th>
    <td align="left">
    	&nbsp;
    </td><th>
        <jecs:label  key="shipping.district"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.district}
    </td></tr>

    <tr><th>
    	&nbsp;
    </th>
    <td align="left">
    	&nbsp;
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
	showPagination="false" sortable="false" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row>
    			<ec:column property="jpmProductSale.jpmProduct.productNo" title="busi.product.productno"/>
    			<ec:column property="jpmProductSale.productName" title="pmProduct.productName"/>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn">
    				<fmt:formatNumber value="${pi.price}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
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
		return;
	}
	
	<c:if test='${empty jpoMemberOrder}'>
		if($('orderAmount1').value>0){
			if(isFormPosted()){
				
				var channelBean = new ChannelBean();
				
				channelBean.instCode = $('bankId').value;
				channelBean.orderAmount = $('orderAmount1').value;
				channelBean.buyerContact = $('payerContact').value;
				channelBean.ext1 = '${sessionLogin.userCode}';
				//channelBean.orderNo = '${param.orderId}';
				
				channelUtil.getChannelBean(channelBean,${flag},callBack);
			}
		}
	</c:if>
	<c:if test='${not empty jpoMemberOrder}'>
	if(isFormPosted()){
		if($('orderAmount1').value==0){
			<c:if test='${not empty param.orderId}'>
				window.location.href = 'fiPay99billmsReceive.html?orderId=${param.orderId}&isAllPay=1';
			</c:if>
		}else{
				var channel = new ChannelBean();

				channel.instCode = $('bankId').value;
				channel.orderAmount = $('orderAmount1').value;
				channel.buyerContact = $('payerContact').value;
				channel.ext1 = '${sessionLogin.userCode }';
				channel.orderNo = '${param.orderId}';
				
				channelUtil.getChannelBean(channel,${flag},callBack);
		}
	}
	</c:if>
}
function callBack(valid){
	if(valid==null){
		alert('ERROR!');
	}else{
	
		$('Name').value = valid.name;
		$('Version').value = valid.version;
		$('Charset').value = valid.charset;
		$('MsgSender').value = valid.msgSender;
		$('SendTime').value = valid.sendTime;
		$('OrderNo').value = valid.orderNo;
		$('OrderAmount').value = valid.orderAmount;
		$('OrderTime').value = valid.orderTime;
		$('PayType').value = valid.payType;
		$('PayChannel').value = valid.payChannel;
		$('InstCode').value = valid.instCode;
		$('PageUrl').value = valid.pageUrl;
		//$('BackUrl').value = valid.backUrl;
		$('NotifyUrl').value = valid.notifyUrl;
		$('ProductName').value = valid.productName;
		$('BuyerContact').value = valid.buyerContact;
		$('BuyerIp').value = valid.buyerIp;
		$('Ext1').value = valid.ext1;
		$('SignType').value = valid.signType;
		$('SignMsg').value = valid.signMsg;
		
		$('fiPayChannel').action = valid.postUrl;
		$('fiPayChannel').target="_blank";
		$('fiPayChannel').submit();
	}
}
</script>