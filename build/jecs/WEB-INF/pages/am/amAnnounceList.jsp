<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amAnnounceList.title"/></title>
<content tag="heading"><jecs:locale key="amAnnounceList.heading"/></content>
<meta name="menu" content="AmAnnounceMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/amAnnounceManager.js'/>" ></script>

<c:set var="buttons">
		<jecs:power powerCode="addAmAnnounce">
			<c:if test ="${strAction == 'editAmAnnounce'}">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editAmAnnounce.html"/>?strAction=addAmAnnounce'"
			        value="<jecs:locale key="member.new.num"/>"/>
			</c:if>
		</jecs:power>
</c:set>


<form name="amAnnounceListTableForm" id="amAnnounceListTableForm" action="<c:url value='/amAnnounces.html'/>" >

<input type="hidden" name="strAction" value="${strAction}"/>


<div class="searchBar">
		
	  	<div class="new_searchBar">
	  		<label><jecs:locale  key="amAnnounce.subject"/>:</label>
	  		<input type="text" name="subject" value="${param.subject}" size="10"/>
	  	</div>
	  	<div class="new_searchBar">
	  		<label><jecs:locale  key="bdReconsumMoneyReport.typeCH"/>:</label>
	  		<jecs:list listCode="annoclassno" name="annoClassNo" id="annoClassNo" showBlankLine="true" value="${param.annoClassNo}" defaultValue=""/> 
	  	</div>
	  	<div class="new_searchBar"> 
			<label><jecs:locale  key="amAnnounce.content"/>:</label>
			<input type="text" name="content" value="${param.content}" size="5"/>	
		</div>
		
		<div class="new_searchBar">
			<label><jecs:locale  key="busi.message.issuetime"/>:</label>
			<input name="createTimeStart" id="createTimeStart" type="text" value="${param.createTimeStart }"  
				style="cursor:pointer;width:76px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="createTimeEnd" id="createTimeEnd" type="text" value="${param.createTimeEnd }"  
				style="cursor:pointer;width:76px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
			<!--  
		    <img src="./images/calendar/calendar7.gif" id="img_startSearchTime"  style="cursor: pointer;width:70px;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startSearchTime", 
					singleClick    :    true
					});
				</script>
			
			<input id="createTimeEnd" name="createTimeEnd" readonly value="${param.createTimeEnd }" size="8"/>
		    <img src="./images/calendar/calendar7.gif" id="img_endSearchTime" style="cursor: pointer;width:70px;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endSearchTime", 
					singleClick    :    true
					}); 
				</script>		
			-->	
			
	<!--  		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>" />	-->
	<c:if test ="${strAction == 'checkAmAnnounce'}">
			<div class="new_searchBar">
				<label><jecs:locale  key="busi.message.issuer"/>:</label>
				<input type="text" name="issuer" value="${param.issuer}" size="20"/>	
			</div>		
		</c:if>
	<c:if test ="${strAction == 'browserAmAnnounce'}">
			<div class="new_searchBar">
				<label><jecs:locale  key="amAnnounce.state"/>:</label>	
				<jecs:list listCode="amnnounce.status" name="reply_status" id="reply_status"  value="${param.reply_status}" defaultValue=""/>	
			</div>
				<%--<input type='radio' name='reply_status' value='1' 
					<c:if test="${param.reply_status ==1 }">checked</c:if> onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/globe_16.gif" border="0" align="middle"/>
				<input type='radio' name='reply_status' value='2' 
					<c:if test="${param.reply_status ==2 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/bubble_16.gif" border="0" <c:if test=""></c:if> align="middle"/>
				<jecs:title  key="ammessage.agentstatus.1"/>
					<font color="red">${noreadNum}</font>
				<jecs:locale  key="amAnnounce.items"/>
				<input type='radio' name='reply_status' value='3' 
					<c:if test="${param.reply_status ==3 }">checked</c:if>  onclick="selectAnnounce(this.value)">
				<img  src="images/newIcons/letter_16.gif" border="0" align="middle"/>
				<jecs:title  key="amAnnounce.read"/>
					<font color="red">${readNum }</font>
				<jecs:locale  key="amAnnounce.items"/>--%>
				
				
				
		</c:if>
		
		<c:if test="${sessionScope.sessionLogin.isManager}">
			<div class="new_searchBar">
			
				<label><jecs:locale  key="sys.company.code"/>:</label>
				<jecs:company name="companyCode" selected="${param.companyCode }"  prompt="" withAA="false"  />	
			</div>
		</c:if>	
		
		<c:if test="${sessionScope.sessionLogin.isManager || sessionScope.sessionLogin.isCompany}">
			
			<c:if test="${strAction !='browserAmAnnounce'}">
				<div class="new_searchBar">
					<label><jecs:locale  key="pd.checkstatus"/>:</label>
					<jecs:list listCode="amannounce.status" name="status" id="status" showBlankLine="true" value="${param.status}" defaultValue=""/>	
				</div>
			</c:if>
			
	  	</c:if> 
	  	
