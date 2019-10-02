<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdExchangeOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdExchangeOrderDetail.heading"/></content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/pdExchangeOrderDetailManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/scripts/livevalidation.js'/>"></script> 
<style>	
	.LV_validation_message{
    font-weight:bold;
    margin:0 0 0 5px;
}

.LV_valid {
    color:#00CC00;
}
	
.LV_invalid {
    color:#CC0000;
}
    
.LV_valid_field,
input.LV_valid_field:hover, 
input.LV_valid_field:active,
textarea.LV_valid_field:hover, 
textarea.LV_valid_field:active {
    border: 1px solid #00CC00;
}
    
.LV_invalid_field, 
input.LV_invalid_field:hover, 
input.LV_invalid_field:active,
textarea.LV_invalid_field:hover, 
textarea.LV_invalid_field:active {
    border: 1px solid #CC0000;
}
</style>	
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<c:choose>
					
					<c:when test="${param.strAction=='addPdExchangeOrder'}">
					   <%-- <input type="button" id="buttonNext" class="button" name="handButton"   onclick="pdExAdd(document.pdExchangeOrderForm)" value="<jecs:locale  key='button.next'/>"  />
					   <font color="red">(<jecs:locale key="showsInformationExchangeGoods"/>)</font> --%>
					   
					   <button type="button" id="buttonNext" class="btn btn_sele" name="handButton"  onclick="pdExAdd(document.pdExchangeOrderForm)" ><jecs:locale  key='button.next'/></button>
					   <font color="red">(<jecs:locale key="showsInformationExchangeGoods"/>)</font>
					</c:when>
					<c:when test="${(param.strAction=='pdExchangeOrderAddNext')&&(pdExchangeOrder.orderFlag==-1)}">
							<%-- <input type="button" class="button" name="handButton"   onclick="pdExAddNext(document.pdExchangeOrderForm)" value="<jecs:locale  key='operation.button.save'/>" />
							<input type="button" class="button" name="delete" onclick="handPdExchangeOrder('deletePdExchangeOrder')" value="<jecs:locale key="operation.button.delete"/>" /> --%>
					        <button type="button"  class="btn btn_sele" name="handButton"  onclick="pdExAddNext(document.pdExchangeOrderForm)" ><jecs:locale  key='operation.button.save'/></button>
					        <button type="button"  class="btn btn_sele" name="delete"  onclick="handPdExchangeOrder('deletePdExchangeOrder')" ><jecs:locale key="operation.button.delete"/></button>
					</c:when>
					
						<c:when test="${(param.strAction=='editPdExchangeOrder')&&(pdExchangeOrder.orderFlag==-1)&&(pdExchangeOrder.selfReplacement!='Y')}">
<%-- 							<input type="button" class="button" name="handButton"   onclick="handPdExchangeOrder('submitPdExchangeOrder')" value="<jecs:locale  key='button.submit'/>" />
 --%>								
                                <button type="button"  class="btn btn_sele" name="handButton"  onclick="handPdExchangeOrder('submitPdExchangeOrder')" ><jecs:locale  key='button.submit'/></button>
                                
<%-- 								<input type="button" class="button" name="handButton"   onclick="handPdExchangeOrder('editPdExchangeOrder')" value="<jecs:locale  key='operation.button.save'/>" />
 --%>							
                                 <button type="button"  class="btn btn_sele" name="handButton"  onclick="handPdExchangeOrder('editPdExchangeOrder')" ><jecs:locale  key='operation.button.save'/></button>
                                	
<%-- 								<input type="button" class="button" name="delete" onclick="handPdExchangeOrder('deletePdExchangeOrder')" value="<jecs:locale key="operation.button.delete"/>" />
 --%>						    
                                 <button type="button"  class="btn btn_sele" name="delete"  onclick="handPdExchangeOrder('deletePdExchangeOrder')" ><jecs:locale key="operation.button.delete"/></button>
                       </c:when>
					
					<c:when test="${(param.strAction=='checkPdExchangeOrder')&&(pdExchangeOrder.orderFlag==0)}">
