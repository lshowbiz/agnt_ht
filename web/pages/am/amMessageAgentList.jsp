<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amMessageList.title"/></title>
<content tag="heading"><jecs:locale key="amMessageList.heading"/></content>
<meta name="menu" content="AmMessageMenu"/>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/amMessageManager.js'/>" ></script>



<c:set var="buttons">
	<input type="button" class="button" style="margin-right: 5px"
		onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=agentAmMessage'"
			value="<jecs:locale key="member.new.num"/>"/>
</c:set>

<form id="frm" name="frm" action="<c:url value='/amMessages.html'/>" >
	<input type="hidden" name="strAction" id="strAction" value="${strAction}"/>
	<input type="hidden" name="returnView" id="returnView" value="agentAmMessage"/>
	
<div class="searchBar">&nbsp;&nbsp;
	<div class="new_searchBar">
			<jecs:title key="amMessage.state"/>
			<jecs:list listCode="ammessage.send" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>	
	</div>
	<div class="new_searchBar">
			<jecs:title key="amMessage.status"/>
			<jecs:list listCode="ammessage.agentstatus" name="status" id="status" showBlankLine="true" value="${amMessageExample.status}" defaultValue=""/>
	</div>
	<div class="new_searchBar">
    		<jecs:title key="amMessage.msgClassNo"/>
      		<jecs:list listCode="msgclassno" name="msgClassNo" id="msgClassNo" showBlankLine="true" value="${amMessageExample.msgClassNo}" defaultValue=""/>
     </div>
     <div class="new_searchBar">
     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
        	
        	<jecs:power powerCode="addAmMessage">
		<button name="search" class="btn btn_ins" style="width:auto" type="button" onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=agentAmMessage'">
			<jecs:locale key="amMessage.sendmsg"/>
		</button>
		</jecs:power>
	</div>
	<div style="clear:both; margin-top:5px; margin-bottom:10px;margin-right:180px;text-align:right;">
		
				<jecs:title  key="ammessage.agentstatus.1"/>
					<font color="red">${noreadNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
			&nbsp;&nbsp;
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
	</div>			
		

</div>
<!--  
<div class="searchBar">&nbsp;&nbsp;

			<jecs:title key="amMessage.state"/>
			<jecs:list listCode="ammessage.send" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>	
			<jecs:title key="amMessage.status"/>
			<jecs:list listCode="ammessage.agentstatus" name="status" id="status" showBlankLine="true" value="${amMessageExample.status}" defaultValue=""/>
    		<jecs:title key="amMessage.msgClassNo"/>
      		<jecs:list listCode="msgclassno" name="msgClassNo" id="msgClassNo" showBlankLine="true" value="${amMessageExample.msgClassNo}" defaultValue=""/>
        	<input type="submit" class="button"   value="<jecs:locale  key='operation.button.search'/>"/>
				<jecs:title  key="ammessage.agentstatus.1"/>
					<font color="red">${noreadNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
				
		<jecs:power powerCode="addAmMessage">
		<input name="search" class="button" type="button" onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=agentAmMessage'" value="<jecs:locale key="amMessage.sendmsg"/>" />
		</jecs:power>

</div>
-->
<%--<table width="100%" border="0" id="searchTable" >
	<tr>
		<th>			
    	</th>
		<th>			
    	</th>
    	<th>
    	</th>
    	<th>
        	<jecs:locale  key="operation.button.search"/>		
		</th>
		<th>&nbsp;
		</th>
	</tr>
	<tr>
		<td width="400px">
				<input type='radio' name='reply_status' value='1' 
					<c:if test="${param.reply_status ==1 }">checked</c:if> onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/globe_16.gif" border="0" align="absmiddle"/>
				<input type='radio' name='reply_status' value='2' 
					<c:if test="${param.reply_status ==2 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/icons/pm_replied.gif" border="0" <c:if test=""></c:if> align="absmiddle"/>
				<jecs:title  key="ammessage.agentstatus.1"/>
					<font color="red">${noreadNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
				
				<input type='radio' name='reply_status' value='3' 
					<c:if test="${param.reply_status ==3 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"/>
				<jecs:title  key="ammessage.noreply"/>
					<font color="red">${noReplyNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
				
				<input type='radio' name='reply_status' value='4'
					<c:if test="${param.reply_status ==4 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/letter_16.gif" border="0" align="absmiddle"/>
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
		</td>
		<td width='70px'>			
    	</td>
    	<td width='90px'>				
    	</td>
    	<td width="50px">
		</td>
		<td>&nbsp;
		</td>
	</tr>
</table>--%>


<%--<table class='grid_table'>
	<tr class='grid_span'>
		<td colspan='10'>
			<jecs:power powerCode="addAmMessage">
			<span onclick="location.href=''" style="cursor:pointer">
				<img title="<jecs:locale  key='amMessage.sendmsg'/>" alt="<jecs:locale  key='amMessage.sendmsg'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='amMessage.sendmsg'/></font>
			</span>
			</jecs:power>
		</td>
</tr>
</table>--%>

<%--<ec:table 
	tableId="amMessageListTable"
	items="amMessageList"
	var="amMessage"
	action="${pageContext.request.contextPath}/amMessages.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit" style="word-break:break-all"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
	<ec:row highlightRow="false">
 			
 			<ec:column property="aa" sortable="false" title=" " style="width:3%">
 				<c:if test="${amMessage.status>=4}">
 					<img  src="images/icons/pm_replied.gif" border="0" align="absmiddle"/>
 				</c:if>
 				<c:if test="${amMessage.status<4}">
 					<img  src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"/>
 				</c:if>
 			</ec:column>
 			
 			<ec:column property="issueTime" title="amMessage.content" style="valign:center;">
 				
 				<img src="images/icons/tree_star.gif" border="0" align="absmiddle">
 				<jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/>
 				<jecs:locale  key="amMessage.subject"/>:
 					<font color='blue'>${amMessage.subject}</font>
 					
 				<c:if test="${(amMessage.sendRoute =='1') && (amMessage.status <=0 )}">
 					<img  src="images/newIcons/pencil_16.gif" border="0" align="absmiddle"  style="cursor:pointer"
 						title="<jecs:locale  key="amMessage.editItem"/>" alt="<jecs:locale  key="amMessage.editItem"/>"
 						onclick="editAmMessage('${amMessage.uniNo}','editSendAmMessage');"/>
 					
 					<img  src="images/newIcons/delete_16.gif" border="0" align="absmiddle" style="cursor:pointer"
 						title="<jecs:locale  key="amMessage.delItem"/>" alt="<jecs:locale  key="amMessage.delItem"/>"
 						onclick="delMsg('${amMessage.uniNo}')"/>
 				
 				</c:if>
 					
 					
 				<br/>
 				
 				
 				<table width='100%' cellpadding='6' cellspacing='6' border='0'>
			<tr>
				<td valign='top' width='50%' bgcolor='#FFEEAA' style='border: 1px solid #BED5F0;'>
 				<font color='#888888'><jecs:locale  key="amMessage.senderNo"/>:</font>
 					<font color='blue'>${amMessage.senderNo}<c:if test="${amMessage.senderName!=''}">&nbsp;/&nbsp;</c:if></font>&nbsp;&nbsp;
 				<!-- <font color='#888888'><jecs:locale  key="amMessage.issueTime"/>:</font> -->
 					<font color='blue'>${amMessage.issueTime}</font><br/>
 				<br/>  
 				${amMessage.content}
 						
 				
 					</td>
 					
 					
			<td valign='top' width='100%' bgcolor='#FFFFCC' style='border: 1px solid #BED5F0;'>
			<!-- 					
  				<c:if test="${(amMessage.sendRoute =='1') && (amMessage.status ==-1)}">
  					<input type="button" class='form_button' onclick="editAmMessage('${amMessage.uniNo}','editSendAmMessage');" value="<jecs:locale  key='button.edit'/>"/><br/>
  				</c:if> -->
  				<c:choose>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==3)}">
  				 				<img  src="images/newIcons/search_16.gif" border="0" align="absmiddle"  style="cursor:pointer"
 									title="<jecs:locale  key="amMessage.view"/>" alt="<jecs:locale  key="amMessage.view"/>"
 										onclick="viewAmMessage('${amMessage.uniNo}');"/>
  				 				<!-- <input type="button" class='form_button' onclick="viewAmMessage('${amMessage.uniNo}');" value="<jecs:locale  key='amMessage.view'/>"/> -->
  				 				<br/>
  				 				<font color ='red'><jecs:locale  key='amMessage.newRepliedMsg'/><font color ='red'>
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='0') && (amMessage.status ==3)}">
  				 				<img  src="images/icons/replymsg.gif" border="0" align="absmiddle"  style="cursor:pointer"
 									title="<jecs:locale  key="amMessage.reply"/>" alt="<jecs:locale  key="amMessage.reply"/>"
 										onclick="editAmMessage('${amMessage.uniNo}','editReplyAmMessage');"/>
  				 				<!-- <input type="button" class='form_button' onclick="editAmMessage('${amMessage.uniNo}','editReplyAmMessage');" value="<jecs:locale  key='amMessage.reply'/>"/> -->
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==-1)}">
  				 				<img  src="images/icons/sendmsg.gif" border="0" align="absmiddle" style="cursor:pointer"
 									title="<jecs:locale  key='operation.button.send'/>" alt="<jecs:locale  key='operation.button.send'/>"
 											onclick="sendMessage('${amMessage.uniNo}');"/><!-- checkAmMessage('${amMessage.uniNo}') -->
  				 				<!-- <input type="button" class='form_button' onclick="sendMessage('${amMessage.uniNo}');" value="<jecs:locale  key='operation.button.send'/>"/> -->
  				 		</c:when>
  				 		<c:when test="${amMessage.status >=4}">
  				 			
  				 			<!-- ${amMessage.receiverNo}<c:if test="${amMessage.receiverName!=''}">&nbsp;/&nbsp;</c:if> -->
  							<!-- <font color='#888888'><jecs:locale  key="amMessage.replyTime"/>:</font>
  							 -->
  							${amMessage.replyTime}
  							<br/>
  							<!-- <font color='red'><b>[</b></font><jecs:locale  key="amMessage.replyContent"/>:<font color='red'><b>]</b></font> -->
  							
  							${amMessage.replyContent}
   								
  				 		</c:when>
  				 		<c:otherwise>
  							 <font color ='red'><jecs:locale  key="ammessage.noreplynum"/></font>
  						</c:otherwise>
  				</c:choose>
 				
 						</td>
 				</tr>
 				
 				</table>
 				
 			</ec:column>
 			
 			
	</ec:row>

