<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title"/></title>
<content tag="heading"><jecs:locale key="bdPeriodList.heading"/></content>
<meta name="menu" content="BdPeriodMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addBdPeriod">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editBdPeriod.html"/>?strAction=addBdPeriod'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="bdPeriodListTable"
	items="bdPeriodList"
	var="bdPeriod"
	action="${pageContext.request.contextPath}/bdPeriods.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editBdPeriod('${bdPeriod.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="bdPeriod.wyear" />
    			<ec:column property="wmonth" title="bdPeriod.wmonth" />
    			<ec:column property="wweek" title="bdPeriod.wweek" />
    			<ec:column property="startTime" title="bdPeriod.startTime" />
    			<ec:column property="endTime" title="bdPeriod.endTime" />
    			<ec:column property="lastMark" title="bdPeriod.lastMark" />
    			<ec:column property="bonusSend" title="bdPeriod.bonusSend" />
    			<ec:column property="archivingStatus" title="bdPeriod.archivingStatus" />
    			<ec:column property="monthStatus" title="bdPeriod.monthStatus" />
    			<ec:column property="oldWeek" title="bdPeriod.oldWeek" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editBdPeriod(id){
   		<jecs:power powerCode="editBdPeriod">
					window.location="editBdPeriod.html?strAction=editBdPeriod&id="+id;
			</jecs:power>
		}

</script>