<%-- 						<input type="button" class="button" name="handButton"   onclick="handPdExchangeOrder('checkPdExchangeOrder')" value="<jecs:locale  key='button.audit'/>" />
 --%>						
                            <button type="button"  class="btn btn_sele" name="handButton"  onclick="handPdExchangeOrder('checkPdExchangeOrder')" ><jecs:locale  key='button.audit'/></button>
					</c:when>
					
					<c:when test="${(param.strAction=='confirmPdExchangeOrder')&&(pdExchangeOrder.orderFlag==1)&&( empty pdExchangeOrder.selfReplacement)}">
<%-- 						<input type="button" class="button" name="handButton"   onclick="handPdExchangeOrder('confirmPdExchangeOrder')" value="<jecs:locale  key='operation.button.confirm'/>" />
 --%>					
                           <button type="button"  class="btn btn_sele" name="handButton"  onclick="handPdExchangeOrder('confirmPdExchangeOrder')" ><jecs:locale  key='operation.button.confirm'/></button>
					</c:when>
					
				</c:choose>
					
					<c:if test="${(param.strAction=='checkPdExchangeOrder')&&(pdExchangeOrder.orderFlag>=0)&&(pdExchangeOrder.orderFlag<=1)}">
		 
<%-- 		 <input type="submit" class="button" name="ret" onclick="$('strAction').value='tonewPdExchangeOrder',bCancel=true;" value="<jecs:locale key="button.cancelToNew"/>" />
 --%>		
		     <button type="submit"  class="btn btn_sele" name="ret"  onclick="$('strAction').value='tonewPdExchangeOrder',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
		</c:if>
<%-- 		<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
 --%>			
		   <button type="button"  class="btn btn_sele" name="back"  onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
		    
		</jecs:power>
		
</c:set>

<spring:bind path="pdExchangeOrder.*">
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

<form:form commandName="pdExchangeOrder" method="post" action="editPdExchangeOrder.html" onsubmit="return validatePdExchangeOrder(this)" id="pdExchangeOrderForm" name="pdExchangeOrderForm">




