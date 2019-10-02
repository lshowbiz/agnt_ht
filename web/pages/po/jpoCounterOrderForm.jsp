<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="poCounterOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="poCounterOrderDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
			<c:choose>
				<c:when test="${(param.strAction=='editPoCounterOrder')&&(jpoCounterOrder.csStatus==-1)}">
					    <button type="button" class="btn btn_sele" name="handButton"  onclick="handPoCounterOrder()" ><jecs:locale  key='${checkButtonKey}'/></button>
<%-- 					<input type="button" class="button" name="handButton"   onclick="handPoCounterOrder()" value="<jecs:locale  key='${checkButtonKey}'/>" />
 --%>				
				</c:when>
				<c:when test="${(param.strAction=='paymentPoCounterOrder')&&(jpoCounterOrder.csStatus==0)}">
	                   <button type="submit" class="btn btn_sele" name="ret"  onclick="$('strAction').value='toNewPoCounterOrder',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
					   <button type="button" class="btn btn_sele" name="handButton"  onclick="handPoCounterOrder()" ><jecs:locale  key='${checkButtonKey}'/></button>
					<%-- <input type="submit" class="button" name="ret" onclick="$('strAction').value='toNewPoCounterOrder',bCancel=true;" value="<jecs:locale key="button.cancelToNew"/>" />
					<input type="button" class="button" name="handButton"   onclick="handPoCounterOrder()" value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
				
				</c:when>
				<c:when test="${(param.strAction=='repealPoCounterOrder')&&(jpoCounterOrder.csStatus==2)}">
				        <button type="button" class="btn btn_sele" name="handButton"  onclick="handPoCounterOrder()" ><jecs:locale  key='${checkButtonKey}'/></button>
<%-- 					<input type="button" class="button" name="handButton"   onclick="handPoCounterOrder()" value="<jecs:locale  key='${checkButtonKey}'/>" />
 --%>				</c:when>
				<c:when test="${(param.strAction=='shipPoCounterOrder')&&(jpoCounterOrder.csStatus==1)}">
						<button type="button" class="btn btn_sele" name="handButton"  onclick="handPoCounterOrder()" ><jecs:locale  key='${checkButtonKey}'/></button>
				
<%-- 					<input type="button" class="button" name="handButton"   onclick="handPoCounterOrder()" value="<jecs:locale  key='${checkButtonKey}'/>" />
 --%>				
               </c:when>
			</c:choose>
			
		</jecs:power>
	<c:if test="${!disabledFlag}">
		<jecs:power powerCode="${param.strAction}">
				<c:if test="${checkFlag==-1 }">
					<button type="submit" class="btn btn_sele" name="save"  onclick="$('csStatus').value='-1';bCancel=false" ><jecs:locale key="button.next"/></button>
				
<%-- 				<input type="submit" class="button" name="save"  onclick="$('csStatus').value='-1';bCancel=false" value="<jecs:locale key="button.next"/>" />
 --%>				
				</c:if>
				<c:if test="${checkFlag!=-1 }">
					<button type="submit" class="btn btn_sele" name="save"  onclick="$('csStatus').value='-1';bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				
<%-- 				<input type="submit" class="button" name="save"  onclick="$('csStatus').value='-1';bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
 --%>				
				</c:if>
				
				
		</jecs:power>
		<jecs:power powerCode="deletePoCounterOrder">
			<c:if test="${strAction=='editPoCounterOrder'}">
					<button type="button" class="btn btn_sele" name="delete"  onclick="deleteMe();" ><jecs:locale key="operation.button.delete"/></button>
<%-- 				<input type="button" class="button" name="delete" onclick="deleteMe();" value="<jecs:locale key="operation.button.delete"/>" />
 --%>			
			</c:if>
		</jecs:power>
	</c:if>
	   		<button type="button" class="btn btn_sele" name="back"  onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
	   	
<%-- 		<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
 --%>	
</c:set>



<spring:bind path="jpoCounterOrder.*">
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

<form:form commandName="jpoCounterOrder" method="post" action="editJpoCounterOrder.html" onsubmit="return validateJpoCounterOrder(this)" id="jpoCounterOrderForm">


