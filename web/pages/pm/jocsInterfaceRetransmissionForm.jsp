<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jocsInterfaceRetransmissionDetail.title"/></title>
<content tag="heading"><jecs:locale key="jocsInterfaceRetransmissionDetail.heading"/></content>
<script type='text/javascript'
	src='<c:url value="/dwr/interface/pdWarehouseStockManager.js"/>'></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/alCityManager.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<input type="button" class="button" name="back" onclick="javascript:	history.back();"
		value="<jecs:locale  key="operation.button.return"/>" />
</c:set>

<spring:bind path="jocsInterfaceRetransmission.*">
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

<form:form commandName="jocsInterfaceRetransmission" method="post" action="editJocsInterfaceRetransmission.html?strAction=${param.strAction }" onsubmit="return validateJocsInterfaceRetransmission(this)" id="jocsInterfaceRetransmissionForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JocsInterfaceRetransmission')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.logType"/>
    </th>
    <td align="left">
        <!--form:errors path="logType" cssClass="fieldError"/-->
        <jecs:list listCode="jocsinterfacelog.logtype" name="logtype" id="logtype"
				value="${jocsInterfaceRetransmission.logType}" defaultValue="" disabled="disabled"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.flag"/>
    </th>
    <td align="left">
        <!--form:errors path="flag" cssClass="fieldError"/-->
        <jecs:list listCode="jocsinterfacelog.flag" name="flag" id="flag"
				value="${jocsInterfaceRetransmission.flag}" defaultValue="" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.method"/>
    </th>
    <td align="left">
        <!--form:errors path="method" cssClass="fieldError"/-->
        <form:input path="method" id="method" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.type"/>
    </th>
    <td align="left">
        <!--form:errors path="type" cssClass="fieldError"/-->
        <form:input path="type" id="type" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.charset"/>
    </th>
    <td align="left">
        <!--form:errors path="charset" cssClass="fieldError"/-->
        <form:input path="charset" id="charset" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.ver"/>
    </th>
    <td align="left">
        <!--form:errors path="ver" cssClass="fieldError"/-->
        <form:input path="ver" id="ver" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.content"/>
    </th>
    <td align="left">
        <!--form:errors path="content" cssClass="fieldError"/-->
        <form:textarea cols='88' rows='16' path="content" id="content"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.sign"/>
    </th>
    <td align="left">
        <!--form:errors path="sign" cssClass="fieldError"/-->
        <form:input path="sign" id="sign" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.returnResult"/>
    </th>
    <td align="left">
        <!--form:errors path="returnResult" cssClass="fieldError"/-->
        <form:input path="returnResult" id="returnResult" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.returnDesc"/>
    </th>
    <td align="left">
        <!--form:errors path="returnDesc" cssClass="fieldError"/-->
        <form:input path="returnDesc" id="returnDesc" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.sendTime"/>
    </th>
    <td align="left">
        <!--form:errors path="sendTime" cssClass="fieldError"/-->
        <form:input path="sendTime" id="sendTime" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.reciverTime"/>
    </th>
    <td align="left">
        <!--form:errors path="reciverTime" cssClass="fieldError"/-->
        <form:input path="reciverTime" id="reciverTime" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.editTime"/>
    </th>
    <td align="left">
        <!--form:errors path="editTime" cssClass="fieldError"/-->
        <form:input path="editTime" id="editTime" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <form:input path="remark" id="remark" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.retranType"/>
    </th>
    <td align="left">
        <!--form:errors path="retranType" cssClass="fieldError"/-->
        <jecs:list listCode="jocsinterfaceretransmission.returntype" name="retranType" id="retranType"
				value="${jocsInterfaceRetransmission.retranType}" defaultValue="" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.retranStatus"/>
    </th>
    <td align="left">
        <!--form:errors path="retranStatus" cssClass="fieldError"/-->
        <jecs:list listCode="jocsinterfaceretransmission.returnstatus" name="retranStatus" id="retranStatus"
				value="${jocsInterfaceRetransmission.retranStatus}" defaultValue=""/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.retranTime"/>
    </th>
    <td align="left">
        <!--form:errors path="retranTime" cssClass="fieldError"/-->
        <form:input path="retranTime" id="retranTime" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.retranCode"/>
    </th>
    <td align="left">
        <!--form:errors path="retranCode" cssClass="fieldError"/-->
        <form:input path="retranCode" id="retranCode" cssClass="text medium" disabled="true"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jocsInterfaceLog.retranReason"/>
    </th>
    <td align="left">
        <!--form:errors path="retranReason" cssClass="fieldError"/-->
        <form:input path="retranReason" id="retranReason" cssClass="text medium" disabled="true"/>
    </td></tr>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JocsInterfaceRetransmission')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jocsInterfaceRetransmissionForm'));

    function updateHurryFlag(siNo){
		var str = pdWarehouseStockManager.updateHurryFlag(siNo,validatePdHurry);
	}
</script>

<v:javascript formName="jocsInterfaceRetransmission" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
