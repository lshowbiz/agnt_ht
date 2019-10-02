<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysVisitLogList.title"/></title>
<content tag="heading"><jecs:locale key="sysVisitLogList.heading"/></content>
<meta name="menu" content="SysVisitLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysVisitLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysVisitLog.html"/>?strAction=addSysVisitLog'"
			     value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysVisitLogListTable"
	items="sysVisitLogList"
	var="sysVisitLog"
	action="${pageContext.request.contextPath}/sysVisitLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysVisitLog('${sysVisitLog.logId}')">
    			<ec:column property="moduleCode" title="sysVisitLog.moduleCode" />
    			<ec:column property="userCode" title="sysVisitLog.userCode" />
    			<ec:column property="visitUrl" title="sysVisitLog.visitUrl" />
    			<ec:column property="visitTime" title="sysVisitLog.visitTime" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysVisitLog.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='logId' id='logId'/>
</form>

<script type="text/javascript">

   function editSysVisitLog(logId){
   		<jecs:power powerCode="editSysVisitLog">
					window.location="editSysVisitLog.html?strAction=editSysVisitLog&logId="+logId;
			</jecs:power>
		}

</script>
