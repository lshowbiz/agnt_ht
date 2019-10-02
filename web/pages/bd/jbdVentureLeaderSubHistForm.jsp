<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdVentureLeaderSubHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdVentureLeaderSubHistDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdVentureLeaderSubHist">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdVentureLeaderSubHist')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdVentureLeaderSubHist.*">
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

<form:form commandName="jbdVentureLeaderSubHist" method="post" action="editJbdVentureLeaderSubHist.html" onsubmit="return validateJbdVentureLeaderSubHist(this)" id="jbdVentureLeaderSubHistForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdVentureLeaderSubHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.bounsType"/>
    </th>
    <td align="left">
        <!--form:errors path="bounsType" cssClass="fieldError"/-->
        <form:input path="bounsType" id="bounsType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.nlevel"/>
    </th>
    <td align="left">
        <!--form:errors path="nlevel" cssClass="fieldError"/-->
        <form:input path="nlevel" id="nlevel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.recommendedCode"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendedCode" cssClass="fieldError"/-->
        <form:input path="recommendedCode" id="recommendedCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.passGroupPv"/>
    </th>
    <td align="left">
        <!--form:errors path="passGroupPv" cssClass="fieldError"/-->
        <form:input path="passGroupPv" id="passGroupPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.bounsPoint"/>
    </th>
    <td align="left">
        <!--form:errors path="bounsPoint" cssClass="fieldError"/-->
        <form:input path="bounsPoint" id="bounsPoint" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdVentureLeaderSubHist.bounsMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="bounsMoney" cssClass="fieldError"/-->
        <form:input path="bounsMoney" id="bounsMoney" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdVentureLeaderSubHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdVentureLeaderSubHistForm'));
</script>

<v:javascript formName="jbdVentureLeaderSubHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
