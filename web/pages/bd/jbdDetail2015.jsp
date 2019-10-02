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



<c:if test="${type=='ventureLeaderPv201607' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHists"
		var="jbdVentureLeaderSubHist"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="BounsCalcSell.xls" tooltip="Export Excel"/>
			  
			    				<ec:column property="nlevel" title="代数" />
			    				<ec:column property="recommendedCode" title="miMember.memberNo" />
			    				<ec:column property="memberOrderNo" title="订单编号" />
			    				<ec:column property="orderType" title="订单类型" >
									<jecs:code listCode="po.all.order_type" value="${jbdVentureLeaderSubHist.orderType }"></jecs:code>
								</ec:column>
			    				<ec:column property="passGroupPv" title="订单PV" />
			    				
								<ec:column property="bounsPoint" title="bdCalculatingSubDetail.bounsPoint" >
			    				<fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%
								</ec:column>
			    				<ec:column property="bounsMoney" title="销售奖PV" />	
			    				 
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='ventureLeaderPv201805' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHists"
		var="jbdVentureLeaderSubHist"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="BounsCalcSell.xls" tooltip="Export Excel"/>
			<ec:column property="num" title="序号" />
			<ec:column property="user_code" title="会员编号" />
			<ec:column property="w_week" title="结算日期" />
			<ec:column property="week_group_first_pv_lnk" title="新增业绩" />
			<ec:column property="bouns" title="封顶后奖金PV" />
			<ec:column property="keep_pv" title="当日保留业绩PV" />
			<ec:column property="last_keep_pv" title="上日保留业绩PV" />			
			<ec:column property="bouns_point" title="奖金比率" >
			<fmt:formatNumber value="${jbdVentureLeaderSubHist.bouns_point*100 }" type="number" pattern="###,###,###.##"/>%	
			</ec:column>
			<jecs:power powerCode="viewSomeData">	
				<ec:column property="1" title="title.view" width="100px" viewsDenied="xls">
					<a href="jbdDetail.html?userCode=${jbdVentureLeaderSubHist.user_code }&wweek=${jbdVentureLeaderSubHist.w_week }&type=ventureLeaderPvDetail201805" ><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" />
				</ec:column>
			</jecs:power>	 
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='ventureLeaderPvDetail201805' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHistDetails"
		var="obj"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="BounsCalcSell.xls" tooltip="Export Excel"/>
			<ec:column property="reuser_code" title="会员编号" />
			<ec:column property="member_order_no" title="订单编号" />
			<ec:column property="order_pv" title="业绩" />
		</ec:row>
	</ec:table>	

</c:if>
<c:if test="${type=='popularizeBonusPv' }">
	<ec:table 
		tableId="popularizeBonusPvsTable"
		items="popularizeBonusPvs"
		var="obj"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="Sales.xls" tooltip="Export Excel"/>
			<ec:column property="reuser_code" title="贡献会员" />
			<ec:column property="order_pv" title="贡献订单业绩" />
			<ec:column property="member_order_no" title="贡献订单编号" />
			<ec:column property="com_rate" title="领取比例" >
    			<fmt:formatNumber value="${obj.com_rate*100 }" type="number" pattern="###,###,###.##"/>%
			</ec:column>
			<ec:column property="recommend_pv" title="领取奖金" >
				<fmt:formatNumber value="${obj.recommend_pv }" type="number" pattern="###,###,###.##"/>
			</ec:column>
					
					
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='ventureLeaderPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHists"
		var="jbdVentureLeaderSubHist"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="BounsCalcSell.xls" tooltip="Export Excel"/>
			
			
			<c:if test="${param.wweek==201516 }">
			    
			    				<ec:column property="nlevel" title="bdMemberBounsCalcSell.level" />
								<ec:column property="recommendedCode" title="miMember.memberNo" />
								<ec:column property="memberLevel" title="miMember.cardType" >
									<jecs:code listCode="member.level" value="${jbdVentureLeaderSubHist.memberLevel}"/>
								</ec:column>
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
								
			    </c:if>
			  <c:if test="${param.wweek>201516 }">
			  
			    				<ec:column property="recommendedCode" title="miMember.memberNo" />
			    				<ec:column property="bounsType" title="类别" >
			    					<c:if test="${jbdVentureLeaderSubHist.bounsType=='01' }">
			    						首单
			    					</c:if>
			    					<c:if test="${jbdVentureLeaderSubHist.bounsType=='02' }">
			    						重消
			    					</c:if>	
			    				</ec:column>
			    				<ec:column property="passGroupPv" title="来源业绩" />
								<ec:column property="bounsPoint" title="bdCalculatingSubDetail.bounsPoint" >
			    				<fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%
								</ec:column>
			    				<ec:column property="bounsMoney" title="领取奖金PV" />	
			    				 
			    				<jecs:power powerCode="viewSomeData">	
    			<ec:column property="1" title="title.view" width="100px" viewsDenied="xls">
