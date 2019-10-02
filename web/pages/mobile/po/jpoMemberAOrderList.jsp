<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta name="decorator" content="mobile"/>
<script>
	function editJpoMemberAOrder(moId){
   		<jecs:power powerCode="editPoMemberAOrder">
			location="editJpoMemberAOrder.html?strAction=editPoMemberAOrder&moId="+moId;
		</jecs:power>
	}
</script>

<jecs:power powerCode="addPoMemberAOrder">
	<input type="button" onclick="location='${ctx}/editJpoMemberAOrder.html?strAction=addPoMemberAOrder'" value="<jecs:locale  key='member.new.num'/>" />
</jecs:power>

<c:if test="${fn:length(jpoMemberOrderList) == 0}">
	暂无订单！
</c:if>

<ul data-role="listview" style="margin: 0px;">
	<c:forEach items="${jpoMemberOrderList}" var="jpoMemberOrder">
	  <li>
	    <jecs:power powerCode="viewPoMemberOrder">
			<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}">
				<h3>订单编号：${jpoMemberOrder.memberOrderNo}</h3>
			    <p>
			    	订单类型：<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
			    </p>
			    <p>
			    	会员编号：${jpoMemberOrder.sysUser.userCode}
			    </p>
			    <p>
			    	总金额：${jpoMemberOrder.amount}
			    </p>
			    <p>
			    	总PV：${jpoMemberOrder.pvAmt}
			    </p>
			</a>
	    </jecs:power>
	    
	    <c:if test="${jpoMemberOrder.status=='1' && jpoMemberOrder.isPay!='1' && jpoMemberOrder.submitStatus=='1'}">
	   		<jecs:power powerCode="editPoMemberAOrder">
	   		<p class="pPadding" onclick="editJpoMemberAOrder('${jpoMemberOrder.moId}')">
		   		编辑<img src="${ctx}/images/newIcons/pencil_16.gif" /> 
			</p>
			</jecs:power>
		</c:if>
	    
		<%-- <jecs:power powerCode="jfiPay99bill">
			<c:if test="${jpoMemberOrder.status=='1'}">
			<p class="pPadding" onclick="location='jfiPay99bill.html?strAction=jfiPay99bill&orderId=${jpoMemberOrder.moId}&flag=1';">
			现在支付<img src="${ctx}/images/newIcons/coins.gif" /> 
			</p>
			</c:if>
		</jecs:power> --%>
	  </li>
	</c:forEach>
</ul>