<table class='detail' width="70%">
<tbody class="window" >
<input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>
<input type="hidden" id="moId" name="moId" value="${param.moId}"/>
<form:hidden path="eoNo"/>

    

	<tr class="edit_tr">
	<th>
        <span class="text-font-style-tc"><jecs:label  key="customerRecord.customerNo"/></span>
    </th>
    <td>
        <!--form:errors path="customerCode" cssClass="fieldError"/-->
        <span class="textbox">${pdExchangeOrder.customer.userCode}/${pdExchangeOrder.customer.userName}</span>
        
    </td>
    <th>
        <span class="text-font-style-tc"><jecs:label  key="prReFund.originateOrderNo"/></span>
    </th>
    <td>
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
       <span class="textbox">${pdExchangeOrder.orderNo}</span>
    </td></tr>
    <tr  class="edit_tr">
    <c:choose>
    <c:when test="${pdExchangeOrder.selfReplacement !='Y'}">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="pdReturnPurchase.returnWarehouseNo"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        
        	<span class="textbox"><form:input path="warehouseNo" disabled="${disabledFlag}" onclick="selectWarehouse('warehouseNo');" id="warehouseNo"  cssClass="textbox-text"/></span>
    </td>
	</c:when>
	<c:otherwise>
	       <form:input path="warehouseNo" disabled="${disabledFlag}" onclick="selectWarehouse('warehouseNo');" id="warehouseNo"  cssClass="textbox-text" cssStyle="display:none"/>
	</c:otherwise>
	</c:choose>	
     <th>
        <span class="text-font-style-tc"><jecs:label  key="po.shipment.type"/></span>
    </th>
    <td>
        <!--form:errors path="recipientPhone" cssClass="fieldError"/-->
       
        	<span class="textbox"><jecs:list listCode="pd.exchange.type" name="shipType" showBlankLine="true" id="shipType" value="${pdExchangeOrder.shipType}" defaultValue="" styleClass="textbox-text" /></span>
        	
    </td></tr>

    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:input path="remark" id="remark" cssClass="textarea_border"/></span>
    </td></tr>    

 <form:input path="orderNo" id="orderNo" cssClass="text medium" cssStyle="display:none"/>
	<tr class="edit_tr">
	<th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.firstName"/></span>
    </th>
    <td>
        <!--form:errors path="firstName" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="firstName" disabled="${disabledFlag}" id="firstName" cssClass="textbox-text"/></span>
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.lastName"/></span>
    </th>
    <td>
        <!--form:errors path="lastName" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="lastName" disabled="${disabledFlag}" id="lastName" cssClass="textbox-text"/></span>
    </td></tr>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.province"/></span>
    </th>
    <td>
    	 <!--
    	 <select name="province" id="province">
					<option value=""><jecs:locale key='list.please.select'/></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq pdReturnPurchase.province}">selected</c:if> >
								${alStateProvince.stateProvinceName}
						</option>
					</c:forEach>
				</select>
				//-->
        <span class="textbox"><form:select path="province"  cssClass="textbox-text" onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
				</form:select></span>
		 
		
    </td>

    <th>
        <span class="text-font-style-tc"><span class="req">*</span><jecs:label  key="shipping.city"/></span>
    </th>
    <td>
    	 
      <span class="textbox"><select name="city" id="city" onchange="getDistrict()" class="textbox-text">
			<option value=""><jecs:locale key="list.please.select"/></option>
			</select></span>
	 
    </td></tr>

    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.district"/></span>
    </th>
    <td>
        <span class="textbox"><select name="district" id="district" class="textbox-text">
						<option value=""><jecs:locale key="list.please.select"/></option>
				</select></span>	
    </td>
    <th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.postalcode"/></span>
    </th>
    <td>
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="postalcode" id="postalcode"  cssClass="textbox-text"/></span>
    </td></tr>
    
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.address"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address"  cssClass="textbox"  size="80" cssStyle="width:500px;"/>
    </td>
    </tr>

   

    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.phone"/></span>
    </th>
    <td>
        <!--form:errors path="phone" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="phone" id="phone"  cssClass="textbox-text"/></span>
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele"/></span>
    </th>
      <td>
        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/></span>
    </td>
    </tr>
   <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
	    </th>
	    <td>
	        <!--form:errors path="email" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="email" id="email" cssClass="textbox-text"/></span>
	    </td>
         
    </tr>
   
  
  
  
  <c:forEach items="${pdExchangeOrder.pdExchangeOrderDetails}" var="pdExchangeOrderDetailTwo">
       <input type="text"  id="<c:out value="${pdExchangeOrderDetailTwo.uniNo}" />-dqtyT" name="<c:out value="${pdExchangeOrderDetailTwo.uniNo}" />-dqtyT" style="display:none"/>
  </c:forEach>
  <tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
   </tr>
 </tbody>
</table>

<div class="edit_tr" style="width:70%;" >
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<jecs:label key="pdReturnPurchase.returnWarehouseNo"/>
				  AMOUNT:
				       <input type="text" value="${totalBackPrice }" readonly="true" id="allBackAmount" name="allBackAmount"  /> 
				  &nbsp;
			 	PV:
			 	    <input type="text" value="${totalBackPv }"  readonly="true" id="allBackPv" name="allBackPv" />
