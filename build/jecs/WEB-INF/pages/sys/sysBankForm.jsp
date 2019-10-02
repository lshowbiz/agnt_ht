<%@ include file="/common/taglibs.jsp"%>
<script language="javascript" src="../scripts/validate.js"></script>
<script type="text/javascript">
	function validateSysBank(theForm){
		if(theForm.orderNo.value!="" && !isUnsignedInteger(theForm.orderNo.value)){
				alert('<jecs:locale key="sysModule.orderNo.error"/>');
	    		return false;
    	}
		if(theForm.bankKey.value==''){
			alert('<jecs:locale key="operation.notice.js.bankKey.notNull"/>');
			return false;
		}
		if(theForm.bankValue.value==''){
			alert('<jecs:locale key="operation.notice.js.bankValue.notNull"/>');
			return false;
		}

		return true;
	}
</script> 
<title><jecs:locale key="sysBankDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysBankDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<c:if test="${not empty sysBank.bankId}">
		<jecs:power powerCode="deleteSysBank">
				<button type="submit" class="btn btn_sele" name="delete" onclick="javascript:document.sysBankForm.strAction.value='deleteSysBank'" ><jecs:locale key="operation.button.delete"/></button>
		</jecs:power>
		
		</c:if>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()" ><jecs:locale key="operation.button.cancel"/></button>
</c:set>

<spring:bind path="sysBank.*">
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

<form:form commandName="sysBank" method="post" action="editSysBank.html" onsubmit="return validateSysBank(this)" id="sysBankForm" name="sysBankForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<form:hidden path="bankId"/>
<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysListValue.valueTitle"/></span></th>
			<td >
				<span class="textbox">
				<!--form:errors path="bankValue" cssClass="fieldError"/-->
				<form:input path="bankValue" id="bankValue" cssClass="textbox-text" size="60" onchange="javascript:document.getElementById('bankKey').value=this.value;" onkeyup="javascript:document.getElementById('bankKey').value=this.value;"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysListValue.valueCode"/></span></th>
			<td >
				<!--form:errors path="bankKey" cssClass="fieldError"/-->
				<span class="textbox">
					<form:input path="bankKey" id="bankKey" cssClass="textbox-text" readonly="true" size="60"/>
				</span>

			</td>
		</tr>

		

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sys.common.companyCode"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="companyCode" cssClass="fieldError"/-->
				<form:input path="companyCode" id="companyCode" cssClass="textbox-text" readonly="readonly" size="60"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysListValue.orderNo"/>:</span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="orderNo" cssClass="fieldError"/-->
				<form:input path="orderNo" id="orderNo" cssClass="textbox-text" size="60"/>
				</span>
			</td>
		</tr>

		<tr>
			<td class="command" colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
	</tbody>
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysBankForm'));
</script>

