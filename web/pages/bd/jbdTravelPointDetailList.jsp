<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>

<title><jecs:locale key="jbdTravelPointDetailList.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdTravelPointDetailList.heading" />
</content>
<meta name="menu" content="JbdTravelPointDetailMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="" method="get" name="bdSendRecordForm1"
	id="bdSendRecordForm1">
	<div class="searchBar">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<div class="new_searchBar">
			<input type="hidden" id="strAction" name="strAction"
				value="jbdTravelPointDetails" />
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode}"
				size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.createTime" />
			<input name="startTime" id="startTime" type="text" 
					value="${param.startTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endTime" id="endTime" type="text" 
					value="${param.endTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<button name="cancel" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addJbdTravelPointDetail">
				<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJbdTravelPointDetail.html"/>?strAction=addJbdTravelPointDetail'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
		</div>
	</c:if>
	</div>



</form>




<c:if test="${sessionScope.sessionLogin.userType=='M'}">
	<table class='detail' width="100%">


		<tr>
			<th>
				奖励积分：
			</th>
			<td align="left">
				${jbdTravelPoint.total }
			</td>
		</tr>


		<tr>
			<th>
				活动考核期间内最高奖衔：
			</th>
			<td align="left">
				<jecs:code listCode="pass.star.zero"
					value="${jbdTravelPoint.passStar }" />
			</td>
		</tr>


		<tr>
			<th>
				推荐VIP级别会员人数：
			</th>
			<td align="left">
				${recommendVips}
			</td>
		</tr>

	</table>

</c:if>



<ec:table tableId="jbdTravelPointDetailListTable"
	items="jbdTravelPointDetailList" var="jbdTravelPointDetail"
	action="${pageContext.request.contextPath}/jbdTravelPointDetails.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<jecs:power powerCode="jbdTravelPointDetailsExport">
			<ec:exportXls fileName="jbdTravelPointDetails.xls"></ec:exportXls>
		</jecs:power>

		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="travelType" title="bdBonusStatReport.item"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="bd.traveltype"
				value="${jbdTravelPointDetail.travelType }" />
		</ec:column>
		<ec:column property="usePoint" title="fiCoinLog.coin" />
		<ec:column property="createUser" title="customerRecord.createNo" />
		<ec:column property="createTime" title="pd.createTime" />


		<jecs:power powerCode="deleteJbdTravelPointDetail">
			<ec:column property="1" title="title.operation" sortable="false"
				width="150px" viewsDenied="xls">

				<a
					href="jbdTravelPointDetails.html?id=${jbdTravelPointDetail.id}&strAction=deleteJbdTravelPointDetail"
					onclick="return window.confirm('<jecs:locale key="amMessage.confirmdelete"/>');"><img
						border="0" src="images/icons/w.gif"
						alt="<jecs:locale key="operation.button.delete"/>" />
				</a>

			</ec:column>
		</jecs:power>


	</ec:row>

</ec:table>


