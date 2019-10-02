<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="publicScheduleList.title"/></title>
<content tag="heading"><jecs:locale key="publicScheduleList.heading"/></content>
<meta name="menu" content="PublicScheduleMenu"/>

<c:set var="buttons">

		<jecs:power powerCode="addPublicSchedule">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px;width:auto"
			        onclick="location.href='<c:url value="/editPublicSchedule.html"/>?strAction=addPublicSchedule'"><jecs:locale key="button.add"/>
			     </button>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="publicScheduleListTable"
	items="publicScheduleList"
	var="publicSchedule"
	action="${pageContext.request.contextPath}/publicSchedules.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPublicSchedule('${publicSchedule.psId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="name" title="publicSchedule.name" />
    			<ec:column property="type" title="publicSchedule.type" />
    			<ec:column property="startTime" format="yyyy-MM-dd HH:mm" cell="date" title="publicSchedule.startTime" />
    			<ec:column property="endTime" format="yyyy-MM-dd HH:mm" cell="date" title="publicSchedule.endTime" />
    			<ec:column property="content" title="publicSchedule.content" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPublicSchedule(psId){
   		<jecs:power powerCode="editPublicSchedule">
					window.location="editPublicSchedule.html?strAction=editPublicSchedule&psId="+psId;
			</jecs:power>
		}

</script>
