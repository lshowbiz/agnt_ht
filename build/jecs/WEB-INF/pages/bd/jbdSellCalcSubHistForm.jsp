<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSellCalcSubHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSellCalcSubHistDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSellCalcSubHist">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSellCalcSubHist')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSellCalcSubHist.*">
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

<form:form commandName="jbdSellCalcSubHist" method="post" action="editJbdSellCalcSubHist.html" onsubmit="return validateJbdSellCalcSubHist(this)" id="jbdSellCalcSubHistForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSellCalcSubHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.linkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNo" cssClass="fieldError"/-->
        <form:input path="linkNo" id="linkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.groupPv"/>
    </th>
    <td align="left">
        <!--form:errors path="groupPv" cssClass="fieldError"/-->
        <form:input path="groupPv" id="groupPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.bounsPoint"/>
    </th>
    <td align="left">
        <!--form:errors path="bounsPoint" cssClass="fieldError"/-->
        <form:input path="bounsPoint" id="bounsPoint" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.bounsPv"/>
    </th>
    <td align="left">
        <!--form:errors path="bounsPv" cssClass="fieldError"/-->
        <form:input path="bounsPv" id="bounsPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.keepPv"/>
    </th>
    <td align="left">
        <!--form:errors path="keepPv" cssClass="fieldError"/-->
        <form:input path="keepPv" id="keepPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.lastKeepPv"/>
    </th>
    <td align="left">
        <!--form:errors path="lastKeepPv" cssClass="fieldError"/-->
        <form:input path="lastKeepPv" id="lastKeepPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubHist.serialNumber"/>
    </th>
    <td align="left">
        <!--form:errors path="serialNumber" cssClass="fieldError"/-->
        <form:input path="serialNumber" id="serialNumber" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSellCalcSubHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSellCalcSubHistForm'));
</script>

<v:javascript formName="jbdSellCalcSubHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
