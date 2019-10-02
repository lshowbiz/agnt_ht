<%@ include file="/common/taglibs.jsp"%>

<title><jecs:title  key="jpmProductSaleNewDetail.title" />
</title>
<content tag="heading">
<jecs:title  key="jpmProductSaleNewDetail.heading" />
</content>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type='text/javascript'
	src='<c:url value="/dwr/interface/jocsInterfaceRetransmissionManager.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>


<form name="frm" id="frm"action="<c:url value='/jocsInterfaceRetransmissions.html'/>">
<div class="searchBar">
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}" />
	<c:choose>
		<c:when test="${sessionLogin.userCode=='root'}">
		<div class="new_searchBar">
			<jecs:title  key="jocsInterfaceLog.method"/>
			<input name="method" id="method" value="<c:out value='${param.method}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">
			<jecs:title  key="amAnnounce.content"/>
			<input name="content" id="content" value="<c:out value='${param.content}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">
			<jecs:title  key="jocsInterfaceLog.logType" />
			<jecs:list listCode="jocsinterfacelog.logtype" name="logType" showBlankLine="true" id="logType" value="${param.logType}" defaultValue=""/>
		</div>
		<div class="new_searchBar">
			<jecs:title  key="jocsInterfaceLog.flag" />
			<jecs:list listCode="jocsinterfacelog.flag" name="flag" showBlankLine="true" id="flag" value="${param.flag}" defaultValue=""/>
		</div>
		<div class="new_searchBar">
			<jecs:title  key="jocsInterfaceLog.reciverTime" />
			<input name="logStartTime" id="logStartTime" type="text" 
			value="${logStartTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			- 
			<input name="logEndTime" id="logEndTime" type="text" 
			value="${logEndTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">  
			<jecs:title  key="jocsInterfaceLog.retranType" />
			<jecs:list listCode="jocsinterfaceretransmission.returntype" name="retranType" showBlankLine="true" id="retranType" value="${param.retranType}" defaultValue=""/>
		</div>
		<div class="new_searchBar">
		<jecs:title  key="jocsInterfaceLog.retranStatus" />
			<jecs:list listCode="jocsinterfaceretransmission.returnstatus" name="retranStatus" showBlankLine="true" id="retranStatus" value="${param.retranStatus}" defaultValue=""/>
		</div>
		<div class="new_searchBar" style="margin-left:50px;">
			<button type="button" class="btn btn_long" onclick="batchPrint();" >
				<jecs:locale  key='memu.pd.batchretran'/>
			</button>
			
			<button type="submit" class="btn btn_sele">
				<jecs:locale  key='operation.button.search'/>
			</button>
		</div>
		</c:when>
		<c:otherwise>
			
		<div class="new_searchBar" style="margin-left:50px;">
			<button type="button" class="btn btn_long" onclick="batchPrint();" >
				<jecs:locale  key='memu.pd.batchretran'/>
			</button>
			<button type="submit" class="btn btn_sele">
				<jecs:locale  key='operation.button.search'/>
			</button>
		</div>
		</c:otherwise>
	</c:choose>