<c:if test="${strAction == 'browserAmAnnounce' }">
	<div class="new_searchBar">
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  		
		 	<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
		</div>
	
</c:if>
		
<c:if test="${strAction == 'editAmAnnounce'}">
	<div class="new_searchBar">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
		<jecs:power powerCode="addAmAnnounce">
			<button name="search" class="btn btn_ins" style="width:auto" type="button" onclick="location.href='<c:url value="/editAmAnnounce.html"/>?strAction=addAmAnnounce&editFlag=1'"><jecs:locale key="member.new.num"/></button>
		</jecs:power>

	</div>
</c:if>


<c:if test="${strAction =='checkAmAnnounce'}">
	
	<div style="clear:both; margin-top:5px; margin-bottom:10px;"> 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
		<jecs:power powerCode="addAmAnnounce">
			<button name="search" class="btn btn_ins" style="width:auto" type="button" onclick="location.href='<c:url value="/editAmAnnounce.html"/>?strAction=addAmAnnounce&editFlag=1'"><jecs:locale key="member.new.num"/></button>
		</jecs:power>
		<button class="btn btn_ins" style="width:auto" type='button' name='cmd'  onclick="delAnnounce()"><jecs:locale  key="operation.button.delete"/></button>
	
		
		<button class="btn btn_long" style="width:auto"  type='button' name='cmd' onclick="checkAnnounce();"><jecs:locale  key="amAnnounce.checkAnnounce"/></button>
	
		<jecs:power powerCode="reSetAmAnnounce">
			<button class="btn btn_long" style="width:auto"  type='button' name='cmd' onclick="reSetCreateTime();"><jecs:locale  key="amAnnounce.topAnnounce"/></button>
	
		</jecs:power>
	
		
		<jecs:power powerCode="setHighlight">
	
			<button class="btn btn_long"  style="width:auto" type='button' name='cmd' onclick="setHighlight('1');"><jecs:locale  key="amAnnounce.setHighlight1"/></button>
			<button class="btn btn_long" style="width:auto" type='button' name='cmd' onclick="setHighlight('0');"><jecs:locale  key="amAnnounce.setHighlight0"/></button>
		</jecs:power>

		<button class="btn btn_long"  style="width:auto" type='button' name='cmd' onclick="outAnnounce();"><jecs:locale  key="amAnnounce.outAnnounce"/></button>
	</div>
</c:if>	

		<c:if test ="${strAction == 'browserAmAnnounce'}">
			<div style="clear:both; margin-top:5px; margin-bottom:10px;margin-right:180px;text-align:right;">
				
				<jecs:locale  key="ammessage.agentstatus.1"/>
					<font color="red">${noreadNum}</font>
					<jecs:locale  key="amAnnounce.items"/>
				&nbsp;&nbsp;
						<jecs:locale  key="amAnnounce.read"/><font color="red">${readNum }</font>
					<jecs:locale  key="amAnnounce.items"/>
			</div>
		</c:if>
</div>

