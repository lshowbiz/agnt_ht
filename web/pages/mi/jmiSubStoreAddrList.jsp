<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiSubStoreList.title"/></title>
<content tag="heading"><jecs:locale key="jmiSubStoreList.heading"/></content>
<meta name="menu" content="JmiSubStoreMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

		
<script type="text/javascript" >

function checkStore(theForm,strAction){
	if(!confirm("<jecs:locale key="jmiSubStore.confirm.check"/>"))
	{
		return false;
	}
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		alert("<jecs:locale key="amMessage.discuss.select"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="amMessage.discuss.select"/>");
		return false;
	}
	$('strAction').value=strAction;
			if(isFormPosted()){
				theForm.submit();
			}
}

</script>
		

<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div class="searchBar">
	<input type="hidden" id="strAction" name="strAction" value="jmiSubStores"/>
	<input type="hidden" name="strCodes" value=""/>
	<input name="xslText" type="hidden" id="xslText" value="">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<div class="new_searchBar"> 
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		</div>
		<div class="new_searchBar"> 
			<jecs:title key="customerFollow.state"/>
	        <jecs:list name="subStoreStatus" listCode="jmisubstore.status" 
	        value="${param.subStoreStatus}" defaultValue="" showBlankLine="true"/>
        </div>
        
        <div class="new_searchBar"> 
			<jecs:title key="pd.okstatus"/>
	        <jecs:list name="confirmStatus" listCode="jmisubstore.confirmstatus" 
	        value="${param.confirmStatus}" defaultValue="" showBlankLine="true"/>	
		</div>
		<div class="new_searchBar"> 
		 	<jecs:list listCode="store.timetype" name="timetype" value="${param.timetype}" 
		 		style="width:80px" defaultValue="createTime"/>
			<%-- <jecs:title key="label.dateselect.ex"/>--%>
			<input id="startTime" name="startTime" type="text" value="${param.startTime }" 
			style="cursor: pointer;width:77px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			 - 
			<input id="endTime" name="endTime" type="text" value="${param.endTime }" 
			style="cursor: pointer;width:77px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar" style="margin-left: 70px">
			<button type="submit" class="btn btn_sele" name="cancel" onclick="document.getElementById('xslText').value='';" >
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_long" type="submit" onclick="document.getElementById('xslText').value='xslText';">
				<jecs:locale key="toolbar.text.xls"/>
			</button>
			<%-- 
				<input type="submit" class="btn btn_long" onclick="document.getElementById('xslText').value='';" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
				<input name="search" class="btn btn_sele" type="submit" onclick="document.getElementById('xslText').value='xslText';" value="<jecs:locale key="toolbar.text.xls"/>" />
			--%>
		</div>
	</c:if>
	
			<%--<jecs:power powerCode="checkJmiSubStore">
				<jecs:locale key="jmiSubStore.checkRemark"/>:<textarea name="checkRemark" rows="1" cols="30" onclick="javascript:this.rows=5;"></textarea>
			</jecs:power>
			
			<jecs:power powerCode="checkJmiSubStore">
				<input name="checkJmiSubStore" class="button" type="button" onclick="checkStore(document.miMemberForm,'checkJmiSubStore')" value="<jecs:locale key="button.audit"/>" />
			</jecs:power>
			<jecs:power powerCode="unCheckJmiSubStore">
				<input name="checkJmiSubStore" class="button" type="button" onclick="checkStore(document.miMemberForm,'unCheckJmiSubStore')" value="<jecs:locale key="button.notCheck"/>" />
			</jecs:power>
			
			<jecs:power powerCode="confirmJmiSubStore">
				<jecs:locale key="jmiSubStore.confirmRemark"/>:<textarea name="confirmRemark" rows="1" cols="30" onclick="javascript:this.rows=5;"></textarea>
			</jecs:power>
			<jecs:power powerCode="confirmJmiSubStore">
				<input name="checkJmiSubStore" class="button" type="button" onclick="checkStore(document.miMemberForm,'confirmJmiSubStore')" value="<jecs:locale key="operation.button.confirm"/>" />
			</jecs:power>
			<jecs:power powerCode="unConfirmJmiSubStore">
				<input name="checkJmiSubStore" class="button" type="button" onclick="checkStore(document.miMemberForm,'unConfirmJmiSubStore')" value="<jecs:locale key="button.bdUnCheck"/>" />
			</jecs:power>--%>
</div>
</form>
	
