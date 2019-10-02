<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderFeeDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderFeeDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpoMemberOrderFee">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpoMemberOrderFee')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpoMemberOrderFee.*">
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
<script>
function onSubmitCheck(formId){
	if(validateJpoMemberOrder(formId)){
		if(isFormPosted()){
			return true;
		}else{
		return false;
		}
	}else{
		return false;
	}
}
</script>
<form:form commandName="jpoMemberOrderFee" method="post" action="editJpoMemberOrderFee.html" onsubmit="return onSubmitCheck(this);" id="jpoMemberOrderFeeForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoMemberOrderFee')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="mofId"/>

    <tr><th>
        <jecs:label  key="jpoMemberOrderFee.moId"/>
    </th>
    <td align="left">
        <!--form:errors path="moId" cssClass="fieldError"/-->
        <form:input path="moId" id="moId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoMemberOrderFee.fee"/>
    </th>
    <td align="left">
        <!--form:errors path="fee" cssClass="fieldError"/-->
        <form:input path="fee" id="fee" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoMemberOrderFee.feeType"/>
    </th>
    <td align="left">
        <!--form:errors path="feeType" cssClass="fieldError"/-->
        <form:input path="feeType" id="feeType" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoMemberOrderFee')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpoMemberOrderFeeForm'));
</script>

<v:javascript formName="jpoMemberOrderFee" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
