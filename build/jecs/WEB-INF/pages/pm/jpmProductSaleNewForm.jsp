<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleNewDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductSaleNewDetail.heading" />
</content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<c:choose>
	<c:when test="${uniNoStr!=null && uniNoStr!='' && uniNoStr!='null'}">
		<script type="text/javascript">
			location.href='jpmProductSaleNews.html?strAction=confirmJpmProductSaleNew&uniNoStr=${uniNoStr }&status2=${status2 }';
		</script>
	</c:when>
	<c:otherwise>
<%--	
<c:set var="buttons">

	<c:choose>
		<c:when test="${param.strAction =='confirmJpmProductSaleNew'}">
			<jecs:power powerCode="confirmJpmProductSaleNew">
				<input type="button" class="button" name="confirm"
					onclick="editJpmProductSaleNew('confirm')"
					value="<jecs:locale  key='pmProductSale.confirm'/>" />

			</jecs:power>
		</c:when>

		<c:otherwise>
			<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save" onclick="bCancel = false;" value="<jecs:locale key='operation.button.save'/>" />
			</jecs:power>
		</c:otherwise>
	</c:choose>
	<!--  
	<jecs:power powerCode="deleteJpmProductSaleNew">
		<input type="submit" class="button" name="delete"
			onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleNew')"
			value="<jecs:locale key="operation.button.delete"/>" />
	</jecs:power>-->
	<input type="button" class="button" name="back" onclick="javascript:	history.back();"
		value="<jecs:locale  key="operation.button.return"/>" />
</c:set>
--%>
<c:set var="buttons">

	<c:choose>
		<c:when test="${param.strAction =='confirmJpmProductSaleNew'}">
			<jecs:power powerCode="confirmJpmProductSaleNew">
				<button type="button" class="btn btn_sele" name="confirm"
					onclick="editJpmProductSaleNew('confirm')"><jecs:locale  key='pmProductSale.confirm'/></button>

			</jecs:power>
		</c:when>

		<c:otherwise>
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save" onclick="bCancel = false;"><jecs:locale key='operation.button.save'/></button>
			</jecs:power>
		</c:otherwise>
	</c:choose>
	<!--  
	<jecs:power powerCode="deleteJpmProductSaleNew">
		<input type="submit" class="button" name="delete"
			onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleNew')"
			value="<jecs:locale key="operation.button.delete"/>" />
	</jecs:power>-->
	<button type="button" class="btn btn_sele" name="back" onclick="javascript:	history.back();"><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jpmProductSaleNew.*">
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

<form:form commandName="jpmProductSaleNew" method="post"
	action="editJpmProductSaleNew.html"
	onsubmit="return validateJpmProductSaleNew(this) && waiting();"
	id="jpmProductSaleNewForm">
<%-- 
	<div id="titleBar">
		<c:if test="${param.strAction=='confirmJpmProductSaleNew'}">
			
			<tr class="edit_tr">
			<span class="text-font-style-tc"><th><jecs:label key="piProduct.statusNo" /></th></span>
			<span class="textbox"><td><jecs:list listCode="pmproduct.statusno" styleClass="textbox-text" name="status" id="status"
				value="${jpmProductSaleNew.status}" defaultValue="1" /></td></span>
			</tr>
		</c:if>
--%>
		<%-- 
		<c:out value="${buttons}" escapeXml="false" />
		--%>
	</div>
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleNew')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
	

		<form:hidden path="uniNo" />
<%-- 		
		<fieldset style="padding: 2">
			<legend>
				<jecs:label key="productnew.basicinfo" />
			</legend>
			
