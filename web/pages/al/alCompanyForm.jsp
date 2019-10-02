<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCompanyDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCompanyDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value="/dwr/interface/alStateProvinceManager.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/util.js"/>"></script>

<form:form commandName="alCompany" method="post" action="editAlCompany.html" onsubmit="return validateAlCompany(this)&&validateOther(this)" id="alCompanyForm">

<spring:bind path="alCompany.*">
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
<form:hidden path="companyId"/>

<table class='detail' width="70%" >
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.companyCode"/></span></th>
			<td>
				<span class="textbox">
				<c:if test="${not empty alCompany.companyId}">
				<form:input path="companyCode" id="companyCode" size="4" readonly="true" cssClass="textbox-text"/>
				</c:if>
				<c:if test="${empty alCompany.companyId}">
				<form:input path="companyCode" id="companyCode" size="4" cssClass="textbox-text"/>
				</c:if>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.companyName"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="companyName" id="companyName" size="60" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.characterCode"/></span></th>
			<td>
				<span class="textbox">
				<form:select path="characterCode" id="characterCode" items="${alCharacterCodings}" itemValue="characterCode" itemLabel="characterName"  cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.currencyCode"/></span></th>
			<td><span class="textbox"><form:input path="currencyCode" id="currencyCode" size="6" cssClass="textbox-text"/></span></td>
		</tr>


		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.preAgentCode"/></span></th>
			<td><span class="textbox"><form:input path="preAgentCode" id="preAgentCode" cssClass="textbox-text"/></span></td>
			
			<th><span class="text-font-style-tc"><jecs:label key="bdRecCrtOrder.productCode"/></span></th>
			<td><span class="textbox"><form:input path="productNo" id="productNo" cssClass="textbox-text"/></span></td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="bdRecCrtOrder.productCode"/></span></th>
			<td><span class="textbox"><form:input path="productNo" id="productNo" cssClass="textbox-text"/></span></td>
			
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.regName"/></span></th>
			<td><span class="textbox"><form:input path="regName" id="regName" size="60" cssClass="textbox-text"/></span></td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.taxRate"/></span></th>
			<td><span class="textbox"><form:input path="taxRate" id="taxRate" cssClass="textbox-text"/></span></td>

			<th><span class="text-font-style-tc"><jecs:label key="bdNetWorkCostReport.rateCH"/></span></th>
			<td><span class="textbox"><form:input path="exchangeRate" id="exchangeRate" cssClass="textbox-text"/></span></td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="bdNetWorkCostReport.rateCH"/></span></th>
			<td><span class="textbox"><form:input path="exchangeRate" id="exchangeRate" cssClass="textbox-text"/></span></td>
			
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.taxType"/></span></th>
			<td>
				<span class="textbox">
				<jecs:list listCode="tax_type" name="taxType" value="${alCompany.taxType}" defaultValue="1" styleClass="textbox-text"/>
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.zipCode"/></span></th>
			<td><span class="textbox"><form:input path="zipCode" id="zipCode" cssClass="textbox-text"/></span></td>
			
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.countryCode"/></span></th>
			<td>
				<span class="textbox">
				<form:select path="countryCode" id="countryCode"  cssClass="textbox-text" onchange="changeCountry(this.form);">
					<c:forEach items="${alCountrys}" var="alCountryVar">
						<option value="${alCountryVar.countryCode }"<c:if test="${alCountryVar.countryCode==alCompany.countryCode}"> selected</c:if>>[${alCountryVar.countryCode}] <jecs:locale key="${alCountryVar.countryName}"/></option>
					</c:forEach>
				</form:select>
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.stateProvinceCode"/></span></th>
			<td>
				<span class="textbox">
				<form:select  cssClass="textbox-text" path="stateProvinceCode">
					<form:option value=""></form:option>
					<c:forEach items="${alStateProvinces}" var="alStateProvinceVar">
						<option value="${alStateProvinceVar.stateProvinceCode }"<c:if test="${alStateProvinceVar.stateProvinceCode==alCompany.stateProvinceCode}"> selected</c:if>><jecs:locale key="${alStateProvinceVar.stateProvinceName}"/></option>
					</c:forEach>
				</form:select>
				</span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.city"/></span></th>
			<td><span class="textbox"><form:input path="city" id="city" cssClass="textbox-text"/></span></td>
		</tr>


		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.address"/></span></th>
			<td><span class="textbox"><form:input path="address" id="address" size="60" cssClass="textbox-text"/></span></td>
			
			<th><span class="text-font-style-tc"><jecs:label key="alCompany.phone"/></span></th>
			<td><span class="textbox"><form:input path="phone" id="phone" cssClass="textbox-text"/></span></td>
		</tr>

		
		<c:if test="${empty alCompany.companyId}">
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label key="alCompany.module.refrence"/></span></th>
				<td>
					<span class="textbox">
					<select name="refCompanyCode" Class="textbox-text">
						<option value=""></option>
						<c:forEach items="${refCompanys}" var="refCompanyVar">
							<option value="${refCompanyVar.companyCode }">[${refCompanyVar.companyCode }] ${refCompanyVar.companyName }</option>
						</c:forEach>
					</select>
					</span>
				<br>
				
			</tr>
			<tr class="edit_tr">
				<th>&nbsp;</th>
				<td colspan="2">
					
					<jecs:locale key="alCompany.module.refrence.remark"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<th>
				<jecs:label key="busi.common.remark"/>
			</th>
			<td  colspan="3">
在增加分公司完成后,请按照需求进行对应的设置:<br>
1. 此分公司可使用的模块权限设置<br>
2. 进入&lt;&lt;序列号管理&gt;&gt;进行序列号对应的设置,以避免一些单据生成有问题<br>
3. 进入&lt;&lt;参数维护&gt;&gt;进行参数对应的设置,以避免一些功能报错或者造成数据问题
				
			</td>
		</tr>
		
		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty alCompany.companyId}">
					<jecs:power powerCode="deleteAlCompany">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlCompany')"><jecs:locale key="operation.button.delete"/></button>
					</jecs:power>
				</c:if>
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='alCompanys.html?needReload=1'"><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>
</form:form>

<script type="text/javascript">
Form.focusFirstElement($('alCompanyForm'));

function validateOther(theForm){
	if(theForm.companyCode.value!="" && theForm.companyCode.value.length!=2 && !isAllUpperLetter(theForm.companyCode.value)){
		alert("<jecs:locale key="error.alCompany.companyCode.length"/>");
		theForm.companyCode.focus();
		return false;
	}
	if(theForm.taxRate.value!="" && !isUnsignedNumeric(theForm.taxRate.value)){
		alert("<jecs:locale key="alcompany.taxRate.invalidate"/>");
		theForm.taxRate.focus();
		return false;
	}
	if(theForm.exchangeRate.value!="" && !isUnsignedNumeric(theForm.exchangeRate.value)){
		alert("<jecs:locale key="alcompany.exchangeRate.invalidate"/>");
		theForm.taxRate.focus();
		return false;
	}
	
	return isFormPosted();
}

function changeCountry(theForm){
	dwr.util.removeAllOptions("stateProvinceCode");
 	alStateProvinceManager.getAlStateProvincesMapByCountry(theForm.countryCode.value, function(data){
 	dwr.util.addOptions("stateProvinceCode", data);
	});
}
</script>

<v:javascript formName="alCompany" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>