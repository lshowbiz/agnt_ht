<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdReturnPurchaseDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdReturnPurchaseDetail.heading"/></content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>
<c:set var="buttons">

					
				
		<jecs:power powerCode="${param.strAction}">
				<c:choose>
					
					<c:when test="${(param.strAction=='editPdReturnPurchase')&&(pdReturnPurchase.orderFlag==-1)}">
						<%-- <input type="button" class="button" name="handButton"   onclick="handPdReturnPurchase()" value="<jecs:locale  key='button.submit'/>" />
							
							<input type="button" class="button" name="handButton"   onclick="savePdReturnPurchase()" value="<jecs:locale  key='operation.button.save'/>" />
							<input type="submit" class="button" name="delete" onclick="$('strAction').value='deletePdReturnPurchase',bCancel=true;return confirmDelete(this.form,'PdReturnPurchase')" value="<jecs:locale key="operation.button.delete"/>" /> --%>
									
							<button type="button" class="btn btn_sele"  name="handButton" onclick="handPdReturnPurchase()" ><jecs:locale  key='button.submit'/></button>
							<button type="button" class="btn btn_sele"  name="handButton" onclick="savePdReturnPurchase()" ><jecs:locale  key='operation.button.save'/></button>
							<button type="submit" class="btn btn_sele"  name="delete" onclick="$('strAction').value='deletePdReturnPurchase',bCancel=true;return confirmDelete(this.form,'PdReturnPurchase')" ><jecs:locale key="operation.button.delete"/></button>
					
					</c:when>
					
					<c:when test="${(param.strAction=='checkPdReturnPurchase')&&(pdReturnPurchase.orderFlag==0)}">
<%-- 						<input type="button" class="button" name="handButton"   onclick="handPdReturnPurchase()" value="<jecs:locale  key='${checkButtonKey}'/>" />
 --%>			
                     <button type="button" class="btn btn_sele"  name="handButton" onclick="handPdReturnPurchase()" ><jecs:locale  key='${checkButtonKey}'/></button>
                     		
					</c:when>
					<c:when test="${(param.strAction=='financePdReturnPurchase')&&(pdReturnPurchase.orderFlag==1)}">
<%-- 						<input type="button" class="button" name="handButton"   onclick="handPdReturnPurchase()" value="<jecs:locale  key='${checkButtonKey}'/>" />
 --%>					
                      <button type="button" class="btn btn_sele"  name="handButton" onclick="handPdReturnPurchase()" ><jecs:locale  key='${checkButtonKey}'/></button>
					</c:when>
					<c:when test="${(param.strAction=='confirmPdReturnPurchase')&&(pdReturnPurchase.orderFlag==1 || pdReturnPurchase.orderFlag==2)}">
<%-- 						<input type="button" class="button" name="handButton"   onclick="handPdReturnPurchase()" value="<jecs:locale  key='${checkButtonKey}'/>" />
 --%>					
                            <button type="button" class="btn btn_sele"  name="handButton" onclick="handPdReturnPurchase()" ><jecs:locale  key='${checkButtonKey}'/></button>
					</c:when>
				</c:choose>
					
		
		</jecs:power>
			
			
		<c:if test="${!disabled}">
			
					<jecs:power powerCode="addPdReturnPurchase">
							<c:if test="${param.strAction=='addPdReturnPurchase'}">
<%-- 							<input type="button" class="button" name="save"  onclick="savePdReturnPurchase();" value="<jecs:locale key="button.next"/>" />
 --%>						
                                <button type="button" class="btn btn_sele"  name="save" onclick="savePdReturnPurchase();" ><jecs:locale key="button.next"/></button>
                                
						</c:if>
					</jecs:power>
					
					
					
		</c:if>
		
		<c:if test="${(param.strAction=='checkPdReturnPurchase')&&(pdReturnPurchase.orderFlag==0 || pdReturnPurchase.orderFlag==1)}">
<%-- 		 <input type="submit" class="button" name="ret" onclick="$('strAction').value='toNewPdReturnPurchase',bCancel=true;" value="<jecs:locale key="button.cancelToNew"/>" />
 --%>	
             <button type="submit" class="btn btn_sele"  name="ret" onclick="$('strAction').value='toNewPdReturnPurchase',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
		</c:if>
