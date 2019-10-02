<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPay99billDetail.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta name="decorator" content="mobile"/>
<content tag="heading"><jecs:locale key="jfiPay99billDetail.heading"/></content>
<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/bill99Util.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/Bill99.js'/>" ></script>

<style>
	th {
		text-align: right;
	}
	td {
		text-align: left;
	}
	.ui-checkbox {
		width: 170px;
	}
</style>

<c:if test='${empty param.orderId}'>
<div class="searchBar">
<c:if test="${param.offline == '1' }">
	<input data-inline="true" name="change" type="button" onclick="location.href='jfiPay99bill.html?strAction=fiPay99bill&offline=0'" value="<jecs:locale key="jfiPay99bill.online"/>" />
</c:if>
<c:if test="${param.offline != '1' }">
	<input data-inline="true" name="change" type="button" onclick="location.href='jfiPay99bill.html?strAction=fiPay99bill&offline=1'" value="<jecs:locale key="jfiPay99bill.offline"/>" />
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
<table class='detail' width="100%" id="tbFirst">
    <tr>
    <th align="right">
        <jecs:label  key="jfiPay99bill.payerName"/>
    </th>
    <td align="left">
    	<input id="payerName" name="payerName" type="text" value="${sessionScope.sessionLogin.userName }" size="30"/>
    </td>
    </tr>
    
    <c:if test='${!(empty param.orderId && param.offline == "1")}'>
    <tr>
    <th>
        <jecs:label  key="jfiPay99bill.bankId"/>
    </th>
    <td align="left">
    	<jecs:list listCode="bill99.bank.id" name="bankId" id="bankId" value="ICBC" defaultValue="ICBC"/>
    </td>
    </tr>
    </c:if>
    
    <c:if test='${empty param.orderId && param.offline == "1"}'>
    	<input type="hidden" name="bankId" id="bankId" />
    </c:if>
    
    <tr>
    <th>
        <jecs:label  key="jfiPay99bill.orderAmount"/>
    </th>
    
    <td align="left">
    	<c:if test="${empty jpoMemberOrder}">
    		<input id="orderAmount1" name="orderAmount1" type="text" value="" size="10" onchange="resetOrderAmount()"/>
    	</c:if>
    	
    	<c:if test="${not empty jpoMemberOrder}">
	    	<input id="orderAmount1" name="orderAmount1" type="text" value="${jpoMemberOrder.amount }" 
	    	size="10" readonly="readonly"/><br/>
	    	<label for="bankbook"><jecs:locale key="bankbook.pay"/></label>
	    	<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" 
	    	name="bankbook" value="1" />
    	</c:if>
    	
<c:if test="${not empty jpoMemberOrder}">
	<c:if test="${not empty jpoMemberOrder && (coinPay=='true')}">
		<jecs:power powerCode="jfiPayByCoinAndBankbook">
			<input data-inline="true" name="change" type="button" onclick="location.href='jfiPayByCoinAndBankbook.html?strAction=jfiPayByCoinAndBankbook&orderId=${param.orderId }'" value="<jecs:locale key="jfiPayByCoin.Button"/>" />
		</jecs:power>
	</c:if>
	
	<jecs:power powerCode="jfiPayByJJ">
		<input data-inline="true" name="change" type="button" onclick="location.href='jfiPayByJJ.html?strAction=jfiPayByJJ&orderId=${param.orderId }'" value="<jecs:locale key="jfiPayByJJ.Button"/>" />
	</jecs:power>
</c:if>

    </td>
   </tr>
   
    <tr>
    <th>
        <jecs:label  key="poOrder.handlingUSFee"/>
    </th>
    <td align="left">
    	<%=com.joymain.jecs.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;
    </td>
    </tr>
    
    <%--tr><th>
        <jecs:label  key="jfiPay99bill.payerContact"/>
    </th>
    <td align="left">
    	<input id="payerContact" name="payerContact" type="text" value="" size="30"/>
    </td></tr--%>
    <input type="hidden" id="payerContact" name="payerContact" value=""/>
    
	<tr>
	<th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
		<input data-inline="true" type="button" name="cancel" onclick="actionPost();" value="<jecs:locale key="operation.button.save"/>" />
		<input data-inline="true" type="button" name="cancel" onclick="history.back(-1);" value="<jecs:locale key="operation.button.cancel"/>" />
    </td>
    </tr>

