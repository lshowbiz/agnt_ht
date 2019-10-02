<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointLogAllList.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdTravelPointLogAllList.heading" />
</content>
<meta name="menu" content="JbdTravelPointLogAllMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<c:set var="buttons">
	<jecs:power powerCode="addJbdTravelPointLogAll">
			<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/editJbdTravelPointLogAll.html"/>?strAction=addJbdTravelPointLogAll'">
				<jecs:locale key="button.add"/>
			</button>
	</jecs:power>
</c:set>
<form action="" method="get" name="jbdTravelPointAllForm1"
	id="jbdTravelPointAllForm1">
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
			<jecs:title key="jbdTravelPointAll.fyear" />
			<jecs:list name="fyear" listCode="jbdtravelpointall.fyear"
				value="${param.fyear}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.createTime" />
			<input name="startTime" id="startTime" type="text" 
					value="${param.startTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endTime" id="endTime" type="text" 
					value="${param.endTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="cancel" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>	
			<c:out value="${buttons}" escapeXml="false" />		
		</div>
		
	</div>
</form>
<ec:table tableId="jbdTravelPointLogAllListTable"
	items="jbdTravelPointLogAllList" var="jbdTravelPointLogAll"
	action="${pageContext.request.contextPath}/jbdTravelPointLogAlls.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<jecs:power powerCode="jbdTravelPointLogAllsExport">
			<ec:exportXls fileName="jbdTravelPointLogAlls.xls"></ec:exportXls>
		</jecs:power>

		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="fyear" title="jbdTravelPointAll.fyear" />
		<ec:column property="usePoint" title="fibankbooktemp.dealtype.A"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if test="${jbdTravelPointLogAll.dealType=='A'}">${jbdTravelPointLogAll.usePoint}</c:if>
			<c:if test="${jbdTravelPointLogAll.dealType=='D'}">0</c:if>
		</ec:column>
		<ec:column property="usePoint" title="fibankbooktemp.dealtype.D"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if test="${jbdTravelPointLogAll.dealType=='D'}">${jbdTravelPointLogAll.usePoint}</c:if>
			<c:if test="${jbdTravelPointLogAll.dealType=='A'}">0</c:if>
		</ec:column>
		<ec:column property="remark" title="customerRecord.remark" >
			<jecs:substr key="${jbdTravelPointLogAll.remark }" length="20"/>
		</ec:column>

		<ec:column property="createUser" title="customerRecord.createNo" />
		<ec:column property="createTime" cell="date"
			format="yyyy-MM-dd HH:mm:ss" title="pd.createTime" />
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJbdTravelPointLogAll(logId){
   		<jecs:power powerCode="editJbdTravelPointLogAll">
					window.location="editJbdTravelPointLogAll.html?strAction=editJbdTravelPointLogAll&logId="+logId;
			</jecs:power>
		}

</script>
