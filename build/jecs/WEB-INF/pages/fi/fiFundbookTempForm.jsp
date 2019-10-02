<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiFundbookTempDetail.title" /></title>
<content tag="heading">
<jecs:locale key="fiFundbookTempDetail.heading" />
</content>

<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/sysUserManager.js'/>"></script>
<script>
function searchMiMember(){
	sysUserManager.getSysUser($("userCode").value,callBack);
}
function callBack(valid){
	if(valid==null){
		alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
	}else{
	  	document.getElementById('userName').innerText=valid.userName;
	}
}
</script>
<form:form commandName="fiFundbookTemp" method="post"
	action="editFiFundbookTemp.html"
	onsubmit="return validateOthers(this);return false;"
	id="fiFundbookTempForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />
	<form:hidden path="tempId"/>
	<spring:bind path="fiFundbookTemp.*">
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
	
	<table class='detail' width="70%">
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="label.member.or.agent.code" />
					</span>
				</th>
				<td>
					<span class="textbox">
					<c:if test="${not empty fiFundbookTemp.tempId}">
						<form:input path="userCode" id="userCode" cssClass="textbox-text"
							readonly="true" />
					</c:if>
					<c:if test="${empty fiFundbookTemp.tempId}">
						<form:input path="userCode" id="userCode" cssClass="textbox-text"/>						
					</c:if>					
					</span>
					<img src="<c:url value="/images/l_1.gif"/>" id="person"
							onclick="searchMiMember()" style="cursor: pointer;"
							title="<jecs:locale key="operation.button.search"/>" />
					<span id="userName">${sysUser.userName }</span>
				</td>				
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiBankbookTemp.bankbookType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fifundbook.bankbooktype" name="bankbookType"
						id="bankbookType" value="${fiFundbookTemp.bankbookType}"
						defaultValue="1" styleClass="textbox-text"/>
						
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiBankbookTemp.dealType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fibankbooktemp.dealtype" name="dealType"
						id="dealType" value="${fiFundbookTemp.dealType}" defaultValue="A" styleClass="textbox-text"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiBankbookTemp.moneyType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fifundbooktemp.moneytype" name="moneyType"
						value="${fiFundbookTemp.moneyType}" defaultValue="0"
						showBlankLine="true" styleClass="textbox-text"/>
						
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="money" class="required">
							<span class="req">*</span>
							<jecs:locale key="busi.finance.amount" />
							:
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
						<form:input path="money" id="money" maxlength="11" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="bdBounsDeduct.summary" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
						<form:textarea path="notes" id="notes" rows="5" cols="100"
						htmlEscape="true" cssClass="textarea_border"/>
					</span>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="${param.strAction}">
						<input type="submit" class="btn btn_sele" name="save"
							onclick="bCancel=false"
							value="<jecs:locale key="operation.button.save"/>" />
					</jecs:power>
					<!-- 
					<c:if test="${not empty fiFundbookTemp.tempId}">
						<jecs:power powerCode="deleteFiFundbookTemp">
							<input type="submit" class="btn btn_sele" name="delete"
								onclick="bCancel=true;return confirmDelete(this.form,'FiFundbookTemp')"
								value="<jecs:locale key="operation.button.delete"/>" />
						</jecs:power>
					</c:if>
					 -->
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="history.back();"
						value="<jecs:locale key="operation.button.cancel"/>" />					
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiFundbookTempForm'));
    function validateOthers(theForm){
    
		if(theForm.elements['userCode'].value==""){
			alert("<jecs:locale key="bdBounsDeduct.error.memberNo.empty"/>");
			theForm.elements['userCode'].focus();
			return false;
		}
		if(theForm.money.value=="" || !isUnsignedNumeric(theForm.money.value)){
			alert("<jecs:locale key="fiPayAdvice.money.required"/>");
			theForm.money.focus();
			return false;
		}
		if(theForm.money.value==0){
			alert("<jecs:locale key="fiPayAdvice.money.required"/>");
			theForm.money.focus();
			return false;
		}
		
		if(theForm.notes.value.length > 500){
			alert("<jecs:locale key="fi.notestolength.required"/>");
			theForm.notes.focus();
			return false;
		}
		
		return isFormPosted();
	}
</script>

<v:javascript formName="fiFundbookTemp" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>