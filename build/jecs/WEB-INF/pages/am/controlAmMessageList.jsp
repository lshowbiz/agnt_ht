<%@ page contentType="text/html; charset=utf-8" language="java"%>
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
			        onclick="location.href='<c:url value="/editAmMessage.html"/>?strAction=addAmMessage&returnView=controlAmMessage'"
			        value="<jecs:locale key="member.new.num"/>" />
		
</c:set>




<form id="frm" name="frm" action="<c:url value='/amMessages.html'/>" >


<div class="searchBar">&nbsp;&nbsp;

		<div class="new_searchBar">
			<jecs:title key="amMessage.state"/>
			<jecs:list listCode="ammessage.send3" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>
		</div>
		<div class="new_searchBar">	
    		<jecs:title  key="pd.agentormember"/>
			<input name="agentNo" id="agentNo" value="<c:out value='${param.agentNo}'/>"  size="10"/>
		</div>
			<%--<jecs:title  key="bdCalculatingSubDetail.name"/>
			<input name="userName" id="userName" value="<c:out value='${param.userName}'/>" size="10"/>--%>
		<div class="new_searchBar">	
		<label><jecs:locale key="alStateProvince.stateProvinceName"/>:</label>
				<select name="recipientCaNo">
					<option value=""></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq param.recipientCaNo}">selected</c:if> >
								${alStateProvince.stateProvinceName}
						</option>
					</c:forEach>
				</select>
		</div>
		<div class="new_searchBar">	
    		<jecs:title key="ammessage.sendroute"/>
    		<jecs:list listCode="ammessage.sendroute" name="sendRoute" id="sendRoute" showBlankLine="true" value="${param.sendRoute}" defaultValue=""/>
    	</div>
    	<div class="new_searchBar">	
    		<jecs:title key="amMessage.status"/>
			<jecs:list listCode="ammessage.searchstatus" name="msgStatus" id="msgStatus" showBlankLine="true" value="${param.msgStatus}" defaultValue=""/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="amMessage.msgClassNo"/>
      		<jecs:list listCode="msgclassno" name="msgClassNo" id="msgClassNo" showBlankLine="true" value="${param.msgClassNo}" defaultValue=""/>
      	</div>
      	<div class="new_searchBar">	
			<jecs:title key="amMessage.receiverNo"/>
      		<input name="receiverNo" id="receiverNo" value="<c:out value='${param.receiverNo}'/>" size="10"/>
      	</div>
      	<div class="new_searchBar">	
      		<!-- add by lihao -->
      		<!-- 回复人所属部门 -->
      		<label><jecs:locale key = "sysDepartment.departmentName"/>:</label>
     <!-- 	<jecs:list listCode="sysdepartment.departmentname" name="departmentName" id="departmentName" showBlankLine="true" value="${param.departmentName }" defaultValue=""/>  --> 	
      		<select name="departmentId" id="departmentId">
      			<option value=""></option>
      			<option value="29791" <c:if test="${param.departmentId eq '29791'}">selected</c:if> >财务部</option>
      			<option value="3088165" <c:if test="${param.departmentId eq '3088165'}">selected</c:if>>产品售后部</option>
      			<optgroup label="客服部">
	      			<option value="3389077" <c:if test="${param.departmentId eq '3389077'}">selected</c:if> >单据组</option>
      				<option value="3389078" <c:if test="${param.departmentId eq '3389078'}">selected</c:if>>话务组</option>
      				<option value="3389079" <c:if test="${param.departmentId eq '3389079'}">selected</c:if>>资深组</option>
      				<option value="3389080" <c:if test="${param.departmentId eq '3389080'}">selected</c:if>>质检现管</option>
      			</optgroup>
      			<option value="29280" <c:if test="${param.departmentId eq '29280'}">selected</c:if>>物流部 </option>
      			<option value="130953" <c:if test="${param.departmentId eq '130953'}">selected</c:if>>业务支持部 </option>
      			<option value="29790" <c:if test="${param.departmentId eq '130953'}">selected</c:if>>结算部 </option>
      			
      		</select>
      	</div>	
      	<!--  	<input name ="departmentName" id="departmentName" value="<c:out value='${param.departmentName }'/>" size="30"/>	-->
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
			value="${param.startSearchTime }" style="cursor: pointer;width:80px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endSearchTime" id="endSearchTime" type="text"
			value="${param.endSearchTime }" style="cursor: pointer;width:80px;"
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
				<jecs:company name="companyCode" selected="${param.companyCode }"  prompt="" withAA="false"  />	
			</div>
		</c:if>	
		<div class="new_searchBar">	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>

		
			<input type="hidden" name="strAction" value="${strAction}"/>
			<input type="hidden" name="returnView" value="controlAmMessage"/>	
			

			<button name="search" type="button" onclick="checkAmMessageBatch()" class="btn btn_long" style="width:auto">
				<jecs:locale key="amMessage.checkmsg"/>
			</button>

			<button name="search" type="button" onclick="controlAmMessageBatch()" class="btn btn_long" style="width:auto">
				<jecs:locale key="amMessage.control.batch"/>
			</button>
		</div>
