<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwSuggestionDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwSuggestionDetail.heading"/></content>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandsortManager.js'/>" ></script>
<input type="button" class="button" name="cancel"  onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
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
<form:form  commandName="inwSuggestion" method="post" action="editInwSuggestion.html" onsubmit="return validateInwSuggestion(this)" id="inwSuggestionForm" name="inwSuggestionForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="100%">


    <tr><th>
        <jecs:label  key="inwSuggestion.suggestedTopics"/>
    </th>
    <td align="left" width="80%" colspan="3">
        ${inwSuggestion.subject }
    </td></tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.type"/>
    </th>
    <td align="left" colspan="3">
           <jecs:list name="suggestionSort" listCode="inwsuggestion.suggestionsort" defaultValue="" value="${inwSuggestion.suggestionSort }" disabled="true"></jecs:list>
    </td></tr>
     <tr>
                <td nowrap="nowrap" align="right"><jecs:label key="inwDemandsort.spece"/></td>
                <td nowrap="nowrap"><input name="sortName" id="sortName" cssClass="text medium" maxlength="100" size="30"  disabled="true"/></td>
                <td nowrap="nowrap"><jecs:label key="inwDemand.name"/></td>
                <td nowrap="nowrap"><input name="name" id="name" cssClass="text medium" maxlength="100" size="30"  disabled="true"/></td>
                
         </tr>
       <tr>
                <td nowrap="nowrap" align="right"><jecs:label key="schedule.tel"/></td>
                <td nowrap="nowrap">${inwSuggestion.phone}</td>
                <td nowrap="nowrap"></td>
                <td nowrap="nowrap"></td>
         </tr>
          <tr>
                <td nowrap="nowrap" align="right"><jecs:label key="micro.channel"/></td>
                <td nowrap="nowrap">${inwSuggestion.microMessage}</td>
                <td nowrap="nowrap">QQ</td>
                <td nowrap="nowrap">${inwSuggestion.qq}</td>
         </tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.proposalContent"/>
    </th>
    <td align="left" colspan="3">
       <form:textarea path="content" id="content" cssClass="text medium"  cssStyle="width:80%;height:250px;margin-top:10px;resize:none;" readonly="true"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.createTime"/>
    </th>
    <td align="left" colspan="3">
        ${inwSuggestion.createTime }
    </td></tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.userCode"/>
    </th>
    <td align="left" colspan="3">
         ${inwSuggestion.userCode }
    </td></tr>
    <tr><th>
        <jecs:label  key="inwsuggestion.sstatus"/>
    </th>
    <td align="left" colspan="3">
        <jecs:list name="status" listCode="inwsuggestion.status" defaultValue="" value="${inwSuggestion.status }" disabled="true"></jecs:list>
    </td></tr>
     <tr>
       <td nowrap="nowrap"><form:input path="demandsortId" id="demandsortId" cssClass="text medium" maxlength="50" size="15"  cssStyle="display:none"/></td>
       <td colspan="3" ><form:input path="demandId" id="demandId" cssClass="text medium" maxlength="50" size="15"  cssStyle="display:none"/></td>
                
    </tr>
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
	     <tr>
		        <th>
		             <jecs:label  key="pd.checkTime"/>
		        </th>
		        <td align="left" colspan="3">
		              	        ${inwSuggestion.initialAuditTime}
		        </td>
		 </tr>
		  <tr>
		        <th>
		             <jecs:label  key="inwDemand.reviewedUserCode"/>
		        </th>
		        <td align="left" colspan="3">
		              	        ${inwSuggestion.initialAuditUser}
		        </td>
		 </tr>
		 	 
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
              <form:textarea path="replyTontent" id="replyTontent" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;" disabled="true"/>
        </td>
    </tr>
    <tr>
        <th>
             <jecs:label  key="inwDemand.reviewedTime"/>
        </th>
        <td align="left" colspan="3">
                      ${inwSuggestion.firstReplyTime }       
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
	        </td>
	    </tr>
    
    
    <tr>
        <th>
             <jecs:label  key="suggested.replyTwo"/>
        </th>
        <td align="left" colspan="3">
              <form:textarea path="replyContentSecond" id="replyContentSecond" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;" disabled="true"/>
        </td>
        
    </tr>
    <tr>
        <th>
             <jecs:label  key="inwDemand.reviewedTime"/>
        </th>
        <td align="left" colspan="3">
                      ${inwSuggestion.secondReplyTime }       
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
	        </td>
	   </tr>
    
    
    <tr>
        <th>
             <jecs:label  key="suggested.replyThree"/>
        </th>
        <td align="left" colspan="3">
              <form:textarea path="replyContentThird" id="replyContentThird" cssClass="text medium" cssStyle="width:80%;height:100px;margin-top:10px;resize:none;" disabled="true"/>
        </td>
        
    </tr>
    
    <tr>
        <th>
             <jecs:label  key="inwDemand.reviewedTime"/>
        </th>
        <td align="left" colspan="3">
                      ${inwSuggestion.thirdReplyTime }       
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
	        </td>
	    </tr>
    
   

</table>
</form:form>

<script type="text/javascript">
      window.onload = function definitionHide(){
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
       

      var strAction = "<%= session.getAttribute("strAction")%>";
      function returnOld(){
                  //update 2014-05-09 gw
         //if("activitySuggestion"==strAction){
         //     window.location.href="inwSuggestions.html?strAction=activitySuggestion";
         //  }
       // else if("initialAudit"==strAction){
       //       window.location.href="inwSuggestions.html?strAction=initialAudit";
        // }
        // else{
        //      window.location.href="inwSuggestions.html?strAction=feasibilityAudit";
        // }
          window.history.back();
      }
      
      
</script>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