</ec:table>--%>	




<ec:table 
	tableId="amMessageListTable"
	items="amMessageList"
	var="amMessage"
	action="${pageContext.request.contextPath}/amMessages.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit" style="word-break:break-all"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
	<ec:row highlightRow="false"  onclick="showTR($('tr${ROWCOUNT}'));">
 			
 			<ec:column property="aa" sortable="false" title=" " style="width:3%">
 				<c:if test="${amMessage.status>=4}">
 					<img  src="images/icons/pm_replied.gif" border="0" align="absmiddle"/>
 				</c:if>
 				<c:if test="${amMessage.status<4}">
 					<img  src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"/>
 				</c:if>
 			</ec:column>
 			
 			
 			
			<ec:column property="subject" title="amMessage.subject" style="white-space:nowrap;">
				<jecs:substr key="${amMessage.subject }" length="20"/> 
			</ec:column>
			
			<c:set var="msgClassNo" scope="request"><jecs:code listCode="msgclassno" value="${amMessage.msg_class_no}"/></c:set>
			<ec:column property="msgClassNo" title="amMessage.msgClassNo" style="white-space:nowrap;">
				<!-- <jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/> -->
				<jecs:substr key="${requestScope.msgClassNo }" length="5"/>
   			</ec:column>
			
			<ec:column property="senderNo" title="amMessage.senderNo" style="white-space:nowrap;"/>
			<ec:column property="issueTime" title="amMessage.issueTime" style="white-space:nowrap;"/>
			
			<ec:column property="receiverNo" title="amMessage.receiverNo" style="white-space:nowrap;"/>
			
			<ec:column property="replyTime" title="amMessage.replyTime" style="white-space:nowrap;"/>
			
			<ec:column property="123" title="customerRecord.state" sortable="false" style="white-space:nowrap;">
				<c:choose>
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
			</ec:column>
 			
 			
			<ec:column property="1" title="title.operation" sortable="false" style="white-space:nowrap;">
			 	<c:if test="${(amMessage.sendRoute =='1') && (amMessage.status <=0 )}">
 					<img  src="images/newIcons/pencil_16.gif" border="0" align="absmiddle"  style="cursor:pointer"
 						title="<jecs:locale  key="amMessage.editItem"/>" alt="<jecs:locale  key="amMessage.editItem"/>"
 						onclick="editAmMessage('${amMessage.uniNo}','editSendAmMessage');"/>
 					<img  src="images/newIcons/delete_16.gif" border="0" align="absmiddle" style="cursor:pointer"
 						title="<jecs:locale  key="amMessage.delItem"/>" alt="<jecs:locale  key="amMessage.delItem"/>"
 						onclick="delMsg('${amMessage.uniNo}')"/>
 				</c:if>
 				<c:choose>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==3)}">
  				 				<img  src="images/newIcons/search_16.gif" border="0" align="absmiddle"  style="cursor:pointer"
 									title="<jecs:locale  key="amMessage.view"/>" alt="<jecs:locale  key="amMessage.view"/>"
 										onclick="viewAmMessage('${amMessage.uniNo}');"/>
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='0') && (amMessage.status ==3)}">
  				 				<img  src="images/icons/replymsg.gif" border="0" align="absmiddle"  style="cursor:pointer"
 									title="<jecs:locale  key="amMessage.reply"/>" alt="<jecs:locale  key="amMessage.reply"/>"
 										onclick="editAmMessage('${amMessage.uniNo}','editReplyAmMessage');"/>
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==-1)}">
  				 				<img  src="images/icons/sendmsg.gif" border="0" align="absmiddle" style="cursor:pointer"
 									title="<jecs:locale  key='operation.button.send'/>" alt="<jecs:locale  key='operation.button.send'/>"
 											onclick="sendMessage('${amMessage.uniNo}');"/>
  				 		</c:when>
  				 		<%--<c:when test="${amMessage.status >=4}">
  				 			
  				 			
  							${amMessage.replyTime}
  							
  							${amMessage.replyContent}
   								
  				 		</c:when>
  				 		<c:otherwise>
  							 <font color ='red'><jecs:locale  key="ammessage.noreplynum"/></font>
  						</c:otherwise>--%>
  				</c:choose>
  				<c:if test="${empty amMessage.discuss && amMessage.status==9 }">
  					<img  src="images/newIcons/discuss.png" border="0" align="absmiddle" style="cursor:pointer"
 									title="<jecs:locale  key='amMessage.discuss'/>" alt="<jecs:locale  key='amMessage.discuss'/>"
 											onclick="discussAmMessage('${amMessage.uniNo}');"/>
  				</c:if>
  				
			</ec:column>
 			
 			
 			
	</ec:row>

		
						
						
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
							
							
							<td colspan="9">
							<table width='100%' border="0" cellpadding="0" cellspacing="0"><tr><td  bgcolor='#FFEEAA' width='60%'>${amMessage.content}
							<br/>
  				<c:if test="${empty amMessage.discuss && amMessage.status==9 }">
  					<img src="images/newIcons/navigation.gif"><a href="#" onclick="discussAmMessage('${amMessage.uniNo}');"><jecs:locale  key='amMessage.discuss.onclick'/></a>
  				</c:if>
							</td>
							<td  bgcolor='#FFFFCC'  width='40%'><c:choose>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==3)}">
  				 				
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='0') && (amMessage.status ==3)}">
  				 		</c:when>
  				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status ==-1)}">
  				 				
  				 		</c:when>
  				 		<c:when test="${amMessage.status >=4}">
  				 			
  				 			
  							
  							${amMessage.replyContent}
   								
  				 		</c:when>
  				 		<c:otherwise>
  				 		
  						</c:otherwise>
  				</c:choose>
  				</td></tr></table>
							
				</c:if>
						
						
