<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jprRefundListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jprRefundListDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" >
					<jecs:locale key="button.save"/>
				</button>
		</jecs:power>
		<jecs:power powerCode="deleteJprRefundList">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JprRefundList')" >
					<jecs:locale key="button.delete"/>
				</button>
		</jecs:power>
</c:set>

<spring:bind path="jprRefundList.*">
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

<form:form commandName="jprRefundList" method="post" action="editJprRefundList.html" onsubmit="return validateJprRefundList(this)" id="jprRefundListForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JprRefundList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="prlId"/>

    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="jprRefundList.productNo"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="productNo" id="productNo" />
    </span></td></tr>

    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="jprRefundList.price"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="price" id="price" />
    </span></td></tr>

    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="jprRefundList.qty"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="qty" id="qty" />
    </span></td></tr>

    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="jprRefundList.pv"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="pv" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="pv" id="pv" />
    </span></td></tr>
    
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JprRefundList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </span></td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jprRefundListForm'));
</script>

<v:javascript formName="jprRefundList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
