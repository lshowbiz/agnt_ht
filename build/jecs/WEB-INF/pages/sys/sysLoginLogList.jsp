<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysLoginLogList.title"/></title>
<content tag="heading"><jecs:locale key="sysLoginLogList.heading"/></content>
<meta name="menu" content="SysLoginLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysLoginLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysLoginLog.html"/>?strAction=addSysLoginLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysLoginLogListTable"
	items="sysLoginLogList"
	var="sysLoginLog"
	action="${pageContext.request.contextPath}/sysLoginLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editSysLoginLog('${sysLoginLog.llId}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="sysLoginLog.userCode" />
    			<ec:column property="operaterCode" title="sysLoginLog.operaterCode" />
    			<ec:column property="ipAddr" title="sysLoginLog.ipAddr" />
    			<ec:column property="operateTime" title="sysLoginLog.operateTime" />
    			<ec:column property="operationType" title="sysLoginLog.operationType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editSysLoginLog(llId){
   		<jecs:power powerCode="editSysLoginLog">
					window.location="editSysLoginLog.html?strAction=editSysLoginLog&llId="+llId;
			</jecs:power>
		}

</script>
