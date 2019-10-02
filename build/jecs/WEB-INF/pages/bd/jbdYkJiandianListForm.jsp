<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdYkJiandianListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdYkJiandianListDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdYkJiandianList">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdYkJiandianList')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdYkJiandianList.*">
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

<form:form commandName="jbdYkJiandianList" method="post" action="editJbdYkJiandianList.html" onsubmit="return validateJbdYkJiandianList(this)" id="jbdYkJiandianListForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdYkJiandianList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.petName"/>
    </th>
    <td align="left">
        <!--form:errors path="petName" cssClass="fieldError"/-->
        <form:input path="petName" id="petName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.userType"/>
    </th>
    <td align="left">
        <!--form:errors path="userType" cssClass="fieldError"/-->
        <form:input path="userType" id="userType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.memberLevel"/>
    </th>
    <td align="left">
        <!--form:errors path="memberLevel" cssClass="fieldError"/-->
        <form:input path="memberLevel" id="memberLevel" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.ykRefMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="ykRefMoney" cssClass="fieldError"/-->
        <form:input path="ykRefMoney" id="ykRefMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.comTime"/>
    </th>
    <td align="left">
        <!--form:errors path="comTime" cssClass="fieldError"/-->
        <form:input path="comTime" id="comTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.reuserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="reuserCode" cssClass="fieldError"/-->
        <form:input path="reuserCode" id="reuserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdYkJiandianList.nlevel"/>
    </th>
    <td align="left">
        <!--form:errors path="nlevel" cssClass="fieldError"/-->
        <form:input path="nlevel" id="nlevel" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdYkJiandianList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdYkJiandianListForm'));
</script>

<v:javascript formName="jbdYkJiandianList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
