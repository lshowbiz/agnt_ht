<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiCommonAddrList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiCommonAddrList.heading" />
</content>
<meta name="menu" content="FiCommonAddrMenu" />

<form action="fiCommonAddrs.html" method="get"
	name="fiBillAccountSearchForm" id="fiBillAccountSearchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会员编号：
			<input name="userCode" type="text" value="${param['userCode'] }"
				size="14" />
		</div>
		<div class="new_searchBar">
			省：
			<select name="province">
	
				<option value=""></option>
				<c:forEach items="${alStateProvinces}" var="alStateProvince">
					<c:if test="${param['province'] != alStateProvince.stateProvinceId}">
						<option value="${alStateProvince.stateProvinceId }">
							${alStateProvince.stateProvinceName}
						</option>
					</c:if>
					<c:if test="${param['province'] == alStateProvince.stateProvinceId}">
						<option value="${alStateProvince.stateProvinceId }"
							selected="selected">
							${alStateProvince.stateProvinceName}
						</option>
					</c:if>
				</c:forEach>
				<select>
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>
<ec:table tableId="fiCommonAddrListTable" items="fiCommonAddrList"
	var="fiCommonAddr"
	action="${pageContext.request.contextPath}/fiCommonAddrs.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<%-- 
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiCommonAddr('${fiCommonAddr.userCode}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					--%>
		<ec:column property="userCode" title="会员编号" />
		<ec:column property="province" title="省" />
		<ec:column property="city" title="市" />
		<ec:column property="district" title="区" />
		<ec:column property="address" title="街道地址" />
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editFiCommonAddr(userCode){
   		<jecs:power powerCode="editFiCommonAddr">
					window.location="editFiCommonAddr.html?strAction=editFiCommonAddr&userCode="+userCode;
			</jecs:power>
		}

</script>