</div>	
		<ec:table tableId="jocsInterfaceRetransmissionListTable"
			items="jocsInterfaceRetransmissionList"
			var="jocsInterfaceRetransmission"
			action="${pageContext.request.contextPath}/jocsInterfaceRetransmissions.html"
			width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
			sortable="false" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif"  form="frm">
			<ec:row>
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<c:if test="${jocsInterfaceRetransmission.retranStatus==0}">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jocsInterfaceRetransmission.logId}" class="checkbox" checked="checked"/>
					</c:if>
				</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false"
					viewsAllowed="html">
					<c:if test="${jocsInterfaceRetransmission.retranStatus==0 && sessionLogin.userCode=='root'}">
						<img src="<c:url value='/images/icons/editIcon.gif'/>"
							onclick="javascript:editJocsInterfaceRetransmission('${jocsInterfaceRetransmission.logId}')"
							style="cursor: pointer;" title='<jecs:title key="button.edit"/>'/>
						<input type="button" value="<jecs:locale  key='jocsInterfaceRetransmission.retry'/>" onclick="retran('${jocsInterfaceRetransmission.logId}');"/>
					</c:if>
				</ec:column>
				<ec:column property="logType" title="jocsInterfaceLog.logType" >
	    			<jecs:code listCode="jocsinterfacelog.logtype" value="${jocsInterfaceRetransmission.logType}"/>
	    		</ec:column>
				<ec:column property="flag" title="jocsInterfaceLog.flag" >
	    			<jecs:code listCode="jocsinterfacelog.flag" value="${jocsInterfaceRetransmission.flag}"/>
	    		</ec:column>
				<ec:column property="method" title="jocsInterfaceLog.method" />
				<ec:column property="returnResult" title="jocsInterfaceLog.returnResult" />
				<ec:column property="returnDesc" title="jocsInterfaceLog.returnDesc" />
				<ec:column property="reciverTime" title="jocsInterfaceLog.reciverTime" >
	   				<fmt:formatDate value="${jocsInterfaceRetransmission.reciverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	   			</ec:column>
				<ec:column property="retranType" title="jocsInterfaceRetransmission.returnType" >
	    			<jecs:code listCode="jocsinterfaceretransmission.returntype" value="${jocsInterfaceRetransmission.retranType}"/>
	    		</ec:column>
	   			<ec:column property="retranStatus" title="jocsInterfaceRetransmission.returnStatus" >
	    			<jecs:code listCode="jocsinterfaceretransmission.returnstatus" value="${jocsInterfaceRetransmission.retranStatus}"/>
	    		</ec:column>
	   			<ec:column property="retranTime" title="jocsInterfaceRetransmission.returnTime" >
	   				<fmt:formatDate value="${jocsInterfaceRetransmission.retranTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	   			</ec:column>
				<ec:column property="retranCode"
					title="jocsInterfaceRetransmission.returnCode" />
				<ec:column property="retranReason"
					title="jocsInterfaceRetransmission.returnReason" />
				<ec:column property="sender" title="sender" >
	    			<c:choose>
	    				<c:when test="${jocsInterfaceRetransmission.sender=='1'}">SCH</c:when>
	    				<c:when test="${jocsInterfaceRetransmission.sender=='2'}">OMS</c:when>
	    				<c:when test="${jocsInterfaceRetransmission.sender=='3'}">CRM</c:when>
	    				<c:when test="${jocsInterfaceRetransmission.sender=='4'}">GHB</c:when>
	    				<c:when test="${jocsInterfaceRetransmission.sender=='5'}">XW</c:when>
	    			</c:choose>
	    		</ec:column>
			</ec:row>
		</ec:table>
</form>
<script type="text/javascript">
		
   function editJocsInterfaceRetransmission(logId){
 	   <jecs:power powerCode="editJocsInterfaceRetransmission">
			window.location="editJocsInterfaceRetransmission.html?strAction=editJocsInterfaceRetransmission&logId="+logId;
	   </jecs:power>
   }

   function retran(logId){
	   var str = jocsInterfaceRetransmissionManager.retranInterfaceInfo(logId,validatePdHurry);
   }

   function validatePdHurry(ret){
	   alert("<jecs:title  key='schedule.completed'/>");
	   window.location.reload();
   }

   function batchPrint(){
  	    var selectedId = document.getElementsByName("selectedId");
		var selectStr = '';
		for(var i=0;i<selectedId.length;i++){ 
			if(selectedId[i].checked){
				selectStr += selectedId[i].value+",";
			}
		}  
		if(selectStr==''){
			alert("<jecs:title  key='amMessage.discuss.select'/>");//?
			return;
		} 
		var str = jocsInterfaceRetransmissionManager.retranInterfaceInfo(selectStr,validatePdHurry2);
   }

   function validatePdHurry2(ret){
   	   if(ret=='2'){
   	   		alert("<jecs:title  key='jocs.schedule.completed'/>");
   	   }
	   alert("<jecs:title  key='schedule.completed'/>");
	   window.location.reload();
   }
</script>
