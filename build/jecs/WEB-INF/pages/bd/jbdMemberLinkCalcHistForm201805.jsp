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
  	  <jecs:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" /><!-- 期别 -->
    </td>
    <td width="15%" align="right"> <jecs:label  key="bdNetWorkCostReport.memberCH"/></td>
    <td width="15%">
    	${jbdMemberLinkCalcHist.userCode }<!-- 会员编号 -->
    </td>
    <td width="15%" align="right"><jecs:label  key="bdNetWorkCostReport.rateCH"/></td>
    <td width="15%">
    	${jbdMemberLinkCalcHist.exchangeRate} <!-- 汇率 -->
    </td>
  </tr>
  
  <tr>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.honorStar"/></td>
    <td><jecs:code  listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar}"/><!-- 荣誉奖衔 --></td>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.passStar"/></td>
    <td><jecs:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar}"/><!-- 合格奖衔 --></td>
    <td align="right"><jecs:label  key="miMember.store.type"/></td>
    <td> <jecs:code listCode="isstore" value="${jbdMemberLinkCalcHist.isstore}"/> <!-- 加盟店类型 --></td>
  </tr>
  
  <tr class="trbg">
    <td align="right">销售级别:</td>
    <td> <jecs:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/></td>
    <td align="right"><%-- <jecs:label  key="miMember.gradeType"/>  --%></td>
    <td><%-- <jecs:code listCode="grade.type" value="${jbdMemberLinkCalcHist.gradeType}"/> --%></td>
    <td align="right">个人当月重消业绩:</td>
    <td>${jbdMemberLinkCalcHist.monthConsumerPv}</td>
  </tr>
  
  
  
  <tr >
    <td align="right">个人当月新增业绩:</td>
    <td> ${jbdMemberLinkCalcHist.monthOwnPv} </td>
    <td align="right">当月新增大区业绩:</td>
    <td>${jbdMemberLinkCalcHist.monthMaxPv}</td>
    <td align="right">当月新增小区业绩:</td>
    <td>${jbdMemberLinkCalcHist.monthAreaTotalPv}</td>
  </tr>
  

</table>
<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14">总奖金</strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">


  <tr class="trbg"><!-- 销售奖 -->
    <td width="20%"  align="right"><jecs:label  key="jbdMemberLinkCalcHist.ventureLeaderPv37.pv.jicha"/></td>
    <td  width="15%" align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			${jbdMemberLinkCalcHist.ventureLeaderPv}    	</c:otherwise>
    </c:choose></td>
    <td  width="15%"  align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			  <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureLeaderPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	</c:otherwise>
    </c:choose></td>
    <td align="center">
    	
          <jecs:power powerCode="viewSomeData">
		 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureLeaderPv201805">
		<jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a> 
		</jecs:power>
    </td>
  </tr>
  
  <tr ><!-- 代数奖 --><!-- 改名为感恩奖 -->
    <td align="right">
    	<c:if test="${jbdMemberLinkCalcHist.wweek<201905 }">
    		<jecs:label  key="jbdMemberLinkCalcHist.successLeaderPv.pv.daishu"/> 
		</c:if>
		<c:if test="${jbdMemberLinkCalcHist.wweek>=201905}">
			领导奖(PV):
		</c:if>
    
    </td>
    <td align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.successLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			${jbdMemberLinkCalcHist.successLeaderPv}    	</c:otherwise>
    </c:choose></td>
    <td align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.successLeaderPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			  <fmt:formatNumber value="${jbdMemberLinkCalcHist.successLeaderPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	</c:otherwise>
    </c:choose></td>
    <td align="center">
    	
          <jecs:power powerCode="viewSomeData">
		 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=successLeaderPv">
		<jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a> 
		</jecs:power>
    </td>
  </tr>
  
  <c:if test="${jbdMemberLinkCalcHist.wweek<201701 }">
  
  <tr class="trbg">
    <td align="right"> 服务奖(PV):</td>
    <td align="right">
	    	
	    <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv }" pattern="###,###,##0.##"></fmt:formatNumber>
	    
	    </td>
    <td align="right">
	    
	 <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	</td>
    <td align="center">
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=servicePv201607">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/>
			</jecs:power>
    </td>
  </tr>
   <tr><!-- 推荐奖 -->
    <td align="right">
      	推广奖(PV):
     </td>
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
  
  </c:if>
  
  
  <c:if test="${jbdMemberLinkCalcHist.wweek<201905 }">
	 <tr class="trbg" >
		<td align="right">报单奖(PV):</td>
		<td align="right">
			<c:set var="bdj" value="0"></c:set>
			<c:choose>
				<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
	    		
	    		</c:when>
				<c:otherwise>
					<c:set var="bdj"
						value="${bdj + jbdMemberLinkCalcHist.storeExpandPv}"></c:set>
				</c:otherwise>
			</c:choose> <c:choose>
				<c:when test="${empty jbdMemberLinkCalcHist.franchisePv}">
	    		
	    		</c:when>
				<c:otherwise>
					<c:set var="bdj"
						value="${bdj + jbdMemberLinkCalcHist.franchisePv}"></c:set>
				</c:otherwise>
			</c:choose> ${bdj}</td>
		<td align="right"><c:choose>
				<c:when test="${empty bdj}">
	    		0.00
	    	</c:when>
				<c:otherwise>
					<fmt:formatNumber value="${bdj*jbdMemberLinkCalcHist.exchangeRate}"
						type="number" pattern="###,###,##0.00" />
				</c:otherwise>
			</c:choose>
		</td>
		<td align="center"><jecs:power powerCode="viewSomeData">
				<a
					href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=bdjPv201607">
					<jecs:locale key="menu.editJbdMemberLinkCalcHis" /> </a>
			</jecs:power></td>
	</tr> 
  </c:if>
	
	<c:if test="${jbdMemberLinkCalcHist.wweek>=201905 }">
	  <%-- <tr >
	    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.storeRecommendPv.pv"/></td>
	    <td align="right">
	    	<c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					${jbdMemberLinkCalcHist.storeRecommendPv} 
		    	</c:otherwise>
		    </c:choose>
		   </td>
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
	  </tr> --%>
	  
	  
	  	<tr class="trbg"><!-- 销售奖 -->
		    <td width="20%"  align="right">推广奖(PV):</td>
		    <td  width="15%" align="right"><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					${jbdMemberLinkCalcHist.recommendBonusPv}    	</c:otherwise>
		    </c:choose></td>
		    <td  width="15%"  align="right"><c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					  <fmt:formatNumber value="${jbdMemberLinkCalcHist.recommendBonusPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	</c:otherwise>
		    </c:choose></td>
		    <td align="center">
		    	
		          <jecs:power powerCode="viewSomeData">
				 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=popularizeBonusPv">
				<jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a> 
				</jecs:power>
		    </td>
  		</tr>
   </c:if>
	
	
	
	
	
	
	
	<c:if test="${jbdMemberLinkCalcHist.wweek>=201701 }">
  <tr > 
    <td align="right">
      	重消奖(PV):
     </td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.successSalesPv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.successSalesPv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td align="right">   <c:choose>
		    	<c:when test="${empty jbdMemberLinkCalcHist.successSalesPv}">
		    		0.00
		    	</c:when>
		    	<c:otherwise>
					<fmt:formatNumber value="${jbdMemberLinkCalcHist.successSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
		    	</c:otherwise>
		    </c:choose></td>
    <td align="center">
	     
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=successSalesPv">
			<jecs:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
			</jecs:power>
    </td>
  </tr> 
