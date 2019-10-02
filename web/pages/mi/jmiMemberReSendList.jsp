<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript">

function reSend(theForm,strAction){
	if(!confirm("<jecs:locale key="bdSendRegister.confirm.register"/>"))
	{
		return false;
	}
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
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
		
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	$('strAction').value=strAction;
	var i = 0;
	if(isFormPosted() && i==0){
		theForm.submit();
		i += 1;
	}
}

function reSendStr(theForm,strAction){
	$('strAction').value=strAction;
	var i = 0;
	var result = isFormPosted();
	if(result && i==0){
		theForm.submit();
		i += 1;
	} 
}

</script>

<c:if test="${not empty errorList}">
	<div class="error" id="errorMessages">

		<c:forEach var="error" items="${errorList}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>
<form action="jmiMemberReSend.html" method="post" name="miMemberForm" id="miMemberForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:list name="sendStatus" listCode="interface.type" value="${param.sendStatus}" defaultValue="2"/>
			
        </div>
		<div class="new_searchBar">
			<input name="userCodeStr" id="userCodeStr" type="text"/>
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_long" onclick="reSendStr(document.miMemberForm,'reSendPCN')">
				PCN SEND str
			</button>
			<button name="search" class="btn btn_long" onclick="reSend(document.miMemberForm,'reSendPCN')">
				PCN SEND
			</button>
		</div>

	</div>

<input type="hidden" id="strAction" name="strAction" value="jmiMemberReSend"/>
<input type="hidden" name="strRegisterSuccessCodes" value=""/>


</form>

<ec:table 
	tableId="jmiMemberListTable"
	items="jmiMemberList"
	var="jmiMember"
	action="${pageContext.request.contextPath}/jmiMemberReSend.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
				<ec:row>
				
				
				<ec:column property="1" title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();>" sortable="false" styleClass="centerColumn">
					<input type="checkbox" name="selectedId" id="selectedId" value="${jmiMember.userCode}" class="checkbox"/>
				</ec:column>
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="miUserName" title="bdCalculatingSubDetail.name" />
    			<ec:column property="cardType" title="bdSendRecord.cardType">
    				<jecs:code listCode="bd.cardtype" value="${jmiMember.cardType }"/>
    			</ec:column>

    			<ec:column property="createTime" title="miMember.createTime" />
    			<ec:column property="sysUser.remark" title="miMember.remark" />
    			
				</ec:row>

</ec:table>	

