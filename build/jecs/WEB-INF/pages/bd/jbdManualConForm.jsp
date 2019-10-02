<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdManualConDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdManualConDetail.heading"/></content>

<c:set var="buttons">
	<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
		<jecs:locale key="button.save"/>
	</button>
	<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
</c:set>

<spring:bind path="jbdManualCon.*">
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

<form:form commandName="jbdManualCon" method="post" action="editJbdManualCon.html" onsubmit="return validateJbdManualCon(this)" id="jbdManualConForm">
<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>


<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc">会员编号：</span></th>
	    <td>
	        <span class="textbox">${jbdManualCon.userCode }</span>
	    </td>
	    <th></th>
    	<td>
    		
    	</td>
    </tr>

    <tr class="edit_tr">
    	<th><span class="text-font-style-tc">结束期:</span></th>
	    <td>
	       <span class="textbox"><form:input path="endWeek" id="endWeek" cssClass="textbox-text"/></span>
	    </td>
		<th>
			<span class="text-font-style-tc">开始期:</span>
		</th>
	    <td>
	    	<span class="textbox"><form:input path="startWeek" id="startWeek" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	<tr class="edit_tr">
    	<th><span class="text-font-style-tc">感恩奖资格：</span></th>
    	<td>
    		<span class="textbox">
    		<jecs:list name="consumerStatus" listCode="yesno" styleClass="textbox-text"
    		 value="${jbdManualCon.consumerStatus}" defaultValue="0"/>	
    		</span>
    	</td>
    	<th><span class="text-font-style-tc">销售奖资格：</span></th>
    	<td>
    		<span class="textbox">
		    	<jecs:list name="salesStatus" listCode="yesno" styleClass="textbox-text"
		    	value="${jbdManualCon.salesStatus}" defaultValue="0"/>	
	    	</span>
    	</td>
    </tr>
	<tr class="edit_tr">
    	<td colspan="4" class="command" align="center">
    		<c:out value="${buttons}" escapeXml="false"/>	
    	</td>
    </tr>
    
</table>

</form:form>
