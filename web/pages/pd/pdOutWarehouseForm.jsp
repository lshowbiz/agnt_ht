<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:label key="pdOutWarehouseDetail.title" />
</title>
<content tag="heading">
<jecs:label key="pdOutWarehouseDetail.heading" />
</content>
<script type='text/javascript' src='<c:url value="/dwr/interface/pdWarehouseStockManager.js"/>'></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<spring:bind path="pdOutWarehouse.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="./images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>
<c:if test="${not empty errorMsgs}">
	<div class="error">
		<c:forEach var="error" items="${errorMsgs}">
			<img src="<c:url value="/images/iconWarning.gif"/>"
				alt="<<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
		<c:remove var="errorMsgs" scope="session" />
	</div>
</c:if> 
<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<c:choose>

			<c:when
				test="${(param.strAction=='editPdOutWarehouse')&&(pdOutWarehouse.orderFlag==-1)}">
				<%-- <input type="button" class="button" style="margin-right: 5px"
					onclick="checkOutWarehouse('${checkFlag}');"
					value="<jecs:label  key='button.submit'/>" /> --%>
				<button type="button" class="btn btn_sele"   onclick="checkOutWarehouse('${checkFlag}');" ><jecs:locale  key='button.submit'/></button>
			</c:when>
			<c:when
				test="${(param.strAction=='checkPdOutWarehouse')&&(pdOutWarehouse.orderFlag==0)}">
				<%-- <input type="button" class="button" style="margin-right: 5px"
					onclick="checkOutWarehouse('${checkFlag}');"
					value="<jecs:label  key='${checkButtonKey}'/>" /> --%>
				<button type="button" class="btn btn_sele"   onclick="checkOutWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
			</c:when>
			<c:when
				test="${(param.strAction=='confirmPdOutWarehouse')&&(pdOutWarehouse.orderFlag==1)}">
				<%-- <input type="button" class="button" style="margin-right: 5px"
					onclick="checkOutWarehouse('${checkFlag}');"
					value="<jecs:label  key='${checkButtonKey}'/>" /> --%>
				<button type="button" class="btn btn_sele"   onclick="checkOutWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
			</c:when>
		</c:choose>

	</jecs:power>

	<c:if test="${param.strAction=='addPdOutWarehouse'}">
		 <jecs:power powerCode="addPdOutWarehouse">
			<%--<input type="button" class="button" name="save"
				onclick="savePdOutWarehouse()"
				value="<jecs:label key="button.next"/>" /> --%>
		<button type="button" class="btn btn_sele"  name="save" onclick="savePdOutWarehouse()" ><jecs:locale key="button.next"/></button>
		</jecs:power>
	</c:if>


	<c:if
		test="${!disabledFlag && (param.strAction=='editPdOutWarehouse')}">
		<jecs:power powerCode="${param.strAction}">
			<%-- <input type="button" class="button" name="save"
				onclick="savePdOutWarehouse()"
				value="<jecs:label key="operation.button.save"/>" /> --%>
		<button type="button" class="btn btn_sele"  name="save" onclick="savePdOutWarehouse()" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>

		<c:if test="${param.strAction=='editPdOutWarehouse'}">
			<%-- <input type="submit" class="button" name="delete"
				onclick="$('strAction').value='deletePdOutWarehouse',bCancel=true;return confirmDelete('PdOutWarehouse')"
				value="<jecs:label key="operation.button.delete"/>" /> --%>
			<button type="submit" class="btn btn_sele"  name="delete" onclick="$('strAction').value='deletePdOutWarehouse',bCancel=true;return confirmDelete('PdOutWarehouse')" ><jecs:locale key="operation.button.delete"/></button>
		</c:if>
	</c:if>

	<c:if
		test="${(param.strAction=='checkPdOutWarehouse')&&(pdOutWarehouse.orderFlag==1 || pdOutWarehouse.orderFlag==0)}">
		<%-- <input type="submit" class="button" name="tonew"
			onclick="$('strAction').value='toNewPdOutWarehouse',bCancel=true;"
			value="<jecs:label key="button.cancelToNew"/>" /> --%>
		<button type="submit" class="btn btn_sele"  name="tonew" onclick="$('strAction').value='toNewPdOutWarehouse',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
	</c:if>

	<%-- <input type="button" class="button" name="back"
		onclick="toback('${param.strAction}');"
		value="<jecs:label  key="operation.button.return"/>" /> --%>
	<button type="button" class="btn btn_sele"  name="back" onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
	

