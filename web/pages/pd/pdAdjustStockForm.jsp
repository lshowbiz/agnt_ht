<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdAdjustStockDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdAdjustStockDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang/calendar-zh.js"> </script> 
<spring:bind path="pdAdjustStock.*">
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

<c:if test="${not empty errorMsgs}">
    <div class="error">    
        <c:forEach var="error" items="${errorMsgs}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
        <c:remove var="errorMsgs" scope="session" />
    </div>
 </c:if>
<c:set var="buttons">

  	<c:choose>
  
		<c:when test="${(param.strAction=='editPdAdjustStock')&&(pdAdjustStock.orderFlag==-1)}">
				<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="checkPdAdjustStock('${checkFlag}');"
        value="<jecs:locale  key='button.submit'/>"/> --%>
        
        <button type="button" class="btn btn_sele"   onclick="checkPdAdjustStock('${checkFlag}');" ><jecs:locale  key='button.submit'/></button>
        
        	<%-- <input type="button" class="button" name="save" 
        	 onclick="savePdAdjustStock()" value="<jecs:locale key="operation.button.save"/>" /> --%>
        	 <button type="button" class="btn btn_sele"  name="save" onclick="savePdAdjustStock()" ><jecs:locale key="operation.button.save"/></button>
        	 
        	<%-- <input type="submit" class="button" name="delete" 
        		onclick="$('strAction').value='deletePdAdjustStock',bCancel=true;return confirmDelete('PdAdjustStock')" 
        		value="<jecs:locale key="operation.button.delete"/>" /> --%>
        	 <button type="submit" class="btn btn_sele"  name="delete" onclick="$('strAction').value='deletePdAdjustStock',bCancel=true;return confirmDelete('PdAdjustStock')" ><jecs:locale key="operation.button.delete"/></button>
        	
		</c:when>
		<c:when test="${(param.strAction=='checkPdAdjustStock')&&(pdAdjustStock.orderFlag==0)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="checkPdAdjustStock('${checkFlag}');"
        value="<jecs:locale  key='${checkButtonKey}'/>"/> --%>
        <button type="button" class="btn btn_sele"   onclick="checkPdAdjustStock('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
        
		</c:when>
		<c:when test="${(param.strAction=='confirmPdAdjustStock')&&(pdAdjustStock.orderFlag==1)}">
				<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="checkPdAdjustStock('${checkFlag}');"
        value="<jecs:locale  key='${checkButtonKey}'/>"/> --%>
         <button type="button" class="btn btn_sele"   onclick="checkPdAdjustStock('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
		</c:when>
	</c:choose>
	
	
	
	
	
	
	<c:if test="${!disabledFlag && (param.strAction=='addPdAdjustStock')}">
		<jecs:power powerCode="${param.strAction}">
<%-- 				<input type="button" class="button" name="save"  onclick="savePdAdjustStock()" value="<jecs:locale key="button.next"/>" />
 --%>		
                    <button type="button" class="btn btn_sele" name="save"  onclick="savePdAdjustStock()" ><jecs:locale key="button.next"/></button>
		</jecs:power>
		
	</c:if>
   
   <c:if test="${(param.strAction=='checkPdAdjustStock')&&(pdAdjustStock.orderFlag==0 || pdAdjustStock.orderFlag==1)}">
<%-- 		 <input type="submit" class="button" name="ret" onclick="$('strAction').value='toNewPdAdjustStock',bCancel=true;" value="<jecs:locale key="button.cancelToNew"/>" />
 --%>	
            <button type="submit" class="btn btn_sele" name="ret"  onclick="$('strAction').value='toNewPdAdjustStock',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
	</c:if>
</c:set>


<form:form commandName="pdAdjustStock" method="post" action="editPdAdjustStock.html" onsubmit="return validatePdAdjustStock(this)" id="pdAdjustStockForm" name="pdAdjustStockForm">


<table class='detail' width="70%">
<tbody class="window" >
<input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>
<form:hidden path="asNo"/>

	
    

    

    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="busi.warehouse.warehouseno"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="warehouseNo" disabled="${disabledFlag}" id="warehouseNo"  cssClass="textbox-text"/></span>
