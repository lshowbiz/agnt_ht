<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiStateLogList.title"/></title>
<content tag="heading"><jecs:locale key="jmiStateLogList.heading"/></content>
<meta name="menu" content="JmiStateLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiStateLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiStateLog.html"/>?strAction=addJmiStateLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiStateLogListTable"
	items="jmiStateLogList"
	var="jmiStateLog"
	action="${pageContext.request.contextPath}/jmiStateLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiStateLog('${jmiStateLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jmiStateLog.userCode" />
    			<ec:column property="createTime" title="jmiStateLog.createTime" />
    			<ec:column property="createNo" title="jmiStateLog.createNo" />
    			<ec:column property="logType" title="jmiStateLog.logType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiStateLog(id){
   		<jecs:power powerCode="editJmiStateLog">
					window.location="editJmiStateLog.html?strAction=editJmiStateLog&id="+id;
			</jecs:power>
		}

</script>
