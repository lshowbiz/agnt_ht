<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>
<script language="javascript" src="scripts/validate.js"></script>
<c:set var="buttons">

	<c:if test="${ empty viewOrder  }">
			<input type="button" class="button" name="button123" onclick="nextStep(this.form)"  value="<jecs:locale key="button.next"/>" />		
	</c:if>		
				
	<c:if test="${ not empty viewOrder  }">
	<input type="button" class="button" name="button12" onclick="window.location.href='editJpoMemberOrderPayOther.html'"  value="<jecs:locale key="register.perous"/>" />
			
				
				<input type="button" class="button" name="button1" onclick="onSubmitCheck(this.form)"  value="<jecs:locale key="btn.pay.now"/>" />
	</c:if>
</c:set>

<spring:bind path="jpoMemberOrder.*">
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
<script>
function nextStep(theForm){
	
	$('stepNext').value="stepNext";
	theForm.method='get';
			if(isFormPosted()){
				theForm.submit();
			}
}
function onSubmitCheck(theForm){

			if(isFormPosted()){
				theForm.submit();
			}

}
</script>
<form:form commandName="jpoMemberOrder" method="post" onsubmit="return saveSub()" action="editJpoMemberOrderPayOther.html"  id="jpoMemberOrderForm">

<div class="searchBar" >
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="stepNext" id="stepNext" value=""/>

<table class='detail' width="100%">
	<c:if test="${ empty viewOrder  }">
	
	    <tr><th>
	        <jecs:label  key="miMember.memberNo"/>
	    </th>
	    <td align="left">
	    	
	    	
	        <input name="userCode" id="userCode" class="text medium" />
	    </td></tr>
	    
	    <tr><th>
	        <jecs:label  key="poMemberOrder.memberOrderNo"/>
	    </th>
	    <td align="left">
	        <form:input path="memberOrderNo" id="memberOrderNo" cssClass="text medium"/>
	    </td></tr>
	
	</c:if>
	
	<c:if test="${ not empty viewOrder  }">
		
<input type="hidden" name="memberOrderNo" id="memberOrderNo" value="${jpoMemberOrder.memberOrderNo }"/>
<input type="hidden" name="userCode" id="userCode" value="${jpoMemberOrder.sysUser.userCode }"/>
	  
<table class='detail' width="100%">

    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.memberOrderNo }
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.pvAmt }
    </td></tr>

    <tr><th>
        <jecs:label  key="bonus.order.amount"/>
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
        <jecs:label  key="fiPayByCoin.bankbook.1"/>
    </th>
    <td align="left">
    	${coin }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiPayByCoin.bankbook.1.use"/>
    </th>
    <td align="left">
    	<input id="amount" name="amount" type="text" value="0" size="10" onchange="resetOrderAmount()"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="miMember.pwd2" required="true"/>
    </th>
    <td align="left">
        <input name="password" id="password" type="password" value=""/>
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

</table>
</form:form>
<script>
function resetOrderAmount(){
	if($('amount').value < 0 || !isUnsignedNumeric($('amount').value) ){
		$('amount').value = 0;
	}
	if($('amount').value>${coin }){
		$('amount').value = 0;
	}
}
</script>
<script language="javascript">
function saveSub(){

	<c:if test="${ not empty viewOrder  }">
if(${jpoMemberOrder.amount }>parseInt($('amount').value)+${bankbook}){
		alert('<jecs:locale key="error.fiBankbookJournal.balance.not.enough"/>');
		return false;
	}
	</c:if>
		if(!isFormPosted()){
			return false;
		}
	return true;
}
</script>

