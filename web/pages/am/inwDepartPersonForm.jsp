<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDepartPersonDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDepartPersonDetail.heading"/></content>

<spring:bind path="inwDepartPerson.*">
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

<form:form commandName="inwDepartPerson" method="post" action="editInwDepartPerson.html"  name="inwDepartPersonForm" id="inwDepartPersonForm">
<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
<table class='detail' width="100%">
<form:hidden path="id"/>
     <tr>
	    <th>
	        <jecs:label  key="miMember.memberNo"/>
	    </th>
	    <td align="left">
	        <form:input path="userCode" id="userCode" cssClass="text medium" maxlength="20"/>
	    </td>
    </tr>
    <tr>
	    <th>
	        <jecs:label  key="inwDepartment.name"/>
	    </th>
	    <td align="left">
	        <form:select path="departmentId" id="departmentId">
	             <form:option value="" label=""/>
	             <form:options items="${inwDepartmentList}" itemValue="id" itemLabel="name"/>
	        </form:select>
	    </td>
    </tr>
</table>

</form:form>

<table width="100%">
  <tr>
    <td align="right" width="25%">     
         <input name="save"  type="button" class="button" size="1"  onclick="saveInwDepartPerson(document.inwDepartPersonForm)" value="<jecs:locale key="button.save"/>" />&nbsp;&nbsp;&nbsp;&nbsp;       
      <c:if test="${strAction == 'editorInwDepartPerson'}">
         <input name="deleteInwDepartPerson"  type="button" class="button" size="1"  onclick="deleteInwDepartPerson(document.inwDepartPersonForm)" value="<jecs:locale key="operation.button.delete"/>" />&nbsp;&nbsp;
      </c:if>
    </td>
    <td align="left" width="45%">
         &nbsp;&nbsp;<input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
    </td>
  </tr>
</table>

<script type="text/javascript">
    
    
    
    function saveInwDepartPerson(theForm){   
         theForm.submit();
     }
    
     function deleteInwDepartPerson(theForm){
         if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
             document.getElementById("strAction").value="deleteInwDepartPerson";
             theForm.submit();
         }
     }
    
    function returnOld(){
         window.history.back();
     }
     
   

</script>


