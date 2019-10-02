<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUser.title"/></title>
<content tag="heading"><jecs:locale key="sysUser.heading"/></content>

<c:set var="buttons">
				<input type="submit" class="button" name="next"  value="<jecs:locale key="button.next"/>" />
</c:set>

<spring:bind path="sysUser.*">
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

<form:form commandName="sysUser" method="post" action="editJfi99billPosLog.html" id="sysUserForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="addJfi99billPosLog"/>

<table class='detail' width="100%">

    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.pwd1"/>
    </th>
    <td align="left">
        <form:password path="password" id="password" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="jfi99billPosLog.isCheckOrder"/>
    </th>
    <td align="left">
        <input type="checkbox" name="isCheckOrder" id="isCheckOrder" value="1" />
    </td></tr>

    <tr><th>
        <jecs:label  key="sysUser.validateCode"/>
    </th>
    <td align="left">
        <input name="verifyCode" tabindex="3" maxlength="4" type="text"/>
        <img name="vpic" src="<c:url value="/generateverifycode"/>?rnd=<%=Math.random() * 1000000%>" />
    </td></tr>

</table>
</form:form>
