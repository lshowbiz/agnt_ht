<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="memberSearchM"/>
	<div class="searchBar">
	
	&nbsp;&nbsp;
	<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
<%--<jecs:title key="bdCalculatingSubDetail.name"/>
		<input name="userName" type="text" value="${param.userName}" size="10"/>	
<jecs:title key="miMember.petName"/>
		<input name="petName" type="text" value="${param.petName}" size="10"/>
<jecs:title key="miMember.papernumber"/>
		<input name="papernumber" type="text" value="${param.papernumber}" size="10"/>	
<jecs:title key="miMember.cardType"/>
		
         <jecs:list name="cardType" listCode="bd.cardtype" value="${param.cardType}" defaultValue="" showBlankLine="true"/>	
<jecs:title key="bdReconsumMoney.orderDateScope"/>
				 <input id="checkBDate" name="checkBDate" type="text" size="8" maxlength="10" value="${param.checkBDate }"/>
				<img src="./images/calendar/calendar7.gif" id="img_checkBDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "checkBDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_checkBDate", 
					singleClick    :    true
					}); 
				</script> - 
				<input id="checkEDate" name="checkEDate" type="text" size="8" maxlength="10" value="${param.checkEDate }"/>
				<img src="./images/calendar/calendar7.gif" id="img_checkEDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "checkEDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_checkEDate", 
					singleClick    :    true
					}); 
				</script>
<jecs:title key="miMember.joinTimeRange"/>
	 <input id="createBTime" name="createBTime" type="text" size="8" maxlength="10" value="${param.createBTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_createBTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createBTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_createBTime", 
					singleClick    :    true
					}); 
				</script> - 
				<input id="createETime" name="createETime" type="text" size="8" maxlength="10" value="${param.createETime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_createETime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createETime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_createETime", 
					singleClick    :    true
					}); 
				</script>--%>

		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
</div>



</form>

<ec:table 
	tableId="jmiMemberListTable"
	items="jmiMemberList"
	var="jmiMember"
	action="${pageContext.request.contextPath}/jmiMemberMs.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
				<ec:row>
				
				
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="petName" title="miMember.petName" />
    			<%--<c:if test="${sessionScope.sessionLogin.userType=='C'}">
    				<ec:column property="miUserName" title="bdCalculatingSubDetail.name" />
    			</c:if>--%>
    			<ec:column property="cardType" title="bdSendRecord.cardType">
    				<jecs:code listCode="bd.cardtype" value="${jmiMember.cardType }"/>
    			</ec:column>
    			<ec:column property="recommendNo" title="miMember.recommendNo" />
    			<ec:column property="papernumber" title="miMember.papernumber" />
    			<ec:column property="createTime" title="miMember.createTime" />
    			<ec:column property="1" title="title.operation" sortable="false" width="150px">
					
					
					
					<jecs:power powerCode="miMemberDetailViewM">
					<img src="images/newIcons/search_16.gif" onclick="window.location.href='miMemberDetailViewM.html?memberNo=${jmiMember.userCode}';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					
				</ec:column>
				</ec:row>

</ec:table>	

