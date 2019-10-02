<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiPayDataDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiPayDataDetail.heading" />
</content>

<form action="" method="post" enctype="multipart/form-data"
	name="jfiPosImportForm" id="jfiPosImportForm"
	onsubmit="return validateOthers(this)">

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


	<table class='detail' width="100%">
		<tr>
			<th>
				<label for="xlsFile" class="required">
					<span class="req">*</span>
					<jecs:locale key="fiPayData.importFile" />
					:
				</label>
			</th>
			<td align="left">
				<input type="file" name="xlsFile" id="xlsFile" size="50" />
			</td>
		</tr>

		<tr>
			<th>
				<label>
					<jecs:locale key="fiPayData.import.notice.label" />
					:
				</label>
			</th>
			<td align="left">
				<jecs:locale key="jfiPosImport.import.notice.text" />
			</td>
		</tr>

		<tr>
			<th class="command">
				<jecs:label key="sysOperationLog.moduleName" />
			</th>
			<td class="command">
				<input type="hidden" name="strAction" value="jfiPosImportExcel" />
				<jecs:power powerCode="jfiPosImportExcel">
					<input type="submit" class="button" name="import"
						onclick="bCancel=false" value="<jecs:locale key="button.import"/>" />
				</jecs:power>

				<input type="button" class="button" name="cancel"
					onclick="window.location='jfiPosImports.html?strAction=jfiPosImports&needReload=1';"
					value="<jecs:locale key="operation.button.cancel"/>" />
			</td>
		</tr>

	</table>
</form>

<script type="text/javascript">
Form.focusFirstElement($('jfiPosImportForm'));

function validateOthers(theForm){
	if(theForm.xlsFile.value=="" || getFileType(theForm.xlsFile.value)!=".xls"){
		alert("<jecs:locale key="fiPayData.xlsFile.required"/>");
		theForm.xlsFile.focus();
		return false;
	}
	return isFormPosted();
}
</script>