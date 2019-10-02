<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>


<title><jecs:locale key="jfiSunJmiMemberList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiSunJmiMemberList.heading" />
</content>
<meta name="menu" content="JfiSunJmiMemberMenu" />




<form action="" method="get" name="miMemberForm" id="miMemberForm">

	<div class="searchBar">
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地区：
			<select name="villageAddr" id="villageAddr">
	
				<option value="东北"
					<c:if test="${param.villageAddr=='东北' }">selected="selected"</c:if>>
					东北
				</option>
				<option value="华北"
					<c:if test="${param.villageAddr=='华北' }">selected="selected"</c:if>>
					华北
				</option>
				<option value="华东"
					<c:if test="${param.villageAddr=='华东' }">selected="selected"</c:if>>
					华东
				</option>
				<option value="华南"
					<c:if test="${param.villageAddr=='华南' }">selected="selected"</c:if>>
					华南
				</option>
	
			</select>
		</div>
		<div class="new_searchBar">
			<button name="cancel" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>


	</div>

</form>

<ec:table tableId="jfiSunJmiMemberListTable" items="jfiSunJmiMemberList"
	var="jfiSunJmiMember"
	action="${pageContext.request.contextPath}/jfiSunJmiMembers.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<%--<ec:column property="userCode" title="miMember.memberNo" />--%>
		<ec:column property="miUserName" title="bdCalculatingSubDetail.name" />
		<ec:column property="petName" title="miMember.petName" />
		<ec:column property="sex" title="miMember.sex">
			<jecs:code listCode="sex" value="${jfiSunJmiMember.sex}" />
		</ec:column>
		<ec:column property="province" title="miMember.province">
			<c:forEach items="${alStateProvinces }" var="al">
				<c:if test="${al.stateProvinceId==jfiSunJmiMember.province }">
					<c:out value="${al.stateProvinceName}" />
				</c:if>
			</c:forEach>
		</ec:column>
		<ec:column property="city" title="miMember.idAddr2" />
		<ec:column property="district" title="miMember.district" />
		<ec:column property="address" title="busi.address" />
		<ec:column property="email" title="miMember.email" />
		<ec:column property="villageAddr" title="地区" />


		<ec:column property="1" title="title.operation" sortable="false"
			width="150px">

			<img src="images/newIcons/search_16.gif"
				onclick="window.location.href='<c:url value="jfiSunOrderPeriodStatistic.html"><c:param name="agentNo" value="${jfiSunJmiMember.miUserName}"/><c:param name="period" value="${period}"/><c:param name="search" value="null"/></c:url>';"
				alt="<jecs:locale key="function.menu.view"/>"
				style="cursor: pointer" />
		</ec:column>
	</ec:row>

</ec:table>

