<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiBlacklistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiBlacklistDetail.heading"/></content>

<c:set var="buttons">
	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" 
			value="<jecs:locale key="operation.button.save"/>" />
	</jecs:power>
	<input type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()" 
		value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="jmiBlacklist.*">
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

<form:form commandName="jmiBlacklist" method="post" action="editJmiBlacklist.html" id="jmiBlacklistForm">
<%-- 
<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiBlacklist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">

<form:hidden path="blId"/>
	<tbody class="window">
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="papertype" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='editJmiBlacklist' }">
			    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
			        <jecs:code listCode="papertype.tw" value="${jmiBlacklist.papertype}"/>
			    </c:if>
			    <c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
			        <jecs:code listCode="papertype" value="${jmiBlacklist.papertype}"/>
			    </c:if>
	        </c:if>
        
	        <c:if test="${param.strAction=='addJmiBlacklist' }">
	        
			    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
				  <jecs:list name="papertype" id="papertype" listCode="papertype.tw" styleClass="textbox-text" value="${jmiBlacklist.papertype}" defaultValue="1"/>
			    </c:if>
			    <c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
				  <jecs:list name="papertype" id="papertype" listCode="papertype" styleClass="textbox-text" value="${jmiBlacklist.papertype}" defaultValue="1"/>
			    </c:if>
	        </c:if>
	      	</span>
    	</td>
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="miMember.papernumber" required="true"/></span>
    	</th> 
	    <td>
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='editJmiBlacklist' }">
	        	 <span class="textbox">${jmiBlacklist.papernumber}</span>
	        </c:if>
	        <c:if test="${param.strAction=='addJmiBlacklist' }">
		        <span class="textbox">
		        	<form:input path="papernumber" id="papernumber" cssClass="textbox-text"/>
		        </span>
	        </c:if>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
	    </th>
    	<td>
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='editJmiBlacklist' }">
	        	<span class="textbox">${jmiBlacklist.userCode}</span>
	        </c:if>
	        <c:if test="${param.strAction=='addJmiBlacklist' }">
		        <span class="textbox">
		        	<form:input path="userCode" id="userCode" cssClass="textbox-text"/>
		        </span>
	        </c:if>
    	</td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="bdCalculatingSubDetail.name"/></span>
	    </th>
	    <td>
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='editJmiBlacklist' }">
	        	<span class="textbox">${jmiBlacklist.userName}</span>
	        </c:if>
	        
	        <c:if test="${param.strAction=='addJmiBlacklist' }">
	        	<span class="textbox">
		        	<form:input path="userName" id="userName" cssClass="textbox-text"/>
		        </span>
	        </c:if>
	    </td>
    </tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.phone"/></span>
	    </th>
	    <td>
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='editJmiBlacklist' }">
	       		<span class="textbox">${jmiBlacklist.phone}</span>
	        </c:if>
	        <c:if test="${param.strAction=='addJmiBlacklist' }">
		        <span class="textbox">
		        	<form:input path="phone" id="phone" cssClass="textbox-text"/>
		        </span>
	        </c:if>
	    </td>
		<th>
        	<span class="text-font-style-tc"><jecs:label  key="customerRecord.state"/></span>
    	</th>
	    <td>
	    	<span class="textbox">
		 		<jecs:list listCode="blacklist.status" name="status" id="status" 
		 			value="${jmiBlacklist.status}" defaultValue="0" styleClass="textbox-text"/>
		 	</span> 
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="customerRecord.type" required="true"/></span>
	    </th>
	    <td colspan="3">
	    	<span class="textbox">
		        <!--form:errors path="blackType" cssClass="fieldError"/--> 
		        <c:if test="${param.strAction=='editJmiBlacklist' }">
			        <jecs:code listCode="blacktype" value="${jmiBlacklist.blackType}"/>
		        </c:if>
		        <c:if test="${param.strAction=='addJmiBlacklist' }">
		        	<jecs:list listCode="blacktype" name="blackType" id="blackType" styleClass="textbox-text"
		        	value="${jmiBlacklist.blackType}" defaultValue="" showBlankLine="true"/>
		        </c:if>
	        </span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
	    </th>
	    <td colspan="3">
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	        <span class="text-font-style-tc-right">
				<form:textarea path="remark" id="remark" cssClass="textarea_border"/>
			</span>
		</td>
	</tr>
	<tr class="edit_tr">
		<td class="command" colspan="4" align="center">
	       <c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
</table>

</form:form>

