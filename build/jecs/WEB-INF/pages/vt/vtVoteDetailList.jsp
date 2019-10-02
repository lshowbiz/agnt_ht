<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVoteDetailList.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteDetailList.heading"/></content>
<meta name="menu" content="VtVoteDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addVtVoteDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editVtVoteDetail.html"/>?strAction=addVtVoteDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="vtVoteDetailListTable"
	items="vtVoteDetailList"
	var="vtVoteDetail"
	action="${pageContext.request.contextPath}/vtVoteDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editVtVoteDetail('${vtVoteDetail.vdId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="vtId" title="vtVoteDetail.vtId" />
    			<ec:column property="content" title="vtVoteDetail.content" />
    			<ec:column property="orderNo" title="vtVoteDetail.orderNo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editVtVoteDetail(vdId){
   		<jecs:power powerCode="editVtVoteDetail">
					window.location="editVtVoteDetail.html?strAction=editVtVoteDetail&vdId="+vdId;
			</jecs:power>
		}

</script>