</c:if>


	<tr  class="trbg"  >
	    <td align="right"><b>总奖金PV:</b></td>
	    <td align="right"><b>${jbdMemberLinkCalcHist.bounsPv+jbdMemberLinkCalcHist.franchisePv}</b></td>
	    <td align="right">
	    	<jecs:power powerCode="viewSomeData">
	    	<b><fmt:formatNumber value="${jbdMemberLinkCalcHist.bounsMoney+jbdMemberLinkCalcHist.franchisePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/></b>
	    	</jecs:power>
	    </td>
	    <td align="center"></td>
  </tr>
</table>





<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14">扣款项</strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">

  <tr class="trbg">
    <td width="20%" align="right"><jecs:label  key="consumer.amount"/> </td>
    <td width="15%" align="right"><fmt:formatNumber value="${jbdMemberLinkCalcHist.consumerAmount}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center">
    	 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=consumerAmount">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a>
    </td>
    </tr>
  <tr  >
    <td width="20%" align="right"><jecs:label  key="jbdMemberLinkCalcHist.deductMoney"/>	</td>
    <td width="15%" align="right"> <fmt:formatNumber value="${jbdMemberLinkCalcHist.deductMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center">
    	<jecs:power powerCode="bdBounsDeducts">
    		<a href="bdBounsDeducts.html?userCode=${jbdMemberLinkCalcHist.userCode }">查看明细</a>
    	</jecs:power>
    </td>
    </tr>
  <tr class="trbg">
    <td width="20%" align="right"> <jecs:label  key="jbdMemberLinkCalcHist.deductTax"/>	</td>
    <td width="15%" align="right"> <fmt:formatNumber value="${jbdMemberLinkCalcHist.deductTax}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center"><%--<a href="#">查看明细</a>--%> </td>
    </tr>
  <c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">  
  <tr >
    <td width="20%" align="right"> <jecs:label  key="bdSendRecord.networkMoney"/>		</td>
    <td width="15%" align="right"><fmt:formatNumber value="${jbdMemberLinkCalcHist.networkMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right"></td>
    <td align="center"><!-- <a href="#">查看明细</a> --> </td>
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
	    <td width="20%" align="right">应发总奖金:</td>
	    <td width="15%" align="right"><b><fmt:formatNumber value="${jbdMemberLinkCalcHist.sendMoney+jbdMemberLinkCalcHist.currentDev}" type="number" pattern="###,###,##0.00"/></b></td>
	    <td width="15%" align="right"></td>
	    <td align="center">&nbsp;</td>
	  <tr>
	    <td width="20%" align="right">电子存折:</td>
	    <td width="15%" align="right"><b><fmt:formatNumber value="${jbdMemberLinkCalcHist.sendMoney}" type="number" pattern="###,###,##0.00"/></b></td>
	    <td width="15%" align="right"></td>
	    <td align="center">&nbsp;</td>
	  </tr>
 
	<c:if test="${jbdMemberLinkCalcHist.wweek >=201805 }">
    <tr class="trbg">
	    <td width="20%" align="right"> <jecs:label  key="bd.currentDev.bd"/>		</td>
	    <td width="15%" align="right"> <fmt:formatNumber value="${jbdMemberLinkCalcHist.currentDev}" type="number" pattern="###,###,##0.00"/></td>
	    <td width="15%" align="right"></td>
	    <td align="center"></td>
    </tr>
    </c:if>
</table>
</jecs:power>

</div>



