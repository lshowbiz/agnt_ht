<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSellCalcSubHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSellCalcSubHistList.heading"/></content>
<meta name="menu" content="JbdSellCalcSubHistMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdSellCalcSubHist">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdSellCalcSubHist.html"/>?strAction=addJbdSellCalcSubHist'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdSellCalcSubHistListTable"
	items="jbdSellCalcSubHistList"
	var="jbdSellCalcSubHist"
	action="${pageContext.request.contextPath}/jbdSellCalcSubHists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJbdSellCalcSubHist('${jbdSellCalcSubHist.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdSellCalcSubHist.wyear" />
    			<ec:column property="wmonth" title="jbdSellCalcSubHist.wmonth" />
    			<ec:column property="wweek" title="jbdSellCalcSubHist.wweek" />
    			<ec:column property="userCode" title="jbdSellCalcSubHist.userCode" />
    			<ec:column property="linkNo" title="jbdSellCalcSubHist.linkNo" />
    			<ec:column property="companyCode" title="jbdSellCalcSubHist.companyCode" />
    			<ec:column property="cardType" title="jbdSellCalcSubHist.cardType" />
    			<ec:column property="groupPv" title="jbdSellCalcSubHist.groupPv" />
    			<ec:column property="bounsPoint" title="jbdSellCalcSubHist.bounsPoint" />
    			<ec:column property="bounsPv" title="jbdSellCalcSubHist.bounsPv" />
    			<ec:column property="keepPv" title="jbdSellCalcSubHist.keepPv" />
    			<ec:column property="lastKeepPv" title="jbdSellCalcSubHist.lastKeepPv" />
    			<ec:column property="serialNumber" title="jbdSellCalcSubHist.serialNumber" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdSellCalcSubHist(id){
   		<jecs:power powerCode="editJbdSellCalcSubHist">
					window.location="editJbdSellCalcSubHist.html?strAction=editJbdSellCalcSubHist&id="+id;
			</jecs:power>
		}

</script>
