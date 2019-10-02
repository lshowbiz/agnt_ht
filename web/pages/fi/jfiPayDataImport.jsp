<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiPayDataDetail.title" /></title>
<content tag="heading">
<jecs:locale key="fiPayDataDetail.heading" />
</content>

<form action="" method="post" enctype="multipart/form-data"
	name="jfiPayDataForm" id="jfiPayDataForm"
	onsubmit="return validateOthers(this)">

	<spring:bind path="jfiPayData.*">
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
				<th width="25%">
					<label for="xlsFile" class="required">
						<span class="req">*</span>
						<jecs:locale key="fiPayData.importFile" />
						:
					</label>
				</th>
				<td colspan="3" >
				 	<input type="file" name="xlsFile" id="xlsFile" size="50" />
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<label>
						<jecs:locale key="fiPayData.import.notice.label" />
						:
					</label>
				</th>
				<td colspan="3" >
				 	<jecs:locale key="fiPayData.import.notice.text" />
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">	
					<input type="hidden" name="strAction" value="importFiPayData" />
					<jecs:power powerCode="importFiPayData">
						<input type="submit" class="btn btn_sele" name="import"
							onclick="bCancel=false" value="<jecs:locale key="button.import"/>" />
					</jecs:power>
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.location='jfiPayDatas.html?needReload=1';"
						value="<jecs:locale key="operation.button.cancel"/>" />					
				</td>
			</tr>
		</tbody>
	</table>
</form>

<script type="text/javascript">
Form.focusFirstElement($('jfiPayDataForm'));

function validateOthers(theForm){
	if(theForm.xlsFile.value=="" || getFileType(theForm.xlsFile.value)!=".xls"){
		alert("<jecs:locale key="fiPayData.xlsFile.required"/>");
		theForm.xlsFile.focus();
		return false;
	}
	return isFormPosted();
}
</script>