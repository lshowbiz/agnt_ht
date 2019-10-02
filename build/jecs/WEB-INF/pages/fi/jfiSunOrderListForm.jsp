<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunOrderListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunOrderListDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiSunOrderList">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiSunOrderList')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiSunOrderList.*">
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

<form:form commandName="jfiSunOrderList" method="post" action="editJfiSunOrderList.html" onsubmit="return validateJfiSunOrderList(this)" id="jfiSunOrderListForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunOrderList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="molId"/>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.productId"/>
    </th>
    <td align="left">
        <!--form:errors path="productId" cssClass="fieldError"/-->
        <form:input path="productId" id="productId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.pv"/>
    </th>
    <td align="left">
        <!--form:errors path="pv" cssClass="fieldError"/-->
        <form:input path="pv" id="pv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.qty"/>
    </th>
    <td align="left">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <form:input path="qty" id="qty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.weight"/>
    </th>
    <td align="left">
        <!--form:errors path="weight" cssClass="fieldError"/-->
        <form:input path="weight" id="weight" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.volume"/>
    </th>
    <td align="left">
        <!--form:errors path="volume" cssClass="fieldError"/-->
        <form:input path="volume" id="volume" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunOrderList.sprice"/>
    </th>
    <td align="left">
        <!--form:errors path="sprice" cssClass="fieldError"/-->
        <form:input path="sprice" id="sprice" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunOrderList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiSunOrderListForm'));
</script>

<v:javascript formName="jfiSunOrderList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
