<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jbdMemberLinkCalcHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistDetail.heading"/></content>


	<div id="titleBar" class="searchBar">
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>

<c:if test="${type=='ventureSalesPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdSellCalcSubHists"
		var="jbdSellCalcSubHist"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
					<ec:column property="serialNumber" title="column.headercell.tooltip.sort" />
			
					<ec:column property="userCode" title="miMember.memberNo" />
					<ec:column property="groupPv" title="jbdSellCalcSubHists.groupPV" />
					
					<c:if test="${param.wweek >= 201037 }">
						<ec:column property="areaConsumption" title="jbdSellCalcSubHists.areaConsumption" />
					</c:if>
					
					<ec:column property="bounsPoint" title="bdCalculatingSubDetail.bounsPoint" >
    				<fmt:formatNumber value="${jbdSellCalcSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%
					</ec:column>
					<ec:column property="bounsPv" title="bd.send.bounspv.top" />
					<ec:column property="keepPv" title="bdPv.pvType05" />
					<ec:column property="lastKeepPv" title="bdBonus.tree.pre.keepPv" />
				
          <jecs:power powerCode="viewSomeData">	
    			<ec:column property="1" title="title.view" width="100px">
<img src="images/newIcons/search_16.gif" onclick="javascript:openWindow('jbdSellCalcSubDetailHists.html?userCode=${jbdSellCalcSubHist.userCode }&wweek=${jbdSellCalcSubHist.wweek }','','width=550,height=360,scrollbars=yes')" style="cursor:pointer"/>

				</ec:column>
					</jecs:power>
		</ec:row>
	</ec:table>	
</c:if>






<c:if test="${type=='ventureLeaderPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHists"
		var="jbdVentureLeaderSubHist"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
					<ec:exportXls fileName="BounsCalcSell.xls" tooltip="Export Excel"/>
					<ec:column property="nlevel" title="bdMemberBounsCalcSell.level" />
					<ec:column property="recommendedCode" title="miMember.memberNo.star" />
					<c:if test="${param.wweek>=201037 }">
						<ec:column property="passGroupPv" title="group.pv.star.37" />
					</c:if>
					<c:if test="${param.wweek<201037 }">
						<ec:column property="passGroupPv" title="group.pv.star" />
					</c:if>
					<ec:column property="bounsPoint" title="bdCalculatingSubDetail.bounsPoint" >
    				<fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%
					</ec:column>
					<ec:column property="bounsMoney" title="bd.send.bounspv" />
					
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='successSalesPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdMemberLinkCalcHists"
		var="jbdMemberLinkCalcHist"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="Sales.xls" tooltip="Export Excel"/>
			<ec:column property="userCode" title="miMember.memberNo" />
			<ec:column property="monthRecommendGroupPv" title="jbdMemberLinkCalcHist.monthRecommendGroupPv" />
			<ec:column property="successSalesRate" title="successSaleBouns.rate" />
			
					
					
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='successLeaderPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHists"
		var="jbdVentureLeaderSubHist"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
					<ec:exportXls fileName="Leader.xls" tooltip="Export Excel"/>
					<ec:column property="nlevel" title="bdMemberBounsCalcSell.level" />
					<ec:column property="recommendedCode" title="miMember.memberNo.pass" />
					<ec:column property="passGroupPv" title="group.pv.pass" />
					<ec:column property="bounsPoint" title="bdCalculatingSubDetail.bounsPoint" >
    				<fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%
					</ec:column>
					<ec:column property="bounsMoney" title="bd.send.bounspv" />
					
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='franchiseMoney' }">
	
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="franchiseMoneys"
		var="franchiseMoney"
		width="70%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="Franchise.xls" tooltip="Export Excel"/>
					<ec:column property="recommend_no" title="miMember.memberNo" />
					<ec:column property="member_order_no" title="poMemberOrder.memberOrderNo" />
					<ec:column property="fees" title="bd.fees.pv" />
				<ec:column property="percentage" title="billAccount.persent" >
					<c:if test="${not empty franchiseMoney.percentage }">
   						<fmt:formatNumber value="${franchiseMoney.percentage*100 }" type="number" pattern="###,###,###.##"/>%
					</c:if>
				</ec:column>
					
		</ec:row>
	</ec:table>	
</c:if>

