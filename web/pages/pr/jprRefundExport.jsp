<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/WEB-INF/tld/jecs.tld" prefix="jecs" %>
<%@ taglib uri="/WEB-INF/core.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt-rt.tld" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	</head>
	<body>
		<table width="100%" border="1">
			<tr>
				<td><jecs:locale key="prReFund.reFundOrderNo"/></td>
				<td><jecs:locale key="prReFund.originateOrderNo"/></td>
				<td><jecs:locale key="prRefund.createTime"/></td>
				<td><jecs:locale key="miMember.memberNo"/></td>
				<td><jecs:locale key="sys.user.username"/></td>
				<td><jecs:locale key="busi.orderReturn.warehouseNo"/></td>
				<td><jecs:locale key="pdProductsMain.editUreNo"/></td>
			</tr>
			<c:forEach items="${jprRefunds }" var="jprRefund">
			<tr>
				<td>${jprRefund.roNo }</td>
				<td>${jprRefund.jpoMemberOrder.memberOrderNo }</td>
				<td><fmt:formatDate value="${jprRefund.createTime }" pattern="yyyy-MM-dd hh:mm"/> </td>
				<td>${jprRefund.sysUser.userCode}</td>
				<td>${jprRefund.sysUser.userName }</td>
				<td>${jprRefund.resendSpNo}</td>
				<td>${jprRefund.intoUNo }</td>
			</tr>
				<c:forEach items="${jprRefund.jprReFundList }" var="reitem">
					<tr>
						<td>&nbsp;</td>
						<td>${reitem.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}</td>
						<td>${reitem.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productName }</td>
						<td>${reitem.qty }</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					</tr>
				</c:forEach>
			</c:forEach>
			
		</table>
	</body>
</html>



