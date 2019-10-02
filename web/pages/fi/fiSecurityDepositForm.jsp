<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiSecurityDepositDetail.title" /></title>
<content tag="heading">
<jecs:locale key="fiSecurityDepositDetail.heading" />
</content>


<spring:bind path="fiSecurityDeposit.*">
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

<form:form commandName="fiSecurityDeposit" method="post"
	action="editFiSecurityDeposit.html"
	onsubmit="return validateFiSecurityDeposit(this)"
	id="fiSecurityDepositForm">


	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="70%">
		<form:hidden path="fsdId" />
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiSecurityDeposit.userCode" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<c:if test="${empty fiSecurityDeposit.fsdId}">
							<form:input path="userCode" id="userCode" cssClass="textbox-text" />
						</c:if>
						<c:if test="${not empty fiSecurityDeposit.fsdId}">
		        	${fiSecurityDeposit.userCode }<form:hidden path="userCode"
								id="userCode" />
						</c:if>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiSecurityDeposit.userName" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="userName" id="userName" cssClass="textbox-text" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiSecurityDeposit.dept" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fisecuritydeposit.dept" name="dept" id="dept"
						showBlankLine="false" value="${fiSecurityDeposit.dept}" styleClass="textbox-text"
						defaultValue="0" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiSecurityDeposit.tel" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="tel" id="tel" cssClass="textbox-text" />
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
					<c:if test="${not empty fiSecurityDeposit.fsdId}">
						<jecs:power powerCode="deleteFiSecurityDeposit">
							<input type="submit" class="btn btn_sele" name="delete"
								onclick="bCancel=true;return confirmDelete(this.form,'FiSecurityDeposit')"
								value="<jecs:locale key="operation.button.delete"/>" />
						</jecs:power>
					</c:if>
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="location.href='<c:url value="/fiSecurityDeposits.html"/>'"
						value="<fmt:message key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiSecurityDeposit')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiSecurityDepositForm'));
</script>

<v:javascript formName="fiSecurityDeposit" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
