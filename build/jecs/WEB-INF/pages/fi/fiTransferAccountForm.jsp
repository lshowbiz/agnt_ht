<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiTransferAccountDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiTransferAccountDetail.heading" />
</content>

<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/sysUserManager.js'/>"></script>
<script>
   function searchMiMember(){
		sysUserManager.getSysUser($("destinationUserCode").value,callBack);
   }
   function callBack(valid){
	   if(valid==null){
	   	alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
	   }else{
		   document.getElementById('userName').innerText=valid.userName;
	   }
   }
</script>

<form:form commandName="fiTransferAccount" method="post"
	action="editFiTransferAccount.html"
	onsubmit="return validateFiTransferAccount(this)&&validateOthers(this)"
	id="fiTransferAccountForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<spring:bind path="fiTransferAccount.*">
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

	<table class='detail' width="70%">
		<tbody class="window">				
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiTransferAccount.destinationUserCode" />
					</span>
				</th>
				<td>
					<span class="textbox">
					 	<form:input path="destinationUserCode" id="destinationUserCode"
							cssClass="textbox-text" />
					</span>
					<img src="<c:url value="/images/l_1.gif"/>" id="person"
						onclick="searchMiMember()" style="cursor: pointer;"
						title="<jecs:locale key="operation.button.search"/>" />
					<span id="userName"></span>
				</td>			
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiTransferAccount.money" />
					</span>
				</th>
				<td>
					<span class="textbox">
				 		<form:input path="money" id="money" cssClass="textbox-text"/>
				 	</span>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="fiTransferAccount.notes" />
					</span>
				</th>
				<td colspan="3" >
					<span class="text-font-style-tc-right">
					 	<form:textarea path="notes" id="notes" rows="5" cols="50"
						htmlEscape="true" cssClass="textarea_border"/>
					</span>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiTransferAccount.paypassword" />
					</span>
				</th>
				<td colspan="3" >
					<span class="textbox">
				 		<input type="password" id="paypwd" name="paypwd" class="textbox-text">
				 	</span>
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="${param.strAction}">
						<input type="submit" class="btn btn_sele" name="save"
							onclick="bCancel=false"
							value="<fmt:message key="operation.button.save"/>" />
					</jecs:power>
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="history.back();"
						value="<fmt:message key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>
	<!--/fieldset-->

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiTransferAccountForm'));
    function validateOthers(theForm){
  
		if(theForm.elements['destinationUserCode'].value==""){
			alert("<jecs:locale key="destinationUserCode.error.empty"/>");
			theForm.elements['destinationUserCode'].focus();
			return false;
		}
		if(theForm.money.value=="" || !isUnsignedNumeric(theForm.money.value)){
			alert("<jecs:locale key="fiPayAdvice.money.required"/>");
			theForm.money.focus();
			return false;
		}
		if(theForm.money.value<=0){
			alert("<jecs:locale key="fiPayAdvice.money.required"/>");
			theForm.money.focus();
			return false;
		}
		if(!isUnsignedMoney(theForm.money.value)){
			alert("金额不符合要求");
			theForm.money.focus();
			return false;
		}
		
		if(document.getElementById('paypwd').value=='') {
			
			alert("<jecs:locale key="paypwd.error.empty"/>");
			document.getElementById('paypwd').focus();
			return false;			
		}
		
		var destin_user=theForm.elements['destinationUserCode'].value;
		var destin_money=theForm.money.value
		if(window.confirm('<jecs:locale key="transfer.sure"/>'+destin_money+'<jecs:locale key="transfer.to"/>'+destin_user+'?')){
			
			//document.getElementById('save').disabled=true;
			return isFormPosted();
		}else{
			return false;	
		}
	}
</script>

<v:javascript formName="fiTransferAccount" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
