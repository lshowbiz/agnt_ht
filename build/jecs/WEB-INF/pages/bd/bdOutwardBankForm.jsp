<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdOutwardBankDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="bdOutwardBankDetail.heading" />
</content>



<spring:bind path="bdOutwardBank.*">
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

<form:form commandName="bdOutwardBank" method="post"
	action="editBdOutwardBank.html"
	onsubmit="return validateBdOutwardBank(this)" id="bdOutwardBankForm">


	<input type="hidden" name="strAction" value="${param.strAction }" />
	<form:hidden path="bankId" />
	<table class='detail' width="70%">
		<tbody class="window">
			<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<jecs:locale key="bdReconsumMoneyReport.company" />
						</span>
					</th>
					<td>
						<span class="textbox">
							<jecs:company name="companyCode"
							selected="${bdOutwardBank.companyCode }" prompt="" withAA="false" />
						</span>
					</td>
					<th>
						&nbsp;
					</th>
					<td>
						&nbsp;
					</td>
				</tr>
			</c:if>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdOutwardBank.bankCode" required="true" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<c:if test='${bdOutwardBank.bankId!=null }'>
							<form:input path="bankCode" id="bankCode" cssClass="textbox-text"
								disabled="true" />
						</c:if>
						<c:if test='${bdOutwardBank.bankId==null }'>
							<form:input path="bankCode" id="bankCode" cssClass="textbox-text" />
						</c:if>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdOutwardBank.bankName" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="bankName" id="bankName" cssClass="textbox-text"/>
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
						<form:input path="cityName" id="cityName" cssClass="textbox-text" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdOutwardBank.accountName" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="accountName" id="accountName"
							cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="bdOutwardBank.accountCode" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="accountCode" id="accountCode"
							cssClass="textbox-text"/>
					</span>
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<button type="submit" class="btn btn_sele" name="save">
						<jecs:locale key="operation.button.save"/>
					</button>
					<button type="button" class="btn btn_sele" name="search" onclick="window.location.href=('bdOutwardBanks.html?strAction=bdOutwardBanks');">
						<jecs:locale key="operation.button.return"/>
					</button>
				</td>
			</tr>
		</tbody>
	</table>

		<%--<tr>
	<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
	<td class="command">
	<input type="submit" class="button" name="save" value="<jecs:locale key="operation.button.save"/>" />
		<input type="button" class="button" name="search"  onclick="window.location.href=('bdOutwardBanks.html?strAction=bdOutwardBanks');" value="<jecs:locale key="operation.button.return"/>" />

	</td>
</tr>--%>
	</table>
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('BdOutwardBank')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('bdOutwardBankForm'));
</script>

<v:javascript formName="bdOutwardBank" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
