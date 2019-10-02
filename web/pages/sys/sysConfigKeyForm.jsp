<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysConfigKeyDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysConfigKeyDetail.heading"/></content>

<form:form commandName="sysConfigKey" method="post" action="editSysConfigKey.html" onsubmit="return validateForm(this)" id="sysConfigKeyForm">

<spring:bind path="sysConfigKey.*">
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

<table class="detail" width="100%">

<form:hidden path="keyId"/>

    <tr><th>
        <jecs:label  key="sysConfigKey.configCode"/>
    </th>
    <td align="left">
    	<c:if test="${not empty sysConfigKey.keyId }">
        	<form:input path="configCode" id="configCode" cssClass="readonly" readonly="true" size="60"/>
	    </c:if>
	    <c:if test="${empty sysConfigKey.keyId }">
	        	<form:input path="configCode" id="configCode" size="40"/>
	    </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysConfigKey.keyDesc"/>
    </th>
    <td align="left">
        <form:input path="keyDesc" id="keyDesc" size="40"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysConfigKey.defaultValue"/>
    </th>
    <td align="left">
        <form:input path="defaultValue" id="defaultValue"/>
    </td></tr>
    
    <tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty sysConfigKey.keyId }">
				<jecs:power powerCode="deleteSysConfigKey">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysConfigKey')"><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
				</c:if>
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysConfigKeys.html?needReload=1'"><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>

</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysConfigKeyForm'));
    
    function validateForm(theForm){
    	if(theForm.configCode.value==""){
    		alert("<jecs:locale key="sysConfigKey.configCode.required"/>");
    		theForm.configCode.focus();
    		return false;
    	}
    	if(theForm.keyDesc.value==""){
    		alert("<jecs:locale key="sysConfigKey.keyDesc.required"/>");
    		theForm.keyDesc.focus();
    		return false;
    	}
    	if(theForm.defaultValue.value==""){
    		alert("<jecs:locale key="sysConfigKey.defaultValue.required"/>");
    		theForm.defaultValue.focus();
    		return false;
    	}
    	return isFormPosted();
    }
</script>

<v:javascript formName="sysConfigKey" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
