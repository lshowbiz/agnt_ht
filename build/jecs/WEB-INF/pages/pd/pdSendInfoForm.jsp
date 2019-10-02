<%@ include file="/common/taglibs.jsp"%>


<title><jecs:locale key="pdSendInfoDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="pdSendInfoDetail.heading" />
</content>
<script type='text/javascript'
	src='<c:url value="/dwr/interface/pdWarehouseStockManager.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/dwr/interface/pdSendInfoManager.js"/>'></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/alCityManager.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>


<spring:bind path="pdSendInfo.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="images/newIcons/warning_16.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>
<div id="errorWarn">
</div>
<c:set var="buttons">

    <c:choose>
        <c:when test="${((not empty pdSendInfo.shipType)&&(pdSendInfo.shipType == 4)) || ((not empty pdSendInfo.whetherStock)&&(pdSendInfo.whetherStock == 'Y')) }">
            	
        </c:when>
        <c:otherwise>
					<jecs:power powerCode="${param.strAction}">
						<c:choose>
				
							<c:when test="${param.strAction == 'addPdSendInfo' }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									onclick="checkSendInfo('${checkFlag}');"
									value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
									
							<button type="button" class="btn btn_sele"  onclick="checkSendInfo('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
							
							</c:when>
				
							<c:when
								test="${(param.strAction == 'editPdSendInfo')&&(pdSendInfo.orderFlag == -1) }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									onclick="checkSendInfo('${checkFlag}');"
									value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
									
								<button type="button" class="btn btn_sele"  onclick="checkSendInfo('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
							</c:when>
							<c:when
								test="${(param.strAction == 'checkPdSendInfo')&&(pdSendInfo.orderFlag==0) }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									onclick="checkSendInfo('${checkFlag}');"
									value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
								
									<button type="button" class="btn btn_sele"  onclick="checkSendInfo('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
							</c:when>
							<c:when
								test="${(param.strAction == 'sendPdSendInfo')&&(pdSendInfo.orderFlag==1) }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									onclick="checkSendInfo('${checkFlag}');"
									value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
								<button type="button" class="btn btn_sele"  onclick="checkSendInfo('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
							</c:when>
							<c:when
								test="${(param.strAction == 'acceptPdSendInfo')&&(pdSendInfo.orderFlag==2) }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									onclick="checkSendInfo('${checkFlag}');"
									value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
									
									<button type="button" class="btn btn_sele"  onclick="checkSendInfo('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
							
							</c:when>
							<c:when
								test="${(param.strAction == 'receiptPdSendInfo')&&(pdSendInfo.orderFlag>=2)&&(pdSendInfo.orderFlag<4) }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									onclick="checkSendInfo('${checkFlag}');"
									value="<jecs:locale  key='${checkButtonKey}'/>" /> --%>
									
								<button type="button" class="btn btn_sele"  onclick="checkSendInfo('${checkFlag}');" ><jecs:locale  key='${checkButtonKey}'/></button>
									
							</c:when>
				
				
				
				
						</c:choose>
				
				
					</jecs:power>
				
					<c:if test="${!disabledFlag}">
						<jecs:power powerCode="addPdSendInfo">
							<%-- <input type="button" class="button" name="save"
								onclick="addPdSendInfo();"
								value="<jecs:locale key="operation.button.save"/>" /> --%>
								
							<button type="button" class="btn btn_sele"  name="save" onclick="addPdSendInfo();" ><jecs:locale key="operation.button.save"/></button>
							
						</jecs:power>
						<!-- 
							<jecs:power powerCode="deletePdSendInfo">
								<c:if test="${param.strAction=='editPdSendInfo'}">
									<input type="submit" class="button" name="delete"
										onclick="$('strAction').value='deletePdSendInfo';"
										value="<jecs:locale key="operation.button.delete"/>" />
								</c:if>
							</jecs:power>
				         -->
					</c:if>
					
					<c:if test="${(param.strAction=='checkPdSendInfo') && (pdSendInfo.orderFlag<=1)&& (pdSendInfo.orderFlag>=0)}">
						<%-- <input type="button" class="button" name="ret"
							onclick="toNewPdSendInfo();"
							value="<jecs:locale key="button.cancelToNew"/>" /> --%>
						
						<button type="button" class="btn btn_sele"  name="ret" onclick="toNewPdSendInfo();" ><jecs:locale key="button.cancelToNew"/></button>
						
				    </c:if>
        </c:otherwise>
    
    </c:choose>



	

</c:set>




