<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiTaiwanTravelList.title" />
</title>
<content tag="heading">
<jecs:locale key="jmiTaiwanTravelList.heading" />
</content>
<meta name="menu" content="JmiTaiwanTravelMenu" />

<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<c:set var="buttons">

		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="miMember.memberNo" />
				<input name="userCode" type="text" value="${param.userCode}"
					size="10" />
			</div>
			<div class="new_searchBar">
				<button name="search" class="btn btn_sele" type="submit">
					<jecs:locale key="operation.button.search"/>
				</button>
				<jecs:power powerCode="addJmiTaiwanTravel">
					<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJmiTaiwanTravel.html"/>?strAction=addJmiTaiwanTravel'">
						<jecs:locale key="button.add"/>
					</button>
				</jecs:power>
			</div>
		</c:if>
	</c:set>
	<div class="searchBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>

</form>
<ec:table tableId="jmiTaiwanTravelListTable" items="jmiTaiwanTravelList"
	var="jmiTaiwanTravel"
	action="${pageContext.request.contextPath}/jmiTaiwanTravels.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="petName" title="miMember.petName" />
		<ec:column property="idNo" title="miMember.idNo" />
		<ec:column property="createUser" title="customerRecord.createNo" />
		<ec:column property="createTime" title="pd.createTime" />
		<ec:column property="1" title="title.operation" sortable="false"
			width="100">
			<jecs:power powerCode="viewJmiTaiwanTravel">
				<img src="images/newIcons/search_16.gif"
					onclick="window.location.href='editJmiTaiwanTravel.html?userCode=${jmiTaiwanTravel.userCode}&strAction=viewJmiTaiwanTravel';"
					alt="<jecs:locale key="function.menu.view"/>"
					style="cursor: pointer" />
			</jecs:power>

			<jecs:power powerCode="editJmiTaiwanTravel">
				<img src="images/newIcons/pencil_16.gif"
					onclick="window.location.href='editJmiTaiwanTravel.html?userCode=${jmiTaiwanTravel.userCode}&strAction=editJmiTaiwanTravel';"
					alt="<jecs:locale key="button.edit"/>" style="cursor: pointer" />
			</jecs:power>

		</ec:column>
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJmiTaiwanTravel(userCode){
   		<jecs:power powerCode="editJmiTaiwanTravel">
					window.location="editJmiTaiwanTravel.html?strAction=editJmiTaiwanTravel&userCode="+userCode;
			</jecs:power>
		}

</script>
