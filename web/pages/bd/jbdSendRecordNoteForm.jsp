<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendRecordNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdSendRecordNoteDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdSendRecordNote">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdSendRecordNote')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>

<spring:bind path="jbdSendRecordNote.*">
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

<form:form commandName="jbdSendRecordNote" method="post" action="editJbdSendRecordNote.html" onsubmit="return validateJbdSendRecordNote(this)" id="jbdSendRecordNoteForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSendRecordNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="id"/>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.wyear"/>
    </th>
    <td align="left">
        <!--form:errors path="wyear" cssClass="fieldError"/-->
        <form:input path="wyear" id="wyear" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.wmonth"/>
    </th>
    <td align="left">
        <!--form:errors path="wmonth" cssClass="fieldError"/-->
        <form:input path="wmonth" id="wmonth" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.wweek"/>
    </th>
    <td align="left">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input path="wweek" id="wweek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.companyCode"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.userCode"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.recommendNo"/>
    </th>
    <td align="left">
        <!--form:errors path="recommendNo" cssClass="fieldError"/-->
        <form:input path="recommendNo" id="recommendNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.linkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="linkNo" cssClass="fieldError"/-->
        <form:input path="linkNo" id="linkNo" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.name"/>
    </th>
    <td align="left">
        <!--form:errors path="name" cssClass="fieldError"/-->
        <form:input path="name" id="name" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.petName"/>
    </th>
    <td align="left">
        <!--form:errors path="petName" cssClass="fieldError"/-->
        <form:input path="petName" id="petName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.cardType"/>
    </th>
    <td align="left">
        <!--form:errors path="cardType" cssClass="fieldError"/-->
        <form:input path="cardType" id="cardType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.bank"/>
    </th>
    <td align="left">
        <!--form:errors path="bank" cssClass="fieldError"/-->
        <form:input path="bank" id="bank" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.bankaddress"/>
    </th>
    <td align="left">
        <!--form:errors path="bankaddress" cssClass="fieldError"/-->
        <form:input path="bankaddress" id="bankaddress" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.bankbook"/>
    </th>
    <td align="left">
        <!--form:errors path="bankbook" cssClass="fieldError"/-->
        <form:input path="bankbook" id="bankbook" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.bankcard"/>
    </th>
    <td align="left">
        <!--form:errors path="bankcard" cssClass="fieldError"/-->
        <form:input path="bankcard" id="bankcard" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.exitDate"/>
    </th>
    <td align="left">
        <!--form:errors path="exitDate" cssClass="fieldError"/-->
        <form:input path="exitDate" id="exitDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendLateCause"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateCause" cssClass="fieldError"/-->
        <form:input path="sendLateCause" id="sendLateCause" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendLateRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendLateRemark" cssClass="fieldError"/-->
        <form:input path="sendLateRemark" id="sendLateRemark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.remittanceMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceMoney" cssClass="fieldError"/-->
        <form:input path="remittanceMoney" id="remittanceMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.remittanceBNum"/>
    </th>
    <td align="left">
        <!--form:errors path="remittanceBNum" cssClass="fieldError"/-->
        <form:input path="remittanceBNum" id="remittanceBNum" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.operaterCode"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterCode" cssClass="fieldError"/-->
        <form:input path="operaterCode" id="operaterCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.operaterTime"/>
    </th>
    <td align="left">
        <!--form:errors path="operaterTime" cssClass="fieldError"/-->
        <form:input path="operaterTime" id="operaterTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendDate"/>
    </th>
    <td align="left">
        <!--form:errors path="sendDate" cssClass="fieldError"/-->
        <form:input path="sendDate" id="sendDate" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.registerStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="registerStatus" cssClass="fieldError"/-->
        <form:input path="registerStatus" id="registerStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.reissueStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="reissueStatus" cssClass="fieldError"/-->
        <form:input path="reissueStatus" id="reissueStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.supplyTime"/>
    </th>
    <td align="left">
        <!--form:errors path="supplyTime" cssClass="fieldError"/-->
        <form:input path="supplyTime" id="supplyTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendTrace"/>
    </th>
    <td align="left">
        <!--form:errors path="sendTrace" cssClass="fieldError"/-->
        <form:input path="sendTrace" id="sendTrace" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendRemark"/>
    </th>
    <td align="left">
        <!--form:errors path="sendRemark" cssClass="fieldError"/-->
        <form:input path="sendRemark" id="sendRemark" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendMoney"/>
    </th>
    <td align="left">
        <!--form:errors path="sendMoney" cssClass="fieldError"/-->
        <form:input path="sendMoney" id="sendMoney" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.active"/>
    </th>
    <td align="left">
        <!--form:errors path="active" cssClass="fieldError"/-->
        <form:input path="active" id="active" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.status"/>
    </th>
    <td align="left">
        <!--form:errors path="status" cssClass="fieldError"/-->
        <form:input path="status" id="status" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.memberType"/>
    </th>
    <td align="left">
        <!--form:errors path="memberType" cssClass="fieldError"/-->
        <form:input path="memberType" id="memberType" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.startWeek"/>
    </th>
    <td align="left">
        <!--form:errors path="startWeek" cssClass="fieldError"/-->
        <form:input path="startWeek" id="startWeek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.validWeek"/>
    </th>
    <td align="left">
        <!--form:errors path="validWeek" cssClass="fieldError"/-->
        <form:input path="validWeek" id="validWeek" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.freezeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="freezeStatus" cssClass="fieldError"/-->
        <form:input path="freezeStatus" id="freezeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.beforeFreezeStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="beforeFreezeStatus" cssClass="fieldError"/-->
        <form:input path="beforeFreezeStatus" id="beforeFreezeStatus" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.totalDev"/>
    </th>
    <td align="left">
        <!--form:errors path="totalDev" cssClass="fieldError"/-->
        <form:input path="totalDev" id="totalDev" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.deductedDev"/>
    </th>
    <td align="left">
        <!--form:errors path="deductedDev" cssClass="fieldError"/-->
        <form:input path="deductedDev" id="deductedDev" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.currentDev"/>
    </th>
    <td align="left">
        <!--form:errors path="currentDev" cssClass="fieldError"/-->
        <form:input path="currentDev" id="currentDev" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.leaderDev"/>
    </th>
    <td align="left">
        <!--form:errors path="leaderDev" cssClass="fieldError"/-->
        <form:input path="leaderDev" id="leaderDev" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.leaderDevPv"/>
    </th>
    <td align="left">
        <!--form:errors path="leaderDevPv" cssClass="fieldError"/-->
        <form:input path="leaderDevPv" id="leaderDevPv" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendStatusDev"/>
    </th>
    <td align="left">
        <!--form:errors path="sendStatusDev" cssClass="fieldError"/-->
        <form:input path="sendStatusDev" id="sendStatusDev" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendDateDev"/>
    </th>
    <td align="left">
        <!--form:errors path="sendDateDev" cssClass="fieldError"/-->
        <form:input path="sendDateDev" id="sendDateDev" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jbdSendRecordNote.sendUserDev"/>
    </th>
    <td align="left">
        <!--form:errors path="sendUserDev" cssClass="fieldError"/-->
        <form:input path="sendUserDev" id="sendUserDev" cssClass="text medium"/>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdSendRecordNote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdSendRecordNoteForm'));
</script>

<v:javascript formName="jbdSendRecordNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