</div>
	<ec:table 
	tableId="pdExchangeOrderBackTable"
	items="pdExchangeOrder.pdExchangeOrderBacks"
	var="pdExchangeOrderBack"
	action="${pageContext.request.contextPath}/editPdExchangeOrder.html"
	width="70%"
	method="post"
	form="pdExchangeOrderForm"
	showPagination="false"
	sortable="false" imagePath="./images/extremetable/*.gif" styleClass="detail">
			 
				 
				<ec:row >
					
					<ec:column property="productNo" title="busi.product.productno" />
					<ec:column property="productName" title="pmProduct.productName" >
    				${compamyProductMap[pdExchangeOrderBack.productNo]}
    			</ec:column>
    			
    			<ec:column property="originalQty" title="busi.pd.orderqty" styleClass="numberColumn"/>
    			<ec:column property="price" title="pd.price" />
    			<ec:column property="pv" title="PV" />
    			
    			<c:if test="${param.strAction!='addPdExchangeOrder'}">
	    			<ec:column property="qty" title="pd.qty" >
	    				<input type="text" onkeypress="validateMe();" onkeyup="this.value=this.value.replace(/\D/g,'');comparativeQuantity('${pdExchangeOrderBack.uniNo}-bqty','${pdExchangeOrderBack.originalQty}')"  <c:if test="${disabledFlag}"> readonly="true" </c:if>  name="<c:out value="${pdExchangeOrderBack.uniNo}" />-bqty" value="${pdExchangeOrderBack.qty}" id="<c:out value="${pdExchangeOrderBack.uniNo}" />-bqty"/>
	    			</ec:column>
    			</c:if>
    			
    			<c:if test="${param.strAction=='addPdExchangeOrder'}">
	    			<ec:column property="qty" title="pd.qty" >
	    			    <input type="text" onkeypress="validateMeAdd();" onkeyup="this.value=this.value.replace(/\D/g,'');comparativeQuantity('${pdExchangeOrderBack.productNo}-${pdExchangeOrderBack.price}-bqtyOne','${pdExchangeOrderBack.originalQty}')" <c:if test="${disabledFlag}"> readonly="true" </c:if> 
	    			     name="<c:out value="${pdExchangeOrderBack.productNo}"/>-<c:out value="${pdExchangeOrderBack.price}"/>-bqtyOne"
	    			      value="${pdExchangeOrderBack.qty}" 
	    			      id="<c:out value="${pdExchangeOrderBack.productNo}"/>-<c:out value="${pdExchangeOrderBack.price}"/>-bqtyOne"/>
	    			      
	    				<input type="text"  <c:if test="${disabledFlag}"> readonly="true" </c:if>  
	    				name="<c:out value="${pdExchangeOrderBack.productNo}"/>-<c:out value="${pdExchangeOrderBack.price}"/>-bqty"  
	    				value="${pdExchangeOrderBack.productNo},${pdExchangeOrderBack.price},${pdExchangeOrderBack.pv},${pdExchangeOrderBack.qty},${pdExchangeOrderBack.originalQty},${pdExchangeOrderBack.erpProductNo}"  
	    				id="<c:out value="${pdExchangeOrderBack.productNo}"/>-<c:out value="${pdExchangeOrderBack.price}"/>-bqty" style="display:none"/>
	    			</ec:column>
    			</c:if>
    			
				</ec:row>

</ec:table>	

    

</form:form>
<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdExchangeOrder')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->

<c:if test="${param.strAction!='addPdExchangeOrder'}">
<c:if test="${(pdExchangeOrder.orderFlag=='-1')&&(pdExchangeOrder.selfReplacement!='Y')}">
<div class="searchBar">
	
	<form:form commandName="pdExchangeOrderDetail" method="post" action="editPdExchangeOrderDetail.html?eoNo=${pdExchangeOrder.eoNo}&strAction=addPdExchangeOrderDetail" onsubmit="return validatePdExchangeOrderDetail(this)" id="pdExchangeOrderDetailForm">
				<input type="hidden"  name="erpProductNo" id="erpProductNo" />
				<input type="hidden"  name="erpPrice" id="erpPrice" />
				<table class='detail' width="70%">
					<tbody class="window" >
					<%-- <tr>
						<th ><jecs:locale  key="busi.product.productno"/></th>
						<th ><jecs:locale  key="pd.price"/></th>
						<th ><jecs:locale  key="PV"/></th>
						<th ><jecs:locale  key="pd.qty"/></th>
						<th >&nbsp;</th>
					</tr> --%>
					<tr style="height:20px;">
						<td align="center" style="background: #ECECEC;">
							<jecs:locale  key="busi.product.productno"/>
						</td>
						<td align="center" style="background: #ECECEC;">
							<jecs:locale  key="pd.price"/>
						</td>
						<td align="center" style="background: #ECECEC;">
							<jecs:locale  key="PV"/>
						</td>
						<td align="center" style="background: #ECECEC;">
							<jecs:locale  key="pd.qty"/>
						</td>
				    </tr>
					<tr class="edit_tr">
						<td align="center">
						<input type="text" name="productNo" id="productNo" />
