<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayBankDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayBankDetail.heading" />
</content>

<form:form commandName="jfiPayBank" method="post"
	action="editJfiPayBank.html" onsubmit="return validateFiPayBank(this)"
	id="jfiPayBankForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<spring:bind path="jfiPayBank.*">
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
			<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
				<tr class="edit_tr">
					<th>
						<jecs:label key="fiPayBank.companyCode" />
					</th>
					<td>
						<jecs:company selected="${param.companyCode }" name="companyCode"
						prompt="" withAA='false' />
					</td>
				</tr>
			</c:if>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdOutwardBank.bankCode" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<c:if test="${not empty jfiPayBank.accountCode}">
							<form:input path="accountCode" id="accountCode" cssClass="readonly"
								readonly="true" />
						</c:if>
						<c:if test="${empty jfiPayBank.accountCode}">
							<form:input path="accountCode" id="accountCode" cssClass="textbox-text"/>
						</c:if>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdSendRemittanceReport.openBankCH" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="bankName" id="bankName" size="60" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdSendRemittanceReport.openCityCH" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="bankCity" id="bankCity" size="60" cssClass="textbox-text"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdOutwardBank.accountName" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="accountName" id="accountName" size="60" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiPayBank.accountNo" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="accountNo" id="accountNo" size="60" cssClass="textbox-text"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="title.index" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="serialNo" id="serialNo" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="${param.strAction}">
					<input type="submit" class="btn btn_sele" name="save" onclick=
						bCancel;;
					= false;
					value="<jecs:locale key="operation.button.save"/>" />
									</jecs:power>
					
									<c:if test="${not empty jfiPayBank.accountCode}">
										<jecs:power powerCode="deleteFiPayBank">
											<input type="submit" class="btn btn_sele" name="delete" onclick=
						bCancel;;
					= true;
						return
												confirmDelete(this.form, 'FiPayBank');
					value="<jecs:locale key="operation.button.delete"/>" />
										</jecs:power>
									</c:if>
					
									<input type="button" class="btn btn_sele" name="cancel"
										onclick=
						history.back();;;;
					value="<jecs:locale key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>

</form:form>

<script type="text/javascript">
	Form.focusFirstElement($('jfiPayBankForm'));
</script>

<v:javascript formName="jfiPayBank" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
