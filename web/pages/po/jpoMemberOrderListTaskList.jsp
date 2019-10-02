<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderListTaskList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderListTaskList.heading"/></content>
<meta name="menu" content="JpoMemberOrderListTaskMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberOrderListTask">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberOrderListTask.html"/>?strAction=addJpoMemberOrderListTask'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoMemberOrderListTaskListTable"
	items="jpoMemberOrderListTaskList"
	var="jpoMemberOrderListTask"
	action="${pageContext.request.contextPath}/jpoMemberOrderListTasks.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberOrderListTask('${jpoMemberOrderListTask.moltId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="motId" title="jpoMemberOrderListTask.motId" />
    			<ec:column property="productId" title="jpoMemberOrderListTask.productId" />
    			<ec:column property="price" title="jpoMemberOrderListTask.price" />
    			<ec:column property="pv" title="jpoMemberOrderListTask.pv" />
    			<ec:column property="qty" title="jpoMemberOrderListTask.qty" />
    			<ec:column property="weight" title="jpoMemberOrderListTask.weight" />
    			<ec:column property="volume" title="jpoMemberOrderListTask.volume" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberOrderListTask(moltId){
   		<jecs:power powerCode="editJpoMemberOrderListTask">
					window.location="editJpoMemberOrderListTask.html?strAction=editJpoMemberOrderListTask&moltId="+moltId;
			</jecs:power>
		}

</script>
