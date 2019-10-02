<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdDayBounsCalcHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdDayBounsCalcHistList.heading"/></content>
<meta name="menu" content="JbdDayBounsCalcHistMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdDayBounsCalcHist">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdDayBounsCalcHist.html"/>?strAction=addJbdDayBounsCalcHist'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdDayBounsCalcHistListTable"
	items="jbdDayBounsCalcHistList"
	var="jbdDayBounsCalcHist"
	action="${pageContext.request.contextPath}/jbdDayBounsCalcHists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdDayBounsCalcHist('${jbdDayBounsCalcHist.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdDayBounsCalcHist.wyear" />
    			<ec:column property="wmonth" title="jbdDayBounsCalcHist.wmonth" />
    			<ec:column property="wweek" title="jbdDayBounsCalcHist.wweek" />
    			<ec:column property="userCode" title="jbdDayBounsCalcHist.userCode" />
    			<ec:column property="companyCode" title="jbdDayBounsCalcHist.companyCode" />
    			<ec:column property="recommendNo" title="jbdDayBounsCalcHist.recommendNo" />
    			<ec:column property="linkNo" title="jbdDayBounsCalcHist.linkNo" />
    			<ec:column property="name" title="jbdDayBounsCalcHist.name" />
    			<ec:column property="petName" title="jbdDayBounsCalcHist.petName" />
    			<ec:column property="cardType" title="jbdDayBounsCalcHist.cardType" />
    			<ec:column property="isstore" title="jbdDayBounsCalcHist.isstore" />
    			<ec:column property="bank" title="jbdDayBounsCalcHist.bank" />
    			<ec:column property="bankaddress" title="jbdDayBounsCalcHist.bankaddress" />
    			<ec:column property="bankbook" title="jbdDayBounsCalcHist.bankbook" />
    			<ec:column property="bankcard" title="jbdDayBounsCalcHist.bankcard" />
    			<ec:column property="exitDate" title="jbdDayBounsCalcHist.exitDate" />
    			<ec:column property="firstMonth" title="jbdDayBounsCalcHist.firstMonth" />
    			<ec:column property="linkNum" title="jbdDayBounsCalcHist.linkNum" />
    			<ec:column property="pendingLinkNum" title="jbdDayBounsCalcHist.pendingLinkNum" />
    			<ec:column property="recommendNum" title="jbdDayBounsCalcHist.recommendNum" />
    			<ec:column property="pendingRecommendNum" title="jbdDayBounsCalcHist.pendingRecommendNum" />
    			<ec:column property="monthConsumerPv" title="jbdDayBounsCalcHist.monthConsumerPv" />
    			<ec:column property="pendingPv" title="jbdDayBounsCalcHist.pendingPv" />
    			<ec:column property="monthAreaTotalPv" title="jbdDayBounsCalcHist.monthAreaTotalPv" />
    			<ec:column property="weekGroupPv" title="jbdDayBounsCalcHist.weekGroupPv" />
    			<ec:column property="monthGroupPv" title="jbdDayBounsCalcHist.monthGroupPv" />
    			<ec:column property="pendingGroupPv" title="jbdDayBounsCalcHist.pendingGroupPv" />
    			<ec:column property="monthRecommendPv" title="jbdDayBounsCalcHist.monthRecommendPv" />
    			<ec:column property="pendingRecommendPv" title="jbdDayBounsCalcHist.pendingRecommendPv" />
    			<ec:column property="passStar" title="jbdDayBounsCalcHist.passStar" />
    			<ec:column property="passGrade" title="jbdDayBounsCalcHist.passGrade" />
    			<ec:column property="consumerAmount" title="jbdDayBounsCalcHist.consumerAmount" />
    			<ec:column property="pendingConsumerAmount" title="jbdDayBounsCalcHist.pendingConsumerAmount" />
    			<ec:column property="franchisePv" title="jbdDayBounsCalcHist.franchisePv" />
    			<ec:column property="pendingFranchisePv" title="jbdDayBounsCalcHist.pendingFranchisePv" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdDayBounsCalcHist(id){
   		<jecs:power powerCode="editJbdDayBounsCalcHist">
					window.location="editJbdDayBounsCalcHist.html?strAction=editJbdDayBounsCalcHist&id="+id;
			</jecs:power>
		}

</script>
