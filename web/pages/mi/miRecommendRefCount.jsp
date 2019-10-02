<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="miMember.title"/></title>
<content tag="heading"><jecs:locale key="miMember.heading"/></content>

<form action="miRecommendRefCount.html" method="post" name="miRecommendRefCountForm" id="miRecommendRefCountForm">

<c:if test="${param.strActionOperator!='countList'}">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>
		<input type="text"  name="memberNo" value="${param.memberNo}" size="10"/>	
	</div>
	<div class="new_searchBar">
		<jecs:label key="saFiIncomeReport.dataSection"/>
		<%-- <jecs:locale key="label.dateselect.ex"/>--%>
		<input type="text"  id="startTime" name="startTime" value="${param.startTime}"
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
		<input type="text"  id="endTime" name="endTime" value="${param.endTime}" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>	
	<div class="new_searchBar">
		<jecs:label key="bdReconsumMoney.orderDateScope"/>
		<%-- <jecs:label key="label.dateselect.ex"/>--%>
		<input type="text"  id="auditBDate" name="auditBDate" value="${param.auditBDate}" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
		<input type="text"  id="auditEDate" name="auditEDate" value="${param.auditEDate}" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.recommendCount"/>
		<input type="text"  name="recommendCount" value="${param.recommendCount}" size="3"/>
	</div>	
	<div class="new_searchBar">
		<jecs:label key="bdCalculatingSubDetail.cardType"/>
		<jecs:list name="cardType" id="cardType" listCode="bd.cardtype" value="${param.cardType }" defaultValue="" showBlankLine="true"/>	
	</div>
	<div class="new_searchBar">
		<jecs:label key="bdCalculatingSubDetail.cardType.1"/>
		<jecs:list name="recomCardType" id="recomCardType" listCode="bd.cardtype" value="${param.recomCardType }" defaultValue="" showBlankLine="true"/>	
	</div>
	<div class="new_searchBar" style="margin-left: 45px">
		<button name="search" class="btn btn_sele" type="submit">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%-- 
		<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
		--%>
	</div>	
</div>



			<input name="strActionOperator" type="hidden" value="recommendCount" />



	
		
		<ec:table tableId="miRecommendCountTable"
		items="miRecommendRefList"
		var="miVar"
		retrieveRowsCallback="limit"
		action="${pageContext.request.contextPath}/miRecommendRefCount.html"
		width="100%"
		rowsDisplayed="20" showTooltips="false" form="miRecommendRefCountForm"
		imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif">
		<ec:row>
			<ec:column property="user_code" title="miMember.memberNo" />

			<ec:column property="card_type" title="miMember.cardType" styleClass="centerColumn">
				<jecs:code listCode="bd.cardtype" value="${miVar.card_type}"/>
			</ec:column>
			<ec:column property="create_time" title="miMember.createTime" />

			<ec:column property="recommendcount" title="miMember.recommendCount"/>
			<ec:column property="check_date" title="logType.C">
				<fmt:parseDate value="${ miVar['check_date']}" pattern="yyyy-MM-dd" var="varDate"/>
				<fmt:formatDate value="${varDate }" pattern="yyyy-MM-dd"/>
			</ec:column>
			<ec:column property="1" title="title.operation" styleClass="centerColumn" sortable="false">
				<img src="images/newIcons/search_16.gif" onclick="window.location.href='miRecommendRefCount.html?strActionOperator=countList&memberNo=${miVar.user_code}';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
			</ec:column>

		</ec:row>
		</ec:table>
	</c:if>
	
	<c:if test="${param.strActionOperator=='countList'}">
		<div class="searchBar">
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="button" 
			onClick="javascript:window.history.back()">
				<jecs:locale key="operation.button.return"/>
			</button>
			
		</div>
		</div>
		<ec:table tableId="miRecommendCountTable"
		items="miRecommendRefList"
		var="miVar"
		retrieveRowsCallback="limit"
		action="${pageContext.request.contextPath}/miRecommendRefCount.html"
		width="100%"
		rowsDisplayed="20" showTooltips="false" form="miRecommendRefCountForm"
		imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif">
		<ec:row>
			<ec:column property="userCode" title="miMember.memberNo" />
			<ec:column property="jmiMember.petName" title="miMember.petName" />
			<ec:column property="jmiMember.cardType" title="miMember.cardType">
				<jecs:code listCode="bd.cardtype" value="${miVar.jmiMember.cardType}"/>
			</ec:column>
			<ec:column property="jmiMember.createTime" title="miMember.createTime" format="yyyy-MM-dd HH:mm:ss" cell="date"/>

		</ec:row>
		</ec:table>
	</c:if>
</form>