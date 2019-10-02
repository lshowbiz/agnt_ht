<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="itemsDetail.title"/></title>
<content tag="heading"><jecs:locale key="itemsDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteItems">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'Items')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="items.*">
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

<form:form commandName="items" method="post" action="editItems.html" onsubmit="return validateItems(this)" id="itemsForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Items')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="itemsId"/>

    <tr><th>
        <jecs:label  key="items.statusId"/>
    </th>
    <td align="left">
        <!--form:errors path="statusId" cssClass="fieldError"/-->
        <form:input path="statusId" id="statusId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="items.accepttime"/>
    </th>
    <td align="left">
        <!--form:errors path="accepttime" cssClass="fieldError"/-->
        <form:input path="accepttime" id="accepttime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="items.acceptaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="acceptaddress" cssClass="fieldError"/-->
        <form:input path="acceptaddress" id="acceptaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="items.event"/>
    </th>
    <td align="left">
        <!--form:errors path="event" cssClass="fieldError"/-->
        <form:input path="event" id="event" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('Items')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('itemsForm'));
</script>

<v:javascript formName="items" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