<!-- </table> -->
</form>
<c:if test="${not empty jpoMemberOrder }">
<!-- <table class='detail' width="100%" id="tbSecond"> -->

  <tr>
    <th align="right">
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.memberOrderNo }
    </td>
   </tr>
   
   <tr>
    <th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    </td>
  </tr>
   
   <tr>
    <th>
        <jecs:label  key="shipping.firstName"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.firstName}
    </td>
   </tr>

  <tr>
    <th>
        <jecs:label  key="shipping.lastName"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.lastName}
    </td>
  </tr>

   <tr>
    <th>
        <jecs:label  key="bonus.order.amount"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.amount }
    </td>
   </tr>
   
   <tr>
    <th>
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.pvAmt }
    </td>
   </tr>
   
   <tr>
    <th>
        <jecs:label  key="shipping.province"/>
    </th>
    <td align="left">
		<c:forEach items="${alStateProvinces }" var="al">
		   	<c:if test="${al.stateProvinceId==jpoMemberOrder.province }">
		   		<c:out value="${al.stateProvinceName}" /> 
		   	</c:if>
 		</c:forEach>
    </td>
   </tr>

  <tr>
    <th>
        <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.city}
    </td>
  </tr>

  <%-- <tr>
    <th>
    	&nbsp;
        <jecs:label  key="busi.order.payMode"/>
    </th>
    <td align="left">
    	&nbsp;
    	<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    </td>
  </tr> --%>
   
   <tr> 
    <th>
        <jecs:label  key="shipping.district"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.district}
    </td>
   </tr>
    
   <tr>
    <th>
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
    </td>
   </tr>
    
   <tr> 
    <th>
        <jecs:label  key="shipping.address"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.address}
    </td>
   </tr>

  <tr>
    <th>
        <jecs:label  key="shipping.postalcode"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.postalcode}
    </td>
  </tr>
   
   <tr> 
    <th>
        <jecs:label  key="miMember.phone"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.phone}
    </td>
  </tr>

   <tr> 
    <th>
        <jecs:label  key="miMember.mobiletele"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.mobiletele}
    </td>
  </tr>
 
 <tr>   
    <th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.email}
    </td>
</tr>
</table>

<ec:table 
	tableId="piProductListTable"
	items="jpoMemberOrder.jpoMemberOrderList"
	var="pi"
	action="${pageContext.request.contextPath}/editPoMemberFOrder.html"
	width="100%" form = "poMemberOrderForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
    			<%-- <ec:column property="jpmProductSale.jpmProduct.productNo" title="busi.product.productno"/>
    			<ec:column property="jpmProductSale.productName" title="pmProduct.productName"/> --%>
    			<ec:column property="jpmProductSale.productName" title="商品">
    				${pi.jpmProductSale.productName}<br />
    				${pi.jpmProductSale.jpmProduct.productNo}
    			</ec:column>
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
				var bill99 =  new Bill99();
				bill99.payerName = $('payerName').value;
				bill99.bankId = $('bankId').value;
				bill99.orderAmount = $('orderAmount1').value;
				bill99.payerContact = $('payerContact').value;
				bill99.ext2 = '${sessionLogin.userCode }';
				bill99.orderId = '${param.orderId}';
				bill99Util.getBill99(bill99,${flag},callBack);
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
			var bill99 =  new Bill99();
			bill99.payerName = $('payerName').value;
			bill99.bankId = $('bankId').value;
			bill99.orderAmount = $('orderAmount1').value;
			bill99.payerContact = $('payerContact').value;
			bill99.ext2 = '${sessionLogin.userCode }';
			bill99.orderId = '${param.orderId}';
			bill99Util.getBill99(bill99,${flag},callBack);
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