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

<c:set var="strA" value="batchupdateLevel"></c:set>


<form:form commandName="fileUpload" method="post"
	action="batchJpoInviteList.html"
	enctype="multipart/form-data"
	onsubmit="return validateBatchJpoInviteList(this)" id="uploadForm">
	<table class='detail' width="70%">
	<tbody class="window">
		<tr class="edit_tr">
			<td align="right">
				<jecs:label key="uploadForm.name" styleClass="desc" />
			<td>
			<td colspan="3">
				<form:errors path="name" cssClass="fieldError" />
				<form:input path="name" id="name" cssClass="text medium" />
			</td>
		</tr>
		<tr class="edit_tr">
			<td align="right">
				<jecs:label key="uploadForm.file" styleClass="desc" />
			<td>
			<td colspan="2">
				<form:errors path="file" cssClass="fieldError" />
				<spring:bind path="fileUpload.file">
					<input type="file" name="file" id="file" size='30'
						value="<c:out value="${status.value}"/>" />
				</spring:bind>
			</td>
		</tr>
		<tr class="edit_tr">
			<td align="right">
				<jecs:label key="busi.common.remark" styleClass="desc" />
			</td>
			<td colspan="3">	  
			    <jecs:locale key="busi.common.remark.jpoInviteList" />
			</td>
		</tr>
	
		<tr class="edit_tr">
			
			<td align="right">
				<jecs:label key="upload.module" styleClass="desc" />
			</td>
			<td  colspan="3">	
			     <a href="./images/uploadBatchJmiValidLevelList.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>  
			</td>
		</tr>
		<tr  class="edit_tr">
			<td class="command"  colspan="4" align="center">
			    <button type="submit" name="upload" class="btn btn_sele" style="width:auto" onclick="uploadFile();"><jecs:locale key="button.editAll"/></button>
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
	
	function validateFileUpload2(theForm){
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
