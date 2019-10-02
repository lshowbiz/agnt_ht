<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>

<script type="text/javascript">

function checkJmiMemberSubmit(theForm){

	if(!confirm("<jecs:locale key="jmiSubStore.confirm.check"/>"))
	{
		return false;
	}
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strRegisterSuccessCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
		return false;
	}
	theForm.strAction.value="checkJmiMemberSubmit";
			if(isFormPosted()){
				theForm.submit();
			}
}
</script>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="jmiMemberChecks"/>
	<input type="hidden" name="strRegisterSuccessCodes" value=""/>
	<div class="searchBar">
		<div class="new_searchBar"> 
			<jecs:title key="customerFollow.state"/>
			<jecs:list name="status" listCode="mi.status.check" value="${param.status }" defaultValue="" showBlankLine="true" />		
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.joinTimeRange"/>
	 		<input id="createBTime" name="createBTime" type="text" value="${param.createBTime }" 
	 		style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				 - 
			<input id="createETime" name="createETime" type="text" value="${param.createETime }" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>	
		<div  style="margin-left: 65px;" class="new_searchBar">
			<button type="submit" class="btn btn_sele" name="cancel"><jecs:locale key="operation.button.search"/></button>
			<button name="search" class="btn btn_long" type="button" onclick="checkJmiMemberSubmit(miMemberForm)">
				<jecs:locale key="button.audit"/>
			</button>
			<%--<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
			<input name="search" class="button" type="button" onclick="checkJmiMemberSubmit(miMemberForm)" 
			value="<jecs:locale key="button.audit"/>" />--%>
		</div>

</div>



</form>

<ec:table 
	tableId="jmiMemberListTable"
	items="jmiMemberList"
	var="jmiMember"
	action="${pageContext.request.contextPath}/jmiMemberChecks.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
				<ec:row>
				
				<ec:column property="1" title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();>" sortable="false" styleClass="centerColumn">
					<c:if test="${jmiMember.status=='1' }">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jmiMember.user_code}" class="checkbox" />
					</c:if>
				</ec:column>
				
				
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<ec:column property="mi_user_name" title="bdCalculatingSubDetail.name" />
    			<ec:column property="create_time" title="miMember.createTime" />
    			
    			<ec:column property="status" title="customerFollow.state" >
    				
    					<jecs:code listCode="mi.status.check" value="${jmiMember.status }"/>
    			</ec:column>
    			<ec:column property="busi.user.check" title="miMember.papernumber" />
    			<ec:column property="check_time" title="logType.BC" />
    			<ec:column property="check_user" title="busi.user.check" />
    			
    			
				</ec:row>

</ec:table>	

