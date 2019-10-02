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

<c:set var="buttons">
	<input type="button" class="button" style="margin-right: 5px" 
		onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=companyAmMessage'"
			value="<jecs:locale key="member.new.num"/>"/>
</c:set>


<form id="frm" name="frm" action="<c:url value='/amMessages.html'/>" >

<input type="hidden" name="strAction" value="${strAction}"/>
<input type="hidden" name="returnView" value="companyAmMessage"/>
<div class="searchBar">&nbsp;&nbsp;

		
		<div class="new_searchBar">
			<jecs:title key="amMessage.state"/>
			<jecs:list listCode="ammessage.send2" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>
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
    		<select id="selectTime" name="selectTime" style="width:auto;" >
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
      		<button type="submit" class="btn btn_sele" style="width:auto;"><jecs:locale  key='operation.button.search'/></button>    
       	   		
			<jecs:power powerCode="addAmMessage">
				<button name="search" class="btn btn_long" style="width:auto" type="button" onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=companyAmMessage'" 
				><jecs:locale key="amMessage.sendmsg"/></button>
			</jecs:power>
		</div>
    	<div style="clear:both; margin-top:5px; margin-bottom:10px;margin-right:180px;text-align:right;">
				<jecs:title  key="ammessage.noreply"/>
					<font color="red">${noReplyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
				&nbsp;&nbsp;
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
		</div>
</div>
 

<!--  
<div class="searchBar">&nbsp;&nbsp;

		<c:if test="${'AA' == sessionScope.sessionLogin.companyCode}">
			<jecs:title  key="sys.company.code"/>	
			<jecs:company name="companyCode" selected="${amMessageExample.companyCode }"  prompt="" withAA="false"  />	
		</c:if>	
<jecs:title key="amMessage.state"/>
			<jecs:list listCode="ammessage.send2" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>	
    		<jecs:title  key="pd.agentormember"/>
			<input name="agentNo" id="agentNo" value="<c:out value='${param.agentNo}'/>" size="15"/>
			<%--<jecs:title  key="bdCalculatingSubDetail.name"/>
			<input name="userName" id="userName" value="<c:out value='${param.userName}'/>"  size="15"/>--%>
		<jecs:title key="ammessage.sendroute"/>
    	<jecs:list listCode="ammessage.sendroute" name="sendRoute" id="sendRoute" showBlankLine="true" value="${param.sendRoute}" defaultValue=""/>
		<jecs:title key="amMessage.msgClassNo"/>
    	<jecs:list listCode="msgclassno" name="msgClassNo" id="msgClassNo" showBlankLine="true" value="${amMessageExample.msgClassNo}" defaultValue=""/>
    		<select id="selectTime" name="selectTime" >
    			<option value="issueTime" <c:if test="${param.selectTime == 'issueTime'}">
											selected
										</c:if>><jecs:locale  key="amMessage.issueTime"/></option>
    			<option value="replyTime" <c:if test="${param.selectTime == 'replyTime'}">
											selected
										</c:if>><jecs:locale  key="amMessage.replyTime"/></option>
    		</select>
    		
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
				
      	<input type="submit" class="button" 
       	 value="<jecs:locale  key='operation.button.search'/>"/>       
				
		<jecs:power powerCode="addAmMessage">
		<input name="search" class="button" type="button" onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=companyAmMessage'" value="<jecs:locale key="amMessage.sendmsg"/>" />
		</jecs:power>
    		
				<jecs:title  key="ammessage.noreply"/>
					<font color="red">${noReplyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
</div>
-->

<%--<br/>
<table width='100%'>
<tr>
<th width=120></th>
<td class=list_normal>
				<input type='radio' name='reply_status' value='1' 
					<c:if test="${param.reply_status ==1 }">checked</c:if> onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/globe_16.gif" border="0" align="absmiddle"/>
				
				<input type='radio' name='reply_status' value='3' 
					<c:if test="${param.reply_status ==3 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"/>
				<jecs:title  key="ammessage.noreply"/>
					<font color="red">${noReplyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
				
				<input type='radio' name='reply_status' value='4'
					<c:if test="${param.reply_status ==4 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/letter_16.gif" border="0" align="absmiddle"/>
				<jecs:title  key="ammessage.replynum"/>
					<font color="red">${replyNum }</font>
				<jecs:locale  key="amAnnounce.items"/>
</td>
</tr>
</table>


<table width="100%" border="0" id="searchTable" >
	<tr>
		<th>		
		</th>
		<th>	
      	</th>
      <!--   
      <jecs:locale  key="pd.senderNo"/>:
			<input name="senderNo" id="senderNo" value="<c:out value='${amMessageExample.senderNo}'/>"  cssClass="text medium"/>
		-->
		<th>
     	</th>
     	
     	<th>
    	</th>
    	
    	<th>
    	</th>
    	<th>
    		<jecs:locale  key="operation.button.search"/>
		</th>
		<th>
			&nbsp;
		</th>
	</tr>
	<tr>
				
    	<td width="20px">
		</td>	
		<td width="20px">
      	</td>
      <!--   
      <jecs:locale  key="pd.senderNo"/>:
			<input name="senderNo" id="senderNo" value="<c:out value='${amMessageExample.senderNo}'/>"  cssClass="text medium"/>
		-->
		<td width="20px">	
     	</td>
		<td width="20px">			
    	</td>
    	<td width="210px">
    	</td>
    	<td width="50px"> 
						
				
		</td>
		<td>
			&nbsp;
		</td>
	</tr>
</table>
</div>--%>


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
				 <c:set var="msgClassNo" scope="request"><jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/></c:set> 
				<ec:column property="msgClassNo" title="amMessage.msgClassNo" style="white-space:nowrap;" >
					<%--  <jecs:code listCode="msgclassno" value="${amMessage.msgClassNo}"/>  --%>
					 <jecs:substr key="${requestScope.msgClassNo }" length="5"/>
    			</ec:column>
				
				<ec:column property="senderNo" title="amMessage.senderNo" style="white-space:nowrap;"/>
				<ec:column property="issueTime" title="amMessage.issueTime" style="white-space:nowrap;"/>
				
				<ec:column property="receiverNo" title="amMessage.receiverNo" style="white-space:nowrap;"/>
				
				<ec:column property="replyTime" title="amMessage.replyTime" style="white-space:nowrap;" />
    			
				<ec:column property="checkUserNo" title="amMessage.checkUserNo" style="white-space:nowrap;" />
    			
				<ec:column property="1" title="title.operation" sortable="false" style="white-space:nowrap;">
					<c:if test="${(amMessage.senderNo == sessionScope.sessionLogin.userCode) && (amMessage.status <3 ) }">
		 					<img  src="images/newIcons/pencil_16.gif" border="0" align="absmiddle"  style="cursor:pointer"
		 						title="<jecs:locale  key="amMessage.editItem"/>" alt="<jecs:locale  key="amMessage.editItem"/>"
		 						onclick="editAmMessage('${amMessage.uniNo}','editSendAmMessage');"/>
		 					
		 					<img  src="images/newIcons/delete_16.gif" border="0" align="absmiddle" style="cursor:pointer"
		 						title="<jecs:locale  key="amMessage.delItem"/>" alt="<jecs:locale  key="amMessage.delItem"/>"
		 						onclick="delMsg('${amMessage.uniNo}')"/>
		 				
		 			</c:if>
					 <c:choose>
    				 		<c:when test="${amMessage.status ==2}">
    							
    							<c:if test="${amMessage.senderNo != sessionScope.sessionLogin.userCode}">
    							<img  src="images/icons/replymsg.gif" border="0" align="absmiddle" style="cursor:pointer"
    								title="<jecs:locale  key='amMessage.reply'/>" alt="<jecs:locale  key='amMessage.reply'/>"
    											onclick="editAmMessage('${amMessage.uniNo}','editReplyAmMessage');"  />
    							</c:if>
	    								
    				 		</c:when>
    				 		<c:when test="${(amMessage.sendRoute =='1') && (amMessage.status <3)}">
    				 			
    				 			<img  src="images/icons/replymsg.gif" border="0" align="absmiddle" style="cursor:pointer"
    								title="<jecs:locale  key='amMessage.reply'/>" alt="<jecs:locale  key='amMessage.reply'/>"
    											onclick="editAmMessage('${amMessage.uniNo}','editReplyAmMessage');"  />
    				 		</c:when>    				 			
    				 		<c:when test="${(amMessage.sendRoute =='0') && (amMessage.status ==4)}">
    				 			 <img  src="images/newIcons/search_16.gif" border="0" align="absmiddle" style="cursor:pointer"
    									title="<jecs:locale  key='amMessage.view'/>" alt="<jecs:locale  key='amMessage.view'/>"
    											onclick="viewAmMessage('${amMessage.uniNo}');"  />
    				 		</c:when>
    				 		<c:otherwise>
    				 		
    				 			
    				 		</c:otherwise>
    				 </c:choose>
				</ec:column>
    			
    			
    			
				</ec:row>

				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
							
							
							<td colspan="9">
							<table width='100%' border="0" cellpadding="0" cellspacing="0"><tr><td  bgcolor='#FFEEAA' width='60%'>${amMessage.content}</td>
							<td  bgcolor='#FFFFCC'  width='40%'>${amMessage.replyContent}</td></tr></table>
							
							</td>
						</tr>
				</c:if>
</ec:table>	
</form>
<form name="form1" action="<c:url value='editAmMessage.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='uniNo' id='uniNo'/>
</form>

<script type="text/javascript">

   function editAmMessage(uniNo,strAction){
   		
					window.location="editAmMessage.html?returnView=companyAmMessage&strAction="+strAction+"&uniNo="+uniNo;
			
		}
		function viewAmMessage(uniNo){
				amMessageManager.readAmMessage(uniNo); //showModalDialog
					window.open("<c:url value='/editAmMessage.html?strAction=viewAmMessage'/>&uniNo="+uniNo,'','height=350, width=400, top=250, left=300, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
					
			}

	function selectAnnounce(radioId){
		//alert(radioId);
		document.getElementById("frm").submit();
	}
	
	function delMsg(uniNo){
		
		if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
			amMessageManager.removeAmMessage(uniNo);
		
			window.location="amMessages.html?strAction=companyAmMessage";
		
			window.location.reload();
		}
	}
	
</script>
