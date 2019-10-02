<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="poCounterOrderDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="poCounterOrderDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="editPoCounterOrder">
<%-- 				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
 --%>		
                    <button type="submit" class="btn btn_sele"  name="save" onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deleteJpoCounterOrderDetail">
				
<%-- 				<input type="submit" class="button" name="delete" onclick="$('strAction').value='deleteJpoCounterOrderDetail';bCancel=true;return confirmDelete(this.form,'JpoCounterOrderDetail')" value="<jecs:locale key="operation.button.delete"/>" />
 --%>		
                    <button type="submit" class="btn btn_sele"  name="delete" onclick="$('strAction').value='deleteJpoCounterOrderDetail';bCancel=true;return confirmDelete(this.form,'JpoCounterOrderDetail')" ><jecs:locale key="operation.button.delete"/></button>
		</jecs:power>
		
<%-- 		<input type="button" class="button" name="back" onclick="toback('${param.strAction}');" value="<jecs:locale  key="operation.button.return"/>" />
 --%>
           <button type="button" class="btn btn_sele"  name="back" onclick="toback('${param.strAction}');" ><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jpoCounterOrderDetail.*">
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

<form:form commandName="jpoCounterOrderDetail" method="post" action="editJpoCounterOrderDetail.html" onsubmit="return validateJpoCounterOrderDetail(this)" id="poCounterOrderDetailForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="codNo"/>
<form:hidden path="jpoCounterOrder.coNo"/> 
 
 
 
 
 
 
 
 
    <tr height="50px"></tr>

    <tr  class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="busi.product.productno"/></span>
    </th>
    <td>
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        
        	
        <span class="textbox"><form:input path="jpmProductSaleNew.jpmProduct.productNo" id="productNo" readonly="true"  cssClass="textbox-text"/></span>
       	<!--input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" /-->
        <!--input type="text" value="${poCounterOrderDetail.pmProduct.productName}" id="productName" readonly="true"  cssClass="text medium"/-->
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="pd.price"/></span>
    </th>
    <td>
        <!--form:errors path="price" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="price" id="price" cssClass="textbox-text"/></span>
    </td></tr>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="pd.qty"/></span>
    </th>
    <td>
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="qty" id="qty" cssClass="textbox-text"/></span>
    </td></tr>
    <tr height="15px"></tr>
    <tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
	</tr>
<%-- <div class="search">
	<c:out value="${buttons}" escapeXml="false"/>
</div> --%>
</tbody>		
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PoCounterOrderDetail')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('poCounterOrderDetailForm'));
    
    function selectProduct(){
    		open("<c:url value='/pmProductSales.html?strAction=selectProduct&selectControl=assist'/>");
    	}
    	
    	
    	function toback(str){
    	
    		this.location='<c:url value="/editJpoCounterOrder.html"/>?strAction=editPoCounterOrder&coNo=${jpoCounterOrderDetail.jpoCounterOrder.coNo}';
    	}
</script>

<v:javascript formName="poCounterOrderDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
