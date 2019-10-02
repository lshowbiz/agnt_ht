<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdEnterWarehouseDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdEnterWarehouseDetail.heading"/></content>

<c:set var="buttons">
	<c:choose>
		
		<c:when test="${(param.strAction=='editPdEnterWarehouse')&&(pdEnterWarehouse.orderFlag==-1)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="checkEnterWarehouse('${checkFlag}');"
        value="<jecs:locale  key='button.submit'/>"/> --%>
        <button type="button" class="btn btn_sele"   onclick="checkEnterWarehouse('${checkFlag}');" ><jecs:locale  key='button.submit'/></button>
        	
     <%-- <input type="button" class="button" name="save" 
        	 onclick="saveEnterWarehouse()" value="<jecs:locale key="operation.button.save"/>" /> --%>
       <button type="button" class="btn btn_sele" name="save" onclick="saveEnterWarehouse()" ><jecs:locale key="operation.button.save"/></button>
		</c:when>
		<c:when test="${(param.strAction=='checkPdEnterWarehouse')&&(pdEnterWarehouse.orderFlag==0)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="checkEnterWarehouse('${checkFlag}');"
        value="<jecs:locale  key='${checkButtonKey}'/>"/> --%>
         <button type="button" class="btn btn_sele" onclick="checkEnterWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
		</c:when>
		<c:when test="${(param.strAction=='confirmPdEnterWarehouse')&&(pdEnterWarehouse.orderFlag==1)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="checkEnterWarehouse('${checkFlag}');"
        value="<jecs:locale  key='${checkButtonKey}'/>"/> --%>
        
       <button type="button" class="btn btn_sele" onclick="checkEnterWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
		</c:when>
	
	
   <c:when test="${param.strAction=='addPdEnterWarehouse'}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
        onclick="addEnterWarehouse();"
        value="<jecs:locale  key='button.next'/>"/> --%>
        
        <button type="button" class="btn btn_sele" onclick="addEnterWarehouse();" ><jecs:locale  key='button.next'/></button>
        
		</c:when>
	</c:choose>
	<c:if test="${!disabledFlag}">
		<c:if test = "${param.strAction == 'editPdEnterWarehouse'}">
<%-- 			<input type="submit" class="button" name="delete" onclick="$('strAction').value='deletePdEnterWarehouse',bCancel=true;return confirmDelete('PdEnterWarehouse')" value="<jecs:locale key="operation.button.delete"/>" />
 --%>	
                <button type="submit" class="btn btn_sele" name="delete" onclick="$('strAction').value='deletePdEnterWarehouse',bCancel=true;return confirmDelete('PdEnterWarehouse')" ><jecs:locale key="operation.button.delete"/></button>
		</c:if>
	</c:if>
	<c:if test="${(param.strAction=='checkPdEnterWarehouse')&&(pdEnterWarehouse.orderFlag==0 || pdEnterWarehouse.orderFlag==1)}">
	
<%-- 		 <input type="submit" class="button" name="tonew" onclick="$('strAction').value='toNewPdEnterWarehouse',bCancel=true;" value="<jecs:locale key="button.cancelToNew"/>" />
 --%>	
             <button type="submit" class="btn btn_sele" name="tonew" onclick="$('strAction').value='toNewPdEnterWarehouse',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
	</c:if>
   	<button type="button" class="btn btn_sele"  name="back" onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
   
</c:set>

<spring:bind path="pdEnterWarehouse.*">
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
<form:form commandName="pdEnterWarehouse" method="post" action="editPdEnterWarehouse.html" onsubmit="return validatePdEnterWarehouse(this)" id="pdEnterWarehouseForm">


<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
<input type="hidden" name="pageOrderFlag" id="pageOrderFlag" />

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdEnterWarehouse')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >

<form:hidden path="companyCode"/>
<form:hidden path="ewNo"/>
		
    

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="busi.pd.enterWarehouseNo"/></span>
    </th>
    <td>
    				<span class="textbox"><form:input path="warehouseNo" readonly="true" id="warehouseNo" cssClass="textbox-text" /></span>
						
