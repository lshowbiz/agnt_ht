<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductCombinationDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductCombinationDetail.heading"/></content>

<%-- 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmProductCombination">
			<c:if test="${param.strAction=='editJpmProductCombination'}">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmProductCombination')" value="<jecs:locale key="operation.button.delete"/>" />
			</c:if>
		</jecs:power>
		<input type="button" class="button" name="back"
			onclick="javascript:history.back();"
			value="<jecs:locale  key="operation.button.return"/>" />
</c:set>
--%>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deleteJpmProductCombination">
			<c:if test="${param.strAction=='editJpmProductCombination'}">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmProductCombination')" ><jecs:locale key="operation.button.delete"/></button>
			</c:if>
		</jecs:power>
		<button type="button" class="btn btn_sele" name="back"
			onclick="javascript:history.back();"><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jpmProductCombination.*">
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

<form:form commandName="jpmProductCombination" method="post" action="editJpmProductCombination.html" onsubmit="return validateJpmProductCombination(this)" id="jpmProductCombinationForm">

<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="parentProductNo" value="${jpmProductCombination.productNo}"/>
<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductCombination')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70">
	<tbody class="window">
		<form:hidden path="jpcId"/>
		
		   
		
		    <tr class="edit_tr">
			    <th>
			        <span class="text-font-style-tc"><font color='red'>*</font><jecs:label  key="busi.product.productno"/></span>
			    </th>
			    <td align="left">
			        <!--form:errors path="subProductNo" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="subProductNo" readonly="true"  id="subProductNo" cssClass="textbox-text"/></span>
			        <button type="button" class="btn btn_sele" <c:if test="${param.strAction=='editJpmProductCombination'}">disabled="true"</c:if>  name="selectProduct" onclick="selectNotCombinateProduct()"><jecs:locale key="button.select"/></button> 
			    </td>
			 </tr>
			<tr class="edit_tr">
			 <th>
			        <span class="text-font-style-tc"><font color='red'>*</font><jecs:label  key="pd.qty"/></span>
			    </th>
			    <td align="left">
			        <!--form:errors path="subProductNo" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="qty" id="qty" cssClass="textbox-text" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="10" /></span>
			       
			    </td>
		 </tr>
		   <tr>		
			<td class="command" colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
    </tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductCombination')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
  //  Form.focusFirstElement($('jpmProductCombinationForm'));
    
    function selectNotCombinateProduct(){
    			open("<c:url value='/jpmProducts.html?strAction=selectProduct'/>","","height=400, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
</script>

<v:javascript formName="jpmProductCombination" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
