<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="upload.title" /></title>
<content tag="heading">
<jecs:locale key="upload.heading" />
</content>
<meta name="menu" content="FileUpload" />

<!--
    The most important part is to declare your form's enctype to be "multipart/form-data"
-->

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

<c:choose>
	<c:when test="${flagnum eq '1'}">
		<c:set var="strA" value="notChangeProduct"></c:set>
	</c:when>
</c:choose>

<form:form commandName="fileUpload" method="post"
	action="pdExchangeOrderFileUpload.html?strAction=${strA }&flagnum=${flagnum}"
	enctype="multipart/form-data"
	onsubmit="return validatePdExchangeOrderFileUpload(this)" id="uploadForm">
	<table class='detail' width="70%">
	<tbody class="window">
	<c:choose>
		<c:when test="${flagnum eq '7'}">
			<input type="hidden" name="name" value="11" id="name" />
		</c:when>
		<c:otherwise>
			<tr class="edit_tr">
				<th width="25%">
					<jecs:label key="uploadForm.name" styleClass="desc" />
				</th>
				<td colspan="3" >
					<form:errors path="name" cssClass="fieldError" />
					<form:input path="name" id="name" cssClass="text medium" />
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
		<tr class="edit_tr">
			<th width="25%">
				<jecs:label key="uploadForm.file" styleClass="desc" />
			</th>
			<td colspan="3">
				<form:errors path="file" cssClass="fieldError" />
				<spring:bind path="fileUpload.file">
					<input type="file" name="file" id="file" size='30'
						value="<c:out value="${status.value}"/>" />
				</spring:bind>
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th width="25%">
				<jecs:label key="busi.common.remark" styleClass="desc" />
			</th>
			<td colspan="3">	  
				<c:choose>
					<c:when test="${flagnum eq '1'}">
						<jecs:locale key="busi.common.remark.pdnotchange" />
					</c:when>	
				</c:choose>
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th width="25%">
				<jecs:label key="upload.module" styleClass="desc" />
			</th>
			<td colspan="3">	  
				<c:choose>
					<c:when test="${flagnum eq '1'}">
						<a href="./images/upload_pd08.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr class="edit_tr">
			
			<td colspan="4" align="center" class="command">
				<c:choose>
					<c:when test="${flagnum eq '1'}">
						<input type="submit" name="upload" class="btn btn_sele"
							onclick="uploadFile();" value="<jecs:locale key="operation.button.pdnotchangeproduct"/>" />
					</c:when>
				</c:choose>
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
	
	function validatePdExchangeOrderFileUpload(theForm){
		if(${flagnum}=='7'){

			if(theForm.file.value==""){
				alert("<jecs:locale key="bdBounsDeduct.importExcelFile.required"/>");
				theForm.file.focus();
				return false;
			}
		}
		waiting();
		return true;
	}

	function uploadFile() {

	}
	
</script>
<v:javascript formName="fileUpload" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
