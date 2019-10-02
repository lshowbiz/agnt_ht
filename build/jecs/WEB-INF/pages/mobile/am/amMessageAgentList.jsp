<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>
	
	
		<jecs:power powerCode="addAmMessage">
<input name="search" class="button" type="button"  onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=agentAmMessage'" value="<jecs:locale key="amMessage.sendmsg"/>" />
		</jecs:power>

<ul data-role="listview" style="margin: 0px;"  >



<c:forEach items="${amMessageList }" var="amMessage">
	
  <li>

  				 				<a href="editAmMessage.html?strAction=viewAmMessage&uniNo=${amMessage.uniNo}" rel="external">



    <h3>${amMessage.subject}</h3>
    <p><jecs:title key="amMessage.msgClassNo"  /><jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/>
    </p>
    <p><jecs:title key="amMessage.senderNo"  />${amMessage.senderNo}
    </p>
    <p><jecs:title key="amMessage.issueTime"  />${amMessage.issueTime}
    </p>
    <p><jecs:title key="amMessage.receiverNo"  />${amMessage.receiverNo}
    </p>
    <p><jecs:title key="amMessage.replyTime"  />${amMessage.replyTime}
    </p>
    <p><jecs:title key="customerRecord.state"  /><c:choose>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==3)}">
  				 				<font color ='red'><jecs:locale  key='amMessage.newRepliedMsg'/><font color ='red'>
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='0') && (amMessage.status ==3)}">
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==-1)}">
  				 				<img  src="images/icons/sendmsg.gif" border="0" align="absmiddle" style="cursor:pointer"
 									title="<jecs:locale  key='operation.button.send'/>" alt="<jecs:locale  key='operation.button.send'/>"
 											onclick="sendMessage('${amMessage.uniNo}');"/>
  				 		</c:when>
  				 		<c:when test="${amMessage.status >=4}">
   								
  				 		</c:when>
  				 		<c:otherwise>
  							 <font color ='red'><jecs:locale  key="ammessage.noreplynum"/></font>
  						</c:otherwise>
  				</c:choose>
    </p>
	
    	
  </a></li>
  

</c:forEach>


  
</ul>

</html>
