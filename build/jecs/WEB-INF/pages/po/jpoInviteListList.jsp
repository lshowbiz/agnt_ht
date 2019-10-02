<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoInviteListList.title"/></title>
<content tag="heading"><jecs:locale key="jpoInviteListList.heading"/></content>
<meta name="menu" content="JpoInviteListMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">
	<input type="submit" class="btn btn_sele" value="<jecs:locale  key='operation.button.search'/>"/>
	<input type="button" class="btn btn_sele" onclick="batchJpoInviteList()" value="<jecs:locale  key='button.editAll.xls'/>"/>
</c:set>
<form name="frm" action="<c:url value='jpoInviteLists.html'/>" >
<div class="newsearchBar">
<jecs:label  key="miMember.memberNo"/>
<input name="userCode" id="userCode" value="<c:out value='${param.userCode}'/>" cssClass="text medium"/>	
<jecs:label  key="jpoInviteList.inviteCode"/>
<input name="inviteCode" id="inviteCode" value="<c:out value='${param.inviteCode}'/>" cssClass="text medium"/>	
<jecs:label  key="poMemberOrder.memberOrderNo"/>
<input name="memberOrderNo" id="memberOrderNo" value="<c:out value='${param.memberOrderNo}'/>" cssClass="text medium"/>	

<jecs:label key="pd.createTime"/>
<input name="createTimeStart" size='11' id="createTimeStart"  value="${param.createTimeStart}">
   	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
	<script type="text/javascript"> 
		Calendar.setup({
		inputField     :    "createTimeStart", 
		ifFormat       :    "%Y-%m-%d",  
		button         :    "img_startTime", 
		singleClick    :    true
		}); 
	</script>
	-<input name="createTimeEnd" size='11' id="createTimeEnd"  value="${param.createTimeEnd}">
   	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
	<script type="text/javascript"> 
		Calendar.setup({
		inputField     :    "createTimeEnd", 
		ifFormat       :    "%Y-%m-%d",  
		button         :    "img_endTime", 
		singleClick    :    true
		}); 
	</script>
<jecs:label  key="schedule.state"/>
<jecs:list listCode="po.invitelist.status" name="status" id="status" showBlankLine="true" value="${param.status}" defaultValue=""/>	
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoInviteListListTable"
	items="jpoInviteLists"
	var="jpoInviteList"
	action="${pageContext.request.contextPath}/jpoInviteLists.html"
	width="100%"
	form="frm"
	method="post"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="inviteCode" title="jpoInviteList.inviteCode" />
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="createTime" title="pd.createTime" />
    			<ec:column property="status" title="schedule.state" >
    			   <jecs:code listCode="po.invitelist.status" value="${jpoInviteList.status }"></jecs:code>
    			</ec:column>
    			<ec:column property="useUserCode" title="jpoInviteList.useUserCode" />
    			<ec:column property="useTime" title="jpoInviteList.useTime" />
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">

   function editJpoInviteList(id){
   		<jecs:power powerCode="editJpoInviteList">
					window.location="editJpoInviteList.html?strAction=editJpoInviteList&id="+id;
			</jecs:power>
		}
		
	function batchJpoInviteList(){
	     window.location = "batchJpoInviteList.html";
		//window.location = "jpoInviteListBatch.html";
	}

</script>
