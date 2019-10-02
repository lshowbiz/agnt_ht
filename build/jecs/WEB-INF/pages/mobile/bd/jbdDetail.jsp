<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>



<ul data-role="listview" style="margin: 0px;" >


<c:if test="${type=='ventureSalesPv' }">

<c:forEach items="${jbdSellCalcSubHists }" var="jbdSellCalcSubHist">
  <li>

    <h3><jecs:label key="column.headercell.tooltip.sort"  />${ jbdSellCalcSubHist.serialNumber}</h3>
    <p><jecs:label key="miMember.memberNo"  />${ jbdSellCalcSubHist.userCode}</p>
    <p><jecs:label key="jbdSellCalcSubHists.groupPV"  />${ jbdSellCalcSubHist.groupPv}</p>
    <p><jecs:label key="jbdSellCalcSubHists.areaConsumption"  />${ jbdSellCalcSubHist.areaConsumption}</p>
    <p><jecs:label key="bdCalculatingSubDetail.bounsPoint"  /><fmt:formatNumber value="${jbdSellCalcSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%</p>
    <p><jecs:label key="bd.send.bounspv.top"  />${ jbdSellCalcSubHist.bounsPv}</p>
    <p><jecs:label key="bdPv.pvType05"  />${ jbdSellCalcSubHist.keepPv}</p>
    <p><jecs:label key="bdBonus.tree.pre.keepPv"  />${ jbdSellCalcSubHist.lastKeepPv}</p>
    <p><jecs:label key="bdBonus.tree.pre.keepPv"  />${ jbdSellCalcSubHist.lastKeepPv}</p>
    	
    	
  </a></li>
  

</c:forEach>

</c:if>



<c:if test="${type=='ventureLeaderPv' }">
<c:forEach items="${jbdVentureLeaderSubHists }" var="jbdVentureLeaderSubHist">
  <li>
    <h3><jecs:label key="bdMemberBounsCalcSell.level"  />${ jbdVentureLeaderSubHist.nlevel}</h3>
    <p><jecs:label key="miMember.memberNo.star"  />${ jbdVentureLeaderSubHist.recommendedCode}</p>
    <p><jecs:label key="group.pv.star.37"  />${ jbdVentureLeaderSubHist.passGroupPv}</p>
    <p><jecs:label key="bdCalculatingSubDetail.bounsPoint"  /><fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%</p>
    <p><jecs:label key="bd.send.bounspv"  />${ jbdVentureLeaderSubHist.bounsMoney}</p>
    	
    	
  </a></li>
</c:forEach>
</c:if>





<c:if test="${type=='successSalesPv' }">
<c:forEach items="${jbdMemberLinkCalcHists }" var="jbdMemberLinkCalcHist">
  <li>
    <p><jecs:label key="miMember.memberNo"  />${ jbdMemberLinkCalcHist.userCode}</p>
    <p><jecs:label key="jbdMemberLinkCalcHist.monthRecommendGroupPv"  />${ jbdMemberLinkCalcHist.monthRecommendGroupPv}</p>
    <p><jecs:label key="successSaleBouns.rate"  />${ jbdMemberLinkCalcHist.successSalesRate}</p>
    	
    	
  </a></li>
</c:forEach>
</c:if>


<c:if test="${type=='successLeaderPv' }">
<c:forEach items="${jbdVentureLeaderSubHists }" var="jbdVentureLeaderSubHist">
  <li>
    <h3><jecs:label key="bdMemberBounsCalcSell.level"  />${ jbdVentureLeaderSubHist.nlevel}</h3>
    <p><jecs:label key="miMember.memberNo.pass"  />${ jbdVentureLeaderSubHist.recommendedCode}</p>
    <p><jecs:label key="group.pv.pass"  />${ jbdVentureLeaderSubHist.passGroupPv}</p>
    <p><jecs:label key="bdCalculatingSubDetail.bounsPoint"  /><fmt:formatNumber value="${jbdVentureLeaderSubHist.bounsPoint*100 }" type="number" pattern="###,###,###.##"/>%</p>
    <p><jecs:label key="bd.send.bounspv"  />${ jbdVentureLeaderSubHist.bounsMoney}</p>
    	
  </a></li>
</c:forEach>
</c:if>


<c:if test="${type=='franchiseMoney' }">
<c:forEach items="${franchiseMoneys }" var="franchiseMoney">
  <li>
    <p><jecs:label key="miMember.memberNo"  />${ franchiseMoney.recommend_no}</p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ franchiseMoney.member_order_no}</p>
    <p><jecs:label key="bd.fees.pv"  />${ franchiseMoney.fees}</p>
    <p><jecs:label key="billAccount.persent"  />
	<c:if test="${not empty franchiseMoney.percentage }">
   						<fmt:formatNumber value="${franchiseMoney.percentage*100 }" type="number" pattern="###,###,###.##"/>%
					</c:if></p>
    	
  </a></li>
