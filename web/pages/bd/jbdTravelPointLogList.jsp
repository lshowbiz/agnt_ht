<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointLogList.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdTravelPointLogList.heading" />
</content>
<meta name="menu" content="JbdTravelPointLogMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>


<form action="" method="get" name="bdSendRecordForm1"
	id="bdSendRecordForm1">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode}" size="10" />
		</div>
		<div class="new_searchBar">		
			<jecs:title key="fiBankbookTemp.dealType" />
			<jecs:list name="dealType" listCode="fibankbooktemp.dealtype"
				value="${param.dealType}" defaultValue="" showBlankLine="true" />
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
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="cancel" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addJbdTravelPointLog">
				<button name="cancel" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJbdTravelPointLog.html"/>?strAction=addJbdTravelPointLog'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
		</div>
	</div>

</form>
<ec:table tableId="jbdTravelPointLogListTable"
	items="jbdTravelPointLogList" var="jbdTravelPointLog"
	action="${pageContext.request.contextPath}/jbdTravelPointLogs.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row>

		<jecs:power powerCode="jbdTravelPointLogsExport">

			<ec:exportXls fileName="jbdTravelPointLogs.xls"></ec:exportXls>
		</jecs:power>


		<ec:column property="userCode" title="miMember.memberNo" />

		<ec:column property="usePoint" title="fibankbooktemp.dealtype.A"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if test="${jbdTravelPointLog.dealType=='A'}">${jbdTravelPointLog.usePoint}</c:if>
			<c:if test="${jbdTravelPointLog.dealType=='D'}">0</c:if>
		</ec:column>
		<ec:column property="usePoint" title="fibankbooktemp.dealtype.D"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if test="${jbdTravelPointLog.dealType=='D'}">${jbdTravelPointLog.usePoint}</c:if>
			<c:if test="${jbdTravelPointLog.dealType=='A'}">0</c:if>
		</ec:column>


		<ec:column property="remark" title="customerRecord.remark" />
		<ec:column property="createUser" title="customerRecord.createNo" />
		<ec:column property="createTime" title="pd.createTime" />
	</ec:row>

</ec:table>