<a href="jbdDetail.html?userCode=${jbdVentureLeaderSubHist.recommendedCode }&wweek=${jbdVentureLeaderSubHist.wweek }&type=ventureLeaderPvDetail" ><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" />
				</ec:column>
					</jecs:power>
					
			    		
			    </c:if>
				
			
			
			
			
					
		</ec:row>
	</ec:table>	

</c:if>

<c:if test="${type=='successSalesPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdMemberLinkCalcHists"
		var="jbdMemberLinkCalcHist"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
		<ec:exportXls fileName="Sales.xls" tooltip="Export Excel"/>	
		
    <c:choose>
    
    	
    	<c:when test="${param.wweek >=201701}">
    	
    		 <ec:column property="part_no" title="层数" />
    		 <ec:column property="reuser_code" title="会员编号" />
    		 <ec:column property="2" title="订单编号" />
    		 <ec:column property="1" title="订单类型" />
			<ec:column property="rets_group_pv" title="订单业绩" />
			<ec:column property="sales_rate" title="奖金比率" >
			<fmt:formatNumber value="${jbdMemberLinkCalcHist.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%
			</ec:column>
			<ec:column property="success_sales_pv" title="重消奖" />
			
			
    	</c:when>
    
    
    	<c:when test="${param.wweek >=201532}">
    	
    		 <ec:column property="reuser_code" title="领取会员编号" />
			<ec:column property="rets_group_pv" title="销售业绩" />
			<ec:column property="sales_rate" title="卓越奖奖励比率" >
			<fmt:formatNumber value="${jbdMemberLinkCalcHist.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%
			</ec:column>
			<ec:column property="success_sales_pv" title="卓越奖PV" />
			
			
    	</c:when>
    	<c:when test="${param.wweek >201516}">
    		 <ec:column property="reuser_code" title="奖衔会员编号" />
			<ec:column property="rets_group_pv" title="销售业绩" />
			<ec:column property="sales_rate" title="领导奖奖励比率" >
			<fmt:formatNumber value="${jbdMemberLinkCalcHist.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%
			</ec:column>
			<ec:column property="success_sales_pv" title="领导奖PV" />
			
    	</c:when>
    	<c:otherwise>
			
			</c:otherwise>
    </c:choose>
    
		
					
					
		</ec:row>
	</ec:table>	

</c:if>




<c:if test="${type=='successLeaderPvDetail' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="successLeaderPvDetailList"
		var="jbdMemberLinkCalcHist"
		width="100%"
		showPagination="false"
		showStatusBar="true"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" >
		
		<ec:exportXls fileName="successLeaderPvDetailList.xls"/>
			<ec:column property="re_user_code" title="会员编号" />
			<ec:column property="member_order_no" title="订单编号" />
			<ec:column property="pv_amt" title="订单PV" />
			<%-- <ec:column property="part_no	number" title="部门编号" />
			<ec:column property="re_user_code1" title="第一代编号" /> --%>
			
					
					
		</ec:row>
	</ec:table>	

