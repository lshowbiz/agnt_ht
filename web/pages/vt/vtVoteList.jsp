<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function selectAll(status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function checkSelected(theForm,strActionOperation){
	if(!confirm("<jecs:locale key="bdBounsDeduct.confirm.checkSelected"/>"))
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
			theForm.strAdvicesCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdBounsDeduct"/>");
		return false;
	}
	theForm.strAction.value=strActionOperation;
	theForm.strActionOperation.value="strActionOperation";
	theForm.submit();
}
</script>
<title><jecs:locale key="vtVoteList.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteList.heading"/></content>
<meta name="menu" content="VtVoteMenu"/>

<form action="" method="get" name="voteForm" id="voteForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="strActionOperation" value=""/>
<input type="hidden" name="strAdvicesCodes" value=""/>

<div id="titleBar" class="searchBar">
	<div class="new_searchBar">
			<jecs:title key="customerRecord.subject"/>
			<input name="subject" type="text" value="${param.subject}" size="10"/>	
	</div>
		<c:if test="${empty param.strAction }">
			<div class="new_searchBar">
				<button type="submit" class="btn btn_sele" style="width:auto" name="cancel" ><jecs:locale key="operation.button.search"/></button>
			</div>
		</c:if>
		<c:if test="${param.strAction=='vtVotes' }">
			<div class="new_searchBar">
				<button type="submit" class="btn btn_sele" style="width:auto" name="cancel" ><jecs:locale key="operation.button.search"/></button>
				<jecs:power powerCode="addVtVote">
			    	<button type="button" class="btn btn_ins" style="width:auto"
			       	 	onclick="location.href='<c:url value="/editVtVote.html"/>?strAction=addVtVote'"><jecs:locale key="button.add"/></button>
				</jecs:power>
			</div>
		</c:if>
		<c:if test="${param.strAction=='auditVote' }">
			<div class="new_searchBar">
				<button type="submit" class="btn btn_sele" style="width:auto" name="cancel" ><jecs:locale key="operation.button.search"/></button>
				<jecs:power powerCode="auditVtVote">
				    <button type="button" class="btn btn_long" style="width:auto" onclick="checkSelected(this.form,'auditVtVote')">
				    	<jecs:locale key="button.audit"/>
				    </button>
				</jecs:power>
				<jecs:power powerCode="toPendingVtVote">
				    <button type="button" class="btn btn_long" style="width:auto" onclick="checkSelected(this.form,'toPendingVtVote')">
				    	<jecs:locale key="toPendingVtVote"/>
				    </button>
				</jecs:power>
		
				<jecs:power powerCode="cacelVtVote">
				    <button type="button" class="btn btn_long" style="width:auto" onclick="checkSelected(this.form,'cacelVtVote')">
				    	<jecs:locale key="operation.button.cancel"/>
				    </button>
				</jecs:power>
				
				
				<jecs:power powerCode="onVtVote">
				    <button type="button" class="btn btn_long" style="width:auto" onclick="checkSelected(this.form,'onVtVote')">
				    	<jecs:locale key="msnstatus.1"/>
				    </button>
				</jecs:power>
				<jecs:power powerCode="offVtVote">
				    <button type="button" class="btn btn_long" style="width:auto" onclick="checkSelected(this.form,'offVtVote')">
				    	<jecs:locale key="msnstatus.0"/>
				    </button>
				</jecs:power>
			</div>
		</c:if>
</div>

<!--  
<div id="titleBar" class="searchBar">
			<jecs:title key="customerRecord.subject"/>
			<input name="subject" type="text" value="${param.subject}" size="10"/>	

			<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
			<c:if test="${param.strAction=='vtVotes' }">
		<jecs:power powerCode="addVtVote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editVtVote.html"/>?strAction=addVtVote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
		
		</c:if>
		<c:if test="${param.strAction=='auditVote' }">
			
			<jecs:power powerCode="auditVtVote">
				    <input type="button" class="button" style="margin-right: 5px" onclick="checkSelected(this.form,'auditVtVote')" value="<jecs:locale key="button.audit"/>"/>
			</jecs:power>
			<jecs:power powerCode="toPendingVtVote">
				    <input type="button" class="button" style="margin-right: 5px" onclick="checkSelected(this.form,'toPendingVtVote')" value="<jecs:locale key="toPendingVtVote"/>"/>
			</jecs:power>
			<jecs:power powerCode="cacelVtVote">
				    <input type="button" class="button" style="margin-right: 5px" onclick="checkSelected(this.form,'cacelVtVote')" value="<jecs:locale key="operation.button.cancel"/>"/>
			</jecs:power>
			<jecs:power powerCode="onVtVote">
				    <input type="button" class="button" style="margin-right: 5px" onclick="checkSelected(this.form,'onVtVote')" value="<jecs:locale key="msnstatus.1"/>"/>
			</jecs:power>
			<jecs:power powerCode="offVtVote">
				    <input type="button" class="button" style="margin-right: 5px" onclick="checkSelected(this.form,'offVtVote')" value="<jecs:locale key="msnstatus.0"/>"/>
			</jecs:power>
		</c:if>
</div>
-->
</form>


<ec:table 
	tableId="vtVoteListTable"
	items="vtVoteList"
	var="vtVote"
	action="${pageContext.request.contextPath}/vtVotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<c:if test="${param.strAction=='auditVote' }">
					<ec:column property="2" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" >
						<input type="checkbox" name="selectedId" id="selectedId" value="${vtVote.vtId}" class="checkbox"/>
					</ec:column>
				</c:if>
    			<ec:column property="subject" title="customerRecord.subject" />
    			<ec:column property="optionNum" title="vtVote.optionNum" />
    			<ec:column property="startTime" title="common.startTime" />
    			<ec:column property="endTime" title="customerFollow.endTime" />
    			<ec:column property="status" title="customerFollow.state" >
    				<jecs:code listCode="vote.status" value="${vtVote.status }"/>
    			</ec:column>
    			<ec:column property="state" title="sysModule.isActive" >
    				<jecs:code listCode="msnstatus" value="${vtVote.state }"/>
    			</ec:column>
    			<ec:column property="createTime" title="pd.createTime" />
    			<ec:column property="createUser" title="pd.createUserNo" />
    			
					<ec:column property="1" title="title.operation" sortable="false" width="100" >
						<c:if test="${param.strAction=='vtVotes' && vtVote.status=='1'}">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editVtVote('${vtVote.vtId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
						</c:if>
						
					<jecs:power powerCode="viewVote">
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='editVtVote.html?strAction=viewVtVote&vtId=${vtVote.vtId }';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editVtVote(vtId){
   		<jecs:power powerCode="editVtVote">
					window.location="editVtVote.html?strAction=editVtVote&vtId="+vtId;
			</jecs:power>
		}

</script>
