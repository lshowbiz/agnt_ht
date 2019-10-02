<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>
<style>

.td_detail{
font-weight: normal;
font-size: 12px;
} 
</style>

<li class="ui-li ui-li-divider ui-btn ui-bar-b ui-corner-top ui-btn-up-undefined" data-role="list-divider" role="heading"><jecs:locale  key="member.base.info"/></li>
<div class="ui-grid-b">
			<div class="ui-block-a" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;" >
					<h2 class="ui-li-heading"><jecs:locale  key="bdBounsDeduct.wweek"/></h2>
					<p class="ui-li-desc"><jecs:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" /></p>
					</span>
			</div>
			<div class="ui-block-b" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;">
					<h2 class="ui-li-heading"><jecs:locale  key="month.bouns"/></h2>
					<p class="ui-li-desc"><c:if test="${jbdMemberLinkCalcHist.status=='1'}">
        <jecs:locale  key="yesno.yes"/>
    </c:if>
    <c:if test="${jbdMemberLinkCalcHist.status!='1'}">
        <jecs:locale  key="yesno.no"/>
    </c:if></p>
					</span>
			</div>
			<div class="ui-block-c" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;border-right-width: 0" >
					<h2 class="ui-li-heading"><jecs:locale  key="is.active"/></h2>
					<p class="ui-li-desc"><c:if test="${jbdMemberLinkCalcHist.active=='1'}">
        <jecs:locale  key="yesno.yes"/>
    </c:if>
    <c:if test="${jbdMemberLinkCalcHist.active=='0'}">
        <jecs:locale  key="yesno.no"/>
    </c:if></p>
					</span>
			</div>



			<div class="ui-block-a" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;" >
					<h2 class="ui-li-heading"><jecs:locale  key="bdNetWorkCostReport.memberCH"/></h2>
					<p class="ui-li-desc">${jbdMemberLinkCalcHist.userCode }</p>
					</span>
			</div>
			<div class="ui-block-b" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;">
					<h2 class="ui-li-heading"><jecs:locale  key="miMember.petName"/></h2>
					<p class="ui-li-desc">${jbdMemberLinkCalcHist.petName }</p>
					</span>
			</div>
			<div class="ui-block-c" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;border-right-width: 0" >
					<h2 class="ui-li-heading"><jecs:locale  key="miMember.isstore"/></h2>
					<p class="ui-li-desc"><jecs:code listCode="isstore" value="${jbdMemberLinkCalcHist.isstore}"/></p>
					</span>
			</div>
			
			<div class="ui-block-a" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;" >
					<h2 class="ui-li-heading"><jecs:locale  key="bdCalculatingSubDetail.cardType"/></h2>
					<p class="ui-li-desc"><jecs:code listCode="bd.cardtype" value="${jbdMemberLinkCalcHist.cardType}"/></p>
					</span>
			</div>
			<div class="ui-block-b" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;">
					<h2 class="ui-li-heading"><jecs:locale  key="jbdMemberLinkCalcHist.honorStar"/></h2>
					<p class="ui-li-desc"><jecs:code listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar}"/></p>
					</span>
			</div>
			<div class="ui-block-c" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;border-right-width: 0" >
					<h2 class="ui-li-heading"><jecs:locale  key="jbdMemberLinkCalcHist.passStar"/></h2>
					<p class="ui-li-desc"><jecs:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar}"/></p>
					</span>
			</div>
			
			

			<div class="ui-block-a" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;" >
					<h2 class="ui-li-heading" style="white-space:normal"><jecs:locale  key="jbdMemberLinkCalcHist.monthConsumerPv"/></h2>
					<p class="ui-li-desc">${jbdMemberLinkCalcHist.monthConsumerPv} </p>
					</span>
			</div>
			<div class="ui-block-b" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;">
					<h2 class="ui-li-heading" style="white-space:normal"><jecs:locale  key="jbdMemberLinkCalcHist.monthAreaTotalPv"/></h2>
					<p class="ui-li-desc">${jbdMemberLinkCalcHist.monthAreaTotalPv}</p>
					</span>
			</div>
			<div class="ui-block-c" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;border-
right-width: 0" >
					<h2 class="ui-li-heading" style="white-space:normal"><jecs:locale  key="jbdMemberLinkCalcHist.areaConsumption"/></h2>
					<p class="ui-li-desc">${jbdMemberLinkCalcHist.areaConsumption}</p>
					</span>
			</div>
			
			<div class="ui-block-a" >
					<span class="ui-bar ui-bar-c" style="border-left-width: 0;border-top-width: 0;" >
					<h2 class="ui-li-heading" ><jecs:label  key="bdNetWorkCostReport.rateCH"/></h2>
					<p class="ui-li-desc">${jbdMemberLinkCalcHist.exchangeRate}</p>
					</span>
			</div>

		</div>





  
</ul>

<br/>

<li class="ui-li ui-li-divider ui-btn ui-bar-b ui-corner-top ui-btn-up-undefined" data-role="list-divider" role="heading"><jecs:locale  key="jbdMemberLinkCalcHist.pv"/></li>




<ul data-role="listview" style="margin: 0px;" >

	
  <li><a rel="external"  href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureSalesPv">
    <h3><jecs:label  key="jbdMemberLinkCalcHist.ventureSalesPv.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" > <c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureSalesPv}">
    		0.00    	</c:when>
    	<c:otherwise>
			${jbdMemberLinkCalcHist.ventureSalesPv} 
    	</c:otherwise>
	</c:choose></td>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.ventureSalesPv}">
	    		0.00    	</c:when>
	    	<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/> 
	    	</c:otherwise>
		</c:choose></td>
  </tr>
