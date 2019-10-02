<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="upload.title" />
</title>
<content tag="heading">
<jecs:locale key="upload.heading" />
</content>
<meta name="menu" content="FileFiAvailableImportInit" />

<spring:bind path="fileUpload.*">
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


<c:set var="strA" value="uploadrecipientinfo"></c:set>


<form:form commandName="fileUpload" method="post"
	action="fiAvailableInvoiceImport.html?strAction=${strA }&flagnum=${flagnum}"
	enctype="multipart/form-data"
	onsubmit="return validateFiAvailableImport(this)" id="uploadForm">
	
	
	<table class='detail' width="70%">
		<tbody class="window">				
			<tr class="edit_tr">
				<th>
					<jecs:label key="uploadForm.name" styleClass="desc" />
				</th>
				<td colspan="3" >
				 	<form:errors path="name" cssClass="fieldError" />
					<form:input path="name" id="name" cssClass="text medium" />
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<jecs:label key="uploadForm.file" styleClass="desc" />
				</th>
				<td colspan="3" >
				 	<form:errors path="file" cssClass="fieldError" />
					<spring:bind path="fileUpload.file">
						<input type="file" name="file" id="file" size='30'
							value="<c:out value="${status.value}"/>" />
					</spring:bind>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<jecs:label key="busi.common.remark" styleClass="desc" />
				</th>
				<td colspan="3" >
				 	<jecs:locale key="pd.fiAvaliaInvoiceExportList" />
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<jecs:label key="upload.module" styleClass="desc" />
				</th>
				<td colspan="3" >
				 	<a href="./images/uploadFiAvaliaInvoice.xls" target="blank"><img
						src="<c:url value='/images/extremetable/xls.gif'/>" />
				</a>
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="submit" name="upload" class="btn btn_sele"
								onclick=
				uploadFile();;
			value="<jecs:locale key="fi.fiAvailableInvoiceImports"/>" />
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
	Form.focusFirstElement($('uploadForm'));
	highlightFormElements();

	function waiting(){
		disableButton();
		var o = document.createElement('iframe');
		o.id = 'fram_bk';
		document.body.appendChild(o);
		with ($('fram_bk').style){
			display='block';
			top = "60px";
			left = "30%";
		}
		var el=document.getElementById("sending_sub");
		if (el.hasChildNodes()){
		  el.removeChild(el.lastChild);
		}
		
		el.appendChild( document.createTextNode('<jecs:locale key="button.loading"/>'));
		document.getElementById("sending").style.visibility="visible";
		document.getElementById("sending").style.display="block";
	}
	
	function validateFiAvailableImport(){
		waiting();
		return true;
	}

	function uploadFile() {

	}
</script>
<v:javascript formName="fileUpload" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
