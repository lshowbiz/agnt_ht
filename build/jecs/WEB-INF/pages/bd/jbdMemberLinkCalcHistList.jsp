<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdMemberLinkCalcHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistList.heading"/></content>
<meta name="menu" content="JbdMemberLinkCalcHistMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdMemberLinkCalcHist">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdMemberLinkCalcHist.html"/>?strAction=addJbdMemberLinkCalcHist'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdMemberLinkCalcHistListTable"
	items="jbdMemberLinkCalcHistList"
	var="jbdMemberLinkCalcHist"
	action="${pageContext.request.contextPath}/jbdMemberLinkCalcHists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJbdMemberLinkCalcHist('${jbdMemberLinkCalcHist.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdMemberLinkCalcHist.wyear" />
    			<ec:column property="wmonth" title="jbdMemberLinkCalcHist.wmonth" />
    			<ec:column property="wweek" title="jbdMemberLinkCalcHist.wweek" />
    			<ec:column property="companyCode" title="jbdMemberLinkCalcHist.companyCode" />
    			<ec:column property="userCode" title="jbdMemberLinkCalcHist.userCode" />
    			<ec:column property="recommendNo" title="jbdMemberLinkCalcHist.recommendNo" />
    			<ec:column property="linkNo" title="jbdMemberLinkCalcHist.linkNo" />
    			<ec:column property="name" title="jbdMemberLinkCalcHist.name" />
    			<ec:column property="petName" title="jbdMemberLinkCalcHist.petName" />
    			<ec:column property="isstore" title="jbdMemberLinkCalcHist.isstore" />
    			<ec:column property="cardType" title="jbdMemberLinkCalcHist.cardType" />
    			<ec:column property="bank" title="jbdMemberLinkCalcHist.bank" />
    			<ec:column property="bankaddress" title="jbdMemberLinkCalcHist.bankaddress" />
    			<ec:column property="bankbook" title="jbdMemberLinkCalcHist.bankbook" />
    			<ec:column property="bankcard" title="jbdMemberLinkCalcHist.bankcard" />
    			<ec:column property="exitDate" title="jbdMemberLinkCalcHist.exitDate" />
    			<ec:column property="passStatus" title="jbdMemberLinkCalcHist.passStatus" />
    			<ec:column property="honorStar" title="jbdMemberLinkCalcHist.honorStar" />
    			<ec:column property="passStar" title="jbdMemberLinkCalcHist.passStar" />
    			<ec:column property="honorGrade" title="jbdMemberLinkCalcHist.honorGrade" />
    			<ec:column property="passGrade" title="jbdMemberLinkCalcHist.passGrade" />
    			<ec:column property="honorPosition" title="jbdMemberLinkCalcHist.honorPosition" />
    			<ec:column property="monthConsumerPv" title="jbdMemberLinkCalcHist.monthConsumerPv" />
    			<ec:column property="monthAreaTotalPv" title="jbdMemberLinkCalcHist.monthAreaTotalPv" />
    			<ec:column property="weekGroupPv" title="jbdMemberLinkCalcHist.weekGroupPv" />
    			<ec:column property="monthGroupPv" title="jbdMemberLinkCalcHist.monthGroupPv" />
    			<ec:column property="successSalesRate" title="jbdMemberLinkCalcHist.successSalesRate" />
    			<ec:column property="monthRecommendGroupPv" title="jbdMemberLinkCalcHist.monthRecommendGroupPv" />
    			<ec:column property="franchisePv" title="jbdMemberLinkCalcHist.franchisePv" />
    			<ec:column property="franchiseMoney" title="jbdMemberLinkCalcHist.franchiseMoney" />
    			<ec:column property="consumerAmount" title="jbdMemberLinkCalcHist.consumerAmount" />
    			<ec:column property="ventureSalesPv" title="jbdMemberLinkCalcHist.ventureSalesPv" />
    			<ec:column property="ventureLeaderPv" title="jbdMemberLinkCalcHist.ventureLeaderPv" />
    			<ec:column property="successSalesPv" title="jbdMemberLinkCalcHist.successSalesPv" />
    			<ec:column property="successLeaderPv" title="jbdMemberLinkCalcHist.successLeaderPv" />
    			<ec:column property="deductMoney" title="jbdMemberLinkCalcHist.deductMoney" />
    			<ec:column property="deductTax" title="jbdMemberLinkCalcHist.deductTax" />
    			<ec:column property="exchangeRate" title="jbdMemberLinkCalcHist.exchangeRate" />
    			<ec:column property="keepPv" title="jbdMemberLinkCalcHist.keepPv" />
    			<ec:column property="keepUserCode" title="jbdMemberLinkCalcHist.keepUserCode" />
    			<ec:column property="lastKeepPv" title="jbdMemberLinkCalcHist.lastKeepPv" />
    			<ec:column property="lastKeepUserCode" title="jbdMemberLinkCalcHist.lastKeepUserCode" />
    			<ec:column property="departmenKeepPv" title="jbdMemberLinkCalcHist.departmenKeepPv" />
    			<ec:column property="passStarGroupPv" title="jbdMemberLinkCalcHist.passStarGroupPv" />
    			<ec:column property="passGradeGroupPv" title="jbdMemberLinkCalcHist.passGradeGroupPv" />
    			<ec:column property="firstMonth" title="jbdMemberLinkCalcHist.firstMonth" />
    			<ec:column property="bounsPv" title="jbdMemberLinkCalcHist.bounsPv" />
    			<ec:column property="bounsMoney" title="jbdMemberLinkCalcHist.bounsMoney" />
    			<ec:column property="sendMoney" title="jbdMemberLinkCalcHist.sendMoney" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdMemberLinkCalcHist(id){
   		<jecs:power powerCode="editJbdMemberLinkCalcHist">
					window.location="editJbdMemberLinkCalcHist.html?strAction=editJbdMemberLinkCalcHist&id="+id;
			</jecs:power>
		}

</script>