</div>

<ec:table 
	tableId="amMessageListTable"
	items="amMessageList"
	var="amMessage"
	action="${pageContext.request.contextPath}/amMessages.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit" style="word-break:break-all"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
			<ec:exportXls fileName="ammessage.xls"/>
				<ec:row  highlightRow="false" onclick="showTR($('tr${ROWCOUNT}'));">
    			
    			<ec:column property="aa1" sortable="false" title=" " style="width:2%;white-space:nowrap;">
    				<c:if test="${(amMessage.send_route == '1') && (amMessage.status <= 1)}">
    					 <input type="checkbox" class="checkbox" name="chkSelUserCode" value="${amMessage.uni_no}" >
    				</c:if>
    			</ec:column>
    			
    			<ec:column property="aa" sortable="false" title=" " style="width:2%;white-space:nowrap;">
    				<c:if test="${amMessage.status>=4}">
    					<img  src="images/icons/pm_replied.gif" border="0" align="absmiddle"/>
    				</c:if>
    				<c:if test="${amMessage.status<4}">
    					<img  src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"/>
    				</c:if>
    				<c:if test="${amMessage.status < 3 && amMessage.status >= 2}">
    				 <input type="checkbox" class="checkbox" name="chkSel" value="${amMessage.uni_no}" >
    				 </c:if>
    			</ec:column>
    			
    			<%--<ec:column property="a" sortable="false" title="operation.button.send" style="width:3%">
    			</ec:column>--%>
    			
    			<ec:column property="subject" title="amMessage.subject" style="white-space:nowrap;" >
					<jecs:substr key="${amMessage.subject }" length="20"/> 
    			</ec:column>
    			
    			<c:set var="msgClassNo" scope="request"><jecs:code listCode="msgclassno" value="${amMessage.msg_class_no}"/></c:set>
				
				<%--   
				<ec:column property="msg_class_no" title="amMessage.msgClassNo" cell="com.joymain.jecs.util.ectable.EcTableCell" style="white-space:nowrap;">
						
						<jecs:code listCode="msgclassno" value="${amMessage.msg_class_no}"/>
					
    			</ec:column>
				--%>
				<ec:column property="msg_class_no" title="amMessage.msgClassNo" cell="com.joymain.jecs.util.ectable.EcTableCell" style="white-space:nowrap;">
					
					<jecs:substr key="${requestScope.msgClassNo }" length="5"/>
    			</ec:column>
				
				<ec:column property="sender_no" title="amMessage.senderNo" style="white-space:nowrap;">
					
    			</ec:column>
				<ec:column property="issue_time" title="amMessage.issueTime" style="white-space:nowrap;">
					
    			</ec:column>
				
				<ec:column property="receiver_no" title="amMessage.receiverNo" style="white-space:nowrap;">
					
    			</ec:column>
				
				<ec:column property="department_name" title="sysDepartment.departmentName" style="white-space:nowrap;">
				
				</ec:column>
				<ec:column property="reply_time" title="amMessage.replyTime" style="white-space:nowrap;">
					
    			</ec:column>
    			
				<ec:column property="check_user_no" title="amMessage.checkUserNo" style="white-space:nowrap;" >
					
    			</ec:column>
				<%--<ec:column property="check_msg_time" title="amMessage.checkUserNo" >
					
    			</ec:column>--%>
    			
    			
    			<ec:column property="1" title="title.operation" sortable="false" style="white-space:nowrap;">
				<c:if test="${(amMessage.status <=2)}">
    				<jecs:power powerCode="editSendAmMessage">
    					<img  src="images/newIcons/pencil_16.gif" border="0" align="absmiddle" style="cursor:pointer"
    						title="<jecs:locale  key="amMessage.editItem"/>"  alt="<jecs:locale  key="amMessage.editItem"/>"
    						onclick="editAmMessage('${amMessage.uni_no}','editSendAmMessage');"/>
    				</jecs:power>
    				<jecs:power powerCode="delMsgAmMessage">
    					<img  src="images/newIcons/delete_16.gif" border="0" align="absmiddle" style="cursor:pointer"
    						title="<jecs:locale  key="amMessage.delItem"/>" alt="<jecs:locale  key="amMessage.delItem"/>"
    						onclick="delMsg('${amMessage.uni_no}')"/>
    				</jecs:power>
    			</c:if>	
    			<c:choose>
    			
	    				 		<c:when test="${(amMessage.send_route == '1') && (amMessage.status <= 1)}">
	    				 				<img  src="images/icons/arrangeReply.gif" border="0" align="absmiddle" style="cursor:pointer"
    										title="<jecs:locale  key='amMessage.control'/>" alt="<jecs:locale  key='amMessage.control'/>"
    											onclick="controlAmMessage('${amMessage.uni_no}');" />
	    				 				<!-- <input type="button" class="form_button" onclick="controlAmMessage('${amMessage.uniNo}');" value="<jecs:locale  key='amMessage.control'/>"/> -->
	    				 				<img  src="images/icons/replymsg.gif" border="0" align="absmiddle" style="cursor:pointer"
    										title="<jecs:locale  key='amMessage.reply'/>" alt="<jecs:locale  key='amMessage.reply'/>"
    											onclick="editAmMessage('${amMessage.uni_no}','editReplyAmMessage');"  />
	    				 				<!-- <input type="button" class="form_button" onclick="editAmMessage('${amMessage.uniNo}','editReplyAmMessage');" value="<jecs:locale  key='amMessage.reply'/>"/> -->
	    				 				<br/>
	    				 		</c:when>	
	    						<c:when test="${(amMessage.send_route == '1') && (amMessage.status == 2)}">
	    								 <img  src="images/icons/sendmsg.gif" border="0" align="absmiddle" style="cursor:pointer"
    										title="<jecs:locale  key='operation.button.send'/>" alt="<jecs:locale  key='operation.button.send'/>"
    											onclick="checkAmMessage('${amMessage.uni_no}')"/>
	    								 <!-- <input type="button" class="form_button" onclick="checkAmMessage('${amMessage.uniNo}')" value="<jecs:locale  key='operation.button.send'/>"/> -->
	    								 
	    								 <jecs:power powerCode="editReplyAmMessage">
	    								 <img  src="images/icons/replymsg.gif" border="0" align="absmiddle" style="cursor:pointer"
    										title="<jecs:locale  key='amMessage.reply'/>" alt="<jecs:locale  key='amMessage.reply'/>"
    											onclick="editAmMessage('${amMessage.uni_no}','editReplyAmMessage');"  />
    									</jecs:power>		
	    						</c:when>
	    						<c:when test="${(amMessage.send_route == '0') && (amMessage.status == 2)}">
	    								 <img  src="images/icons/sendmsg.gif" border="0" align="absmiddle" style="cursor:pointer"
    										title="<jecs:locale  key='operation.button.send'/>" alt="<jecs:locale  key='operation.button.send'/>"
    											onclick="checkAmMessage('${amMessage.uni_no}')"/>
	    								 <!-- <input type="button" class="form_button" onclick="checkAmMessage('${amMessage.uniNo}')" value="<jecs:locale  key='operation.button.send'/>"/> -->
	    								 <br/>
	    						</c:when>
	    						<c:otherwise>
	    							 &nbsp;
	    						</c:otherwise>
	    						
	    				   </c:choose>
    			</ec:column>
    			
    			
				</ec:row>

				
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
							
							
							<td colspan="9">
							<table width='100%' border="0" cellpadding="0" cellspacing="0"><tr><td  bgcolor='#FFEEAA' width='60%'>${amMessage.content}</td>
							<td  bgcolor='#FFFFCC'  width='40%'>${amMessage.reply_content}</td></tr></table>
							
							</td>
						</tr>
				</c:if>