<form:form commandName="pdSendInfo" method="post"
	action="editPdSendInfo.html" onsubmit="return validatePdSendInfo(this)"
	id="pdSendInfoForm">
	<table class='detail' width="70%">
     <tbody class="window" >
		<input type="hidden" id="validateStockFlag" name="validateStockFlag"
			value="0" />
		<input type="hidden" id="interfaceShipType" name="interfaceShipType"
			value="${interfaceShipType}" />
		<input type="hidden" id="strAction" name="strAction"
			value="${param.strAction}" />
		<form:hidden path="siNo" />

		<form:hidden path="orderFlag" id="orderFlag" />



		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdSendInfo.sendWarehouseNo" /></span>
			</th>
			<td>
				<!--form:errors path="sendWarehouseNo" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="warehouseNo" readonly="true" id="warehouseNo" cssClass="textbox-text" /></span>

				<%-- <input type="button" class="button"
					<c:if test="${disabledFlag}">disabled="ture"</c:if> name="select"
					onclick="selectWarehouse('warehouseNo');"
					value="<jecs:locale key="button.select"/>" /> --%>
				<button type="button" class="btn btn_sele"  <c:if test="${disabledFlag}">disabled="ture"</c:if> name="select" onclick="selectWarehouse('warehouseNo');" ><jecs:locale key="button.select"/></button>
			</td>
       
			<th>
				<span class="text-font-style-tc"><jecs:label key="customerRecord.customerNo" /></span>
			</th>
			<td>
				<!--form:errors path="agentNo" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="customer.userCode" readonly="true"
					id="customerCode" cssClass="textbox-text" /></span>
			</td>
		</tr>
       <tr class="edit_tr">
		<c:if
			test="${sessionScope.sessionLogin.companyCode !='US' && sessionScope.sessionLogin.companyCode!='JP'}">
			
				<th>
					<span class="text-font-style-tc"><jecs:label key="pdSendInfo.recipientName" /></span>
				</th>
				<td>
					<!--form:errors path="recipientName" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="recipientName" readonly="${disabledFlag}"
						id="recipientName" cssClass="textbox-text" /></span>
				</td>
			
		</c:if>
            <th>
				<span class="text-font-style-tc"><jecs:label key="pdSendInfo.recipientZip" /></span>
			</th>
			<td>
				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientZip" readonly="${disabledFlag}"
					id="recipientZip" cssClass="textbox-text" /></span>
				<c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
					<%-- <input type="button" class="button" onclick="getPostalcode();"
						value="<jecs:locale key="operation.button.search"/>" /> --%>
						
					<button type="button" class="btn btn_sele" onclick="getPostalcode();" ><jecs:locale key="operation.button.search"/></button>
						
				</c:if>
			</td>
			</tr>
		<c:if
			test="${sessionScope.sessionLogin.companyCode=='US' || sessionScope.sessionLogin.companyCode=='JP'}">

			<tr  class="edit_tr">
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
				<td align="left">
					<!--form:errors path="recipientName" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="recipientLastName" readonly="${disabledFlag}"
						id="recipientLastName" cssClass="textbox-text" /></span>
				</td>
			</tr>
		</c:if>
		<tr class="edit_tr">
			
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="alStateProvince.stateProvinceName" /></span>
			</th>
			<td>
				<c:if test="${disabledFlag}">
    		<span class="textbox">${alStateProvinceMap[pdSendInfo.recipientCaNo]}</span>
					<form:hidden path="recipientCaNo" />
				</c:if>
				<c:if test="${!disabledFlag}">
					<span class="textbox"><select name="recipientCaNo" onchange="getCity()" class="textbox-text">
						<option value="" ></option>
						<c:forEach items="${alStateProvinces}" var="alStateProvince">
							<option value="${alStateProvince.stateProvinceId}"
								<c:if test="${alStateProvince.stateProvinceId eq pdSendInfo.recipientCaNo}">selected</c:if>>
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
			<td>

				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<c:if test="${disabledFlag}">
        	<span class="textbox">${alCityMap[pdSendInfo.city]}</span>
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
				<span class="text-font-style-tc"><jecs:label key="pdSendInfo.recipientAddr" /></span>
			</th>
			<td colspan="3">
				<!--form:errors path="recipientName" cssClass="fieldError"/-->
				<form:input path="recipientAddr" readonly="${disabledFlag}"
					id="recipientAddr" cssClass="textbox"  size="80" cssStyle="width:520px;" />
			</td>
		</tr>



		<tr  class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdSendInfo.recipientPhone" /></span>
			</th>
			<td>
				<!--form:errors path="recipientPhone" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="recipientPhone" readonly="${disabledFlag}"
					id="recipientPhone" cssClass="textbox-text" /></span>
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


		<tr  class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="po.shipment.type" /></span>
			</th>
			
				<td>
				     <!-- modify fu 20150825 -->
	                  <c:choose>
			             <c:when test="${(pdSendInfo.orderFlag>=2)||((not empty pdSendInfo.shipType)&&(pdSendInfo.shipType == 4)) || ((not empty pdSendInfo.whetherStock)&&(pdSendInfo.whetherStock == 'Y')) }">
			                    <c:if test="${pdSendInfo.shipType==0 || empty pdSendInfo.shipType}">
			                        <span class="textbox"><jecs:locale key="po.shipment.type0"></jecs:locale></span>
			                    </c:if>
			                    <c:if test="${pdSendInfo.shipType==2}">
			                         <span class="textbox"><jecs:locale key="poMemberOrder.shipments"></jecs:locale></span>
			                    </c:if>
			                    <c:if test="${pdSendInfo.shipType==3}">
			                         <span class="textbox"><jecs:locale key="schedule.parden"></jecs:locale><jecs:locale key="ps.shipped"></jecs:locale></span>
			                    </c:if>
			                    <c:if test="${pdSendInfo.shipType==4}">
			                         <span class="textbox"><jecs:locale key="pdSendInfo.shipType.4"></jecs:locale></span>
			                    </c:if>
			             </c:when>
			             <c:otherwise>
								<span class="textbox"><jecs:list listCode="po.shipment.type" name="shipType"
									showBlankLine="false" id="shipType" value="${pdSendInfo.shipType}"
									defaultValue="" onchange="shipTypeChange('${pdSendInfo.shipType}')" styleClass="textbox-text"/></span>
									<c:if test="${param.strAction=='editPdSendInfo' || param.strAction == 'checkPdSendInfo'}">
										<%-- <input type="button" class="button" style="margin-right: 5px"
											value="<jecs:locale key="pd.normal.send"/>"
											onclick="updateShipType('${pdSendInfo.siNo}');" /> --%>
											
									 <button type="button" class="btn btn_sele"   onclick="updateShipType('${pdSendInfo.siNo}');" ><jecs:locale key="pd.normal.send"/></button>
									    
				                    </c:if>
						</c:otherwise>
	                 </c:choose>
				</td>
			<th>
				<span class="text-font-style-tc"><jecs:label key="pdSendInfo.suspendShipment" /></span>
			</th>
			<td>
			    <c:choose>
			        <c:when test="${pdSendInfo.orderFlag>=2}">
			             <c:if test="${empty pdSendInfo.suspendShipment}"></c:if>
			        	 <c:if test="${pdSendInfo.suspendShipment==1}">
			        	       <span class="textbox"><jecs:locale key="ps.purchaseReturned"></jecs:locale></span>
			        	 </c:if>
			        	 <c:if test="${pdSendInfo.suspendShipment==2}">
			        	      <span class="textbox"><jecs:locale key="busi.exchange"></jecs:locale></span>
			        	 </c:if>
			        	 <c:if test="${pdSendInfo.suspendShipment==3}">
			        	      <span class="textbox"><jecs:locale key="pd.customMattress "></jecs:locale></span>
			        	 </c:if>
			        	  <c:if test="${empty pdSendInfo.suspendShipment}">
			        	      <span class="textbox"></span>
			        	 </c:if>
			        </c:when>
			        <c:otherwise>
			             <span class="textbox"><jecs:list listCode="pd.suspendshipment" name="suspendShipment" showBlankLine="true" id="suspendShipment" value="${pdSendInfo.suspendShipment}" defaultValue="" styleClass="textbox-text"/></span>
			        </c:otherwise>
			    </c:choose>
			</td>
		</tr>


		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="poMemberOrder.shippingPay" /></span>
			</th>
			<td>
				<!--form:errors path="recipientPhone" cssClass="fieldError"/-->

				<span class="textbox"><jecs:list listCode="yesno" name="codFlag" showBlankLine="true"
					id="codFlag" value="${pdSendInfo.codFlag}" defaultValue="" styleClass="textbox-text"/></span>

			</td>
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="poMemberOrder.shippingDay" /></span>
			</th>
			<td>
				<!--form:errors path="recipientPhone" cssClass="fieldError"/-->

				<%--<input name="recipientTime" size='11' id="recipientTime"
					value="${pdSendInfo.recipientTime}">
				 <img src="./images/calendar/calendar7.gif" id="img_startTime"
					style="cursor: pointer;"
					title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "recipientTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script> --%>
				 <input name="recipientTime" id="recipientTime" type="text" value="${pdSendInfo.recipientTime}" style="height:21px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" size="25"/>

			</td>
		</tr>

		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="pd.shno" /></span>
			</th>
			<td>
				<!--form:errors path="shNo" cssClass="fieldError"/-->
				<c:if test="${disabledFlag}">

					<c:choose>
						<c:when
							test="${param.strAction=='checkPdSendInfo' && sessionScope.sessionLogin.companyCode=='CN' }">

							<span class="textbox"><jecs:list listCode="pd.shno" name="shNo" showBlankLine="true"
								id="shNo" value="${pdSendInfo.shNo}" defaultValue="" styleClass="textbox-text"/></span>

						</c:when>
						<c:otherwise>
							<span class="textbox"><jecs:code listCode="pd.shno" value="${pdSendInfo.shNo}" /></span>
							<form:hidden path="shNo" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${!disabledFlag}">

					<span class="textbox"><jecs:list listCode="pd.shno" name="shNo" showBlankLine="true"
						id="shNo" value="${pdSendInfo.shNo}" defaultValue="" styleClass="textbox-text"/></span>
				</c:if>

			</td>
		
			
		</tr>

		<tr  class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.user.check" /></span>
			</th>
			<td align="left">
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="checkUsrCode" id="checkUsrCode"
					 cssClass="textbox-text"/>
				${pdSendInfo.checkTime}</span>
			</td>
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="pd.barcode" /></span>
			</th>
			<td align="left">
				<span class="textbox"><form:input path="barCode" id="barCode" cssClass="textbox-text"/></span>
			</td>
		</tr>

		<c:if test="${param.strAction=='sendPdSendInfo'}">

		</c:if>

		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc-tare"><jecs:label key="busi.common.remark" /></span>
			</th>
			<td colspan="3">
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="text-font-style-tc-right"><form:textarea path="remark"
					readonly="${disabledFlag}" id="remark"  cssClass="textarea_border"/></span>
			</td>
		</tr>

		<c:choose>
			<c:when test="${checkFlag ==1}">
				<tr  class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.checkRemark" /></span>
					</th>
					<td colspan="3">
						<!--form:errors path="checkRemark" cssClass="fieldError"/-->
						<span class="text-font-style-tc-right"><form:textarea path="checkRemark"
							id="checkRemark" cssClass="textarea_border"/></span>
					</td>
				</tr>
			</c:when>
			<c:when test="${checkFlag ==2}">
				<tr  class="edit_tr">
					<th>
						<span class="text-font-style-tc-tare"><jecs:label key="pd.okRemark" /></span>
					</th>
					<td colspan="3">
						<!--form:errors path="okRemark" cssClass="fieldError"/-->
						<span class="text-font-style-tc-right"><form:textarea path="okRemark" id="okRemark"
							cssClass="textarea_border" /></span>
					</td>
				</tr>
			</c:when>
		</c:choose>

		<c:if test="${param.strAction=='searchPdSendInfo'}">
			<tr  class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="pd.checkRemark" /></span>
				</th>
				<td colspan="3">
					<!--form:errors path="checkRemark" cssClass="fieldError"/-->
					<span class="text-font-style-tc-right"><div class="textarea_border">${pdSendInfo.checkRemark}</div></span>
				</td>
			</tr>
			<tr  class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="pd.okRemark" /></span>
				</th>
				<td  colspan="3">
					<!--form:errors path="okRemark" cssClass="fieldError"/-->
					<span class="text-font-style-tc-right"><div class="textarea_border">${pdSendInfo.okRemark}</div></span>
				</td>
			</tr>
		</c:if>
       <tr  class="edit_tr">
		<th>
			<span class="text-font-style-tc"><jecs:label key="pd.confirmUserNo" /></span>
		</th>
		<td>
			<span class="textbox">${pdSendInfo.okUsrCode}&nbsp;${pdSendInfo.okTime}</span>


		</td>
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.poMemberOrder.total.amount" /></span>
			</th>
			<td align="left">
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="amount" id="amount" readonly="true"
					 cssClass="textbox-text" /></span>
			</td>
		</tr>

		
		<c:if test="${pdSendInfo.shipType==1}">
		   <c:if test="${not empty pdSendInfo.partSend}">
				<tr  class="edit_tr">
					<th>
						<span class="text-font-style-tc"><jecs:label key="pd.partialShipment" /></span>
					</th>
					<td>
						<span class="textbox"><jecs:code listCode="yesno.isbuy" value="0"></jecs:code></span>
					</td>
				</tr>
			</c:if>
        </c:if>
        <tr  class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="busi.common.trackingno" /></span>
			</th>
			<td>
				<!--form:errors path="remark" cssClass="fieldError"/-->
				<span class="textbox"><form:input path="trackingNo" id="trackingNo" cssClass="textbox-text"/></span>
			</td>
			 <!-- 2015-10-20  modify fu  WMS on the line to annotate this part of the code --> 
		
			<th>
				<span class="text-font-style-tc"><jecs:label key="pd.hurry" /></span>
			</th>
			<td>
			   <c:choose>
			         <c:when test="${((not empty pdSendInfo.shipType)&&(pdSendInfo.shipType == 4)) || ((not empty pdSendInfo.whetherStock)&&(pdSendInfo.whetherStock == 'Y')) }">
			               <span class="textbox"><jecs:list listCode="yesno" name="hurryFlag" showBlankLine="true" id="hurryFlag" value="${pdSendInfo.hurryFlag}" defaultValue="" styleClass="textbox-text"/></span>
			         </c:when>
			         <c:otherwise>
						              <span class="textbox"><jecs:list listCode="yesno" name="hurryFlag" showBlankLine="true"
								id="hurryFlag" value="${pdSendInfo.hurryFlag}" defaultValue="" styleClass="textbox-text"/></span>
							<c:if test="${param.strAction=='editPdSendInfo' || ((param.strAction == 'checkPdSendInfo')&&(pdSendInfo.orderFlag==1)) }">
								<%-- <input type="button" class="button" style="margin-right: 5px"
									value="<jecs:locale key="pd.update.hurry"/>"
									onclick="updateHurryFlag('${pdSendInfo.siNo}');" /> --%>
								<button type="button" class="btn btn_sele" onclick="updateHurryFlag('${pdSendInfo.siNo}');" ><jecs:locale key="pd.update.hurry"/></button>
							</c:if>
			         </c:otherwise>
			   </c:choose>
			</td>
		</tr>
		

		<tr  class="edit_tr">
			<td class="command" colspan="4"  align="center">
				<c:if test="${param.strAction !='searchPdSendInfo'}">
					<c:out value="${buttons}" escapeXml="false" />
				</c:if>
				
				<button type="button" class="btn btn_sele" name="back"  onclick="toBack('${param.strAction}')" ><jecs:locale key="operation.button.return"/></button>

                <c:if test="${pdSendInfo.warehouseNo == 'LC'}">
                	<button type="button" class="btn btn_sele" name="ret"  onclick="showExtra('${pdSendInfo.siNo}');" ><jecs:locale key="busi.common.remark"/></button>
				</c:if>
				<%-- <input type="button" class="button" name="back"
					onclick="toBack('${param.strAction}')"
					value="<jecs:locale key="operation.button.return"/>" />
				<c:if test="${pdSendInfo.warehouseNo == 'LC'}">
					<input type="button" class="button" name="ret"
						onclick="showExtra('${pdSendInfo.siNo}');"
						value="<jecs:locale key="busi.common.remark"/>" />
				</c:if> --%>

				<c:if
					test="${sessionScope.sessionLogin.companyCode=='US' && pdSendInfo.shNo=='UPS' && pdSendInfo.orderFlag>1}">
									
				<button type="button" class="btn btn_sele" name="label"  onclick="openLabel('${pdSendInfo.siNo}')" >UPS Label</button>
					
					<%-- <input type="button" class="button" name="label"
						onclick="openLabel('${pdSendInfo.siNo}')" value="UPS Label" /> --%>
				</c:if>
				<!--<input type="button" class="button" name="back" onclick="saveTrackingNo()" value="<jecs:locale key="updateTrackingNo"/>" />-->

			</td>
		</tr>
     </tbody>
	</table>



	<div id="pdSendInfoDetailId">
		<!--iframe src="<c:url value='/pdEnterWarehouseDetails.html?ewNo=${ewNo}'/>" width="100%"></iframe-->

		<ec:table tableId="pdSendInfoDetailListTable"
			items="pdSendInfoDetailList" var="pdSendInfoDetail"
			action="${pageContext.request.contextPath}/pdSendInfoDetails.html"
			width="70%" form="pdSendInfoForm" showPagination="false"
			sortable="false" imagePath="./images/extremetable/*.gif" styleClass="detail">
			<ec:row>



				<ec:column property="productNo" title="busi.product.productno" />

				<ec:column property="pmProduct.productName"
					title="pmProduct.productName">
					<c:forEach items="${compamyProductMap}" var="productEn">
						<c:if test="${productEn.key eq pdSendInfoDetail.productNo}">${productEn.value}</c:if>
					</c:forEach>
				</ec:column>



				<ec:column property="price" title="pd.price">
					<input type="text" readOnly="true" class="readonly"
						name="<c:out value="${pdSendInfoDetail.productNo}" />-price"
						value="${pdSendInfoDetail.price}"
						id="<c:out value="${pdSendInfoDetail.productNo}" />-price" />
				</ec:column>
				<ec:column property="qty" title="pd.qty">
					<input type="text" readOnly="true" class="readonly"
						name="<c:out value="${pdSendInfoDetail.productNo}" />-qty"
						value="${pdSendInfoDetail.qty}"
						id="<c:out value="${pdSendInfoDetail.productNo}" />-qty" />
				</ec:column>
				
				<ec:column property="combinationProductNo" title="pd.encodingPackage" />

				<ec:column property="pmProduct.productName"
					title="pd.packageName">
					<c:forEach items="${compamyProductMap}" var="productEn">
						<c:if test="${productEn.key eq pdSendInfoDetail.combinationProductNo}">${productEn.value}</c:if>
					</c:forEach>
				</ec:column>
				
				<c:if
					test="${sessionScope.sessionLogin.companyCode=='US' && pdSendInfo.shNo=='UPS' && pdSendInfo.orderFlag>1}">
					<ec:column property="label" title="Lable">
						<input type="button" class="button" name="label"
							onclick="openSingleLabel('${pdSendInfoDetail.uniNo}')"
							value="UPS Label" />
					</ec:column>
				</c:if>
			</ec:row>

		</ec:table>







	</div>



