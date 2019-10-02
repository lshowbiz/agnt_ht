<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdTravelPointDetailDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="jbdTravelPointDetail2014.*">
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

<form:form commandName="jbdTravelPointDetail2014" method="post" action="editJbdTravelPointDetail2014.html"  onsubmit="if(isFormPosted()){return true;}{return false;}" id="jbdTravelPointDetailForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="id"/>

	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	
	    <tr><th>
	        <jecs:label  key="miMember.memberNo"/>
	    </th>
	    <td align="left">
	        <form:input path="userCode" id="userCode" cssClass="text medium"/>
	    </td></tr>
    
	</c:if>

    <tr><th>
        <jecs:label  key="bdBonusStatReport.item"/>
    </th>
    <td align="left">
	  <jecs:list name="travelType" id="travelType" listCode="bd.traveltype.2014" value="${jbdTravelPointDetail2014.travelType}" defaultValue="" showBlankLine="true"/>
        
    </td></tr>

    

</table>
</form:form>