<%-- 						<input type="button" class="button" name="select" onclick="selectProduct('${pdExchangeOrder.orderNo}')" value="<jecs:locale key="button.select"/>" />
 --%>						
                       	<button type="button" class="btn btn_sele" name="select"  onclick="selectProduct('${pdExchangeOrder.orderNo}')" ><jecs:locale key="button.select"/></button>
						<input type="text" name="productName" id="productName" size="40"/>
						</td>
						<td align="center">
						<input type="text" name="price" id="price" />
						</td>
						<td align="center">
						<input type="text" name="pv" id="pv" />
						</td>
						<td align="center">
						<input type="text" name="qty" id="qty" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
<%--                         <input type="submit" class="button"  name="add"  value="<jecs:locale key="operation.button.add"/>"/>
 --%>                        
                        <button type="submit" class="btn btn_sele" name="add"><jecs:locale key="operation.button.add"/></button>
                      </td>
					</tr>
					</tbody>
				</table>
	</form:form>
</div>
</c:if>

<div class="edit_tr" style="width:70%;" >
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


				 <jecs:label key="busi.exchange"/> 
				 	
				 		AMOUNT:<input type="text" value="${totalDetailPrice }" readonly="true" class="readonly" id="allDetailAmount" name="allDetailAmount"/> 
				 		&nbsp;
				 	PV:<input type="text" value="${totalDetailPv }" readonly="true" class="readonly" id="allDetailPv" name="allDetailPv"/>
</div>
 <!-- 
<table class='detail' width="100%">
    <tr>
    <td>
        <input type="button" class="button" name="rgPdEx"  onclick="rgPdEx()" value="<jecs:locale key="relationJpmProductSaleRelated.productName"/>" id="rgPdEx"/>
    </td>
    </tr>
