<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleRelatedDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductSaleRelatedDetail.heading" />
</content>

<%-- 
<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="button" name="save"
			onclick="bCancel=false"
			value="<jecs:locale key="operation.button.save"/>" />
	</jecs:power>
	<jecs:power powerCode="deleteJpmProductSaleRelated">
		<c:if test="${param.strAction=='editJpmProductSaleRelated'}">
			<input type="submit" class="button" name="delete"
				onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleRelated')"
				value="<jecs:locale key="operation.button.delete"/>" />
		</c:if>
	</jecs:power>
	<input type="button" class="button" name="back"
		onclick="javascript:	history.back();"
		value="<jecs:locale  key="operation.button.return"/>" />
</c:set>
--%>
<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<button type="submit" class="btn btn_sele" name="save"
			onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
	</jecs:power>
	<jecs:power powerCode="deleteJpmProductSaleRelated">
		<c:if test="${param.strAction=='editJpmProductSaleRelated'}">
			<button type="submit" class="btn btn_sele" name="delete"
				onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleRelated')"><jecs:locale key="operation.button.delete"/></button>
		</c:if>
	</jecs:power>
	<button type="button" class="btn btn_sele" name="back"
		onclick="javascript:	history.back();"><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jpmProductSaleRelated.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="/images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<form:form commandName="jpmProductSaleRelated" method="post"
	action="editJpmProductSaleRelated.html"
	onsubmit="return validateJpmProductSaleRelated(this)"
	id="jpmProductSaleRelatedForm">

<%-- 
	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>
--%>
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleRelated')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
	<table class='detail' width="70">
		 <tbody class="window" >	
				<form:hidden path="id" />
		
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productNo" /></span>
					</th>
					<td>
						<c:choose>
							<c:when test="${jpmProductSaleRelated.jpmProductSaleNew.jpmProduct.productNo!=null}">
								<span class="textbox"><span class="textbox-text">${jpmProductSaleRelated.jpmProductSaleNew.jpmProduct.productNo }</span></span><input type="hidden" name="productNo" value="${jpmProductSaleRelated.jpmProductSaleNew.jpmProduct.productNo }" />
							</c:when>
							<c:otherwise>
								<span class="textbox"><span class="textbox-text">${productNo }</span></span><input type="hidden" name="uniNo" value="${uniNo }" /><input type="hidden" name="productNo" value="${productNo }" />
							</c:otherwise>
						</c:choose>
					</td>
				
					<th>
						<span class="text-font-style-tc"><jecs:label key="relationJpmProductSaleRelated.relationUniNo" /></span>
					</th>
					<td>
						<!--
						<form:input path="relationJpmProductSaleNew.jpmProduct.productNo" id="jpmProductSaleNew.jpmProduct.productNo" cssClass="text medium" onclick="selectProduct();"/>
						<input type="button" class="button" name="select"
							onclick="selectProduct();"
							value="<jecs:locale key="button.select"/>" />
						<form:hidden path="relationUniNo" id="relationUniNo"/>-->
						<c:choose>
							<c:when test="${jpmProductSaleRelated.relationJpmProductSaleNew.jpmProduct.productNo!=null}">
								<span class="textbox"><span class="textbox-text">${jpmProductSaleRelated.relationJpmProductSaleNew.jpmProduct.productNo }</span></span>
							</c:when>
							<c:otherwise>
								<span class="textbox"><form:input path="relationJpmProductSaleNew.jpmProduct.productNo" id="jpmProductSaleNew.jpmProduct.productNo" cssClass="textbox-text" onclick="selectProduct();"/></span>
								<button type="button" class="btn btn_sele" name="select"
									onclick="selectProduct();"><jecs:locale key="button.select"/></button>
								<form:hidden path="relationUniNo" id="relationUniNo"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
		
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc"><jecs:label key="jpmProductSaleRelated.status" /></span>
					</th>
					<td align="left">
						<!--form:errors path="status" cssClass="fieldError"/-->
						<span class="textbox"><jecs:list styleClass="textbox-text" listCode="jmimemberteam.status" name="status" id="status"
							showBlankLine="false" value="${jpmProductSaleRelated.status}"
							defaultValue="0" /></span>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleRelated')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmProductSaleRelatedForm'));
    
     function selectProduct() {
		open(
				"<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=3&uniNo="+${uniNo}+"'/>",
				"",
				"height=800, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	}
</script>

<v:javascript formName="jpmProductSaleRelated" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
