<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVoteDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteVtVoteDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'VtVoteDetail')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="vtVoteDetail.*">
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

<form:form commandName="vtVoteDetail" method="post" action="editVtVoteDetail.html" onsubmit="return validateVtVoteDetail(this)" id="vtVoteDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('VtVoteDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="vdId"/>

    <tr><th>
        <jecs:label  key="vtVoteDetail.vtId"/>
    </th>
    <td align="left">
        <!--form:errors path="vtId" cssClass="fieldError"/-->
        <form:input path="vtId" id="vtId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="vtVoteDetail.content"/>
    </th>
    <td align="left">
        <!--form:errors path="content" cssClass="fieldError"/-->
        <form:input path="content" id="content" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="vtVoteDetail.orderNo"/>
    </th>
    <td align="left">
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <form:input path="orderNo" id="orderNo" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('VtVoteDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('vtVoteDetailForm'));
</script>

<v:javascript formName="vtVoteDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