<input type="hidden" id="strAction" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%" >
<tbody class="window" >
<form:hidden path="coNo"/>
<form:hidden path="csStatus"/>
   
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="pd.agentormember"/></span>
	    </th>
	    <td>
	        <!--form:errors path="userCode" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="sysUser.userCode" disabled="${disabledFlag}" id="userCode" cssClass="textbox-text"/></span>
	    <%-- <span class="text-font-style-tc"><jecs:label  key="sys.user.username"/></span>
	    <input type="text" name="agentName" disabled="${sendDisabled}" id="agentName" />
	        	<img src="<c:url value="/images/l_1.gif"/>" id="person" 
	        		<c:if test="${disabledFlag}">disabled="true"</c:if>
	        		 onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="sys.user.search"/>"/>  --%>
	    </td>
	    <th>
	          <span class="text-font-style-tc"><jecs:label  key="sys.user.username"/></span>
	    </th>
        <td>
              <span class="textbox"><input type="text" name="agentName" disabled="${sendDisabled}" id="agentName" class="textbox-text"/></span>
              <img src="<c:url value="/images/l_1.gif"/>" id="person" 
	        		<c:if test="${disabledFlag}">disabled="true"</c:if>
	        		 onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="sys.user.search"/>"/> 
        </td>
    </tr>

    <tr class="edit_tr">
        <th>
	        <span class="text-font-style-tc"><jecs:label  key="poCounterOrder.outWatehouseNo"/></span>
	    </th>
	    <td>
	        <!--form:errors path="outWatehouseNo" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="warehouseNo" disabled="${disabledFlag}"     id="warehouseNo" cssClass="textbox-text"/></span>
            <button type="button" class="btn btn_sele" name="select"  onclick="selectWarehouse('warehouseNo');" ><jecs:locale key="button.select"/></button>
<%-- 	        	<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>	    
       </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="busi.finance.amount"/></span>
	    </th>
	    <td align="left">
	        <!--form:errors path="amount" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="amount" id="amount" readonly="true"  cssClass="textbox-text" /></span>
	    </td>
    </tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
	    </th>
	    <td colspan="3">
	        <!--form:errors path="remark" cssClass="fieldError"/-->
	       <span class="text-font-style-tc-right"><form:textarea path="remark" disabled="${disabledFlag}" id="remark" cssClass="textarea_border"/></span>
	    </td>
    </tr>
    <tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
    </tr>
</tbody>
</table>



