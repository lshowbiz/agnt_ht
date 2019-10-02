
<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="jfiPay99billDetail.title"/></title>
<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/bill99Util.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/Bill99.js'/>" ></script>
<c:if test='${empty param.orderId}'>
<div class="searchBar">
<c:if test="${param.offline == '1' }">
	<input name="change" class="button" type="button" onclick="location.href='jfiPay99bill.html?strAction=fiPay99bill&offline=0'" value="<jecs:locale key="jfiPay99bill.online"/>" />
</c:if>
<c:if test="${param.offline != '1' }">
	<input name="change" class="button" type="button" onclick="location.href='jfiPay99bill.html?strAction=fiPay99bill&offline=1'" value="<jecs:locale key="jfiPay99bill.offline"/>" />
</c:if>
</div>
</c:if>

<form id="jfiPay99bill" method="GET" action="" <c:if test='${empty param.orderId && param.offline == "1"}'>target="_blank" </c:if> onsubmit="return validateOthers(this);">
			<input type="hidden" id="inputCharset" name="inputCharset" value=""/>
			<input type="hidden" id="bgUrl" name="bgUrl" value=""/>
			<input type="hidden" id="pageUrl" name="pageUrl" value=""/>
			<input type="hidden" id="version" name="version" value=""/>
			<input type="hidden" id="language" name="language" value=""/>
			<input type="hidden" id="signType" name="signType" value=""/>
			<input type="hidden" id="signMsg" name="signMsg" value=""/>
			<input type="hidden" id="merchantAcctId" name="merchantAcctId" value=""/>
			<input type="hidden" id="payerContactType" name="payerContactType" value=""/>
			<input type="hidden" id="orderId" name="orderId" value=""/>
			<input type="hidden" id="orderTime" name="orderTime" value=""/>
			<input type="hidden" id="productName" name="productName" value=""/>
			<input type="hidden" id="productNum" name="productNum" value=""/>
			<input type="hidden" id="productId" name="productId" value=""/>
			<input type="hidden" id="productDesc" name="productDesc" value=""/>
			<input type="hidden" id="ext1" name="ext1" value=""/>
			<input type="hidden" id="ext2" name="ext2" value=""/>
			<input type="hidden" id="payType" name="payType" value=""/>
			<input type="hidden" id="redoFlag" name="redoFlag" value=""/>
			<input type="hidden" id="pid" name="pid" value=""/>
			<input type="hidden" id="orderAmount" name="orderAmount" value=""/>
<table class='detail' width="100%">

	<tr><th>
        <jecs:label  key="jfoundationItem.name"/>
    </th>
    <td align="left">
    	${foundationOrder.foundationItem.name }
    </td>
    </tr>
    
    <tr>
    <th>
        <jecs:label  key="foundationItem.remark"/>
    </th>
    
    <td align="left">
    	${foundationOrder.foundationItem.remark}
    </td></tr>
    <tr><th>
        <jecs:label  key="jfiPay99bill.payerName"/>
    </th>
    <td align="left">
    	${sessionScope.sessionLogin.userName }<input id="payerName" name="payerName" type="hidden" value="${sessionScope.sessionLogin.userName }" size="30"/>
    </td></tr>
    <c:if test='${!(empty param.orderId && param.offline == "1")}'>
    <tr><th>
        <jecs:label  key="jfiPay99bill.bankId"/>
    </th>
    <td align="left">
    	<jecs:list listCode="bill99.bank.id" name="bankId" id="bankId" value="ICBC" defaultValue="ICBC"/>
    </td></tr>
    </c:if>
    <c:if test='${empty param.orderId && param.offline == "1"}'>
    	<input type="hidden" name="bankId" id="bankId" />
    </c:if>
    <tr><th>
        <jecs:label  key="jfiPay99bill.orderAmount"/>
    </th>
    <td align="left">

    	<c:if test="${not empty foundationOrder}">
    		<input id="orderAmount1" name="orderAmount1" type="text" value="${foundationOrder.amount }" size="10" readonly="readonly"/>
    		<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1" /><jecs:locale key="bankbook.pay"/>
    		 (<jecs:label  key="bankbook.balance"/>${bankbook })
    	</c:if>

    </td></tr>
    <tr><th>
        <jecs:label  key="poOrder.handlingUSFee"/>
    </th>
    <td align="left">
    	<%=com.joymain.jecs.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;
    </td></tr>
    
    <%--tr><th>
        <jecs:label  key="jfiPay99bill.payerContact"/>
    </th>
    <td align="left">
    	<input id="payerContact" name="payerContact" type="text" value="" size="30"/>
    </td></tr--%>
    <input type="hidden" id="payerContact" name="payerContact" value=""/>
    
	<tr><th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
		<input type="button" class="button" name="cancel" onclick="actionPost();" value="<fmt:message key="operation.button.pay"/>" />
		<input type="button" class="button" name="cancel" onclick="history.back(-1);" value="<fmt:message key="operation.button.cancel"/>" />
    </td></tr>

