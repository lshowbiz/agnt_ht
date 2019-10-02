<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDepartmentDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDepartmentDetail.heading"/></content>

<spring:bind path="inwDepartment.*">
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

<c:if test="${param.strAction == 'queryDetailInwDepartment'}">
      <form:form commandName="inwDepartment" method="post" action="editInwDepartment.html"  name="inwDepartmentQueryDetailForm" id="inwDepartmentQueryDetailForm">
				<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
				<table class='detail' width="100%">
				<form:hidden path="id"/>
				<form:hidden path="status" id="status"/>
				    <tr>
					    <th>
					        <jecs:label  key="inwDepartment.name"/>
					    </th>
					    <td align="left">
					        ${inwDepartment.name }
					    </td>
				    </tr>
				    <tr>
					    <th>
					        <jecs:label  key="inwDepartment.category"/>
					    </th>
					    <td align="left">
					        <c:if test="${inwDepartment.category == '1'}">
					             <jecs:code listCode="inwdepartment.departcategory" value="1"></jecs:code>
					        </c:if>
					         <c:if test="${inwDepartment.category == '2'}">
					             <jecs:code listCode="inwdepartment.departcategory" value="2"></jecs:code>
					        </c:if>
					         <c:if test="${inwDepartment.category == '3'}">
					             <jecs:code listCode="inwdepartment.departcategory" value="3"></jecs:code>
					        </c:if>
					    </td>
				    </tr>
				    <tr>
					    <th>
					        <jecs:label  key="inwDepartment.higerDepartName"/>
					    </th>
					    <td align="left">
					        ${inwDepartment.higerDepartName }
					    </td>
				    </tr>
				</table>
		</form:form>
</c:if>

<c:if test="${param.strAction != 'queryDetailInwDepartment'}">
	<form:form commandName="inwDepartment" method="post" action="editInwDepartment.html"  name="inwDepartmentForm" id="inwDepartmentForm">
	
		<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
		
		<table class='detail' width="100%">
		
		<form:hidden path="id"/>
		<form:hidden path="status" id="status"/>
		
		
		    <tr>
			    <th>
			        <jecs:label  key="inwDepartment.name"/>
			    </th>
			    <td align="left">
			        <form:input path="name" id="name" cssClass="text medium"/>
			    </td>
		    </tr>
		
		    <tr>
			    <th>
			        <jecs:label  key="inwDepartment.category"/>
			    </th>
			    <td align="left">
			        <jecs:list name="category" listCode="inwdepartment.departcategory" id="category" value="${inwDepartment.category}" defaultValue="false" onchange="resetV()"></jecs:list>
			    </td>
		    </tr>
		
		    <tr>
			    <th>
			        <jecs:label  key="inwDepartment.higerDepartName"/>
			    </th>
			    <td align="left">
			        <form:hidden path="higerId" id="higerId"/>
			        <form:input path="higerDepartName" id="higerDepartName" cssClass="text medium" readonly="true" onclick="checkHigerDepart()"/>
			    </td>
		    </tr>
		 
		</table>
	</form:form>
</c:if>

<table width="100%">
  <tr>
    <td align="right" width="35%">
       <c:if test="${strAction == 'editorInwDepartment'}">
         <input name="save"  type="button" class="button" size="1"  onclick="saveInwDepartment(document.inwDepartmentForm)" value="<jecs:locale key="button.save"/>" />&nbsp;&nbsp;
      </c:if>
    </td>
    <td align="right" width="10%">
      <c:if test="${strAction == 'deleteInwDepartment'}">
         <input name="deleteInwDepart"  type="button" class="button" size="1"  onclick="deleteInwDepartment(document.inwDepartmentForm)" value="<jecs:locale key="operation.button.delete"/>" />&nbsp;&nbsp;
      </c:if>
    </td>
    <td align="left" width="55%">
         &nbsp;&nbsp;<input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
    </td>
  </tr>
</table>

<script>

     function checkHigerDepart(){
          var category = document.getElementById("category").value;
          open("<c:url value='/inwDepartments.html?departCategory="+category+"'/>","","height=500, width=700, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");     
     }
     
     function saveInwDepartment(theForm){
         document.getElementById("strAction").value="editorInwDepartment";
         theForm.submit();
     }
     
     function returnOld(){
         window.history.back();
     }

     function resetV(){
         document.getElementById("higerId").value="";
         document.getElementById("higerDepartName").value="";
     }
     
     function deleteInwDepartment(theForm){
         if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
             document.getElementById("strAction").value="deleteInwDepartment";
             theForm.submit();
         }
     }

</script>
