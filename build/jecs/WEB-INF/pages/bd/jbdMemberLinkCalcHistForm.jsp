<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jbdMemberLinkCalcHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistDetail.heading"/></content>
<link type="text/css" rel="stylesheet" href="css/bd/style.css" />


	<div id="titleBar" class="searchBar">&nbsp;&nbsp;
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>



<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14"><jecs:locale  key="member.base.info"/></strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">
  <tr class="trbg">
    <td width="15%" align="right"><jecs:label  key="bdBounsDeduct.wweek"/></td>
    <td width="15%"> 
  	  <jecs:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" />
    </td>
    <td width="15%" align="right"> <jecs:label  key="month.bouns"/></td>
    <td width="15%"><c:if test="${jbdMemberLinkCalcHist.status=='1'}">
        <jecs:locale  key="yesno.yes"/>
    </c:if>
    <c:if test="${jbdMemberLinkCalcHist.status!='1'}">
        <jecs:locale  key="yesno.no"/>
    </c:if>  </td>
    <td width="15%" align="right"><jecs:label  key="is.active"/></td>
    <td width="15%"><c:if test="${jbdMemberLinkCalcHist.active=='1'}">
        <jecs:locale  key="yesno.yes"/>
    </c:if>
    <c:if test="${jbdMemberLinkCalcHist.active=='0'}">
        <jecs:locale  key="yesno.no"/>
    </c:if></td>
  </tr>
  <tr  >
    <td align="right"><jecs:label  key="bdNetWorkCostReport.memberCH"/></td>
    <td>${jbdMemberLinkCalcHist.userCode }</td>
    <td align="right"> <jecs:label  key="miMember.petName"/></td>
    <td>${jbdMemberLinkCalcHist.petName }</td>
    <td align="right"><jecs:label  key="miMember.isstore"/></td>
    <td> <jecs:code listCode="isstore" value="${jbdMemberLinkCalcHist.isstore}"/></td>
  </tr>
  <tr class="trbg">
    <td align="right"> <jecs:label  key="bdCalculatingSubDetail.cardType"/></td>
    <td> <jecs:code listCode="bd.cardtype" value="${jbdMemberLinkCalcHist.cardType}"/></td>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.honorStar"/></td>
    <td><jecs:code listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar}"/></td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.passStar"/></td>
    <td><jecs:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar}"/></td>
  </tr>
  <tr>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.monthConsumerPv"/></td>
    <td>${jbdMemberLinkCalcHist.monthConsumerPv} </td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.monthAreaTotalPv"/></td>
    <td>${jbdMemberLinkCalcHist.monthAreaTotalPv}</td>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.areaConsumption"/></td>
    <td>  ${jbdMemberLinkCalcHist.areaConsumption}</td>
  </tr>
  <tr>
    <td align="right"><jecs:label  key="bdNetWorkCostReport.rateCH"/></td>
    <td>${jbdMemberLinkCalcHist.exchangeRate} </td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.monthMaxPv"/></td>
    <td>${jbdMemberLinkCalcHist.monthMaxPv} </td>
    <td align="right"> </td>
    <td> </td>
  </tr>

