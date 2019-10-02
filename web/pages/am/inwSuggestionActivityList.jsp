<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwSuggestionList.title"/></title>
<content tag="heading"><jecs:locale key="inwSuggestionList.heading"/></content>
<meta name="menu" content="InwSuggestionMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/inwSuggestionManager.js'/>" ></script>

<form action="inwSuggestions.html" method="get" name="inwSuggesionForm1" id="inwSuggesionForm1">
<table class='detail' width="100%">
    <input type="hidden" id="strAction" name="strAction" value="activitySuggestion"/>
<div class="searchBar">&nbsp;&nbsp;
			<tr>
				<td>
					<jecs:title key="inwDemand.name" />
				</td>
				<td>
					<input name="name" type="text" value="${param.name }" size="15" />
				</td>
				<td>
					<jecs:title key="inwSuggestion.userCode" />
				</td>
				<td>
					<input name="suggestionUserCode" type="text"
						value="${param.suggestionUserCode }" size="12" />
				</td>
				<td>
					<jecs:title key="linkmanActivity.topic" />
				</td>
				<td>
					<input name="subject" type="text" value="${param.subject }"
						size="12" />
				</td>
				<td>
					<jecs:title key="inwSuggestion.createTime" />
					</td>
						<td nowrap="nowrap">
							<input id="createTimeBegin" name="createTimeBegin" type="text"
								size="8" maxlength="10" value="${param.createTimeBegin }" />
							<img src="./images/calendar/calendar7.gif"
								id="img_createTimeBegin" style="cursor: pointer;"
								title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
							<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "createTimeBegin", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_createTimeBegin", 
			singleClick    :    true
			}); 
		</script>
							-
							<input id="createTimeEnd" name="createTimeEnd" type="text"
								size="8" maxlength="10" value="${param.createTimeEnd }" />
							<img src="./images/calendar/calendar7.gif" id="img_createTimeEnd"
								style="cursor: pointer;"
								title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
							<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "createTimeEnd", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_createTimeEnd", 
			singleClick    :    true
			}); 
		</script>
						</td>
			</tr>
			<tr>
				<td>
					<jecs:title key="inwDemandsort.spece" />
				</td>
				<td>
					<select name="demandsortId" id="demandsortId">
						<option label="" value="" />
							<c:forEach items="${demandsortList}" var="demandsort">
								<option value="${demandsort.id}">
									${demandsort.name }
								</option>
							</c:forEach>
					</select>
				</td>
				<td>
					<jecs:title key="inwsuggestion.sstatus" />
				</td>
				<td>
					<c:if test="${strAction=='activitySuggestion'|| strAction=='activitySuggestionRestore' }">
						<jecs:list name="status" listCode="firstquery.audit"
							value="${param.status}" defaultValue="false" showBlankLine="true" />
					</c:if>
					<c:if test="${strAction=='initialAudit'}">
						<jecs:list name="status" listCode="initialquery.audit"
							value="${param.status}" defaultValue="false" showBlankLine="true" />
					</c:if>
					<c:if test="${strAction=='feasibilityAudit'}">
						<jecs:list name="status" listCode="feasibilityquery.audit"
							value="${param.status}" defaultValue="false" showBlankLine="true" />
					</c:if>
				</td>
				<c:if test="${strAction=='activitySuggestion'}">
						<td><jecs:title key="inwDemand.verify" /></td>
						<td><jecs:list name="initialAudit" listCode="inwdemand.verify"
									value="${param.initialAudit}" defaultValue="false" showBlankLine="true" /></td>
				</c:if>		
				<td colspan="2">
					<input name="search" type="button" class="button"
						onclick="checkApplication(document.inwSuggesionForm1)"
						value="<jecs:locale key="operation.button.search"/>" />
					<c:if test="${strAction=='activitySuggestion'}">	
				        &nbsp;&nbsp;
				   	    <input class="button"  type='button' name='cmd' value='<jecs:locale  key="button.audit"/>' onclick="checkInwSuggestion();">
				   	</c:if>	
				</td>
			</tr>
		</div>
