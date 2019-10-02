<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiLinkRefDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiLinkRefDetail.heading"/></content>

<c:set var="buttons">

		<wecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.save"/>" />
		</wecs:power>
		<wecs:power powerCode="deleteJmiLinkRef">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiLinkRef<jecs:localefmt:message key="button.delete"/>" />
		</wecs:power>
</c:set>

<spring:bind path="jmiLinkRef.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
 <jecs:locale   alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jmiLinkRef" method="post" action="editJmiLinkRef.html" onsubmit="return validateJmiLinkRef(this)" id="jmiLinkRefForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return<jecs:localete('JmiLinkRef')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="memberNo"/>

    <tr><th>
        <wecs:label  key="jmiLinkRef.linkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNo" cssClass="fieldError"/-->
        <form:input path="linkNo" id="linkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiLinkRef.layerId"/>
    </th>
    <td align="left">
        <!--form:errors path="layerId" cssClass="fieldError"/-->
        <form:input path="layerId" id="layerId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiLinkRef.treeNo"/>
    </th>
    <td align="left">
        <!--form:errors path="treeNo" cssClass="fieldError"/-->
        <form:input path="treeNo" id="treeNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <wecs:label  key="jmiLinkRef.treeIndex"/>
    </th>
    <td align="left">
        <!--form:errors path="treeIndex" cssClass="fieldError"/-->
        <form:input path="treeIndex" id="treeIndex" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
     <jecs:localepe="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="but<jecs:localeelete" onclick="bCancel=true;return confirmDelete('JmiLinkRef')" value="<fmt:message key="button.delete"/>" />
   <jecs:localetype="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiLinkRefForm'));
</script>

<v:javascript formName="jmiLinkRef" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
