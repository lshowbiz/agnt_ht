<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdFlitWarehouseDetail.title" />
</title>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/pdWarehouseManager.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/pdWarehouseStockManager.js"/>'></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<content tag="heading">
<jecs:locale key="pdFlitWarehouseDetail.heading" />
</content>


<c:set var="buttons">

	<c:choose>

		<c:when test="${param.strAction=='addPdFlitWarehouse'}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
				onclick="checkFlitWarehouse('${checkFlag}');"
				value="<jecs:locale  key='button.next'/>" /> --%>
			
			<button type="button" class="btn btn_sele"   onclick="checkFlitWarehouse('${checkFlag}');" ><jecs:locale  key='button.next'/></button>
			
		</c:when>
		<c:when
			test="${(param.strAction=='editPdFlitWarehouse')&&(pdFlitWarehouse.orderFlag==-1)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
				onclick="checkFlitWarehouse('${checkFlag}');"
				value="<jecs:locale  key='button.submit'/>" /> --%>
			<button type="button" class="btn btn_sele"   onclick="checkFlitWarehouse('${checkFlag}');" ><jecs:locale  key='button.submit'/></button>

			<%-- <input type="button" class="button" style="margin-right: 5px"
				onclick="savePdFlitWarehouse('${checkFlag}');"
				value="<jecs:locale  key='operation.button.save'/>" /> --%>
				
			<button type="button" class="btn btn_sele"   onclick="savePdFlitWarehouse('${checkFlag}');" ><jecs:locale  key='operation.button.save'/></button>
		
		</c:when>
		<c:when
			test="${(param.strAction=='checkPdFlitWarehouse')&&(pdFlitWarehouse.orderFlag==0)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
				onclick="checkFlitWarehouse('${checkFlag}');"
				value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
			<button type="button" class="btn btn_sele"   onclick="checkFlitWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
			
		</c:when>
		<c:when
			test="${(param.strAction=='confirmPdFlitWarehouse')&&(pdFlitWarehouse.orderFlag==1)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
				onclick="checkFlitWarehouse('${checkFlag}');"
				value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
			<button type="button" class="btn btn_sele"   onclick="checkFlitWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
				
		</c:when>
		<c:when
			test="${(param.strAction=='arrivedPdFlitWarehouse')&&(pdFlitWarehouse.orderFlag==2)}">
			<%-- <input type="button" class="button" style="margin-right: 5px"
				onclick="checkFlitWarehouse('${checkFlag}');"
				value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
			<button type="button" class="btn btn_sele"   onclick="checkFlitWarehouse('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
			
		</c:when>
	</c:choose>


	<c:if test="${!disabledFlag}">
		<c:if test="${strAction == 'editPdFlitWarehouse'}">
			<%-- <input type="submit" class="button" name="delete"
				onclick="$('strAction').value='deletePdFlitWarehouse';bCancel=true;return confirmDelete('PdFlitWarehouse')"
				value="<jecs:locale key="operation.button.delete"/>" /> --%>
			<button type="submit" class="btn btn_sele"  name="delete" onclick="$('strAction').value='deletePdFlitWarehouse';bCancel=true;return confirmDelete('PdFlitWarehouse')" ><jecs:locale key="operation.button.delete"/></button>
				
		</c:if>
	</c:if>
	<c:if
		test="${(param.strAction=='checkPdFlitWarehouse')&&(pdFlitWarehouse.orderFlag==0 || pdFlitWarehouse.orderFlag==1)}">
		<%-- <input type="submit" class="button" name="tonew"
			onclick="$('strAction').value='toNewPdFlitWarehouse',bCancel=true;"
			value="<jecs:locale key="button.cancelToNew"/>" /> --%>
		<button type="submit" class="btn btn_sele"   onclick="$('strAction').value='toNewPdFlitWarehouse',bCancel=true;" ><jecs:locale key="button.cancelToNew"/></button>
		
	</c:if>
</c:set>


<spring:bind path="pdFlitWarehouse.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="/images/iconWarning.gif"/>"
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
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
		<c:remove var="errorMsgs" scope="session" />
	</div>
