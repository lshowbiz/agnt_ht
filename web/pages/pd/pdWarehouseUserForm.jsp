<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseUserDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="addPdWarehouseUser">
			
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		
		<jecs:power powerCode="deletePdWarehouseUser">
			<c:if test="${pdWarehouseUser.wuId != null}">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdWarehouseUser')" value="<jecs:locale key="operation.button.delete"/>" />
		  </c:if>
		</jecs:power>
</c:set>

<spring:bind path="pdWarehouseUser.*">
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

<form:form commandName="pdWarehouseUser" method="post" action="editPdWarehouseUser.html" onsubmit="return validatePdWarehouseUser(this)" id="pdWarehouseUserForm">

<div id="titleBar">
	
</div>
<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseUser')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="wuId"/>

    <tr><th>
        <jecs:label  key="busi.warehouse.warehouseno"/>
    </th>
    <td align="left">
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <form:input path="warehouseNo" id="warehouseNo" readonly="true" onclick="selectWarehouse('warehouseNo');" cssClass="readonly"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="login.userType.console"/>
    </th>
    <td align="left">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input path="userCode" id="userCode" cssClass="text medium"/>
        
       <img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
   		 <input type="text" name="userName" readonly="true" id="agentName"/>
    </td></tr>


	<tr>
    <th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
    	
    		
		<c:out value="${buttons}" escapeXml="false"/>	
   	
   	<input type="button" class="button" name="cancel" onclick="toBack('pdWarehouseUserContent');" value="<jecs:locale key="operation.button.return"/>" />
    </td>
		</tr>
		
		
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdWarehouseUserForm'));
    function selectWarehouse(str){
     			
     			//window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=600, width=800, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
     	
    function toback(str){
			
				
    		this.location='<c:url value="/pdWarehouseUsers.html"/>?strAction='+str;
    	}
    	
    	function searchUser(){
    			var userCodeExample = $('userCode').value;
    			////open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=company&userCode="+userCodeExample);
    			
    			$('userCode').value=ret['userCode'];
    			$('userName').value=ret['userName'];
    			
    			//window.open("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,"","height=400, width=600, top=180, left=250, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    			
    		}
    		
</script>

<v:javascript formName="pdWarehouseUser" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
