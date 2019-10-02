<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="scheduleManageList.title"/></title>
<content tag="heading"><jecs:locale key="scheduleManageList.heading"/></content>
<meta name="menu" content="ScheduleManageMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addScheduleManage">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editScheduleManage.html"/>?strAction=addScheduleManage'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="scheduleManageListTable"
	items="scheduleManageList"
	var="scheduleManage"
	action="${pageContext.request.contextPath}/scheduleManages.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editScheduleManage('${scheduleManage.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="scheduleName" title="scheduleManage.scheduleName" />
    			<ec:column property="startTime" title="scheduleManage.startTime" />
    			<ec:column property="endTime" title="scheduleManage.endTime" />
    			<ec:column property="priority" title="scheduleManage.priority" />
    			<ec:column property="status" title="scheduleManage.status" />
    			<ec:column property="linkmanId" title="scheduleManage.linkmanId" />
    			<ec:column property="remark" title="scheduleManage.remark" />
    			<ec:column property="remind" title="scheduleManage.remind" />
    			<ec:column property="repeat" title="scheduleManage.repeat" />
    			<ec:column property="loginUserNo" title="scheduleManage.loginUserNo" />
    			<ec:column property="eventType" title="scheduleManage.eventType" />
    			<ec:column property="place" title="scheduleManage.place" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editScheduleManage(id){
   		<jecs:power powerCode="editScheduleManage">
					window.location="editScheduleManage.html?strAction=editScheduleManage&id="+id;
			</jecs:power>
		}

</script>
