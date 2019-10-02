<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiAvailableInvoiceDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiAvailableInvoiceDetail.heading"/></content>

<spring:bind path="fiAvailableInvoice.*">
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

<form:form commandName="fiAvailableInvoice" method="post" action="editFiAvailableInvoice.html" onsubmit="return validateFiAvailableInvoice(this)" id="fiAvailableInvoiceForm" name="fiAvailableInvoiceForm">
<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th width="15%">
        <jecs:label  key="pd.agentormember"/>
    </th>
    <td align="left">
        <!--<form:input path="userCode" id="userCode" cssClass="text medium"/>-->
        ${fiAvailableInvoice.userCode}
    </td></tr>

    <tr><th>
        <jecs:label  key="sys.user.username"/>
    </th>
    <td align="left">
        <!--<form:input path="userName" id="userName" cssClass="text medium"/>-->
        ${fiAvailableInvoice.userName}
    </td></tr>

    <tr><th>
        <jecs:label  key="fiAvailableInvoice.invoiceValue"/>
    </th>
    <td align="left">
        <!--<form:input path="invoiceValue" id="invoiceValue" cssClass="text medium"/>-->
         ${fiAvailableInvoice.invoiceValue}
    </td></tr>

    <tr><th>
        <jecs:label  key="fiAvailableInvoice.bond"/>
    </th>
    <td align="left">
        <!--<form:input path="bond" id="bond" cssClass="text medium"/>-->
         ${fiAvailableInvoice.bond}
    </td></tr>

    <tr><th>
        <jecs:label  key="fiAvailableInvoice.jyear"/>
    </th>
    <td align="left">
        <!--<form:input path="jyear" id="jyear" cssClass="text medium"/>-->
        ${fiAvailableInvoice.jyear}
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </th>
    <td align="left">
        <!--<form:input path="jmonth" id="jmonth" cssClass="text medium"/>-->
         ${fiAvailableInvoice.jmonth}
    </td></tr>

    <tr><th>
        <jecs:label  key="fiAvailableInvoice.ownedCompany"/>
    </th>
    <td align="left">
        <!--<form:input path="ownedCompany" id="ownedCompany" cssClass="text medium"/>-->
        ${fiAvailableInvoice.ownedCompany}
    </td></tr>

    <tr><th>
        <jecs:label  key="pd.createTime"/>
    </th>
    <td align="left">
         <!--<form:input path="createTime" id="createTime" cssClass="text medium"/>-->
         ${fiAvailableInvoice.createTime }
    </td></tr>
    <tr><th>
        <jecs:label  key="pd.checkstatus"/>
    </th>
    <td align="left">
	    <jecs:code listCode="jmistore.status" value="${fiAvailableInvoice.status}"></jecs:code>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
       <!--   <form:input path="remark" id="remark" cssClass="text medium"/>-->
        ${fiAvailableInvoice.remark }
    </td></tr>

</table>

</form:form>
<table width="100%">
     <tr>
        <td align="right" width="17%">  
            <c:if test="${fiAvailableInvoice.status =='0'}">
                <input type="button" name="ssave"  class="button" onclick="fiAvailableInvoiceCheck(document.fiAvailableInvoiceForm)" value="<jecs:locale key="button.audit"/>" />&nbsp;&nbsp;&nbsp;&nbsp; 
               <input type="button" name="delet"  class="button" onclick="fiAvailableInvoiceDelete(document.fiAvailableInvoiceForm)" value="<jecs:locale key="operation.button.delete"/>" />&nbsp;&nbsp;&nbsp;&nbsp; 
            </c:if>
        </td>
        <td align="left" width="45%">       
               <input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
        </td> 
     
     </tr>
</table>
<script type="text/javascript">

    
    function fiAvailableInvoiceCheck(theForm){
    	document.getElementById("strAction").value= "checkFiAvailableInvoice";
    	 theForm.submit();
    }
    
    function fiAvailableInvoiceDelete(theForm){
        if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
           document.getElementById("strAction").value= "deleteFiAvailableInvoice";
           theForm.submit();
       }
    }

    function returnOld(){
        window.history.back();
    }
    
</script>

<v:javascript formName="fiAvailableInvoice" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
