<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductSaleLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmProductSaleLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jpmProductSaleLog.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jpmProductSaleLog" method="post" action="editJpmProductSaleLog.html" onsubmit="return validateJpmProductSaleLog(this)" id="jpmProductSaleLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="uniNo"/>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.productNo"/>
    </th>
    <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/-->
        <form:input path="productNo" id="productNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.productName"/>
    </th>
    <td align="left">
        <!--form:errors path="productName" cssClass="fieldError"/-->
        <form:input path="productName" id="productName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.fpPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="fpPrice" cssClass="fieldError"/-->
        <form:input path="fpPrice" id="fpPrice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.fpPv"/>
    </th>
    <td align="left">
        <!--form:errors path="fpPv" cssClass="fieldError"/-->
        <form:input path="fpPv" id="fpPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.mpPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="mpPrice" cssClass="fieldError"/-->
        <form:input path="mpPrice" id="mpPrice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.mpPv"/>
    </th>
    <td align="left">
        <!--form:errors path="mpPv" cssClass="fieldError"/-->
        <form:input path="mpPv" id="mpPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.storeFpPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="storeFpPrice" cssClass="fieldError"/-->
        <form:input path="storeFpPrice" id="storeFpPrice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.storeFpPv"/>
    </th>
    <td align="left">
        <!--form:errors path="storeFpPv" cssClass="fieldError"/-->
        <form:input path="storeFpPv" id="storeFpPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.storeMpPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="storeMpPrice" cssClass="fieldError"/-->
        <form:input path="storeMpPrice" id="storeMpPrice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.storeMpPv"/>
    </th>
    <td align="left">
        <!--form:errors path="storeMpPv" cssClass="fieldError"/-->
        <form:input path="storeMpPv" id="storeMpPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.whoPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="whoPrice" cssClass="fieldError"/-->
        <form:input path="whoPrice" id="whoPrice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.discountPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="discountPrice" cssClass="fieldError"/-->
        <form:input path="discountPrice" id="discountPrice" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.weight"/>
    </th>
    <td align="left">
        <!--form:errors path="weight" cssClass="fieldError"/-->
        <form:input path="weight" id="weight" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.volume"/>
    </th>
    <td align="left">
        <!--form:errors path="volume" cssClass="fieldError"/-->
        <form:input path="volume" id="volume" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.length"/>
    </th>
    <td align="left">
        <!--form:errors path="length" cssClass="fieldError"/-->
        <form:input path="length" id="length" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.width"/>
    </th>
    <td align="left">
        <!--form:errors path="width" cssClass="fieldError"/-->
        <form:input path="width" id="width" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.height"/>
    </th>
    <td align="left">
        <!--form:errors path="height" cssClass="fieldError"/-->
        <form:input path="height" id="height" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.imageLink"/>
    </th>
    <td align="left">
        <!--form:errors path="imageLink" cssClass="fieldError"/-->
        <form:input path="imageLink" id="imageLink" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.albumLink"/>
    </th>
    <td align="left">
        <!--form:errors path="albumLink" cssClass="fieldError"/-->
        <form:input path="albumLink" id="albumLink" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.confirm"/>
    </th>
    <td align="left">
        <!--form:errors path="confirm" cssClass="fieldError"/-->
        <form:input path="confirm" id="confirm" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.controlFirst"/>
    </th>
    <td align="left">
        <!--form:errors path="controlFirst" cssClass="fieldError"/-->
        <form:input path="controlFirst" id="controlFirst" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.controlUpdate"/>
    </th>
    <td align="left">
        <!--form:errors path="controlUpdate" cssClass="fieldError"/-->
        <form:input path="controlUpdate" id="controlUpdate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.controlRepurchase"/>
    </th>
    <td align="left">
        <!--form:errors path="controlRepurchase" cssClass="fieldError"/-->
        <form:input path="controlRepurchase" id="controlRepurchase" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.editUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="editUserCode" cssClass="fieldError"/-->
        <form:input path="editUserCode" id="editUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmProductSaleLog.editTime"/>
    </th>
    <td align="left">
        <!--form:errors path="editTime" cssClass="fieldError"/-->
        <form:input path="editTime" id="editTime" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmProductSaleLogForm'));
</script>

<v:javascript formName="jpmProductSaleLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
