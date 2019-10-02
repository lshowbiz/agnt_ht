<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysListValueDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysListValueDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>

<form:form commandName="sysListValue" method="post" action="editSysListValue.html" onsubmit="return validateForm(this)" id="sysListValueForm">

<spring:bind path="sysListValue.*">
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

<table class="detail" width="70%">
	<tbody class="window" >
<input type="hidden" name="keyId" value="${sysListValue.sysListKey.keyId }"/>
<form:hidden path="valueId"/>
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"> <jecs:label key="sysListValue.valueCode"/></span></th>
		<td>
			<span class="textbox">
			<c:if test="${not empty sysListValue.valueId }">
				<form:input path="valueCode" id="valueCode" cssClass="textbox-text" readonly="true" size="60"/>
			</c:if>
			<c:if test="${empty sysListValue.valueId }">
				<form:input path="valueCode" id="valueCode" size="60" cssClass="textbox-text"/>
			</c:if>
			</span>
		</td>
		<th><span class="text-font-style-tc"> <jecs:label key="sysListValue.valueTitle"/></span></th>
		<td align="left">
		   <span class="textbox"> <form:input path="valueTitle" id="valueTitle" size="60"  cssClass="textbox-text"/></span>
		   
		</td>
    </tr>

    
	<tr class="edit_tr">
	<th><span class="text-font-style-tc"> <jecs:label key="sysListValue.orderNo"/></span></th>
    <td align="left">
        <form:input path="orderNo" id="orderNo"/>
    </td></tr>
   
	<tr class="edit_tr">
		<th align="left" ><span class="edit_tr_span"> <jecs:label key="sysListValue.exCompanyCode"/><span></th>
		<td align="left"  colspan="3">
    	<c:forEach items="${alCompanys}" var="alCompanyVar">
    		<c:set var="isChecked" value="false"/>
    		<c:forEach items="${exCompanyCodes}" var="exCompanyCodeVar">
    			<c:if test="${exCompanyCodeVar==alCompanyVar.companyCode}">
    				<c:set var="isChecked" value="true"/>
    			</c:if>
    		</c:forEach>
    		<c:if test="${isChecked=='true'}">
    		<div class="edit_tr_div"><input type="checkbox" name="companyCode" value="${alCompanyVar.companyCode }" checked="checked" class="checkbox" id="companyCode_${alCompanyVar.companyCode }"/>${alCompanyVar.companyCode } - ${alCompanyVar.companyName }</div>
    		</c:if>
    		<c:if test="${isChecked=='false'}">
    		<div class="edit_tr_div"><input type="checkbox" name="companyCode" value="${alCompanyVar.companyCode }" class="checkbox" id="companyCode_${alCompanyVar.companyCode }"/>${alCompanyVar.companyCode } - ${alCompanyVar.companyName }</div>
    		</c:if>    	
    		
    	</c:forEach>
    </td>
	</tr>

   
    
    <tr>
		 <td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<c:if test="${not empty sysListValue.valueId }">
			<jecs:power powerCode="deleteSysListValue">
			<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysListValue')"><jecs:locale key="operation.button.delete"/></button>
			</jecs:power>
			</c:if>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysListValues.html?keyId=${sysListValue.sysListKey.keyId }'">
			<jecs:locale key="operation.button.cancel"/>
			</button>
			<input type="hidden" name="strAction" value="${param.strAction }"/>
		</td>
	</tr>

</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysListValueForm'));
    
    function validateForm(theForm){
    	if(theForm.valueCode.value==""){
    		alert("<jecs:locale key="sysListValue.valueCode.required"/>");
    		theForm.valueCode.focus();
    		return false;
    	}
    	if(theForm.valueTitle.value==""){
    		alert("<jecs:locale key="sysListValue.valueTitle.required"/>");
    		theForm.valueTitle.focus();
    		return false;
    	}
    	
    	if(theForm.orderNo.value!="" && !isInteger(theForm.orderNo.value)){
    		alert("<jecs:locale key="sysListValue.orderNo.mustBeInt"/>");
    		theForm.orderNo.focus();
    		return false;
    	}
    	return isFormPosted();
    }
</script>

<v:javascript formName="sysListValue" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
