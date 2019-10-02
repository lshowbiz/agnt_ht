<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miMemberManage.title"/></title>
<content tag="heading"><jecs:locale key="miMemberManage.heading"/></content>
<meta name="menu" content="BdSendRecordMenu"/>


<form action="" method="post" name="miMemberManageForm1" id="miMemberManageForm1">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:locale key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:locale key="miMember.bnum"/>
			<input name="bankcard" type="text" value="${param.bankcard }" size="10"/>	
		</div>
		<div class="new_searchBar">
			<button name="search" type="submit" class="btn btn_sele">
				<jecs:locale key="operation.button.search"/>
			</button>
			<c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">
				<jecs:power powerCode="batchUpdateBank">
				<button name="search" class="btn btn_long" type="button" 
					onclick="location.href='<c:url value="batchUpdateBank.html"/>'">
					批量修改银行资料
				</button>
			</jecs:power>
			</c:if>
		</div>
</div>
</form>

<ec:table 
	tableId="miMemberManageTable"
	items="miMemberManageList"
	var="jmiMember"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/miMemberBankManageList.html"
	width="100%"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		
				<ec:column property="userCode" title="miMember.memberNo" />
				
    			<%--<ec:column property="miUserName" title="bdCalculatingSubDetail.name" />--%>
				<ec:column property="petName" title="miMember.petName" />
	
				<ec:column property="bank" title="miMember.openBank" />
				
				<ec:column property="bankaddress" title="miMember.bcity" />
				<ec:column property="bankbook" title="miMember.bname" />
				<ec:column property="bankcard" title="miMember.bnum" />
				
				<ec:column property="bankProvince" title="miMember.bankProvince" >
						<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jmiMember.bankProvince }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
				</ec:column>
				<ec:column property="bankCity" title="miMember.bankCity" />
				
				<ec:column property="mobiletele" title="miMember.mobiletele" />

				<ec:row>
					<ec:column property="2" title="title.operation" width="100" sortable="false">
					<jecs:power powerCode="viewMiMemberBank">
					<img src="images/newIcons/search_16.gif" onclick="window.location.href='miMemberBankManageForm.html?strAction=viewMiMemberBank&userCode=${jmiMember.userCode}'" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					<jecs:power powerCode="editMiMemberBank">
					<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='miMemberBankManageForm.html?strAction=editMiMemberBank&userCode=${jmiMember.userCode}'" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</jecs:power>
				</ec:column>	
				</ec:row>

</ec:table>

<form name="form1" action="<c:url value='editBdSendRecord.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='recordId' id='recordId'/>
</form>
<script type="text/javascript">

   function editBdSendRecord(recordId){
   		<jecs:power powerCode="editBdSendRecord">
					window.location="editBdSendRecord.html?strAction=editBdSendRecord&recordId="+recordId;
			</jecs:power>
		}

</script>