</table>
</form>

<script>
function resetOrderAmount(){
	if($('orderAmount1').value < 0 || !isUnsignedNumeric($('orderAmount1').value) ){
		$('orderAmount1').value = 0;
	}
	if($('bankbook') == undefined){
		
	}else{
		if($('bankbook').checked==true){
			var orderAmount = ${bankbook - foundationOrder.amount };
			if(orderAmount < 0 ){
				$('orderAmount1').value = -orderAmount;
			}else{
				$('orderAmount1').value = '0';
			}
		}else{
			$('orderAmount1').value = '${foundationOrder.amount }';
		}
	}
}
function actionPost(){
	if($('orderAmount1').value < 0 || !isUnsignedNumeric($('orderAmount1').value) ){
		$('orderAmount1').value = 0;
		return ;
	}
	
	<c:if test='${not empty param.orderId}'>
	if(isFormPosted()){
		if($('orderAmount1').value==0){
			<c:if test='${not empty param.orderId}'>
				window.location.href = 'jfiPayFoundationReceive.html?orderId=${param.orderId}&isAllPay=1';
			</c:if>
		}else{
			var bill99 =  new Bill99();
			bill99.payerName = $('payerName').value;
			bill99.bankId = $('bankId').value;
			bill99.orderAmount = $('orderAmount1').value;
			bill99.payerContact = $('payerContact').value;
			bill99.ext2 = '${sessionLogin.userCode }';
			bill99.orderId = '${param.orderId}';
			
			bill99Util.getObjectBill99(bill99,2,callBack);
		}
	}
	</c:if>
}
function callBack(valid){

	if(valid==null){
		alert('ERROR!');
	}else{
	
		$('inputCharset').value = valid.inputCharset;
		$('bgUrl').value = valid.bgUrl;
		$('pageUrl').value = valid.pageUrl;
		$('version').value = valid.version;
		$('language').value = valid.language;
		$('signType').value = valid.signType;
		$('signMsg').value = valid.signMsg;
		$('merchantAcctId').value = valid.merchantAcctId;
		$('payerContactType').value = valid.payerContactType;
		$('orderId').value = valid.orderId;
		$('orderTime').value = valid.orderTime;
		$('productName').value = valid.productName;
		$('productNum').value = valid.productNum;
		$('productId').value = valid.productId;
		$('productDesc').value = valid.productDesc;
		$('ext1').value = valid.ext1;
		$('ext2').value = valid.ext2;
		$('payType').value = valid.payType;
		$('redoFlag').value = valid.redoFlag;
		$('pid').value = valid.pid;
		$('orderAmount').value = valid.orderAmount;
		$('jfiPay99bill').action = valid.postUrl;
		$('jfiPay99bill').target="_blank";
		$('jfiPay99bill').submit();
	}
}
</script>