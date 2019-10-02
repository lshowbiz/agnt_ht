<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdNotChangeProductDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdNotChangeProductDetail.heading"/></content>

<c:set var="buttons">

<%-- 				<input type="button" class="button" name="save"  onclick="savePdNotChangeProduct(document.pdNotChangeProductForm)" value="<jecs:locale key="button.save"/>" />
 --%>				
                   	<button type="button" class="btn btn_sele" name="save"  onclick="savePdNotChangeProduct(document.pdNotChangeProductForm)" ><jecs:locale key="button.save"/></button>
                   
				<c:if test="${not empty pdNotChangeProduct.id}">
<%-- 					<input type="button" class="button" name="delete" onclick="deletePdNotChangeProduct(document.pdNotChangeProductForm)" value="<jecs:locale key="button.delete"/>" />
 --%>	                
                       	<button type="button" class="btn btn_sele" name="delete"  onclick="deletePdNotChangeProduct(document.pdNotChangeProductForm)" ><jecs:locale key="button.delete"/></button>
	            </c:if>
<%-- 	            <input type="button" class="button" name="back"  onclick="toBack()" value="<jecs:locale key="operation.button.return"/>" />
 --%>	            
                 <button type="button" class="btn btn_sele" name="back"  onclick="toBack()" ><jecs:locale key="operation.button.return"/></button>
               
</c:set>

<spring:bind path="pdNotChangeProduct.*">
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

<form:form commandName="pdNotChangeProduct" method="post" action="editPdNotChangeProduct.html" onsubmit="return validatePdNotChangeProduct(this)" id="pdNotChangeProductForm" name="pdNotChangeProductForm">


<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="id"/>
<tr height="50px"></tr>
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="jpmProductSaleNew.productNo"/></span>
    </th>
    <td>
        <span class="textbox"><form:input path="productNo" id="productNo" cssClass="textbox-text"/></span>
    </td></tr>
   <tr height="15px"></tr>
    <tr class="edit_tr">
			<td class="command"  colspan="2" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
	</tr>
   <!--  
    <tr><th>
        <jecs:label  key="pdNotChangeProduct.teamCode"/>
    </th>
    <td align="left">
        <form:input path="teamCode" id="teamCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdNotChangeProduct.orderType"/>
    </th>
    <td align="left">
        <form:input path="orderType" id="orderType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdNotChangeProduct.companyCode"/>
    </th>
    <td align="left">
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdNotChangeProduct.createUserCode"/>
    </th>
    <td align="left">
        <form:input path="createUserCode" id="createUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdNotChangeProduct.createTime"/>
    </th>
    <td align="left">
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdNotChangeProduct.isAvailable"/>
    </th>
    <td align="left">
        <form:input path="isAvailable" id="isAvailable" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdNotChangeProduct.other"/>
    </th>
    <td align="left">
        <form:input path="other" id="other" cssClass="text medium"/>
    </td></tr>
  -->
  </tbody>
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdNotChangeProductForm'));

    function savePdNotChangeProduct(theForm){
        document.getElementById("strAction").value="addPdNotChangeProduct";
    	theForm.submit();
    }

    function deletePdNotChangeProduct(theForm){
        document.getElementById("strAction").value="deletePdNotChangeProduct";
    	theForm.submit();
    }

    function toBack(){
        window.location.href="pdNotChangeProducts.html?strAction=pdNotChangeProductQuery";
    	//window.history.back();
    }
    
</script>

<v:javascript formName="pdNotChangeProduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