</c:if>
<form:form commandName="pdFlitWarehouse" method="post"
	action="editPdFlitWarehouse.html"
	onsubmit="return validatePdFlitWarehouse(this)"
	id="pdFlitWarehouseForm">



	<table class='detail' width="70%">
	<tbody class="window" >
		<input type="hidden" id="strAction" value="${param.strAction}"
			name="strAction" />
		<input type="hidden" id="warehosueFlag" value="0" name="warehosueFlag" />
		<input type="hidden" id="pageOrderFlag" value="-1"
			name="pageOrderFlag" />
		<form:hidden path="fwNo" />
		<form:hidden path="orderFlag" id="orderFlag" />







		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.warehouse.outwatehouseno" /></span>
			</th>
			<td>
				<!--form:errors path="outWarehouseNo" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="outWarehouse.warehouseNo"
					disabled="${disabledFlag}" id="outWarehouseNo"
					cssClass="textbox-text" /></span>
				<%-- <input type="button" class="button"
					<c:if test="${disabledFlag}">disabled</c:if> name="select"
					onclick="selectWarehouse('outWarehouseNo');"
					value="<jecs:locale key="button.select"/>" /> --%>
				<button type="button" class="btn btn_sele"  <c:if test="${disabledFlag}">disabled</c:if> name="select"
					onclick="selectWarehouse('outWarehouseNo');"><jecs:locale key="button.select"/></button>
				
			</td>
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.pd.enterWarehouseNo" /></span>
			</th>
			<td>
				<!--form:errors path="inWarehouseNo" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="inWarehouse.warehouseNo"
					disabled="${disabledFlag}" id="inWarehouseNo"
					cssClass="textbox-text" /></span>
				<%-- <input type="button" class="button"
					<c:if test="${disabledFlag}">disabled</c:if> name="select"
					onclick=selectAllWarehouse('inWarehouseNo');;
value="<jecs:locale key="button.select"/>" /> --%>
                <button type="button" class="btn btn_sele"  <c:if test="${disabledFlag}">disabled</c:if> name="select"
					onclick="selectAllWarehouse('inWarehouseNo');"><jecs:locale key="button.select"/></button>
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
						id="recipientFirstName" cssClass="textbox-text"/></span>
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
		<!-- Add By WuCF 20130515 -->
<%-- 		<c:if test="${sessionScope.sessionLogin.companyCode !='US' && sessionScope.sessionLogin.companyCode!='JP'}">
 --%>			
           <tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.recipientName" /></span>
				</th>
				<td>
					<!--form:errors path="recipientName" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="recipientName" readonly="${disabledFlag}"
						id="recipientName" cssClass="textbox-text" /></span>
				</td>
			
			
