<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmConfigSpecDetailedDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmConfigSpecDetailedDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmConfigSpecDetailed">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmConfigSpecDetailed')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmConfigSpecDetailed.*">
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

<form:form commandName="jpmConfigSpecDetailed" method="post" action="editJpmConfigSpecDetailed.html" onsubmit="return validateJpmConfigSpecDetailed(this)" id="jpmConfigSpecDetailedForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmConfigSpecDetailed')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="specNo"/>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.configNo"/>
    </th>
    <td align="left">
        <!--form:errors path="configNo" cssClass="fieldError"/-->
        <form:input path="configNo" id="configNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.productTemplateNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productTemplateNo" cssClass="fieldError"/-->
        <form:input path="productTemplateNo" id="productTemplateNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.productTemplateName"/>
    </th>
    <td align="left">
        <!--form:errors path="productTemplateName" cssClass="fieldError"/-->
        <form:input path="productTemplateName" id="productTemplateName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.complateAmount"/>
    </th>
    <td align="left">
        <!--form:errors path="complateAmount" cssClass="fieldError"/-->
        <form:input path="complateAmount" id="complateAmount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.complateWeight"/>
    </th>
    <td align="left">
        <!--form:errors path="complateWeight" cssClass="fieldError"/-->
        <form:input path="complateWeight" id="complateWeight" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmConfigSpecDetailed.productNum"/>
    </th>
    <td align="left">
        <!--form:errors path="productNum" cssClass="fieldError"/-->
        <form:input path="productNum" id="productNum" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmConfigSpecDetailed')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmConfigSpecDetailedForm'));
</script>

<v:javascript formName="jpmConfigSpecDetailed" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