<%-- 		<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
 --%>        
           <button type="button" class="btn btn_sele"  name="back" onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="pdReturnPurchase.*">
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
<form:form commandName="pdReturnPurchase" method="post" action="editPdReturnPurchase.html" onsubmit="return validatePdReturnPurchase(this)" id="pdReturnPurchaseForm">


<input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>



<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="rpNo"/>

<form:hidden path="orderFlag" id="orderFlag"/>
<form:hidden path="amount" id="amount"/>    

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="customerRecord.customerNo"/></span>
    </th>
    <td>
        <!--form:errors path="agentNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="customer.userCode" disabled="${disabledFlag}" id="customCode"  cssClass="textbox-text"/></span>
    </td>
    <th>
        <span class="text-font-style-tc"><jecs:label  key="linkman.name"/></span>
    </th>
    <td style="white-space:nowrap;">
         <span class="textbox"><input type="text" name="customName" disabled="${sendDisabled}" id="customName" class="textbox-text" style="height:21px;" size="25"/></span>
        <img src="<c:url value="/images/l_1.gif"/>" id="person" <c:if test="${disabledFlag}">disabled="true"</c:if> onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="sys.user.search"/>"/> 
    </td></tr>

   

    

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="pdReturnPurchase.returnWarehouseNo"/></span>
    </th>
    <td>
        <!--form:errors path="returnWarehouseNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="returnWarehouseNo"   id="returnWarehouseNo" cssClass="textbox-text"/></span>
<%--     		<input type="button" class="button" name="select" <c:if test="${disabledFlag}">disabled="true"</c:if> onclick="selectWarehouse('returnWarehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>    
                <button type="button" class="btn btn_sele"  name="select" <c:if test="${disabledFlag}">disabled="true"</c:if> onclick="selectWarehouse('returnWarehouseNo');" ><jecs:locale key="button.select"/></button>
     </td>
	     <th>
	        <span class="text-font-style-tc"><jecs:label  key="pdReturnPurchase.returnType"/></span>
	    </th>
	    <td>
	        <span class="textbox"><jecs:list name="returnType" listCode="pdreturn.returntype" defaultValue="" value="${pdReturnPurchase.returnType}"  id="returnType" showBlankLine="" styleClass="textbox-text" ></jecs:list></span>
	    </td>
    </tr>
    
   

		<tr class="edit_tr"><th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea  path="remark" disabled="${disabledFlag}" id="remark" cssClass="textarea_border"/></span>
    </td></tr>
     

<c:choose>
		<c:when test="${checkFlag ==1}">
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc-tare"><jecs:label  key="pd.checkRemark"/></span>
	    </th>
	    <td colspan="3">
		     <c:choose>
			      <c:when test="${param.strAction=='checkPdReturnPurchase'}">
			           <span class="text-font-style-tc-right"><form:textarea  path="checkRemark"  id="checkRemark" cssClass="textarea_border"/></span>
			      </c:when>
			      <c:otherwise>
			           <span class="text-font-style-tc-right"><form:textarea  path="checkRemark"  disabled="${disabledFlag}" id="checkRemark" cssClass="table_textarea"/></span>
			      </c:otherwise>
		    </c:choose>
	    </td>
    </tr>
    </c:when>
		<c:when test="${checkFlag ==2}">

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc-tare"><jecs:label  key="pd.financeRemark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="financeRemark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea  path="financeRemark" id="financeRemark" cssClass="textarea_border"/></span>
    </td></tr>
		</c:when>
		<c:when test="${checkFlag ==3}">
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc-tare"><jecs:label  key="pd.okRemark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="okRemark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea path="okRemark" id="okRemark" cssClass="textarea_border" /></span>
    </td></tr>
		</c:when>
