<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookTempDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempDetail.heading" />
</content>

<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/sysUserManager.js'/>"></script>
<script>
		   function searchMiMember(){
		   sysUserManager.getSysUser($("sysUser.userCode").value,callBack);
		   }
		   function callBack(valid){
			   if(valid==null){
			   	alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else{
				   document.getElementById('userName').innerText=valid.userName;
			   }
		   }
</script>
<form:form commandName="fiBankbookTemp" method="post"
	action="editFiBankbookTemp.html" onsubmit="return validateOthers(this)"
	id="fiBankbookTempForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<spring:bind path="fiBankbookTemp.*">
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
		<form:hidden path="tempId" />
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="label.member.or.agent.code" />
					</span>
				</th>
				<td>
					<c:if test="${not empty fiBankbookTemp.tempId}">
						<span class="textbox">
							<form:input path="sysUser.userCode" id="sysUser.userCode"
								cssClass="textbox-text" readonly="true"/>
						</span>
					</c:if>
					<c:if test="${empty fiBankbookTemp.tempId}">
						<span class="textbox">
							<form:input path="sysUser.userCode" id="sysUser.userCode" cssClass="textbox-text"/>
						</span>
					</c:if>
					<img src="<c:url value="/images/l_1.gif"/>" id="person"
							onclick="searchMiMember()" style="cursor: pointer;"
							title="<jecs:locale key="operation.button.search"/>" />
					<span id="userName">${fiBankbookTemp.sysUser.userName }</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiBankbookTemp.bankbookType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fibankbooktemp.bankbooktype"
						name="bankbookType" id="bankbookType"
						value="${fiBankbookTemp.bankbookType}" defaultValue="1" styleClass="textbox-text"/>
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
						id="dealType" value="${fiBankbookTemp.dealType}" defaultValue="A" styleClass="textbox-text"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiBankbookTemp.moneyType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<select name="moneyType" class="textbox-text">
							<c:forEach items="${moneyTypes}" var="moneyTypeVar">
								<c:if
									test="${moneyTypeVar.key!=13 && moneyTypeVar.key!=15 && moneyTypeVar.key!=16 && moneyTypeVar.key!=21 && moneyTypeVar.key!=22 && moneyTypeVar.key!=29 && moneyTypeVar.key!=30 && moneyTypeVar.key!=31 && moneyTypeVar.key!=32 && moneyTypeVar.key!=33}">
									<option value="${moneyTypeVar.key }"
										<c:if test="${moneyTypeVar.key==fiBankbookTemp.moneyType || (empty fiBankbookTemp.moneyType && moneyTypeVar.key==1)}"> selected</c:if>>
										<jecs:locale key="${moneyTypeVar.value }" />
									</option>
								</c:if>
							</c:forEach>
						</select>
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
			<tr class="edit_tr">

				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="bdBounsDeduct.description" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
						<form:textarea path="description" id="description" rows="5"
						cols="100" htmlEscape="true" cssClass="textarea_border"/>
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
					<c:if test="${not empty fiBankbookTemp.tempId}">
						<jecs:power powerCode="deleteFiBankbookTemp">
							<input type="submit" class="btn btn_sele" name="delete"
								onclick="bCancel=true;return confirmDelete(this.form,'FiBankbookTemp')"
								value="<jecs:locale key="operation.button.delete"/>" />
						</jecs:power>
					</c:if>
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="history.back();"
						value="<jecs:locale key="operation.button.cancel"/>" />		
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiBankbookTempForm'));
    function validateOthers(theForm){

		if(theForm.elements['sysUser.userCode'].value==""){
			alert("<jecs:locale key="bdBounsDeduct.error.memberNo.empty"/>");
			theForm.elements['sysUser.userCode'].focus();
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
		if(!isUnsignedMoney(theForm.money.value)){
			alert("金额不符合要求");
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

<v:javascript formName="fiBankbookTemp" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