</c:if>




<%-- <c:if test="${type=='successLeaderPv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdVentureLeaderSubHists"
		var="jbdVentureLeaderSubHist"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			
					<ec:column property="nlevel" title="bdMemberBounsCalcSell.level" />
					<ec:column property="recommendedCode" title="miMember.memberNo.pass" />
					<ec:column property="passGroupPv" title="group.pv.pass" />
					<ec:column property="bounsPoint" title="bdCalculatingSubDetail.bounsPoint" >
    				<fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%
					</ec:column>
					<ec:column property="bounsMoney" title="bd.send.bounspv" />
					
		</ec:row>
	</ec:table>	

</c:if> --%>

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
				


			
			
		  <c:choose>
    	<c:when test="${param.wweek ==201516}">
    	     		
			
    			<ec:column property="recommendedCode" title="miMember.memberNo" />
    			<ec:column property="recommendedOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="recommendedOrderType" title="pd.orderType" >
        				<jecs:code listCode="po.all.order_type" value="${fn:trim(recommendBouns.recommendedOrderType)}"/>
    			</ec:column>
    			<ec:column property="pv" title="report.inPv" />
			
    	</c:when>
    	<c:when test="${param.wweek >=201532}">
    	     	
			
    			<ec:column property="recommendedCode" title="miMember.memberNo" />
    			<ec:column property="recommendedOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="recommendedOrderType" title="pd.orderType" >
        				<jecs:code listCode="po.all.order_type" value="${fn:trim(recommendBouns.recommendedOrderType)}"/>
    			</ec:column>
    			<ec:column property="pv" title="销售PV" />	
			
			
    	</c:when>
    	<c:when test="${param.wweek >201516}">
    		<ec:column property="nlevel" title="层数" />
    			<ec:column property="recommendedCode" title="miMember.memberNo" />
    			<ec:column property="pv" title="销售业绩" />
    			<ec:column property="bounsRate" title="奖金比率" >
    			
   					<fmt:formatNumber value="${recommendBouns.bounsRate*100 }" type="number" pattern="###,###,###.##"/>%
    			</ec:column>
    			<ec:column property="bounsPv" title="奖金PV" />
			
    	</c:when>
    	<c:otherwise>
			
			</c:otherwise>
    </c:choose>	
			
			
			
			
			
				
				
    			
    			
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


<c:if test="${type=='bdjPv201607' }">
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
				<ec:exportXls fileName="bdj.xls" tooltip="Export Excel"/>
    			<ec:column property="reno" title="storeExpandPv.recommendedNo" />
    			<ec:column property="isstore" title="busi.common.store" >
        				<jecs:code listCode="isstore" value="${fn:trim(storeExpandPv.isstore)}"/>
    			</ec:column>
    			<ec:column property="order_no" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="pv_amt" title="bd.fees.pv" />
    			
				<ec:column property="bfb" title="billAccount.persent" >
				
				<c:choose> 
		            <c:when test="${storeExpandPv.bfb =='1'}">&nbsp;</c:when> 
		            <c:otherwise><fmt:formatNumber value="${storeExpandPv.bfb*100 }" type="number" pattern="###,###,###.##"/>%</c:otherwise> 
				</c:choose>
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

<c:if test="${type=='successLeaderPv' }"><!-- 代数奖 -->
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdSuccessLeaderPvList"
		var="jbdSuccessLeaderPv"
		width="100%"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
				<ec:exportXls fileName="Leader.xls" tooltip="Export Excel"/>
				<ec:row >
    			<ec:column property="algebra" title="代数" />
    			<ec:column property="re_user_code" title="miMember.memberNo" />
    			<ec:column property="repass_star" title="奖衔" >
    				<jecs:code listCode="pass.star.zero" value="${jbdSuccessLeaderPv.repass_star}"/>
    			</ec:column>
    			<ec:column property="success_rate" title="奖金比率" >
    				<fmt:formatNumber value="${jbdSuccessLeaderPv.success_rate*100 }" type="number" pattern="###,###,###.##"/>%
    			</ec:column>
    			<ec:column property="success_leader_pv" title="感恩奖奖金PV" >
