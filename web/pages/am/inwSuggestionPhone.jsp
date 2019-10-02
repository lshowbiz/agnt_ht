<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwSuggestionList.title"/></title>
<content tag="heading"><jecs:locale key="inwSuggestionList.heading"/></content>
<meta name="menu" content="InwSuggestionMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type="text/javascript">
       function checkApplication(theForm){
			theForm.strAction.value="phoneSuggestion";
			if(isFormPosted()){
				theForm.submit();
			}
	}
</script> 
<form action="inwSuggestions.html" method="get" name="inwSuggesionForm1" id="inwSuggesionForm1">
    <input type="hidden" id="strAction" name="strAction" value="phoneSuggestion"/>
<div class="searchBar">&nbsp;&nbsp;
      <jecs:label key="inwSuggestion.userCode"/>
      <input name="suggestionUserCode" type="text" value="${param.suggestionUserCode }" size="12"/>
      <jecs:label key="inwSuggestion.suggestedTopics"/>
      <input name="subject" type="text" value="${param.subject }" size="12"/>
      <jecs:label key="inwSuggestion.createTime"/>
		 <input id="createTimeBegin" name="createTimeBegin" type="text" size="8" maxlength="10" value="${param.createTimeBegin }"/>
		<img src="./images/calendar/calendar7.gif" id="img_createTimeBegin" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "createTimeBegin", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_createTimeBegin", 
			singleClick    :    true
			}); 
		</script> - 
		<input id="createTimeEnd" name="createTimeEnd" type="text" size="8" maxlength="10" value="${param.createTimeEnd }"/>
		<img src="./images/calendar/calendar7.gif" id="img_createTimeEnd" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "createTimeEnd", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_createTimeEnd", 
			singleClick    :    true
			}); 
		</script>
		<jecs:label key="inwSuggestion.viewStatus"/>
		<jecs:list name="viewstatus" listCode="viewstatus" value="${param.viewstatus}" defaultValue=""/>	
        <input name="search" type="button" class="button" onclick="checkApplication(document.inwSuggesionForm1)" value="<jecs:locale key="operation.button.search"/>"/>
</div>
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
    			<ec:column property="subject" title="inwSuggestion.suggestedTopics" />
    			<ec:column property="user_code" title="inwSuggestion.userCode" />
    			<ec:column property="create_time" title="inwSuggestion.createTime"/>
    			<ec:column property="1" title="title.view" >
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='inwSuggestions.html?strAction=suggestionQuery&id=${inwSuggestionAndInwDemand.id }&differenceInw=inwDemandPhone';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
				</ec:column>
				</ec:row>
</ec:table>	
</div>