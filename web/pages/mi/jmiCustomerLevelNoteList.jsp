<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiCustomerLevelNoteList.title"/></title>
<content tag="heading"><jecs:locale key="jmiCustomerLevelNoteList.heading"/></content>
<meta name="menu" content="JmiCustomerLevelNoteMenu"/>



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
			if(isFormPosted()){
				theForm.submit();
			}
}

function reSendStr(theForm,strAction){
	$('strAction').value=strAction;
			if(isFormPosted()){
				theForm.submit();
			}
}

</script>

<form action="jmiCustomerLevelNotes.html" method="post" name="miMemberUpgradeNoteForm" id="miMemberUpgradeNoteForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:locale key="miMember.memberNo" />
			<input name="userCode" type="text" size="10" value="${param.userCode }"/>	
		</div>
		<div class="new_searchBar">
			<button name="search" type="submit" class="btn btn_sele">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_ins" type="button"
				onclick="location.href='<c:url value="editJmiCustomerLevelNote.html"/>'">
				<jecs:locale key="member.new.num"/>
			</button>
			<%-- 
			<input name="search" type="submit" class="button" value="<jecs:locale key="operation.button.search"/>"/> 
			<input name="search" class="button" type="button" onclick="location.href='<c:url value="editJmiCustomerLevelNote.html"/>'" value="<jecs:locale key="member.new.num"/>" />
			--%>
		</div>

		<input type="hidden" id="strAction" name="strAction" value="jmiCustomerLevelNotes"/>
		<input type="hidden" name="strRegisterSuccessCodes" value=""/>

		<jecs:power powerCode="sendToPcnModifyLevel">
			<div class="new_searchBar">
				<jecs:list name="status" listCode="yesno" value="${param.status}" 
					defaultValue="" showBlankLine="true"/>
				<button name="search" class="btn btn_long" type="button" 
					onclick="reSend(document.miMemberUpgradeNoteForm,'sendToPcnModifyLevel')">
				PCN SEND
				</button>
				<%-- 
				<input name="search" class="button" type="button" 
				onclick="reSend(document.miMemberUpgradeNoteForm,'sendToPcnModifyLevel')" value="PCN SEND" />
				--%>
			</div>
		</jecs:power>
</div>



</form>

<ec:table 
	tableId="jmiCustomerLevelNoteListTable"
	items="jmiCustomerLevelNoteList"
	var="jmiCustomerLevelNote"
	action="${pageContext.request.contextPath}/jmiCustomerLevelNotes.html"
	width="100%"
	autoIncludeParameters="true"	
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
				
				<ec:column property="1" title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();>" sortable="false" styleClass="centerColumn">
					<c:if test="${jmiCustomerLevelNote.status=='1' }">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jmiCustomerLevelNote.id}" class="checkbox"/>
					</c:if>
				</ec:column>
				
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="oldCustomerLevel" title="miMemberUpgradeNote.oldCard" >
    				<jecs:code listCode="bd.customerlevel" value="${jmiCustomerLevelNote.oldCustomerLevel}"/>
    			</ec:column>
    			<ec:column property="newCustomerLevel" title="miMemberUpgradeNote.newCard" >
    				<jecs:code listCode="bd.customerlevel" value="${jmiCustomerLevelNote.newCustomerLevel}"/>
    			</ec:column>
    			<ec:column property="remark" title="busi.common.remark" />
    			
    			
<jecs:power powerCode="sendToPcnModifyLevel">
	
    			<ec:column property="sendRemark" title="busi.common.remark" />
    			<ec:column property="status" title="busi.common.remark" />
       
</jecs:power>
    			
    			
				</ec:row>

</ec:table>	


