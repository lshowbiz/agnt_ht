<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSellCalcSubDetailHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSellCalcSubDetailHistDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSellCalcSubDetailHist">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSellCalcSubDetailHist')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSellCalcSubDetailHist.*">
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

<form:form commandName="jbdSellCalcSubDetailHist" method="post" action="editJbdSellCalcSubDetailHist.html" onsubmit="return validateJbdSellCalcSubDetailHist(this)" id="jbdSellCalcSubDetailHistForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSellCalcSubDetailHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.nlevel"/>
    </th>
    <td align="left">
        <!--form:errors path="nlevel" cssClass="fieldError"/-->
        <form:input path="nlevel" id="nlevel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.recommendedCode"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendedCode" cssClass="fieldError"/-->
        <form:input path="recommendedCode" id="recommendedCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.recommendedCompanyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendedCompanyCode" cssClass="fieldError"/-->
        <form:input path="recommendedCompanyCode" id="recommendedCompanyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.recommendedOrderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendedOrderNo" cssClass="fieldError"/-->
        <form:input path="recommendedOrderNo" id="recommendedOrderNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.recommendedOrderClass"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendedOrderClass" cssClass="fieldError"/-->
        <form:input path="recommendedOrderClass" id="recommendedOrderClass" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.recommendedOrderType"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendedOrderType" cssClass="fieldError"/-->
        <form:input path="recommendedOrderType" id="recommendedOrderType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSellCalcSubDetailHist.pv"/>
    </th>
    <td align="left">
        <!--form:errors path="pv" cssClass="fieldError"/-->
        <form:input path="pv" id="pv" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSellCalcSubDetailHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSellCalcSubDetailHistForm'));
</script>

<v:javascript formName="jbdSellCalcSubDetailHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
