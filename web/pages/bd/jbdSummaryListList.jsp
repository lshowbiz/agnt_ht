<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSummaryListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSummaryListList.heading"/></content>
<meta name="menu" content="JbdSummaryListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdSummaryList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdSummaryList.html"/>?strAction=addJbdSummaryList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdSummaryListListTable"
	items="jbdSummaryListList"
	var="jbdSummaryList"
	action="${pageContext.request.contextPath}/jbdSummaryLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdSummaryList('${jbdSummaryList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jbdSummaryList.userCode" />
    			<ec:column property="cardType" title="jbdSummaryList.cardType" />
    			<ec:column property="inType" title="jbdSummaryList.inType" />
    			<ec:column property="createTime" title="jbdSummaryList.createTime" />
    			<ec:column property="orderType" title="jbdSummaryList.orderType" />
    			<ec:column property="oldCheckDate" title="jbdSummaryList.oldCheckDate" />
    			<ec:column property="newCheckDate" title="jbdSummaryList.newCheckDate" />
    			<ec:column property="pvAmt" title="jbdSummaryList.pvAmt" />
    			<ec:column property="oldLinkNo" title="jbdSummaryList.oldLinkNo" />
    			<ec:column property="newLinkNo" title="jbdSummaryList.newLinkNo" />
    			<ec:column property="oldRecommendNo" title="jbdSummaryList.oldRecommendNo" />
    			<ec:column property="newRecommendNo" title="jbdSummaryList.newRecommendNo" />
    			<ec:column property="newCompanyCode" title="jbdSummaryList.newCompanyCode" />
    			<ec:column property="userCreateTime" title="jbdSummaryList.userCreateTime" />
    			<ec:column property="wweek" title="jbdSummaryList.wweek" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdSummaryList(id){
   		<jecs:power powerCode="editJbdSummaryList">
					window.location="editJbdSummaryList.html?strAction=editJbdSummaryList&id="+id;
			</jecs:power>
		}

</script>
