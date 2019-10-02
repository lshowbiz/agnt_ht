<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookTempDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempDetail.heading" />
</content>

<form action="" method="post" enctype="multipart/form-data"
	name="jfiBankbookTempForm" id="jfiBankbookTempForm"
	onsubmit="return validateOthers(this)">

	<spring:bind path="jfiBankbookTemp.*">
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
				<td>
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
				<td>
				 	<jecs:locale key="fiBankbookTemp.import.notice.text" />
					<br />
					<br />
					<jecs:locale key="fiBankbookTemp.dealType" />
					<br />
					<c:forEach items="${dealTypes}" var="dealTypeVar">
	    			${dealTypeVar.key } - <jecs:locale key="${dealTypeVar.value }" />
						<br />
					</c:forEach>
					<br />
					<jecs:locale key="fiBankbookTemp.moneyType" />
					:
					<br />
					<c:forEach items="${moneyTypes}" var="moneyTypeVar">
						<c:if
							test="${moneyTypeVar.key!=13 && moneyTypeVar.key!=15 && moneyTypeVar.key!=16 && moneyTypeVar.key!=21 && moneyTypeVar.key!=22 && moneyTypeVar.key!=29 && moneyTypeVar.key!=30 && moneyTypeVar.key!=31 && moneyTypeVar.key!=32 && moneyTypeVar.key!=33}">
	    			${moneyTypeVar.key } - <jecs:locale key="${moneyTypeVar.value }" />
							<br />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="hidden" name="strAction" value="importFiBankbookTemp" />
					<jecs:power powerCode="importFiBankbookTemp">
						<input type="submit" class="btn btn_sele" name="import"
							onclick="bCancel=false" value="<jecs:locale key="button.import"/>" />
					</jecs:power>
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.location='jfiBankbookTemps.html?strAction=listfiBankbookTemps&needReload=1';"
						value="<jecs:locale key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>
</form>

<script type="text/javascript">
Form.focusFirstElement($('jfiBankbookTempForm'));

function validateOthers(theForm){
	if(theForm.xlsFile.value=="" || getFileType(theForm.xlsFile.value)!=".xls"){
		alert("<jecs:locale key="fiBankbookTemp.xlsFile.required"/>");
		theForm.xlsFile.focus();
		return false;
	}
	return isFormPosted();
}
</script>