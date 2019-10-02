<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdFlitWarehouseDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdFlitWarehouseDetailDetail.heading"/></content>

<c:set var="buttons">

		<%-- <jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdFlitWarehouseDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdFlitWarehouseDetail')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power> --%>
		<jecs:power powerCode="${param.strAction}">
		      <button type="submit" class="btn btn_sele"  name="save" onclick="bCancel=false" ><jecs:locale key="button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deletePdFlitWarehouseDetail">
		     <button type="submit" class="btn btn_sele"  name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdFlitWarehouseDetail')" ><jecs:locale key="button.delete"/></button>
        </jecs:power>
        <button type="button" class="btn btn_sele"  name="toback" onclick="javascript:history.back();" ><jecs:locale  key="operation.button.return"/></button>
		
</c:set>

<spring:bind path="pdFlitWarehouseDetail.*">
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

<form:form commandName="pdFlitWarehouseDetail" method="post" action="editPdFlitWarehouseDetail.html" onsubmit="return validatePdFlitWarehouseDetail(this)" id="pdFlitWarehouseDetailForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdFlitWarehouseDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="uniNo"/>
<tr height="50px"></tr>
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="busi.product.productno"/></span>
    </th>
    <td>
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="productNo" disabled="true" id="productNo" cssClass="textbox-text"/></span>
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="pd.qty"/></span>
    </th>
    <td align="left">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="qty" id="qty" cssClass="textbox-text"/></span>
    </td></tr>
    
    <%-- <div id="titleBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div> --%>
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
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdFlitWarehouseDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdFlitWarehouseDetailForm'));
</script>

<v:javascript formName="pdFlitWarehouseDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