<fmt:formatNumber value="${jbdSuccessLeaderPv.success_leader_pv }" type="number" pattern="###,###,##0.##"/>
</ec:column>
    			 <jecs:power powerCode="viewSomeData">	
    			<ec:column property="1" title="title.view" width="100px" viewsDenied="xls">
<a href="jbdDetail.html?userCode=${jbdSuccessLeaderPv.re_user_code }&wweek=${jbdSuccessLeaderPv.w_week }&type=successLeaderPvDetail&passStar=${jbdSuccessLeaderPv.repass_star }&algebra=${jbdSuccessLeaderPv.algebra}" ><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" />
				</ec:column>
					</jecs:power>
					
				</ec:row>

					
		</ec:row>
	</ec:table>	
</c:if>




<!-- 销售奖明细 的明细 -->
<c:if test="${type=='ventureLeaderPvDetail' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="ventureLeaderPvDetailList"
		var="jpoMemberOrder"
		width="100%"
		showPagination="false"
		showStatusBar="false"
	action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" >
		
			<ec:column property="user_code" title="会员编号" />
			<ec:column property="company_code" title="国别" />
			<ec:column property="member_order_no" title="订单编号" />
			<ec:column property="order_type" title="订单类型" >
				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.order_type }"></jecs:code>
			</ec:column>
			<ec:column property="pv_amt" title="销售PV" />
			
					
					
		</ec:row>
	</ec:table>	

</c:if>



<!-- 服务奖明细 的明细 -->
<c:if test="${type=='servicePv' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="servicePvList"
		var="servicePv"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" >
		
			<ec:exportXls fileName="jbdDetail.xls"/>
			<ec:column property="dia_level" title="代数" />
			<ec:column property="reuser_code" title="会员编号" />
			<ec:column property="order_type" title="订单类型" >
				<jecs:code listCode="po.all.order_type" value="${servicePv.order_type }"></jecs:code>
			</ec:column>
			
			<ec:column property="cx_amt" title="订单PV" cell="number" format="###,###,###.##" />
   			<ec:column property="cx_rate" title="奖金比率" >
   				<fmt:formatNumber value="${servicePv.cx_rate*100 }" type="number" pattern="###,###,###.##"/>%
   			</ec:column>
			
			<ec:column property="cx_pv" title="服务奖PV" cell="number" format="###,###,###.##" />
					
					
		</ec:row>
	</ec:table>	

</c:if>


<!-- 服务奖明细 的明细201607 -->
<c:if test="${type=='servicePv201607' }">
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="servicePvList"
		var="servicePv"
		width="100%"
		showPagination="false"
		showStatusBar="false"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row highlightRow="false" >
			<ec:exportXls fileName="jbdDetail.xls"/>
			<ec:column property="part_no" title="部门数" />
			<ec:column property="reuser_code" title="会员编号" />
			<ec:column property="member_order_no" title="订单编号" />
			<ec:column property="order_type" title="订单类型" >
				<jecs:code listCode="po.all.order_type" value="${servicePv.order_type }"></jecs:code>
			</ec:column>
			<ec:column property="rets_group_pv" title="订单PV" cell="number" format="###,###,###.##" />
   			<ec:column property="sales_rate" title="奖金比率" >
   				<fmt:formatNumber value="${servicePv.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%
   			</ec:column>
			<ec:column property="success_sales_pv" title="服务奖PV" cell="number" format="###,###,###.##" />
		</ec:row>
	</ec:table>	

</c:if>



<script type="text/javascript">
window.top.document.getElementById("menuLabel").innerHTML='<jecs:locale key="function.menu.basicManage"/>>><jecs:locale key="function.menu.bonusManage"/>>><jecs:locale key="function.menu.bonusSearch"/>>><jecs:locale key="function.menu.addMiMember.bdSubDetails.html"/>';

</script>