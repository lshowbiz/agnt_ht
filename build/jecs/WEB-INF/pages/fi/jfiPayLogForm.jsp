<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiPayLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJfiPayLog">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JfiPayLog')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jfiPayLog.*">
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

<form:form commandName="jfiPayLog" method="post" action="editJfiPayLog.html" onsubmit="return validateJfiPayLog(this)" id="jfiPayLogForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiPayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

<form:hidden path="companyCode"/>

<form:hidden path="payType"/>

<form:hidden path="dealId"/>

<form:hidden path="flag"/>

<form:hidden path="userCode"/>

<form:hidden path="merchantId"/>

<form:hidden path="version"/>

<form:hidden path="signType"/>

<form:hidden path="sign"/>

<form:hidden path="merkey"/>

<form:hidden path="bankId"/>

<form:hidden path="bankDealId"/>

<form:hidden path="amtType"/>

<form:hidden path="orderId"/>

<form:hidden path="orderDate"/>

<form:hidden path="orderAmount"/>

<form:hidden path="orderTime"/>

<form:hidden path="expireTime"/>

<form:hidden path="payResult"/>

<form:hidden path="errCode"/>

<form:hidden path="errMsg"/>

<form:hidden path="remark"/>

<form:hidden path="returnMsg"/>

<form:hidden path="createTime"/>

<form:hidden path="url"/>

<form:hidden path="urlRemark"/>

<form:hidden path="inc"/>

<form:hidden path="ip"/>

<form:hidden path="dataType"/>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JfiPayLog')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiPayLogForm'));
</script>

<v:javascript formName="jfiPayLog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
