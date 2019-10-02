<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwSuggestionDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwSuggestionDetail.heading"/></content>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandsortManager.js'/>" ></script>
<spring:bind path="inwSuggestion.*">
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
<c:if test="${param.strAction == 'suggestionReplyDepart'}">

	<form:form commandName="inwSuggestion" method="post" action="editInwSuggestionDepart.html" onsubmit="return validateInwSuggestion(this)" id="inwSuggestionFormDepart" name="inwSuggestionFormDepart">
	
	<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>
	<input type="hidden" name="differenceInwDepart" value="${param.differenceInwDepart }" id="differenceInwDepart"/>
	<input type="hidden" name="departmentId" value="${param.departmentId }" id="departmentId"/>
	
	<table class='detail' width="100%">
	
	<form:hidden path="id"/>
	
	    <tr><th>
	        <jecs:label  key="inwSuggestion.suggestedTopics"/>
	    </th>
	    <td align="left" width="80%"  colspan="3">
	        ${inwSuggestion.subject}
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="inwSuggestion.type"/>
	    </th>
	    <td align="left" colspan="3">
	           <c:if test ="${inwSuggestion.suggestionSort =='1' }">
	               <jecs:code listCode="inwsuggestion.suggestionsort" value="1"></jecs:code>
	           </c:if>
	           <c:if test ="${inwSuggestion.suggestionSort =='2' }">
	               <jecs:code listCode="inwsuggestion.suggestionsort" value="2"></jecs:code>
	           </c:if>
	    </td></tr>
	     <tr>
	                <td nowrap="nowrap" align="right"><jecs:label key="inwDemandsort.spece"/></td>
	                <td nowrap="nowrap">	         	                  
	                            <input name="sortName" id="sortName" cssClass="text medium" maxlength="100" size="30"  disabled="true"/>
	                </td>
	                
	                
	                
	                <td nowrap="nowrap"><jecs:label key="inwDemand.name"/></td>
	                <td nowrap="nowrap">	                    
	                            <input name="name" id="name" cssClass="text medium" maxlength="100" size="30"  disabled="true"/>	                        
	                </td>
	                
	         </tr>
	       <tr>
	                <td nowrap="nowrap" align="right"><jecs:label key="schedule.tel"/></td>
	                <td nowrap="nowrap">${inwSuggestion.phone}</td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	         </tr>
	           <tr>
	                <td nowrap="nowrap"><ng:locale key="micro.channel"/></td>
	                <td nowrap="nowrap">${inwSuggestion.microMessage }</td>
	                <td nowrap="nowrap">QQ</td>
	                <td nowrap="nowrap">${inwSuggestion.qq }</td>
	         </tr>
	         
	    <tr><th>
	        <jecs:label  key="inwSuggestion.proposalContent"/>
	    </th>
	    <td align="left"  colspan="3">
	         <form:textarea path="content" id="content" cssClass="text medium" cssStyle="width:80%;height:250px;margin-top:10px;resize:none;" readonly="true"/>
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="inwSuggestion.createTime"/>
	    </th>
	    <td align="left"  colspan="3">
	         ${inwSuggestion.createTime}
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="inwSuggestion.userCode"/>
	    </th>
	    <td align="left"  colspan="3">
	         ${inwSuggestion.userCode}
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="proposed.adoption"/>
	    </th>
	    <td colspan="3">
	      <c:if   test="${differenceInwDepart=='activitySuggestionDepart'}">
	           <jecs:list name="status" listCode="first.audit" value="${inwSuggestion.status}" defaultValue="false"/>
	      </c:if>
	      <c:if   test="${differenceInwDepart=='initialAuditDepart'}">
	            <jecs:list name="status" listCode="initial.audit" value="${inwSuggestion.status}" defaultValue="false"/>
	      </c:if>
	      <c:if   test="${differenceInwDepart=='feasibilityAuditDepart'}">
	             <jecs:list name="status" listCode="feasibility.audit" value="${inwSuggestion.status}" defaultValue="false"/>
	      </c:if>
	    </td>
	    </tr>
	    <c:if   test="${differenceInwDepart=='activitySuggestionDepart'}">
		    <tr>
		        <th>
		             <jecs:label  key="inwDemand.verify"/>
		        </th>
		        <td align="left" colspan="3">
		               <c:choose>
		    			        <c:when test="${inwSuggestion.initialAudit == 'Y'}">
		    			             <jecs:code listCode="inwdemand.verify" value="Y"></jecs:code>
		    			        </c:when>
		    			        <c:otherwise>
		    			             <jecs:code listCode="inwdemand.verify" value="N"></jecs:code>
		    			        </c:otherwise>
		    			</c:choose>
		    			<form:hidden path="initialAudit" id="initialAudit"/>
		        </td>
		    </tr>
	    </c:if>
	    <tr>
	        <th>
	             <jecs:label  key="inwIntegration.num"/>
	        </th>
	        <td align="left" colspan="3">
	              ${inwSuggestion.integration}
	        </td>
	    </tr>
	      
	     <tr>
	        <th>
	             <jecs:label  key="suggested.reply"/>
	        </th>
	        <td align="left" colspan="3">
	              <form:textarea path="replyTontent" id="replyTontent" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;"/>
	        </td>
	        
	    </tr>
	    
	     <tr>
	        <th>
	             <jecs:label  key="inwSuggestion.firstReplyAudit"/>
	        </th>
	        <td align="left" colspan="3">
	             
    			        <c:if test="${inwSuggestion.firstReplyAudit == 'Y'}">
    			             <jecs:code listCode="inwsuggestion.contentaudit" value="Y"></jecs:code>
    			        </c:if>
    			        <c:if test="${inwSuggestion.firstReplyAudit == 'N'}">
    			             <jecs:code listCode="inwsuggestion.contentaudit" value="N"></jecs:code>
    			        </c:if>
		    	  
		    			<form:hidden path="firstReplyAudit" id="firstReplyAudit"/>
	           
	        </td>
	    </tr>
	    
	    
	         <tr>
	        <th>
	             <jecs:label  key="suggested.replyTwo"/>
	        </th>
	        <td align="left" colspan="3">
	              <form:textarea path="replyContentSecond" id="replyContentSecond" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;"/>
	        </td>
	        
	    </tr>
	    
	    <tr>
	        <th>
	             <jecs:label  key="inwSuggestion.secondReplyAudit"/>
	        </th>
	        <td align="left" colspan="3">
	             
    			        <c:if test="${inwSuggestion.secondReplyAudit == 'Y'}">
    			             <jecs:code listCode="inwsuggestion.contentaudit" value="Y"></jecs:code>
    			        </c:if>
    			        <c:if test="${inwSuggestion.secondReplyAudit == 'N'}">
    			             <jecs:code listCode="inwsuggestion.contentaudit" value="N"></jecs:code>
    			        </c:if>
		    	  
		    	  <form:hidden path="secondReplyAudit" id="secondReplyAudit"/>	        
	        </td>
	    </tr>
    
	    <tr>
	        <th>
	             <jecs:label  key="suggested.replyThree"/>
	        </th>
	        <td align="left" colspan="3">
	              <form:textarea path="replyContentThird" id="replyContentThird" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;"/>
	        </td>	        
	    </tr>
	    
	    <tr>
	        <th>
	             <jecs:label  key="inwSuggestion.thirdReplyAudit"/>
	        </th>
	        <td align="left" colspan="3">	        
	           
    			        <c:if test="${inwSuggestion.thirdReplyAudit == 'Y'}">
    			             <jecs:code listCode="inwsuggestion.contentaudit" value="Y"></jecs:code>
    			        </c:if>
    			        <c:if test="${inwSuggestion.thirdReplyAudit == 'N'}">
    			             <jecs:code listCode="inwsuggestion.contentaudit" value="N"></jecs:code>
    			        </c:if>
		    	  
		    	  <form:hidden path="thirdReplyAudit" id="thirdReplyAudit"/>	   
	        </td>
	    </tr>
	    
	</table>
	</form:form>
