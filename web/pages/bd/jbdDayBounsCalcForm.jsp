<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdDayBounsCalcDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdDayBounsCalcDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdDayBounsCalc">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdDayBounsCalc')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdDayBounsCalc.*">
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

<form:form commandName="jbdDayBounsCalc" method="post" action="editJbdDayBounsCalc.html" onsubmit="return validateJbdDayBounsCalc(this)" id="jbdDayBounsCalcForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdDayBounsCalc')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.recommendNo"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNo" cssClass="fieldError"/-->
        <form:input path="recommendNo" id="recommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.linkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNo" cssClass="fieldError"/-->
        <form:input path="linkNo" id="linkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.name"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        <form:input path="name" id="name" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.petName"/>
    </th>
    <td align="left">
        <!--form:errors path="petName" cssClass="fieldError"/-->
        <form:input path="petName" id="petName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.isstore"/>
    </th>
    <td align="left">
        <!--form:errors path="isstore" cssClass="fieldError"/-->
        <form:input path="isstore" id="isstore" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.bank"/>
    </th>
    <td align="left">
        <!--form:errors path="bank" cssClass="fieldError"/-->
        <form:input path="bank" id="bank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.bankaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="bankaddress" cssClass="fieldError"/-->
        <form:input path="bankaddress" id="bankaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.bankbook"/>
    </th>
    <td align="left">
        <!--form:errors path="bankbook" cssClass="fieldError"/-->
        <form:input path="bankbook" id="bankbook" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.bankcard"/>
    </th>
    <td align="left">
        <!--form:errors path="bankcard" cssClass="fieldError"/-->
        <form:input path="bankcard" id="bankcard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.exitDate"/>
    </th>
    <td align="left">
        <!--form:errors path="exitDate" cssClass="fieldError"/-->
        <form:input path="exitDate" id="exitDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.firstMonth"/>
    </th>
    <td align="left">
        <!--form:errors path="firstMonth" cssClass="fieldError"/-->
        <form:input path="firstMonth" id="firstMonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.linkNum"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNum" cssClass="fieldError"/-->
        <form:input path="linkNum" id="linkNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingLinkNum"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingLinkNum" cssClass="fieldError"/-->
        <form:input path="pendingLinkNum" id="pendingLinkNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.recommendNum"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNum" cssClass="fieldError"/-->
        <form:input path="recommendNum" id="recommendNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingRecommendNum"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingRecommendNum" cssClass="fieldError"/-->
        <form:input path="pendingRecommendNum" id="pendingRecommendNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.monthConsumerPv"/>
    </th>
    <td align="left">
        <!--form:errors path="monthConsumerPv" cssClass="fieldError"/-->
        <form:input path="monthConsumerPv" id="monthConsumerPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingPv"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingPv" cssClass="fieldError"/-->
        <form:input path="pendingPv" id="pendingPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.monthAreaTotalPv"/>
    </th>
    <td align="left">
        <!--form:errors path="monthAreaTotalPv" cssClass="fieldError"/-->
        <form:input path="monthAreaTotalPv" id="monthAreaTotalPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.weekGroupPv"/>
    </th>
    <td align="left">
        <!--form:errors path="weekGroupPv" cssClass="fieldError"/-->
        <form:input path="weekGroupPv" id="weekGroupPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.monthGroupPv"/>
    </th>
    <td align="left">
        <!--form:errors path="monthGroupPv" cssClass="fieldError"/-->
        <form:input path="monthGroupPv" id="monthGroupPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingGroupPv"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingGroupPv" cssClass="fieldError"/-->
        <form:input path="pendingGroupPv" id="pendingGroupPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.monthRecommendPv"/>
    </th>
    <td align="left">
        <!--form:errors path="monthRecommendPv" cssClass="fieldError"/-->
        <form:input path="monthRecommendPv" id="monthRecommendPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingRecommendPv"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingRecommendPv" cssClass="fieldError"/-->
        <form:input path="pendingRecommendPv" id="pendingRecommendPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.passStar"/>
    </th>
    <td align="left">
        <!--form:errors path="passStar" cssClass="fieldError"/-->
        <form:input path="passStar" id="passStar" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.passGrade"/>
    </th>
    <td align="left">
        <!--form:errors path="passGrade" cssClass="fieldError"/-->
        <form:input path="passGrade" id="passGrade" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.consumerAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="consumerAmount" cssClass="fieldError"/-->
        <form:input path="consumerAmount" id="consumerAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingConsumerAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingConsumerAmount" cssClass="fieldError"/-->
        <form:input path="pendingConsumerAmount" id="pendingConsumerAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.franchisePv"/>
    </th>
    <td align="left">
        <!--form:errors path="franchisePv" cssClass="fieldError"/-->
        <form:input path="franchisePv" id="franchisePv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdDayBounsCalc.pendingFranchisePv"/>
    </th>
    <td align="left">
        <!--form:errors path="pendingFranchisePv" cssClass="fieldError"/-->
        <form:input path="pendingFranchisePv" id="pendingFranchisePv" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdDayBounsCalc')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdDayBounsCalcForm'));
</script>

<v:javascript formName="jbdDayBounsCalc" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
