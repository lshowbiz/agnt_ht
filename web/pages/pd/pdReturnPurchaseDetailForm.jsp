<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdReturnPurchaseDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdReturnPurchaseDetailDetail.heading"/></content>

<c:set var="buttons">

		<%-- <jecs:power powerCode="editPdReturnPurchase">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="editPdReturnPurchase">
				<input type="submit" class="button" name="delete" onclick="$(strAction).value='deletePdReturnPurchaseDetail';bCancel=true;return confirmDelete(this.form,'PdReturnPurchaseDetail')" value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power> --%>
		<jecs:power powerCode="editPdReturnPurchase">
		    <button type="submit" class="btn btn_sele"  name="save" onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="editPdReturnPurchase">
		    <button type="submit" class="btn btn_sele"  name="delete" onclick="$(strAction).value='deletePdReturnPurchaseDetail';bCancel=true;return confirmDelete(this.form,'PdReturnPurchaseDetail')" ><jecs:locale key="operation.button.delete"/></button>
        </jecs:power>
            <button type="button" class="btn btn_sele"  name="toback" onclick="javascript:history.back();" ><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="pdReturnPurchaseDetail.*">
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

<form:form commandName="pdReturnPurchaseDetail" method="post" action="editPdReturnPurchaseDetail.html" onsubmit="return validatePdReturnPurchaseDetail(this)" id="pdReturnPurchaseDetailForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdReturnPurchaseDetail')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="uniNo"/>
	<form:hidden path="rpNo"/>

   <form:hidden path="price"/> 

    <tr height="50px"></tr>
    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="busi.product.productno"/></span>
    </th>
    <td>
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        
        <span class="textbox"><form:input path="productNo" id="productNo" readonly="true"  cssClass="textbox-text"/></span>
       	<!--input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" /--> 
        <!--input type="text" value="<jecs:locale key='${pdReturnPurchaseDetail.productNo}'/>" id="productName" readonly="true" onclick="selectProduct();" cssClass="text medium"/-->
        	
    </td>

    

    <th>
        <span class="text-font-style-tc"><jecs:label  key="pd.qty"/></span>
    </th>
    <td>
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="qty" id="qty" cssClass="textbox-text"/></span>
    </td></tr>

   <%--  <tr>
	    <th class="command">
	        <jecs:label key="sysOperationLog.moduleName"/>
	    </th>
	    <td class="command">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
	</tr> --%>
    <tr height="15px"></tr>
    <tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
	</tr>
</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdReturnPurchaseDetail')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdReturnPurchaseDetailForm'));
    function selectProduct(){
    		open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		//open("<c:url value='/pmProductSales.html?strAction=selectProduct&selectControl=all'/>");
    	}
</script>

<v:javascript formName="pdReturnPurchaseDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
