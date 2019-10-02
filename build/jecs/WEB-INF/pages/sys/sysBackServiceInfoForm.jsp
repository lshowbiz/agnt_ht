<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysBackServiceInfoDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysBackServiceInfoDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysBackServiceInfo">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysBackServiceInfo')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysBackServiceInfo.*">
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

<form:form commandName="sysBackServiceInfo" method="post" action="editSysBackServiceInfo.html" onsubmit="return validateSysBackServiceInfo(this)" id="sysBackServiceInfoForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysBackServiceInfo')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="bsiId"/>

    <tr><th>
        <jecs:label  key="sysBackServiceInfo.exeFlag"/>
    </th>
    <td align="left">
        <!--form:errors path="exeFlag" cssClass="fieldError"/-->
        <form:input path="exeFlag" id="exeFlag" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysBackServiceInfo.info"/>
    </th>
    <td align="left">
        <!--form:errors path="info" cssClass="fieldError"/-->
        <form:input path="info" id="info" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysBackServiceInfo.bsiType"/>
    </th>
    <td align="left">
        <!--form:errors path="bsiType" cssClass="fieldError"/-->
        <form:input path="bsiType" id="bsiType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysBackServiceInfo.command"/>
    </th>
    <td align="left">
        <!--form:errors path="command" cssClass="fieldError"/-->
        <form:input path="command" id="command" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SysBackServiceInfo')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysBackServiceInfoForm'));
</script>

<v:javascript formName="sysBackServiceInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