<ec:table 
	tableId="jmiSubStoreListTable"
	items="jmiSubStoreList"
	var="jmiSubStore"
	action="${pageContext.request.contextPath}/jmiSubStoreAddrs.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<%--<c:if test="${sessionScope.sessionLogin.userType=='C' }">
				
	    			<ec:column property="2" title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();>" sortable="false" styleClass="centerColumn">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jmiSubStore.id}" class="checkbox" />
					</ec:column>
				
				</c:if>--%>
				
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<ec:column property="jmiMember.petName" title="miMember.petName" />
    			 
    			<ec:column property="jmiMember.cardType" title="bdSendRecord.cardType">
    				<jecs:code listCode="member.level" value="${jmiSubStore.jmiMember.memberLevel }"/>
    			</ec:column>
    			<%-- 
    			<ec:column property="jmiMember.subRecommendStore" title="miMember.subRecommendStore" />
    			--%>
    			<ec:column property="createTime" title="miMember.createTime" format="yyyy-MM-dd HH:mm:ss" cell="date"/>
    			<%-- 
    			<ec:column property="editTime" title="busi.order.editTime" />
    			<ec:column property="jmiSubStore.jmiMember.subStoreCheckDate" title="jmiSubStore.subStoreCheckDate" >
    				
    				<fmt:formatDate value="${jmiSubStore.jmiMember.subStoreCheckDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			
    			
    			--%>
    			<%-- 
				<c:if test="${sessionScope.sessionLogin.userType=='C' }">
    			<ec:column property="addrCheckUser" title="jmiSubStore.addrCheckUser" />
    			</c:if>
    			
    			<ec:column property="checkRemark" title="jmiSubStore.checkRemark" />
    			
				<c:if test="${sessionScope.sessionLogin.userType=='C' }">
    			<ec:column property="addrConfirmUser" title="jmiSubStore.addrConfirmUser" />
    			</c:if>
    			--%>
    			<ec:column property="jmiSubStore.addrCheck" title="customerFollow.state" >
    				 <jecs:code listCode="jmisubstore.status" value="${jmiSubStore.addrCheck}"/>
    			</ec:column>
    			<ec:column property="jmiMember.addrConfirm" title="pd.okstatus" >
    				 <jecs:code listCode="jmisubstore.confirmstatus" value="${jmiSubStore.addrConfirm}"/>
    			</ec:column>
    			
    			<ec:column property="confirmRemark" title="jmiSubStore.confirmRemark" />
	    		
				<jecs:power powerCode="businessLiceseJmiSubStore">
	    			<ec:column property="businessLicese" title="jmiSubStore.businessLicese" >
	    				<c:if test="${jmiSubStore.businessLicese=='0' }"><a href="jmiSubStoreAddrs.html?strAction=businessLiceseJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="operation.button.confirm"/></a></c:if>
	    				<c:if test="${jmiSubStore.businessLicese=='1' }"><a href="jmiSubStoreAddrs.html?strAction=businessLiceseJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="busi.cancel"/></a></c:if>
	    			</ec:column>
	    		</jecs:power>
				<jecs:power powerCode="contracJmiSubStore">
    			<ec:column property="contract" title="jmiSubStore.contract" >
    				<c:if test="${jmiSubStore.contract=='0' }"><a href="jmiSubStoreAddrs.html?strAction=contracJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="operation.button.confirm"/></a></c:if>
    				<c:if test="${jmiSubStore.contract=='1' }"><a href="jmiSubStoreAddrs.html?strAction=contracJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="busi.cancel"/></a></c:if>
    			</ec:column>
	    		</jecs:power>
    			
				<jecs:power powerCode="storePicJmiSubStore">
	    			<ec:column property="storePic" title="jmiSubStore.storePic" >
	    				<c:if test="${jmiSubStore.storePic=='0' }"><a href="jmiSubStoreAddrs.html?strAction=storePicJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="operation.button.confirm"/></a></c:if>
	    				<c:if test="${jmiSubStore.storePic=='1' }"><a href="jmiSubStoreAddrs.html?strAction=storePicJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="busi.cancel"/></a></c:if>
	    			</ec:column>
	    		</jecs:power>
    			
				<jecs:power powerCode="noticeTimeJmiSubStore">
	    			<ec:column property="noticeTime" title="jmiSubStore.noticeTime" >
	    				<c:if test="${empty jmiSubStore.noticeTime }">
	    				<a href="jmiSubStoreAddrs.html?strAction=noticeTimeJmiSubStore&id=${jmiSubStore.id }" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><jecs:locale key="busi.notice"/></a>
	    				</c:if>
	    				<c:if test="${not empty jmiSubStore.noticeTime }">
	    					${jmiSubStore.noticeTime  }
	    				</c:if>
	    			</ec:column>
	    		</jecs:power>
    			
    			<ec:column property="1" title="title.operation" sortable="false">
					
					
					<jecs:power powerCode="viewJmiSubStoreAddr">
					<img src="images/newIcons/search_16.gif" onclick="window.location.href='editJmiSubStoreAddr.html?id=${jmiSubStore.id}&strAction=viewJmiSubStoreAddr';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					<jecs:power powerCode="editJmiSubStoreAddr">
						<c:if test="${sessionScope.sessionLogin.userType=='M' && jmiSubStore.addrCheck!='1' }">
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiSubStoreAddr.html?id=${jmiSubStore.id}&strAction=editJmiSubStoreAddr';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						</c:if>
						<c:if test="${sessionScope.sessionLogin.userType=='C'  }">
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiSubStoreAddr.html?id=${jmiSubStore.id}&strAction=editJmiSubStoreAddr';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						</c:if>
						
					<%--<c:if test="${sessionScope.sessionLogin.userType=='C'}">		
							<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiSubStore.html?id=${jmiSubStore.id}&strAction=editJmiSubStore';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.userType=='M'}">
						<c:if test="${jmiSubStore.jmiMember.subStoreStatus=='0'||jmiSubStore.jmiMember.subStoreStatus=='3' }">
						
							<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiSubStore.html?id=${jmiSubStore.id}&strAction=editJmiSubStore';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
						</c:if>
					</c:if>--%>
					
					</jecs:power>
					
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiSubStore(id){
   		<jecs:power powerCode="editJmiSubStore">
					window.location="editJmiSubStore.html?strAction=editJmiSubStore&id="+id;
			</jecs:power>
		}

</script>
