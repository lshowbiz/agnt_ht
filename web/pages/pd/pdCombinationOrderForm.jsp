<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdCombinationOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdCombinationOrderDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
			
		
		<c:choose>
			<c:when test="${(param.strAction=='editPdCombinationOrder')&&(pdCombinationOrder.orderFlag==-1)}">
					<button type="button" class="btn btn_sele" name="handButton"  onclick="handPdCombinationOrder()" ><jecs:locale  key='button.submit'/></button>
					<button type="button" class="btn btn_sele" name="save"  onclick="savePdCombinationOrder()" ><jecs:locale  key='operation.button.save'/></button>
					<button type="submit" class="btn btn_sele" name="delete"  onclick="bCancel=true;return confirmDelete(this.form,'PdCombinationOrder')" ><jecs:locale key="operation.button.delete"/></button>
			
					<%-- <input type="button" class="button" name="handButton"   onclick="handPdCombinationOrder()" value="<jecs:locale  key='button.submit'/>" />
							
					<input type="button" class="button" name="save"   onclick="savePdCombinationOrder()" value="<jecs:locale  key='operation.button.save'/>" />
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdCombinationOrder')" value="<jecs:locale key="operation.button.delete"/>" /> --%>
								
			</c:when>
			<c:when test="${param.strAction=='checkPdCombinationOrder'}">
				<c:if test="${pdCombinationOrder.orderFlag==0}">
				    <button type="button" class="btn btn_sele" name="handButton"  onclick="handPdCombinationOrder()" ><jecs:locale  key='button.checked'/></button>
				    <button type="button" class="btn btn_sele" name="handButton"  onclick="toNew()"><jecs:locale  key='button.cancelToNew'/></button>
				    
				
					<%-- <input type="button" class="button" name="handButton"   onclick="handPdCombinationOrder()" value="<jecs:locale  key='button.checked'/>" />
						<input type="button" class="button" name="handButton"   onclick="toNew()" value="<jecs:locale  key='button.cancelToNew'/>" /> --%>
				</c:if>
				<c:if test="${pdCombinationOrder.orderFlag==1 }">
					<button type="button" class="btn btn_sele" name="handButton"  onclick="toNew()"><jecs:locale  key='button.cancelToNew'/></button>
<%-- 					<input type="button" class="button" name="handButton"   onclick="toNew()" value="<jecs:locale  key='button.cancelToNew'/>" />
 --%>				
				</c:if>
			</c:when>
			<c:when test="${(param.strAction=='confirmPdCombinationOrder')&&(pdCombinationOrder.orderFlag==1)}">
			        <button type="button" class="btn btn_sele" name="handButton"  onclick="handPdCombinationOrder()"><jecs:locale  key='operation.button.confirm'/></button>
<%-- 				<input type="button" class="button" name="handButton"   onclick="handPdCombinationOrder()" value="<jecs:locale  key='operation.button.confirm'/>" />
 --%>			
			</c:when>
			<c:when test="${param.strAction=='addPdCombinationOrder'}">
			    <button type="button" class="btn btn_sele" name="save"  onclick="savePdCombinationOrder()"><jecs:locale  key='operation.button.save'/></button>
			    
				<!--input type="button" class="button" name="handButton"   onclick="handPdCombinationOrder()" value="<jecs:locale  key='button.submit'/>" /-->
<%-- 				<input type="button" class="button" name="save"   onclick="savePdCombinationOrder()" value="<jecs:locale  key='operation.button.save'/>" />
 --%>			</c:when>
		</c:choose>
	</jecs:power>
		<button type="button" class="btn btn_sele" name="back"  onclick="toback('${param.strAction}');"><jecs:locale  key="operation.button.return"/></button>
<%-- 	<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
 --%></c:set>



<c:if test="${not empty  errorList}">
    <div class="error">    
        <c:forEach var="error" items="${errorList}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/> <jecs:locale key="${errorCode}"/> <br />
        </c:forEach>
    </div>
    <c:remove var="errorList" scope="session" />
    </c:if>


<form:form commandName="pdCombinationOrder" method="post" action="editPdCombinationOrder.html" onsubmit="return validatePdCombinationOrder(this)" id="pdCombinationOrderForm">

<%-- <div class="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div> --%>
<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdCombinationOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%" >
<tbody class="window" >
<form:hidden path="pcNo"/>
<c:if test="${sessionScope.sessionLogin.isManager}">
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="pdCombinationOrder.companyCode"/></span>
    </th>
    <td>
        <span class="textbox"><form:input path="companyCode" disabled="${disabled}" id="companyCode" cssClass="textbox-text" /></span>
    </td>
    </tr>