<div class="divClass">

		
			

	
	<ec:table 
	tableId="jpoCounterOrderDetailListTable"
	items="jpoCounterOrderDetailList"
	var="poCounterOrderDetail"
	action="${pageContext.request.contextPath}/editJpoCounterOrder.html"
	width="70%"
	
	showPagination="false"
	
	 sortable="true" imagePath="./images/extremetable/*.gif" styleClass="detail">
	
				
				<ec:row>
					<c:if test="${!disabledFlag}">
					<ec:column property="edit" title="title.operation" sortable="false">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPoCounterOrderDetail('${poCounterOrderDetail.codNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					</c:if>
					
						
    			<ec:column property="jpmProductSaleNew.jpmProduct.productNo" title="busi.product.productno" />
    			<ec:column property="jpmProductSaleNew.productName" title="pmProduct.productName" />
    				
    			
    			
    			<ec:column property="price" title="pd.price" styleClass="numberColumn" style="text-align:center;" >
    				<span class="textbox"><input type="text" name="<c:out value='${poCounterOrderDetail.codNo}'/>~price" 
    					id="<c:out value='${poCounterOrderDetail.codNo}'/>~price"
    					value="${poCounterOrderDetail.price}" readOnly="true" class="textbox" style="text-align:center;"/></span>
    				
    			</ec:column>
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn" style="text-align:center;">
    				<span class="textbox"><input type="text" name="<c:out value='${poCounterOrderDetail.codNo}'/>~qty" 
    					id="<c:out value='${poCounterOrderDetail.codNo}'/>~qty"
    					value="${poCounterOrderDetail.qty}"  
    					<c:if test="${disabledFlag}"> readOnly="true" class="textbox" </c:if>
    					style="text-align:center;"/></span>
    			</ec:column>
				</ec:row>

</ec:table>	
	

</div>
</form:form>

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PoCounterOrder')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->




<script type="text/javascript">
    Form.focusFirstElement($('jpoCounterOrderForm'));

    
    function addPoCounterOrderDetail(){
    			window.location="<c:url value='/editPoCounterOrderDetail.html'/>?strAction=addPoCounterOrderDetail&poCounterOrder.coNo=${coNo}";
    	}
    
    function waiting(){
		disableButton();
		var o = document.createElement('iframe');
		o.id = 'fram_bk';
		document.body.appendChild(o);
		with ($('fram_bk').style){
			display='block';
			top = "60px";
			left = "30%";
		}
		var el=document.getElementById("sending_sub");
		if (el.hasChildNodes()){
		  el.removeChild(el.lastChild);
		}
		
		el.appendChild( document.createTextNode('<jecs:locale key="button.loading"/>'));
		document.getElementById("sending").style.visibility="visible";
		document.getElementById("sending").style.display="block";
	}	
    	
    function handPoCounterOrder(){
    	   waiting();
    			<c:choose>
    	      
    	      	<c:when test="${checkFlag ==-1}">
								$('strAction').value='addPoCounterOrder';
								$('csStatus').value="0";
								
						</c:when>
						
						<c:when test="${checkFlag ==0}">
								$('strAction').value='submitPoCounterOrder';
								$('csStatus').value="0";
								
						</c:when>
						
						<c:when test="${checkFlag ==1}">
								$('strAction').value='paymentPoCounterOrder';
								
								
						</c:when>
						<c:when test="${checkFlag ==2}">
								$('strAction').value='repealPoCounterOrder';
								
								
						</c:when>
						<c:when test="${checkFlag ==3}">
								$('strAction').value='shipPoCounterOrder';
								
								
						</c:when>
						<c:when test="${checkFlag ==-2}">
								
								return;
						</c:when>
				</c:choose>		
				
				//alert($('strAction').value);
				if(validateJpoCounterOrder($('jpoCounterOrderForm'))){
    			$('jpoCounterOrderForm').submit();
    			}
    	}	
    	
   function editPoCounterOrderDetail(codNo){
   	<c:if test="${!disabledFlag}">
   		<jecs:power powerCode="editPoCounterOrder">
   		
					window.location="editJpoCounterOrderDetail.html?strAction=editPoCounterOrderDetail&codNo="+codNo;
			
			</jecs:power>
			</c:if>
		}
		
		function deleteMe(){
			
				$('strAction').value='deletePoCounterOrder';
				$('jpoCounterOrderForm').submit();
			}
		
		function toback(str){
			
				if(str == 'addPoCounterOrder'){
						str = 'editPoCounterOrder';
					}
    		this.location='<c:url value="/jpoCounterOrders.html"/>?strAction='+str;
    	}
    	
    	function selectWarehouse(elementId){
     			open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&userCode=${sessionScope.sessionLogin.userCode}&elementId="+elementId,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
     	
     	
     	function searchUser(){
     		
     		var userCodeExample = $('userCode').value;
     		
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,null,"dialogHeight:400px;dialogWidth:1000px;");
    			
    			$('userCode').value=ret['userCode'];
    			$('agentName').value=ret['userName'];
    			
    		
    			
    		}
    		
    		function selectProduct(){
    			//var discount = $('discount').value;
    			//var discountFlag = $('discountFlag').value;
    			open("<c:url value='/pmProductSales.html?strAction=selectProduct&selectControl=assist&discount='/>"+discount+"&discountFlag="+discountFlag,"","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    			//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
    	
    	function validatePoCounterOrderDetail(form){
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
    			 	if(form['price'].value==''){
    			 	  ret = false;
    			 	  fields[i++] = '<jecs:locale  key="pd.price"/> <jecs:locale  key="errors.required"/>';
    			 	}
    			
    			 
    			 if (fields.length > 0) {
			       
			       alert(fields.join('\n'));                                                                      
			    }  
			    return ret;
    		}
</script>

<v:javascript formName="jpoCounterOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>

<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
