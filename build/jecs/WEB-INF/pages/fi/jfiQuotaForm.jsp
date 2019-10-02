<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiQuotaDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiQuotaDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiQuota">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiQuota')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiQuota.*">
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

<form:form commandName="jfiQuota" method="post" action="editJfiQuota.html" onsubmit="return validateJfiQuota(this)" id="jfiQuotaForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiQuota')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="quotaId"/>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="validityPeriod" cssClass="fieldError"/-->
        <form:input path="validityPeriod" id="validityPeriod" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBillAccount.AccountCode"/>
    </th>
    <td align="left">
        <!--form:errors path="accountId" cssClass="fieldError"/-->
        <form:input path="accountId" id="accountId" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="foundationOrder.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <jecs:list listCode="jmimemberteam.status" name="status" id="status"
			showBlankLine="false" value="${jfiQuota.status}"
			defaultValue="0" />
    </td></tr>

    <tr><th>
        <jecs:label  key="jfiquota.maxmoney"/>
    </th>
    <td align="left">
        <!--form:errors path="maxMoney" cssClass="fieldError"/-->
        <form:input path="maxMoney" id="maxMoney" cssClass="text medium"/>
        <input type="hidden" name="oldMaxMoney" id="oldMaxMoney" value="${jfiQuota.maxMoney}" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" maxlength="12"/>
    </td></tr>


    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiQuota')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiQuotaForm'));
</script>

<v:javascript formName="jfiQuota" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