</table>
</form>
<div id="loading">
<ec:table 
	tableId="inwSuggestionListTable"
	items="inwSuggestionList"
	var="inwSuggestionAndInwDemand"
	action="${pageContext.request.contextPath}/inwSuggestions.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<c:if test="${strAction=='activitySuggestion'}">
					<ec:column alias="chkSel" headerCell="selectAll" style="width:5px;">  
						      <input type="checkbox" class="checkbox" name="chkSel" id="chkSel" value="${inwSuggestionAndInwDemand.id}" />
					</ec:column>
				</c:if>
				<ec:column property="demandsortname" title="inwDemandsort.spece" style="white-space:nowrap"/>
    			<ec:column property="name" title="inwDemand.name" style="white-space:nowrap"/>
    			<ec:column property="subject" title="inwSuggestion.suggestedTopics" />
    			<ec:column property="user_code" title="inwSuggestion.userCode" style="white-space:nowrap"/>
    			<ec:column property="create_time" title="inwSuggestion.createTime" style="white-space:nowrap"/>
    			<c:if test="${strAction=='activitySuggestion'}">
	    			<ec:column property="initial_audit" title="inwDemand.verify" style="white-space:nowrap">
	    			   <c:choose>
	    			        <c:when test="${inwSuggestionAndInwDemand.initial_audit == 'Y'}">
	    			             <jecs:code listCode="inwdemand.verify" value="Y"></jecs:code>
	    			        </c:when>
	    			        <c:otherwise>
	    			             <jecs:code listCode="inwdemand.verify" value="N"></jecs:code>
	    			        </c:otherwise>
	    			   </c:choose>
	    			</ec:column>
	    			<ec:column property="initial_audit_time" title="pd.checkTime" style="white-space:nowrap"/>
   			    </c:if>
    			<ec:column property="1" title="sysOperationLog.moduleName" style="white-space:nowrap">
						<img src="images/newIcons/search_16.gif" onclick="detailQuery(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					<c:if test="${strAction =='activitySuggestionRestore'}">
					    <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
					</c:if>
					<c:if test="${strAction !='activitySuggestionRestore'}">
							&nbsp;&nbsp;
							<c:if test="${ (inwSuggestionAndInwDemand.status=='0' || inwSuggestionAndInwDemand.status=='1'|| inwSuggestionAndInwDemand.status=='2' || inwSuggestionAndInwDemand.status=='3')&&(strAction=='activitySuggestion') }">
							      <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
					        </c:if>
					        <c:if test="${ (inwSuggestionAndInwDemand.status=='3' || inwSuggestionAndInwDemand.status=='4' || inwSuggestionAndInwDemand.status=='5')&&(strAction=='initialAudit') }">
							      <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
					        </c:if>
					        <c:if test="${ (inwSuggestionAndInwDemand.status=='5'|| inwSuggestionAndInwDemand.status=='6' || inwSuggestionAndInwDemand.status=='7')&&( strAction=='feasibilityAudit' ) }">
							      <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
					        </c:if>
					        
					        <c:if test="${inwSuggestionAndInwDemand.update_flag == 'Y' }">
							      <img src="images/xx.png" alt="<jecs:locale key="sysDataLog.update"/>" style="cursor:pointer"/>
					        </c:if>
					     <jecs:power powerCode="suggestionContentAudit">
					        <img src="images/newIcons/tick_16.gif" onclick="suggestionContentAudit(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="button.audit"/>" style="cursor:pointer"/>
					     </jecs:power>
				   </c:if>    
				</ec:column>
				</ec:row>
</ec:table>	
</div>

<script>

       window.onload = function setValueProperty(){
       
                 var checkedOrNO = document.getElementsByName("chkSel");
                 for(var i=0;i<checkedOrNO.length;i++){
                      checkedOrNO[i].checked = false;
                  }
           
             var demandsortId = "<%= request.getAttribute("demandsortId")%>";
             document.getElementById("demandsortId").value = demandsortId;
       
       }

       var sstrAction = "<%= request.getAttribute("strAction")%>";
       function detailQuery(id){
             window.location.href="inwSuggestions.html?strAction=suggestionQuery&id="+id+"&differenceInw="+sstrAction;
       }
       
       function suggestionReply(id){
             window.location.href="editInwSuggestion.html?strAction=suggestionReply&id="+id+"&differenceInw="+sstrAction;
       }
       
       function suggestionContentAudit(id){
		     window.location.href="editInwSuggestion.html?strAction=suggestionContentAudit&id="+id+"&differenceInw="+sstrAction;		     
		}

       
       function checkApplication(theForm){
			theForm.strAction.value=sstrAction;
			if(isFormPosted()){
				theForm.submit();
			}
	  }

       function checkInwSuggestion(){
       
          var selectedId = document.getElementsByName("chkSel");
          var selectStr = '';
          for(var i=0;i<selectedId.length;i++){
                 
                  if(selectedId[i].checked){
                          
                           selectStr += selectedId[i].value+",";
                  }
            }
           if(selectStr==''){
                      alert("<jecs:locale key='sys.role.noselect'/>");//?
                      return;
             }
             
             inwSuggestionManager.checkInwSuggestionList(selectStr,function(){window.location.reload();});
          
		}
		
</script>