<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
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

<spring:bind path="jbdMemberStarRewards.*">
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

<form:form commandName="jbdMemberStarRewards" method="post" action="editJbdMemberStarRewards.html" id="JbdMemberStarRewardsForm">
<%-- 
	<div class="searchBar">	
	<c:out value="${buttons}" escapeXml="false"/>
</div>--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>

	<tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td><span class="textbox">
    	<input type="hidden" name="userCode" value="${jbdMemberStarRewards.userCode }">
    	${jbdMemberStarRewards.userCode }
    </span></td></tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="bdPeriod.fyear"/>
    </span></th>
    <td><span class="textbox">
    	<input type="hidden" name="fyear" value="${jbdMemberStarRewards.fyear }">
    	${jbdMemberStarRewards.fyear }
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="rewards.repolicy"/>
    </span></th>
    <td><span class="textbox">
    	<jecs:list styleClass="textbox-text" name="rewards" listCode="star.rewards.repolicy" defaultValue="" value="${jbdMemberStarRewards.rewards }"></jecs:list styleClass="textbox-text">
    </span></td></tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="rewards.isReach"/>
    </span></th>
    <td><span class="textbox">
<jecs:list styleClass="textbox-text" name="isReach" listCode="yesno" defaultValue="" value="${jbdMemberStarRewards.isReach }"></jecs:list styleClass="textbox-text">	

    </span></td></tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="schedule.remark"/>
    </span></th>
    <td><span class="textbox">
	<textarea rows="3" cols="20" name="remark">${jbdMemberStarRewards.remark }</textarea>
    </span></td></tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc-tare">
       	 <label>会员备注</label>
    </span></th>
    <td><span class="text-font-style-tc-right">
		<textarea name="memberRemark">${jbdMemberStarRewards.memberRemark }</textarea>
    </span></td></tr>
    <tr class="edit_tr">
    	<td class="command"></td>
    	<td class="command"><c:out value="${buttons}" escapeXml="false"/></td>
    </tr>
    </tbody>
</table>
</form:form>