</ec:table>	



	<%--<table class='grid_table'   cellpadding='0' cellspacing='1' border='0'>
	<tr class='grid_span'>
	<td colspan='10' align='right'>
	&nbsp;<input class='button' type='button' name='cmd' value='<jecs:locale  key=""/>' >
	&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
	</tr>
	</table>--%>
	
	
</form>
<form name="form1" action="<c:url value='editAmMessage.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='uniNo' id='uniNo'/>
</form>

<script type="text/javascript">

   function editAmMessage(uniNo,strAction){
   		
					window.location="editAmMessage.html?returnView=controlAmMessage&strAction="+strAction+"&uniNo="+uniNo;
			
		}
		function viewAmMessage(){
				
			}
			
		function checkAmMessage(uniNo){
					amMessageManager.checkAmMessage(uniNo,function(){window.location.reload();});
					
					
			}	
		function controlAmMessage(uniNo){
				//window.open("<c:url value='/sysUserSelect.html'/>?selectControl=company");
				var ret = window.showModalDialog("<c:url value='/sysUserSelect.html?strAction=companyUserSelect&selectControl=company'/>");
				
				//alert("usercode="+ret['userName']);				
				if(ret != null){
						amMessageManager.saveAmMessageReceiver(uniNo,ret['userCode'],ret['userName'],'${logUser}');
						window.location.reload();
					}
				
				//window.location.reload();
			}
		
		function alertMsgNum(){
				if( ${readMsg}==0 )
					alert("<jecs:locale key="ammessage.nochecknum"/>:" + ${nocheckNum} );
		}
		 
		alertMsgNum();
		
	function selectAnnounce(radioId){
		//alert(radioId);
		document.getElementById("frm").submit();
	}
	
	function delMsg(uniNo){
		
		if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
			amMessageManager.removeAmMessage(uniNo);
		
			window.location="amMessages.html?strAction=agentAmMessage";
		
			window.location.reload();
		}
	}
	
	function getSelectedInfoRows(formId,checkName)
	{
	 var temp = "";
	 for ( var i=0; i<Form.getInputs(formId,'checkbox',checkName).length; i++ )
	 {
	  var e = Form.getInputs(formId,'checkbox',checkName)[i];
	  if((!e.disabled)&&(e.checked)){
	  	temp += temp==""? e.value: "," + e.value ;
	  	}
	
	 }
	
	 return temp;
	}
	
	function checkAmMessageBatch(){
		var announces = getSelectedInfoRows('frm','chkSel');
		//alert(announces.length);
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				if(confirm("<jecs:locale key="amMessage.sendmsg"/>?"))
					amMessageManager.checkAmMessageBatch(announces,function(){window.location.reload();});
			}
					
					
	}
	
	function controlAmMessageBatch(){
		var announces = getSelectedInfoRows('frm','chkSelUserCode');
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
		}else{
				if(confirm("<jecs:locale key="amMessage.sendmsg"/>?")){
					
					var ret = window.showModalDialog("<c:url value='/sysUserSelect.html?strAction=companyUserSelect&selectControl=company'/>");
					if(ret != null){
							var strs= new Array(); 
 							strs=announces.split(","); 
							for (i=0;i<strs.length ;i++ )    
						     {    
						        amMessageManager.saveAmMessageReceiver(strs[i],ret['userCode'],ret['userName'],'${logUser}'); 
						     }
						     window.location.reload();
					}
				}
				
			}
					
					
	}
</script>
