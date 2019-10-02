<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdOutwardBankDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdOutwardBankDetail.heading"/></content>



<spring:bind path="bdOutwardBank.*">
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

<form:form commandName="bdOutwardBank" method="post" action="editBdOutwardBank.html" onsubmit="return validateBdOutwardBank(this)" id="bdOutwardBankForm">
	<div class="searchBar">	
	<input type="button" class="button" name="search"  onclick="window.location.href=('bdOutwardBanks.html?strAction=bdOutwardBanks');" value="<jecs:locale key="operation.button.return"/>" />
	
	</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="bankId"/>

    <tr><th>
        <c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<jecs:locale key="bdReconsumMoneyReport.company"/>:
    </th>
    <td align="left">
        
       
			<jecs:company name="companyCode" selected="${bdOutwardBank.companyCode }" label="true"  prompt="" withAA="false"  />
        
		
    </td></tr>
</c:if>

    <tr><th>
        <jecs:label  key="bdOutwardBank.bankCode"/>
    </th>
    <td align="left">
        <!--form:errors path="bankCode" cssClass="fieldError"/-->

        	
	${bdOutwardBank.bankCode }

         
         
    </td></tr>

    <tr><th>
        <jecs:label  key="bdOutwardBank.bankName"/>
    </th>
    <td align="left">
        <!--form:errors path="bankName" cssClass="fieldError"/-->
       
        ${bdOutwardBank.bankName }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdSendRemittanceReport.openCityCH"/>
    </th>
    <td align="left">
        <!--form:errors path="cityName" cssClass="fieldError"/-->
        ${bdOutwardBank.cityName }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdOutwardBank.accountName"/>
    </th>
    <td align="left">
        <!--form:errors path="accountName" cssClass="fieldError"/-->
        ${bdOutwardBank.accountName }
    </td></tr>

    <tr><th>
        <jecs:label  key="bdOutwardBank.accountCode"/>
    </th>
    <td align="left">
        <!--form:errors path="accountCode" cssClass="fieldError"/-->
        ${bdOutwardBank.accountCode }
    </td></tr>
<%--<tr>
	<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
	<td class="command">
		<input type="button" class="button" name="search"  onclick="window.location.href=('bdOutwardBanks.html?strAction=bdOutwardBanks');" value="<jecs:locale key="operation.button.return"/>" />
	</td>
</tr>--%>
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('bdOutwardBankForm'));
</script>

<v:javascript formName="bdOutwardBank" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
