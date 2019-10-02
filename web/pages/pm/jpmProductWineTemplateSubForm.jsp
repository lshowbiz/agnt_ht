<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductWineTemplateSubDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductWineTemplateSubDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmProductWineTemplateSub">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmProductWineTemplateSub')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmProductWineTemplateSub.*">
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

<form:form commandName="jpmProductWineTemplateSub" method="post" action="editJpmProductWineTemplateSub.html" onsubmit="return validateJpmProductWineTemplateSub(this)" id="jpmProductWineTemplateSubForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductWineTemplateSub')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="subNo"/>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.productTemplateNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productTemplateNo" cssClass="fieldError"/-->
        <form:input path="productTemplateNo" id="productTemplateNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.subName"/>
    </th>
    <td align="left">
        <!--form:errors path="subName" cssClass="fieldError"/-->
        <form:input path="subName" id="subName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.price"/>
    </th>
    <td align="left">
        <!--form:errors path="price" cssClass="fieldError"/-->
        <form:input path="price" id="price" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.specification"/>
    </th>
    <td align="left">
        <!--form:errors path="specification" cssClass="fieldError"/-->
        <form:input path="specification" id="specification" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.num"/>
    </th>
    <td align="left">
        <!--form:errors path="num" cssClass="fieldError"/-->
        <form:input path="num" id="num" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.unit"/>
    </th>
    <td align="left">
        <!--form:errors path="unit" cssClass="fieldError"/-->
        <form:input path="unit" id="unit" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.lossRatio"/>
    </th>
    <td align="left">
        <!--form:errors path="lossRatio" cssClass="fieldError"/-->
        <form:input path="lossRatio" id="lossRatio" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isMainMaterial"/>
    </th>
    <td align="left">
        <!--form:errors path="isMainMaterial" cssClass="fieldError"/-->
        <form:input path="isMainMaterial" id="isMainMaterial" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isSendMaterial"/>
    </th>
    <td align="left">
        <!--form:errors path="isSendMaterial" cssClass="fieldError"/-->
        <form:input path="isSendMaterial" id="isSendMaterial" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isDelegateOut"/>
    </th>
    <td align="left">
        <!--form:errors path="isDelegateOut" cssClass="fieldError"/-->
        <form:input path="isDelegateOut" id="isDelegateOut" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isFeatureItem"/>
    </th>
    <td align="left">
        <!--form:errors path="isFeatureItem" cssClass="fieldError"/-->
        <form:input path="isFeatureItem" id="isFeatureItem" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isMustSelected"/>
    </th>
    <td align="left">
        <!--form:errors path="isMustSelected" cssClass="fieldError"/-->
        <form:input path="isMustSelected" id="isMustSelected" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isDefaultSelected"/>
    </th>
    <td align="left">
        <!--form:errors path="isDefaultSelected" cssClass="fieldError"/-->
        <form:input path="isDefaultSelected" id="isDefaultSelected" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.isNumChange"/>
    </th>
    <td align="left">
        <!--form:errors path="isNumChange" cssClass="fieldError"/-->
        <form:input path="isNumChange" id="isNumChange" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.numMax"/>
    </th>
    <td align="left">
        <!--form:errors path="numMax" cssClass="fieldError"/-->
        <form:input path="numMax" id="numMax" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductWineTemplateSub.numMin"/>
    </th>
    <td align="left">
        <!--form:errors path="numMin" cssClass="fieldError"/-->
        <form:input path="numMin" id="numMin" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductWineTemplateSub')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmProductWineTemplateSubForm'));
</script>

<v:javascript formName="jpmProductWineTemplateSub" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
