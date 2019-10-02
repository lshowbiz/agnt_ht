<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmWineSettingListInfDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmWineSettingListInfDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmWineSettingListInf">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmWineSettingListInf')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmWineSettingListInf.*">
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

<form:form commandName="jpmWineSettingListInf" method="post" action="editJpmWineSettingListInf.html" onsubmit="return validateJpmWineSettingListInf(this)" id="jpmWineSettingListInfForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmWineSettingListInf')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="idf"/>

    <tr><th>
        <jecs:label  key="jpmWineSettingListInf.materialNo"/>
    </th>
    <td align="left">
        <!--form:errors path="materialNo" cssClass="fieldError"/-->
        <form:input path="materialNo" id="materialNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingListInf.qty"/>
    </th>
    <td align="left">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <form:input path="qty" id="qty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingListInf.sdate"/>
    </th>
    <td align="left">
        <!--form:errors path="sdate" cssClass="fieldError"/-->
        <form:input path="sdate" id="sdate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingListInf.edate"/>
    </th>
    <td align="left">
        <!--form:errors path="edate" cssClass="fieldError"/-->
        <form:input path="edate" id="edate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingListInf.memo"/>
    </th>
    <td align="left">
        <!--form:errors path="memo" cssClass="fieldError"/-->
        <form:input path="memo" id="memo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineSettingListInf.settingId"/>
    </th>
    <td align="left">
        <!--form:errors path="settingId" cssClass="fieldError"/-->
        <form:input path="settingId" id="settingId" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmWineSettingListInf')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmWineSettingListInfForm'));
</script>

<v:javascript formName="jpmWineSettingListInf" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
