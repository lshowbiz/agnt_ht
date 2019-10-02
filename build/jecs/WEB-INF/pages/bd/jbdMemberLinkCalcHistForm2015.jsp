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
  
  <c:if test="${jbdMemberLinkCalcHist.wweek==201516 }">
  
   <tr class="trbg">
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.monthOwnPv"/></td>
    <td> ${jbdMemberLinkCalcHist.monthOwnPv} <!-- 个人当月新增业绩 --></td>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.histPv"/></td>
    <td>${jbdMemberLinkCalcHist.totalGroup-jbdMemberLinkCalcHist.weekGroupPv+jbdMemberLinkCalcHist.firstPv}<!-- 历史业绩 --></td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.cioType"/></td>
    <td><jecs:code listCode="cio.type" value="${jbdMemberLinkCalcHist.cioType }"/>
    <!-- 达成首席类型 --></td>
  </tr>
  
  
  
  <tr >
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.monthConsumerPv1"/></td>
    <td> ${jbdMemberLinkCalcHist.monthConsumerPv} <!-- 个人当月重消业绩 --></td>
    <td align="right"> <jecs:label  key="bdBonus.month_group_pv"/></td>
    <td>${jbdMemberLinkCalcHist.weekGroupPv}<!-- 当月新增业绩 --></td>
    <td align="right"><jecs:label  key="bdBonus.zyType"/></td>
    <td><jecs:code listCode="yesno" value="${jbdMemberLinkCalcHist.zyType }"/><!-- 是否达成卓越 --></td>
  </tr>
  <tr class="trbg">
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.monthAreaTotalPv1"/></td>
    <td>${jbdMemberLinkCalcHist.monthAreaTotalPv} <!-- 当月新增小区业绩 --></td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.totalGroup"/></td>
    <td>${jbdMemberLinkCalcHist.totalGroup}<!-- 累计业绩 --></td>
    <td align="right"> <jecs:label  key="jbdMemberLinkCalcHist.retainLevel"/></td>
    <td><jecs:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/><!-- 结算级别 --></td>
  </tr>
  <tr >
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.monthMaxPv"/></td>
    <td>${jbdMemberLinkCalcHist.monthMaxPv}<!-- 当月大区业绩 --> </td>
    <td align="right"><%-- <jecs:label  key="jbdMemberLinkCalcHist.retainGroup"/> --%></td>
    <td><%-- ${jbdMemberLinkCalcHist.retainGroup} --%> <!-- 当月小组业绩 --></td>
    <td align="right"> </td>
    <td> </td>
  </tr>
  
  </c:if>
  
  
  
  
  
  <c:if test="${jbdMemberLinkCalcHist.wweek>201516 && jbdMemberLinkCalcHist.wweek<201532  }">
  
<!--   结算级别：member_level  个人当月重消业绩：MONTH_CONSUMER_PV 
 当月新增大区业绩=奖金表. 当月新增小区业绩=奖金表.MONTH_AREA_TOTAL_PV    
  大区累计业绩=jbd_level.maxpart_pv 小区累计业绩=jbd_level.area_pv		 -->									
											
											
  
  
  
   <tr class="trbg">
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.retainLevel"/></td>
    <td> <jecs:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/></td>
    <td align="right">大区历史业绩:</td>
    <td>${jbdLevel.lastmaxpart_pv}</td>
    <td align="right">小区历史业绩:</td>
    <td>${jbdLevel.lastarea_pv}</td>
  </tr>
  
  
  
  <tr >
    <td align="right">个人当月新增业绩:</td>
    <td> ${jbdMemberLinkCalcHist.monthOwnPv} </td>
    <td align="right">当月新增大区业绩:</td>
    <td>${jbdMemberLinkCalcHist.monthMaxPv}</td>
    <td align="right">当月新增小区业绩:</td>
    <td>${jbdMemberLinkCalcHist.monthAreaTotalPv}</td>
  </tr>
  <tr class="trbg">
    <td align="right">个人当月重消业绩:</td>
    <td>${jbdMemberLinkCalcHist.monthConsumerPv} </td>
    <td align="right">大区累计业绩:</td>
    <td>${jbdLevel.maxpart_pv}</td>
    <td align="right">小区累计业绩:</td>
    <td>${jbdLevel.area_pv}</td>
  </tr>
  
  <tr class="trbg">
    <td align="right"><jecs:label  key="miMember.gradeType"/>  </td>
    <td> <jecs:code listCode="grade.type" value="${jbdMemberLinkCalcHist.gradeType}"/></td>
    <td align="right"></td>
    <td></td>
    <td align="right"></td>
    <td></td>
  </tr>
  
  </c:if>
  
  
    
  <c:if test="${jbdMemberLinkCalcHist.wweek>=201532 }">
  
											
  
  
  
   <tr class="trbg">
    <td align="right">销售级别:</td>
    <td> <jecs:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/></td>
    <td align="right"><jecs:label  key="miMember.gradeType"/> </td>
    <td><jecs:code listCode="grade.type" value="${jbdMemberLinkCalcHist.gradeType}"/></td>
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

  
  </c:if>
  
  
  
  
  
  
  
  
  
  
  <%-- <tr>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.retainGroup"/><!-- 级别保留判断小组业绩 --></td>
    <td>${jbdMemberLinkCalcHist.retainGroup} </td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.monthOwnPv"/><!--个人当月新增业绩  --></td>
    <td>${jbdMemberLinkCalcHist.monthOwnPv} </td>
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.totalGroup"/><!--累计业绩  --> </td>
    <td>${jbdMemberLinkCalcHist.totalGroup}  </td>
  </tr> --%>

