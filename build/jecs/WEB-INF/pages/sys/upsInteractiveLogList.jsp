<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="upsInteractiveLogList.title"/></title>
<content tag="heading"><jecs:locale key="upsInteractiveLogList.heading"/></content>
<meta name="menu" content="UpsInteractiveLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addUpsInteractiveLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editUpsInteractiveLog.html"/>?strAction=addUpsInteractiveLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="upsInteractiveLogListTable"
	items="upsInteractiveLogList"
	var="upsInteractiveLog"
	action="${pageContext.request.contextPath}/upsInteractiveLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editUpsInteractiveLog('${upsInteractiveLog.uniId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="type" title="upsInteractiveLog.type" />
    			<ec:column property="orderNo" title="upsInteractiveLog.orderNo" />
    			<ec:column property="send" title="upsInteractiveLog.send" />
    			<ec:column property="receive" title="upsInteractiveLog.receive" />
    			<ec:column property="createTime" title="upsInteractiveLog.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editUpsInteractiveLog(uniId){
   		<jecs:power powerCode="editUpsInteractiveLog">
					window.location="editUpsInteractiveLog.html?strAction=editUpsInteractiveLog&uniId="+uniId;
			</jecs:power>
		}

</script>
