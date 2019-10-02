<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBusinessNumDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiBusinessNumDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiBusinessNum">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiBusinessNum')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiBusinessNum.*">
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

<form:form commandName="jfiBusinessNum" method="post" action="editJfiBusinessNum.html" onsubmit="return validateJfiBusinessNum(this)" id="jfiBusinessNumForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiBusinessNum')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="businessId"/>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.paymentCompany"/>
    </th>
    <td align="left">
        <!--form:errors path="paymentCompany" cssClass="fieldError"/-->
        <form:input path="paymentCompany" id="paymentCompany" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.merchantId"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantId" cssClass="fieldError"/-->
        <form:input path="merchantId" id="merchantId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.merchantName"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantName" cssClass="fieldError"/-->
        <form:input path="merchantName" id="merchantName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.merchantType"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantType" cssClass="fieldError"/-->
        <form:input path="merchantType" id="merchantType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.password"/>
    </th>
    <td align="left">
        <!--form:errors path="password" cssClass="fieldError"/-->
        <form:input path="password" id="password" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.password2"/>
    </th>
    <td align="left">
        <!--form:errors path="password2" cssClass="fieldError"/-->
        <form:input path="password2" id="password2" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.busicode"/>
    </th>
    <td align="left">
        <!--form:errors path="busicode" cssClass="fieldError"/-->
        <form:input path="busicode" id="busicode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.address"/>
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.maxMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="maxMoney" cssClass="fieldError"/-->
        <form:input path="maxMoney" id="maxMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.merchantStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="merchantStatus" cssClass="fieldError"/-->
        <form:input path="merchantStatus" id="merchantStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.createName"/>
    </th>
    <td align="left">
        <!--form:errors path="createName" cssClass="fieldError"/-->
        <form:input path="createName" id="createName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.operateName"/>
    </th>
    <td align="left">
        <!--form:errors path="operateName" cssClass="fieldError"/-->
        <form:input path="operateName" id="operateName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiBusinessNum.operateTime"/>
    </th>
    <td align="left">
        <!--form:errors path="operateTime" cssClass="fieldError"/-->
        <form:input path="operateTime" id="operateTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiBusinessNum')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiBusinessNumForm'));
</script>

<v:javascript formName="jfiBusinessNum" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
