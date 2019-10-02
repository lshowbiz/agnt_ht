<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiPayAccountConfigDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiPayAccountConfigDetail.heading"/></content>


<spring:bind path="fiPayAccountConfig.*">
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

<form:form commandName="fiPayAccountConfig" method="post" action="editFiPayAccountConfig.html" onsubmit="return validateFiPayAccountConfig(this)" id="fiPayAccountConfigForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiPayAccountConfig')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="province"/>

	<tr><th>
        	省份:
    </th>
    <td align="left">
    	${fiPayAccountConfig.provinceName }
       
    </td></tr>
    <tr><th>
        	商户号:
    </th>
    <td align="left">
    	<select id="accountId" name="accountId">
    		 <c:forEach items="${fiPayAccounts}" var="fiPayAccount">
    			<option value="${fiPayAccount.accountId}" <c:if test='${fiPayAccount.accountCode eq fiPayAccountConfig.fiPayAccount.accountCode}'>selected="selected"</c:if>>
    				${fiPayAccount.accountCode}，${fiPayAccount.accountName} [<jecs:code listCode="providertypes" value="${fiPayAccount.providerType}"/>]
    			</option>
    		 </c:forEach>
    	</select>
       
       
    </td></tr>
    
    <tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
        
		
		<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		
		

        <c:if test="${not empty fiPayAccount.accountId}">
        	<jecs:power powerCode="deleteFiPayAccountConfig">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiPayAccountConfig')" value="删除" />
			</jecs:power>
		</c:if>
		
		<input type="button" class="button" name="cancel" onclick="window.location.href='fiPayAccountConfigs.html'" value="<fmt:message key="operation.button.cancel"/>" />
	
		
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FiPayAccountConfig')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiPayAccountConfigForm'));
</script>

<v:javascript formName="fiPayAccountConfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
