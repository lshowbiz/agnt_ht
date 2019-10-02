<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiStoreList.title"/></title>
<content tag="heading"><jecs:locale key="jmiStoreList.heading"/></content>
<meta name="menu" content="JmiStoreMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div class="searchBar">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		</div>	
		<div class="new_searchBar">
			<jecs:title key="customerFollow.state"/>
        	<jecs:list name="checkStatus" listCode="jmistore.status" 
        	value="${param.checkStatus}" defaultValue="" showBlankLine="true"/>
        </div>	
        <div class="new_searchBar">
			<jecs:title key="pd.okstatus"/>
	        <jecs:list name="confirmStatus" listCode="jmisubstore.confirmstatus" 
	        value="${param.confirmStatus}" defaultValue="" showBlankLine="true"/>	
		</div>
		<div class="new_searchBar">
			<jecs:list listCode="store.timetype.1" name="timetype" 
			value="${param.timetype}" defaultValue="createTime" style="width:80px"/>
			<%-- <jecs:title key="label.dateselect.ex"/>--%>
			<input id="startTime" name="startTime" type="text" value="${param.startTime }"
			style="cursor: pointer;width:77px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			 - 
			<input id="endTime" name="endTime" type="text" value="${param.endTime }"
			style="cursor: pointer;width:77px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			
		</div>	
		<div class="new_searchBar" style="margin-left: 30px">
			<button type="submit" class="btn btn_sele" onclick="document.getElementById('xslText').value='';">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button class="btn btn_long" type="submit" onclick="document.getElementById('xslText').value='xslText';">
				<jecs:locale key="toolbar.text.xls"/>
			</button >
			<input name="xslText" type="hidden" id="xslText" value="">
			<%-- 
			<input type="submit" class="button" onclick="document.getElementById('xslText').value='';" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
			<input name="search" class="button" type="submit" onclick="document.getElementById('xslText').value='xslText';" value="<jecs:locale key="toolbar.text.xls"/>" />	
			--%>	
		</div>		

	</c:if>
			<%-- <jecs:power powerCode="addJmiStore">
			<c:if test="${sessionScope.sessionLogin.userType=='C'|| (sessionScope.sessionLogin.userType=='M' && empty jmiStoreExist) }">
				<input name="search" class="button" type="button" onclick="location.href='<c:url value="editJmiStore.html?strAction=addJmiStore"/>'" value="<jecs:locale key="member.new.num"/>" />
			</c:if>
			</jecs:power> --%>
</div>
</form>
<ec:table 
	tableId="jmiStoreListTable"
	items="jmiStoreList"
	var="jmiStore"
	action="${pageContext.request.contextPath}/jmiStores.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<ec:column property="jmiMember.petName" title="miMember.petName" />
    			<%-- 
    			<ec:column property="honorStar" title="bdPinTitleRecord.pinTitle" >
    				<jecs:code listCode="honor.star.zero" value="${jmiStore.honorStar}"/>
    			</ec:column>
    			--%>
    			<ec:column property="createTime" title="miMember.createTime" format="yyyy-MM-dd HH:mm:ss" cell="date"/>
    			<%-- 
    			<ec:column property="editTime" title="busi.order.editTime" format="yyyy-MM-dd HH:mm:ss" cell="date"/>
    			<ec:column property="orderTime" title="store.orderTime" />
    			--%>
    			
    			
    			<ec:column property="checkStatus" title="customerFollow.state" >
    				 <jecs:code listCode="jmisubstore.status" value="${jmiStore.checkStatus}"/>
    			</ec:column>
    			<%-- 
				<c:if test="${sessionScope.sessionLogin.userType=='C' }">
    				<ec:column property="checkUser" title="jmiSubStore.addrCheckUser" />
    			</c:if>
    			<ec:column property="checkRemark" title="jmiSubStore.checkRemark" />
    			--%>
    			<ec:column property="confirmStatus" title="pd.okstatus" >
    				 <jecs:code listCode="jmisubstore.confirmstatus" value="${jmiStore.confirmStatus}"/>
    			</ec:column>
    			<%-- 
				<c:if test="${sessionScope.sessionLogin.userType=='C' }">
    				<ec:column property="confirmUser" title="jmiSubStore.addrConfirmUser" />
    			</c:if>
    			
    			<ec:column property="confirmRemark" title="jmiSubStore.confirmRemark" />
    			--%>
    			
    			<ec:column property="1" title="title.operation" sortable="false">
					
					
					<jecs:power powerCode="viewJmiStore">
					<img src="images/newIcons/search_16.gif" onclick="window.location.href='editJmiStore.html?id=${jmiStore.id}&strAction=viewJmiStore';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					<jecs:power powerCode="editJmiStore">
						<c:if test="${sessionScope.sessionLogin.userType=='M' && jmiStore.checkStatus!='1' }">
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiStore.html?id=${jmiStore.id}&strAction=editJmiStore';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						</c:if>
						<c:if test="${sessionScope.sessionLogin.userType=='C'  }">
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiStore.html?id=${jmiStore.id}&strAction=editJmiStore';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						</c:if>
					
					</jecs:power>
					
				</ec:column>
    			
				</ec:row>

</ec:table>	

