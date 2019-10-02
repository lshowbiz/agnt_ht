<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterKeyDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterKeyDetail.heading"/></content>

<form:form commandName="alCharacterKey" method="post" action="editAlCharacterKey.html" onsubmit="return validateForm(this)" id="alCharacterKeyForm">

<spring:bind path="alCharacterKey.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:hidden path="keyId"/>
<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="alCharacterType.parentId"/></span></th>
		<td>
			<c:if test="${not empty alCharacterKey.alCharacterType.typeId}">
				<span class="textbox"><jecs:locale key="${alCharacterKey.alCharacterType.typeName }"/></span>
				<input type="hidden" name="typeId" value="${alCharacterKey.alCharacterType.typeId }"/>
			</c:if>
			<c:if test="${empty alCharacterKey.alCharacterType.typeId}">
				<span class="textbox"><jecs:locale key="alCharacterType.allType"/></span>
				<input type="hidden" name="typeId" value=""/>
			</c:if>
		</td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCharacterKey.characterKey"/></span></th>
			<td>
				<span class="textbox">
				<c:if test="${not empty alCharacterKey.keyId }">
					<form:input path="characterKey" id="characterKey" cssClass="textbox-text" readonly="true" size="60"/>
				</c:if>
				<c:if test="${empty alCharacterKey.keyId }">
					<form:input path="characterKey" id="characterKey" size="60" cssClass="textbox-text"/>
				</c:if>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="alCharacterKey.keyDesc"/>:</span></th>
			<td>
				<span class="textbox">
				<form:input path="keyDesc" id="keyDesc" cssClass="textbox-text" size="60" htmlEscape="true"/>
				</span>
			</td>
		</tr>

		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn_sele btn" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty alCharacterKey.keyId }">
				<jecs:power powerCode="deleteAlCharacterKey">
				<button type="submit" class="btn_sele btn" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlCharacterKey')" ><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
				</c:if>
				<button type="button" class="btn_sele btn" name="cancel" onclick="history.back();" ><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alCharacterKeyForm'));
    
    function validateForm(theForm){
    	if(theForm.characterKey.value==""){
    		alert("<jecs:locale key="alCharacterKey.characterKey.required"/>");
    		theForm.characterKey.focus();
    		return false;
    	}
    	if(theForm.keyDesc.value==""){
    		alert("<jecs:locale key="alCharacterKey.keyDesc.required"/>");
    		theForm.keyDesc.focus();
    		return false;
    	}
    	return isFormPosted();
    }
</script>

<v:javascript formName="alCharacterKey" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
