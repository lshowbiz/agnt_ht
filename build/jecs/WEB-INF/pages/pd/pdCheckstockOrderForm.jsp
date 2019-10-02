<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdCheckstockOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdCheckstockOrderDetail.heading"/></content>

<c:set var="buttons">

	
		
		
		
		  	<c:choose>
  <c:when test="${param.strAction=='addPdCheckstockOrder'}">
			<input type="button" class="button" style="margin-right: 5px"
        onclick="handPdAdjustStock('add');"
        value="<jecs:locale  key='operate.common.start'/>"/>
		</c:when>
		<c:when test="${(param.strAction=='editPdCheckstockOrder')&&(pdAdjustStock.orderFlag<=1)}">
				<input type="button" class="button" style="margin-right: 5px"
        onclick="handPdAdjustStock('edit');"
        value="<jecs:locale  key='operation.button.save'/>"/>
        	<input type="button" class="button" name="save" 
        	 onclick="savePdAdjustStock()" value="<jecs:locale key="operation.button.save"/>" />
        	<input type="submit" class="button" name="delete" 
        		onclick="$('strAction').value='deletePdAdjustStock',bCancel=true;return confirmDelete('PdAdjustStock')" 
        		value="<jecs:locale key="operation.button.delete"/>" />
        			
        	
		</c:when>
		
		<c:when test="${(param.strAction=='confirmPdCheckstockOrder')&&(pdAdjustStock.orderFlag==1)}">
				<input type="button" class="button" style="margin-right: 5px"
        onclick="handPdAdjustStock('confirm');"
        value="<jecs:locale  key='operate.common.end'/>"/>
		</c:when>
	</c:choose>
	
	
	
	
</c:set>

<spring:bind path="pdCheckstockOrder.*">
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

<form:form commandName="pdCheckstockOrder" method="post" action="editPdCheckstockOrder.html" onsubmit="return validatePdCheckstockOrder(this)" id="pdCheckstockOrderForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdCheckstockOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="pcoNo"/>

    

    <tr><th>
        <jecs:label  key="busi.warehouse.warehouseno"/>
    </th>
    <td align="left">
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <form:input path="warehouseNo" disabled="${disabledFlag}" id="warehouseNo"  cssClass="text medium"/>
    		<input type="button" class="button" name="select" <c:if test="${disabledFlag}">disabled="true"</c:if> onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
    </td></tr>

     

    

    <tr><th>
        <jecs:label  key="pdCheckstockOrder.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

  

    
    <tr><th>
        <jecs:label  key="pdCheckstockOrder.checkRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="checkRemark" cssClass="fieldError"/-->
        <form:input path="checkRemark" id="checkRemark" cssClass="text medium"/>
    </td></tr>

    
    <tr><th>
        <jecs:label  key="pdCheckstockOrder.okRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="okRemark" cssClass="fieldError"/-->
        <form:input path="okRemark" id="okRemark" cssClass="text medium"/>
    </td></tr>

    
</table>


<div id="pdCheckstockOrderDetailDiv">
<!--iframe src="<c:url value='/pdEnterWarehouseDetails.html?ewNo=${ewNo}'/>" width="100%"></iframe-->

	<ec:table 
	tableId="pdCheckstockOrderDetailTable"
	items="pdCheckstockOrder.pdCheckstockOrderDetails"
	var="detail"
	action="${pageContext.request.c./images/extremetableails.html"
	width="100%"
	form="pdSendInfoForm"
	showPagination="false"
	sortable="false" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row>
    			
			

    			<ec:column property="productNo" title="busi.product.productno" />
    			
    			<ec:column property="pmProduct.productName" title="pmProduct.productName" >
    				${compamyProductMap[detail.productNo]}
    				
    			</ec:column>
    				<ec:column property="sysQty" title="pd.checkstock.sysQty" />
    		
    		
    			
    			<ec:column property="realQty" title="pd.checkstock.realQty" >
    					<input type="text"  name="<c:out value="${pdSendInfoDetail.uniNo}" />-realQty" id="<c:out value="${pdSendInfoDetail.uniNo}" />-realQty" value="${detail.realQty}"/>
    			</ec:column>	
    			
    			<ec:column property="diffQty" title="pd.checkstock.diff" >
    				<c:if test="${detail.sysQty != detail.realQty} ">
    					<font color='red'>${detail.sysQty - detail.realQty}</font>
    				</c:if>
    				<c:if test="${detail.sysQty != detail.realQty} ">
    					<font color='green'>${detail.sysQty - detail.realQty}</font>
    				</c:if>
    			</ec:column>
    		
				</ec:row>

</ec:table>	
	
	
	
		

	

</div>

<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdCheckstockOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    //Form.focusFirstElement($('pdCheckstockOrderForm'));
    
    function handPdAdjustStock(str){
    		
    		$('pdCheckstockOrderForm').submit();
    	}
     function selectWarehouse(elementId){
     			open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+elementId,"","height=600, width=800, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
</script>

<v:javascript formName="pdCheckstockOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