--%>
			<table class='detail' width="70%">
				<tbody class="window">
					
					<c:if test="${sessionScope.sessionLogin.companyCode != 'AA'}">
						<form:hidden id="companyCode" path="companyCode" />
					</c:if>
					
					<c:if test="${sessionScope.sessionLogin.companyCode == 'AA'}">
						<tr class="edit_tr">
							<th>
								<span class="text-font-style-tc"><jecs:label key="sys.company.code" /></span>
							</th>
							<td>
								<!--form:errors path="companyCode" cssClass="fieldError"/-->
								<c:choose>
									<c:when test="${jpmProductSaleNew.jpmProduct.productNo == null}">
										<span class="textbox">
											<jecs:company name="companyCode" styleClass="textbox-text" 
												selected="${jpmProductSaleNew.companyCode}" prompt="" withAA="true"
												label="${disabled}"/>
										</span>
									</c:when>
									<c:otherwise>
										${jpmProductSaleNew.companyCode }
									</c:otherwise>
								</c:choose>					
							</td>
						</tr>
					</c:if>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productNo" /></span>
						</th>
						<td>
							<c:if test="${jpmProductSaleNew.jpmProduct.productNo != null}">
					        	<span class="textbox"><span class="textbox-text">${jpmProductSaleNew.jpmProduct.productNo}</span></span>
					        </c:if>
							<c:if test="${jpmProductSaleNew.jpmProduct.productNo == null}">
								<span class="textbox"><form:input path="jpmProduct.productNo" disabled="${disabled}"
									readonly="true" id="productNo" onclick="selectProduct();" 
									cssClass="textbox-text"/></span>
								<button type="button" class="btn btn_sele" name="select"
									onclick=selectProduct();><jecs:locale key="button.select"/></button>
							</c:if>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productName" /></span>
						</th>
						<td>
							<!--form:errors path="productName" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="productName" id="productName"
								cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
					</tr>
			
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.startOnSale" /></span>
						</th>
						<td>
							<span class="textbox">
								<form:input path="startOnSale" id="startOnSale" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
										cssClass="textbox-text" disabled="${disabled}"/></span>
						<%-- 
							<span class="textbox">
								<form:input path="startOnSale" id="startOnSale" readonly="true"
										cssClass="textbox-text" disabled="${disabled}"/></span>
							<img src="./images/calendar/calendar7.gif" id="img_startTime"
								style="cursor: pointer;"
								title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
							<script type="text/javascript">
								Calendar.setup( {
									inputField : "startOnSale",
									ifFormat : "%Y-%m-%d",
									button : "img_startTime",
									singleClick : true
								});
							</script>
							--%>
						</td>
				
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.endOnSale" /></span>
						</th>
						<td>
						<span class="textbox">
								<form:input path="endOnSale" id="endOnSale" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
										cssClass="textbox-text" disabled="${disabled}"/></span>
						<%-- 
							<span class="textbox">
								<form:input path="endOnSale" id="endOnSale" readonly="true"
										cssClass="textbox-text" disabled="${disabled}"/></span>
							<img src="./images/calendar/calendar7.gif" id="img_endTime"
								style="cursor: pointer;"
								title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
							<script type="text/javascript">
								Calendar.setup( {
									inputField : "endOnSale",
									ifFormat : "%Y-%m-%d",
									button : "img_endTime",
									singleClick : true
								});
							</script>
							--%>
						</td>
					</tr>
			
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.shipStrategy" /></span>
						</th>
						<td>
							<span class="textbox">
							<jecs:list styleClass="textbox-text" listCode="ship.strategy" name="shipStrategy"
									id="shipStrategy" showBlankLine="false"
									value="${jpmProductSaleNew.shipStrategy}" defaultValue=""/></span>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.logisticsStrategy" /></span>
						</th>
						<td>
							<span class="textbox">
							<jecs:list styleClass="textbox-text" listCode="ship.logisticsstrategy" name="logisticsStrategy"
									id="logisticsStrategy" showBlankLine="false"
									value="${jpmProductSaleNew.logisticsStrategy}" defaultValue=""/></span>
						</td>
					</tr>
					
			<!--  
					<tr>
						<th>
							<jecs:label key="jpmProductSaleNew.whoPrice" />
						</th>
						<td align="left">
							<form:input path="whoPrice" id="whoPrice" cssClass="text medium" disabled="${disabled}"/>
						</td>
					</tr>
			
					<tr>
						<th>
							<jecs:label key="jpmProductSaleNew.discountPrice" />
						</th>
						<td align="left">
							<form:input path="discountPrice" id="discountPrice"
								cssClass="text medium"  disabled="${disabled}"/>
						</td>
					</tr>
				-->	
				
<%-- 
				</tbody>
			</table>
		</fieldset>
		
		<fieldset style="padding: 2">
			<legend>
				<jecs:label key="productnew.detailinfo" />
			</legend>
			<table class='detail' width="70%">
				<tbody class="window">
				