</table>
  </a></li>
  

  <li> <jecs:power powerCode="viewSomeData">
		   <a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureFund">
		    </jecs:power>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.ventureFund.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.ventureFund}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					${jbdMemberLinkCalcHist.ventureFund}    	
				</c:otherwise>
		    </c:choose></td>
    <td class="td_detail" ><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.ventureFund}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					    	<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureFund*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
				</c:otherwise>
		    </c:choose></td>
  </tr>
</table>
  </a></li>
  
  <li> <jecs:power powerCode="viewSomeData">
		 <a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureLeaderPv">
		</jecs:power>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.ventureLeaderPv37.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureLeaderPv}" type="number" pattern="###,###,##0.00"/>
	 	</c:otherwise>
    </c:choose></td>
    <td class="td_detail" ><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			  <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureLeaderPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	</c:otherwise>
    </c:choose></td>
  </tr>
</table>
  </a></li>


  <li><jecs:power powerCode="viewSomeData">
			<a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=recommendBonusPv">
			</jecs:power>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.recommendBonusPv.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.recommendBonusPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td class="td_detail" ><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					<fmt:formatNumber value="${jbdMemberLinkCalcHist.recommendBonusPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
		    	</c:otherwise>
		    </c:choose></td>
  </tr>
</table>
  </a></li>





  <li><jecs:power powerCode="viewSomeData">
			<a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeExpandPv">
			</jecs:power>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.storeExpandPv.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.storeExpandPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.storeExpandPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
  </tr>
</table>
  </a></li>




  <li>
          <jecs:power powerCode="viewSomeData">
			<a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeServePv">
			</jecs:power>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.storeServePv.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeServePv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.storeServePv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeServePv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.storeServePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
  </tr>
</table>
  </a></li>




  <li>
          <jecs:power powerCode="viewSomeData">
			
				<a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeRecommendPv">
			</jecs:power>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.storeRecommendPv.pv"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.storeRecommendPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td class="td_detail" ><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				 <fmt:formatNumber value="${jbdMemberLinkCalcHist.storeRecommendPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
  </tr>
</table>
  </a></li>




  <li>
    <h3><b> <jecs:label  key="bd.send.bounspv"/></b></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><b>${jbdMemberLinkCalcHist.bounsPv}</b></td>
    <td class="td_detail" >
    	<jecs:power powerCode="viewSomeData">
    	<b><fmt:formatNumber value="${jbdMemberLinkCalcHist.bounsMoney}" type="number" pattern="###,###,##0.00"/></b>
    	</jecs:power>
	</td>
  </tr>
</table>
  </li>


</ul>



<br/>
<li class="ui-li ui-li-divider ui-btn ui-bar-b ui-corner-top ui-btn-up-undefined" data-role="list-divider" role="heading"><jecs:locale  key="msgclassno.10"/></li>

<ul data-role="listview" style="margin: 0px;" >


  <li><a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
    <h3><jecs:label  key="franchise.moeny"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><fmt:formatNumber value="${jbdMemberLinkCalcHist.franchiseMoney}" type="number" pattern="###,###,##0.00"/></td>
  </tr>
</table>
  </a></li>

  <li> <a rel="external" href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=consumerAmount">
    <h3><jecs:label  key="consumer.amount"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><fmt:formatNumber value="${jbdMemberLinkCalcHist.consumerAmount}" type="number" pattern="###,###,##0.00"/></td>
  </tr>
</table>
  </a></li>
  
  <li>
    <h3><jecs:label  key="jbdMemberLinkCalcHist.deductMoney"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><fmt:formatNumber value="${jbdMemberLinkCalcHist.deductMoney}" type="number" pattern="###,###,##0.00"/></td>
  </tr>
</table>
  </a></li>

  <li>
    <h3> <jecs:label  key="jbdMemberLinkCalcHist.deductTax"/>	</h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><fmt:formatNumber value="${jbdMemberLinkCalcHist.deductTax}" type="number" pattern="###,###,##0.00"/></td>
  </tr>
</table>
 </li>


  <li>
    <h3><jecs:label  key="bdSendRecord.networkMoney"/>	</h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><fmt:formatNumber value="${jbdMemberLinkCalcHist.networkMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td class="td_detail" ></td>
  </tr>
</table>
</li>

  <li>
    <h3> <jecs:label  key="bd.currentDev.bd"/>	</h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><fmt:formatNumber value="${jbdMemberLinkCalcHist.currentDev}" type="number" pattern="###,###,##0.00"/></td>
  </tr>
</table>
</a></li>







</ul>






 <jecs:power powerCode="viewSomeData">
<br/>
<li class="ui-li ui-li-divider ui-btn ui-bar-b ui-corner-top ui-btn-up-undefined" data-role="list-divider" role="heading"><jecs:locale  key="bdSendRecord.bonusMoney"/></li>

<ul data-role="listview" style="margin: 0px;" >


  <li>
    <h3><jecs:label  key="bdSendRecord.bonusMoney"/></h3>
		
<table width="100%" border="0">
  <tr>
    <td class="td_detail" ><b><fmt:formatNumber value="${jbdMemberLinkCalcHist.sendMoney}" type="number" pattern="###,###,##0.00"/></b></td>
  </tr>
</table>
  </a></li>





</ul>


</jecs:power>


</html>