</table>
<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14"><jecs:locale  key="jbdMemberLinkCalcHist.pv"/></strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">
  <tr class="trbg">
    <td width="20%" align="right"><jecs:label  key="jbdMemberLinkCalcHist.ventureSalesPv.pv"/></td>
    <td width="15%" align="right">  <c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureSalesPv}">
    		0.00    	</c:when>
    	<c:otherwise>
			${jbdMemberLinkCalcHist.ventureSalesPv} 
    	</c:otherwise>
	</c:choose></td>
    <td width="15%" align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.ventureSalesPv}">
	    		0.00    	</c:when>
	    	<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/> 
	    	</c:otherwise>
		</c:choose></td>
    <td align="center">
    <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureSalesPv"><jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a>
    	
    
    </td>
    </tr>
  <tr>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.ventureFund.pv"/></td>
    <td align="right"><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.ventureFund}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					${jbdMemberLinkCalcHist.ventureFund}    	
				</c:otherwise>
		    </c:choose></td>
    <td align="right"><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.ventureFund}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					    	<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureFund*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
				</c:otherwise>
		    </c:choose></td>
    <td align="center">
    	
          <jecs:power powerCode="viewSomeData">
		   <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureFund"><jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a>
		    </jecs:power>
    </td>
  </tr>
  <tr class="trbg">
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.ventureLeaderPv37.pv"/></td>
    <td align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			${jbdMemberLinkCalcHist.ventureLeaderPv}    	</c:otherwise>
    </c:choose></td>
    <td align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			  <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureLeaderPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	</c:otherwise>
    </c:choose></td>
    <td align="center">
    	
          <jecs:power powerCode="viewSomeData">
		 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureLeaderPv">
		<jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a> 
		</jecs:power>
    </td>
  </tr>
  <tr>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.recommendBonusPv.pv"/></td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.recommendBonusPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td align="right">   <c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					<fmt:formatNumber value="${jbdMemberLinkCalcHist.recommendBonusPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
		    	</c:otherwise>
		    </c:choose></td>
    <td align="center">
	     
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=recommendBonusPv">
			<jecs:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
			</jecs:power>
    </td>
  </tr>
  <tr>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.storeExpandPv.pv"/></td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.storeExpandPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.storeExpandPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
    <td align="center">
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeExpandPv">
			<jecs:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
			</jecs:power>
    </td>
  </tr>
  <tr class="trbg">
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.storeServePv.pv"/></td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeServePv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.storeServePv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td align="right">  <c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeServePv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.storeServePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
    <td align="center">
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeServePv">
			<jecs:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
			</jecs:power>
    </td>
  </tr>
  <tr>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.storeRecommendPv.pv"/></td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.storeRecommendPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td align="right"> <c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				 <fmt:formatNumber value="${jbdMemberLinkCalcHist.storeRecommendPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
    <td align="center">
          <jecs:power powerCode="viewSomeData">
			
				<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeRecommendPv">
			<jecs:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
			</jecs:power>
    </td>
  </tr>
  <tr class="trbg">
    <td align="right"><b> <jecs:label  key="bd.send.bounspv"/></b></td>
    <td align="right"><b>${jbdMemberLinkCalcHist.bounsPv}</b></td>
    <td align="right">
    	<jecs:power powerCode="viewSomeData">
    	<b><fmt:formatNumber value="${jbdMemberLinkCalcHist.bounsMoney}" type="number" pattern="###,###,##0.00"/></b>
    	</jecs:power>
    </td>
    <td align="center"></td>
  </tr>
</table>





<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14"><jecs:locale  key="msgclassno.10"/></strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">


  <tr  class="trbg">
    <td width="20%" align="right"> <jecs:label  key="franchise.moeny"/>	</td>
    <td width="15%" align="right"><fmt:formatNumber value="${jbdMemberLinkCalcHist.franchiseMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right">&nbsp;</td>
    <td align="center">
    	<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a>
    </td>
    </tr>
  <tr>
    <td width="20%" align="right"><jecs:label  key="consumer.amount"/> </td>
    <td width="15%" align="right"><fmt:formatNumber value="${jbdMemberLinkCalcHist.consumerAmount}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center">
    	 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=consumerAmount">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a>
    </td>
    </tr>
  <tr  class="trbg">
    <td width="20%" align="right"><jecs:label  key="jbdMemberLinkCalcHist.deductMoney"/>	</td>
    <td width="15%" align="right"> <fmt:formatNumber value="${jbdMemberLinkCalcHist.deductMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center">
    	<%--<jecs:power powerCode="spWelfareSubPower">
    		<a href="spBounsDetail.html?strAction=spWelfareSubPower&userCode=${spBonusCalcHist.userCode }&wweek=${spBonusCalcHist.wweek }">查看明细</a>
    	</jecs:power>--%>
    </td>
    </tr>
  <tr>
    <td width="20%" align="right"> <jecs:label  key="jbdMemberLinkCalcHist.deductTax"/>	</td>
    <td width="15%" align="right"> <fmt:formatNumber value="${jbdMemberLinkCalcHist.deductTax}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center"><%--<a href="#">查看明细</a>--%> </td>
    </tr>
  <c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">  
  <tr>
    <td width="20%" align="right"> <jecs:label  key="bdSendRecord.networkMoney"/>		</td>
    <td width="15%" align="right"><fmt:formatNumber value="${jbdMemberLinkCalcHist.networkMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center"><a href="#">查看明细</a> </td>
    </tr>
   </c:if>
<c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">  
 
      <tr>
    <td width="20%" align="right"> <jecs:label  key="bd.currentDev.bd"/>		</td>
    <td width="15%" align="right"> <fmt:formatNumber value="${jbdMemberLinkCalcHist.currentDev}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center"></td>
    </tr>
    
    </c:if>
</table>



 <jecs:power powerCode="viewSomeData">
<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14"> <jecs:locale  key="bdSendRecord.bonusMoney"/></strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">
  <tr class="trbg">
    <td width="20%" align="right"> <jecs:label  key="bdSendRecord.bonusMoney"/></td>
    <td width="15%" align="right"><b><fmt:formatNumber value="${jbdMemberLinkCalcHist.sendMoney}" type="number" pattern="###,###,##0.00"/></b></td>
    <td width="15%" align="right"></td>
    <td align="center">&nbsp;</td>
    </tr>
</table>
</jecs:power>











</div>