--%>
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.weight" /></span>
						</th>
						<td>
							<!--form:errors path="weight" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="weight" id="weight" cssClass="textbox-text"  disabled="${disabled}"/></span>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.volume" /></span>
						</th>
						<td align="left">
							<!--form:errors path="volume" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="volume" id="volume" cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
					</tr>
			
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.length" /></span>
						</th>
						<td align="left">
							<!--form:errors path="length" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="length" id="length" cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.width" /></span>
						</th>
						<td align="left">
							<!--form:errors path="width" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="width" id="width" cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
					</tr>
			
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.height" /></span>
						</th>
						<td>
							<!--form:errors path="height" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="height" id="height" cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.storageCordon" /></span>
						</th>
						<td align="left">
							<!--form:errors path="storageCordon" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="storageCordon" id="storageCordon"
								cssClass="textbox-text" /></span>
						</td>
					</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.hotFlag" /></span>
						</th>
						<td>
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="yesno" name="hotFlag" id="hotFlag"
									showBlankLine="false" value="${jpmProductSaleNew.hotFlag}"
									defaultValue=""/></span>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.isHidden" /></span>
						</th>
						<td align="left">
							<!--form:errors path="isHidden" cssClass="fieldError"/--> 
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="yesno" name="isHidden" id="isHidden"
									showBlankLine="false" value="${jpmProductSaleNew.isHidden}"
									defaultValue="0"/></span>
						</td>
					</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.changeabledFlag" /></span>
						</th>
						<td align="left">
							<!--form:errors path="isHidden" cssClass="fieldError"/--> 
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="yesno" name="changeabledFlag" id="changeabledFlag"
									showBlankLine="false" value="${jpmProductSaleNew.changeabledFlag}"
									defaultValue="0"/></span>
						</td>
						
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.isRecommend" /></span>
						</th>
						<td align="left">
							<!--form:errors path="isHidden" cssClass="fieldError"/--> 
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="yesno" name="isRecommend" id="isRecommend"
									showBlankLine="false" value="${jpmProductSaleNew.isRecommend}"
									defaultValue="0"/></span>
						</td>
						
					</tr>
					
				<!-- add by lihao,添加商品和代金券的绑定关系 -->
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.bindJpmCoupon" /></span>
					</th>
					<td align="left">
						<c:choose>
							<c:when test="${not empty jpmProductSaleNew }">
								<c:if test="${not empty jpmProductSaleNew.jpmCouponRelationships }">
									<c:forEach begin="0" end="0" items="${jpmProductSaleNew.jpmCouponRelationships }" var="jpmCouponRelationship">
										
										<span class="textbox"><input type="text" name="cpName" id="cpName" class="textbox-text" readonly="readonly" value="${jpmCouponRelationship.jpmCouponInfo.cpName }"/></span>
									</c:forEach>
									
								</c:if>
								<c:if test="${empty jpmProductSaleNew.jpmCouponRelationships }">
									<span class="textbox"><input type="text" name="cpName" id="cpName" class="textbox-text" readonly="readonly" /></span>
								</c:if>

							</c:when>
							<c:otherwise>
								<span class="textbox"><input type="text" name="cpName" id="cpName" class="textbox-text" readonly="readonly" /></span>
							</c:otherwise>
							
						</c:choose>
						<c:if test = "${param.strAction=='editJpmProductSaleNew'}"> 
							<button type="button" class="btn btn_sele" name="select"
									onclick="selectJpmCouponInfo();"><jecs:locale key="button.select"/></button>
						</c:if>
						<c:if test = "${param.strAction=='addJpmProductSaleNew'}"> 
							<button type="button" class="btn btn_sele" name="select"
									onclick="selectJpmCouponInfo();"><jecs:locale key="button.select"/></button>
						</c:if>
					<%-- 	<form:hidden path="cpId" id="cpId"/>		--%>
						<input type="hidden" name="cpId" id="cpId" />
						
					</td>
				</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.sortFlag" /></span>
						</th>
						<td>
							<!--form:errors path="sortFlag" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="sortFlag" id="sortFlag" cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productSpecification" /></span>
						</th>
						<td>
							<span class="textbox"><form:input path="productSpecification" id="productSpecification" cssClass="textbox-text" disabled="${disabled}"/></span>
						</td>
						<c:if test="${param.strAction=='confirmJpmProductSaleNew'}">
			
						
							<th><span class="text-font-style-tc"><jecs:label key="piProduct.statusNo" /></span></th>
							<td><span class="textbox"><jecs:list listCode="pmproduct.statusno" styleClass="textbox-text" name="status" id="status"
							value="${jpmProductSaleNew.status}" defaultValue="1" /></span></td>
					
						</c:if>
					</tr>
					
