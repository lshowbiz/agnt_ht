<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdFlitWarehouseDetail.title"/></title>
	<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/pdWarehouseManager.js'/>" ></script>
<content tag="heading"><jecs:locale key="pdFlitWarehouseDetail.heading"/></content>


<c:set var="buttons">
	
   <input type="submit" class="button" style="margin-right: 5px"
      onclick="savePdFlitWarehouse();"
        value="<jecs:locale  key='operation.button.save'/>"/>	
</c:set>


<spring:bind path="pdFlitWarehouse.*">
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
<form:form commandName="pdFlitWarehouse" method="post" action="editPdFlitWarehouseUpload.html" 
onsubmit="return validatePdFlitWarehouse(this)" id="pdFlitWarehouseForm" enctype="multipart/form-data">



<table class='detail' width="100%">
<input type="hidden" id="strAction" value="${param.strAction}" name="strAction"/>
<input type="hidden" id="warehosueFlag" value="0" name="warehosueFlag"/>
<input type="hidden" id="pageOrderFlag" value="-1" name="pageOrderFlag"/>
<form:hidden path="fwNo"/>
<form:hidden path="orderFlag" id="orderFlag"/>

 	
    

  


    <tr><th>
        <jecs:label  key="busi.warehouse.outwatehouseno"/>
    </th>
    <td align="left">
        <!--form:errors path="outWarehouseNo" cssClass="fieldError"/-->
        <form:input path="outWarehouse.warehouseNo" disabled="${disabledFlag}" id="outWarehouseNo"   cssClass="text medium"/>
    		<input type="button" class="button" <c:if test="${disabledFlag}">disabled</c:if> name="select" onclick="selectWarehouse('outWarehouseNo');" value="<jecs:locale key="button.select"/>" />
    </td></tr>

    <tr><th>
        <jecs:label  key="busi.pd.enterWarehouseNo"/>
    </th>
    <td align="left">
        <!--form:errors path="inWarehouseNo" cssClass="fieldError"/-->
        <form:input path="inWarehouse.warehouseNo" disabled="${disabledFlag}" id="inWarehouseNo"   cssClass="text medium"/>
    		<input type="button" class="button" <c:if test="${disabledFlag}">disabled</c:if> name="select" onclick="selectAllWarehouse('inWarehouseNo');" value="<jecs:locale key="button.select"/>" />
    </td></tr>

		<tr><th>
        <jecs:label  key="busi.user.check"/>
    </th>
    <td align="left">
        <!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
        <form:input path="checkUsrCode" disabled="true" id="checkUsrCode" cssClass="text medium"/>
    </td></tr>
    
    
    <tr><th>
        <jecs:label  key="pd.confirmUserNo"/>
    </th>
    <td align="left">
        <!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
       ${pdFlitWarehouse.okUsrCode}&nbsp;${pdFlitWarehouse.okTime}
    </td></tr>
    
    <tr><th>
        <jecs:label  key="pdFlitWarehouse.arrivedPdFlitWarehouse"/>
    </th>
    <td align="left">
        <!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
       ${pdFlitWarehouse.toUsrCode}&nbsp;${pdFlitWarehouse.toTime}
    </td></tr>
    
    
    
    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:textarea cols='70' rows='3'  path="remark" disabled="${disabledFlag}" id="remark" cssClass="table_textarea"/>
    </td></tr>

	<c:choose>
				<c:when test="${checkFlag ==1}">
				<tr>
				<th>
						<jecs:locale  key="pd.checkRemark"/>
				</th>		
				<td>	
		        <form:textarea cols='70' rows='3'  path="checkRemark" id="checkRemark" cssClass="table_textarea"/>
		    </td>   
		  </tr>	
				</c:when>
				<c:when test="${checkFlag ==2}">
				<tr>
						<th><jecs:locale  key="pd.okRemark"/></th>
		        <td><form:textarea cols='70' rows='3'  path="okRemark" id="okRemark" cssClass="table_textarea"/></td>
		     </tr>   	
				</c:when>
				<c:when test="${checkFlag ==3}">
				<tr>
						<th><jecs:locale  key="pd.toRemark"/></th>
		        <td><form:textarea cols='70' rows='3'  path="toRemark" id="toRemark" cssClass="table_textarea"/></td>
		     </tr>   	
				</c:when>
		</c:choose>
    <c:if test="${param.strAction=='searchPdFlitWarehouse'}">
    	<tr>
				<th>
						<jecs:locale  key="pd.checkRemark"/>
				</th>		
				<td>	
		        ${pdFlitWarehouse.checkRemark}
		    </td>   
		  </tr>	
		  <tr>
						<th><jecs:locale  key="pd.okRemark"/></th>
		        <td>${pdFlitWarehouse.okRemark}</td>
		     </tr>   
		     <tr>
						<th><jecs:locale  key="pd.toRemark"/></th>
		        <td>${pdFlitWarehouse.toRemark}</td>
		     </tr>  
    </c:if>


 <tr><th>
        <jecs:label  key="busi.common.trackingno"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="trackingNo"   id="trackingNo"  cssClass="text medium"/>
    </td></tr>
    
     <tr><th>
        <jecs:label key="uploadForm.file" styleClass="desc"/>
     </th>
     <td align="left">
        <form:errors path="file" cssClass="fieldError"/>
        <spring:bind path="pdFlitWarehouse.file">
        <input type="file" name="file" id="file" size='30'  value="<c:out value="${status.value}"/>"/>
       </spring:bind>
    </td>
    </tr>
    
    
    
		<tr>
    <th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
    	<jecs:power powerCode="${param.strAction}">
	<c:if test="${param.strAction !='searchPdFlitWarehouse'}">
		<c:out value="${buttons}" escapeXml="false"/>
	</c:if>
