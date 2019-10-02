<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>

<c:set var="buttons">

<c:choose>
    <c:when test="${strAction == 'addAmMessage'}">
    		<%--
    		<c:if test="${returnView=='agentAmMessage'}">
    			<input type="button" class="button" name="save22"  onclick="saveAmMessage()" value="<jecs:locale key="operation.button.save"/>" />
    		</c:if>--%>
				<input type="button" class="button" name="save33333"  onclick="submitAmMessage()" value="<jecs:locale key="operation.button.send"/>" />
    		<input type="button" class="button" name="back" onclick="toback('${param.returnView}');" value="<jecs:locale  key="operation.button.return"/>" />
    </c:when>	 
    
    <c:when test="${strAction == 'editSendAmMessage'}">
   		 <%-- 
    	  <c:if test="${returnView=='agentAmMessage'}">
    	  	<input type="button" class="button" name="save111"  onclick="saveAmMessage()" value="<jecs:locale key="operation.button.save"/>" />
    	  </c:if>--%>
				<input type="button" class="button" name="save"  onclick="disableButton();submitAmMessage()" value="<jecs:locale key="operation.button.send"/>" />
				<%--input type="button" class="button" name="save44444"  onclick="deleteAmMessage()" value="<jecs:locale key="operation.button.delete"/>" --%>
    		<input type="button" class="button" name="back" onclick="toback('${param.returnView}');" value="<jecs:locale  key="operation.button.return"/>" />
    </c:when>
    
    <c:when test="${strAction == 'editReplyAmMessage'}">
    		<input type="button" class="button" name="save"  onclick="editAmMessage()" value="<jecs:locale key="operation.button.send"/>" />	
    		<input type="button" class="button" name="back" onclick="toback('${param.returnView}');" value="<jecs:locale  key="operation.button.return"/>" />
    </c:when>
    <c:when test="${strAction == 'viewAmMessage'}"><input type="button"  name="cancel"  onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" /> 
    </c:when>
   		
    			 			
</c:choose>   			 			

	
</c:set>

<spring:bind path="amMessage.*">
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






<form:form commandName="amMessage" method="post" action="editAmMessage.html" onsubmit="checkFrm(this)" id="amMessageForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="returnView" value="${param.returnView}"/>
	





<form:hidden path="uniNo"/>
<form:hidden path="status"/>
<form:hidden path="companyCode"/>
<form:hidden path="senderNo"/>




<div data-role="fieldcontain">  
           <jecs:label  key="amMessage.msgClassNo"/>
       
        <c:choose>
    		<c:when test="${strAction != 'viewAmMessage'}">	
        		<jecs:list listCode="msgclassno" name="msgClassNo" id="msgClassNo" value="${amMessage.msgClassNo}" defaultValue=""/>
        	</c:when>
        	<c:otherwise>
        		<jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/>
          	</c:otherwise>
         </c:choose>
</div> 



<div data-role="fieldcontain">  
           <jecs:label  key="amMessage.subject"/>
         <form:input path="subject" disabled="${sendDisabled}" id="subject" size="90" />
</div> 



<div data-role="fieldcontain">  
          <jecs:label  key="amMessage.content"/>
       <c:choose>
		    		<c:when test="${!sendDisabled}">
		    					<FCK:editor instanceName="content" toolbarSet="simpleBar">
											<jsp:attribute name="value">${amMessage.content}</jsp:attribute>
									</FCK:editor>
		    		</c:when>
       			<c:otherwise>
		    			${amMessage.content}
		    		</c:otherwise>
       		</c:choose>
        	
</div> 


<c:if test="${strAction == 'viewAmMessage'}">
<div data-role="fieldcontain">  
           <jecs:label  key="amMessage.replyContent"/>
		   ${amMessage.replyContent}
</div> 

</c:if>



<div id="titleBar" class="searchBar">
					<c:out value="${buttons}" escapeXml="false"/>						
</div>



</form:form>


<script type="text/javascript">

    
    function deleteAmMessage(){
    		$('strAction').value='deleteAmMessage';
    		$('amMessageForm').submit();
    	}
    function saveAmMessage(){
    			$('#status').value="-1"
    			
    			$('#amMessageForm').submit();
    	}
    	
    function submitAmMessage(){
		var status=document.getElementById('status');
		status.value="0";
		var amMessageForm=document.getElementById('amMessageForm');
			amMessageForm.submit();
   	}
    	
  	function editAmMessage(){
  		$('amMessageForm').submit();
  	}
    		
    function editAmMessageOld(){
    			 <c:choose>
    			 
    			 		<c:when test="${flag ==-1}">
    			 			$('status').value="0";
    			 			$('strAction').value="addAmMessage";
    			 			$('amMessageForm').submit();
    			 		</c:when>
    			 		<c:when test="${flag ==0}">
    			 			$('status').value="0"
    			 			$('strAction').value="sendAmMessage";
    			 			$('amMessageForm').submit();
    			 		</c:when>
    			 		<c:when test="${flag ==1}">
    			 			$('status').value="1"
    			 			$('amMessageForm').submit();
    			 		</c:when>
    			 		<c:when test="${flag ==2}">
    			 			$('status').value="2"
    			 			$('amMessageForm').submit();
    			 		</c:when>
    			 		<c:when test="${flag ==3}">
    			 			$('status').value="3"
    			 			$('amMessageForm').submit();
    			 		</c:when>
    			 		<c:otherwise>
    			 			this.location="<c:url value="/amMessages.html"/>?strAction=searchAmMessage";
    			 		</c:otherwise>
    			 </c:choose>
    	
    	}
    	
    	function searchUser(){
    			var userCodeExample = $('agentNo').value;
    			////open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample);
    			
    			$('agentNo').value=ret['userCode'];
    			$('agentName').value=ret['userName'];
    			
    			//window.open("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,"","height=400, width=600, top=180, left=250, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    			
    		}
    	
    	function searchRegularMsg(){    		
    		window.open("<c:url value='/amRegularMsgs.html'/>?strAction=regularMsgSelect","","height=400, width=600, top=180, left=250, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
    	
    	function toback(str){
				
    		this.location='<c:url value="/amMessages.html"/>?strAction='+str;
    	}
    	
    	function view(){
    			//var   win=   window.dialogArguments;
    			//win.location.reload();
    			this.close();
    			reloadWin();	//window.opener.location.reload();
    		}
    		function closeMe(){
    				this.close();
    			}
    			
    			
    	function checkFrm(frm){
    		//return validateAmMessage(this)
    		
    		var tag = validateAmMessage(frm);
    		
    		if(!tag)ableButton();
    		
    		return 	tag;
    	}
    	function discussAmMessage(uniNo){
			var pars=new Array();
			pars[0]="<jecs:locale key='amMessage.discuss'/>";
			pars[1]="editAmMessageDiscuss.html?strAction=editAmMessageDiscuss&uniNo=" + uniNo;
			pars[2]=window;
			var ret=showDialog("<%=request.getContextPath()%>",pars,510,250,1);

		}
</script>

<v:javascript formName="amMessage" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>




</html>