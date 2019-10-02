<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderFeeDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunMemberOrderFeeDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiSunMemberOrderFee">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiSunMemberOrderFee')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiSunMemberOrderFee.*">
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

<form:form commandName="jfiSunMemberOrderFee" method="post" action="editJfiSunMemberOrderFee.html" onsubmit="return validateJfiSunMemberOrderFee(this)" id="jfiSunMemberOrderFeeForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunMemberOrderFee')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="mofId"/>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrderFee.moId"/>
    </th>
    <td align="left">
        <!--form:errors path="moId" cssClass="fieldError"/-->
        <form:input path="moId" id="moId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrderFee.fee"/>
    </th>
    <td align="left">
        <!--form:errors path="fee" cssClass="fieldError"/-->
        <form:input path="fee" id="fee" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrderFee.feeType"/>
    </th>
    <td align="left">
        <!--form:errors path="feeType" cssClass="fieldError"/-->
        <form:input path="feeType" id="feeType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiSunMemberOrderFee.detailType"/>
    </th>
    <td align="left">
        <!--form:errors path="detailType" cssClass="fieldError"/-->
        <form:input path="detailType" id="detailType" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiSunMemberOrderFee')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiSunMemberOrderFeeForm'));
</script>

<v:javascript formName="jfiSunMemberOrderFee" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