<c:if test="${type=='consumerAmount' }">
	
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="consumerAmounts"
		var="consumerAmount"
		width="70%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			
					<ec:column property="recommend_no" title="miMember.memberNo" />
					<ec:column property="member_order_no" title="poMemberOrder.memberOrderNo" />
					<ec:column property="fees" title="pomember.fee" />
				<ec:column property="percentage" title="billAccount.persent" >
					<c:if test="${not empty consumerAmount.percentage }">
   						<fmt:formatNumber value="${consumerAmount.percentage*100 }" type="number" pattern="###,###,###.##"/>%
					</c:if>
				</ec:column>
					
		</ec:row>
	</ec:table>	
</c:if>




<c:if test="${type=='recommendBonusPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdSellCalcRecommendBouns"
		var="recommendBouns"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
				<ec:exportXls fileName="RecommendBouns.xls" tooltip="Export Excel"/>
				<ec:row >
    			<ec:column property="recommendedCode" title="miMember.memberNo" />
    			<ec:column property="recommendedOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="recommendedOrderType" title="pd.orderType" >
        				<jecs:code listCode="po.all.order_type" value="${fn:trim(jbdSellCalcSubDetailHist.recommendedOrderType)}"/>
    			</ec:column>
    			<ec:column property="pv" title="report.inPv" />
				</ec:row>

					
		</ec:row>
	</ec:table>	
</c:if>




<c:if test="${type=='storeExpandPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="storeExpandPvs"
		var="storeExpandPv"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
				<ec:exportXls fileName="StoreExpand.xls" tooltip="Export Excel"/>
    			<ec:column property="recommended_no" title="storeExpandPv.recommendedNo" />
    			<ec:column property="isstore" title="busi.common.store" >
        				<jecs:code listCode="isstore" value="${fn:trim(storeExpandPv.isstore)}"/>
    			</ec:column>
    			<ec:column property="order_no" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="pv_amt" title="bd.fees.pv" />
    			
				<ec:column property="proportion" title="billAccount.persent" >
   					<fmt:formatNumber value="${storeExpandPv.proportion*100 }" type="number" pattern="###,###,###.##"/>%
				</ec:column>
				
    			<ec:column property="pv" title="bd.send.bounspv" />
		</ec:row>
	</ec:table>	
</c:if>






<c:if test="${type=='storeServePv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="storeServePvs"
		var="storeServePv"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
    			<ec:column property="recommended_no" title="storeExpandPv.recommendedNo" />
    			<ec:column property="isstore" title="busi.common.store" >
        				<jecs:code listCode="isstore" value="${fn:trim(storeServePv.isstore)}"/>
    			</ec:column>
    			<ec:column property="order_no" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="pv_amt" title="bd.fees.pv" />
    			
				<ec:column property="proportion" title="billAccount.persent" >
   					<fmt:formatNumber value="${storeServePv.proportion*100 }" type="number" pattern="###,###,###.##"/>%
				</ec:column>
				
    			<ec:column property="pv" title="bd.send.bounspv" />	
		</ec:row>
	</ec:table>	
</c:if>




<c:if test="${type=='storeRecommendPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="storeRecommendPvs"
		var="storeRecommendPv"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
				<ec:exportXls fileName="StoreRecommend.xls" tooltip="Export Excel"/>
				<ec:row >
    			<ec:column property="recommended_no" title="miMember.recommendNo" />
    			<ec:column property="order_no" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="pv_amt" title="report.inPv" />
				<ec:column property="proportion" title="billAccount.persent" >
					<c:if test="${not empty storeRecommendPv.proportion }">
   						<fmt:formatNumber value="${storeRecommendPv.proportion*100 }" type="number" pattern="###,###,###.##"/>%
   					</c:if>
				</ec:column>
				</ec:row>

					
		</ec:row>
	</ec:table>	
</c:if>





<c:if test="${type=='ventureFund' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="ventureFundPvs"
		var="ventureFundPv"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
		
				<ec:row >
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<ec:column property="order_no" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="pv_amt" title="report.inPv" />
				<ec:column property="percentage" title="billAccount.persent" >
					<c:if test="${not empty ventureFundPv.percentage }">
   						<fmt:formatNumber value="${ventureFundPv.percentage*100 }" type="number" pattern="###,###,###.##"/>%
   					</c:if>
				</ec:column>
				</ec:row>

					
		</ec:row>
	</ec:table>	
</c:if>


<script type="text/javascript">
window.top.document.getElementById("menuLabel").innerHTML='<jecs:locale key="function.menu.basicManage"/>>><jecs:locale key="function.menu.bonusManage"/>>><jecs:locale key="function.menu.bonusSearch"/>>><jecs:locale key="function.menu.addMiMember.bdSubDetails.html"/>';

</script>