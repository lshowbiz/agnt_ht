<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendExtraDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdSendExtraDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdSendExtra">
			<c:if test="${not empty pdSendExtra.uniId}">
				<input type="submit" class="button" name="delete" onclick="$('strAction').value='deletePdSendExtra'" value="<jecs:locale key="operation.button.delete"/>" />
			</c:if>
		</jecs:power>
</c:set>

<spring:bind path="pdSendExtra.*">
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

<form:form commandName="pdSendExtra" method="post" action="editPdSendExtra.html" onsubmit="return validatePdSendExtra(this)" id="pdSendExtraForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" id="strAction" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdSendExtra')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniId"/>
<form:hidden path="siNo"/>
   

    <tr><th>
        <jecs:label  key="busi.product.productno"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" onclick="selectSelProduct()" cssClass="text medium"/>
        	<input type="text" name="productName" id="productName" readonly="true" size="40"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pd.qty"/>
    </th>
    <td align="left">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <form:input path="qty" id="qty" cssClass="text medium"/>
    </td></tr>

    

    

    <tr><th>
         <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
         <form:textarea cols='70' rows='3'  path="remark"   id="remark" cssClass="table_textarea"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdSendExtra')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdSendExtraForm'));
    function selectSelProduct(){
    		open("<c:url value='/jpmProductSales.html?strAction=selectProduct&productProvider=LC&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		
    		
    	}
</script>

<v:javascript formName="pdSendExtra" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
