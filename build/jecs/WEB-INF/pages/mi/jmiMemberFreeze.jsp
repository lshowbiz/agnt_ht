<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="jmiMemberFreeze"/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:label key="bdCalculatingSubDetail.name"/>
			<input name="miUserName" type="text" value="${param.miUserName}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:label key="miMember.papernumber"/>
			<input name="papernumber" type="text" value="${param.papernumber}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:label key="miMember.joinTimeRange"/>
	 		<input id="createBTime" name="createBTime" type="text" value="${param.createBTime }"
	 		style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				 - 
			<input id="createETime" name="createETime" type="text" value="${param.createETime }"
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<button type="submit" class="btn btn_sele" name="cancel">
				<jecs:locale key="operation.button.search"/>
			</button>
			<%--<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />--%>
		</div>
</div>

</form>

<ec:table 
	tableId="jmiMemberListTable"
	items="jmiMemberList"
	var="jmiMember"
	action="${pageContext.request.contextPath}/jmiMemberFreeze.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
				<ec:row>
				
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<ec:column property="mi_user_name" title="miMember.miUserName" />
    			<ec:column property="papernumber" title="miMember.papernumber" />
    			<ec:column property="update_time" title="miMember.createTime" />
    			
				</ec:row>

</ec:table>	

