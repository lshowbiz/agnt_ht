<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<title><jecs:locale key="amMessageDetail.title"/></title>
<content tag="heading"><jecs:locale key="amMessageDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 



<script   src='<c:url value="/ckeditor/ckeditor.js" />' ></script>


<c:set var="buttons">

<c:choose>
    <c:when test="${strAction == 'addAmMessage'}">
    		
    		
    		<button type="button" class="btn btn_sele" style="width:auto" name="save"  onclick="disableButton();submitAmMessage()"><jecs:locale key="operation.button.send"/></button>
    		<button type="button" class="btn btn_sele" style="width:auto" name="back" onclick="toback('${param.returnView}');"><jecs:locale  key="operation.button.return"/></button>
    </c:when>	 
    
    <c:when test="${strAction == 'editSendAmMessage'}">
   		
			<button type="button" class="btn btn_sele" style="width:auto" name="save"  onclick="disableButton();submitAmMessage()"><jecs:locale key="operation.button.send"/></button>
    		<button type="button" class="btn btn_sele" style="width:auto" name="back" onclick="toback('${param.returnView}');"><jecs:locale  key="operation.button.return"/></button>
    </c:when>
    
    <c:when test="${strAction == 'editReplyAmMessage'}">
    	
    	<button type="button" class="btn btn_sele" style="width:auto" name="save"  onclick="editAmMessage()"><jecs:locale key="operation.button.send"/></button>	
    	<button type="button" class="btn btn_sele" style="width:auto" name="back" onclick="toback('${param.returnView}');"><jecs:locale  key="operation.button.return"/></button>
    </c:when>
    <c:when test="${strAction == 'viewAmMessage'}">
    	
    	<button type="button" class="btn btn_sele" style="width:auto" name="save"  onclick="view()"><jecs:locale key="operation.button.close"/></button>
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


<div id="titleBar" class="searchBar">

	<%--   	<c:out value="${buttons}" escapeXml="false"/>		--%>			
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="returnView" value="${param.returnView}"/>
													                           
<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmMessage')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />

</legend-->


<form:hidden path="uniNo"/>
<form:hidden path="status"/>
<form:hidden path="companyCode"/>
<form:hidden path="senderNo"/>	
	<!--fieldset  style="${sendDivDisplay}">
		<legend> send field</legend-->
		
		<!--��������-->
<table id="sendTable" style="${sendDivDisplay}" class='detail' width="70%">
	<tbody class="window">
		 <c:choose>
		 	<c:when test="${sessionScope.sessionLogin.isCompany || sessionScope.sessionLogin.isManager }">
		 		<tr class="edit_tr">
	    			<th>
	        			<span class="text-font-style-tc"><jecs:label  key="pd.agentormember"/></span>
			    	</th>
			   		 <td>
			       
				        <form:input path="agentNo"   disabled="${sendDisabled}" id="agentNo"  cssClass="text-meduim"  cssStyle="height:22px;width:160px;"/>
				        <img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
				        <input type="text" class="text-medium" name="agentName" disabled="${sendDisabled}" id="agentName" value="${amMessage.senderName}" style="height:22px;width:80px;"/>
					</td>
					
					<th>
			        	<span class="text-font-style-tc"><jecs:label  key="amMessage.msgClassNo"/></span>
			    	</th>
				    <td>
				        
				        <c:choose>
				    		<c:when test="${strAction != 'viewAmMessage'}">	
				        		 <span class="textbox"><jecs:list listCode="msgclassno" styleClass="textbox-text" name="msgClassNo" id="msgClassNo" value="${amMessage.msgClassNo}" defaultValue=""/></span>
				        	</c:when>
				        	<c:otherwise>
				        		<span class="textbox"><span class="textbox-text"><jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/></span></span>
				          	</c:otherwise>
				         </c:choose>
				    </td>
				</tr>
		 	</c:when>
		 	<c:otherwise>
		 		<tr class="edit_tr">
			    	<th>
			        	<span class="text-font-style-tc"><jecs:label  key="amMessage.msgClassNo"/></span>
			    	</th>
				    <td>
				        
				        <c:choose>
				    		<c:when test="${strAction != 'viewAmMessage'}">	
				        		 <span class="textbox"><jecs:list listCode="msgclassno" styleClass="textbox-text" name="msgClassNo" id="msgClassNo" value="${amMessage.msgClassNo}" defaultValue=""/></span>
				        	</c:when>
				        	<c:otherwise>
				        		<span class="textbox"><span class="textbox"><jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/></span></span>
				          	</c:otherwise>
				         </c:choose>
				    </td>
		 		</tr>
		 	</c:otherwise>
		 </c:choose>
		<tr class="edit_tr">
	    	<th>
	        	<span class="text-font-style-tc"><jecs:label  key="amMessage.subject"/></span>
	   		 </th>
	   		 <td colspan="3">
	        <!--form:errors path="subject" cssClass="fieldError"/-->
	        	<form:input path="subject" cssClass="textbox-text" disabled="${sendDisabled}" id="subject" size="90" cssStyle="height:22px;"/>
	   		 </td>
	   	</tr>