</c:forEach>
</c:if>



<c:if test="${type=='consumerAmount' }">
<c:forEach items="${consumerAmounts }" var="consumerAmount">
  <li>
    <p><jecs:label key="miMember.memberNo"  />${ consumerAmount.recommend_no}</p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ consumerAmount.member_order_no}</p>
    <p><jecs:label key="pomember.fee"  />${ consumerAmount.fees}</p>
    <p><jecs:label key="percentage"  />
					<c:if test="${not empty consumerAmount.percentage }">
   						<fmt:formatNumber value="${consumerAmount.percentage*100 }" type="number" pattern="###,###,###.##"/>%
					</c:if></p>
    	
  </a></li>
</c:forEach>
</c:if>




<c:if test="${type=='recommendBonusPv' }">
<c:forEach items="${jbdSellCalcRecommendBouns }" var="recommendBouns">
  <li>
    <p><jecs:label key="miMember.memberNo"  />${ recommendBouns.recommendedCode}</p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ recommendBouns.recommendedOrderNo}</p>
    <p><jecs:label key="pd.orderType"  /><jecs:code listCode="po.all.order_type" value="${fn:trim(recommendBouns.recommendedOrderType)}"/></p>
    <p><jecs:label key="report.inPv"  />${ recommendBouns.pv}</p>
    	
  </a></li>
</c:forEach>
</c:if>




<c:if test="${type=='storeExpandPv' }">
<c:forEach items="${storeExpandPvs }" var="storeExpandPv">
  <li>
    <p><jecs:label key="storeExpandPv.recommendedNo"  />${ storeExpandPv.recommended_no}</p>
    <p><jecs:label key="busi.common.store"  />	<jecs:code listCode="isstore" value="${fn:trim(storeExpandPv.isstore)}"/></p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ storeExpandPv.order_no}</p>
    <p><jecs:label key="bd.fees.pv"  />${ storeExpandPv.pv_amt}</p>
    <p><jecs:label key="billAccount.persent"  /><fmt:formatNumber value="${storeExpandPv.proportion*100 }" type="number" pattern="###,###,###.##"/>%</p>
    <p><jecs:label key="bd.send.bounspv"  />${ storeExpandPv.pv}</p>
    	
  </a></li>
</c:forEach>
</c:if>



<c:if test="${type=='storeServePv' }">
<c:forEach items="${storeServePvs }" var="storeServePv">
  <li>
    <p><jecs:label key="storeExpandPv.recommendedNo"  />${ storeServePv.recommended_no}</p>
    <p><jecs:label key="busi.common.store"  /><jecs:code listCode="isstore" value="${fn:trim(storeServePv.isstore)}"/></p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ storeServePv.order_no}</p>
    <p><jecs:label key="bd.fees.pv"  />${ storeServePv.pv_amt}</p>
    <p><jecs:label key="billAccount.persent"  /><fmt:formatNumber value="${storeServePv.proportion*100 }" type="number" pattern="###,###,###.##"/>%</p>
    <p><jecs:label key="bd.send.bounspv"  />${ storeServePv.pv}</p>
    	
  </a></li>
</c:forEach>
</c:if>





<c:if test="${type=='storeRecommendPv' }">
<c:forEach items="${storeRecommendPvs }" var="storeRecommendPv">
  <li>
    <p><jecs:label key="miMember.recommendNo"  />${ storeRecommendPv.recommended_no}</p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ storeRecommendPv.order_no}</p>
    <p><jecs:label key="report.inPv"  />${ storeRecommendPv.pv_amt}</p>
    <p><jecs:label key="billAccount.persent"  /><c:if test="${not empty storeRecommendPv.proportion }">
   						<fmt:formatNumber value="${storeRecommendPv.proportion*100 }" type="number" pattern="###,###,###.##"/>%
   					</c:if></p>
  </a></li>
</c:forEach>
</c:if>



<c:if test="${type=='ventureFund' }">
<c:forEach items="${ventureFundPvs }" var="ventureFundPv">
  <li>
    <p><jecs:label key="miMember.memberNo"  />${ ventureFundPv.user_code}</p>
    <p><jecs:label key="poMemberOrder.memberOrderNo"  />${ ventureFundPv.order_no}</p>
    <p><jecs:label key="report.inPv"  />${ ventureFundPv.pv_amt}</p>
    <p><jecs:label key="billAccount.persent"  /><c:if test="${not empty ventureFundPv.percentage }">
   						<fmt:formatNumber value="${ventureFundPv.percentage*100 }" type="number" pattern="###,###,###.##"/>%
   					</c:if></p>
  </a></li>
</c:forEach>
</c:if>


</ul>












</html>