<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSummaryListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSummaryListDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSummaryList">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSummaryList')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSummaryList.*">
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

<form:form commandName="jbdSummaryList" method="post" action="editJbdSummaryList.html" onsubmit="return validateJbdSummaryList(this)" id="jbdSummaryListForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSummaryList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdSummaryList.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.inType"/>
    </th>
    <td align="left">
        <!--form:errors path="inType" cssClass="fieldError"/-->
        <form:input path="inType" id="inType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.orderType"/>
    </th>
    <td align="left">
        <!--form:errors path="orderType" cssClass="fieldError"/-->
        <form:input path="orderType" id="orderType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.oldCheckDate"/>
    </th>
    <td align="left">
        <!--form:errors path="oldCheckDate" cssClass="fieldError"/-->
        <form:input path="oldCheckDate" id="oldCheckDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.newCheckDate"/>
    </th>
    <td align="left">
        <!--form:errors path="newCheckDate" cssClass="fieldError"/-->
        <form:input path="newCheckDate" id="newCheckDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.pvAmt"/>
    </th>
    <td align="left">
        <!--form:errors path="pvAmt" cssClass="fieldError"/-->
        <form:input path="pvAmt" id="pvAmt" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.oldLinkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="oldLinkNo" cssClass="fieldError"/-->
        <form:input path="oldLinkNo" id="oldLinkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.newLinkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="newLinkNo" cssClass="fieldError"/-->
        <form:input path="newLinkNo" id="newLinkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.oldRecommendNo"/>
    </th>
    <td align="left">
        <!--form:errors path="oldRecommendNo" cssClass="fieldError"/-->
        <form:input path="oldRecommendNo" id="oldRecommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.newRecommendNo"/>
    </th>
    <td align="left">
        <!--form:errors path="newRecommendNo" cssClass="fieldError"/-->
        <form:input path="newRecommendNo" id="newRecommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.newCompanyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="newCompanyCode" cssClass="fieldError"/-->
        <form:input path="newCompanyCode" id="newCompanyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.userCreateTime"/>
    </th>
    <td align="left">
        <!--form:errors path="userCreateTime" cssClass="fieldError"/-->
        <form:input path="userCreateTime" id="userCreateTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSummaryList.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSummaryList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSummaryListForm'));
</script>

<v:javascript formName="jbdSummaryList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
