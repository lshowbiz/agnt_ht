<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysManagerModlPowDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysManagerModlPowDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="operation.button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteSysManagerModlPow">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysManagerModlPow<jecs:localefmt:message key="operation.button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="sysManagerModlPow.*">
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

<form:form commandName="sysManagerModlPow" method="post" action="editSysManagerModlPow.html" onsubmit="return validateSysManagerModlPow(this)" id="sysManagerModlPowForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="sa<jecs:locale="bCancel=false" value="<fmt:message key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confir<jecs:localeManagerModlPow')" value="<fmt:message key="operation.button.delete"/>" />
        <input type="submit" class="button" name="<jecs:localeick="bCancel=true" value="<fmt:message key="operation.button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="rollId"/>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="b<jecs:locale"save"  onclick="bCancel=false" value="<fmt:message key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=tru<jecs:localefirmDelete('SysManagerModlPow')" value="<fmt:message key="operation.button.delete"/>" />
        <input type="submit" class=<jecs:localee="cancel" onclick="bCancel=true" value="<fmt:message key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysManagerModlPowForm'));
</script>

<v:javascript formName="sysManagerModlPow" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
