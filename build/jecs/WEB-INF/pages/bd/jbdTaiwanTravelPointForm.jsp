<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTaiwanTravelPointDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdTaiwanTravelPointDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdTaiwanTravelPoint">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdTaiwanTravelPoint')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdTaiwanTravelPoint.*">
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

<form:form commandName="jbdTaiwanTravelPoint" method="post" action="editJbdTaiwanTravelPoint.html" onsubmit="return validateJbdTaiwanTravelPoint(this)" id="jbdTaiwanTravelPointForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdTaiwanTravelPoint')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.userName"/>
    </th>
    <td align="left">
        <!--form:errors path="userName" cssClass="fieldError"/-->
        <form:input path="userName" id="userName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.phone"/>
    </th>
    <td align="left">
        <!--form:errors path="phone" cssClass="fieldError"/-->
        <form:input path="phone" id="phone" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.passStar1"/>
    </th>
    <td align="left">
        <!--form:errors path="passStar1" cssClass="fieldError"/-->
        <form:input path="passStar1" id="passStar1" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.passStar2"/>
    </th>
    <td align="left">
        <!--form:errors path="passStar2" cssClass="fieldError"/-->
        <form:input path="passStar2" id="passStar2" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.passStar3"/>
    </th>
    <td align="left">
        <!--form:errors path="passStar3" cssClass="fieldError"/-->
        <form:input path="passStar3" id="passStar3" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.passStar4"/>
    </th>
    <td align="left">
        <!--form:errors path="passStar4" cssClass="fieldError"/-->
        <form:input path="passStar4" id="passStar4" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.recommendZuanshi"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendZuanshi" cssClass="fieldError"/-->
        <form:input path="recommendZuanshi" id="recommendZuanshi" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.recommendShenghuoguan"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendShenghuoguan" cssClass="fieldError"/-->
        <form:input path="recommendShenghuoguan" id="recommendShenghuoguan" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.shenghuoguanCheck"/>
    </th>
    <td align="left">
        <!--form:errors path="shenghuoguanCheck" cssClass="fieldError"/-->
        <form:input path="shenghuoguanCheck" id="shenghuoguanCheck" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdTaiwanTravelPoint.total"/>
    </th>
    <td align="left">
        <!--form:errors path="total" cssClass="fieldError"/-->
        <form:input path="total" id="total" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdTaiwanTravelPoint')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdTaiwanTravelPointForm'));
</script>

<v:javascript formName="jbdTaiwanTravelPoint" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
