<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
		<table width="100%" border="1">
			<tr>
				<td><jecs:locale key="poMemberOrder.memberOrderNo"/></td>
				<%-- <td><jecs:locale key="poMemberOrder.orderType"/></td> --%>
				<td><jecs:locale key="miMember.memberNo"/></td>
				<td><jecs:locale key="bdCalculatingSubDetail.name"/></td>
				
				<td><jecs:locale key="miMember.papertype"/></td>
				<td><jecs:locale key="miMember.papernumber"/></td>
				<td><jecs:locale key="bdBonusRankingReport.recommandPerson"/></td>
				<td><jecs:locale key="miMember.recommendName"/></td>
				<td><jecs:locale key="miMember.link"/></td>
				<td><jecs:locale key="miMember.linkName"/></td> 
				
				<td><jecs:locale key="busi.order.amount"/></td>
				<td><jecs:locale key="busi.order.jjAmount"/></td>
				
				<td><jecs:locale key="poMemberOrder.pvAmt"/></td>
				<td><jecs:locale key="pd.ordertype.prRefund"/></td>
				
				
				<td><jecs:locale key="fiPayData.createrName"/></td>
				
				<td><jecs:locale key="fiTransferAccount.checkerName"/></td>
				<td><jecs:locale key="pd.checkTime"/></td>
				<td><jecs:locale key="logType.C"/></td>
				
				<%-- <td><jecs:locale key="shipping.firstName"/></td>
				<td><jecs:locale key="shipping.lastName"/></td>
				<td><jecs:locale key="shipping.province"/></td>
				<td><jecs:locale key="miMember.shippingAddress2"/></td>
				<td><jecs:locale key="shipping.district"/></td>
				<td><jecs:locale key="shippingAddress"/></td>
				<td><jecs:locale key="alCompany.phone"/></td>
				<td><jecs:locale key="subStore.mobiletele"/></td> --%>
			</tr>
			<c:forEach items="${jpoMemberOrderList }" var="order">
			<tr>
				<td>${order.memberOrderNo }</td>
				
				<td>${order.sysUser.userCode }</td>
				<td>${order.sysUser.userName }</td>
				
				<td>${order.sysUser.jmiMember.papertype }</td>
				<td>${order.sysUser.jmiMember.papernumber }&nbsp;</td>
				<td>${order.sysUser.jmiMember.recommendNo }</td>
				<td>${order.recommandName }</td>
				<td>${order.sysUser.jmiMember.linkNo }</td>
				<td>${order.linkUserName }</td>
				
				<td>${order.amount }</td>
				<td>${order.jjAmount }</td>
				<td>${order.pvAmt }</td>
				<td>${order.isRetreatOrder }</td>
				
				<td>${order.orderUserCode }</td>
				<td>${order.checkUserCode }</td>
				<td>${order.checkTime }&nbsp;</td>
				<td>${order.checkDate }&nbsp;</td>
				
				
				<%-- <td>${order.firstName}</td>
				<td>${order.lastName }</td>
				<td>${order.province }</td>
				<td>${order.city }</td>
				<td>${order.district }</td>
				<td>${order.address }</td>
				<td>${order.phone }</td>
				<td>${order.mobiletele }</td> --%>
			</tr>
				<c:if test="${proInfo eq '1' }">
				<c:forEach items="${order.jpoMemberOrderList }" var="orderItem">
					<tr>
						<td>&nbsp;</td>
						<td>${orderItem.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}</td>
						<td>${orderItem.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productName }</td>
						<td>${orderItem.qty }</td>
						<td>${orderItem.qty*orderItem.price }</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;
						</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					</tr>
				</c:forEach>
				</c:if>
			</c:forEach>
		</table>
	</body>
</html>