<%-- 						<input type="button" class="button" <c:if test="${disabledFlag}">disabled="ture"</c:if>  name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>		
                      <button type="button" class="btn btn_sele" <c:if test="${disabledFlag}">disabled="ture"</c:if>  name="select" onclick="selectWarehouse('warehouseNo');" ><jecs:locale key="button.select"/></button>
  </td>
				
   

    <th>
        <span class="text-font-style-tc"><jecs:label  key="busi.user.check"/></span>
    </th>
    <td>
        <!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="checkUsrCode" id="checkUsrCode" disabled="true" cssClass="textbox-text"/></span>
    </td></tr>
    
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="pd.confirmUserNo"/></span>
    </th>
    <td align="left">
        <!--form:errors path="okUsrCode" cssClass="fieldError"/-->
       <span class="textbox">${pdEnterWarehouse.okUsrCode}&nbsp;${pdEnterWarehouse.okTime}</span>
    </td></tr>
    

    

    

    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
    </th>
    <td colspan="3">
        <span class="text-font-style-tc-right">
        <div class="textarea_border">
        ${pdEnterWarehouse.remark}<br/>
        ${pdEnterWarehouse.checkRemark}<br/>
        ${pdEnterWarehouse.okRemark}
        </div></span>
    </td></tr>
    
    


   
   <c:choose>
   		<c:when test="${pdEnterWarehouse.orderFlag ==-1}">
				<tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
		    </th>
		    <td colspan="3">
		        <!--form:errors path="remark" cssClass="fieldError"/-->
		        <span class="text-font-style-tc-right"><form:textarea path="remark" disabled="${disabledFlag}" id="remark" cssClass="textarea_border" /></span>
		    </td></tr>
				</c:when>
				<c:when test="${pdEnterWarehouse.orderFlag ==0}">
				<tr class="edit_tr">
				<th>
						<span class="text-font-style-tc-tare"><jecs:label  key="pd.checkRemark"/></span>
				</th>		
				<td colspan="3">	
		        <span class="text-font-style-tc-right"><form:textarea  path="checkRemark" id="checkRemark" cssClass="textarea_border"/></span>
		    </td>   
		  </tr>	
				</c:when>
				<c:when test="${pdEnterWarehouse.orderFlag ==1}">
				<tr class="edit_tr">
						<th>
						<span class="text-font-style-tc-tare"><jecs:label  key="pd.okRemark"/></span>
						</th>
		        <td colspan="3">
		        <span class="text-font-style-tc-right">
		        <form:textarea  path="okRemark" id="okRemark" cssClass="textarea_border"/>
		        </span>
		        </td>
		     </tr>   	
				</c:when>
		</c:choose>
		<tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
  
</tbody>
</table>
<!--/fieldset-->


</form:form>


<div class="searchBar">
	
	<c:if test="${checkFlag=='0' && !disabledFlag}">
	<form:form commandName="pdEnterWarehouseDetail" method="post" action="editPdEnterWarehouseDetail.html?ewNo=${ewNo}&strAction=addPdEnterWarehouseDetail" onsubmit="return validatePdEnterWarehouseDetail(this)" id="pdEnterWarehouseDetailForm">
				<input type="hidden" id="companyNo" name="companyNo" value="AA"/>
				<input type="hidden" name="uniNo" id="uniNo"/>
				
				<table class='detail' width="70%">
				  <tbody class="window" >
					<tr style="height:20px;">
					<td align="center" style="background: #ECECEC;">
						<jecs:locale  key="busi.product.productno"/>
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
						<input type="text" name="qty" id="qty"/>
<%-- 						<input type="submit" class="button"  name="add"  value="<jecs:locale key="operation.button.add"/>"/>
 --%>						
                           <button type="submit" class="btn btn_sele" name="add"><jecs:locale key="operation.button.add"/></button>
						</td>
						
					</tr>
					</tbody>
				</table>
		</form:form>
		</c:if>
</div>