</c:if>

<c:if test="${param.strAction == 'suggestionContentAuditDepart'}">
   <form:form commandName="inwSuggestion" method="post" action="editInwSuggestionDepart.html" onsubmit="return validateInwSuggestion(this)" id="inwSuggestionFormTwoDepart" name="inwSuggestionFormTwoDepart">
	
	<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>
	<input type="hidden" name="differenceInwDepart" value="${param.differenceInwDepart }" id="differenceInwDepart"/>
	<input type="hidden" name="departmentId" value="${param.departmentId }" id="departmentId"/>
	
	
	<table class='detail' width="100%">
	
	<form:hidden path="id"/>
	
	    <tr><th>
	        <jecs:label  key="inwSuggestion.suggestedTopics"/>
	    </th>
	    <td align="left" width="80%"  colspan="3">
	        ${inwSuggestion.subject}
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="inwSuggestion.type"/>
	    </th>
	    <td align="left" colspan="3">
	           <c:if test ="${inwSuggestion.suggestionSort =='1' }">
	               <jecs:code listCode="inwsuggestion.suggestionsort" value="1"></jecs:code>
	           </c:if>
	           <c:if test ="${inwSuggestion.suggestionSort =='2' }">
	               <jecs:code listCode="inwsuggestion.suggestionsort" value="2"></jecs:code>
	           </c:if>
	    </td></tr>
	     <tr>
	                <td nowrap="nowrap" align="right"><jecs:label key="inwDemandsort.spece"/></td>
	                <td nowrap="nowrap">  
	                     <input name="sortName" id="sortName" cssClass="text medium" maxlength="100" size="30"  disabled="true"/>
	                </td>
	                
	                
	                
	                <td nowrap="nowrap"><jecs:label key="inwDemand.name"/></td>
	                <td nowrap="nowrap">
	                     <input name="name" id="name" cssClass="text medium" maxlength="100" size="30"  disabled="true"/>
	                </td>
	                
	         </tr>
	       <tr>
	                <td nowrap="nowrap" align="right"><jecs:label key="schedule.tel"/></td>
	                <td nowrap="nowrap">${inwSuggestion.phone}</td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	         </tr>
	           <tr>
	                <td nowrap="nowrap"><ng:locale key="micro.channel"/></td>
	                <td nowrap="nowrap">${inwSuggestion.microMessage }</td>
	                <td nowrap="nowrap">QQ</td>
	                <td nowrap="nowrap">${inwSuggestion.qq }</td>
	         </tr>
	         
	    <tr><th>
	        <jecs:label  key="inwSuggestion.proposalContent"/>
	    </th>
	    <td align="left"  colspan="3">
	         <form:textarea path="content" id="content" cssClass="text medium" cssStyle="width:80%;height:250px;margin-top:10px;resize:none;" readonly="true"/>
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="inwSuggestion.createTime"/>
	    </th>
	    <td align="left"  colspan="3">
	         ${inwSuggestion.createTime}
	    </td></tr>
	    <tr><th>
	        <jecs:label  key="inwSuggestion.userCode"/>
	    </th>
	    <td align="left"  colspan="3">
	         ${inwSuggestion.userCode}
	    </td></tr>

	    <tr><th>
	        <jecs:label  key="proposed.adoption"/>
	    </th>
	    <td colspan="3">
	      <c:if   test="${differenceInwDepart=='activitySuggestionDepart'}">
	           <jecs:list name="status" listCode="first.audit" value="${inwSuggestion.status}" defaultValue="false"/>
	      </c:if>
	      <c:if   test="${differenceInwDepart=='initialAuditDepart'}">
	            <jecs:list name="status" listCode="initial.audit" value="${inwSuggestion.status}" defaultValue="false"/>
	      </c:if>
	      <c:if   test="${differenceInwDepart=='feasibilityAuditDepart'}">
	             <jecs:list name="status" listCode="feasibility.audit" value="${inwSuggestion.status}" defaultValue="false"/>
	      </c:if>
	    </td>
	    </tr>
	    <c:if   test="${differenceInwDepart=='activitySuggestionDepart'}">
		    <tr>
		        <th>
		             <jecs:label  key="inwDemand.verify"/>
		        </th>
		        <td align="left" colspan="3">
		               <c:choose>
		    			        <c:when test="${inwSuggestion.initialAudit == 'Y'}">
		    			             <jecs:code listCode="inwdemand.verify" value="Y"></jecs:code>
		    			        </c:when>
		    			        <c:otherwise>
		    			             <jecs:code listCode="inwdemand.verify" value="N"></jecs:code>
		    			        </c:otherwise>
		    			</c:choose>
		    			<form:hidden path="initialAudit" id="initialAudit"/>
		        </td>
		    </tr>
	    </c:if>
	    <tr>
	        <th>
	             <jecs:label  key="inwIntegration.num"/>
	        </th>
	        <td align="left" colspan="3">
	              ${inwSuggestion.integration }
	        </td>
	    </tr>
	      
	     <tr>
	        <th>
	             <jecs:label  key="suggested.reply"/>
	        </th>
	        <td align="left" colspan="3">
	              <form:textarea path="replyTontent" id="replyTontent" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;" readonly="true"/>
	        </td>
	        
	    </tr>
	    
	    <tr>
	        <th>
	            <jecs:label  key="inwSuggestion.firstReplyAudit"/>
	        </th>
	        <td align="left" colspan="3">
	           <jecs:list name="firstReplyAudit" listCode="inwsuggestion.contentaudit" value="${inwSuggestion.firstReplyAudit}" defaultValue="false" showBlankLine="true"/>
	        </td>
	    </tr>
	    
	    
	         <tr>
	        <th>
	             <jecs:label  key="suggested.replyTwo"/>
	        </th>
	        <td align="left" colspan="3">
	              <form:textarea path="replyContentSecond" id="replyContentSecond" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;" readonly="true"/>
	        </td>
	        
	    </tr>
	    
	    <tr>
	        <th>
	           <jecs:label  key="inwSuggestion.secondReplyAudit"/>
	        </th>
	        <td align="left" colspan="3">
	           <jecs:list name="secondReplyAudit" listCode="inwsuggestion.contentaudit" value="${inwSuggestion.secondReplyAudit}" defaultValue="false" showBlankLine="true" />
	        </td>
	    </tr>
	    
	    <tr>
	        <th>
	             <jecs:label  key="suggested.replyThree"/>
	        </th>
	        <td align="left" colspan="3">
	              <form:textarea path="replyContentThird" id="replyContentThird" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;" readonly="true"/>
	        </td>
	        
	    </tr>
	    
	    <tr>
	        <th>
	           <jecs:label  key="inwSuggestion.thirdReplyAudit"/>
	        </th>
	        <td align="left" colspan="3">
	           <jecs:list name="thirdReplyAudit" listCode="inwsuggestion.contentaudit" value="${inwSuggestion.thirdReplyAudit}" defaultValue="false" showBlankLine="true"/>
	        </td>
	    </tr>
	    
	    
	</table>
	</form:form>
