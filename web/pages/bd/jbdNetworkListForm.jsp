<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdNetworkListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdNetworkListDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdNetworkList">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdNetworkList')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdNetworkList.*">
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

<form:form commandName="jbdNetworkList" method="post" action="editJbdNetworkList.html" onsubmit="return validateJbdNetworkList(this)" id="jbdNetworkListForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdNetworkList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdNetworkList.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.startMonth"/>
    </th>
    <td align="left">
        <!--form:errors path="startMonth" cssClass="fieldError"/-->
        <form:input path="startMonth" id="startMonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.endMonth"/>
    </th>
    <td align="left">
        <!--form:errors path="endMonth" cssClass="fieldError"/-->
        <form:input path="endMonth" id="endMonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.networkMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="networkMoney" cssClass="fieldError"/-->
        <form:input path="networkMoney" id="networkMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.balanceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="balanceMoney" cssClass="fieldError"/-->
        <form:input path="balanceMoney" id="balanceMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.deductMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        <form:input path="deductMoney" id="deductMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdNetworkList.calcWeek"/>
    </th>
    <td align="left">
        <!--form:errors path="calcWeek" cssClass="fieldError"/-->
        <form:input path="calcWeek" id="calcWeek" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdNetworkList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdNetworkListForm'));
</script>

<v:javascript formName="jbdNetworkList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
