<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdExchangeOrderDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdExchangeOrderDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdExchangeOrderDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdExchangeOrderDetail')" value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
		        <input type="submit" class="button" name="returnOl" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="pdExchangeOrderDetail.*">
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

<form:form commandName="pdExchangeOrderDetail" method="post" action="editPdExchangeOrderDetail.html" onsubmit="return validatePdExchangeOrderDetail(this)" id="pdExchangeOrderDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="busi.product.productno"/>
    </th>
    <td align="left">
        <form:input path="productNo" id="productNo" cssClass="text medium" readonly="readonly"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pd.qty"/>
    </th>
    <td align="left">
        <form:input path="qty" id="qty" cssClass="text medium" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
    </td></tr>

</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdExchangeOrderDetailForm'));
    function returnOld(){
       window.history.back();
   }
</script>

<v:javascript formName="pdExchangeOrderDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