</c:if>

<table width="100%">
<tr>
  <td id="saveOrUpdate" align="right" nowrap="nowrap">
  
	  <c:if test="${param.strAction=='suggestionReplyDepart'}">
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" name="saveOrUpdate"  onclick="saveOrUpdate(document.inwSuggestionFormDepart)" value="<jecs:locale key="operation.button.save"/>" /> &nbsp;&nbsp;&nbsp;&nbsp;</td>
	  </c:if>
	  <c:if test="${param.strAction=='suggestionContentAuditDepart'}">
	  	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" name="saveOrUpdateTwo"  onclick="saveOrUpdate(document.inwSuggestionFormTwoDepart)" value="<jecs:locale key="button.audit"/>" /> &nbsp;&nbsp;&nbsp;&nbsp;</td>	  
	  </c:if>
  
  <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" name="cancel"  onclick="returnOld()" value="<jecs:locale key='operation.button.return'/>" /></td>
</tr>
</table>

<script>

     var differenceInwDepart = "<%= session.getAttribute("differenceInwDepart")%>";
     var sstrAction = "<%= request.getAttribute("strAction")%>";
     window.onload = function definitionHide(){
            if("activitySuggestionDepart"==differenceInwDepart){
                document.getElementById("differenceInwDepart").value="activitySuggestionDepart";            
           }else if("initialAuditDepart"==differenceInwDepart){
                document.getElementById("differenceInwDepart").value="initialAuditDepart";
           }else{
                document.getElementById("differenceInwDepart").value="feasibilityAuditDepart";
           }           
                var demandId = "<%= session.getAttribute("demandId")%>";
                inwDemandManager.getInwDemandById(demandId,demandName); 
            
      }
      
      function demandName(name){
           document.getElementById("name").value=name;
           demandsortNameGet();
      }
      
      function demandsortNameGet(){
          var demandsortId = "<%= session.getAttribute("demandsortId")%>";
               inwDemandsortManager.getInwDemandsortById(demandsortId,demandsortName);
      }
      
      function demandsortName(sortName){
           document.getElementById("sortName").value=sortName;
      } 
         
      
      function returnOld(){
                     window.history.back();
      }
     
      function saveOrUpdate(theForm){
                theForm.submit();
      }
      
      
</script>
<v:javascript formName="inwSuggestion" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
