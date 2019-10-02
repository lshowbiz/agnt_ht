<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdVentureLeaderSubHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdVentureLeaderSubHistList.heading"/></content>
<meta name="menu" content="JbdVentureLeaderSubHistMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdVentureLeaderSubHist">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdVentureLeaderSubHist.html"/>?strAction=addJbdVentureLeaderSubHist'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdVentureLeaderSubHistListTable"
	items="jbdVentureLeaderSubHistList"
	var="jbdVentureLeaderSubHist"
	action="${pageContext.request.contextPath}/jbdVentureLeaderSubHists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdVentureLeaderSubHist('${jbdVentureLeaderSubHist.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdVentureLeaderSubHist.wyear" />
    			<ec:column property="wmonth" title="jbdVentureLeaderSubHist.wmonth" />
    			<ec:column property="wweek" title="jbdVentureLeaderSubHist.wweek" />
    			<ec:column property="companyCode" title="jbdVentureLeaderSubHist.companyCode" />
    			<ec:column property="userCode" title="jbdVentureLeaderSubHist.userCode" />
    			<ec:column property="bounsType" title="jbdVentureLeaderSubHist.bounsType" />
    			<ec:column property="nlevel" title="jbdVentureLeaderSubHist.nlevel" />
    			<ec:column property="recommendedCode" title="jbdVentureLeaderSubHist.recommendedCode" />
    			<ec:column property="passGroupPv" title="jbdVentureLeaderSubHist.passGroupPv" />
    			<ec:column property="bounsPoint" title="jbdVentureLeaderSubHist.bounsPoint" />
    			<ec:column property="bounsMoney" title="jbdVentureLeaderSubHist.bounsMoney" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdVentureLeaderSubHist(id){
   		<jecs:power powerCode="editJbdVentureLeaderSubHist">
					window.location="editJbdVentureLeaderSubHist.html?strAction=editJbdVentureLeaderSubHist&id="+id;
			</jecs:power>
		}

</script>
