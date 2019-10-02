<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayAdviceDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayAdviceDetail.heading" />
</content>

<script language="javascript" src="scripts/validate.js"></script>
<!-- è£è½½æ¥åJS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form:form commandName="jfiPayAdvice" method="post"
	action="editJfiPayAdvice.html"
	onsubmit="return validateJfiPayAdvice(this)&&validateOthers(this)"
	id="jfiPayAdviceForm">
	<spring:bind path="jfiPayAdvice.*">
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
						<jecs:label key="fiPayAdvice.adviceCode" />
					</span>
				</th>
				<td>		
					<span class="textbox"> 
					    <form:input path="adviceCode" id="adviceCode" cssClass="textbox-text"
						readonly="true" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<label for="payDate" class="required">
							<span class="req">*</span>
							<jecs:locale key="comm.busi.deal.transaction.date" />
							:
						</label>
					</span>
				</th>
				<td>				
					<span class="textbox"> 
						<input name="payDate" id="payDate" type="text" 
						value="${param.payDate }" style="cursor: pointer;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>	
					</span>			 
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="telephone" class="required">
							<span class="req">*</span>
							<jecs:label key="fiPayAdvice.telephone" />
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox"> 
				 		<form:input path="telephone" id="telephone" cssClass="textbox-text"/>
				 	</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="accountCode" class="required">
							<span class="req">*</span>
							<jecs:locale key="fiPayAdvice.accountCode" />
							:
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox"> 
					 	<form:select path="accountCode" id="accountCode" cssClass="textbox-text">
							<c:forEach items="${fiPayBanks}" var="payBankVar">
								<option value="${payBankVar.accountCode }"
									<c:if test="${payBankVar.accountCode==jfiPayAdvice.accountCode }"> selected</c:if>>
									${payBankVar.accountCode } - ${payBankVar.bankName } -
									${payBankVar.accountNo }
								</option>
							</c:forEach>
						</form:select>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3">
					<span style="color: red;"><jecs:locale key="fiPayAdvice.accountCode.notice" /></span>
				</td>
			</tr> 
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="payMoney" class="required">
							<span class="req">*</span>
							<jecs:locale key="fiBankbookJournal.money" />
							:
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
				 		<form:input path="payMoney" id="payMoney" cssClass="textbox-text"/><br/>
				 	</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3" align="left">
					<span style="color: red;"><jecs:locale key="fiPayAdvice.payMoney.notice" /></span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="payMoney" class="required">
							<label for="notice" class="required">
								<span class="req">*</span>
								<jecs:label key="fiPayAdvice.notice" />
							</label>
						</span>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
				 		<form:input path="notice" id="notice" size="60" cssClass="textbox-text"/><br/>
				 	</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3">
					<span style="color: red;"><jecs:locale key="fiPayAdvice.notice.notice" /></span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiPayAdvice.dealType" />
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
				 		<form:input path="dealType" id="dealType" cssClass="textbox-text"/>
				 	</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3">
					<span style="color: red;"><jecs:locale key="fiPayAdvice.dealType.notice" /></span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="busi.common.remark" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
					 	<form:textarea path="remark" id="remark" rows="5" cols="50"
						htmlEscape="true" cssClass="textarea_border"/>
					</span>
				</td>
			</tr>
			<c:if test="${empty jfiPayAdvice.adviceCode}">
				<tr>
					<th>
					&nbsp;
				</th>
				<td colspan="3">
						<span style="color: red;"> <label>
								<jecs:locale key="fiPayAdvice.input" />
								:
						 </label>
						 <jecs:locale key="fiPayAdvice.input.notice" /></span>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="hidden" name="strAction" value="${param.strAction }" />

					<c:if test="${empty jfiPayAdvice.adviceCode}">
						<jecs:power powerCode="${param.strAction}">
							<input type="submit" class="btn btn_sele" name="save"
								onclick="bCancel=false"
								value="<jecs:locale key="operation.button.save"/>" />
						</jecs:power>
					</c:if>
	
					<c:if test="${not empty jfiPayAdvice.adviceCode}">
						<jecs:power powerCode="deletePayAdvice">
							<input type="submit" class="btn btn_sele" name="delete"
								onclick="bCancel=true;return confirmDelete(this.form,'PayAdvice')"
								value="<jecs:locale key="operation.button.delete"/>" />
						</jecs:power>
					</c:if>
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.location='jfiPayAdvices.html?strAction=listFiPayAdvices';"
						value="<jecs:locale key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>	
</form:form>

<script type="text/javascript">
Form.focusFirstElement($('jfiPayAdviceForm'));

function validateOthers(theForm){
	if(theForm.payMoney.value=="" || !isUnsignedNumeric(theForm.payMoney.value)){
		alert("<jecs:locale key="jfiPayAdvice.payMoney.required"/>");
		theForm.payMoney.focus();
		return false;
	}
	if(!isUnsignedMoney(theForm.payMoney.value)){
		alert("金额不符合要求");
		theForm.payMoney.focus();
		return false;
	}
	return isFormPosted();
}
</script>

<v:javascript formName="jfiPayAdvice" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
