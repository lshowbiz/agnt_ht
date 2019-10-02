<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiSecurityDepositList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiSecurityDepositList.heading" />
</content>
<meta name="menu" content="FiSecurityDepositMenu" />

<form action="fiSecurityDeposits.html" method="get"
	name="fiSecurityDepositsSearchForm" id="fiSecurityDepositsSearchForm">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<jecs:power powerCode="addFiSecurityDeposit">
			<div class="searchBar">
				<div class="new_searchBar">
					<jecs:title key="label.member.or.agent.code" />
					<input name="userCode" type="text" value="${param['userCode'] }"
						size="14" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="fiSecurityDeposit.userName2" />
					<input name="userName" type="text" value="${param['userName'] }"
						size="14" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="fiSecurityDeposit.dept2" />
					<jecs:list listCode="fisecuritydeposit.dept" name="dept" id="dept"
						value="${param.dept}" defaultValue="0" showBlankLine="true" />
				</div>
				<div class="new_searchBar">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
						<jecs:locale key="operation.button.search"/>
					</button>
					<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editFiSecurityDeposit.html"/>?strAction=addFiSecurityDeposit'">
						<jecs:locale key="button.add"/>
					</button>					
					<button name="makeupbtn" id="makeupbtn" type="button" class="btn btn_long" onclick="makeupall('${pageContext.request.contextPath}/fiSecurityDeposits.html?makeup=all')">
						<jecs:locale key="button.makeup"/>
					</button>
				</div>
			</div>
		</jecs:power>
	</c:if>
</form>
<ec:table tableId="fiSecurityDepositListTable"
	items="fiSecurityDepositList" var="fiSecurityDeposit"
	action="${pageContext.request.contextPath}/fiSecurityDeposits.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="edit" title="title.operation" sortable="false"
			viewsAllowed="html">
			<img src="<c:url value='/images/icons/editIcon.gif'/>"
				onclick="javascript:editFiSecurityDeposit('${fiSecurityDeposit.fsd_id}')"
				style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
		</ec:column>
		<ec:column property="user_code" title="label.member.or.agent.code" />
		<ec:column property="user_name" title="fiSecurityDeposit.userName" />
		<ec:column property="dept" title="fiSecurityDeposit.dept">
			<jecs:code listCode="fisecuritydeposit.dept"
				value="${fiSecurityDeposit.dept}" />
		</ec:column>
		<ec:column property="tel" title="fiSecurityDeposit.tel" />

		<ec:column property="balance" title="fiSecurityDeposit.balance"
			styleClass="numberColumn">

		</ec:column>
		<%--
    			<ec:column property="makeup" title="title.operation" style="align:center">
    				
						<c:if test="${fiSecurityDeposit.balance!=securityDepositLimit}">
							<a href="${pageContext.request.contextPath}/fiSecurityDeposits.html?makeup=1&fsdId=${fiSecurityDeposit.fsd_id}">[<jecs:locale  key="security.deposit.makeup"/>]</a>
						</c:if>
						<c:if test="${fiSecurityDeposit.balance==securityDepositLimit}">
							
						</c:if>
						
						<c:if test="${fiSecurityDeposit.balance!=securityDepositLimit}">
							<a href="${pageContext.request.contextPath}/fiSecurityDeposits.html?makeup=1&fsdId=${fiSecurityDeposit.fsd_id}">[<jecs:locale  key="security.deposit.makeup"/>]</a>
						</c:if>
				</ec:column>
				 --%>
		<ec:column property="makeup" title="title.operation"
			style="align:center">

			<c:if test="${fiSecurityDeposit.balance>0}">
				<a
					href="${pageContext.request.contextPath}/downFiSecurityDeposit.html?strAction=downFiSecurityDeposit&fsdId=${fiSecurityDeposit.fsd_id}">[<jecs:locale
						key="security.deposit.makedown" />]</a>
			</c:if>

		</ec:column>


	</ec:row>

</ec:table>

<script type="text/javascript">

   function editFiSecurityDeposit(fsdId){
   		<jecs:power powerCode="editFiSecurityDeposit">
					window.location="editFiSecurityDeposit.html?strAction=editFiSecurityDeposit&fsdId="+fsdId;
			</jecs:power>
	}
	function makeupall(url){
		
		if(!confirm("<jecs:locale key="makeupall.confirm.verify"/>")){
			return false;
		}
		
		var stamp = document.getElementById("makeupbtn");
		stamp.disabled=true;
		stamp.value='<jecs:locale key="not.out"/>';
		
		window.location=url;
	}
</script>