</ec:table>	
</form>

<form name="form1" action="<c:url value='editAmMessage.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='uniNo' id='uniNo'/>
</form>

<script type="text/javascript">

   function editAmMessage(uniNo,strAction){
   		
			window.location="editAmMessage.html?returnView=agentAmMessage&strAction="+strAction+"&uniNo="+uniNo;
			
		}
		function sendMessage(uniNo){
				amMessageManager.agentSendMessage(uniNo);
				//window.location.reload();
				window.location="<c:url value='/amMessages.html?strAction=agentAmMessage'/>";
			}
		function viewAmMessage(uniNo){
					amMessageManager.readAmMessage(uniNo);
					//window.showModalDialog("<c:url value='/editAmMessage.html?strAction=viewAmMessage&uniNo='/>"+uniNo,'','height=300, width=500, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
					window.open("<c:url value='/editAmMessage.html?strAction=viewAmMessage&uniNo='/>"+uniNo,'','height=450, width=850, top=250, left=300, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
			}
			
	function selectAnnounce(radioId){
		//alert(radioId);
		document.getElementById("frm").submit();
	}
	
	function delMsg(uniNo){
		
		if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
			amMessageManager.removeAmMessage(uniNo,delMsgCallBack);
		
		}
		
	}
	function delMsgCallBack(valid){
	
			window.location="amMessages.html?strAction=agentAmMessage";
			window.location.reload();
			alert('<jecs:locale key="bdOutWardBank.deleteSuccess"/>');
	}
	function discussAmMessage(uniNo){
			var pars=new Array();
			pars[0]="<jecs:locale key='amMessage.discuss'/>";
			pars[1]="editAmMessageDiscuss.html?strAction=editAmMessageDiscuss&uniNo=" + uniNo;
			pars[2]=window;
			var ret=showDialog("<%=request.getContextPath()%>",pars,510,250,1);

	}
</script>
