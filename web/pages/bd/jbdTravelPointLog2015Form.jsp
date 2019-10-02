<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdTravelPointLogDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="jbdTravelPointLog2015.*">
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

<form:form commandName="jbdTravelPointLog2015" method="post" action="editJbdTravelPointLog2015.html" onsubmit="if(isFormPosted()){return true;}{return false;}" id="jbdTravelPointLogForm">

<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="logId"/>

    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBankbookTemp.dealType"/>
    </th>
    <td align="left">
        <jecs:list listCode="fibankbooktemp.dealtype" name="dealType" id="dealType" value="${jbdTravelPointLog2015.dealType}" defaultValue="A"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiCoinLog.coin"/>
    </th>
    <td align="left">
        <form:input path="usePoint" id="usePoint" cssClass="text medium"/>
    </td></tr>


    <tr><th>
        <jecs:label  key="customerRecord.remark"/>
    </th>
    <td align="left">
        <form:textarea path="remark" id="remark" rows="6" cols="50" htmlEscape="true"/>
    </td></tr>

</table>
</form:form>