</c:choose>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="shipping.firstName"/></span>
    </th>
    <td>
        <span class="textbox"><form:input path="firstName" id="firstName" cssClass="textbox-text"/></span>
    </td>
    <th>
         <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="shipping.lastName"/></span>
    </th>
    <td>
        <span class="textbox"><form:input path="lastName" id="lastName" cssClass="textbox-text"/></span>
    </td></tr>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="shipping.province"/></span>
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
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="shipping.city"/></span>
    </th>
    <td>
    	 
      <span class="textbox"><select name="city" id="city" onchange="getDistrict()" class="textbox-text">
			<option value=""><jecs:locale key="list.please.select"/></option>
			</select></span>
	 
    </td></tr>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="shipping.district"/></span>
    </th>
    <td>
        <span class="textbox"><select name="district" id="district" class="textbox-text">
						<option value=""><jecs:locale key="list.please.select"/></option>
				</select></span>	
    </td>
    
    <th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="shipping.postalcode"/></span>
    </th>
    <td>
        <span class="textbox"><form:input path="postalcode" id="postalcode" cssClass="textbox-text" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="10"/></span>
    </td>
    </tr>
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="shipping.address"/></span>
	    </th>
	    <td colspan="3">
	        <form:input path="address" id="address" cssClass="textbox"  size="80" cssStyle="width:500px;"/>
	    </td>
    </tr>
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="miMember.phone"/></span>
    </th>
    <td>
         <span class="textbox"><form:input path="phone" id="phone" cssClass="textbox-text" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="20"/></span>
    </td><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele"/></span>
    </th>
    <td>
        <span class="textbox"><form:input path="mobiletele" id="mobiletele" cssClass="textbox-text" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="20"/></span>
    </td>
    </tr>

    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
    </th>
    <td align="left">
        <span class="textbox"><form:input path="email" id="email" cssClass="textbox-text" onkeyup="this.value=this.value.replace(^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$,'')" maxlength="30"/></span>
    </td>
    </tr>
    <tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
    
   </tbody>
    
</table>

</form:form>



<div class="searchBar">
	
	<c:if test="${(checkFlag=='0') && (!disabledFlag)}">
		
		<form:form commandName="pdReturnPurchaseDetail" method="post" action="editPdReturnPurchaseDetail.html?strAction=addPdReturnPurchaseDetail&rpNo=${rpNo}" onsubmit="return validatePdReturnPurchaseDetail(this)" id="pdReturnPurchaseDetailForm">
				
				
				<table class="detail" width="70%">
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
						<input type="text" name="productNo" id="productNo" />
						
<%-- 						<input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" />
 --%>						
                        <button type="button" class="btn btn_sele" name="select"  onclick="selectProduct()" ><jecs:locale key="button.select"/></button>
                        
                        <input type="text" name="productName" id="productName" style="width:300px;"/>
                        </td>
						<td align="center">
						<input type="text" name="qty" id="qty" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
						<!--td><input type="text" name="price" id="price"/></td-->
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


<div class="divClass">
<!--iframe src="<c:url value='/pdEnterWarehouseDetails.html?ewNo=${ewNo}'/>" width="100%"></iframe-->


		
<c:if test="${strAction != 'addPdReturnPurchase'}">
				
		

	
	<ec:table 
	tableId="pdReturnPurchaseDetailListTable"
	items="pdReturnPurchaseDetailList"
				 
	var="pdReturnPurchaseDetail"
	
	action="${pageContext.request.contextPath}/editPdReturnPurchase.html"
	width="70%"
	rowsDisplayed="20" sortable="true" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" styleClass="detail">
				<ec:row >
					<ec:parameter name="strAction" value="${param.strAction}"/>
					<ec:parameter name="rpNo" value="${rpNo}"/>
					<c:if test="${pdReturnPurchase.orderFlag == -1}">
						<ec:column property="edit" title="title.operation" sortable="false">
								<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
									onclick="javascript:editPdReturnPurchaseDetail('${pdReturnPurchaseDetail.uniNo}')"
									 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
						</ec:column>
    			</c:if>
    			<ec:column property="productNo" title="busi.product.productno" />
    			<ec:column property="productName" title="pmProduct.productName" >
    				${compamyProductMap[pdReturnPurchaseDetail.productNo]}
    			</ec:column>
    			<ec:column property="price" title="pd.price" styleClass="numberColumn"/>
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn"/>
    			
				</ec:row>

</ec:table>
	
</c:if>
</div>


<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdReturnPurchase')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->


