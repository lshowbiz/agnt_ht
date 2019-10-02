<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysListKeyDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysListKeyDetail.heading"/></content>

<form:form commandName="sysListKey" method="post" action="editSysListKey.html" onsubmit="return validateForm(this)" id="sysListKeyForm">

<spring:bind path="sysListKey.*">
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
<form:hidden path="isReadOnly"/>

    <tr>
	    <th>
	        <jecs:label  key="sysListKey.listCode"/>
	    </th>
	    <td align="left">
	    	<c:if test="${not empty sysListKey.keyId }">
        	<form:input path="listCode" id="listCode" cssClass="readonly" readonly="true" size="60"/>
	        </c:if>
	        <c:if test="${empty sysListKey.keyId }">
	        	<form:input path="listCode" id="listCode" cssStyle="width:250px;"/>
	        </c:if>
	    </td>
    </tr>

    <tr>
	    <th>
	        <jecs:label  key="sysListKey.listName"/>
	    </th>
	    <td align="left">
	        <form:input path="listName" id="listName" cssStyle="width:250px;"/>
	    </td>
    </tr>
    <tr>
	    <th>
	        <jecs:label  key="busi.common.remark"/>
	    </th>
	    <td align="left">
	        <form:textarea path="remark" id="remark" rows="6" cssStyle="width:250px;"/>
	    </td>
    </tr>
    
    <tr>
		<td class="command">&nbsp;</td>
		<td class="command">
			<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<c:if test="${not empty sysListKey.keyId }">
			<jecs:power powerCode="deleteSysListKey">
			<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysListKey')" ><jecs:locale key="operation.button.delete"/></button>
			</jecs:power>
			</c:if>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysListKeys.html?needReload=1'"><jecs:locale key="operation.button.cancel"/></button>
			<input type="hidden" name="strAction" value="${param.strAction }"/>
		</td>
	</tr>

</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysListKeyForm'));
    
    function validateForm(theForm){
    	if(theForm.listCode.value==""){
    		alert("<jecs:locale key="sysListKey.listCode.required"/>");
    		theForm.listCode.focus();
    		return false;
    	}
    	if(theForm.listName.value==""){
    		alert("<jecs:locale key="sysListKey.listName.required"/>");
    		theForm.listName.focus();
    		return false;
    	}
    	return isFormPosted();
    }
</script>

<v:javascript formName="sysListKey" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