</c:set>
<form:form commandName="pdOutWarehouse" method="post"
	action="editPdOutWarehouse.html"
	onsubmit="return validatePdOutWarehouse(this)" id="pdOutWarehouseForm">


	<table class='detail' width="70%">
	<tbody class="window" >
		<input type="hidden" id="strAction" value="${param.strAction}"
			name="strAction" />
		<form:hidden path="owNo" />






		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.warehouse.outwatehouseno" /></span>
			</th>
			<td>
				<!--form:errors path="warehouseNo" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="warehouseNo" disabled="${disabledFlag}"
					readonly="true" id="warehouseNo" cssClass="textbox-text" /></span>
				<%-- <input type="button" class="button"
					<c:if test="${disabledFlag}">disabled="true"</c:if> name="select"
					onclick="selectWarehouse('warehouseNo');"
					value="<jecs:label key="button.select"/>" /> --%>
				<button type="button" class="btn btn_sele"  <c:if test="${disabledFlag}">disabled="true"</c:if> name="select" onclick="selectWarehouse('warehouseNo');" ><jecs:locale key="button.select"/></button>
			</td>
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.owtNo" /></span>
			</th>
			<td align="left">
				<!--form:errors path="astNo" cssClass="fieldError"/-->


				<span class="textbox"><jecs:list listCode="owtnolist" name="owtNo" id="owtNo"
					value="${pdOutWarehouse.owtNo}" defaultValue="" styleClass="textbox-text" /></span>

			</td>
		</tr>
<tr class="edit_tr">
		<!-- Add By WuCF 20130515 -->
		<c:if
			test="${sessionScope.sessionLogin.companyCode !='US' && sessionScope.sessionLogin.companyCode!='JP'}">
			<!-- <tr class="edit_tr"> -->
				<th>
					<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.recipientName" /></span>
				</th>
				<td>
					<!--form:errors path="recipientName" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="recipientName" readonly="${disabledFlag}"
						id="recipientName" cssClass="textbox-text" /></span>
				</td>
			<!-- </tr> -->
		</c:if>
		<th>
				<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.recipientZip" /></span>
			</th>
			<td>
				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientZip" readonly="${disabledFlag}"
					id="recipientZip" cssClass="textbox-text" /></span>
				<c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
					<%-- <input type="button" class="button" onclick="getPostalcode();"
						value="<jecs:label key="operation.button.search"/>" /> --%>
				<button type="button" class="btn btn_sele" onclick="getPostalcode();"><jecs:label key="operation.button.search"/></button>
				</c:if>
			</td>
