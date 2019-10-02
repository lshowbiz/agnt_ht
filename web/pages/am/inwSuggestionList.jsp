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
			theForm.strAction.value="lookSuggestion";
			if(isFormPosted()){
				theForm.submit();
			}
	}
</script> 
<form action="inwSuggestions.html" method="get" name="inwSuggesionForm1" id="inwSuggesionForm1">
    <input type="hidden" id="strAction" name="strAction" value="lookSuggestion"/>
<div class="searchBar">&nbsp;&nbsp;
      <jecs:title key="inwDemand.name"/>
      <input name="name" type="text" value="${param.name }" size="15"/>
      <jecs:title key="inwSuggestion.userCode"/>
      <input name="suggestionUserCode" type="text" value="${param.suggestionUserCode }" size="12"/>
      <jecs:title key="inwSuggestion.suggestedTopics"/>
      <input name="subject" type="text" value="${param.subject }" size="12"/>
      <jecs:title key="inwSuggestion.createTime"/>
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
		<jecs:title key="inwSuggestion.viewStatus"/>
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
    			<ec:column property="name" title="inwDemand.name" />
    			<ec:column property="subject" title="inwSuggestion.suggestedTopics" />
    			<ec:column property="user_code" title="inwSuggestion.userCode" />
    			<ec:column property="create_time" title="inwSuggestion.createTime"/>
    			<ec:column property="1" title="sysOperationLog.moduleName" >
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='inwSuggestions.html?strAction=suggestionQuery&id=${inwSuggestionAndInwDemand.id }&differenceInw=inwDemand';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
						&nbsp;&nbsp;
						<img src="images/ii_4.gif" onclick="window.location.href='editInwSuggestion.html?strAction=suggestionReply&id=${inwSuggestionAndInwDemand.id }&differenceInw=inwDemand';" alt="<jecs:locale key="suggested.reply"/>" style="cursor:pointer"/>
				</ec:column>
				</ec:row>
</ec:table>	
</div>