<%-- 	    
	    <c:if test="${sessionScope.sessionLogin.isCompany || sessionScope.sessionLogin.isManager }">
	    		<tr class="edit_tr">
	    			<th>
	        			<span class="text-font-style-tc"><jecs:label  key="pd.agentormember"/></span>
			    	</th>
			    <td>
			       
			        <span class="textbox"><form:input path="agentNo"   disabled="${sendDisabled}" id="agentNo"  cssClass="textbox-text"/></span>
			        <img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
			        <span class="textbox"><input type="text" class="textbox-text" name="agentName" disabled="${sendDisabled}" id="agentName" value="${amMessage.senderName}"/></span>
					</td>
				</tr>
				
				
	    </c:if>
	    
	--%>    
	   
	    
	    
	    <%--
	    	<tr class="edit_tr">
				
		<th>
			<span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
		</th>
		<td  colspan="3">
		    <span class="text-font-style-tc-right"><form:textarea  cssClass="textarea_border" path="remark" id="remark"   /></span>
			
		</td>
	</tr>
	    
	     --%>
	    <tr class="edit_tr">
	    
		    <th>
				<span class="text-font-style-tc" style="border:0"><jecs:label  key="amMessage.content"/></span>
		    </th>
		    <td <c:if test="${!sendDisabled}">colspan="2"</c:if>>
		        <!--form:errors path="content" cssClass="fieldError"/-->
		        <c:choose>
				    		<c:when test="${!sendDisabled}">
				    					<%-- <FCK:editor instanceName="content" toolbarSet="simpleBar">
													<jsp:attribute name="value">${amMessage.content}</jsp:attribute>
											</FCK:editor> --%>
											
					    							<textarea id="content" name="content">${amMessage.content }</textarea>
						    							<script type="text/javascript">
															CKEDITOR.replace( 'content' );
														</script>
				    		</c:when>
		       			<c:otherwise>
				    			${amMessage.content}
				    		</c:otherwise>
		       		</c:choose>
		        	
		    </td>
	    </tr>
	    
	    
	    
	    
	    
	  <c:if test="${strAction == 'editReplyAmMessage' || strAction == 'viewAmMessage' }">
	    	<jecs:power powerCode="regularMsgSelect">
		    	<c:if test="${strAction == 'editReplyAmMessage'}">
			    	<tr class="edit_tr">
			    		<th>
			        		<span class="text-font-style-tc" style="border:0"><jecs:label  key="ammessage.regularmsg"/></span>
					    </th>
					    <td colspan="3">
					    		
					      <img src="<c:url value="/images/regularmsg.gif"/>" id="person"  onclick="searchRegularMsg()" style="cursor: pointer;" title="<jecs:locale key="sys.user.searchRegular"/>"/>
					     </td>
					</tr>
				</c:if>
			</jecs:power>
			
			<c:if test="${strAction != 'viewAmMessage'}">
	    		<tr class="edit_tr">
	    			<th>
	        			<span class="text-font-style-tc" style="border:0"><jecs:label  key="ammessage.recently"/></span>
			    	</th>
			    	<td colspan="2">
			    	<c:forEach items="${amMessageRecents }" var="amMessageRecent">
			    		
			    		<table width='100%' cellpadding='6' cellspacing='6' border='0'>
							<tr>
								<td valign='top' width='50%' bgcolor='#FFEEAA' style='border: 1px solid #BED5F0;'>
	    				<font color='#888888'><jecs:locale  key="amMessage.senderNo"/>:</font>
	    					<font color='blue'>${amMessageRecent.senderNo }&nbsp;/&nbsp;</font>&nbsp;&nbsp;
	    					<font color='blue'>${amMessageRecent.issueTime }</font><br/>
	    				<br/>  
	    				<p>${amMessageRecent.content }</p>
	    					</td>
	    			
							<td valign='top' width='100%' bgcolor='#FFFFCC' style='border: 1px solid #BED5F0;'>
	 				 			<font color='#888888'><jecs:locale  key="amMessage.receiverNo"/>:
	 				 			${amMessageRecent.receiverNo }&nbsp;/&nbsp;
	 				 			</font>&nbsp;&nbsp;${amMessageRecent.replyTime }
	 				 			${amMessageRecent.replyContent }
	 							<br/>
	  							</td>
	    				</tr>
	    				
	    				</table>
			    		
			    	</c:forEach>
			       	
			       	 
					</td>
				</tr>
				</c:if>
	    		<tr class="edit_tr">
	    			<th>
	    				<c:choose>
	    					<c:when test="${!replyDisabled}">
			        			<span class="text-font-style-tc" style="border:0"><jecs:label  key="amMessage.replyContent"/></span>
	    					</c:when>
	    					<c:otherwise>
	    						<jecs:label  key="amMessage.replyContent"/>
	    					</c:otherwise>
	    				</c:choose>
			   		 </th>
			    	<td <c:if test="${!replyDisabled}">colspan="2"</c:if>>
				    	<c:choose>
				    		
				    		<c:when test="${!replyDisabled}">
				    					<%-- <FCK:editor instanceName="replyContent" toolbarSet="simpleBar">
													<jsp:attribute name="value">${amMessage.replyContent}</jsp:attribute>
											</FCK:editor> --%>
					    							<textarea id="replyContent" name="replyContent">${amMessage.replyContent }</textarea>
						    							<script type="text/javascript">
															CKEDITOR.replace( 'replyContent' );
														</script>
				    		</c:when>
				    		<c:otherwise>
				    			${amMessage.replyContent}
				    		</c:otherwise>
				    	</c:choose>
			        
			    	</td>
			    </tr>
			    <tr class="edit_tr">
			    	<th>
			    		<span class="text-font-style-tc" style="border:0"><jecs:label  key='amMessage.discuss'/></span>
			    	</th>
				    <td>
					    <c:if test="${sessionScope.sessionLogin.userType=='M'}">
						    	<c:if test="${empty amMessage.discuss && amMessage.status==9 }">
				  					<img src="images/icons/bookmark.png"><a href="#" onclick="discussAmMessage('${amMessage.uniNo}');"><jecs:locale  key='amMessage.discuss.onclick'/></a>
				  				</c:if>
					    </c:if>
				    </td>
			    </tr>
	</c:if>
    	
    	<%-- 按钮 --%>
    	<tr>		
		    		<td class="command" colspan="4" align="center">
		    			<c:out value="${buttons}" escapeXml="false"/>
		    	
		    		</td>
	   			 </tr>		
    	
    </tbody>
   </table>
