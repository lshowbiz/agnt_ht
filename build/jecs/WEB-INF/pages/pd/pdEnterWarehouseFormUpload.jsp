<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdEnterWarehouseDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdEnterWarehouseDetail.heading"/></content>

<c:set var="buttons">
	<input type="submit" class="button" onclick="$('strAction').value='uploadPdEnterWarehouse',bCancel=true;return saveEnterWarehouse()" value="<jecs:locale key="operation.button.save"/>" />
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
 
<form:form commandName="pdEnterWarehouse" method="post" action="pdEnterWarehouseFormUpload.html" 
	onsubmit="return validatePdEnterWarehouse(this)" id="pdEnterWarehouseForm" enctype="multipart/form-data">
	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
	<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
	<input type="hidden" name="pageOrderFlag" id="pageOrderFlag" />
	<form:hidden path="companyCode"/>
	<form:hidden path="ewNo"/>
	<table class='detail' width="100%">
	    <tr>
		    <th>
		        <jecs:label key="busi.pd.enterWarehouseNo"/>
		    </th>
		    <td align="left">
		    	<form:input path="warehouseNo" readonly="true" id="warehouseNo" cssClass="text medium"/>
				<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
			</td>
	   </tr>
	   <tr>
		   	<th>
		        <jecs:label key="uploadForm.file" styleClass="desc"/>
		    </th>
		    <td align="left">
		        <form:errors path="file" cssClass="fieldError"/>
		        <spring:bind path="pdEnterWarehouse.file">
		        	<input type="file" name="file" id="file" size='30'  value="<c:out value="${status.value}"/>"/>
		       	</spring:bind>
		    </td>
	   </tr>
	</table>
</form:form>
<script type="text/javascript">
    Form.focusFirstElement($('pdEnterWarehouseForm'));
    
      function editPdEnterWarehouseDetail(uniNo){
  	  
  		<c:if test="${!disabledFlag}">
					window.location="editPdEnterWarehouseDetail.html?strAction=editPdEnterWarehouseDetail&uniNo="+uniNo;
			</c:if>
		}
    
     function selectProduct(){
    		//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		
    	}
    	
    function selectWarehouse(elementId){
     			open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+elementId,'','height=400, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');
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
</script>

<v:javascript formName="pdEnterWarehouse" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