<%-- 		</c:if>
 --%>		
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.recipientZip" /></span>
			</th>
			<td>
				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientZip" readonly="${disabledFlag}"
					id="recipientZip" cssClass="textbox-text"/></span>
				<c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
					<%-- <input type="button" class="button" onclick="getPostalcode();"
						value="<jecs:locale key="operation.button.search"/>" /> --%>
	                <button type="button" class="btn btn_sele" onclick="getPostalcode();" ><jecs:locale key="operation.button.search"/></button>
				</c:if>
			</td>
		 <tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdOutWarehouse.stateProvinceName" /></span>
			</th>
			<td>
				<c:if test="${disabledFlag}">
    		<span class="textbox">${alStateProvinceMap[pdFlitWarehouse.recipientCaNo]}</span>
					<form:hidden path="recipientCaNo" />
				</c:if>
				<c:if test="${!disabledFlag}">
					<span class="textbox"><select name="recipientCaNo" onchange="getCity()" class="textbox-text">
						<option value=""></option>
						<c:forEach items="${alStateProvinces}" var="alStateProvince">
							<option value="${alStateProvince.stateProvinceId}"
								<c:if test="${alStateProvince.stateProvinceId eq pdFlitWarehouse.recipientCaNo}">selected</c:if>>
								${alStateProvince.stateProvinceName}
							</option>
						</c:forEach>

					</select></span>
				</c:if>



				<!--form:input path="recipientCaNo" readonly="${disabledFlag}" id="recipientCaNo" cssClass="text medium"/-->
			</td>
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.city" /></span>
			</th>
			<td align="left">

				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<c:if test="${disabledFlag}">
        	<span class="textbox">${alCityMap[pdFlitWarehouse.city]}</span>
        	<form:hidden path="city" />
				</c:if>

				<c:if test="${!disabledFlag}">
					<span class="textbox"><select name="city" id="city" class="textbox-text">
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
					id="recipientAddr"  cssClass="textbox"  size="80" cssStyle="width:500px;"/>
			</td>
		</tr>
		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="shipping.phone" /></span>
			</th>
			<td align="left">
				<!--form:errors path="recipientPhone" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientPhone" readonly="${disabledFlag}"
					id="recipientPhone" cssClass="textbox-text"/></span>
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
				<span class="text-font-style-tc"><jecs:label key="busi.user.check" /></span>
			</th>
			<td>
				<!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="checkUsrCode" disabled="true" id="checkUsrCode"
					cssClass="textbox-text" /></span>
			</td>
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="pd.confirmUserNo" /></span>
			</th>
			<td>
				<!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
				<span class="textbox">${pdFlitWarehouse.okUsrCode}&nbsp;${pdFlitWarehouse.okTime}</span>
			</td>
		</tr>

		<tr  class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdFlitWarehouse.arrivedPdFlitWarehouse" /></span>
			</th>
			<td>
				<!--form:errors path="checkUsrCode" cssClass="fieldError"/-->
				<span class="textbox">${pdFlitWarehouse.toUsrCode}&nbsp;${pdFlitWarehouse.toTime}</span>
			</td>

			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.common.trackingno" /></span>
			</th>
			<td align="left">
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="trackingNo" id="trackingNo" cssClass="textbox-text"/></span>
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

		<c:choose>
			<c:when test="${checkFlag ==1}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.checkRemark" /></span>
					</th>
					<td colspan="3">
						<span class="text-font-style-tc-right"><form:textarea path="checkRemark"
							id="checkRemark" cssClass="textarea_border" /></span>
					</td>
				</tr>
			</c:when>
			<c:when test="${checkFlag ==2}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.okRemark" /></span>
					</th>
					<td colspan="3">
						<span class="text-font-style-tc-right"><form:textarea path="okRemark" id="okRemark"
							cssClass="textarea_border"/></span>
					</td>
				</tr>
			</c:when>
			<c:when test="${checkFlag ==3}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.toRemark" /></span>
					</th>
					<td colspan="3">
						<span class="text-font-style-tc-right"><form:textarea path="toRemark" id="toRemark"
							cssClass="textarea_border" /></span>
					</td>
				</tr>
			</c:when>
		</c:choose>
		<c:if test="${param.strAction=='searchPdFlitWarehouse'}">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="pd.checkRemark" /></span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right"><div class="textarea_border">${pdFlitWarehouse.checkRemark}</div></span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="pd.okRemark" /></span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right"><div class="textarea_border">${pdFlitWarehouse.okRemark}</div></span>
				</td>
			</tr>
			<tr  class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="pd.toRemark" /></span>
				</th>
				<td  colspan="3">
					<span class="text-font-style-tc-right"><div class="textarea_border">${pdFlitWarehouse.toRemark}</div></span>
				</td>
			</tr>
		</c:if>


		

	<%-- 	<tr>
			<th class="command">
				<jecs:label key="sysOperationLog.moduleName" />
			</th>
			<td class="command">
				<jecs:power powerCode="${param.strAction}">
					<c:if test="${param.strAction !='searchPdFlitWarehouse'}">
						<c:out value="${buttons}" escapeXml="false" />
					</c:if>
				</jecs:power>
				
			</td>
		</tr> --%>
		
		<tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
					<c:if test="${param.strAction !='searchPdFlitWarehouse'}">
						<c:out value="${buttons}" escapeXml="false" />
					</c:if>
				</jecs:power>
				<%-- <input type="button" class="button" name="back"
					onclick="toback('${param.strAction}');"
					value="<jecs:locale  key="operation.button.return"/>" /> --%>
		           <button type="button" class="btn btn_sele" name="back"  onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
			</td>
		</tr>
</tbody>
	</table>
</form:form>



<div class="searchBar">

	<c:if test="${checkFlag=='0' && !disabledFlag}">
		<form:form commandName="pdFlitWarehouseDetail" method="post"
			action="editPdFlitWarehouseDetail.html?fwNo=${fwNo}&strAction=addPdFlitWarehouseDetail"
			onsubmit="return validatePdFlitWarehouseDetail(this)"
			id="pdFlitWarehouseDetailForm">



			<table class='detail' width="70%">
			<tbody class="window" >
				<tr style="height:20px;">
					<td align="center" style="background: #ECECEC;">
						<jecs:locale key="busi.product.productno" />
					</td>
					<td align="center" style="background: #ECECEC;">
						<jecs:locale key="pd.qty" />
					</td>
				</tr>
				<tr  class="edit_tr">
					<td  align="center">
						<input type="text" name="productNo" id="productNo" />
						
						<%-- <input type="button" class="button" name="select"
							onclick="selectProduct();" value="<jecs:locale key="button.select"/>" /> --%>
						<button type="button" class="btn btn_sele" name="select"  onclick="selectProduct()" ><jecs:locale key="button.select"/></button>
							
						<input type="text" name="productName" id="productName" style="width:300px;"/>
					</td>
					<td align="center">
						<input type="text" name="qty" id="qty" />
						<%-- <input type="submit" class="button" name="add"
							value="<jecs:locale key="operation.button.add"/>" /> --%>
						<button type="submit" class="btn btn_sele" name="add"><jecs:locale key="operation.button.add"/></button>
					</td>
				</tr>
				</tbody>
			</table>
		</form:form>
	</c:if>