</c:if>


		<tr class="edit_tr">
		<th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="busi.warehouse.warehouseno"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="warehouseNo" disabled="${disabled}" id="warehouseNo"  cssClass="textbox-text"/></span>
    	<button type="button" class="btn btn_sele" name="select" <c:if test="${disabled}">disabled="true"</c:if>  onclick="selectWarehouse('warehouseNo');"><jecs:locale key="button.select"/></button>
<%--     		<input type="button" class="button" name="select" <c:if test="${disabled}">disabled="true"</c:if> onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>    </td>
    <th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="busi.product.productno"/></span>
    </th>
    <td>
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="productNo" disabled="${disabled}" id="productNo" cssClass="textbox-text" /></span>
        <button type="button" <c:if test="${disabled}">disabled="true"</c:if> class="btn btn_sele" name="select"  onclick="selectCombProduct()"><jecs:locale key="button.select"/></button>
<%--         	<input type="button" <c:if test="${disabled}">disabled="true"</c:if> class="button" name="select" onclick="selectCombProduct()" value="<jecs:locale key="button.select"/>" />
 --%>    </td>
    
    </tr>


		<tr  class="edit_tr">
		<th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="pd.qty"/></span>
       </th>
    <td>
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="qty" id="qty" disabled="${disabled}" cssClass="textbox-text" /></span>
    </td>
    </tr>
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        
        	<span class="text-font-style-tc-right"><form:textarea  path="remark" id="remark" cssClass="textarea_border"/></span>
    </td></tr>

   

    <tr class="edit_tr">
    <th>
         <span class="text-font-style-tc-tare"><jecs:label  key="pd.checkRemark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="checkRemark" cssClass="fieldError"/-->
        
        	<span class="text-font-style-tc-right"><form:textarea path="checkRemark" id="checkRemark" cssClass="textarea_border"/></span>
    </td>
    </tr>

    

   
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc-tare"><jecs:label  key="pd.okRemark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="okRemark" cssClass="fieldError"/-->
        
        <span class="text-font-style-tc-right"><form:textarea path="okRemark" id="okRemark" cssClass="textarea_border"/></span>
    </td></tr>

    <tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
    </tr>
    
</tbody>
</table>
<div id="pdSendInfoDetailId">


	<ec:table 
	tableId="pdSendInfoDetailListTable"
	items="pdCombinationOrder.pdCombinationDetails"
	var="pdCombinationDetail"
	action="${pageContext.request.contextPath}/editPdCombinationOrder.html"
	width="70%"
	form="pdCombinationOrderForm"
	showPagination="false"
	sortable="false" imagePath="/jecs/images/extremetable/*.gif" styleClass="detail">
				<ec:row>

    			<ec:column property="productNo" title="busi.product.productno" />
    			
    			<ec:column property="pmProduct.productName" title="pmProduct.productName" >
    				${compamyProductMap[pdCombinationDetail.productNo]}
    				
    			</ec:column>
    				
    			<ec:column property="qty" title="pd.qty" >
    					<input type="text" <c:if test="${disabled}">readOnly="true" class="readonly"</c:if> name="<c:out value='${pdCombinationDetail.productNo}' />-qty" value="${pdCombinationDetail.qty}" id="<c:out value='${pdCombinationDetail.productNo}' />-qty"/>
    			</ec:column>	
	
				</ec:row>

</ec:table>	
</div>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdCombinationOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdCombinationOrderForm'));
    
    function toNew(){
    		$('strAction').value="toNewPdCombinationOrder";
    		$('pdCombinationOrderForm').submit();
    	}
    function savePdCombinationOrder(){
    			<jecs:power powerCode="editPdCombinationOrder">
    			
    			if(validatePdCombinationOrder($('pdCombinationOrderForm'))){
    					$('pdCombinationOrderForm').submit();
    				}
							
			    </jecs:power>
    	}
    	
    	function handPdCombinationOrder(){
    		if(${(param.strAction=='editPdCombinationOrder')||(param.strAction=='addPdCombinationOrder') }){
    						$('strAction').value="submitPdCombinationOrder";
    			}
    			
    				if(validatePdCombinationOrder($('pdCombinationOrderForm'))){
    					$('pdCombinationOrderForm').submit();
    				}
    				
    			
    		}
    		
    		function selectCombProduct(){
    				open("<c:url value='/jpmProducts.html?strAction=selectProduct&combineFlag=1'/>","","height=400, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    			}
    			
    			function toback(str){
	
				if(str == 'addPdCombinationOrder'){
						str = 'editPdCombinationOrder';
					}
    		this.location='<c:url value="/pdCombinationOrders.html"/>?strAction='+str;
    	}
</script>

<v:javascript formName="pdCombinationOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