</table>
	 -->


	<ec:table 
	tableId="pdExchangeOrderDetailListTable"
	items="pdExchangeOrder.pdExchangeOrderDetails"
	var="pdExchangeOrderDetail"
	form="pdExchangeOrderForm"
	action="${pageContext.request.contextPath}/editPdReturnPurchase.html"
	width="70%"
	showPagination="false"
	sortable="false" imagePath="./images/extremetable/*.gif" styleClass="detail">
				<ec:row >
    			<ec:column property="productNo" title="busi.product.productno" />
    			<ec:column property="productName" title="pmProduct.productName" >
    				${compamyProductMap[pdExchangeOrderDetail.productNo]}
    			</ec:column>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn"/>
    			<ec:column property="pv" title="PV" styleClass="numberColumn"/>
    			<ec:column property="qty" title="pd.qty" >
    			  
    			  <input type="text" onchange="ssetVa('${pdExchangeOrderDetail.uniNo}');" onkeypress="validateMe();"  <c:if test="${disabledFlag}"> readonly="true" </c:if>  name="<c:out value="${pdExchangeOrderDetail.uniNo}" />-dqty" value="${pdExchangeOrderDetail.qty}" id="<c:out value="${pdExchangeOrderDetail.uniNo}" />-dqty"/>
    			  
    			<!-- 
    			  <c:if test="${param.strAction!='addPdExchangeOrderNext'}">
    				 <input type="text" onkeypress="validateMe();"  readonly="true" name="<c:out value="${pdExchangeOrderDetail.uniNo}" />-dqty" value="${pdExchangeOrderDetail.qty}" id="<c:out value="${pdExchangeOrderDetail.uniNo}" />-dqty"/>
    			  </c:if>
    			  <c:if test="${param.strAction=='addPdExchangeOrderNext'}">
    			      <input type="text" onkeypress="validateMeAdd();" readonly="true"
    			        name="<c:out value="${pdExchangeOrderDetail.productNo}"/>-<c:out value="${pdExchangeOrderDetail.price}"/>-<c:out value="${pdExchangeOrderDetail.pv}"/>-<c:out value="${pdExchangeOrderDetail.erpProductNo}"/>-dqtyOne"
    			         value="${pdExchangeOrderDetail.qty}" 
    			        id="<c:out value="${pdExchangeOrderDetail.productNo}"/>-<c:out value="${pdExchangeOrderDetail.price}"/>-<c:out value="${pdExchangeOrderDetail.pv}"/>-<c:out value="${pdExchangeOrderDetail.erpProductNo}"/>-dqtyOne"/>
    			 
    			      <input type="text" onkeypress="validateMeAdd();" <c:if test="${disabledFlag}"> readonly="true" </c:if> 
    			        name="<c:out value="${pdExchangeOrderDetail.productNo}"/>-<c:out value="${pdExchangeOrderDetail.price}"/>-<c:out value="${pdExchangeOrderDetail.pv}"/>-<c:out value="${pdExchangeOrderDetail.erpProductNo}"/>-dqty"
    			         value="${pdExchangeOrderDetail.productNo},${pdExchangeOrderDetail.price},${pdExchangeOrderDetail.pv},${pdExchangeOrderDetail.erpProductNo}" 
    			        id="<c:out value="${pdExchangeOrderDetail.productNo}"/>-<c:out value="${pdExchangeOrderDetail.price}"/>-<c:out value="${pdExchangeOrderDetail.pv}"/>-<c:out value="${pdExchangeOrderDetail.erpProductNo}"/>-dqty" style="display:none"/>
	    			
    			  </c:if>
    			   -->
    			</ec:column>
    			<c:if test="${(pdExchangeOrder.orderFlag=='-1')&&(pdExchangeOrder.selfReplacement!='Y')}">
	    			<ec:column property="edit" title="title.operation" sortable="false">
<%-- 	    			    <input type="button" class="button" name="delete" onclick="deletePdExchangeOrderDetail(this,'${pdExchangeOrderDetail.uniNo}','${pdExchangeOrder.eoNo}')" value="<jecs:locale key="operation.button.delete"/>" />
 --%>	    			    
                            <button type="button"  class="btn btn_sele" name="delete"  onclick="deletePdExchangeOrderDetail(this,'${pdExchangeOrderDetail.uniNo}','${pdExchangeOrder.eoNo}')" ><jecs:locale key="operation.button.delete"/></button>
	    			</ec:column>
    			</c:if>
				</ec:row>
   </ec:table>

</c:if>