<%--     		<input type="button" class="button" name="select" <c:if test="${disabledFlag}">disabled="true"</c:if> onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>    
        <button type="button" class="btn btn_sele" name="select" <c:if test="${disabledFlag}">disabled="true"</c:if>  onclick="selectWarehouse('warehouseNo');" ><jecs:locale key="button.select"/></button>
    </td>
    <th>
        <span class="text-font-style-tc"><jecs:label  key="pdAdjustStock.astNo"/></span>
    </th>
    <td align="left">
        <!--form:errors path="astNo" cssClass="fieldError"/-->
       <c:if test="${disabledFlag}">
       	<span class="textbox"><jecs:code listCode="astnolist"  value="${pdAdjustStock.astNo}" /></span>	
       	
       	</c:if>
       	<c:if test="${!disabledFlag}">
        <span class="textbox"><jecs:list listCode="astnolist" name="astNo" id="astNo" value="${pdAdjustStock.astNo}" defaultValue="1" styleClass="textbox-text"/>	</span>
        </c:if>
    </td></tr>


    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea  path="remark" disabled="${disabledFlag}" id="remark"  cssClass="textarea_border"/></span>
    </td></tr>


    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="busi.user.check"/></span>
    </th>
    <td>
        <!--form:errors path="checkUrsNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="checkUsrCode" id="checkUsrCode" disabled="true" cssClass="textbox-text"/></span>
    </td>
    
    <th>
        <span class="text-font-style-tc"><jecs:label  key="pd.confirmUserNo"/></span>
    </th>
    <td>
      <span class="textbox">${pdAdjustStock.okUsrCode}&nbsp;${pdAdjustStock.okTime}</span>
        
        
    </td></tr>
    
<c:choose>
		<c:when test="${checkFlag ==1}">
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc-tare"><jecs:label  key="pd.checkRemark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="checkRemark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea  path="checkRemark"  id="checkRemark" cssClass="textarea_border"/></span>
    </td></tr>
		</c:when>
		<c:when test="${checkFlag ==2}">
   

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc-tare"><jecs:label  key="pd.okRemark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="okRemark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea  path="okRemark" id="okRemark" cssClass="textarea_border"/></span>
    </td></tr>
		</c:when>
</c:choose>

<%--     <tr>
    <th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
 <jecs:power powerCode="${param.strAction}">
	
		
			<c:out value="${buttons}" escapeXml="false"/>	

</jecs:power>

<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
    </td>
</tr> --%>
<tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
			             <c:out value="${buttons}" escapeXml="false"/>	
               </jecs:power>
<%--                <input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
 --%>               
               <button type="button" class="btn btn_sele" name="back"  onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
			</td>
		</tr>
      <tr  height="25px"><th></th><td></td><th></th><td></td></tr>
</tbody>
</table>
</form:form>
<!--/fieldset-->

<div class="searchBar">
	
	<c:if test="${checkFlag=='0' && !disabledFlag}">
		
		<form:form commandName="pdAdjustStockDetail" method="post" action="editPdAdjustStockDetail.html?strAction=addPdAdjustStockDetail&asNo=${asNo}" onsubmit="return validatePdAdjustStockDetail(this)" id="pdAdjustStockDetailForm">
				
				
				<table class='detail' width="70%">
					<tr style="height:20px;">
					<td align="center" style="background: #ECECEC;">
						<jecs:locale  key="busi.product.productno"/>
					</td>
					<td align="center" style="background: #ECECEC;">
						<jecs:locale  key="busi.direct.price.first.purchase"/>
					</td>
					<td align="center" style="background: #ECECEC;">
						<jecs:locale  key="pd.qty"/>
					</td>
				</tr>
					<tr class="edit_tr">
						<td align="center">
						<input type="text" name="productNo" id="productNo"/>
						
<%-- 						<input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" />
 --%>												
                        <button type="button" class="btn btn_sele" name="select"  onclick="selectProduct()" ><jecs:locale key="button.select"/></button>
						<input type="text" name="productName" id="productName" style="width:300px;"/>
						</td>
						<td align="center">
						<input type="text" name="price" id="price"/>
						</td>
						<td align="center">
						<input type="text" name="qty" id="qty"/>
<%-- 						<input type="submit" class="button"  name="add"  value="<jecs:locale key="operation.button.add"/>"/>
 --%>					    
                           <button type="submit" class="btn btn_sele" name="add"><jecs:locale key="operation.button.add"/></button>
                        </td>
					</tr>
				</table>
		</form:form>
		</c:if>
</div>