<ec:table 
	tableId="amAnnounceListTable"
	items="amAnnounceList"
	var="amAnnounce"
	action="${pageContext.request.contextPath}/amAnnounces.html"
	width="100%"
	form="amAnnounceListTableForm"
	
	retrieveRowsCallback="limit"  style="word-break:break-all"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
							
		<ec:row  >
			
			<c:choose>
				<c:when test ="${strAction == 'browserAmAnnounce'}">
					<ec:column property="aa" sortable="false" title=" " style="width:3%">
					
		    			<c:if test="${amAnnounce.readed=='0'}">
		    			<img title="<jecs:locale  key='amAnnounce.notread'/>" alt="<jecs:locale  key='amAnnounce.notread'/>" src="images/newIcons/bubble_16.gif" border="0" align="absmiddle"
		    				 style="cursor:pointer" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','2')" />
		    			</c:if>
		    			<c:if test="${amAnnounce.readed=='1'}">
						<img title="<jecs:locale  key='amAnnounce.hasread'/>" alt="<jecs:locale  key='amAnnounce.hasread'/>" src="images/newIcons/letter_16.gif" border="0" align="absmiddle"
							 style="cursor:pointer" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','2')" />
						</c:if>
						
						
		    		</ec:column>
		    		
		    		<ec:column  property="subject" title="amAnnounce.subject">	
		    			<c:if test="${amAnnounce.highlight=='1' }">
		    				<c:set value="font-weight: bold;color: #F805FD;" var="highlightVar"></c:set>
		    			</c:if>	    								
		    			<c:if test="${amAnnounce.readed=='0'}">
		    				<b><span  style="cursor:pointer;${highlightVar}" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','2')">${amAnnounce.subject}</span></b>
		    			</c:if>
		    			<c:if test="${amAnnounce.readed=='1'}">
		    				<span  style="cursor:pointer;${highlightVar}" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','2')">${amAnnounce.subject}</span>
		    			</c:if>		
		    			
		    			<c:remove var="highlightVar"/>    								
		    		</ec:column>
		    		
		    		<ec:column  property="anno_class_no" title="bdReconsumMoneyReport.typeCH">	
						<jecs:code listCode="annoclassno" value="${amAnnounce.anno_class_no}"/>
					</ec:column>
							
					<c:if test="${sessionScope.sessionLogin.isManager}">
			    		<ec:column property="companycode" title="sys.company.code"  />
			    	</c:if>
		    		
		    		<ec:column property="create_time" title="busi.message.issuetime"  style="width:15%"/>
		    					
		    		
		    		
		    		
				</c:when>
				<c:otherwise>				
					<c:if test="${strAction =='checkAmAnnounce'}">
					<ec:column alias="chkSel" headerCell="selectAll" style="width:5px;">  
					      <input type="checkbox" class="checkbox" name="chkSel" value="${amAnnounce.aano}" >
					</ec:column>
					</c:if>
							<!--  -->
					<c:if test="${sessionScope.sessionLogin.isManager}">
			    		<ec:column property="companycode" title="sys.company.code"  />
			    	</c:if>
		    		
		    		<ec:column property="issuer_name" title="busi.message.issuer" style="width:25%">
		    			${amAnnounce.issue_user_no}
		    			<%--<c:if test="${amAnnounce.issuer_name!=''}">&nbsp;/&nbsp;</c:if>${amAnnounce.issuer_name}
		    		--%></ec:column>
		    		
		    		<ec:column property="create_time" title="busi.message.issuetime"  style="width:15%"/>
		    					
		    		<ec:column  property="subject" title="amAnnounce.subject">	    
		    		
		    			<c:if test="${amAnnounce.highlight=='1' }">
		    				<c:set value="font-weight: bold;color: #F805FD;" var="highlightVar"></c:set>
		    			</c:if>	    
		    											
		    			<span style="${highlightVar}">${amAnnounce.subject}</span>
		    			<c:remove var="highlightVar"/>
		    		</ec:column>
		    		
		    		<ec:column  property="anno_class_no" title="bdReconsumMoneyReport.typeCH">	
						<jecs:code listCode="annoclassno" value="${amAnnounce.anno_class_no}"/>
					</ec:column>
		    		
		    		<c:if test="${strAction =='checkAmAnnounce'}">
		    			<ec:column property="check_user_no" title="busi.user.check"/>
		    		</c:if>
		    				
		    		<c:if test="${strAction !='browserAmAnnounce'}">
		    			<ec:column property="status" title="pd.checkstatus" >
		    				<c:if test="${amAnnounce.status==0}"><font color="red"></c:if>
		    				<jecs:code listCode="amannounce.status" value="${amAnnounce.status}"/>
		    				<c:if test="${amAnnounce.status==1}"></font></c:if>
		    			</ec:column>
		    		</c:if>    		
		    		<ec:column property="out_announce" title="amAnnounce.outAnnounce.yesno">
		    			<jecs:code listCode="yesno" value="${amAnnounce.out_announce=='1'?'1':'0'}"/>
		    		</ec:column>
					<c:if test="${strAction !='browserAmAnnounce'}">
						<ec:column property="a" sortable="false" title="title.operation" style="width:6%">
							<c:choose>
						     <c:when test ="${strAction == 'editAmAnnounce'}">
						       		<img title="<jecs:locale  key='function.menu.view'/>" alt="<jecs:locale  key='function.menu.view'/>" src="images/newIcons/search_16.gif" border="0" align="absmiddle"
										 style="cursor:pointer" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','2')"/>
								<c:if test="${amAnnounce.status==0}">
									<img title="<jecs:locale  key='amMessage.editItem'/>" alt="<jecs:locale  key='amMessage.editItem'/>" src="images/newIcons/pencil_16.gif" border="0" align="absmiddle"
										 style="cursor:pointer" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','1')"/>
								</c:if>
						     </c:when>
						     <c:otherwise>
						        <c:if test ="${strAction =='checkAmAnnounce' }">
							  		<img title="<jecs:locale  key='function.menu.view'/>" alt="<jecs:locale  key='function.menu.view'/>" src="images/newIcons/search_16.gif" border="0" align="absmiddle"
										style="cursor:pointer" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','2')"/>
							
									<%--<img title="<jecs:locale  key='amMessage.editItem'/>" alt="<jecs:locale  key='amMessage.editItem'/>" src="images/newIcons/pencil_16.gif" border="0" align="absmiddle"
										style="cursor:pointer" onclick="javascript:editAmAnnounce('${amAnnounce.aano}','${strAction}','1')"/>--%>
								
								</c:if>
						     </c:otherwise>
						  </c:choose>
							
						</ec:column>
					</c:if>
				</c:otherwise>
			</c:choose>
    			
		</ec:row>

