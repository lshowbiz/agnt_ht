<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiPayAccountDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiPayAccountDetail.heading"/></content>



<spring:bind path="fiPayAccount.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="fiPayAccount" method="post" action="editFiPayAccount.html" onsubmit="return validateOthers(this)" id="fiPayAccountForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiPayAccount')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="accountId"/>

	 <tr><th>
        	支付平台：
    </th>
    <td align="left">
       <jecs:list listCode="providertypes" name="providerType" value="${fiPayAccount.providerType}" defaultValue="1" showBlankLine="false"/>
    </td></tr>
    
	<tr><th>
        	商户号：
    </th>
    <td align="left">
        <!--form:errors path="accountCode" cssClass="fieldError"/-->
        <form:input path="accountCode" id="accountCode" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        	商户密钥：
    </th>
    <td align="left">
        <!--form:errors path="passKey" cssClass="fieldError"/-->
        <form:input path="passKey" id="passKey" cssClass="text medium"/>
    </td></tr>
    
    

    <tr><th>
        	注册名称：
    </th>
    <td align="left">
        <!--form:errors path="accountName" cssClass="fieldError"/-->
        <form:input path="accountName" id="accountName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        	注册邮箱：
    </th>
    <td align="left">
        <!--form:errors path="registerEmail" cssClass="fieldError"/-->
        <form:input path="registerEmail" id="registerEmail" cssClass="text medium"/>
    </td></tr>

   

    

    <tr><th>
        	适用类型：
    </th>
    <td align="left">
        <select name="accountType" id="accountType" >
        	<option value="1">网页</option>
        	<option value="2">移动终端</option>
        </select>
    </td></tr>

    

    <tr><th>
        	生效状态：
    </th>
    <td align="left">
       <select name="status" id="status" >
        	<option value="1">启用</option>
        	<option value="0">禁用</option>
        </select>
        
    </td></tr>

	<tr><th>
        	是否默认：
    </th>
    <td align="left">
        <select name="isDefault" id="isDefault" >
        	
        	<option value="0">否</option>
        	<option value="1">是</option>
        </select>
        
    </td></tr>
    <tr><th>
        	备注：
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
        
		<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />

        <c:if test="${not empty fiPayAccount.accountId}">
        	<jecs:power powerCode="deleteFiPayAccount">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiPayAccount')" value="删除" />
		</jecs:power>
		</c:if>
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<fmt:message key="operation.button.cancel"/>" />
	
		
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiPayAccount')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiPayAccountForm'));
    function validateOthers(theForm){
  
  		if(theForm.elements['providerType'].value==""){
			alert("支付平台不能为空!");
			theForm.elements['providerType'].focus();
			return false;
		}
		
		if(theForm.elements['accountCode'].value==""){
			alert("商户号不能为空!");
			theForm.elements['accountCode'].focus();
			return false;
		}
		if(theForm.elements['passKey'].value==""){
			alert("商户密钥不能为空!");
			theForm.elements['passKey'].focus();
			return false;
		}
		if(theForm.elements['accountName'].value==""){
			alert("注册名称不能为空!");
			theForm.elements['accountName'].focus();
			return false;
		}
		
	}
</script>

<v:javascript formName="fiPayAccount" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
