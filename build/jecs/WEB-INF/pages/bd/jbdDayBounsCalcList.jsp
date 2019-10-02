<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdDayBounsCalcList.title"/></title>
<content tag="heading"><jecs:locale key="jbdDayBounsCalcList.heading"/></content>
<meta name="menu" content="JbdDayBounsCalcMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdDayBounsCalc">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdDayBounsCalc.html"/>?strAction=addJbdDayBounsCalc'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdDayBounsCalcListTable"
	items="jbdDayBounsCalcList"
	var="jbdDayBounsCalc"
	action="${pageContext.request.contextPath}/jbdDayBounsCalcs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdDayBounsCalc('${jbdDayBounsCalc.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdDayBounsCalc.wyear" />
    			<ec:column property="wmonth" title="jbdDayBounsCalc.wmonth" />
    			<ec:column property="wweek" title="jbdDayBounsCalc.wweek" />
    			<ec:column property="userCode" title="jbdDayBounsCalc.userCode" />
    			<ec:column property="companyCode" title="jbdDayBounsCalc.companyCode" />
    			<ec:column property="recommendNo" title="jbdDayBounsCalc.recommendNo" />
    			<ec:column property="linkNo" title="jbdDayBounsCalc.linkNo" />
    			<ec:column property="name" title="jbdDayBounsCalc.name" />
    			<ec:column property="petName" title="jbdDayBounsCalc.petName" />
    			<ec:column property="cardType" title="jbdDayBounsCalc.cardType" />
    			<ec:column property="isstore" title="jbdDayBounsCalc.isstore" />
    			<ec:column property="bank" title="jbdDayBounsCalc.bank" />
    			<ec:column property="bankaddress" title="jbdDayBounsCalc.bankaddress" />
    			<ec:column property="bankbook" title="jbdDayBounsCalc.bankbook" />
    			<ec:column property="bankcard" title="jbdDayBounsCalc.bankcard" />
    			<ec:column property="exitDate" title="jbdDayBounsCalc.exitDate" />
    			<ec:column property="firstMonth" title="jbdDayBounsCalc.firstMonth" />
    			<ec:column property="linkNum" title="jbdDayBounsCalc.linkNum" />
    			<ec:column property="pendingLinkNum" title="jbdDayBounsCalc.pendingLinkNum" />
    			<ec:column property="recommendNum" title="jbdDayBounsCalc.recommendNum" />
    			<ec:column property="pendingRecommendNum" title="jbdDayBounsCalc.pendingRecommendNum" />
    			<ec:column property="monthConsumerPv" title="jbdDayBounsCalc.monthConsumerPv" />
    			<ec:column property="pendingPv" title="jbdDayBounsCalc.pendingPv" />
    			<ec:column property="monthAreaTotalPv" title="jbdDayBounsCalc.monthAreaTotalPv" />
    			<ec:column property="weekGroupPv" title="jbdDayBounsCalc.weekGroupPv" />
    			<ec:column property="monthGroupPv" title="jbdDayBounsCalc.monthGroupPv" />
    			<ec:column property="pendingGroupPv" title="jbdDayBounsCalc.pendingGroupPv" />
    			<ec:column property="monthRecommendPv" title="jbdDayBounsCalc.monthRecommendPv" />
    			<ec:column property="pendingRecommendPv" title="jbdDayBounsCalc.pendingRecommendPv" />
    			<ec:column property="passStar" title="jbdDayBounsCalc.passStar" />
    			<ec:column property="passGrade" title="jbdDayBounsCalc.passGrade" />
    			<ec:column property="consumerAmount" title="jbdDayBounsCalc.consumerAmount" />
    			<ec:column property="pendingConsumerAmount" title="jbdDayBounsCalc.pendingConsumerAmount" />
    			<ec:column property="franchisePv" title="jbdDayBounsCalc.franchisePv" />
    			<ec:column property="pendingFranchisePv" title="jbdDayBounsCalc.pendingFranchisePv" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdDayBounsCalc(id){
   		<jecs:power powerCode="editJbdDayBounsCalc">
					window.location="editJbdDayBounsCalc.html?strAction=editJbdDayBounsCalc&id="+id;
			</jecs:power>
		}

</script>