</form:form>

<script type="text/javascript">
	function setqty(productNo){
		var qty = document.getElementById(productNo+"-qty");
		var qty2 = document.getElementById(productNo+"-qty2");
		if(qty2.value<0 || qty2.value>qty.value){
			qty2.value=qty.value;
		}
	}

    Form.focusFirstElement($('pdSendInfoForm'));
    var warnProductNo;
    var allQty = 0;
    
    function openLabel(siNo){
    		open('<c:url value="pdPrintLabels.html?siNo="/>'+siNo);
    	}
    	
    	function openSingleLabel(uniNo){
    		open('<c:url value="pdPrintLabels.html?type=single&uniNo="/>'+uniNo);
    	}
  
    function saveTrackingNo(){
    		$('strAction').value='saveTrackingNo';
    		$('pdSendInfoForm').submit();
    	}
    	
    	function showExtra(siNo){
    			open('<c:url value="pdSendExtras.html?strAction=editPdSendExtra&siNo="/>'+siNo);
    		
    		}
    
    function validateProduct(ret){
				 // alert(ret);����ʱ validateStockFlag =1
					var strHtml ='<img src="<c:url value="images/newIcons/warning_16.gif"/>" alt="<jecs:locale key="icon.warning"/>" class="icon" />' 
					             + warnProductNo + '<jecs:locale key="pd.notEnoughStock"/>(<font color="red"><jecs:locale key="pd.stock.useable"/>'+ret+"</font>)";
					if(ret<0){
						  
						  DWRUtil.setValue('validateStockFlag', 1);
						  Element.addClassName("errorWarn","error");
						  
							$("errorWarn").innerHTML= $("errorWarn").innerHTML+strHtml;
							
						}
						//alert("validateStockFlag="+DWRUtil.getValue('validateStockFlag'));
				}
				
				
   
    function editPdSendInfoDetail(uniNo){
    	<c:if test="${!disabledFlag}">
					//window.location="editPdSendInfoDetail.html?uniNo="+uniNo;
			</c:if>
		}
		
		function addSendInfoDetail(){
			
			}
		
		function addPdSendInfo(){
				waiting();
			  //alert("1="+validateStock());
			  //alert("addPdSendInfo");
			  var validateStockFlag = DWRUtil.getValue('validateStockFlag');
			  //alert("addPdSendInfo..."+validateStockFlag);
				$('orderFlag').value='-1';
				
				if(validatePdSendInfo($('pdSendInfoForm'))){
					  //alert(validateQty());
					  //alert(validateStock());
					  if(validateQty()==0){
					  	
					  	if(allQty <=0){
					  		
						    alert('<jecs:locale key="pd.qtyMustBeMoreThanZero"/>');
						    waitingEnd();
						    return ;
								
							}
					/**
					  		if(validateStock()){
					  				if(confirm('<jecs:locale key="pd.notEnoughStock"/>,<jecs:locale key="aiAgent.confirm"/>')){
					  						$('pdSendInfoForm').submit();
					  					}else{
					  						waitingEnd();
					  						return;	
					  					}
					  			}else{
					  						
					  					$('pdSendInfoForm').submit();
					  				}
		  				**/
					  	$('pdSendInfoForm').submit();
					  	}
					
						
					}
				
				 
				
			}
			
			
		
		function checkSendInfo(checkFlag){
			//checkFlag= -1 �½���0 �޸ģ�1 ����2 ��ȷ����3�ջ�ȷ�� 4 �ص�ȷ��
			//return validatePdSendInfo($('pdSendInfoForm'));
			 //validateStock();
			// alert("ee");
			  waiting();
					var validateQtyNum = 0;
			 var validateStockFlag ;
			 <c:choose>
			 
			 			<c:when test="${checkFlag ==-1}">
								
								
								$('strAction').value="addPdSendInfo";
								$('orderFlag').value="0";
								 validateQtyNum = validateQty();
										if(allQty <=0){
						    					alert('<jecs:locale key="pd.qtyMustBeMoreThanZero"/>');
						    					waitingEnd();
						    					return ;
											}
									
									if(validateQtyNum>0){
										  waitingEnd();
										  return;
										}		
									
									/**if(validateStock()){
					  				if(!confirm('<jecs:locale key="pd.notEnoughStock"/>,<jecs:locale key="aiAgent.confirm"/>')){
					  						waitingEnd();
					  						return;
					  					}
					  			}
					  			**/
						</c:when>
    	      <c:when test="${checkFlag ==0}">
    	      		
    	      		
								//$('orderFlag').value="0";
								$('strAction').value="submitPdSendInfo";
								validateQtyNum = validateQty();
										if(allQty <=0){
						    					alert('<jecs:locale key="pd.qtyMustBeMoreThanZero"/>');
						    					waitingEnd();
						    					return ;
											}
								/**
								if(validateQtyNum>0){
											waitingEnd();
										  return;
										}		
									**/
									/**
									if(validateStock()){
					  				if(!confirm('<jecs:locale key="pd.notEnoughStock"/>,<jecs:locale key="aiAgent.confirm"/>')){
					  						waitingEnd();
					  						return;
					  					}
					  			}
									**/
						</c:when>
    	      
						<c:when test="${checkFlag ==1}">
								//$('checkStatus').value="2";
								$('strAction').value="checkPdSendInfo";
								/**
								if(validateStock()){
					  				if(!confirm('<jecs:locale key="pd.notEnoughStock"/>,<jecs:locale key="aiAgent.confirm"/>')){
					  						waitingEnd();
					  						return;
					  					}
					  			}
					  			**/
								//$('orderFlag').value="1";
						</c:when>
						
						<c:when test="${checkFlag ==2}">
								//$('okStatus').value="2";
								//$('orderFlag').value="2";
								$('strAction').value="sendPdSendInfo";
						</c:when>
						<c:when test="${checkFlag ==3}">
								
								//$('orderFlag').value="3";
								$('strAction').value="acceptPdSendInfo";
						</c:when>
						<c:when test="${checkFlag ==4}">
								
								//$('orderFlag').value="4";
								$('strAction').value="receiptPdSendInfo";
						</c:when>
						
						 
				</c:choose>		
    			
    			
    		
    			if(validatePdSendInfo($('pdSendInfoForm'))){
    				    
    						$('pdSendInfoForm').submit();
    				}
    			
    			 
			
			}
			
			
			function validateQty(){
					var remainQty;
					var qty;
					var ret =0;
					allQty = 0;
					
					<c:forEach items="${pdSendInfoDetailList}" var="sendInfoDetailT" varStatus="status">
									qty       = $('<c:out value="${sendInfoDetailT.productNo}"/>-qty').value;
									allQty +=qty;
					</c:forEach>
						/**	
					<c:if test="${!disabledFlag}">
					
					<c:forEach items="${pdSendInfoDetailList}" var="sendInfoDetailT" varStatus="status">
					{
							remainQty = $('<c:out value="${sendInfoDetailT.productNo}"/>-remainQty').innerHTML;
							qty       = $('<c:out value="${sendInfoDetailT.productNo}"/>-qty').value;
							allQty +=qty;
							//alert(remainQty+","+qty);
							//alert(qty-remainQty>0);
							if(((qty-remainQty)>0)||(qty<0)){
										ret +=1;
										//new Abstract.Insertion("<c:out value='${sendInfoDetailT.productNo}' />-warn", "<font color='red'><jecs:locale key='operation.warn.qty.notenough'/></font>");
										//Effect.Fade("<c:out value='${sendInfoDetailT.productNo}' />-warn");
										$("<c:out value='${sendInfoDetailT.productNo}' />-warn").innerHTML='<img src="<c:url value='/images/icons/w.gif'/>" /><font color="red"><jecs:locale key="operation.warn.qty.erro"/></font>';
										//Effect.Fade("<c:out value='${sendInfoDetailT.productNo}' />-warn");
								}else{
										//Effect.Fade("<c:out value='${sendInfoDetailT.productNo}' />-warn");
										$("<c:out value='${sendInfoDetailT.productNo}' />-warn").innerHTML='<img src="<c:url value='/images/icons/r.gif'/>" />';
									}
						}
					</c:forEach>
					
					</c:if>
					<c:if test="${disabledFlag}">
							<c:forEach items="${pdSendInfoDetailList}" var="sendInfoDetailT" varStatus="status">
									qty       = $('<c:out value="${sendInfoDetailT.productNo}"/>-qty').value;
									allQty +=qty;
							</c:forEach>
					</c:if>
					**/
					return ret;
				}
			
			
			function toNewPdSendInfo(){
				        waiting();
						$('strAction').value='toNewPdSendInfo';
						$('pdSendInfoForm').submit();
				}
			
			function 	validateStock(){
						
						DWREngine.setAsync(false);
						DWRUtil.setValue('validateStockFlag',0);
						var ret = false;//
						var realQty;
						var qty;
						var warehouseNo = $('warehouseNo').value;
						<c:forEach items="${pdSendInfoDetailList}" var="sendInfoDetailT" varStatus="status">
							qty       = $('<c:out value="${sendInfoDetailT.productNo}"/>-qty').value;
							warnProductNo    = "<c:out value='${sendInfoDetailT.productNo}' />";
							if(qty>0){
								pdWarehouseStockManager.enoughStockSend(warehouseNo,'<c:out value="${sendInfoDetailT.productNo}"/>',qty,validateProduct);
							}
						</c:forEach>
				
						
						var flag= DWRUtil.getValue('validateStockFlag');
						if(flag==1){
							 ret = true;
							}
							
							return ret;
				}
			
			function toback(str){
			
				
    		this.location='<c:url value="/pdSendInfos.html"/>?strAction='+str;
    	}
    	
    	function saveTrackingNo(){
    			$('strAction').value='saveTrackingNo';
    			$('pdSendInfoForm').submit();
    		}
    		
    		
    		
    		function getPostalcode(){
    			var postalcode=$('recipientZip').value;
		   		jalPostalcodeManager.getJalPostalcodeByCode(postalcode,callGetPostalcode);
    			
    			}
    			
    			 function callGetPostalcode(valid){
		    	if(valid!=null){
		    	
						DWREngine.setAsync(false);
						dwr.util.setValue('recipientCaNo',valid.alCity.alStateProvince.stateProvinceId);
						getCity();
						dwr.util.setValue('city',valid.alCity.cityId);
						
					DWREngine.setAsync(true);
		    	}
		    	
		    }
    			
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
		dwr.util.setValue('city','${pdSendInfo.city}');
    	
		
			
		}
		
		<c:if test="${(not empty pdSendInfo.recipientCaNo) && !disabledFlag}">
		
		getCity();
	</c:if>
	
	function updateHurryFlag(siNo){
		var str = pdSendInfoManager.updateHurryFlag(siNo,validatePdHurry);
	}
	
	function shipTypeChange(shipType){
	     var changShipType = document.getElementById('shipType').value;
	     if(0==changShipType){
			    var qrzcfh = "";
			    var suspendShipment = document.getElementById('suspendShipment').value;
			    
		         if(1==suspendShipment){
		                qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.one'/>";
		         }else if(2==suspendShipment){
		                qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.two'/>";
		         }else if(3==suspendShipment){
		                qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.three'/>";
			     }else{
			        qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.emp'/>";
			     }
			    
		         if(confirm(qrzcfh)){
		         }else{
		             document.getElementById('shipType').value = shipType;
		         }
	     }
	}
	
	function updateShipType(siNo){
	    var qrzcfh = "";
	    var suspendShipment = document.getElementById('suspendShipment').value;
	    
         if(1==suspendShipment){
                qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.one'/>";
         }else if(2==suspendShipment){
                qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.two'/>";
         }else if(3==suspendShipment){
                qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.three'/>";
	     }else{
	            qrzcfh = "<jecs:locale key='pdsendinfo.suspendshipment.emp'/>";
	     }
	     
	    if(confirm(qrzcfh)){
	       var str = pdSendInfoManager.updateShipType(siNo,validateShipType);
	    }
	}

	
	function validatePdHurry(ret){
		if(ret=="succ"){
			alert("<jecs:locale key="pd.update.hurry.success"/>");
			document.getElementById('hurryFlag').value='1';
		}else if(ret=="haveDo"){
			alert("<jecs:locale key="pd.haveDo"/>"+"<jecs:locale key="pd.update.hurry.failured"/>");
		}else if(ret=="fail"){
			alert("<jecs:locale key="pd.update.hurry.failured"/>");
		}else{
			//alert("<jecs:locale key="pd.inquiryMechanismUpdate"/>"+"<jecs:locale key="pd.update.hurry.failured"/>");
			alert(ret+"<jecs:locale key="pd.update.hurry.failured"/>");
		}		
	}

	function validateShipType(ret){
		if(ret=="succ"){
			alert("<jecs:locale key="pd.update.shipType.success"/>");
			document.getElementById('shipType').value='0';
		}else if(ret=="haveDo"){
			alert("<jecs:locale key="pd.haveDo"/>"+"<jecs:locale key="pd.update.shipType.failured"/>");
		}else if(ret=="fail"){
			alert("<jecs:locale key="pd.update.shipType.failured"/>");
		}else{
			//alert("<jecs:locale key="pd.inquiryMechanismUpdate"/>"+"<jecs:locale key="pd.update.shipType.failured"/>");
			alert(ret+"<jecs:locale key="pd.update.shipType.failured"/>");		
		}
	}

	 function ggetUrl(logisticsDo,siNo){
	   		window.open("<c:url value='/mailStatuss.html'/>?strAction=logisticsDo&logisticsDo="+logisticsDo+"&siNo="+siNo,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     }
	
</script>

<v:javascript formName="pdSendInfo" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
