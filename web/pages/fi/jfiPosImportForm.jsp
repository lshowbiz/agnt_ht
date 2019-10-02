<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPosImportDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPosImportDetail.heading" />
</content>

<c:set var="buttons">
	<input type="submit" class="button" name="save"
		onclick="bCancel=false;return isFormPosted();"
		value="<jecs:locale key="button.next"/>" />
</c:set>

<spring:bind path="jfiPosImport.*">
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

<form:form commandName="jfiPosImport" method="post"
	action="editJfiPosImport.html" id="jfiPosImportForm">
	
	<table class='detail' width="70%">
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="jfiPosImport.posNo" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="posNo" id="posNo" cssClass="textbox-text"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiBankbookJournal.money" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="amount" id="amount" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="jpmCardSeq.sequenceNo" />
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
						<form:input path="pid" id="pid" size="8" cssClass="textbox-text"/>
						-
						<form:input path="tel" size="8" id="tel" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="submit" class="btn btn_sele" name="save"
					onclick="bCancel=false;return isFormPosted();"
					value="<jecs:locale key="button.next"/>" />					
				</td>
			</tr>
		</tbody>
	</table>
</form:form>