<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdLogisticsBaseDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdLogisticsBaseDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deletePdLogisticsBaseDetail">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdLogisticsBaseDetail')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="pdLogisticsBaseDetail.*">
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

<form:form commandName="pdLogisticsBaseDetail" method="post" action="editPdLogisticsBaseDetail.html" onsubmit="return validatePdLogisticsBaseDetail(this)" id="pdLogisticsBaseDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdLogisticsBaseDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="detailId"/>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.numId"/>
    </th>
    <td align="left">
        <!--form:errors path="numId" cssClass="fieldError"/-->
        <form:input path="numId" id="numId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.erpGoodsBn"/>
    </th>
    <td align="left">
        <!--form:errors path="erpGoodsBn" cssClass="fieldError"/-->
        <form:input path="erpGoodsBn" id="erpGoodsBn" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.qty"/>
    </th>
    <td align="left">
        <!--form:errors path="qty" cssClass="fieldError"/-->
        <form:input path="qty" id="qty" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.otherOne"/>
    </th>
    <td align="left">
        <!--form:errors path="otherOne" cssClass="fieldError"/-->
        <form:input path="otherOne" id="otherOne" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="pdLogisticsBaseDetail.otherTwo"/>
    </th>
    <td align="left">
        <!--form:errors path="otherTwo" cssClass="fieldError"/-->
        <form:input path="otherTwo" id="otherTwo" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdLogisticsBaseDetail')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdLogisticsBaseDetailForm'));
</script>

<v:javascript formName="pdLogisticsBaseDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