<script type="text/javascript">
    //Form.focusFirstElement($('pdReturnPurchaseForm'));
    
    
    function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
    function addPdReturnPurchaseDetail(){
    				window.location="<c:url value='/editPdReturnPurchaseDetail.html'/>?strAction=addPdReturnPurchaseDetail&rpNo=${rpNo}";
    	}
    
    function editPdReturnPurchaseDetail(uniNo){
   		<jecs:power powerCode="editPdReturnPurchase">
					window.location="<c:url value='/editPdReturnPurchaseDetail.html'/>?strAction=editPdReturnPurchaseDetail&uniNo="+uniNo;
			</jecs:power>
		}
    function savePdReturnPurchase(){
    	waiting();
    			if($('customCode').value=='' || $('customCode').value==null){
    					alert('<jecs:locale  key="customerRecord.customerNo"/> <jecs:locale  key="poAssistMemberOrder.notNUll"/>');
    					waitingEnd();
    					return;
    				}
					//$('orderFlag').value='-1';
					if(validatePdReturnPurchase($('pdReturnPurchaseForm'))){
							$('pdReturnPurchaseForm').submit();
						}
					
    	}
    function handPdReturnPurchase(){
    		waiting();
    		if($('customCode').value=='' || $('customCode').value==null){
    					alert('<jecs:locale  key="busi.product.productno"/> <jecs:locale  key="errors.required"/>');
    					waitingEnd();
    					return;
    				}
    		
    		<c:choose>
    	      
    	      	<c:when test="${checkFlag ==-1}">
								$('strAction').value='addPdReturnPurchase';
								//$('orderFlag').value="0";
								
						</c:when>
						
						<c:when test="${checkFlag ==0}">
								$('strAction').value='submitPdReturnPurchase';
								//$('orderFlag').value="0";
								
						</c:when>
						
						<c:when test="${checkFlag ==1}">
								$('strAction').value='checkPdReturnPurchase';
								//$('orderFlag').value="1";
								
						</c:when>
						<c:when test="${checkFlag ==2}">
								$('strAction').value='financePdReturnPurchase';
								//$('orderFlag').value="2";
								
						</c:when>
						<c:when test="${checkFlag ==3}">
								$('strAction').value='confirmPdReturnPurchase';
								//$('orderFlag').value="3";
								
						</c:when>
				</c:choose>		
    			
    			
    			if(validatePdReturnPurchase($('pdReturnPurchaseForm'))){
    					$('pdReturnPurchaseForm').submit();
    				}
    			
    			
    	}
    	
    	function toback(str){
			
				if(str == 'addPdReturnPurchase'){
						str = 'editPdReturnPurchase';
					}
    		this.location='<c:url value="/pdReturnPurchases.html"/>?strAction='+str;
    	}
    	
    	function selectProduct(){
    		open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
    	
    	function searchUser(){
    			var userCodeExample = $('customCode').value;
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,"","dialogHeight:400px;dialogWidth:1000px;");
    			if(ret != null){
    					$('customCode').value=ret['userCode'];
    					$('customName').value=ret['userName'];
    				}
    			
    			
    		}
    		
    	function validatePdReturnPurchaseDetail(form){
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
    			 	
    			
    			 
    			 if (fields.length > 0) {
			       
			       alert(fields.join('\n'));                                                                      
			    }  
			    return ret;
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
		dwr.util.setValue('city','${pdReturnPurchase.city}');
    	
		
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
		dwr.util.setValue('district','${pdReturnPurchase.district}');
		
		/**
    	districtElemment.options.length=0;
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
		for (var i=0;i<valid.length;i++) {
			var districtId= valid[i].districtId;	   
			var districtName=valid[i].districtName;   
			var o=new Option(districtName,districtId);
			if(districtId=='${pdReturnPurchase.district}'){
					o.setAttribute("selected","selected")
				}
			districtElemment.options.add(o);  
		}
		
		 districtElemment=document.getElementById('district');
			for (var i=0;i<districtElemment.options.length;i++) {
			
					if (districtElemment.options[i].value == '${pdReturnPurchase.district}' ) {  
						districtElemment.options[i].selected=true;
						break;
					}
				
			}**/
	}
	<c:if test="${not empty pdReturnPurchase.province}">
		
		getCity();
	</c:if>
</script>

<v:javascript formName="pdReturnPurchase" cdata="false" dynamicJavascript="true" staticJavascript="false"/>

<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
