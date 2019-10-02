<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayDataDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayDataDetail.heading" />
</content>

<script language="javascript" src="scripts/validate.js"></script>
<!-- è£è½½æ¥åJS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<form:form commandName="jfiPayData" method="post"
	action="editJfiPayData.html" onsubmit="return validateOthers(this)"
	id="jfiPayDataForm">

	<spring:bind path="jfiPayData.*">
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
						<jecs:label key="fiPayData.accountCode" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:select path="accountCode" id="accountCode" cssClass="textbox-text">
							<c:forEach items="${jfiPayBanks}" var="payBankVar">
								<option value="${payBankVar.accountCode }"
									<c:if test="${payBankVar.accountCode==jfiPayData.accountCode }"> selected</c:if>>
									${payBankVar.accountCode } - ${payBankVar.bankName } -
									${payBankVar.accountNo }
								</option>
							</c:forEach>
						</form:select>
					</span>
				</td>		
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="comm.busi.deal.transaction.date" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="dealDate" id="dealDate" cssClass="textbox-text"
						readonly="true" />
					</span>
				</td>		
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="incomeMoney" class="required">
							<span class="req">*</span>
							<jecs:locale key="fiPayData.incomeMoney" />
							:
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
				 		<form:input path="incomeMoney" id="incomeMoney" cssClass="textbox-text"/>
					</span>
				</td>		
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="fiPayData.excerpt" />
					</span>		
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
					 	<form:textarea path="excerpt" id="excerpt" rows="5" cols="50"
						htmlEscape="true" cssClass="textarea_border"/>
					</span>
				</td>		
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="hidden" name="strAction" value="${param.strAction }" />
					<jecs:power powerCode="${param.strAction}">
					<input type="submit" class="btn btn_sele" name="save"
						onclick=
						bCancel = false;
					value="<jecs:locale key="operation.button.save"/>" />
									</jecs:power>
					
									<c:if test="${not empty jfiPayData.dataId}">
										<jecs:power powerCode="deleteFiPayData">
											<input type="submit" class="btn btn_sele" name="delete"
												onclick=
						bCancel = true;
						return confirmDelete(this.form, 'FiPayData');
					value="<jecs:locale key="operation.button.delete"/>" />
										</jecs:power>
									</c:if>
					
									<input type="button" class="btn btn_sele" name="cancel"
										onclick=
						window.location = 'jfiPayDatas.html?strAction=listFiPayDatas&needReload=1';;
					value="<jecs:locale key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
Form.focusFirstElement($('jfiPayDataForm'));

function validateOthers(theForm){
	if(theForm.incomeMoney.value=="" || !isNumeric(theForm.incomeMoney.value)){
		alert("<jecs:locale key="fiPayData.incomeMoney.required"/>");
		theForm.incomeMoney.focus();
		return false;
	}
	if(!isUnsignedMoney(theForm.incomeMoney.value)){
		alert("金额不符合要求");
		theForm.incomeMoney.focus();
		return false;
	}
	return isFormPosted();
}
</script>

<v:javascript formName="jfiPayData" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