<%-- 
				</tbody>
			</table>
		</fieldset>
		
		<fieldset style="padding: 2">
			<legend>
				<jecs:label key="productnew.other" />
			</legend>
			

		
			<table class='detail' width="70%">
				<tbody class="window">
--%>
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc-tare"><jecs:label key="jpmProductSaleNew.briefIntroduction" /></span>
						</th>
						<td colspan="3">
							<span class="text-font-style-tc-right">
								<form:textarea cssClass="textarea_border" path="briefIntroduction" id="briefIntroduction"
									disabled="${disabled}"/></span>
						</td>
					</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc-tare"><jecs:label key="jpmProductSaleNew.healthKnowledge" /></span>
						</th>
						<td colspan="3">
							<span class="text-font-style-tc-right">
								<form:textarea  cssClass="textarea_border" path="healthKnowledge" id="healthKnowledge"
									disabled="${disabled}"/></span>
						</td>
					</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc-tare"><jecs:label key="jpmProductSaleNew.productDesc" /></span>
						</th>
						<td colspan="3">
							<!--form:errors path="productDesc" cssClass="fieldError"/--> 
							<span class="text-font-style-tc-right">
							<form:textarea cssClass="textarea_border" path="productDesc" id="productDesc"
									disabled="${disabled}"/></span>
						</td>
					</tr>
				
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc-tare"><jecs:label key="jpmProductSaleNew.remark" /></span>
						</th>
						<td colspan="3">
							<!--form:errors path="remark" cssClass="fieldError"/-->
							<span class="text-font-style-tc-right">
								<form:textarea cssClass="textarea_border" path="remark" id="remark"
									disabled="${disabled}"/></span>
						</td>
					</tr>
					<%-- 
					<tr>
						<th class="command">
							<jecs:label key="sysOperationLog.moduleName" />
						</th>
						<td class="command">
							<c:out value="${buttons}" escapeXml="false" />
						</td>
					</tr>
					--%>
					<tr>		
						<td class="command" colspan="4" align="center">
							<c:out value="${buttons}" escapeXml="false"/>
						</td>
					</tr>
				</tbody>
			</table>
			
	<!--  	</fieldset>	-->
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleNew')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>
 </c:otherwise>
</c:choose>
<script type="text/javascript">
    Form.focusFirstElement($('jpmProductSaleNewForm'));
    
     function editJpmProductSaleNew(flag){
    	 	var ss = document.getElementById("shipStrategy").value;
     		var ls = document.getElementById("logisticsStrategy").value;
	     	if(ss=="" && ls==""){
	     		alert("<jecs:locale key="shipStrategy.notNull"/>\n<jecs:locale key="logistics.notNull"/> ");
	     		return false;
	     	}else if(ss==""){
	     		alert("<jecs:locale key="shipStrategy.notNull"/>");
	     		return false;
	     	}else if(ls==""){
	     		alert("<jecs:locale key="logistics.notNull"/>");
	     		return false;
	     	}
    		$('jpmProductSaleNewForm').submit();
    	}
    	function toback(strAction){
    		if(strAction=='addJpmProductSaleNew'){
    				strAction='editJpmProductSaleNew';
    			}
    		this.location='<c:url value="/jpmProductSaleNews.html"/>?strAction='+strAction;
    }
    function selectProduct(){
    		open("<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=400, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    }	
    
    //绑定代金券
    function selectJpmCouponInfo(){
    	
		open(
				"<c:url value='/jpmCouponInfos.html?strAction=selectJpmCouponInfo'/>",
				"",
				"height=700, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");

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
    
</script>

<v:javascript formName="jpmProductSaleNew" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