</div>







<div>
	<c:if test="${strAction != 'addPdFlitWarehouse'}">

		<ec:table tableId="pdFlitWarehouseDetailListTable"
			items="pdFlitWarehouse.pdFlitWarehouseDetails"
			var="pdFlitWarehouseDetail" autoIncludeParameters="true"
			showPagination="false"
			action="${pageContext.request.contextPath}/editPdFlitWarehouse.html"
			width="70%" sortable="true"
			imagePath="./images/extremetable/*.gif" styleClass="detail">
			<ec:parameter name="strAction" value="${param.strAction}" />
			<ec:parameter name="fwNo" value="${fwNo}"/>
			<ec:row>
				<c:if test="${!disabledFlag}">
					<ec:column property="edit" title="title.operation" sortable="false">
						<img src="<c:url value='/images/icons/editIcon.gif'/>"
							onclick="javascript:editPdFlitWarehouseDetail('${pdFlitWarehouseDetail.uniNo}')"
							style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
					</ec:column>
				</c:if>
				<!--ec:column property="fwNo" title="pdFlitWarehouseDetail.fwNo" /-->
				<ec:column property="productNo" title="busi.product.productno" />
				<ec:column property="productName" title="pmProduct.productName">
    				${compamyProductMap[pdFlitWarehouseDetail.productNo]}
    			</ec:column>
				<c:if test="${sessionScope.sessionLogin.companyCode == 'KR'}">
					<ec:column property="price" title="pd.price" />
				</c:if>
				<ec:column property="qty" title="pd.qty" styleClass="numberColumn" />
			</ec:row>

		</ec:table>
	</c:if>
</div>

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdFlitWarehouse')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->


<script type="text/javascript"> 
	Form.focusFirstElement($('pdFlitWarehouseForm'));

	 function selectWarehouse(str){ 
     			
     			<c:if test="${sessionScope.sessionLogin.isManager}">
     				window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectAllWarehouse&elementId="+str,"","height=400, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     			</c:if>
     			<c:if test="${!sessionScope.sessionLogin.isManager}">
     				window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=400, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     			</c:if>
     	}

	 function addWarehouseDetail(){
    			window.location="<c:url value='/editPdFlitWarehouseDetail.html?strAction=addPdFlitWarehouseDetail&fwNo=${fwNo}'/>";
    			
    	}

	function addPdFlitWarehouseDetail() {

		$('pdFlitWarehouseDetailForm').submit();
	}
	function savePdFlitWarehouse() {
		waiting();
		confirmOutWarehouse();
		if ($('warehosueFlag').value == 1) {
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

	function confirmOutWarehouse() {

		DWREngine.setAsync(false);
		$('warehosueFlag').value = 0;
		var outWarehouseNo = $('outWarehouseNo').value;

		var companyCode = '${sessionScope.sessionLogin.companyCode}';
		if (companyCode == 'AA') {
			companyCode = "";
		}

		pdWarehouseManager.existWarehouseNoByCompany(outWarehouseNo,
				companyCode, confirmInWarehouse);

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

	function editPdFlitWarehouseDetail(uniNo) {
		
		window.location = "editPdFlitWarehouseDetail.html?strAction=editPdFlitWarehouseDetail&uniNo="
				+ uniNo;
		
	}

	function selectProduct(){
			open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=1&companyCode=${pdFlitWarehouse.outWarehouse.companyCode}'/>","","height=500, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
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

<v:javascript formName="pdFlitWarehouse" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />

<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
<script type="text/javascript">
	function getCity(){
		var province=dwr.util.getValue('recipientCaNo');
		alCityManager.getAlCityByProvinceId(province,callBackCity);
	}
	
	function callBackCity(valid){
		dwr.engine.setAsync(false);
		var cityElemment=document.getElementById('city');
		dwr.util.removeAllOptions('city');
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		cityElemment.options.add(o);
		dwr.util.addOptions('city',valid,'cityId','cityName');
		dwr.util.setValue('city','${pdFlitWarehouse.city}');
	}
	<c:if test="${(not empty pdFlitWarehouse.recipientCaNo) && !disabledFlag}">
		getCity();
	</c:if>
</script> 
