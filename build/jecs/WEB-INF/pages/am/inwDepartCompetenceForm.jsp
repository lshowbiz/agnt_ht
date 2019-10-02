<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDepartCompetenceDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDepartCompetenceDetail.heading"/></content>



<spring:bind path="inwDepartCompetence.*">
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

<c:if test="${strAction!='queryDetailInwDepartCompetence'}">
<form:form commandName="inwDepartCompetence" method="post" action="editInwDepartCompetence.html" name="inwDepartCompetenceForm" id="inwDepartCompetenceForm">
<input type="hidden" name="strAction" id="strAction"  value="${param.strAction }"/>
	<table class='detail' width="100%">
	    <form:hidden path="id" id="id"/>
	    <tr>
			   <th>
			       <jecs:label  key="inwDepartment.name"/>
			   </th>
			   <td align="left">
			       <form:input path="departmentId" id="departmentId" cssClass="text medium" cssStyle="display:none"/>
			       <input name="departmentName" id="departmentName" cssClass="text medium"   value="${departmentName }" readonly="true" onclick="selectInwDepart()"/>
			   </td>
	    </tr>
	    <tr>
			    <th>
			        <jecs:label  key="sysPower.powerName"/>
			    </th>
			    <td align="left">
			        <form:input path="demandId" id="demandId" cssClass="text medium" cssStyle="display:none"/>
			        <input name="demandName" id="demandName" cssClass="text medium"  value="${demandName }" readonly="true" onclick="selectInwDemand()"/>
			    </td>
	    </tr>
	</table>
</form:form>


<table width="100%">
  <tr>
    <td align="right" width="32%">
         <input name="save"  type="button" class="button" size="1"  onclick="saveInwDepartCompetence(document.inwDepartCompetenceForm)" value="<jecs:locale key="button.save"/>" />&nbsp;&nbsp;
    </td>
	<td align="right" width="6%">
	         <input name="deleteInwDepart"  type="button" class="button" size="1" id="deleteInwDepart"  onclick="deleteInwDepartCompetence(document.inwDepartCompetenceForm)" value="<jecs:locale key="operation.button.delete"/>" />&nbsp;&nbsp;
	</td>
    <td align="left" width="62%">
         <input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />&nbsp;&nbsp;
    </td>
  </tr>
</table>
</c:if>


<c:if test="${strAction =='queryDetailInwDepartCompetence'}">
     <form:form commandName="inwDepartCompetence" method="post" action="editInwDepartCompetence.html" name="inwDepartCompetenceFormTwo" id="inwDepartCompetenceFormTwo">
			<form:hidden path="id" id="id"/>
			<table class='detail' width="100%">
			    <tr>
					   <th>
					       <jecs:label  key="inwDepartment.name"/>
					   </th>
					   <td align="left">
					       <form:input path="departmentId" id="departmentId" cssClass="text medium" cssStyle="display:none"/>
					       ${departmentName }
					   </td>
			    </tr>
			    <tr>
					    <th>
					        <jecs:label  key="sysPower.powerName"/>
					    </th>
					    <td align="left">
					        <form:input path="demandId" id="demandId" cssClass="text medium" cssStyle="display:none"/>
					        ${demandName }
					    </td>
			    </tr>
			</table>
   </form:form>
   
	    <table width="100%">
			  <tr>
				    <td align="right" width="60%">
				         <input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />&nbsp;&nbsp;
				    </td>
				    <td align="left" width="40%">
				    </td>
			  </tr>
	    </table>

</c:if>

<script type="text/javascript">

    window.onload = function initJsp(){
         var idV = document.getElementById("id").value;
         if(""==idV || null ==idV){
             document.getElementById("deleteInwDepart").style.display='none';
         }else{
             document.getElementById("deleteInwDepart").style.display='';
         }    
    }

    function selectInwDepart(){
        open("<c:url value='/inwDepartments.html?strAction=selectInwDepartByInwDC'/>","","height=500, width=800, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");     
    }

    function selectInwDemand(){
        open("<c:url value='/inwDemandsActivity.html?strAction=selectInwDemandByInwDC'/>","","height=500, width=850, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");     
    }
    
    function saveInwDepartCompetence(theForm){
         document.getElementById("strAction").value="editorInwDepartCompetence";
         var departmentName = document.getElementById("departmentName").value;
         var demandName = document.getElementById("demandName").value;
         if(""==departmentName || null ==departmentName){
             alert("<jecs:locale key='inwDepartment.name'/>"+"<jecs:locale key='poAssistMemberOrder.notNUll'/>");
         }else if(""==demandName || null ==demandName){
             alert("<jecs:locale key='sysPower.powerName'/>"+"<jecs:locale key='poAssistMemberOrder.notNUll'/>");
         }else{
              theForm.submit();
         }         
        
    }
    
    function deleteInwDepartCompetence(theForm){
        if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
             document.getElementById("strAction").value="deleteInwDepartCompetence";
             theForm.submit();
         }
    }
    
    function returnOld(){
        window.history.back();
    }

</script>