<div id="pdEnterWarehouseDetailId" <c:if test="${strAction=='addPdEnterWarehouse'}">style="display:none"</c:if>>
<!--iframe src="<c:url value='/pdEnterWarehouseDetails.html?ewNo=${ewNo}'/>" width="100%"></iframe-->

		
		<c:if test="${checkFlag=='0' && !disabledFlag}">
				
				<!--input type="button" class="button"  name="add"  onclick="addWarehouseDetail();" value="<jecs:locale key="operation.button.add"/>" /-->
		</c:if>
	
	<ec:table 
	tableId="pdEnterWarehouseDetailListTable"
	items="pdEnterWarehouseDetailList"
	var="pdEnterWarehouseDetail"
	action="${pageContext.request.contextPath}/editPdEnterWarehouse.html"
	width="70%"
	method="get"
	
	
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" styleClass="detail">
				<ec:parameter name="strAction" value="${param.strAction}"/>
				<ec:parameter name="ewNo" value="${ewNo}"/>
				
				<ec:row >
					<c:if test="${!disabledFlag}">
					<ec:column property="edit" title="title.operation" sortable="false">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdEnterWarehouseDetail(${pdEnterWarehouseDetail.uniNo})"
								 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					</c:if>
					<!--ec:column property="ewNo" title="pdEnterWarehouse.ewNo" /-->
    			<!--ec:column property="companyNo" title="sys.company.code" /-->
    			<ec:column property="productNo" title="busi.product.productno" >
    					${pdEnterWarehouseDetail.productNo}/${compamyProductMap[pdEnterWarehouseDetail.productNo]}
    			</ec:column>
    			
    			<ec:column property="qty" title="pd.qty"/>
    				
				</ec:row>
				
</ec:table>	


</div>

<script type="text/javascript">
    Form.focusFirstElement($('pdEnterWarehouseForm'));
    
      function editPdEnterWarehouseDetail(uniNo){
  	  
  		<c:if test="${!disabledFlag}">
					window.location="editPdEnterWarehouseDetail.html?strAction=editPdEnterWarehouseDetail&uniNo="+uniNo;
			</c:if>
		}
    
     function selectProduct(){
    		//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		
    	}
    	
    function selectWarehouse(elementId){
     			open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+elementId,'','height=400, width=1000, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');
    }
     	
     	function addPdWarehouseDetail(){
     			$('pdEnterWarehouseDetailForm').submit();
     		}
    function addWarehouseDetail(){
    			window.location="<c:url value='/editPdEnterWarehouseDetail.html'/>?strAction=addPdEnterWarehouseDetail&ewNo=${ewNo}";
    			
    	}
    function saveEnterWarehouse(){
    	waiting();
    	$('pageOrderFlag').value="-1";
    			$('pdEnterWarehouseForm').submit();
    			
    	}	
    	
    	function addEnterWarehouse(){
    		waiting();
    			if(validatePdEnterWarehouse($('pdEnterWarehouseForm'))){
    				$('pageOrderFlag').value="-1";
    					$('pdEnterWarehouseForm').submit();
    				}
    			
    		}
    function checkEnterWarehouse(checkFlag){
    	
    	  	waiting();
    	  	
    	  	<c:if test="${param.strAction == 'editPdEnterWarehouse'}">
    	   		$('strAction').value='submitPdEnterWarehouse';
    	   		$('pageOrderFlag').value="0";
    	   </c:if>
    	   
    		
    			
    			$('pdEnterWarehouseForm').submit();
    			
    			//window.location="<c:url value='/editPdEnterWarehouseDetail.html?strAction=checkEnterWarehouse&ewNo=${ewNo}'/>";
    		}
    		
    		function toBack(strAction){
    					var str = strAction;
    					if(str == 'addPdEnterWarehouse'){
    							str = 'editPdEnterWarehouse';
    						}
    					window.location="<c:url value='/pdEnterWarehouses.html'/>?strAction="+str;
    			}
    			
    			function validatePdEnterWarehouseDetail(form){
    			 var ret = true;
    			 var fields = new Array(); 
    			 var i=0;
    			 if(form['productNo'].value==''){
    			 	  ret = false;
    			 	  fields[i++] = '<jecs:locale  key="busi.product.productno"/> <jecs:locale  key="errors.required"/>';
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
    		
    		function toback(str){
		//			alert(this.document.referrer);
					if(str == 'addPdEnterWarehouse'){
							str = 'editPdEnterWarehouse';
						}
	    		this.location='<c:url value="/pdEnterWarehouses.html"/>?strAction='+str;
	    	}
</script>

<v:javascript formName="pdEnterWarehouse" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