</tr>
		<c:if
			test="${sessionScope.sessionLogin.companyCode=='US' || sessionScope.sessionLogin.companyCode=='JP'}">

			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="shipping.firstName" /></span>
				</th>
				<td>
					<!--form:errors path="recipientName" cssClass="fieldError"/-->
					<form:hidden path="recipientName" />
					<span class="textbox"><form:input path="recipientFirstName" readonly="${disabledFlag}"
						id="recipientFirstName" cssClass="textbox-text" /></span>
				</td>
				<th>
					<span class="text-font-style-tc"><jecs:label key="shipping.lastName" /></span>
				</th>
				<td>
					<!--form:errors path="recipientName" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="recipientLastName" readonly="${disabledFlag}"
						id="recipientLastName" cssClass="textbox-text"/></span>
				</td>
			</tr>
		</c:if>
		<tr class="edit_tr">
			
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.stateProvinceName" /></span>
			</th>
			<td align="left">
				<c:if test="${disabledFlag}">
    		<span class="textbox">${alStateProvinceMap[pdOutWarehouse.recipientCaNo]}</span>
					<form:hidden path="recipientCaNo" />
				</c:if>
				<c:if test="${!disabledFlag}">
					<span class="textbox"><select name="recipientCaNo" onchange="getCity()" class="textbox-text">
						<option value=""></option>
						<c:forEach items="${alStateProvinces}" var="alStateProvince">
							<option value="${alStateProvince.stateProvinceId}"
								<c:if test="${alStateProvince.stateProvinceId eq pdOutWarehouse.recipientCaNo}">selected</c:if>>
								${alStateProvince.stateProvinceName}
							</option>
						</c:forEach>

					</select></span>
				</c:if>



				<!--form:input path="recipientCaNo" readonly="${disabledFlag}" id="recipientCaNo" cssClass="text medium"/-->
			</td>
			<th>
				<span class="text-font-style-tc"><jecs:label key="jfiUsCreditCardLog.city" /></span>
			</th>
			<td>

				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<c:if test="${disabledFlag}">
        	<span class="textbox">${alCityMap[pdOutWarehouse.city]}</span>
        	<form:hidden path="city" />
				</c:if>

				<c:if test="${!disabledFlag}">
					<span class="textbox"><select name="city" id="city"  class="textbox-text">
						<option value="">
							<jecs:locale key="list.please.select" />
						</option>
					</select></span>

				</c:if>

				<!--form:input path="city" readonly="${disabledFlag}" id="city" cssClass="text medium"/-->
			</td>
		</tr>
		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.recipientAddr" /></span>
			</th>
			<td colspan="3">
				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<form:input path="recipientAddr" readonly="${disabledFlag}"
					id="recipientAddr" cssClass="textbox"  size="80" cssStyle="width:520px;" />
			</td>
		</tr>
		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="shipping.phone" /></span>
			</th>
			<td>
				<!--form:errors path="recipientPhone" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientPhone" readonly="${disabledFlag}"
					id="recipientPhone" cssClass="textbox-text"  /></span>
			</td>

			<th>
				<span class="text-font-style-tc"><jecs:label key="aiAgent.bossPhone2" /></span>
			</th>
			<td>
				<!--form:errors path="recipientPhone" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientMobile" readonly="${disabledFlag}"
					id="recipientMobile" cssClass="textbox-text" /></span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc-tare"><jecs:label key="busi.common.remark" /></span>
			</th>
			<td colspan="3">
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="text-font-style-tc-right"><form:textarea path="remark"
					disabled="${disabledFlag}" id="remark" cssClass="textarea_border" /></span>
			</td>
		</tr>




		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.user.check" /></span>
			</th>
			<td>
				<!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="checkUsrCode" disabled="true" id="checkUsrCode"
					cssClass="textbox-text"/></span>
			</td>

			<th>
				<span class="text-font-style-tc"><jecs:label key="pd.confirmUserNo" /></span>
			</th>
			<td>
				<span class="textbox">${pdOutWarehouse.okUsrCode}&nbsp;${pdOutWarehouse.okTime}</span>


			</td>
		</tr>





		<c:choose>
			<c:when test="${checkFlag ==1}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.checkRemark" /></span>
					</th>
					<td colspan="3">
						<!--form:errors path="checkRemark" cssClass="fieldError"/-->
						<span class="text-font-style-tc-right"><form:textarea path="checkRemark"
							id="checkRemark" cssClass="textarea_border"  /></span>
					</td>
				</tr>
			</c:when>
			<c:when test="${checkFlag ==2}">

				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.okRemark" /></span>
					</th>
					<td colspan="3">
						<!--form:errors path="okRemark" cssClass="fieldError"/-->
						<span class="text-font-style-tc-right"><form:textarea  path="okRemark" id="okRemark"
							cssClass="textarea_border"/></span>
					</td>
				</tr>

			</c:when>
		</c:choose>

		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.common.trackingno" /></span>
			</th>
			<td>
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="trackingNo" id="trackingNo" cssClass="textbox-text" /></span>
			</td>
		</tr>

		<tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false" />
			</td>
		</tr>
     <!--  <tr  height="25px"><th></th><td></td><th></th><td></td></tr> -->
    </tbody>
	</table>
</form:form>



<div class="searchBar">

	<c:if test="${checkFlag=='0' && !disabledFlag}">
		<form:form commandName="pdOutWarehouseDetail" method="post"
			action="editPdOutWarehouseDetail.html?strAction=addPdOutWarehouseDetail&owNo=${owNo}" onsubmit="return validatePdOutWarehouseDetail(this)" id="pdOutWarehouseDetailForm">

            

			<table class='detail' width="70%">
			<tbody class="window">
				<tr style="height:20px;">
					<td align="center" style="background: #ECECEC;">
						<jecs:locale key="busi.product.productno" />
					</td>
					<td align="center" style="background: #ECECEC;">
						<jecs:locale key="pd.qty" />
					</td>
				</tr>
				<tr class="edit_tr">
					<td align="center">
						<input type="text" name="productNo" id="productNo" />
						<%-- <input type="button" class="button" name="select"
							onclick="selectProduct()"
							value="<jecs:label key="button.select"/>" /> --%>
							
						<button type="button" class="btn btn_sele" name="select"  onclick="selectProduct()" ><jecs:locale key="button.select"/></button>
						
						<input type="text" name="productName" id="productName" style="width:300px;"/>
					</td>	
					<td align="center">
						<input type="text" name="qty" id="qty" />
						<%-- <input type="submit" class="button" name="add"
							value="<jecs:label key="operation.button.add"/>" /> --%>
						<button type="submit" class="btn btn_sele" name="add"><jecs:locale key="operation.button.add"/></button>
					</td>
				</tr>
			</tbody>
			</table>
		</form:form>
	</c:if>
