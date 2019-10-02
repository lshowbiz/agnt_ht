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
		<c:set var="strA" value="uploadrecipientinfo"></c:set>
	</c:when>
	<c:when test="${flagnum eq '2'}">
		<c:set var="strA" value="uploaddepotgoodstate"></c:set>
	</c:when>
	<c:when test="${flagnum eq '3'}">
		<c:set var="strA" value="uploadstoragecordon"></c:set>
	</c:when>
	<c:when test="${flagnum eq '6'}">
		<c:set var="strA" value="uploaddepotgoodpdsendinfo"></c:set>
	</c:when>
	<c:when test="${flagnum eq '7'}">
		<c:set var="strA" value="uploadjpmcombinationrelated"></c:set>
	</c:when>
</c:choose>

<form:form commandName="fileUpload" method="post"
	action="pdFileUpload2.html?strAction=${strA }&flagnum=${flagnum}"
	enctype="multipart/form-data"
	onsubmit="return validateFileUpload2(this)" id="uploadForm">
	<table class='detail' width="70%">
	<tbody class="window">
	<c:choose>
		<c:when test="${flagnum eq '7'}">
			<input type="hidden" name="name" value="11" id="name" />
		</c:when>
		<c:otherwise>
			<tr class="edit_tr">
				<td align="right">
					<jecs:label key="uploadForm.name" styleClass="desc" />
				<td>
				<td colspan="3">
					<form:errors path="name" cssClass="fieldError" />
					<form:input path="name" id="name" cssClass="text medium" />
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
		<tr class="edit_tr">
			<td align="right">
				<jecs:label key="uploadForm.file" styleClass="desc" />
			<td>
			<td colspan="3">
				<form:errors path="file" cssClass="fieldError" />
				<spring:bind path="fileUpload.file">
					<input type="file" name="file" id="file" size='30'
						value="<c:out value="${status.value}"/>" />
				</spring:bind>
			</td>
		</tr>
		<c:choose>
		<c:when test="${flagnum eq '7'}">
			
		</c:when>
		<c:otherwise>
			<tr class="edit_tr">
				<td align="right">
					<jecs:label key="busi.common.remark" styleClass="desc" />
				</td>
				<td colspan="3">	  
					<c:choose>
						<c:when test="${flagnum eq '1'}">
							<jecs:locale key="busi.common.remark.pduploadrecipientinfo" />
						</c:when>
						<c:when test="${flagnum eq '2'}">
							<jecs:locale key="busi.common.remark.pduploaddepotgoodstate" />
						</c:when>
						<c:when test="${flagnum eq '3'}">
							<jecs:locale key="busi.common.remark.pduploadstoragecordon" />
						</c:when>
						<c:when test="${flagnum eq '6'}">
							<jecs:locale key="operation.button.uploaddepotgoodpdsendinfo" />
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
		
		<tr class="edit_tr">
			
			<td align="right">
				<jecs:label key="upload.module" styleClass="desc" />
			</td>
			<td  colspan="3">	  
				<c:choose>
					<c:when test="${flagnum eq '2'}">
						<a href="./images/upload_pd01.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
					</c:when>
					<c:when test="${flagnum eq '1'}">
						<a href="./images/upload_pd02.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
					</c:when>
					<c:when test="${flagnum eq '6'}">
						<a href="./images/upload_pd06.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
					</c:when>
					<c:when test="${flagnum eq '7'}">
						<a href="./images/upload_pd07.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr  class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:choose>
					<c:when test="${flagnum eq '1'}">
						<button type="submit" name="upload" class="btn btn_sele" style="width:auto"
							onclick="uploadFile();"><jecs:locale key="operation.button.uploadrecipientinfo"/></button>
					</c:when>
					<c:when test="${flagnum eq '2'}">
						<button type="submit" name="upload" class="btn btn_sele" style="width:auto"
							onclick="uploadFile();"><jecs:locale key="operation.button.uploaddepotgoodstate"/></button>
					</c:when>
					<c:when test="${flagnum eq '3'}">
						<button type="submit" name="upload" class="btn btn_sele" style="width:auto"
							onclick="uploadFile();"><jecs:locale key="operation.button.uploadstoragecordon"/></button>
					</c:when>
					<c:when test="${flagnum eq '6'}">
						<button type="submit" name="upload" class="btn btn_sele" style="width:auto"
							onclick="uploadFile();"><jecs:locale key="uploaddepotgoodpdsendinfo"/></button>
					</c:when>
					<c:when test="${flagnum eq '7'}">
						<button type="submit" name="upload" class="btn btn_sele" style="width:auto"
							onclick="uploadFile();"><jecs:locale key="piliang.shangchuan"/></button>
						<button type="button" class="btn btn_sele" style="width:auto" name="cancel"  onclick="window.location.href='jpmCombinationRelateds.html'">
							<jecs:locale key="operation.button.return"/>
						</button>
						
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
