<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoAutoShipDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoAutoShipDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpoAutoShip">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpoAutoShip')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
		
<c:if test='${empty jpoAutoShip.jasId}'>
<jecs:power powerCode="addPoAutoShip">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
</jecs:power>
</c:if>
<c:if test='${jpoAutoShip.status=="1" && not empty jpoAutoShip.jasId}'>
<jecs:power powerCode="editPoAutoShip">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
				
</jecs:power>
<jecs:power powerCode="deletePoAutoShip">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PoAutoShip')" value="<jecs:locale key="operation.button.delete"/>" />
</jecs:power>
</c:if>

<input type="button" class="button" name="back"  onclick="window.location.href='jpoAutoShips.html?needReload=1'" value="<jecs:locale key="operation.button.return"/>" />

		
</c:set>

<spring:bind path="jpoAutoShip.*">
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

<form:form commandName="jpoAutoShip" method="post" action="editJpoAutoShip.html" onsubmit="return validateJpoAutoShip(this)" id="jpoAutoShipForm">

<div id="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="jasId"/>

    <tr><th>
        <jecs:label  key="jpoAutoShip.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.jasActionTime"/>
    </th>
    <td align="left">
        <!--form:errors path="jasActionTime" cssClass="fieldError"/-->
        <form:input path="jasActionTime" id="jasActionTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.moId"/>
    </th>
    <td align="left">
        <!--form:errors path="moId" cssClass="fieldError"/-->
        <form:input path="moId" id="moId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.createUser"/>
    </th>
    <td align="left">
        <!--form:errors path="createUser" cssClass="fieldError"/-->
        <form:input path="createUser" id="createUser" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.checkTime"/>
    </th>
    <td align="left">
        <!--form:errors path="checkTime" cssClass="fieldError"/-->
        <form:input path="checkTime" id="checkTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.firstName"/>
    </th>
    <td align="left">
        <!--form:errors path="firstName" cssClass="fieldError"/-->
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.lastName"/>
    </th>
    <td align="left">
        <!--form:errors path="lastName" cssClass="fieldError"/-->
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.cardCvv"/>
    </th>
    <td align="left">
        <!--form:errors path="cardCvv" cssClass="fieldError"/-->
        <form:input path="cardCvv" id="cardCvv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.cardNumber"/>
    </th>
    <td align="left">
        <!--form:errors path="cardNumber" cssClass="fieldError"/-->
        <form:input path="cardNumber" id="cardNumber" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.expireDate"/>
    </th>
    <td align="left">
        <!--form:errors path="expireDate" cssClass="fieldError"/-->
        <form:input path="expireDate" id="expireDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.payCause"/>
    </th>
    <td align="left">
        <!--form:errors path="payCause" cssClass="fieldError"/-->
        <form:input path="payCause" id="payCause" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpoAutoShip.returnData"/>
    </th>
    <td align="left">
        <!--form:errors path="returnData" cssClass="fieldError"/-->
        <form:input path="returnData" id="returnData" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoAutoShip')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpoAutoShipForm'));
</script>

<v:javascript formName="jpoAutoShip" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
