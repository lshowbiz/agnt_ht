<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jmiLevelLockDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiLevelLockDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
				<jecs:locale key="operation.button.save"/>
			</button>
		</jecs:power>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
			<jecs:locale key="operation.button.return"/>
		</button>
</c:set>

<spring:bind path="jmiLevelLock.*">
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

<form:form commandName="jmiLevelLock" method="post" action="editJmiLevelLock.html"   id="jmiLevelLockForm">
<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
    </th>
    <td>
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        
        
     	 <c:if test="${not empty jmiLevelLock.id}"> ${jmiLevelLock.userCode }

	        </c:if>
        
	        <c:if test="${empty jmiLevelLock.id}">
              <span class="textbox"><form:input path="userCode" id="userCode" cssClass="textbox-text"/></span>
	        </c:if>
    </td>
    <th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.cardType"/></span>
    </th>
    <td>
        <!--form:errors path="memberLevel" cssClass="fieldError"/-->
         <span class="textbox">
         	<jecs:list styleClass="textbox-text" name="memberLevel" listCode="member.level" 
         		value="${jmiLevelLock.memberLevel}" defaultValue="5" />
         </span>

    </td></tr>
	<tr class="edit_tr">
		
		<td class="command" align="center" colspan="4">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>

   <%--  <tr><th>
        <jecs:label  key="sysClickLog.isValid"/>
    </th>
    <td align="left">
         <jecs:list styleClass="textbox-text" name="isValid" listCode="yesno" value="${jmiLevelLock.isValid}" defaultValue="1" />
    </td></tr> --%>
</tbody>
</table>

</form:form>