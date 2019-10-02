<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiSecurityDepositDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiSecurityDepositDetail.heading"/></content>


<spring:bind path="fiSecurityDeposit.*">
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

<form:form commandName="fiSecurityDeposit" method="post" action="downFiSecurityDeposit.html" onsubmit="return validateFiSecurityDeposit(this)" id="fiSecurityDepositForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="fsdId"/>

    <tr><th>
        <jecs:label key="fiSecurityDeposit.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        
        
        	${fiSecurityDeposit.userCode }
        
    </td></tr>

    <tr><th>
        <jecs:label  key="fiSecurityDeposit.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        ${fiSecurityDeposit.userName }
    </td></tr>

	<tr><th>
        <jecs:label  key="fiSecurityDeposit.dept"/>
    </th>
    <td align="left">
        <!--form:errors path="dept" cssClass="fieldError"/-->

		<jecs:code listCode="fisecuritydeposit.dept" value="${fiSecurityDeposit.dept}"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiSecurityDeposit.tel"/>
    </th>
    <td align="left">
        <!--form:errors path="tel" cssClass="fieldError"/-->
        ${fiSecurityDeposit.tel }
    </td></tr>
<%-- 
    <tr><th>
        <jecs:label  key="fiSecurityDeposit.email"/>
    </th>
    <td align="left">
        <!--form:errors path="email" cssClass="fieldError"/-->
        <form:input path="email" id="email" cssClass="text medium"/>
    </td></tr>
--%>
    <tr><th>
        <jecs:label  key="fiSecurityDeposit.balance"/>
    </th>
    <td align="left">
        <!--form:errors path="balance" cssClass="fieldError"/-->
        ${fiSecurityDeposit.balance }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiSecurityDeposit.down.amount"/>
    </th>
    <td align="left">
        <!--form:errors path="balance" cssClass="fieldError"/-->
        <input type="text" name="downAmount" id="downAmount" />
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiSecurityDeposit.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="balance" cssClass="fieldError"/-->
        <input type="text" name="remark" id="remark" />
    </td></tr>

    <tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
    
    	<jecs:power powerCode="${param.strAction}">
			<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="operation.button.save"/>" />
		</jecs:power>

		<input type="button" class="button" name="cancel" onclick="location.href='<c:url value="/fiSecurityDeposits.html"/>'" value="<fmt:message key="operation.button.cancel"/>" />
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiSecurityDeposit')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiSecurityDepositForm'));
</script>

<v:javascript formName="fiSecurityDeposit" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
