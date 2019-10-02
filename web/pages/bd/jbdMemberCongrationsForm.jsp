<%@ include file="/common/taglibs.jsp"%>


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

<spring:bind path="jbdMemberCongrations.*">
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

<form:form commandName="jbdMemberCongrations" method="post" action="editJbdMemberCongrations.html" id="JbdMemberCongrationsForm">
<%-- 
	<div class="searchBar">	
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="7%">
<tbody class="window">
<form:hidden path="id"/>

	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
	    </th>
	    <td>
	    	<input type="hidden" name="userCode" value="${jbdMemberCongrations.userCode }">
	    	<span class="textbox">${jbdMemberCongrations.userCode }</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="bdPeriod.fyear"/></span>
	    </th>
	    <td>
	    	<input type="hidden" name="fyear" value="${jbdMemberCongrations.yearMonth }">
	    	<span class="textbox">${jbdMemberCongrations.yearMonth }</span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="pass.star.type"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    		<jecs:list name="starLevel" listCode="qualify.star.zero" styleClass="textbox-text"
	    		defaultValue="" value="${jbdMemberCongrations.starLevel }"/>
	    	</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="chinese.name"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    		<input type="text" name="chineseName" class="textbox-text"
	    		value="${jbdMemberCongrations.chineseName }" />
	    	</span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="english.name"/></span>
	    </th>
	    <td colspan="3">
	    	<span class="textbox">
	    		<input type="text" name="englishName" value="${jbdMemberCongrations.englishName }" class="textbox-text"/>
	    	</span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc-tare">
	        	<jecs:label  key="schedule.remark"/>
	        </span>
	    </th>
	    <td colspan="3">
			<textarea name="remark" class="textarea_border">${jbdMemberCongrations.remark }</textarea>
	    </td>
	</tr>
	<tr class="edit_tr">
	    
	    <td colspan="4" align="center" class="command">
			<c:out value="${buttons}" escapeXml="false"/>
	    </td>
	</tr>
    </tbody>
</table>
</form:form>