</table>
<ul class="detail_til">
<li class="ico dtlef"></li>
<li class="dtbg"><strong class="blued f14"><jecs:locale  key="jbdMemberLinkCalcHist.pv"/></strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">



  <%-- <tr class="trbg">
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
    </tr> --%>
    
    
    
  <%-- <tr>
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
  </tr> --%>
  
  
  
  <tr class="trbg"><!-- 级差奖 --><!-- 改名为销售奖 -->
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
		 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureLeaderPv">
		<jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a> 
		</jecs:power>
    </td>
  </tr>
  <tr ><!-- 代数奖 --><!-- 改名为感恩奖 -->
    <td align="right"><jecs:label  key="jbdMemberLinkCalcHist.successLeaderPv.pv.daishu"/></td>
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
  <tr class="trbg"><!-- 卓越领导奖 -->
    <td align="right">
    
    <c:choose>
    	<c:when test="${jbdMemberLinkCalcHist.wweek >=201532}">
    		卓越奖(PV):
    	</c:when>
    	<c:otherwise>
			  <jecs:label  key="jbdMemberLinkCalcHist.successSalesPv.pv.leaderPv"/>
			</c:otherwise>
    </c:choose>
    
  
    
    
    
    </td>
    <td align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.successSalesPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			${jbdMemberLinkCalcHist.successSalesPv}    	</c:otherwise>
    </c:choose></td>
    <td align="right"><c:choose>
    	<c:when test="${empty jbdMemberLinkCalcHist.successSalesPv}">
    		0.00
    	</c:when>
    	<c:otherwise>
			  <fmt:formatNumber value="${jbdMemberLinkCalcHist.successSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	</c:otherwise>
    </c:choose></td>
    <td align="center">
    	
          <jecs:power powerCode="viewSomeData">
		 <a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=successSalesPv">
		<jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a> 
		</jecs:power>
    </td>
  </tr>
   <tr><!-- 推荐奖 -->
    <td align="right">
<%--     
  <c:if test="${jbdMemberLinkCalcHist.wweek==201516 }">
    
    
    </c:if>
  <c:if test="${jbdMemberLinkCalcHist.wweek>201516 }">
   
    </c:if> --%>
     
     
    <c:choose>
    	<c:when test="${jbdMemberLinkCalcHist.wweek ==201516}">
    		 <jecs:label  key="bdBonus.zyBonus"/>
    	</c:when>
    	<c:when test="${jbdMemberLinkCalcHist.wweek >=201532}">
    		 推广奖(PV):
    	</c:when>
    	<c:otherwise>
			  新人奖(PV):
			</c:otherwise>
    </c:choose>
     
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
   <tr class="trbg">
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
  <%-- <tr class="trbg">
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
  </tr> --%>
  <tr >
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
    <td align="right"> <jecs:label  key="franchise.moeny.pv"/></td>
    <td align="right"><c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.franchisePv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				${jbdMemberLinkCalcHist.franchisePv} 
	    	</c:otherwise>
	    </c:choose></td>
    <td align="right"> <c:choose>
	    	<c:when test="${empty jbdMemberLinkCalcHist.franchisePv}">
	    		0.00
	    	</c:when>
	    	<c:otherwise>
				 <fmt:formatNumber value="${jbdMemberLinkCalcHist.franchisePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	    	</c:otherwise>
	    </c:choose></td>
    <td align="center">
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/>
			</jecs:power>
    </td>
  </tr>
  
  <c:if test="${jbdMemberLinkCalcHist.wweek>=201604 }">
    <tr >
    <td align="right"> 服务奖(PV):</td>
    <td align="right">
	    	
	    <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv }" pattern="###,###,##0.##"></fmt:formatNumber>
	    
	    </td>
    <td align="right">
	    
	 <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
	</td>
    <td align="center">
          <jecs:power powerCode="viewSomeData">
			<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=servicePv">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/>
			</jecs:power>
    </td>
  </tr>
  
  
  </c:if>
  
  
  <tr <c:if test="${jbdMemberLinkCalcHist.wweek>=201604 }">class="trbg"</c:if> >
    <td align="right"><b> <jecs:label  key="bd.send.bounspv"/></b></td>
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
<li class="dtbg"><strong class="blued f14"><jecs:locale  key="jbdMemberLinkCalcHist.payment"/></strong></li>
<li class="ico dtrig"></li>
</ul><p class="cb bt2"></p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="detail_tab">


<%--   <tr  class="trbg">
    <td width="20%" align="right"> <jecs:label  key="franchise.moeny"/>	</td>
    <td width="15%" align="right"><fmt:formatNumber value="${jbdMemberLinkCalcHist.franchiseMoney}" type="number" pattern="###,###,##0.00"/></td>
    <td width="15%" align="right">&nbsp;</td>
    <td align="center">
    	<a href="jbdDetail.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
	   <jecs:locale key="menu.editJbdMemberLinkCalcHis"/></a>
    </td>
    </tr> --%>
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
<c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">  
 
      <tr class="trbg">
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