</div>



<c:if test="${strAction != 'addPdOutWarehouse'}">
	<div id="pdOutWarehouseDetailId">
		<!--iframe src="<c:url value='/pdEnterWarehouseDetails.html?ewNo=${ewNo}'/>" width="100%"></iframe-->





		<ec:table tableId="pdOutWarehouseDetailListTable"
			items="pdOutWarehouseDetailList" var="pdOutWarehouseDetail"
			action="${pageContext.request.contextPath}/editPdOutWarehouse.html"
			width="70%" showPagination="false" sortable="true"
			imagePath="./images/extremetable/*.gif"  styleClass="detail">
			<ec:row>
				<ec:parameter name="strAction" value="${param.strAction}" />
				<ec:parameter name="owNo" value="${owNo}" />
				<c:if
					test="${(param.strAction=='editPdOutWarehouse')&&(pdOutWarehouse.orderFlag==-1)}">
					<ec:column property="edit" title="title.operation" sortable="false">
						<img src="<c:url value='/images/icons/editIcon.gif'/>"
							onclick="javascript:editPdOutWarehouseDetail('${pdOutWarehouseDetail.uniNo}','${param.strAction}')"
							style="cursor: pointer;" />
					</ec:column>
				</c:if>
				<!--ec:column property="owNo" title="pdOutWarehouseDetail.owNo" /-->

				<ec:column property="productNo" title="busi.product.productno" />

				<ec:column property="productName" title="pmProduct.productName">
    				${compamyProductMap[pdOutWarehouseDetail.productNo]}
    			</ec:column>
				<ec:column property="qty" title="pd.qty"/>
			</ec:row>

		</ec:table>









	</div>
</c:if>







<script type="text/javascript">
    Form.focusFirstElement($('pdOutWarehouseForm'));
    
    function savePdOutWarehouse(){
    	waiting();
    		if(validatePdOutWarehouse($('pdOutWarehouseForm'))){
    					$('pdOutWarehouseForm').submit();
    				}
    	
    	}
    function editPdOutWarehouseDetail(uniNo,strAction){
    		<c:if test="${param.strAction == 'editPdOutWarehouse'}">
					window.location="<c:url value='/editPdOutWarehouseDetail.html'/>?uniNo="+uniNo+"&strAction=editPdOutWarehouseDetail";
				</c:if>
		}
		
		function addOutWarehouseDetail(){
					window.location="<c:url value='/editPdOutWarehouseDetail.html?strAction=addPdOutWarehouseDetail&owNo=${owNo}'/>";
			}
			
			
			function selectProduct(){
				open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
		function checkOutWarehouse(checkFlag){
    	  waiting();
    	   <c:if test="${param.strAction == 'editPdOutWarehouse'}">
    	   		$('strAction').value='submitPdOutWarehouse';
    	   </c:if>
    			
    			
    			if(validatePdOutWarehouse($('pdOutWarehouseForm'))){
    					$('pdOutWarehouseForm').submit();
    				}
    			
    			
    			//window.location="<c:url value='/editPdEnterWarehouseDetail.html?strAction=checkEnterWarehouse&ewNo=${ewNo}'/>";
    		}


				function toback(str){
	//			alert(this.document.referrer);
				if(str == 'addPdOutWarehouse'){
						str = 'editPdOutWarehouse';
					}
    		this.location='<c:url value="/pdOutWarehouses.html"/>?strAction='+str;
    	}
    	
    	function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=300, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
     	
     	
     	function validatePdOutWarehouseDetail(form){
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


<script type="text/javascript">
	function getCity(){
		var province=dwr.util.getValue('recipientCaNo');
		alCityManager.getAlCityByProvinceId(province,callBackCity);
	}
	
	function callBackCity(valid){
		dwr.engine.setAsync(false);
		var cityElemment=document.getElementById('city');
		dwr.util.removeAllOptions('city');
		var o=new Option("---请选择---","");
		cityElemment.options.add(o);
		dwr.util.addOptions('city',valid,'cityId','cityName');
		dwr.util.setValue('city','${pdOutWarehouse.city}');
	}
	<c:if test="${(not empty pdOutWarehouse.recipientCaNo) && !disabledFlag}">
		getCity();
	</c:if>
</script> 

<v:javascript formName="pdOutWarehouse" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />

<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
