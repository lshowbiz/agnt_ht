<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="upload.title" /></title>
<content tag="heading">
<jecs:locale key="upload.heading" />
</content>
<meta name="menu" content="FileUpload" />

<spring:bind path="fileUpload.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<%--<img src="<c:url value="/images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				--%><br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<c:choose>
	<c:when test="${flagnum eq '1'}">
		<c:set var="strA" value="jpoMemberOrderBatchQuery"></c:set>
	</c:when>
	<c:when test="${flagnum eq '2'}">
		<c:set var="strA" value="jpoMemberOrderBatchQueryCompare"></c:set>
	</c:when>
</c:choose>

<form:form commandName="fileUpload" method="post"
	action="jpoMemberBatchCheck.html?strAction=${strA }&flagnum=${flagnum}"
	enctype="multipart/form-data"
	onsubmit="return validateFileUpload2(this)" id="uploadForm">
	<table class='detail' width="70%">
		<tbody class="window">
		<tr class="edit_tr">
			<th width="25%">
				<jecs:title key="uploadForm.name" styleClass="desc" />
			</th>
			<td>
				<form:errors path="name" cssClass="fieldError" />
				<form:input path="name" id="name" cssClass="text medium" />
			</td>
		</tr>
		<tr class="edit_tr">
			<th width="25%">
				<jecs:title key="uploadForm.file" styleClass="desc" />
			</th>
			<td>
				<form:errors path="file" cssClass="fieldError" />
				<spring:bind path="fileUpload.file">
					<input type="file" name="file" id="file" size='30'
						value="<c:out value="${status.value}"/>" />
				</spring:bind>
			</td>
		</tr>
		<tr class="edit_tr">
			<th width="25%">
				<jecs:title key="busi.common.remark" styleClass="desc" />
			</th>
			<td>
				<c:choose>
						<c:when test="${flagnum eq '1'}">
							<jecs:locale key="operation.button.jpoOrderBatchQuery" />
						</c:when>
						<c:when test="${flagnum eq '2'}">
							<jecs:locale key="busi.common.remark.jpoMemberOrderBatchQueryCompare" />
						</c:when>
				</c:choose>	  
			</td>
		</tr>
		<tr class="edit_tr">
			<th width="25%">
				<jecs:title key="upload.module" styleClass="desc" />
			</th>
			<td>
			    <c:choose>
						<c:when test="${flagnum eq '1'}">
						      <a href="./images/jpoMemberOrderBatchQueryInit.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
						</c:when>
						<c:when test="${flagnum eq '2'}">
						      <a href="./images/jpoMemberOrderBatchQueryCompareInit.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
						</c:when>
				</c:choose>	 
				  
			</td>
		</tr>
		<tr class="edit_tr">
			<td  class="command"  colspan="2" align="center">
			       <c:choose>
							<c:when test="${flagnum eq '1'}">
										<button type="submit" name="upload" class="btn btn_sele" onclick="uploadFile();" >
											<jecs:locale key="jpoOrderBatchQuery"/>
										</button>
							</c:when>
							<c:when test="${flagnum eq '2'}">
										<button type="submit" name="upload" class="btn btn_sele" onclick="uploadFile();">
											<jecs:locale key="jpoOrderBatchQueryCompare"/>
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
	var checkSubmitFlg = false; 
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
		waitingEnd();
	}
	
	function validateFileUpload2(){
		if (checkSubmitFlg == true) { 
	         if(confirm('<jecs:locale key="po.orderBatchQuery"/>')){
	        	 window.location.reload();
	             return false; 
	         }
	     }
		waiting();
		 
      checkSubmitFlg = true; 
      return true; 
		
	}

	function uploadFile() {

	}

</script>
<v:javascript formName="fileUpload" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
