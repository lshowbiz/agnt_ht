<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCoinLogList.title"/></title>
<content tag="heading"><jecs:locale key="fiCoinLogList.heading"/></content>
<meta name="menu" content="FiCoinLogMenu"/>

<c:if test="${not empty errorList || not empty successList }">
	<div class="error" id="errorMessages">
		<c:forEach var="error" items="${successList}">
			<img src="<c:url value="images/newIcons/tick_16.gif"/>"
				alt="<jecs:locale key="opration.notice.js.orderNo.auditSuccess"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
		<c:forEach var="error" items="${errorList}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>

<form action="" method="get" name="searchForm" id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">		
			<jecs:title key="fiCoinLog.uniqueCode"/>
			<input name="uniqueCode" size="10" type="text" value="${param.uniqueCode }"/>
		</div>
		<div class="new_searchBar">		
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" size="10" type="text" value="${param.userCode }"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="fiBankbookTemp.status"/>
			<jecs:list name="status" listCode="jsm.status" value="${param.status}" defaultValue="" showBlankLine="true"/>
		</div>
		<div class="new_searchBar" style="margin-left:32px;">
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
			
			<button name="search" class="btn btn_sele" type="submit" 
						onclick="javascript:$('submitType').value='2';readCheck();">
				<jecs:locale key="button.audit"/>
			</button>
			
		
		</div>
		<input type="hidden" name="submitType" id="submitType" value="" />
		<input type="hidden" name="logId" id="logId" value=""/>
	</div>
</form>

<ec:table 
	tableId="fiCoinLogListTable"
	items="fiCoinLogList"
	var="fiCoinLog"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/fiCoinLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<c:if test="${fiCoinLog.status=='1' }">
						<input type="checkbox" name="selectedId" id="selectedId" value="${fiCoinLog.fclId}" class="checkbox"/>
					</c:if>
				</ec:column>
    			<ec:column property="uniqueCode" title="fiCoinLog.uniqueCode" />
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="createTime" title="pd.createTime" />
    			<ec:column property="receiveCoin" title="miMember.memberNo" />
    			<ec:column property="coin" title="fiCoinLog.coin" />
    			<ec:column property="status" title="fiBankbookTemp.status" styleClass="centerColumn">
    				<jecs:code listCode="jsm.status" value="${fiCoinLog.status}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

    <% 
    if(request.getAttribute("exception")!=null){
    	Exception exception = (Exception) request.getAttribute("exception");
    %>
<div id="main" style="display:none">
	<h1><jecs:locale key="errorPage.heading"/></h1>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
</div>
    <%
    }
    %>
    <% 
    if(request.getAttribute("appException")!=null){
    	com.joymain.jecs.util.exception.AppException appException = (com.joymain.jecs.util.exception.AppException) request.getAttribute("appException");
    %>
<div id="main" style="display:none">
	<h1><jecs:locale key="errorPage.heading"/></h1>
    <pre><% appException.printStackTrace(new java.io.PrintWriter(out)); %></pre>
</div>
    <%
    }
    %>
<script type="text/javascript">

function readCheck(poMemberOrderCheck){
	var elements=document.getElementsByName("selectedId");
	$('logId').value = '';
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			$('logId').value += elements[i].value + ",";
		}
	}
	if($('logId').value !=''){
		if(confirm("<jecs:locale key="aiAgent.confirm"/>")){
			if(isFormPosted()){
			$('searchForm').action = 'fiCoinLogs.html?strAction=auditStr';
			$('searchForm').method="post";
			$('searchForm').submit();
			}
		}
	}else{
		alert('<jecs:locale key="poAssistMemberOrder.mustCheck"/>');
	}
}
</script>