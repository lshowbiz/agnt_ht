<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdMemberLinkCalcHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistDetail.heading"/></content>


<spring:bind path="jbdMemberLinkCalcHist.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jbdMemberLinkCalcHist" method="post" action="editMiBonusKpvChange.html" id="jbdMemberLinkCalcHistForm">
	<%-- 
	<div class="searchBar">
		<input type="submit" class="button" name="save" value="<jecs:locale key="operation.button.save"/>" />
			
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="bdBounsDeduct.wweek"/></span>
	    </th>
	    <td>
	        <!--form:errors path="wweek" cssClass="fieldError"/-->
	        <span class="textbox"><jecs:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" /></span>
	    </td>
	
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
	    </th>
	    <td>
	        <!--form:errors path="companyCode" cssClass="fieldError"/-->
	       <span class="textbox">${jbdMemberLinkCalcHist.userCode }</span>
	    </td>
	</tr>
    
    <%--<tr><th>
        <jecs:label  key="bdCalculatingSubDetail.name"/>
    </th>
    <td align="left">
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
       ${jbdMemberLinkCalcHist.name }
    </td></tr>--%>
	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="bdSendRecord.cardType"/></span>
	    </th>
	    <td>
	        <!--form:errors path="cardType" cssClass="fieldError"/-->
	        
			<span class="textbox"><jecs:code listCode="bd.cardtype" value="${jbdMemberLinkCalcHist.cardType}"/></span>
	    </td>
	
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="bdBonusStatReport.company.keey.pv"/></span>
	    </th>
	    <td>
	        <!--form:errors path="keepPv" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="keepPv" id="keepPv" cssClass="textbox-text"/></span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="bdBonus.kpvMember"/></span>
	    </th>
	    <td>
	        <!--form:errors path="keepUserCode" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="keepUserCode" id="keepUserCode" cssClass="textbox-text"/></span>
	    </td>
	</tr>

<%--<tr>
	<th class="command"><wecs:label key="sysOperationLog.moduleName" /></th>
	<td class="command">
		<input type="submit" class="button" name="save" value="<jecs:locale key="operation.button.save"/>" />
			
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />

		
	</td>
</tr>--%>
   <tr class="edit_tr">
   	<td colspan="4" class="command" align="center">
   		<button type="submit" class="btn btn_sele" name="save">
   			<jecs:locale key="operation.button.save"/>
   		</button>
   		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
   			<jecs:locale key="operation.button.return"/>
   		</button>
   	</td>
   </tr>
</tbody>
</table>

</form:form>