<c:if test="${strAction != 'addPdAdjustStock'}">
<div id="pdEnterWarehouseDetailId">
<!--iframe src="<c:url value='/pdEnterWarehouseDetails.html?ewNo=${ewNo}'/>" width="100%"></iframe-->

	
	
	<ec:table 
	tableId="pdAdjustStockDetailTable"
	items="pdAdjustStockDetailList"
	var="pdAdjustStockDetail"
	action="${pageContext.request.contextPath}/editPdAdjustStock.html"
	width="70%"
	
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" styleClass="detail">
				<ec:row >
					<ec:parameter name="strAction" value="${param.strAction}"/>
					<ec:parameter name="asNo" value="${asNo}"/>
					<c:if test="${!disabledFlag}">
					<ec:column property="edit" title="title.operation" sortable="false">
						
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdAdjustStockDetail('${pdAdjustStockDetail.uniNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					</c:if>
    			<!--ec:column property="asNo" title="pdAdjustStockDetail.asNo" /-->
    			
    			<ec:column property="productNo" title="busi.product.productno" />
    			<ec:column property="productName" title="pmProduct.productName" >
    				${compamyProductMap[pdAdjustStockDetail.productNo]}
    			</ec:column>
				<ec:column property="price" title="busi.direct.price.first.purchase" />
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn"/>
				</ec:row>

</ec:table>	
	

</div>
</c:if>


<script type="text/javascript">
    Form.focusFirstElement($('pdAdjustStockForm'));
    
    function savePdAdjustStock(){
    		if(validatePdAdjustStock($('pdAdjustStockForm'))){
    			   $('pdAdjustStockForm').submit();
    			}
    	}
    	
    function checkPdAdjustStock(checkFlag){
    			waiting();
    			<c:if test="${param.strAction == 'editPdAdjustStock'}">
    					$('strAction').value ='submitPdAdjustStock';
    			</c:if>
    			
    			$('pdAdjustStockForm').submit();
    	}
    function selectWarehouse(elementId){
    		<c:if test="${sessionScope.sessionLogin.isManager}">
     			open("<c:url value='/pdWarehouses.html'/>?strAction=selectAllWarehouse&elementId="+elementId,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     		</c:if>
     		<c:if test="${!sessionScope.sessionLogin.isManager}">
     			open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+elementId,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     		</c:if>
     		
     	}
     	
     	function addPdAdjustStockDetail(){
     				window.location="<c:url value='/editPdAdjustStockDetail.html'/>?strAction=addPdAdjustStockDetail&asNo=${asNo}";
     		}
     		
    function editPdAdjustStockDetail(uniNo){
    	<c:if test="${!disabledFlag}">
					window.location="editPdAdjustStockDetail.html?strAction=editPdAdjustStockDetail&uniNo="+uniNo;
			</c:if>
		}
		
		function toback(str){
			
				if(str == 'addPdAdjustStock'){
						str = 'editPdAdjustStock';
					}
    		this.location='<c:url value="/pdAdjustStocks.html"/>?strAction='+str;
    	}
    	function selectProduct(){
    		open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
    	
    	
    	function validatePdAdjustStockDetail(form){
    			 var ret = true;
    			 var fields = new Array(); 
    			 var i=0;
    			 if(form['productNo'].value==''){
    			 	  ret = false;
    			 	  fields[i++] = '<jecs:locale  key="busi.product.productno"/> <jecs:locale  key="errors.required"/>';
    			 	}
    			 
    			 if(form['price'].value==''){
    			 	  ret = false;
    			 	  fields[i++] = '<jecs:locale  key="pd.price"/> <jecs:locale  key="errors.required"/>';
    			 }
    			 
    			 
    			 
    			 if(form['qty'].value==''){
    			 	  ret = false;
    			 	  fields[i++] = '<jecs:locale  key="pd.qty"/> <jecs:locale  key="errors.required"/>';
    			 	}
    		//	 	if(form['price'].value=''){
    		//	 	  ret = false;
    		//	 	  fields[i++] = '<jecs:label  key="pd.price"/> <jecs:label  key="errors.required"/>';
    		//	 	}
    			
    			 
    			 if (fields.length > 0) {
			       
			       alert(fields.join('\n'));                                                                      
			    }  
			    return ret;
    		}
</script>

<v:javascript formName="pdAdjustStock" cdata="false" dynamicJavascript="true" staticJavascript="false"/>

<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
