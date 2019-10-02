<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwSuggestionList.title"/></title>
<content tag="heading"><jecs:locale key="inwSuggestionList.heading"/></content>
<meta name="menu" content="InwSuggestionMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="inwSuggestionsDepart.html" method="get" name="inwSuggesionFormDepart" id="inwSuggesionFormDepart">
<table class='detail' width="100%">
    <input type="hidden" id="strAction" name="strAction" value="activitySuggestionDepart"/>
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
					<c:if test="${strAction=='activitySuggestionDepart'}">
						<jecs:list name="status" listCode="firstquery.audit"
							value="${param.status}" defaultValue="false" showBlankLine="true" />
					</c:if>
					<c:if test="${strAction=='initialAuditDepart'}">
						<jecs:list name="status" listCode="initialquery.audit"
							value="${param.status}" defaultValue="false" showBlankLine="true" />
					</c:if>
					<c:if test="${strAction=='feasibilityAuditDepart'}">
						<jecs:list name="status" listCode="feasibilityquery.audit"
							value="${param.status}" defaultValue="false" showBlankLine="true" />
					</c:if>
				</td>
				<td>
				    <jecs:title key="inwSuggestion.firstReplyAudit" />
				</td>
				<td>
				      <jecs:list name="firstReplyAudit" listCode="inwsuggestion.contentaudit" 
				          value="${param.firstReplyAudit}" defaultValue="false" showBlankLine="true"/>
				</td>
				<td colspan="2">
					<input name="search" type="button" class="button"
						onclick="checkApplication(document.inwSuggesionFormDepart)"
						value="<jecs:locale key="operation.button.search"/>" />
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
	action="${pageContext.request.contextPath}/inwSuggestionsDepart.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="demandsortname" title="inwDemandsort.spece" />
    			<ec:column property="name" title="inwDemand.name" />
    			<ec:column property="subject" title="inwSuggestion.suggestedTopics" />
    			<ec:column property="user_code" title="inwSuggestion.userCode" />
    			<ec:column property="create_time" title="inwSuggestion.createTime"/>  			    
    			<ec:column property="1" title="sysOperationLog.moduleName" >
						<img src="images/newIcons/search_16.gif" onclick="detailQuery(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
						&nbsp;&nbsp;
					<jecs:power powerCode="suggestionReplyDepart">
						<c:if test="${ (inwSuggestionAndInwDemand.status=='0' || inwSuggestionAndInwDemand.status=='1'|| inwSuggestionAndInwDemand.status=='2' || inwSuggestionAndInwDemand.status=='3')&&(strAction=='activitySuggestionDepart') }">
						      <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
				        </c:if>
				        <c:if test="${ (inwSuggestionAndInwDemand.status=='3' || inwSuggestionAndInwDemand.status=='4' || inwSuggestionAndInwDemand.status=='5')&&(strAction=='initialAuditDepart') }">
						      <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
				        </c:if>
				        <c:if test="${ (inwSuggestionAndInwDemand.status=='5'|| inwSuggestionAndInwDemand.status=='6' || inwSuggestionAndInwDemand.status=='7')&&( strAction=='feasibilityAuditDepart' ) }">
						      <img src="images/ii_4.gif" onclick="suggestionReply(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
				        </c:if>
				     </jecs:power>
				     <jecs:power powerCode="suggestionContentAuditDepart">
				        <img src="images/newIcons/tick_16.gif" onclick="suggestionContentAudit(${inwSuggestionAndInwDemand.id })" alt="<jecs:locale key="button.audit"/>" style="cursor:pointer"/>
				     </jecs:power>  
				</ec:column>
				</ec:row>
</ec:table>	
</div>

<script>

       window.onload = function setValueProperty(){
             var demandsortId = "<%= request.getAttribute("demandsortId")%>";
             document.getElementById("demandsortId").value = demandsortId;      
       }

       var sstrAction = "<%= request.getAttribute("strAction")%>";
       var departmentId = "<%= request.getAttribute("departmentId")%>";
       function detailQuery(id){
             window.location.href="inwSuggestionsDepart.html?strAction=suggestionQueryDepart&id="+id+"&differenceInwDepart="+sstrAction;
       }
       
       function suggestionReply(id){
             window.location.href="editInwSuggestionDepart.html?strAction=suggestionReplyDepart&id="+id+"&differenceInwDepart="+sstrAction+"&departmentId="+departmentId;
       }
       
       function suggestionContentAudit(id){
		     window.location.href="editInwSuggestionDepart.html?strAction=suggestionContentAuditDepart&id="+id+"&differenceInwDepart="+sstrAction+"&departmentId="+departmentId;		     
		}

       
       function checkApplication(theForm){
			theForm.strAction.value=sstrAction;
			if(isFormPosted()){
				theForm.submit();
			}
	  }
		
</script>