</ec:table>

</form>


<script type="text/javascript">
   //open("jpmProducts.html?strAction=selectProduct&selectControl=2");
   function editAmAnnounce(aaNo,strAction,editFlag){
   		//alert("strAction:"+strAction+"_strAction:"+editFlag);
   		<jecs:power powerCode="${strAction}">
					window.location="editAmAnnounce.html?strAction="+strAction+"&aaNo="+aaNo+"&editFlag="+editFlag;
			</jecs:power>
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
	
	function delAnnounce(){
		var announces = getSelectedInfoRows('amAnnounceListTableForm','chkSel');
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				if(confirm("<jecs:locale key="bdOutWardBank.confirm.delete"/>"))
					amAnnounceManager.removeAnnounceBatch(announces,function(){window.location.reload();});
			}
	}
	
	function checkAnnounce(){
		var announces = getSelectedInfoRows('amAnnounceListTableForm','chkSel');
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				amAnnounceManager.checkAnnounceBatch(announces,'${userCode}','${userName}',function(){window.location.reload();});
			}
	}
	
	function reSetCreateTime(){
		var announces = getSelectedInfoRows('amAnnounceListTableForm','chkSel');
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				if(confirm("<jecs:locale key="bdOutWardBank.confirm.top"/>"))
					amAnnounceManager.reSetCreateTime(announces,function(){window.location.reload();});
			}
	}
	
	
	function setHighlight(hightlight){
		var announces = getSelectedInfoRows('amAnnounceListTableForm','chkSel');
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				if(confirm("<jecs:locale key="bdOutWardBank.confirm.hightlight"/>"))
					amAnnounceManager.setHighlight(announces,hightlight,function(){window.location.reload();});
			}
	}
	
	
	function selectAnnounce(radioId){
		//alert(radioId);
		$("amAnnounceListTableForm").submit();
	}
	
	//outAnnounce
	function outAnnounce(){
		var announces = getSelectedInfoRows('amAnnounceListTableForm','chkSel');
		if(announces.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				<c:forEach var="item" items="${amAnnounceList}">
					var status = "${item.status}";
					var aano = '${item.aano}';
					var outAnnounce = "${item.out_announce}";
					if(outAnnounce=='1'&&announces.indexOf(aano)!=-1){
						alert("<jecs:locale key="amAnnounce.outAnnounce.msg"/>");
						return false;
					}
					if(status!=1&&announces.indexOf(aano)!=-1){
						alert("<jecs:locale key="amAnnounce.noChecked.msg"/>");
						return false;
					}
				</c:forEach>
				
				if(confirm("<jecs:locale key="outAnnounce.confirm.outAnnounce"/>")){
					amAnnounceManager.updateOutAmAnnounce(announces,function(){window.location.reload();});
				}
			}
	}
</script>