<script type="text/javascript">
    //Form.focusFirstElement($('pdExchangeOrderForm'));
    
    //<c:forEach items="${pdExchangeOrder.pdExchangeOrderBacks}" var="backItem">
    //      var addPdExchangeOrder = "addPdExchangeOrder";
	//      if(addPdExchangeOrder==${param.strAction}){
	//      	   new LiveValidation('${backItem.productNo}-${pdExchangeOrderBack.price}-bqtyOne').add(Validate.Numericality, {minimum: 0, maximum: ${backItem.originalQty},onlyInteger: true }).add(Validate.Presence);
	//      }else{ 
	//    	   new LiveValidation('${backItem.uniNo}-bqty').add(Validate.Numericality, {minimum: 0, maximum: ${backItem.originalQty},onlyInteger: true }).add(Validate.Presence);
	//      }
   // </c:forEach>
    <c:forEach items="${pdExchangeOrder.pdExchangeOrderDetails}" var="detail">
    	new LiveValidation('${detail.uniNo}-dqty').add(Validate.Numericality, { minimum: 0,onlyInteger: true }).add(Validate.Presence);
    </c:forEach>	
    	
    	function pdExAdd(theForm){
    	   if(validatePdExchangeOrder($('pdExchangeOrderForm'))){
    	                    document.getElementById("strAction").value="pdExchangeOrderAdd";
    	                    waiting();
    						theForm.submit();
    	   }
    	}
    	
    	function pdExAddNext(theForm){
    	   if(validatePdExchangeOrder($('pdExchangeOrderForm'))){
    	                    document.getElementById("strAction").value="pdExchangeOrderAddNext";
    	                    waiting();
    						theForm.submit();
    	   }
    	}

    	function comparativeQuantity(newID,oldQuantity){
                var newQuantity = document.getElementById(newID).value;
                if(null==newQuantity || ""==newQuantity){
                	document.getElementById(newID).value = oldQuantity;
                }

                if(parseInt(newQuantity)>parseInt(oldQuantity)){
                	alert("<jecs:locale  key="prRefund.newQtyTooBig"/>");
                	document.getElementById(newID).value = oldQuantity;
                }
    	}

    	function editPdExchangeOrderDetail(uniNo){
    		window.location.href="editPdExchangeOrderDetail.html?strAction=editPdExchangeOrderDetail&uniNo="+uniNo;
    	}

    	function ssetVa(newID){
    		var aty = document.getElementById(newID+"-dqty").value ;
    		 document.getElementById(newID+"-dqtyT").value = aty;
    	}

        function deletePdExchangeOrderDetail(obj,uniNo,eoNo){
            if(confirm("<jecs:locale  key="amMessage.confirmdelete"/>")){
	        	
	            waiting();
	        	pdExchangeOrderDetailManager.removePdExchangeOrderDetail(uniNo);
	        	waitingEnd();
	        	var tr=obj.parentNode.parentNode; 
	        	var tbody=tr.parentNode; 
	        	tbody.removeChild(tr); 
            }
        }
    	
    	
   // function rgPdEx(){
   //      alert("111111");
   //      var moId = document.getElementById("moId").value;
   //      pdExchangeOrderDetailManager.getPdExchangeOrderDetailForMoid(moId,rsPdExchangeOrderDetail);
  //  }	
    	
  //  function PdExchangeOrderDetail(pdExchangeOrderDetails){
  //               alert("22222");
   //     document.getElementById("moId").items =pdExchangeOrderDetails;
   //     window.location.reload();
  //  }
    	
     function  validateMe(){
     	  var ret =true;
     	
     		var allBackAmount = 0;
     		var allBackPv=0;
     		var allDetailAmount = 0;
     		var allDetailPv=0;
     		var oriQty;
     		var backPrice;
     		var backPv;
     		var backQty;
     		<c:forEach items="${pdExchangeOrder.pdExchangeOrderBacks}" var="backItem">
     		  backPrice = ${backItem.price};
     		  backPv = ${backItem.pv};
     		  backQty = $('${backItem.uniNo}-bqty').value;
     		  
     		  oriQty = ${backItem.originalQty};
     		  
     		   
     			allBackAmount += backPrice*backQty;
     			allBackPv += backPv*backQty;
     		</c:forEach>
     		
     		<c:forEach items="${pdExchangeOrder.pdExchangeOrderDetails}" var="detail">
     		  backPrice = ${detail.price};
     		  backPv = ${detail.pv};
     		  backQty = $('${detail.uniNo}-dqty').value;
     		  
     			allDetailAmount += backPrice*backQty;
     			allDetailPv += backPv*backQty;
     		</c:forEach>
     		$('allBackAmount').value=allBackAmount;
     		$('allBackPv').value=allBackPv;
     		$('allDetailAmount').value=allDetailAmount;
     		$('allDetailPv').value=allDetailPv;
     		
     		if((allBackAmount!=allDetailAmount)||(allBackPv!=allDetailPv)){
     			ret=false;
     			}
     		//alert(allBackAmount);
     		//alert(allBackPv);
     		//alert(allDetailAmount);
     		//alert(allDetailPv);
     		return ret;
     	}
     	
     	 function  validateMeAdd(){
     	  var ret =true;
     	
     		var allBackAmount = 0;
     		var allBackPv=0;
     		var allDetailAmount = 0;
     		var allDetailPv=0;
     		var oriQty;
     		var backPrice;
     		var backPv;
     		var backQty;
     		<c:forEach items="${pdExchangeOrder.pdExchangeOrderBacks}" var="backItem">
     		  backPrice = ${backItem.price};
     		  backPv = ${backItem.pv};
     		  
     		  backQty = $('${backItem.productNo}-${backItem.price}-bqtyOne').value;
     		  
     		  oriQty = ${backItem.originalQty};
     		  
     		   
     			allBackAmount += backPrice*backQty;
     			allBackPv += backPv*backQty;
     		</c:forEach>
     		
     		<c:forEach items="${pdExchangeOrder.pdExchangeOrderDetails}" var="detail">
     		  backPrice = ${detail.price};
     		  backPv = ${detail.pv};
     		  backQty = $('${detail.productNo}-${detail.price}-${detail.pv}-${detail.erpProductNo}-dqtyOne').value;
     		  
     			allDetailAmount += backPrice*backQty;
     			allDetailPv += backPv*backQty;
     		</c:forEach>
     		
     		$('allBackAmount').value=allBackAmount;
     		$('allBackPv').value=allBackPv;
     		$('allDetailAmount').value=allDetailAmount;
     		$('allDetailPv').value=allDetailPv;
     		
     		if((allBackAmount!=allDetailAmount)||(allBackPv!=allDetailPv)){
     			ret=false;
     		}
     			
     		return ret;
     	}	
     	
     		
    function selectProduct(orderNo){
    		open("<c:url value='/pdExchangeOrderDetails.html?strAction=selectProduct&orderNo="+orderNo+"'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
    
    //function selectProduct(){
     //        open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=all'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
   // }	
    	
    function handPdExchangeOrder(strAction){
    	
    			
    			
    			$('strAction').value=strAction;
    			if(strAction == 'deletePdExchangeOrder'){
    				    waiting();
    					$('pdExchangeOrderForm').submit();
    			}else{
    					if(  validatePdExchangeOrder($('pdExchangeOrderForm'))){
    						waiting();
    						$('pdExchangeOrderForm').submit();
    			         }
    			         else{
    				         waitingEnd();
    				     }
    		    }
    	}	
    	
    		function toback(str){
			
				if(str == 'addPdExchangeOrder'){
						str = 'editPdExchangeOrder';
					}
    		this.location='<c:url value="/pdExchangeOrders.html"/>?strAction='+str;
    	}
    	
    	var tmpSelect = '';
	 function getCity(){
		 var province=dwr.util.getValue('province');
		 alCityManager.getAlCityByProvinceId(province,callBackCity);
	}
	function callBackCity(valid){
		
		dwr.engine.setAsync(false);
		var cityElemment=document.getElementById('city');
		dwr.util.removeAllOptions('city');
	 
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		cityElemment.options.add(o);
		dwr.util.addOptions('city',valid,'cityId','cityName');
		dwr.util.setValue('city','${pdExchangeOrder.city}');
    	
		
			getDistrict();
		}
		
	
	function getDistrict(){
		
		var city=dwr.util.getValue('city');
		alDistrictManager.getAlDistrictByCityId(city,callBackDistrict);
	}
	function callBackDistrict(valid){
		dwr.engine.setAsync(false);
		
		var districtElemment=document.getElementById('district');
		
		dwr.util.removeAllOptions('district');
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);
		dwr.util.addOptions('district',valid,'districtId','districtName');
		dwr.util.setValue('district','${pdExchangeOrder.district}');
		
		
	}

	 function cancel(){
	     	waitingEnd();
	     }
	
	<c:if test="${not empty pdExchangeOrder.province}">
		
		getCity();
	</c:if>
	
</script>

<v:javascript formName="pdExchangeOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