<!--��������  end  -->

		<table id="controlTable" style="display:none" class='detail' width="100%">
    		<tr><th width="20%">
        <jecs:label  key="amMessage.receiverNo"/>
		    </th>
		    <td align="left" width="80%">
		        <!--form:errors path="receiverNo" cssClass="fieldError"/-->
		        <form:input path="receiverNo" id="receiverNo"  disabled="${!controlFlag}" cssClass="text medium"/>
		    </td></tr>
		</table>
 
 <!--�ظ�����-->
 
 <!--  
		<table id="replayTable" style="${replyDivDisplay}" class='detail' width="70%">
			<tbody class="window">
	    		<jecs:power powerCode="regularMsgSelect">
		    		<c:if test="${strAction == 'editReplyAmMessage'}">
			    		<tr class="edit_tr">
			    			<th>
			        			<jecs:label  key="ammessage.regularmsg"/>
					    	</th>
					    	<td colspan="3">
					    		
					       	 <img src="<c:url value="/images/regularmsg.gif"/>" id="person"  onclick="searchRegularMsg()" style="cursor: pointer;" title="<jecs:locale key="sys.user.searchRegular"/>"/>
					       	 	
							</td>
						</tr>
					</c:if>
				</jecs:power>
				
				<c:if test="${strAction != 'viewAmMessage'}">
	    		<tr class="edit_tr">
	    			<th>
	        			<jecs:label  key="ammessage.recently"/>
			    	</th>
			    	<td>
			    	<c:forEach items="${amMessageRecents }" var="amMessageRecent">
			    		
			    		<table width='100%' cellpadding='6' cellspacing='6' border='0'>
							<tr>
								<td valign='top' width='50%' bgcolor='#FFEEAA' style='border: 1px solid #BED5F0;'>
	    				<font color='#888888'><jecs:locale  key="amMessage.senderNo"/>:</font>
	    					<font color='blue'>${amMessageRecent.senderNo }&nbsp;/&nbsp;</font>&nbsp;&nbsp;
	    					<font color='blue'>${amMessageRecent.issueTime }</font><br/>
	    				<br/>  
	    				<p>${amMessageRecent.content }</p>
	    					</td>
	    			
							<td valign='top' width='100%' bgcolor='#FFFFCC' style='border: 1px solid #BED5F0;'>
	 				 			<font color='#888888'><jecs:locale  key="amMessage.receiverNo"/>:
	 				 			${amMessageRecent.receiverNo }&nbsp;/&nbsp;
	 				 			</font>&nbsp;&nbsp;${amMessageRecent.replyTime }
	 				 			${amMessageRecent.replyContent }
	 							<br/>
	  							</td>
	    				</tr>
	    				
	    				</table>
			    		
			    	</c:forEach>
			       	
			       	 
					</td>
				</tr>
				</c:if>
	    		<tr class="edit_tr">
	    			<th>
	        			<jecs:label  key="amMessage.replyContent"/>
			   		 </th>
			    	<td>
			    	<c:choose>
			    		
			    		<c:when test="${!replyDisabled}">
			    					<%-- <FCK:editor instanceName="replyContent" toolbarSet="simpleBar">
												<jsp:attribute name="value">${amMessage.replyContent}</jsp:attribute>
										</FCK:editor> --%>
				    							<textarea id="replyContent" name="replyContent">${amMessage.replyContent }</textarea>
					    							<script type="text/javascript">
														CKEDITOR.replace( 'replyContent' );
													</script>
			    		</c:when>
			    		<c:otherwise>
			    			${amMessage.replyContent}
			    		</c:otherwise>
			    	</c:choose>
			        
			        
			    </td></tr>
			    <tr class="edit_tr">
			    	<th>
			    		<jecs:label  key='amMessage.discuss'/>
			    	</th>
				    <td>
					    <c:if test="${sessionScope.sessionLogin.userType=='M'}">
						    	<c:if test="${empty amMessage.discuss && amMessage.status==9 }">
				  					<img src="images/icons/bookmark.png"><a href="#" onclick="discussAmMessage('${amMessage.uniNo}');"><jecs:locale  key='amMessage.discuss.onclick'/></a>
				  				</c:if>
					    </c:if>
				    </td>
			    </tr>
			  </tbody>
    	</table>
  -->  	
    	
	
		<!--�ظ����� end -->
 
</form:form>

<script type="text/javascript">
	window.onunload = reloadWin;
	function reloadWin(){
		window.opener.location.reload();
	}
    Form.focusFirstElement($('amMessageForm'));
    
    function deleteAmMessage(){
    		$('strAction').value='deleteAmMessage';
    		$('amMessageForm').submit();
    	}
    function saveAmMessage(){
    			$('status').value="-1"
    			
    			$('amMessageForm').submit();
    	}
    	
    function submitAmMessage(){
		$('status').value="0"
		//if(validateAmMessage($('amMessageForm')))
			$('amMessageForm').submit();
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
    	
    	function reply(){
    				var   win=   window.dialogArguments;
    				win.location.reload();
    				this.close();
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
