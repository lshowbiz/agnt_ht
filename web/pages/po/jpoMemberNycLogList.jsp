<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberNycLogList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberNycLogList.heading"/></content>
<meta name="menu" content="JpoMemberNycLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberNycLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberNycLog.html"/>?strAction=addJpoMemberNycLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoMemberNycLogListTable"
	items="jpoMemberNycLogList"
	var="jpoMemberNycLog"
	action="${pageContext.request.contextPath}/jpoMemberNycLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycLog('${jpoMemberNycLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycLog.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycLog('${jpoMemberNycLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycLog.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycLog('${jpoMemberNycLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycLog.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycLog('${jpoMemberNycLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycLog.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycLog('${jpoMemberNycLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycLog.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycLog('${jpoMemberNycLog.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycLog.id" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberNycLog(id){
   		<jecs:power powerCode="editJpoMemberNycLog">
					window.location="editJpoMemberNycLog.html?strAction=editJpoMemberNycLog&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycLog(id){
   		<jecs:power powerCode="editJpoMemberNycLog">
					window.location="editJpoMemberNycLog.html?strAction=editJpoMemberNycLog&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycLog(id){
   		<jecs:power powerCode="editJpoMemberNycLog">
					window.location="editJpoMemberNycLog.html?strAction=editJpoMemberNycLog&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycLog(id){
   		<jecs:power powerCode="editJpoMemberNycLog">
					window.location="editJpoMemberNycLog.html?strAction=editJpoMemberNycLog&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycLog(id){
   		<jecs:power powerCode="editJpoMemberNycLog">
					window.location="editJpoMemberNycLog.html?strAction=editJpoMemberNycLog&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycLog(id){
   		<jecs:power powerCode="editJpoMemberNycLog">
					window.location="editJpoMemberNycLog.html?strAction=editJpoMemberNycLog&id="+id;
			</jecs:power>
		}

</script>
