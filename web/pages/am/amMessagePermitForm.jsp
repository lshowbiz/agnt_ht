<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amMessagePermitDetail.title"/></title>
<content tag="heading"><jecs:locale key="amMessagePermitDetail.heading"/></content>

<%-- 
<c:set var="buttons">

		<jecs:power powerCode="addAmMessagePermit">
			
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		
		<jecs:power powerCode="deletePdWarehouseUser">
			<c:if test="${amMessagePermit.ampNo != null}">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'AmMessagePermit')" value="<jecs:locale key="operation.button.delete"/>" />
		  </c:if>
		</jecs:power>
</c:set>
--%>
<c:set var="buttons">

		<jecs:power powerCode="addAmMessagePermit">
			
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		
		<jecs:power powerCode="deletePdWarehouseUser">
			<c:if test="${amMessagePermit.ampNo != null}">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'AmMessagePermit')"><jecs:locale key="operation.button.delete"/></button>
		  </c:if>
		</jecs:power>
</c:set>
<spring:bind path="amMessagePermit.*">
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

<form:form commandName="amMessagePermit" method="post" action="editAmMessagePermit.html" onsubmit="return validateAmMessagePermit(this)" id="amMessagePermitForm">
<%-- 
	<div class="searchBar">
		<c:out value="${buttons}" escapeXml="false"/>	
   	
   	<input type="button" class="button" name="cancel" onclick="toBack('pdWarehouseUserContent');" value="<jecs:locale key="operation.button.return"/>" />
	</div>
--%>

<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
	<tbody class="window">
	<form:hidden path="ampNo"/>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="amMessage.msgClassNo"/></span>
		    </th>
		    <td>
		        <!--form:errors path="msgClassNo" cssClass="fieldError"/-->
		        <span class="textbox"><span class="textbox-text"><jecs:code listCode="msgclassno" value="${amMessagePermit.msgClassNo}"/></span></span>
				<input type="hidden" name="msgClassNo" value="${amMessagePermit.msgClassNo}"/>
		    </td>
		</tr>
	
	    
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="login.userType.console"/></span>
		    </th>
		    <td align="left">
		        <!--form:errors path="userCode" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="userCode" id="userCode" cssClass="textbox-text"/></span>
		        
		       <img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
		   		<span class="textbox"> <input type="text" name="userName" readonly="readonly" id="agentName" class="textbox-text"/></span>
		    </td>
		</tr>
		
		<tr>		
			<td class="command" colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
				<button type="button" class="btn btn_sele" name="cancel" onclick="toBack('pdWarehouseUserContent');" ><jecs:locale key="operation.button.return"/></button>
			</td>
		</tr>
		
	    
	    <%--<tr>
	    <th class="command">
	        <jecs:label key="sysOperationLog.moduleName"/>
	    </th>
	    <td class="command">
	    	
	    		
			<c:out value="${buttons}" escapeXml="false"/>	
	   	
	   	<input type="button" class="button" name="cancel" onclick="toBack('pdWarehouseUserContent');" value="<jecs:locale key="operation.button.return"/>" />
	    </td>
			</tr>--%>
	</tbody>
</table>

</form:form>
<script type="text/javascript">

    	
    	function searchUser(){
    			var userCodeExample = $('userCode').value;
    			////open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=company&userCode="+userCodeExample);
    			
    			$('userCode').value=ret['userCode'];
    			$('userName').value=ret['userName'];
    			
    			//window.open("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,"","height=400, width=600, top=180, left=250, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    			
    		}
    		
</script>