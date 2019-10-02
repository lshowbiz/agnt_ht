<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amMessageList.title"/></title>
<content tag="heading"><jecs:locale key="amMessageList.heading"/></content>
<meta name="menu" content="AmMessageMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/amMessageManager.js'/>" ></script>



<form id="frm" name="frm" action="<c:url value='/amMessages.html'/>" >

<input type="hidden" name="strAction" value="${strAction}"/>
<input type="hidden" name="returnView" value="companyAmMessage"/>

<div class="searchBar">&nbsp;&nbsp;

		
		<div class="new_searchBar">
			<jecs:title key="amMessage.state"/>
			<jecs:list listCode="ammessage.send3" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>
		</div>
		<div class="new_searchBar">	
    		<jecs:title  key="pd.agentormember"/>
			<input name="agentNo" id="agentNo" value="<c:out value='${param.agentNo}'/>" size="15"/>
		</div>
			<%--<jecs:title  key="bdCalculatingSubDetail.name"/>
			<input name="userName" id="userName" value="<c:out value='${param.userName}'/>"  size="15"/>--%>
	<div class="new_searchBar">
		<jecs:title key="ammessage.sendroute"/>
    	<jecs:list listCode="ammessage.sendroute" name="sendRoute" id="sendRoute" showBlankLine="true" value="${param.sendRoute}" defaultValue=""/>
    </div>
    <div class="new_searchBar">
		<jecs:title key="amMessage.msgClassNo"/>
    	<jecs:list listCode="msgclassno" name="msgClassNo" id="msgClassNo" showBlankLine="true" value="${amMessageExample.msgClassNo}" defaultValue=""/>
    </div>
    <div class="new_searchBar">
    		<select id="selectTime" name="selectTime" style="width:auto;">
    			<option value="issueTime" <c:if test="${param.selectTime == 'issueTime'}">
											selected
										</c:if>><jecs:locale  key="amMessage.issueTime"/></option>
    			<option value="replyTime" <c:if test="${param.selectTime == 'replyTime'}">
											selected
										</c:if>><jecs:locale  key="amMessage.replyTime"/></option>
    		</select>
    		
    		<input name="startSearchTime" id="startSearchTime" type="text" 
			value="${param.startSearchTime }" style="cursor: pointer;width:78px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endSearchTime" id="endSearchTime" type="text"
			value="${param.endSearchTime }" style="cursor: pointer;width:78px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	<!--  
		    <input id="startSearchTime" name="startSearchTime" readonly value="${param.startSearchTime }" size="8"/>
		    <img src="./images/calendar/calendar7.gif" id="img_startSearchTime"  style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
					inputField     :    "startSearchTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startSearchTime", 
					singleClick    :    true
					});
				</script>
				-
				<input id="endSearchTime" name="endSearchTime" readonly value="${param.endSearchTime }" size="8"/>
		    <img src="./images/calendar/calendar7.gif" id="img_endSearchTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
					inputField     :    "endSearchTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endSearchTime", 
					singleClick    :    true
					}); 
				</script>
	-->
		</div>
		<c:if test="${'AA' == sessionScope.sessionLogin.companyCode}">
			<div class="new_searchBar">
				<jecs:title  key="sys.company.code"/>	
				<jecs:company name="companyCode" selected="${amMessageExample.companyCode }"  prompt="" withAA="false"  />	
			</div>
		</c:if>	
		<div class="new_searchBar">	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
      		<button type="submit" class="btn btn_sele" style="width:auto" ><jecs:locale  key='operation.button.search'/></button>     
       	 </div> 

    		
				<%--<jecs:title  key="ammessage.noreply"/>
					<font color="red">${noReplyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>--%>
</div>


<%--<ec:table 
	tableId="amMessageListTable"
	items="amMessageList"
	var="amMessage"
	action="${pageContext.request.contextPath}/amMessages.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit" style="word-break:break-all"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
				<ec:row  highlightRow="false">
    			
    			<ec:column property="aa" sortable="false" title=" " style="width:2%">
    				<c:if test="${amMessage.status>=4}">
    					<img  src="images/icons/pm_replied.gif" border="0" align="absmiddle"/>
    				</c:if>
    				<c:if test="${amMessage.status<4}">
    					<img  src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"/>
    				</c:if>
    			</ec:column>
    			
    			<ec:column property="replyTime" title="amMessage.content" style="valign:center;">
    				<img src="images/icons/tree_star.gif" border="0" align="absmiddle">
    				<jecs:code listCode="msgclassno" value="${amMessage.msg_class_no}"/>
    				<jecs:locale  key="amMessage.subject"/>:
    					<font color='blue'>${amMessage.subject}</font>
    				
    				<br/>
    				
    				<table width='100%' cellpadding='6' cellspacing='6' border='0'>
						<tr>
							<td valign='top' width='50%' bgcolor='#FFEEAA' style='border: 1px solid #BED5F0;'>
    				<font color='#888888'><jecs:locale  key="amMessage.senderNo"/>:</font>
    					<font color='blue'>${amMessage.sender_no}<c:if test="${amMessage.sender_name!=''}">&nbsp;/&nbsp;</c:if></font>&nbsp;&nbsp;
    				<!-- <font color='#888888'><jecs:locale  key="amMessage.issueTime"/>:</font>  -->
    					<font color='blue'>${amMessage.issue_time}</font><br/>
    				<br/>  
    				${amMessage.content}
    						
					</td>
    					
    				<td valign='top' width='100%' bgcolor='#FFFFCC' style='border: 1px solid #BED5F0;'>
	    

    				 		
    				 			<font color='#888888'><jecs:locale  key="amMessage.receiverNo"/>:</font>
    				 			${amMessage.receiver_no}<c:if test="${amMessage.receiver_name!=''}">&nbsp;/&nbsp;</c:if>
    							<!-- <font color='#888888'><jecs:locale  key="amMessage.replyTime"/>:</font> -->
    							${amMessage.reply_time}
    							<br/>
    							
    							<c:if test="${not empty amMessage.check_user_no}">
	 								<font color='#888888'><jecs:locale  key="amMessage.checkUserNo"/>:</font>
	 								${amMessage.check_user_no}&nbsp;&nbsp;${amMessage.check_msg_time}
	 								<br/>
	 							</c:if>	
    							<!-- <font color='red'><b>[</b></font><jecs:locale  key="amMessage.replyContent"/>:<font color='red'><b>]</b></font> -->
    							
    							${amMessage.reply_content}
	    	
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
				
				<ec:row  highlightRow="false" onclick="showTR($('tr${ROWCOUNT}'));">
    			
    			<ec:column property="aa" sortable="false" title=" " style="width:2%">
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
				<ec:column property="msg_class_no" title="amMessage.msgClassNo" style="white-space:nowrap;">
					<!--  <jecs:code listCode="msgclassno" value="${amMessage.msg_class_no}"/>  -->
					<jecs:substr key="${requestScope.msgClassNo }" length="5"/>
    			</ec:column>
				
				<ec:column property="sender_no" title="amMessage.senderNo" style="white-space:nowrap;"/>
				<ec:column property="issue_time" title="amMessage.issueTime" style="white-space:nowrap;"/>
				
				<ec:column property="receiver_no" title="amMessage.receiverNo" style="white-space:nowrap;"/>
				
				<ec:column property="reply_time" title="amMessage.replyTime" style="white-space:nowrap;"/>
				
				<ec:column property="discuss" title="amMessage.discuss" style="white-space:nowrap;">
					<jecs:code listCode="discuss.list" value="${amMessage.discuss}"/>
				</ec:column>
				
				
				
				
    			
    			
    			
				</ec:row>

				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
							
							
							<td colspan="8">
							<table width='100%' border="0" cellpadding="0" cellspacing="0"><tr><td  bgcolor='#FFEEAA' width='60%'>${amMessage.content}</td>
							<td  bgcolor='#FFFFCC'  width='40%'>${amMessage.reply_content}</td></tr></table>
							
							</td>
						</tr>
				</c:if>
</ec:table>	
</form>