</jecs:power>
<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
    </td>
		</tr>

</table>
</form:form>








<script type="text/javascript">
    Form.focusFirstElement($('pdFlitWarehouseForm'));
     
     function selectWarehouse(str){
     			
     			<c:if test="${sessionScope.sessionLogin.isManager}">
     				window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectAllWarehouse&elementId="+str,"","height=600, width=800, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     			</c:if>
     			<c:if test="${!sessionScope.sessionLogin.isManager}">
     				window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=600, width=800, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     			</c:if>
     	}
     
     function addWarehouseDetail(){
    			window.location="<c:url value='/editPdFlitWarehouseDetail.html?strAction=addPdFlitWarehouseDetail&fwNo=${fwNo}'/>";
    			
    	}
     
     function addPdFlitWarehouseDetail(){
     	
     			$('pdFlitWarehouseDetailForm').submit();
     	}
     	function savePdFlitWarehouse(){
     		waiting();
     				confirmOutWarehouse();
								if($('warehosueFlag').value == 1){
									waitingEnd();
										return;
									}
     				$('pdFlitWarehouseForm').submit();
     		}
     	
		function checkFlitWarehouse(checkFlag){
					
					waiting();
					
					<c:choose>
					
						<c:when test="${param.strAction=='addPdFlitWarehouse'}">
    	      		$('pageOrderFlag').value='-1';
								confirmOutWarehouse();
								if($('warehosueFlag').value == 1){
									waitingEnd();
										return;
									}
						</c:when>
						
    	      <c:when test="${param.strAction=='editPdFlitWarehouse'}">
    	      		$('pageOrderFlag').value='0';
								confirmOutWarehouse();
								if($('warehosueFlag').value == 1){
									waitingEnd();
										return;
									}
						</c:when>
						
						
						<c:when test="${checkFlag ==4}">
						waitingEnd();
								return;
						</c:when>
						
				</c:choose>		
    			//if(validatePdFlitWarehouse($('pdFlitWarehouseForm'))){
    				$('pdFlitWarehouseForm').submit();
				//	}
			
			}
		
		
		function confirmOutWarehouse(){
					
					DWREngine.setAsync(false);
					$('warehosueFlag').value=0;
			    var outWarehouseNo = $('outWarehouseNo').value;
			    
			    var companyCode = '${sessionScope.sessionLogin.companyCode}';
			    if(companyCode == 'AA'){
			    			companyCode = "";
			    	}
			    
					pdWarehouseManager.existWarehouseNoByCompany(outWarehouseNo,companyCode,confirmInWarehouse);
					
					
					DWREngine.setAsync(true);
			}
		
		function confirmInWarehouse(flag){
			
				var inWarehouseNo = $('inWarehouseNo').value;
			  if(flag){
			  			pdWarehouseManager.existWarehouseNo(inWarehouseNo,confirmWarehouse);
			  	}else{
			  		alert('<jecs:locale key="busi.warehouse.outwatehouseno"/><jecs:locale key="errors.invalid"/>');
			  		$('warehosueFlag').value=1;
			  		return;
			  	}
				
			}
			
			function confirmWarehouse(ret){
					var outWarehouseNo = $('outWarehouseNo').value;
					var inWarehouseNo = $('inWarehouseNo').value;
					
					if(!ret){
							alert('<jecs:locale key="busi.pd.enterWarehouseNo"/><jecs:locale key="errors.invalid"/>');
							$('warehosueFlag').value=1;
			  			return;
						}else{ 
							if(outWarehouseNo ==inWarehouseNo){
									alert('<jecs:locale key="operation.warehouse.eq"/>');
									$('warehosueFlag').value=1;
									return;
								}
							
						}
						
				}
		function toback(str){
			
				if(str == 'addPdFlitWarehouse'){
						str = 'editPdFlitWarehouse';
					}
    		this.location='<c:url value="/pdFlitWarehouses.html"/>?strAction='+str;
    	}
		
		 function editPdFlitWarehouseDetail(uniNo){
		 	<jecs:power powerCode="editPdFlitWarehouse">
		 		<c:if test="${!disabledFlag}">
					window.location="editPdFlitWarehouseDetail.html?strAction=editPdFlitWarehouseDetail&uniNo="+uniNo;
				</c:if>
			</jecs:power >
		}
		
		function selectProduct(){
			open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1&companyCode=${pdFlitWarehouse.outWarehouse.companyCode}'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	//	open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
  
    	function validatePdFlitWarehouseDetail(form){
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
    	//		 	if(form['price'].value=''){
    	//		 	  ret = false;
    	//		 	  fields[i++] = '<jecs:label  key="pd.price"/> <jecs:label  key="errors.required"/>';
    	//		 	}
    			
    			 
    			 if (fields.length > 0) {
			       
			       alert(fields.join('\n'));                                                                      
			    }  
			    return ret;
    		}
</script>

<v:javascript formName="pdFlitWarehouse" cdata="false" dynamicJavascript="true" staticJavascript="false"/>

<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
