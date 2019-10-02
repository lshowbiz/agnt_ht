<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<style media="print">
	.noPrint { 
		display: none;
	}
</style>
<body>
<DIV STYLE="position: absolute; top: 38mm; left: 25mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatDate value="${jpoMemberOrder.checkTime }" pattern="yyyy-MM-dd"/></DIV>
<DIV STYLE="position: absolute; top: 47mm; left: 30mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrder.memberOrderNo }</DIV>

<c:if test="${not empty jpoMemberOrder.transactionNumber }">
<DIV STYLE="position: absolute; top: 47mm; left: 205mm; font-size: 12pt ; font-family: Arial; color: #000020; ">Transaction No.:${jpoMemberOrder.transactionNumber }</DIV>
</c:if>

<DIV STYLE="position: absolute; top: 60mm; left: 20mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrder.sysUser.userName }</DIV>
<DIV STYLE="position: absolute; top: 78mm; left: 20mm; font-size: 12pt ; font-family: Arial; color: #000020; "><c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoMemberOrder.sysUser.jmiMember.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>&nbsp;${jpoMemberOrder.sysUser.jmiMember.city }<br/>${jpoMemberOrder.sysUser.jmiMember.district }&nbsp;${jpoMemberOrder.sysUser.jmiMember.town }
						<br/>${jpoMemberOrder.sysUser.jmiMember.address }</DIV>
<c:if test="${jpoMemberOrder.pickup!='1' }">
<DIV STYLE="position: absolute; top: 60mm; left: 190mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrder.firstName }&nbsp;${jpoMemberOrder.lastName }</DIV>
<DIV STYLE="position: absolute; top: 78mm; left: 190mm; font-size: 12pt ; font-family: Arial; color: #000020; "><c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoMemberOrder.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>&nbsp;${jpoMemberOrder.city }<br/>${jpoMemberOrder.district }&nbsp;${jpoMemberOrder.town }
						<br/>${jpoMemberOrder.address }</DIV>
</c:if>
<DIV STYLE="position: absolute; top: 101mm; left: 30mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrder.sysUser.userCode }</DIV>
<DIV STYLE="position: absolute; top: 101mm; left: 200mm; font-size: 12pt ; font-family: Arial; color: #000020; "><jecs:code listCode="po.shipment.type" value="${jpoMemberOrder.pickup}"/></DIV>
<DIV STYLE="position: absolute; top: 106mm; left: 30mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrder.sysUser.jmiMember.papernumber }</DIV>
<DIV STYLE="position: absolute; top: 110mm; left: 30mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrder.sysUser.jmiMember.mobiletele }</DIV>

<c:set var="poMemberOrderQtyTotal" value="0"/>
<c:set var="top" value="125"/>
<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
<DIV STYLE="position: absolute; top: ${top }mm; left: 0mm; font-size: 9pt ; font-family: Arial; color: #000020; ">${jpoMemberOrderDetail.jpmProductSale.jpmProduct.productNo }</DIV>
<DIV STYLE="position: absolute; top: ${top }mm; left: 50mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrderDetail.qty }</DIV>
<DIV STYLE="position: absolute; top: ${top }mm; left: 80mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrderDetail.jpmProductSale.productName }</DIV>
<DIV STYLE="position: absolute; top: ${top }mm; left: 180mm; font-size: 12pt ; font-family: Arial; color: #000020; ">${jpoMemberOrderDetail.pv }</DIV>
<DIV STYLE="position: absolute; top: ${top }mm; left: 205mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${jpoMemberOrderDetail.qty*jpoMemberOrderDetail.pv }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="position: absolute; top: ${top }mm; left: 235mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${jpoMemberOrderDetail.price }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="position: absolute; top: ${top }mm; left: 280mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${jpoMemberOrderDetail.qty*jpoMemberOrderDetail.price }" type="number" pattern="###,###,##0.00"/></DIV>
<c:set var="poMemberOrderQtyTotal" value="${poMemberOrderQtyTotal + jpoMemberOrderDetail.qty }"/>
<c:set var="top" value="${top + 10 }"/>
</c:forEach>

<DIV STYLE="position: absolute; top: 210mm; left: 0mm; font-size: 12pt ; font-family: Arial; color: #000020; ">Thank you for your continued patronage!</DIV>
<DIV STYLE="position: absolute; top: 192mm; left: 175mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${poMemberOrderQtyTotal }" type="number" pattern="###,###,##0"/></DIV>

<div style="float:right; text-align:right; position: absolute; top: 192mm; left: 260mm; font-size: 12pt ; font-family: Arial; color: #000020; ">
<DIV STYLE="top: 192mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${jpoMemberOrder.amount }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="top: 196mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${elf:append(jpoMemberOrder.amount,'' ) / 1.12 }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="top: 200mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${elf:append(jpoMemberOrder.amount,'' )-elf:append(jpoMemberOrder.amount,'' )/1.12 }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="top: 204mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; ">0.00</DIV>
<DIV STYLE="top: 208mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; ">0.00</DIV>
<DIV STYLE="top: 212mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; ">0.00</DIV>
<DIV STYLE="top: 216mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${jpoMemberOrder.amount }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="top: 220mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; "><fmt:formatNumber value="${jpoMemberOrder.amount }" type="number" pattern="###,###,##0.00"/></DIV>
<DIV STYLE="top: 224mm; left: 250mm; font-size: 12pt ; font-family: Arial; color: #000020; ">0.00</DIV>
</div>

<DIV id="print" class="noPrint"><input type="button" value="<jecs:locale key="button.print"/>" onclick="window.print();"></DIV>
</body>
</html>
<script language="javascript">
window.print();
</script>